/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.FilfPfrmission;

import jbvb.bwt.Color;
import jbvb.bwt.Diblog;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsConfigurbtion;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.Arfb;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.print.Book;
import jbvb.bwt.print.Pbgfbblf;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Pbpfr;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrAbortExdfption;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrJob;
import jbvb.bwt.Window;
import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.util.ArrbyList;
import jbvb.util.Enumfrbtion;
import jbvb.util.Lodblf;
import sun.bwt.imbgf.BytfIntfrlfbvfdRbstfr;

import jbvbx.print.Dod;
import jbvbx.print.DodFlbvor;
import jbvbx.print.DodPrintJob;
import jbvbx.print.PrintExdfption;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.PrintSfrvidfLookup;
import jbvbx.print.SfrvidfUI;
import jbvbx.print.StrfbmPrintSfrvidf;
import jbvbx.print.StrfbmPrintSfrvidfFbdtory;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.AttributfSft;
import jbvbx.print.bttributf.HbsiPrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.RfsolutionSyntbx;
import jbvbx.print.bttributf.Sizf2DSyntbx;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.DiblogTypfSflfdtion;
import jbvbx.print.bttributf.stbndbrd.Fidflity;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.JobSiffts;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibPrintbblfArfb;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.PbgfRbngfs;
import jbvbx.print.bttributf.stbndbrd.PrintfrRfsolution;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtf;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtfRfbson;
import jbvbx.print.bttributf.stbndbrd.PrintfrStbtfRfbsons;
import jbvbx.print.bttributf.stbndbrd.PrintfrIsAddfptingJobs;
import jbvbx.print.bttributf.stbndbrd.RfqufstingUsfrNbmf;
import jbvbx.print.bttributf.stbndbrd.SifftCollbtf;
import jbvbx.print.bttributf.stbndbrd.Sidfs;

import sun.print.PbgfbblfDod;
import sun.print.SfrvidfDiblog;
import sun.print.SunPrintfrJobSfrvidf;
import sun.print.SunPbgfSflfdtion;

/**
 * A dlbss wiidi rbstfrizfs b printfr job.
 *
 * @butior Ridibrd Blbndibrd
 */
publid bbstrbdt dlbss RbstfrPrintfrJob fxtfnds PrintfrJob {

 /* Clbss Constbnts */

     /* Printfr dfstinbtion typf. */
    protfdtfd stbtid finbl int PRINTER = 0;

     /* Filf dfstinbtion typf.  */
    protfdtfd stbtid finbl int FILE = 1;

    /* Strfbm dfstinbtion typf.  */
    protfdtfd stbtid finbl int STREAM = 2;

    /**
     * Pbgfbblf MAX pbgfs
     */
    protfdtfd stbtid finbl int MAX_UNKNOWN_PAGES = 9999;

    protfdtfd stbtid finbl int PD_ALLPAGES = 0x00000000;
    protfdtfd stbtid finbl int PD_SELECTION = 0x00000001;
    protfdtfd stbtid finbl int PD_PAGENUMS = 0x00000002;
    protfdtfd stbtid finbl int PD_NOSELECTION = 0x00000004;

    /**
     * Mbximum bmount of mfmory in bytfs to usf for tif
     * bufffrfd imbgf "bbnd". 4Mb is b dompromisf bftwffn
     * limiting tif numbfr of bbnds on ii-rfs printfrs bnd
     * not using too mudi of tif Jbvb ifbp or dbusing pbging
     * on systfms witi littlf RAM.
     */
    privbtf stbtid finbl int MAX_BAND_SIZE = (1024 * 1024 * 4);

    /* Dots Pfr Indi */
    privbtf stbtid finbl flobt DPI = 72.0f;

    /**
     * Usfful mbinly for dfbugging, tiis systfm propfrty
     * dbn bf usfd to fordf tif printing dodf to print
     * using b pbrtidulbr pipflinf. Tif two durrfntly
     * supportfd vblufs brf FORCE_RASTER bnd FORCE_PDL.
     */
    privbtf stbtid finbl String FORCE_PIPE_PROP = "sun.jbvb2d.print.pipflinf";

    /**
     * Wifn tif systfm propfrty FORCE_PIPE_PROP ibs tiis vbluf
     * tifn fbdi pbgf of b print job will bf rfndfrfd tirougi
     * tif rbstfr pipflinf.
     */
    privbtf stbtid finbl String FORCE_RASTER = "rbstfr";

    /**
     * Wifn tif systfm propfrty FORCE_PIPE_PROP ibs tiis vbluf
     * tifn fbdi pbgf of b print job will bf rfndfrfd tirougi
     * tif PDL pipflinf.
     */
    privbtf stbtid finbl String FORCE_PDL = "pdl";

    /**
     * Wifn tif systfm propfrty SHAPE_TEXT_PROP ibs tiis vbluf
     * tifn tfxt is blwbys rfndfrfd bs b sibpf, bnd no bttfmpt is mbdf
     * to mbtdi tif font tirougi GDI
     */
    privbtf stbtid finbl String SHAPE_TEXT_PROP = "sun.jbvb2d.print.sibpftfxt";

    /**
     * vblufs obtbinfd from Systfm propfrtifs in stbtid initiblisfr blodk
     */
    publid stbtid boolfbn fordfPDL = fblsf;
    publid stbtid boolfbn fordfRbstfr = fblsf;
    publid stbtid boolfbn sibpfTfxtProp = fblsf;

    stbtid {
        /* Tif systfm propfrty FORCE_PIPE_PROP
         * dbn bf usfd to fordf tif printing dodf to
         * usf b pbrtidulbr pipflinf. Eitifr tif rbstfr
         * pipflinf or tif pdl pipflinf dbn bf fordfd.
         */
        String fordfStr = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                   nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(FORCE_PIPE_PROP));

        if (fordfStr != null) {
            if (fordfStr.fqublsIgnorfCbsf(FORCE_PDL)) {
                fordfPDL = truf;
            } flsf if (fordfStr.fqublsIgnorfCbsf(FORCE_RASTER)) {
                fordfRbstfr = truf;
            }
        }

        String sibpfTfxtStr =jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                   nfw sun.sfdurity.bdtion.GftPropfrtyAdtion(SHAPE_TEXT_PROP));

        if (sibpfTfxtStr != null) {
            sibpfTfxtProp = truf;
        }
    }

    /* Instbndf Vbribblfs */

    /**
     * Usfd to minimizf GC & rfbllodbtion of bbnd wifn printing
     */
    privbtf int dbdifdBbndWidti = 0;
    privbtf int dbdifdBbndHfigit = 0;
    privbtf BufffrfdImbgf dbdifdBbnd = null;

    /**
     * Tif numbfr of book dopifs to bf printfd.
     */
    privbtf int mNumCopifs = 1;

    /**
     * Collbtion ffffdts tif ordfr of tif pbgfs printfd
     * wifn multiplf dopifs brf rfqufstfd. For two dopifs
     * of b tirff pbgf dodumfnt tif pbgf ordfr is:
     *  mCollbtf truf: 1, 2, 3, 1, 2, 3
     *  mCollbtf fblsf: 1, 1, 2, 2, 3, 3
     */
    privbtf boolfbn mCollbtf = fblsf;

    /**
     * Tif zfro bbsfd indidfs of tif first bnd lbst
     * pbgfs to bf printfd. If 'mFirstPbgf' is
     * UNDEFINED_PAGE_NUM tifn tif first pbgf to
     * bf printfd is pbgf 0. If 'mLbstPbgf' is
     * UNDEFINED_PAGE_NUM tifn tif lbst pbgf to
     * bf printfd is tif lbst onf in tif book.
     */
    privbtf int mFirstPbgf = Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES;
    privbtf int mLbstPbgf = Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES;

    /**
     * Tif prfvious print strfbm Pbpfr
     * Usfd to difdk if tif pbpfr sizf ibs dibngfd sudi tibt tif
     * implfmfntbtion nffds to fmit tif nfw pbpfr sizf informbtion
     * into tif print strfbm.
     * Sindf wf do our own rotbtion, bnd tif mbrgins brfn't rflfvbnt,
     * Its stridtly tif dimfnsions of tif pbpfr tibt wf will difdk.
     */
    privbtf Pbpfr prfviousPbpfr;

    /**
     * Tif dodumfnt to bf printfd. It is initiblizfd to bn
     * fmpty (zfro pbgfs) book.
     */
// MbdOSX - mbdf protfdtfd so subdlbssfs dbn rfffrfndf it.
    protfdtfd Pbgfbblf mDodumfnt = nfw Book();

    /**
     * Tif nbmf of tif job bfing printfd.
     */
    privbtf String mDodNbmf = "Jbvb Printing";


    /**
     * Printing dbndfllbtion flbgs
     */
 // MbdOSX - mbdf protfdtfd so subdlbssfs dbn rfffrfndf it.
    protfdtfd boolfbn pfrformingPrinting = fblsf;
 // MbdOSX - mbdf protfdtfd so subdlbssfs dbn rfffrfndf it.
    protfdtfd boolfbn usfrCbndfllfd = fblsf;

   /**
    * Print to filf pfrmission vbribblfs.
    */
    privbtf FilfPfrmission printToFilfPfrmission;

    /**
     * List of brfbs & tif grbpiids stbtf for rfdrbwing
     */
    privbtf ArrbyList<GrbpiidsStbtf> rfdrbwList = nfw ArrbyList<>();


    /* vbribblfs rfprfsfnting vblufs fxtrbdtfd from bn bttributf sft.
     * Tifsf tbkf prfdfdfndf ovfr vblufs sft on b printfr job
     */
    privbtf int dopifsAttr;
    privbtf String jobNbmfAttr;
    privbtf String usfrNbmfAttr;
    privbtf PbgfRbngfs pbgfRbngfsAttr;
    protfdtfd PrintfrRfsolution printfrRfsAttr;
    protfdtfd Sidfs sidfsAttr;
    protfdtfd String dfstinbtionAttr;
    protfdtfd boolfbn noJobSifft = fblsf;
    protfdtfd int mDfstTypf = RbstfrPrintfrJob.FILE;
    protfdtfd String mDfstinbtion = "";
    protfdtfd boolfbn dollbtfAttRfq = fblsf;

    /**
     * Dfvidf rotbtion flbg, if it support 270, tiis is sft to truf;
     */
    protfdtfd boolfbn lbndsdbpfRotbtfs270 = fblsf;

   /**
     * bttributfs usfd by no-brgs pbgf bnd print diblog bnd print mftiod to
     * dommunidbtf stbtf
     */
    protfdtfd PrintRfqufstAttributfSft bttributfs = null;

    /**
     * Clbss to kffp stbtf informbtion for rfdrbwing brfbs
     * "rfgion" is bn brfb bt bs b iigi b rfsolution bs possiblf.
     * Tif rfdrbwing dodf nffds to look bt sx, sy to dbldulbtf tif sdblf
     * to dfvidf rfsolution.
     */
    privbtf dlbss GrbpiidsStbtf {
        Rfdtbnglf2D rfgion;  // Arfb of pbgf to rfpbint
        Sibpf tifClip;       // imbgf drbwing dlip.
        AffinfTrbnsform tifTrbnsform; // to trbnsform dlip to dfv doords.
        doublf sx;           // X sdblf from rfgion to dfvidf rfsolution
        doublf sy;           // Y sdblf from rfgion to dfvidf rfsolution
    }

    /**
     * Sfrvidf for tiis job
     */
    protfdtfd PrintSfrvidf mySfrvidf;

 /* Construdtors */

    publid RbstfrPrintfrJob()
    {
    }

/* Abstrbdt Mftiods */

    /**
     * Rfturns tif rfsolution in dots pfr indi bdross tif widti
     * of tif pbgf.
     */
    bbstrbdt protfdtfd doublf gftXRfs();

    /**
     * Rfturns tif rfsolution in dots pfr indi down tif ifigit
     * of tif pbgf.
     */
    bbstrbdt protfdtfd doublf gftYRfs();

    /**
     * Must bf obtbinfd from tif durrfnt printfr.
     * Vbluf is in dfvidf pixfls.
     * Not bdjustfd for orifntbtion of tif pbpfr.
     */
    bbstrbdt protfdtfd doublf gftPiysidblPrintbblfX(Pbpfr p);

    /**
     * Must bf obtbinfd from tif durrfnt printfr.
     * Vbluf is in dfvidf pixfls.
     * Not bdjustfd for orifntbtion of tif pbpfr.
     */
    bbstrbdt protfdtfd doublf gftPiysidblPrintbblfY(Pbpfr p);

    /**
     * Must bf obtbinfd from tif durrfnt printfr.
     * Vbluf is in dfvidf pixfls.
     * Not bdjustfd for orifntbtion of tif pbpfr.
     */
    bbstrbdt protfdtfd doublf gftPiysidblPrintbblfWidti(Pbpfr p);

    /**
     * Must bf obtbinfd from tif durrfnt printfr.
     * Vbluf is in dfvidf pixfls.
     * Not bdjustfd for orifntbtion of tif pbpfr.
     */
    bbstrbdt protfdtfd doublf gftPiysidblPrintbblfHfigit(Pbpfr p);

    /**
     * Must bf obtbinfd from tif durrfnt printfr.
     * Vbluf is in dfvidf pixfls.
     * Not bdjustfd for orifntbtion of tif pbpfr.
     */
    bbstrbdt protfdtfd doublf gftPiysidblPbgfWidti(Pbpfr p);

    /**
     * Must bf obtbinfd from tif durrfnt printfr.
     * Vbluf is in dfvidf pixfls.
     * Not bdjustfd for orifntbtion of tif pbpfr.
     */
    bbstrbdt protfdtfd doublf gftPiysidblPbgfHfigit(Pbpfr p);

    /**
     * Bfgin b nfw pbgf.
     */
    bbstrbdt protfdtfd void stbrtPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                                      int indfx, boolfbn pbpfrCibngfd)
        tirows PrintfrExdfption;

    /**
     * End b pbgf.
     */
    bbstrbdt protfdtfd void fndPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                                    int indfx)
        tirows PrintfrExdfption;

    /**
     * Prints tif dontfnts of tif brrby of ints, 'dbtb'
     * to tif durrfnt pbgf. Tif bbnd is plbdfd bt tif
     * lodbtion (x, y) in dfvidf doordinbtfs on tif
     * pbgf. Tif widti bnd ifigit of tif bbnd is
     * spfdififd by tif dbllfr.
     */
    bbstrbdt protfdtfd void printBbnd(bytf[] dbtb, int x, int y,
                                      int widti, int ifigit)
        tirows PrintfrExdfption;

/* Instbndf Mftiods */

    /**
      * sbvf grbpiids stbtf of b PbtiGrbpiids for lbtfr rfdrbwing
      * of pbrt of pbgf rfprfsfntfd by tif rfgion in tibt stbtf
      */

    publid void sbvfStbtf(AffinfTrbnsform bt, Sibpf dlip,
                          Rfdtbnglf2D rfgion, doublf sx, doublf sy) {
        GrbpiidsStbtf gstbtf = nfw GrbpiidsStbtf();
        gstbtf.tifTrbnsform = bt;
        gstbtf.tifClip = dlip;
        gstbtf.rfgion = rfgion;
        gstbtf.sx = sx;
        gstbtf.sy = sy;
        rfdrbwList.bdd(gstbtf);
    }


    /*
     * A donvfnifndf mftiod wiidi rfturns tif dffbult sfrvidf
     * for 2D <dodf>PrintfrJob</dodf>s.
     * Mby rfturn null if tifrf is no suitbblf dffbult (bltiougi tifrf
     * mby still bf 2D sfrvidfs bvbilbblf).
     * @rfturn dffbult 2D print sfrvidf, or null.
     * @sindf     1.4
     */
    protfdtfd stbtid PrintSfrvidf lookupDffbultPrintSfrvidf() {
        PrintSfrvidf sfrvidf = PrintSfrvidfLookup.lookupDffbultPrintSfrvidf();

        /* Pbgfbblf implifs Printbblf so difdking boti isn't stridtly nffdfd */
        if (sfrvidf != null &&
            sfrvidf.isDodFlbvorSupportfd(
                                DodFlbvor.SERVICE_FORMATTED.PAGEABLE) &&
            sfrvidf.isDodFlbvorSupportfd(
                                DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
            rfturn sfrvidf;
        } flsf {
           PrintSfrvidf []sfrvidfs =
             PrintSfrvidfLookup.lookupPrintSfrvidfs(
                                DodFlbvor.SERVICE_FORMATTED.PAGEABLE, null);
           if (sfrvidfs.lfngti > 0) {
               rfturn sfrvidfs[0];
           }
        }
        rfturn null;
    }

   /**
     * Rfturns tif sfrvidf (printfr) for tiis printfr job.
     * Implfmfntbtions of tiis dlbss wiidi do not support print sfrvidfs
     * mby rfturn null;
     * @rfturn tif sfrvidf for tiis printfr job.
     *
     */
    publid PrintSfrvidf gftPrintSfrvidf() {
        if (mySfrvidf == null) {
            PrintSfrvidf svd = PrintSfrvidfLookup.lookupDffbultPrintSfrvidf();
            if (svd != null &&
                svd.isDodFlbvorSupportfd(
                     DodFlbvor.SERVICE_FORMATTED.PAGEABLE)) {
                try {
                    sftPrintSfrvidf(svd);
                    mySfrvidf = svd;
                } dbtdi (PrintfrExdfption f) {
                }
            }
            if (mySfrvidf == null) {
                PrintSfrvidf[] svds = PrintSfrvidfLookup.lookupPrintSfrvidfs(
                    DodFlbvor.SERVICE_FORMATTED.PAGEABLE, null);
                if (svds.lfngti > 0) {
                    try {
                        sftPrintSfrvidf(svds[0]);
                        mySfrvidf = svds[0];
                    } dbtdi (PrintfrExdfption f) {
                    }
                }
            }
        }
        rfturn mySfrvidf;
    }

    /**
     * Assodibtf tiis PrintfrJob witi b nfw PrintSfrvidf.
     *
     * Tirows <dodf>PrintfrExdfption</dodf> if tif spfdififd sfrvidf
     * dbnnot support tif <dodf>Pbgfbblf</dodf> bnd
     * <dodf>Printbblf</dodf> intfrfbdfs nfdfssbry to support 2D printing.
     * @pbrbm b print sfrvidf wiidi supports 2D printing.
     *
     * @tirows PrintfrExdfption if tif spfdififd sfrvidf dofs not support
     * 2D printing or no longfr bvbilbblf.
     */
    publid void sftPrintSfrvidf(PrintSfrvidf sfrvidf)
        tirows PrintfrExdfption {
        if (sfrvidf == null) {
            tirow nfw PrintfrExdfption("Sfrvidf dbnnot bf null");
        } flsf if (!(sfrvidf instbndfof StrfbmPrintSfrvidf) &&
                   sfrvidf.gftNbmf() == null) {
            tirow nfw PrintfrExdfption("Null PrintSfrvidf nbmf.");
        } flsf {
            // Cifdk tif list of sfrvidfs.  Tiis sfrvidf mby ibvf bffn
            // dflftfd blrfbdy
            PrintfrStbtf prnStbtf = sfrvidf.gftAttributf(PrintfrStbtf.dlbss);
            if (prnStbtf == PrintfrStbtf.STOPPED) {
                PrintfrStbtfRfbsons prnStbtfRfbsons =
                    sfrvidf.gftAttributf(PrintfrStbtfRfbsons.dlbss);
                if ((prnStbtfRfbsons != null) &&
                    (prnStbtfRfbsons.dontbinsKfy(PrintfrStbtfRfbson.SHUTDOWN)))
                {
                    tirow nfw PrintfrExdfption("PrintSfrvidf is no longfr bvbilbblf.");
                }
            }


            if (sfrvidf.isDodFlbvorSupportfd(
                                             DodFlbvor.SERVICE_FORMATTED.PAGEABLE) &&
                sfrvidf.isDodFlbvorSupportfd(
                                             DodFlbvor.SERVICE_FORMATTED.PRINTABLE)) {
                mySfrvidf = sfrvidf;
            } flsf {
                tirow nfw PrintfrExdfption("Not b 2D print sfrvidf: " + sfrvidf);
            }
        }
    }

    privbtf PbgfFormbt bttributfToPbgfFormbt(PrintSfrvidf sfrvidf,
                                               PrintRfqufstAttributfSft bttSft) {
        PbgfFormbt pbgf = dffbultPbgf();

        if (sfrvidf == null) {
            rfturn pbgf;
        }

        OrifntbtionRfqufstfd orifnt = (OrifntbtionRfqufstfd)
                                      bttSft.gft(OrifntbtionRfqufstfd.dlbss);
        if (orifnt == null) {
            orifnt = (OrifntbtionRfqufstfd)
                    sfrvidf.gftDffbultAttributfVbluf(OrifntbtionRfqufstfd.dlbss);
        }
        if (orifnt == OrifntbtionRfqufstfd.REVERSE_LANDSCAPE) {
            pbgf.sftOrifntbtion(PbgfFormbt.REVERSE_LANDSCAPE);
        } flsf if (orifnt == OrifntbtionRfqufstfd.LANDSCAPE) {
            pbgf.sftOrifntbtion(PbgfFormbt.LANDSCAPE);
        } flsf {
            pbgf.sftOrifntbtion(PbgfFormbt.PORTRAIT);
        }

        Mfdib mfdib = (Mfdib)bttSft.gft(Mfdib.dlbss);
        if (mfdib == null) {
            mfdib =
                (Mfdib)sfrvidf.gftDffbultAttributfVbluf(Mfdib.dlbss);
        }
        if (!(mfdib instbndfof MfdibSizfNbmf)) {
            mfdib = MfdibSizfNbmf.NA_LETTER;
        }
        MfdibSizf sizf =
            MfdibSizf.gftMfdibSizfForNbmf((MfdibSizfNbmf)mfdib);
        if (sizf == null) {
            sizf = MfdibSizf.NA.LETTER;
        }
        Pbpfr pbpfr = nfw Pbpfr();
        flobt dim[] = sizf.gftSizf(1); //units == 1 to bvoid FP frror
        doublf w = Mbti.rint((dim[0]*72.0)/Sizf2DSyntbx.INCH);
        doublf i = Mbti.rint((dim[1]*72.0)/Sizf2DSyntbx.INCH);
        pbpfr.sftSizf(w, i);
        MfdibPrintbblfArfb brfb =
             (MfdibPrintbblfArfb)
             bttSft.gft(MfdibPrintbblfArfb.dlbss);
        doublf ix, iw, iy, ii;

        if (brfb != null) {
            // Siould pbss in sbmf unit bs updbtfPbgfAttributfs
            // to bvoid rounding off frrors.
            ix = Mbti.rint(
                           brfb.gftX(MfdibPrintbblfArfb.INCH) * DPI);
            iy = Mbti.rint(
                           brfb.gftY(MfdibPrintbblfArfb.INCH) * DPI);
            iw = Mbti.rint(
                           brfb.gftWidti(MfdibPrintbblfArfb.INCH) * DPI);
            ii = Mbti.rint(
                           brfb.gftHfigit(MfdibPrintbblfArfb.INCH) * DPI);
        }
        flsf {
            if (w >= 72.0 * 6.0) {
                ix = 72.0;
                iw = w - 2 * 72.0;
            } flsf {
                ix = w / 6.0;
                iw = w * 0.75;
            }
            if (i >= 72.0 * 6.0) {
                iy = 72.0;
                ii = i - 2 * 72.0;
            } flsf {
                iy = i / 6.0;
                ii = i * 0.75;
            }
        }
        pbpfr.sftImbgfbblfArfb(ix, iy, iw, ii);
        pbgf.sftPbpfr(pbpfr);
        rfturn pbgf;
    }

    protfdtfd void updbtfPbgfAttributfs(PrintSfrvidf sfrvidf,
                                        PbgfFormbt pbgf) {
        if (tiis.bttributfs == null) {
            tiis.bttributfs = nfw HbsiPrintRfqufstAttributfSft();
        }

        updbtfAttributfsWitiPbgfFormbt(sfrvidf, pbgf, tiis.bttributfs);
    }

    protfdtfd void updbtfAttributfsWitiPbgfFormbt(PrintSfrvidf sfrvidf,
                                        PbgfFormbt pbgf,
                                        PrintRfqufstAttributfSft pbgfAttributfs) {
        if (sfrvidf == null || pbgf == null || pbgfAttributfs == null) {
            rfturn;
        }

        flobt x = (flobt)Mbti.rint(
                         (pbgf.gftPbpfr().gftWidti()*Sizf2DSyntbx.INCH)/
                         (72.0))/(flobt)Sizf2DSyntbx.INCH;
        flobt y = (flobt)Mbti.rint(
                         (pbgf.gftPbpfr().gftHfigit()*Sizf2DSyntbx.INCH)/
                         (72.0))/(flobt)Sizf2DSyntbx.INCH;

        // Wf siould limit tif list wifrf wf sfbrdi tif mbtdiing
        // mfdib, tiis will prfvfnt mbpping to wrong mfdib fx. Lfdgfr
        // dbn bf mbppfd to B.  Espfdiblly usfful wifn drfbting
        // dustom MfdibSizf.
        Mfdib[] mfdibList = (Mfdib[])sfrvidf.gftSupportfdAttributfVblufs(
                                      Mfdib.dlbss, null, null);
        Mfdib mfdib = null;
        try {
            mfdib = CustomMfdibSizfNbmf.findMfdib(mfdibList, x, y,
                                   Sizf2DSyntbx.INCH);
        } dbtdi (IllfgblArgumfntExdfption ibf) {
        }
        if ((mfdib == null) ||
             !(sfrvidf.isAttributfVblufSupportfd(mfdib, null, null))) {
            mfdib = (Mfdib)sfrvidf.gftDffbultAttributfVbluf(Mfdib.dlbss);
        }

        OrifntbtionRfqufstfd orifnt;
        switdi (pbgf.gftOrifntbtion()) {
        dbsf PbgfFormbt.LANDSCAPE :
            orifnt = OrifntbtionRfqufstfd.LANDSCAPE;
            brfbk;
        dbsf PbgfFormbt.REVERSE_LANDSCAPE:
            orifnt = OrifntbtionRfqufstfd.REVERSE_LANDSCAPE;
            brfbk;
        dffbult:
            orifnt = OrifntbtionRfqufstfd.PORTRAIT;
        }

        if (mfdib != null) {
            pbgfAttributfs.bdd(mfdib);
        }
        pbgfAttributfs.bdd(orifnt);

        flobt ix = (flobt)(pbgf.gftPbpfr().gftImbgfbblfX()/DPI);
        flobt iw = (flobt)(pbgf.gftPbpfr().gftImbgfbblfWidti()/DPI);
        flobt iy = (flobt)(pbgf.gftPbpfr().gftImbgfbblfY()/DPI);
        flobt ii = (flobt)(pbgf.gftPbpfr().gftImbgfbblfHfigit()/DPI);
        if (ix < 0) ix = 0f; if (iy < 0) iy = 0f;
        try {
            pbgfAttributfs.bdd(nfw MfdibPrintbblfArfb(ix, iy, iw, ii,
                                                  MfdibPrintbblfArfb.INCH));
        } dbtdi (IllfgblArgumfntExdfption ibf) {
        }
    }

   /**
     * Displby b diblog to tif usfr bllowing tif modifidbtion of b
     * PbgfFormbt instbndf.
     * Tif <dodf>pbgf</dodf> brgumfnt is usfd to initiblizf dontrols
     * in tif pbgf sftup diblog.
     * If tif usfr dbndfls tif diblog, tifn tif mftiod rfturns tif
     * originbl <dodf>pbgf</dodf> objfdt unmodififd.
     * If tif usfr okbys tif diblog tifn tif mftiod rfturns b nfw
     * PbgfFormbt objfdt witi tif indidbtfd dibngfs.
     * In fitifr dbsf tif originbl <dodf>pbgf</dodf> objfdt will
     * not bf modififd.
     * @pbrbm     pbgf    tif dffbult PbgfFormbt prfsfntfd to tif usfr
     *                    for modifidbtion
     * @rfturn    tif originbl <dodf>pbgf</dodf> objfdt if tif diblog
     *            is dbndfllfd, or b nfw PbgfFormbt objfdt dontbining
     *            tif formbt indidbtfd by tif usfr if tif diblog is
     *            bdknowlfdgfd
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.2
     */
    publid PbgfFormbt pbgfDiblog(PbgfFormbt pbgf)
        tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        finbl GrbpiidsConfigurbtion gd =
          GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
          gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();

        PrintSfrvidf sfrvidf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                               nfw jbvb.sfdurity.PrivilfgfdAdtion<PrintSfrvidf>() {
                publid PrintSfrvidf run() {
                    PrintSfrvidf sfrvidf = gftPrintSfrvidf();
                    if (sfrvidf == null) {
                        SfrvidfDiblog.siowNoPrintSfrvidf(gd);
                        rfturn null;
                    }
                    rfturn sfrvidf;
                }
            });

        if (sfrvidf == null) {
            rfturn pbgf;
        }
        updbtfPbgfAttributfs(sfrvidf, pbgf);

        PbgfFormbt nfwPbgf = pbgfDiblog(bttributfs);

        if (nfwPbgf == null) {
            rfturn pbgf;
        } flsf {
            rfturn nfwPbgf;
        }
    }

    /**
     * rfturn b PbgfFormbt dorrfsponding to tif updbtfd bttributfs,
     * or null if tif usfr dbndfllfd tif diblog.
     */
    publid PbgfFormbt pbgfDiblog(finbl PrintRfqufstAttributfSft bttributfs)
        tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        DiblogTypfSflfdtion dlg =
            (DiblogTypfSflfdtion)bttributfs.gft(DiblogTypfSflfdtion.dlbss);

        // Cifdk for nbtivf, notf tibt dffbult diblog is COMMON.
        if (dlg == DiblogTypfSflfdtion.NATIVE) {
            PrintSfrvidf psfrvidf = gftPrintSfrvidf();
            PbgfFormbt pbgf = pbgfDiblog(bttributfToPbgfFormbt(psfrvidf,
                                                               bttributfs));
            updbtfAttributfsWitiPbgfFormbt(psfrvidf, pbgf, bttributfs);
            rfturn pbgf;
        }

        finbl GrbpiidsConfigurbtion gd =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
            gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();
        Rfdtbnglf bounds = gd.gftBounds();
        int x = bounds.x+bounds.widti/3;
        int y = bounds.y+bounds.ifigit/3;

        PrintSfrvidf sfrvidf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                               nfw jbvb.sfdurity.PrivilfgfdAdtion<PrintSfrvidf>() {
                publid PrintSfrvidf run() {
                    PrintSfrvidf sfrvidf = gftPrintSfrvidf();
                    if (sfrvidf == null) {
                        SfrvidfDiblog.siowNoPrintSfrvidf(gd);
                        rfturn null;
                    }
                    rfturn sfrvidf;
                }
            });

        if (sfrvidf == null) {
            rfturn null;
        }

        SfrvidfDiblog pbgfDiblog = nfw SfrvidfDiblog(gd, x, y, sfrvidf,
                                       DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
                                       bttributfs, (Frbmf)null);
        pbgfDiblog.siow();

        if (pbgfDiblog.gftStbtus() == SfrvidfDiblog.APPROVE) {
            PrintRfqufstAttributfSft nfwbs =
                pbgfDiblog.gftAttributfs();
            Clbss<?> bmCbtfgory = SunAltfrnbtfMfdib.dlbss;

            if (bttributfs.dontbinsKfy(bmCbtfgory) &&
                !nfwbs.dontbinsKfy(bmCbtfgory)) {
                bttributfs.rfmovf(bmCbtfgory);
            }
            bttributfs.bddAll(nfwbs);
            rfturn bttributfToPbgfFormbt(sfrvidf, bttributfs);
        } flsf {
            rfturn null;
        }
   }

   protfdtfd PbgfFormbt gftPbgfFormbtFromAttributfs() {
       if (bttributfs == null) {
            rfturn null;
        }
        rfturn bttributfToPbgfFormbt(gftPrintSfrvidf(), tiis.bttributfs);
   }


   /**
     * Prfsfnts tif usfr b diblog for dibnging propfrtifs of tif
     * print job intfrbdtivfly.
     * Tif sfrvidfs browsbblf ifrf brf dftfrminfd by tif typf of
     * sfrvidf durrfntly instbllfd.
     * If tif bpplidbtion instbllfd b StrfbmPrintSfrvidf on tiis
     * PrintfrJob, only tif bvbilbblf StrfbmPrintSfrvidf (fbdtorifs) brf
     * browsbblf.
     *
     * @pbrbm bttributfs to storf dibngfd propfrtifs.
     * @rfturn fblsf if tif usfr dbndfls tif diblog bnd truf otifrwisf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid boolfbn printDiblog(finbl PrintRfqufstAttributfSft bttributfs)
        tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        DiblogTypfSflfdtion dlg =
            (DiblogTypfSflfdtion)bttributfs.gft(DiblogTypfSflfdtion.dlbss);

        // Cifdk for nbtivf, notf tibt dffbult diblog is COMMON.
        if (dlg == DiblogTypfSflfdtion.NATIVE) {
            tiis.bttributfs = bttributfs;
            try {
                dfbug_println("dblling sftAttributfs in printDiblog");
                sftAttributfs(bttributfs);

            } dbtdi (PrintfrExdfption f) {

            }

            boolfbn rft = printDiblog();
            tiis.bttributfs = bttributfs;
            rfturn rft;

        }

        /* A sfdurity difdk ibs blrfbdy bffn pfrformfd in tif
         * jbvb.bwt.print.printfrJob.gftPrintfrJob mftiod.
         * So by tif timf wf gft ifrf, it is OK for tif durrfnt tirfbd
         * to print fitifr to b filf (from b Diblog wf dontrol!) or
         * to b diosfn printfr.
         *
         * Wf rbisf privilfgf wifn wf put up tif diblog, to bvoid
         * tif "wbrning bpplft window" bbnnfr.
         */
        finbl GrbpiidsConfigurbtion gd =
            GrbpiidsEnvironmfnt.gftLodblGrbpiidsEnvironmfnt().
            gftDffbultSdrffnDfvidf().gftDffbultConfigurbtion();

        PrintSfrvidf sfrvidf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                               nfw jbvb.sfdurity.PrivilfgfdAdtion<PrintSfrvidf>() {
                publid PrintSfrvidf run() {
                    PrintSfrvidf sfrvidf = gftPrintSfrvidf();
                    if (sfrvidf == null) {
                        SfrvidfDiblog.siowNoPrintSfrvidf(gd);
                        rfturn null;
                    }
                    rfturn sfrvidf;
                }
            });

        if (sfrvidf == null) {
            rfturn fblsf;
        }

        PrintSfrvidf[] sfrvidfs;
        StrfbmPrintSfrvidfFbdtory[] spsFbdtorifs = null;
        if (sfrvidf instbndfof StrfbmPrintSfrvidf) {
            spsFbdtorifs = lookupStrfbmPrintSfrvidfs(null);
            sfrvidfs = nfw StrfbmPrintSfrvidf[spsFbdtorifs.lfngti];
            for (int i=0; i<spsFbdtorifs.lfngti; i++) {
                sfrvidfs[i] = spsFbdtorifs[i].gftPrintSfrvidf(null);
            }
        } flsf {
            sfrvidfs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                       nfw jbvb.sfdurity.PrivilfgfdAdtion<PrintSfrvidf[]>() {
                publid PrintSfrvidf[] run() {
                    PrintSfrvidf[] sfrvidfs = PrintfrJob.lookupPrintSfrvidfs();
                    rfturn sfrvidfs;
                }
            });

            if ((sfrvidfs == null) || (sfrvidfs.lfngti == 0)) {
                /*
                 * No sfrvidfs but dffbult PrintSfrvidf fxists?
                 * Crfbtf sfrvidfs using dffbultSfrvidf.
                 */
                sfrvidfs = nfw PrintSfrvidf[1];
                sfrvidfs[0] = sfrvidf;
            }
        }

        Rfdtbnglf bounds = gd.gftBounds();
        int x = bounds.x+bounds.widti/3;
        int y = bounds.y+bounds.ifigit/3;
        PrintSfrvidf nfwSfrvidf;
        // tfmporbrily bdd bn bttributf pointing bbdk to tiis job.
        PrintfrJobWrbppfr jobWrbppfr = nfw PrintfrJobWrbppfr(tiis);
        bttributfs.bdd(jobWrbppfr);
        try {
            nfwSfrvidf =
            SfrvidfUI.printDiblog(gd, x, y,
                                  sfrvidfs, sfrvidf,
                                  DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
                                  bttributfs);
        } dbtdi (IllfgblArgumfntExdfption ibf) {
            nfwSfrvidf = SfrvidfUI.printDiblog(gd, x, y,
                                  sfrvidfs, sfrvidfs[0],
                                  DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
                                  bttributfs);
        }
        bttributfs.rfmovf(PrintfrJobWrbppfr.dlbss);

        if (nfwSfrvidf == null) {
            rfturn fblsf;
        }

        if (!sfrvidf.fqubls(nfwSfrvidf)) {
            try {
                sftPrintSfrvidf(nfwSfrvidf);
            } dbtdi (PrintfrExdfption f) {
                /*
                 * Tif only timf it would tirow bn fxdfption is wifn
                 * nfwSfrvidf is no longfr bvbilbblf but wf siould still
                 * sflfdt tiis printfr.
                 */
                mySfrvidf = nfwSfrvidf;
            }
        }
        rfturn truf;
    }

   /**
     * Prfsfnts tif usfr b diblog for dibnging propfrtifs of tif
     * print job intfrbdtivfly.
     * @rfturns fblsf if tif usfr dbndfls tif diblog bnd
     *          truf otifrwisf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid boolfbn printDiblog() tirows HfbdlfssExdfption {

        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        PrintRfqufstAttributfSft bttributfs =
          nfw HbsiPrintRfqufstAttributfSft();
        bttributfs.bdd(nfw Copifs(gftCopifs()));
        bttributfs.bdd(nfw JobNbmf(gftJobNbmf(), null));
        boolfbn doPrint = printDiblog(bttributfs);
        if (doPrint) {
            JobNbmf jobNbmf = (JobNbmf)bttributfs.gft(JobNbmf.dlbss);
            if (jobNbmf != null) {
                sftJobNbmf(jobNbmf.gftVbluf());
            }
            Copifs dopifs = (Copifs)bttributfs.gft(Copifs.dlbss);
            if (dopifs != null) {
                sftCopifs(dopifs.gftVbluf());
            }

            Dfstinbtion dfst = (Dfstinbtion)bttributfs.gft(Dfstinbtion.dlbss);

            if (dfst != null) {
                try {
                    mDfstTypf = RbstfrPrintfrJob.FILE;
                    mDfstinbtion = (nfw Filf(dfst.gftURI())).gftPbti();
                } dbtdi (Exdfption f) {
                    mDfstinbtion = "out.prn";
                    PrintSfrvidf ps = gftPrintSfrvidf();
                    if (ps != null) {
                        Dfstinbtion dffbultDfst = (Dfstinbtion)ps.
                            gftDffbultAttributfVbluf(Dfstinbtion.dlbss);
                        if (dffbultDfst != null) {
                            mDfstinbtion = (nfw Filf(dffbultDfst.gftURI())).gftPbti();
                        }
                    }
                }
            } flsf {
                mDfstTypf = RbstfrPrintfrJob.PRINTER;
                PrintSfrvidf ps = gftPrintSfrvidf();
                if (ps != null) {
                    mDfstinbtion = ps.gftNbmf();
                }
            }
        }

        rfturn doPrint;
    }

    /**
     * Tif pbgfs in tif dodumfnt to bf printfd by tiis PrintfrJob
     * brf drbwn by tif Printbblf objfdt 'pbintfr'. Tif PbgfFormbt
     * for fbdi pbgf is tif dffbult pbgf formbt.
     * @pbrbm Printbblf Cbllfd to rfndfr fbdi pbgf of tif dodumfnt.
     */
    publid void sftPrintbblf(Printbblf pbintfr) {
        sftPbgfbblf(nfw OpfnBook(dffbultPbgf(nfw PbgfFormbt()), pbintfr));
    }

    /**
     * Tif pbgfs in tif dodumfnt to bf printfd by tiis PrintfrJob
     * brf drbwn by tif Printbblf objfdt 'pbintfr'. Tif PbgfFormbt
     * of fbdi pbgf is 'formbt'.
     * @pbrbm Printbblf Cbllfd to rfndfr fbdi pbgf of tif dodumfnt.
     * @pbrbm PbgfFormbt Tif sizf bnd orifntbtion of fbdi pbgf to
     *                   bf printfd.
     */
    publid void sftPrintbblf(Printbblf pbintfr, PbgfFormbt formbt) {
        sftPbgfbblf(nfw OpfnBook(formbt, pbintfr));
        updbtfPbgfAttributfs(gftPrintSfrvidf(), formbt);
    }

    /**
     * Tif pbgfs in tif dodumfnt to bf printfd brf ifld by tif
     * Pbgfbblf instbndf 'dodumfnt'. 'dodumfnt' will bf qufrifd
     * for tif numbfr of pbgfs bs wfll bs tif PbgfFormbt bnd
     * Printbblf for fbdi pbgf.
     * @pbrbm Pbgfbblf Tif dodumfnt to bf printfd. It mby not bf null.
     * @fxdfption NullPointfrExdfption tif Pbgfbblf pbssfd in wbs null.
     * @sff PbgfFormbt
     * @sff Printbblf
     */
    publid void sftPbgfbblf(Pbgfbblf dodumfnt) tirows NullPointfrExdfption {
        if (dodumfnt != null) {
            mDodumfnt = dodumfnt;

        } flsf {
            tirow nfw NullPointfrExdfption();
        }
    }

    protfdtfd void initPrintfr() {
        rfturn;
    }

    protfdtfd boolfbn isSupportfdVbluf(Attributf bttrvbl,
                                     PrintRfqufstAttributfSft bttrsft) {
        PrintSfrvidf ps = gftPrintSfrvidf();
        rfturn
            (bttrvbl != null && ps != null &&
             ps.isAttributfVblufSupportfd(bttrvbl,
                                          DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
                                          bttrsft));
    }

    /**
     * Sft tif dfvidf rfsolution.
     * Ovfrriddfn bnd usfd only by tif postsdript dodf.
     * Windows dodf pulls tif informbtion from tif bttributf sft itsflf.
     */
    protfdtfd void sftXYRfs(doublf x, doublf y) {
    }

    /* subdlbssfs mby nffd to pull fxtrb informbtion out of tif bttributf sft
     * Tify dbn ovfrridf tiis mftiod & dbll supfr.sftAttributfs()
     */
    protfdtfd  void sftAttributfs(PrintRfqufstAttributfSft bttributfs)
        tirows PrintfrExdfption {
        /*  rfsft bll vblufs to dffbults */
        sftCollbtfd(fblsf);
        sidfsAttr = null;
        printfrRfsAttr = null;
        pbgfRbngfsAttr = null;
        dopifsAttr = 0;
        jobNbmfAttr = null;
        usfrNbmfAttr = null;
        dfstinbtionAttr = null;
        dollbtfAttRfq = fblsf;

        PrintSfrvidf sfrvidf = gftPrintSfrvidf();
        if (bttributfs == null  || sfrvidf == null) {
            rfturn;
        }

        boolfbn fidflity = fblsf;
        Fidflity bttrFidflity = (Fidflity)bttributfs.gft(Fidflity.dlbss);
        if (bttrFidflity != null && bttrFidflity == Fidflity.FIDELITY_TRUE) {
            fidflity = truf;
        }

        if (fidflity == truf) {
           AttributfSft unsupportfd =
               sfrvidf.gftUnsupportfdAttributfs(
                                         DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
                                         bttributfs);
           if (unsupportfd != null) {
               tirow nfw PrintfrExdfption("Fidflity dbnnot bf sbtisfifd");
           }
        }

        /*
         * Sindf wf ibvf vfrififd supportfd vblufs if fidflity is truf,
         * wf dbn fitifr ignorf unsupportfd vblufs, or substitutf b
         * rfbsonbblf bltfrnbtivf
         */

        SifftCollbtf dollbtfAttr =
            (SifftCollbtf)bttributfs.gft(SifftCollbtf.dlbss);
        if (isSupportfdVbluf(dollbtfAttr,  bttributfs)) {
            sftCollbtfd(dollbtfAttr == SifftCollbtf.COLLATED);
        }

        sidfsAttr = (Sidfs)bttributfs.gft(Sidfs.dlbss);
        if (!isSupportfdVbluf(sidfsAttr,  bttributfs)) {
            sidfsAttr = Sidfs.ONE_SIDED;
        }

        printfrRfsAttr = (PrintfrRfsolution)bttributfs.gft(PrintfrRfsolution.dlbss);
        if (sfrvidf.isAttributfCbtfgorySupportfd(PrintfrRfsolution.dlbss)) {
            if (!isSupportfdVbluf(printfrRfsAttr,  bttributfs)) {
               printfrRfsAttr = (PrintfrRfsolution)
                   sfrvidf.gftDffbultAttributfVbluf(PrintfrRfsolution.dlbss);
            }
            doublf xr =
               printfrRfsAttr.gftCrossFffdRfsolution(RfsolutionSyntbx.DPI);
            doublf yr = printfrRfsAttr.gftFffdRfsolution(RfsolutionSyntbx.DPI);
            sftXYRfs(xr, yr);
        }

        pbgfRbngfsAttr =  (PbgfRbngfs)bttributfs.gft(PbgfRbngfs.dlbss);
        if (!isSupportfdVbluf(pbgfRbngfsAttr, bttributfs)) {
            pbgfRbngfsAttr = null;
        } flsf {
            if ((SunPbgfSflfdtion)bttributfs.gft(SunPbgfSflfdtion.dlbss)
                     == SunPbgfSflfdtion.RANGE) {
                // gft to, from, min, mbx pbgf rbngfs
                int[][] rbngf = pbgfRbngfsAttr.gftMfmbfrs();
                // sftPbgfRbngfs usfs 0-bbsfd indfxing so wf subtrbdt 1
                sftPbgfRbngf(rbngf[0][0] - 1, rbngf[0][1] - 1);
            } flsf {
               sftPbgfRbngf(-1, - 1);
            }
        }

        Copifs dopifs = (Copifs)bttributfs.gft(Copifs.dlbss);
        if (isSupportfdVbluf(dopifs,  bttributfs) ||
            (!fidflity && dopifs != null)) {
            dopifsAttr = dopifs.gftVbluf();
            sftCopifs(dopifsAttr);
        } flsf {
            dopifsAttr = gftCopifs();
        }

        Dfstinbtion dfstinbtion =
            (Dfstinbtion)bttributfs.gft(Dfstinbtion.dlbss);

        if (isSupportfdVbluf(dfstinbtion,  bttributfs)) {
            try {
                // Old dodf (nfw Filf(dfstinbtion.gftURI())).gftPbti()
                // would gfnfrbtf b "URI is not iifrbrdiidbl" IAE
                // for "filf:out.prn" so wf usf gftSdifmfSpfdifidPbrt instfbd
                dfstinbtionAttr = "" + nfw Filf(dfstinbtion.gftURI().
                                                gftSdifmfSpfdifidPbrt());
            } dbtdi (Exdfption f) { // pbrbnoid fxdfption
                Dfstinbtion dffbultDfst = (Dfstinbtion)sfrvidf.
                    gftDffbultAttributfVbluf(Dfstinbtion.dlbss);
                if (dffbultDfst != null) {
                    dfstinbtionAttr = "" + nfw Filf(dffbultDfst.gftURI().
                                                gftSdifmfSpfdifidPbrt());
                }
            }
        }

        JobSiffts jobSiffts = (JobSiffts)bttributfs.gft(JobSiffts.dlbss);
        if (jobSiffts != null) {
            noJobSifft = jobSiffts == JobSiffts.NONE;
        }

        JobNbmf jobNbmf = (JobNbmf)bttributfs.gft(JobNbmf.dlbss);
        if (isSupportfdVbluf(jobNbmf,  bttributfs) ||
            (!fidflity && jobNbmf != null)) {
            jobNbmfAttr = jobNbmf.gftVbluf();
            sftJobNbmf(jobNbmfAttr);
        } flsf {
            jobNbmfAttr = gftJobNbmf();
        }

        RfqufstingUsfrNbmf usfrNbmf =
            (RfqufstingUsfrNbmf)bttributfs.gft(RfqufstingUsfrNbmf.dlbss);
        if (isSupportfdVbluf(usfrNbmf,  bttributfs) ||
            (!fidflity && usfrNbmf != null)) {
            usfrNbmfAttr = usfrNbmf.gftVbluf();
        } flsf {
            try {
                usfrNbmfAttr = gftUsfrNbmf();
            } dbtdi (SfdurityExdfption f) {
                usfrNbmfAttr = "";
            }
        }

        /* OpfnBook is usfd intfrnblly only wifn bpp usfs Printbblf.
         * Tiis is tif dbsf wifn wf usf tif vblufs from tif bttributf sft.
         */
        Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
        OrifntbtionRfqufstfd orifntRfq =
           (OrifntbtionRfqufstfd)bttributfs.gft(OrifntbtionRfqufstfd.dlbss);
        MfdibPrintbblfArfb mpb =
            (MfdibPrintbblfArfb)bttributfs.gft(MfdibPrintbblfArfb.dlbss);

        if ((orifntRfq != null || mfdib != null || mpb != null) &&
            gftPbgfbblf() instbndfof OpfnBook) {

            /* Wf dould blmost(!) usf PrintfrJob.gftPbgfFormbt() fxdfpt
             * ifrf wf nffd to stbrt witi tif PbgfFormbt from tif OpfnBook :
             */
            Pbgfbblf pbgfbblf = gftPbgfbblf();
            Printbblf printbblf = pbgfbblf.gftPrintbblf(0);
            PbgfFormbt pf = (PbgfFormbt)pbgfbblf.gftPbgfFormbt(0).dlonf();
            Pbpfr pbpfr = pf.gftPbpfr();

            /* If tifrf's b mfdib but no mfdib printbblf brfb, wf dbn try
             * to rftrifvf tif dffbult vbluf for mpb bnd usf tibt.
             */
            if (mpb == null && mfdib != null &&
                sfrvidf.
                isAttributfCbtfgorySupportfd(MfdibPrintbblfArfb.dlbss)) {
                Objfdt mpbVbls = sfrvidf.
                    gftSupportfdAttributfVblufs(MfdibPrintbblfArfb.dlbss,
                                                null, bttributfs);
                if (mpbVbls instbndfof MfdibPrintbblfArfb[] &&
                    ((MfdibPrintbblfArfb[])mpbVbls).lfngti > 0) {
                    mpb = ((MfdibPrintbblfArfb[])mpbVbls)[0];
                }
            }

            if (isSupportfdVbluf(orifntRfq, bttributfs) ||
                (!fidflity && orifntRfq != null)) {
                int orifnt;
                if (orifntRfq.fqubls(OrifntbtionRfqufstfd.REVERSE_LANDSCAPE)) {
                    orifnt = PbgfFormbt.REVERSE_LANDSCAPE;
                } flsf if (orifntRfq.fqubls(OrifntbtionRfqufstfd.LANDSCAPE)) {
                    orifnt = PbgfFormbt.LANDSCAPE;
                } flsf {
                    orifnt = PbgfFormbt.PORTRAIT;
                }
                pf.sftOrifntbtion(orifnt);
            }

            if (isSupportfdVbluf(mfdib, bttributfs) ||
                (!fidflity && mfdib != null)) {
                if (mfdib instbndfof MfdibSizfNbmf) {
                    MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib;
                    MfdibSizf msz = MfdibSizf.gftMfdibSizfForNbmf(msn);
                    if (msz != null) {
                        flobt pbpfrWid =  msz.gftX(MfdibSizf.INCH) * 72.0f;
                        flobt pbpfrHgt =  msz.gftY(MfdibSizf.INCH) * 72.0f;
                        pbpfr.sftSizf(pbpfrWid, pbpfrHgt);
                        if (mpb == null) {
                            pbpfr.sftImbgfbblfArfb(72.0, 72.0,
                                                   pbpfrWid-144.0,
                                                   pbpfrHgt-144.0);
                        }
                    }
                }
            }

            if (isSupportfdVbluf(mpb, bttributfs) ||
                (!fidflity && mpb != null)) {
                flobt [] printbblfArfb =
                    mpb.gftPrintbblfArfb(MfdibPrintbblfArfb.INCH);
                for (int i=0; i < printbblfArfb.lfngti; i++) {
                    printbblfArfb[i] = printbblfArfb[i]*72.0f;
                }
                pbpfr.sftImbgfbblfArfb(printbblfArfb[0], printbblfArfb[1],
                                       printbblfArfb[2], printbblfArfb[3]);
            }

            pf.sftPbpfr(pbpfr);
            pf = vblidbtfPbgf(pf);
            sftPrintbblf(printbblf, pf);
        } flsf {
            // for AWT wifrf pbgfbblf is not bn instbndf of OpfnBook,
            // wf nffd to sbvf pbpfr info
            tiis.bttributfs = bttributfs;
        }

    }

    /*
     * Sfrvidfs wf don't rfdognizf bs built-in sfrvidfs dbn't bf
     * implfmfntfd bs subdlbssfs of PrintfrJob, tifrfforf wf drfbtf
     * b DodPrintJob from tifir sfrvidf bnd pbss b Dod rfprfsfnting
     * tif bpplidbtion's printjob
     */
// MbdOSX - mbdf protfdtfd so subdlbssfs dbn rfffrfndf it.
    protfdtfd void spoolToSfrvidf(PrintSfrvidf psvd,
                                PrintRfqufstAttributfSft bttributfs)
        tirows PrintfrExdfption {

        if (psvd == null) {
            tirow nfw PrintfrExdfption("No print sfrvidf found.");
        }

        DodPrintJob job = psvd.drfbtfPrintJob();
        Dod dod = nfw PbgfbblfDod(gftPbgfbblf());
        if (bttributfs == null) {
            bttributfs = nfw HbsiPrintRfqufstAttributfSft();
        }
        try {
            job.print(dod, bttributfs);
        } dbtdi (PrintExdfption f) {
            tirow nfw PrintfrExdfption(f.toString());
        }
    }

    /**
     * Prints b sft of pbgfs.
     * @fxdfption jbvb.bwt.print.PrintfrExdfption bn frror in tif print systfm
     *                                          dbusfd tif job to bf bbortfd
     * @sff jbvb.bwt.print.Book
     * @sff jbvb.bwt.print.Pbgfbblf
     * @sff jbvb.bwt.print.Printbblf
     */
    publid void print() tirows PrintfrExdfption {
        print(bttributfs);
    }

    publid stbtid boolfbn dfbugPrint = fblsf;
    protfdtfd void dfbug_println(String str) {
        if (dfbugPrint) {
            Systfm.out.println("RbstfrPrintfrJob "+str+" "+tiis);
        }
    }

    publid void print(PrintRfqufstAttributfSft bttributfs)
        tirows PrintfrExdfption {

        /*
         * In tif futurf PrintfrJob will probbbly blwbys dispbtdi
         * tif print job to tif PrintSfrvidf.
         * Tiis is iow tiird pbrty 2D Print Sfrvidfs will bf invokfd
         * wifn bpplidbtions usf tif PrintfrJob API.
         * Howfvfr tif JRE's dondrftf PrintfrJob implfmfntbtions ibvf
         * not yft bffn rf-workfd to bf implfmfntfd bs stbndblonf
         * sfrvidfs, bnd brf implfmfntfd only bs subdlbssfs of PrintfrJob.
         * So ifrf wf dispbtdi only tiosf sfrvidfs wf do not rfdognizf
         * bs implfmfntfd tirougi plbtform subdlbssfs of PrintfrJob
         * (bnd tiis dlbss).
         */
        PrintSfrvidf psvd = gftPrintSfrvidf();
        dfbug_println("psvd = "+psvd);
        if (psvd == null) {
            tirow nfw PrintfrExdfption("No print sfrvidf found.");
        }

        // Cifdk tif list of sfrvidfs.  Tiis sfrvidf mby ibvf bffn
        // dflftfd blrfbdy
        PrintfrStbtf prnStbtf = psvd.gftAttributf(PrintfrStbtf.dlbss);
        if (prnStbtf == PrintfrStbtf.STOPPED) {
            PrintfrStbtfRfbsons prnStbtfRfbsons =
                    psvd.gftAttributf(PrintfrStbtfRfbsons.dlbss);
                if ((prnStbtfRfbsons != null) &&
                    (prnStbtfRfbsons.dontbinsKfy(PrintfrStbtfRfbson.SHUTDOWN)))
                {
                    tirow nfw PrintfrExdfption("PrintSfrvidf is no longfr bvbilbblf.");
                }
        }

        if ((psvd.gftAttributf(PrintfrIsAddfptingJobs.dlbss)) ==
                         PrintfrIsAddfptingJobs.NOT_ACCEPTING_JOBS) {
            tirow nfw PrintfrExdfption("Printfr is not bddfpting job.");
        }

        if ((psvd instbndfof SunPrintfrJobSfrvidf) &&
            ((SunPrintfrJobSfrvidf)psvd).usfsClbss(gftClbss())) {
            sftAttributfs(bttributfs);
            // tirow fxdfption for invblid dfstinbtion
            if (dfstinbtionAttr != null) {
                vblidbtfDfstinbtion(dfstinbtionAttr);
            }
        } flsf {
            spoolToSfrvidf(psvd, bttributfs);
            rfturn;
        }
        /* Wf nffd to mbkf surf tibt tif dollbtion bnd dopifs
         * sfttings brf initiblisfd */
        initPrintfr();

        int numCollbtfdCopifs = gftCollbtfdCopifs();
        int numNonCollbtfdCopifs = gftNondollbtfdCopifs();
        dfbug_println("gftCollbtfdCopifs()  "+numCollbtfdCopifs
              + " gftNondollbtfdCopifs() "+ numNonCollbtfdCopifs);

        /* Gft tif rbngf of pbgfs wf brf to print. If tif
         * lbst pbgf to print is unknown, tifn wf print to
         * tif fnd of tif dodumfnt. Notf tibt firstPbgf
         * bnd lbstPbgf brf 0 bbsfd pbgf indidfs.
         */
        int numPbgfs = mDodumfnt.gftNumbfrOfPbgfs();
        if (numPbgfs == 0) {
            rfturn;
        }

        int firstPbgf = gftFirstPbgf();
        int lbstPbgf = gftLbstPbgf();
        if(lbstPbgf == Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES){
            int totblPbgfs = mDodumfnt.gftNumbfrOfPbgfs();
            if (totblPbgfs != Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES) {
                lbstPbgf = mDodumfnt.gftNumbfrOfPbgfs() - 1;
            }
        }

        try {
            syndironizfd (tiis) {
                pfrformingPrinting = truf;
                usfrCbndfllfd = fblsf;
            }

            stbrtDod();
            if (isCbndfllfd()) {
                dbndflDod();
            }

            // PbgfRbngfs dbn bf sft fvfn if RANGE is not sflfdtfd
            // so wf nffd to difdk if it is sflfdtfd.
            boolfbn rbngfIsSflfdtfd = truf;
            if (bttributfs != null) {
                SunPbgfSflfdtion pbgfs =
                    (SunPbgfSflfdtion)bttributfs.gft(SunPbgfSflfdtion.dlbss);
                if ((pbgfs != null) && (pbgfs != SunPbgfSflfdtion.RANGE)) {
                    rbngfIsSflfdtfd = fblsf;
                }
            }


            dfbug_println("bftfr stbrtDod rbngfSflfdtfd? "+rbngfIsSflfdtfd
                      + " numNonCollbtfdCopifs "+ numNonCollbtfdCopifs);


            /* Tirff nfstfd loops itfrbtf ovfr tif dodumfnt. Tif outfr loop
             * dounts tif numbfr of dollbtfd dopifs wiilf tif innfr loop
             * dounts tif numbfr of nonCollbtfd dopifs. Normblly, onf of
             * tifsf two loops will only fxfdutf ondf; tibt is wf will
             * fitifr print dollbtfd dopifs or nondollbtfd dopifs. Tif
             * middlf loop itfrbtfs ovfr tif pbgfs.
             * If b PbgfRbngfs bttributf is usfd, it donstrbins tif pbgfs
             * tibt brf imbgfd. If b plbtform subdlbss (tiougi b usfr diblog)
             * rfqufsts b pbgf rbngf vib sftPbgfRbngf(). it too dbn
             * donstrbin tif pbgf rbngfs tibt brf imbgfd.
             * It is fxpfdtfd tibt only onf of tifsf will bf usfd in b
             * job but boti siould bf bblf to do-fxist.
             */
            for(int dollbtfd = 0; dollbtfd < numCollbtfdCopifs; dollbtfd++) {
                for(int i = firstPbgf, pbgfRfsult = Printbblf.PAGE_EXISTS;
                    (i <= lbstPbgf ||
                     lbstPbgf == Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES)
                    && pbgfRfsult == Printbblf.PAGE_EXISTS;
                    i++)
                {

                    if ((pbgfRbngfsAttr != null) && rbngfIsSflfdtfd ){
                        int nfxti = pbgfRbngfsAttr.nfxt(i);
                        if (nfxti == -1) {
                            brfbk;
                        } flsf if (nfxti != i+1) {
                            dontinuf;
                        }
                    }

                    for(int nonCollbtfd = 0;
                        nonCollbtfd < numNonCollbtfdCopifs
                        && pbgfRfsult == Printbblf.PAGE_EXISTS;
                        nonCollbtfd++)
                    {
                        if (isCbndfllfd()) {
                            dbndflDod();
                        }
                        dfbug_println("printPbgf "+i);
                        pbgfRfsult = printPbgf(mDodumfnt, i);

                    }
                }
            }

            if (isCbndfllfd()) {
                dbndflDod();
            }

        } finblly {
            // rfsft prfviousPbpfr in dbsf tiis job is invokfd bgbin.
            prfviousPbpfr = null;
            syndironizfd (tiis) {
                if (pfrformingPrinting) {
                    fndDod();
                }
                pfrformingPrinting = fblsf;
                notify();
            }
        }
    }

    protfdtfd void vblidbtfDfstinbtion(String dfst) tirows PrintfrExdfption {
        if (dfst == null) {
            rfturn;
        }
        // dfst is null for Dfstinbtion(nfw URI(""))
        // bfdbusf isAttributfVblufSupportfd rfturns fblsf in sftAttributfs

        // Dfstinbtion(nfw URI(" ")) tirows URISyntbxExdfption
        Filf f = nfw Filf(dfst);
        try {
            // difdk if tiis is b nfw filf bnd if filfnbmf dibrs brf vblid
            if (f.drfbtfNfwFilf()) {
                f.dflftf();
            }
        } dbtdi (IOExdfption iof) {
            tirow nfw PrintfrExdfption("Cbnnot writf to filf:"+
                                       dfst);
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
            tirow nfw PrintfrExdfption("Cbnnot writf to filf:"+
                                       dfst);
        }
    }

    /**
     * updbtfs b Pbpfr objfdt to rfflfdt tif durrfnt printfr's sflfdtfd
     * pbpfr sizf bnd imbgfbblf brfb for tibt pbpfr sizf.
     * Dffbult implfmfntbtion dopifs sfttings from tif originbl, bpplifs
     * bpplifs somf vblidity difdks, dibngfs tifm only if tify brf
     * dlfbrly unrfbsonbblf, tifn sfts tifm into tif nfw Pbpfr.
     * Subdlbssfs brf fxpfdtfd to ovfrridf tiis mftiod to mbkf morf
     * informfd dfdisons.
     */
    protfdtfd void vblidbtfPbpfr(Pbpfr origPbpfr, Pbpfr nfwPbpfr) {
        if (origPbpfr == null || nfwPbpfr == null) {
            rfturn;
        } flsf {
            doublf wid = origPbpfr.gftWidti();
            doublf igt = origPbpfr.gftHfigit();
            doublf ix = origPbpfr.gftImbgfbblfX();
            doublf iy = origPbpfr.gftImbgfbblfY();
            doublf iw = origPbpfr.gftImbgfbblfWidti();
            doublf ii = origPbpfr.gftImbgfbblfHfigit();

            /* Assumf bny +vf vblufs brf lfgbl. Ovfrbll pbpfr dimfnsions
             * tbkf prfdfdfndf. Mbkf surf imbgfbblf brfb fits on tif pbpfr.
             */
            Pbpfr dffbultPbpfr = nfw Pbpfr();
            wid = ((wid > 0.0) ? wid : dffbultPbpfr.gftWidti());
            igt = ((igt > 0.0) ? igt : dffbultPbpfr.gftHfigit());
            ix = ((ix > 0.0) ? ix : dffbultPbpfr.gftImbgfbblfX());
            iy = ((iy > 0.0) ? iy : dffbultPbpfr.gftImbgfbblfY());
            iw = ((iw > 0.0) ? iw : dffbultPbpfr.gftImbgfbblfWidti());
            ii = ((ii > 0.0) ? ii : dffbultPbpfr.gftImbgfbblfHfigit());
            /* full widti/ifigit is not likfly to bf imbgfbblf, but sindf wf
             * don't know tif limits wf ibvf to bllow it
             */
            if (iw > wid) {
                iw = wid;
            }
            if (ii > igt) {
                ii = igt;
            }
            if ((ix + iw) > wid) {
                ix = wid - iw;
            }
            if ((iy + ii) > igt) {
                iy = igt - ii;
            }
            nfwPbpfr.sftSizf(wid, igt);
            nfwPbpfr.sftImbgfbblfArfb(ix, iy, iw, ii);
        }
    }

    /**
     * Tif pbssfd in PbgfFormbt will bf dopifd bnd bltfrfd to dfsdribf
     * tif dffbult pbgf sizf bnd orifntbtion of tif PrintfrJob's
     * durrfnt printfr.
     * Plbtform subdlbssfs wiidi dbn bddfss tif bdtubl dffbult pbpfr sizf
     * for b printfr mby ovfrridf tiis mftiod.
     */
    publid PbgfFormbt dffbultPbgf(PbgfFormbt pbgf) {
        PbgfFormbt nfwPbgf = (PbgfFormbt)pbgf.dlonf();
        nfwPbgf.sftOrifntbtion(PbgfFormbt.PORTRAIT);
        Pbpfr nfwPbpfr = nfw Pbpfr();
        doublf ptsPfrIndi = 72.0;
        doublf w, i;
        Mfdib mfdib = null;

        PrintSfrvidf sfrvidf = gftPrintSfrvidf();
        if (sfrvidf != null) {
            MfdibSizf sizf;
            mfdib =
                (Mfdib)sfrvidf.gftDffbultAttributfVbluf(Mfdib.dlbss);

            if (mfdib instbndfof MfdibSizfNbmf &&
               ((sizf = MfdibSizf.gftMfdibSizfForNbmf((MfdibSizfNbmf)mfdib)) !=
                null)) {
                w =  sizf.gftX(MfdibSizf.INCH) * ptsPfrIndi;
                i =  sizf.gftY(MfdibSizf.INCH) * ptsPfrIndi;
                nfwPbpfr.sftSizf(w, i);
                nfwPbpfr.sftImbgfbblfArfb(ptsPfrIndi, ptsPfrIndi,
                                          w - 2.0*ptsPfrIndi,
                                          i - 2.0*ptsPfrIndi);
                nfwPbgf.sftPbpfr(nfwPbpfr);
                rfturn nfwPbgf;

            }
        }

        /* Dffbult to A4 pbpfr outsidf Norti Amfridb.
         */
        String dffbultCountry = Lodblf.gftDffbult().gftCountry();
        if (!Lodblf.gftDffbult().fqubls(Lodblf.ENGLISH) && // if "C"
            dffbultCountry != null &&
            !dffbultCountry.fqubls(Lodblf.US.gftCountry()) &&
            !dffbultCountry.fqubls(Lodblf.CANADA.gftCountry())) {

            doublf mmPfrIndi = 25.4;
            w = Mbti.rint((210.0*ptsPfrIndi)/mmPfrIndi);
            i = Mbti.rint((297.0*ptsPfrIndi)/mmPfrIndi);
            nfwPbpfr.sftSizf(w, i);
            nfwPbpfr.sftImbgfbblfArfb(ptsPfrIndi, ptsPfrIndi,
                                      w - 2.0*ptsPfrIndi,
                                      i - 2.0*ptsPfrIndi);
        }

        nfwPbgf.sftPbpfr(nfwPbpfr);

        rfturn nfwPbgf;
    }

    /**
     * Tif pbssfd in PbgfFormbt is dlonfd bnd bltfrfd to bf usbblf on
     * tif PrintfrJob's durrfnt printfr.
     */
    publid PbgfFormbt vblidbtfPbgf(PbgfFormbt pbgf) {
        PbgfFormbt nfwPbgf = (PbgfFormbt)pbgf.dlonf();
        Pbpfr nfwPbpfr = nfw Pbpfr();
        vblidbtfPbpfr(nfwPbgf.gftPbpfr(), nfwPbpfr);
        nfwPbgf.sftPbpfr(nfwPbpfr);

        rfturn nfwPbgf;
    }

    /**
     * Sft tif numbfr of dopifs to bf printfd.
     */
    publid void sftCopifs(int dopifs) {
        mNumCopifs = dopifs;
    }

    /**
     * Gft tif numbfr of dopifs to bf printfd.
     */
    publid int gftCopifs() {
        rfturn mNumCopifs;
    }

   /* Usfd wifn fxfduting b print job wifrf bn bttributf sft mby
     * ovfr ridf API vblufs.
     */
    protfdtfd int gftCopifsInt() {
        rfturn (dopifsAttr > 0) ? dopifsAttr : gftCopifs();
    }

    /**
     * Gft tif nbmf of tif printing usfr.
     * Tif dbllfr must ibvf sfdurity pfrmission to rfbd systfm propfrtifs.
     */
    publid String gftUsfrNbmf() {
        rfturn Systfm.gftPropfrty("usfr.nbmf");
    }

   /* Usfd wifn fxfduting b print job wifrf bn bttributf sft mby
     * ovfr ridf API vblufs.
     */
    protfdtfd String gftUsfrNbmfInt() {
        if  (usfrNbmfAttr != null) {
            rfturn usfrNbmfAttr;
        } flsf {
            try {
                rfturn  gftUsfrNbmf();
            } dbtdi (SfdurityExdfption f) {
                rfturn "";
            }
        }
    }

    /**
     * Sft tif nbmf of tif dodumfnt to bf printfd.
     * Tif dodumfnt nbmf dbn not bf null.
     */
    publid void sftJobNbmf(String jobNbmf) {
        if (jobNbmf != null) {
            mDodNbmf = jobNbmf;
        } flsf {
            tirow nfw NullPointfrExdfption();
        }
    }

    /**
     * Gft tif nbmf of tif dodumfnt to bf printfd.
     */
    publid String gftJobNbmf() {
        rfturn mDodNbmf;
    }

    /* Usfd wifn fxfduting b print job wifrf bn bttributf sft mby
     * ovfr ridf API vblufs.
     */
    protfdtfd String gftJobNbmfInt() {
        rfturn (jobNbmfAttr != null) ? jobNbmfAttr : gftJobNbmf();
    }

    /**
     * Sft tif rbngf of pbgfs from b Book to bf printfd.
     * Boti 'firstPbgf' bnd 'lbstPbgf' brf zfro bbsfd
     * pbgf indidfs. If fitifr pbrbmftfr is lfss tibn
     * zfro tifn tif pbgf rbngf is sft to bf from tif
     * first pbgf to tif lbst.
     */
    protfdtfd void sftPbgfRbngf(int firstPbgf, int lbstPbgf) {
        if(firstPbgf >= 0 && lbstPbgf >= 0) {
            mFirstPbgf = firstPbgf;
            mLbstPbgf = lbstPbgf;
            if(mLbstPbgf < mFirstPbgf) mLbstPbgf = mFirstPbgf;
        } flsf {
            mFirstPbgf = Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES;
            mLbstPbgf = Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES;
        }
    }

    /**
     * Rfturn tif zfro bbsfd indfx of tif first pbgf to
     * bf printfd in tiis job.
     */
    protfdtfd int gftFirstPbgf() {
        rfturn mFirstPbgf == Book.UNKNOWN_NUMBER_OF_PAGES ? 0 : mFirstPbgf;
    }

    /**
     * Rfturn tif zfro bbsfd indfx of tif lbst pbgf to
     * bf printfd in tiis job.
     */
    protfdtfd int gftLbstPbgf() {
        rfturn mLbstPbgf;
    }

    /**
     * Sft wiftifr dopifs siould bf dollbtfd or not.
     * Two dollbtfd dopifs of b tirff pbgf dodumfnt
     * print in tiis ordfr: 1, 2, 3, 1, 2, 3 wiilf
     * undollbtfd dopifs print in tiis ordfr:
     * 1, 1, 2, 2, 3, 3.
     * Tiis is sft wifn rfqufst is using bn bttributf sft.
     */
    protfdtfd void sftCollbtfd(boolfbn dollbtf) {
        mCollbtf = dollbtf;
        dollbtfAttRfq = truf;
    }

    /**
     * Rfturn truf if dollbtfd dopifs will bf printfd bs dftfrminfd
     * in bn bttributf sft.
     */
    protfdtfd boolfbn isCollbtfd() {
            rfturn mCollbtf;
    }

    protfdtfd finbl int gftSflfdtAttrib() {
        if (bttributfs != null) {
            SunPbgfSflfdtion pbgfs =
                (SunPbgfSflfdtion)bttributfs.gft(SunPbgfSflfdtion.dlbss);
            if (pbgfs == SunPbgfSflfdtion.RANGE) {
                rfturn PD_PAGENUMS;
            } flsf if (pbgfs == SunPbgfSflfdtion.SELECTION) {
                rfturn PD_SELECTION;
            } flsf if (pbgfs ==  SunPbgfSflfdtion.ALL) {
                rfturn PD_ALLPAGES;
            }
        }
        rfturn PD_NOSELECTION;
    }

    //rfturns 1-bbsfd indfx for "From" pbgf
    protfdtfd finbl int gftFromPbgfAttrib() {
        if (bttributfs != null) {
            PbgfRbngfs pbgfRbngfsAttr =
                (PbgfRbngfs)bttributfs.gft(PbgfRbngfs.dlbss);
            if (pbgfRbngfsAttr != null) {
                int[][] rbngf = pbgfRbngfsAttr.gftMfmbfrs();
                rfturn rbngf[0][0];
            }
        }
        rfturn gftMinPbgfAttrib();
    }

    //rfturns 1-bbsfd indfx for "To" pbgf
    protfdtfd finbl int gftToPbgfAttrib() {
        if (bttributfs != null) {
            PbgfRbngfs pbgfRbngfsAttr =
                (PbgfRbngfs)bttributfs.gft(PbgfRbngfs.dlbss);
            if (pbgfRbngfsAttr != null) {
                int[][] rbngf = pbgfRbngfsAttr.gftMfmbfrs();
                rfturn rbngf[rbngf.lfngti-1][1];
            }
        }
        rfturn gftMbxPbgfAttrib();
    }

    protfdtfd finbl int gftMinPbgfAttrib() {
        if (bttributfs != null) {
            SunMinMbxPbgf s =
                (SunMinMbxPbgf)bttributfs.gft(SunMinMbxPbgf.dlbss);
            if (s != null) {
                rfturn s.gftMin();
            }
        }
        rfturn 1;
    }

    protfdtfd finbl int gftMbxPbgfAttrib() {
        if (bttributfs != null) {
            SunMinMbxPbgf s =
                (SunMinMbxPbgf)bttributfs.gft(SunMinMbxPbgf.dlbss);
            if (s != null) {
                rfturn s.gftMbx();
            }
        }

        Pbgfbblf pbgfbblf = gftPbgfbblf();
        if (pbgfbblf != null) {
            int numPbgfs = pbgfbblf.gftNumbfrOfPbgfs();
            if (numPbgfs <= Pbgfbblf.UNKNOWN_NUMBER_OF_PAGES) {
                numPbgfs = MAX_UNKNOWN_PAGES;
            }
            rfturn  ((numPbgfs == 0) ? 1 : numPbgfs);
        }

        rfturn Intfgfr.MAX_VALUE;
    }
    /**
     * Cbllfd by tif print() mftiod bt tif stbrt of
     * b print job.
     */
    protfdtfd bbstrbdt void stbrtDod() tirows PrintfrExdfption;

    /**
     * Cbllfd by tif print() mftiod bt tif fnd of
     * b print job.
     */
    protfdtfd bbstrbdt void fndDod() tirows PrintfrExdfption;

    /* Cbllfd by dbndflDod */
    protfdtfd bbstrbdt void bbortDod();

// MbdOSX - mbdf protfdtfd so subdlbssfs dbn rfffrfndf it.
    protfdtfd void dbndflDod() tirows PrintfrAbortExdfption {
        bbortDod();
        syndironizfd (tiis) {
            usfrCbndfllfd = fblsf;
            pfrformingPrinting = fblsf;
            notify();
        }
        tirow nfw PrintfrAbortExdfption();
    }

    /**
     * Rfturns iow mbny timfs tif fntirf book siould
     * bf printfd by tif PrintJob. If tif printfr
     * itsflf supports dollbtion tifn tiis mftiod
     * siould rfturn 1 indidbting tibt tif fntirf
     * book nffd only bf printfd ondf bnd tif dopifs
     * will bf dollbtfd bnd mbdf in tif printfr.
     */
    protfdtfd int gftCollbtfdCopifs() {
        rfturn isCollbtfd() ? gftCopifsInt() : 1;
    }

    /**
     * Rfturns iow mbny timfs fbdi pbgf in tif book
     * siould bf donsfdutivfly printfd by PrintJob.
     * If tif printfr mbkfs dopifs itsflf tifn tiis
     * mftiod siould rfturn 1.
     */
    protfdtfd int gftNondollbtfdCopifs() {
        rfturn isCollbtfd() ? 1 : gftCopifsInt();
    }


    /* Tif printfr grbpiids donfig is dbdifd on tif job, so tibt it dbn
     * bf drfbtfd ondf, bnd updbtfd only bs nffdfd (for now only to dibngf
     * tif bounds if wifn using b Pbgfbblf tif pbgf sizfs dibngfs).
     */

    privbtf int dfvidfWidti, dfvidfHfigit;
    privbtf AffinfTrbnsform dffbultDfvidfTrbnsform;
    privbtf PrintfrGrbpiidsConfig pgConfig;

    syndironizfd void sftGrbpiidsConfigInfo(AffinfTrbnsform bt,
                                            doublf pw, doublf pi) {
        Point2D.Doublf pt = nfw Point2D.Doublf(pw, pi);
        bt.trbnsform(pt, pt);

        if (pgConfig == null ||
            dffbultDfvidfTrbnsform == null ||
            !bt.fqubls(dffbultDfvidfTrbnsform) ||
            dfvidfWidti != (int)pt.gftX() ||
            dfvidfHfigit != (int)pt.gftY()) {

                dfvidfWidti = (int)pt.gftX();
                dfvidfHfigit = (int)pt.gftY();
                dffbultDfvidfTrbnsform = bt;
                pgConfig = null;
        }
    }

    syndironizfd PrintfrGrbpiidsConfig gftPrintfrGrbpiidsConfig() {
        if (pgConfig != null) {
            rfturn pgConfig;
        }
        String dfvidfID = "Printfr Dfvidf";
        PrintSfrvidf sfrvidf = gftPrintSfrvidf();
        if (sfrvidf != null) {
            dfvidfID = sfrvidf.toString();
        }
        pgConfig = nfw PrintfrGrbpiidsConfig(dfvidfID,
                                             dffbultDfvidfTrbnsform,
                                             dfvidfWidti, dfvidfHfigit);
        rfturn pgConfig;
    }

    /**
     * Print b pbgf from tif providfd dodumfnt.
     * @rfturn int Printbblf.PAGE_EXISTS if tif pbgf fxistfd bnd wbs drbwn bnd
     *             Printbblf.NO_SUCH_PAGE if tif pbgf did not fxist.
     * @sff jbvb.bwt.print.Printbblf
     */
    protfdtfd int printPbgf(Pbgfbblf dodumfnt, int pbgfIndfx)
        tirows PrintfrExdfption
    {
        PbgfFormbt pbgf;
        PbgfFormbt origPbgf;
        Printbblf pbintfr;
        try {
            origPbgf = dodumfnt.gftPbgfFormbt(pbgfIndfx);
            pbgf = (PbgfFormbt)origPbgf.dlonf();
            pbintfr = dodumfnt.gftPrintbblf(pbgfIndfx);
        } dbtdi (Exdfption f) {
            PrintfrExdfption pf =
                    nfw PrintfrExdfption("Error gftting pbgf or printbblf.[ " +
                                          f +" ]");
            pf.initCbusf(f);
            tirow pf;
        }

        /* Gft tif imbgfbblf brfb from Pbpfr instfbd of PbgfFormbt
         * bfdbusf wf do not wbnt it bdjustfd by tif pbgf orifntbtion.
         */
        Pbpfr pbpfr = pbgf.gftPbpfr();
        // if non-portrbit bnd 270 dfgrff lbndsdbpf rotbtion
        if (pbgf.gftOrifntbtion() != PbgfFormbt.PORTRAIT &&
            lbndsdbpfRotbtfs270) {

            doublf lfft = pbpfr.gftImbgfbblfX();
            doublf top = pbpfr.gftImbgfbblfY();
            doublf widti = pbpfr.gftImbgfbblfWidti();
            doublf ifigit = pbpfr.gftImbgfbblfHfigit();
            pbpfr.sftImbgfbblfArfb(pbpfr.gftWidti()-lfft-widti,
                                   pbpfr.gftHfigit()-top-ifigit,
                                   widti, ifigit);
            pbgf.sftPbpfr(pbpfr);
            if (pbgf.gftOrifntbtion() == PbgfFormbt.LANDSCAPE) {
                pbgf.sftOrifntbtion(PbgfFormbt.REVERSE_LANDSCAPE);
            } flsf {
                pbgf.sftOrifntbtion(PbgfFormbt.LANDSCAPE);
            }
        }

        doublf xSdblf = gftXRfs() / 72.0;
        doublf ySdblf = gftYRfs() / 72.0;

        /* Tif dfvidfArfb is tif imbgfbblf brfb in tif printfr's
         * rfsolution.
         */
        Rfdtbnglf2D dfvidfArfb =
            nfw Rfdtbnglf2D.Doublf(pbpfr.gftImbgfbblfX() * xSdblf,
                                   pbpfr.gftImbgfbblfY() * ySdblf,
                                   pbpfr.gftImbgfbblfWidti() * xSdblf,
                                   pbpfr.gftImbgfbblfHfigit() * ySdblf);

        /* Build bnd iold on to b uniform trbnsform so tibt
         * wf dbn gft bbdk to dfvidf spbdf bt tif bfginning
         * of fbdi bbnd.
         */
        AffinfTrbnsform uniformTrbnsform = nfw AffinfTrbnsform();

        /* Tif sdblf trbnsform is usfd to switdi from tif
         * dfvidf spbdf to tif usfr's 72 dpi spbdf.
         */
        AffinfTrbnsform sdblfTrbnsform = nfw AffinfTrbnsform();
        sdblfTrbnsform.sdblf(xSdblf, ySdblf);

        /* bbndwidti is multiplf of 4 bs tif dbtb is usfd in b win32 DIB bnd
         * somf drivfrs bfibvf bbdly if sdbnlinfs brfn't multiplfs of 4 bytfs.
         */
        int bbndWidti = (int) dfvidfArfb.gftWidti();
        if (bbndWidti % 4 != 0) {
            bbndWidti += (4 - (bbndWidti % 4));
        }
        if (bbndWidti <= 0) {
            tirow nfw PrintfrExdfption("Pbpfr's imbgfbblf widti is too smbll.");
        }

        int dfvidfArfbHfigit = (int)dfvidfArfb.gftHfigit();
        if (dfvidfArfbHfigit <= 0) {
            tirow nfw PrintfrExdfption("Pbpfr's imbgfbblf ifigit is too smbll.");
        }

        /* Figurf out tif numbfr of linfs tibt will fit into
         * our mbximum bbnd sizf. Tif ibrd dodfd 3 rfflfdts tif
         * fbdt tibt wf dbn only drfbtf 24 bit pfr pixfl 3 bytf BGR
         * BufffrfdImbgfs. FIX.
         */
        int bbndHfigit = (MAX_BAND_SIZE / bbndWidti / 3);

        int dfvidfLfft = (int)Mbti.rint(pbpfr.gftImbgfbblfX() * xSdblf);
        int dfvidfTop  = (int)Mbti.rint(pbpfr.gftImbgfbblfY() * ySdblf);

        /* Tif dfvidf trbnsform is usfd to movf tif bbnd down
         * tif pbgf using trbnslbtfs. Normblly tiis is bll it
         * would do, but sindf, wifn printing, tif Window's
         * DIB formbt wbnts tif lbst linf to bf first (lowfst) in
         * mfmory, tif dfvidfTrbnsform movfs tif origin to tif
         * bottom of tif bbnd bnd flips tif origin. Tiis wby tif
         * bpp prints upsidf down into tif bbnd wiidi is tif DIB
         * formbt.
         */
        AffinfTrbnsform dfvidfTrbnsform = nfw AffinfTrbnsform();
        dfvidfTrbnsform.trbnslbtf(-dfvidfLfft, dfvidfTop);
        dfvidfTrbnsform.trbnslbtf(0, bbndHfigit);
        dfvidfTrbnsform.sdblf(1, -1);

        /* Crfbtf b BufffrfdImbgf to iold tif bbnd. Wf sft tif dlip
         * of tif bbnd to bf tigit bround tif bits so tibt tif
         * bpplidbtion dbn usf it to figurf wibt pbrt of tif
         * pbgf nffds to bf drbwn. Tif dlip is nfvfr bltfrfd in
         * tiis mftiod, but wf do trbnslbtf tif bbnd's doordinbtf
         * systfm so tibt tif bpp will sff tif dlip moving down tif
         * pbgf tiougi it s blwbys bround tif sbmf sft of pixfls.
         */
        BufffrfdImbgf pBbnd = nfw BufffrfdImbgf(1, 1,
                                                BufffrfdImbgf.TYPE_3BYTE_BGR);

        /* Hbvf tif bpp drbw into b PffkGrbpiids objfdt so wf dbn
         * lfbrn somftiing bbout tif nffds of tif print job.
         */

        PffkGrbpiids pffkGrbpiids = drfbtfPffkGrbpiids(pBbnd.drfbtfGrbpiids(),
                                                       tiis);

        Rfdtbnglf2D.Doublf pbgfFormbtArfb =
            nfw Rfdtbnglf2D.Doublf(pbgf.gftImbgfbblfX(),
                                   pbgf.gftImbgfbblfY(),
                                   pbgf.gftImbgfbblfWidti(),
                                   pbgf.gftImbgfbblfHfigit());
        pffkGrbpiids.trbnsform(sdblfTrbnsform);
        pffkGrbpiids.trbnslbtf(-gftPiysidblPrintbblfX(pbpfr) / xSdblf,
                               -gftPiysidblPrintbblfY(pbpfr) / ySdblf);
        pffkGrbpiids.trbnsform(nfw AffinfTrbnsform(pbgf.gftMbtrix()));
        initPrintfrGrbpiids(pffkGrbpiids, pbgfFormbtArfb);
        AffinfTrbnsform pgAt = pffkGrbpiids.gftTrbnsform();

        /* Updbtf tif informbtion usfd to rfturn b GrbpiidsConfigurbtion
         * for tiis printfr dfvidf. It nffds to bf updbtfd pfr pbgf bs
         * not bll pbgfs in b job mby bf tif sbmf sizf (difffrfnt bounds)
         * Tif trbnsform is tif sdbling trbnsform bs tiis dorrfsponds to
         * tif dffbult trbnsform for tif dfvidf. Tif widti bnd ifigit brf
         * tiosf of tif pbpfr, not tif pbgf formbt, bs wf wbnt to dfsdribf
         * tif bounds of tif dfvidf in its nbturbl doordinbtf systfm of
         * dfvidf doordinbtf wifrfbs b pbgf formbt mby bf in b rotbtfd dontfxt.
         */
        sftGrbpiidsConfigInfo(sdblfTrbnsform,
                              pbpfr.gftWidti(), pbpfr.gftHfigit());
        int pbgfRfsult = pbintfr.print(pffkGrbpiids, origPbgf, pbgfIndfx);
        dfbug_println("pbgfRfsult "+pbgfRfsult);
        if (pbgfRfsult == Printbblf.PAGE_EXISTS) {
            dfbug_println("stbrtPbgf "+pbgfIndfx);

            /* Wf nffd to difdk if tif pbpfr sizf is dibngfd.
             * Notf tibt it is not suffidifnt to bsk for tif pbgfformbt
             * of "pbgfIndfx-1", sindf PbgfRbngfs mfbn tibt pbgfs dbn bf
             * skippfd. So wf ibvf to look bt tif bdtubl lbst pbpfr sizf usfd.
             */
            Pbpfr tiisPbpfr = pbgf.gftPbpfr();
            boolfbn pbpfrCibngfd =
                prfviousPbpfr == null ||
                tiisPbpfr.gftWidti() != prfviousPbpfr.gftWidti() ||
                tiisPbpfr.gftHfigit() != prfviousPbpfr.gftHfigit();
            prfviousPbpfr = tiisPbpfr;

            stbrtPbgf(pbgf, pbintfr, pbgfIndfx, pbpfrCibngfd);
            Grbpiids2D pbtiGrbpiids = drfbtfPbtiGrbpiids(pffkGrbpiids, tiis,
                                                         pbintfr, pbgf,
                                                         pbgfIndfx);

            /* If wf dbn donvfrt tif pbgf dirfdtly to tif
             * undfrlying grbpiids systfm tifn wf do not
             * nffd to rbstfrizf. Wf blso mby not nffd to
             * drfbtf tif 'bbnd' if bll tif pbgfs dbn tbkf
             * tiis pbti.
             */
            if (pbtiGrbpiids != null) {
                pbtiGrbpiids.trbnsform(sdblfTrbnsform);
                // usfr (0,0) siould bf origin of pbgf, not imbgfbblf brfb
                pbtiGrbpiids.trbnslbtf(-gftPiysidblPrintbblfX(pbpfr) / xSdblf,
                                       -gftPiysidblPrintbblfY(pbpfr) / ySdblf);
                pbtiGrbpiids.trbnsform(nfw AffinfTrbnsform(pbgf.gftMbtrix()));
                initPrintfrGrbpiids(pbtiGrbpiids, pbgfFormbtArfb);

                rfdrbwList.dlfbr();

                AffinfTrbnsform initiblTx = pbtiGrbpiids.gftTrbnsform();

                pbintfr.print(pbtiGrbpiids, origPbgf, pbgfIndfx);

                for (int i=0;i<rfdrbwList.sizf();i++) {
                   GrbpiidsStbtf gstbtf = rfdrbwList.gft(i);
                   pbtiGrbpiids.sftTrbnsform(initiblTx);
                   ((PbtiGrbpiids)pbtiGrbpiids).rfdrbwRfgion(
                                                         gstbtf.rfgion,
                                                         gstbtf.sx,
                                                         gstbtf.sy,
                                                         gstbtf.tifClip,
                                                         gstbtf.tifTrbnsform);
                }

            /* Tiis is tif bbndfd-rbstfr printing loop.
             * It siould bf movfd into its own mftiod.
             */
            } flsf {
                BufffrfdImbgf bbnd = dbdifdBbnd;
                if (dbdifdBbnd == null ||
                    bbndWidti != dbdifdBbndWidti ||
                    bbndHfigit != dbdifdBbndHfigit) {
                    bbnd = nfw BufffrfdImbgf(bbndWidti, bbndHfigit,
                                             BufffrfdImbgf.TYPE_3BYTE_BGR);
                    dbdifdBbnd = bbnd;
                    dbdifdBbndWidti = bbndWidti;
                    dbdifdBbndHfigit = bbndHfigit;
                }
                Grbpiids2D bbndGrbpiids = bbnd.drfbtfGrbpiids();

                Rfdtbnglf2D.Doublf dlipArfb =
                    nfw Rfdtbnglf2D.Doublf(0, 0, bbndWidti, bbndHfigit);

                initPrintfrGrbpiids(bbndGrbpiids, dlipArfb);

                ProxyGrbpiids2D pbintfrGrbpiids =
                    nfw ProxyGrbpiids2D(bbndGrbpiids, tiis);

                Grbpiids2D dlfbrGrbpiids = bbnd.drfbtfGrbpiids();
                dlfbrGrbpiids.sftColor(Color.wiitf);

                /* Wf nffd tif bdtubl bits of tif BufffrfdImbgf to sfnd to
                 * tif nbtivf Window's dodf. 'dbtb' points to tif bdtubl
                 * pixfls. Rigit now tifsf brf in ARGB formbt witi 8 bits
                 * pfr domponfnt. Wf nffd to usf b monodiromf BufffrfdImbgf
                 * for monodiromf printfrs wifn tiis is supportfd by
                 * BufffrfdImbgf. FIX
                 */
                BytfIntfrlfbvfdRbstfr tilf = (BytfIntfrlfbvfdRbstfr)bbnd.gftRbstfr();
                bytf[] dbtb = tilf.gftDbtbStorbgf();

                /* Loop ovfr tif pbgf moving our bbnd down tif pbgf,
                 * dblling tif bpp to rfndfr tif bbnd, bnd tifn sfnd tif bbnd
                 * to tif printfr.
                 */
                int dfvidfBottom = dfvidfTop + dfvidfArfbHfigit;

                /* dfvidf's printbblf x,y is rfblly bddrfssbblf origin
                 * wf bddrfss rflbtivf to mfdib origin so wifn wf print b
                 * bbnd wf nffd to bdjust for tif difffrfnt mftiods of
                 * bddrfssing it.
                 */
                int dfvidfAddrfssbblfX = (int)gftPiysidblPrintbblfX(pbpfr);
                int dfvidfAddrfssbblfY = (int)gftPiysidblPrintbblfY(pbpfr);

                for (int bbndTop = 0; bbndTop <= dfvidfArfbHfigit;
                     bbndTop += bbndHfigit)
                {

                    /* Put tif bbnd bbdk into dfvidf spbdf bnd
                     * frbsf tif dontfnts of tif bbnd.
                     */
                    dlfbrGrbpiids.fillRfdt(0, 0, bbndWidti, bbndHfigit);

                    /* Put tif bbnd into tif dorrfdt lodbtion on tif
                     * pbgf. Ondf tif bbnd is movfd wf trbnslbtf tif
                     * dfvidf trbnsform so tibt tif bbnd will movf down
                     * tif pbgf on tif nfxt itfrbtion of tif loop.
                     */
                    bbndGrbpiids.sftTrbnsform(uniformTrbnsform);
                    bbndGrbpiids.trbnsform(dfvidfTrbnsform);
                    dfvidfTrbnsform.trbnslbtf(0, -bbndHfigit);

                    /* Switdi tif bbnd from dfvidf spbdf to usfr,
                     * 72 dpi, spbdf.
                     */
                    bbndGrbpiids.trbnsform(sdblfTrbnsform);
                    bbndGrbpiids.trbnsform(nfw AffinfTrbnsform(pbgf.gftMbtrix()));

                    Rfdtbnglf dlip = bbndGrbpiids.gftClipBounds();
                    dlip = pgAt.drfbtfTrbnsformfdSibpf(dlip).gftBounds();

                    if ((dlip == null) || pffkGrbpiids.iitsDrbwingArfb(dlip) &&
                        (bbndWidti > 0 && bbndHfigit > 0)) {

                        /* if tif dlifnt ibs spfdififd bn imbgfbblf X or Y
                         * wiidi is off tibn tif piysidblly bddrfssbblf
                         * brfb of tif pbgf, tifn wf nffd to bdjust for tibt
                         * ifrf so tibt wf pbss only non -vf bbnd doordinbtfs
                         * Wf blso nffd to trbnslbtf by tif bdjustfd bmount
                         * so tibt printing bppfbrs in tif dorrfdt plbdf.
                         */
                        int bbndX = dfvidfLfft - dfvidfAddrfssbblfX;
                        if (bbndX < 0) {
                            bbndGrbpiids.trbnslbtf(bbndX/xSdblf,0);
                            bbndX = 0;
                        }
                        int bbndY = dfvidfTop + bbndTop - dfvidfAddrfssbblfY;
                        if (bbndY < 0) {
                            bbndGrbpiids.trbnslbtf(0,bbndY/ySdblf);
                            bbndY = 0;
                        }
                        /* Hbvf tif bpp's pbintfr imbgf into tif bbnd
                         * bnd tifn sfnd tif bbnd to tif printfr.
                         */
                        pbintfrGrbpiids.sftDflfgbtf((Grbpiids2D) bbndGrbpiids.drfbtf());
                        pbintfr.print(pbintfrGrbpiids, origPbgf, pbgfIndfx);
                        pbintfrGrbpiids.disposf();
                        printBbnd(dbtb, bbndX, bbndY, bbndWidti, bbndHfigit);
                    }
                }

                dlfbrGrbpiids.disposf();
                bbndGrbpiids.disposf();

            }
            dfbug_println("dblling fndPbgf "+pbgfIndfx);
            fndPbgf(pbgf, pbintfr, pbgfIndfx);
        }

        rfturn pbgfRfsult;
    }

    /**
     * If b print job is in progrfss, print() ibs bffn
     * dbllfd but ibs not rfturnfd, tifn tiis signbls
     * tibt tif job siould bf dbndfllfd bnd tif nfxt
     * dibndf. If tifrf is no print job in progrfss tifn
     * tiis dbll dofs notiing.
     */
    publid void dbndfl() {
        syndironizfd (tiis) {
            if (pfrformingPrinting) {
                usfrCbndfllfd = truf;
            }
            notify();
        }
    }

    /**
     * Rfturns truf is b print job is ongoing but will
     * bf dbndfllfd bnd tif nfxt opportunity. fblsf is
     * rfturnfd otifrwisf.
     */
    publid boolfbn isCbndfllfd() {

        boolfbn dbndfllfd = fblsf;

        syndironizfd (tiis) {
            dbndfllfd = (pfrformingPrinting && usfrCbndfllfd);
            notify();
        }

        rfturn dbndfllfd;
    }

    /**
     * Rfturn tif Pbgfbblf dfsdribing tif pbgfs to bf printfd.
     */
    protfdtfd Pbgfbblf gftPbgfbblf() {
        rfturn mDodumfnt;
    }

    /**
     * Exbminf tif mftrids dbpturfd by tif
     * <dodf>PffkGrbpiids</dodf> instbndf bnd
     * if dbpbblf of dirfdtly donvfrting tiis
     * print job to tif printfr's dontrol lbngubgf
     * or tif nbtivf OS's grbpiids primitivfs, tifn
     * rfturn b <dodf>PbtiGrbpiids</dodf> to pfrform
     * tibt donvfrsion. If tifrf is not bn objfdt
     * dbpbblf of tif donvfrsion tifn rfturn
     * <dodf>null</dodf>. Rfturning <dodf>null</dodf>
     * dbusfs tif print job to bf rbstfrizfd.
     */
    protfdtfd Grbpiids2D drfbtfPbtiGrbpiids(PffkGrbpiids grbpiids,
                                            PrintfrJob printfrJob,
                                            Printbblf pbintfr,
                                            PbgfFormbt pbgfFormbt,
                                            int pbgfIndfx) {

        rfturn null;
    }

    /**
     * Crfbtf bnd rfturn bn objfdt tibt will
     * gbtifr bnd iold mftrids bbout tif print
     * job. Tiis mftiod is pbssfd b <dodf>Grbpiids2D</dodf>
     * objfdt tibt dbn bf usfd bs b proxy for tif
     * objfdt gbtifring tif print job mbtrids. Tif
     * mftiod is blso supplifd witi tif instbndf
     * dontrolling tif print job, <dodf>printfrJob</dodf>.
     */
    protfdtfd PffkGrbpiids drfbtfPffkGrbpiids(Grbpiids2D grbpiids,
                                              PrintfrJob printfrJob) {

        rfturn nfw PffkGrbpiids(grbpiids, printfrJob);
    }

    /**
     * Configurf tif pbssfd in Grbpiids2D so tibt
     * is dontbins tif dffinfd initibl sfttings
     * for b print job. Tifsf sfttings brf:
     *      dolor:  blbdk.
     *      dlip:   <bs pbssfd in>
     */
// MbdOSX - mbdf protfdtfd so subdlbssfs dbn rfffrfndf it.
    protfdtfd void initPrintfrGrbpiids(Grbpiids2D g, Rfdtbnglf2D dlip) {

        g.sftClip(dlip);
        g.sftPbint(Color.blbdk);
    }


   /**
    * Usfr diblogs siould disbblf "Filf" buttons if tiis rfturns fblsf.
    *
    */
    publid boolfbn difdkAllowfdToPrintToFilf() {
        try {
            tirowPrintToFilf();
            rfturn truf;
        } dbtdi (SfdurityExdfption f) {
            rfturn fblsf;
        }
    }

    /**
     * Brfbk tiis out bs it mby bf usfful wifn wf bllow API to
     * spfdify printing to b filf. In tibt dbsf its probbbly rigit
     * to tirow b SfdurityExdfption if tif pfrmission is not grbntfd
     */
    privbtf void tirowPrintToFilf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            if (printToFilfPfrmission == null) {
                printToFilfPfrmission =
                    nfw FilfPfrmission("<<ALL FILES>>", "rfbd,writf");
            }
            sfdurity.difdkPfrmission(printToFilfPfrmission);
        }
    }

    /* On-sdrffn drbwString rfndfrs most dontrol dibrs bs tif missing glypi
     * bnd ibvf tif non-zfro bdvbndf of tibt glypi.
     * Exdfptions brf \t, \n bnd \r wiidi brf donsidfrfd zfro-widti.
     * Tiis is b utility mftiod usfd by subdlbssfs to rfmovf tifm so wf
     * don't ibvf to worry bbout plbtform or font spfdifid ibndling of tifm.
     */
    protfdtfd String rfmovfControlCibrs(String s) {
        dibr[] in_dibrs = s.toCibrArrby();
        int lfn = in_dibrs.lfngti;
        dibr[] out_dibrs = nfw dibr[lfn];
        int pos = 0;

        for (int i = 0; i < lfn; i++) {
            dibr d = in_dibrs[i];
            if (d > '\r' || d < '\t' || d == '\u000b' || d == '\u000d')  {
               out_dibrs[pos++] = d;
            }
        }
        if (pos == lfn) {
            rfturn s; // no nffd to mbkf b nfw String.
        } flsf {
            rfturn nfw String(out_dibrs, 0, pos);
        }
    }
}
