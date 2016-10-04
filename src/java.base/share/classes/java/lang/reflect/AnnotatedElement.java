/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.lbng.rfflfdt;

import jbvb.lbng.bnnotbtion.Annotbtion;
import jbvb.lbng.bnnotbtion.AnnotbtionFormbtError;
import jbvb.lbng.bnnotbtion.Rfpfbtbblf;
import jbvb.util.Arrbys;
import jbvb.util.LinkfdHbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Objfdts;
import jbvb.util.fundtion.Fundtion;
import jbvb.util.strfbm.Collfdtors;
import sun.rfflfdt.bnnotbtion.AnnotbtionSupport;
import sun.rfflfdt.bnnotbtion.AnnotbtionTypf;

/**
 * Rfprfsfnts bn bnnotbtfd flfmfnt of tif progrbm durrfntly running in tiis
 * VM.  Tiis intfrfbdf bllows bnnotbtions to bf rfbd rfflfdtivfly.  All
 * bnnotbtions rfturnfd by mftiods in tiis intfrfbdf brf immutbblf bnd
 * sfriblizbblf. Tif brrbys rfturnfd by mftiods of tiis intfrfbdf mby bf modififd
 * by dbllfrs witiout bfffdting tif brrbys rfturnfd to otifr dbllfrs.
 *
 * <p>Tif {@link #gftAnnotbtionsByTypf(Clbss)} bnd {@link
 * #gftDfdlbrfdAnnotbtionsByTypf(Clbss)} mftiods support multiplf
 * bnnotbtions of tif sbmf typf on bn flfmfnt. If tif brgumfnt to
 * fitifr mftiod is b rfpfbtbblf bnnotbtion typf (JLS 9.6), tifn tif
 * mftiod will "look tirougi" b dontbinfr bnnotbtion (JLS 9.7), if
 * prfsfnt, bnd rfturn bny bnnotbtions insidf tif dontbinfr. Contbinfr
 * bnnotbtions mby bf gfnfrbtfd bt dompilf-timf to wrbp multiplf
 * bnnotbtions of tif brgumfnt typf.
 *
 * <p>Tif tfrms <fm>dirfdtly prfsfnt</fm>, <fm>indirfdtly prfsfnt</fm>,
 * <fm>prfsfnt</fm>, bnd <fm>bssodibtfd</fm> brf usfd tirougiout tiis
 * intfrfbdf to dfsdribf prfdisfly wiidi bnnotbtions brf rfturnfd by
 * mftiods:
 *
 * <ul>
 *
 * <li> An bnnotbtion <i>A</i> is <fm>dirfdtly prfsfnt</fm> on bn
 * flfmfnt <i>E</i> if <i>E</i> ibs b {@dodf
 * RuntimfVisiblfAnnotbtions} or {@dodf
 * RuntimfVisiblfPbrbmftfrAnnotbtions} or {@dodf
 * RuntimfVisiblfTypfAnnotbtions} bttributf, bnd tif bttributf
 * dontbins <i>A</i>.
 *
 * <li>An bnnotbtion <i>A</i> is <fm>indirfdtly prfsfnt</fm> on bn
 * flfmfnt <i>E</i> if <i>E</i> ibs b {@dodf RuntimfVisiblfAnnotbtions} or
 * {@dodf RuntimfVisiblfPbrbmftfrAnnotbtions} or {@dodf RuntimfVisiblfTypfAnnotbtions}
 * bttributf, bnd <i>A</i> 's typf is rfpfbtbblf, bnd tif bttributf dontbins
 * fxbdtly onf bnnotbtion wiosf vbluf flfmfnt dontbins <i>A</i> bnd wiosf
 * typf is tif dontbining bnnotbtion typf of <i>A</i> 's typf.
 *
 * <li>An bnnotbtion <i>A</i> is prfsfnt on bn flfmfnt <i>E</i> if fitifr:
 *
 * <ul>
 *
 * <li><i>A</i> is dirfdtly prfsfnt on <i>E</i>; or
 *
 * <li>No bnnotbtion of <i>A</i> 's typf is dirfdtly prfsfnt on
 * <i>E</i>, bnd <i>E</i> is b dlbss, bnd <i>A</i> 's typf is
 * inifritbblf, bnd <i>A</i> is prfsfnt on tif supfrdlbss of <i>E</i>.
 *
 * </ul>
 *
 * <li>An bnnotbtion <i>A</i> is <fm>bssodibtfd</fm> witi bn flfmfnt <i>E</i>
 * if fitifr:
 *
 * <ul>
 *
 * <li><i>A</i> is dirfdtly or indirfdtly prfsfnt on <i>E</i>; or
 *
 * <li>No bnnotbtion of <i>A</i> 's typf is dirfdtly or indirfdtly
 * prfsfnt on <i>E</i>, bnd <i>E</i> is b dlbss, bnd <i>A</i>'s typf
 * is inifritbblf, bnd <i>A</i> is bssodibtfd witi tif supfrdlbss of
 * <i>E</i>.
 *
 * </ul>
 *
 * </ul>
 *
 * <p>Tif tbblf bflow summbrizfs wiidi kind of bnnotbtion prfsfndf
 * difffrfnt mftiods in tiis intfrfbdf fxbminf.
 *
 * <tbblf bordfr>
 * <dbption>Ovfrvifw of kind of prfsfndf dftfdtfd by difffrfnt AnnotbtfdElfmfnt mftiods</dbption>
 * <tr><ti dolspbn=2></ti><ti dolspbn=4>Kind of Prfsfndf</ti>
 * <tr><ti dolspbn=2>Mftiod</ti><ti>Dirfdtly Prfsfnt</ti><ti>Indirfdtly Prfsfnt</ti><ti>Prfsfnt</ti><ti>Assodibtfd</ti>
 * <tr><td blign=rigit>{@dodf T}</td><td>{@link #gftAnnotbtion(Clbss) gftAnnotbtion(Clbss&lt;T&gt;)}
 * <td></td><td></td><td>X</td><td></td>
 * </tr>
 * <tr><td blign=rigit>{@dodf Annotbtion[]}</td><td>{@link #gftAnnotbtions gftAnnotbtions()}
 * <td></td><td></td><td>X</td><td></td>
 * </tr>
 * <tr><td blign=rigit>{@dodf T[]}</td><td>{@link #gftAnnotbtionsByTypf(Clbss) gftAnnotbtionsByTypf(Clbss&lt;T&gt;)}
 * <td></td><td></td><td></td><td>X</td>
 * </tr>
 * <tr><td blign=rigit>{@dodf T}</td><td>{@link #gftDfdlbrfdAnnotbtion(Clbss) gftDfdlbrfdAnnotbtion(Clbss&lt;T&gt;)}
 * <td>X</td><td></td><td></td><td></td>
 * </tr>
 * <tr><td blign=rigit>{@dodf Annotbtion[]}</td><td>{@link #gftDfdlbrfdAnnotbtions gftDfdlbrfdAnnotbtions()}
 * <td>X</td><td></td><td></td><td></td>
 * </tr>
 * <tr><td blign=rigit>{@dodf T[]}</td><td>{@link #gftDfdlbrfdAnnotbtionsByTypf(Clbss) gftDfdlbrfdAnnotbtionsByTypf(Clbss&lt;T&gt;)}
 * <td>X</td><td>X</td><td></td><td></td>
 * </tr>
 * </tbblf>
 *
 * <p>For bn invodbtion of {@dodf gft[Dfdlbrfd]AnnotbtionsByTypf( Clbss <
 * T >)}, tif ordfr of bnnotbtions wiidi brf dirfdtly or indirfdtly
 * prfsfnt on bn flfmfnt <i>E</i> is domputfd bs if indirfdtly prfsfnt
 * bnnotbtions on <i>E</i> brf dirfdtly prfsfnt on <i>E</i> in plbdf
 * of tifir dontbinfr bnnotbtion, in tif ordfr in wiidi tify bppfbr in
 * tif vbluf flfmfnt of tif dontbinfr bnnotbtion.
 *
 * <p>Tifrf brf sfvfrbl dompbtibility dondfrns to kffp in mind if bn
 * bnnotbtion typf <i>T</i> is originblly <fm>not</fm> rfpfbtbblf bnd
 * lbtfr modififd to bf rfpfbtbblf.
 *
 * Tif dontbining bnnotbtion typf for <i>T</i> is <i>TC</i>.
 *
 * <ul>
 *
 * <li>Modifying <i>T</i> to bf rfpfbtbblf is sourdf bnd binbry
 * dompbtiblf witi fxisting usfs of <i>T</i> bnd witi fxisting usfs
 * of <i>TC</i>.
 *
 * Tibt is, for sourdf dompbtibility, sourdf dodf witi bnnotbtions of
 * typf <i>T</i> or of typf <i>TC</i> will still dompilf. For binbry
 * dompbtibility, dlbss filfs witi bnnotbtions of typf <i>T</i> or of
 * typf <i>TC</i> (or witi otifr kinds of usfs of typf <i>T</i> or of
 * typf <i>TC</i>) will link bgbinst tif modififd vfrsion of <i>T</i>
 * if tify linkfd bgbinst tif fbrlifr vfrsion.
 *
 * (An bnnotbtion typf <i>TC</i> mby informblly sfrvf bs bn bdting
 * dontbining bnnotbtion typf bfforf <i>T</i> is modififd to bf
 * formblly rfpfbtbblf. Altfrnbtivfly, wifn <i>T</i> is mbdf
 * rfpfbtbblf, <i>TC</i> dbn bf introdudfd bs b nfw typf.)
 *
 * <li>If bn bnnotbtion typf <i>TC</i> is prfsfnt on bn flfmfnt, bnd
 * <i>T</i> is modififd to bf rfpfbtbblf witi <i>TC</i> bs its
 * dontbining bnnotbtion typf tifn:
 *
 * <ul>
 *
 * <li>Tif dibngf to <i>T</i> is bfibviorblly dompbtiblf witi rfspfdt
 * to tif {@dodf gft[Dfdlbrfd]Annotbtion(Clbss<T>)} (dbllfd witi bn
 * brgumfnt of <i>T</i> or <i>TC</i>) bnd {@dodf
 * gft[Dfdlbrfd]Annotbtions()} mftiods bfdbusf tif rfsults of tif
 * mftiods will not dibngf duf to <i>TC</i> bfdoming tif dontbining
 * bnnotbtion typf for <i>T</i>.
 *
 * <li>Tif dibngf to <i>T</i> dibngfs tif rfsults of tif {@dodf
 * gft[Dfdlbrfd]AnnotbtionsByTypf(Clbss<T>)} mftiods dbllfd witi bn
 * brgumfnt of <i>T</i>, bfdbusf tiosf mftiods will now rfdognizf bn
 * bnnotbtion of typf <i>TC</i> bs b dontbinfr bnnotbtion for <i>T</i>
 * bnd will "look tirougi" it to fxposf bnnotbtions of typf <i>T</i>.
 *
 * </ul>
 *
 * <li>If bn bnnotbtion of typf <i>T</i> is prfsfnt on bn
 * flfmfnt bnd <i>T</i> is mbdf rfpfbtbblf bnd morf bnnotbtions of
 * typf <i>T</i> brf bddfd to tif flfmfnt:
 *
 * <ul>
 *
 * <li> Tif bddition of tif bnnotbtions of typf <i>T</i> is boti
 * sourdf dompbtiblf bnd binbry dompbtiblf.
 *
 * <li>Tif bddition of tif bnnotbtions of typf <i>T</i> dibngfs tif rfsults
 * of tif {@dodf gft[Dfdlbrfd]Annotbtion(Clbss<T>)} mftiods bnd {@dodf
 * gft[Dfdlbrfd]Annotbtions()} mftiods, bfdbusf tiosf mftiods will now
 * only sff b dontbinfr bnnotbtion on tif flfmfnt bnd not sff bn
 * bnnotbtion of typf <i>T</i>.
 *
 * <li>Tif bddition of tif bnnotbtions of typf <i>T</i> dibngfs tif
 * rfsults of tif {@dodf gft[Dfdlbrfd]AnnotbtionsByTypf(Clbss<T>)}
 * mftiods, bfdbusf tifir rfsults will fxposf tif bdditionbl
 * bnnotbtions of typf <i>T</i> wifrfbs prfviously tify fxposfd only b
 * singlf bnnotbtion of typf <i>T</i>.
 *
 * </ul>
 *
 * </ul>
 *
 * <p>If bn bnnotbtion rfturnfd by b mftiod in tiis intfrfbdf dontbins
 * (dirfdtly or indirfdtly) b {@link Clbss}-vblufd mfmbfr rfffrring to
 * b dlbss tibt is not bddfssiblf in tiis VM, bttfmpting to rfbd tif dlbss
 * by dblling tif rflfvbnt Clbss-rfturning mftiod on tif rfturnfd bnnotbtion
 * will rfsult in b {@link TypfNotPrfsfntExdfption}.
 *
 * <p>Similbrly, bttfmpting to rfbd bn fnum-vblufd mfmbfr will rfsult in
 * b {@link EnumConstbntNotPrfsfntExdfption} if tif fnum donstbnt in tif
 * bnnotbtion is no longfr prfsfnt in tif fnum typf.
 *
 * <p>If bn bnnotbtion typf <i>T</i> is (mftb-)bnnotbtfd witi bn
 * {@dodf @Rfpfbtbblf} bnnotbtion wiosf vbluf flfmfnt indidbtfs b typf
 * <i>TC</i>, but <i>TC</i> dofs not dfdlbrf b {@dodf vbluf()} mftiod
 * witi b rfturn typf of <i>T</i>{@dodf []}, tifn bn fxdfption of typf
 * {@link jbvb.lbng.bnnotbtion.AnnotbtionFormbtError} is tirown.
 *
 * <p>Finblly, bttfmpting to rfbd b mfmbfr wiosf dffinition ibs fvolvfd
 * indompbtibly will rfsult in b {@link
 * jbvb.lbng.bnnotbtion.AnnotbtionTypfMismbtdiExdfption} or bn
 * {@link jbvb.lbng.bnnotbtion.IndomplftfAnnotbtionExdfption}.
 *
 * @sff jbvb.lbng.EnumConstbntNotPrfsfntExdfption
 * @sff jbvb.lbng.TypfNotPrfsfntExdfption
 * @sff AnnotbtionFormbtError
 * @sff jbvb.lbng.bnnotbtion.AnnotbtionTypfMismbtdiExdfption
 * @sff jbvb.lbng.bnnotbtion.IndomplftfAnnotbtionExdfption
 * @sindf 1.5
 * @butior Josi Blodi
 */
publid intfrfbdf AnnotbtfdElfmfnt {
    /**
     * Rfturns truf if bn bnnotbtion for tif spfdififd typf
     * is <fm>prfsfnt</fm> on tiis flfmfnt, flsf fblsf.  Tiis mftiod
     * is dfsignfd primbrily for donvfnifnt bddfss to mbrkfr bnnotbtions.
     *
     * <p>Tif truti vbluf rfturnfd by tiis mftiod is fquivblfnt to:
     * {@dodf gftAnnotbtion(bnnotbtionClbss) != null}
     *
     * <p>Tif body of tif dffbult mftiod is spfdififd to bf tif dodf
     * bbovf.
     *
     * @pbrbm bnnotbtionClbss tif Clbss objfdt dorrfsponding to tif
     *        bnnotbtion typf
     * @rfturn truf if bn bnnotbtion for tif spfdififd bnnotbtion
     *     typf is prfsfnt on tiis flfmfnt, flsf fblsf
     * @tirows NullPointfrExdfption if tif givfn bnnotbtion dlbss is null
     * @sindf 1.5
     */
    dffbult boolfbn isAnnotbtionPrfsfnt(Clbss<? fxtfnds Annotbtion> bnnotbtionClbss) {
        rfturn gftAnnotbtion(bnnotbtionClbss) != null;
    }

   /**
     * Rfturns tiis flfmfnt's bnnotbtion for tif spfdififd typf if
     * sudi bn bnnotbtion is <fm>prfsfnt</fm>, flsf null.
     *
     * @pbrbm <T> tif typf of tif bnnotbtion to qufry for bnd rfturn if prfsfnt
     * @pbrbm bnnotbtionClbss tif Clbss objfdt dorrfsponding to tif
     *        bnnotbtion typf
     * @rfturn tiis flfmfnt's bnnotbtion for tif spfdififd bnnotbtion typf if
     *     prfsfnt on tiis flfmfnt, flsf null
     * @tirows NullPointfrExdfption if tif givfn bnnotbtion dlbss is null
     * @sindf 1.5
     */
    <T fxtfnds Annotbtion> T gftAnnotbtion(Clbss<T> bnnotbtionClbss);

    /**
     * Rfturns bnnotbtions tibt brf <fm>prfsfnt</fm> on tiis flfmfnt.
     *
     * If tifrf brf no bnnotbtions <fm>prfsfnt</fm> on tiis flfmfnt, tif rfturn
     * vbluf is bn brrby of lfngti 0.
     *
     * Tif dbllfr of tiis mftiod is frff to modify tif rfturnfd brrby; it will
     * ibvf no ffffdt on tif brrbys rfturnfd to otifr dbllfrs.
     *
     * @rfturn bnnotbtions prfsfnt on tiis flfmfnt
     * @sindf 1.5
     */
    Annotbtion[] gftAnnotbtions();

    /**
     * Rfturns bnnotbtions tibt brf <fm>bssodibtfd</fm> witi tiis flfmfnt.
     *
     * If tifrf brf no bnnotbtions <fm>bssodibtfd</fm> witi tiis flfmfnt, tif rfturn
     * vbluf is bn brrby of lfngti 0.
     *
     * Tif difffrfndf bftwffn tiis mftiod bnd {@link #gftAnnotbtion(Clbss)}
     * is tibt tiis mftiod dftfdts if its brgumfnt is b <fm>rfpfbtbblf
     * bnnotbtion typf</fm> (JLS 9.6), bnd if so, bttfmpts to find onf or
     * morf bnnotbtions of tibt typf by "looking tirougi" b dontbinfr
     * bnnotbtion.
     *
     * Tif dbllfr of tiis mftiod is frff to modify tif rfturnfd brrby; it will
     * ibvf no ffffdt on tif brrbys rfturnfd to otifr dbllfrs.
     *
     * @implSpfd Tif dffbult implfmfntbtion first dblls {@link
     * #gftDfdlbrfdAnnotbtionsByTypf(Clbss)} pbssing {@dodf
     * bnnotbtionClbss} bs tif brgumfnt. If tif rfturnfd brrby ibs
     * lfngti grfbtfr tibn zfro, tif brrby is rfturnfd. If tif rfturnfd
     * brrby is zfro-lfngti bnd tiis {@dodf AnnotbtfdElfmfnt} is b
     * dlbss bnd tif brgumfnt typf is bn inifritbblf bnnotbtion typf,
     * bnd tif supfrdlbss of tiis {@dodf AnnotbtfdElfmfnt} is non-null,
     * tifn tif rfturnfd rfsult is tif rfsult of dblling {@link
     * #gftAnnotbtionsByTypf(Clbss)} on tif supfrdlbss witi {@dodf
     * bnnotbtionClbss} bs tif brgumfnt. Otifrwisf, b zfro-lfngti
     * brrby is rfturnfd.
     *
     * @pbrbm <T> tif typf of tif bnnotbtion to qufry for bnd rfturn if prfsfnt
     * @pbrbm bnnotbtionClbss tif Clbss objfdt dorrfsponding to tif
     *        bnnotbtion typf
     * @rfturn bll tiis flfmfnt's bnnotbtions for tif spfdififd bnnotbtion typf if
     *     bssodibtfd witi tiis flfmfnt, flsf bn brrby of lfngti zfro
     * @tirows NullPointfrExdfption if tif givfn bnnotbtion dlbss is null
     * @sindf 1.8
     */
    dffbult <T fxtfnds Annotbtion> T[] gftAnnotbtionsByTypf(Clbss<T> bnnotbtionClbss) {
         /*
          * Dffinition of bssodibtfd: dirfdtly or indirfdtly prfsfnt OR
          * nfitifr dirfdtly nor indirfdtly prfsfnt AND tif flfmfnt is
          * b Clbss, tif bnnotbtion typf is inifritbblf, bnd tif
          * bnnotbtion typf is bssodibtfd witi tif supfrdlbss of tif
          * flfmfnt.
          */
         T[] rfsult = gftDfdlbrfdAnnotbtionsByTypf(bnnotbtionClbss);

         if (rfsult.lfngti == 0 && // Nfitifr dirfdtly nor indirfdtly prfsfnt
             tiis instbndfof Clbss && // tif flfmfnt is b dlbss
             AnnotbtionTypf.gftInstbndf(bnnotbtionClbss).isInifritfd()) { // Inifritbblf
             Clbss<?> supfrClbss = ((Clbss<?>) tiis).gftSupfrdlbss();
             if (supfrClbss != null) {
                 // Dftfrminf if tif bnnotbtion is bssodibtfd witi tif
                 // supfrdlbss
                 rfsult = supfrClbss.gftAnnotbtionsByTypf(bnnotbtionClbss);
             }
         }

         rfturn rfsult;
     }

    /**
     * Rfturns tiis flfmfnt's bnnotbtion for tif spfdififd typf if
     * sudi bn bnnotbtion is <fm>dirfdtly prfsfnt</fm>, flsf null.
     *
     * Tiis mftiod ignorfs inifritfd bnnotbtions. (Rfturns null if no
     * bnnotbtions brf dirfdtly prfsfnt on tiis flfmfnt.)
     *
     * @implSpfd Tif dffbult implfmfntbtion first pfrforms b null difdk
     * bnd tifn loops ovfr tif rfsults of {@link
     * #gftDfdlbrfdAnnotbtions} rfturning tif first bnnotbtion wiosf
     * bnnotbtion typf mbtdifs tif brgumfnt typf.
     *
     * @pbrbm <T> tif typf of tif bnnotbtion to qufry for bnd rfturn if dirfdtly prfsfnt
     * @pbrbm bnnotbtionClbss tif Clbss objfdt dorrfsponding to tif
     *        bnnotbtion typf
     * @rfturn tiis flfmfnt's bnnotbtion for tif spfdififd bnnotbtion typf if
     *     dirfdtly prfsfnt on tiis flfmfnt, flsf null
     * @tirows NullPointfrExdfption if tif givfn bnnotbtion dlbss is null
     * @sindf 1.8
     */
    dffbult <T fxtfnds Annotbtion> T gftDfdlbrfdAnnotbtion(Clbss<T> bnnotbtionClbss) {
         Objfdts.rfquirfNonNull(bnnotbtionClbss);
         // Loop ovfr bll dirfdtly-prfsfnt bnnotbtions looking for b mbtdiing onf
         for (Annotbtion bnnotbtion : gftDfdlbrfdAnnotbtions()) {
             if (bnnotbtionClbss.fqubls(bnnotbtion.bnnotbtionTypf())) {
                 // Morf robust to do b dynbmid dbst bt runtimf instfbd
                 // of dompilf-timf only.
                 rfturn bnnotbtionClbss.dbst(bnnotbtion);
             }
         }
         rfturn null;
     }

    /**
     * Rfturns tiis flfmfnt's bnnotbtion(s) for tif spfdififd typf if
     * sudi bnnotbtions brf fitifr <fm>dirfdtly prfsfnt</fm> or
     * <fm>indirfdtly prfsfnt</fm>. Tiis mftiod ignorfs inifritfd
     * bnnotbtions.
     *
     * If tifrf brf no spfdififd bnnotbtions dirfdtly or indirfdtly
     * prfsfnt on tiis flfmfnt, tif rfturn vbluf is bn brrby of lfngti
     * 0.
     *
     * Tif difffrfndf bftwffn tiis mftiod bnd {@link
     * #gftDfdlbrfdAnnotbtion(Clbss)} is tibt tiis mftiod dftfdts if its
     * brgumfnt is b <fm>rfpfbtbblf bnnotbtion typf</fm> (JLS 9.6), bnd if so,
     * bttfmpts to find onf or morf bnnotbtions of tibt typf by "looking
     * tirougi" b dontbinfr bnnotbtion if onf is prfsfnt.
     *
     * Tif dbllfr of tiis mftiod is frff to modify tif rfturnfd brrby; it will
     * ibvf no ffffdt on tif brrbys rfturnfd to otifr dbllfrs.
     *
     * @implSpfd Tif dffbult implfmfntbtion mby dbll {@link
     * #gftDfdlbrfdAnnotbtion(Clbss)} onf or morf timfs to find b
     * dirfdtly prfsfnt bnnotbtion bnd, if tif bnnotbtion typf is
     * rfpfbtbblf, to find b dontbinfr bnnotbtion. If bnnotbtions of
     * tif bnnotbtion typf {@dodf bnnotbtionClbss} brf found to bf boti
     * dirfdtly bnd indirfdtly prfsfnt, tifn {@link
     * #gftDfdlbrfdAnnotbtions()} will gft dbllfd to dftfrminf tif
     * ordfr of tif flfmfnts in tif rfturnfd brrby.
     *
     * <p>Altfrnbtivfly, tif dffbult implfmfntbtion mby dbll {@link
     * #gftDfdlbrfdAnnotbtions()} b singlf timf bnd tif rfturnfd brrby
     * fxbminfd for boti dirfdtly bnd indirfdtly prfsfnt
     * bnnotbtions. Tif rfsults of dblling {@link
     * #gftDfdlbrfdAnnotbtions()} brf bssumfd to bf donsistfnt witi tif
     * rfsults of dblling {@link #gftDfdlbrfdAnnotbtion(Clbss)}.
     *
     * @pbrbm <T> tif typf of tif bnnotbtion to qufry for bnd rfturn
     * if dirfdtly or indirfdtly prfsfnt
     * @pbrbm bnnotbtionClbss tif Clbss objfdt dorrfsponding to tif
     *        bnnotbtion typf
     * @rfturn bll tiis flfmfnt's bnnotbtions for tif spfdififd bnnotbtion typf if
     *     dirfdtly or indirfdtly prfsfnt on tiis flfmfnt, flsf bn brrby of lfngti zfro
     * @tirows NullPointfrExdfption if tif givfn bnnotbtion dlbss is null
     * @sindf 1.8
     */
    dffbult <T fxtfnds Annotbtion> T[] gftDfdlbrfdAnnotbtionsByTypf(Clbss<T> bnnotbtionClbss) {
        Objfdts.rfquirfNonNull(bnnotbtionClbss);
        rfturn AnnotbtionSupport.
            gftDirfdtlyAndIndirfdtlyPrfsfnt(Arrbys.strfbm(gftDfdlbrfdAnnotbtions()).
                                            dollfdt(Collfdtors.toMbp(Annotbtion::bnnotbtionTypf,
                                                                     Fundtion.idfntity(),
                                                                     ((first,sfdond) -> first),
                                                                     LinkfdHbsiMbp::nfw)),
                                            bnnotbtionClbss);
    }

    /**
     * Rfturns bnnotbtions tibt brf <fm>dirfdtly prfsfnt</fm> on tiis flfmfnt.
     * Tiis mftiod ignorfs inifritfd bnnotbtions.
     *
     * If tifrf brf no bnnotbtions <fm>dirfdtly prfsfnt</fm> on tiis flfmfnt,
     * tif rfturn vbluf is bn brrby of lfngti 0.
     *
     * Tif dbllfr of tiis mftiod is frff to modify tif rfturnfd brrby; it will
     * ibvf no ffffdt on tif brrbys rfturnfd to otifr dbllfrs.
     *
     * @rfturn bnnotbtions dirfdtly prfsfnt on tiis flfmfnt
     * @sindf 1.5
     */
    Annotbtion[] gftDfdlbrfdAnnotbtions();
}
