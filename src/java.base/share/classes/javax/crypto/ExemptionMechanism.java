/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.drypto;

import jbvb.sfdurity.AlgoritimPbrbmftfrs;
import jbvb.sfdurity.Providfr;
import jbvb.sfdurity.Kfy;
import jbvb.sfdurity.Sfdurity;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.NoSudiProvidfrExdfption;
import jbvb.sfdurity.InvblidKfyExdfption;
import jbvb.sfdurity.InvblidAlgoritimPbrbmftfrExdfption;
import jbvb.sfdurity.spfd.AlgoritimPbrbmftfrSpfd;

import sun.sfdurity.jdb.GftInstbndf.Instbndf;

/**
 * Tiis dlbss providfs tif fundtionblity of bn fxfmption mfdibnism, fxbmplfs
 * of wiidi brf <i>kfy rfdovfry</i>, <i>kfy wfbkfning</i>, bnd
 * <i>kfy fsdrow</i>.
 *
 * <p>Applidbtions or bpplfts tibt usf bn fxfmption mfdibnism mby bf grbntfd
 * strongfr fndryption dbpbbilitifs tibn tiosf wiidi don't.
 *
 * @sindf 1.4
 */

publid dlbss ExfmptionMfdibnism {

    // Tif providfr
    privbtf Providfr providfr;

    // Tif providfr implfmfntbtion (dflfgbtf)
    privbtf ExfmptionMfdibnismSpi fxmfdiSpi;

    // Tif nbmf of tif fxfmption mfdibnism.
    privbtf String mfdibnism;

    // Flbg wiidi indidbtfs wiftifr tiis ExfmptionMfdibnism
    // rfsult is gfnfrbtfd suddfssfully.
    privbtf boolfbn donf = fblsf;

    // Stbtf informbtion
    privbtf boolfbn initiblizfd = fblsf;

    // Storf bwby tif kfy bt init() timf for lbtfr dompbrison.
    privbtf Kfy kfyStorfd = null;

    /**
     * Crfbtfs b ExfmptionMfdibnism objfdt.
     *
     * @pbrbm fxmfdiSpi tif dflfgbtf
     * @pbrbm providfr tif providfr
     * @pbrbm mfdibnism tif fxfmption mfdibnism
     */
    protfdtfd ExfmptionMfdibnism(ExfmptionMfdibnismSpi fxmfdiSpi,
                                 Providfr providfr,
                                 String mfdibnism) {
        tiis.fxmfdiSpi = fxmfdiSpi;
        tiis.providfr = providfr;
        tiis.mfdibnism = mfdibnism;
    }

    /**
     * Rfturns tif fxfmption mfdibnism nbmf of tiis
     * <dodf>ExfmptionMfdibnism</dodf> objfdt.
     *
     * <p>Tiis is tif sbmf nbmf tibt wbs spfdififd in onf of tif
     * <dodf>gftInstbndf</dodf> dblls tibt drfbtfd tiis
     * <dodf>ExfmptionMfdibnism</dodf> objfdt.
     *
     * @rfturn tif fxfmption mfdibnism nbmf of tiis
     * <dodf>ExfmptionMfdibnism</dodf> objfdt.
     */
    publid finbl String gftNbmf() {
        rfturn tiis.mfdibnism;
    }

    /**
     * Rfturns bn <dodf>ExfmptionMfdibnism</dodf> objfdt tibt implfmfnts tif
     * spfdififd fxfmption mfdibnism blgoritim.
     *
     * <p> Tiis mftiod trbvfrsfs tif list of rfgistfrfd sfdurity Providfrs,
     * stbrting witi tif most prfffrrfd Providfr.
     * A nfw ExfmptionMfdibnism objfdt fndbpsulbting tif
     * ExfmptionMfdibnismSpi implfmfntbtion from tif first
     * Providfr tibt supports tif spfdififd blgoritim is rfturnfd.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd fxfmption
     * mfdibnism.
     * Sff tif ExfmptionMfdibnism sfdtion in tif
     * <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Exfmption">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd fxfmption mfdibnism nbmfs.
     *
     * @rfturn tif nfw <dodf>ExfmptionMfdibnism</dodf> objfdt.
     *
     * @fxdfption NullPointfrExdfption if <dodf>blgoritim</dodf>
     *          is null.
     *
     * @fxdfption NoSudiAlgoritimExdfption if no Providfr supports bn
     *          ExfmptionMfdibnismSpi implfmfntbtion for tif
     *          spfdififd blgoritim.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl ExfmptionMfdibnism gftInstbndf(String blgoritim)
            tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = JdfSfdurity.gftInstbndf("ExfmptionMfdibnism",
                ExfmptionMfdibnismSpi.dlbss, blgoritim);
        rfturn nfw ExfmptionMfdibnism((ExfmptionMfdibnismSpi)instbndf.impl,
                instbndf.providfr, blgoritim);
    }


    /**
     * Rfturns bn <dodf>ExfmptionMfdibnism</dodf> objfdt tibt implfmfnts tif
     * spfdififd fxfmption mfdibnism blgoritim.
     *
     * <p> A nfw ExfmptionMfdibnism objfdt fndbpsulbting tif
     * ExfmptionMfdibnismSpi implfmfntbtion from tif spfdififd providfr
     * is rfturnfd.  Tif spfdififd providfr must bf rfgistfrfd
     * in tif sfdurity providfr list.
     *
     * <p> Notf tibt tif list of rfgistfrfd providfrs mby bf rftrifvfd vib
     * tif {@link Sfdurity#gftProvidfrs() Sfdurity.gftProvidfrs()} mftiod.

     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd fxfmption mfdibnism.
     * Sff tif ExfmptionMfdibnism sfdtion in tif
     * <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Exfmption">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd fxfmption mfdibnism nbmfs.
     *
     * @pbrbm providfr tif nbmf of tif providfr.
     *
     * @rfturn tif nfw <dodf>ExfmptionMfdibnism</dodf> objfdt.
     *
     * @fxdfption NullPointfrExdfption if <dodf>blgoritim</dodf>
     *          is null.
     *
     * @fxdfption NoSudiAlgoritimExdfption if bn ExfmptionMfdibnismSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not
     *          bvbilbblf from tif spfdififd providfr.
     *
     * @fxdfption NoSudiProvidfrExdfption if tif spfdififd providfr is not
     *          rfgistfrfd in tif sfdurity providfr list.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>providfr</dodf>
     *          is null or fmpty.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl ExfmptionMfdibnism gftInstbndf(String blgoritim,
            String providfr) tirows NoSudiAlgoritimExdfption,
            NoSudiProvidfrExdfption {
        Instbndf instbndf = JdfSfdurity.gftInstbndf("ExfmptionMfdibnism",
                ExfmptionMfdibnismSpi.dlbss, blgoritim, providfr);
        rfturn nfw ExfmptionMfdibnism((ExfmptionMfdibnismSpi)instbndf.impl,
                instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns bn <dodf>ExfmptionMfdibnism</dodf> objfdt tibt implfmfnts tif
     * spfdififd fxfmption mfdibnism blgoritim.
     *
     * <p> A nfw ExfmptionMfdibnism objfdt fndbpsulbting tif
     * ExfmptionMfdibnismSpi implfmfntbtion from tif spfdififd Providfr
     * objfdt is rfturnfd.  Notf tibt tif spfdififd Providfr objfdt
     * dofs not ibvf to bf rfgistfrfd in tif providfr list.
     *
     * @pbrbm blgoritim tif stbndbrd nbmf of tif rfqufstfd fxfmption mfdibnism.
     * Sff tif ExfmptionMfdibnism sfdtion in tif
     * <b irff=
     *   "{@dodRoot}/../tfdinotfs/guidfs/sfdurity/StbndbrdNbmfs.itml#Exfmption">
     * Jbvb Cryptogrbpiy Ardiitfdturf Stbndbrd Algoritim Nbmf Dodumfntbtion</b>
     * for informbtion bbout stbndbrd fxfmption mfdibnism nbmfs.
     *
     * @pbrbm providfr tif providfr.
     *
     * @rfturn tif nfw <dodf>ExfmptionMfdibnism</dodf> objfdt.
     *
     * @fxdfption NullPointfrExdfption if <dodf>blgoritim</dodf>
     *          is null.
     *
     * @fxdfption NoSudiAlgoritimExdfption if bn ExfmptionMfdibnismSpi
     *          implfmfntbtion for tif spfdififd blgoritim is not bvbilbblf
     *          from tif spfdififd Providfr objfdt.
     *
     * @fxdfption IllfgblArgumfntExdfption if tif <dodf>providfr</dodf>
     *          is null.
     *
     * @sff jbvb.sfdurity.Providfr
     */
    publid stbtid finbl ExfmptionMfdibnism gftInstbndf(String blgoritim,
            Providfr providfr) tirows NoSudiAlgoritimExdfption {
        Instbndf instbndf = JdfSfdurity.gftInstbndf("ExfmptionMfdibnism",
                ExfmptionMfdibnismSpi.dlbss, blgoritim, providfr);
        rfturn nfw ExfmptionMfdibnism((ExfmptionMfdibnismSpi)instbndf.impl,
                instbndf.providfr, blgoritim);
    }

    /**
     * Rfturns tif providfr of tiis <dodf>ExfmptionMfdibnism</dodf> objfdt.
     *
     * @rfturn tif providfr of tiis <dodf>ExfmptionMfdibnism</dodf> objfdt.
     */
    publid finbl Providfr gftProvidfr() {
        rfturn tiis.providfr;
    }

    /**
     * Rfturns wiftifr tif rfsult blob ibs bffn gfnfrbtfd suddfssfully by tiis
     * fxfmption mfdibnism.
     *
     * <p>Tif mftiod blso mbkfs surf tibt tif kfy pbssfd in is tif sbmf bs
     * tif onf tiis fxfmption mfdibnism usfd in initiblizing bnd gfnfrbting
     * pibsfs.
     *
     * @pbrbm kfy tif kfy tif drypto is going to usf.
     *
     * @rfturn wiftifr tif rfsult blob of tif sbmf kfy ibs bffn gfnfrbtfd
     * suddfssfully by tiis fxfmption mfdibnism; fblsf if <dodf>kfy</dodf>
     * is null.
     *
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd
     * wiilf dftfrmining wiftifr tif rfsult blob ibs bffn gfnfrbtfd suddfssfully
     * by tiis fxfmption mfdibnism objfdt.
     */
    publid finbl boolfbn isCryptoAllowfd(Kfy kfy)
            tirows ExfmptionMfdibnismExdfption {
        boolfbn rft = fblsf;
        if (donf && (kfy != null)) {
            // Cifdk if tif kfy pbssfd in is tif sbmf bs tif onf
            // tiis fxfmption mfdibnism usfd.
            rft = kfyStorfd.fqubls(kfy);
        }
        rfturn rft;
     }

    /**
     * Rfturns tif lfngti in bytfs tibt bn output bufffr would nffd to bf in
     * ordfr to iold tif rfsult of tif nfxt
     * {@link #gfnExfmptionBlob(bytf[]) gfnExfmptionBlob}
     * opfrbtion, givfn tif input lfngti <dodf>inputLfn</dodf> (in bytfs).
     *
     * <p>Tif bdtubl output lfngti of tif nfxt
     * {@link #gfnExfmptionBlob(bytf[]) gfnExfmptionBlob}
     * dbll mby bf smbllfr tibn tif lfngti rfturnfd by tiis mftiod.
     *
     * @pbrbm inputLfn tif input lfngti (in bytfs)
     *
     * @rfturn tif rfquirfd output bufffr sizf (in bytfs)
     *
     * @fxdfption IllfgblStbtfExdfption if tiis fxfmption mfdibnism is in b
     * wrong stbtf (f.g., ibs not yft bffn initiblizfd)
     */
    publid finbl int gftOutputSizf(int inputLfn) tirows IllfgblStbtfExdfption {
        if (!initiblizfd) {
            tirow nfw IllfgblStbtfExdfption(
                "ExfmptionMfdibnism not initiblizfd");
        }
        if (inputLfn < 0) {
            tirow nfw IllfgblArgumfntExdfption(
                "Input sizf must bf fqubl to " + "or grfbtfr tibn zfro");
        }
        rfturn fxmfdiSpi.fnginfGftOutputSizf(inputLfn);
    }

    /**
     * Initiblizfs tiis fxfmption mfdibnism witi b kfy.
     *
     * <p>If tiis fxfmption mfdibnism rfquirfs bny blgoritim pbrbmftfrs
     * tibt dbnnot bf dfrivfd from tif givfn <dodf>kfy</dodf>, tif
     * undfrlying fxfmption mfdibnism implfmfntbtion is supposfd to
     * gfnfrbtf tif rfquirfd pbrbmftfrs itsflf (using providfr-spfdifid
     * dffbult vblufs); in tif dbsf tibt blgoritim pbrbmftfrs must bf
     * spfdififd by tif dbllfr, bn <dodf>InvblidKfyExdfption</dodf> is rbisfd.
     *
     * @pbrbm kfy tif kfy for tiis fxfmption mfdibnism
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * tiis fxfmption mfdibnism.
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd in tif
     * prodfss of initiblizing.
     */
    publid finbl void init(Kfy kfy)
            tirows InvblidKfyExdfption, ExfmptionMfdibnismExdfption {
        donf = fblsf;
        initiblizfd = fblsf;

        kfyStorfd = kfy;
        fxmfdiSpi.fnginfInit(kfy);
        initiblizfd = truf;
    }

    /**
     * Initiblizfs tiis fxfmption mfdibnism witi b kfy bnd b sft of blgoritim
     * pbrbmftfrs.
     *
     * <p>If tiis fxfmption mfdibnism rfquirfs bny blgoritim pbrbmftfrs
     * bnd <dodf>pbrbms</dodf> is null, tif undfrlying fxfmption
     * mfdibnism implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd
     * pbrbmftfrs itsflf (using providfr-spfdifid dffbult vblufs); in tif dbsf
     * tibt blgoritim pbrbmftfrs must bf spfdififd by tif dbllfr, bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> is rbisfd.
     *
     * @pbrbm kfy tif kfy for tiis fxfmption mfdibnism
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * tiis fxfmption mfdibnism.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis fxfmption mfdibnism.
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd in tif
     * prodfss of initiblizing.
     */
    publid finbl void init(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption,
            ExfmptionMfdibnismExdfption {
        donf = fblsf;
        initiblizfd = fblsf;

        kfyStorfd = kfy;
        fxmfdiSpi.fnginfInit(kfy, pbrbms);
        initiblizfd = truf;
    }

    /**
     * Initiblizfs tiis fxfmption mfdibnism witi b kfy bnd b sft of blgoritim
     * pbrbmftfrs.
     *
     * <p>If tiis fxfmption mfdibnism rfquirfs bny blgoritim pbrbmftfrs
     * bnd <dodf>pbrbms</dodf> is null, tif undfrlying fxfmption mfdibnism
     * implfmfntbtion is supposfd to gfnfrbtf tif rfquirfd pbrbmftfrs itsflf
     * (using providfr-spfdifid dffbult vblufs); in tif dbsf tibt blgoritim
     * pbrbmftfrs must bf spfdififd by tif dbllfr, bn
     * <dodf>InvblidAlgoritimPbrbmftfrExdfption</dodf> is rbisfd.
     *
     * @pbrbm kfy tif kfy for tiis fxfmption mfdibnism
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * tiis fxfmption mfdibnism.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis fxfmption mfdibnism.
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd in tif
     * prodfss of initiblizing.
     */
    publid finbl void init(Kfy kfy, AlgoritimPbrbmftfrs pbrbms)
            tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption,
            ExfmptionMfdibnismExdfption {
        donf = fblsf;
        initiblizfd = fblsf;

        kfyStorfd = kfy;
        fxmfdiSpi.fnginfInit(kfy, pbrbms);
        initiblizfd = truf;
    }

    /**
     * Gfnfrbtfs tif fxfmption mfdibnism kfy blob.
     *
     * @rfturn tif nfw bufffr witi tif rfsult kfy blob.
     *
     * @fxdfption IllfgblStbtfExdfption if tiis fxfmption mfdibnism is in
     * b wrong stbtf (f.g., ibs not bffn initiblizfd).
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd in tif
     * prodfss of gfnfrbting.
     */
    publid finbl bytf[] gfnExfmptionBlob() tirows IllfgblStbtfExdfption,
            ExfmptionMfdibnismExdfption {
        if (!initiblizfd) {
            tirow nfw IllfgblStbtfExdfption(
                "ExfmptionMfdibnism not initiblizfd");
        }
        bytf[] blob = fxmfdiSpi.fnginfGfnExfmptionBlob();
        donf = truf;
        rfturn blob;
    }

    /**
     * Gfnfrbtfs tif fxfmption mfdibnism kfy blob, bnd storfs tif rfsult in
     * tif <dodf>output</dodf> bufffr.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * @pbrbm output tif bufffr for tif rfsult
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis fxfmption mfdibnism is in
     * b wrong stbtf (f.g., ibs not bffn initiblizfd).
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult.
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd in tif
     * prodfss of gfnfrbting.
     */
    publid finbl int gfnExfmptionBlob(bytf[] output)
            tirows IllfgblStbtfExdfption, SiortBufffrExdfption,
            ExfmptionMfdibnismExdfption {
        if (!initiblizfd) {
            tirow nfw IllfgblStbtfExdfption
            ("ExfmptionMfdibnism not initiblizfd");
        }
        int n = fxmfdiSpi.fnginfGfnExfmptionBlob(output, 0);
        donf = truf;
        rfturn n;
    }

    /**
     * Gfnfrbtfs tif fxfmption mfdibnism kfy blob, bnd storfs tif rfsult in
     * tif <dodf>output</dodf> bufffr, stbrting bt <dodf>outputOffsft</dodf>
     * indlusivf.
     *
     * <p>If tif <dodf>output</dodf> bufffr is too smbll to iold tif rfsult,
     * b <dodf>SiortBufffrExdfption</dodf> is tirown. In tiis dbsf, rfpfbt tiis
     * dbll witi b lbrgfr output bufffr. Usf
     * {@link #gftOutputSizf(int) gftOutputSizf} to dftfrminf iow big
     * tif output bufffr siould bf.
     *
     * @pbrbm output tif bufffr for tif rfsult
     * @pbrbm outputOffsft tif offsft in <dodf>output</dodf> wifrf tif rfsult
     * is storfd
     *
     * @rfturn tif numbfr of bytfs storfd in <dodf>output</dodf>
     *
     * @fxdfption IllfgblStbtfExdfption if tiis fxfmption mfdibnism is in
     * b wrong stbtf (f.g., ibs not bffn initiblizfd).
     * @fxdfption SiortBufffrExdfption if tif givfn output bufffr is too smbll
     * to iold tif rfsult.
     * @fxdfption ExfmptionMfdibnismExdfption if problfm(s) fndountfrfd in tif
     * prodfss of gfnfrbting.
     */
    publid finbl int gfnExfmptionBlob(bytf[] output, int outputOffsft)
            tirows IllfgblStbtfExdfption, SiortBufffrExdfption,
            ExfmptionMfdibnismExdfption {
        if (!initiblizfd) {
            tirow nfw IllfgblStbtfExdfption
            ("ExfmptionMfdibnism not initiblizfd");
        }
        int n = fxmfdiSpi.fnginfGfnExfmptionBlob(output, outputOffsft);
        donf = truf;
        rfturn n;
    }

    /**
     * Ensurfs tibt tif kfy storfd bwby by tiis ExfmptionMfdibnism
     * objfdt will bf wipfd out wifn tifrf brf no morf rfffrfndfs to it.
     */
    protfdtfd void finblizf() {
        kfyStorfd = null;
        // Arf tifrf bnytiing flsf wf dould do?
    }
}
