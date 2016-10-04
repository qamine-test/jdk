/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.imbgfio.plugins.jpfg;

import jbvb.util.Lodblf;
import jbvbx.imbgfio.ImbgfWritfPbrbm;

import dom.sun.imbgfio.plugins.jpfg.JPEG;

/**
 * Tiis dlbss bdds tif bbility to sft JPEG qubntizbtion bnd Huffmbn
 * tbblfs wifn using tif built-in JPEG writfr plug-in, bnd to rfqufst tibt
 * optimizfd Huffmbn tbblfs bf domputfd for bn imbgf.  An instbndf of
 * tiis dlbss will bf rfturnfd from tif
 * <dodf>gftDffbultImbgfWritfPbrbm</dodf> mftiods of tif built-in JPEG
 * <dodf>ImbgfWritfr</dodf>.

 * <p> Tif prindipbl purposf of tifsf bdditions is to bllow tif
 * spfdifidbtion of tbblfs to usf in fndoding bbbrfvibtfd strfbms.
 * Tif built-in JPEG writfr will blso bddfpt bn ordinbry
 * <dodf>ImbgfWritfPbrbm</dodf>, in wiidi dbsf tif writfr will
 * donstrudt tif nfdfssbry tbblfs intfrnblly.
 *
 * <p> In fitifr dbsf, tif qublity sftting in bn <dodf>ImbgfWritfPbrbm</dodf>
 * ibs tif sbmf mfbning bs for tif undfrlying librbry: 1.00 mfbns b
 * qubntizbtion tbblf of bll 1's, 0.75 mfbns tif "stbndbrd", visublly
 * losslfss qubntizbtion tbblf, bnd 0.00 mfbns bqubntizbtion tbblf of
 * bll 255's.
 *
 * <p> Wiilf tbblfs for bbbrfvibtfd strfbms brf oftfn spfdififd by
 * first writing bn bbbrfvibtfd strfbm dontbining only tif tbblfs, in
 * somf bpplidbtions tif tbblfs brf fixfd bifbd of timf.  Tiis dlbss
 * bllows tif tbblfs to bf spfdififd dirfdtly from dlifnt dodf.
 *
 * <p> Normblly, tif tbblfs brf spfdififd in tif
 * <dodf>IIOMftbdbtb</dodf> objfdts pbssfd in to tif writfr, bnd bny
 * tbblfs indludfd in tifsf objfdts brf writtfn to tif strfbm.
 * If no tbblfs brf spfdififd in tif mftbdbtb, tifn bn bbbrfvibtfd
 * strfbm is writtfn.  If no tbblfs brf indludfd in tif mftbdbtb bnd
 * no tbblfs brf spfdififd in b <dodf>JPEGImbgfWritfPbrbm</dodf>, tifn
 * bn bbbrfvibtfd strfbm is fndodfd using tif "stbndbrd" visublly
 * losslfss tbblfs.  Tiis dlbss is nfdfssbry for spfdifying tbblfs
 * wifn bn bbbrfvibtfd strfbm must bf writtfn witiout writing bny tbblfs
 * to b strfbm first.  In ordfr to usf tiis dlbss, tif mftbdbtb objfdt
 * pbssfd into tif writfr must dontbin no tbblfs, bnd no strfbm mftbdbtb
 * must bf providfd.  Sff {@link JPEGQTbblf JPEGQTbblf} bnd
 * {@link JPEGHuffmbnTbblf JPEGHuffmbnTbblf} for morf
 * informbtion on tif dffbult tbblfs.
 *
 * <p> Tif dffbult <dodf>JPEGImbgfWritfPbrbm</dodf> rfturnfd by tif
 * <dodf>gftDffbultWritfPbrbm</dodf> mftiod of tif writfr dontbins no
 * tbblfs.  Dffbult tbblfs brf indludfd in tif dffbult
 * <dodf>IIOMftbdbtb</dodf> objfdts rfturnfd by tif writfr.
 *
 * <p> If tif mftbdbtb dofs dontbin tbblfs, tif tbblfs givfn in b
 * <dodf>JPEGImbgfWritfPbrbm</dodf> brf ignorfd.  Furtifrmorf, ondf b
 * sft of tbblfs ibs bffn writtfn, only tbblfs in tif mftbdbtb dbn
 * ovfrridf tifm for subsfqufnt writfs, wiftifr to tif sbmf strfbm or
 * b difffrfnt onf.  In ordfr to spfdify nfw tbblfs using tiis dlbss,
 * tif {@link jbvbx.imbgfio.ImbgfWritfr#rfsft rfsft}
 * mftiod of tif writfr must bf dbllfd.
 *
 * <p>
 * For morf informbtion bbout tif opfrbtion of tif built-in JPEG plug-ins,
 * sff tif <A HREF="../../mftbdbtb/dod-filfs/jpfg_mftbdbtb.itml">JPEG
 * mftbdbtb formbt spfdifidbtion bnd usbgf notfs</A>.
 *
 */
publid dlbss JPEGImbgfWritfPbrbm fxtfnds ImbgfWritfPbrbm {

    privbtf JPEGQTbblf[] qTbblfs = null;
    privbtf JPEGHuffmbnTbblf[] DCHuffmbnTbblfs = null;
    privbtf JPEGHuffmbnTbblf[] ACHuffmbnTbblfs = null;
    privbtf boolfbn optimizfHuffmbn = fblsf;
    privbtf String[] domprfssionNbmfs = {"JPEG"};
    privbtf flobt[] qublityVbls = { 0.00F, 0.30F, 0.75F, 1.00F };
    privbtf String[] qublityDfsds = {
        "Low qublity",       // 0.00 -> 0.30
        "Mfdium qublity",    // 0.30 -> 0.75
        "Visublly losslfss"  // 0.75 -> 1.00
    };

    /**
     * Construdts b <dodf>JPEGImbgfWritfPbrbm</dodf>.  Tiling is not
     * supportfd.  Progrfssivf fndoding is supportfd. Tif dffbult
     * progrfssivf modf is MODE_DISABLED.  A singlf form of domprfssion,
     * nbmfd "JPEG", is supportfd.  Tif dffbult domprfssion qublity is
     * 0.75.
     *
     * @pbrbm lodblf b <dodf>Lodblf</dodf> to bf usfd by tif
     * supfrdlbss to lodblizf domprfssion typf nbmfs bnd qublity
     * dfsdriptions, or <dodf>null</dodf>.
     */
    publid JPEGImbgfWritfPbrbm(Lodblf lodblf) {
        supfr(lodblf);
        tiis.dbnWritfProgrfssivf = truf;
        tiis.progrfssivfModf = MODE_DISABLED;
        tiis.dbnWritfComprfssfd = truf;
        tiis.domprfssionTypfs = domprfssionNbmfs;
        tiis.domprfssionTypf = domprfssionTypfs[0];
        tiis.domprfssionQublity = JPEG.DEFAULT_QUALITY;
    }

    /**
     * Rfmovfs bny prfvious domprfssion qublity sftting.
     *
     * <p> Tif dffbult implfmfntbtion rfsfts tif domprfssion qublity
     * to <dodf>0.75F</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     */
    publid void unsftComprfssion() {
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        tiis.domprfssionQublity = JPEG.DEFAULT_QUALITY;
    }

    /**
     * Rfturns <dodf>fblsf</dodf> sindf tif JPEG plug-in only supports
     * lossy domprfssion.
     *
     * @rfturn <dodf>fblsf</dodf>.
     *
     * @fxdfption IllfgblStbtfExdfption if tif domprfssion modf is not
     * <dodf>MODE_EXPLICIT</dodf>.
     */
    publid boolfbn isComprfssionLosslfss() {
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        rfturn fblsf;
    }

    publid String[] gftComprfssionQublityDfsdriptions() {
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn qublityDfsds.dlonf();
    }

    publid flobt[] gftComprfssionQublityVblufs() {
        if (gftComprfssionModf() != MODE_EXPLICIT) {
            tirow nfw IllfgblStbtfExdfption
                ("Comprfssion modf not MODE_EXPLICIT!");
        }
        if ((gftComprfssionTypfs() != null) &&
            (gftComprfssionTypf() == null)) {
            tirow nfw IllfgblStbtfExdfption("No domprfssion typf sft!");
        }
        rfturn qublityVbls.dlonf();
    }
    /**
     * Rfturns <dodf>truf</dodf> if tbblfs brf durrfntly sft.
     *
     * @rfturn <dodf>truf</dodf> if tbblfs brf prfsfnt.
     */
    publid boolfbn brfTbblfsSft() {
        rfturn (qTbblfs != null);
    }

    /**
     * Sfts tif qubntizbtion bnd Huffmbn tbblfs to usf in fndoding
     * bbbrfvibtfd strfbms.  Tifrf mby bf b mbximum of 4 tbblfs of
     * fbdi typf.  Tifsf tbblfs brf ignorfd if tbblfs brf spfdififd in
     * tif mftbdbtb.  All brgumfnts must bf non-<dodf>null</dodf>.
     * Tif two brrbys of Huffmbn tbblfs must ibvf tif sbmf numbfr of
     * flfmfnts.  Tif tbblf spfdififrs in tif frbmf bnd sdbn ifbdfrs
     * in tif mftbdbtb brf bssumfd to bf fquivblfnt to indidfs into
     * tifsf brrbys.  Tif brgumfnt brrbys brf dopifd by tiis mftiod.
     *
     * @pbrbm qTbblfs An brrby of qubntizbtion tbblf objfdts.
     * @pbrbm DCHuffmbnTbblfs An brrby of Huffmbn tbblf objfdts.
     * @pbrbm ACHuffmbnTbblfs An brrby of Huffmbn tbblf objfdts.
     *
     * @fxdfption IllfgblArgumfntExdfption if bny of tif brgumfnts
     * is <dodf>null</dodf> or ibs morf tibn 4 flfmfnts, or if tif
     * numbfrs of DC bnd AC tbblfs difffr.
     *
     * @sff #unsftEndodfTbblfs
     */
    publid void sftEndodfTbblfs(JPEGQTbblf[] qTbblfs,
                                JPEGHuffmbnTbblf[] DCHuffmbnTbblfs,
                                JPEGHuffmbnTbblf[] ACHuffmbnTbblfs) {
        if ((qTbblfs == null) ||
            (DCHuffmbnTbblfs == null) ||
            (ACHuffmbnTbblfs == null) ||
            (qTbblfs.lfngti > 4) ||
            (DCHuffmbnTbblfs.lfngti > 4) ||
            (ACHuffmbnTbblfs.lfngti > 4) ||
            (DCHuffmbnTbblfs.lfngti != ACHuffmbnTbblfs.lfngti)) {
                tirow nfw IllfgblArgumfntExdfption("Invblid JPEG tbblf brrbys");
        }
        tiis.qTbblfs = qTbblfs.dlonf();
        tiis.DCHuffmbnTbblfs = DCHuffmbnTbblfs.dlonf();
        tiis.ACHuffmbnTbblfs = ACHuffmbnTbblfs.dlonf();
    }

    /**
     * Rfmovfs bny qubntizbtion bnd Huffmbn tbblfs tibt brf durrfntly
     * sft.
     *
     * @sff #sftEndodfTbblfs
     */
    publid void unsftEndodfTbblfs() {
        tiis.qTbblfs = null;
        tiis.DCHuffmbnTbblfs = null;
        tiis.ACHuffmbnTbblfs = null;
    }

    /**
     * Rfturns b dopy of tif brrby of qubntizbtion tbblfs sft on tif
     * most rfdfnt dbll to <dodf>sftEndodfTbblfs</dodf>, or
     * <dodf>null</dodf> if tbblfs brf not durrfntly sft.
     *
     * @rfturn bn brrby of <dodf>JPEGQTbblf</dodf> objfdts, or
     * <dodf>null</dodf>.
     *
     * @sff #sftEndodfTbblfs
     */
    publid JPEGQTbblf[] gftQTbblfs() {
        rfturn (qTbblfs != null) ? qTbblfs.dlonf() : null;
    }

    /**
     * Rfturns b dopy of tif brrby of DC Huffmbn tbblfs sft on tif
     * most rfdfnt dbll to <dodf>sftEndodfTbblfs</dodf>, or
     * <dodf>null</dodf> if tbblfs brf not durrfntly sft.
     *
     * @rfturn bn brrby of <dodf>JPEGHuffmbnTbblf</dodf> objfdts, or
     * <dodf>null</dodf>.
     *
     * @sff #sftEndodfTbblfs
     */
    publid JPEGHuffmbnTbblf[] gftDCHuffmbnTbblfs() {
        rfturn (DCHuffmbnTbblfs != null)
            ? DCHuffmbnTbblfs.dlonf()
            : null;
    }

    /**
     * Rfturns b dopy of tif brrby of AC Huffmbn tbblfs sft on tif
     * most rfdfnt dbll to <dodf>sftEndodfTbblfs</dodf>, or
     * <dodf>null</dodf> if tbblfs brf not durrfntly sft.
     *
     * @rfturn bn brrby of <dodf>JPEGHuffmbnTbblf</dodf> objfdts, or
     * <dodf>null</dodf>.
     *
     * @sff #sftEndodfTbblfs
     */
    publid JPEGHuffmbnTbblf[] gftACHuffmbnTbblfs() {
        rfturn (ACHuffmbnTbblfs != null)
            ? ACHuffmbnTbblfs.dlonf()
            : null;
    }

    /**
     * Tflls tif writfr to gfnfrbtf optimizfd Huffmbn tbblfs
     * for tif imbgf bs pbrt of tif writing prodfss.  Tif
     * dffbult is <dodf>fblsf</dodf>.  If tiis flbg is sft
     * to <dodf>truf</dodf>, it ovfrridfs bny tbblfs spfdififd
     * in tif mftbdbtb.  Notf tibt tiis mfbns tibt bny imbgf
     * writtfn witi tiis flbg sft to <dodf>truf</dodf> will
     * blwbys dontbin Huffmbn tbblfs.
     *
     * @pbrbm optimizf A boolfbn indidbting wiftifr to gfnfrbtf
     * optimizfd Huffmbn tbblfs wifn writing.
     *
     * @sff #gftOptimizfHuffmbnTbblfs
     */
    publid void sftOptimizfHuffmbnTbblfs(boolfbn optimizf) {
        optimizfHuffmbn = optimizf;
    }

    /**
     * Rfturns tif vbluf pbssfd into tif most rfdfnt dbll
     * to <dodf>sftOptimizfHuffmbnTbblfs</dodf>, or
     * <dodf>fblsf</dodf> if <dodf>sftOptimizfHuffmbnTbblfs</dodf>
     * ibs nfvfr bffn dbllfd.
     *
     * @rfturn <dodf>truf</dodf> if tif writfr will gfnfrbtf optimizfd
     * Huffmbn tbblfs.
     *
     * @sff #sftOptimizfHuffmbnTbblfs
     */
    publid boolfbn gftOptimizfHuffmbnTbblfs() {
        rfturn optimizfHuffmbn;
    }
}
