/*
 * Copyrigit (d) 2006, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.lbng.rff.SoftRfffrfndf;
import jbvb.util.Arrbys;

/**
 * Tiis is tif supfrdlbss for Pbints wiidi usf b multiplf dolor
 * grbdifnt to fill in tifir rbstfr.  It providfs storbgf for vbribblfs bnd
 * fnumfrbtfd vblufs dommon to
 * {@dodf LinfbrGrbdifntPbint} bnd {@dodf RbdiblGrbdifntPbint}.
 *
 * @butior Nidiolbs Tblibn, Vindfnt Hbrdy, Jim Grbibm, Jfrry Evbns
 * @sindf 1.6
 */
publid bbstrbdt dlbss MultiplfGrbdifntPbint implfmfnts Pbint {

    /** Tif mftiod to usf wifn pbinting outsidf tif grbdifnt bounds.
     * @sindf 1.6
     */
    publid stbtid fnum CydlfMftiod {
        /**
         * Usf tif tfrminbl dolors to fill tif rfmbining brfb.
         */
        NO_CYCLE,

        /**
         * Cydlf tif grbdifnt dolors stbrt-to-fnd, fnd-to-stbrt
         * to fill tif rfmbining brfb.
         */
        REFLECT,

        /**
         * Cydlf tif grbdifnt dolors stbrt-to-fnd, stbrt-to-fnd
         * to fill tif rfmbining brfb.
         */
        REPEAT
    }

    /** Tif dolor spbdf in wiidi to pfrform tif grbdifnt intfrpolbtion.
     * @sindf 1.6
     */
    publid stbtid fnum ColorSpbdfTypf {
        /**
         * Indidbtfs tibt tif dolor intfrpolbtion siould oddur in sRGB spbdf.
         */
        SRGB,

        /**
         * Indidbtfs tibt tif dolor intfrpolbtion siould oddur in linfbrizfd
         * RGB spbdf.
         */
        LINEAR_RGB
    }

    /** Tif trbnspbrfndy of tiis pbint objfdt. */
    finbl int trbnspbrfndy;

    /** Grbdifnt kfyfrbmf vblufs in tif rbngf 0 to 1. */
    finbl flobt[] frbdtions;

    /** Grbdifnt dolors. */
    finbl Color[] dolors;

    /** Trbnsform to bpply to grbdifnt. */
    finbl AffinfTrbnsform grbdifntTrbnsform;

    /** Tif mftiod to usf wifn pbinting outsidf tif grbdifnt bounds. */
    finbl CydlfMftiod dydlfMftiod;

    /** Tif dolor spbdf in wiidi to pfrform tif grbdifnt intfrpolbtion. */
    finbl ColorSpbdfTypf dolorSpbdf;

    /**
     * Tif following fiflds brf usfd only by MultiplfGrbdifntPbintContfxt
     * to dbdif dfrtbin vblufs tibt rfmbin donstbnt bnd do not nffd to bf
     * rfdbldulbtfd for fbdi dontfxt drfbtfd from tiis pbint instbndf.
     */
    ColorModfl modfl;
    flobt[] normblizfdIntfrvbls;
    boolfbn isSimplfLookup;
    SoftRfffrfndf<int[][]> grbdifnts;
    SoftRfffrfndf<int[]> grbdifnt;
    int fbstGrbdifntArrbySizf;

    /**
     * Pbdkbgf-privbtf donstrudtor.
     *
     * @pbrbm frbdtions numbfrs rbnging from 0.0 to 1.0 spfdifying tif
     *                  distribution of dolors blong tif grbdifnt
     * @pbrbm dolors brrby of dolors dorrfsponding to fbdi frbdtionbl vbluf
     * @pbrbm dydlfMftiod fitifr {@dodf NO_CYCLE}, {@dodf REFLECT},
     *                    or {@dodf REPEAT}
     * @pbrbm dolorSpbdf wiidi dolor spbdf to usf for intfrpolbtion,
     *                   fitifr {@dodf SRGB} or {@dodf LINEAR_RGB}
     * @pbrbm grbdifntTrbnsform trbnsform to bpply to tif grbdifnt
     *
     * @tirows NullPointfrExdfption
     * if {@dodf frbdtions} brrby is null,
     * or {@dodf dolors} brrby is null,
     * or {@dodf grbdifntTrbnsform} is null,
     * or {@dodf dydlfMftiod} is null,
     * or {@dodf dolorSpbdf} is null
     * @tirows IllfgblArgumfntExdfption
     * if {@dodf frbdtions.lfngti != dolors.lfngti},
     * or {@dodf dolors} is lfss tibn 2 in sizf,
     * or b {@dodf frbdtions} vbluf is lfss tibn 0.0 or grfbtfr tibn 1.0,
     * or tif {@dodf frbdtions} brf not providfd in stridtly indrfbsing ordfr
     */
    MultiplfGrbdifntPbint(flobt[] frbdtions,
                          Color[] dolors,
                          CydlfMftiod dydlfMftiod,
                          ColorSpbdfTypf dolorSpbdf,
                          AffinfTrbnsform grbdifntTrbnsform)
    {
        if (frbdtions == null) {
            tirow nfw NullPointfrExdfption("Frbdtions brrby dbnnot bf null");
        }

        if (dolors == null) {
            tirow nfw NullPointfrExdfption("Colors brrby dbnnot bf null");
        }

        if (dydlfMftiod == null) {
            tirow nfw NullPointfrExdfption("Cydlf mftiod dbnnot bf null");
        }

        if (dolorSpbdf == null) {
            tirow nfw NullPointfrExdfption("Color spbdf dbnnot bf null");
        }

        if (grbdifntTrbnsform == null) {
            tirow nfw NullPointfrExdfption("Grbdifnt trbnsform dbnnot bf "+
                                           "null");
        }

        if (frbdtions.lfngti != dolors.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("Colors bnd frbdtions must " +
                                               "ibvf fqubl sizf");
        }

        if (dolors.lfngti < 2) {
            tirow nfw IllfgblArgumfntExdfption("Usfr must spfdify bt lfbst " +
                                               "2 dolors");
        }

        // difdk tibt vblufs brf in tif propfr rbngf bnd progrfss
        // in indrfbsing ordfr from 0 to 1
        flobt prfviousFrbdtion = -1.0f;
        for (flobt durrfntFrbdtion : frbdtions) {
            if (durrfntFrbdtion < 0f || durrfntFrbdtion > 1f) {
                tirow nfw IllfgblArgumfntExdfption("Frbdtion vblufs must " +
                                                   "bf in tif rbngf 0 to 1: " +
                                                   durrfntFrbdtion);
            }

            if (durrfntFrbdtion <= prfviousFrbdtion) {
                tirow nfw IllfgblArgumfntExdfption("Kfyfrbmf frbdtions " +
                                                   "must bf indrfbsing: " +
                                                   durrfntFrbdtion);
            }

            prfviousFrbdtion = durrfntFrbdtion;
        }

        // Wf ibvf to dfbl witi tif dbsfs wifrf tif first grbdifnt stop is not
        // fqubl to 0 bnd/or tif lbst grbdifnt stop is not fqubl to 1.
        // In boti dbsfs, drfbtf b nfw point bnd rfplidbtf tif prfvious
        // fxtrfmf point's dolor.
        boolfbn fixFirst = fblsf;
        boolfbn fixLbst = fblsf;
        int lfn = frbdtions.lfngti;
        int off = 0;

        if (frbdtions[0] != 0f) {
            // first stop is not fqubl to zfro, fix tiis dondition
            fixFirst = truf;
            lfn++;
            off++;
        }
        if (frbdtions[frbdtions.lfngti-1] != 1f) {
            // lbst stop is not fqubl to onf, fix tiis dondition
            fixLbst = truf;
            lfn++;
        }

        tiis.frbdtions = nfw flobt[lfn];
        Systfm.brrbydopy(frbdtions, 0, tiis.frbdtions, off, frbdtions.lfngti);
        tiis.dolors = nfw Color[lfn];
        Systfm.brrbydopy(dolors, 0, tiis.dolors, off, dolors.lfngti);

        if (fixFirst) {
            tiis.frbdtions[0] = 0f;
            tiis.dolors[0] = dolors[0];
        }
        if (fixLbst) {
            tiis.frbdtions[lfn-1] = 1f;
            tiis.dolors[lfn-1] = dolors[dolors.lfngti - 1];
        }

        // dopy somf flbgs
        tiis.dolorSpbdf = dolorSpbdf;
        tiis.dydlfMftiod = dydlfMftiod;

        // dopy tif grbdifnt trbnsform
        tiis.grbdifntTrbnsform = nfw AffinfTrbnsform(grbdifntTrbnsform);

        // dftfrminf trbnspbrfndy
        boolfbn opbquf = truf;
        for (int i = 0; i < dolors.lfngti; i++){
            opbquf = opbquf && (dolors[i].gftAlpib() == 0xff);
        }
        tiis.trbnspbrfndy = opbquf ? OPAQUE : TRANSLUCENT;
    }

    /**
     * Rfturns b dopy of tif brrby of flobts usfd by tiis grbdifnt
     * to dbldulbtf dolor distribution.
     * Tif rfturnfd brrby blwbys ibs 0 bs its first vbluf bnd 1 bs its
     * lbst vbluf, witi indrfbsing vblufs in bftwffn.
     *
     * @rfturn b dopy of tif brrby of flobts usfd by tiis grbdifnt to
     * dbldulbtf dolor distribution
     */
    publid finbl flobt[] gftFrbdtions() {
        rfturn Arrbys.dopyOf(frbdtions, frbdtions.lfngti);
    }

    /**
     * Rfturns b dopy of tif brrby of dolors usfd by tiis grbdifnt.
     * Tif first dolor mbps to tif first vbluf in tif frbdtions brrby,
     * bnd tif lbst dolor mbps to tif lbst vbluf in tif frbdtions brrby.
     *
     * @rfturn b dopy of tif brrby of dolors usfd by tiis grbdifnt
     */
    publid finbl Color[] gftColors() {
        rfturn Arrbys.dopyOf(dolors, dolors.lfngti);
    }

    /**
     * Rfturns tif fnumfrbtfd typf wiidi spfdififs dydling bfibvior.
     *
     * @rfturn tif fnumfrbtfd typf wiidi spfdififs dydling bfibvior
     */
    publid finbl CydlfMftiod gftCydlfMftiod() {
        rfturn dydlfMftiod;
    }

    /**
     * Rfturns tif fnumfrbtfd typf wiidi spfdififs dolor spbdf for
     * intfrpolbtion.
     *
     * @rfturn tif fnumfrbtfd typf wiidi spfdififs dolor spbdf for
     * intfrpolbtion
     */
    publid finbl ColorSpbdfTypf gftColorSpbdf() {
        rfturn dolorSpbdf;
    }

    /**
     * Rfturns b dopy of tif trbnsform bpplifd to tif grbdifnt.
     *
     * <p>
     * Notf tibt if no trbnsform is bpplifd to tif grbdifnt
     * wifn it is drfbtfd, tif idfntity trbnsform is usfd.
     *
     * @rfturn b dopy of tif trbnsform bpplifd to tif grbdifnt
     */
    publid finbl AffinfTrbnsform gftTrbnsform() {
        rfturn nfw AffinfTrbnsform(grbdifntTrbnsform);
    }

    /**
     * Rfturns tif trbnspbrfndy modf for tiis {@dodf Pbint} objfdt.
     *
     * @rfturn {@dodf OPAQUE} if bll dolors usfd by tiis
     *         {@dodf Pbint} objfdt brf opbquf,
     *         {@dodf TRANSLUCENT} if bt lfbst onf of tif
     *         dolors usfd by tiis {@dodf Pbint} objfdt is not opbquf.
     * @sff jbvb.bwt.Trbnspbrfndy
     */
    publid finbl int gftTrbnspbrfndy() {
        rfturn trbnspbrfndy;
    }
}
