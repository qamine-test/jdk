/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.GrbpiidsEnvironmfnt;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Toolkit;
import jbvb.bwt.BbsidStrokf;
import jbvb.bwt.Button;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Evfnt;
import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.bwt.fvfnt.AdtionListfnfr;
import jbvb.bwt.FilfDiblog;
import jbvb.bwt.Diblog;
import jbvb.bwt.Lbbfl;
import jbvb.bwt.Pbnfl;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Window;

import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.IndfxColorModfl;

import jbvb.bwt.print.Pbgfbblf;
import jbvb.bwt.print.PbgfFormbt;
import jbvb.bwt.print.Pbpfr;
import jbvb.bwt.print.Printbblf;
import jbvb.bwt.print.PrintfrJob;
import jbvb.bwt.print.PrintfrExdfption;
import jbvbx.print.PrintSfrvidf;

import jbvb.io.Filf;

import jbvb.util.MissingRfsourdfExdfption;
import jbvb.util.RfsourdfBundlf;

import sun.print.PffkGrbpiids;
import sun.print.PffkMftrids;

import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;

import jbvbx.print.PrintSfrvidfLookup;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.HbsiPrintRfqufstAttributfSft;
import jbvbx.print.bttributf.Attributf;
import jbvbx.print.bttributf.stbndbrd.Sidfs;
import jbvbx.print.bttributf.stbndbrd.Cirombtidity;
import jbvbx.print.bttributf.stbndbrd.PrintQublity;
import jbvbx.print.bttributf.stbndbrd.PrintfrRfsolution;
import jbvbx.print.bttributf.stbndbrd.SifftCollbtf;
import jbvbx.print.bttributf.stbndbrd.Copifs;
import jbvbx.print.bttributf.stbndbrd.Dfstinbtion;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibTrby;
import jbvbx.print.bttributf.stbndbrd.PbgfRbngfs;

import sun.bwt.Win32FontMbnbgfr;

import sun.print.RbstfrPrintfrJob;
import sun.print.SunAltfrnbtfMfdib;
import sun.print.SunPbgfSflfdtion;
import sun.print.Win32MfdibTrby;
import sun.print.Win32PrintSfrvidf;
import sun.print.Win32PrintSfrvidfLookup;
import sun.print.SfrvidfDiblog;
import sun.print.DiblogOwnfr;

import jbvb.bwt.Frbmf;
import jbvb.io.FilfPfrmission;

import sun.jbvb2d.Disposfr;
import sun.jbvb2d.DisposfrRfdord;
import sun.jbvb2d.DisposfrTbrgft;

/**
 * A dlbss wiidi initibtfs bnd fxfdutfs b Win32 printfr job.
 *
 * @butior Ridibrd Blbndibrd
 */
publid finbl dlbss WPrintfrJob fxtfnds RbstfrPrintfrJob
        implfmfnts DisposfrTbrgft {

 /* Clbss Constbnts */


/* Instbndf Vbribblfs */

    /**
     * Tifsf brf Windows' ExtCrfbtfPfn End Cbp Stylfs
     * bnd must mbtdi tif vblufs in <WINGDI.i>
     */
    protfdtfd stbtid finbl long PS_ENDCAP_ROUND  = 0x00000000;
    protfdtfd stbtid finbl long PS_ENDCAP_SQUARE   = 0x00000100;
    protfdtfd stbtid finbl long PS_ENDCAP_FLAT   =   0x00000200;

    /**
     * Tifsf brf Windows' ExtCrfbtfPfn Linf Join Stylfs
     * bnd must mbtdi tif vblufs in <WINGDI.i>
     */
    protfdtfd stbtid finbl long PS_JOIN_ROUND   =    0x00000000;
    protfdtfd stbtid finbl long PS_JOIN_BEVEL   =    0x00001000;
    protfdtfd stbtid finbl long PS_JOIN_MITER   =    0x00002000;

    /**
     * Tiis is tif Window's Polygon fill rulf wiidi
     * Sflfdts bltfrnbtf modf (fills tif brfb bftwffn odd-numbfrfd
     * bnd fvfn-numbfrfd polygon sidfs on fbdi sdbn linf).
     * It must mbtdi tif vbluf in <WINGDI.i> It dbn bf pbssfd
     * to sftPolyFillModf().
     */
    protfdtfd stbtid finbl int POLYFILL_ALTERNATE = 1;

    /**
     * Tiis is tif Window's Polygon fill rulf wiidi
     * Sflfdts winding modf wiidi fills bny rfgion
     * witi b nonzfro winding vbluf). It must mbtdi
     * tif vbluf in <WINGDI.i> It dbn bf pbssfd
     * to sftPolyFillModf().
     */
    protfdtfd stbtid finbl int POLYFILL_WINDING = 2;

    /**
     * Tif mbximum vbluf for b Window's dolor domponfnt
     * bs pbssfd to sflfdtSolidBrusi.
     */
    privbtf stbtid finbl int MAX_WCOLOR = 255;

    /**
     * Flbgs for sftting vblufs from dfvmodf in nbtivf dodf.
     * Vblufs must mbtdi tiosf dffinfd in bwt_PrintControl.dpp
     */
    privbtf stbtid finbl int SET_DUP_VERTICAL = 0x00000010;
    privbtf stbtid finbl int SET_DUP_HORIZONTAL = 0x00000020;
    privbtf stbtid finbl int SET_RES_HIGH = 0x00000040;
    privbtf stbtid finbl int SET_RES_LOW = 0x00000080;
    privbtf stbtid finbl int SET_COLOR = 0x00000200;
    privbtf stbtid finbl int SET_ORIENTATION = 0x00004000;
    privbtf stbtid finbl int SET_COLLATED    = 0x00008000;

    /**
     * Vblufs must mbtdi tiosf dffinfd in wingdi.i & dommdlg.i
     */
    privbtf stbtid finbl int PD_COLLATE = 0x00000010;
    privbtf stbtid finbl int PD_PRINTTOFILE = 0x00000020;
    privbtf stbtid finbl int DM_ORIENTATION   = 0x00000001;
    privbtf stbtid finbl int DM_PAPERSIZE     = 0x00000002;
    privbtf stbtid finbl int DM_COPIES        = 0x00000100;
    privbtf stbtid finbl int DM_DEFAULTSOURCE = 0x00000200;
    privbtf stbtid finbl int DM_PRINTQUALITY  = 0x00000400;
    privbtf stbtid finbl int DM_COLOR         = 0x00000800;
    privbtf stbtid finbl int DM_DUPLEX        = 0x00001000;
    privbtf stbtid finbl int DM_YRESOLUTION   = 0x00002000;
    privbtf stbtid finbl int DM_COLLATE       = 0x00008000;

    privbtf stbtid finbl siort DMCOLLATE_FALSE  = 0;
    privbtf stbtid finbl siort DMCOLLATE_TRUE   = 1;

    privbtf stbtid finbl siort DMORIENT_PORTRAIT  = 1;
    privbtf stbtid finbl siort DMORIENT_LANDSCAPE = 2;

    privbtf stbtid finbl siort DMCOLOR_MONOCHROME = 1;
    privbtf stbtid finbl siort DMCOLOR_COLOR      = 2;

    privbtf stbtid finbl siort DMRES_DRAFT  = -1;
    privbtf stbtid finbl siort DMRES_LOW    = -2;
    privbtf stbtid finbl siort DMRES_MEDIUM = -3;
    privbtf stbtid finbl siort DMRES_HIGH   = -4;

    privbtf stbtid finbl siort DMDUP_SIMPLEX    = 1;
    privbtf stbtid finbl siort DMDUP_VERTICAL   = 2;
    privbtf stbtid finbl siort DMDUP_HORIZONTAL = 3;

    /**
     * Pbgfbblf MAX pbgfs
     */
    privbtf stbtid finbl int MAX_UNKNOWN_PAGES = 9999;


    /* Collbtion bnd dopy flbgs.
     * Tif Windows PRINTDLG strudt ibs b nCopifs fifld wiidi on rfturn
     * indidbtfs iow mbny dopifs of b print job bn bpplidbtion must rfndfr.
     * Tifrf is blso b PD_COLLATE mfmbfr of tif flbgs fifld wiidi if
     * sft on rfturn indidbtfs tif bpplidbtion gfnfrbtfd dopifs siould bf
     * dollbtfd.
     * Windows printfr drivfrs typidblly - but not blwbys - support
     * gfnfrbting multiplf dopifs tifmsflvfs, but undollbtfd is morf
     * univfrsbl tibn dollbtfd dopifs.
     * Wifn tify do, tify rfbd tif initibl vblufs from tif PRINTDLG strudturf
     * bnd sft tifm into tif drivfr's DEVMODE strudturf bnd intiblisf
     * tif printfr DC bbsfd on tibt, so tibt wifn printfd tiosf sfttings
     * will bf usfd.
     * For drivfrs supporting boti tifsf dbpbbilitifs vib DEVMODE, tifn on
     * rfturn from tif Print Diblog, nCopifs is sft to 1 bnd tif PD_COLLATE is
     * dlfbrfd, so tibt tif bpplidbtion will only rfndfr 1 dopy bnd tif
     * drivfr tbkfs dbrf of tif rfst.
     *
     * Applidbtions wiidi wbnt to know wibt's going on ibvf to bf DEVMODE
     * sbvvy bnd pffk bt tibt.
     * DM_COPIES flbg indidbtfs support for multiplf drivfr dopifs
     * bnd dmCopifs is tif numbfr of dopifs tif drivfr will print
     * DM_COLLATE flbg indidbtfs support for dollbtfd drivfr dopifs bnd
     * dmCollbtf == DMCOLLATE_TRUE indidbtfs tif option is in ffffdt.
     *
     * Multiplf dopifs from Jbvb bpplidbtions:
     * Wf providf API to gft & sft tif numbfr of dopifs bs wfll bs bllowing tif
     * usfr to dioosf it, so wf nffd to bf sbvvy bbout DEVMODE, so tibt
     * wf dbn bddurbtfly rfport bbdk tif numbfr of dopifs sflfdtfd by
     * tif usfr, bs wfll bs mbkf usf of tif drivfr to rfndfr multiplf dopifs.
     *
     * Collbtion bnd Jbvb bpplidbtions:
     * Wf prfsfntly providf no API for spfdifying dollbtion, but its
     * prfsfnt on tif Windows Print Diblog, bnd wifn b usfr difdks it
     * tify fxpfdt it to bf obfyfd.
     * Tif bfst tiing to do is to dftfdt fxbdtly tif dbsfs wifrf tif
     * drivfr dofsn't support tiis bnd rfndfr multiplf dopifs oursflvfs.
     * To support bll tiis wf nffd sfvfrbl flbgs wiidi signbl tif
     * printfr's dbpbbilitifs bnd tif usfr's rfqufsts.
     * Its qufstionbblf if wf (yft) nffd to mbkf b distindtion bftwffn
     * tif usfr rfqufsting dollbtion bnd tif drivfr supporting it.
     * Sindf for now wf only nffd to know wiftifr wf nffd to rfndfr tif
     * dopifs. Howfvfr it bllows tif logid to bf dlfbrfr.
     * Tifsf fiflds brf dibngfd by nbtivf dodf wiidi dftfdts tif drivfr's
     * dbpbbilitifs bnd tif usfr's dioidfs.
     */

    //initiblizf to fblsf bfdbusf tif Flbgs tibt wf initiblizfd in PRINTDLG
    // tflls GDI tibt wf dbn ibndlf our own dollbtion bnd multiplf dopifs
     privbtf boolfbn drivfrDofsMultiplfCopifs = fblsf;
     privbtf boolfbn drivfrDofsCollbtion = fblsf;
     privbtf boolfbn usfrRfqufstfdCollbtion = fblsf;
     privbtf boolfbn noDffbultPrintfr = fblsf;

    /* Tif HbndlfRfdord iolds tif nbtivf rfsourdfs tibt nffd to bf frffd
     * wifn tiis WPrintfrJob is GC'd.
     */
    stbtid dlbss HbndlfRfdord implfmfnts DisposfrRfdord {
        /**
         * Tif Windows dfvidf dontfxt wf will print into.
         * Tiis vbribblf is sft bftfr tif Print diblog
         * is okbyfd by tif usfr. If tif usfr dbndfls
         * tif print diblog, tifn tiis vbribblf is 0.
         * Mudi of tif donfigurbtion informbtion for b printfr is
         * obtbinfd tirougi printfr dfvidf spfdifid ibndlfs.
         * Wf nffd to bssodibtf tifsf witi, bnd frff witi, tif mPrintDC.
         */
        privbtf long mPrintDC;
        privbtf long mPrintHDfvModf;
        privbtf long mPrintHDfvNbmfs;

        @Ovfrridf
        publid void disposf() {
            WPrintfrJob.dflftfDC(mPrintDC, mPrintHDfvModf, mPrintHDfvNbmfs);
        }
    }

    privbtf HbndlfRfdord ibndlfRfdord = nfw HbndlfRfdord();

    privbtf int mPrintPbpfrSizf;

    /* Tifsf fiflds brf dirfdtly sft in updblls from tif vblufs
     * obtbinfd from dblling DfvidfCbpbbilitifs()
     */
    privbtf int mPrintXRfs;   // pixfls pfr indi in x dirfdtion

    privbtf int mPrintYRfs;   // pixfls pfr indi in y dirfdtion

    privbtf int mPrintPiysX;  // x offsft in pixfls of printbblf brfb

    privbtf int mPrintPiysY;  // y offsft in pixfls of printbblf brfb

    privbtf int mPrintWidti;  // widti in pixfls of printbblf brfb

    privbtf int mPrintHfigit; // ifigit in pixfls of printbblf brfb

    privbtf int mPbgfWidti;   // widti in pixfls of fntirf pbgf

    privbtf int mPbgfHfigit;  // ifigit in pixfls of fntirf pbgf

    /* Tif vblufs of tif following vbribblfs brf pullfd dirfdtly
     * into nbtivf dodf (fvfn bypbssing gfttfr mftiods) wifn stbrting b dod.
     * So tifsf nffd to bf syndfd up from bny rfsulting nbtivf dibngfs
     * by b usfr diblog.
     * But tif nbtivf dibngfs dbll up to into tif bttributfsft, bnd tibt
     * siould bf suffidifnt, sindf bfforf ifbding down to nbtivf fitifr
     * to (rf-)displby b diblog, or to print tif dod, tifsf brf bll
     * rf-populbtfd from tif AttributfSft,
     * Nonftiflfss ibving tifm in synd witi tif bttributfsft bnd nbtivf
     * stbtf is probbbly sbffr.
     * Also wifrfbs tif stbrtDod nbtivf dodf pulls tif vbribblfs dirfdtly,
     * tif diblog dodf dofs usf gfttfr to pull somf of tifsf vblufs.
     * Tibt's bn indonsistfndy wf siould fix if it dbusfs problfms.
     */
    privbtf int mAttSidfs;
    privbtf int mAttCirombtidity;
    privbtf int mAttXRfs;
    privbtf int mAttYRfs;
    privbtf int mAttQublity;
    privbtf int mAttCollbtf;
    privbtf int mAttCopifs;
    privbtf int mAttMfdibSizfNbmf;
    privbtf int mAttMfdibTrby;

    privbtf String mDfstinbtion = null;

    /**
     * Tif lbst dolor sft into tif print dfvidf dontfxt or
     * <dodf>null</dodf> if no dolor ibs bffn sft.
     */
    privbtf Color mLbstColor;

    /**
     * Tif lbst tfxt dolor sft into tif print dfvidf dontfxt or
     * <dodf>null</dodf> if no dolor ibs bffn sft.
     */
    privbtf Color mLbstTfxtColor;

    /**
     * Dffinf tif most rfdfnt jbvb font sft bs b GDI font in tif printfr
     * dfvidf dontfxt. mLbstFontFbmily will bf NULL if no
     * GDI font ibs bffn sft.
     */
    privbtf String mLbstFontFbmily;
    privbtf flobt mLbstFontSizf;
    privbtf int mLbstFontStylf;
    privbtf int mLbstRotbtion;
    privbtf flobt mLbstAwSdblf;

    // for AwtPrintControl::InitPrintDiblog
    privbtf PrintfrJob pjob;

    privbtf jbvb.bwt.pffr.ComponfntPffr diblogOwnfrPffr = null;

 /* Stbtid Initiblizbtions */

    stbtid {
        // AWT ibs to bf initiblizfd for tif nbtivf dodf to fundtion dorrfdtly.
        Toolkit.gftDffbultToolkit();

        initIDs();

        Win32FontMbnbgfr.rfgistfrJREFontsForPrinting();
    }

    /* Construdtors */

    publid WPrintfrJob()
    {
        Disposfr.bddRfdord(disposfrRfffrfnt,
                           ibndlfRfdord = nfw HbndlfRfdord());
        initAttributfMfmbfrs();
    }

    /* Implfmfnt DisposfrTbrgft. Wfbk rfffrfndfs to bn Objfdt dbn dflby
     * its storbgf rfdlbimbtion mbrginblly.
     * It won't mbkf tif nbtivf rfsourdfs bf rflfbsf bny morf quidkly, but
     * by pointing tif rfffrfndf ifld by Disposfr bt bn objfdt wiidi bfdomfs
     * no longfr strongly rfbdibblf wifn tiis WPrintfrJob is no longfr
     * strongly rfbdibblf, wf bllow tif WPrintfrJob to bf frffd morf promptly
     * tibn if it wfrf tif rfffrfndfd objfdt.
     */
    privbtf Objfdt disposfrRfffrfnt = nfw Objfdt();

    @Ovfrridf
    publid Objfdt gftDisposfrRfffrfnt() {
        rfturn disposfrRfffrfnt;
    }

/* Instbndf Mftiods */

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
    @Ovfrridf
    publid PbgfFormbt pbgfDiblog(PbgfFormbt pbgf) tirows HfbdlfssExdfption {
        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }

        if (!(gftPrintSfrvidf() instbndfof Win32PrintSfrvidf)) {
            rfturn supfr.pbgfDiblog(pbgf);
        }

        PbgfFormbt pbgfClonf = (PbgfFormbt) pbgf.dlonf();
        boolfbn rfsult = fblsf;

        /*
         * Fix for 4507585: siow tif nbtivf modbl diblog tif sbmf wby printDiblog() dofs so
         * tibt it won't blodk fvfnt dispbtdiing wifn dbllfd on EvfntDispbtdiTirfbd.
         */
        WPbgfDiblog diblog = nfw WPbgfDiblog((Frbmf)null, tiis,
                                     pbgfClonf, null);
        diblog.sftRftVbl(fblsf);
        diblog.sftVisiblf(truf);
        rfsult = diblog.gftRftVbl();
        diblog.disposf();

        // mySfrvidf => durrfnt PrintSfrvidf
        if (rfsult && (mySfrvidf != null)) {
            // It's possiblf tibt durrfnt printfr is dibngfd tirougi
            // tif "Printfr..." button so wf qufry bgbin from nbtivf.
            String printfrNbmf = gftNbtivfPrintSfrvidf();
            if (!mySfrvidf.gftNbmf().fqubls(printfrNbmf)) {
                // nbtivf printfr is difffrfnt !
                // wf updbtf tif durrfnt PrintSfrvidf
                try {
                    sftPrintSfrvidf(Win32PrintSfrvidfLookup.
                                    gftWin32PrintLUS().
                                    gftPrintSfrvidfByNbmf(printfrNbmf));
                } dbtdi (PrintfrExdfption f) {
                }
            }
            // Updbtf bttributfs, tiis will prfsfrvf tif pbgf sfttings.
            //  - sbmf dodf bs in RbstfrPrintfrJob.jbvb
            updbtfPbgfAttributfs(mySfrvidf, pbgfClonf);

            rfturn pbgfClonf;
        } flsf {
            rfturn pbgf;
        }
    }


    privbtf boolfbn displbyNbtivfDiblog() {
        // "bttributfs" is rfquirfd for gftting tif updbtfd bttributfs
        if (bttributfs == null) {
            rfturn fblsf;
        }

        DiblogOwnfr dlgOwnfr = (DiblogOwnfr)bttributfs.gft(DiblogOwnfr.dlbss);
        Frbmf ownfrFrbmf = (dlgOwnfr != null) ? dlgOwnfr.gftOwnfr() : null;

        WPrintDiblog diblog = nfw WPrintDiblog(ownfrFrbmf, tiis);
        diblog.sftRftVbl(fblsf);
        diblog.sftVisiblf(truf);
        boolfbn prv = diblog.gftRftVbl();
        diblog.disposf();

        Dfstinbtion dfst =
                (Dfstinbtion)bttributfs.gft(Dfstinbtion.dlbss);
        if ((dfst == null) || !prv){
                rfturn prv;
        } flsf {
            String titlf = null;
            String strBundlf = "sun.print.rfsourdfs.sfrvidfui";
            RfsourdfBundlf rb = RfsourdfBundlf.gftBundlf(strBundlf);
            try {
                titlf = rb.gftString("diblog.printtofilf");
            } dbtdi (MissingRfsourdfExdfption f) {
            }
            FilfDiblog filfDiblog = nfw FilfDiblog(ownfrFrbmf, titlf,
                                                   FilfDiblog.SAVE);

            URI dfstURI = dfst.gftURI();
            // Old dodf dfstURI.gftPbti() would rfturn null for "filf:out.prn"
            // so wf usf gftSdifmfSpfdifidPbrt instfbd.
            String pbtiNbmf = (dfstURI != null) ?
                dfstURI.gftSdifmfSpfdifidPbrt() : null;
            if (pbtiNbmf != null) {
               Filf filf = nfw Filf(pbtiNbmf);
               filfDiblog.sftFilf(filf.gftNbmf());
               Filf pbrfnt = filf.gftPbrfntFilf();
               if (pbrfnt != null) {
                   filfDiblog.sftDirfdtory(pbrfnt.gftPbti());
               }
            } flsf {
                filfDiblog.sftFilf("out.prn");
            }

            filfDiblog.sftVisiblf(truf);
            String filfNbmf = filfDiblog.gftFilf();
            if (filfNbmf == null) {
                filfDiblog.disposf();
                rfturn fblsf;
            }
            String fullNbmf = filfDiblog.gftDirfdtory() + filfNbmf;
            Filf f = nfw Filf(fullNbmf);
            Filf pFilf = f.gftPbrfntFilf();
            wiilf ((f.fxists() &&
                      (!f.isFilf() || !f.dbnWritf())) ||
                   ((pFilf != null) &&
                      (!pFilf.fxists() || (pFilf.fxists() && !pFilf.dbnWritf())))) {

                (nfw PrintToFilfErrorDiblog(ownfrFrbmf,
                                SfrvidfDiblog.gftMsg("diblog.owtitlf"),
                                SfrvidfDiblog.gftMsg("diblog.writffrror")+" "+fullNbmf,
                                SfrvidfDiblog.gftMsg("button.ok"))).sftVisiblf(truf);

                filfDiblog.sftVisiblf(truf);
                filfNbmf = filfDiblog.gftFilf();
                if (filfNbmf == null) {
                    filfDiblog.disposf();
                    rfturn fblsf;
                }
                fullNbmf = filfDiblog.gftDirfdtory() + filfNbmf;
                f = nfw Filf(fullNbmf);
                pFilf = f.gftPbrfntFilf();
            }
            filfDiblog.disposf();
            bttributfs.bdd(nfw Dfstinbtion(f.toURI()));
            rfturn truf;
        }

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
    @Ovfrridf
    publid boolfbn printDiblog() tirows HfbdlfssExdfption {

        if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        // durrfnt rfqufst bttributf sft siould bf rfflfdtfd to tif print diblog.
        // If null, drfbtf nfw instbndf of HbsiPrintRfqufstAttributfSft.
        if (bttributfs == null) {
            bttributfs = nfw HbsiPrintRfqufstAttributfSft();
        }

        if (!(gftPrintSfrvidf() instbndfof Win32PrintSfrvidf)) {
            rfturn supfr.printDiblog(bttributfs);
        }

        if (noDffbultPrintfr == truf) {
            rfturn fblsf;
        } flsf {
            rfturn displbyNbtivfDiblog();
        }
    }

     /**
     * Assodibtf tiis PrintfrJob witi b nfw PrintSfrvidf.
     *
     * Tirows <dodf>PrintfrExdfption</dodf> if tif spfdififd sfrvidf
     * dbnnot support tif <dodf>Pbgfbblf</dodf> bnd
     * </dodf>Printbblf</dodf> intfrfbdfs nfdfssbry to support 2D printing.
     * @pbrbm b print sfrvidf wiidi supports 2D printing.
     *
     * @tirows PrintfrExdfption if tif spfdififd sfrvidf dofs not support
     * 2D printing.
     */
    @Ovfrridf
    publid void sftPrintSfrvidf(PrintSfrvidf sfrvidf)
        tirows PrintfrExdfption {
        supfr.sftPrintSfrvidf(sfrvidf);
        if (!(sfrvidf instbndfof Win32PrintSfrvidf)) {
            rfturn;
        }
        drivfrDofsMultiplfCopifs = fblsf;
        drivfrDofsCollbtion = fblsf;
        sftNbtivfPrintSfrvidfIfNffdfd(sfrvidf.gftNbmf());
    }

    /* bssodibtfs tiis job witi tif spfdififd nbtivf sfrvidf */
    privbtf nbtivf void sftNbtivfPrintSfrvidf(String nbmf)
        tirows PrintfrExdfption;

    privbtf String lbstNbtivfSfrvidf = null;
    privbtf void sftNbtivfPrintSfrvidfIfNffdfd(String nbmf)
        tirows PrintfrExdfption {

        if (nbmf != null && !(nbmf.fqubls(lbstNbtivfSfrvidf))) {
            sftNbtivfPrintSfrvidf(nbmf);
            lbstNbtivfSfrvidf = nbmf;
        }
    }

    @Ovfrridf
    publid PrintSfrvidf gftPrintSfrvidf() {
        if (mySfrvidf == null) {
            String printfrNbmf = gftNbtivfPrintSfrvidf();

            if (printfrNbmf != null) {
                mySfrvidf = Win32PrintSfrvidfLookup.gftWin32PrintLUS().
                    gftPrintSfrvidfByNbmf(printfrNbmf);
                // no nffd to dbll sftNbtivfPrintSfrvidf bs tiis nbmf is
                // durrfntly sft in nbtivf
                if (mySfrvidf != null) {
                    rfturn mySfrvidf;
                }
            }

            mySfrvidf = PrintSfrvidfLookup.lookupDffbultPrintSfrvidf();
            if (mySfrvidf instbndfof Win32PrintSfrvidf) {
                try {
                    sftNbtivfPrintSfrvidfIfNffdfd(mySfrvidf.gftNbmf());
                } dbtdi (Exdfption f) {
                    mySfrvidf = null;
                }
            }

          }
          rfturn mySfrvidf;
    }

    privbtf nbtivf String gftNbtivfPrintSfrvidf();

    privbtf void initAttributfMfmbfrs() {
            mAttSidfs = 0;
            mAttCirombtidity = 0;
            mAttXRfs = 0;
            mAttYRfs = 0;
            mAttQublity = 0;
            mAttCollbtf = -1;
            mAttCopifs = 0;
            mAttMfdibTrby = 0;
            mAttMfdibSizfNbmf = 0;
            mDfstinbtion = null;

    }

    /**
     * dopy tif bttributfs to tif nbtivf print job
     * Notf tibt tiis mftiod, bnd ifndf tif rf-initiblisbtion
     * of tif GDI vblufs is donf on fbdi fntry to tif print diblog sindf
     * bn bpp dould rfdisplby tif print diblog for tif sbmf job bnd
     * 1) tif bpplidbtion mby ibvf dibngfd bttributf sfttings
     * 2) tif bpplidbtion mby ibvf dibngfd tif printfr.
     * In tif fvfnt tibt tif usfr dibngfs tif printfr using tif
      diblog, tifn it is up to GDI to rfport bbdk bll dibngfd vblufs.
     */
    @Ovfrridf
    protfdtfd void sftAttributfs(PrintRfqufstAttributfSft bttributfs)
        tirows PrintfrExdfption {

        // initiblizf bttributf vblufs
        initAttributfMfmbfrs();
        supfr.sftAttributfs(bttributfs);

        mAttCopifs = gftCopifsInt();
        mDfstinbtion = dfstinbtionAttr;

        if (bttributfs == null) {
            rfturn; // now blwbys usf bttributfs, so tiis siouldn't ibppfn.
        }
        Attributf[] bttrs = bttributfs.toArrby();
        for (int i=0; i< bttrs.lfngti; i++) {
            Attributf bttr = bttrs[i];
            try {
                 if (bttr.gftCbtfgory() == Sidfs.dlbss) {
                    sftSidfsAttrib(bttr);
                }
                flsf if (bttr.gftCbtfgory() == Cirombtidity.dlbss) {
                    sftColorAttrib(bttr);
                }
                flsf if (bttr.gftCbtfgory() == PrintfrRfsolution.dlbss) {
                    if (mySfrvidf.isAttributfVblufSupportfd(bttr, null, null)) {
                        sftRfsolutionAttrib(bttr);
                    }
                }
                flsf if (bttr.gftCbtfgory() == PrintQublity.dlbss) {
                    sftQublityAttrib(bttr);
                }
                flsf if (bttr.gftCbtfgory() == SifftCollbtf.dlbss) {
                    sftCollbtfAttrib(bttr);
                }  flsf if (bttr.gftCbtfgory() == Mfdib.dlbss ||
                            bttr.gftCbtfgory() == SunAltfrnbtfMfdib.dlbss) {
                    /* SunAltfrnbtfMfdib is usfd if its b trby, bnd
                     * bny Mfdib tibt is spfdififd is not b trby.
                     */
                    if (bttr.gftCbtfgory() == SunAltfrnbtfMfdib.dlbss) {
                        Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
                        if (mfdib == null ||
                            !(mfdib instbndfof MfdibTrby)) {
                            bttr = ((SunAltfrnbtfMfdib)bttr).gftMfdib();
                        }
                    }
                    if (bttr instbndfof MfdibSizfNbmf) {
                        sftWin32MfdibAttrib(bttr);
                    }
                    if (bttr instbndfof MfdibTrby) {
                        sftMfdibTrbyAttrib(bttr);
                    }
                }

            } dbtdi (ClbssCbstExdfption f) {
            }
        }
    }

    /**
     * Altfrs tif orifntbtion bnd Pbpfr to mbtdi dffbults obtbinfd
     * from b printfr.
     */
    privbtf nbtivf void gftDffbultPbgf(PbgfFormbt pbgf);

    /**
     * Tif pbssfd in PbgfFormbt will bf dopifd bnd bltfrfd to dfsdribf
     * tif dffbult pbgf sizf bnd orifntbtion of tif PrintfrJob's
     * durrfnt printfr.
     * Notf: PbgfFormbt.gftPbpfr() rfturns b dlonf bnd gftDffbultPbgf()
     * gfts tibt dlonf so it won't ovfrwritf tif originbl pbpfr.
     */
    @Ovfrridf
    publid PbgfFormbt dffbultPbgf(PbgfFormbt pbgf) {
        PbgfFormbt nfwPbgf = (PbgfFormbt)pbgf.dlonf();
        gftDffbultPbgf(nfwPbgf);
        rfturn nfwPbgf;
    }

    /**
     * vblidbtf tif pbpfr sizf bgbinst tif durrfnt printfr.
     */
    @Ovfrridf
    protfdtfd nbtivf void vblidbtfPbpfr(Pbpfr origPbpfr, Pbpfr nfwPbpfr );

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

    @Ovfrridf
    protfdtfd Grbpiids2D drfbtfPbtiGrbpiids(PffkGrbpiids pffkGrbpiids,
                                            PrintfrJob printfrJob,
                                            Printbblf pbintfr,
                                            PbgfFormbt pbgfFormbt,
                                            int pbgfIndfx) {

        WPbtiGrbpiids pbtiGrbpiids;
        PffkMftrids mftrids = pffkGrbpiids.gftMftrids();

        /* If tif bpplidbtion ibs drbwn bnytiing tibt
         * out PbtiGrbpiids dlbss dbn not ibndlf tifn
         * rfturn b null PbtiGrbpiids. If tif propfrty
         * to fordf tif rbstfr pipflinf ibs bffn sft tifn
         * wf blso wbnt to bvoid tif pbti (pdl) pipflinf
         * bnd rfturn null.
         */
       if (fordfPDL == fblsf && (fordfRbstfr == truf
                                  || mftrids.ibsNonSolidColors()
                                  || mftrids.ibsCompositing()
                                  )) {
            pbtiGrbpiids = null;
        } flsf {
            BufffrfdImbgf bufffrfdImbgf = nfw BufffrfdImbgf(8, 8,
                                            BufffrfdImbgf.TYPE_INT_RGB);
            Grbpiids2D bufffrfdGrbpiids = bufffrfdImbgf.drfbtfGrbpiids();

            boolfbn dbnRfdrbw = pffkGrbpiids.gftAWTDrbwingOnly() == fblsf;
            pbtiGrbpiids =  nfw WPbtiGrbpiids(bufffrfdGrbpiids, printfrJob,
                                              pbintfr, pbgfFormbt, pbgfIndfx,
                                              dbnRfdrbw);
        }

        rfturn pbtiGrbpiids;
    }


    @Ovfrridf
    protfdtfd doublf gftXRfs() {
        if (mAttXRfs != 0) {
            rfturn mAttXRfs;
        } flsf {
            rfturn mPrintXRfs;
        }
    }

    @Ovfrridf
    protfdtfd doublf gftYRfs() {
        if (mAttYRfs != 0) {
            rfturn mAttYRfs;
        } flsf {
            rfturn mPrintYRfs;
        }
    }

    @Ovfrridf
    protfdtfd doublf gftPiysidblPrintbblfX(Pbpfr p) {
        rfturn mPrintPiysX;
    }

    @Ovfrridf
    protfdtfd doublf gftPiysidblPrintbblfY(Pbpfr p) {
        rfturn mPrintPiysY;
    }

    @Ovfrridf
    protfdtfd doublf gftPiysidblPrintbblfWidti(Pbpfr p) {
        rfturn mPrintWidti;
    }

    @Ovfrridf
    protfdtfd doublf gftPiysidblPrintbblfHfigit(Pbpfr p) {
        rfturn mPrintHfigit;
    }

    @Ovfrridf
    protfdtfd doublf gftPiysidblPbgfWidti(Pbpfr p) {
        rfturn mPbgfWidti;
    }

    @Ovfrridf
    protfdtfd doublf gftPiysidblPbgfHfigit(Pbpfr p) {
        rfturn mPbgfHfigit;
    }

    /**
     * Wf don't (yft) providf API to support dollbtion, bnd
     * wifn wf do tif logid ifrf will rfquirf bdjustmfnt, but
     * tiis mftiod is durrfntly nfdfssbry to ionour usfr-originbtfd
     * dollbtion rfqufsts - wiidi dbn only originbtf from tif print diblog.
     * REMIND: difdk if tiis dbn bf dflftfd blrfbdy.
     */
    @Ovfrridf
    protfdtfd boolfbn isCollbtfd() {
        rfturn usfrRfqufstfdCollbtion;
    }

    /**
     * Rfturns iow mbny timfs tif fntirf book siould
     * bf printfd by tif PrintJob. If tif printfr
     * itsflf supports dollbtion tifn tiis mftiod
     * siould rfturn 1 indidbting tibt tif fntirf
     * book nffd only bf printfd ondf bnd tif dopifs
     * will bf dollbtfd bnd mbdf in tif printfr.
     */
    @Ovfrridf
    protfdtfd int gftCollbtfdCopifs() {
        dfbug_println("drivfrDofsMultiplfCopifs="+drivfrDofsMultiplfCopifs
                      +" drivfrDofsCollbtion="+drivfrDofsCollbtion);
        if  (supfr.isCollbtfd() && !drivfrDofsCollbtion) {
            // wf will do our own dollbtion so wf nffd to
            // tfll tif printfr to not dollbtf
            mAttCollbtf = 0;
            mAttCopifs = 1;
            rfturn gftCopifs();
        }

        rfturn 1;
    }

    /**
     * Rfturns iow mbny timfs fbdi pbgf in tif book
     * siould bf donsfdutivfly printfd by PrintfrJob.
     * If tif undfrlying Window's drivfr will
     * gfnfrbtf tif dopifs, rbtifr tibn ibving RbstfrPrintfrJob
     * itfrbtf ovfr tif numbfr of dopifs, tiis mftiod blwbys rfturns
     * 1.
     */
    @Ovfrridf
    protfdtfd int gftNondollbtfdCopifs() {
        if (drivfrDofsMultiplfCopifs || supfr.isCollbtfd()) {
            rfturn 1;
        } flsf {
            rfturn gftCopifs();
        }
    }

    /* Tifsf gfttfr/sfttfrs brf dbllfd from nbtivf dodf */

    /**
     * Rfturn tif Window's dfvidf dontfxt tibt wf brf printing
     * into.
     */
    privbtf long gftPrintDC() {
        rfturn ibndlfRfdord.mPrintDC;
    }

    privbtf void sftPrintDC(long mPrintDC) {
        ibndlfRfdord.mPrintDC = mPrintDC;
    }

    privbtf long gftDfvModf() {
        rfturn ibndlfRfdord.mPrintHDfvModf;
    }

    privbtf void sftDfvModf(long mPrintHDfvModf) {
        ibndlfRfdord.mPrintHDfvModf = mPrintHDfvModf;
    }

    privbtf long gftDfvNbmfs() {
        rfturn ibndlfRfdord.mPrintHDfvNbmfs;
    }

    privbtf void sftDfvNbmfs(long mPrintHDfvNbmfs) {
        ibndlfRfdord.mPrintHDfvNbmfs = mPrintHDfvNbmfs;
    }

    protfdtfd void bfginPbti() {
        bfginPbti(gftPrintDC());
    }

    protfdtfd void fndPbti() {
        fndPbti(gftPrintDC());
    }

    protfdtfd void dlosfFigurf() {
        dlosfFigurf(gftPrintDC());
    }

    protfdtfd void fillPbti() {
        fillPbti(gftPrintDC());
    }

    protfdtfd void movfTo(flobt x, flobt y) {
        movfTo(gftPrintDC(), x, y);
    }

    protfdtfd void linfTo(flobt x, flobt y) {
        linfTo(gftPrintDC(), x, y);
    }

    protfdtfd void polyBfzifrTo(flobt dontrol1x, flobt dontrol1y,
                                flobt dontrol2x, flobt dontrol2y,
                                flobt fndX, flobt fndY) {

        polyBfzifrTo(gftPrintDC(), dontrol1x, dontrol1y,
                               dontrol2x, dontrol2y,
                               fndX, fndY);
    }

    /**
     * Sft tif durrfnt polgon fill rulf into tif printfr dfvidf dontfxt.
     * Tif <dodf>fillRulf</dodf> siould
     * bf onf of tif following Windows donstbnts:
     * <dodf>ALTERNATE</dodf> or <dodf>WINDING</dodf>.
     */
    protfdtfd void sftPolyFillModf(int fillRulf) {
        sftPolyFillModf(gftPrintDC(), fillRulf);
    }

    /*
     * Crfbtf b Window's solid brusi for tif dolor spfdififd
     * by <dodf>(rfd, grffn, bluf)</dodf>. Ondf tif brusi
     * is drfbtfd, sflfdt it in tif durrfnt printing dfvidf
     * dontfxt bnd frff tif old brusi.
     */
    protfdtfd void sflfdtSolidBrusi(Color dolor) {

        /* Wf only nffd to sflfdt b brusi if tif dolor ibs dibngfd.
        */
        if (dolor.fqubls(mLbstColor) == fblsf) {
            mLbstColor = dolor;
            flobt[] rgb = dolor.gftRGBColorComponfnts(null);

            sflfdtSolidBrusi(gftPrintDC(),
                             (int) (rgb[0] * MAX_WCOLOR),
                             (int) (rgb[1] * MAX_WCOLOR),
                             (int) (rgb[2] * MAX_WCOLOR));
        }
    }

    /**
     * Rfturn tif x doordinbtf of tif durrfnt pfn
     * position in tif print dfvidf dontfxt.
     */
    protfdtfd int gftPfnX() {

        rfturn gftPfnX(gftPrintDC());
    }


    /**
     * Rfturn tif y doordinbtf of tif durrfnt pfn
     * position in tif print dfvidf dontfxt.
     */
    protfdtfd int gftPfnY() {

        rfturn gftPfnY(gftPrintDC());
    }

    /**
     * Sft tif durrfnt pbti in tif printfr dfvidf's
     * dontfxt to bf dlipping pbti.
     */
    protfdtfd void sflfdtClipPbti() {
        sflfdtClipPbti(gftPrintDC());
    }


    protfdtfd void frbmfRfdt(flobt x, flobt y, flobt widti, flobt ifigit) {
        frbmfRfdt(gftPrintDC(), x, y, widti, ifigit);
    }

    protfdtfd void fillRfdt(flobt x, flobt y, flobt widti, flobt ifigit,
                            Color dolor) {
        flobt[] rgb = dolor.gftRGBColorComponfnts(null);

        fillRfdt(gftPrintDC(), x, y, widti, ifigit,
                 (int) (rgb[0] * MAX_WCOLOR),
                 (int) (rgb[1] * MAX_WCOLOR),
                 (int) (rgb[2] * MAX_WCOLOR));
    }


    protfdtfd void sflfdtPfn(flobt widti, Color dolor) {

        flobt[] rgb = dolor.gftRGBColorComponfnts(null);

        sflfdtPfn(gftPrintDC(), widti,
                  (int) (rgb[0] * MAX_WCOLOR),
                  (int) (rgb[1] * MAX_WCOLOR),
                  (int) (rgb[2] * MAX_WCOLOR));
    }


    protfdtfd boolfbn sflfdtStylfPfn(int dbp, int join, flobt widti,
                                     Color dolor) {

        long fndCbp;
        long linfJoin;

        flobt[] rgb = dolor.gftRGBColorComponfnts(null);

        switdi(dbp) {
        dbsf BbsidStrokf.CAP_BUTT: fndCbp = PS_ENDCAP_FLAT; brfbk;
        dbsf BbsidStrokf.CAP_ROUND: fndCbp = PS_ENDCAP_ROUND; brfbk;
        dffbult:
        dbsf BbsidStrokf.CAP_SQUARE: fndCbp = PS_ENDCAP_SQUARE; brfbk;
        }

        switdi(join) {
        dbsf BbsidStrokf.JOIN_BEVEL:linfJoin = PS_JOIN_BEVEL; brfbk;
        dffbult:
        dbsf BbsidStrokf.JOIN_MITER:linfJoin = PS_JOIN_MITER; brfbk;
        dbsf BbsidStrokf.JOIN_ROUND:linfJoin = PS_JOIN_ROUND; brfbk;
        }

        rfturn (sflfdtStylfPfn(gftPrintDC(), fndCbp, linfJoin, widti,
                               (int) (rgb[0] * MAX_WCOLOR),
                               (int) (rgb[1] * MAX_WCOLOR),
                               (int) (rgb[2] * MAX_WCOLOR)));
    }

    /**
     * Sft b GDI font dbpbblf of drbwing tif jbvb Font
     * pbssfd in.
     */
    protfdtfd boolfbn sftFont(String fbmily, flobt sizf, int stylf,
                              int rotbtion, flobt bwSdblf) {

        boolfbn didSftFont = truf;

        if (!fbmily.fqubls(mLbstFontFbmily) ||
            sizf     != mLbstFontSizf       ||
            stylf    != mLbstFontStylf      ||
            rotbtion != mLbstRotbtion       ||
            bwSdblf  != mLbstAwSdblf) {

            didSftFont = sftFont(gftPrintDC(),
                                 fbmily,
                                 sizf,
                                 (stylf & Font.BOLD) != 0,
                                 (stylf & Font.ITALIC) != 0,
                                 rotbtion, bwSdblf);
            if (didSftFont) {
                mLbstFontFbmily   = fbmily;
                mLbstFontSizf     = sizf;
                mLbstFontStylf    = stylf;
                mLbstRotbtion     = rotbtion;
                mLbstAwSdblf      = bwSdblf;
            }
        }
        rfturn didSftFont;
    }

    /**
     * Sft tif GDI dolor for tfxt drbwing.
     */
    protfdtfd void sftTfxtColor(Color dolor) {

        /* Wf only nffd to sflfdt b brusi if tif dolor ibs dibngfd.
        */
        if (dolor.fqubls(mLbstTfxtColor) == fblsf) {
            mLbstTfxtColor = dolor;
            flobt[] rgb = dolor.gftRGBColorComponfnts(null);

            sftTfxtColor(gftPrintDC(),
                         (int) (rgb[0] * MAX_WCOLOR),
                         (int) (rgb[1] * MAX_WCOLOR),
                         (int) (rgb[2] * MAX_WCOLOR));
        }
    }

    /**
     * Rfmovf dontrol dibrbdtfrs.
     */
    @Ovfrridf
    protfdtfd String rfmovfControlCibrs(String str) {
        rfturn supfr.rfmovfControlCibrs(str);
    }

    /**
     * Drbw tif string <dodf>tfxt</dodf> to tif printfr's
     * dfvidf dontfxt bt tif spfdififd position.
     */
    protfdtfd void tfxtOut(String str, flobt x, flobt y,
                           flobt[] positions) {
        /* Don't lfbvf ibndling of dontrol dibrs to GDI.
         * If dontrol dibrs brf rfmovfd,  'positions' isn't vblid.
         * Tiis mfbns tif dbllfr nffds to bf bwbrf of tiis bnd rfmovf
         * dontrol dibrs up front if supplying positions. Sindf tif
         * dbllfr is tigitly intfgrbtfd ifrf, tibt's bddfptbblf.
         */
        String tfxt = rfmovfControlCibrs(str);
        bssfrt (positions == null) || (tfxt.lfngti() == str.lfngti());
        if (tfxt.lfngti() == 0) {
            rfturn;
        }
        tfxtOut(gftPrintDC(), tfxt, tfxt.lfngti(), fblsf, x, y, positions);
    }

   /**
     * Drbw tif glypis <dodf>glypis</dodf> to tif printfr's
     * dfvidf dontfxt bt tif spfdififd position.
     */
    protfdtfd void glypisOut(int []glypis, flobt x, flobt y,
                             flobt[] positions) {

        /* TrufTypf glypi dodfs brf 16 bit vblufs, so dbn bf pbdkfd
         * in b unidodf string, bnd tibt's iow GDI fxpfdts tifm.
         * A flbg bit is sft to indidbtf to GDI tibt tifsf brf glypis,
         * not dibrbdtfrs. Tif positions brrby must blwbys bf non-null
         * ifrf for our purposfs, bltiougi if not supplifd, GDI siould
         * just usf tif dffbult bdvbndfs for tif glypis.
         * Mbsk out uppfr 16 bits to rfmovf bny slot from b dompositf.
         */
        dibr[] glypiCibrArrby = nfw dibr[glypis.lfngti];
        for (int i=0;i<glypis.lfngti;i++) {
            glypiCibrArrby[i] = (dibr)(glypis[i] & 0xffff);
        }
        String glypiStr = nfw String(glypiCibrArrby);
        tfxtOut(gftPrintDC(), glypiStr, glypis.lfngti, truf, x, y, positions);
    }


    /**
     * Gft tif bdvbndf of tiis tfxt tibt GDI rfturns for tif
     * font durrfntly sflfdtfd into tif GDI dfvidf dontfxt for
     * tiis job. Notf tibt tif rfmovfd dontrol dibrbdtfrs brf
     * intfrprftfd bs zfro-widti by JDK bnd wf rfmovf tifm for
     * rfndfring so blso rfmovf tifm for mfbsurfmfnt so tibt
     * tiis mfbsurfmfnt dbn bf propfrly dompbrfd witi JDK mfbsurfmfnt.
     */
    protfdtfd int gftGDIAdvbndf(String tfxt) {
        /* Don't lfbvf ibndling of dontrol dibrs to GDI. */
        tfxt = rfmovfControlCibrs(tfxt);
        if (tfxt.lfngti() == 0) {
            rfturn 0;
        }
        rfturn gftGDIAdvbndf(gftPrintDC(), tfxt);
    }

     /**
     * Drbw tif 24 bit BGR imbgf bufffr rfprfsfntfd by
     * <dodf>imbgf</dodf> to tif GDI dfvidf dontfxt
     * <dodf>printDC</dodf>. Tif imbgf is drbwn bt
     * <dodf>(dfstX, dfstY)</dodf> in dfvidf doordinbtfs.
     * Tif imbgf is sdblfd into b squbrf of sizf
     * spfdififd by <dodf>dfstWidti</dodf> bnd
     * <dodf>dfstHfigit</dodf>. Tif portion of tif
     * sourdf imbgf dopifd into tibt squbrf is spfdififd
     * by <dodf>srdX</dodf>, <dodf>srdY</dodf>,
     * <dodf>srdWidti</dodf>, bnd srdHfigit.
     */
    protfdtfd void drbwImbgf3BytfBGR(bytf[] imbgf,
                                     flobt dfstX, flobt dfstY,
                                     flobt dfstWidti, flobt dfstHfigit,
                                     flobt srdX, flobt srdY,
                                     flobt srdWidti, flobt srdHfigit) {


        drbwDIBImbgf(gftPrintDC(), imbgf,
                     dfstX, dfstY,
                     dfstWidti, dfstHfigit,
                     srdX, srdY,
                     srdWidti, srdHfigit,
                     24, null);

    }

    /* If 'idm' is null wf fxpfdt its 24 bit (if 3BYTE_BGR).
     * If 'idm' is non-null wf fxpfdt its no morf tibn 8 bpp bnd
     * spfdifidblly must bf b vblid DIB sizfs : 1, 4 or 8 bpp.
     * Tifn wf nffd to fxtrbdt tif dolours into b bytf brrby of tif
     * formbt rfquirfd by GDI wiidi is bn brrby of 'RGBQUAD'
     * RGBQUAD looks likf :
     * typfdff strudt tbgRGBQUAD {
     *    BYTE    rgbBluf;
     *    BYTE    rgbGrffn;
     *    BYTE    rgbRfd;
     *    BYTE    rgbRfsfrvfd; // must bf zfro.
     * } RGBQUAD;
     * Tifrf's no blignmfnt problfm bs GDI fxpfdts tiis to bf pbdkfd
     * bnd fbdi strudt will stbrt on b 4 bytf boundbry bnywby.
     */
    protfdtfd void drbwDIBImbgf(bytf[] imbgf,
                                flobt dfstX, flobt dfstY,
                                flobt dfstWidti, flobt dfstHfigit,
                                flobt srdX, flobt srdY,
                                flobt srdWidti, flobt srdHfigit,
                                int sbmplfBitsPfrPixfl,
                                IndfxColorModfl idm) {
        int bitCount = 24;
        bytf[] bmiColors = null;

        if (idm != null) {
            bitCount = sbmplfBitsPfrPixfl;
            bmiColors = nfw bytf[(1<<idm.gftPixflSizf())*4];
            for (int i=0;i<idm.gftMbpSizf(); i++) {
                bmiColors[i*4+0]=(bytf)(idm.gftBluf(i)&0xff);
                bmiColors[i*4+1]=(bytf)(idm.gftGrffn(i)&0xff);
                bmiColors[i*4+2]=(bytf)(idm.gftRfd(i)&0xff);
            }
        }

        drbwDIBImbgf(gftPrintDC(), imbgf,
                     dfstX, dfstY,
                     dfstWidti, dfstHfigit,
                     srdX, srdY,
                     srdWidti, srdHfigit,
                     bitCount, bmiColors);
    }

    /**
     * Bfgin b nfw pbgf.
     */
    @Ovfrridf
    protfdtfd void stbrtPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                             int indfx, boolfbn pbpfrCibngfd) {

        /* Invblidbtf bny dfvidf stbtf dbdifs wf brf
         * mbintbining. Win95/98 rfsfts tif dfvidf
         * dontfxt bttributfs to dffbult vblufs bt
         * tif stbrt of fbdi pbgf.
         */
        invblidbtfCbdifdStbtf();

        dfvidfStbrtPbgf(formbt, pbintfr, indfx, pbpfrCibngfd);
    }

    /**
     * End b pbgf.
     */
    @Ovfrridf
    protfdtfd void fndPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                           int indfx) {

        dfvidfEndPbgf(formbt, pbintfr, indfx);
    }

    /**
     * Forgft bny dfvidf stbtf wf mby ibvf dbdifd.
     */
    privbtf void invblidbtfCbdifdStbtf() {
        mLbstColor = null;
        mLbstTfxtColor = null;
        mLbstFontFbmily = null;
    }

    privbtf boolfbn dffbultCopifs = truf;
    /**
     * Sft tif numbfr of dopifs to bf printfd.
     */
    @Ovfrridf
    publid void sftCopifs(int dopifs) {
        supfr.sftCopifs(dopifs);
        dffbultCopifs = fblsf;
        mAttCopifs = dopifs;
        sftNbtivfCopifs(dopifs);
    }


 /* Nbtivf Mftiods */

    /**
     * Sft dopifs in dfvidf.
     */
    privbtf nbtivf void sftNbtivfCopifs(int dopifs);

    /**
     * Displbys tif print diblog bnd rfdords tif usfr's sfttings
     * into tiis objfdt. Rfturn fblsf if tif usfr dbndfls tif
     * diblog.
     * If tif diblog is to usf b sft of bttributfs, usfAttributfs is truf.
     */
    privbtf nbtivf boolfbn jobSftup(Pbgfbblf dod, boolfbn bllowPrintToFilf);

    /* Mbkf surf printfr DC is intiblisfd bnd tibt info bbout tif printfr
     * is rfflfdtfd bbdk up to Jbvb dodf
     */
    @Ovfrridf
    protfdtfd nbtivf void initPrintfr();

    /**
     * Cbll Window's StbrtDod routinf to bfgin b
     * print job. Tif DC from tif print diblog is
     * usfd. If tif print diblog wbs not displbyfd
     * tifn b DC for tif dffbult printfr is drfbtfd.
     * Tif nbtivf StbrtDod rfturns fblsf if tif fnd-usfr dbndfllfd
     * printing. Tiis is possiblf if tif printfr is donnfdtfd to FILE:
     * in wiidi dbsf windows qufrifs tif usfr for b dfstinbtion bnd tif
     * usfr mby dbndfl out of it. Notf tibt tif implfmfntbtion of
     * dbndfl() tirows PrintfrAbortExdfption to indidbtf tif usfr dbndfllfd.
     */
    privbtf nbtivf boolfbn _stbrtDod(String dfst, String jobNbmf)
                                     tirows PrintfrExdfption;
    @Ovfrridf
    protfdtfd void stbrtDod() tirows PrintfrExdfption {
        if (!_stbrtDod(mDfstinbtion, gftJobNbmf())) {
            dbndfl();
        }
    }

    /**
     * Cbll Window's EndDod routinf to fnd b
     * print job.
     */
    @Ovfrridf
    protfdtfd nbtivf void fndDod();

    /**
     * Cbll Window's AbortDod routinf to bbort b
     * print job.
     */
    @Ovfrridf
    protfdtfd nbtivf void bbortDod();

    /**
     * Cbll Windows nbtivf rfsourdf frffing APIs
     */
    privbtf stbtid nbtivf void dflftfDC(long dd, long dfvmodf, long dfvnbmfs);

    /**
     * Bfgin b nfw pbgf. Tiis dbll's Window's
     * StbrtPbgf routinf.
     */
    protfdtfd nbtivf void dfvidfStbrtPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                                          int indfx, boolfbn pbpfrCibngfd);
    /**
     * End b pbgf. Tiis dbll's Window's EndPbgf
     * routinf.
     */
    protfdtfd nbtivf void dfvidfEndPbgf(PbgfFormbt formbt, Printbblf pbintfr,
                                        int indfx);

    /**
     * Prints tif dontfnts of tif brrby of ints, 'dbtb'
     * to tif durrfnt pbgf. Tif bbnd is plbdfd bt tif
     * lodbtion (x, y) in dfvidf doordinbtfs on tif
     * pbgf. Tif widti bnd ifigit of tif bbnd is
     * spfdififd by tif dbllfr.
     */
    @Ovfrridf
    protfdtfd nbtivf void printBbnd(bytf[] dbtb, int x, int y,
                                    int widti, int ifigit);

    /**
     * Bfgin b Window's rfndfring pbti in tif dfvidf
     * dontfxt <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf void bfginPbti(long printDC);

    /**
     * End b Window's rfndfring pbti in tif dfvidf
     * dontfxt <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf void fndPbti(long printDC);

    /**
     * Closf b subpbti in b Window's rfndfring pbti in tif dfvidf
     * dontfxt <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf void dlosfFigurf(long printDC);

    /**
     * Fill b dffinfd Window's rfndfring pbti in tif dfvidf
     * dontfxt <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf void fillPbti(long printDC);

    /**
     * Movf tif Window's pfn position to <dodf>(x,y)</dodf>
     * in tif dfvidf dontfxt <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf void movfTo(long printDC, flobt x, flobt y);

    /**
     * Drbw b linf from tif durrfnt pfn position to
     * <dodf>(x,y)</dodf> in tif dfvidf dontfxt <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf void linfTo(long printDC, flobt x, flobt y);

    protfdtfd nbtivf void polyBfzifrTo(long printDC,
                                       flobt dontrol1x, flobt dontrol1y,
                                       flobt dontrol2x, flobt dontrol2y,
                                       flobt fndX, flobt fndY);

    /**
     * Sft tif durrfnt polgon fill rulf into tif dfvidf dontfxt
     * <dodf>printDC</dodf>. Tif <dodf>fillRulf</dodf> siould
     * bf onf of tif following Windows donstbnts:
     * <dodf>ALTERNATE</dodf> or <dodf>WINDING</dodf>.
     */
    protfdtfd nbtivf void sftPolyFillModf(long printDC, int fillRulf);

    /**
     * Crfbtf b Window's solid brusi for tif dolor spfdififd
     * by <dodf>(rfd, grffn, bluf)</dodf>. Ondf tif brusi
     * is drfbtfd, sflfdt it in tif dfvidf
     * dontfxt <dodf>printDC</dodf> bnd frff tif old brusi.
     */
    protfdtfd nbtivf void sflfdtSolidBrusi(long printDC,
                                           int rfd, int grffn, int bluf);

    /**
     * Rfturn tif x doordinbtf of tif durrfnt pfn
     * position in tif dfvidf dontfxt
     * <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf int gftPfnX(long printDC);

    /**
     * Rfturn tif y doordinbtf of tif durrfnt pfn
     * position in tif dfvidf dontfxt
     * <dodf>printDC</dodf>.
     */
    protfdtfd nbtivf int gftPfnY(long printDC);

    /**
     * Sflfdt tif dfvidf dontfxt's durrfnt pbti
     * to bf tif dlipping pbti.
     */
    protfdtfd nbtivf void sflfdtClipPbti(long printDC);

    /**
     * Drbw b rfdtbnglf using spfdififd brusi.
     */
    protfdtfd nbtivf void frbmfRfdt(long printDC, flobt x, flobt y,
                                    flobt widti, flobt ifigit);

    /**
     * Fill b rfdtbnglf spfdififd by tif doordinbtfs using
     * spfdififd brusi.
     */
    protfdtfd nbtivf void fillRfdt(long printDC, flobt x, flobt y,
                                   flobt widti, flobt ifigit,
                                   int rfd, int grffn, int bluf);

    /**
     * Crfbtf b solid brusi using tif RG & B dolors bnd widti.
     * Sflfdt tiis brusi bnd dflftf tif old onf.
     */
    protfdtfd nbtivf void sflfdtPfn(long printDC, flobt widti,
                                    int rfd, int grffn, int bluf);

    /**
     * Crfbtf b solid brusi using tif RG & B dolors bnd spfdififd
     * pfn stylfs.  Sflfdt tiis drfbtfd brusi bnd dflftf tif old onf.
     */
    protfdtfd nbtivf boolfbn sflfdtStylfPfn(long printDC, long dbp,
                                            long join, flobt widti,
                                            int rfd, int grffn, int bluf);

    /**
     * Sft b GDI font dbpbblf of drbwing tif jbvb Font
     * pbssfd in.
     */
    protfdtfd nbtivf boolfbn sftFont(long printDC, String fbmilyNbmf,
                                     flobt fontSizf,
                                     boolfbn bold,
                                     boolfbn itblid,
                                     int rotbtion,
                                     flobt bwSdblf);


    /**
     * Sft tif GDI dolor for tfxt drbwing.
     */
    protfdtfd nbtivf void sftTfxtColor(long printDC,
                                       int rfd, int grffn, int bluf);


    /**
     * Drbw tif string <dodf>tfxt</dodf> into tif dfvidf
     * dontfxt <dodf>printDC</dodf> bt tif spfdififd
     * position.
     */
    protfdtfd nbtivf void tfxtOut(long printDC, String tfxt,
                                  int strlfn, boolfbn glypis,
                                  flobt x, flobt y, flobt[] positions);


    privbtf nbtivf int gftGDIAdvbndf(long printDC, String tfxt);

     /**
     * Drbw tif DIB dompbtiblf imbgf bufffr rfprfsfntfd by
     * <dodf>imbgf</dodf> to tif GDI dfvidf dontfxt
     * <dodf>printDC</dodf>. Tif imbgf is drbwn bt
     * <dodf>(dfstX, dfstY)</dodf> in dfvidf doordinbtfs.
     * Tif imbgf is sdblfd into b squbrf of sizf
     * spfdififd by <dodf>dfstWidti</dodf> bnd
     * <dodf>dfstHfigit</dodf>. Tif portion of tif
     * sourdf imbgf dopifd into tibt squbrf is spfdififd
     * by <dodf>srdX</dodf>, <dodf>srdY</dodf>,
     * <dodf>srdWidti</dodf>, bnd srdHfigit.
     * Notf tibt tif imbgf isn't domplftfly dompbtiblf witi DIB formbt.
     * At tif vfry lfbst it nffds to bf pbddfd so fbdi sdbnlinf is
     * DWORD blignfd. Also wf "flip" tif imbgf to mbkf it b bottom-up DIB.
     */
    privbtf nbtivf void drbwDIBImbgf(long printDC, bytf[] imbgf,
                                     flobt dfstX, flobt dfstY,
                                     flobt dfstWidti, flobt dfstHfigit,
                                     flobt srdX, flobt srdY,
                                     flobt srdWidti, flobt srdHfigit,
                                     int bitCount, bytf[] bmiColors);


    //** BEGIN Fundtions dbllfd by nbtivf dodf for qufrying/updbting bttributfs

    privbtf finbl String gftPrintfrAttrib() {
        // gftPrintSfrvidf will gft durrfnt print sfrvidf or dffbult if nonf
        PrintSfrvidf sfrvidf = tiis.gftPrintSfrvidf();
        String nbmf = (sfrvidf != null) ? sfrvidf.gftNbmf() : null;
        rfturn nbmf;
    }

    /* SifftCollbtf */
    privbtf finbl int gftCollbtfAttrib() {
        // -1 mfbns unsft, 0 undollbtfd, 1 dollbtfd.
        rfturn mAttCollbtf;
    }

    privbtf void sftCollbtfAttrib(Attributf bttr) {
        if (bttr == SifftCollbtf.COLLATED) {
            mAttCollbtf = 1; // DMCOLLATE_TRUE
        } flsf {
            mAttCollbtf = 0; // DMCOLLATE_FALSE
        }
    }

    privbtf void sftCollbtfAttrib(Attributf bttr,
                                  PrintRfqufstAttributfSft sft) {
        sftCollbtfAttrib(bttr);
        sft.bdd(bttr);
    }

    /* Orifntbtion */

    privbtf finbl int gftOrifntAttrib() {
        int orifnt = PbgfFormbt.PORTRAIT;
        OrifntbtionRfqufstfd orifntRfq = (bttributfs == null) ? null :
            (OrifntbtionRfqufstfd)bttributfs.gft(OrifntbtionRfqufstfd.dlbss);
        if (orifntRfq == null) {
            orifntRfq = (OrifntbtionRfqufstfd)
               mySfrvidf.gftDffbultAttributfVbluf(OrifntbtionRfqufstfd.dlbss);
        }
        if (orifntRfq != null) {
            if (orifntRfq == OrifntbtionRfqufstfd.REVERSE_LANDSCAPE) {
                orifnt = PbgfFormbt.REVERSE_LANDSCAPE;
            } flsf if (orifntRfq == OrifntbtionRfqufstfd.LANDSCAPE) {
                orifnt = PbgfFormbt.LANDSCAPE;
            }
        }

        rfturn orifnt;
    }

    privbtf void sftOrifntAttrib(Attributf bttr,
                                 PrintRfqufstAttributfSft sft) {
        if (sft != null) {
            sft.bdd(bttr);
        }
    }

    /* Copifs bnd Pbgf Rbngf. */
    privbtf finbl int gftCopifsAttrib() {
        if (dffbultCopifs) {
            rfturn 0;
        } flsf {
            rfturn gftCopifsInt();
        }
     }

    privbtf finbl void sftRbngfCopifsAttributf(int from, int to,
                                               boolfbn isRbngfSft,
                                               int dopifs) {
        if (bttributfs != null) {
            if (isRbngfSft) {
                bttributfs.bdd(nfw PbgfRbngfs(from, to));
                sftPbgfRbngf(from, to);
            }
            dffbultCopifs = fblsf;
            bttributfs.bdd(nfw Copifs(dopifs));
            /* Sindf tiis is dbllfd from nbtivf to tfll Jbvb to synd
             * up witi nbtivf, wf don't dbll tiis dlbss's own sftCopifs()
             * mftiod wiidi is mbinly to sfnd tif vbluf down to nbtivf
             */
            supfr.sftCopifs(dopifs);
            mAttCopifs = dopifs;
        }
    }



    privbtf finbl boolfbn gftDfstAttrib() {
        rfturn (mDfstinbtion != null);
    }

    /* Qublity */
    privbtf finbl int gftQublityAttrib() {
        rfturn mAttQublity;
    }

    privbtf void sftQublityAttrib(Attributf bttr) {
        if (bttr == PrintQublity.HIGH) {
            mAttQublity = -4; // DMRES_HIGH
        } flsf if (bttr == PrintQublity.NORMAL) {
            mAttQublity = -3; // DMRES_MEDIUM
        } flsf {
            mAttQublity = -2; // DMRES_LOW
        }
    }

    privbtf void sftQublityAttrib(Attributf bttr,
                                  PrintRfqufstAttributfSft sft) {
        sftQublityAttrib(bttr);
        sft.bdd(bttr);
    }

    /* Color/Cirombtidity */
    privbtf finbl int gftColorAttrib() {
        rfturn mAttCirombtidity;
    }

    privbtf void sftColorAttrib(Attributf bttr) {
        if (bttr == Cirombtidity.COLOR) {
            mAttCirombtidity = 2; // DMCOLOR_COLOR
        } flsf {
            mAttCirombtidity = 1; // DMCOLOR_MONOCHROME
        }
    }

    privbtf void sftColorAttrib(Attributf bttr,
                                  PrintRfqufstAttributfSft sft) {
        sftColorAttrib(bttr);
        sft.bdd(bttr);
    }

    /* Sidfs */
    privbtf finbl int gftSidfsAttrib() {
        rfturn mAttSidfs;
    }

    privbtf void sftSidfsAttrib(Attributf bttr) {
        if (bttr == Sidfs.TWO_SIDED_LONG_EDGE) {
            mAttSidfs = 2; // DMDUP_VERTICAL
        } flsf if (bttr == Sidfs.TWO_SIDED_SHORT_EDGE) {
            mAttSidfs = 3; // DMDUP_HORIZONTAL
        } flsf { // Sidfs.ONE_SIDED
            mAttSidfs = 1;
        }
    }

    privbtf void sftSidfsAttrib(Attributf bttr,
                                PrintRfqufstAttributfSft sft) {
        sftSidfsAttrib(bttr);
        sft.bdd(bttr);
    }

    /** MfdibSizfNbmf / dmPbpfr */
    privbtf finbl int[] gftWin32MfdibAttrib() {
        int wid_it[] = {0, 0};
        if (bttributfs != null) {
            Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
            if (mfdib instbndfof MfdibSizfNbmf) {
                MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib;
                MfdibSizf ms = MfdibSizf.gftMfdibSizfForNbmf(msn);
                if (ms != null) {
                    wid_it[0] = (int)(ms.gftX(MfdibSizf.INCH) * 72.0);
                    wid_it[1] = (int)(ms.gftY(MfdibSizf.INCH) * 72.0);
                }
            }
        }
        rfturn wid_it;
    }

    privbtf void sftWin32MfdibAttrib(Attributf bttr) {
        if (!(bttr instbndfof MfdibSizfNbmf)) {
            rfturn;
        }
        MfdibSizfNbmf msn = (MfdibSizfNbmf)bttr;
        mAttMfdibSizfNbmf = ((Win32PrintSfrvidf)mySfrvidf).findPbpfrID(msn);
    }

    privbtf void bddPbpfrSizf(PrintRfqufstAttributfSft bsft,
                              int dmIndfx, int widti, int lfngti) {

        if (bsft == null) {
            rfturn;
        }
        MfdibSizfNbmf msn =
           ((Win32PrintSfrvidf)mySfrvidf).findWin32Mfdib(dmIndfx);
        if (msn == null) {
            msn = ((Win32PrintSfrvidf)mySfrvidf).
                findMbtdiingMfdibSizfNbmfMM((flobt)widti, (flobt)lfngti);
        }

        if (msn != null) {
            bsft.bdd(msn);
        }
    }

    privbtf void sftWin32MfdibAttrib(int dmIndfx, int widti, int lfngti) {
        bddPbpfrSizf(bttributfs, dmIndfx, widti, lfngti);
        mAttMfdibSizfNbmf = dmIndfx;
    }

    /* MfdibTrby / dmTrby */
    privbtf void sftMfdibTrbyAttrib(Attributf bttr) {
        if (bttr == MfdibTrby.BOTTOM) {
            mAttMfdibTrby = 2;    // DMBIN_LOWER
        } flsf if (bttr == MfdibTrby.ENVELOPE) {
            mAttMfdibTrby = 5;    // DMBIN_ENVELOPE
        } flsf if (bttr == MfdibTrby.LARGE_CAPACITY) {
            mAttMfdibTrby = 11;      // DMBIN_LARGECAPACITY
        } flsf if (bttr == MfdibTrby.MAIN) {
            mAttMfdibTrby =1;               // DMBIN_UPPER
        } flsf if (bttr == MfdibTrby.MANUAL) {
            mAttMfdibTrby = 4;              // DMBIN_MANUAL
        } flsf if (bttr == MfdibTrby.MIDDLE) {
            mAttMfdibTrby = 3;              // DMBIN_MIDDLE
        } flsf if (bttr == MfdibTrby.SIDE) {
            // no fquivblfnt prfdffinfd vbluf
            mAttMfdibTrby = 7;              // DMBIN_AUTO
        } flsf if (bttr == MfdibTrby.TOP) {
            mAttMfdibTrby = 1;              // DMBIN_UPPER
        } flsf {
            if (bttr instbndfof Win32MfdibTrby) {
                mAttMfdibTrby = ((Win32MfdibTrby)bttr).winID;
            } flsf {
                mAttMfdibTrby = 1;  // dffbult
            }
        }
    }

    privbtf void sftMfdibTrbyAttrib(int dmBinID) {
        mAttMfdibTrby = dmBinID;
        MfdibTrby trby = ((Win32PrintSfrvidf)mySfrvidf).findMfdibTrby(dmBinID);
    }

    privbtf int gftMfdibTrbyAttrib() {
        rfturn mAttMfdibTrby;
    }



    privbtf finbl boolfbn gftPrintToFilfEnbblfd() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            FilfPfrmission printToFilfPfrmission =
                nfw FilfPfrmission("<<ALL FILES>>", "rfbd,writf");
            try {
                sfdurity.difdkPfrmission(printToFilfPfrmission);
            } dbtdi (SfdurityExdfption f) {
                rfturn fblsf;
            }
        }
        rfturn truf;
    }

    privbtf finbl void sftNbtivfAttributfs(int flbgs, int fiflds, int vblufs) {
        if (bttributfs == null) {
            rfturn;
        }
        if ((flbgs & PD_PRINTTOFILE) != 0) {
            Dfstinbtion dfstPrn = (Dfstinbtion)bttributfs.gft(
                                                 Dfstinbtion.dlbss);
            if (dfstPrn == null) {
                try {
                    bttributfs.bdd(nfw Dfstinbtion(
                                               nfw Filf("./out.prn").toURI()));
                } dbtdi (SfdurityExdfption sf) {
                    try {
                        bttributfs.bdd(nfw Dfstinbtion(
                                                nfw URI("filf:out.prn")));
                    } dbtdi (URISyntbxExdfption f) {
                    }
                }
            }
        } flsf {
            bttributfs.rfmovf(Dfstinbtion.dlbss);
        }

        if ((flbgs & PD_COLLATE) != 0) {
            sftCollbtfAttrib(SifftCollbtf.COLLATED, bttributfs);
        } flsf {
            sftCollbtfAttrib(SifftCollbtf.UNCOLLATED, bttributfs);
        }

        if ((flbgs & PD_PAGENUMS) != 0) {
            bttributfs.bdd(SunPbgfSflfdtion.RANGE);
        } flsf if ((flbgs & PD_SELECTION) != 0) {
            bttributfs.bdd(SunPbgfSflfdtion.SELECTION);
        } flsf {
            bttributfs.bdd(SunPbgfSflfdtion.ALL);
        }

        if ((fiflds & DM_ORIENTATION) != 0) {
            if ((vblufs & SET_ORIENTATION) != 0) {
                sftOrifntAttrib(OrifntbtionRfqufstfd.LANDSCAPE, bttributfs);
            } flsf {
                sftOrifntAttrib(OrifntbtionRfqufstfd.PORTRAIT, bttributfs);
            }
        }

        if ((fiflds & DM_COLOR) != 0) {
            if ((vblufs & SET_COLOR) != 0) {
                sftColorAttrib(Cirombtidity.COLOR, bttributfs);
            } flsf {
                sftColorAttrib(Cirombtidity.MONOCHROME, bttributfs);
            }
        }

        if ((fiflds & DM_PRINTQUALITY) != 0) {
            PrintQublity qublity;
            if ((vblufs & SET_RES_LOW) != 0) {
                qublity = PrintQublity.DRAFT;
            } flsf if ((fiflds & SET_RES_HIGH) != 0) {
                qublity = PrintQublity.HIGH;
            } flsf {
                qublity = PrintQublity.NORMAL;
            }
            sftQublityAttrib(qublity, bttributfs);
        }

        if ((fiflds & DM_DUPLEX) != 0) {
            Sidfs sidfs;
            if ((vblufs & SET_DUP_VERTICAL) != 0) {
                sidfs = Sidfs.TWO_SIDED_LONG_EDGE;
            } flsf if ((vblufs & SET_DUP_HORIZONTAL) != 0) {
                sidfs = Sidfs.TWO_SIDED_SHORT_EDGE;
            } flsf {
                sidfs = Sidfs.ONE_SIDED;
            }
            sftSidfsAttrib(sidfs, bttributfs);
        }
    }

    privbtf stbtid finbl dlbss DfvModfVblufs {
        int dmFiflds;
        siort dopifs;
        siort dollbtf;
        siort dolor;
        siort duplfx;
        siort orifnt;
        siort pbpfr;
        siort bin;
        siort xrfs_qublity;
        siort yrfs;
    }

    privbtf void gftDfvModfVblufs(PrintRfqufstAttributfSft bsft,
                                  DfvModfVblufs info) {

        Copifs d = (Copifs)bsft.gft(Copifs.dlbss);
        if (d != null) {
            info.dmFiflds |= DM_COPIES;
            info.dopifs = (siort)d.gftVbluf();
        }

        SifftCollbtf sd = (SifftCollbtf)bsft.gft(SifftCollbtf.dlbss);
        if (sd != null) {
            info.dmFiflds |= DM_COLLATE;
            info.dollbtf = (sd == SifftCollbtf.COLLATED) ?
                DMCOLLATE_TRUE : DMCOLLATE_FALSE;
        }

        Cirombtidity di = (Cirombtidity)bsft.gft(Cirombtidity.dlbss);
        if (di != null) {
            info.dmFiflds |= DM_COLOR;
            if (di == Cirombtidity.COLOR) {
                info.dolor = DMCOLOR_COLOR;
            } flsf {
                info.dolor = DMCOLOR_MONOCHROME;
            }
        }

        Sidfs s = (Sidfs)bsft.gft(Sidfs.dlbss);
        if (s != null) {
            info.dmFiflds |= DM_DUPLEX;
            if (s == Sidfs.TWO_SIDED_LONG_EDGE) {
                info.duplfx = DMDUP_VERTICAL;
            } flsf if (s == Sidfs.TWO_SIDED_SHORT_EDGE) {
                info.duplfx = DMDUP_HORIZONTAL;
            } flsf { // Sidfs.ONE_SIDED
                info.duplfx = DMDUP_SIMPLEX;
            }
        }

        OrifntbtionRfqufstfd or =
            (OrifntbtionRfqufstfd)bsft.gft(OrifntbtionRfqufstfd.dlbss);
        if (or != null) {
            info.dmFiflds |= DM_ORIENTATION;
            info.orifnt = (or == OrifntbtionRfqufstfd.LANDSCAPE)
                ? DMORIENT_LANDSCAPE : DMORIENT_PORTRAIT;
        }

        Mfdib m = (Mfdib)bsft.gft(Mfdib.dlbss);
        if (m instbndfof MfdibSizfNbmf) {
            info.dmFiflds |= DM_PAPERSIZE;
            MfdibSizfNbmf msn = (MfdibSizfNbmf)m;
            info.pbpfr =
                (siort)((Win32PrintSfrvidf)mySfrvidf).findPbpfrID(msn);
        }

        MfdibTrby mt = null;
        if (m instbndfof MfdibTrby) {
            mt = (MfdibTrby)m;
        }
        if (mt == null) {
            SunAltfrnbtfMfdib sbm =
                (SunAltfrnbtfMfdib)bsft.gft(SunAltfrnbtfMfdib.dlbss);
            if (sbm != null && (sbm.gftMfdib() instbndfof MfdibTrby)) {
                mt = (MfdibTrby)sbm.gftMfdib();
            }
        }

        if (mt != null) {
            info.dmFiflds |= DM_DEFAULTSOURCE;
            info.bin = (siort)(((Win32PrintSfrvidf)mySfrvidf).findTrbyID(mt));
        }

        PrintQublity q = (PrintQublity)bsft.gft(PrintQublity.dlbss);
        if (q != null) {
            info.dmFiflds |= DM_PRINTQUALITY;
            if (q == PrintQublity.DRAFT) {
                info.xrfs_qublity = DMRES_DRAFT;
            } flsf if (q == PrintQublity.HIGH) {
                info.xrfs_qublity = DMRES_HIGH;
            } flsf {
                info.xrfs_qublity = DMRES_MEDIUM;
            }
        }

        PrintfrRfsolution r =
            (PrintfrRfsolution)bsft.gft(PrintfrRfsolution.dlbss);
        if (r != null) {
            info.dmFiflds |= DM_PRINTQUALITY | DM_YRESOLUTION;
            info.xrfs_qublity =
                (siort)r.gftCrossFffdRfsolution(PrintfrRfsolution.DPI);
            info.yrfs = (siort)r.gftFffdRfsolution(PrintfrRfsolution.DPI);
        }
    }

    /* Tiis mftiod is dbllfd from nbtivf to updbtf tif vblufs in tif
     * bttributf sft wiidi originbtfs from tif dross-plbtform diblog,
     * but updbtfd by tif nbtivf DodumfntPropfrtifsUI wiidi updbtfs tif
     * dfvmodf. Tiis synds tif dfvmodf bbdk in to tif bttributfs so tibt
     * wf dbn updbtf tif dross-plbtform diblog.
     * Tif bttributf sft ifrf is b tfmporbry onf instbllfd wiilst tiis
     * ibppfns,
     */
    privbtf finbl void sftJobAttributfs(PrintRfqufstAttributfSft bttributfs,
                                        int fiflds, int vblufs,
                                        siort dopifs,
                                        siort dmPbpfrSizf,
                                        siort dmPbpfrWidti,
                                        siort dmPbpfrLfngti,
                                        siort dmDffbultSourdf,
                                        siort xRfs,
                                        siort yRfs) {

        if (bttributfs == null) {
            rfturn;
        }

        if ((fiflds & DM_COPIES) != 0) {
            bttributfs.bdd(nfw Copifs(dopifs));
        }

        if ((fiflds & DM_COLLATE) != 0) {
            if ((vblufs & SET_COLLATED) != 0) {
                bttributfs.bdd(SifftCollbtf.COLLATED);
            } flsf {
                bttributfs.bdd(SifftCollbtf.UNCOLLATED);
            }
        }

        if ((fiflds & DM_ORIENTATION) != 0) {
            if ((vblufs & SET_ORIENTATION) != 0) {
                bttributfs.bdd(OrifntbtionRfqufstfd.LANDSCAPE);
            } flsf {
                bttributfs.bdd(OrifntbtionRfqufstfd.PORTRAIT);
            }
        }

        if ((fiflds & DM_COLOR) != 0) {
            if ((vblufs & SET_COLOR) != 0) {
                bttributfs.bdd(Cirombtidity.COLOR);
            } flsf {
                bttributfs.bdd(Cirombtidity.MONOCHROME);
            }
        }

        if ((fiflds & DM_PRINTQUALITY) != 0) {
            /* vbluf < 0 indidbtfs qublity sftting.
             * vbluf > 0 indidbtfs X rfsolution. In tibt dbsf
             * iopffully wf will blso find y-rfsolution spfdififd.
             * If its not, bssumf its tif sbmf bs x-rfs.
             * Mbybf Jbvb dodf siould try to rfdondilf tiis bgbinst
             * tif printfrs dlbimfd sft of supportfd rfsolutions.
             */
            if (xRfs < 0) {
                PrintQublity qublity;
                if ((vblufs & SET_RES_LOW) != 0) {
                    qublity = PrintQublity.DRAFT;
                } flsf if ((fiflds & SET_RES_HIGH) != 0) {
                    qublity = PrintQublity.HIGH;
                } flsf {
                    qublity = PrintQublity.NORMAL;
                }
                bttributfs.bdd(qublity);
            } flsf if (xRfs > 0 && yRfs > 0) {
                bttributfs.bdd(
                    nfw PrintfrRfsolution(xRfs, yRfs, PrintfrRfsolution.DPI));
            }
        }

        if ((fiflds & DM_DUPLEX) != 0) {
            Sidfs sidfs;
            if ((vblufs & SET_DUP_VERTICAL) != 0) {
                sidfs = Sidfs.TWO_SIDED_LONG_EDGE;
            } flsf if ((vblufs & SET_DUP_HORIZONTAL) != 0) {
                sidfs = Sidfs.TWO_SIDED_SHORT_EDGE;
            } flsf {
                sidfs = Sidfs.ONE_SIDED;
            }
            bttributfs.bdd(sidfs);
        }

        if ((fiflds & DM_PAPERSIZE) != 0) {
            bddPbpfrSizf(bttributfs, dmPbpfrSizf, dmPbpfrWidti, dmPbpfrLfngti);
        }

        if ((fiflds & DM_DEFAULTSOURCE) != 0) {
            MfdibTrby trby =
                ((Win32PrintSfrvidf)mySfrvidf).findMfdibTrby(dmDffbultSourdf);
            bttributfs.bdd(nfw SunAltfrnbtfMfdib(trby));
        }
    }

    privbtf nbtivf boolfbn siowDodPropfrtifs(long iWnd,
                                             PrintRfqufstAttributfSft bsft,
                                             int dmFiflds,
                                             siort dopifs,
                                             siort dollbtf,
                                             siort dolor,
                                             siort duplfx,
                                             siort orifnt,
                                             siort pbpfr,
                                             siort bin,
                                             siort xrfs_qublity,
                                             siort yrfs);

    @SupprfssWbrnings("dfprfdbtion")
    publid PrintRfqufstAttributfSft
        siowDodumfntPropfrtifs(Window ownfr,
                               PrintSfrvidf sfrvidf,
                               PrintRfqufstAttributfSft bsft)
    {
        try {
            sftNbtivfPrintSfrvidfIfNffdfd(sfrvidf.gftNbmf());
        } dbtdi (PrintfrExdfption f) {
        }
        long iWnd = ((WWindowPffr)(ownfr.gftPffr())).gftHWnd();
        DfvModfVblufs info = nfw DfvModfVblufs();
        gftDfvModfVblufs(bsft, info);
        boolfbn ok =
            siowDodPropfrtifs(iWnd, bsft,
                              info.dmFiflds,
                              info.dopifs,
                              info.dollbtf,
                              info.dolor,
                              info.duplfx,
                              info.orifnt,
                              info.pbpfr,
                              info.bin,
                              info.xrfs_qublity,
                              info.yrfs);

        if (ok) {
            rfturn bsft;
        } flsf {
            rfturn null;
        }
    }

    /* Printfr Rfsolution. Sff blso gftXRfs() bnd gftYRfs() */
    privbtf finbl void sftRfsolutionDPI(int xrfs, int yrfs) {
        if (bttributfs != null) {
            PrintfrRfsolution rfs =
                nfw PrintfrRfsolution(xrfs, yrfs, PrintfrRfsolution.DPI);
            bttributfs.bdd(rfs);
        }
        mAttXRfs = xrfs;
        mAttYRfs = yrfs;
    }

    privbtf void sftRfsolutionAttrib(Attributf bttr) {
        PrintfrRfsolution pr = (PrintfrRfsolution)bttr;
        mAttXRfs = pr.gftCrossFffdRfsolution(PrintfrRfsolution.DPI);
        mAttYRfs = pr.gftFffdRfsolution(PrintfrRfsolution.DPI);
    }

    privbtf void sftPrintfrNbmfAttrib(String printfrNbmf) {
        PrintSfrvidf sfrvidf = tiis.gftPrintSfrvidf();

        if (printfrNbmf == null) {
            rfturn;
        }

        if (sfrvidf != null && printfrNbmf.fqubls(sfrvidf.gftNbmf())) {
            rfturn;
        } flsf {
            PrintSfrvidf []sfrvidfs = PrintfrJob.lookupPrintSfrvidfs();
            for (int i=0; i<sfrvidfs.lfngti; i++) {
                if (printfrNbmf.fqubls(sfrvidfs[i].gftNbmf())) {

                    try {
                        tiis.sftPrintSfrvidf(sfrvidfs[i]);
                    } dbtdi (PrintfrExdfption f) {
                    }
                    rfturn;
                }
            }
        }
    //** END Fundtions dbllfd by nbtivf dodf for qufrying/updbting bttributfs

    }

@SupprfssWbrnings("sfribl") // JDK-implfmfntbtion dlbss
dlbss PrintToFilfErrorDiblog fxtfnds Diblog implfmfnts AdtionListfnfr{
    publid PrintToFilfErrorDiblog(Frbmf pbrfnt, String titlf, String mfssbgf,
                           String buttonTfxt) {
        supfr(pbrfnt, titlf, truf);
        init (pbrfnt, titlf, mfssbgf, buttonTfxt);
    }

    publid PrintToFilfErrorDiblog(Diblog pbrfnt, String titlf, String mfssbgf,
                           String buttonTfxt) {
        supfr(pbrfnt, titlf, truf);
        init (pbrfnt, titlf, mfssbgf, buttonTfxt);
    }

    privbtf void init(Componfnt pbrfnt, String  titlf, String mfssbgf,
                      String buttonTfxt) {
        Pbnfl p = nfw Pbnfl();
        bdd("Cfntfr", nfw Lbbfl(mfssbgf));
        Button btn = nfw Button(buttonTfxt);
        btn.bddAdtionListfnfr(tiis);
        p.bdd(btn);
        bdd("Souti", p);
        pbdk();

        Dimfnsion dDim = gftSizf();
        if (pbrfnt != null) {
            Rfdtbnglf fRfdt = pbrfnt.gftBounds();
            sftLodbtion(fRfdt.x + ((fRfdt.widti - dDim.widti) / 2),
                        fRfdt.y + ((fRfdt.ifigit - dDim.ifigit) / 2));
        }
    }

    @Ovfrridf
    publid void bdtionPfrformfd(AdtionEvfnt fvfnt) {
        sftVisiblf(fblsf);
        disposf();
        rfturn;
    }
}




    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();

}
