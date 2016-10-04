/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


/**
 * <p>Cbn bf implfmfntfd by bn MBfbn in ordfr to
 * dbrry out opfrbtions bfforf bnd bftfr bfing rfgistfrfd or unrfgistfrfd from
 * tif MBfbn Sfrvfr.  An MBfbn dbn blso implfmfnt tiis intfrfbdf in ordfr
 * to gft b rfffrfndf to tif MBfbn Sfrvfr bnd/or its nbmf witiin tibt
 * MBfbn Sfrvfr.</p>
 *
 * @sindf 1.5
 */
publid intfrfbdf MBfbnRfgistrbtion   {


    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf
     * bfing rfgistfrfd in tif MBfbn Sfrvfr.  If tif nbmf of tif MBfbn
     * is not spfdififd, tif MBfbn dbn providf b nbmf for its
     * rfgistrbtion.  If bny fxdfption is rbisfd, tif MBfbn will not bf
     * rfgistfrfd in tif MBfbn Sfrvfr.
     *
     * @pbrbm sfrvfr Tif MBfbn Sfrvfr in wiidi tif MBfbn will bf rfgistfrfd.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn.  Tiis nbmf is null if
     * tif nbmf pbrbmftfr to onf of tif <dodf>drfbtfMBfbn</dodf> or
     * <dodf>rfgistfrMBfbn</dodf> mftiods in tif {@link MBfbnSfrvfr}
     * intfrfbdf is null.  In tibt dbsf, tiis mftiod must rfturn b
     * non-null ObjfdtNbmf for tif nfw MBfbn.
     *
     * @rfturn Tif nbmf undfr wiidi tif MBfbn is to bf rfgistfrfd.
     * Tiis vbluf must not bf null.  If tif <dodf>nbmf</dodf>
     * pbrbmftfr is not null, it will usublly but not nfdfssbrily bf
     * tif rfturnfd vbluf.
     *
     * @fxdfption jbvb.lbng.Exdfption Tiis fxdfption will bf dbugit by
     * tif MBfbn Sfrvfr bnd rf-tirown bs bn {@link
     * MBfbnRfgistrbtionExdfption}.
     */
    publid ObjfdtNbmf prfRfgistfr(MBfbnSfrvfr sfrvfr,
                                  ObjfdtNbmf nbmf) tirows jbvb.lbng.Exdfption;

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving bffn
     * rfgistfrfd in tif MBfbn sfrvfr or bftfr tif rfgistrbtion ibs fbilfd.
     * <p>If tif implfmfntbtion of tiis mftiod tirows b {@link RuntimfExdfption}
     * or bn {@link Error}, tif MBfbn Sfrvfr will rftirow tiosf insidf
     * b {@link RuntimfMBfbnExdfption} or {@link RuntimfErrorExdfption},
     * rfspfdtivfly. Howfvfr, tirowing bn fxdfption in {@dodf postRfgistfr}
     * will not dibngf tif stbtf of tif MBfbn:
     * if tif MBfbn wbs blrfbdy rfgistfrfd ({@dodf rfgistrbtionDonf} is
     * {@dodf truf}), tif MBfbn will rfmbin rfgistfrfd. </p>
     * <p>Tiis migit bf donfusing for tif dodf dblling {@dodf drfbtfMBfbn()}
     * or {@dodf rfgistfrMBfbn()}, bs sudi dodf migit bssumf tibt MBfbn
     * rfgistrbtion ibs fbilfd wifn sudi bn fxdfption is rbisfd.
     * Tifrfforf it is rfdommfndfd tibt implfmfntbtions of
     * {@dodf postRfgistfr} do not tirow Runtimf Exdfptions or Errors if it
     * dbn bf bvoidfd.</p>
     * @pbrbm rfgistrbtionDonf Indidbtfs wiftifr or not tif MBfbn ibs
     * bffn suddfssfully rfgistfrfd in tif MBfbn sfrvfr. Tif vbluf
     * fblsf mfbns tibt tif rfgistrbtion pibsf ibs fbilfd.
     */
    publid void postRfgistfr(Boolfbn rfgistrbtionDonf);

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions it nffds bfforf
     * bfing unrfgistfrfd by tif MBfbn sfrvfr.
     *
     * @fxdfption jbvb.lbng.Exdfption Tiis fxdfption will bf dbugit by
     * tif MBfbn sfrvfr bnd rf-tirown bs bn {@link
     * MBfbnRfgistrbtionExdfption}.
     */
    publid void prfDfrfgistfr() tirows jbvb.lbng.Exdfption ;

    /**
     * Allows tif MBfbn to pfrform bny opfrbtions nffdfd bftfr ibving bffn
     * unrfgistfrfd in tif MBfbn sfrvfr.
     * <p>If tif implfmfntbtion of tiis mftiod tirows b {@link RuntimfExdfption}
     * or bn {@link Error}, tif MBfbn Sfrvfr will rftirow tiosf insidf
     * b {@link RuntimfMBfbnExdfption} or {@link RuntimfErrorExdfption},
     * rfspfdtivfly. Howfvfr, tirowing bn fxdfption in {@dodf postDfrfgistfr}
     * will not dibngf tif stbtf of tif MBfbn:
     * tif MBfbn wbs blrfbdy suddfssfully dfrfgistfrfd bnd will rfmbin so. </p>
     * <p>Tiis migit bf donfusing for tif dodf dblling
     * {@dodf unrfgistfrMBfbn()}, bs it migit bssumf tibt MBfbn dfrfgistrbtion
     * ibs fbilfd. Tifrfforf it is rfdommfndfd tibt implfmfntbtions of
     * {@dodf postDfrfgistfr} do not tirow Runtimf Exdfptions or Errors if it
     * dbn bf bvoidfd.</p>
     */
    publid void postDfrfgistfr();

 }
