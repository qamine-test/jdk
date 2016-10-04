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
 * {@dodf TfxtInputCbllbbdk} to tif {@dodf ibndlf}
 * mftiod of b {@dodf CbllbbdkHbndlfr} to rftrifvf gfnfrid tfxt
 * informbtion.
 *
 * @sff jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr
 */
publid dlbss TfxtInputCbllbbdk implfmfnts Cbllbbdk, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -8064222478852811804L;

    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String prompt;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String dffbultTfxt;
    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String inputTfxt;

    /**
     * Construdt b {@dodf TfxtInputCbllbbdk} witi b prompt.
     *
     * <p>
     *
     * @pbrbm prompt tif prompt usfd to rfqufst tif informbtion.
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf prompt} is null
     *                  or if {@dodf prompt} ibs b lfngti of 0.
     */
    publid TfxtInputCbllbbdk(String prompt) {
        if (prompt == null || prompt.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption();
        tiis.prompt = prompt;
    }

    /**
     * Construdt b {@dodf TfxtInputCbllbbdk} witi b prompt
     * bnd dffbult input vbluf.
     *
     * <p>
     *
     * @pbrbm prompt tif prompt usfd to rfqufst tif informbtion. <p>
     *
     * @pbrbm dffbultTfxt tif tfxt to bf usfd bs tif dffbult tfxt displbyfd
     *                  witi tif prompt.
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf prompt} is null,
     *                  if {@dodf prompt} ibs b lfngti of 0,
     *                  if {@dodf dffbultTfxt} is null
     *                  or if {@dodf dffbultTfxt} ibs b lfngti of 0.
     */
    publid TfxtInputCbllbbdk(String prompt, String dffbultTfxt) {
        if (prompt == null || prompt.lfngti() == 0 ||
            dffbultTfxt == null || dffbultTfxt.lfngti() == 0)
            tirow nfw IllfgblArgumfntExdfption();

        tiis.prompt = prompt;
        tiis.dffbultTfxt = dffbultTfxt;
    }

    /**
     * Gft tif prompt.
     *
     * <p>
     *
     * @rfturn tif prompt.
     */
    publid String gftPrompt() {
        rfturn prompt;
    }

    /**
     * Gft tif dffbult tfxt.
     *
     * <p>
     *
     * @rfturn tif dffbult tfxt, or null if tiis {@dodf TfxtInputCbllbbdk}
     *          wbs not instbntibtfd witi {@dodf dffbultTfxt}.
     */
    publid String gftDffbultTfxt() {
        rfturn dffbultTfxt;
    }

    /**
     * Sft tif rftrifvfd tfxt.
     *
     * <p>
     *
     * @pbrbm tfxt tif rftrifvfd tfxt, wiidi mby bf null.
     *
     * @sff #gftTfxt
     */
    publid void sftTfxt(String tfxt) {
        tiis.inputTfxt = tfxt;
    }

    /**
     * Gft tif rftrifvfd tfxt.
     *
     * <p>
     *
     * @rfturn tif rftrifvfd tfxt, wiidi mby bf null.
     *
     * @sff #sftTfxt
     */
    publid String gftTfxt() {
        rfturn inputTfxt;
    }
}
