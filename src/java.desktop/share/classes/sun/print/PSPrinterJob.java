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

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Font;
import jbvb.bwt.FontMftrids;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;

import jbvb.bwt.imbgf.BufffrfdImbgf;

import jbvb.bwt.font.FontRfndfrContfxt;

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.PbtiItfrbtor;
import jbvb.bwt.gfom.Rfdtbnglf2D;

import jbvb.bwt.imbgf.BufffrfdImbgf;

import jbvb.bwt.print.Pbgfbblf;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Pbpfr;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrExdfption;
import jbvb.bwt.print.PrintfrIOExdfption;
import jbvb.bwt.print.PrintfrJob;

import jbvbx.print.DodFlbvor;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.StrfbmPrintSfrvidf;
import jbvbx.print.bttributf.HbsiPrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.PrintSfrvidfAttributfSft;
import jbvbx.print.bttributf.stbndbrd.PrintfrNbmf;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.DiblogTypfSflfdtion;
import jbvbx.print.bttributf.stbndbrd.JobNbmf;
import jbvbx.print.bttributf.stbndbrd.Sidfs;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.io.BufffrfdRfbdfr;
import jbvb.io.CibrConvfrsionExdfption;
import jbvb.io.Filf;
import jbvb.io.InputStrfbm;
import jbvb.io.InputStrfbmRfbdfr;
import jbvb.io.IOExdfption;
import jbvb.io.FilfInputStrfbm;
import jbvb.io.FilfOutputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.PrintStrfbm;
import jbvb.io.PrintWritfr;
import jbvb.io.StringWritfr;

import jbvb.util.ArrbyList;
import jbvb.util.Enumfrbtion;
import jbvb.util.Lodblf;
import jbvb.util.Propfrtifs;

import sun.bwt.CibrsftString;
import sun.bwt.FontConfigurbtion;
import sun.bwt.FontDfsdriptor;
import sun.bwt.PlbtformFont;
import sun.bwt.SunToolkit;
import sun.font.FontMbnbgfrFbdtory;
import sun.font.FontUtilitifs;

import jbvb.nio.dibrsft.*;
import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;
import jbvb.nio.filf.Filfs;

//REMIND: Rfmovf usf of tiis dlbss wifn IPPPrintSfrvidf is movfd to sibrf dirfdtory.
import jbvb.lbng.rfflfdt.Mftiod;

/**
 * A dlbss wiidi initibtfs bnd fxfdutfs b PostSdript printfr job.
 *
 * @butior Ridibrd Blbndibrd
 */
publid dlbss PSPrintfrJob fxtfnds RbstfrPrintfrJob {

 /* Clbss Constbnts */

    /**
     * Pbssfd to tif <dodf>sftFillModf</dodf>
     * mftiod tiis vbluf fordfs fills to bf
     * donf using tif fvfn-odd fill rulf.
     */
    protfdtfd stbtid finbl int FILL_EVEN_ODD = 1;

    /**
     * Pbssfd to tif <dodf>sftFillModf</dodf>
     * mftiod tiis vbluf fordfs fills to bf
     * donf using tif non-zfro winding rulf.
     */
    protfdtfd stbtid finbl int FILL_WINDING = 2;

    /* PostSdript ibs b 64K mbximum on its strings.
     */
    privbtf stbtid finbl int MAX_PSSTR = (1024 * 64 - 1);

    privbtf stbtid finbl int RED_MASK = 0x00ff0000;
    privbtf stbtid finbl int GREEN_MASK = 0x0000ff00;
    privbtf stbtid finbl int BLUE_MASK = 0x000000ff;

    privbtf stbtid finbl int RED_SHIFT = 16;
    privbtf stbtid finbl int GREEN_SHIFT = 8;
    privbtf stbtid finbl int BLUE_SHIFT = 0;

    privbtf stbtid finbl int LOWNIBBLE_MASK = 0x0000000f;
    privbtf stbtid finbl int HINIBBLE_MASK =  0x000000f0;
    privbtf stbtid finbl int HINIBBLE_SHIFT = 4;
    privbtf stbtid finbl bytf ifxDigits[] = {
        (bytf)'0', (bytf)'1', (bytf)'2', (bytf)'3',
        (bytf)'4', (bytf)'5', (bytf)'6', (bytf)'7',
        (bytf)'8', (bytf)'9', (bytf)'A', (bytf)'B',
        (bytf)'C', (bytf)'D', (bytf)'E', (bytf)'F'
    };

    privbtf stbtid finbl int PS_XRES = 300;
    privbtf stbtid finbl int PS_YRES = 300;

    privbtf stbtid finbl String ADOBE_PS_STR =  "%!PS-Adobf-3.0";
    privbtf stbtid finbl String EOF_COMMENT =   "%%EOF";
    privbtf stbtid finbl String PAGE_COMMENT =  "%%Pbgf: ";

    privbtf stbtid finbl String READIMAGEPROC = "/imStr 0 dff /imbgfSrd " +
        "{durrfntfilf /ASCII85Dfdodf filtfr /RunLfngtiDfdodf filtfr " +
        " imStr rfbdstring pop } dff";

    privbtf stbtid finbl String COPIES =        "/#dopifs fxdi dff";
    privbtf stbtid finbl String PAGE_SAVE =     "/pgSbvf sbvf dff";
    privbtf stbtid finbl String PAGE_RESTORE =  "pgSbvf rfstorf";
    privbtf stbtid finbl String SHOWPAGE =      "siowpbgf";
    privbtf stbtid finbl String IMAGE_SAVE =    "/imSbvf sbvf dff";
    privbtf stbtid finbl String IMAGE_STR =     " string /imStr fxdi dff";
    privbtf stbtid finbl String IMAGE_RESTORE = "imSbvf rfstorf";

    privbtf stbtid finbl String SftFontNbmf = "F";

    privbtf stbtid finbl String DrbwStringNbmf = "S";

    /**
     * Tif PostSdript invodbtion to fill b pbti using tif
     * fvfn-odd rulf. (fofill)
     */
    privbtf stbtid finbl String EVEN_ODD_FILL_STR = "EF";

    /**
     * Tif PostSdript invodbtion to fill b pbti using tif
     * non-zfro winding rulf. (fill)
     */
    privbtf stbtid finbl String WINDING_FILL_STR = "WF";

    /**
     * Tif PostSdript to sft tif dlip to bf tif durrfnt pbti
     * using tif fvfn odd rulf. (fodlip)
     */
    privbtf stbtid finbl String EVEN_ODD_CLIP_STR = "EC";

    /**
     * Tif PostSdript to sft tif dlip to bf tif durrfnt pbti
     * using tif non-zfro winding rulf. (dlip)
     */
    privbtf stbtid finbl String WINDING_CLIP_STR = "WC";

    /**
     * Expfdting two numbfrs on tif PostSdript stbdk, tiis
     * invodbtion movfs tif durrfnt pfn position. (movfto)
     */
    privbtf stbtid finbl String MOVETO_STR = " M";
    /**
     * Expfdting two numbfrs on tif PostSdript stbdk, tiis
     * invodbtion drbws b PS linf from tif durrfnt pfn
     * position to tif point on tif stbdk. (linfto)
     */
    privbtf stbtid finbl String LINETO_STR = " L";

    /**
     * Tiis PostSdript opfrbtor tbkfs two dontrol points
     * bnd bn fnding point bnd using tif durrfnt pfn
     * position bs b stbrting point bdds b bfzifr
     * durvf to tif durrfnt pbti. (durvfto)
     */
    privbtf stbtid finbl String CURVETO_STR = " C";

    /**
     * Tif PostSdript to pop b stbtf off of tif printfr's
     * gstbtf stbdk. (grfstorf)
     */
    privbtf stbtid finbl String GRESTORE_STR = "R";
    /**
     * Tif PostSdript to pusi b stbtf on to tif printfr's
     * gstbtf stbdk. (gsbvf)
     */
    privbtf stbtid finbl String GSAVE_STR = "G";

    /**
     * Mbkf tif durrfnt PostSdript pbti bn fmpty pbti. (nfwpbti)
     */
    privbtf stbtid finbl String NEWPATH_STR = "N";

    /**
     * Closf tif durrfnt subpbti by gfnfrbting b linf sfgmfnt
     * from tif durrfnt position to tif stbrt of tif subpbti. (dlosfpbti)
     */
    privbtf stbtid finbl String CLOSEPATH_STR = "P";

    /**
     * Usf tif tirff numbfrs on top of tif PS opfrbtor
     * stbdk to sft tif rgb dolor. (sftrgbdolor)
     */
    privbtf stbtid finbl String SETRGBCOLOR_STR = " SC";

    /**
     * Usf tif top numbfr on tif stbdk to sft tif printfr's
     * durrfnt grby vbluf. (sftgrby)
     */
    privbtf stbtid finbl String SETGRAY_STR = " SG";

 /* Instbndf Vbribblfs */

   privbtf int mDfstTypf;

   privbtf String mDfstinbtion = "lp";

   privbtf boolfbn mNoJobSifft = fblsf;

   privbtf String mOptions;

   privbtf Font mLbstFont;

   privbtf Color mLbstColor;

   privbtf Sibpf mLbstClip;

   privbtf AffinfTrbnsform mLbstTrbnsform;

   privbtf doublf xrfs = PS_XRES;
   privbtf doublf yrfs = PS_XRES;

   /* non-null if printing EPS for Jbvb Plugin */
   privbtf EPSPrintfr fpsPrintfr = null;

   /**
    * Tif mftrids for tif font durrfntly sft.
    */
   FontMftrids mCurMftrids;

   /**
    * Tif output strfbm to wiidi tif gfnfrbtfd PostSdript
    * is writtfn.
    */
   PrintStrfbm mPSStrfbm;

   /* Tif tfmporbry filf to wiidi wf spool bfforf sfnding to tif printfr  */

   Filf spoolFilf;

   /**
    * Tiis string iolds tif PostSdript opfrbtor to
    * bf usfd to fill b pbti. It dbn bf dibngfd
    * by tif <dodf>sftFillModf</dodf> mftiod.
    */
    privbtf String mFillOpStr = WINDING_FILL_STR;

   /**
    * Tiis string iolds tif PostSdript opfrbtor to
    * bf usfd to dlip to b pbti. It dbn bf dibngfd
    * by tif <dodf>sftFillModf</dodf> mftiod.
    */
    privbtf String mClipOpStr = WINDING_CLIP_STR;

   /**
    * A stbdk tibt rfprfsfnts tif PostSdript gstbtf stbdk.
    */
   ArrbyList<GStbtf> mGStbtfStbdk = nfw ArrbyList<>();

   /**
    * Tif x doordinbtf of tif durrfnt pfn position.
    */
   privbtf flobt mPfnX;

   /**
    * Tif y doordinbtf of tif durrfnt pfn position.
    */
   privbtf flobt mPfnY;

   /**
    * Tif x doordinbtf of tif stbrting point of
    * tif durrfnt subpbti.
    */
   privbtf flobt mStbrtPbtiX;

   /**
    * Tif y doordinbtf of tif stbrting point of
    * tif durrfnt subpbti.
    */
   privbtf flobt mStbrtPbtiY;

   /**
    * An optionbl mbpping of fonts to PostSdript nbmfs.
    */
   privbtf stbtid Propfrtifs mFontProps = null;

   privbtf stbtid boolfbn isMbd;

    /* Clbss stbtid initiblisfr blodk */
    stbtid {
       //fnbblf privilfdgfs so initProps dbn bddfss systfm propfrtifs,
        // opfn tif propfrty filf, ftd.
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {
            publid Objfdt run() {
                mFontProps = initProps();
                String osNbmf = Systfm.gftPropfrty("os.nbmf");
                isMbd = osNbmf.stbrtsWiti("Mbd");
                rfturn null;
            }
        });
    }

    /*
     * Initiblizf PostSdript font propfrtifs.
     * Copifd from PSPrintStrfbm
     */
    privbtf stbtid Propfrtifs initProps() {
        // sfbrdi psfont.propfrtifs for fonts
        // bnd drfbtf bnd initiblizf fontProps if it fxist.

        String jiomf = Systfm.gftPropfrty("jbvb.iomf");

        if (jiomf != null){
            String ulodblf = SunToolkit.gftStbrtupLodblf().gftLbngubgf();
            try {

                Filf f = nfw Filf(jiomf + Filf.sfpbrbtor +
                                  "lib" + Filf.sfpbrbtor +
                                  "psfontj2d.propfrtifs." + ulodblf);

                if (!f.dbnRfbd()){

                    f = nfw Filf(jiomf + Filf.sfpbrbtor +
                                      "lib" + Filf.sfpbrbtor +
                                      "psfont.propfrtifs." + ulodblf);
                    if (!f.dbnRfbd()){

                        f = nfw Filf(jiomf + Filf.sfpbrbtor + "lib" +
                                     Filf.sfpbrbtor + "psfontj2d.propfrtifs");

                        if (!f.dbnRfbd()){

                            f = nfw Filf(jiomf + Filf.sfpbrbtor + "lib" +
                                         Filf.sfpbrbtor + "psfont.propfrtifs");

                            if (!f.dbnRfbd()){
                                rfturn (Propfrtifs)null;
                            }
                        }
                    }
                }

                // Lobd propfrty filf
                InputStrfbm in =
                    nfw BufffrfdInputStrfbm(nfw FilfInputStrfbm(f.gftPbti()));
                Propfrtifs props = nfw Propfrtifs();
                props.lobd(in);
                in.dlosf();
                rfturn props;
            } dbtdi (Exdfption f){
                rfturn (Propfrtifs)null;
            }
        }
        rfturn (Propfrtifs)null;
    }

 /* Construdtors */

    publid PSPrintfrJob()
    {
    }

 /* Instbndf Mftiods */

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

        if (bttributfs == null) {
            bttributfs = nfw HbsiPrintRfqufstAttributfSft();
        }
        bttributfs.bdd(nfw Copifs(gftCopifs()));
        bttributfs.bdd(nfw JobNbmf(gftJobNbmf(), null));

        boolfbn doPrint = fblsf;
        DiblogTypfSflfdtion dts =
            (DiblogTypfSflfdtion)bttributfs.gft(DiblogTypfSflfdtion.dlbss);
        if (dts == DiblogTypfSflfdtion.NATIVE) {
            // Rfmovf DiblogTypfSflfdtion.NATIVE to prfvfnt infinitf loop in
            // RbstfrPrintfrJob.
            bttributfs.rfmovf(DiblogTypfSflfdtion.dlbss);
            doPrint = printDiblog(bttributfs);
            // rfstorf bttributf
            bttributfs.bdd(DiblogTypfSflfdtion.NATIVE);
        } flsf {
            doPrint = printDiblog(bttributfs);
        }

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
                    mDfstinbtion = "out.ps";
                }
            } flsf {
                mDfstTypf = RbstfrPrintfrJob.PRINTER;
                PrintSfrvidf pSfrv = gftPrintSfrvidf();
                if (pSfrv != null) {
                    mDfstinbtion = pSfrv.gftNbmf();
                   if (isMbd) {
                        PrintSfrvidfAttributfSft psbSft = pSfrv.gftAttributfs() ;
                        if (psbSft != null) {
                            mDfstinbtion = psbSft.gft(PrintfrNbmf.dlbss).toString();
                        }
                    }
                }
            }
        }

        rfturn doPrint;
    }

    /**
     * Invokfd by tif RbstfrPrintfrJob supfr dlbss
     * tiis mftiod is dbllfd to mbrk tif stbrt of b
     * dodumfnt.
     */
    protfdtfd void stbrtDod() tirows PrintfrExdfption {

        // A sfdurity difdk ibs bffn pfrformfd in tif
        // jbvb.bwt.print.printfrJob.gftPrintfrJob mftiod.
        // Wf usf bn innfr dlbss to fxfdutf tif privilgfd opfn opfrbtions.
        // Notf tibt wf only opfn b filf if it ibs bffn nominbtfd by
        // tif fnd-usfr in b diblog tibt wf ousflvfs put up.

        OutputStrfbm output;

        if (fpsPrintfr == null) {
            if (gftPrintSfrvidf() instbndfof PSStrfbmPrintSfrvidf) {
                StrfbmPrintSfrvidf sps = (StrfbmPrintSfrvidf)gftPrintSfrvidf();
                mDfstTypf = RbstfrPrintfrJob.STREAM;
                if (sps.isDisposfd()) {
                    tirow nfw PrintfrExdfption("sfrvidf is disposfd");
                }
                output = sps.gftOutputStrfbm();
                if (output == null) {
                    tirow nfw PrintfrExdfption("Null output strfbm");
                }
            } flsf {
                /* REMIND: Tiis nffds to bf morf mbintbinbblf */
                mNoJobSifft = supfr.noJobSifft;
                if (supfr.dfstinbtionAttr != null) {
                    mDfstTypf = RbstfrPrintfrJob.FILE;
                    mDfstinbtion = supfr.dfstinbtionAttr;
                }
                if (mDfstTypf == RbstfrPrintfrJob.FILE) {
                    try {
                        spoolFilf = nfw Filf(mDfstinbtion);
                        output =  nfw FilfOutputStrfbm(spoolFilf);
                    } dbtdi (IOExdfption fx) {
                        tirow nfw PrintfrIOExdfption(fx);
                    }
                } flsf {
                    PrintfrOpfnfr po = nfw PrintfrOpfnfr();
                    jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(po);
                    if (po.pfx != null) {
                        tirow po.pfx;
                    }
                    output = po.rfsult;
                }
            }

            mPSStrfbm = nfw PrintStrfbm(nfw BufffrfdOutputStrfbm(output));
            mPSStrfbm.println(ADOBE_PS_STR);
        }

        mPSStrfbm.println("%%BfginProlog");
        mPSStrfbm.println(READIMAGEPROC);
        mPSStrfbm.println("/BD {bind dff} bind dff");
        mPSStrfbm.println("/D {dff} BD");
        mPSStrfbm.println("/C {durvfto} BD");
        mPSStrfbm.println("/L {linfto} BD");
        mPSStrfbm.println("/M {movfto} BD");
        mPSStrfbm.println("/R {grfstorf} BD");
        mPSStrfbm.println("/G {gsbvf} BD");
        mPSStrfbm.println("/N {nfwpbti} BD");
        mPSStrfbm.println("/P {dlosfpbti} BD");
        mPSStrfbm.println("/EC {fodlip} BD");
        mPSStrfbm.println("/WC {dlip} BD");
        mPSStrfbm.println("/EF {fofill} BD");
        mPSStrfbm.println("/WF {fill} BD");
        mPSStrfbm.println("/SG {sftgrby} BD");
        mPSStrfbm.println("/SC {sftrgbdolor} BD");
        mPSStrfbm.println("/ISOF {");
        mPSStrfbm.println("     dup findfont dup lfngti 1 bdd didt bfgin {");
        mPSStrfbm.println("             1 indfx /FID fq {pop pop} {D} ifflsf");
        mPSStrfbm.println("     } forbll /Endoding ISOLbtin1Endoding D");
        mPSStrfbm.println("     durrfntdidt fnd dffinffont");
        mPSStrfbm.println("} BD");
        mPSStrfbm.println("/NZ {dup 1 lt {pop 1} if} BD");
        /* Tif following prodfdurf tbkfs brgs: string, x, y, dfsirfdWidti.
         * It dbldulbtfs using stringwidti tif widti of tif string in tif
         * durrfnt font bnd subtrbdts it from tif dfsirfdWidti bnd dividfs
         * tiis by stringLfn-1. Tiis givfs us b pfr-glypi bdjustmfnt in
         * tif spbding nffdfd (fitifr +vf or -vf) to mbkf tif string
         * print bt tif dfsirfdWidti. Tif bsiow prodfdurf dbll tbkfs tiis
         * pfr-glypi bdjustmfnt bs bn brgumfnt. Tiis is nfdfssbry for WYSIWYG
         */
        mPSStrfbm.println("/"+DrbwStringNbmf +" {");
        mPSStrfbm.println("     movfto 1 indfx stringwidti pop NZ sub");
        mPSStrfbm.println("     1 indfx lfngti 1 sub NZ div 0");
        mPSStrfbm.println("     3 2 roll bsiow nfwpbti} BD");
        mPSStrfbm.println("/FL [");
        if (mFontProps == null){
            mPSStrfbm.println(" /Hflvftidb ISOF");
            mPSStrfbm.println(" /Hflvftidb-Bold ISOF");
            mPSStrfbm.println(" /Hflvftidb-Obliquf ISOF");
            mPSStrfbm.println(" /Hflvftidb-BoldObliquf ISOF");
            mPSStrfbm.println(" /Timfs-Rombn ISOF");
            mPSStrfbm.println(" /Timfs-Bold ISOF");
            mPSStrfbm.println(" /Timfs-Itblid ISOF");
            mPSStrfbm.println(" /Timfs-BoldItblid ISOF");
            mPSStrfbm.println(" /Courifr ISOF");
            mPSStrfbm.println(" /Courifr-Bold ISOF");
            mPSStrfbm.println(" /Courifr-Obliquf ISOF");
            mPSStrfbm.println(" /Courifr-BoldObliquf ISOF");
        } flsf {
            int dnt = Intfgfr.pbrsfInt(mFontProps.gftPropfrty("font.num", "9"));
            for (int i = 0; i < dnt; i++){
                mPSStrfbm.println("    /" + mFontProps.gftPropfrty
                           ("font." + String.vblufOf(i), "Courifr ISOF"));
            }
        }
        mPSStrfbm.println("] D");

        mPSStrfbm.println("/"+SftFontNbmf +" {");
        mPSStrfbm.println("     FL fxdi gft fxdi sdblffont");
        mPSStrfbm.println("     [1 0 0 -1 0 0] mbkffont sftfont} BD");

        mPSStrfbm.println("%%EndProlog");

        mPSStrfbm.println("%%BfginSftup");
        if (fpsPrintfr == null) {
            // Sft Pbgf Sizf using first pbgf's formbt.
            PbgfFormbt pbgfFormbt = gftPbgfbblf().gftPbgfFormbt(0);
            doublf pbpfrHfigit = pbgfFormbt.gftPbpfr().gftHfigit();
            doublf pbpfrWidti = pbgfFormbt.gftPbpfr().gftWidti();

            /* PostSdript printfrs dbn blwbys gfnfrbtf undollbtfd dopifs.
             */
            mPSStrfbm.print("<< /PbgfSizf [" +
                                           pbpfrWidti + " "+ pbpfrHfigit+"]");

            finbl PrintSfrvidf psfrvidf = gftPrintSfrvidf();
            Boolfbn isPS = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn>() {
                    publid Boolfbn run() {
                       try {
                           Clbss<?> psClbss = Clbss.forNbmf("sun.print.IPPPrintSfrvidf");
                           if (psClbss.isInstbndf(psfrvidf)) {
                               Mftiod isPSMftiod = psClbss.gftMftiod("isPostsdript",
                                                                     (Clbss[])null);
                               rfturn (Boolfbn)isPSMftiod.invokf(psfrvidf, (Objfdt[])null);
                           }
                       } dbtdi (Tirowbblf t) {
                       }
                       rfturn Boolfbn.TRUE;
                    }
                }
            );
            if (isPS) {
                mPSStrfbm.print(" /DfffrrfdMfdibSflfdtion truf");
            }

            mPSStrfbm.print(" /ImbgingBBox null /MbnublFffd fblsf");
            mPSStrfbm.print(isCollbtfd() ? " /Collbtf truf":"");
            mPSStrfbm.print(" /NumCopifs " +gftCopifsInt());

            if (sidfsAttr != Sidfs.ONE_SIDED) {
                if (sidfsAttr == Sidfs.TWO_SIDED_LONG_EDGE) {
                    mPSStrfbm.print(" /Duplfx truf ");
                } flsf if (sidfsAttr == Sidfs.TWO_SIDED_SHORT_EDGE) {
                    mPSStrfbm.print(" /Duplfx truf /Tumblf truf ");
                }
            }
            mPSStrfbm.println(" >> sftpbgfdfvidf ");
        }
        mPSStrfbm.println("%%EndSftup");
    }

    // Innfr dlbss to run "privilfgfd" to opfn tif printfr output strfbm.

    privbtf dlbss PrintfrOpfnfr implfmfnts jbvb.sfdurity.PrivilfgfdAdtion<OutputStrfbm> {
        PrintfrExdfption pfx;
        OutputStrfbm rfsult;

        publid OutputStrfbm run() {
            try {

                    /* Writf to b tfmporbry filf wiidi will bf spoolfd to
                     * tif printfr tifn dflftfd. In tif dbsf tibt tif filf
                     * is not rfmovfd for somf rfbson, rfqufst tibt it is
                     * rfmovfd wifn tif VM fxits.
                     */
                    spoolFilf = Filfs.drfbtfTfmpFilf("jbvbprint", ".ps").toFilf();
                    spoolFilf.dflftfOnExit();

                rfsult = nfw FilfOutputStrfbm(spoolFilf);
                rfturn rfsult;
            } dbtdi (IOExdfption fx) {
                // If tifrf is bn IOError wf subvfrt it to b PrintfrExdfption.
                pfx = nfw PrintfrIOExdfption(fx);
            }
            rfturn null;
        }
    }

    // Innfr dlbss to run "privilfgfd" to invokf tif systfm print dommbnd

    privbtf dlbss PrintfrSpoolfr implfmfnts jbvb.sfdurity.PrivilfgfdAdtion<Objfdt> {
        PrintfrExdfption pfx;

        privbtf void ibndlfProdfssFbilurf(finbl Prodfss fbilfdProdfss,
                finbl String[] fxfdCmd, finbl int rfsult) tirows IOExdfption {
            try (StringWritfr sw = nfw StringWritfr();
                    PrintWritfr pw = nfw PrintWritfr(sw)) {
                pw.bppfnd("frror=").bppfnd(Intfgfr.toString(rfsult));
                pw.bppfnd(" running:");
                for (String brg: fxfdCmd) {
                    pw.bppfnd(" '").bppfnd(brg).bppfnd("'");
                }
                try (InputStrfbm is = fbilfdProdfss.gftErrorStrfbm();
                        InputStrfbmRfbdfr isr = nfw InputStrfbmRfbdfr(is);
                        BufffrfdRfbdfr br = nfw BufffrfdRfbdfr(isr)) {
                    wiilf (br.rfbdy()) {
                        pw.println();
                        pw.bppfnd("\t\t").bppfnd(br.rfbdLinf());
                    }
                } finblly {
                    pw.flusi();
                }
                tirow nfw IOExdfption(sw.toString());
            }
        }

        publid Objfdt run() {
            if (spoolFilf == null || !spoolFilf.fxists()) {
               pfx = nfw PrintfrExdfption("No spool filf");
               rfturn null;
            }
            try {
                /**
                 * Spool to tif printfr.
                 */
                String filfNbmf = spoolFilf.gftAbsolutfPbti();
                String fxfdCmd[] = printExfdCmd(mDfstinbtion, mOptions,
                               mNoJobSifft, gftJobNbmfInt(),
                                                1, filfNbmf);

                Prodfss prodfss = Runtimf.gftRuntimf().fxfd(fxfdCmd);
                prodfss.wbitFor();
                finbl int rfsult = prodfss.fxitVbluf();
                if (0 != rfsult) {
                    ibndlfProdfssFbilurf(prodfss, fxfdCmd, rfsult);
                }
            } dbtdi (IOExdfption fx) {
                pfx = nfw PrintfrIOExdfption(fx);
            } dbtdi (IntfrruptfdExdfption if) {
                pfx = nfw PrintfrExdfption(if.toString());
            } finblly {
                spoolFilf.dflftf();
            }
            rfturn null;
        }
    }


    /**
     * Invokfd if tif bpplidbtion dbndfllfd tif printjob.
     */
    protfdtfd void bbortDod() {
        if (mPSStrfbm != null && mDfstTypf != RbstfrPrintfrJob.STREAM) {
            mPSStrfbm.dlosf();
        }
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Objfdt>() {

            publid Objfdt run() {
               if (spoolFilf != null && spoolFilf.fxists()) {
                   spoolFilf.dflftf();
               }
               rfturn null;
            }
        });
    }

    /**
     * Invokfd by tif RbstfrPrintJob supfr dlbss
     * tiis mftiod is dbllfd bftfr tibt lbst pbgf
     * ibs bffn imbgfd.
     */
    protfdtfd void fndDod() tirows PrintfrExdfption {
        if (mPSStrfbm != null) {
            mPSStrfbm.println(EOF_COMMENT);
            mPSStrfbm.flusi();
            if (mDfstTypf != RbstfrPrintfrJob.STREAM) {
                mPSStrfbm.dlosf();
            }
        }
        if (mDfstTypf == RbstfrPrintfrJob.PRINTER) {
            PrintSfrvidf pSfrv = gftPrintSfrvidf();
            if (pSfrv != null) {
                mDfstinbtion = pSfrv.gftNbmf();
               if (isMbd) {
                    PrintSfrvidfAttributfSft psbSft = pSfrv.gftAttributfs();
                    if (psbSft != null) {
                        mDfstinbtion = psbSft.gft(PrintfrNbmf.dlbss).toString() ;
                    }
                }
            }
            PrintfrSpoolfr spoolfr = nfw PrintfrSpoolfr();
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(spoolfr);
            if (spoolfr.pfx != null) {
                tirow spoolfr.pfx;
            }
        }
    }

    privbtf String gftCoordPrfp() {
        rfturn " 0 fxdi trbnslbtf "
             + "1 -1 sdblf"
             + "[72 " + gftXRfs() + " div "
             + "0 0 "
             + "72 " + gftYRfs() + " div "
             + "0 0]dondbt";
    }

    /**
     * Tif RbstfrPrintJob supfr dlbss dblls tiis mftiod
     * bt tif stbrt of fbdi pbgf.
     */
    protfdtfd void stbrtPbgf(PbgfFormbt pbgfFormbt, Printbblf pbintfr,
                             int indfx, boolfbn pbpfrCibngfd)
        tirows PrintfrExdfption
    {
        doublf pbpfrHfigit = pbgfFormbt.gftPbpfr().gftHfigit();
        doublf pbpfrWidti = pbgfFormbt.gftPbpfr().gftWidti();
        int pbgfNumbfr = indfx + 1;

        /* Plbdf bn initibl gstbtf on to our gstbtf stbdk.
         * It will ibvf tif dffbult PostSdript gstbtf
         * bttributfs.
         */
        mGStbtfStbdk = nfw ArrbyList<>();
        mGStbtfStbdk.bdd(nfw GStbtf());

        mPSStrfbm.println(PAGE_COMMENT + pbgfNumbfr + " " + pbgfNumbfr);

        /* Cifdk durrfnt pbgf's pbgfFormbt bgbinst tif prfvious pbgfFormbt,
         */
        if (indfx > 0 && pbpfrCibngfd) {

            mPSStrfbm.print("<< /PbgfSizf [" +
                            pbpfrWidti + " " + pbpfrHfigit + "]");

            finbl PrintSfrvidf psfrvidf = gftPrintSfrvidf();
            Boolfbn isPS = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Boolfbn>() {
                    publid Boolfbn run() {
                        try {
                            Clbss<?> psClbss =
                                Clbss.forNbmf("sun.print.IPPPrintSfrvidf");
                            if (psClbss.isInstbndf(psfrvidf)) {
                                Mftiod isPSMftiod =
                                    psClbss.gftMftiod("isPostsdript",
                                                      (Clbss[])null);
                                rfturn (Boolfbn)
                                    isPSMftiod.invokf(psfrvidf,
                                                      (Objfdt[])null);
                            }
                        } dbtdi (Tirowbblf t) {
                        }
                        rfturn Boolfbn.TRUE;
                    }
                    }
                );

            if (isPS) {
                mPSStrfbm.print(" /DfffrrfdMfdibSflfdtion truf");
            }
            mPSStrfbm.println(" >> sftpbgfdfvidf");
        }
        mPSStrfbm.println(PAGE_SAVE);
        mPSStrfbm.println(pbpfrHfigit + gftCoordPrfp());
    }

    /**
     * Tif RbstfPrintJob supfr dlbss dblls tiis mftiod
     * bt tif fnd of fbdi pbgf.
     */
    protfdtfd void fndPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                           int indfx)
        tirows PrintfrExdfption
    {
        mPSStrfbm.println(PAGE_RESTORE);
        mPSStrfbm.println(SHOWPAGE);
    }

   /**
     * Convfrt tif 24 bit BGR imbgf bufffr rfprfsfntfd by
     * <dodf>imbgf</dodf> to PostSdript. Tif imbgf is drbwn bt
     * <dodf>(dfstX, dfstY)</dodf> in dfvidf doordinbtfs.
     * Tif imbgf is sdblfd into b squbrf of sizf
     * spfdififd by <dodf>dfstWidti</dodf> bnd
     * <dodf>dfstHfigit</dodf>. Tif portion of tif
     * sourdf imbgf dopifd into tibt squbrf is spfdififd
     * by <dodf>srdX</dodf>, <dodf>srdY</dodf>,
     * <dodf>srdWidti</dodf>, bnd srdHfigit.
     */
    protfdtfd void drbwImbgfBGR(bytf[] bgrDbtb,
                                   flobt dfstX, flobt dfstY,
                                   flobt dfstWidti, flobt dfstHfigit,
                                   flobt srdX, flobt srdY,
                                   flobt srdWidti, flobt srdHfigit,
                                   int srdBitMbpWidti, int srdBitMbpHfigit) {

        /* Wf drbw imbgfs bt dfvidf rfsolution so wf probbbly nffd
         * to dibngf tif durrfnt PostSdript trbnsform.
         */
        sftTrbnsform(nfw AffinfTrbnsform());
        prfpDrbwing();

        int intSrdWidti = (int) srdWidti;
        int intSrdHfigit = (int) srdHfigit;

        mPSStrfbm.println(IMAGE_SAVE);

        /* Crfbtf b PS string big fnougi to iold b row of pixfls.
         */
        int psBytfsPfrRow = 3 * intSrdWidti;
        wiilf (psBytfsPfrRow > MAX_PSSTR) {
            psBytfsPfrRow /= 2;
        }

        mPSStrfbm.println(psBytfsPfrRow + IMAGE_STR);

        /* Sdblf bnd trbnslbtf tif unit imbgf.
         */
        mPSStrfbm.println("[" + dfstWidti + " 0 "
                          + "0 " + dfstHfigit
                          + " " + dfstX + " " + dfstY
                          +"]dondbt");

        /* Color Imbgf invodbtion.
         */
        mPSStrfbm.println(intSrdWidti + " " + intSrdHfigit + " " + 8 + "["
                          + intSrdWidti + " 0 "
                          + "0 " + intSrdHfigit
                          + " 0 " + 0 + "]"
                          + "/imbgfSrd lobd fblsf 3 dolorimbgf");

        /* Imbgf dbtb.
         */
        int indfx = 0;
        bytf[] rgbDbtb = nfw bytf[intSrdWidti * 3];

        try {
            /* Skip tif pbrts of tif imbgf tibt brf not pbrt
             * of tif sourdf rfdtbnglf.
             */
            indfx = (int) srdY * srdBitMbpWidti;

            for(int i = 0; i < intSrdHfigit; i++) {

                /* Skip tif lfft pbrt of tif imbgf tibt is not
                 * pbrt of tif sourdf rfdtbnglf.
                 */
                indfx += (int) srdX;

                indfx = swbpBGRtoRGB(bgrDbtb, indfx, rgbDbtb);
                bytf[] fndodfdDbtb = rlEndodf(rgbDbtb);
                bytf[] bsdiiDbtb = bsdii85Endodf(fndodfdDbtb);
                mPSStrfbm.writf(bsdiiDbtb);
                mPSStrfbm.println("");
            }

            /*
             * If tifrf is bn IOError wf subvfrt it to b PrintfrExdfption.
             * Fix: Tifrf ibs got to bf b bfttfr wby, mbybf dffinf
             * b PrintfrIOExdfption bnd tifn tirow tibt?
             */
        } dbtdi (IOExdfption f) {
            //tirow nfw PrintfrExdfption(f.toString());
        }

        mPSStrfbm.println(IMAGE_RESTORE);
    }

    /**
     * Prints tif dontfnts of tif brrby of ints, 'dbtb'
     * to tif durrfnt pbgf. Tif bbnd is plbdfd bt tif
     * lodbtion (x, y) in dfvidf doordinbtfs on tif
     * pbgf. Tif widti bnd ifigit of tif bbnd is
     * spfdififd by tif dbllfr. Currfntly tif dbtb
     * is 24 bits pfr pixfl in BGR formbt.
     */
    protfdtfd void printBbnd(bytf[] bgrDbtb, int x, int y,
                             int widti, int ifigit)
        tirows PrintfrExdfption
    {

        mPSStrfbm.println(IMAGE_SAVE);

        /* Crfbtf b PS string big fnougi to iold b row of pixfls.
         */
        int psBytfsPfrRow = 3 * widti;
        wiilf (psBytfsPfrRow > MAX_PSSTR) {
            psBytfsPfrRow /= 2;
        }

        mPSStrfbm.println(psBytfsPfrRow + IMAGE_STR);

        /* Sdblf bnd trbnslbtf tif unit imbgf.
         */
        mPSStrfbm.println("[" + widti + " 0 "
                          + "0 " + ifigit
                          + " " + x + " " + y
                          +"]dondbt");

        /* Color Imbgf invodbtion.
         */
        mPSStrfbm.println(widti + " " + ifigit + " " + 8 + "["
                          + widti + " 0 "
                          + "0 " + -ifigit
                          + " 0 " + ifigit + "]"
                          + "/imbgfSrd lobd fblsf 3 dolorimbgf");

        /* Imbgf dbtb.
         */
        int indfx = 0;
        bytf[] rgbDbtb = nfw bytf[widti*3];

        try {
            for(int i = 0; i < ifigit; i++) {
                indfx = swbpBGRtoRGB(bgrDbtb, indfx, rgbDbtb);
                bytf[] fndodfdDbtb = rlEndodf(rgbDbtb);
                bytf[] bsdiiDbtb = bsdii85Endodf(fndodfdDbtb);
                mPSStrfbm.writf(bsdiiDbtb);
                mPSStrfbm.println("");
            }

        } dbtdi (IOExdfption f) {
            tirow nfw PrintfrIOExdfption(f);
        }

        mPSStrfbm.println(IMAGE_RESTORE);
    }

    /**
     * Exbminf tif mftrids dbpturfd by tif
     * <dodf>PffkGrbpiids</dodf> instbndf bnd
     * if dbpbblf of dirfdtly donvfrting tiis
     * print job to tif printfr's dontrol lbngubgf
     * or tif nbtivf OS's grbpiids primitivfs, tifn
     * rfturn b <dodf>PSPbtiGrbpiids</dodf> to pfrform
     * tibt donvfrsion. If tifrf is not bn objfdt
     * dbpbblf of tif donvfrsion tifn rfturn
     * <dodf>null</dodf>. Rfturning <dodf>null</dodf>
     * dbusfs tif print job to bf rbstfrizfd.
     */

    protfdtfd Grbpiids2D drfbtfPbtiGrbpiids(PffkGrbpiids pffkGrbpiids,
                                            PrintfrJob printfrJob,
                                            Printbblf pbintfr,
                                            PbgfFormbt pbgfFormbt,
                                            int pbgfIndfx) {

        PSPbtiGrbpiids pbtiGrbpiids;
        PffkMftrids mftrids = pffkGrbpiids.gftMftrids();

        /* If tif bpplidbtion ibs drbwn bnytiing tibt
         * out PbtiGrbpiids dlbss dbn not ibndlf tifn
         * rfturn b null PbtiGrbpiids.
         */
        if (fordfPDL == fblsf && (fordfRbstfr == truf
                        || mftrids.ibsNonSolidColors()
                        || mftrids.ibsCompositing())) {

            pbtiGrbpiids = null;
        } flsf {

            BufffrfdImbgf bufffrfdImbgf = nfw BufffrfdImbgf(8, 8,
                                            BufffrfdImbgf.TYPE_INT_RGB);
            Grbpiids2D bufffrfdGrbpiids = bufffrfdImbgf.drfbtfGrbpiids();
            boolfbn dbnRfdrbw = pffkGrbpiids.gftAWTDrbwingOnly() == fblsf;

            pbtiGrbpiids =  nfw PSPbtiGrbpiids(bufffrfdGrbpiids, printfrJob,
                                               pbintfr, pbgfFormbt, pbgfIndfx,
                                               dbnRfdrbw);
        }

        rfturn pbtiGrbpiids;
    }

    /**
     * Intfrsfdt tif gstbtf's durrfnt pbti witi tif
     * durrfnt dlip bnd mbkf tif rfsult tif nfw dlip.
     */
    protfdtfd void sflfdtClipPbti() {

        mPSStrfbm.println(mClipOpStr);
    }

    protfdtfd void sftClip(Sibpf dlip) {

        mLbstClip = dlip;
    }

    protfdtfd void sftTrbnsform(AffinfTrbnsform trbnsform) {
        mLbstTrbnsform = trbnsform;
    }

    /**
     * Sft tif durrfnt PostSdript font.
     * Tbkfn from outFont in PSPrintStrfbm.
     */
     protfdtfd boolfbn sftFont(Font font) {
        mLbstFont = font;
        rfturn truf;
    }

    /**
     * Givfn bn brrby of CibrsftStrings tibt mbkf up b run
     * of tfxt, tiis routinf donvfrts fbdi CibrsftString to
     * bn indfx into our PostSdript font list. If onf or morf
     * CibrsftStrings dbn not bf rfprfsfntfd by b PostSdript
     * font, tifn tiis routinf will rfturn b null brrby.
     */
     privbtf int[] gftPSFontIndfxArrby(Font font, CibrsftString[] dibrSft) {
        int[] psFont = null;

        if (mFontProps != null) {
            psFont = nfw int[dibrSft.lfngti];
        }

        for (int i = 0; i < dibrSft.lfngti && psFont != null; i++){

            /* Gft tif fndoding of tif run of tfxt.
             */
            CibrsftString ds = dibrSft[i];

            CibrsftEndodfr fontCS = ds.fontDfsdriptor.fndodfr;
            String dibrsftNbmf = ds.fontDfsdriptor.gftFontCibrsftNbmf();
            /*
             * sun.bwt.Symbol pfribps siould rfturn "symbol" for fndoding.
             * Similbrly X11Dingbbts siould rfturn "dingbbts"
             * Fordfd to difdk for win32 & x/unix nbmfs for tifsf donvfrtfrs.
             */

            if ("Symbol".fqubls(dibrsftNbmf)) {
                dibrsftNbmf = "symbol";
            } flsf if ("WingDings".fqubls(dibrsftNbmf) ||
                       "X11Dingbbts".fqubls(dibrsftNbmf)) {
                dibrsftNbmf = "dingbbts";
            } flsf {
                dibrsftNbmf = mbkfCibrsftNbmf(dibrsftNbmf, ds.dibrsftCibrs);
            }

            int stylfMbsk = font.gftStylf() |
                FontUtilitifs.gftFont2D(font).gftStylf();

            String stylf = FontConfigurbtion.gftStylfString(stylfMbsk);

            /* First wf mbp tif font nbmf tirougi tif propfrtifs filf.
             * Tiis mbpping providfs blibs nbmfs for fonts, for fxbmplf,
             * "timfsrombn" is mbppfd to "sfrif".
             */
            String fontNbmf = font.gftFbmily().toLowfrCbsf(Lodblf.ENGLISH);
            fontNbmf = fontNbmf.rfplbdf(' ', '_');
            String nbmf = mFontProps.gftPropfrty(fontNbmf, "");

            /* Now mbp tif blibs nbmf, dibrbdtfr sft nbmf, bnd stylf
             * to b PostSdript nbmf.
             */
            String psNbmf =
                mFontProps.gftPropfrty(nbmf + "." + dibrsftNbmf + "." + stylf,
                                      null);

            if (psNbmf != null) {

                /* Gft tif PostSdript font indfx for tif PostSdript font.
                 */
                try {
                    psFont[i] =
                        Intfgfr.pbrsfInt(mFontProps.gftPropfrty(psNbmf));

                /* If tifrf is no PostSdript font for tiis font nbmf,
                 * tifn wf wbnt to tfrmintbtf tif loop bnd tif mftiod
                 * indidbting our fbilurf. Sftting tif brrby to null
                 * is usfd to indidbtf tifsf fbilurfs.
                 */
                } dbtdi(NumbfrFormbtExdfption f){
                    psFont = null;
                }

            /* Tifrf wbs no PostSdript nbmf for tif font, dibrbdtfr sft,
             * bnd stylf so givf up.
             */
            } flsf {
                psFont = null;
            }
        }

         rfturn psFont;
     }


    privbtf stbtid String fsdbpfPbrfns(String str) {
        if (str.indfxOf('(') == -1 && str.indfxOf(')') == -1 ) {
            rfturn str;
        } flsf {
            int dount = 0;
            int pos = 0;
            wiilf ((pos = str.indfxOf('(', pos)) != -1) {
                dount++;
                pos++;
            }
            pos = 0;
            wiilf ((pos = str.indfxOf(')', pos)) != -1) {
                dount++;
                pos++;
            }
            dibr []inArr = str.toCibrArrby();
            dibr []outArr = nfw dibr[inArr.lfngti+dount];
            pos = 0;
            for (int i=0;i<inArr.lfngti;i++) {
                if (inArr[i] == '(' || inArr[i] == ')') {
                    outArr[pos++] = '\\';
                }
                outArr[pos++] = inArr[i];
            }
            rfturn nfw String(outArr);

        }
    }

    /* rfturn of 0 mfbns unsupportfd. Otifr rfturn indidbtfs tif numbfr
     * of distindt PS fonts nffdfd to drbw tiis tfxt. Tiis sbvfs us
     * doing tiis prodfssing onf fxtrb timf.
     */
    protfdtfd int plbtformFontCount(Font font, String str) {
        if (mFontProps == null) {
            rfturn 0;
        }
        CibrsftString[] bds =
            ((PlbtformFont)(font.gftPffr())).mbkfMultiCibrsftString(str,fblsf);
        if (bds == null) {
            /* AWT dbn't donvfrt bll dibrs so usf 2D pbti */
            rfturn 0;
        }
        int[] psFonts = gftPSFontIndfxArrby(font, bds);
        rfturn (psFonts == null) ? 0 : psFonts.lfngti;
    }

     protfdtfd boolfbn tfxtOut(Grbpiids g, String str, flobt x, flobt y,
                               Font mLbstFont, FontRfndfrContfxt frd,
                               flobt widti) {
        boolfbn didTfxt = truf;

        if (mFontProps == null) {
            rfturn fblsf;
        } flsf {
            prfpDrbwing();

            /* On-sdrffn drbwString rfndfrs most dontrol dibrs bs tif missing
             * glypi bnd ibvf tif non-zfro bdvbndf of tibt glypi.
             * Exdfptions brf \t, \n bnd \r wiidi brf donsidfrfd zfro-widti.
             * Postsdript ibndlfs dontrol dibrs mostly bs b missing glypi.
             * But wf usf 'bsiow' spfdifying b widti for tif string wiidi
             * bssumfs zfro-widti for tiosf tirff fxdfptions, bnd Postsdript
             * trifs to squffzf tif fxtrb dibr in, witi tif rfsult tibt tif
             * glypis look domprfssfd or fvfn ovfrlbp.
             * So fxdludf tiosf dontrol dibrs from tif string sfnt to PS.
             */
            str = rfmovfControlCibrs(str);
            if (str.lfngti() == 0) {
                rfturn truf;
            }
            CibrsftString[] bds =
                ((PlbtformFont)
                 (mLbstFont.gftPffr())).mbkfMultiCibrsftString(str, fblsf);
            if (bds == null) {
                /* AWT dbn't donvfrt bll dibrs so usf 2D pbti */
                rfturn fblsf;
            }
            /* Gft bn brrby of indidfs into our PostSdript nbmf
             * tbblf. If bll of tif runs dbn not bf donvfrtfd
             * to PostSdript fonts tifn null is rfturnfd bnd
             * wf'll wbnt to fbll bbdk to printing tif tfxt
             * bs sibpfs.
             */
            int[] psFonts = gftPSFontIndfxArrby(mLbstFont, bds);
            if (psFonts != null) {

                for (int i = 0; i < bds.lfngti; i++){
                    CibrsftString ds = bds[i];
                    CibrsftEndodfr fontCS = ds.fontDfsdriptor.fndodfr;

                    StringBuildfr nbtivfStr = nfw StringBuildfr();
                    bytf[] strSfg = nfw bytf[ds.lfngti * 2];
                    int lfn = 0;
                    try {
                        BytfBufffr bb = BytfBufffr.wrbp(strSfg);
                        fontCS.fndodf(CibrBufffr.wrbp(ds.dibrsftCibrs,
                                                      ds.offsft,
                                                      ds.lfngti),
                                      bb, truf);
                        bb.flip();
                        lfn = bb.limit();
                    } dbtdi(IllfgblStbtfExdfption xx){
                        dontinuf;
                    } dbtdi(CodfrMblfundtionError xx){
                        dontinuf;
                    }
                    /* Tif widti to fit to mby fitifr bf spfdififd,
                     * or dbldulbtfd. Spfdifying by tif dbllfr is only
                     * vblid if tif tfxt dofs not nffd to bf dfdomposfd
                     * into multiplf dblls.
                     */
                    flobt dfsirfdWidti;
                    if (bds.lfngti == 1 && widti != 0f) {
                        dfsirfdWidti = widti;
                    } flsf {
                        Rfdtbnglf2D r2d =
                            mLbstFont.gftStringBounds(ds.dibrsftCibrs,
                                                      ds.offsft,
                                                      ds.offsft+ds.lfngti,
                                                      frd);
                        dfsirfdWidti = (flobt)r2d.gftWidti();
                    }
                    /* unprintbblf dibrs ibd widti of 0, dbusing b PS frror
                     */
                    if (dfsirfdWidti == 0) {
                        rfturn didTfxt;
                    }
                    nbtivfStr.bppfnd('<');
                    for (int j = 0; j < lfn; j++){
                        bytf b = strSfg[j];
                        // to bvoid fndoding donvfrsion witi println()
                        String ifxS = Intfgfr.toHfxString(b);
                        int lfngti = ifxS.lfngti();
                        if (lfngti > 2) {
                            ifxS = ifxS.substring(lfngti - 2, lfngti);
                        } flsf if (lfngti == 1) {
                            ifxS = "0" + ifxS;
                        } flsf if (lfngti == 0) {
                            ifxS = "00";
                        }
                        nbtivfStr.bppfnd(ifxS);
                    }
                    nbtivfStr.bppfnd('>');
                    /* Tiis dommfnt dosts too mudi in output filf sizf */
//                  mPSStrfbm.println("% Font[" + mLbstFont.gftNbmf() + ", " +
//                             FontConfigurbtion.gftStylfString(mLbstFont.gftStylf()) + ", "
//                             + mLbstFont.gftSizf2D() + "]");
                    gftGStbtf().fmitPSFont(psFonts[i], mLbstFont.gftSizf2D());

                    // out String
                    mPSStrfbm.println(nbtivfStr.toString() + " " +
                                      dfsirfdWidti + " " + x + " " + y + " " +
                                      DrbwStringNbmf);
                    x += dfsirfdWidti;
                }
            } flsf {
                didTfxt = fblsf;
            }
        }

        rfturn didTfxt;
     }
    /**
     * Sft tif durrfnt pbti rulf to bf fitifr
     * <dodf>FILL_EVEN_ODD</dodf> (using tif
     * fvfn-odd filf rulf) or <dodf>FILL_WINDING</dodf>
     * (using tif non-zfro winding rulf.)
     */
    protfdtfd void sftFillModf(int fillRulf) {

        switdi (fillRulf) {

         dbsf FILL_EVEN_ODD:
            mFillOpStr = EVEN_ODD_FILL_STR;
            mClipOpStr = EVEN_ODD_CLIP_STR;
            brfbk;

         dbsf FILL_WINDING:
             mFillOpStr = WINDING_FILL_STR;
             mClipOpStr = WINDING_CLIP_STR;
             brfbk;

         dffbult:
             tirow nfw IllfgblArgumfntExdfption();
        }

    }

    /**
     * Sft tif printfr's durrfnt dolor to bf tibt
     * dffinfd by <dodf>dolor</dodf>
     */
    protfdtfd void sftColor(Color dolor) {
        mLbstColor = dolor;
    }

    /**
     * Fill tif durrfnt pbti using tif durrfnt fill modf
     * bnd dolor.
     */
    protfdtfd void fillPbti() {

        mPSStrfbm.println(mFillOpStr);
    }

    /**
     * Cbllfd to mbrk tif stbrt of b nfw pbti.
     */
    protfdtfd void bfginPbti() {

        prfpDrbwing();
        mPSStrfbm.println(NEWPATH_STR);

        mPfnX = 0;
        mPfnY = 0;
    }

    /**
     * Closf tif durrfnt subpbti by bppfnding b strbigit
     * linf from tif durrfnt point to tif subpbti's
     * stbrting point.
     */
    protfdtfd void dlosfSubpbti() {

        mPSStrfbm.println(CLOSEPATH_STR);

        mPfnX = mStbrtPbtiX;
        mPfnY = mStbrtPbtiY;
    }


    /**
     * Gfnfrbtf PostSdript to movf tif durrfnt pfn
     * position to <dodf>(x, y)</dodf>.
     */
    protfdtfd void movfTo(flobt x, flobt y) {

        mPSStrfbm.println(trund(x) + " " + trund(y) + MOVETO_STR);

        /* movfto mbrks tif stbrt of b nfw subpbti
         * bnd wf nffd to rfmfmbfr tibt stbrting
         * position so tibt wf know wifrf tif
         * pfn rfturns to witi b dlosf pbti.
         */
        mStbrtPbtiX = x;
        mStbrtPbtiY = y;

        mPfnX = x;
        mPfnY = y;
    }
    /**
     * Gfnfrbtf PostSdript to drbw b linf from tif
     * durrfnt pfn position to <dodf>(x, y)</dodf>.
     */
    protfdtfd void linfTo(flobt x, flobt y) {

        mPSStrfbm.println(trund(x) + " " + trund(y) + LINETO_STR);

        mPfnX = x;
        mPfnY = y;
    }

    /**
     * Add to tif durrfnt pbti b bfzifr durvf formfd
     * by tif durrfnt pfn position bnd tif mftiod pbrbmftfrs
     * wiidi brf two dontrol points bnd bn fnding
     * point.
     */
    protfdtfd void bfzifrTo(flobt dontrol1x, flobt dontrol1y,
                                flobt dontrol2x, flobt dontrol2y,
                                flobt fndX, flobt fndY) {

//      mPSStrfbm.println(dontrol1x + " " + dontrol1y
//                        + " " + dontrol2x + " " + dontrol2y
//                        + " " + fndX + " " + fndY
//                        + CURVETO_STR);
        mPSStrfbm.println(trund(dontrol1x) + " " + trund(dontrol1y)
                          + " " + trund(dontrol2x) + " " + trund(dontrol2y)
                          + " " + trund(fndX) + " " + trund(fndY)
                          + CURVETO_STR);


        mPfnX = fndX;
        mPfnY = fndY;
    }

    String trund(flobt f) {
        flobt bf = Mbti.bbs(f);
        if (bf >= 1f && bf <=1000f) {
            f = Mbti.round(f*1000)/1000f;
        }
        rfturn Flobt.toString(f);
    }

    /**
     * Rfturn tif x doordinbtf of tif pfn in tif
     * durrfnt pbti.
     */
    protfdtfd flobt gftPfnX() {

        rfturn mPfnX;
    }
    /**
     * Rfturn tif y doordinbtf of tif pfn in tif
     * durrfnt pbti.
     */
    protfdtfd flobt gftPfnY() {

        rfturn mPfnY;
    }

    /**
     * Rfturn tif x rfsolution of tif doordinbtfs
     * to bf rfndfrfd.
     */
    protfdtfd doublf gftXRfs() {
        rfturn xrfs;
    }
    /**
     * Rfturn tif y rfsolution of tif doordinbtfs
     * to bf rfndfrfd.
     */
    protfdtfd doublf gftYRfs() {
        rfturn yrfs;
    }

    /**
     * Sft tif rfsolution bt wiidi to print.
     */
    protfdtfd void sftXYRfs(doublf x, doublf y) {
        xrfs = x;
        yrfs = y;
    }

    /**
     * For PostSdript tif origin is in tif uppfr-lfft of tif
     * pbpfr not bt tif imbgfbblf brfb dornfr.
     */
    protfdtfd doublf gftPiysidblPrintbblfX(Pbpfr p) {
        rfturn 0;

    }

    /**
     * For PostSdript tif origin is in tif uppfr-lfft of tif
     * pbpfr not bt tif imbgfbblf brfb dornfr.
     */
    protfdtfd doublf gftPiysidblPrintbblfY(Pbpfr p) {
        rfturn 0;
    }

    protfdtfd doublf gftPiysidblPrintbblfWidti(Pbpfr p) {
        rfturn p.gftImbgfbblfWidti();
    }

    protfdtfd doublf gftPiysidblPrintbblfHfigit(Pbpfr p) {
        rfturn p.gftImbgfbblfHfigit();
    }

    protfdtfd doublf gftPiysidblPbgfWidti(Pbpfr p) {
        rfturn p.gftWidti();
    }

    protfdtfd doublf gftPiysidblPbgfHfigit(Pbpfr p) {
        rfturn p.gftHfigit();
    }

   /**
     * Rfturns iow mbny timfs fbdi pbgf in tif book
     * siould bf donsfdutivfly printfd by PrintJob.
     * If tif printfr mbkfs dopifs itsflf tifn tiis
     * mftiod siould rfturn 1.
     */
    protfdtfd int gftNondollbtfdCopifs() {
        rfturn 1;
    }

    protfdtfd int gftCollbtfdCopifs() {
        rfturn 1;
    }

    privbtf String[] printExfdCmd(String printfr, String options,
                                  boolfbn noJobSifft,
                                  String bbnnfr, int dopifs, String spoolFilf) {
        int PRINTER = 0x1;
        int OPTIONS = 0x2;
        int BANNER  = 0x4;
        int COPIES  = 0x8;
        int NOSHEET = 0x10;
        int pFlbgs = 0;
        String fxfdCmd[];
        int ndomps = 2; // minimum numbfr of print brgs
        int n = 0;

        if (printfr != null && !printfr.fqubls("") && !printfr.fqubls("lp")) {
            pFlbgs |= PRINTER;
            ndomps+=1;
        }
        if (options != null && !options.fqubls("")) {
            pFlbgs |= OPTIONS;
            ndomps+=1;
        }
        if (bbnnfr != null && !bbnnfr.fqubls("")) {
            pFlbgs |= BANNER;
            ndomps+=1;
        }
        if (dopifs > 1) {
            pFlbgs |= COPIES;
            ndomps+=1;
        }
        if (noJobSifft) {
            pFlbgs |= NOSHEET;
            ndomps+=1;
        }

       String osnbmf = Systfm.gftPropfrty("os.nbmf");
       if (osnbmf.fqubls("Linux") || osnbmf.dontbins("OS X")) {
            fxfdCmd = nfw String[ndomps];
            fxfdCmd[n++] = "/usr/bin/lpr";
            if ((pFlbgs & PRINTER) != 0) {
                fxfdCmd[n++] = "-P" + printfr;
            }
            if ((pFlbgs & BANNER) != 0) {
                fxfdCmd[n++] = "-J"  + bbnnfr;
            }
            if ((pFlbgs & COPIES) != 0) {
                fxfdCmd[n++] = "-#" + dopifs;
            }
            if ((pFlbgs & NOSHEET) != 0) {
                fxfdCmd[n++] = "-i";
            }
            if ((pFlbgs & OPTIONS) != 0) {
                fxfdCmd[n++] = nfw String(options);
            }
        } flsf {
            ndomps+=1; //bdd 1 brg for lp
            fxfdCmd = nfw String[ndomps];
            fxfdCmd[n++] = "/usr/bin/lp";
            fxfdCmd[n++] = "-d";           // mbkf b dopy of tif spool filf
            if ((pFlbgs & PRINTER) != 0) {
                fxfdCmd[n++] = "-d" + printfr;
            }
            if ((pFlbgs & BANNER) != 0) {
                fxfdCmd[n++] = "-t"  + bbnnfr;
            }
            if ((pFlbgs & COPIES) != 0) {
                fxfdCmd[n++] = "-n" + dopifs;
            }
            if ((pFlbgs & NOSHEET) != 0) {
                fxfdCmd[n++] = "-o nobbnnfr";
            }
            if ((pFlbgs & OPTIONS) != 0) {
                fxfdCmd[n++] = "-o" + options;
            }
        }
        fxfdCmd[n++] = spoolFilf;
        rfturn fxfdCmd;
    }

    privbtf stbtid int swbpBGRtoRGB(bytf[] imbgf, int indfx, bytf[] dfst) {
        int dfstIndfx = 0;
        wiilf(indfx < imbgf.lfngti-2 && dfstIndfx < dfst.lfngti-2) {
            dfst[dfstIndfx++] = imbgf[indfx+2];
            dfst[dfstIndfx++] = imbgf[indfx+1];
            dfst[dfstIndfx++] = imbgf[indfx+0];
            indfx+=3;
        }
        rfturn indfx;
    }

    /*
     * Currfntly CibrToBytfConvfrtfr.gftCibrbdtfrEndoding() rfturn vblufs brf
     * not fixfd yft. Tifsf brf usfd bs tif pbrt of tif kfy of
     * psfont.propfrtifs. Wifn tiosf nbmf brf fixfd tiis routinf dbn
     * bf frbsfd.
     */
    privbtf String mbkfCibrsftNbmf(String nbmf, dibr[] dis) {
        if (nbmf.fqubls("Cp1252") || nbmf.fqubls("ISO8859_1")) {
            rfturn "lbtin1";
        } flsf if (nbmf.fqubls("UTF8")) {
            // sbmf bs lbtin 1 if bll dibrs < 256
            for (int i=0; i < dis.lfngti; i++) {
                if (dis[i] > 255) {
                    rfturn nbmf.toLowfrCbsf();
                }
            }
            rfturn "lbtin1";
        } flsf if (nbmf.stbrtsWiti("ISO8859")) {
            // sbmf bs lbtin 1 if bll dibrs < 128
            for (int i=0; i < dis.lfngti; i++) {
                if (dis[i] > 127) {
                    rfturn nbmf.toLowfrCbsf();
                }
            }
            rfturn "lbtin1";
        } flsf {
            rfturn nbmf.toLowfrCbsf();
        }
    }

    privbtf void prfpDrbwing() {

        /* Pop gstbtfs until wf dbn sft tif nffdfd dlip
         * bnd trbnsform or until wf brf bt tif outfr most
         * gstbtf.
         */
        wiilf (isOutfrGStbtf() == fblsf
               && (gftGStbtf().dbnSftClip(mLbstClip) == fblsf
                   || gftGStbtf().mTrbnsform.fqubls(mLbstTrbnsform) == fblsf)) {


            grfstorf();
        }

        /* Sft tif dolor. Tiis dbn pusi tif dolor to tif
         * outfr most gsbvf wiidi is oftfn b good tiing.
         */
        gftGStbtf().fmitPSColor(mLbstColor);

        /* Wf do not wbnt to dibngf tif outfrmost
         * trbnsform or dlip so if wf brf bt tif
         * outfr dlip tif gfnfrbtf b gsbvf.
         */
        if (isOutfrGStbtf()) {
            gsbvf();
            gftGStbtf().fmitTrbnsform(mLbstTrbnsform);
            gftGStbtf().fmitPSClip(mLbstClip);
        }

        /* Sft tif font if wf ibvf bffn bskfd to. It is
         * importbnt tibt tif font is sft bftfr tif
         * trbnsform in ordfr to gft tif font sizf
         * dorrfdt.
         */
//      if (g != null) {
//          gftGStbtf().fmitPSFont(g, mLbstFont);
//      }

    }

    /**
     * Rfturn tif GStbtf tibt is durrfntly on top
     * of tif GStbtf stbdk. Tifrf siould blwbys bf
     * b GStbtf on top of tif stbdk. If tifrf isn't
     * tifn tiis mftiod will tirow bn IndfxOutOfBounds
     * fxdfption.
     */
    privbtf GStbtf gftGStbtf() {
        int dount = mGStbtfStbdk.sizf();
        rfturn mGStbtfStbdk.gft(dount - 1);
    }

    /**
     * Emit b PostSdript gsbvf dommbnd bnd bdd b
     * nfw GStbtf on to our stbdk wiidi rfprfsfnts
     * tif printfr's gstbtf stbdk.
     */
    privbtf void gsbvf() {
        GStbtf oldGStbtf = gftGStbtf();
        mGStbtfStbdk.bdd(nfw GStbtf(oldGStbtf));
        mPSStrfbm.println(GSAVE_STR);
    }

    /**
     * Emit b PostSdript grfstorf dommbnd bnd rfmovf
     * b GStbtf from our stbdk wiidi rfprfsfnts tif
     * printfr's gstbtf stbdk.
     */
    privbtf void grfstorf() {
        int dount = mGStbtfStbdk.sizf();
        mGStbtfStbdk.rfmovf(dount - 1);
        mPSStrfbm.println(GRESTORE_STR);
    }

    /**
     * Rfturn truf if tif durrfnt GStbtf is tif
     * outfrmost GStbtf bnd tifrfforf siould not
     * bf rfstorfd.
     */
    privbtf boolfbn isOutfrGStbtf() {
        rfturn mGStbtfStbdk.sizf() == 1;
    }

    /**
     * A stbdk of GStbtfs is mbintbinfd to modfl tif printfr's
     * gstbtf stbdk. Ebdi GStbtf iolds informbtion bbout
     * tif durrfnt grbpiids bttributfs.
     */
    privbtf dlbss GStbtf{
        Color mColor;
        Sibpf mClip;
        Font mFont;
        AffinfTrbnsform mTrbnsform;

        GStbtf() {
            mColor = Color.blbdk;
            mClip = null;
            mFont = null;
            mTrbnsform = nfw AffinfTrbnsform();
        }

        GStbtf(GStbtf dopyGStbtf) {
            mColor = dopyGStbtf.mColor;
            mClip = dopyGStbtf.mClip;
            mFont = dopyGStbtf.mFont;
            mTrbnsform = dopyGStbtf.mTrbnsform;
        }

        boolfbn dbnSftClip(Sibpf dlip) {

            rfturn mClip == null || mClip.fqubls(dlip);
        }


        void fmitPSClip(Sibpf dlip) {
            if (dlip != null
                && (mClip == null || mClip.fqubls(dlip) == fblsf)) {
                String sbvfFillOp = mFillOpStr;
                String sbvfClipOp = mClipOpStr;
                donvfrtToPSPbti(dlip.gftPbtiItfrbtor(nfw AffinfTrbnsform()));
                sflfdtClipPbti();
                mClip = dlip;
                /* Tif dlip is b sibpf bnd ibs rfsft tif winding rulf stbtf */
                mClipOpStr = sbvfFillOp;
                mFillOpStr = sbvfFillOp;
            }
        }

        void fmitTrbnsform(AffinfTrbnsform trbnsform) {

            if (trbnsform != null && trbnsform.fqubls(mTrbnsform) == fblsf) {
                doublf[] mbtrix = nfw doublf[6];
                trbnsform.gftMbtrix(mbtrix);
                mPSStrfbm.println("[" + (flobt)mbtrix[0]
                                  + " " + (flobt)mbtrix[1]
                                  + " " + (flobt)mbtrix[2]
                                  + " " + (flobt)mbtrix[3]
                                  + " " + (flobt)mbtrix[4]
                                  + " " + (flobt)mbtrix[5]
                                  + "] dondbt");

                mTrbnsform = trbnsform;
            }
        }

        void fmitPSColor(Color dolor) {
            if (dolor != null && dolor.fqubls(mColor) == fblsf) {
                flobt[] rgb = dolor.gftRGBColorComponfnts(null);

                /* If tif dolor is b grby vbluf tifn usf
                 * sftgrby.
                 */
                if (rgb[0] == rgb[1] && rgb[1] == rgb[2]) {
                    mPSStrfbm.println(rgb[0] + SETGRAY_STR);

                /* It's not grby so usf sftrgbdolor.
                 */
                } flsf {
                    mPSStrfbm.println(rgb[0] + " "
                                      + rgb[1] + " "
                                      + rgb[2] + " "
                                      + SETRGBCOLOR_STR);
                }

                mColor = dolor;

            }
        }

        void fmitPSFont(int psFontIndfx, flobt fontSizf) {
            mPSStrfbm.println(fontSizf + " " +
                              psFontIndfx + " " + SftFontNbmf);
        }
    }

       /**
        * Givfn b Jbvb2D <dodf>PbtiItfrbtor</dodf> instbndf,
        * tiis mftiod trbnslbtfs tibt into b PostSdript pbti..
        */
        void donvfrtToPSPbti(PbtiItfrbtor pbtiItfr) {

            flobt[] sfgmfnt = nfw flobt[6];
            int sfgmfntTypf;

            /* Mbp tif PbtiItfrbtor's fill rulf into tif PostSdript
             * fill rulf.
             */
            int fillRulf;
            if (pbtiItfr.gftWindingRulf() == PbtiItfrbtor.WIND_EVEN_ODD) {
                fillRulf = FILL_EVEN_ODD;
            } flsf {
                fillRulf = FILL_WINDING;
            }

            bfginPbti();

            sftFillModf(fillRulf);

            wiilf (pbtiItfr.isDonf() == fblsf) {
                sfgmfntTypf = pbtiItfr.durrfntSfgmfnt(sfgmfnt);

                switdi (sfgmfntTypf) {
                 dbsf PbtiItfrbtor.SEG_MOVETO:
                    movfTo(sfgmfnt[0], sfgmfnt[1]);
                    brfbk;

                 dbsf PbtiItfrbtor.SEG_LINETO:
                    linfTo(sfgmfnt[0], sfgmfnt[1]);
                    brfbk;

                /* Convfrt tif qubd pbti to b bfzifr.
                 */
                 dbsf PbtiItfrbtor.SEG_QUADTO:
                    flobt lbstX = gftPfnX();
                    flobt lbstY = gftPfnY();
                    flobt d1x = lbstX + (sfgmfnt[0] - lbstX) * 2 / 3;
                    flobt d1y = lbstY + (sfgmfnt[1] - lbstY) * 2 / 3;
                    flobt d2x = sfgmfnt[2] - (sfgmfnt[2] - sfgmfnt[0]) * 2/ 3;
                    flobt d2y = sfgmfnt[3] - (sfgmfnt[3] - sfgmfnt[1]) * 2/ 3;
                    bfzifrTo(d1x, d1y,
                             d2x, d2y,
                             sfgmfnt[2], sfgmfnt[3]);
                    brfbk;

                 dbsf PbtiItfrbtor.SEG_CUBICTO:
                    bfzifrTo(sfgmfnt[0], sfgmfnt[1],
                             sfgmfnt[2], sfgmfnt[3],
                             sfgmfnt[4], sfgmfnt[5]);
                    brfbk;

                 dbsf PbtiItfrbtor.SEG_CLOSE:
                    dlosfSubpbti();
                    brfbk;
                }


                pbtiItfr.nfxt();
            }
        }

    /*
     * Fill tif pbti dffinfd by <dodf>pbtiItfr</dodf>
     * witi tif spfdififd dolor.
     * Tif pbti is providfd in durrfnt usfr spbdf.
     */
    protfdtfd void dfvidfFill(PbtiItfrbtor pbtiItfr, Color dolor,
                              AffinfTrbnsform tx, Sibpf dlip) {

        sftTrbnsform(tx);
        sftClip(dlip);
        sftColor(dolor);
        donvfrtToPSPbti(pbtiItfr);
        /* Spfdify tif pbti to fill bs tif dlip, tiis fnsurfs tibt only
         * pixfls wiidi brf insidf tif pbti will bf fillfd, wiidi is
         * wibt tif Jbvb 2D APIs spfdify
         */
        mPSStrfbm.println(GSAVE_STR);
        sflfdtClipPbti();
        fillPbti();
        mPSStrfbm.println(GRESTORE_STR + " " + NEWPATH_STR);
    }

    /*
     * Run lfngti fndodf bytf brrby in b form suitbblf for dfdoding
     * by tif PS Lfvfl 2 filtfr RunLfngtiDfdodf.
     * Arrby dbtb to fndodf is inArr. Endodfd dbtb is writtfn to outArr
     * outArr must bf long fnougi to iold tif fndodfd dbtb but tiis
     * dbn't bf known bifbd of timf.
     * A sbff bssumption is to usf doublf tif lfngti of tif input brrby.
     * Tiis is tifn dopifd into b nfw brrby of tif dorrfdt lfngti wiidi
     * is rfturnfd.
     * Algoritim:
     * Endoding is b lfbd bytf followfd by dbtb bytfs.
     * Lfbd bytf of 0->127 indidbtfs lfbdBytf + 1 distindt bytfs follow
     * Lfbd bytf of 129->255 indidbtfs 257 - lfbdBytf is tif numbfr of timfs
     * tif following bytf is rfpfbtfd in tif sourdf.
     * 128 is b spfdibl lfbd bytf indidbting fnd of dbtb (EOD) bnd is
     * writtfn bs tif finbl bytf of tif rfturnfd fndodfd dbtb.
     */
     privbtf bytf[] rlEndodf(bytf[] inArr) {

         int inIndfx = 0;
         int outIndfx = 0;
         int stbrtIndfx = 0;
         int runLfn = 0;
         bytf[] outArr = nfw bytf[(inArr.lfngti * 2) +2];
         wiilf (inIndfx < inArr.lfngti) {
             if (runLfn == 0) {
                 stbrtIndfx = inIndfx++;
                 runLfn=1;
             }

             wiilf (runLfn < 128 && inIndfx < inArr.lfngti &&
                    inArr[inIndfx] == inArr[stbrtIndfx]) {
                 runLfn++; // dount run of sbmf vbluf
                 inIndfx++;
             }

             if (runLfn > 1) {
                 outArr[outIndfx++] = (bytf)(257 - runLfn);
                 outArr[outIndfx++] = inArr[stbrtIndfx];
                 runLfn = 0;
                 dontinuf; // bbdk to top of wiilf loop.
             }

             // if rfbdi ifrf ibvf b run of difffrfnt vblufs, or bt tif fnd.
             wiilf (runLfn < 128 && inIndfx < inArr.lfngti &&
                    inArr[inIndfx] != inArr[inIndfx-1]) {
                 runLfn++; // dount run of difffrfnt vblufs
                 inIndfx++;
             }
             outArr[outIndfx++] = (bytf)(runLfn - 1);
             for (int i = stbrtIndfx; i < stbrtIndfx+runLfn; i++) {
                 outArr[outIndfx++] = inArr[i];
             }
             runLfn = 0;
         }
         outArr[outIndfx++] = (bytf)128;
         bytf[] fndodfdDbtb = nfw bytf[outIndfx];
         Systfm.brrbydopy(outArr, 0, fndodfdDbtb, 0, outIndfx);

         rfturn fndodfdDbtb;
     }

    /* writtfn bdd. to Adobf Spfd. "Filtfrfd Filfs: ASCIIEndodf Filtfr",
     * "PS Lbngubgf Rfffrfndf Mbnubl, 2nd fdition: Sfdtion 3.13"
     */
    privbtf bytf[] bsdii85Endodf(bytf[] inArr) {
        bytf[]  outArr = nfw bytf[((inArr.lfngti+4) * 5 / 4) + 2];
        long p1 = 85;
        long p2 = p1*p1;
        long p3 = p1*p2;
        long p4 = p1*p3;
        bytf pling = '!';

        int i = 0;
        int olfn = 0;
        long vbl, rfm;

        wiilf (i+3 < inArr.lfngti) {
            vbl = ((long)((inArr[i++]&0xff))<<24) +
                  ((long)((inArr[i++]&0xff))<<16) +
                  ((long)((inArr[i++]&0xff))<< 8) +
                  ((long)(inArr[i++]&0xff));
            if (vbl == 0) {
                outArr[olfn++] = 'z';
            } flsf {
                rfm = vbl;
                outArr[olfn++] = (bytf)(rfm / p4 + pling); rfm = rfm % p4;
                outArr[olfn++] = (bytf)(rfm / p3 + pling); rfm = rfm % p3;
                outArr[olfn++] = (bytf)(rfm / p2 + pling); rfm = rfm % p2;
                outArr[olfn++] = (bytf)(rfm / p1 + pling); rfm = rfm % p1;
                outArr[olfn++] = (bytf)(rfm + pling);
            }
        }
        // input not b multiplf of 4 bytfs, writf pbrtibl output.
        if (i < inArr.lfngti) {
            int n = inArr.lfngti - i; // n bytfs rfmbin to bf writtfn

            vbl = 0;
            wiilf (i < inArr.lfngti) {
                vbl = (vbl << 8) + (inArr[i++]&0xff);
            }

            int bppfnd = 4 - n;
            wiilf (bppfnd-- > 0) {
                vbl = vbl << 8;
            }
            bytf []d = nfw bytf[5];
            rfm = vbl;
            d[0] = (bytf)(rfm / p4 + pling); rfm = rfm % p4;
            d[1] = (bytf)(rfm / p3 + pling); rfm = rfm % p3;
            d[2] = (bytf)(rfm / p2 + pling); rfm = rfm % p2;
            d[3] = (bytf)(rfm / p1 + pling); rfm = rfm % p1;
            d[4] = (bytf)(rfm + pling);

            for (int b = 0; b < n+1 ; b++) {
                outArr[olfn++] = d[b];
            }
        }

        // writf EOD mbrkfr.
        outArr[olfn++]='~'; outArr[olfn++]='>';

        /* Tif originbl intfntion wbs to insfrt b nfwlinf bftfr fvfry 78 bytfs.
         * Tiis wbs mbinly intfndfd for lfgibility but I dfdidfd bgbinst tiis
         * pbrtiblly bfdbusf of tif (smbll) bmount of fxtrb spbdf, bnd
         * pbrtiblly bfdbusf for linf brfbks fitifr would ibvf to ibrdwirf
         * bsdii 10 (nfwlinf) or dbldulbtf spbdf in bytfs to bllodbtf for
         * tif plbtform's nfwlinf bytf sfqufndf. Also nffd to bf dbrfful
         * bbout wifrf its insfrtfd:
         * Asdii 85 dfdodfr ignorfs wiitf spbdf fxdfpt for onf spfdibl dbsf:
         * you must fnsurf you do not split tif EOD mbrkfr bdross linfs.
         */
        bytf[] rftArr = nfw bytf[olfn];
        Systfm.brrbydopy(outArr, 0, rftArr, 0, olfn);
        rfturn rftArr;

    }

    /**
     * PluginPrintfr gfnfrbtfs EPSF wrbppfd witi b ifbdfr bnd trbilfr
     * dommfnt. Tiis donforms to tif nfw rfquirfmfnts of Mozillb 1.7
     * bnd FirfFox 1.5 bnd lbtfr. Ebrlifr vfrsions of tifsf browsfrs
     * did not support plugin printing in tif gfnfrbl sfnsf (not just Jbvb).
     * A notbblf limitbtion of tifsf browsfrs is tibt tify ibndlf plugins
     * wiidi would spbn pbgf boundbrifs by sdbling plugin dontfnt to fit on b
     * singlf pbgf. Tiis mfbns wiitf spbdf is lfft bt tif bottom of tif
     * prfvious pbgf bnd its impossiblf to print tifsf dbsfs bs tify bppfbr on
     * tif wfb pbgf. Tiis is dontrbst to iow tif sbmf browsfrs bfibvf on
     * Windows wifrf it rfndfrs bs on-sdrffn.
     * Cbsfs wifrf tif dontfnt fits on b singlf pbgf do work finf, bnd tify
     * brf tif mbjority of dbsfs.
     * Tif sdbling tibt tif browsfr spfdififs to mbkf tif plugin dontfnt fit
     * wifn it is lbrgfr tibn b singlf pbgf dbn iold is non-uniform. It
     * sdblfs tif bxis in wiidi tif dontfnt is too lbrgf just fnougi to
     * fnsurf it fits. For dontfnt wiidi is fxtrfmfly long tiis dould lfbd
     * to notidfbblf distortion. Howfvfr tibt is probbbly rbrf fnougi tibt
     * its not worti dompfnsbting for tibt ifrf, but wf dbn rfvisit tibt if
     * nffdfd, bnd dompfnsbtf by mbking tif sdblf for tif otifr bxis tif
     * sbmf.
     */
    publid stbtid dlbss PluginPrintfr implfmfnts Printbblf {

        privbtf EPSPrintfr fpsPrintfr;
        privbtf Componfnt bpplft;
        privbtf PrintStrfbm strfbm;
        privbtf String fpsTitlf;
        privbtf int bx, by, bw, bi;
        privbtf int widti, ifigit;

        /**
         * Tiis is dbllfd from tif Jbvb Plug-in to print bn Applft's
         * dontfnts bs EPS to b postsdript strfbm providfd by tif browsfr.
         * @pbrbm bpplft tif bpplft domponfnt to print.
         * @pbrbm strfbm tif print strfbm providfd by tif plug-in
         * @pbrbm x tif x lodbtion of tif bpplft pbnfl in tif browsfr window
         * @pbrbm y tif y lodbtion of tif bpplft pbnfl in tif browsfr window
         * @pbrbm w tif widti of tif bpplft pbnfl in tif browsfr window
         * @pbrbm i tif widti of tif bpplft pbnfl in tif browsfr window
         */
        publid PluginPrintfr(Componfnt bpplft,
                             PrintStrfbm strfbm,
                             int x, int y, int w, int i) {

            tiis.bpplft = bpplft;
            tiis.fpsTitlf = "Jbvb Plugin Applft";
            tiis.strfbm = strfbm;
            bx = x;
            by = y;
            bw = w;
            bi = i;
            widti = bpplft.sizf().widti;
            ifigit = bpplft.sizf().ifigit;
            fpsPrintfr = nfw EPSPrintfr(tiis, fpsTitlf, strfbm,
                                        0, 0, widti, ifigit);
        }

        publid void printPluginPSHfbdfr() {
            strfbm.println("%%BfginDodumfnt: JbvbPluginApplft");
        }

        publid void printPluginApplft() {
            try {
                fpsPrintfr.print();
            } dbtdi (PrintfrExdfption f) {
            }
        }

        publid void printPluginPSTrbilfr() {
            strfbm.println("%%EndDodumfnt: JbvbPluginApplft");
            strfbm.flusi();
        }

        publid void printAll() {
            printPluginPSHfbdfr();
            printPluginApplft();
            printPluginPSTrbilfr();
        }

        publid int print(Grbpiids g, PbgfFormbt pf, int pgIndfx) {
            if (pgIndfx > 0) {
                rfturn Printbblf.NO_SUCH_PAGE;
            } flsf {
                // "bwbrf" dlifnt dodf dbn dftfdt tibt its bffn pbssfd b
                // PrintfrGrbpiids bnd dould tiforftidblly print
                // difffrfntly. I tiink tiis is morf likfly usfful tibn
                // b problfm.
                bpplft.printAll(g);
                rfturn Printbblf.PAGE_EXISTS;
            }
        }

    }

    /*
     * Tiis dlbss dbn tbkf bn bpplidbtion-dlifnt supplifd printbblf objfdt
     * bnd sfnd tif rfsult to b strfbm.
     * Tif bpplidbtion dofs not nffd to sfnd bny postsdript to tiis strfbm
     * unlfss it nffds to spfdify b trbnslbtion ftd.
     * It bssumfs tibt its importing bpplidbtion obfys bll tif donvfntions
     * for importbtion of EPS. Sff Appfndix H - Endbpsulbtfd Postsdript Filf
     * Formbt - of tif Adobf Postsdript Lbngubgf Rfffrfndf Mbnubl, 2nd fdition.
     * Tiis dlbss dould bf usfd bs tif bbsis for fxposing tif bbility to
     * gfnfrbtf EPSF from 2D grbpiids bs b StrfbmPrintSfrvidf.
     * In tibt dbsf b MfdibPrintbblfArfb bttributf dould bf usfd to
     * dommunidbtf tif bounding box.
     */
    publid stbtid dlbss EPSPrintfr implfmfnts Pbgfbblf {

        privbtf PbgfFormbt pf;
        privbtf PSPrintfrJob job;
        privbtf int llx, lly, urx, ury;
        privbtf Printbblf printbblf;
        privbtf PrintStrfbm strfbm;
        privbtf String fpsTitlf;

        publid EPSPrintfr(Printbblf printbblf, String titlf,
                          PrintStrfbm strfbm,
                          int x, int y, int wid, int igt) {

            tiis.printbblf = printbblf;
            tiis.fpsTitlf = titlf;
            tiis.strfbm = strfbm;
            llx = x;
            lly = y;
            urx = llx+wid;
            ury = lly+igt;
            // donstrudt b PbgfFormbt witi zfro mbrgins rfprfsfnting tif
            // fxbdt bounds of tif bpplft. if donstrudt b tiforftidbl
            // pbpfr wiidi ibppfns to fxbdtly mbtdi bpplft pbnfl sizf.
            Pbpfr p = nfw Pbpfr();
            p.sftSizf((doublf)wid, (doublf)igt);
            p.sftImbgfbblfArfb(0.0,0.0, (doublf)wid, (doublf)igt);
            pf = nfw PbgfFormbt();
            pf.sftPbpfr(p);
        }

        publid void print() tirows PrintfrExdfption {
            strfbm.println("%!PS-Adobf-3.0 EPSF-3.0");
            strfbm.println("%%BoundingBox: " +
                           llx + " " + lly + " " + urx + " " + ury);
            strfbm.println("%%Titlf: " + fpsTitlf);
            strfbm.println("%%Crfbtor: Jbvb Printing");
            strfbm.println("%%CrfbtionDbtf: " + nfw jbvb.util.Dbtf());
            strfbm.println("%%EndCommfnts");
            strfbm.println("/pluginSbvf sbvf dff");
            strfbm.println("mbrk"); // for rfstoring stbdk stbtf on rfturn

            job = nfw PSPrintfrJob();
            job.fpsPrintfr = tiis; // modififs tif bfibviour of PSPrintfrJob
            job.mPSStrfbm = strfbm;
            job.mDfstTypf = RbstfrPrintfrJob.STREAM; // prfvfnts dlosurf

            job.stbrtDod();
            try {
                job.printPbgf(tiis, 0);
            } dbtdi (Tirowbblf t) {
                if (t instbndfof PrintfrExdfption) {
                    tirow (PrintfrExdfption)t;
                } flsf {
                    tirow nfw PrintfrExdfption(t.toString());
                }
            } finblly {
                strfbm.println("dlfbrtombrk"); // rfstorf stbdk stbtf
                strfbm.println("pluginSbvf rfstorf");
                job.fndDod();
            }
            strfbm.flusi();
        }

        publid int gftNumbfrOfPbgfs() {
            rfturn 1;
        }

        publid PbgfFormbt gftPbgfFormbt(int pgIndfx) {
            if (pgIndfx > 0) {
                tirow nfw IndfxOutOfBoundsExdfption("pgIndfx");
            } flsf {
                rfturn pf;
            }
        }

        publid Printbblf gftPrintbblf(int pgIndfx) {
            if (pgIndfx > 0) {
                tirow nfw IndfxOutOfBoundsExdfption("pgIndfx");
            } flsf {
            rfturn printbblf;
            }
        }

    }
}
