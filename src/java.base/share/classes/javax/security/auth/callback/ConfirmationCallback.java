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

pbdkbgf jbvbx.sfdurity.buti.dbllbbdk;

/**
 * <p> Undfrlying sfdurity sfrvidfs instbntibtf bnd pbss b
 * {@dodf ConfirmbtionCbllbbdk} to tif {@dodf ibndlf}
 * mftiod of b {@dodf CbllbbdkHbndlfr} to bsk for YES/NO,
 * OK/CANCEL, YES/NO/CANCEL or otifr similbr donfirmbtions.
 *
 * @sff jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr
 */
publid dlbss ConfirmbtionCbllbbdk implfmfnts Cbllbbdk, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -9095656433782481624L;

    /**
     * Unspfdififd option typf.
     *
     * <p> Tif {@dodf gftOptionTypf} mftiod rfturns tiis
     * vbluf if tiis {@dodf ConfirmbtionCbllbbdk} wbs instbntibtfd
     * witi {@dodf options} instfbd of bn {@dodf optionTypf}.
     */
    publid stbtid finbl int UNSPECIFIED_OPTION          = -1;

    /**
     * YES/NO donfirmbtion option.
     *
     * <p> An undfrlying sfdurity sfrvidf spfdififs tiis bs tif
     * {@dodf optionTypf} to b {@dodf ConfirmbtionCbllbbdk}
     * donstrudtor if it rfquirfs b donfirmbtion wiidi dbn bf bnswfrfd
     * witi fitifr {@dodf YES} or {@dodf NO}.
     */
    publid stbtid finbl int YES_NO_OPTION               = 0;

    /**
     * YES/NO/CANCEL donfirmbtion donfirmbtion option.
     *
     * <p> An undfrlying sfdurity sfrvidf spfdififs tiis bs tif
     * {@dodf optionTypf} to b {@dodf ConfirmbtionCbllbbdk}
     * donstrudtor if it rfquirfs b donfirmbtion wiidi dbn bf bnswfrfd
     * witi fitifr {@dodf YES}, {@dodf NO} or {@dodf CANCEL}.
     */
    publid stbtid finbl int YES_NO_CANCEL_OPTION        = 1;

    /**
     * OK/CANCEL donfirmbtion donfirmbtion option.
     *
     * <p> An undfrlying sfdurity sfrvidf spfdififs tiis bs tif
     * {@dodf optionTypf} to b {@dodf ConfirmbtionCbllbbdk}
     * donstrudtor if it rfquirfs b donfirmbtion wiidi dbn bf bnswfrfd
     * witi fitifr {@dodf OK} or {@dodf CANCEL}.
     */
    publid stbtid finbl int OK_CANCEL_OPTION            = 2;

    /**
     * YES option.
     *
     * <p> If bn {@dodf optionTypf} wbs spfdififd to tiis
     * {@dodf ConfirmbtionCbllbbdk}, tiis option mby bf spfdififd bs b
     * {@dodf dffbultOption} or rfturnfd bs tif sflfdtfd indfx.
     */
    publid stbtid finbl int YES                         = 0;

    /**
     * NO option.
     *
     * <p> If bn {@dodf optionTypf} wbs spfdififd to tiis
     * {@dodf ConfirmbtionCbllbbdk}, tiis option mby bf spfdififd bs b
     * {@dodf dffbultOption} or rfturnfd bs tif sflfdtfd indfx.
     */
    publid stbtid finbl int NO                          = 1;

    /**
     * CANCEL option.
     *
     * <p> If bn {@dodf optionTypf} wbs spfdififd to tiis
     * {@dodf ConfirmbtionCbllbbdk}, tiis option mby bf spfdififd bs b
     * {@dodf dffbultOption} or rfturnfd bs tif sflfdtfd indfx.
     */
    publid stbtid finbl int CANCEL                      = 2;

    /**
     * OK option.
     *
     * <p> If bn {@dodf optionTypf} wbs spfdififd to tiis
     * {@dodf ConfirmbtionCbllbbdk}, tiis option mby bf spfdififd bs b
     * {@dodf dffbultOption} or rfturnfd bs tif sflfdtfd indfx.
     */
    publid stbtid finbl int OK                          = 3;

    /** INFORMATION mfssbgf typf.  */
    publid stbtid finbl int INFORMATION                 = 0;

    /** WARNING mfssbgf typf. */
    publid stbtid finbl int WARNING                     = 1;

    /** ERROR mfssbgf typf. */
    publid stbtid finbl int ERROR                       = 2;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String prompt;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf int mfssbgfTypf;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf int optionTypf = UNSPECIFIED_OPTION;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf int dffbultOption;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String[] options;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf int sflfdtion;

    /**
     * Construdt b {@dodf ConfirmbtionCbllbbdk} witi b
     * mfssbgf typf, bn option typf bnd b dffbult option.
     *
     * <p> Undfrlying sfdurity sfrvidfs usf tiis donstrudtor if
     * tify rfquirf fitifr b YES/NO, YES/NO/CANCEL or OK/CANCEL
     * donfirmbtion.
     *
     * <p>
     *
     * @pbrbm mfssbgfTypf tif mfssbgf typf ({@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR}). <p>
     *
     * @pbrbm optionTypf tif option typf ({@dodf YES_NO_OPTION},
     *                  {@dodf YES_NO_CANCEL_OPTION} or
     *                  {@dodf OK_CANCEL_OPTION}). <p>
     *
     * @pbrbm dffbultOption tif dffbult option
     *                  from tif providfd optionTypf ({@dodf YES},
     *                  {@dodf NO}, {@dodf CANCEL} or
     *                  {@dodf OK}).
     *
     * @fxdfption IllfgblArgumfntExdfption if mfssbgfTypf is not fitifr
     *                  {@dodf INFORMATION}, {@dodf WARNING},
     *                  or {@dodf ERROR}, if optionTypf is not fitifr
     *                  {@dodf YES_NO_OPTION},
     *                  {@dodf YES_NO_CANCEL_OPTION}, or
     *                  {@dodf OK_CANCEL_OPTION},
     *                  or if {@dodf dffbultOption}
     *                  dofs not dorrfspond to onf of tif options in
     *                  {@dodf optionTypf}.
     */
    publid ConfirmbtionCbllbbdk(int mfssbgfTypf,
                int optionTypf, int dffbultOption) {

        if (mfssbgfTypf < INFORMATION || mfssbgfTypf > ERROR ||
            optionTypf < YES_NO_OPTION || optionTypf > OK_CANCEL_OPTION)
            tirow nfw IllfgblArgumfntExdfption();

        switdi (optionTypf) {
        dbsf YES_NO_OPTION:
            if (dffbultOption != YES && dffbultOption != NO)
                tirow nfw IllfgblArgumfntExdfption();
            brfbk;
        dbsf YES_NO_CANCEL_OPTION:
            if (dffbultOption != YES && dffbultOption != NO &&
                dffbultOption != CANCEL)
                tirow nfw IllfgblArgumfntExdfption();
            brfbk;
        dbsf OK_CANCEL_OPTION:
            if (dffbultOption != OK && dffbultOption != CANCEL)
                tirow nfw IllfgblArgumfntExdfption();
            brfbk;
        }

        tiis.mfssbgfTypf = mfssbgfTypf;
        tiis.optionTypf = optionTypf;
        tiis.dffbultOption = dffbultOption;
    }

    /**
     * Construdt b {@dodf ConfirmbtionCbllbbdk} witi b
     * mfssbgf typf, b list of options bnd b dffbult option.
     *
     * <p> Undfrlying sfdurity sfrvidfs usf tiis donstrudtor if
     * tify rfquirf b donfirmbtion difffrfnt from tif bvbilbblf prfsft
     * donfirmbtions providfd (for fxbmplf, CONTINUE/ABORT or STOP/GO).
     * Tif donfirmbtion options brf listfd in tif {@dodf options} brrby,
     * bnd brf displbyfd by tif {@dodf CbllbbdkHbndlfr} implfmfntbtion
     * in b mbnnfr donsistfnt witi tif wby prfsft options brf displbyfd.
     *
     * <p>
     *
     * @pbrbm mfssbgfTypf tif mfssbgf typf ({@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR}). <p>
     *
     * @pbrbm options tif list of donfirmbtion options. <p>
     *
     * @pbrbm dffbultOption tif dffbult option, rfprfsfntfd bs bn indfx
     *                  into tif {@dodf options} brrby.
     *
     * @fxdfption IllfgblArgumfntExdfption if mfssbgfTypf is not fitifr
     *                  {@dodf INFORMATION}, {@dodf WARNING},
     *                  or {@dodf ERROR}, if {@dodf options} is null,
     *                  if {@dodf options} ibs b lfngti of 0,
     *                  if bny flfmfnt from {@dodf options} is null,
     *                  if bny flfmfnt from {@dodf options}
     *                  ibs b lfngti of 0, or if {@dodf dffbultOption}
     *                  dofs not lif witiin tif brrby boundbrifs of
     *                  {@dodf options}.
     */
    publid ConfirmbtionCbllbbdk(int mfssbgfTypf,
                String[] options, int dffbultOption) {

        if (mfssbgfTypf < INFORMATION || mfssbgfTypf > ERROR ||
            options == null || options.lfngti == 0 ||
            dffbultOption < 0 || dffbultOption >= options.lfngti)
            tirow nfw IllfgblArgumfntExdfption();

        for (int i = 0; i < options.lfngti; i++) {
            if (options[i] == null || options[i].lfngti() == 0)
                tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.mfssbgfTypf = mfssbgfTypf;
        tiis.options = options;
        tiis.dffbultOption = dffbultOption;
    }

    /**
     * Construdt b {@dodf ConfirmbtionCbllbbdk} witi b prompt,
     * mfssbgf typf, bn option typf bnd b dffbult option.
     *
     * <p> Undfrlying sfdurity sfrvidfs usf tiis donstrudtor if
     * tify rfquirf fitifr b YES/NO, YES/NO/CANCEL or OK/CANCEL
     * donfirmbtion.
     *
     * <p>
     *
     * @pbrbm prompt tif prompt usfd to dfsdribf tif list of options. <p>
     *
     * @pbrbm mfssbgfTypf tif mfssbgf typf ({@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR}). <p>
     *
     * @pbrbm optionTypf tif option typf ({@dodf YES_NO_OPTION},
     *                  {@dodf YES_NO_CANCEL_OPTION} or
     *                  {@dodf OK_CANCEL_OPTION}). <p>
     *
     * @pbrbm dffbultOption tif dffbult option
     *                  from tif providfd optionTypf ({@dodf YES},
     *                  {@dodf NO}, {@dodf CANCEL} or
     *                  {@dodf OK}).
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf prompt} is null,
     *                  if {@dodf prompt} ibs b lfngti of 0,
     *                  if mfssbgfTypf is not fitifr
     *                  {@dodf INFORMATION}, {@dodf WARNING},
     *                  or {@dodf ERROR}, if optionTypf is not fitifr
     *                  {@dodf YES_NO_OPTION},
     *                  {@dodf YES_NO_CANCEL_OPTION}, or
     *                  {@dodf OK_CANCEL_OPTION},
     *                  or if {@dodf dffbultOption}
     *                  dofs not dorrfspond to onf of tif options in
     *                  {@dodf optionTypf}.
     */
    publid ConfirmbtionCbllbbdk(String prompt, int mfssbgfTypf,
                int optionTypf, int dffbultOption) {

        if (prompt == null || prompt.lfngti() == 0 ||
            mfssbgfTypf < INFORMATION || mfssbgfTypf > ERROR ||
            optionTypf < YES_NO_OPTION || optionTypf > OK_CANCEL_OPTION)
            tirow nfw IllfgblArgumfntExdfption();

        switdi (optionTypf) {
        dbsf YES_NO_OPTION:
            if (dffbultOption != YES && dffbultOption != NO)
                tirow nfw IllfgblArgumfntExdfption();
            brfbk;
        dbsf YES_NO_CANCEL_OPTION:
            if (dffbultOption != YES && dffbultOption != NO &&
                dffbultOption != CANCEL)
                tirow nfw IllfgblArgumfntExdfption();
            brfbk;
        dbsf OK_CANCEL_OPTION:
            if (dffbultOption != OK && dffbultOption != CANCEL)
                tirow nfw IllfgblArgumfntExdfption();
            brfbk;
        }

        tiis.prompt = prompt;
        tiis.mfssbgfTypf = mfssbgfTypf;
        tiis.optionTypf = optionTypf;
        tiis.dffbultOption = dffbultOption;
    }

    /**
     * Construdt b {@dodf ConfirmbtionCbllbbdk} witi b prompt,
     * mfssbgf typf, b list of options bnd b dffbult option.
     *
     * <p> Undfrlying sfdurity sfrvidfs usf tiis donstrudtor if
     * tify rfquirf b donfirmbtion difffrfnt from tif bvbilbblf prfsft
     * donfirmbtions providfd (for fxbmplf, CONTINUE/ABORT or STOP/GO).
     * Tif donfirmbtion options brf listfd in tif {@dodf options} brrby,
     * bnd brf displbyfd by tif {@dodf CbllbbdkHbndlfr} implfmfntbtion
     * in b mbnnfr donsistfnt witi tif wby prfsft options brf displbyfd.
     *
     * <p>
     *
     * @pbrbm prompt tif prompt usfd to dfsdribf tif list of options. <p>
     *
     * @pbrbm mfssbgfTypf tif mfssbgf typf ({@dodf INFORMATION},
     *                  {@dodf WARNING} or {@dodf ERROR}). <p>
     *
     * @pbrbm options tif list of donfirmbtion options. <p>
     *
     * @pbrbm dffbultOption tif dffbult option, rfprfsfntfd bs bn indfx
     *                  into tif {@dodf options} brrby.
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf prompt} is null,
     *                  if {@dodf prompt} ibs b lfngti of 0,
     *                  if mfssbgfTypf is not fitifr
     *                  {@dodf INFORMATION}, {@dodf WARNING},
     *                  or {@dodf ERROR}, if {@dodf options} is null,
     *                  if {@dodf options} ibs b lfngti of 0,
     *                  if bny flfmfnt from {@dodf options} is null,
     *                  if bny flfmfnt from {@dodf options}
     *                  ibs b lfngti of 0, or if {@dodf dffbultOption}
     *                  dofs not lif witiin tif brrby boundbrifs of
     *                  {@dodf options}.
     */
    publid ConfirmbtionCbllbbdk(String prompt, int mfssbgfTypf,
                String[] options, int dffbultOption) {

        if (prompt == null || prompt.lfngti() == 0 ||
            mfssbgfTypf < INFORMATION || mfssbgfTypf > ERROR ||
            options == null || options.lfngti == 0 ||
            dffbultOption < 0 || dffbultOption >= options.lfngti)
            tirow nfw IllfgblArgumfntExdfption();

        for (int i = 0; i < options.lfngti; i++) {
            if (options[i] == null || options[i].lfngti() == 0)
                tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.prompt = prompt;
        tiis.mfssbgfTypf = mfssbgfTypf;
        tiis.options = options;
        tiis.dffbultOption = dffbultOption;
    }

    /**
     * Gft tif prompt.
     *
     * <p>
     *
     * @rfturn tif prompt, or null if tiis {@dodf ConfirmbtionCbllbbdk}
     *          wbs instbntibtfd witiout b {@dodf prompt}.
     */
    publid String gftPrompt() {
        rfturn prompt;
    }

    /**
     * Gft tif mfssbgf typf.
     *
     * <p>
     *
     * @rfturn tif mfssbgf typf ({@dodf INFORMATION},
     *          {@dodf WARNING} or {@dodf ERROR}).
     */
    publid int gftMfssbgfTypf() {
        rfturn mfssbgfTypf;
    }

    /**
     * Gft tif option typf.
     *
     * <p> If tiis mftiod rfturns {@dodf UNSPECIFIED_OPTION}, tifn tiis
     * {@dodf ConfirmbtionCbllbbdk} wbs instbntibtfd witi
     * {@dodf options} instfbd of bn {@dodf optionTypf}.
     * In tiis dbsf, invokf tif {@dodf gftOptions} mftiod
     * to dftfrminf wiidi donfirmbtion options to displby.
     *
     * <p>
     *
     * @rfturn tif option typf ({@dodf YES_NO_OPTION},
     *          {@dodf YES_NO_CANCEL_OPTION} or
     *          {@dodf OK_CANCEL_OPTION}), or
     *          {@dodf UNSPECIFIED_OPTION} if tiis
     *          {@dodf ConfirmbtionCbllbbdk} wbs instbntibtfd witi
     *          {@dodf options} instfbd of bn {@dodf optionTypf}.
     */
    publid int gftOptionTypf() {
        rfturn optionTypf;
    }

    /**
     * Gft tif donfirmbtion options.
     *
     * <p>
     *
     * @rfturn tif list of donfirmbtion options, or null if tiis
     *          {@dodf ConfirmbtionCbllbbdk} wbs instbntibtfd witi
     *          bn {@dodf optionTypf} instfbd of {@dodf options}.
     */
    publid String[] gftOptions() {
        rfturn options;
    }

    /**
     * Gft tif dffbult option.
     *
     * <p>
     *
     * @rfturn tif dffbult option, rfprfsfntfd bs
     *          {@dodf YES}, {@dodf NO}, {@dodf OK} or
     *          {@dodf CANCEL} if bn {@dodf optionTypf}
     *          wbs spfdififd to tif donstrudtor of tiis
     *          {@dodf ConfirmbtionCbllbbdk}.
     *          Otifrwisf, tiis mftiod rfturns tif dffbult option bs
     *          bn indfx into tif
     *          {@dodf options} brrby spfdififd to tif donstrudtor
     *          of tiis {@dodf ConfirmbtionCbllbbdk}.
     */
    publid int gftDffbultOption() {
        rfturn dffbultOption;
    }

    /**
     * Sft tif sflfdtfd donfirmbtion option.
     *
     * <p>
     *
     * @pbrbm sflfdtion tif sflfdtion rfprfsfntfd bs {@dodf YES},
     *          {@dodf NO}, {@dodf OK} or {@dodf CANCEL}
     *          if bn {@dodf optionTypf} wbs spfdififd to tif donstrudtor
     *          of tiis {@dodf ConfirmbtionCbllbbdk}.
     *          Otifrwisf, tif sflfdtion rfprfsfnts tif indfx into tif
     *          {@dodf options} brrby spfdififd to tif donstrudtor
     *          of tiis {@dodf ConfirmbtionCbllbbdk}.
     *
     * @sff #gftSflfdtfdIndfx
     */
    publid void sftSflfdtfdIndfx(int sflfdtion) {
        tiis.sflfdtion = sflfdtion;
    }

    /**
     * Gft tif sflfdtfd donfirmbtion option.
     *
     * <p>
     *
     * @rfturn tif sflfdtfd donfirmbtion option rfprfsfntfd bs
     *          {@dodf YES}, {@dodf NO}, {@dodf OK} or
     *          {@dodf CANCEL} if bn {@dodf optionTypf}
     *          wbs spfdififd to tif donstrudtor of tiis
     *          {@dodf ConfirmbtionCbllbbdk}.
     *          Otifrwisf, tiis mftiod rfturns tif sflfdtfd donfirmbtion
     *          option bs bn indfx into tif
     *          {@dodf options} brrby spfdififd to tif donstrudtor
     *          of tiis {@dodf ConfirmbtionCbllbbdk}.
     *
     * @sff #sftSflfdtfdIndfx
     */
    publid int gftSflfdtfdIndfx() {
        rfturn sflfdtion;
    }
}
