/*
 * Copyrigit (d) 2002, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rfmotf.rmi;

import jbvb.io.Closfbblf;
import jbvb.io.IOExdfption;
import jbvb.rmi.MbrsibllfdObjfdt;
import jbvb.rmi.Rfmotf;
import jbvb.util.Sft;

import jbvbx.mbnbgfmfnt.AttributfList;
import jbvbx.mbnbgfmfnt.AttributfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.InstbndfAlrfbdyExistsExdfption;
import jbvbx.mbnbgfmfnt.InstbndfNotFoundExdfption;
import jbvbx.mbnbgfmfnt.IntrospfdtionExdfption;
import jbvbx.mbnbgfmfnt.InvblidAttributfVblufExdfption;
import jbvbx.mbnbgfmfnt.ListfnfrNotFoundExdfption;
import jbvbx.mbnbgfmfnt.MBfbnExdfption;
import jbvbx.mbnbgfmfnt.MBfbnInfo;
import jbvbx.mbnbgfmfnt.MBfbnRfgistrbtionExdfption;
import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;

import jbvbx.mbnbgfmfnt.ObjfdtInstbndf;
import jbvbx.mbnbgfmfnt.ObjfdtNbmf;
import jbvbx.mbnbgfmfnt.RfflfdtionExdfption;
import jbvbx.mbnbgfmfnt.RuntimfMBfbnExdfption;
import jbvbx.mbnbgfmfnt.RuntimfOpfrbtionsExdfption;
import jbvbx.mbnbgfmfnt.rfmotf.NotifidbtionRfsult;
import jbvbx.sfdurity.buti.Subjfdt;

/**
 * <p>RMI objfdt usfd to forwbrd bn MBfbnSfrvfr rfqufst from b dlifnt
 * to its MBfbnSfrvfr implfmfntbtion on tif sfrvfr sidf.  Tifrf is onf
 * Rfmotf objfdt implfmfnting tiis intfrfbdf for fbdi rfmotf dlifnt
 * donnfdtfd to bn RMI donnfdtor.</p>
 *
 * <p>Usfr dodf dofs not usublly rfffr to tiis intfrfbdf.  It is
 * spfdififd bs pbrt of tif publid API so tibt difffrfnt
 * implfmfntbtions of tibt API will intfropfrbtf.</p>
 *
 * <p>To fnsurf tibt dlifnt pbrbmftfrs will bf dfsfriblizfd bt tif
 * sfrvfr sidf witi tif dorrfdt dlbsslobdfr, dlifnt pbrbmftfrs sudi bs
 * pbrbmftfrs usfd to invokf b mftiod brf wrbppfd in b {@link
 * MbrsibllfdObjfdt}.  An implfmfntbtion of tiis intfrfbdf must first
 * gft tif bppropribtf dlbss lobdfr for tif opfrbtion bnd its tbrgft,
 * tifn dfsfriblizf tif mbrsibllfd pbrbmftfrs witi tiis dlbsslobdfr.
 * Exdfpt bs notfd, b pbrbmftfr tibt is b
 * <dodf>MbrsibllfdObjfdt</dodf> or <dodf>MbrsibllfdObjfdt[]</dodf>
 * must not bf null; tif bfibvior is unspfdififd if it is.</p>
 *
 * <p>Clbss lobding bspfdts brf dftbilfd in tif
 * <b irff="{@dodRoot}/../tfdinotfs/guidfs/jmx/JMX_1_4_spfdifidbtion.pdf">
 * JMX Spfdifidbtion, vfrsion 1.4</b> PDF dodumfnt.</p>
 *
 * <p>Most mftiods in tiis intfrfbdf pbrbllfl mftiods in tif {@link
 * MBfbnSfrvfrConnfdtion} intfrfbdf.  Wifrf bn bspfdt of tif bfibvior
 * of b mftiod is not spfdififd ifrf, it is tif sbmf bs in tif
 * dorrfsponding <dodf>MBfbnSfrvfrConnfdtion</dodf> mftiod.
 *
 * @sindf 1.5
 */
/*
 * Notidf tibt wf omit tif typf pbrbmftfr from MbrsibllfdObjfdt fvfrywifrf,
 * fvfn tiougi it would bdd usfful informbtion to tif dodumfntbtion.  Tif
 * rfbson is tibt it wbs only bddfd in Mustbng (Jbvb SE 6), wifrfbs vfrsions
 * 1.4 bnd 2.0 of tif JMX API must bf implfmfntbblf on Tigfr pfr our
 * dommitmfnts for JSR 255.  Tiis is blso wiy wf supprfss rbwtypfs wbrnings.
 */
@SupprfssWbrnings("rbwtypfs")
publid intfrfbdf RMIConnfdtion fxtfnds Closfbblf, Rfmotf {
    /**
     * <p>Rfturns tif donnfdtion ID.  Tiis string is difffrfnt for
     * fvfry opfn donnfdtion to b givfn RMI donnfdtor sfrvfr.</p>
     *
     * @rfturn tif donnfdtion ID
     *
     * @sff RMIConnfdtor#donnfdt RMIConnfdtor.donnfdt
     *
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid String gftConnfdtionId() tirows IOExdfption;

    /**
     * <p>Closfs tiis donnfdtion.  On rfturn from tiis mftiod, tif RMI
     * objfdt implfmfnting tiis intfrfbdf is unfxportfd, so furtifr
     * rfmotf dblls to it will fbil.</p>
     *
     * @tirows IOExdfption if tif donnfdtion dould not bf dlosfd,
     * or tif Rfmotf objfdt dould not bf unfxportfd, or tifrf wbs b
     * dommunidbtion fbilurf wifn trbnsmitting tif rfmotf dlosf
     * rfqufst.
     */
    publid void dlosf() tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#drfbtfMBfbn(String,
     * ObjfdtNbmf)}.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn An <dodf>ObjfdtInstbndf</dodf>, dontbining tif
     * <dodf>ObjfdtNbmf</dodf> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.ClbssNotFoundExdfption</dodf> or b
     * <dodf>jbvb.lbng.Exdfption</dodf> tibt oddurrfd
     * wifn trying to invokf tif MBfbn's donstrudtor.
     * @tirows InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @tirows MBfbnRfgistrbtionExdfption Tif
     * <dodf>prfRfgistfr</dodf> (<dodf>MBfbnRfgistrbtion</dodf>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @tirows MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption.
     * @tirows NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <dodf>ObjfdtNbmf</dodf> pbssfd
     * in pbrbmftfr dontbins b pbttfrn or no <dodf>ObjfdtNbmf</dodf>
     * is spfdififd for tif MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                                      ObjfdtNbmf nbmf,
                                      Subjfdt dflfgbtionSubjfdt)
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#drfbtfMBfbn(String,
     * ObjfdtNbmf, ObjfdtNbmf)}.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm lobdfrNbmf Tif objfdt nbmf of tif dlbss lobdfr to bf usfd.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn An <dodf>ObjfdtInstbndf</dodf>, dontbining tif
     * <dodf>ObjfdtNbmf</dodf> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.ClbssNotFoundExdfption</dodf> or b
     * <dodf>jbvb.lbng.Exdfption</dodf> tibt oddurrfd wifn trying to
     * invokf tif MBfbn's donstrudtor.
     * @tirows InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @tirows MBfbnRfgistrbtionExdfption Tif
     * <dodf>prfRfgistfr</dodf> (<dodf>MBfbnRfgistrbtion</dodf>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @tirows MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption.
     * @tirows NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn.
     * @tirows InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr
     * is not rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <dodf>ObjfdtNbmf</dodf> pbssfd
     * in pbrbmftfr dontbins b pbttfrn or no <dodf>ObjfdtNbmf</dodf>
     * is spfdififd for tif MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                                      ObjfdtNbmf nbmf,
                                      ObjfdtNbmf lobdfrNbmf,
                                      Subjfdt dflfgbtionSubjfdt)
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption,
        InstbndfNotFoundExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#drfbtfMBfbn(String,
     * ObjfdtNbmf, Objfdt[], String[])}.  Tif <dodf>Objfdt[]</dodf>
     * pbrbmftfr is wrbppfd in b <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs of tif
     * donstrudtor to bf invokfd, fndbpsulbtfd into b
     * <dodf>MbrsibllfdObjfdt</dodf>.  Tif fndbpsulbtfd brrby dbn bf
     * null, fquivblfnt to bn fmpty brrby.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif
     * donstrudtor to bf invokfd.  Cbn bf null, fquivblfnt to bn fmpty
     * brrby.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn An <dodf>ObjfdtInstbndf</dodf>, dontbining tif
     * <dodf>ObjfdtNbmf</dodf> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.ClbssNotFoundExdfption</dodf> or b
     * <dodf>jbvb.lbng.Exdfption</dodf> tibt oddurrfd wifn trying to
     * invokf tif MBfbn's donstrudtor.
     * @tirows InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @tirows MBfbnRfgistrbtionExdfption Tif
     * <dodf>prfRfgistfr</dodf> (<dodf>MBfbnRfgistrbtion</dodf>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @tirows MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption.
     * @tirows NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <dodf>ObjfdtNbmf</dodf> pbssfd
     * in pbrbmftfr dontbins b pbttfrn, or no <dodf>ObjfdtNbmf</dodf>
     * is spfdififd for tif MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                                ObjfdtNbmf nbmf,
                                MbrsibllfdObjfdt pbrbms,
                                String signbturf[],
                                Subjfdt dflfgbtionSubjfdt)
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#drfbtfMBfbn(String,
     * ObjfdtNbmf, ObjfdtNbmf, Objfdt[], String[])}.  Tif
     * <dodf>Objfdt[]</dodf> pbrbmftfr is wrbppfd in b
     * <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm dlbssNbmf Tif dlbss nbmf of tif MBfbn to bf instbntibtfd.
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn. Mby bf null.
     * @pbrbm lobdfrNbmf Tif objfdt nbmf of tif dlbss lobdfr to bf usfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs of tif
     * donstrudtor to bf invokfd, fndbpsulbtfd into b
     * <dodf>MbrsibllfdObjfdt</dodf>.  Tif fndbpsulbtfd brrby dbn bf
     * null, fquivblfnt to bn fmpty brrby.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif
     * donstrudtor to bf invokfd.  Cbn bf null, fquivblfnt to bn fmpty
     * brrby.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn An <dodf>ObjfdtInstbndf</dodf>, dontbining tif
     * <dodf>ObjfdtNbmf</dodf> bnd tif Jbvb dlbss nbmf of tif nfwly
     * instbntibtfd MBfbn.  If tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>n</dodf>, tif dontbinfd Jbvb dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(n)}.gftClbssNbmf()</dodf>.
     *
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.ClbssNotFoundExdfption</dodf> or b
     * <dodf>jbvb.lbng.Exdfption</dodf> tibt oddurrfd wifn trying to
     * invokf tif MBfbn's donstrudtor.
     * @tirows InstbndfAlrfbdyExistsExdfption Tif MBfbn is blrfbdy
     * undfr tif dontrol of tif MBfbn sfrvfr.
     * @tirows MBfbnRfgistrbtionExdfption Tif
     * <dodf>prfRfgistfr</dodf> (<dodf>MBfbnRfgistrbtion</dodf>
     * intfrfbdf) mftiod of tif MBfbn ibs tirown bn fxdfption. Tif
     * MBfbn will not bf rfgistfrfd.
     * @tirows MBfbnExdfption Tif donstrudtor of tif MBfbn ibs
     * tirown bn fxdfption.
     * @tirows NotComplibntMBfbnExdfption Tiis dlbss is not b JMX
     * domplibnt MBfbn.
     * @tirows InstbndfNotFoundExdfption Tif spfdififd dlbss lobdfr
     * is not rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif dlbssNbmf
     * pbssfd in pbrbmftfr is null, tif <dodf>ObjfdtNbmf</dodf> pbssfd
     * in pbrbmftfr dontbins b pbttfrn, or no <dodf>ObjfdtNbmf</dodf>
     * is spfdififd for tif MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid ObjfdtInstbndf drfbtfMBfbn(String dlbssNbmf,
                                ObjfdtNbmf nbmf,
                                ObjfdtNbmf lobdfrNbmf,
                                MbrsibllfdObjfdt pbrbms,
                                String signbturf[],
                                Subjfdt dflfgbtionSubjfdt)
        tirows
        RfflfdtionExdfption,
        InstbndfAlrfbdyExistsExdfption,
        MBfbnRfgistrbtionExdfption,
        MBfbnExdfption,
        NotComplibntMBfbnExdfption,
        InstbndfNotFoundExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#unrfgistfrMBfbn(ObjfdtNbmf)}.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn to bf unrfgistfrfd.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows MBfbnRfgistrbtionExdfption Tif prfDfrfgistfr
     * ((<dodf>MBfbnRfgistrbtion</dodf> intfrfbdf) mftiod of tif MBfbn
     * ibs tirown bn fxdfption.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null or tif MBfbn you brf wifn trying to
     * unrfgistfr is tif {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrDflfgbtf
     * MBfbnSfrvfrDflfgbtf} MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid void unrfgistfrMBfbn(ObjfdtNbmf nbmf, Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        MBfbnRfgistrbtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftObjfdtInstbndf(ObjfdtNbmf)}.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn Tif <dodf>ObjfdtInstbndf</dodf> bssodibtfd witi tif MBfbn
     * spfdififd by <vbr>nbmf</vbr>.  Tif dontbinfd <dodf>ObjfdtNbmf</dodf>
     * is <dodf>nbmf</dodf> bnd tif dontbinfd dlbss nbmf is
     * <dodf>{@link #gftMBfbnInfo gftMBfbnInfo(nbmf)}.gftClbssNbmf()</dodf>.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid ObjfdtInstbndf gftObjfdtInstbndf(ObjfdtNbmf nbmf,
                                            Subjfdt dflfgbtionSubjfdt)
        tirows InstbndfNotFoundExdfption, IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#qufryMBfbns(ObjfdtNbmf,
     * QufryExp)}.  Tif <dodf>QufryExp</dodf> is wrbppfd in b
     * <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif objfdt nbmf pbttfrn idfntifying tif MBfbns to
     * bf rftrifvfd. If null or no dombin bnd kfy propfrtifs brf
     * spfdififd, bll tif MBfbns rfgistfrfd will bf rftrifvfd.
     * @pbrbm qufry Tif qufry fxprfssion to bf bpplifd for sflfdting
     * MBfbns, fndbpsulbtfd into b <dodf>MbrsibllfdObjfdt</dodf>. If
     * tif <dodf>MbrsibllfdObjfdt</dodf> fndbpsulbtfs b null vbluf no
     * qufry fxprfssion will bf bpplifd for sflfdting MBfbns.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn A sft dontbining tif <dodf>ObjfdtInstbndf</dodf>
     * objfdts for tif sflfdtfd MBfbns.  If no MBfbn sbtisfifs tif
     * qufry bn fmpty list is rfturnfd.
     *
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid Sft<ObjfdtInstbndf>
        qufryMBfbns(ObjfdtNbmf nbmf,
                    MbrsibllfdObjfdt qufry,
                    Subjfdt dflfgbtionSubjfdt)
        tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#qufryNbmfs(ObjfdtNbmf,
     * QufryExp)}.  Tif <dodf>QufryExp</dodf> is wrbppfd in b
     * <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif objfdt nbmf pbttfrn idfntifying tif MBfbn nbmfs
     * to bf rftrifvfd. If null or no dombin bnd kfy propfrtifs brf
     * spfdififd, tif nbmf of bll rfgistfrfd MBfbns will bf rftrifvfd.
     * @pbrbm qufry Tif qufry fxprfssion to bf bpplifd for sflfdting
     * MBfbns, fndbpsulbtfd into b <dodf>MbrsibllfdObjfdt</dodf>. If
     * tif <dodf>MbrsibllfdObjfdt</dodf> fndbpsulbtfs b null vbluf no
     * qufry fxprfssion will bf bpplifd for sflfdting MBfbns.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn A sft dontbining tif ObjfdtNbmfs for tif MBfbns
     * sflfdtfd.  If no MBfbn sbtisfifs tif qufry, bn fmpty list is
     * rfturnfd.
     *
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid Sft<ObjfdtNbmf>
        qufryNbmfs(ObjfdtNbmf nbmf,
                   MbrsibllfdObjfdt qufry,
                   Subjfdt dflfgbtionSubjfdt)
        tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#isRfgistfrfd(ObjfdtNbmf)}.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn to bf difdkfd.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn Truf if tif MBfbn is blrfbdy rfgistfrfd in tif MBfbn
     * sfrvfr, fblsf otifrwisf.
     *
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid boolfbn isRfgistfrfd(ObjfdtNbmf nbmf, Subjfdt dflfgbtionSubjfdt)
        tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftMBfbnCount()}.
     *
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn tif numbfr of MBfbns rfgistfrfd.
     *
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid Intfgfr gftMBfbnCount(Subjfdt dflfgbtionSubjfdt)
        tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftAttributf(ObjfdtNbmf,
     * String)}.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn from wiidi tif
     * bttributf is to bf rftrifvfd.
     * @pbrbm bttributf A String spfdifying tif nbmf of tif bttributf
     * to bf rftrifvfd.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn  Tif vbluf of tif rftrifvfd bttributf.
     *
     * @tirows AttributfNotFoundExdfption Tif bttributf spfdififd
     * is not bddfssiblf in tif MBfbn.
     * @tirows MBfbnExdfption Wrbps bn fxdfption tirown by tif
     * MBfbn's gfttfr.
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.Exdfption</dodf> tirown wifn trying to invokf
     * tif gfttfr.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null or tif bttributf in pbrbmftfr is
     * null.
     * @tirows RuntimfMBfbnExdfption Wrbps b runtimf fxdfption tirown
     * by tif MBfbn's gfttfr.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     *
     * @sff #sftAttributf
     */
    publid Objfdt gftAttributf(ObjfdtNbmf nbmf,
                               String bttributf,
                               Subjfdt dflfgbtionSubjfdt)
        tirows
        MBfbnExdfption,
        AttributfNotFoundExdfption,
        InstbndfNotFoundExdfption,
        RfflfdtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftAttributfs(ObjfdtNbmf,
     * String[])}.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn from wiidi tif
     * bttributfs brf rftrifvfd.
     * @pbrbm bttributfs A list of tif bttributfs to bf rftrifvfd.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn Tif list of tif rftrifvfd bttributfs.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows RfflfdtionExdfption An fxdfption oddurrfd wifn
     * trying to invokf tif gftAttributfs mftiod of b Dynbmid MBfbn.
     * @tirows RuntimfOpfrbtionsExdfption Wrbp b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null or bttributfs in pbrbmftfr is null.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     *
     * @sff #sftAttributfs
     */
    publid AttributfList gftAttributfs(ObjfdtNbmf nbmf,
                                       String[] bttributfs,
                                       Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        RfflfdtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#sftAttributf(ObjfdtNbmf,
     * Attributf)}.  Tif <dodf>Attributf</dodf> pbrbmftfr is wrbppfd
     * in b <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn witiin wiidi tif bttributf is
     * to bf sft.
     * @pbrbm bttributf Tif idfntifidbtion of tif bttributf to bf sft
     * bnd tif vbluf it is to bf sft to, fndbpsulbtfd into b
     * <dodf>MbrsibllfdObjfdt</dodf>.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows AttributfNotFoundExdfption Tif bttributf spfdififd
     * is not bddfssiblf in tif MBfbn.
     * @tirows InvblidAttributfVblufExdfption Tif vbluf spfdififd
     * for tif bttributf is not vblid.
     * @tirows MBfbnExdfption Wrbps bn fxdfption tirown by tif
     * MBfbn's sfttfr.
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.Exdfption</dodf> tirown wifn trying to invokf
     * tif sfttfr.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null or tif bttributf in pbrbmftfr is
     * null.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     *
     * @sff #gftAttributf
     */
    publid void sftAttributf(ObjfdtNbmf nbmf,
                             MbrsibllfdObjfdt bttributf,
                             Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        AttributfNotFoundExdfption,
        InvblidAttributfVblufExdfption,
        MBfbnExdfption,
        RfflfdtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#sftAttributfs(ObjfdtNbmf,
     * AttributfList)}.  Tif <dodf>AttributfList</dodf> pbrbmftfr is
     * wrbppfd in b <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn witiin wiidi tif
     * bttributfs brf to bf sft.
     * @pbrbm bttributfs A list of bttributfs: Tif idfntifidbtion of
     * tif bttributfs to bf sft bnd tif vblufs tify brf to bf sft to,
     * fndbpsulbtfd into b <dodf>MbrsibllfdObjfdt</dodf>.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn Tif list of bttributfs tibt wfrf sft, witi tifir nfw
     * vblufs.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows RfflfdtionExdfption An fxdfption oddurrfd wifn
     * trying to invokf tif gftAttributfs mftiod of b Dynbmid MBfbn.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null or bttributfs in pbrbmftfr is null.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     *
     * @sff #gftAttributfs
     */
    publid AttributfList sftAttributfs(ObjfdtNbmf nbmf,
                          MbrsibllfdObjfdt bttributfs,
                          Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        RfflfdtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#invokf(ObjfdtNbmf,
     * String, Objfdt[], String[])}.  Tif <dodf>Objfdt[]</dodf>
     * pbrbmftfr is wrbppfd in b <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif objfdt nbmf of tif MBfbn on wiidi tif mftiod is
     * to bf invokfd.
     * @pbrbm opfrbtionNbmf Tif nbmf of tif opfrbtion to bf invokfd.
     * @pbrbm pbrbms An brrby dontbining tif pbrbmftfrs to bf sft wifn
     * tif opfrbtion is invokfd, fndbpsulbtfd into b
     * <dodf>MbrsibllfdObjfdt</dodf>.  Tif fndbpsulbtfd brrby dbn bf
     * null, fquivblfnt to bn fmpty brrby.
     * @pbrbm signbturf An brrby dontbining tif signbturf of tif
     * opfrbtion. Tif dlbss objfdts will bf lobdfd using tif sbmf
     * dlbss lobdfr bs tif onf usfd for lobding tif MBfbn on wiidi tif
     * opfrbtion wbs invokfd.  Cbn bf null, fquivblfnt to bn fmpty
     * brrby.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn Tif objfdt rfturnfd by tif opfrbtion, wiidi rfprfsfnts
     * tif rfsult of invoking tif opfrbtion on tif MBfbn spfdififd.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows MBfbnExdfption Wrbps bn fxdfption tirown by tif
     * MBfbn's invokfd mftiod.
     * @tirows RfflfdtionExdfption Wrbps b
     * <dodf>jbvb.lbng.Exdfption</dodf> tirown wiilf trying to invokf
     * tif mftiod.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps bn {@link
     * IllfgblArgumfntExdfption} wifn <dodf>nbmf</dodf> or
     * <dodf>opfrbtionNbmf</dodf> is null.
     */
    publid Objfdt invokf(ObjfdtNbmf nbmf,
                         String opfrbtionNbmf,
                         MbrsibllfdObjfdt pbrbms,
                         String signbturf[],
                         Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        MBfbnExdfption,
        RfflfdtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftDffbultDombin()}.
     *
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn tif dffbult dombin.
     *
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid String gftDffbultDombin(Subjfdt dflfgbtionSubjfdt)
        tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftDombins()}.
     *
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn tif list of dombins.
     *
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid String[] gftDombins(Subjfdt dflfgbtionSubjfdt)
        tirows IOExdfption;

    /**
     * Hbndlfs tif mftiod
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#gftMBfbnInfo(ObjfdtNbmf)}.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn to bnblyzf
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn An instbndf of <dodf>MBfbnInfo</dodf> bllowing tif
     * rftrifvbl of bll bttributfs bnd opfrbtions of tiis MBfbn.
     *
     * @tirows IntrospfdtionExdfption An fxdfption oddurrfd during
     * introspfdtion.
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd wbs
     * not found.
     * @tirows RfflfdtionExdfption An fxdfption oddurrfd wifn
     * trying to invokf tif gftMBfbnInfo of b Dynbmid MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null.
     */
    publid MBfbnInfo gftMBfbnInfo(ObjfdtNbmf nbmf, Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        IntrospfdtionExdfption,
        RfflfdtionExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#isInstbndfOf(ObjfdtNbmf,
     * String)}.
     *
     * @pbrbm nbmf Tif <dodf>ObjfdtNbmf</dodf> of tif MBfbn.
     * @pbrbm dlbssNbmf Tif nbmf of tif dlbss.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @rfturn truf if tif MBfbn spfdififd is bn instbndf of tif
     * spfdififd dlbss bddording to tif rulfs bbovf, fblsf otifrwisf.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn spfdififd is not
     * rfgistfrfd in tif MBfbn sfrvfr.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps b
     * <dodf>jbvb.lbng.IllfgblArgumfntExdfption</dodf>: Tif objfdt
     * nbmf in pbrbmftfr is null.
     */
    publid boolfbn isInstbndfOf(ObjfdtNbmf nbmf,
                                String dlbssNbmf,
                                Subjfdt dflfgbtionSubjfdt)
        tirows InstbndfNotFoundExdfption, IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#bddNotifidbtionListfnfr(ObjfdtNbmf,
     * ObjfdtNbmf, NotifidbtionFiltfr, Objfdt)}.  Tif
     * <dodf>NotifidbtionFiltfr</dodf> pbrbmftfr is wrbppfd in b
     * <dodf>MbrsibllfdObjfdt</dodf>.  Tif <dodf>Objfdt</dodf>
     * (ibndbbdk) pbrbmftfr is blso wrbppfd in b
     * <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf bddfd.
     * @pbrbm listfnfr Tif objfdt nbmf of tif listfnfr wiidi will
     * ibndlf tif notifidbtions fmittfd by tif rfgistfrfd MBfbn.
     * @pbrbm filtfr Tif filtfr objfdt, fndbpsulbtfd into b
     * <dodf>MbrsibllfdObjfdt</dodf>. If filtfr fndbpsulbtfd in tif
     * <dodf>MbrsibllfdObjfdt</dodf> ibs b null vbluf, no filtfring
     * will bf pfrformfd bfforf ibndling notifidbtions.
     * @pbrbm ibndbbdk Tif dontfxt to bf sfnt to tif listfnfr wifn b
     * notifidbtion is fmittfd, fndbpsulbtfd into b
     * <dodf>MbrsibllfdObjfdt</dodf>.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn nbmf of tif
     * notifidbtion listfnfr or of tif notifidbtion brobddbstfr dofs
     * not mbtdi bny of tif rfgistfrfd MBfbns.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps bn {@link
     * IllfgblArgumfntExdfption}.  Tif MBfbn nbmfd by
     * <dodf>listfnfr</dodf> fxists but dofs not implfmfnt tif
     * {@link jbvbx.mbnbgfmfnt.NotifidbtionListfnfr} intfrfbdf,
     * or <dodf>nbmf</dodf> or <dodf>listfnfr</dodf> is null.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     *
     * @sff #rfmovfNotifidbtionListfnfr(ObjfdtNbmf, ObjfdtNbmf, Subjfdt)
     * @sff #rfmovfNotifidbtionListfnfr(ObjfdtNbmf, ObjfdtNbmf,
     * MbrsibllfdObjfdt, MbrsibllfdObjfdt, Subjfdt)
     */
    publid void bddNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                        ObjfdtNbmf listfnfr,
                        MbrsibllfdObjfdt filtfr,
                        MbrsibllfdObjfdt ibndbbdk,
                        Subjfdt dflfgbtionSubjfdt)
        tirows InstbndfNotFoundExdfption, IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#rfmovfNotifidbtionListfnfr(ObjfdtNbmf,
     * ObjfdtNbmf)}.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf rfmovfd.
     * @pbrbm listfnfr Tif objfdt nbmf of tif listfnfr to bf rfmovfd.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @tirows ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd in tif MBfbn.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps bn {@link
     * IllfgblArgumfntExdfption} wifn <dodf>nbmf</dodf> or
     * <dodf>listfnfr</dodf> is null.
     *
     * @sff #bddNotifidbtionListfnfr
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                                           ObjfdtNbmf listfnfr,
                                           Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        ListfnfrNotFoundExdfption,
        IOExdfption;

    /**
     * Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#rfmovfNotifidbtionListfnfr(ObjfdtNbmf,
     * ObjfdtNbmf, NotifidbtionFiltfr, Objfdt)}.  Tif
     * <dodf>NotifidbtionFiltfr</dodf> pbrbmftfr is wrbppfd in b
     * <dodf>MbrsibllfdObjfdt</dodf>.  Tif <dodf>Objfdt</dodf>
     * pbrbmftfr is blso wrbppfd in b <dodf>MbrsibllfdObjfdt</dodf>.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif listfnfr siould
     * bf rfmovfd.
     * @pbrbm listfnfr A listfnfr tibt wbs prfviously bddfd to tiis
     * MBfbn.
     * @pbrbm filtfr Tif filtfr tibt wbs spfdififd wifn tif listfnfr
     * wbs bddfd, fndbpsulbtfd into b <dodf>MbrsibllfdObjfdt</dodf>.
     * @pbrbm ibndbbdk Tif ibndbbdk tibt wbs spfdififd wifn tif
     * listfnfr wbs bddfd, fndbpsulbtfd into b <dodf>MbrsibllfdObjfdt</dodf>.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @tirows InstbndfNotFoundExdfption Tif MBfbn nbmf providfd
     * dofs not mbtdi bny of tif rfgistfrfd MBfbns.
     * @tirows ListfnfrNotFoundExdfption Tif listfnfr is not
     * rfgistfrfd in tif MBfbn, or it is not rfgistfrfd witi tif givfn
     * filtfr bnd ibndbbdk.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to pfrform tiis opfrbtion.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     * @tirows RuntimfOpfrbtionsExdfption Wrbps bn {@link
     * IllfgblArgumfntExdfption} wifn <dodf>nbmf</dodf> or
     * <dodf>listfnfr</dodf> is null.
     *
     * @sff #bddNotifidbtionListfnfr
     */
    publid void rfmovfNotifidbtionListfnfr(ObjfdtNbmf nbmf,
                      ObjfdtNbmf listfnfr,
                      MbrsibllfdObjfdt filtfr,
                      MbrsibllfdObjfdt ibndbbdk,
                      Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        ListfnfrNotFoundExdfption,
        IOExdfption;

    // Spfdibl Hbndling of Notifidbtions -------------------------------------

    /**
     * <p>Hbndlfs tif mftiod {@link
     * jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#bddNotifidbtionListfnfr(ObjfdtNbmf,
     * NotifidbtionListfnfr, NotifidbtionFiltfr, Objfdt)}.</p>
     *
     * <p>Rfgistfr for notifidbtions from tif givfn MBfbns tibt mbtdi
     * tif givfn filtfrs.  Tif rfmotf dlifnt dbn subsfqufntly rftrifvf
     * tif notifidbtions using tif {@link #fftdiNotifidbtions
     * fftdiNotifidbtions} mftiod.</p>
     *
     * <p>For fbdi listfnfr, tif originbl
     * <dodf>NotifidbtionListfnfr</dodf> bnd <dodf>ibndbbdk</dodf> brf
     * kfpt on tif dlifnt sidf; in ordfr for tif dlifnt to bf bblf to
     * idfntify tifm, tif sfrvfr gfnfrbtfs bnd rfturns b uniquf
     * <dodf>listfnfrID</dodf>.  Tiis <dodf>listfnfrID</dodf> is
     * forwbrdfd witi tif <dodf>Notifidbtions</dodf> to tif rfmotf
     * dlifnt.</p>
     *
     * <p>If bny onf of tif givfn (nbmf, filtfr) pbirs dbnnot bf
     * rfgistfrfd, tifn tif opfrbtion fbils witi bn fxdfption, bnd no
     * nbmfs or filtfrs brf rfgistfrfd.</p>
     *
     * @pbrbm nbmfs tif <dodf>ObjfdtNbmfs</dodf> idfntifying tif
     * MBfbns fmitting tif Notifidbtions.
     * @pbrbm filtfrs bn brrby of mbrsibllfd rfprfsfntbtions of tif
     * <dodf>NotifidbtionFiltfrs</dodf>.  Elfmfnts of tiis brrby dbn
     * bf null.
     * @pbrbm dflfgbtionSubjfdts tif <dodf>Subjfdts</dodf> on bfiblf
     * of wiidi tif listfnfrs brf bfing bddfd.  Elfmfnts of tiis brrby
     * dbn bf null.  Also, tif <dodf>dflfgbtionSubjfdts</dodf>
     * pbrbmftfr itsflf dbn bf null, wiidi is fquivblfnt to bn brrby
     * of null vblufs witi tif sbmf sizf bs tif <dodf>nbmfs</dodf> bnd
     * <dodf>filtfrs</dodf> brrbys.
     *
     * @rfturn bn brrby of <dodf>listfnfrIDs</dodf> idfntifying tif
     * lodbl listfnfrs.  Tiis brrby ibs tif sbmf numbfr of flfmfnts bs
     * tif pbrbmftfrs.
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>nbmfs</dodf> or
     * <dodf>filtfrs</dodf> is null, or if <dodf>nbmfs</dodf> dontbins
     * b null flfmfnt, or if tif tirff brrbys do not bll ibvf tif sbmf
     * sizf.
     * @tirows ClbssCbstExdfption if onf of tif flfmfnts of
     * <dodf>filtfrs</dodf> unmbrsiblls bs b non-null objfdt tibt is
     * not b <dodf>NotifidbtionFiltfr</dodf>.
     * @tirows InstbndfNotFoundExdfption if onf of tif
     * <dodf>nbmfs</dodf> dofs not dorrfspond to bny rfgistfrfd MBfbn.
     * @tirows SfdurityExdfption if, for onf of tif MBfbns, tif
     * dlifnt, or tif dflfgbtfd Subjfdt if bny, dofs not ibvf
     * pfrmission to bdd b listfnfr.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid Intfgfr[] bddNotifidbtionListfnfrs(ObjfdtNbmf[] nbmfs,
                    MbrsibllfdObjfdt[] filtfrs,
                    Subjfdt[] dflfgbtionSubjfdts)
        tirows InstbndfNotFoundExdfption, IOExdfption;

    /**
     * <p>Hbndlfs tif
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#rfmovfNotifidbtionListfnfr(ObjfdtNbmf,NotifidbtionListfnfr)
     * rfmovfNotifidbtionListfnfr(ObjfdtNbmf, NotifidbtionListfnfr)} bnd
     * {@link jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion#rfmovfNotifidbtionListfnfr(ObjfdtNbmf,NotifidbtionListfnfr,NotifidbtionFiltfr,Objfdt)
     * rfmovfNotifidbtionListfnfr(ObjfdtNbmf, NotifidbtionListfnfr, NotifidbtionFiltfr, Objfdt)} mftiods.</p>
     *
     * <p>Tiis mftiod rfmovfs onf or morf
     * <dodf>NotifidbtionListfnfr</dodf>s from b givfn MBfbn in tif
     * MBfbn sfrvfr.</p>
     *
     * <p>Tif <dodf>NotifidbtionListfnfrs</dodf> brf idfntififd by tif
     * IDs wiidi wfrf rfturnfd by tif {@link
     * #bddNotifidbtionListfnfrs(ObjfdtNbmf[], MbrsibllfdObjfdt[],
     * Subjfdt[])} mftiod.</p>
     *
     * @pbrbm nbmf tif <dodf>ObjfdtNbmf</dodf> idfntifying tif MBfbn
     * fmitting tif Notifidbtions.
     * @pbrbm listfnfrIDs tif list of tif IDs dorrfsponding to tif
     * listfnfrs to rfmovf.
     * @pbrbm dflfgbtionSubjfdt Tif <dodf>Subjfdt</dodf> dontbining tif
     * dflfgbtion prindipbls or <dodf>null</dodf> if tif butifntidbtion
     * prindipbl is usfd instfbd.
     *
     * @tirows InstbndfNotFoundExdfption if tif givfn
     * <dodf>nbmf</dodf> dofs not dorrfspond to bny rfgistfrfd MBfbn.
     * @tirows ListfnfrNotFoundExdfption if onf of tif listfnfrs wbs
     * not found on tif sfrvfr sidf.  Tiis fxdfption dbn ibppfn if tif
     * MBfbn disdbrdfd b listfnfr for somf rfbson otifr tibn b dbll to
     * <dodf>MBfbnSfrvfr.rfmovfNotifidbtionListfnfr</dodf>.
     * @tirows SfdurityExdfption if tif dlifnt, or tif dflfgbtfd Subjfdt
     * if bny, dofs not ibvf pfrmission to rfmovf tif listfnfrs.
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     * @tirows IllfgblArgumfntExdfption if <dodf>ObjfdtNbmf</dodf> or
     * <dodf>listfnfrIds</dodf> is null or if <dodf>listfnfrIds</dodf>
     * dontbins b null flfmfnt.
     */
    publid void rfmovfNotifidbtionListfnfrs(ObjfdtNbmf nbmf,
                                            Intfgfr[] listfnfrIDs,
                                            Subjfdt dflfgbtionSubjfdt)
        tirows
        InstbndfNotFoundExdfption,
        ListfnfrNotFoundExdfption,
        IOExdfption;

    /**
     * <p>Rftrifvfs notifidbtions from tif donnfdtor sfrvfr.  Tiis
     * mftiod dbn blodk until tifrf is bt lfbst onf notifidbtion or
     * until tif spfdififd timfout is rfbdifd.  Tif mftiod dbn blso
     * rfturn bt bny timf witi zfro notifidbtions.</p>
     *
     * <p>A notifidbtion dbn bf indludfd in tif rfsult if its sfqufndf
     * numbfr is no lfss tibn <dodf>dlifntSfqufndfNumbfr</dodf> bnd
     * tiis dlifnt ibs rfgistfrfd bt lfbst onf listfnfr for tif MBfbn
     * gfnfrbting tif notifidbtion, witi b filtfr tibt bddfpts tif
     * notifidbtion.  Ebdi listfnfr tibt is intfrfstfd in tif
     * notifidbtion is idfntififd by bn Intfgfr ID tibt wbs rfturnfd
     * by {@link #bddNotifidbtionListfnfrs(ObjfdtNbmf[],
     * MbrsibllfdObjfdt[], Subjfdt[])}.</p>
     *
     * @pbrbm dlifntSfqufndfNumbfr tif first sfqufndf numbfr tibt tif
     * dlifnt is intfrfstfd in.  If nfgbtivf, it is intfrprftfd bs
     * mfbning tif sfqufndf numbfr tibt tif nfxt notifidbtion will
     * ibvf.
     *
     * @pbrbm mbxNotifidbtions tif mbximum numbfr of difffrfnt
     * notifidbtions to rfturn.  Tif <dodf>TbrgftfdNotifidbtion</dodf>
     * brrby in tif rfturnfd <dodf>NotifidbtionRfsult</dodf> dbn ibvf
     * morf flfmfnts tibn tiis if tif sbmf notifidbtion bppfbrs morf
     * tibn ondf.  Tif bfibvior is unspfdififd if tiis pbrbmftfr is
     * nfgbtivf.
     *
     * @pbrbm timfout tif mbximum timf in millisfdonds to wbit for b
     * notifidbtion to brrivf.  Tiis dbn bf 0 to indidbtf tibt tif
     * mftiod siould not wbit if tifrf brf no notifidbtions, but
     * siould rfturn bt ondf.  It dbn bf <dodf>Long.MAX_VALUE</dodf>
     * to indidbtf tibt tifrf is no timfout.  Tif bfibvior is
     * unspfdififd if tiis pbrbmftfr is nfgbtivf.
     *
     * @rfturn A <dodf>NotifidbtionRfsult</dodf>.
     *
     * @tirows IOExdfption if b gfnfrbl dommunidbtion fxdfption oddurrfd.
     */
    publid NotifidbtionRfsult fftdiNotifidbtions(long dlifntSfqufndfNumbfr,
                                                 int mbxNotifidbtions,
                                                 long timfout)
            tirows IOExdfption;
}
