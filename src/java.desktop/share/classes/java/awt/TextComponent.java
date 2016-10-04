/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.pffr.TfxtComponfntPffr;
import jbvb.bwt.fvfnt.*;
import jbvb.util.EvfntListfnfr;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.tfxt.BrfbkItfrbtor;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.bddfssibility.*;
import jbvb.bwt.im.InputMftiodRfqufsts;
import sun.bwt.AWTPfrmissions;
import sun.bwt.InputMftiodSupport;

/**
 * Tif <dodf>TfxtComponfnt</dodf> dlbss is tif supfrdlbss of
 * bny domponfnt tibt bllows tif fditing of somf tfxt.
 * <p>
 * A tfxt domponfnt fmbodifs b string of tfxt.  Tif
 * <dodf>TfxtComponfnt</dodf> dlbss dffinfs b sft of mftiods
 * tibt dftfrminf wiftifr or not tiis tfxt is fditbblf. If tif
 * domponfnt is fditbblf, it dffinfs bnotifr sft of mftiods
 * tibt supports b tfxt insfrtion dbrft.
 * <p>
 * In bddition, tif dlbss dffinfs mftiods tibt brf usfd
 * to mbintbin b durrfnt <fm>sflfdtion</fm> from tif tfxt.
 * Tif tfxt sflfdtion, b substring of tif domponfnt's tfxt,
 * is tif tbrgft of fditing opfrbtions. It is blso rfffrrfd
 * to bs tif <fm>sflfdtfd tfxt</fm>.
 *
 * @butior      Sbmi Sibio
 * @butior      Artiur vbn Hoff
 * @sindf       1.0
 */
publid dlbss TfxtComponfnt fxtfnds Componfnt implfmfnts Addfssiblf {

    /**
     * Tif vbluf of tif tfxt.
     * A <dodf>null</dodf> vbluf is tif sbmf bs "".
     *
     * @sfribl
     * @sff #sftTfxt(String)
     * @sff #gftTfxt()
     */
    String tfxt;

    /**
     * A boolfbn indidbting wiftifr or not tiis
     * <dodf>TfxtComponfnt</dodf> is fditbblf.
     * It will bf <dodf>truf</dodf> if tif tfxt domponfnt
     * is fditbblf bnd <dodf>fblsf</dodf> if not.
     *
     * @sfribl
     * @sff #isEditbblf()
     */
    boolfbn fditbblf = truf;

    /**
     * Tif sflfdtion rfffrs to tif sflfdtfd tfxt, bnd tif
     * <dodf>sflfdtionStbrt</dodf> is tif stbrt position
     * of tif sflfdtfd tfxt.
     *
     * @sfribl
     * @sff #gftSflfdtionStbrt()
     * @sff #sftSflfdtionStbrt(int)
     */
    int sflfdtionStbrt;

    /**
     * Tif sflfdtion rfffrs to tif sflfdtfd tfxt, bnd tif
     * <dodf>sflfdtionEnd</dodf>
     * is tif fnd position of tif sflfdtfd tfxt.
     *
     * @sfribl
     * @sff #gftSflfdtionEnd()
     * @sff #sftSflfdtionEnd(int)
     */
    int sflfdtionEnd;

    // A flbg usfd to tfll wiftifr tif bbdkground ibs bffn sft by
    // dfvflopfr dodf (bs opposfd to AWT dodf).  Usfd to dftfrminf
    // tif bbdkground dolor of non-fditbblf TfxtComponfnts.
    boolfbn bbdkgroundSftByClifntCodf = fblsf;

    /**
     * A list of listfnfrs tibt will rfdfivf fvfnts from tiis objfdt.
     */
    trbnsifnt protfdtfd TfxtListfnfr tfxtListfnfr;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -2214773872412987419L;

    /**
     * Construdts b nfw tfxt domponfnt initiblizfd witi tif
     * spfdififd tfxt. Sfts tif vbluf of tif dursor to
     * <dodf>Cursor.TEXT_CURSOR</dodf>.
     * @pbrbm      tfxt       tif tfxt to bf displbyfd; if
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, tif fmpty
     *             string <dodf>""</dodf> will bf displbyfd
     * @fxdfption  HfbdlfssExdfption if
     *             <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *             rfturns truf
     * @sff        jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff        jbvb.bwt.Cursor
     */
    TfxtComponfnt(String tfxt) tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        tiis.tfxt = (tfxt != null) ? tfxt : "";
        sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.TEXT_CURSOR));
    }

    privbtf void fnbblfInputMftiodsIfNfdfssbry() {
        if (difdkForEnbblfIM) {
            difdkForEnbblfIM = fblsf;
            try {
                Toolkit toolkit = Toolkit.gftDffbultToolkit();
                boolfbn siouldEnbblf = fblsf;
                if (toolkit instbndfof InputMftiodSupport) {
                    siouldEnbblf = ((InputMftiodSupport)toolkit)
                      .fnbblfInputMftiodsForTfxtComponfnt();
                }
                fnbblfInputMftiods(siouldEnbblf);
            } dbtdi (Exdfption f) {
                // if somftiing bbd ibppfns, just don't fnbblf input mftiods
            }
        }
    }

    /**
     * Enbblfs or disbblfs input mftiod support for tiis tfxt domponfnt. If input
     * mftiod support is fnbblfd bnd tif tfxt domponfnt blso prodfssfs kfy fvfnts,
     * indoming fvfnts brf offfrfd to tif durrfnt input mftiod bnd will only bf
     * prodfssfd by tif domponfnt or dispbtdifd to its listfnfrs if tif input mftiod
     * dofs not donsumf tifm. Wiftifr bnd iow input mftiod support for tiis tfxt
     * domponfnt is fnbblfd or disbblfd by dffbult is implfmfntbtion dfpfndfnt.
     *
     * @pbrbm fnbblf truf to fnbblf, fblsf to disbblf
     * @sff #prodfssKfyEvfnt
     * @sindf 1.2
     */
    publid void fnbblfInputMftiods(boolfbn fnbblf) {
        difdkForEnbblfIM = fblsf;
        supfr.fnbblfInputMftiods(fnbblf);
    }

    boolfbn brfInputMftiodsEnbblfd() {
        // movfd from tif donstrudtor bbovf to ifrf bnd bddNotify bflow,
        // tiis dbll will initiblizf tif toolkit if not blrfbdy initiblizfd.
        if (difdkForEnbblfIM) {
            fnbblfInputMftiodsIfNfdfssbry();
        }

        // TfxtComponfnt ibndlfs kfy fvfnts witiout toudiing tif fvfntMbsk or
        // ibving b kfy listfnfr, so just difdk wiftifr tif flbg is sft
        rfturn (fvfntMbsk & AWTEvfnt.INPUT_METHODS_ENABLED_MASK) != 0;
    }

    publid InputMftiodRfqufsts gftInputMftiodRfqufsts() {
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) rfturn pffr.gftInputMftiodRfqufsts();
        flsf rfturn null;
    }



    /**
     * Mbkfs tiis Componfnt displbybblf by donnfdting it to b
     * nbtivf sdrffn rfsourdf.
     * Tiis mftiod is dbllfd intfrnblly by tif toolkit bnd siould
     * not bf dbllfd dirfdtly by progrbms.
     * @sff       jbvb.bwt.TfxtComponfnt#rfmovfNotify
     */
    publid void bddNotify() {
        supfr.bddNotify();
        fnbblfInputMftiodsIfNfdfssbry();
    }

    /**
     * Rfmovfs tif <dodf>TfxtComponfnt</dodf>'s pffr.
     * Tif pffr bllows us to modify tif bppfbrbndf of tif
     * <dodf>TfxtComponfnt</dodf> witiout dibnging its
     * fundtionblity.
     */
    publid void rfmovfNotify() {
        syndironizfd (gftTrffLodk()) {
            TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
            if (pffr != null) {
                tfxt = pffr.gftTfxt();
                sflfdtionStbrt = pffr.gftSflfdtionStbrt();
                sflfdtionEnd = pffr.gftSflfdtionEnd();
            }
            supfr.rfmovfNotify();
        }
    }

    /**
     * Sfts tif tfxt tibt is prfsfntfd by tiis
     * tfxt domponfnt to bf tif spfdififd tfxt.
     * @pbrbm       t   tif nfw tfxt;
     *                  if tiis pbrbmftfr is <dodf>null</dodf> tifn
     *                  tif tfxt is sft to tif fmpty string ""
     * @sff         jbvb.bwt.TfxtComponfnt#gftTfxt
     */
    publid syndironizfd void sftTfxt(String t) {
        boolfbn skipTfxtEvfnt = (tfxt == null || tfxt.isEmpty())
                && (t == null || t.isEmpty());
        tfxt = (t != null) ? t : "";
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        // Plfbsf notf tibt wf do not wbnt to post bn fvfnt
        // if TfxtArfb.sftTfxt() or TfxtFifld.sftTfxt() rfplbdfs bn fmpty tfxt
        // by bn fmpty tfxt, tibt is, if domponfnt's tfxt rfmbins undibngfd.
        if (pffr != null && !skipTfxtEvfnt) {
            pffr.sftTfxt(tfxt);
        }
    }

    /**
     * Rfturns tif tfxt tibt is prfsfntfd by tiis tfxt domponfnt.
     * By dffbult, tiis is bn fmpty string.
     *
     * @rfturn tif vbluf of tiis <dodf>TfxtComponfnt</dodf>
     * @sff     jbvb.bwt.TfxtComponfnt#sftTfxt
     */
    publid syndironizfd String gftTfxt() {
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            tfxt = pffr.gftTfxt();
        }
        rfturn tfxt;
    }

    /**
     * Rfturns tif sflfdtfd tfxt from tif tfxt tibt is
     * prfsfntfd by tiis tfxt domponfnt.
     * @rfturn      tif sflfdtfd tfxt of tiis tfxt domponfnt
     * @sff         jbvb.bwt.TfxtComponfnt#sflfdt
     */
    publid syndironizfd String gftSflfdtfdTfxt() {
        rfturn gftTfxt().substring(gftSflfdtionStbrt(), gftSflfdtionEnd());
    }

    /**
     * Indidbtfs wiftifr or not tiis tfxt domponfnt is fditbblf.
     * @rfturn     <dodf>truf</dodf> if tiis tfxt domponfnt is
     *                  fditbblf; <dodf>fblsf</dodf> otifrwisf.
     * @sff        jbvb.bwt.TfxtComponfnt#sftEditbblf
     * @sindf      1.0
     */
    publid boolfbn isEditbblf() {
        rfturn fditbblf;
    }

    /**
     * Sfts tif flbg tibt dftfrminfs wiftifr or not tiis
     * tfxt domponfnt is fditbblf.
     * <p>
     * If tif flbg is sft to <dodf>truf</dodf>, tiis tfxt domponfnt
     * bfdomfs usfr fditbblf. If tif flbg is sft to <dodf>fblsf</dodf>,
     * tif usfr dbnnot dibngf tif tfxt of tiis tfxt domponfnt.
     * By dffbult, non-fditbblf tfxt domponfnts ibvf b bbdkground dolor
     * of SystfmColor.dontrol.  Tiis dffbult dbn bf ovfrriddfn by
     * dblling sftBbdkground.
     *
     * @pbrbm     b   b flbg indidbting wiftifr tiis tfxt domponfnt
     *                      is usfr fditbblf.
     * @sff       jbvb.bwt.TfxtComponfnt#isEditbblf
     * @sindf     1.0
     */
    publid syndironizfd void sftEditbblf(boolfbn b) {
        if (fditbblf == b) {
            rfturn;
        }

        fditbblf = b;
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftEditbblf(b);
        }
    }

    /**
     * Gfts tif bbdkground dolor of tiis tfxt domponfnt.
     *
     * By dffbult, non-fditbblf tfxt domponfnts ibvf b bbdkground dolor
     * of SystfmColor.dontrol.  Tiis dffbult dbn bf ovfrriddfn by
     * dblling sftBbdkground.
     *
     * @rfturn Tiis tfxt domponfnt's bbdkground dolor.
     *         If tiis tfxt domponfnt dofs not ibvf b bbdkground dolor,
     *         tif bbdkground dolor of its pbrfnt is rfturnfd.
     * @sff #sftBbdkground(Color)
     * @sindf 1.0
     */
    publid Color gftBbdkground() {
        if (!fditbblf && !bbdkgroundSftByClifntCodf) {
            rfturn SystfmColor.dontrol;
        }

        rfturn supfr.gftBbdkground();
    }

    /**
     * Sfts tif bbdkground dolor of tiis tfxt domponfnt.
     *
     * @pbrbm d Tif dolor to bfdomf tiis tfxt domponfnt's dolor.
     *        If tiis pbrbmftfr is null tifn tiis tfxt domponfnt
     *        will inifrit tif bbdkground dolor of its pbrfnt.
     * @sff #gftBbdkground()
     * @sindf 1.0
     */
    publid void sftBbdkground(Color d) {
        bbdkgroundSftByClifntCodf = truf;
        supfr.sftBbdkground(d);
    }

    /**
     * Gfts tif stbrt position of tif sflfdtfd tfxt in
     * tiis tfxt domponfnt.
     * @rfturn      tif stbrt position of tif sflfdtfd tfxt
     * @sff         jbvb.bwt.TfxtComponfnt#sftSflfdtionStbrt
     * @sff         jbvb.bwt.TfxtComponfnt#gftSflfdtionEnd
     */
    publid syndironizfd int gftSflfdtionStbrt() {
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            sflfdtionStbrt = pffr.gftSflfdtionStbrt();
        }
        rfturn sflfdtionStbrt;
    }

    /**
     * Sfts tif sflfdtion stbrt for tiis tfxt domponfnt to
     * tif spfdififd position. Tif nfw stbrt point is donstrbinfd
     * to bf bt or bfforf tif durrfnt sflfdtion fnd. It blso
     * dbnnot bf sft to lfss tibn zfro, tif bfginning of tif
     * domponfnt's tfxt.
     * If tif dbllfr supplifs b vbluf for <dodf>sflfdtionStbrt</dodf>
     * tibt is out of bounds, tif mftiod fnfordfs tifsf donstrbints
     * silfntly, bnd witiout fbilurf.
     * @pbrbm       sflfdtionStbrt   tif stbrt position of tif
     *                        sflfdtfd tfxt
     * @sff         jbvb.bwt.TfxtComponfnt#gftSflfdtionStbrt
     * @sff         jbvb.bwt.TfxtComponfnt#sftSflfdtionEnd
     * @sindf       1.1
     */
    publid syndironizfd void sftSflfdtionStbrt(int sflfdtionStbrt) {
        /* Routf tirougi sflfdt mftiod to fnfordf donsistfnt polidy
         * bftwffn sflfdtionStbrt bnd sflfdtionEnd.
         */
        sflfdt(sflfdtionStbrt, gftSflfdtionEnd());
    }

    /**
     * Gfts tif fnd position of tif sflfdtfd tfxt in
     * tiis tfxt domponfnt.
     * @rfturn      tif fnd position of tif sflfdtfd tfxt
     * @sff         jbvb.bwt.TfxtComponfnt#sftSflfdtionEnd
     * @sff         jbvb.bwt.TfxtComponfnt#gftSflfdtionStbrt
     */
    publid syndironizfd int gftSflfdtionEnd() {
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            sflfdtionEnd = pffr.gftSflfdtionEnd();
        }
        rfturn sflfdtionEnd;
    }

    /**
     * Sfts tif sflfdtion fnd for tiis tfxt domponfnt to
     * tif spfdififd position. Tif nfw fnd point is donstrbinfd
     * to bf bt or bftfr tif durrfnt sflfdtion stbrt. It blso
     * dbnnot bf sft bfyond tif fnd of tif domponfnt's tfxt.
     * If tif dbllfr supplifs b vbluf for <dodf>sflfdtionEnd</dodf>
     * tibt is out of bounds, tif mftiod fnfordfs tifsf donstrbints
     * silfntly, bnd witiout fbilurf.
     * @pbrbm       sflfdtionEnd   tif fnd position of tif
     *                        sflfdtfd tfxt
     * @sff         jbvb.bwt.TfxtComponfnt#gftSflfdtionEnd
     * @sff         jbvb.bwt.TfxtComponfnt#sftSflfdtionStbrt
     * @sindf       1.1
     */
    publid syndironizfd void sftSflfdtionEnd(int sflfdtionEnd) {
        /* Routf tirougi sflfdt mftiod to fnfordf donsistfnt polidy
         * bftwffn sflfdtionStbrt bnd sflfdtionEnd.
         */
        sflfdt(gftSflfdtionStbrt(), sflfdtionEnd);
    }

    /**
     * Sflfdts tif tfxt bftwffn tif spfdififd stbrt bnd fnd positions.
     * <p>
     * Tiis mftiod sfts tif stbrt bnd fnd positions of tif
     * sflfdtfd tfxt, fnfording tif rfstridtion tibt tif stbrt position
     * must bf grfbtfr tibn or fqubl to zfro.  Tif fnd position must bf
     * grfbtfr tibn or fqubl to tif stbrt position, bnd lfss tibn or
     * fqubl to tif lfngti of tif tfxt domponfnt's tfxt.  Tif
     * dibrbdtfr positions brf indfxfd stbrting witi zfro.
     * Tif lfngti of tif sflfdtion is
     * <dodf>fndPosition</dodf> - <dodf>stbrtPosition</dodf>, so tif
     * dibrbdtfr bt <dodf>fndPosition</dodf> is not sflfdtfd.
     * If tif stbrt bnd fnd positions of tif sflfdtfd tfxt brf fqubl,
     * bll tfxt is dfsflfdtfd.
     * <p>
     * If tif dbllfr supplifs vblufs tibt brf indonsistfnt or out of
     * bounds, tif mftiod fnfordfs tifsf donstrbints silfntly, bnd
     * witiout fbilurf. Spfdifidblly, if tif stbrt position or fnd
     * position is grfbtfr tibn tif lfngti of tif tfxt, it is rfsft to
     * fqubl tif tfxt lfngti. If tif stbrt position is lfss tibn zfro,
     * it is rfsft to zfro, bnd if tif fnd position is lfss tibn tif
     * stbrt position, it is rfsft to tif stbrt position.
     *
     * @pbrbm        sflfdtionStbrt tif zfro-bbsfd indfx of tif first
                       dibrbdtfr (<dodf>dibr</dodf> vbluf) to bf sflfdtfd
     * @pbrbm        sflfdtionEnd tif zfro-bbsfd fnd position of tif
                       tfxt to bf sflfdtfd; tif dibrbdtfr (<dodf>dibr</dodf> vbluf) bt
                       <dodf>sflfdtionEnd</dodf> is not sflfdtfd
     * @sff          jbvb.bwt.TfxtComponfnt#sftSflfdtionStbrt
     * @sff          jbvb.bwt.TfxtComponfnt#sftSflfdtionEnd
     * @sff          jbvb.bwt.TfxtComponfnt#sflfdtAll
     */
    publid syndironizfd void sflfdt(int sflfdtionStbrt, int sflfdtionEnd) {
        String tfxt = gftTfxt();
        if (sflfdtionStbrt < 0) {
            sflfdtionStbrt = 0;
        }
        if (sflfdtionStbrt > tfxt.lfngti()) {
            sflfdtionStbrt = tfxt.lfngti();
        }
        if (sflfdtionEnd > tfxt.lfngti()) {
            sflfdtionEnd = tfxt.lfngti();
        }
        if (sflfdtionEnd < sflfdtionStbrt) {
            sflfdtionEnd = sflfdtionStbrt;
        }

        tiis.sflfdtionStbrt = sflfdtionStbrt;
        tiis.sflfdtionEnd = sflfdtionEnd;

        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sflfdt(sflfdtionStbrt, sflfdtionEnd);
        }
    }

    /**
     * Sflfdts bll tif tfxt in tiis tfxt domponfnt.
     * @sff        jbvb.bwt.TfxtComponfnt#sflfdt
     */
    publid syndironizfd void sflfdtAll() {
        tiis.sflfdtionStbrt = 0;
        tiis.sflfdtionEnd = gftTfxt().lfngti();

        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sflfdt(sflfdtionStbrt, sflfdtionEnd);
        }
    }

    /**
     * Sfts tif position of tif tfxt insfrtion dbrft.
     * Tif dbrft position is donstrbinfd to bf bftwffn 0
     * bnd tif lbst dibrbdtfr of tif tfxt, indlusivf.
     * If tif pbssfd-in vbluf is grfbtfr tibn tiis rbngf,
     * tif vbluf is sft to tif lbst dibrbdtfr (or 0 if
     * tif <dodf>TfxtComponfnt</dodf> dontbins no tfxt)
     * bnd no frror is rfturnfd.  If tif pbssfd-in vbluf is
     * lfss tibn 0, bn <dodf>IllfgblArgumfntExdfption</dodf>
     * is tirown.
     *
     * @pbrbm        position tif position of tif tfxt insfrtion dbrft
     * @fxdfption    IllfgblArgumfntExdfption if <dodf>position</dodf>
     *               is lfss tibn zfro
     * @sindf        1.1
     */
    publid syndironizfd void sftCbrftPosition(int position) {
        if (position < 0) {
            tirow nfw IllfgblArgumfntExdfption("position lfss tibn zfro.");
        }

        int mbxposition = gftTfxt().lfngti();
        if (position > mbxposition) {
            position = mbxposition;
        }

        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            pffr.sftCbrftPosition(position);
        } flsf {
            sflfdt(position, position);
        }
    }

    /**
     * Rfturns tif position of tif tfxt insfrtion dbrft.
     * Tif dbrft position is donstrbinfd to bf bftwffn 0
     * bnd tif lbst dibrbdtfr of tif tfxt, indlusivf.
     * If tif tfxt or dbrft ibvf not bffn sft, tif dffbult
     * dbrft position is 0.
     *
     * @rfturn       tif position of tif tfxt insfrtion dbrft
     * @sff #sftCbrftPosition(int)
     * @sindf        1.1
     */
    publid syndironizfd int gftCbrftPosition() {
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        int position = 0;

        if (pffr != null) {
            position = pffr.gftCbrftPosition();
        } flsf {
            position = sflfdtionStbrt;
        }
        int mbxposition = gftTfxt().lfngti();
        if (position > mbxposition) {
            position = mbxposition;
        }
        rfturn position;
    }

    /**
     * Adds tif spfdififd tfxt fvfnt listfnfr to rfdfivf tfxt fvfnts
     * from tiis tfxt domponfnt.
     * If <dodf>l</dodf> is <dodf>null</dodf>, no fxdfption is
     * tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm l tif tfxt fvfnt listfnfr
     * @sff             #rfmovfTfxtListfnfr
     * @sff             #gftTfxtListfnfrs
     * @sff             jbvb.bwt.fvfnt.TfxtListfnfr
     */
    publid syndironizfd void bddTfxtListfnfr(TfxtListfnfr l) {
        if (l == null) {
            rfturn;
        }
        tfxtListfnfr = AWTEvfntMultidbstfr.bdd(tfxtListfnfr, l);
        nfwEvfntsOnly = truf;
    }

    /**
     * Rfmovfs tif spfdififd tfxt fvfnt listfnfr so tibt it no longfr
     * rfdfivfs tfxt fvfnts from tiis tfxt domponfnt
     * If <dodf>l</dodf> is <dodf>null</dodf>, no fxdfption is
     * tirown bnd no bdtion is pfrformfd.
     * <p>Rfffr to <b irff="dod-filfs/AWTTirfbdIssufs.itml#ListfnfrsTirfbds"
     * >AWT Tirfbding Issufs</b> for dftbils on AWT's tirfbding modfl.
     *
     * @pbrbm           l     tif tfxt listfnfr
     * @sff             #bddTfxtListfnfr
     * @sff             #gftTfxtListfnfrs
     * @sff             jbvb.bwt.fvfnt.TfxtListfnfr
     * @sindf           1.1
     */
    publid syndironizfd void rfmovfTfxtListfnfr(TfxtListfnfr l) {
        if (l == null) {
            rfturn;
        }
        tfxtListfnfr = AWTEvfntMultidbstfr.rfmovf(tfxtListfnfr, l);
    }

    /**
     * Rfturns bn brrby of bll tif tfxt listfnfrs
     * rfgistfrfd on tiis tfxt domponfnt.
     *
     * @rfturn bll of tiis tfxt domponfnt's <dodf>TfxtListfnfr</dodf>s
     *         or bn fmpty brrby if no tfxt
     *         listfnfrs brf durrfntly rfgistfrfd
     *
     *
     * @sff #bddTfxtListfnfr
     * @sff #rfmovfTfxtListfnfr
     * @sindf 1.4
     */
    publid syndironizfd TfxtListfnfr[] gftTfxtListfnfrs() {
        rfturn gftListfnfrs(TfxtListfnfr.dlbss);
    }

    /**
     * Rfturns bn brrby of bll tif objfdts durrfntly rfgistfrfd
     * bs <dodf><fm>Foo</fm>Listfnfr</dodf>s
     * upon tiis <dodf>TfxtComponfnt</dodf>.
     * <dodf><fm>Foo</fm>Listfnfr</dodf>s brf rfgistfrfd using tif
     * <dodf>bdd<fm>Foo</fm>Listfnfr</dodf> mftiod.
     *
     * <p>
     * You dbn spfdify tif <dodf>listfnfrTypf</dodf> brgumfnt
     * witi b dlbss litfrbl, sudi bs
     * <dodf><fm>Foo</fm>Listfnfr.dlbss</dodf>.
     * For fxbmplf, you dbn qufry b
     * <dodf>TfxtComponfnt</dodf> <dodf>t</dodf>
     * for its tfxt listfnfrs witi tif following dodf:
     *
     * <prf>TfxtListfnfr[] tls = (TfxtListfnfr[])(t.gftListfnfrs(TfxtListfnfr.dlbss));</prf>
     *
     * If no sudi listfnfrs fxist, tiis mftiod rfturns bn fmpty brrby.
     *
     * @pbrbm listfnfrTypf tif typf of listfnfrs rfqufstfd; tiis pbrbmftfr
     *          siould spfdify bn intfrfbdf tibt dfsdfnds from
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     * @rfturn bn brrby of bll objfdts rfgistfrfd bs
     *          <dodf><fm>Foo</fm>Listfnfr</dodf>s on tiis tfxt domponfnt,
     *          or bn fmpty brrby if no sudi
     *          listfnfrs ibvf bffn bddfd
     * @fxdfption ClbssCbstExdfption if <dodf>listfnfrTypf</dodf>
     *          dofsn't spfdify b dlbss or intfrfbdf tibt implfmfnts
     *          <dodf>jbvb.util.EvfntListfnfr</dodf>
     *
     * @sff #gftTfxtListfnfrs
     * @sindf 1.3
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        EvfntListfnfr l = null;
        if  (listfnfrTypf == TfxtListfnfr.dlbss) {
            l = tfxtListfnfr;
        } flsf {
            rfturn supfr.gftListfnfrs(listfnfrTypf);
        }
        rfturn AWTEvfntMultidbstfr.gftListfnfrs(l, listfnfrTypf);
    }

    // REMIND: rfmovf wifn filtfring is donf bt lowfr lfvfl
    boolfbn fvfntEnbblfd(AWTEvfnt f) {
        if (f.id == TfxtEvfnt.TEXT_VALUE_CHANGED) {
            if ((fvfntMbsk & AWTEvfnt.TEXT_EVENT_MASK) != 0 ||
                tfxtListfnfr != null) {
                rfturn truf;
            }
            rfturn fblsf;
        }
        rfturn supfr.fvfntEnbblfd(f);
    }

    /**
     * Prodfssfs fvfnts on tiis tfxt domponfnt. If tif fvfnt is b
     * <dodf>TfxtEvfnt</dodf>, it invokfs tif <dodf>prodfssTfxtEvfnt</dodf>
     * mftiod flsf it invokfs its supfrdlbss's <dodf>prodfssEvfnt</dodf>.
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif fvfnt
     */
    protfdtfd void prodfssEvfnt(AWTEvfnt f) {
        if (f instbndfof TfxtEvfnt) {
            prodfssTfxtEvfnt((TfxtEvfnt)f);
            rfturn;
        }
        supfr.prodfssEvfnt(f);
    }

    /**
     * Prodfssfs tfxt fvfnts oddurring on tiis tfxt domponfnt by
     * dispbtdiing tifm to bny rfgistfrfd <dodf>TfxtListfnfr</dodf> objfdts.
     * <p>
     * NOTE: Tiis mftiod will not bf dbllfd unlfss tfxt fvfnts
     * brf fnbblfd for tiis domponfnt. Tiis ibppfns wifn onf of tif
     * following oddurs:
     * <ul>
     * <li>A <dodf>TfxtListfnfr</dodf> objfdt is rfgistfrfd
     * vib <dodf>bddTfxtListfnfr</dodf>
     * <li>Tfxt fvfnts brf fnbblfd vib <dodf>fnbblfEvfnts</dodf>
     * </ul>
     * <p>Notf tibt if tif fvfnt pbrbmftfr is <dodf>null</dodf>
     * tif bfibvior is unspfdififd bnd mby rfsult in bn
     * fxdfption.
     *
     * @pbrbm f tif tfxt fvfnt
     * @sff Componfnt#fnbblfEvfnts
     */
    protfdtfd void prodfssTfxtEvfnt(TfxtEvfnt f) {
        TfxtListfnfr listfnfr = tfxtListfnfr;
        if (listfnfr != null) {
            int id = f.gftID();
            switdi (id) {
            dbsf TfxtEvfnt.TEXT_VALUE_CHANGED:
                listfnfr.tfxtVblufCibngfd(f);
                brfbk;
            }
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis
     * <dodf>TfxtComponfnt</dodf>. Tiis
     * mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn      tif pbrbmftfr string of tiis tfxt domponfnt
     */
    protfdtfd String pbrbmString() {
        String str = supfr.pbrbmString() + ",tfxt=" + gftTfxt();
        if (fditbblf) {
            str += ",fditbblf";
        }
        rfturn str + ",sflfdtion=" + gftSflfdtionStbrt() + "-" + gftSflfdtionEnd();
    }

    /**
     * Assigns b vblid vbluf to tif dbnAddfssClipbobrd instbndf vbribblf.
     */
    privbtf boolfbn dbnAddfssClipbobrd() {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm == null) rfturn truf;
        try {
            sm.difdkPfrmission(AWTPfrmissions.ACCESS_CLIPBOARD_PERMISSION);
            rfturn truf;
        } dbtdi (SfdurityExdfption f) {}
        rfturn fblsf;
    }

    /*
     * Sfriblizbtion support.
     */
    /**
     * Tif tfxtComponfnt SfriblizfdDbtbVfrsion.
     *
     * @sfribl
     */
    privbtf int tfxtComponfntSfriblizfdDbtbVfrsion = 1;

    /**
     * Writfs dffbult sfriblizbblf fiflds to strfbm.  Writfs
     * b list of sfriblizbblf TfxtListfnfr(s) bs optionbl dbtb.
     * Tif non-sfriblizbblf TfxtListfnfr(s) brf dftfdtfd bnd
     * no bttfmpt is mbdf to sfriblizf tifm.
     *
     * @sfriblDbtb Null tfrminbtfd sfqufndf of zfro or morf pbirs.
     *             A pbir donsists of b String bnd Objfdt.
     *             Tif String indidbtfs tif typf of objfdt bnd
     *             is onf of tif following :
     *             tfxtListfnfrK indidbting bnd TfxtListfnfr objfdt.
     *
     * @sff AWTEvfntMultidbstfr#sbvf(ObjfdtOutputStrfbm, String, EvfntListfnfr)
     * @sff jbvb.bwt.Componfnt#tfxtListfnfrK
     */
    privbtf void writfObjfdt(jbvb.io.ObjfdtOutputStrfbm s)
      tirows IOExdfption
    {
        // Sfriblizbtion support.  Sindf tif vbluf of tif fiflds
        // sflfdtionStbrt, sflfdtionEnd, bnd tfxt brfn't nfdfssbrily
        // up to dbtf, wf synd tifm up witi tif pffr bfforf sfriblizing.
        TfxtComponfntPffr pffr = (TfxtComponfntPffr)tiis.pffr;
        if (pffr != null) {
            tfxt = pffr.gftTfxt();
            sflfdtionStbrt = pffr.gftSflfdtionStbrt();
            sflfdtionEnd = pffr.gftSflfdtionEnd();
        }

        s.dffbultWritfObjfdt();

        AWTEvfntMultidbstfr.sbvf(s, tfxtListfnfrK, tfxtListfnfr);
        s.writfObjfdt(null);
    }

    /**
     * Rfbd tif ObjfdtInputStrfbm, bnd if it isn't null,
     * bdd b listfnfr to rfdfivf tfxt fvfnts firfd by tif
     * TfxtComponfnt.  Unrfdognizfd kfys or vblufs will bf
     * ignorfd.
     *
     * @fxdfption HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     * <dodf>truf</dodf>
     * @sff #rfmovfTfxtListfnfr
     * @sff #bddTfxtListfnfr
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption
    {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        s.dffbultRfbdObjfdt();

        // Mbkf surf tif stbtf wf just rfbd in for tfxt,
        // sflfdtionStbrt bnd sflfdtionEnd ibs lfgbl vblufs
        tiis.tfxt = (tfxt != null) ? tfxt : "";
        sflfdt(sflfdtionStbrt, sflfdtionEnd);

        Objfdt kfyOrNull;
        wiilf(null != (kfyOrNull = s.rfbdObjfdt())) {
            String kfy = ((String)kfyOrNull).intfrn();

            if (tfxtListfnfrK == kfy) {
                bddTfxtListfnfr((TfxtListfnfr)(s.rfbdObjfdt()));
            } flsf {
                // skip vbluf for unrfdognizfd kfy
                s.rfbdObjfdt();
            }
        }
        fnbblfInputMftiodsIfNfdfssbry();
    }


/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis TfxtComponfnt.
     * For tfxt domponfnts, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTTfxtComponfnt.
     * A nfw AddfssiblfAWTTfxtComponfnt instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTTfxtComponfnt tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis TfxtComponfnt
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTTfxtComponfnt();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>TfxtComponfnt</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tfxt domponfnt usfr-intfrfbdf
     * flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTTfxtComponfnt fxtfnds AddfssiblfAWTComponfnt
        implfmfnts AddfssiblfTfxt, TfxtListfnfr
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = 3631432373506317811L;

        /**
         * Construdts bn AddfssiblfAWTTfxtComponfnt.  Adds b listfnfr to trbdk
         * dbrft dibngf.
         */
        publid AddfssiblfAWTTfxtComponfnt() {
            TfxtComponfnt.tiis.bddTfxtListfnfr(tiis);
        }

        /**
         * TfxtListfnfr notifidbtion of b tfxt vbluf dibngf.
         */
        publid void tfxtVblufCibngfd(TfxtEvfnt tfxtEvfnt)  {
            Intfgfr dpos = Intfgfr.vblufOf(TfxtComponfnt.tiis.gftCbrftPosition());
            firfPropfrtyCibngf(ACCESSIBLE_TEXT_PROPERTY, null, dpos);
        }

        /**
         * Gfts tif stbtf sft of tif TfxtComponfnt.
         * Tif AddfssiblfStbtfSft of bn objfdt is domposfd of b sft of
         * uniquf AddfssiblfStbtfs.  A dibngf in tif AddfssiblfStbtfSft
         * of bn objfdt will dbusf b PropfrtyCibngfEvfnt to bf firfd
         * for tif AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY propfrty.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dontbining tif
         * durrfnt stbtf sft of tif objfdt
         * @sff AddfssiblfStbtfSft
         * @sff AddfssiblfStbtf
         * @sff #bddPropfrtyCibngfListfnfr
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (TfxtComponfnt.tiis.isEditbblf()) {
                stbtfs.bdd(AddfssiblfStbtf.EDITABLE);
            }
            rfturn stbtfs;
        }


        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt (AddfssiblfRolf.TEXT)
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TEXT;
        }

        /**
         * Gft tif AddfssiblfTfxt bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfTfxt intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfTfxt gftAddfssiblfTfxt() {
            rfturn tiis;
        }


        // --- intfrfbdf AddfssiblfTfxt mftiods ------------------------

        /**
         * Mbny of tifsf mftiods brf just donvfnifndf mftiods; tify
         * just dbll tif fquivblfnt on tif pbrfnt
         */

        /**
         * Givfn b point in lodbl doordinbtfs, rfturn tif zfro-bbsfd indfx
         * of tif dibrbdtfr undfr tibt Point.  If tif point is invblid,
         * tiis mftiod rfturns -1.
         *
         * @pbrbm p tif Point in lodbl doordinbtfs
         * @rfturn tif zfro-bbsfd indfx of tif dibrbdtfr undfr Point p.
         */
        publid int gftIndfxAtPoint(Point p) {
            rfturn -1;
        }

        /**
         * Dftfrminfs tif bounding box of tif dibrbdtfr bt tif givfn
         * indfx into tif string.  Tif bounds brf rfturnfd in lodbl
         * doordinbtfs.  If tif indfx is invblid b null rfdtbnglf
         * is rfturnfd.
         *
         * @pbrbm i tif indfx into tif String &gt;= 0
         * @rfturn tif sdrffn doordinbtfs of tif dibrbdtfr's bounding box
         */
        publid Rfdtbnglf gftCibrbdtfrBounds(int i) {
            rfturn null;
        }

        /**
         * Rfturns tif numbfr of dibrbdtfrs (vblid indidifs)
         *
         * @rfturn tif numbfr of dibrbdtfrs &gt;= 0
         */
        publid int gftCibrCount() {
            rfturn TfxtComponfnt.tiis.gftTfxt().lfngti();
        }

        /**
         * Rfturns tif zfro-bbsfd offsft of tif dbrft.
         *
         * Notf: Tif dibrbdtfr to tif rigit of tif dbrft will ibvf tif
         * sbmf indfx vbluf bs tif offsft (tif dbrft is bftwffn
         * two dibrbdtfrs).
         *
         * @rfturn tif zfro-bbsfd offsft of tif dbrft.
         */
        publid int gftCbrftPosition() {
            rfturn TfxtComponfnt.tiis.gftCbrftPosition();
        }

        /**
         * Rfturns tif AttributfSft for b givfn dibrbdtfr (bt b givfn indfx).
         *
         * @pbrbm i tif zfro-bbsfd indfx into tif tfxt
         * @rfturn tif AttributfSft of tif dibrbdtfr
         */
        publid AttributfSft gftCibrbdtfrAttributf(int i) {
            rfturn null; // No bttributfs in TfxtComponfnt
        }

        /**
         * Rfturns tif stbrt offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         * Rfturn 0 if tif tfxt is fmpty, or tif dbrft position
         * if no sflfdtion.
         *
         * @rfturn tif indfx into tif tfxt of tif stbrt of tif sflfdtion &gt;= 0
         */
        publid int gftSflfdtionStbrt() {
            rfturn TfxtComponfnt.tiis.gftSflfdtionStbrt();
        }

        /**
         * Rfturns tif fnd offsft witiin tif sflfdtfd tfxt.
         * If tifrf is no sflfdtion, but tifrf is
         * b dbrft, tif stbrt bnd fnd offsfts will bf tif sbmf.
         * Rfturn 0 if tif tfxt is fmpty, or tif dbrft position
         * if no sflfdtion.
         *
         * @rfturn tif indfx into tif tfxt of tif fnd of tif sflfdtion &gt;= 0
         */
        publid int gftSflfdtionEnd() {
            rfturn TfxtComponfnt.tiis.gftSflfdtionEnd();
        }

        /**
         * Rfturns tif portion of tif tfxt tibt is sflfdtfd.
         *
         * @rfturn tif tfxt, null if no sflfdtion
         */
        publid String gftSflfdtfdTfxt() {
            String sflTfxt = TfxtComponfnt.tiis.gftSflfdtfdTfxt();
            // Fix for 4256662
            if (sflTfxt == null || sflTfxt.fqubls("")) {
                rfturn null;
            }
            rfturn sflTfxt;
        }

        /**
         * Rfturns tif String bt b givfn indfx.
         *
         * @pbrbm pbrt tif AddfssiblfTfxt.CHARACTER, AddfssiblfTfxt.WORD,
         * or AddfssiblfTfxt.SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt &gt;= 0
         * @rfturn tif lfttfr, word, or sfntfndf,
         *   null for bn invblid indfx or pbrt
         */
        publid String gftAtIndfx(int pbrt, int indfx) {
            if (indfx < 0 || indfx >= TfxtComponfnt.tiis.gftTfxt().lfngti()) {
                rfturn null;
            }
            switdi (pbrt) {
            dbsf AddfssiblfTfxt.CHARACTER:
                rfturn TfxtComponfnt.tiis.gftTfxt().substring(indfx, indfx+1);
            dbsf AddfssiblfTfxt.WORD:  {
                    String s = TfxtComponfnt.tiis.gftTfxt();
                    BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf();
                    words.sftTfxt(s);
                    int fnd = words.following(indfx);
                    rfturn s.substring(words.prfvious(), fnd);
                }
            dbsf AddfssiblfTfxt.SENTENCE:  {
                    String s = TfxtComponfnt.tiis.gftTfxt();
                    BrfbkItfrbtor sfntfndf = BrfbkItfrbtor.gftSfntfndfInstbndf();
                    sfntfndf.sftTfxt(s);
                    int fnd = sfntfndf.following(indfx);
                    rfturn s.substring(sfntfndf.prfvious(), fnd);
                }
            dffbult:
                rfturn null;
            }
        }

        privbtf stbtid finbl boolfbn NEXT = truf;
        privbtf stbtid finbl boolfbn PREVIOUS = fblsf;

        /**
         * Nffdfd to unify forwbrd bnd bbdkwbrd sfbrdiing.
         * Tif mftiod bssumfs tibt s is tif tfxt bssignfd to words.
         */
        privbtf int findWordLimit(int indfx, BrfbkItfrbtor words, boolfbn dirfdtion,
                                         String s) {
            // Fix for 4256660 bnd 4256661.
            // Words itfrbtor is difffrfnt from dibrbdtfr bnd sfntfndf itfrbtors
            // in tibt fnd of onf word is not nfdfssbrily stbrt of bnotifr word.
            // Plfbsf sff jbvb.tfxt.BrfbkItfrbtor JbvbDod. Tif dodf bflow is
            // bbsfd on nfxtWordStbrtAftfr fxbmplf from BrfbkItfrbtor.jbvb.
            int lbst = (dirfdtion == NEXT) ? words.following(indfx)
                                           : words.prfdfding(indfx);
            int durrfnt = (dirfdtion == NEXT) ? words.nfxt()
                                              : words.prfvious();
            wiilf (durrfnt != BrfbkItfrbtor.DONE) {
                for (int p = Mbti.min(lbst, durrfnt); p < Mbti.mbx(lbst, durrfnt); p++) {
                    if (Cibrbdtfr.isLfttfr(s.dibrAt(p))) {
                        rfturn lbst;
                    }
                }
                lbst = durrfnt;
                durrfnt = (dirfdtion == NEXT) ? words.nfxt()
                                              : words.prfvious();
            }
            rfturn BrfbkItfrbtor.DONE;
        }

        /**
         * Rfturns tif String bftfr b givfn indfx.
         *
         * @pbrbm pbrt tif AddfssiblfTfxt.CHARACTER, AddfssiblfTfxt.WORD,
         * or AddfssiblfTfxt.SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt &gt;= 0
         * @rfturn tif lfttfr, word, or sfntfndf, null for bn invblid
         *  indfx or pbrt
         */
        publid String gftAftfrIndfx(int pbrt, int indfx) {
            if (indfx < 0 || indfx >= TfxtComponfnt.tiis.gftTfxt().lfngti()) {
                rfturn null;
            }
            switdi (pbrt) {
            dbsf AddfssiblfTfxt.CHARACTER:
                if (indfx+1 >= TfxtComponfnt.tiis.gftTfxt().lfngti()) {
                   rfturn null;
                }
                rfturn TfxtComponfnt.tiis.gftTfxt().substring(indfx+1, indfx+2);
            dbsf AddfssiblfTfxt.WORD:  {
                    String s = TfxtComponfnt.tiis.gftTfxt();
                    BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf();
                    words.sftTfxt(s);
                    int stbrt = findWordLimit(indfx, words, NEXT, s);
                    if (stbrt == BrfbkItfrbtor.DONE || stbrt >= s.lfngti()) {
                        rfturn null;
                    }
                    int fnd = words.following(stbrt);
                    if (fnd == BrfbkItfrbtor.DONE || fnd >= s.lfngti()) {
                        rfturn null;
                    }
                    rfturn s.substring(stbrt, fnd);
                }
            dbsf AddfssiblfTfxt.SENTENCE:  {
                    String s = TfxtComponfnt.tiis.gftTfxt();
                    BrfbkItfrbtor sfntfndf = BrfbkItfrbtor.gftSfntfndfInstbndf();
                    sfntfndf.sftTfxt(s);
                    int stbrt = sfntfndf.following(indfx);
                    if (stbrt == BrfbkItfrbtor.DONE || stbrt >= s.lfngti()) {
                        rfturn null;
                    }
                    int fnd = sfntfndf.following(stbrt);
                    if (fnd == BrfbkItfrbtor.DONE || fnd >= s.lfngti()) {
                        rfturn null;
                    }
                    rfturn s.substring(stbrt, fnd);
                }
            dffbult:
                rfturn null;
            }
        }


        /**
         * Rfturns tif String bfforf b givfn indfx.
         *
         * @pbrbm pbrt tif AddfssiblfTfxt.CHARACTER, AddfssiblfTfxt.WORD,
         *   or AddfssiblfTfxt.SENTENCE to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt &gt;= 0
         * @rfturn tif lfttfr, word, or sfntfndf, null for bn invblid indfx
         *  or pbrt
         */
        publid String gftBfforfIndfx(int pbrt, int indfx) {
            if (indfx < 0 || indfx > TfxtComponfnt.tiis.gftTfxt().lfngti()-1) {
                rfturn null;
            }
            switdi (pbrt) {
            dbsf AddfssiblfTfxt.CHARACTER:
                if (indfx == 0) {
                    rfturn null;
                }
                rfturn TfxtComponfnt.tiis.gftTfxt().substring(indfx-1, indfx);
            dbsf AddfssiblfTfxt.WORD:  {
                    String s = TfxtComponfnt.tiis.gftTfxt();
                    BrfbkItfrbtor words = BrfbkItfrbtor.gftWordInstbndf();
                    words.sftTfxt(s);
                    int fnd = findWordLimit(indfx, words, PREVIOUS, s);
                    if (fnd == BrfbkItfrbtor.DONE) {
                        rfturn null;
                    }
                    int stbrt = words.prfdfding(fnd);
                    if (stbrt == BrfbkItfrbtor.DONE) {
                        rfturn null;
                    }
                    rfturn s.substring(stbrt, fnd);
                }
            dbsf AddfssiblfTfxt.SENTENCE:  {
                    String s = TfxtComponfnt.tiis.gftTfxt();
                    BrfbkItfrbtor sfntfndf = BrfbkItfrbtor.gftSfntfndfInstbndf();
                    sfntfndf.sftTfxt(s);
                    int fnd = sfntfndf.following(indfx);
                    fnd = sfntfndf.prfvious();
                    int stbrt = sfntfndf.prfvious();
                    if (stbrt == BrfbkItfrbtor.DONE) {
                        rfturn null;
                    }
                    rfturn s.substring(stbrt, fnd);
                }
            dffbult:
                rfturn null;
            }
        }
    }  // fnd of AddfssiblfAWTTfxtComponfnt

    privbtf boolfbn difdkForEnbblfIM = truf;
}
