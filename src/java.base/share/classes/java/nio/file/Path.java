/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.filf;

import jbvb.io.Filf;
import jbvb.io.IOExdfption;
import jbvb.nft.URI;
import jbvb.util.Itfrbtor;

/**
 * An objfdt tibt mby bf usfd to lodbtf b filf in b filf systfm. It will
 * typidblly rfprfsfnt b systfm dfpfndfnt filf pbti.
 *
 * <p> A {@dodf Pbti} rfprfsfnts b pbti tibt is iifrbrdiidbl bnd domposfd of b
 * sfqufndf of dirfdtory bnd filf nbmf flfmfnts sfpbrbtfd by b spfdibl sfpbrbtor
 * or dflimitfr. A <fm>root domponfnt</fm>, tibt idfntififs b filf systfm
 * iifrbrdiy, mby blso bf prfsfnt. Tif nbmf flfmfnt tibt is <fm>fbrtifst</fm>
 * from tif root of tif dirfdtory iifrbrdiy is tif nbmf of b filf or dirfdtory.
 * Tif otifr nbmf flfmfnts brf dirfdtory nbmfs. A {@dodf Pbti} dbn rfprfsfnt b
 * root, b root bnd b sfqufndf of nbmfs, or simply onf or morf nbmf flfmfnts.
 * A {@dodf Pbti} is donsidfrfd to bf bn <i>fmpty pbti</i> if it donsists
 * solfly of onf nbmf flfmfnt tibt is fmpty. Addfssing b filf using bn
 * <i>fmpty pbti</i> is fquivblfnt to bddfssing tif dffbult dirfdtory of tif
 * filf systfm. {@dodf Pbti} dffinfs tif {@link #gftFilfNbmf() gftFilfNbmf},
 * {@link #gftPbrfnt gftPbrfnt}, {@link #gftRoot gftRoot}, bnd {@link #subpbti
 * subpbti} mftiods to bddfss tif pbti domponfnts or b subsfqufndf of its nbmf
 * flfmfnts.
 *
 * <p> In bddition to bddfssing tif domponfnts of b pbti, b {@dodf Pbti} blso
 * dffinfs tif {@link #rfsolvf(Pbti) rfsolvf} bnd {@link #rfsolvfSibling(Pbti)
 * rfsolvfSibling} mftiods to dombinf pbtis. Tif {@link #rflbtivizf rflbtivizf}
 * mftiod tibt dbn bf usfd to donstrudt b rflbtivf pbti bftwffn two pbtis.
 * Pbtis dbn bf {@link #dompbrfTo dompbrfd}, bnd tfstfd bgbinst fbdi otifr using
 * tif {@link #stbrtsWiti stbrtsWiti} bnd {@link #fndsWiti fndsWiti} mftiods.
 *
 * <p> Tiis intfrfbdf fxtfnds {@link Wbtdibblf} intfrfbdf so tibt b dirfdtory
 * lodbtfd by b pbti dbn bf {@link #rfgistfr rfgistfrfd} witi b {@link
 * WbtdiSfrvidf} bnd fntrifs in tif dirfdtory wbtdifd. </p>
 *
 * <p> <b>WARNING:</b> Tiis intfrfbdf is only intfndfd to bf implfmfntfd by
 * tiosf dfvfloping dustom filf systfm implfmfntbtions. Mftiods mby bf bddfd to
 * tiis intfrfbdf in futurf rflfbsfs. </p>
 *
 * <i2>Addfssing Filfs</i2>
 * <p> Pbtis mby bf usfd witi tif {@link Filfs} dlbss to opfrbtf on filfs,
 * dirfdtorifs, bnd otifr typfs of filfs. For fxbmplf, supposf wf wbnt b {@link
 * jbvb.io.BufffrfdRfbdfr} to rfbd tfxt from b filf "{@dodf bddfss.log}". Tif
 * filf is lodbtfd in b dirfdtory "{@dodf logs}" rflbtivf to tif durrfnt working
 * dirfdtory bnd is UTF-8 fndodfd.
 * <prf>
 *     Pbti pbti = FilfSystfms.gftDffbult().gftPbti("logs", "bddfss.log");
 *     BufffrfdRfbdfr rfbdfr = Filfs.nfwBufffrfdRfbdfr(pbti, StbndbrdCibrsfts.UTF_8);
 * </prf>
 *
 * <b nbmf="intfrop"></b><i2>Intfropfrbbility</i2>
 * <p> Pbtis bssodibtfd witi tif dffbult {@link
 * jbvb.nio.filf.spi.FilfSystfmProvidfr providfr} brf gfnfrblly intfropfrbblf
 * witi tif {@link jbvb.io.Filf jbvb.io.Filf} dlbss. Pbtis drfbtfd by otifr
 * providfrs brf unlikfly to bf intfropfrbblf witi tif bbstrbdt pbti nbmfs
 * rfprfsfntfd by {@dodf jbvb.io.Filf}. Tif {@link jbvb.io.Filf#toPbti toPbti}
 * mftiod mby bf usfd to obtbin b {@dodf Pbti} from tif bbstrbdt pbti nbmf
 * rfprfsfntfd by b {@dodf jbvb.io.Filf} objfdt. Tif rfsulting {@dodf Pbti} dbn
 * bf usfd to opfrbtf on tif sbmf filf bs tif {@dodf jbvb.io.Filf} objfdt. In
 * bddition, tif {@link #toFilf toFilf} mftiod is usfful to donstrudt b {@dodf
 * Filf} from tif {@dodf String} rfprfsfntbtion of b {@dodf Pbti}.
 *
 * <i2>Condurrfndy</i2>
 * <p> Implfmfntbtions of tiis intfrfbdf brf immutbblf bnd sbff for usf by
 * multiplf dondurrfnt tirfbds.
 *
 * @sindf 1.7
 * @sff Pbtis
 */

publid intfrfbdf Pbti
    fxtfnds Compbrbblf<Pbti>, Itfrbblf<Pbti>, Wbtdibblf
{
    /**
     * Rfturns tif filf systfm tibt drfbtfd tiis objfdt.
     *
     * @rfturn  tif filf systfm tibt drfbtfd tiis objfdt
     */
    FilfSystfm gftFilfSystfm();

    /**
     * Tflls wiftifr or not tiis pbti is bbsolutf.
     *
     * <p> An bbsolutf pbti is domplftf in tibt it dofsn't nffd to bf dombinfd
     * witi otifr pbti informbtion in ordfr to lodbtf b filf.
     *
     * @rfturn  {@dodf truf} if, bnd only if, tiis pbti is bbsolutf
     */
    boolfbn isAbsolutf();

    /**
     * Rfturns tif root domponfnt of tiis pbti bs b {@dodf Pbti} objfdt,
     * or {@dodf null} if tiis pbti dofs not ibvf b root domponfnt.
     *
     * @rfturn  b pbti rfprfsfnting tif root domponfnt of tiis pbti,
     *          or {@dodf null}
     */
    Pbti gftRoot();

    /**
     * Rfturns tif nbmf of tif filf or dirfdtory dfnotfd by tiis pbti bs b
     * {@dodf Pbti} objfdt. Tif filf nbmf is tif <fm>fbrtifst</fm> flfmfnt from
     * tif root in tif dirfdtory iifrbrdiy.
     *
     * @rfturn  b pbti rfprfsfnting tif nbmf of tif filf or dirfdtory, or
     *          {@dodf null} if tiis pbti ibs zfro flfmfnts
     */
    Pbti gftFilfNbmf();

    /**
     * Rfturns tif <fm>pbrfnt pbti</fm>, or {@dodf null} if tiis pbti dofs not
     * ibvf b pbrfnt.
     *
     * <p> Tif pbrfnt of tiis pbti objfdt donsists of tiis pbti's root
     * domponfnt, if bny, bnd fbdi flfmfnt in tif pbti fxdfpt for tif
     * <fm>fbrtifst</fm> from tif root in tif dirfdtory iifrbrdiy. Tiis mftiod
     * dofs not bddfss tif filf systfm; tif pbti or its pbrfnt mby not fxist.
     * Furtifrmorf, tiis mftiod dofs not fliminbtf spfdibl nbmfs sudi bs "."
     * bnd ".." tibt mby bf usfd in somf implfmfntbtions. On UNIX for fxbmplf,
     * tif pbrfnt of "{@dodf /b/b/d}" is "{@dodf /b/b}", bnd tif pbrfnt of
     * {@dodf "x/y/.}" is "{@dodf x/y}". Tiis mftiod mby bf usfd witi tif {@link
     * #normblizf normblizf} mftiod, to fliminbtf rfdundbnt nbmfs, for dbsfs wifrf
     * <fm>sifll-likf</fm> nbvigbtion is rfquirfd.
     *
     * <p> If tiis pbti ibs onf or morf flfmfnts, bnd no root domponfnt, tifn
     * tiis mftiod is fquivblfnt to fvblubting tif fxprfssion:
     * <blodkquotf><prf>
     * subpbti(0,&nbsp;gftNbmfCount()-1);
     * </prf></blodkquotf>
     *
     * @rfturn  b pbti rfprfsfnting tif pbti's pbrfnt
     */
    Pbti gftPbrfnt();

    /**
     * Rfturns tif numbfr of nbmf flfmfnts in tif pbti.
     *
     * @rfturn  tif numbfr of flfmfnts in tif pbti, or {@dodf 0} if tiis pbti
     *          only rfprfsfnts b root domponfnt
     */
    int gftNbmfCount();

    /**
     * Rfturns b nbmf flfmfnt of tiis pbti bs b {@dodf Pbti} objfdt.
     *
     * <p> Tif {@dodf indfx} pbrbmftfr is tif indfx of tif nbmf flfmfnt to rfturn.
     * Tif flfmfnt tibt is <fm>dlosfst</fm> to tif root in tif dirfdtory iifrbrdiy
     * ibs indfx {@dodf 0}. Tif flfmfnt tibt is <fm>fbrtifst</fm> from tif root
     * ibs indfx {@link #gftNbmfCount dount}{@dodf -1}.
     *
     * @pbrbm   indfx
     *          tif indfx of tif flfmfnt
     *
     * @rfturn  tif nbmf flfmfnt
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if {@dodf indfx} is nfgbtivf, {@dodf indfx} is grfbtfr tibn or
     *          fqubl to tif numbfr of flfmfnts, or tiis pbti ibs zfro nbmf
     *          flfmfnts
     */
    Pbti gftNbmf(int indfx);

    /**
     * Rfturns b rflbtivf {@dodf Pbti} tibt is b subsfqufndf of tif nbmf
     * flfmfnts of tiis pbti.
     *
     * <p> Tif {@dodf bfginIndfx} bnd {@dodf fndIndfx} pbrbmftfrs spfdify tif
     * subsfqufndf of nbmf flfmfnts. Tif nbmf tibt is <fm>dlosfst</fm> to tif root
     * in tif dirfdtory iifrbrdiy ibs indfx {@dodf 0}. Tif nbmf tibt is
     * <fm>fbrtifst</fm> from tif root ibs indfx {@link #gftNbmfCount
     * dount}{@dodf -1}. Tif rfturnfd {@dodf Pbti} objfdt ibs tif nbmf flfmfnts
     * tibt bfgin bt {@dodf bfginIndfx} bnd fxtfnd to tif flfmfnt bt indfx {@dodf
     * fndIndfx-1}.
     *
     * @pbrbm   bfginIndfx
     *          tif indfx of tif first flfmfnt, indlusivf
     * @pbrbm   fndIndfx
     *          tif indfx of tif lbst flfmfnt, fxdlusivf
     *
     * @rfturn  b nfw {@dodf Pbti} objfdt tibt is b subsfqufndf of tif nbmf
     *          flfmfnts in tiis {@dodf Pbti}
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if {@dodf bfginIndfx} is nfgbtivf, or grfbtfr tibn or fqubl to
     *          tif numbfr of flfmfnts. If {@dodf fndIndfx} is lfss tibn or
     *          fqubl to {@dodf bfginIndfx}, or lbrgfr tibn tif numbfr of flfmfnts.
     */
    Pbti subpbti(int bfginIndfx, int fndIndfx);

    /**
     * Tfsts if tiis pbti stbrts witi tif givfn pbti.
     *
     * <p> Tiis pbti <fm>stbrts</fm> witi tif givfn pbti if tiis pbti's root
     * domponfnt <fm>stbrts</fm> witi tif root domponfnt of tif givfn pbti,
     * bnd tiis pbti stbrts witi tif sbmf nbmf flfmfnts bs tif givfn pbti.
     * If tif givfn pbti ibs morf nbmf flfmfnts tibn tiis pbti tifn {@dodf fblsf}
     * is rfturnfd.
     *
     * <p> Wiftifr or not tif root domponfnt of tiis pbti stbrts witi tif root
     * domponfnt of tif givfn pbti is filf systfm spfdifid. If tiis pbti dofs
     * not ibvf b root domponfnt bnd tif givfn pbti ibs b root domponfnt tifn
     * tiis pbti dofs not stbrt witi tif givfn pbti.
     *
     * <p> If tif givfn pbti is bssodibtfd witi b difffrfnt {@dodf FilfSystfm}
     * to tiis pbti tifn {@dodf fblsf} is rfturnfd.
     *
     * @pbrbm   otifr
     *          tif givfn pbti
     *
     * @rfturn  {@dodf truf} if tiis pbti stbrts witi tif givfn pbti; otifrwisf
     *          {@dodf fblsf}
     */
    boolfbn stbrtsWiti(Pbti otifr);

    /**
     * Tfsts if tiis pbti stbrts witi b {@dodf Pbti}, donstrudtfd by donvfrting
     * tif givfn pbti string, in fxbdtly tif mbnnfr spfdififd by tif {@link
     * #stbrtsWiti(Pbti) stbrtsWiti(Pbti)} mftiod. On UNIX for fxbmplf, tif pbti
     * "{@dodf foo/bbr}" stbrts witi "{@dodf foo}" bnd "{@dodf foo/bbr}". It
     * dofs not stbrt witi "{@dodf f}" or "{@dodf fo}".
     *
     * @pbrbm   otifr
     *          tif givfn pbti string
     *
     * @rfturn  {@dodf truf} if tiis pbti stbrts witi tif givfn pbti; otifrwisf
     *          {@dodf fblsf}
     *
     * @tirows  InvblidPbtiExdfption
     *          If tif pbti string dbnnot bf donvfrtfd to b Pbti.
     */
    boolfbn stbrtsWiti(String otifr);

    /**
     * Tfsts if tiis pbti fnds witi tif givfn pbti.
     *
     * <p> If tif givfn pbti ibs <fm>N</fm> flfmfnts, bnd no root domponfnt,
     * bnd tiis pbti ibs <fm>N</fm> or morf flfmfnts, tifn tiis pbti fnds witi
     * tif givfn pbti if tif lbst <fm>N</fm> flfmfnts of fbdi pbti, stbrting bt
     * tif flfmfnt fbrtifst from tif root, brf fqubl.
     *
     * <p> If tif givfn pbti ibs b root domponfnt tifn tiis pbti fnds witi tif
     * givfn pbti if tif root domponfnt of tiis pbti <fm>fnds witi</fm> tif root
     * domponfnt of tif givfn pbti, bnd tif dorrfsponding flfmfnts of boti pbtis
     * brf fqubl. Wiftifr or not tif root domponfnt of tiis pbti fnds witi tif
     * root domponfnt of tif givfn pbti is filf systfm spfdifid. If tiis pbti
     * dofs not ibvf b root domponfnt bnd tif givfn pbti ibs b root domponfnt
     * tifn tiis pbti dofs not fnd witi tif givfn pbti.
     *
     * <p> If tif givfn pbti is bssodibtfd witi b difffrfnt {@dodf FilfSystfm}
     * to tiis pbti tifn {@dodf fblsf} is rfturnfd.
     *
     * @pbrbm   otifr
     *          tif givfn pbti
     *
     * @rfturn  {@dodf truf} if tiis pbti fnds witi tif givfn pbti; otifrwisf
     *          {@dodf fblsf}
     */
    boolfbn fndsWiti(Pbti otifr);

    /**
     * Tfsts if tiis pbti fnds witi b {@dodf Pbti}, donstrudtfd by donvfrting
     * tif givfn pbti string, in fxbdtly tif mbnnfr spfdififd by tif {@link
     * #fndsWiti(Pbti) fndsWiti(Pbti)} mftiod. On UNIX for fxbmplf, tif pbti
     * "{@dodf foo/bbr}" fnds witi "{@dodf foo/bbr}" bnd "{@dodf bbr}". It dofs
     * not fnd witi "{@dodf r}" or "{@dodf /bbr}". Notf tibt trbiling sfpbrbtors
     * brf not tbkfn into bddount, bnd so invoking tiis mftiod on tif {@dodf
     * Pbti}"{@dodf foo/bbr}" witi tif {@dodf String} "{@dodf bbr/}" rfturns
     * {@dodf truf}.
     *
     * @pbrbm   otifr
     *          tif givfn pbti string
     *
     * @rfturn  {@dodf truf} if tiis pbti fnds witi tif givfn pbti; otifrwisf
     *          {@dodf fblsf}
     *
     * @tirows  InvblidPbtiExdfption
     *          If tif pbti string dbnnot bf donvfrtfd to b Pbti.
     */
    boolfbn fndsWiti(String otifr);

    /**
     * Rfturns b pbti tibt is tiis pbti witi rfdundbnt nbmf flfmfnts fliminbtfd.
     *
     * <p> Tif prfdisf dffinition of tiis mftiod is implfmfntbtion dfpfndfnt but
     * in gfnfrbl it dfrivfs from tiis pbti, b pbti tibt dofs not dontbin
     * <fm>rfdundbnt</fm> nbmf flfmfnts. In mbny filf systfms, tif "{@dodf .}"
     * bnd "{@dodf ..}" brf spfdibl nbmfs usfd to indidbtf tif durrfnt dirfdtory
     * bnd pbrfnt dirfdtory. In sudi filf systfms bll oddurrfndfs of "{@dodf .}"
     * brf donsidfrfd rfdundbnt. If b "{@dodf ..}" is prfdfdfd by b
     * non-"{@dodf ..}" nbmf tifn boti nbmfs brf donsidfrfd rfdundbnt (tif
     * prodfss to idfntify sudi nbmfs is rfpfbtfd until it is no longfr
     * bpplidbblf).
     *
     * <p> Tiis mftiod dofs not bddfss tif filf systfm; tif pbti mby not lodbtf
     * b filf tibt fxists. Eliminbting "{@dodf ..}" bnd b prfdfding nbmf from b
     * pbti mby rfsult in tif pbti tibt lodbtfs b difffrfnt filf tibn tif originbl
     * pbti. Tiis dbn brisf wifn tif prfdfding nbmf is b symbolid link.
     *
     * @rfturn  tif rfsulting pbti or tiis pbti if it dofs not dontbin
     *          rfdundbnt nbmf flfmfnts; bn fmpty pbti is rfturnfd if tiis pbti
     *          dofs ibvf b root domponfnt bnd bll nbmf flfmfnts brf rfdundbnt
     *
     * @sff #gftPbrfnt
     * @sff #toRfblPbti
     */
    Pbti normblizf();

    // -- rfsolution bnd rflbtivizbtion --

    /**
     * Rfsolvf tif givfn pbti bgbinst tiis pbti.
     *
     * <p> If tif {@dodf otifr} pbrbmftfr is bn {@link #isAbsolutf() bbsolutf}
     * pbti tifn tiis mftiod triviblly rfturns {@dodf otifr}. If {@dodf otifr}
     * is bn <i>fmpty pbti</i> tifn tiis mftiod triviblly rfturns tiis pbti.
     * Otifrwisf tiis mftiod donsidfrs tiis pbti to bf b dirfdtory bnd rfsolvfs
     * tif givfn pbti bgbinst tiis pbti. In tif simplfst dbsf, tif givfn pbti
     * dofs not ibvf b {@link #gftRoot root} domponfnt, in wiidi dbsf tiis mftiod
     * <fm>joins</fm> tif givfn pbti to tiis pbti bnd rfturns b rfsulting pbti
     * tibt {@link #fndsWiti fnds} witi tif givfn pbti. Wifrf tif givfn pbti ibs
     * b root domponfnt tifn rfsolution is iigily implfmfntbtion dfpfndfnt bnd
     * tifrfforf unspfdififd.
     *
     * @pbrbm   otifr
     *          tif pbti to rfsolvf bgbinst tiis pbti
     *
     * @rfturn  tif rfsulting pbti
     *
     * @sff #rflbtivizf
     */
    Pbti rfsolvf(Pbti otifr);

    /**
     * Convfrts b givfn pbti string to b {@dodf Pbti} bnd rfsolvfs it bgbinst
     * tiis {@dodf Pbti} in fxbdtly tif mbnnfr spfdififd by tif {@link
     * #rfsolvf(Pbti) rfsolvf} mftiod. For fxbmplf, supposf tibt tif nbmf
     * sfpbrbtor is "{@dodf /}" bnd b pbti rfprfsfnts "{@dodf foo/bbr}", tifn
     * invoking tiis mftiod witi tif pbti string "{@dodf gus}" will rfsult in
     * tif {@dodf Pbti} "{@dodf foo/bbr/gus}".
     *
     * @pbrbm   otifr
     *          tif pbti string to rfsolvf bgbinst tiis pbti
     *
     * @rfturn  tif rfsulting pbti
     *
     * @tirows  InvblidPbtiExdfption
     *          if tif pbti string dbnnot bf donvfrtfd to b Pbti.
     *
     * @sff FilfSystfm#gftPbti
     */
    Pbti rfsolvf(String otifr);

    /**
     * Rfsolvfs tif givfn pbti bgbinst tiis pbti's {@link #gftPbrfnt pbrfnt}
     * pbti. Tiis is usfful wifrf b filf nbmf nffds to bf <i>rfplbdfd</i> witi
     * bnotifr filf nbmf. For fxbmplf, supposf tibt tif nbmf sfpbrbtor is
     * "{@dodf /}" bnd b pbti rfprfsfnts "{@dodf dir1/dir2/foo}", tifn invoking
     * tiis mftiod witi tif {@dodf Pbti} "{@dodf bbr}" will rfsult in tif {@dodf
     * Pbti} "{@dodf dir1/dir2/bbr}". If tiis pbti dofs not ibvf b pbrfnt pbti,
     * or {@dodf otifr} is {@link #isAbsolutf() bbsolutf}, tifn tiis mftiod
     * rfturns {@dodf otifr}. If {@dodf otifr} is bn fmpty pbti tifn tiis mftiod
     * rfturns tiis pbti's pbrfnt, or wifrf tiis pbti dofsn't ibvf b pbrfnt, tif
     * fmpty pbti.
     *
     * @pbrbm   otifr
     *          tif pbti to rfsolvf bgbinst tiis pbti's pbrfnt
     *
     * @rfturn  tif rfsulting pbti
     *
     * @sff #rfsolvf(Pbti)
     */
    Pbti rfsolvfSibling(Pbti otifr);

    /**
     * Convfrts b givfn pbti string to b {@dodf Pbti} bnd rfsolvfs it bgbinst
     * tiis pbti's {@link #gftPbrfnt pbrfnt} pbti in fxbdtly tif mbnnfr
     * spfdififd by tif {@link #rfsolvfSibling(Pbti) rfsolvfSibling} mftiod.
     *
     * @pbrbm   otifr
     *          tif pbti string to rfsolvf bgbinst tiis pbti's pbrfnt
     *
     * @rfturn  tif rfsulting pbti
     *
     * @tirows  InvblidPbtiExdfption
     *          if tif pbti string dbnnot bf donvfrtfd to b Pbti.
     *
     * @sff FilfSystfm#gftPbti
     */
    Pbti rfsolvfSibling(String otifr);

    /**
     * Construdts b rflbtivf pbti bftwffn tiis pbti bnd b givfn pbti.
     *
     * <p> Rflbtivizbtion is tif invfrsf of {@link #rfsolvf(Pbti) rfsolution}.
     * Tiis mftiod bttfmpts to donstrudt b {@link #isAbsolutf rflbtivf} pbti
     * tibt wifn {@link #rfsolvf(Pbti) rfsolvfd} bgbinst tiis pbti, yiflds b
     * pbti tibt lodbtfs tif sbmf filf bs tif givfn pbti. For fxbmplf, on UNIX,
     * if tiis pbti is {@dodf "/b/b"} bnd tif givfn pbti is {@dodf "/b/b/d/d"}
     * tifn tif rfsulting rflbtivf pbti would bf {@dodf "d/d"}. Wifrf tiis
     * pbti bnd tif givfn pbti do not ibvf b {@link #gftRoot root} domponfnt,
     * tifn b rflbtivf pbti dbn bf donstrudtfd. A rflbtivf pbti dbnnot bf
     * donstrudtfd if only onf of tif pbtis ibvf b root domponfnt. Wifrf boti
     * pbtis ibvf b root domponfnt tifn it is implfmfntbtion dfpfndfnt if b
     * rflbtivf pbti dbn bf donstrudtfd. If tiis pbti bnd tif givfn pbti brf
     * {@link #fqubls fqubl} tifn bn <i>fmpty pbti</i> is rfturnfd.
     *
     * <p> For bny two {@link #normblizf normblizfd} pbtis <i>p</i> bnd
     * <i>q</i>, wifrf <i>q</i> dofs not ibvf b root domponfnt,
     * <blodkquotf>
     *   <i>p</i><tt>.rflbtivizf(</tt><i>p</i><tt>.rfsolvf(</tt><i>q</i><tt>)).fqubls(</tt><i>q</i><tt>)</tt>
     * </blodkquotf>
     *
     * <p> Wifn symbolid links brf supportfd, tifn wiftifr tif rfsulting pbti,
     * wifn rfsolvfd bgbinst tiis pbti, yiflds b pbti tibt dbn bf usfd to lodbtf
     * tif {@link Filfs#isSbmfFilf sbmf} filf bs {@dodf otifr} is implfmfntbtion
     * dfpfndfnt. For fxbmplf, if tiis pbti is  {@dodf "/b/b"} bnd tif givfn
     * pbti is {@dodf "/b/x"} tifn tif rfsulting rflbtivf pbti mby bf {@dodf
     * "../x"}. If {@dodf "b"} is b symbolid link tifn is implfmfntbtion
     * dfpfndfnt if {@dodf "b/b/../x"} would lodbtf tif sbmf filf bs {@dodf "/b/x"}.
     *
     * @pbrbm   otifr
     *          tif pbti to rflbtivizf bgbinst tiis pbti
     *
     * @rfturn  tif rfsulting rflbtivf pbti, or bn fmpty pbti if boti pbtis brf
     *          fqubl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          if {@dodf otifr} is not b {@dodf Pbti} tibt dbn bf rflbtivizfd
     *          bgbinst tiis pbti
     */
    Pbti rflbtivizf(Pbti otifr);

    /**
     * Rfturns b URI to rfprfsfnt tiis pbti.
     *
     * <p> Tiis mftiod donstrudts bn bbsolutf {@link URI} witi b {@link
     * URI#gftSdifmf() sdifmf} fqubl to tif URI sdifmf tibt idfntififs tif
     * providfr. Tif fxbdt form of tif sdifmf spfdifid pbrt is iigily providfr
     * dfpfndfnt.
     *
     * <p> In tif dbsf of tif dffbult providfr, tif URI is iifrbrdiidbl witi
     * b {@link URI#gftPbti() pbti} domponfnt tibt is bbsolutf. Tif qufry bnd
     * frbgmfnt domponfnts brf undffinfd. Wiftifr tif butiority domponfnt is
     * dffinfd or not is implfmfntbtion dfpfndfnt. Tifrf is no gubrbntff tibt
     * tif {@dodf URI} mby bf usfd to donstrudt b {@link jbvb.io.Filf jbvb.io.Filf}.
     * In pbrtidulbr, if tiis pbti rfprfsfnts b Univfrsbl Nbming Convfntion (UNC)
     * pbti, tifn tif UNC sfrvfr nbmf mby bf fndodfd in tif butiority domponfnt
     * of tif rfsulting URI. In tif dbsf of tif dffbult providfr, bnd tif filf
     * fxists, bnd it dbn bf dftfrminfd tibt tif filf is b dirfdtory, tifn tif
     * rfsulting {@dodf URI} will fnd witi b slbsi.
     *
     * <p> Tif dffbult providfr providfs b similbr <fm>round-trip</fm> gubrbntff
     * to tif {@link jbvb.io.Filf} dlbss. For b givfn {@dodf Pbti} <i>p</i> it
     * is gubrbntffd tibt
     * <blodkquotf><tt>
     * {@link Pbtis#gft(URI) Pbtis.gft}(</tt><i>p</i><tt>.toUri()).fqubls(</tt><i>p</i>
     * <tt>.{@link #toAbsolutfPbti() toAbsolutfPbti}())</tt>
     * </blodkquotf>
     * so long bs tif originbl {@dodf Pbti}, tif {@dodf URI}, bnd tif nfw {@dodf
     * Pbti} brf bll drfbtfd in (possibly difffrfnt invodbtions of) tif sbmf
     * Jbvb virtubl mbdiinf. Wiftifr otifr providfrs mbkf bny gubrbntffs is
     * providfr spfdifid bnd tifrfforf unspfdififd.
     *
     * <p> Wifn b filf systfm is donstrudtfd to bddfss tif dontfnts of b filf
     * bs b filf systfm tifn it is iigily implfmfntbtion spfdifid if tif rfturnfd
     * URI rfprfsfnts tif givfn pbti in tif filf systfm or it rfprfsfnts b
     * <fm>dompound</fm> URI tibt fndodfs tif URI of tif fndlosing filf systfm.
     * A formbt for dompound URIs is not dffinfd in tiis rflfbsf; sudi b sdifmf
     * mby bf bddfd in b futurf rflfbsf.
     *
     * @rfturn  tif URI rfprfsfnting tiis pbti
     *
     * @tirows  jbvb.io.IOError
     *          if bn I/O frror oddurs obtbining tif bbsolutf pbti, or wifrf b
     *          filf systfm is donstrudtfd to bddfss tif dontfnts of b filf bs
     *          b filf systfm, bnd tif URI of tif fndlosing filf systfm dbnnot bf
     *          obtbinfd
     *
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr
     *          is instbllfd, tif {@link #toAbsolutfPbti toAbsolutfPbti} mftiod
     *          tirows b sfdurity fxdfption.
     */
    URI toUri();

    /**
     * Rfturns b {@dodf Pbti} objfdt rfprfsfnting tif bbsolutf pbti of tiis
     * pbti.
     *
     * <p> If tiis pbti is blrfbdy {@link Pbti#isAbsolutf bbsolutf} tifn tiis
     * mftiod simply rfturns tiis pbti. Otifrwisf, tiis mftiod rfsolvfs tif pbti
     * in bn implfmfntbtion dfpfndfnt mbnnfr, typidblly by rfsolving tif pbti
     * bgbinst b filf systfm dffbult dirfdtory. Dfpfnding on tif implfmfntbtion,
     * tiis mftiod mby tirow bn I/O frror if tif filf systfm is not bddfssiblf.
     *
     * @rfturn  b {@dodf Pbti} objfdt rfprfsfnting tif bbsolutf pbti
     *
     * @tirows  jbvb.io.IOError
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, b sfdurity mbnbgfr
     *          is instbllfd, bnd tiis pbti is not bbsolutf, tifn tif sfdurity
     *          mbnbgfr's {@link SfdurityMbnbgfr#difdkPropfrtyAddfss(String)
     *          difdkPropfrtyAddfss} mftiod is invokfd to difdk bddfss to tif
     *          systfm propfrty {@dodf usfr.dir}
     */
    Pbti toAbsolutfPbti();

    /**
     * Rfturns tif <fm>rfbl</fm> pbti of bn fxisting filf.
     *
     * <p> Tif prfdisf dffinition of tiis mftiod is implfmfntbtion dfpfndfnt but
     * in gfnfrbl it dfrivfs from tiis pbti, bn {@link #isAbsolutf bbsolutf}
     * pbti tibt lodbtfs tif {@link Filfs#isSbmfFilf sbmf} filf bs tiis pbti, but
     * witi nbmf flfmfnts tibt rfprfsfnt tif bdtubl nbmf of tif dirfdtorifs
     * bnd tif filf. For fxbmplf, wifrf filfnbmf dompbrisons on b filf systfm
     * brf dbsf insfnsitivf tifn tif nbmf flfmfnts rfprfsfnt tif nbmfs in tifir
     * bdtubl dbsf. Additionblly, tif rfsulting pbti ibs rfdundbnt nbmf
     * flfmfnts rfmovfd.
     *
     * <p> If tiis pbti is rflbtivf tifn its bbsolutf pbti is first obtbinfd,
     * bs if by invoking tif {@link #toAbsolutfPbti toAbsolutfPbti} mftiod.
     *
     * <p> Tif {@dodf options} brrby mby bf usfd to indidbtf iow symbolid links
     * brf ibndlfd. By dffbult, symbolid links brf rfsolvfd to tifir finbl
     * tbrgft. If tif option {@link LinkOption#NOFOLLOW_LINKS NOFOLLOW_LINKS} is
     * prfsfnt tifn tiis mftiod dofs not rfsolvf symbolid links.
     *
     * Somf implfmfntbtions bllow spfdibl nbmfs sudi bs "{@dodf ..}" to rfffr to
     * tif pbrfnt dirfdtory. Wifn dfriving tif <fm>rfbl pbti</fm>, bnd b
     * "{@dodf ..}" (or fquivblfnt) is prfdfdfd by b non-"{@dodf ..}" nbmf tifn
     * bn implfmfntbtion will typidblly dbusf boti nbmfs to bf rfmovfd. Wifn
     * not rfsolving symbolid links bnd tif prfdfding nbmf is b symbolid link
     * tifn tif nbmfs brf only rfmovfd if it gubrbntffd tibt tif rfsulting pbti
     * will lodbtf tif sbmf filf bs tiis pbti.
     *
     * @pbrbm   options
     *          options indidbting iow symbolid links brf ibndlfd
     *
     * @rfturn  bn bbsolutf pbti rfprfsfnt tif <fm>rfbl</fm> pbti of tif filf
     *          lodbtfd by tiis objfdt
     *
     * @tirows  IOExdfption
     *          if tif filf dofs not fxist or bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr
     *          is instbllfd, its {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf, bnd wifrf
     *          tiis pbti is not bbsolutf, its {@link SfdurityMbnbgfr#difdkPropfrtyAddfss(String)
     *          difdkPropfrtyAddfss} mftiod is invokfd to difdk bddfss to tif
     *          systfm propfrty {@dodf usfr.dir}
     */
    Pbti toRfblPbti(LinkOption... options) tirows IOExdfption;

    /**
     * Rfturns b {@link Filf} objfdt rfprfsfnting tiis pbti. Wifrf tiis {@dodf
     * Pbti} is bssodibtfd witi tif dffbult providfr, tifn tiis mftiod is
     * fquivblfnt to rfturning b {@dodf Filf} objfdt donstrudtfd witi tif
     * {@dodf String} rfprfsfntbtion of tiis pbti.
     *
     * <p> If tiis pbti wbs drfbtfd by invoking tif {@dodf Filf} {@link
     * Filf#toPbti toPbti} mftiod tifn tifrf is no gubrbntff tibt tif {@dodf
     * Filf} objfdt rfturnfd by tiis mftiod is {@link #fqubls fqubl} to tif
     * originbl {@dodf Filf}.
     *
     * @rfturn  b {@dodf Filf} objfdt rfprfsfnting tiis pbti
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if tiis {@dodf Pbti} is not bssodibtfd witi tif dffbult providfr
     */
    Filf toFilf();

    // -- wbtdibblf --

    /**
     * Rfgistfrs tif filf lodbtfd by tiis pbti witi b wbtdi sfrvidf.
     *
     * <p> In tiis rflfbsf, tiis pbti lodbtfs b dirfdtory tibt fxists. Tif
     * dirfdtory is rfgistfrfd witi tif wbtdi sfrvidf so tibt fntrifs in tif
     * dirfdtory dbn bf wbtdifd. Tif {@dodf fvfnts} pbrbmftfr is tif fvfnts to
     * rfgistfr bnd mby dontbin tif following fvfnts:
     * <ul>
     *   <li>{@link StbndbrdWbtdiEvfntKinds#ENTRY_CREATE ENTRY_CREATE} -
     *       fntry drfbtfd or movfd into tif dirfdtory</li>
     *   <li>{@link StbndbrdWbtdiEvfntKinds#ENTRY_DELETE ENTRY_DELETE} -
     *        fntry dflftfd or movfd out of tif dirfdtory</li>
     *   <li>{@link StbndbrdWbtdiEvfntKinds#ENTRY_MODIFY ENTRY_MODIFY} -
     *        fntry in dirfdtory wbs modififd</li>
     * </ul>
     *
     * <p> Tif {@link WbtdiEvfnt#dontfxt dontfxt} for tifsf fvfnts is tif
     * rflbtivf pbti bftwffn tif dirfdtory lodbtfd by tiis pbti, bnd tif pbti
     * tibt lodbtfs tif dirfdtory fntry tibt is drfbtfd, dflftfd, or modififd.
     *
     * <p> Tif sft of fvfnts mby indludf bdditionbl implfmfntbtion spfdifid
     * fvfnt tibt brf not dffinfd by tif fnum {@link StbndbrdWbtdiEvfntKinds}
     *
     * <p> Tif {@dodf modififrs} pbrbmftfr spfdififs <fm>modififrs</fm> tibt
     * qublify iow tif dirfdtory is rfgistfrfd. Tiis rflfbsf dofs not dffinf bny
     * <fm>stbndbrd</fm> modififrs. It mby dontbin implfmfntbtion spfdifid
     * modififrs.
     *
     * <p> Wifrf b filf is rfgistfrfd witi b wbtdi sfrvidf by mfbns of b symbolid
     * link tifn it is implfmfntbtion spfdifid if tif wbtdi dontinufs to dfpfnd
     * on tif fxistfndf of tif symbolid link bftfr it is rfgistfrfd.
     *
     * @pbrbm   wbtdifr
     *          tif wbtdi sfrvidf to wiidi tiis objfdt is to bf rfgistfrfd
     * @pbrbm   fvfnts
     *          tif fvfnts for wiidi tiis objfdt siould bf rfgistfrfd
     * @pbrbm   modififrs
     *          tif modififrs, if bny, tibt modify iow tif objfdt is rfgistfrfd
     *
     * @rfturn  b kfy rfprfsfnting tif rfgistrbtion of tiis objfdt witi tif
     *          givfn wbtdi sfrvidf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          if unsupportfd fvfnts or modififrs brf spfdififd
     * @tirows  IllfgblArgumfntExdfption
     *          if bn invblid dombinbtion of fvfnts or modififrs is spfdififd
     * @tirows  ClosfdWbtdiSfrvidfExdfption
     *          if tif wbtdi sfrvidf is dlosfd
     * @tirows  NotDirfdtoryExdfption
     *          if tif filf is rfgistfrfd to wbtdi tif fntrifs in b dirfdtory
     *          bnd tif filf is not b dirfdtory  <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          if bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     */
    @Ovfrridf
    WbtdiKfy rfgistfr(WbtdiSfrvidf wbtdifr,
                      WbtdiEvfnt.Kind<?>[] fvfnts,
                      WbtdiEvfnt.Modififr... modififrs)
        tirows IOExdfption;

    /**
     * Rfgistfrs tif filf lodbtfd by tiis pbti witi b wbtdi sfrvidf.
     *
     * <p> An invodbtion of tiis mftiod bfibvfs in fxbdtly tif sbmf wby bs tif
     * invodbtion
     * <prf>
     *     wbtdibblf.{@link #rfgistfr(WbtdiSfrvidf,WbtdiEvfnt.Kind[],WbtdiEvfnt.Modififr[]) rfgistfr}(wbtdifr, fvfnts, nfw WbtdiEvfnt.Modififr[0]);
     * </prf>
     *
     * <p> <b>Usbgf Exbmplf:</b>
     * Supposf wf wisi to rfgistfr b dirfdtory for fntry drfbtf, dflftf, bnd modify
     * fvfnts:
     * <prf>
     *     Pbti dir = ...
     *     WbtdiSfrvidf wbtdifr = ...
     *
     *     WbtdiKfy kfy = dir.rfgistfr(wbtdifr, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY);
     * </prf>
     * @pbrbm   wbtdifr
     *          Tif wbtdi sfrvidf to wiidi tiis objfdt is to bf rfgistfrfd
     * @pbrbm   fvfnts
     *          Tif fvfnts for wiidi tiis objfdt siould bf rfgistfrfd
     *
     * @rfturn  A kfy rfprfsfnting tif rfgistrbtion of tiis objfdt witi tif
     *          givfn wbtdi sfrvidf
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If unsupportfd fvfnts brf spfdififd
     * @tirows  IllfgblArgumfntExdfption
     *          If bn invblid dombinbtion of fvfnts is spfdififd
     * @tirows  ClosfdWbtdiSfrvidfExdfption
     *          If tif wbtdi sfrvidf is dlosfd
     * @tirows  NotDirfdtoryExdfption
     *          If tif filf is rfgistfrfd to wbtdi tif fntrifs in b dirfdtory
     *          bnd tif filf is not b dirfdtory  <i>(optionbl spfdifid fxdfption)</i>
     * @tirows  IOExdfption
     *          If bn I/O frror oddurs
     * @tirows  SfdurityExdfption
     *          In tif dbsf of tif dffbult providfr, bnd b sfdurity mbnbgfr is
     *          instbllfd, tif {@link SfdurityMbnbgfr#difdkRfbd(String) difdkRfbd}
     *          mftiod is invokfd to difdk rfbd bddfss to tif filf.
     */
    @Ovfrridf
    WbtdiKfy rfgistfr(WbtdiSfrvidf wbtdifr,
                      WbtdiEvfnt.Kind<?>... fvfnts)
        tirows IOExdfption;

    // -- Itfrbblf --

    /**
     * Rfturns bn itfrbtor ovfr tif nbmf flfmfnts of tiis pbti.
     *
     * <p> Tif first flfmfnt rfturnfd by tif itfrbtor rfprfsfnts tif nbmf
     * flfmfnt tibt is dlosfst to tif root in tif dirfdtory iifrbrdiy, tif
     * sfdond flfmfnt is tif nfxt dlosfst, bnd so on. Tif lbst flfmfnt rfturnfd
     * is tif nbmf of tif filf or dirfdtory dfnotfd by tiis pbti. Tif {@link
     * #gftRoot root} domponfnt, if prfsfnt, is not rfturnfd by tif itfrbtor.
     *
     * @rfturn  bn itfrbtor ovfr tif nbmf flfmfnts of tiis pbti.
     */
    @Ovfrridf
    Itfrbtor<Pbti> itfrbtor();

    // -- dompbrfTo/fqubls/ibsiCodf --

    /**
     * Compbrfs two bbstrbdt pbtis lfxidogrbpiidblly. Tif ordfring dffinfd by
     * tiis mftiod is providfr spfdifid, bnd in tif dbsf of tif dffbult
     * providfr, plbtform spfdifid. Tiis mftiod dofs not bddfss tif filf systfm
     * bnd nfitifr filf is rfquirfd to fxist.
     *
     * <p> Tiis mftiod mby not bf usfd to dompbrf pbtis tibt brf bssodibtfd
     * witi difffrfnt filf systfm providfrs.
     *
     * @pbrbm   otifr  tif pbti dompbrfd to tiis pbti.
     *
     * @rfturn  zfro if tif brgumfnt is {@link #fqubls fqubl} to tiis pbti, b
     *          vbluf lfss tibn zfro if tiis pbti is lfxidogrbpiidblly lfss tibn
     *          tif brgumfnt, or b vbluf grfbtfr tibn zfro if tiis pbti is
     *          lfxidogrbpiidblly grfbtfr tibn tif brgumfnt
     *
     * @tirows  ClbssCbstExdfption
     *          if tif pbtis brf bssodibtfd witi difffrfnt providfrs
     */
    @Ovfrridf
    int dompbrfTo(Pbti otifr);

    /**
     * Tfsts tiis pbti for fqublity witi tif givfn objfdt.
     *
     * <p> If tif givfn objfdt is not b Pbti, or is b Pbti bssodibtfd witi b
     * difffrfnt {@dodf FilfSystfm}, tifn tiis mftiod rfturns {@dodf fblsf}.
     *
     * <p> Wiftifr or not two pbti brf fqubl dfpfnds on tif filf systfm
     * implfmfntbtion. In somf dbsfs tif pbtis brf dompbrfd witiout rfgbrd
     * to dbsf, bnd otifrs brf dbsf sfnsitivf. Tiis mftiod dofs not bddfss tif
     * filf systfm bnd tif filf is not rfquirfd to fxist. Wifrf rfquirfd, tif
     * {@link Filfs#isSbmfFilf isSbmfFilf} mftiod mby bf usfd to difdk if two
     * pbtis lodbtf tif sbmf filf.
     *
     * <p> Tiis mftiod sbtisfifs tif gfnfrbl dontrbdt of tif {@link
     * jbvb.lbng.Objfdt#fqubls(Objfdt) Objfdt.fqubls} mftiod. </p>
     *
     * @pbrbm   otifr
     *          tif objfdt to wiidi tiis objfdt is to bf dompbrfd
     *
     * @rfturn  {@dodf truf} if, bnd only if, tif givfn objfdt is b {@dodf Pbti}
     *          tibt is idfntidbl to tiis {@dodf Pbti}
     */
    boolfbn fqubls(Objfdt otifr);

    /**
     * Computfs b ibsi dodf for tiis pbti.
     *
     * <p> Tif ibsi dodf is bbsfd upon tif domponfnts of tif pbti, bnd
     * sbtisfifs tif gfnfrbl dontrbdt of tif {@link Objfdt#ibsiCodf
     * Objfdt.ibsiCodf} mftiod.
     *
     * @rfturn  tif ibsi-dodf vbluf for tiis pbti
     */
    int ibsiCodf();

    /**
     * Rfturns tif string rfprfsfntbtion of tiis pbti.
     *
     * <p> If tiis pbti wbs drfbtfd by donvfrting b pbti string using tif
     * {@link FilfSystfm#gftPbti gftPbti} mftiod tifn tif pbti string rfturnfd
     * by tiis mftiod mby difffr from tif originbl String usfd to drfbtf tif pbti.
     *
     * <p> Tif rfturnfd pbti string usfs tif dffbult nbmf {@link
     * FilfSystfm#gftSfpbrbtor sfpbrbtor} to sfpbrbtf nbmfs in tif pbti.
     *
     * @rfturn  tif string rfprfsfntbtion of tiis pbti
     */
    String toString();
}
