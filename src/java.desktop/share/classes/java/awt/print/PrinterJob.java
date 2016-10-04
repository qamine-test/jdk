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

pbdkbgf jbvb.bwt.print;

import jbvb.bwt.AWTError;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.util.Enumfrbtion;

import jbvbx.print.DodFlbvor;
import jbvbx.print.PrintSfrvidf;
import jbvbx.print.PrintSfrvidfLookup;
import jbvbx.print.StrfbmPrintSfrvidfFbdtory;
import jbvbx.print.bttributf.PrintRfqufstAttributfSft;
import jbvbx.print.bttributf.stbndbrd.Mfdib;
import jbvbx.print.bttributf.stbndbrd.MfdibPrintbblfArfb;
import jbvbx.print.bttributf.stbndbrd.MfdibSizf;
import jbvbx.print.bttributf.stbndbrd.MfdibSizfNbmf;
import jbvbx.print.bttributf.stbndbrd.OrifntbtionRfqufstfd;

import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * Tif <dodf>PrintfrJob</dodf> dlbss is tif prindipbl dlbss tibt dontrols
 * printing. An bpplidbtion dblls mftiods in tiis dlbss to sft up b job,
 * optionblly to invokf b print diblog witi tif usfr, bnd tifn to print
 * tif pbgfs of tif job.
 */
publid bbstrbdt dlbss PrintfrJob {

 /* Publid Clbss Mftiods */

    /**
     * Crfbtfs bnd rfturns b <dodf>PrintfrJob</dodf> wiidi is initiblly
     * bssodibtfd witi tif dffbult printfr.
     * If no printfrs brf bvbilbblf on tif systfm, b PrintfrJob will still
     * bf rfturnfd from tiis mftiod, but <dodf>gftPrintSfrvidf()</dodf>
     * will rfturn <dodf>null</dodf>, bnd dblling
     * {@link #print() print} witi tiis <dodf>PrintfrJob</dodf> migit
     * gfnfrbtf bn fxdfption.  Applidbtions tibt nffd to dftfrminf if
     * tifrf brf suitbblf printfrs bfforf drfbting b <dodf>PrintfrJob</dodf>
     * siould fnsurf tibt tif brrby rfturnfd from
     * {@link #lookupPrintSfrvidfs() lookupPrintSfrvidfs} is not fmpty.
     * @rfturn b nfw <dodf>PrintfrJob</dodf>.
     *
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *          {@link jbvb.lbng.SfdurityMbnbgfr#difdkPrintJobAddfss}
     *          mftiod disbllows tiis tirfbd from drfbting b print job rfqufst
     */
    publid stbtid PrintfrJob gftPrintfrJob() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPrintJobAddfss();
        }
        rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<PrintfrJob>() {
            publid PrintfrJob run() {
                String nm = Systfm.gftPropfrty("jbvb.bwt.printfrjob", null);
                try {
                    rfturn (PrintfrJob)Clbss.forNbmf(nm).nfwInstbndf();
                } dbtdi (ClbssNotFoundExdfption f) {
                    tirow nfw AWTError("PrintfrJob not found: " + nm);
                } dbtdi (InstbntibtionExdfption f) {
                 tirow nfw AWTError("Could not instbntibtf PrintfrJob: " + nm);
                } dbtdi (IllfgblAddfssExdfption f) {
                    tirow nfw AWTError("Could not bddfss PrintfrJob: " + nm);
                }
            }
        });
    }

    /**
     * A donvfnifndf mftiod wiidi looks up 2D print sfrvidfs.
     * Sfrvidfs rfturnfd from tiis mftiod mby bf instbllfd on
     * <dodf>PrintfrJob</dodf>s wiidi support print sfrvidfs.
     * Cblling tiis mftiod is fquivblfnt to dblling
     * {@link jbvbx.print.PrintSfrvidfLookup#lookupPrintSfrvidfs(
     * DodFlbvor, AttributfSft)
     * PrintSfrvidfLookup.lookupPrintSfrvidfs()}
     * bnd spfdifying b Pbgfbblf DodFlbvor.
     * @rfturn b possibly fmpty brrby of 2D print sfrvidfs.
     * @sindf     1.4
     */
    publid stbtid PrintSfrvidf[] lookupPrintSfrvidfs() {
        rfturn PrintSfrvidfLookup.
            lookupPrintSfrvidfs(DodFlbvor.SERVICE_FORMATTED.PAGEABLE, null);
    }


    /**
     * A donvfnifndf mftiod wiidi lodbtfs fbdtorifs for strfbm print
     * sfrvidfs wiidi dbn imbgf 2D grbpiids.
     * Sbmplf usbgf :
     * <prf>{@dodf
     * FilfOutputStrfbm outstrfbm;
     * StrfbmPrintSfrvidf psPrintfr;
     * String psMimfTypf = "bpplidbtion/postsdript";
     * PrintfrJob pj = PrintfrJob.gftPrintfrJob();
     *
     * StrfbmPrintSfrvidfFbdtory[] fbdtorifs =
     *     PrintfrJob.lookupStrfbmPrintSfrvidfs(psMimfTypf);
     * if (fbdtorifs.lfngti > 0) {
     *     try {
     *         outstrfbm = nfw Filf("out.ps");
     *         psPrintfr =  fbdtorifs[0].gftPrintSfrvidf(outstrfbm);
     *         // psPrintfr dbn now bf sft bs tif sfrvidf on b PrintfrJob
     *         pj.sftPrintSfrvidf(psPrintfr)
     *     } dbtdi (Exdfption f) {
     *         f.printStbdkTrbdf();
     *     }
     * }
     * }</prf>
     * Sfrvidfs rfturnfd from tiis mftiod mby bf instbllfd on
     * <dodf>PrintfrJob</dodf> instbndfs wiidi support print sfrvidfs.
     * Cblling tiis mftiod is fquivblfnt to dblling
     * {@link jbvbx.print.StrfbmPrintSfrvidfFbdtory#lookupStrfbmPrintSfrvidfFbdtorifs(DodFlbvor, String)
     * StrfbmPrintSfrvidfFbdtory.lookupStrfbmPrintSfrvidfFbdtorifs()
     * } bnd spfdifying b Pbgfbblf DodFlbvor.
     *
     * @pbrbm mimfTypf tif rfquirfd output formbt, or null to mfbn bny formbt.
     * @rfturn b possibly fmpty brrby of 2D strfbm print sfrvidf fbdtorifs.
     * @sindf     1.4
     */
    publid stbtid StrfbmPrintSfrvidfFbdtory[]
        lookupStrfbmPrintSfrvidfs(String mimfTypf) {
        rfturn StrfbmPrintSfrvidfFbdtory.lookupStrfbmPrintSfrvidfFbdtorifs(
                                       DodFlbvor.SERVICE_FORMATTED.PAGEABLE,
                                       mimfTypf);
    }


 /* Publid Mftiods */

    /**
     * A <dodf>PrintfrJob</dodf> objfdt siould bf drfbtfd using tif
     * stbtid {@link #gftPrintfrJob() gftPrintfrJob} mftiod.
     */
    publid PrintfrJob() {
    }

    /**
     * Rfturns tif sfrvidf (printfr) for tiis printfr job.
     * Implfmfntbtions of tiis dlbss wiidi do not support print sfrvidfs
     * mby rfturn null.  null will blso bf rfturnfd if no printfrs brf
     * bvbilbblf.
     * @rfturn tif sfrvidf for tiis printfr job.
     * @sff #sftPrintSfrvidf(PrintSfrvidf)
     * @sff #gftPrintfrJob()
     * @sindf     1.4
     */
    publid PrintSfrvidf gftPrintSfrvidf() {
        rfturn null;
    }

    /**
     * Assodibtf tiis PrintfrJob witi b nfw PrintSfrvidf.
     * Tiis mftiod is ovfrriddfn by subdlbssfs wiidi support
     * spfdifying b Print Sfrvidf.
     *
     * Tirows <dodf>PrintfrExdfption</dodf> if tif spfdififd sfrvidf
     * dbnnot support tif <dodf>Pbgfbblf</dodf> bnd
     * <dodf>Printbblf</dodf> intfrfbdfs nfdfssbry to support 2D printing.
     * @pbrbm sfrvidf b print sfrvidf tibt supports 2D printing
     * @fxdfption PrintfrExdfption if tif spfdififd sfrvidf dofs not support
     * 2D printing, or tiis PrintfrJob dlbss dofs not support
     * sftting b 2D print sfrvidf, or tif spfdififd sfrvidf is
     * otifrwisf not b vblid print sfrvidf.
     * @sff #gftPrintSfrvidf
     * @sindf     1.4
     */
    publid void sftPrintSfrvidf(PrintSfrvidf sfrvidf)
        tirows PrintfrExdfption {
            tirow nfw PrintfrExdfption(
                         "Sftting b sfrvidf is not supportfd on tiis dlbss");
    }

    /**
     * Cblls <dodf>pbintfr</dodf> to rfndfr tif pbgfs.  Tif pbgfs in tif
     * dodumfnt to bf printfd by tiis
     * <dodf>PrintfrJob</dodf> brf rfndfrfd by tif {@link Printbblf}
     * objfdt, <dodf>pbintfr</dodf>.  Tif {@link PbgfFormbt} for fbdi pbgf
     * is tif dffbult pbgf formbt.
     * @pbrbm pbintfr tif <dodf>Printbblf</dodf> tibt rfndfrs fbdi pbgf of
     * tif dodumfnt.
     */
    publid bbstrbdt void sftPrintbblf(Printbblf pbintfr);

    /**
     * Cblls <dodf>pbintfr</dodf> to rfndfr tif pbgfs in tif spfdififd
     * <dodf>formbt</dodf>.  Tif pbgfs in tif dodumfnt to bf printfd by
     * tiis <dodf>PrintfrJob</dodf> brf rfndfrfd by tif
     * <dodf>Printbblf</dodf> objfdt, <dodf>pbintfr</dodf>. Tif
     * <dodf>PbgfFormbt</dodf> of fbdi pbgf is <dodf>formbt</dodf>.
     * @pbrbm pbintfr tif <dodf>Printbblf</dodf> dbllfd to rfndfr
     *          fbdi pbgf of tif dodumfnt
     * @pbrbm formbt tif sizf bnd orifntbtion of fbdi pbgf to
     *                   bf printfd
     */
    publid bbstrbdt void sftPrintbblf(Printbblf pbintfr, PbgfFormbt formbt);

    /**
     * Qufrifs <dodf>dodumfnt</dodf> for tif numbfr of pbgfs bnd
     * tif <dodf>PbgfFormbt</dodf> bnd <dodf>Printbblf</dodf> for fbdi
     * pbgf ifld in tif <dodf>Pbgfbblf</dodf> instbndf,
     * <dodf>dodumfnt</dodf>.
     * @pbrbm dodumfnt tif pbgfs to bf printfd. It dbn not bf
     * <dodf>null</dodf>.
     * @fxdfption NullPointfrExdfption tif <dodf>Pbgfbblf</dodf> pbssfd in
     * wbs <dodf>null</dodf>.
     * @sff PbgfFormbt
     * @sff Printbblf
     */
    publid bbstrbdt void sftPbgfbblf(Pbgfbblf dodumfnt)
        tirows NullPointfrExdfption;

    /**
     * Prfsfnts b diblog to tif usfr for dibnging tif propfrtifs of
     * tif print job.
     * Tiis mftiod will displby b nbtivf diblog if b nbtivf print
     * sfrvidf is sflfdtfd, bnd usfr dioidf of printfrs will bf rfstridtfd
     * to tifsf nbtivf print sfrvidfs.
     * To prfsfnt tif dross plbtform print diblog for bll sfrvidfs,
     * indluding nbtivf onfs instfbd usf
     * <dodf>printDiblog(PrintRfqufstAttributfSft)</dodf>.
     * <p>
     * PrintfrJob implfmfntbtions wiidi dbn usf PrintSfrvidf's will updbtf
     * tif PrintSfrvidf for tiis PrintfrJob to rfflfdt tif nfw sfrvidf
     * sflfdtfd by tif usfr.
     * @rfturn <dodf>truf</dodf> if tif usfr dofs not dbndfl tif diblog;
     * <dodf>fblsf</dodf> otifrwisf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid bbstrbdt boolfbn printDiblog() tirows HfbdlfssExdfption;

    /**
     * A donvfnifndf mftiod wiidi displbys b dross-plbtform print diblog
     * for bll sfrvidfs wiidi brf dbpbblf of printing 2D grbpiids using tif
     * <dodf>Pbgfbblf</dodf> intfrfbdf. Tif sflfdtfd printfr wifn tif
     * diblog is initiblly displbyfd will rfflfdt tif print sfrvidf durrfntly
     * bttbdifd to tiis print job.
     * If tif usfr dibngfs tif print sfrvidf, tif PrintfrJob will bf
     * updbtfd to rfflfdt tiis, unlfss tif usfr dbndfls tif diblog.
     * As wfll bs bllowing tif usfr to sflfdt tif dfstinbtion printfr,
     * tif usfr dbn blso sflfdt vblufs of vbrious print rfqufst bttributfs.
     * <p>
     * Tif bttributfs pbrbmftfr on input will rfflfdt tif bpplidbtions
     * rfquirfd initibl sflfdtions in tif usfr diblog. Attributfs not
     * spfdififd displby using tif dffbult for tif sfrvidf. On rfturn it
     * will rfflfdt tif usfr's dioidfs. Sflfdtions mby bf updbtfd by
     * tif implfmfntbtion to bf donsistfnt witi tif supportfd vblufs
     * for tif durrfntly sflfdtfd print sfrvidf.
     * <p>
     * As tif usfr sdrolls to b nfw print sfrvidf sflfdtion, tif vblufs
     * dopifd brf bbsfd on tif sfttings for tif prfvious sfrvidf, togftifr
     * witi bny usfr dibngfs. Tif vblufs brf not bbsfd on tif originbl
     * sfttings supplifd by tif dlifnt.
     * <p>
     * Witi tif fxdfption of sflfdtfd printfr, tif PrintfrJob stbtf is
     * not updbtfd to rfflfdt tif usfr's dibngfs.
     * For tif sflfdtions to bfffdt b printfr job, tif bttributfs must
     * bf spfdififd in tif dbll to tif
     * <dodf>print(PrintRfqufstAttributfSft)</dodf> mftiod. If using
     * tif Pbgfbblf intfrfbdf, dlifnts wiidi intfnd to usf mfdib sflfdtfd
     * by tif usfr must drfbtf b PbgfFormbt dfrivfd from tif usfr's
     * sflfdtions.
     * If tif usfr dbndfls tif diblog, tif bttributfs will not rfflfdt
     * bny dibngfs mbdf by tif usfr.
     * @pbrbm bttributfs on input is bpplidbtion supplifd bttributfs,
     * on output tif dontfnts brf updbtfd to rfflfdt usfr dioidfs.
     * Tiis pbrbmftfr mby not bf null.
     * @rfturn <dodf>truf</dodf> if tif usfr dofs not dbndfl tif diblog;
     * <dodf>fblsf</dodf> otifrwisf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @fxdfption NullPointfrExdfption if <dodf>bttributfs</dodf> pbrbmftfr
     * is null.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     *
     */
    publid boolfbn printDiblog(PrintRfqufstAttributfSft bttributfs)
        tirows HfbdlfssExdfption {

        if (bttributfs == null) {
            tirow nfw NullPointfrExdfption("bttributfs");
        }
        rfturn printDiblog();
    }

    /**
     * Displbys b diblog tibt bllows modifidbtion of b
     * <dodf>PbgfFormbt</dodf> instbndf.
     * Tif <dodf>pbgf</dodf> brgumfnt is usfd to initiblizf dontrols
     * in tif pbgf sftup diblog.
     * If tif usfr dbndfls tif diblog tifn tiis mftiod rfturns tif
     * originbl <dodf>pbgf</dodf> objfdt unmodififd.
     * If tif usfr okbys tif diblog tifn tiis mftiod rfturns b nfw
     * <dodf>PbgfFormbt</dodf> objfdt witi tif indidbtfd dibngfs.
     * In fitifr dbsf, tif originbl <dodf>pbgf</dodf> objfdt is
     * not modififd.
     * @pbrbm pbgf tif dffbult <dodf>PbgfFormbt</dodf> prfsfntfd to tif
     *                  usfr for modifidbtion
     * @rfturn    tif originbl <dodf>pbgf</dodf> objfdt if tif diblog
     *            is dbndfllfd; b nfw <dodf>PbgfFormbt</dodf> objfdt
     *            dontbining tif formbt indidbtfd by tif usfr if tif
     *            diblog is bdknowlfdgfd.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.2
     */
    publid bbstrbdt PbgfFormbt pbgfDiblog(PbgfFormbt pbgf)
        tirows HfbdlfssExdfption;

    /**
     * A donvfnifndf mftiod wiidi displbys b dross-plbtform pbgf sftup diblog.
     * Tif dioidfs bvbilbblf will rfflfdt tif print sfrvidf durrfntly
     * sft on tiis PrintfrJob.
     * <p>
     * Tif bttributfs pbrbmftfr on input will rfflfdt tif dlifnt's
     * rfquirfd initibl sflfdtions in tif usfr diblog. Attributfs wiidi brf
     * not spfdififd displby using tif dffbult for tif sfrvidf. On rfturn it
     * will rfflfdt tif usfr's dioidfs. Sflfdtions mby bf updbtfd by
     * tif implfmfntbtion to bf donsistfnt witi tif supportfd vblufs
     * for tif durrfntly sflfdtfd print sfrvidf.
     * <p>
     * Tif rfturn vbluf will bf b PbgfFormbt fquivblfnt to tif
     * sflfdtions in tif PrintRfqufstAttributfSft.
     * If tif usfr dbndfls tif diblog, tif bttributfs will not rfflfdt
     * bny dibngfs mbdf by tif usfr, bnd tif rfturn vbluf will bf null.
     * @pbrbm bttributfs on input is bpplidbtion supplifd bttributfs,
     * on output tif dontfnts brf updbtfd to rfflfdt usfr dioidfs.
     * Tiis pbrbmftfr mby not bf null.
     * @rfturn b pbgf formbt if tif usfr dofs not dbndfl tif diblog;
     * <dodf>null</dodf> otifrwisf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @fxdfption NullPointfrExdfption if <dodf>bttributfs</dodf> pbrbmftfr
     * is null.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     *
     */
    publid PbgfFormbt pbgfDiblog(PrintRfqufstAttributfSft bttributfs)
        tirows HfbdlfssExdfption {

        if (bttributfs == null) {
            tirow nfw NullPointfrExdfption("bttributfs");
        }
        rfturn pbgfDiblog(dffbultPbgf());
    }

    /**
     * Clonfs tif <dodf>PbgfFormbt</dodf> brgumfnt bnd bltfrs tif
     * dlonf to dfsdribf b dffbult pbgf sizf bnd orifntbtion.
     * @pbrbm pbgf tif <dodf>PbgfFormbt</dodf> to bf dlonfd bnd bltfrfd
     * @rfturn dlonf of <dodf>pbgf</dodf>, bltfrfd to dfsdribf b dffbult
     *                      <dodf>PbgfFormbt</dodf>.
     */
    publid bbstrbdt PbgfFormbt dffbultPbgf(PbgfFormbt pbgf);

    /**
     * Crfbtfs b nfw <dodf>PbgfFormbt</dodf> instbndf bnd
     * sfts it to b dffbult sizf bnd orifntbtion.
     * @rfturn b <dodf>PbgfFormbt</dodf> sft to b dffbult sizf bnd
     *          orifntbtion.
     */
    publid PbgfFormbt dffbultPbgf() {
        rfturn dffbultPbgf(nfw PbgfFormbt());
    }

    /**
     * Cbldulbtfs b <dodf>PbgfFormbt</dodf> witi vblufs donsistfnt witi tiosf
     * supportfd by tif durrfnt <dodf>PrintSfrvidf</dodf> for tiis job
     * (if tif vbluf rfturnfd by <dodf>gftPrintSfrvidf()</dodf>) bnd mfdib,
     * printbblf brfb bnd orifntbtion dontbinfd in <dodf>bttributfs</dodf>.
     * <p>
     * Cblling tiis mftiod dofs not updbtf tif job.
     * It is usfful for dlifnts tibt ibvf b sft of bttributfs obtbinfd from
     * <dodf>printDiblog(PrintRfqufstAttributfSft bttributfs)</dodf>
     * bnd nffd b PbgfFormbt to print b Pbgfbblf objfdt.
     * @pbrbm bttributfs b sft of printing bttributfs, for fxbmplf obtbinfd
     * from dblling printDiblog. If <dodf>bttributfs</dodf> is null b dffbult
     * PbgfFormbt is rfturnfd.
     * @rfturn b <dodf>PbgfFormbt</dodf> wiosf sfttings donform witi
     * tiosf of tif durrfnt sfrvidf bnd tif spfdififd bttributfs.
     * @sindf 1.6
     */
    publid PbgfFormbt gftPbgfFormbt(PrintRfqufstAttributfSft bttributfs) {

        PrintSfrvidf sfrvidf = gftPrintSfrvidf();
        PbgfFormbt pf = dffbultPbgf();

        if (sfrvidf == null || bttributfs == null) {
            rfturn pf;
        }

        Mfdib mfdib = (Mfdib)bttributfs.gft(Mfdib.dlbss);
        MfdibPrintbblfArfb mpb =
            (MfdibPrintbblfArfb)bttributfs.gft(MfdibPrintbblfArfb.dlbss);
        OrifntbtionRfqufstfd orifntRfq =
           (OrifntbtionRfqufstfd)bttributfs.gft(OrifntbtionRfqufstfd.dlbss);

        if (mfdib == null && mpb == null && orifntRfq == null) {
           rfturn pf;
        }
        Pbpfr pbpfr = pf.gftPbpfr();

        /* If tifrf's b mfdib but no mfdib printbblf brfb, wf dbn try
         * to rftrifvf tif dffbult vbluf for mpb bnd usf tibt.
         */
        if (mpb == null && mfdib != null &&
            sfrvidf.isAttributfCbtfgorySupportfd(MfdibPrintbblfArfb.dlbss)) {
            Objfdt mpbVbls =
                sfrvidf.gftSupportfdAttributfVblufs(MfdibPrintbblfArfb.dlbss,
                                                    null, bttributfs);
            if (mpbVbls instbndfof MfdibPrintbblfArfb[] &&
                ((MfdibPrintbblfArfb[])mpbVbls).lfngti > 0) {
                mpb = ((MfdibPrintbblfArfb[])mpbVbls)[0];
            }
        }

        if (mfdib != null &&
            sfrvidf.isAttributfVblufSupportfd(mfdib, null, bttributfs)) {
            if (mfdib instbndfof MfdibSizfNbmf) {
                MfdibSizfNbmf msn = (MfdibSizfNbmf)mfdib;
                MfdibSizf msz = MfdibSizf.gftMfdibSizfForNbmf(msn);
                if (msz != null) {
                    doublf indi = 72.0;
                    doublf pbpfrWid = msz.gftX(MfdibSizf.INCH) * indi;
                    doublf pbpfrHgt = msz.gftY(MfdibSizf.INCH) * indi;
                    pbpfr.sftSizf(pbpfrWid, pbpfrHgt);
                    if (mpb == null) {
                        pbpfr.sftImbgfbblfArfb(indi, indi,
                                               pbpfrWid-2*indi,
                                               pbpfrHgt-2*indi);
                    }
                }
            }
        }

        if (mpb != null &&
            sfrvidf.isAttributfVblufSupportfd(mpb, null, bttributfs)) {
            flobt [] printbblfArfb =
                mpb.gftPrintbblfArfb(MfdibPrintbblfArfb.INCH);
            for (int i=0; i < printbblfArfb.lfngti; i++) {
                printbblfArfb[i] = printbblfArfb[i]*72.0f;
            }
            pbpfr.sftImbgfbblfArfb(printbblfArfb[0], printbblfArfb[1],
                                   printbblfArfb[2], printbblfArfb[3]);
        }

        if (orifntRfq != null &&
            sfrvidf.isAttributfVblufSupportfd(orifntRfq, null, bttributfs)) {
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

        pf.sftPbpfr(pbpfr);
        pf = vblidbtfPbgf(pf);
        rfturn pf;
    }

    /**
     * Rfturns tif dlonf of <dodf>pbgf</dodf> witi its sfttings
     * bdjustfd to bf dompbtiblf witi tif durrfnt printfr of tiis
     * <dodf>PrintfrJob</dodf>.  For fxbmplf, tif rfturnfd
     * <dodf>PbgfFormbt</dodf> dould ibvf its imbgfbblf brfb
     * bdjustfd to fit witiin tif piysidbl brfb of tif pbpfr tibt
     * is usfd by tif durrfnt printfr.
     * @pbrbm pbgf tif <dodf>PbgfFormbt</dodf> tibt is dlonfd bnd
     *          wiosf sfttings brf dibngfd to bf dompbtiblf witi
     *          tif durrfnt printfr
     * @rfturn b <dodf>PbgfFormbt</dodf> tibt is dlonfd from
     *          <dodf>pbgf</dodf> bnd wiosf sfttings brf dibngfd
     *          to donform witi tiis <dodf>PrintfrJob</dodf>.
     */
    publid bbstrbdt PbgfFormbt vblidbtfPbgf(PbgfFormbt pbgf);

    /**
     * Prints b sft of pbgfs.
     * @fxdfption PrintfrExdfption bn frror in tif print systfm
     *            dbusfd tif job to bf bbortfd.
     * @sff Book
     * @sff Pbgfbblf
     * @sff Printbblf
     */
    publid bbstrbdt void print() tirows PrintfrExdfption;

   /**
     * Prints b sft of pbgfs using tif sfttings in tif bttributf
     * sft. Tif dffbult implfmfntbtion ignorfs tif bttributf sft.
     * <p>
     * Notf tibt somf bttributfs mby bf sft dirfdtly on tif PrintfrJob
     * by fquivblfnt mftiod dblls, (for fxbmplf), dopifs:
     * <dodf>sftdopifs(int)</dodf>, job nbmf: <dodf>sftJobNbmf(String)</dodf>
     * bnd spfdifying mfdib sizf bnd orifntbtion tiougi tif
     * <dodf>PbgfFormbt</dodf> objfdt.
     * <p>
     * If b supportfd bttributf-vbluf is spfdififd in tiis bttributf sft,
     * it will tbkf prfdfdfndf ovfr tif API sfttings for tiis print()
     * opfrbtion only.
     * Tif following bfibviour is spfdififd for PbgfFormbt:
     * If b dlifnt usfs tif Printbblf intfrfbdf, tifn tif
     * <dodf>bttributfs</dodf> pbrbmftfr to tiis mftiod is fxbminfd
     * for bttributfs wiidi spfdify mfdib (by sizf), orifntbtion, bnd
     * imbgfbblf brfb, bnd tiosf brf usfd to donstrudt b nfw PbgfFormbt
     * wiidi is pbssfd to tif Printbblf objfdt's print() mftiod.
     * Sff {@link Printbblf} for bn fxplbnbtion of tif rfquirfd
     * bfibviour of b Printbblf to fnsurf optimbl printing vib PrintfrJob.
     * For dlifnts of tif Pbgfbblf intfrfbdf, tif PbgfFormbt will blwbys
     * bf bs supplifd by tibt intfrfbdf, on b pfr pbgf bbsis.
     * <p>
     * Tifsf bfibviours bllow bn bpplidbtion to dirfdtly pbss tif
     * usfr sfttings rfturnfd from
     * <dodf>printDiblog(PrintRfqufstAttributfSft bttributfs</dodf> to
     * tiis print() mftiod.
     *
     * @pbrbm bttributfs b sft of bttributfs for tif job
     * @fxdfption PrintfrExdfption bn frror in tif print systfm
     *            dbusfd tif job to bf bbortfd.
     * @sff Book
     * @sff Pbgfbblf
     * @sff Printbblf
     * @sindf 1.4
     */
    publid void print(PrintRfqufstAttributfSft bttributfs)
        tirows PrintfrExdfption {
        print();
    }

    /**
     * Sfts tif numbfr of dopifs to bf printfd.
     * @pbrbm dopifs tif numbfr of dopifs to bf printfd
     * @sff #gftCopifs
     */
    publid bbstrbdt void sftCopifs(int dopifs);

    /**
     * Gfts tif numbfr of dopifs to bf printfd.
     * @rfturn tif numbfr of dopifs to bf printfd.
     * @sff #sftCopifs
     */
    publid bbstrbdt int gftCopifs();

    /**
     * Gfts tif nbmf of tif printing usfr.
     * @rfturn tif nbmf of tif printing usfr
     */
    publid bbstrbdt String gftUsfrNbmf();

    /**
     * Sfts tif nbmf of tif dodumfnt to bf printfd.
     * Tif dodumfnt nbmf dbn not bf <dodf>null</dodf>.
     * @pbrbm jobNbmf tif nbmf of tif dodumfnt to bf printfd
     * @sff #gftJobNbmf
     */
    publid bbstrbdt void sftJobNbmf(String jobNbmf);

    /**
     * Gfts tif nbmf of tif dodumfnt to bf printfd.
     * @rfturn tif nbmf of tif dodumfnt to bf printfd.
     * @sff #sftJobNbmf
     */
    publid bbstrbdt String gftJobNbmf();

    /**
     * Cbndfls b print job tibt is in progrfss.  If
     * {@link #print() print} ibs bffn dbllfd but ibs not
     * rfturnfd tifn tiis mftiod signbls
     * tibt tif job siould bf dbndfllfd bt tif nfxt
     * dibndf. If tifrf is no print job in progrfss tifn
     * tiis dbll dofs notiing.
     */
    publid bbstrbdt void dbndfl();

    /**
     * Rfturns <dodf>truf</dodf> if b print job is
     * in progrfss, but is going to bf dbndfllfd
     * bt tif nfxt opportunity; otifrwisf rfturns
     * <dodf>fblsf</dodf>.
     * @rfturn <dodf>truf</dodf> if tif job in progrfss
     * is going to bf dbndfllfd; <dodf>fblsf</dodf> otifrwisf.
     */
    publid bbstrbdt boolfbn isCbndfllfd();

}
