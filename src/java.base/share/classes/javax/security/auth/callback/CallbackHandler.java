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
 * <p> An bpplidbtion implfmfnts b {@dodf CbllbbdkHbndlfr} bnd pbssfs
 * it to undfrlying sfdurity sfrvidfs so tibt tify mby intfrbdt witi
 * tif bpplidbtion to rftrifvf spfdifid butifntidbtion dbtb,
 * sudi bs usfrnbmfs bnd pbsswords, or to displby dfrtbin informbtion,
 * sudi bs frror bnd wbrning mfssbgfs.
 *
 * <p> CbllbbdkHbndlfrs brf implfmfntfd in bn bpplidbtion-dfpfndfnt fbsiion.
 * For fxbmplf, implfmfntbtions for bn bpplidbtion witi b grbpiidbl usfr
 * intfrfbdf (GUI) mby pop up windows to prompt for rfqufstfd informbtion
 * or to displby frror mfssbgfs.  An implfmfntbtion mby blso dioosf to obtbin
 * rfqufstfd informbtion from bn bltfrnbtf sourdf witiout bsking tif fnd usfr.
 *
 * <p> Undfrlying sfdurity sfrvidfs mbkf rfqufsts for difffrfnt typfs
 * of informbtion by pbssing individubl Cbllbbdks to tif
 * {@dodf CbllbbdkHbndlfr}.  Tif {@dodf CbllbbdkHbndlfr}
 * implfmfntbtion dfdidfs iow to rftrifvf bnd displby informbtion
 * dfpfnding on tif Cbllbbdks pbssfd to it.  For fxbmplf,
 * if tif undfrlying sfrvidf nffds b usfrnbmf bnd pbssword to
 * butifntidbtf b usfr, it usfs b {@dodf NbmfCbllbbdk} bnd
 * {@dodf PbsswordCbllbbdk}.  Tif {@dodf CbllbbdkHbndlfr}
 * dbn tifn dioosf to prompt for b usfrnbmf bnd pbssword sfriblly,
 * or to prompt for boti in b singlf window.
 *
 * <p> A dffbult {@dodf CbllbbdkHbndlfr} dlbss implfmfntbtion
 * mby bf spfdififd by sftting tif vbluf of tif
 * {@dodf buti.login.dffbultCbllbbdkHbndlfr} sfdurity propfrty.
 *
 * <p> If tif sfdurity propfrty is sft to tif fully qublififd nbmf of b
 * {@dodf CbllbbdkHbndlfr} implfmfntbtion dlbss,
 * tifn b {@dodf LoginContfxt} will lobd tif spfdififd
 * {@dodf CbllbbdkHbndlfr} bnd pbss it to tif undfrlying LoginModulfs.
 * Tif {@dodf LoginContfxt} only lobds tif dffbult ibndlfr
 * if it wbs not providfd onf.
 *
 * <p> All dffbult ibndlfr implfmfntbtions must providf b publid
 * zfro-brgumfnt donstrudtor.
 *
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */
publid intfrfbdf CbllbbdkHbndlfr {

    /**
     * <p> Rftrifvf or displby tif informbtion rfqufstfd in tif
     * providfd Cbllbbdks.
     *
     * <p> Tif {@dodf ibndlf} mftiod implfmfntbtion difdks tif
     * instbndf(s) of tif {@dodf Cbllbbdk} objfdt(s) pbssfd in
     * to rftrifvf or displby tif rfqufstfd informbtion.
     * Tif following fxbmplf is providfd to iflp dfmonstrbtf wibt bn
     * {@dodf ibndlf} mftiod implfmfntbtion migit look likf.
     * Tiis fxbmplf dodf is for guidbndf only.  Mbny dftbils,
     * indluding propfr frror ibndling, brf lfft out for simplidity.
     *
     * <prf>{@dodf
     * publid void ibndlf(Cbllbbdk[] dbllbbdks)
     * tirows IOExdfption, UnsupportfdCbllbbdkExdfption {
     *
     *   for (int i = 0; i < dbllbbdks.lfngti; i++) {
     *      if (dbllbbdks[i] instbndfof TfxtOutputCbllbbdk) {
     *
     *          // displby tif mfssbgf bddording to tif spfdififd typf
     *          TfxtOutputCbllbbdk tod = (TfxtOutputCbllbbdk)dbllbbdks[i];
     *          switdi (tod.gftMfssbgfTypf()) {
     *          dbsf TfxtOutputCbllbbdk.INFORMATION:
     *              Systfm.out.println(tod.gftMfssbgf());
     *              brfbk;
     *          dbsf TfxtOutputCbllbbdk.ERROR:
     *              Systfm.out.println("ERROR: " + tod.gftMfssbgf());
     *              brfbk;
     *          dbsf TfxtOutputCbllbbdk.WARNING:
     *              Systfm.out.println("WARNING: " + tod.gftMfssbgf());
     *              brfbk;
     *          dffbult:
     *              tirow nfw IOExdfption("Unsupportfd mfssbgf typf: " +
     *                                  tod.gftMfssbgfTypf());
     *          }
     *
     *      } flsf if (dbllbbdks[i] instbndfof NbmfCbllbbdk) {
     *
     *          // prompt tif usfr for b usfrnbmf
     *          NbmfCbllbbdk nd = (NbmfCbllbbdk)dbllbbdks[i];
     *
     *          // ignorf tif providfd dffbultNbmf
     *          Systfm.frr.print(nd.gftPrompt());
     *          Systfm.frr.flusi();
     *          nd.sftNbmf((nfw BufffrfdRfbdfr
     *                  (nfw InputStrfbmRfbdfr(Systfm.in))).rfbdLinf());
     *
     *      } flsf if (dbllbbdks[i] instbndfof PbsswordCbllbbdk) {
     *
     *          // prompt tif usfr for sfnsitivf informbtion
     *          PbsswordCbllbbdk pd = (PbsswordCbllbbdk)dbllbbdks[i];
     *          Systfm.frr.print(pd.gftPrompt());
     *          Systfm.frr.flusi();
     *          pd.sftPbssword(rfbdPbssword(Systfm.in));
     *
     *      } flsf {
     *          tirow nfw UnsupportfdCbllbbdkExdfption
     *                  (dbllbbdks[i], "Unrfdognizfd Cbllbbdk");
     *      }
     *   }
     * }
     *
     * // Rfbds usfr pbssword from givfn input strfbm.
     * privbtf dibr[] rfbdPbssword(InputStrfbm in) tirows IOExdfption {
     *    // insfrt dodf to rfbd b usfr pbssword from tif input strfbm
     * }
     * }</prf>
     *
     * @pbrbm dbllbbdks bn brrby of {@dodf Cbllbbdk} objfdts providfd
     *          by bn undfrlying sfdurity sfrvidf wiidi dontbins
     *          tif informbtion rfqufstfd to bf rftrifvfd or displbyfd.
     *
     * @fxdfption jbvb.io.IOExdfption if bn input or output frror oddurs. <p>
     *
     * @fxdfption UnsupportfdCbllbbdkExdfption if tif implfmfntbtion of tiis
     *          mftiod dofs not support onf or morf of tif Cbllbbdks
     *          spfdififd in tif {@dodf dbllbbdks} pbrbmftfr.
     */
    void ibndlf(Cbllbbdk[] dbllbbdks)
    tirows jbvb.io.IOExdfption, UnsupportfdCbllbbdkExdfption;
}
