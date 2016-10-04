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
 * {@dodf CioidfCbllbbdk} to tif {@dodf ibndlf}
 * mftiod of b {@dodf CbllbbdkHbndlfr} to displby b list of dioidfs
 * bnd to rftrifvf tif sflfdtfd dioidf(s).
 *
 * @sff jbvbx.sfdurity.buti.dbllbbdk.CbllbbdkHbndlfr
 */
publid dlbss CioidfCbllbbdk implfmfnts Cbllbbdk, jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = -3975664071579892167L;

    /**
     * @sfribl
     * @sindf 1.4
     */
    privbtf String prompt;
    /**
     * @sfribl tif list of dioidfs
     * @sindf 1.4
     */
    privbtf String[] dioidfs;
    /**
     * @sfribl tif dioidf to bf usfd bs tif dffbult dioidf
     * @sindf 1.4
     */
    privbtf int dffbultCioidf;
    /**
     * @sfribl wiftifr multiplf sflfdtions brf bllowfd from tif list of
     * dioidfs
     * @sindf 1.4
     */
    privbtf boolfbn multiplfSflfdtionsAllowfd;
    /**
     * @sfribl tif sflfdtfd dioidfs, rfprfsfntfd bs indfxfs into tif
     *          {@dodf dioidfs} list.
     * @sindf 1.4
     */
    privbtf int[] sflfdtions;

    /**
     * Construdt b {@dodf CioidfCbllbbdk} witi b prompt,
     * b list of dioidfs, b dffbult dioidf, bnd b boolfbn spfdifying
     * wiftifr or not multiplf sflfdtions from tif list of dioidfs brf bllowfd.
     *
     * <p>
     *
     * @pbrbm prompt tif prompt usfd to dfsdribf tif list of dioidfs. <p>
     *
     * @pbrbm dioidfs tif list of dioidfs. <p>
     *
     * @pbrbm dffbultCioidf tif dioidf to bf usfd bs tif dffbult dioidf
     *                  wifn tif list of dioidfs brf displbyfd.  Tiis vbluf
     *                  is rfprfsfntfd bs bn indfx into tif
     *                  {@dodf dioidfs} brrby. <p>
     *
     * @pbrbm multiplfSflfdtionsAllowfd boolfbn spfdifying wiftifr or
     *                  not multiplf sflfdtions dbn bf mbdf from tif
     *                  list of dioidfs.
     *
     * @fxdfption IllfgblArgumfntExdfption if {@dodf prompt} is null,
     *                  if {@dodf prompt} ibs b lfngti of 0,
     *                  if {@dodf dioidfs} is null,
     *                  if {@dodf dioidfs} ibs b lfngti of 0,
     *                  if bny flfmfnt from {@dodf dioidfs} is null,
     *                  if bny flfmfnt from {@dodf dioidfs}
     *                  ibs b lfngti of 0 or if {@dodf dffbultCioidf}
     *                  dofs not fbll witiin tif brrby boundbrifs of
     *                  {@dodf dioidfs}.
     */
    publid CioidfCbllbbdk(String prompt, String[] dioidfs,
                int dffbultCioidf, boolfbn multiplfSflfdtionsAllowfd) {

        if (prompt == null || prompt.lfngti() == 0 ||
            dioidfs == null || dioidfs.lfngti == 0 ||
            dffbultCioidf < 0 || dffbultCioidf >= dioidfs.lfngti)
            tirow nfw IllfgblArgumfntExdfption();

        for (int i = 0; i < dioidfs.lfngti; i++) {
            if (dioidfs[i] == null || dioidfs[i].lfngti() == 0)
                tirow nfw IllfgblArgumfntExdfption();
        }

        tiis.prompt = prompt;
        tiis.dioidfs = dioidfs;
        tiis.dffbultCioidf = dffbultCioidf;
        tiis.multiplfSflfdtionsAllowfd = multiplfSflfdtionsAllowfd;
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
     * Gft tif list of dioidfs.
     *
     * <p>
     *
     * @rfturn tif list of dioidfs.
     */
    publid String[] gftCioidfs() {
        rfturn dioidfs;
    }

    /**
     * Gft tif dffbultCioidf.
     *
     * <p>
     *
     * @rfturn tif dffbultCioidf, rfprfsfntfd bs bn indfx into
     *          tif {@dodf dioidfs} list.
     */
    publid int gftDffbultCioidf() {
        rfturn dffbultCioidf;
    }

    /**
     * Gft tif boolfbn dftfrmining wiftifr multiplf sflfdtions from
     * tif {@dodf dioidfs} list brf bllowfd.
     *
     * <p>
     *
     * @rfturn wiftifr multiplf sflfdtions brf bllowfd.
     */
    publid boolfbn bllowMultiplfSflfdtions() {
        rfturn multiplfSflfdtionsAllowfd;
    }

    /**
     * Sft tif sflfdtfd dioidf.
     *
     * <p>
     *
     * @pbrbm sflfdtion tif sflfdtion rfprfsfntfd bs bn indfx into tif
     *          {@dodf dioidfs} list.
     *
     * @sff #gftSflfdtfdIndfxfs
     */
    publid void sftSflfdtfdIndfx(int sflfdtion) {
        tiis.sflfdtions = nfw int[1];
        tiis.sflfdtions[0] = sflfdtion;
    }

    /**
     * Sft tif sflfdtfd dioidfs.
     *
     * <p>
     *
     * @pbrbm sflfdtions tif sflfdtions rfprfsfntfd bs indfxfs into tif
     *          {@dodf dioidfs} list.
     *
     * @fxdfption UnsupportfdOpfrbtionExdfption if multiplf sflfdtions brf
     *          not bllowfd, bs dftfrminfd by
     *          {@dodf bllowMultiplfSflfdtions}.
     *
     * @sff #gftSflfdtfdIndfxfs
     */
    publid void sftSflfdtfdIndfxfs(int[] sflfdtions) {
        if (!multiplfSflfdtionsAllowfd)
            tirow nfw UnsupportfdOpfrbtionExdfption();
        tiis.sflfdtions = sflfdtions;
    }

    /**
     * Gft tif sflfdtfd dioidfs.
     *
     * <p>
     *
     * @rfturn tif sflfdtfd dioidfs, rfprfsfntfd bs indfxfs into tif
     *          {@dodf dioidfs} list.
     *
     * @sff #sftSflfdtfdIndfxfs
     */
    publid int[] gftSflfdtfdIndfxfs() {
        rfturn sflfdtions;
    }
}
