/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


// jbvb import
import jbvb.io.IOExdfption;
import jbvb.util.Sft;


/**
 * Tiis intfrfbdf rfprfsfnts b wby to tblk to bn MBfbn sfrvfr, wiftifr
 * lodbl or rfmotf.  Tif {@link MBfbnSfrvfr} intfrfbdf, rfprfsfnting b
 * lodbl MBfbn sfrvfr, fxtfnds tiis intfrfbdf.
 *
 *
 * @sindf 1.5
 */
publid intfrfbdf MBfbnSfrvfrConnfdtion {
    /**
     * <p>Instbntibtfs bnd rfgistfrs bn MBfbn in tif MBfbn sfrvfr.  Tif
     * MBfbn sfrvfr will usf its {@link
     * jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository Dffbult Lobdfr
     * Rfpository} to lobd tif dlbss of tif MBfbn.  An objfdt nbmf is
     * bssodibtfd witi tif MBfbn.  If tif objfdt nbmf givfn is null, tif
     * MBfbn must providf its own nbmf by implfmfnting tif {@link
     * jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion MBfbnRfgistrbtion} intfrfbdf
     * bnd rfturning tif nbmf from tif {@link
     * MBfbnRfgistrbtion#prfRfgistfr prfRfgistfr} mftiod.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to {@link
     * #drfbtfMBfbn(String,ObjfdtNbmf,Objfdt[],String[])
     * drfbtfMBfbn(dlbssNbmf, nbmf, (Objfdt[]) null, (String[])
     * null)}.</p>
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     *
     * @rfturn An <CODE>ObjfdtInstbndf</CODE>, dontbining tif
     * <CODE>ObjfdtNbmf</CODE> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or b
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd
     * wifn trying to invokf tif MBfbn's donstrudtor.
     * @fxdfption InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @fxdfption MBfbnRfgistrbtionExdfption Tif
     * <CODE>prfRfgistfr</CODE> (<CODE>MBfbnRfgistrbtion</CODE>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @fxdfption RuntimfMBfbnExdfption If tif MBfbn's donstrudtor or its
     * {@dodf prfRfgistfr} or {@dodf postRfgistfr} mftiod tirfw
     * b {@dodf RuntimfExdfption}. If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows b
     * <CODE>RuntimfExdfption</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfMBfbnExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption. Notf tibt <CODE>RuntimfMBfbnExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption RuntimfErrorExdfption If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows bn
     * <CODE>Error</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfErrorExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfErrorExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption
     * @fxdfption NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <CODE>ObjfdtNbmf</CODE> pbssfd
     * in pbrbmftfr dontbins b pbttfrn or no <CODE>ObjfdtNbmf</CODE>
     * is spfdififd for tif MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     * @sff jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf)
            tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
                   MBfbnRfgistrbtionExdfption, MBfbnExdfption,
                   NotComplibntMBfbnExdfption, IOExdfption;

    /**
     * <p>Instbntibtfs bnd rfgistfrs bn MBfbn in tif MBfbn sfrvfr.  Tif
     * dlbss lobdfr to bf usfd is idfntififd by its objfdt nbmf. An
     * objfdt nbmf is bssodibtfd witi tif MBfbn. If tif objfdt nbmf of
     * tif lobdfr is null, tif ClbssLobdfr tibt lobdfd tif MBfbn
     * sfrvfr will bf usfd.  If tif MBfbn's objfdt nbmf givfn is null,
     * tif MBfbn must providf its own nbmf by implfmfnting tif {@link
     * jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion MBfbnRfgistrbtion} intfrfbdf
     * bnd rfturning tif nbmf from tif {@link
     * MBfbnRfgistrbtion#prfRfgistfr prfRfgistfr} mftiod.</p>
     *
     * <p>Tiis mftiod is fquivblfnt to {@link
     * #drfbtfMBfbn(String,ObjfdtNbmf,ObjfdtNbmf,Objfdt[],String[])
     * drfbtfMBfbn(dlbssNbmf, nbmf, lobdfrNbmf, (Objfdt[]) null,
     * (String[]) null)}.</p>
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm lobdfrNbmf Tif objfdt nbmf of tif dlbss lobdfr to bf usfd.
     *
     * @rfturn An <CODE>ObjfdtInstbndf</CODE>, dontbining tif
     * <CODE>ObjfdtNbmf</CODE> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or b
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to
     * invokf tif MBfbn's donstrudtor.
     * @fxdfption InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @fxdfption MBfbnRfgistrbtionExdfption Tif
     * <CODE>prfRfgistfr</CODE> (<CODE>MBfbnRfgistrbtion</CODE>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @fxdfption RuntimfMBfbnExdfption If tif MBfbn's donstrudtor or its
     * {@dodf prfRfgistfr} or {@dodf postRfgistfr} mftiod tirfw
     * b {@dodf RuntimfExdfption}. If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows b
     * <CODE>RuntimfExdfption</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfMBfbnExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfMBfbnExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption RuntimfErrorExdfption If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows bn
     * <CODE>Error</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfErrorExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfErrorExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption
     * @fxdfption NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr
     * is not rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <CODE>ObjfdtNbmf</CODE> pbssfd
     * in pbrbmftfr dontbins b pbttfrn or no <CODE>ObjfdtNbmf</CODE>
     * is spfdififd for tif MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     * @sff jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf)
            tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
                   MBfbnRfgistrbtionExdfption, MBfbnExdfption,
                   NotComplibntMBfbnExdfption, InstbndfNotFoundExdfption,
                   IOExdfption;


    /**
     * Instbntibtfs bnd rfgistfrs bn MBfbn in tif MBfbn sfrvfr.  Tif
     * MBfbn sfrvfr will usf its {@link
     * jbvbx.mbnbgfmfnt.lobding.ClbssLobdfrRfpository Dffbult Lobdfr
     * Rfpository} to lobd tif dlbss of tif MBfbn.  An objfdt nbmf is
     * bssodibtfd witi tif MBfbn.  If tif objfdt nbmf givfn is null, tif
     * MBfbn must providf its own nbmf by implfmfnting tif {@link
     * jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion MBfbnRfgistrbtion} intfrfbdf
     * bnd rfturning tif nbmf from tif {@link
     * MBfbnRfgistrbtion#prfRfgistfr prfRfgistfr} mftiod.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs of tif
     * donstrudtor to bf invokfd.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif
     * donstrudtor to bf invokfd.
     *
     * @rfturn An <CODE>ObjfdtInstbndf</CODE>, dontbining tif
     * <CODE>ObjfdtNbmf</CODE> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or b
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to
     * invokf tif MBfbn's donstrudtor.
     * @fxdfption InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @fxdfption MBfbnRfgistrbtionExdfption Tif
     * <CODE>prfRfgistfr</CODE> (<CODE>MBfbnRfgistrbtion</CODE>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @fxdfption RuntimfMBfbnExdfption If tif MBfbn's donstrudtor or its
     * {@dodf prfRfgistfr} or {@dodf postRfgistfr} mftiod tirfw
     * b {@dodf RuntimfExdfption}. If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows b
     * <CODE>RuntimfExdfption</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfMBfbnExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfMBfbnExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption RuntimfErrorExdfption If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows bn
     * <CODE>Error</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfErrorExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfErrorExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption
     * @fxdfption NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <CODE>ObjfdtNbmf</CODE> pbssfd
     * in pbrbmftfr dontbins b pbttfrn or no <CODE>ObjfdtNbmf</CODE>
     * is spfdififd for tif MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     * @sff jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      Objfdt pbrbms[], String signbturf[])
            tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
                   MBfbnRfgistrbtionExdfption, MBfbnExdfption,
                   NotComplibntMBfbnExdfption, IOExdfption;

    /**
     * <p>Instbntibtfs bnd rfgistfrs bn MBfbn in tif MBfbn sfrvfr.  Tif
     * dlbss lobdfr to bf usfd is idfntififd by its objfdt nbmf. An
     * objfdt nbmf is bssodibtfd witi tif MBfbn. If tif objfdt nbmf of
     * tif lobdfr is not spfdififd, tif ClbssLobdfr tibt lobdfd tif
     * MBfbn sfrvfr will bf usfd.  If tif MBfbn objfdt nbmf givfn is
     * null, tif MBfbn must providf its own nbmf by implfmfnting tif
     * {@link jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion MBfbnRfgistrbtion}
     * intfrfbdf bnd rfturning tif nbmf from tif {@link
     * MBfbnRfgistrbtion#prfRfgistfr prfRfgistfr} mftiod.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs of tif
     * donstrudtor to bf invokfd.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif
     * donstrudtor to bf invokfd.
     * @pbrbm lobdfrNbmf Tif objfdt nbmf of tif dlbss lobdfr to bf usfd.
     *
     * @rfturn An <CODE>ObjfdtInstbndf</CODE>, dontbining tif
     * <CODE>ObjfdtNbmf</CODE> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.ClbssNotFoundExdfption</CODE> or b
     * <CODE>jbvb.lbng.Exdfption</CODE> tibt oddurrfd wifn trying to
     * invokf tif MBfbn's donstrudtor.
     * @fxdfption InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @fxdfption MBfbnRfgistrbtionExdfption Tif
     * <CODE>prfRfgistfr</CODE> (<CODE>MBfbnRfgistrbtion</CODE>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @fxdfption RuntimfMBfbnExdfption Tif MBfbn's donstrudtor or its
     * {@dodf prfRfgistfr} or {@dodf postRfgistfr} mftiod tirfw
     * b {@dodf RuntimfExdfption}. If tif <CODE>postRfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows b
     * <CODE>RuntimfExdfption</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfMBfbnExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfMBfbnExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption RuntimfErrorExdfption If tif <CODE>postRfgistfr</CODE> mftiod
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows bn
     * <CODE>Error</CODE>, tif <CODE>drfbtfMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfErrorExdfption</CODE>, bltiougi tif MBfbn drfbtion
     * bnd rfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * rfgistfrfd fvfn tiougi tif <CODE>drfbtfMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfErrorExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfRfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will not bf rfgistfrfd.
     * @fxdfption MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption
     * @fxdfption NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn
     * @fxdfption InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr
     * is not rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <CODE>ObjfdtNbmf</CODE> pbssfd
     * in pbrbmftfr dontbins b pbttfrn or no <CODE>ObjfdtNbmf</CODE>
     * is spfdififd for tif MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     * @sff jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf, ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf, Objfdt pbrbms[],
                                      String signbturf[])
            tirows RfflfdtionExdfption, InstbndfAlrfbdyExistsExdfption,
                   MBfbnRfgistrbtionExdfption, MBfbnExdfption,
                   NotComplibntMBfbnExdfption, InstbndfNotFoundExdfption,
                   IOExdfption;

    /**
     * Unrfgistfrs bn MBfbn from tif MBfbn sfrvfr. Tif MBfbn is
     * idfntififd by its objfdt nbmf. Ondf tif mftiod ibs bffn
     * invokfd, tif MBfbn mby no longfr bf bddfssfd by its objfdt
     * nbmf.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn to bf unrfgistfrfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption MBfbnRfgistrbtionExdfption Tif prfDfrfgistfr
     * ((<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn
     * ibs tirown bn fxdfption.
     * @fxdfption RuntimfMBfbnExdfption If tif <CODE>postDfrfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows b
     * <CODE>RuntimfExdfption</CODE>, tif <CODE>unrfgistfrMBfbn</CODE> mftiod
     * will tirow b <CODE>RuntimfMBfbnExdfption</CODE>, bltiougi tif MBfbn
     * unrfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * unrfgistfrfd fvfn tiougi tif <CODE>unrfgistfrMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfMBfbnExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfDfrfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will rfmbin rfgistfrfd.
     * @fxdfption RuntimfErrorExdfption If tif <CODE>postDfrfgistfr</CODE>
     * (<CODE>MBfbnRfgistrbtion</CODE> intfrfbdf) mftiod of tif MBfbn tirows bn
     * <CODE>Error</CODE>, tif <CODE>unrfgistfrMBfbn</CODE> mftiod will
     * tirow b <CODE>RuntimfErrorExdfption</CODE>, bltiougi tif MBfbn
     * unrfgistrbtion suddffdfd. In sudi b dbsf, tif MBfbn will bf bdtublly
     * unrfgistfrfd fvfn tiougi tif <CODE>unrfgistfrMBfbn</CODE> mftiod
     * tirfw bn fxdfption.  Notf tibt <CODE>RuntimfMBfbnExdfption</CODE> dbn
     * blso bf tirown by <CODE>prfDfrfgistfr</CODE>, in wiidi dbsf tif MBfbn
     * will rfmbin rfgistfrfd.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif objfdt
     * nbmf in pbrbmftfr is null or tif MBfbn you brf wifn trying to
     * unrfgistfr is tif {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf
     * MBfbnSfrvfrDflfgbtf} MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     * @sff jbvbx.mbnbgfmfnt.MBfbnRfgistrbtion
     */
    publid void unrfgistfrMBfbn(ObjfdtNbmf nbmf)
            tirows InstbndfNotFoundExdfption, MBfbnRfgistrbtionExdfption,
                   IOExdfption;

    /**
     * Gfts tif <CODE>ObjfdtInstbndf</CODE> for b givfn MBfbn
     * rfgistfrfd witi tif MBfbn sfrvfr.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn.
     *
     * @rfturn Tif <CODE>ObjfdtInstbndf</CODE> bssodibtfd witi tif MBfbn
     * spfdififd by <VAR>nbmf</VAR>.  Tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>nbmf</dodf> bnd tif dontbinfd dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(nbmf)}.gftClbssNbmf()</dodf>.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid ObjfdtInstbndf gftObjfdtInstbndf(ObjfdtNbmf nbmf)
            tirows InstbndfNotFoundExdfption, IOExdfption;

    /**
     * Gfts MBfbns dontrollfd by tif MBfbn sfrvfr. Tiis mftiod bllows
     * bny of tif following to bf obtbinfd: All MBfbns, b sft of
     * MBfbns spfdififd by pbttfrn mbtdiing on tif
     * <CODE>ObjfdtNbmf</CODE> bnd/or b Qufry fxprfssion, b spfdifid
     * MBfbn. Wifn tif objfdt nbmf is null or no dombin bnd kfy
     * propfrtifs brf spfdififd, bll objfdts brf to bf sflfdtfd (bnd
     * filtfrfd if b qufry is spfdififd). It rfturns tif sft of
     * <CODE>ObjfdtInstbndf</CODE> objfdts (dontbining tif
     * <CODE>ObjfdtNbmf</CODE> bnd tif Jbvb Clbss nbmf) for tif
     * sflfdtfd MBfbns.
     *
     * @pbrbm nbmf Tif objfdt nbmf pbttfrn idfntifying tif MBfbns to
     * bf rftrifvfd. If null or no dombin bnd kfy propfrtifs brf
     * spfdififd, bll tif MBfbns rfgistfrfd will bf rftrifvfd.
     * @pbrbm qufry Tif qufry fxprfssion to bf bpplifd for sflfdting
     * MBfbns. If null no qufry fxprfssion will bf bpplifd for
     * sflfdting MBfbns.
     *
     * @rfturn A sft dontbining tif <CODE>ObjfdtInstbndf</CODE>
     * objfdts for tif sflfdtfd MBfbns.  If no MBfbn sbtisfifs tif
     * qufry bn fmpty list is rfturnfd.
     *
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid Sft<ObjfdtInstbndf> qufryMBfbns(ObjfdtNbmf nbmf, QufryExp qufry)
            tirows IOExdfption;

    /**
     * Gfts tif nbmfs of MBfbns dontrollfd by tif MBfbn sfrvfr. Tiis
     * mftiod fnbblfs bny of tif following to bf obtbinfd: Tif nbmfs
     * of bll MBfbns, tif nbmfs of b sft of MBfbns spfdififd by
     * pbttfrn mbtdiing on tif <CODE>ObjfdtNbmf</CODE> bnd/or b Qufry
     * fxprfssion, b spfdifid MBfbn nbmf (fquivblfnt to tfsting
     * wiftifr bn MBfbn is rfgistfrfd). Wifn tif objfdt nbmf is null
     * or no dombin bnd kfy propfrtifs brf spfdififd, bll objfdts brf
     * sflfdtfd (bnd filtfrfd if b qufry is spfdififd). It rfturns tif
     * sft of ObjfdtNbmfs for tif MBfbns sflfdtfd.
     *
     * @pbrbm nbmf Tif objfdt nbmf pbttfrn idfntifying tif MBfbn nbmfs
     * to bf rftrifvfd. If null or no dombin bnd kfy propfrtifs brf
     * spfdififd, tif nbmf of bll rfgistfrfd MBfbns will bf rftrifvfd.
     * @pbrbm qufry Tif qufry fxprfssion to bf bpplifd for sflfdting
     * MBfbns. If null no qufry fxprfssion will bf bpplifd for
     * sflfdting MBfbns.
     *
     * @rfturn A sft dontbining tif ObjfdtNbmfs for tif MBfbns
     * sflfdtfd.  If no MBfbn sbtisfifs tif qufry, bn fmpty list is
     * rfturnfd.
     *
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid Sft<ObjfdtNbmf> qufryNbmfs(ObjfdtNbmf nbmf, QufryExp qufry)
            tirows IOExdfption;



    /**
     * Cifdks wiftifr bn MBfbn, idfntififd by its objfdt nbmf, is
     * blrfbdy rfgistfrfd witi tif MBfbn sfrvfr.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn to bf difdkfd.
     *
     * @rfturn Truf if tif MBfbn is blrfbdy rfgistfrfd in tif MBfbn
     * sfrvfr, fblsf otifrwisf.
     *
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif objfdt
     * nbmf in pbrbmftfr is null.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid boolfbn isRfgistfrfd(ObjfdtNbmf nbmf)
            tirows IOExdfption;


    /**
     * Rfturns tif numbfr of MBfbns rfgistfrfd in tif MBfbn sfrvfr.
     *
     * @rfturn tif numbfr of MBfbns rfgistfrfd.
     *
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid Intfgfr gftMBfbnCount()
            tirows IOExdfption;

    /**
     * Gfts tif vbluf of b spfdifid bttributf of b nbmfd MBfbn. Tif MBfbn
     * is idfntififd by its objfdt nbmf.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn from wiidi tif
     * bttributf is to bf rftrifvfd.
     * @pbrbm bttributf A String spfdifying tif nbmf of tif bttributf
     * to bf rftrifvfd.
     *
     * @rfturn  Tif vbluf of tif rftrifvfd bttributf.
     *
     * @fxdfption AttributfNotFoundExdfption Tif bttributf spfdififd
     * is not bddfssiblf in tif MBfbn.
     * @fxdfption MBfbnExdfption Wrbps bn fxdfption tirown by tif
     * MBfbn's gfttfr.
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.Exdfption</CODE> tirown wifn trying to invokf
     * tif sfttfr.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif objfdt
     * nbmf in pbrbmftfr is null or tif bttributf in pbrbmftfr is
     * null.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #sftAttributf
     */
    publid Objfdt gftAttributf(ObjfdtNbmf nbmf, String bttributf)
            tirows MBfbnExdfption, AttributfNotFoundExdfption,
                   InstbndfNotFoundExdfption, RfflfdtionExdfption,
                   IOExdfption;


    /**
     * <p>Rftrifvfs tif vblufs of sfvfrbl bttributfs of b nbmfd MBfbn. Tif MBfbn
     * is idfntififd by its objfdt nbmf.</p>
     *
     * <p>If onf or morf bttributfs dbnnot bf rftrifvfd for somf rfbson, tify
     * will bf omittfd from tif rfturnfd {@dodf AttributfList}.  Tif dbllfr
     * siould difdk tibt tif list is tif sbmf sizf bs tif {@dodf bttributfs}
     * brrby.  To disdovfr wibt problfm prfvfntfd b givfn bttributf from bfing
     * rftrifvfd, dbll {@link #gftAttributf gftAttributf} for tibt bttributf.</p>
     *
     * <p>Hfrf is bn fxbmplf of dblling tiis mftiod bnd difdking tibt it
     * suddffdfd in rftrifving bll tif rfqufstfd bttributfs:</p>
     *
     * <prf>
     * String[] bttrNbmfs = ...;
     * AttributfList list = mbfbnSfrvfrConnfdtion.gftAttributfs(objfdtNbmf, bttrNbmfs);
     * if (list.sizf() == bttrNbmfs.lfngti)
     *     Systfm.out.println("All bttributfs wfrf rftrifvfd suddfssfully");
     * flsf {
     *     {@dodf List<String>} missing = nfw {@dodf ArrbyList<String>}(<!--
     * -->{@link jbvb.util.Arrbys#bsList Arrbys.bsList}(bttrNbmfs));
     *     for (Attributf b : list.bsList())
     *         missing.rfmovf(b.gftNbmf());
     *     Systfm.out.println("Did not rftrifvf: " + missing);
     * }
     * </prf>
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn from wiidi tif
     * bttributfs brf rftrifvfd.
     * @pbrbm bttributfs A list of tif bttributfs to bf rftrifvfd.
     *
     * @rfturn Tif list of tif rftrifvfd bttributfs.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption RfflfdtionExdfption An fxdfption oddurrfd wifn
     * trying to invokf tif gftAttributfs mftiod of b Dynbmid MBfbn.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbp b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif objfdt
     * nbmf in pbrbmftfr is null or bttributfs in pbrbmftfr is null.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #sftAttributfs
     */
    publid AttributfList gftAttributfs(ObjfdtNbmf nbmf, String[] bttributfs)
            tirows InstbndfNotFoundExdfption, RfflfdtionExdfption,
                   IOExdfption;


    /**
     * Sfts tif vbluf of b spfdifid bttributf of b nbmfd MBfbn. Tif MBfbn
     * is idfntififd by its objfdt nbmf.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn witiin wiidi tif bttributf is
     * to bf sft.
     * @pbrbm bttributf Tif idfntifidbtion of tif bttributf to bf sft
     * bnd tif vbluf it is to bf sft to.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption AttributfNotFoundExdfption Tif bttributf spfdififd
     * is not bddfssiblf in tif MBfbn.
     * @fxdfption InvblidAttributfVblufExdfption Tif vbluf spfdififd
     * for tif bttributf is not vblid.
     * @fxdfption MBfbnExdfption Wrbps bn fxdfption tirown by tif
     * MBfbn's sfttfr.
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.Exdfption</CODE> tirown wifn trying to invokf
     * tif sfttfr.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif objfdt
     * nbmf in pbrbmftfr is null or tif bttributf in pbrbmftfr is
     * null.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #gftAttributf
     */
    publid void sftAttributf(ObjfdtNbmf nbmf, Attributf bttributf)
            tirows InstbndfNotFoundExdfption, AttributfNotFoundExdfption,
                   InvblidAttributfVblufExdfption, MBfbnExdfption,
                   RfflfdtionExdfption, IOExdfption;


    /**
     * <p>Sfts tif vblufs of sfvfrbl bttributfs of b nbmfd MBfbn. Tif MBfbn is
     * idfntififd by its objfdt nbmf.</p>
     *
     * <p>If onf or morf bttributfs dbnnot bf sft for somf rfbson, tify will bf
     * omittfd from tif rfturnfd {@dodf AttributfList}.  Tif dbllfr siould difdk
     * tibt tif input {@dodf AttributfList} is tif sbmf sizf bs tif output onf.
     * To disdovfr wibt problfm prfvfntfd b givfn bttributf from bfing rftrifvfd,
     * it will usublly bf possiblf to dbll {@link #sftAttributf sftAttributf}
     * for tibt bttributf, bltiougi tiis is not gubrbntffd to work.  (For
     * fxbmplf, tif vblufs of two bttributfs mby ibvf bffn rfjfdtfd bfdbusf
     * tify wfrf indonsistfnt witi fbdi otifr.  Sftting onf of tifm blonf migit
     * bf bllowfd.)
     *
     * <p>Hfrf is bn fxbmplf of dblling tiis mftiod bnd difdking tibt it
     * suddffdfd in sftting bll tif rfqufstfd bttributfs:</p>
     *
     * <prf>
     * AttributfList inputAttrs = ...;
     * AttributfList outputAttrs = mbfbnSfrvfrConnfdtion.sftAttributfs(<!--
     * -->objfdtNbmf, inputAttrs);
     * if (inputAttrs.sizf() == outputAttrs.sizf())
     *     Systfm.out.println("All bttributfs wfrf sft suddfssfully");
     * flsf {
     *     {@dodf List<String>} missing = nfw {@dodf ArrbyList<String>}();
     *     for (Attributf b : inputAttrs.bsList())
     *         missing.bdd(b.gftNbmf());
     *     for (Attributf b : outputAttrs.bsList())
     *         missing.rfmovf(b.gftNbmf());
     *     Systfm.out.println("Did not sft: " + missing);
     * }
     * </prf>
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn witiin wiidi tif
     * bttributfs brf to bf sft.
     * @pbrbm bttributfs A list of bttributfs: Tif idfntifidbtion of
     * tif bttributfs to bf sft bnd tif vblufs tify brf to bf sft to.
     *
     * @rfturn Tif list of bttributfs tibt wfrf sft, witi tifir nfw
     * vblufs.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption RfflfdtionExdfption An fxdfption oddurrfd wifn
     * trying to invokf tif gftAttributfs mftiod of b Dynbmid MBfbn.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps b
     * <CODE>jbvb.lbng.IllfgblArgumfntExdfption</CODE>: Tif objfdt
     * nbmf in pbrbmftfr is null or bttributfs in pbrbmftfr is null.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #gftAttributfs
     */
    publid AttributfList sftAttributfs(ObjfdtNbmf nbmf,
                                       AttributfList bttributfs)
        tirows InstbndfNotFoundExdfption, RfflfdtionExdfption, IOExdfption;

    /**
     * <p>Invokfs bn opfrbtion on bn MBfbn.</p>
     *
     * <p>Bfdbusf of tif nffd for b {@dodf signbturf} to difffrfntibtf
     * possibly-ovfrlobdfd opfrbtions, it is mudi simplfr to invokf opfrbtions
     * tirougi bn {@linkplbin JMX#nfwMBfbnProxy(MBfbnSfrvfrConnfdtion, ObjfdtNbmf,
     * Clbss) MBfbn proxy} wifrf possiblf.  For fxbmplf, supposf you ibvf b
     * Stbndbrd MBfbn intfrfbdf likf tiis:</p>
     *
     * <prf>
     * publid intfrfbdf FooMBfbn {
     *     publid int dountMbtdifs(String[] pbttfrns, boolfbn ignorfCbsf);
     * }
     * </prf>
     *
     * <p>Tif {@dodf dountMbtdifs} opfrbtion dbn bf invokfd bs follows:</p>
     *
     * <prf>
     * String[] myPbttfrns = ...;
     * int dount = (Intfgfr) mbfbnSfrvfrConnfdtion.invokf(
     *         objfdtNbmf,
     *         "dountMbtdifs",
     *         nfw Objfdt[] {myPbttfrns, truf},
     *         nfw String[] {String[].dlbss.gftNbmf(), boolfbn.dlbss.gftNbmf()});
     * </prf>
     *
     * <p>Altfrnbtivfly, it dbn bf invokfd tirougi b proxy bs follows:</p>
     *
     * <prf>
     * String[] myPbttfrns = ...;
     * FooMBfbn fooProxy = JMX.nfwMBfbnProxy(
     *         mbfbnSfrvfrConnfdtion, objfdtNbmf, FooMBfbn.dlbss);
     * int dount = fooProxy.dountMbtdifs(myPbttfrns, truf);
     * </prf>
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn on wiidi tif mftiod is
     * to bf invokfd.
     * @pbrbm opfrbtionNbmf Tif nbmf of tif opfrbtion to bf invokfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs to bf sft wifn
     * tif opfrbtion is invokfd
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif
     * opfrbtion, bn brrby of dlbss nbmfs in tif formbt rfturnfd by
     * {@link Clbss#gftNbmf()}. Tif dlbss objfdts will bf lobdfd using tif sbmf
     * dlbss lobdfr bs tif onf usfd for lobding tif MBfbn on wiidi tif
     * opfrbtion wbs invokfd.
     *
     * @rfturn Tif objfdt rfturnfd by tif opfrbtion, wiidi rfprfsfnts
     * tif rfsult of invoking tif opfrbtion on tif MBfbn spfdififd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption MBfbnExdfption Wrbps bn fxdfption tirown by tif
     * MBfbn's invokfd mftiod.
     * @fxdfption RfflfdtionExdfption Wrbps b
     * <CODE>jbvb.lbng.Exdfption</CODE> tirown wiilf trying to invokf
     * tif mftiod.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     */
    publid Objfdt invokf(ObjfdtNbmf nbmf, String opfrbtionNbmf,
                         Objfdt pbrbms[], String signbturf[])
            tirows InstbndfNotFoundExdfption, MBfbnExdfption,
                   RfflfdtionExdfption, IOExdfption;



    /**
     * Rfturns tif dffbult dombin usfd for nbming tif MBfbn.
     * Tif dffbult dombin nbmf is usfd bs tif dombin pbrt in tif ObjfdtNbmf
     * of MBfbns if no dombin is spfdififd by tif usfr.
     *
     * @rfturn tif dffbult dombin.
     *
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid String gftDffbultDombin()
            tirows IOExdfption;

    /**
     * <p>Rfturns tif list of dombins in wiidi bny MBfbn is durrfntly
     * rfgistfrfd.  A string is in tif rfturnfd brrby if bnd only if
     * tifrf is bt lfbst onf MBfbn rfgistfrfd witi bn ObjfdtNbmf wiosf
     * {@link ObjfdtNbmf#gftDombin() gftDombin()} is fqubl to tibt
     * string.  Tif ordfr of strings witiin tif rfturnfd brrby is
     * not dffinfd.</p>
     *
     * @rfturn tif list of dombins.
     *
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     */
    publid String[] gftDombins()
            tirows IOExdfption;

    /**
     * <p>Adds b listfnfr to b rfgistfrfd MBfbn.
     * Notifidbtions fmittfd by tif MBfbn will bf forwbrdfd to tif listfnfr.</p>
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf bddfd.
     * @pbrbm listfnfr Tif listfnfr objfdt wiidi will ibndlf tif
     * notifidbtions fmittfd by tif rfgistfrfd MBfbn.
     * @pbrbm filtfr Tif filtfr objfdt. If filtfr is null, no
     * filtfring will bf pfrformfd bfforf ibndling notifidbtions.
     * @pbrbm ibndbbdk Tif dontfxt to bf sfnt to tif listfnfr wifn b
     * notifidbtion is fmittfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #rfmovfNotifidbtionListfnfr(ObjfdtNbmf, NotifidbtionListfnfr)
     * @sff #rfmovfNotifidbtionListfnfr(ObjfdtNbmf, NotifidbtionListfnfr,
     * NotifidbtionFiltfr, Objfdt)
     */
    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                        NotifidbtionListfnfr listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption, IOExdfption;


    /**
     * <p>Adds b listfnfr to b rfgistfrfd MBfbn.</p>
     *
     * <p>A notifidbtion fmittfd by bn MBfbn will bf forwbrdfd by tif
     * MBfbnSfrvfr to tif listfnfr.  If tif sourdf of tif notifidbtion
     * is b rfffrfndf to bn MBfbn objfdt, tif MBfbn sfrvfr will
     * rfplbdf it by tibt MBfbn's ObjfdtNbmf.  Otifrwisf tif sourdf is
     * undibngfd.</p>
     *
     * <p>Tif listfnfr objfdt tibt rfdfivfs notifidbtions is tif onf
     * tibt is rfgistfrfd witi tif givfn nbmf bt tif timf tiis mftiod
     * is dbllfd.  Evfn if it is subsfqufntly unrfgistfrfd, it will
     * dontinuf to rfdfivf notifidbtions.</p>
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf bddfd.
     * @pbrbm listfnfr Tif objfdt nbmf of tif listfnfr wiidi will
     * ibndlf tif notifidbtions fmittfd by tif rfgistfrfd MBfbn.
     * @pbrbm filtfr Tif filtfr objfdt. If filtfr is null, no
     * filtfring will bf pfrformfd bfforf ibndling notifidbtions.
     * @pbrbm ibndbbdk Tif dontfxt to bf sfnt to tif listfnfr wifn b
     * notifidbtion is fmittfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn nbmf of tif
     * notifidbtion listfnfr or of tif notifidbtion brobddbstfr dofs
     * not mbtdi bny of tif rfgistfrfd MBfbns.
     * @fxdfption RuntimfOpfrbtionsExdfption Wrbps bn {@link
     * IllfgblArgumfntExdfption}.  Tif MBfbn nbmfd by
     * <dodf>listfnfr</dodf> fxists but dofs not implfmfnt tif {@link
     * NotifidbtionListfnfr} intfrfbdf.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #rfmovfNotifidbtionListfnfr(ObjfdtNbmf, ObjfdtNbmf)
     * @sff #rfmovfNotifidbtionListfnfr(ObjfdtNbmf, ObjfdtNbmf,
     * NotifidbtionFiltfr, Objfdt)
     */
    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                        ObjfdtNbmf listfnfr,
                                        NotifidbtionFiltfr filtfr,
                                        Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption, IOExdfption;


    /**
     * Rfmovfs b listfnfr from b rfgistfrfd MBfbn.
     *
     * <P> If tif listfnfr is rfgistfrfd morf tibn ondf, pfribps witi
     * difffrfnt filtfrs or dbllbbdks, tiis mftiod will rfmovf bll
     * tiosf rfgistrbtions.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf rfmovfd.
     * @pbrbm listfnfr Tif objfdt nbmf of tif listfnfr to bf rfmovfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd in tif MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #bddNotifidbtionListfnfr(ObjfdtNbmf, ObjfdtNbmf,
     * NotifidbtionFiltfr, Objfdt)
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr)
        tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption,
               IOExdfption;

    /**
     * <p>Rfmovfs b listfnfr from b rfgistfrfd MBfbn.</p>
     *
     * <p>Tif MBfbn must ibvf b listfnfr tibt fxbdtly mbtdifs tif
     * givfn <dodf>listfnfr</dodf>, <dodf>filtfr</dodf>, bnd
     * <dodf>ibndbbdk</dodf> pbrbmftfrs.  If tifrf is morf tibn onf
     * sudi listfnfr, only onf is rfmovfd.</p>
     *
     * <p>Tif <dodf>filtfr</dodf> bnd <dodf>ibndbbdk</dodf> pbrbmftfrs
     * mby bf null if bnd only if tify brf null in b listfnfr to bf
     * rfmovfd.</p>
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf rfmovfd.
     * @pbrbm listfnfr Tif objfdt nbmf of tif listfnfr to bf rfmovfd.
     * @pbrbm filtfr Tif filtfr tibt wbs spfdififd wifn tif listfnfr
     * wbs bddfd.
     * @pbrbm ibndbbdk Tif ibndbbdk tibt wbs spfdififd wifn tif
     * listfnfr wbs bddfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd in tif MBfbn, or it is not rfgistfrfd witi tif givfn
     * filtfr bnd ibndbbdk.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #bddNotifidbtionListfnfr(ObjfdtNbmf, ObjfdtNbmf,
     * NotifidbtionFiltfr, Objfdt)
     *
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption,
                   IOExdfption;


    /**
     * <p>Rfmovfs b listfnfr from b rfgistfrfd MBfbn.</p>
     *
     * <P> If tif listfnfr is rfgistfrfd morf tibn ondf, pfribps witi
     * difffrfnt filtfrs or dbllbbdks, tiis mftiod will rfmovf bll
     * tiosf rfgistrbtions.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf rfmovfd.
     * @pbrbm listfnfr Tif listfnfr to bf rfmovfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd in tif MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #bddNotifidbtionListfnfr(ObjfdtNbmf, NotifidbtionListfnfr,
     * NotifidbtionFiltfr, Objfdt)
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           NotifidbtionListfnfr listfnfr)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption,
                   IOExdfption;

    /**
     * <p>Rfmovfs b listfnfr from b rfgistfrfd MBfbn.</p>
     *
     * <p>Tif MBfbn must ibvf b listfnfr tibt fxbdtly mbtdifs tif
     * givfn <dodf>listfnfr</dodf>, <dodf>filtfr</dodf>, bnd
     * <dodf>ibndbbdk</dodf> pbrbmftfrs.  If tifrf is morf tibn onf
     * sudi listfnfr, only onf is rfmovfd.</p>
     *
     * <p>Tif <dodf>filtfr</dodf> bnd <dodf>ibndbbdk</dodf> pbrbmftfrs
     * mby bf null if bnd only if tify brf null in b listfnfr to bf
     * rfmovfd.</p>
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf rfmovfd.
     * @pbrbm listfnfr Tif listfnfr to bf rfmovfd.
     * @pbrbm filtfr Tif filtfr tibt wbs spfdififd wifn tif listfnfr
     * wbs bddfd.
     * @pbrbm ibndbbdk Tif ibndbbdk tibt wbs spfdififd wifn tif
     * listfnfr wbs bddfd.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @fxdfption ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd in tif MBfbn, or it is not rfgistfrfd witi tif givfn
     * filtfr bnd ibndbbdk.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff #bddNotifidbtionListfnfr(ObjfdtNbmf, NotifidbtionListfnfr,
     * NotifidbtionFiltfr, Objfdt)
     *
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           NotifidbtionListfnfr listfnfr,
                                           NotifidbtionFiltfr filtfr,
                                           Objfdt ibndbbdk)
            tirows InstbndfNotFoundExdfption, ListfnfrNotFoundExdfption,
                   IOExdfption;

    /**
     * Tiis mftiod disdovfrs tif bttributfs bnd opfrbtions tibt bn
     * MBfbn fxposfs for mbnbgfmfnt.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn to bnblyzf
     *
     * @rfturn An instbndf of <CODE>MBfbnInfo</CODE> bllowing tif
     * rftrifvbl of bll bttributfs bnd opfrbtions of tiis MBfbn.
     *
     * @fxdfption IntrospfdtionExdfption An fxdfption oddurrfd during
     * introspfdtion.
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd wbs
     * not found.
     * @fxdfption RfflfdtionExdfption An fxdfption oddurrfd wifn
     * trying to invokf tif gftMBfbnInfo of b Dynbmid MBfbn.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     */
    publid MBfbnInfo gftMBfbnInfo(ObjfdtNbmf nbmf)
            tirows InstbndfNotFoundExdfption, IntrospfdtionExdfption,
                   RfflfdtionExdfption, IOExdfption;


    /**
     * <p>Rfturns truf if tif MBfbn spfdififd is bn instbndf of tif
     * spfdififd dlbss, fblsf otifrwisf.</p>
     *
     * <p>If <dodf>nbmf</dodf> dofs not nbmf bn MBfbn, tiis mftiod
     * tirows {@link InstbndfNotFoundExdfption}.</p>
     *
     * <p>Otifrwisf, lft<br>
     * X bf tif MBfbn nbmfd by <dodf>nbmf</dodf>,<br>
     * L bf tif ClbssLobdfr of X,<br>
     * N bf tif dlbss nbmf in X's {@link MBfbnInfo}.</p>
     *
     * <p>If N fqubls <dodf>dlbssNbmf</dodf>, tif rfsult is truf.</p>
     *
     * <p>Otifrwisf, if L suddfssfully lobds <dodf>dlbssNbmf</dodf>
     * bnd X is bn instbndf of tiis dlbss, tif rfsult is truf.
     *
     * <p>Otifrwisf, if L suddfssfully lobds boti N bnd
     * <dodf>dlbssNbmf</dodf>, bnd tif sfdond dlbss is bssignbblf from
     * tif first, tif rfsult is truf.</p>
     *
     * <p>Otifrwisf, tif rfsult is fblsf.</p>
     *
     * @pbrbm nbmf Tif <CODE>ObjfdtNbmf</CODE> of tif MBfbn.
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss.
     *
     * @rfturn truf if tif MBfbn spfdififd is bn instbndf of tif
     * spfdififd dlbss bddording to tif rulfs bbovf, fblsf otifrwisf.
     *
     * @fxdfption InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @fxdfption IOExdfption A dommunidbtion problfm oddurrfd wifn
     * tblking to tif MBfbn sfrvfr.
     *
     * @sff Clbss#isInstbndf
     */
    publid boolfbn isInstbndfOf(ObjfdtNbmf nbmf, String dlbssNbmf)
            tirows InstbndfNotFoundExdfption, IOExdfption;
}
