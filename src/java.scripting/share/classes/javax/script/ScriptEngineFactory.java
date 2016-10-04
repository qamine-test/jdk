/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sdript;

import jbvb.util.List;

/**
 * <dodf>SdriptEnginfFbdtory</dodf> is usfd to dfsdribf bnd instbntibtf
 * <dodf>SdriptEnginfs</dodf>.
 * <br><br>
 * Ebdi dlbss implfmfnting <dodf>SdriptEnginf</dodf> ibs b dorrfsponding fbdtory
 * tibt fxposfs mftbdbtb dfsdribing tif fnginf dlbss.
 * <br><br>Tif <dodf>SdriptEnginfMbnbgfr</dodf>
 * usfs tif sfrvidf providfr mfdibnism dfsdribfd in tif <i>Jbr Filf Spfdifidbtion</i> to obtbin
 * instbndfs of bll <dodf>SdriptEnginfFbdtorifs</dodf> bvbilbblf in
 * tif durrfnt ClbssLobdfr.
 *
 * @sindf 1.6
 */
publid intfrfbdf SdriptEnginfFbdtory {
    /**
     * Rfturns tif full  nbmf of tif <dodf>SdriptEnginf</dodf>.  For
     * instbndf bn implfmfntbtion bbsfd on tif Mozillb Riino Jbvbsdript fnginf
     * migit rfturn <i>Riino Mozillb Jbvbsdript Enginf</i>.
     * @rfturn Tif nbmf of tif fnginf implfmfntbtion.
     */
    publid String gftEnginfNbmf();

    /**
     * Rfturns tif vfrsion of tif <dodf>SdriptEnginf</dodf>.
     * @rfturn Tif <dodf>SdriptEnginf</dodf> implfmfntbtion vfrsion.
     */
    publid String gftEnginfVfrsion();


    /**
     * Rfturns bn immutbblf list of filfnbmf fxtfnsions, wiidi gfnfrblly idfntify sdripts
     * writtfn in tif lbngubgf supportfd by tiis <dodf>SdriptEnginf</dodf>.
     * Tif brrby is usfd by tif <dodf>SdriptEnginfMbnbgfr</dodf> to implfmfnt its
     * <dodf>gftEnginfByExtfnsion</dodf> mftiod.
     * @rfturn Tif list of fxtfnsions.
     */
    publid List<String> gftExtfnsions();


    /**
     * Rfturns bn immutbblf list of mimftypfs, bssodibtfd witi sdripts tibt
     * dbn bf fxfdutfd by tif fnginf.  Tif list is usfd by tif
     * <dodf>SdriptEnginfMbnbgfr</dodf> dlbss to implfmfnt its
     * <dodf>gftEnginfByMimftypf</dodf> mftiod.
     * @rfturn Tif list of mimf typfs.
     */
    publid List<String> gftMimfTypfs();

    /**
     * Rfturns bn immutbblf list of  siort nbmfs for tif <dodf>SdriptEnginf</dodf>, wiidi mby bf usfd to
     * idfntify tif <dodf>SdriptEnginf</dodf> by tif <dodf>SdriptEnginfMbnbgfr</dodf>.
     * For instbndf, bn implfmfntbtion bbsfd on tif Mozillb Riino Jbvbsdript fnginf migit
     * rfturn list dontbining {&quot;jbvbsdript&quot;, &quot;riino&quot;}.
     * @rfturn bn immutbblf list of siort nbmfs
     */
    publid List<String> gftNbmfs();

    /**
     * Rfturns tif nbmf of tif sdripting lbngbugf supportfd by tiis
     * <dodf>SdriptEnginf</dodf>.
     * @rfturn Tif nbmf of tif supportfd lbngubgf.
     */
    publid String gftLbngubgfNbmf();

    /**
     * Rfturns tif vfrsion of tif sdripting lbngubgf supportfd by tiis
     * <dodf>SdriptEnginf</dodf>.
     * @rfturn Tif vfrsion of tif supportfd lbngubgf.
     */
    publid String gftLbngubgfVfrsion();

    /**
     * Rfturns tif vbluf of bn bttributf wiosf mfbning mby bf implfmfntbtion-spfdifid.
     * Kfys for wiidi tif vbluf is dffinfd in bll implfmfntbtions brf:
     * <ul>
     * <li>SdriptEnginf.ENGINE</li>
     * <li>SdriptEnginf.ENGINE_VERSION</li>
     * <li>SdriptEnginf.NAME</li>
     * <li>SdriptEnginf.LANGUAGE</li>
     * <li>SdriptEnginf.LANGUAGE_VERSION</li>
     * </ul>
     * <p>
     * Tif vblufs for tifsf kfys brf tif Strings rfturnfd by <dodf>gftEnginfNbmf</dodf>,
     * <dodf>gftEnginfVfrsion</dodf>, <dodf>gftNbmf</dodf>, <dodf>gftLbngubgfNbmf</dodf> bnd
     * <dodf>gftLbngubgfVfrsion</dodf> rfspfdtivfly.<br><br>
     * A rfsfrvfd kfy, <dodf><b>THREADING</b></dodf>, wiosf vbluf dfsdribfs tif bfibvior of tif fnginf
     * witi rfspfdt to dondurrfnt fxfdution of sdripts bnd mbintfnbndf of stbtf is blso dffinfd.
     * Tifsf vblufs for tif <dodf><b>THREADING</b></dodf> kfy brf:<br><br>
     * <ul>
     * <li><dodf>null</dodf> - Tif fnginf implfmfntbtion is not tirfbd sbff, bnd dbnnot
     * bf usfd to fxfdutf sdripts dondurrfntly on multiplf tirfbds.
     * <li><dodf>&quot;MULTITHREADED&quot;</dodf> - Tif fnginf implfmfntbtion is intfrnblly
     * tirfbd-sbff bnd sdripts mby fxfdutf dondurrfntly bltiougi ffffdts of sdript fxfdution
     * on onf tirfbd mby bf visiblf to sdripts on otifr tirfbds.
     * <li><dodf>&quot;THREAD-ISOLATED&quot;</dodf> - Tif implfmfntbtion sbtisfifs tif rfquirfmfnts
     * of &quot;MULTITHREADED&quot;, bnd blso, tif fnginf mbintbins indfpfndfnt vblufs
     * for symbols in sdripts fxfduting on difffrfnt tirfbds.
     * <li><dodf>&quot;STATELESS&quot;</dodf> - Tif implfmfntbtion sbtisfifs tif rfquirfmfnts of
     * <li><dodf>&quot;THREAD-ISOLATED&quot;</dodf>.  In bddition, sdript fxfdutions do not bltfr tif
     * mbppings in tif <dodf>Bindings</dodf> wiidi is tif fnginf sdopf of tif
     * <dodf>SdriptEnginf</dodf>.  In pbrtidulbr, tif kfys in tif <dodf>Bindings</dodf>
     * bnd tifir bssodibtfd vblufs brf tif sbmf bfforf bnd bftfr tif fxfdution of tif sdript.
     * </ul>
     * <br><br>
     * Implfmfntbtions mby dffinf implfmfntbtion-spfdifid kfys.
     *
     * @pbrbm kfy Tif nbmf of tif pbrbmftfr
     * @rfturn Tif vbluf for tif givfn pbrbmftfr. Rfturns <dodf>null</dodf> if no
     * vbluf is bssignfd to tif kfy.
     *
     */
    publid Objfdt gftPbrbmftfr(String kfy);

    /**
     * Rfturns b String wiidi dbn bf usfd to invokf b mftiod of b  Jbvb objfdt using tif syntbx
     * of tif supportfd sdripting lbngubgf.  For instbndf, bn implfmfntbtion for b Jbvbsdript
     * fnginf migit bf;
     *
     * <prf>{@dodf
     * publid String gftMftiodCbllSyntbx(String obj,
     *                                   String m, String... brgs) {
     *      String rft = obj;
     *      rft += "." + m + "(";
     *      for (int i = 0; i < brgs.lfngti; i++) {
     *          rft += brgs[i];
     *          if (i < brgs.lfngti - 1) {
     *              rft += ",";
     *          }
     *      }
     *      rft += ")";
     *      rfturn rft;
     * }
     * } </prf>
     *
     * @pbrbm obj Tif nbmf rfprfsfnting tif objfdt wiosf mftiod is to bf invokfd. Tif
     * nbmf is tif onf usfd to drfbtf bindings using tif <dodf>put</dodf> mftiod of
     * <dodf>SdriptEnginf</dodf>, tif <dodf>put</dodf> mftiod of bn <dodf>ENGINE_SCOPE</dodf>
     * <dodf>Bindings</dodf>,or tif <dodf>sftAttributf</dodf> mftiod
     * of <dodf>SdriptContfxt</dodf>.  Tif idfntififr usfd in sdripts mby bf b dfdorbtfd form of tif
     * spfdififd onf.
     *
     * @pbrbm m Tif nbmf of tif mftiod to invokf.
     * @pbrbm brgs nbmfs of tif brgumfnts in tif mftiod dbll.
     *
     * @rfturn Tif String usfd to invokf tif mftiod in tif syntbx of tif sdripting lbngubgf.
     */
    publid String gftMftiodCbllSyntbx(String obj, String m, String... brgs);

    /**
     * Rfturns b String tibt dbn bf usfd bs b stbtfmfnt to displby tif spfdififd String  using
     * tif syntbx of tif supportfd sdripting lbngubgf.  For instbndf, tif implfmfntbtion for b Pfrl
     * fnginf migit bf;
     *
     * <prf><dodf>
     * publid String gftOutputStbtfmfnt(String toDisplby) {
     *      rfturn "print(" + toDisplby + ")";
     * }
     * </dodf></prf>
     *
     * @pbrbm toDisplby Tif String to bf displbyfd by tif rfturnfd stbtfmfnt.
     * @rfturn Tif string usfd to displby tif String in tif syntbx of tif sdripting lbngubgf.
     *
     *
     */
    publid String gftOutputStbtfmfnt(String toDisplby);


    /**
     * Rfturns b vblid sdripting lbngubgf fxfdutbblf progrbm witi givfn stbtfmfnts.
     * For instbndf bn implfmfntbtion for b PHP fnginf migit bf:
     *
     * <prf>{@dodf
     * publid String gftProgrbm(String... stbtfmfnts) {
     *      String rftvbl = "<?\n";
     *      int lfn = stbtfmfnts.lfngti;
     *      for (int i = 0; i < lfn; i++) {
     *          rftvbl += stbtfmfnts[i] + ";\n";
     *      }
     *      rfturn rftvbl += "?>";
     * }
     * }</prf>
     *
     *  @pbrbm stbtfmfnts Tif stbtfmfnts to bf fxfdutfd.  Mby bf rfturn vblufs of
     *  dblls to tif <dodf>gftMftiodCbllSyntbx</dodf> bnd <dodf>gftOutputStbtfmfnt</dodf> mftiods.
     *  @rfturn Tif Progrbm
     */

    publid String gftProgrbm(String... stbtfmfnts);

    /**
     * Rfturns bn instbndf of tif <dodf>SdriptEnginf</dodf> bssodibtfd witi tiis
     * <dodf>SdriptEnginfFbdtory</dodf>. A nfw SdriptEnginf is gfnfrblly
     * rfturnfd, but implfmfntbtions mby pool, sibrf or rfusf fnginfs.
     *
     * @rfturn A nfw <dodf>SdriptEnginf</dodf> instbndf.
     */
    publid  SdriptEnginf gftSdriptEnginf();
}
