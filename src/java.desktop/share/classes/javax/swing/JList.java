/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;

import jbvb.util.Vfdtor;
import jbvb.util.Lodblf;
import jbvb.util.ArrbyList;
import jbvb.util.Collfdtions;
import jbvb.util.List;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.Trbnsifnt;

import jbvbx.swing.fvfnt.*;
import jbvbx.bddfssibility.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.Position;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;

import sun.swing.SwingUtilitifs2;
import sun.swing.SwingUtilitifs2.Sfdtion;
import stbtid sun.swing.SwingUtilitifs2.Sfdtion.*;


/**
 * A domponfnt tibt displbys b list of objfdts bnd bllows tif usfr to sflfdt
 * onf or morf itfms. A sfpbrbtf modfl, {@dodf ListModfl}, mbintbins tif
 * dontfnts of tif list.
 * <p>
 * It's fbsy to displby bn brrby or Vfdtor of objfdts, using tif {@dodf JList}
 * donstrudtor tibt butombtidblly builds b rfbd-only {@dodf ListModfl} instbndf
 * for you:
 * <prf>
 * {@dodf
 * // Crfbtf b JList tibt displbys strings from bn brrby
 *
 * String[] dbtb = {"onf", "two", "tirff", "four"};
 * JList<String> myList = nfw JList<String>(dbtb);
 *
 * // Crfbtf b JList tibt displbys tif supfrdlbssfs of JList.dlbss, by
 * // drfbting it witi b Vfdtor populbtfd witi tiis dbtb
 *
 * Vfdtor<Clbss<?>> supfrClbssfs = nfw Vfdtor<Clbss<?>>();
 * Clbss<JList> rootClbss = jbvbx.swing.JList.dlbss;
 * for(Clbss<?> dls = rootClbss; dls != null; dls = dls.gftSupfrdlbss()) {
 *     supfrClbssfs.bddElfmfnt(dls);
 * }
 * JList<Clbss<?>> myList = nfw JList<Clbss<?>>(supfrClbssfs);
 *
 * // Tif butombtidblly drfbtfd modfl is storfd in JList's "modfl"
 * // propfrty, wiidi you dbn rftrifvf
 *
 * ListModfl<Clbss<?>> modfl = myList.gftModfl();
 * for(int i = 0; i < modfl.gftSizf(); i++) {
 *     Systfm.out.println(modfl.gftElfmfntAt(i));
 * }
 * }
 * </prf>
 * <p>
 * A {@dodf ListModfl} dbn bf supplifd dirfdtly to b {@dodf JList} by wby of b
 * donstrudtor or tif {@dodf sftModfl} mftiod. Tif dontfnts nffd not bf stbtid -
 * tif numbfr of itfms, bnd tif vblufs of itfms dbn dibngf ovfr timf. A dorrfdt
 * {@dodf ListModfl} implfmfntbtion notififs tif sft of
 * {@dodf jbvbx.swing.fvfnt.ListDbtbListfnfr}s tibt ibvf bffn bddfd to it, fbdi
 * timf b dibngf oddurs. Tifsf dibngfs brf dibrbdtfrizfd by b
 * {@dodf jbvbx.swing.fvfnt.ListDbtbEvfnt}, wiidi idfntififs tif rbngf of list
 * indidfs tibt ibvf bffn modififd, bddfd, or rfmovfd. {@dodf JList}'s
 * {@dodf ListUI} is rfsponsiblf for kffping tif visubl rfprfsfntbtion up to
 * dbtf witi dibngfs, by listfning to tif modfl.
 * <p>
 * Simplf, dynbmid-dontfnt, {@dodf JList} bpplidbtions dbn usf tif
 * {@dodf DffbultListModfl} dlbss to mbintbin list flfmfnts. Tiis dlbss
 * implfmfnts tif {@dodf ListModfl} intfrfbdf bnd blso providfs b
 * <dodf>jbvb.util.Vfdtor</dodf>-likf API. Applidbtions tibt nffd b morf
 * dustom <dodf>ListModfl</dodf> implfmfntbtion mby instfbd wisi to subdlbss
 * {@dodf AbstrbdtListModfl}, wiidi providfs bbsid support for mbnbging bnd
 * notifying listfnfrs. For fxbmplf, b rfbd-only implfmfntbtion of
 * {@dodf AbstrbdtListModfl}:
 * <prf>
 * {@dodf
 * // Tiis list modfl ibs bbout 2^16 flfmfnts.  Enjoy sdrolling.
 *
 * ListModfl<String> bigDbtb = nfw AbstrbdtListModfl<String>() {
 *     publid int gftSizf() { rfturn Siort.MAX_VALUE; }
 *     publid String gftElfmfntAt(int indfx) { rfturn "Indfx " + indfx; }
 * };
 * }
 * </prf>
 * <p>
 * Tif sflfdtion stbtf of b {@dodf JList} is mbnbgfd by bnotifr sfpbrbtf
 * modfl, bn instbndf of {@dodf ListSflfdtionModfl}. {@dodf JList} is
 * initiblizfd witi b sflfdtion modfl on donstrudtion, bnd blso dontbins
 * mftiods to qufry or sft tiis sflfdtion modfl. Additionblly, {@dodf JList}
 * providfs donvfnifnt mftiods for fbsily mbnbging tif sflfdtion. Tifsf mftiods,
 * sudi bs {@dodf sftSflfdtfdIndfx} bnd {@dodf gftSflfdtfdVbluf}, brf dovfr
 * mftiods tibt tbkf dbrf of tif dftbils of intfrbdting witi tif sflfdtion
 * modfl. By dffbult, {@dodf JList}'s sflfdtion modfl is donfigurfd to bllow bny
 * dombinbtion of itfms to bf sflfdtfd bt b timf; sflfdtion modf
 * {@dodf MULTIPLE_INTERVAL_SELECTION}. Tif sflfdtion modf dbn bf dibngfd
 * on tif sflfdtion modfl dirfdtly, or vib {@dodf JList}'s dovfr mftiod.
 * Rfsponsibility for updbting tif sflfdtion modfl in rfsponsf to usfr gfsturfs
 * lifs witi tif list's {@dodf ListUI}.
 * <p>
 * A dorrfdt {@dodf ListSflfdtionModfl} implfmfntbtion notififs tif sft of
 * {@dodf jbvbx.swing.fvfnt.ListSflfdtionListfnfr}s tibt ibvf bffn bddfd to it
 * fbdi timf b dibngf to tif sflfdtion oddurs. Tifsf dibngfs brf dibrbdtfrizfd
 * by b {@dodf jbvbx.swing.fvfnt.ListSflfdtionEvfnt}, wiidi idfntififs tif rbngf
 * of tif sflfdtion dibngf.
 * <p>
 * Tif prfffrrfd wby to listfn for dibngfs in list sflfdtion is to bdd
 * {@dodf ListSflfdtionListfnfr}s dirfdtly to tif {@dodf JList}. {@dodf JList}
 * tifn tbkfs dbrf of listfning to tif tif sflfdtion modfl bnd notifying your
 * listfnfrs of dibngf.
 * <p>
 * Rfsponsibility for listfning to sflfdtion dibngfs in ordfr to kffp tif list's
 * visubl rfprfsfntbtion up to dbtf lifs witi tif list's {@dodf ListUI}.
 * <p>
 * <b nbmf="rfndfrfr"></b>
 * Pbinting of dflls in b {@dodf JList} is ibndlfd by b dflfgbtf dbllfd b
 * dfll rfndfrfr, instbllfd on tif list bs tif {@dodf dfllRfndfrfr} propfrty.
 * Tif rfndfrfr providfs b {@dodf jbvb.bwt.Componfnt} tibt is usfd
 * likf b "rubbfr stbmp" to pbint tif dflls. Ebdi timf b dfll nffds to bf
 * pbintfd, tif list's {@dodf ListUI} bsks tif dfll rfndfrfr for tif domponfnt,
 * movfs it into plbdf, bnd ibs it pbint tif dontfnts of tif dfll by wby of its
 * {@dodf pbint} mftiod. A dffbult dfll rfndfrfr, wiidi usfs b {@dodf JLbbfl}
 * domponfnt to rfndfr, is instbllfd by tif lists's {@dodf ListUI}. You dbn
 * substitutf your own rfndfrfr using dodf likf tiis:
 * <prf>
 * {@dodf
 *  // Displby bn idon bnd b string for fbdi objfdt in tif list.
 *
 * dlbss MyCfllRfndfrfr fxtfnds JLbbfl implfmfnts ListCfllRfndfrfr<Objfdt> {
 *     finbl stbtid ImbgfIdon longIdon = nfw ImbgfIdon("long.gif");
 *     finbl stbtid ImbgfIdon siortIdon = nfw ImbgfIdon("siort.gif");
 *
 *     // Tiis is tif only mftiod dffinfd by ListCfllRfndfrfr.
 *     // Wf just rfdonfigurf tif JLbbfl fbdi timf wf'rf dbllfd.
 *
 *     publid Componfnt gftListCfllRfndfrfrComponfnt(
 *       JList<?> list,           // tif list
 *       Objfdt vbluf,            // vbluf to displby
 *       int indfx,               // dfll indfx
 *       boolfbn isSflfdtfd,      // is tif dfll sflfdtfd
 *       boolfbn dfllHbsFodus)    // dofs tif dfll ibvf fodus
 *     {
 *         String s = vbluf.toString();
 *         sftTfxt(s);
 *         sftIdon((s.lfngti() > 10) ? longIdon : siortIdon);
 *         if (isSflfdtfd) {
 *             sftBbdkground(list.gftSflfdtionBbdkground());
 *             sftForfground(list.gftSflfdtionForfground());
 *         } flsf {
 *             sftBbdkground(list.gftBbdkground());
 *             sftForfground(list.gftForfground());
 *         }
 *         sftEnbblfd(list.isEnbblfd());
 *         sftFont(list.gftFont());
 *         sftOpbquf(truf);
 *         rfturn tiis;
 *     }
 * }
 *
 * myList.sftCfllRfndfrfr(nfw MyCfllRfndfrfr());
 * }
 * </prf>
 * <p>
 * Anotifr job for tif dfll rfndfrfr is in iflping to dftfrminf sizing
 * informbtion for tif list. By dffbult, tif list's {@dodf ListUI} dftfrminfs
 * tif sizf of dflls by bsking tif dfll rfndfrfr for its prfffrrfd
 * sizf for fbdi list itfm. Tiis dbn bf fxpfnsivf for lbrgf lists of itfms.
 * To bvoid tifsf dbldulbtions, you dbn sft b {@dodf fixfdCfllWidti} bnd
 * {@dodf fixfdCfllHfigit} on tif list, or ibvf tifsf vblufs dbldulbtfd
 * butombtidblly bbsfd on b singlf prototypf vbluf:
 * <b nbmf="prototypf_fxbmplf"></b>
 * <prf>
 * {@dodf
 * JList<String> bigDbtbList = nfw JList<String>(bigDbtb);
 *
 * // Wf don't wbnt tif JList implfmfntbtion to domputf tif widti
 * // or ifigit of bll of tif list dflls, so wf givf it b string
 * // tibt's bs big bs wf'll nffd for bny dfll.  It usfs tiis to
 * // domputf vblufs for tif fixfdCfllWidti bnd fixfdCfllHfigit
 * // propfrtifs.
 *
 * bigDbtbList.sftPrototypfCfllVbluf("Indfx 1234567890");
 * }
 * </prf>
 * <p>
 * {@dodf JList} dofsn't implfmfnt sdrolling dirfdtly. To drfbtf b list tibt
 * sdrolls, mbkf it tif vifwport vifw of b {@dodf JSdrollPbnf}. For fxbmplf:
 * <prf>
 * JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf(myList);
 *
 * // Or in two stfps:
 * JSdrollPbnf sdrollPbnf = nfw JSdrollPbnf();
 * sdrollPbnf.gftVifwport().sftVifw(myList);
 * </prf>
 * <p>
 * {@dodf JList} dofsn't providf bny spfdibl ibndling of doublf or triplf
 * (or N) mousf dlidks, but it's fbsy to bdd b {@dodf MousfListfnfr} if you
 * wisi to tbkf bdtion on tifsf fvfnts. Usf tif {@dodf lodbtionToIndfx}
 * mftiod to dftfrminf wibt dfll wbs dlidkfd. For fxbmplf:
 * <prf>
 * MousfListfnfr mousfListfnfr = nfw MousfAdbptfr() {
 *     publid void mousfClidkfd(MousfEvfnt f) {
 *         if (f.gftClidkCount() == 2) {
 *             int indfx = list.lodbtionToIndfx(f.gftPoint());
 *             Systfm.out.println("Doublf dlidkfd on Itfm " + indfx);
 *          }
 *     }
 * };
 * list.bddMousfListfnfr(mousfListfnfr);
 * </prf>
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 * <p>
 * Sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/list.itml">How to Usf Lists</b>
 * in <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/"><fm>Tif Jbvb Tutoribl</fm></b>
 * for furtifr dodumfntbtion.
 *
 * @sff ListModfl
 * @sff AbstrbdtListModfl
 * @sff DffbultListModfl
 * @sff ListSflfdtionModfl
 * @sff DffbultListSflfdtionModfl
 * @sff ListCfllRfndfrfr
 * @sff DffbultListCfllRfndfrfr
 *
 * @pbrbm <E> tif typf of tif flfmfnts of tiis list
 *
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A domponfnt wiidi bllows for tif sflfdtion of onf or morf objfdts from b list.
 *
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JList<E> fxtfnds JComponfnt implfmfnts Sdrollbblf, Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "ListUI";

    /**
     * Indidbtfs b vfrtidbl lbyout of dflls, in b singlf dolumn;
     * tif dffbult lbyout.
     * @sff #sftLbyoutOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl int VERTICAL = 0;

    /**
     * Indidbtfs b "nfwspbpfr stylf" lbyout witi dflls flowing vfrtidblly
     * tifn iorizontblly.
     * @sff #sftLbyoutOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl int VERTICAL_WRAP = 1;

    /**
     * Indidbtfs b "nfwspbpfr stylf" lbyout witi dflls flowing iorizontblly
     * tifn vfrtidblly.
     * @sff #sftLbyoutOrifntbtion
     * @sindf 1.4
     */
    publid stbtid finbl int HORIZONTAL_WRAP = 2;

    privbtf int fixfdCfllWidti = -1;
    privbtf int fixfdCfllHfigit = -1;
    privbtf int iorizontblSdrollIndrfmfnt = -1;
    privbtf E prototypfCfllVbluf;
    privbtf int visiblfRowCount = 8;
    privbtf Color sflfdtionForfground;
    privbtf Color sflfdtionBbdkground;
    privbtf boolfbn drbgEnbblfd;

    privbtf ListSflfdtionModfl sflfdtionModfl;
    privbtf ListModfl<E> dbtbModfl;
    privbtf ListCfllRfndfrfr<? supfr E> dfllRfndfrfr;
    privbtf ListSflfdtionListfnfr sflfdtionListfnfr;

    /**
     * How to lby out tif dflls; dffbults to <dodf>VERTICAL</dodf>.
     */
    privbtf int lbyoutOrifntbtion;

    /**
     * Tif drop modf for tiis domponfnt.
     */
    privbtf DropModf dropModf = DropModf.USE_SELECTION;

    /**
     * Tif drop lodbtion.
     */
    privbtf trbnsifnt DropLodbtion dropLodbtion;

    /**
     * A subdlbss of <dodf>TrbnsffrHbndlfr.DropLodbtion</dodf> rfprfsfnting
     * b drop lodbtion for b <dodf>JList</dodf>.
     *
     * @sff #gftDropLodbtion
     * @sindf 1.6
     */
    publid stbtid finbl dlbss DropLodbtion fxtfnds TrbnsffrHbndlfr.DropLodbtion {
        privbtf finbl int indfx;
        privbtf finbl boolfbn isInsfrt;

        privbtf DropLodbtion(Point p, int indfx, boolfbn isInsfrt) {
            supfr(p);
            tiis.indfx = indfx;
            tiis.isInsfrt = isInsfrt;
        }

        /**
         * Rfturns tif indfx wifrf droppfd dbtb siould bf plbdfd in tif
         * list. Intfrprftbtion of tif vbluf dfpfnds on tif drop modf sft on
         * tif bssodibtfd domponfnt. If tif drop modf is fitifr
         * <dodf>DropModf.USE_SELECTION</dodf> or <dodf>DropModf.ON</dodf>,
         * tif rfturn vbluf is bn indfx of b row in tif list. If tif drop modf is
         * <dodf>DropModf.INSERT</dodf>, tif rfturn vbluf rfffrs to tif indfx
         * wifrf tif dbtb siould bf insfrtfd. If tif drop modf is
         * <dodf>DropModf.ON_OR_INSERT</dodf>, tif vbluf of
         * <dodf>isInsfrt()</dodf> indidbtfs wiftifr tif indfx is bn indfx
         * of b row, or bn insfrt indfx.
         * <p>
         * <dodf>-1</dodf> indidbtfs tibt tif drop oddurrfd ovfr fmpty spbdf,
         * bnd no indfx dould bf dbldulbtfd.
         *
         * @rfturn tif drop indfx
         */
        publid int gftIndfx() {
            rfturn indfx;
        }

        /**
         * Rfturns wiftifr or not tiis lodbtion rfprfsfnts bn insfrt
         * lodbtion.
         *
         * @rfturn wiftifr or not tiis is bn insfrt lodbtion
         */
        publid boolfbn isInsfrt() {
            rfturn isInsfrt;
        }

        /**
         * Rfturns b string rfprfsfntbtion of tiis drop lodbtion.
         * Tiis mftiod is intfndfd to bf usfd for dfbugging purposfs,
         * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry
         * bftwffn implfmfntbtions.
         *
         * @rfturn b string rfprfsfntbtion of tiis drop lodbtion
         */
        publid String toString() {
            rfturn gftClbss().gftNbmf()
                   + "[dropPoint=" + gftDropPoint() + ","
                   + "indfx=" + indfx + ","
                   + "insfrt=" + isInsfrt + "]";
        }
    }

    /**
     * Construdts b {@dodf JList} tibt displbys flfmfnts from tif spfdififd,
     * {@dodf non-null}, modfl. All {@dodf JList} donstrudtors dflfgbtf to
     * tiis onf.
     * <p>
     * Tiis donstrudtor rfgistfrs tif list witi tif {@dodf ToolTipMbnbgfr},
     * bllowing for tooltips to bf providfd by tif dfll rfndfrfrs.
     *
     * @pbrbm dbtbModfl tif modfl for tif list
     * @fxdfption IllfgblArgumfntExdfption if tif modfl is {@dodf null}
     */
    publid JList(ListModfl<E> dbtbModfl)
    {
        if (dbtbModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("dbtbModfl must bf non null");
        }

        // Rfgistfr witi tif ToolTipMbnbgfr so tibt tooltips from tif
        // rfndfrfr siow tirougi.
        ToolTipMbnbgfr toolTipMbnbgfr = ToolTipMbnbgfr.sibrfdInstbndf();
        toolTipMbnbgfr.rfgistfrComponfnt(tiis);

        lbyoutOrifntbtion = VERTICAL;

        tiis.dbtbModfl = dbtbModfl;
        sflfdtionModfl = drfbtfSflfdtionModfl();
        sftAutosdrolls(truf);
        sftOpbquf(truf);
        updbtfUI();
    }


    /**
     * Construdts b <dodf>JList</dodf> tibt displbys tif flfmfnts in
     * tif spfdififd brrby. Tiis donstrudtor drfbtfs b rfbd-only modfl
     * for tif givfn brrby, bnd tifn dflfgbtfs to tif donstrudtor tibt
     * tbkfs b {@dodf ListModfl}.
     * <p>
     * Attfmpts to pbss b {@dodf null} vbluf to tiis mftiod rfsults in
     * undffinfd bfibvior bnd, most likfly, fxdfptions. Tif drfbtfd modfl
     * rfffrfndfs tif givfn brrby dirfdtly. Attfmpts to modify tif brrby
     * bftfr donstrudting tif list rfsults in undffinfd bfibvior.
     *
     * @pbrbm  listDbtb  tif brrby of Objfdts to bf lobdfd into tif dbtb modfl,
     *                   {@dodf non-null}
     */
    publid JList(finbl E[] listDbtb)
    {
        tiis (
            nfw AbstrbdtListModfl<E>() {
                publid int gftSizf() { rfturn listDbtb.lfngti; }
                publid E gftElfmfntAt(int i) { rfturn listDbtb[i]; }
            }
        );
    }


    /**
     * Construdts b <dodf>JList</dodf> tibt displbys tif flfmfnts in
     * tif spfdififd <dodf>Vfdtor</dodf>. Tiis donstrudtor drfbtfs b rfbd-only
     * modfl for tif givfn {@dodf Vfdtor}, bnd tifn dflfgbtfs to tif donstrudtor
     * tibt tbkfs b {@dodf ListModfl}.
     * <p>
     * Attfmpts to pbss b {@dodf null} vbluf to tiis mftiod rfsults in
     * undffinfd bfibvior bnd, most likfly, fxdfptions. Tif drfbtfd modfl
     * rfffrfndfs tif givfn {@dodf Vfdtor} dirfdtly. Attfmpts to modify tif
     * {@dodf Vfdtor} bftfr donstrudting tif list rfsults in undffinfd bfibvior.
     *
     * @pbrbm  listDbtb  tif <dodf>Vfdtor</dodf> to bf lobdfd into tif
     *                   dbtb modfl, {@dodf non-null}
     */
    publid JList(finbl Vfdtor<? fxtfnds E> listDbtb) {
        tiis (
            nfw AbstrbdtListModfl<E>() {
                publid int gftSizf() { rfturn listDbtb.sizf(); }
                publid E gftElfmfntAt(int i) { rfturn listDbtb.flfmfntAt(i); }
            }
        );
    }


    /**
     * Construdts b <dodf>JList</dodf> witi bn fmpty, rfbd-only, modfl.
     */
    publid JList() {
        tiis (
            nfw AbstrbdtListModfl<E>() {
              publid int gftSizf() { rfturn 0; }
              publid E gftElfmfntAt(int i) { tirow nfw IndfxOutOfBoundsExdfption("No Dbtb Modfl"); }
            }
        );
    }


    /**
     * Rfturns tif {@dodf ListUI}, tif look bnd fffl objfdt tibt
     * rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>ListUI</dodf> objfdt tibt rfndfrs tiis domponfnt
     */
    publid ListUI gftUI() {
        rfturn (ListUI)ui;
    }


    /**
     * Sfts tif {@dodf ListUI}, tif look bnd fffl objfdt tibt
     * rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>ListUI</dodf> objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(ListUI ui) {
        supfr.sftUI(ui);
    }


    /**
     * Rfsfts tif {@dodf ListUI} propfrty by sftting it to tif vbluf providfd
     * by tif durrfnt look bnd fffl. If tif durrfnt dfll rfndfrfr wbs instbllfd
     * by tif dfvflopfr (rbtifr tibn tif look bnd fffl itsflf), tiis blso dbusfs
     * tif dfll rfndfrfr bnd its diildrfn to bf updbtfd, by dblling
     * {@dodf SwingUtilitifs.updbtfComponfntTrffUI} on it.
     *
     * @sff UIMbnbgfr#gftUI
     * @sff SwingUtilitifs#updbtfComponfntTrffUI
     */
    publid void updbtfUI() {
        sftUI((ListUI)UIMbnbgfr.gftUI(tiis));

        ListCfllRfndfrfr<? supfr E> rfndfrfr = gftCfllRfndfrfr();
        if (rfndfrfr instbndfof Componfnt) {
            SwingUtilitifs.updbtfComponfntTrffUI((Componfnt)rfndfrfr);
        }
    }


    /**
     * Rfturns {@dodf "ListUI"}, tif <dodf>UIDffbults</dodf> kfy usfd to look
     * up tif nbmf of tif {@dodf jbvbx.swing.plbf.ListUI} dlbss tibt dffinfs
     * tif look bnd fffl for tiis domponfnt.
     *
     * @rfturn tif string "ListUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /* -----privbtf-----
     * Tiis mftiod is dbllfd by sftPrototypfCfllVbluf bnd sftCfllRfndfrfr
     * to updbtf tif fixfdCfllWidti bnd fixfdCfllHfigit propfrtifs from tif
     * durrfnt vbluf of prototypfCfllVbluf (if it's non null).
     * <p>
     * Tiis mftiod sfts fixfdCfllWidti bnd fixfdCfllHfigit but dofs <b>not</b>
     * gfnfrbtf PropfrtyCibngfEvfnts for tifm.
     *
     * @sff #sftPrototypfCfllVbluf
     * @sff #sftCfllRfndfrfr
     */
    privbtf void updbtfFixfdCfllSizf()
    {
        ListCfllRfndfrfr<? supfr E> dr = gftCfllRfndfrfr();
        E vbluf = gftPrototypfCfllVbluf();

        if ((dr != null) && (vbluf != null)) {
            Componfnt d = dr.gftListCfllRfndfrfrComponfnt(tiis, vbluf, 0, fblsf, fblsf);

            /* Tif ListUI implfmfntbtion will bdd Componfnt d to its privbtf
             * CfllRfndfrfrPbnf iowfvfr wf dbn't bssumf tibt's blrfbdy
             * bffn donf ifrf.  So wf tfmporbrily sft tif onf "inifritfd"
             * propfrty tibt mby bfffdt tif rfndfrfr domponfnts prfffrrfd sizf:
             * its font.
             */
            Font f = d.gftFont();
            d.sftFont(gftFont());

            Dimfnsion d = d.gftPrfffrrfdSizf();
            fixfdCfllWidti = d.widti;
            fixfdCfllHfigit = d.ifigit;

            d.sftFont(f);
        }
    }


    /**
     * Rfturns tif "prototypidbl" dfll vbluf -- b vbluf usfd to dbldulbtf b
     * fixfd widti bnd ifigit for dflls. Tiis dbn bf {@dodf null} if tifrf
     * is no sudi vbluf.
     *
     * @rfturn tif vbluf of tif {@dodf prototypfCfllVbluf} propfrty
     * @sff #sftPrototypfCfllVbluf
     */
    publid E gftPrototypfCfllVbluf() {
        rfturn prototypfCfllVbluf;
    }

    /**
     * Sfts tif {@dodf prototypfCfllVbluf} propfrty, bnd tifn (if tif nfw vbluf
     * is {@dodf non-null}), domputfs tif {@dodf fixfdCfllWidti} bnd
     * {@dodf fixfdCfllHfigit} propfrtifs by rfqufsting tif dfll rfndfrfr
     * domponfnt for tif givfn vbluf (bnd indfx 0) from tif dfll rfndfrfr, bnd
     * using tibt domponfnt's prfffrrfd sizf.
     * <p>
     * Tiis mftiod is usfful wifn tif list is too long to bllow tif
     * {@dodf ListUI} to domputf tif widti/ifigit of fbdi dfll, bnd tifrf is b
     * singlf dfll vbluf tibt is known to oddupy bs mudi spbdf bs bny of tif
     * otifrs, b so-dbllfd prototypf.
     * <p>
     * Wiilf bll tirff of tif {@dodf prototypfCfllVbluf},
     * {@dodf fixfdCfllHfigit}, bnd {@dodf fixfdCfllWidti} propfrtifs mby bf
     * modififd by tiis mftiod, {@dodf PropfrtyCibngfEvfnt} notifidbtions brf
     * only sfnt wifn tif {@dodf prototypfCfllVbluf} propfrty dibngfs.
     * <p>
     * To sff bn fxbmplf wiidi sfts tiis propfrty, sff tif
     * <b irff="#prototypf_fxbmplf">dlbss dfsdription</b> bbovf.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is <dodf>null</dodf>.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm prototypfCfllVbluf  tif vbluf on wiidi to bbsf
     *                          <dodf>fixfdCfllWidti</dodf> bnd
     *                          <dodf>fixfdCfllHfigit</dodf>
     * @sff #gftPrototypfCfllVbluf
     * @sff #sftFixfdCfllWidti
     * @sff #sftFixfdCfllHfigit
     * @sff JComponfnt#bddPropfrtyCibngfListfnfr
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif dfll prototypf vbluf, usfd to domputf dfll widti bnd ifigit.
     */
    publid void sftPrototypfCfllVbluf(E prototypfCfllVbluf) {
        E oldVbluf = tiis.prototypfCfllVbluf;
        tiis.prototypfCfllVbluf = prototypfCfllVbluf;

        /* If tif prototypfCfllVbluf ibs dibngfd bnd is non-null,
         * tifn rfdomputf fixfdCfllWidti bnd fixfdCfllHfigit.
         */

        if ((prototypfCfllVbluf != null) && !prototypfCfllVbluf.fqubls(oldVbluf)) {
            updbtfFixfdCfllSizf();
        }

        firfPropfrtyCibngf("prototypfCfllVbluf", oldVbluf, prototypfCfllVbluf);
    }


    /**
     * Rfturns tif vbluf of tif {@dodf fixfdCfllWidti} propfrty.
     *
     * @rfturn tif fixfd dfll widti
     * @sff #sftFixfdCfllWidti
     */
    publid int gftFixfdCfllWidti() {
        rfturn fixfdCfllWidti;
    }

    /**
     * Sfts b fixfd vbluf to bf usfd for tif widti of fvfry dfll in tif list.
     * If {@dodf widti} is -1, dfll widtis brf domputfd in tif {@dodf ListUI}
     * by bpplying <dodf>gftPrfffrrfdSizf</dodf> to tif dfll rfndfrfr domponfnt
     * for fbdi list flfmfnt.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is {@dodf -1}.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm widti tif widti to bf usfd for bll dflls in tif list
     * @sff #sftPrototypfCfllVbluf
     * @sff #sftFixfdCfllWidti
     * @sff JComponfnt#bddPropfrtyCibngfListfnfr
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Dffinfs b fixfd dfll widti wifn grfbtfr tibn zfro.
     */
    publid void sftFixfdCfllWidti(int widti) {
        int oldVbluf = fixfdCfllWidti;
        fixfdCfllWidti = widti;
        firfPropfrtyCibngf("fixfdCfllWidti", oldVbluf, fixfdCfllWidti);
    }


    /**
     * Rfturns tif vbluf of tif {@dodf fixfdCfllHfigit} propfrty.
     *
     * @rfturn tif fixfd dfll ifigit
     * @sff #sftFixfdCfllHfigit
     */
    publid int gftFixfdCfllHfigit() {
        rfturn fixfdCfllHfigit;
    }

    /**
     * Sfts b fixfd vbluf to bf usfd for tif ifigit of fvfry dfll in tif list.
     * If {@dodf ifigit} is -1, dfll ifigits brf domputfd in tif {@dodf ListUI}
     * by bpplying <dodf>gftPrfffrrfdSizf</dodf> to tif dfll rfndfrfr domponfnt
     * for fbdi list flfmfnt.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is {@dodf -1}.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm ifigit tif ifigit to bf usfd for for bll dflls in tif list
     * @sff #sftPrototypfCfllVbluf
     * @sff #sftFixfdCfllWidti
     * @sff JComponfnt#bddPropfrtyCibngfListfnfr
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Dffinfs b fixfd dfll ifigit wifn grfbtfr tibn zfro.
     */
    publid void sftFixfdCfllHfigit(int ifigit) {
        int oldVbluf = fixfdCfllHfigit;
        fixfdCfllHfigit = ifigit;
        firfPropfrtyCibngf("fixfdCfllHfigit", oldVbluf, fixfdCfllHfigit);
    }


    /**
     * Rfturns tif objfdt rfsponsiblf for pbinting list itfms.
     *
     * @rfturn tif vbluf of tif {@dodf dfllRfndfrfr} propfrty
     * @sff #sftCfllRfndfrfr
     */
    @Trbnsifnt
    publid ListCfllRfndfrfr<? supfr E> gftCfllRfndfrfr() {
        rfturn dfllRfndfrfr;
    }

    /**
     * Sfts tif dflfgbtf tibt is usfd to pbint fbdi dfll in tif list.
     * Tif job of b dfll rfndfrfr is disdussfd in dftbil in tif
     * <b irff="#rfndfrfr">dlbss lfvfl dodumfntbtion</b>.
     * <p>
     * If tif {@dodf prototypfCfllVbluf} propfrty is {@dodf non-null},
     * sftting tif dfll rfndfrfr blso dbusfs tif {@dodf fixfdCfllWidti} bnd
     * {@dodf fixfdCfllHfigit} propfrtifs to bf rf-dbldulbtfd. Only onf
     * <dodf>PropfrtyCibngfEvfnt</dodf> is gfnfrbtfd iowfvfr -
     * for tif <dodf>dfllRfndfrfr</dodf> propfrty.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is providfd by tif {@dodf ListUI}
     * dflfgbtf, i.f. by tif look bnd fffl implfmfntbtion.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm dfllRfndfrfr tif <dodf>ListCfllRfndfrfr</dodf>
     *                          tibt pbints list dflls
     * @sff #gftCfllRfndfrfr
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif domponfnt usfd to drbw tif dflls.
     */
    publid void sftCfllRfndfrfr(ListCfllRfndfrfr<? supfr E> dfllRfndfrfr) {
        ListCfllRfndfrfr<? supfr E> oldVbluf = tiis.dfllRfndfrfr;
        tiis.dfllRfndfrfr = dfllRfndfrfr;

        /* If tif dfllRfndfrfr ibs dibngfd bnd prototypfCfllVbluf
         * wbs sft, tifn rfdomputf fixfdCfllWidti bnd fixfdCfllHfigit.
         */
        if ((dfllRfndfrfr != null) && !dfllRfndfrfr.fqubls(oldVbluf)) {
            updbtfFixfdCfllSizf();
        }

        firfPropfrtyCibngf("dfllRfndfrfr", oldVbluf, dfllRfndfrfr);
    }


    /**
     * Rfturns tif dolor usfd to drbw tif forfground of sflfdtfd itfms.
     * {@dodf DffbultListCfllRfndfrfr} usfs tiis dolor to drbw tif forfground
     * of itfms in tif sflfdtfd stbtf, bs do tif rfndfrfrs instbllfd by most
     * {@dodf ListUI} implfmfntbtions.
     *
     * @rfturn tif dolor to drbw tif forfground of sflfdtfd itfms
     * @sff #sftSflfdtionForfground
     * @sff DffbultListCfllRfndfrfr
     */
    publid Color gftSflfdtionForfground() {
        rfturn sflfdtionForfground;
    }


    /**
     * Sfts tif dolor usfd to drbw tif forfground of sflfdtfd itfms, wiidi
     * dfll rfndfrfrs dbn usf to rfndfr tfxt bnd grbpiids.
     * {@dodf DffbultListCfllRfndfrfr} usfs tiis dolor to drbw tif forfground
     * of itfms in tif sflfdtfd stbtf, bs do tif rfndfrfrs instbllfd by most
     * {@dodf ListUI} implfmfntbtions.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is dffinfd by tif look bnd fffl
     * implfmfntbtion.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm sflfdtionForfground  tif {@dodf Color} to usf in tif forfground
     *                             for sflfdtfd list itfms
     * @sff #gftSflfdtionForfground
     * @sff #sftSflfdtionBbdkground
     * @sff #sftForfground
     * @sff #sftBbdkground
     * @sff #sftFont
     * @sff DffbultListCfllRfndfrfr
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif forfground dolor of sflfdtfd dflls.
     */
    publid void sftSflfdtionForfground(Color sflfdtionForfground) {
        Color oldVbluf = tiis.sflfdtionForfground;
        tiis.sflfdtionForfground = sflfdtionForfground;
        firfPropfrtyCibngf("sflfdtionForfground", oldVbluf, sflfdtionForfground);
    }


    /**
     * Rfturns tif dolor usfd to drbw tif bbdkground of sflfdtfd itfms.
     * {@dodf DffbultListCfllRfndfrfr} usfs tiis dolor to drbw tif bbdkground
     * of itfms in tif sflfdtfd stbtf, bs do tif rfndfrfrs instbllfd by most
     * {@dodf ListUI} implfmfntbtions.
     *
     * @rfturn tif dolor to drbw tif bbdkground of sflfdtfd itfms
     * @sff #sftSflfdtionBbdkground
     * @sff DffbultListCfllRfndfrfr
     */
    publid Color gftSflfdtionBbdkground() {
        rfturn sflfdtionBbdkground;
    }


    /**
     * Sfts tif dolor usfd to drbw tif bbdkground of sflfdtfd itfms, wiidi
     * dfll rfndfrfrs dbn usf fill sflfdtfd dflls.
     * {@dodf DffbultListCfllRfndfrfr} usfs tiis dolor to fill tif bbdkground
     * of itfms in tif sflfdtfd stbtf, bs do tif rfndfrfrs instbllfd by most
     * {@dodf ListUI} implfmfntbtions.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is dffinfd by tif look
     * bnd fffl implfmfntbtion.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm sflfdtionBbdkground  tif {@dodf Color} to usf for tif
     *                             bbdkground of sflfdtfd dflls
     * @sff #gftSflfdtionBbdkground
     * @sff #sftSflfdtionForfground
     * @sff #sftForfground
     * @sff #sftBbdkground
     * @sff #sftFont
     * @sff DffbultListCfllRfndfrfr
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif bbdkground dolor of sflfdtfd dflls.
     */
    publid void sftSflfdtionBbdkground(Color sflfdtionBbdkground) {
        Color oldVbluf = tiis.sflfdtionBbdkground;
        tiis.sflfdtionBbdkground = sflfdtionBbdkground;
        firfPropfrtyCibngf("sflfdtionBbdkground", oldVbluf, sflfdtionBbdkground);
    }


    /**
     * Rfturns tif vbluf of tif {@dodf visiblfRowCount} propfrty. Sff tif
     * dodumfntbtion for {@link #sftVisiblfRowCount} for dftbils on iow to
     * intfrprft tiis vbluf.
     *
     * @rfturn tif vbluf of tif {@dodf visiblfRowCount} propfrty.
     * @sff #sftVisiblfRowCount
     */
    publid int gftVisiblfRowCount() {
        rfturn visiblfRowCount;
    }

    /**
     * Sfts tif {@dodf visiblfRowCount} propfrty, wiidi ibs difffrfnt mfbnings
     * dfpfnding on tif lbyout orifntbtion: For b {@dodf VERTICAL} lbyout
     * orifntbtion, tiis sfts tif prfffrrfd numbfr of rows to displby witiout
     * rfquiring sdrolling; for otifr orifntbtions, it bfffdts tif wrbpping of
     * dflls.
     * <p>
     * In {@dodf VERTICAL} orifntbtion:<br>
     * Sftting tiis propfrty bfffdts tif rfturn vbluf of tif
     * {@link #gftPrfffrrfdSdrollbblfVifwportSizf} mftiod, wiidi is usfd to
     * dbldulbtf tif prfffrrfd sizf of bn fndlosing vifwport. Sff tibt mftiod's
     * dodumfntbtion for morf dftbils.
     * <p>
     * In {@dodf HORIZONTAL_WRAP} bnd {@dodf VERTICAL_WRAP} orifntbtions:<br>
     * Tiis bfffdts iow dflls brf wrbppfd. Sff tif dodumfntbtion of
     * {@link #sftLbyoutOrifntbtion} for morf dftbils.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is {@dodf 8}.
     * <p>
     * Cblling tiis mftiod witi b nfgbtivf vbluf rfsults in tif propfrty
     * bfing sft to {@dodf 0}.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm visiblfRowCount  bn intfgfr spfdifying tif prfffrrfd numbfr of
     *                         rows to displby witiout rfquiring sdrolling
     * @sff #gftVisiblfRowCount
     * @sff #gftPrfffrrfdSdrollbblfVifwportSizf
     * @sff #sftLbyoutOrifntbtion
     * @sff JComponfnt#gftVisiblfRfdt
     * @sff JVifwport
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif prfffrrfd numbfr of rows to displby witiout
     *              rfquiring sdrolling
     */
    publid void sftVisiblfRowCount(int visiblfRowCount) {
        int oldVbluf = tiis.visiblfRowCount;
        tiis.visiblfRowCount = Mbti.mbx(0, visiblfRowCount);
        firfPropfrtyCibngf("visiblfRowCount", oldVbluf, visiblfRowCount);
    }


    /**
     * Rfturns tif lbyout orifntbtion propfrty for tif list: {@dodf VERTICAL}
     * if tif lbyout is b singlf dolumn of dflls, {@dodf VERTICAL_WRAP} if tif
     * lbyout is "nfwspbpfr stylf" witi tif dontfnt flowing vfrtidblly tifn
     * iorizontblly, or {@dodf HORIZONTAL_WRAP} if tif lbyout is "nfwspbpfr
     * stylf" witi tif dontfnt flowing iorizontblly tifn vfrtidblly.
     *
     * @rfturn tif vbluf of tif {@dodf lbyoutOrifntbtion} propfrty
     * @sff #sftLbyoutOrifntbtion
     * @sindf 1.4
     */
    publid int gftLbyoutOrifntbtion() {
        rfturn lbyoutOrifntbtion;
    }


    /**
     * Dffinfs tif wby list dflls brf lbyfd out. Considfr b {@dodf JList}
     * witi fivf dflls. Cflls dbn bf lbyfd out in onf of tif following wbys:
     *
     * <prf>
     * VERTICAL:          0
     *                    1
     *                    2
     *                    3
     *                    4
     *
     * HORIZONTAL_WRAP:   0  1  2
     *                    3  4
     *
     * VERTICAL_WRAP:     0  3
     *                    1  4
     *                    2
     * </prf>
     * <p>
     * A dfsdription of tifsf lbyouts follows:
     *
     * <tbblf bordfr="1"
     *  summbry="Dfsdribfs lbyouts VERTICAL, HORIZONTAL_WRAP, bnd VERTICAL_WRAP">
     *   <tr><ti><p stylf="tfxt-blign:lfft">Vbluf</p></ti><ti><p stylf="tfxt-blign:lfft">Dfsdription</p></ti></tr>
     *   <tr><td><dodf>VERTICAL</dodf>
     *       <td>Cflls brf lbyfd out vfrtidblly in b singlf dolumn.
     *   <tr><td><dodf>HORIZONTAL_WRAP</dodf>
     *       <td>Cflls brf lbyfd out iorizontblly, wrbpping to b nfw row bs
     *           nfdfssbry. If tif {@dodf visiblfRowCount} propfrty is lfss tibn
     *           or fqubl to zfro, wrbpping is dftfrminfd by tif widti of tif
     *           list; otifrwisf wrbpping is donf in sudi b wby bs to fnsurf
     *           {@dodf visiblfRowCount} rows in tif list.
     *   <tr><td><dodf>VERTICAL_WRAP</dodf>
     *       <td>Cflls brf lbyfd out vfrtidblly, wrbpping to b nfw dolumn bs
     *           nfdfssbry. If tif {@dodf visiblfRowCount} propfrty is lfss tibn
     *           or fqubl to zfro, wrbpping is dftfrminfd by tif ifigit of tif
     *           list; otifrwisf wrbpping is donf bt {@dodf visiblfRowCount} rows.
     *  </tbblf>
     * <p>
     * Tif dffbult vbluf of tiis propfrty is <dodf>VERTICAL</dodf>.
     *
     * @pbrbm lbyoutOrifntbtion tif nfw lbyout orifntbtion, onf of:
     *        {@dodf VERTICAL}, {@dodf HORIZONTAL_WRAP} or {@dodf VERTICAL_WRAP}
     * @sff #gftLbyoutOrifntbtion
     * @sff #sftVisiblfRowCount
     * @sff #gftSdrollbblfTrbdksVifwportHfigit
     * @sff #gftSdrollbblfTrbdksVifwportWidti
     * @tirows IllfgblArgumfntExdfption if {@dodf lbyoutOrifntbtion} isn't onf of tif
     *         bllowbblf vblufs
     * @sindf 1.4
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Dffinfs tif wby list dflls brf lbyfd out.
     *        fnum: VERTICAL JList.VERTICAL
     *              HORIZONTAL_WRAP JList.HORIZONTAL_WRAP
     *              VERTICAL_WRAP JList.VERTICAL_WRAP
     */
    publid void sftLbyoutOrifntbtion(int lbyoutOrifntbtion) {
        int oldVbluf = tiis.lbyoutOrifntbtion;
        switdi (lbyoutOrifntbtion) {
        dbsf VERTICAL:
        dbsf VERTICAL_WRAP:
        dbsf HORIZONTAL_WRAP:
            tiis.lbyoutOrifntbtion = lbyoutOrifntbtion;
            firfPropfrtyCibngf("lbyoutOrifntbtion", oldVbluf, lbyoutOrifntbtion);
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("lbyoutOrifntbtion must bf onf of: VERTICAL, HORIZONTAL_WRAP or VERTICAL_WRAP");
        }
    }


    /**
     * Rfturns tif smbllfst list indfx tibt is durrfntly visiblf.
     * In b lfft-to-rigit {@dodf domponfntOrifntbtion}, tif first visiblf
     * dfll is found dlosfst to tif list's uppfr-lfft dornfr. In rigit-to-lfft
     * orifntbtion, it is found dlosfst to tif uppfr-rigit dornfr.
     * If notiing is visiblf or tif list is fmpty, {@dodf -1} is rfturnfd.
     * Notf tibt tif rfturnfd dfll mby only bf pbrtiblly visiblf.
     *
     * @rfturn tif indfx of tif first visiblf dfll
     * @sff #gftLbstVisiblfIndfx
     * @sff JComponfnt#gftVisiblfRfdt
     */
    publid int gftFirstVisiblfIndfx() {
        Rfdtbnglf r = gftVisiblfRfdt();
        int first;
        if (tiis.gftComponfntOrifntbtion().isLfftToRigit()) {
            first = lodbtionToIndfx(r.gftLodbtion());
        } flsf {
            first = lodbtionToIndfx(nfw Point((r.x + r.widti) - 1, r.y));
        }
        if (first != -1) {
            Rfdtbnglf bounds = gftCfllBounds(first, first);
            if (bounds != null) {
                SwingUtilitifs.domputfIntfrsfdtion(r.x, r.y, r.widti, r.ifigit, bounds);
                if (bounds.widti == 0 || bounds.ifigit == 0) {
                    first = -1;
                }
            }
        }
        rfturn first;
    }


    /**
     * Rfturns tif lbrgfst list indfx tibt is durrfntly visiblf.
     * If notiing is visiblf or tif list is fmpty, {@dodf -1} is rfturnfd.
     * Notf tibt tif rfturnfd dfll mby only bf pbrtiblly visiblf.
     *
     * @rfturn tif indfx of tif lbst visiblf dfll
     * @sff #gftFirstVisiblfIndfx
     * @sff JComponfnt#gftVisiblfRfdt
     */
    publid int gftLbstVisiblfIndfx() {
        boolfbn lfftToRigit = tiis.gftComponfntOrifntbtion().isLfftToRigit();
        Rfdtbnglf r = gftVisiblfRfdt();
        Point lbstPoint;
        if (lfftToRigit) {
            lbstPoint = nfw Point((r.x + r.widti) - 1, (r.y + r.ifigit) - 1);
        } flsf {
            lbstPoint = nfw Point(r.x, (r.y + r.ifigit) - 1);
        }
        int lodbtion = lodbtionToIndfx(lbstPoint);

        if (lodbtion != -1) {
            Rfdtbnglf bounds = gftCfllBounds(lodbtion, lodbtion);

            if (bounds != null) {
                SwingUtilitifs.domputfIntfrsfdtion(r.x, r.y, r.widti, r.ifigit, bounds);
                if (bounds.widti == 0 || bounds.ifigit == 0) {
                    // Try tif top lfft(LTR) or top rigit(RTL) dornfr, bnd
                    // tifn go bdross difdking fbdi dfll for HORIZONTAL_WRAP.
                    // Try tif lowfr lfft dornfr, bnd tifn go bdross difdking
                    // fbdi dfll for otifr list lbyout orifntbtion.
                    boolfbn isHorizontblWrbp =
                        (gftLbyoutOrifntbtion() == HORIZONTAL_WRAP);
                    Point visiblfLodbtion = isHorizontblWrbp ?
                        nfw Point(lbstPoint.x, r.y) :
                        nfw Point(r.x, lbstPoint.y);
                    int lbst;
                    int visIndfx = -1;
                    int lIndfx = lodbtion;
                    lodbtion = -1;

                    do {
                        lbst = visIndfx;
                        visIndfx = lodbtionToIndfx(visiblfLodbtion);

                        if (visIndfx != -1) {
                            bounds = gftCfllBounds(visIndfx, visIndfx);
                            if (visIndfx != lIndfx && bounds != null &&
                                bounds.dontbins(visiblfLodbtion)) {
                                lodbtion = visIndfx;
                                if (isHorizontblWrbp) {
                                    visiblfLodbtion.y = bounds.y + bounds.ifigit;
                                    if (visiblfLodbtion.y >= lbstPoint.y) {
                                        // Pbst visiblf rfgion, bbil.
                                        lbst = visIndfx;
                                    }
                                }
                                flsf {
                                    visiblfLodbtion.x = bounds.x + bounds.widti;
                                    if (visiblfLodbtion.x >= lbstPoint.x) {
                                        // Pbst visiblf rfgion, bbil.
                                        lbst = visIndfx;
                                    }
                                }

                            }
                            flsf {
                                lbst = visIndfx;
                            }
                        }
                    } wiilf (visIndfx != -1 && lbst != visIndfx);
                }
            }
        }
        rfturn lodbtion;
    }


    /**
     * Sdrolls tif list witiin bn fndlosing vifwport to mbkf tif spfdififd
     * dfll domplftfly visiblf. Tiis dblls {@dodf sdrollRfdtToVisiblf} witi
     * tif bounds of tif spfdififd dfll. For tiis mftiod to work, tif
     * {@dodf JList} must bf witiin b <dodf>JVifwport</dodf>.
     * <p>
     * If tif givfn indfx is outsidf tif list's rbngf of dflls, tiis mftiod
     * rfsults in notiing.
     *
     * @pbrbm indfx  tif indfx of tif dfll to mbkf visiblf
     * @sff JComponfnt#sdrollRfdtToVisiblf
     * @sff #gftVisiblfRfdt
     */
    publid void fnsurfIndfxIsVisiblf(int indfx) {
        Rfdtbnglf dfllBounds = gftCfllBounds(indfx, indfx);
        if (dfllBounds != null) {
            sdrollRfdtToVisiblf(dfllBounds);
        }
    }

    /**
     * Turns on or off butombtid drbg ibndling. In ordfr to fnbblf butombtid
     * drbg ibndling, tiis propfrty siould bf sft to {@dodf truf}, bnd tif
     * list's {@dodf TrbnsffrHbndlfr} nffds to bf {@dodf non-null}.
     * Tif dffbult vbluf of tif {@dodf drbgEnbblfd} propfrty is {@dodf fblsf}.
     * <p>
     * Tif job of ionoring tiis propfrty, bnd rfdognizing b usfr drbg gfsturf,
     * lifs witi tif look bnd fffl implfmfntbtion, bnd in pbrtidulbr, tif list's
     * {@dodf ListUI}. Wifn butombtid drbg ibndling is fnbblfd, most look bnd
     * fffls (indluding tiosf tibt subdlbss {@dodf BbsidLookAndFffl}) bfgin b
     * drbg bnd drop opfrbtion wifnfvfr tif usfr prfssfs tif mousf button ovfr
     * bn itfm bnd tifn movfs tif mousf b ffw pixfls. Sftting tiis propfrty to
     * {@dodf truf} dbn tifrfforf ibvf b subtlf ffffdt on iow sflfdtions bfibvf.
     * <p>
     * If b look bnd fffl is usfd tibt ignorfs tiis propfrty, you dbn still
     * bfgin b drbg bnd drop opfrbtion by dblling {@dodf fxportAsDrbg} on tif
     * list's {@dodf TrbnsffrHbndlfr}.
     *
     * @pbrbm b wiftifr or not to fnbblf butombtid drbg ibndling
     * @fxdfption HfbdlfssExdfption if
     *            <dodf>b</dodf> is <dodf>truf</dodf> bnd
     *            <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf>
     *            rfturns <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff #gftDrbgEnbblfd
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     * @sindf 1.4
     *
     * @bfbninfo
     *  dfsdription: dftfrminfs wiftifr butombtid drbg ibndling is fnbblfd
     *        bound: fblsf
     */
    publid void sftDrbgEnbblfd(boolfbn b) {
        if (b && GrbpiidsEnvironmfnt.isHfbdlfss()) {
            tirow nfw HfbdlfssExdfption();
        }
        drbgEnbblfd = b;
    }

    /**
     * Rfturns wiftifr or not butombtid drbg ibndling is fnbblfd.
     *
     * @rfturn tif vbluf of tif {@dodf drbgEnbblfd} propfrty
     * @sff #sftDrbgEnbblfd
     * @sindf 1.4
     */
    publid boolfbn gftDrbgEnbblfd() {
        rfturn drbgEnbblfd;
    }

    /**
     * Sfts tif drop modf for tiis domponfnt. For bbdkwbrd dompbtibility,
     * tif dffbult for tiis propfrty is <dodf>DropModf.USE_SELECTION</dodf>.
     * Usbgf of onf of tif otifr modfs is rfdommfndfd, iowfvfr, for bn
     * improvfd usfr fxpfrifndf. <dodf>DropModf.ON</dodf>, for instbndf,
     * offfrs similbr bfibvior of siowing itfms bs sflfdtfd, but dofs so witiout
     * bfffdting tif bdtubl sflfdtion in tif list.
     * <p>
     * <dodf>JList</dodf> supports tif following drop modfs:
     * <ul>
     *    <li><dodf>DropModf.USE_SELECTION</dodf></li>
     *    <li><dodf>DropModf.ON</dodf></li>
     *    <li><dodf>DropModf.INSERT</dodf></li>
     *    <li><dodf>DropModf.ON_OR_INSERT</dodf></li>
     * </ul>
     * Tif drop modf is only mfbningful if tiis domponfnt ibs b
     * <dodf>TrbnsffrHbndlfr</dodf> tibt bddfpts drops.
     *
     * @pbrbm dropModf tif drop modf to usf
     * @tirows IllfgblArgumfntExdfption if tif drop modf is unsupportfd
     *         or <dodf>null</dodf>
     * @sff #gftDropModf
     * @sff #gftDropLodbtion
     * @sff #sftTrbnsffrHbndlfr
     * @sff TrbnsffrHbndlfr
     * @sindf 1.6
     */
    publid finbl void sftDropModf(DropModf dropModf) {
        if (dropModf != null) {
            switdi (dropModf) {
                dbsf USE_SELECTION:
                dbsf ON:
                dbsf INSERT:
                dbsf ON_OR_INSERT:
                    tiis.dropModf = dropModf;
                    rfturn;
            }
        }

        tirow nfw IllfgblArgumfntExdfption(dropModf + ": Unsupportfd drop modf for list");
    }

    /**
     * Rfturns tif drop modf for tiis domponfnt.
     *
     * @rfturn tif drop modf for tiis domponfnt
     * @sff #sftDropModf
     * @sindf 1.6
     */
    publid finbl DropModf gftDropModf() {
        rfturn dropModf;
    }

    /**
     * Cbldulbtfs b drop lodbtion in tiis domponfnt, rfprfsfnting wifrf b
     * drop bt tif givfn point siould insfrt dbtb.
     *
     * @pbrbm p tif point to dbldulbtf b drop lodbtion for
     * @rfturn tif drop lodbtion, or <dodf>null</dodf>
     */
    DropLodbtion dropLodbtionForPoint(Point p) {
        DropLodbtion lodbtion = null;
        Rfdtbnglf rfdt = null;

        int indfx = lodbtionToIndfx(p);
        if (indfx != -1) {
            rfdt = gftCfllBounds(indfx, indfx);
        }

        switdi(dropModf) {
            dbsf USE_SELECTION:
            dbsf ON:
                lodbtion = nfw DropLodbtion(p,
                    (rfdt != null && rfdt.dontbins(p)) ? indfx : -1,
                    fblsf);

                brfbk;
            dbsf INSERT:
                if (indfx == -1) {
                    lodbtion = nfw DropLodbtion(p, gftModfl().gftSizf(), truf);
                    brfbk;
                }

                if (lbyoutOrifntbtion == HORIZONTAL_WRAP) {
                    boolfbn ltr = gftComponfntOrifntbtion().isLfftToRigit();

                    if (SwingUtilitifs2.lifsInHorizontbl(rfdt, p, ltr, fblsf) == TRAILING) {
                        indfx++;
                    // spfdibl dbsf for bflow bll dflls
                    } flsf if (indfx == gftModfl().gftSizf() - 1 && p.y >= rfdt.y + rfdt.ifigit) {
                        indfx++;
                    }
                } flsf {
                    if (SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, fblsf) == TRAILING) {
                        indfx++;
                    }
                }

                lodbtion = nfw DropLodbtion(p, indfx, truf);

                brfbk;
            dbsf ON_OR_INSERT:
                if (indfx == -1) {
                    lodbtion = nfw DropLodbtion(p, gftModfl().gftSizf(), truf);
                    brfbk;
                }

                boolfbn bftwffn = fblsf;

                if (lbyoutOrifntbtion == HORIZONTAL_WRAP) {
                    boolfbn ltr = gftComponfntOrifntbtion().isLfftToRigit();

                    Sfdtion sfdtion = SwingUtilitifs2.lifsInHorizontbl(rfdt, p, ltr, truf);
                    if (sfdtion == TRAILING) {
                        indfx++;
                        bftwffn = truf;
                    // spfdibl dbsf for bflow bll dflls
                    } flsf if (indfx == gftModfl().gftSizf() - 1 && p.y >= rfdt.y + rfdt.ifigit) {
                        indfx++;
                        bftwffn = truf;
                    } flsf if (sfdtion == LEADING) {
                        bftwffn = truf;
                    }
                } flsf {
                    Sfdtion sfdtion = SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, truf);
                    if (sfdtion == LEADING) {
                        bftwffn = truf;
                    } flsf if (sfdtion == TRAILING) {
                        indfx++;
                        bftwffn = truf;
                    }
                }

                lodbtion = nfw DropLodbtion(p, indfx, bftwffn);

                brfbk;
            dffbult:
                bssfrt fblsf : "Unfxpfdtfd drop modf";
        }

        rfturn lodbtion;
    }

    /**
     * Cbllfd to sft or dlfbr tif drop lodbtion during b DnD opfrbtion.
     * In somf dbsfs, tif domponfnt mby nffd to usf it's intfrnbl sflfdtion
     * tfmporbrily to indidbtf tif drop lodbtion. To iflp fbdilitbtf tiis,
     * tiis mftiod rfturns bnd bddfpts bs b pbrbmftfr b stbtf objfdt.
     * Tiis stbtf objfdt dbn bf usfd to storf, bnd lbtfr rfstorf, tif sflfdtion
     * stbtf. Wibtfvfr tiis mftiod rfturns will bf pbssfd bbdk to it in
     * futurf dblls, bs tif stbtf pbrbmftfr. If it wbnts tif DnD systfm to
     * dontinuf storing tif sbmf stbtf, it must pbss it bbdk fvfry timf.
     * Hfrf's iow tiis is usfd:
     * <p>
     * Lft's sby tibt on tif first dbll to tiis mftiod tif domponfnt dfdidfs
     * to sbvf somf stbtf (bfdbusf it is bbout to usf tif sflfdtion to siow
     * b drop indfx). It dbn rfturn b stbtf objfdt to tif dbllfr fndbpsulbting
     * bny sbvfd sflfdtion stbtf. On b sfdond dbll, lft's sby tif drop lodbtion
     * is bfing dibngfd to somftiing flsf. Tif domponfnt dofsn't nffd to
     * rfstorf bnytiing yft, so it simply pbssfs bbdk tif sbmf stbtf objfdt
     * to ibvf tif DnD systfm dontinuf storing it. Finblly, lft's sby tiis
     * mftiod is mfssbgfd witi <dodf>null</dodf>. Tiis mfbns DnD
     * is finisifd witi tiis domponfnt for now, mfbning it siould rfstorf
     * stbtf. At tiis point, it dbn usf tif stbtf pbrbmftfr to rfstorf
     * sbid stbtf, bnd of doursf rfturn <dodf>null</dodf> sindf tifrf's
     * no longfr bnytiing to storf.
     *
     * @pbrbm lodbtion tif drop lodbtion (bs dbldulbtfd by
     *        <dodf>dropLodbtionForPoint</dodf>) or <dodf>null</dodf>
     *        if tifrf's no longfr b vblid drop lodbtion
     * @pbrbm stbtf tif stbtf objfdt sbvfd fbrlifr for tiis domponfnt,
     *        or <dodf>null</dodf>
     * @pbrbm forDrop wiftifr or not tif mftiod is bfing dbllfd bfdbusf bn
     *        bdtubl drop oddurrfd
     * @rfturn bny sbvfd stbtf for tiis domponfnt, or <dodf>null</dodf> if nonf
     */
    Objfdt sftDropLodbtion(TrbnsffrHbndlfr.DropLodbtion lodbtion,
                           Objfdt stbtf,
                           boolfbn forDrop) {

        Objfdt rftVbl = null;
        DropLodbtion listLodbtion = (DropLodbtion)lodbtion;

        if (dropModf == DropModf.USE_SELECTION) {
            if (listLodbtion == null) {
                if (!forDrop && stbtf != null) {
                    sftSflfdtfdIndidfs(((int[][])stbtf)[0]);

                    int bndior = ((int[][])stbtf)[1][0];
                    int lfbd = ((int[][])stbtf)[1][1];

                    SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(
                            gftSflfdtionModfl(), lfbd, bndior);
                }
            } flsf {
                if (dropLodbtion == null) {
                    int[] inds = gftSflfdtfdIndidfs();
                    rftVbl = nfw int[][] {inds, {gftAndiorSflfdtionIndfx(),
                                                 gftLfbdSflfdtionIndfx()}};
                } flsf {
                    rftVbl = stbtf;
                }

                int indfx = listLodbtion.gftIndfx();
                if (indfx == -1) {
                    dlfbrSflfdtion();
                    gftSflfdtionModfl().sftAndiorSflfdtionIndfx(-1);
                    gftSflfdtionModfl().sftLfbdSflfdtionIndfx(-1);
                } flsf {
                    sftSflfdtionIntfrvbl(indfx, indfx);
                }
            }
        }

        DropLodbtion old = dropLodbtion;
        dropLodbtion = listLodbtion;
        firfPropfrtyCibngf("dropLodbtion", old, dropLodbtion);

        rfturn rftVbl;
    }

    /**
     * Rfturns tif lodbtion tibt tiis domponfnt siould visublly indidbtf
     * bs tif drop lodbtion during b DnD opfrbtion ovfr tif domponfnt,
     * or {@dodf null} if no lodbtion is to durrfntly bf siown.
     * <p>
     * Tiis mftiod is not mfbnt for qufrying tif drop lodbtion
     * from b {@dodf TrbnsffrHbndlfr}, bs tif drop lodbtion is only
     * sft bftfr tif {@dodf TrbnsffrHbndlfr}'s <dodf>dbnImport</dodf>
     * ibs rfturnfd bnd ibs bllowfd for tif lodbtion to bf siown.
     * <p>
     * Wifn tiis propfrty dibngfs, b propfrty dibngf fvfnt witi
     * nbmf "dropLodbtion" is firfd by tif domponfnt.
     * <p>
     * By dffbult, rfsponsibility for listfning for dibngfs to tiis propfrty
     * bnd indidbting tif drop lodbtion visublly lifs witi tif list's
     * {@dodf ListUI}, wiidi mby pbint it dirfdtly bnd/or instbll b dfll
     * rfndfrfr to do so. Dfvflopfrs wisiing to implfmfnt dustom drop lodbtion
     * pbinting bnd/or rfplbdf tif dffbult dfll rfndfrfr, mby nffd to ionor
     * tiis propfrty.
     *
     * @rfturn tif drop lodbtion
     * @sff #sftDropModf
     * @sff TrbnsffrHbndlfr#dbnImport(TrbnsffrHbndlfr.TrbnsffrSupport)
     * @sindf 1.6
     */
    publid finbl DropLodbtion gftDropLodbtion() {
        rfturn dropLodbtion;
    }

    /**
     * Rfturns tif nfxt list flfmfnt wiosf {@dodf toString} vbluf
     * stbrts witi tif givfn prffix.
     *
     * @pbrbm prffix tif string to tfst for b mbtdi
     * @pbrbm stbrtIndfx tif indfx for stbrting tif sfbrdi
     * @pbrbm bibs tif sfbrdi dirfdtion, fitifr
     * Position.Bibs.Forwbrd or Position.Bibs.Bbdkwbrd.
     * @rfturn tif indfx of tif nfxt list flfmfnt tibt
     * stbrts witi tif prffix; otifrwisf {@dodf -1}
     * @fxdfption IllfgblArgumfntExdfption if prffix is {@dodf null}
     * or stbrtIndfx is out of bounds
     * @sindf 1.4
     */
    publid int gftNfxtMbtdi(String prffix, int stbrtIndfx, Position.Bibs bibs) {
        ListModfl<E> modfl = gftModfl();
        int mbx = modfl.gftSizf();
        if (prffix == null) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        if (stbrtIndfx < 0 || stbrtIndfx >= mbx) {
            tirow nfw IllfgblArgumfntExdfption();
        }
        prffix = prffix.toUppfrCbsf();

        // stbrt sfbrdi from tif nfxt flfmfnt bftfr tif sflfdtfd flfmfnt
        int indrfmfnt = (bibs == Position.Bibs.Forwbrd) ? 1 : -1;
        int indfx = stbrtIndfx;
        do {
            E flfmfnt = modfl.gftElfmfntAt(indfx);

            if (flfmfnt != null) {
                String string;

                if (flfmfnt instbndfof String) {
                    string = ((String)flfmfnt).toUppfrCbsf();
                }
                flsf {
                    string = flfmfnt.toString();
                    if (string != null) {
                        string = string.toUppfrCbsf();
                    }
                }

                if (string != null && string.stbrtsWiti(prffix)) {
                    rfturn indfx;
                }
            }
            indfx = (indfx + indrfmfnt + mbx) % mbx;
        } wiilf (indfx != stbrtIndfx);
        rfturn -1;
    }

    /**
     * Rfturns tif tooltip tfxt to bf usfd for tif givfn fvfnt. Tiis ovfrridfs
     * {@dodf JComponfnt}'s {@dodf gftToolTipTfxt} to first difdk tif dfll
     * rfndfrfr domponfnt for tif dfll ovfr wiidi tif fvfnt oddurrfd, rfturning
     * its tooltip tfxt, if bny. Tiis implfmfntbtion bllows you to spfdify
     * tooltip tfxt on tif dfll lfvfl, by using {@dodf sftToolTipTfxt} on your
     * dfll rfndfrfr domponfnt.
     * <p>
     * <strong>Notf:</strong> For <dodf>JList</dodf> to propfrly displby tif
     * tooltips of its rfndfrfrs in tiis mbnnfr, <dodf>JList</dodf> must bf b
     * rfgistfrfd domponfnt witi tif <dodf>ToolTipMbnbgfr</dodf>. Tiis rfgistrbtion
     * is donf butombtidblly in tif donstrudtor. Howfvfr, if bt b lbtfr point
     * <dodf>JList</dodf> is unrfgistfrfd, by wby of b dbll to
     * {@dodf sftToolTipTfxt(null)}, tips from tif rfndfrfrs will no longfr displby.
     *
     * @pbrbm fvfnt tif {@dodf MousfEvfnt} to fftdi tif tooltip tfxt for
     * @sff JComponfnt#sftToolTipTfxt
     * @sff JComponfnt#gftToolTipTfxt
     */
    publid String gftToolTipTfxt(MousfEvfnt fvfnt) {
        if(fvfnt != null) {
            Point p = fvfnt.gftPoint();
            int indfx = lodbtionToIndfx(p);
            ListCfllRfndfrfr<? supfr E> r = gftCfllRfndfrfr();
            Rfdtbnglf dfllBounds;

            if (indfx != -1 && r != null && (dfllBounds =
                               gftCfllBounds(indfx, indfx)) != null &&
                               dfllBounds.dontbins(p.x, p.y)) {
                ListSflfdtionModfl lsm = gftSflfdtionModfl();
                Componfnt rComponfnt = r.gftListCfllRfndfrfrComponfnt(
                           tiis, gftModfl().gftElfmfntAt(indfx), indfx,
                           lsm.isSflfdtfdIndfx(indfx),
                           (ibsFodus() && (lsm.gftLfbdSflfdtionIndfx() ==
                                           indfx)));

                if(rComponfnt instbndfof JComponfnt) {
                    MousfEvfnt      nfwEvfnt;

                    p.trbnslbtf(-dfllBounds.x, -dfllBounds.y);
                    nfwEvfnt = nfw MousfEvfnt(rComponfnt, fvfnt.gftID(),
                                              fvfnt.gftWifn(),
                                              fvfnt.gftModififrs(),
                                              p.x, p.y,
                                              fvfnt.gftXOnSdrffn(),
                                              fvfnt.gftYOnSdrffn(),
                                              fvfnt.gftClidkCount(),
                                              fvfnt.isPopupTriggfr(),
                                              MousfEvfnt.NOBUTTON);

                    String tip = ((JComponfnt)rComponfnt).gftToolTipTfxt(
                                              nfwEvfnt);

                    if (tip != null) {
                        rfturn tip;
                    }
                }
            }
        }
        rfturn supfr.gftToolTipTfxt();
    }

    /**
     * --- ListUI Dflfgbtions ---
     */


    /**
     * Rfturns tif dfll indfx dlosfst to tif givfn lodbtion in tif list's
     * doordinbtf systfm. To dftfrminf if tif dfll bdtublly dontbins tif
     * spfdififd lodbtion, dompbrf tif point bgbinst tif dfll's bounds,
     * bs providfd by {@dodf gftCfllBounds}. Tiis mftiod rfturns {@dodf -1}
     * if tif modfl is fmpty
     * <p>
     * Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf nbmf
     * in tif list's {@dodf ListUI}. It rfturns {@dodf -1} if tif list ibs
     * no {@dodf ListUI}.
     *
     * @pbrbm lodbtion tif doordinbtfs of tif point
     * @rfturn tif dfll indfx dlosfst to tif givfn lodbtion, or {@dodf -1}
     */
    publid int lodbtionToIndfx(Point lodbtion) {
        ListUI ui = gftUI();
        rfturn (ui != null) ? ui.lodbtionToIndfx(tiis, lodbtion) : -1;
    }


    /**
     * Rfturns tif origin of tif spfdififd itfm in tif list's doordinbtf
     * systfm. Tiis mftiod rfturns {@dodf null} if tif indfx isn't vblid.
     * <p>
     * Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf nbmf
     * in tif list's {@dodf ListUI}. It rfturns {@dodf null} if tif list ibs
     * no {@dodf ListUI}.
     *
     * @pbrbm indfx tif dfll indfx
     * @rfturn tif origin of tif dfll, or {@dodf null}
     */
    publid Point indfxToLodbtion(int indfx) {
        ListUI ui = gftUI();
        rfturn (ui != null) ? ui.indfxToLodbtion(tiis, indfx) : null;
    }


    /**
     * Rfturns tif bounding rfdtbnglf, in tif list's doordinbtf systfm,
     * for tif rbngf of dflls spfdififd by tif two indidfs.
     * Tifsf indidfs dbn bf supplifd in bny ordfr.
     * <p>
     * If tif smbllfr indfx is outsidf tif list's rbngf of dflls, tiis mftiod
     * rfturns {@dodf null}. If tif smbllfr indfx is vblid, but tif lbrgfr
     * indfx is outsidf tif list's rbngf, tif bounds of just tif first indfx
     * is rfturnfd. Otifrwisf, tif bounds of tif vblid rbngf is rfturnfd.
     * <p>
     * Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf nbmf
     * in tif list's {@dodf ListUI}. It rfturns {@dodf null} if tif list ibs
     * no {@dodf ListUI}.
     *
     * @pbrbm indfx0 tif first indfx in tif rbngf
     * @pbrbm indfx1 tif sfdond indfx in tif rbngf
     * @rfturn tif bounding rfdtbnglf for tif rbngf of dflls, or {@dodf null}
     */
    publid Rfdtbnglf gftCfllBounds(int indfx0, int indfx1) {
        ListUI ui = gftUI();
        rfturn (ui != null) ? ui.gftCfllBounds(tiis, indfx0, indfx1) : null;
    }


    /**
     * --- ListModfl Support ---
     */


    /**
     * Rfturns tif dbtb modfl tibt iolds tif list of itfms displbyfd
     * by tif <dodf>JList</dodf> domponfnt.
     *
     * @rfturn tif <dodf>ListModfl</dodf> tibt providfs tif displbyfd
     *                          list of itfms
     * @sff #sftModfl
     */
    publid ListModfl<E> gftModfl() {
        rfturn dbtbModfl;
    }

    /**
     * Sfts tif modfl tibt rfprfsfnts tif dontfnts or "vbluf" of tif
     * list, notififs propfrty dibngf listfnfrs, bnd tifn dlfbrs tif
     * list's sflfdtion.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm modfl  tif <dodf>ListModfl</dodf> tibt providfs tif
     *                                          list of itfms for displby
     * @fxdfption IllfgblArgumfntExdfption  if <dodf>modfl</dodf> is
     *                                          <dodf>null</dodf>
     * @sff #gftModfl
     * @sff #dlfbrSflfdtion
     * @bfbninfo
     *       bound: truf
     *   bttributf: visublUpdbtf truf
     * dfsdription: Tif objfdt tibt dontbins tif dbtb to bf drbwn by tiis JList.
     */
    publid void sftModfl(ListModfl<E> modfl) {
        if (modfl == null) {
            tirow nfw IllfgblArgumfntExdfption("modfl must bf non null");
        }
        ListModfl<E> oldVbluf = dbtbModfl;
        dbtbModfl = modfl;
        firfPropfrtyCibngf("modfl", oldVbluf, dbtbModfl);
        dlfbrSflfdtion();
    }


    /**
     * Construdts b rfbd-only <dodf>ListModfl</dodf> from bn brrby of itfms,
     * bnd dblls {@dodf sftModfl} witi tiis modfl.
     * <p>
     * Attfmpts to pbss b {@dodf null} vbluf to tiis mftiod rfsults in
     * undffinfd bfibvior bnd, most likfly, fxdfptions. Tif drfbtfd modfl
     * rfffrfndfs tif givfn brrby dirfdtly. Attfmpts to modify tif brrby
     * bftfr invoking tiis mftiod rfsults in undffinfd bfibvior.
     *
     * @pbrbm listDbtb bn brrby of {@dodf E} dontbining tif itfms to
     *        displby in tif list
     * @sff #sftModfl
     */
    publid void sftListDbtb(finbl E[] listDbtb) {
        sftModfl (
            nfw AbstrbdtListModfl<E>() {
                publid int gftSizf() { rfturn listDbtb.lfngti; }
                publid E gftElfmfntAt(int i) { rfturn listDbtb[i]; }
            }
        );
    }


    /**
     * Construdts b rfbd-only <dodf>ListModfl</dodf> from b <dodf>Vfdtor</dodf>
     * bnd dblls {@dodf sftModfl} witi tiis modfl.
     * <p>
     * Attfmpts to pbss b {@dodf null} vbluf to tiis mftiod rfsults in
     * undffinfd bfibvior bnd, most likfly, fxdfptions. Tif drfbtfd modfl
     * rfffrfndfs tif givfn {@dodf Vfdtor} dirfdtly. Attfmpts to modify tif
     * {@dodf Vfdtor} bftfr invoking tiis mftiod rfsults in undffinfd bfibvior.
     *
     * @pbrbm listDbtb b <dodf>Vfdtor</dodf> dontbining tif itfms to
     *                                          displby in tif list
     * @sff #sftModfl
     */
    publid void sftListDbtb(finbl Vfdtor<? fxtfnds E> listDbtb) {
        sftModfl (
            nfw AbstrbdtListModfl<E>() {
                publid int gftSizf() { rfturn listDbtb.sizf(); }
                publid E gftElfmfntAt(int i) { rfturn listDbtb.flfmfntAt(i); }
            }
        );
    }


    /**
     * --- ListSflfdtionModfl dflfgbtions bnd fxtfnsions ---
     */


    /**
     * Rfturns bn instbndf of {@dodf DffbultListSflfdtionModfl}; dbllfd
     * during donstrudtion to initiblizf tif list's sflfdtion modfl
     * propfrty.
     *
     * @rfturn b {@dodf DffbultListSflfditonModfl}, usfd to initiblizf
     *         tif list's sflfdtion modfl propfrty during donstrudtion
     * @sff #sftSflfdtionModfl
     * @sff DffbultListSflfdtionModfl
     */
    protfdtfd ListSflfdtionModfl drfbtfSflfdtionModfl() {
        rfturn nfw DffbultListSflfdtionModfl();
    }


    /**
     * Rfturns tif durrfnt sflfdtion modfl. Tif sflfdtion modfl mbintbins tif
     * sflfdtion stbtf of tif list. Sff tif dlbss lfvfl dodumfntbtion for morf
     * dftbils.
     *
     * @rfturn tif <dodf>ListSflfdtionModfl</dodf> tibt mbintbins tif
     *         list's sflfdtions
     *
     * @sff #sftSflfdtionModfl
     * @sff ListSflfdtionModfl
     */
    publid ListSflfdtionModfl gftSflfdtionModfl() {
        rfturn sflfdtionModfl;
    }


    /**
     * Notififs {@dodf ListSflfdtionListfnfr}s bddfd dirfdtly to tif list
     * of sflfdtion dibngfs mbdf to tif sflfdtion modfl. {@dodf JList}
     * listfns for dibngfs mbdf to tif sflfdtion in tif sflfdtion modfl,
     * bnd forwbrds notifidbtion to listfnfrs bddfd to tif list dirfdtly,
     * by dblling tiis mftiod.
     * <p>
     * Tiis mftiod donstrudts b {@dodf ListSflfdtionEvfnt} witi tiis list
     * bs tif sourdf, bnd tif spfdififd brgumfnts, bnd sfnds it to tif
     * rfgistfrfd {@dodf ListSflfdtionListfnfrs}.
     *
     * @pbrbm firstIndfx tif first indfx in tif rbngf, {@dodf <= lbstIndfx}
     * @pbrbm lbstIndfx tif lbst indfx in tif rbngf, {@dodf >= firstIndfx}
     * @pbrbm isAdjusting wiftifr or not tiis is onf in b sfrifs of
     *        multiplf fvfnts, wifrf dibngfs brf still bfing mbdf
     *
     * @sff #bddListSflfdtionListfnfr
     * @sff #rfmovfListSflfdtionListfnfr
     * @sff jbvbx.swing.fvfnt.ListSflfdtionEvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfSflfdtionVblufCibngfd(int firstIndfx, int lbstIndfx,
                                             boolfbn isAdjusting)
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        ListSflfdtionEvfnt f = null;

        for (int i = listfnfrs.lfngti - 2; i >= 0; i -= 2) {
            if (listfnfrs[i] == ListSflfdtionListfnfr.dlbss) {
                if (f == null) {
                    f = nfw ListSflfdtionEvfnt(tiis, firstIndfx, lbstIndfx,
                                               isAdjusting);
                }
                ((ListSflfdtionListfnfr)listfnfrs[i+1]).vblufCibngfd(f);
            }
        }
    }


    /* A ListSflfdtionListfnfr tibt forwbrds ListSflfdtionEvfnts from
     * tif sflfdtionModfl to tif JList ListSflfdtionListfnfrs.  Tif
     * forwbrdfd fvfnts only difffr from tif originbls in tibt tifir
     * sourdf is tif JList instfbd of tif sflfdtionModfl itsflf.
     */
    privbtf dlbss ListSflfdtionHbndlfr implfmfnts ListSflfdtionListfnfr, Sfriblizbblf
    {
        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            firfSflfdtionVblufCibngfd(f.gftFirstIndfx(),
                                      f.gftLbstIndfx(),
                                      f.gftVblufIsAdjusting());
        }
    }


    /**
     * Adds b listfnfr to tif list, to bf notififd fbdi timf b dibngf to tif
     * sflfdtion oddurs; tif prfffrrfd wby of listfning for sflfdtion stbtf
     * dibngfs. {@dodf JList} tbkfs dbrf of listfning for sflfdtion stbtf
     * dibngfs in tif sflfdtion modfl, bnd notififs tif givfn listfnfr of
     * fbdi dibngf. {@dodf ListSflfdtionEvfnt}s sfnt to tif listfnfr ibvf b
     * {@dodf sourdf} propfrty sft to tiis list.
     *
     * @pbrbm listfnfr tif {@dodf ListSflfdtionListfnfr} to bdd
     * @sff #gftSflfdtionModfl
     * @sff #gftListSflfdtionListfnfrs
     */
    publid void bddListSflfdtionListfnfr(ListSflfdtionListfnfr listfnfr)
    {
        if (sflfdtionListfnfr == null) {
            sflfdtionListfnfr = nfw ListSflfdtionHbndlfr();
            gftSflfdtionModfl().bddListSflfdtionListfnfr(sflfdtionListfnfr);
        }

        listfnfrList.bdd(ListSflfdtionListfnfr.dlbss, listfnfr);
    }


    /**
     * Rfmovfs b sflfdtion listfnfr from tif list.
     *
     * @pbrbm listfnfr tif {@dodf ListSflfdtionListfnfr} to rfmovf
     * @sff #bddListSflfdtionListfnfr
     * @sff #gftSflfdtionModfl
     */
    publid void rfmovfListSflfdtionListfnfr(ListSflfdtionListfnfr listfnfr) {
        listfnfrList.rfmovf(ListSflfdtionListfnfr.dlbss, listfnfr);
    }


    /**
     * Rfturns bn brrby of bll tif {@dodf ListSflfdtionListfnfr}s bddfd
     * to tiis {@dodf JList} by wby of {@dodf bddListSflfdtionListfnfr}.
     *
     * @rfturn bll of tif {@dodf ListSflfdtionListfnfr}s on tiis list, or
     *         bn fmpty brrby if no listfnfrs ibvf bffn bddfd
     * @sff #bddListSflfdtionListfnfr
     * @sindf 1.4
     */
    publid ListSflfdtionListfnfr[] gftListSflfdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(ListSflfdtionListfnfr.dlbss);
    }


    /**
     * Sfts tif <dodf>sflfdtionModfl</dodf> for tif list to b
     * non-<dodf>null</dodf> <dodf>ListSflfdtionModfl</dodf>
     * implfmfntbtion. Tif sflfdtion modfl ibndlfs tif tbsk of mbking singlf
     * sflfdtions, sflfdtions of dontiguous rbngfs, bnd non-dontiguous
     * sflfdtions.
     * <p>
     * Tiis is b JbvbBfbns bound propfrty.
     *
     * @pbrbm sflfdtionModfl  tif <dodf>ListSflfdtionModfl</dodf> tibt
     *                          implfmfnts tif sflfdtions
     * @fxdfption IllfgblArgumfntExdfption   if <dodf>sflfdtionModfl</dodf>
     *                                          is <dodf>null</dodf>
     * @sff #gftSflfdtionModfl
     * @bfbninfo
     *       bound: truf
     * dfsdription: Tif sflfdtion modfl, rfdording wiidi dflls brf sflfdtfd.
     */
    publid void sftSflfdtionModfl(ListSflfdtionModfl sflfdtionModfl) {
        if (sflfdtionModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("sflfdtionModfl must bf non null");
        }

        /* Rfmovf tif forwbrding ListSflfdtionListfnfr from tif old
         * sflfdtionModfl, bnd bdd it to tif nfw onf, if nfdfssbry.
         */
        if (sflfdtionListfnfr != null) {
            tiis.sflfdtionModfl.rfmovfListSflfdtionListfnfr(sflfdtionListfnfr);
            sflfdtionModfl.bddListSflfdtionListfnfr(sflfdtionListfnfr);
        }

        ListSflfdtionModfl oldVbluf = tiis.sflfdtionModfl;
        tiis.sflfdtionModfl = sflfdtionModfl;
        firfPropfrtyCibngf("sflfdtionModfl", oldVbluf, sflfdtionModfl);
    }


    /**
     * Sfts tif sflfdtion modf for tif list. Tiis is b dovfr mftiod tibt sfts
     * tif sflfdtion modf dirfdtly on tif sflfdtion modfl.
     * <p>
     * Tif following list dfsdribfs tif bddfptfd sflfdtion modfs:
     * <ul>
     * <li>{@dodf ListSflfdtionModfl.SINGLE_SELECTION} -
     *   Only onf list indfx dbn bf sflfdtfd bt b timf. In tiis modf,
     *   {@dodf sftSflfdtionIntfrvbl} bnd {@dodf bddSflfdtionIntfrvbl} brf
     *   fquivblfnt, boti rfplbding tif durrfnt sflfdtion witi tif indfx
     *   rfprfsfntfd by tif sfdond brgumfnt (tif "lfbd").
     * <li>{@dodf ListSflfdtionModfl.SINGLE_INTERVAL_SELECTION} -
     *   Only onf dontiguous intfrvbl dbn bf sflfdtfd bt b timf.
     *   In tiis modf, {@dodf bddSflfdtionIntfrvbl} bfibvfs likf
     *   {@dodf sftSflfdtionIntfrvbl} (rfplbding tif durrfnt sflfdtion},
     *   unlfss tif givfn intfrvbl is immfdibtfly bdjbdfnt to or ovfrlbps
     *   tif fxisting sflfdtion, bnd dbn bf usfd to grow tif sflfdtion.
     * <li>{@dodf ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION} -
     *   In tiis modf, tifrf's no rfstridtion on wibt dbn bf sflfdtfd.
     *   Tiis modf is tif dffbult.
     * </ul>
     *
     * @pbrbm sflfdtionModf tif sflfdtion modf
     * @sff #gftSflfdtionModf
     * @tirows IllfgblArgumfntExdfption if tif sflfdtion modf isn't
     *         onf of tiosf bllowfd
     * @bfbninfo
     * dfsdription: Tif sflfdtion modf.
     *        fnum: SINGLE_SELECTION            ListSflfdtionModfl.SINGLE_SELECTION
     *              SINGLE_INTERVAL_SELECTION   ListSflfdtionModfl.SINGLE_INTERVAL_SELECTION
     *              MULTIPLE_INTERVAL_SELECTION ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION
     */
    publid void sftSflfdtionModf(int sflfdtionModf) {
        gftSflfdtionModfl().sftSflfdtionModf(sflfdtionModf);
    }

    /**
     * Rfturns tif durrfnt sflfdtion modf for tif list. Tiis is b dovfr
     * mftiod tibt dflfgbtfs to tif mftiod of tif sbmf nbmf on tif
     * list's sflfdtion modfl.
     *
     * @rfturn tif durrfnt sflfdtion modf
     * @sff #sftSflfdtionModf
     */
    publid int gftSflfdtionModf() {
        rfturn gftSflfdtionModfl().gftSflfdtionModf();
    }


    /**
     * Rfturns tif bndior sflfdtion indfx. Tiis is b dovfr mftiod tibt
     * dflfgbtfs to tif mftiod of tif sbmf nbmf on tif list's sflfdtion modfl.
     *
     * @rfturn tif bndior sflfdtion indfx
     * @sff ListSflfdtionModfl#gftAndiorSflfdtionIndfx
     */
    publid int gftAndiorSflfdtionIndfx() {
        rfturn gftSflfdtionModfl().gftAndiorSflfdtionIndfx();
    }


    /**
     * Rfturns tif lfbd sflfdtion indfx. Tiis is b dovfr mftiod tibt
     * dflfgbtfs to tif mftiod of tif sbmf nbmf on tif list's sflfdtion modfl.
     *
     * @rfturn tif lfbd sflfdtion indfx
     * @sff ListSflfdtionModfl#gftLfbdSflfdtionIndfx
     * @bfbninfo
     * dfsdription: Tif lfbd sflfdtion indfx.
     */
    publid int gftLfbdSflfdtionIndfx() {
        rfturn gftSflfdtionModfl().gftLfbdSflfdtionIndfx();
    }


    /**
     * Rfturns tif smbllfst sflfdtfd dfll indfx, or {@dodf -1} if tif sflfdtion
     * is fmpty. Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf
     * nbmf on tif list's sflfdtion modfl.
     *
     * @rfturn tif smbllfst sflfdtfd dfll indfx, or {@dodf -1}
     * @sff ListSflfdtionModfl#gftMinSflfdtionIndfx
     */
    publid int gftMinSflfdtionIndfx() {
        rfturn gftSflfdtionModfl().gftMinSflfdtionIndfx();
    }


    /**
     * Rfturns tif lbrgfst sflfdtfd dfll indfx, or {@dodf -1} if tif sflfdtion
     * is fmpty. Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf
     * nbmf on tif list's sflfdtion modfl.
     *
     * @rfturn tif lbrgfst sflfdtfd dfll indfx
     * @sff ListSflfdtionModfl#gftMbxSflfdtionIndfx
     */
    publid int gftMbxSflfdtionIndfx() {
        rfturn gftSflfdtionModfl().gftMbxSflfdtionIndfx();
    }


    /**
     * Rfturns {@dodf truf} if tif spfdififd indfx is sflfdtfd,
     * flsf {@dodf fblsf}. Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod
     * of tif sbmf nbmf on tif list's sflfdtion modfl.
     *
     * @pbrbm indfx indfx to bf qufrifd for sflfdtion stbtf
     * @rfturn {@dodf truf} if tif spfdififd indfx is sflfdtfd,
     *         flsf {@dodf fblsf}
     * @sff ListSflfdtionModfl#isSflfdtfdIndfx
     * @sff #sftSflfdtfdIndfx
     */
    publid boolfbn isSflfdtfdIndfx(int indfx) {
        rfturn gftSflfdtionModfl().isSflfdtfdIndfx(indfx);
    }


    /**
     * Rfturns {@dodf truf} if notiing is sflfdtfd, flsf {@dodf fblsf}.
     * Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf
     * nbmf on tif list's sflfdtion modfl.
     *
     * @rfturn {@dodf truf} if notiing is sflfdtfd, flsf {@dodf fblsf}
     * @sff ListSflfdtionModfl#isSflfdtionEmpty
     * @sff #dlfbrSflfdtion
     */
    publid boolfbn isSflfdtionEmpty() {
        rfturn gftSflfdtionModfl().isSflfdtionEmpty();
    }


    /**
     * Clfbrs tif sflfdtion; bftfr dblling tiis mftiod, {@dodf isSflfdtionEmpty}
     * will rfturn {@dodf truf}. Tiis is b dovfr mftiod tibt dflfgbtfs to tif
     * mftiod of tif sbmf nbmf on tif list's sflfdtion modfl.
     *
     * @sff ListSflfdtionModfl#dlfbrSflfdtion
     * @sff #isSflfdtionEmpty
     */
    publid void dlfbrSflfdtion() {
        gftSflfdtionModfl().dlfbrSflfdtion();
    }


    /**
     * Sflfdts tif spfdififd intfrvbl. Boti {@dodf bndior} bnd {@dodf lfbd}
     * indidfs brf indludfd. {@dodf bndior} dofsn't ibvf to bf lfss tibn or
     * fqubl to {@dodf lfbd}. Tiis is b dovfr mftiod tibt dflfgbtfs to tif
     * mftiod of tif sbmf nbmf on tif list's sflfdtion modfl.
     * <p>
     * Rfffr to tif dodumfntbtion of tif sflfdtion modfl dlbss bfing usfd
     * for dftbils on iow vblufs lfss tibn {@dodf 0} brf ibndlfd.
     *
     * @pbrbm bndior tif first indfx to sflfdt
     * @pbrbm lfbd tif lbst indfx to sflfdt
     * @sff ListSflfdtionModfl#sftSflfdtionIntfrvbl
     * @sff DffbultListSflfdtionModfl#sftSflfdtionIntfrvbl
     * @sff #drfbtfSflfdtionModfl
     * @sff #bddSflfdtionIntfrvbl
     * @sff #rfmovfSflfdtionIntfrvbl
     */
    publid void sftSflfdtionIntfrvbl(int bndior, int lfbd) {
        gftSflfdtionModfl().sftSflfdtionIntfrvbl(bndior, lfbd);
    }


    /**
     * Sfts tif sflfdtion to bf tif union of tif spfdififd intfrvbl witi durrfnt
     * sflfdtion. Boti tif {@dodf bndior} bnd {@dodf lfbd} indidfs brf
     * indludfd. {@dodf bndior} dofsn't ibvf to bf lfss tibn or
     * fqubl to {@dodf lfbd}. Tiis is b dovfr mftiod tibt dflfgbtfs to tif
     * mftiod of tif sbmf nbmf on tif list's sflfdtion modfl.
     * <p>
     * Rfffr to tif dodumfntbtion of tif sflfdtion modfl dlbss bfing usfd
     * for dftbils on iow vblufs lfss tibn {@dodf 0} brf ibndlfd.
     *
     * @pbrbm bndior tif first indfx to bdd to tif sflfdtion
     * @pbrbm lfbd tif lbst indfx to bdd to tif sflfdtion
     * @sff ListSflfdtionModfl#bddSflfdtionIntfrvbl
     * @sff DffbultListSflfdtionModfl#bddSflfdtionIntfrvbl
     * @sff #drfbtfSflfdtionModfl
     * @sff #sftSflfdtionIntfrvbl
     * @sff #rfmovfSflfdtionIntfrvbl
     */
    publid void bddSflfdtionIntfrvbl(int bndior, int lfbd) {
        gftSflfdtionModfl().bddSflfdtionIntfrvbl(bndior, lfbd);
    }


    /**
     * Sfts tif sflfdtion to bf tif sft difffrfndf of tif spfdififd intfrvbl
     * bnd tif durrfnt sflfdtion. Boti tif {@dodf indfx0} bnd {@dodf indfx1}
     * indidfs brf rfmovfd. {@dodf indfx0} dofsn't ibvf to bf lfss tibn or
     * fqubl to {@dodf indfx1}. Tiis is b dovfr mftiod tibt dflfgbtfs to tif
     * mftiod of tif sbmf nbmf on tif list's sflfdtion modfl.
     * <p>
     * Rfffr to tif dodumfntbtion of tif sflfdtion modfl dlbss bfing usfd
     * for dftbils on iow vblufs lfss tibn {@dodf 0} brf ibndlfd.
     *
     * @pbrbm indfx0 tif first indfx to rfmovf from tif sflfdtion
     * @pbrbm indfx1 tif lbst indfx to rfmovf from tif sflfdtion
     * @sff ListSflfdtionModfl#rfmovfSflfdtionIntfrvbl
     * @sff DffbultListSflfdtionModfl#rfmovfSflfdtionIntfrvbl
     * @sff #drfbtfSflfdtionModfl
     * @sff #sftSflfdtionIntfrvbl
     * @sff #bddSflfdtionIntfrvbl
     */
    publid void rfmovfSflfdtionIntfrvbl(int indfx0, int indfx1) {
        gftSflfdtionModfl().rfmovfSflfdtionIntfrvbl(indfx0, indfx1);
    }


    /**
     * Sfts tif sflfdtion modfl's {@dodf vblufIsAdjusting} propfrty. Wifn
     * {@dodf truf}, updoming dibngfs to sflfdtion siould bf donsidfrfd pbrt
     * of b singlf dibngf. Tiis propfrty is usfd intfrnblly bnd dfvflopfrs
     * typidblly nffd not dbll tiis mftiod. For fxbmplf, wifn tif modfl is bfing
     * updbtfd in rfsponsf to b usfr drbg, tif vbluf of tif propfrty is sft
     * to {@dodf truf} wifn tif drbg is initibtfd bnd sft to {@dodf fblsf}
     * wifn tif drbg is finisifd. Tiis bllows listfnfrs to updbtf only
     * wifn b dibngf ibs bffn finblizfd, rbtifr tibn ibndling bll of tif
     * intfrmfdibtf vblufs.
     * <p>
     * You mby wbnt to usf tiis dirfdtly if mbking b sfrifs of dibngfs
     * tibt siould bf donsidfrfd pbrt of b singlf dibngf.
     * <p>
     * Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf nbmf on
     * tif list's sflfdtion modfl. Sff tif dodumfntbtion for
     * {@link jbvbx.swing.ListSflfdtionModfl#sftVblufIsAdjusting} for
     * morf dftbils.
     *
     * @pbrbm b tif nfw vbluf for tif propfrty
     * @sff ListSflfdtionModfl#sftVblufIsAdjusting
     * @sff jbvbx.swing.fvfnt.ListSflfdtionEvfnt#gftVblufIsAdjusting
     * @sff #gftVblufIsAdjusting
     */
    publid void sftVblufIsAdjusting(boolfbn b) {
        gftSflfdtionModfl().sftVblufIsAdjusting(b);
    }


    /**
     * Rfturns tif vbluf of tif sflfdtion modfl's {@dodf isAdjusting} propfrty.
     * <p>
     * Tiis is b dovfr mftiod tibt dflfgbtfs to tif mftiod of tif sbmf nbmf on
     * tif list's sflfdtion modfl.
     *
     * @rfturn tif vbluf of tif sflfdtion modfl's {@dodf isAdjusting} propfrty.
     *
     * @sff #sftVblufIsAdjusting
     * @sff ListSflfdtionModfl#gftVblufIsAdjusting
     */
    publid boolfbn gftVblufIsAdjusting() {
        rfturn gftSflfdtionModfl().gftVblufIsAdjusting();
    }


    /**
     * Rfturns bn brrby of bll of tif sflfdtfd indidfs, in indrfbsing
     * ordfr.
     *
     * @rfturn bll of tif sflfdtfd indidfs, in indrfbsing ordfr,
     *         or bn fmpty brrby if notiing is sflfdtfd
     * @sff #rfmovfSflfdtionIntfrvbl
     * @sff #bddListSflfdtionListfnfr
     */
    @Trbnsifnt
    publid int[] gftSflfdtfdIndidfs() {
        ListSflfdtionModfl sm = gftSflfdtionModfl();
        int iMin = sm.gftMinSflfdtionIndfx();
        int iMbx = sm.gftMbxSflfdtionIndfx();

        if ((iMin < 0) || (iMbx < 0)) {
            rfturn nfw int[0];
        }

        int[] rvTmp = nfw int[1+ (iMbx - iMin)];
        int n = 0;
        for(int i = iMin; i <= iMbx; i++) {
            if (sm.isSflfdtfdIndfx(i)) {
                rvTmp[n++] = i;
            }
        }
        int[] rv = nfw int[n];
        Systfm.brrbydopy(rvTmp, 0, rv, 0, n);
        rfturn rv;
    }


    /**
     * Sflfdts b singlf dfll. Dofs notiing if tif givfn indfx is grfbtfr
     * tibn or fqubl to tif modfl sizf. Tiis is b donvfnifndf mftiod tibt usfs
     * {@dodf sftSflfdtionIntfrvbl} on tif sflfdtion modfl. Rfffr to tif
     * dodumfntbtion for tif sflfdtion modfl dlbss bfing usfd for dftbils on
     * iow vblufs lfss tibn {@dodf 0} brf ibndlfd.
     *
     * @pbrbm indfx tif indfx of tif dfll to sflfdt
     * @sff ListSflfdtionModfl#sftSflfdtionIntfrvbl
     * @sff #isSflfdtfdIndfx
     * @sff #bddListSflfdtionListfnfr
     * @bfbninfo
     * dfsdription: Tif indfx of tif sflfdtfd dfll.
     */
    publid void sftSflfdtfdIndfx(int indfx) {
        if (indfx >= gftModfl().gftSizf()) {
            rfturn;
        }
        gftSflfdtionModfl().sftSflfdtionIntfrvbl(indfx, indfx);
    }


    /**
     * Cibngfs tif sflfdtion to bf tif sft of indidfs spfdififd by tif givfn
     * brrby. Indidfs grfbtfr tibn or fqubl to tif modfl sizf brf ignorfd.
     * Tiis is b donvfnifndf mftiod tibt dlfbrs tif sflfdtion bnd tifn usfs
     * {@dodf bddSflfdtionIntfrvbl} on tif sflfdtion modfl to bdd tif indidfs.
     * Rfffr to tif dodumfntbtion of tif sflfdtion modfl dlbss bfing usfd for
     * dftbils on iow vblufs lfss tibn {@dodf 0} brf ibndlfd.
     *
     * @pbrbm indidfs bn brrby of tif indidfs of tif dflls to sflfdt,
     *                {@dodf non-null}
     * @sff ListSflfdtionModfl#bddSflfdtionIntfrvbl
     * @sff #isSflfdtfdIndfx
     * @sff #bddListSflfdtionListfnfr
     * @tirows NullPointfrExdfption if tif givfn brrby is {@dodf null}
     */
    publid void sftSflfdtfdIndidfs(int[] indidfs) {
        ListSflfdtionModfl sm = gftSflfdtionModfl();
        sm.dlfbrSflfdtion();
        int sizf = gftModfl().gftSizf();
        for (int i : indidfs) {
            if (i < sizf) {
                sm.bddSflfdtionIntfrvbl(i, i);
            }
        }
    }


    /**
     * Rfturns bn brrby of bll tif sflfdtfd vblufs, in indrfbsing ordfr bbsfd
     * on tifir indidfs in tif list.
     *
     * @rfturn tif sflfdtfd vblufs, or bn fmpty brrby if notiing is sflfdtfd
     * @sff #isSflfdtfdIndfx
     * @sff #gftModfl
     * @sff #bddListSflfdtionListfnfr
     *
     * @dfprfdbtfd As of JDK 1.7, rfplbdfd by {@link #gftSflfdtfdVblufsList()}
     */
    @Dfprfdbtfd
    publid Objfdt[] gftSflfdtfdVblufs() {
        ListSflfdtionModfl sm = gftSflfdtionModfl();
        ListModfl<E> dm = gftModfl();

        int iMin = sm.gftMinSflfdtionIndfx();
        int iMbx = sm.gftMbxSflfdtionIndfx();

        if ((iMin < 0) || (iMbx < 0)) {
            rfturn nfw Objfdt[0];
        }

        Objfdt[] rvTmp = nfw Objfdt[1+ (iMbx - iMin)];
        int n = 0;
        for(int i = iMin; i <= iMbx; i++) {
            if (sm.isSflfdtfdIndfx(i)) {
                rvTmp[n++] = dm.gftElfmfntAt(i);
            }
        }
        Objfdt[] rv = nfw Objfdt[n];
        Systfm.brrbydopy(rvTmp, 0, rv, 0, n);
        rfturn rv;
    }

    /**
     * Rfturns b list of bll tif sflfdtfd itfms, in indrfbsing ordfr bbsfd
     * on tifir indidfs in tif list.
     *
     * @rfturn tif sflfdtfd itfms, or bn fmpty list if notiing is sflfdtfd
     * @sff #isSflfdtfdIndfx
     * @sff #gftModfl
     * @sff #bddListSflfdtionListfnfr
     *
     * @sindf 1.7
     */
    publid List<E> gftSflfdtfdVblufsList() {
        ListSflfdtionModfl sm = gftSflfdtionModfl();
        ListModfl<E> dm = gftModfl();

        int iMin = sm.gftMinSflfdtionIndfx();
        int iMbx = sm.gftMbxSflfdtionIndfx();

        if ((iMin < 0) || (iMbx < 0)) {
            rfturn Collfdtions.fmptyList();
        }

        List<E> sflfdtfdItfms = nfw ArrbyList<E>();
        for(int i = iMin; i <= iMbx; i++) {
            if (sm.isSflfdtfdIndfx(i)) {
                sflfdtfdItfms.bdd(dm.gftElfmfntAt(i));
            }
        }
        rfturn sflfdtfdItfms;
    }


    /**
     * Rfturns tif smbllfst sflfdtfd dfll indfx; <i>tif sflfdtion</i> wifn only
     * b singlf itfm is sflfdtfd in tif list. Wifn multiplf itfms brf sflfdtfd,
     * it is simply tif smbllfst sflfdtfd indfx. Rfturns {@dodf -1} if tifrf is
     * no sflfdtion.
     * <p>
     * Tiis mftiod is b dovfr tibt dflfgbtfs to {@dodf gftMinSflfdtionIndfx}.
     *
     * @rfturn tif smbllfst sflfdtfd dfll indfx
     * @sff #gftMinSflfdtionIndfx
     * @sff #bddListSflfdtionListfnfr
     */
    publid int gftSflfdtfdIndfx() {
        rfturn gftMinSflfdtionIndfx();
    }


    /**
     * Rfturns tif vbluf for tif smbllfst sflfdtfd dfll indfx;
     * <i>tif sflfdtfd vbluf</i> wifn only b singlf itfm is sflfdtfd in tif
     * list. Wifn multiplf itfms brf sflfdtfd, it is simply tif vbluf for tif
     * smbllfst sflfdtfd indfx. Rfturns {@dodf null} if tifrf is no sflfdtion.
     * <p>
     * Tiis is b donvfnifndf mftiod tibt simply rfturns tif modfl vbluf for
     * {@dodf gftMinSflfdtionIndfx}.
     *
     * @rfturn tif first sflfdtfd vbluf
     * @sff #gftMinSflfdtionIndfx
     * @sff #gftModfl
     * @sff #bddListSflfdtionListfnfr
     */
    publid E gftSflfdtfdVbluf() {
        int i = gftMinSflfdtionIndfx();
        rfturn (i == -1) ? null : gftModfl().gftElfmfntAt(i);
    }


    /**
     * Sflfdts tif spfdififd objfdt from tif list.
     *
     * @pbrbm bnObjfdt      tif objfdt to sflfdt
     * @pbrbm siouldSdroll  {@dodf truf} if tif list siould sdroll to displby
     *                      tif sflfdtfd objfdt, if onf fxists; otifrwisf {@dodf fblsf}
     */
    publid void sftSflfdtfdVbluf(Objfdt bnObjfdt,boolfbn siouldSdroll) {
        if(bnObjfdt == null)
            sftSflfdtfdIndfx(-1);
        flsf if(!bnObjfdt.fqubls(gftSflfdtfdVbluf())) {
            int i,d;
            ListModfl<E> dm = gftModfl();
            for(i=0,d=dm.gftSizf();i<d;i++)
                if(bnObjfdt.fqubls(dm.gftElfmfntAt(i))){
                    sftSflfdtfdIndfx(i);
                    if(siouldSdroll)
                        fnsurfIndfxIsVisiblf(i);
                    rfpbint();  /** FIX-ME sftSflfdtfdIndfx dofs not rfdrbw bll tif timf witi tif bbsid l&f**/
                    rfturn;
                }
            sftSflfdtfdIndfx(-1);
        }
        rfpbint(); /** FIX-ME sftSflfdtfdIndfx dofs not rfdrbw bll tif timf witi tif bbsid l&f**/
    }



    /**
     * --- Tif Sdrollbblf Implfmfntbtion ---
     */

    privbtf void difdkSdrollbblfPbrbmftfrs(Rfdtbnglf visiblfRfdt, int orifntbtion) {
        if (visiblfRfdt == null) {
            tirow nfw IllfgblArgumfntExdfption("visiblfRfdt must bf non-null");
        }
        switdi (orifntbtion) {
        dbsf SwingConstbnts.VERTICAL:
        dbsf SwingConstbnts.HORIZONTAL:
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("orifntbtion must bf onf of: VERTICAL, HORIZONTAL");
        }
    }


    /**
     * Computfs tif sizf of vifwport nffdfd to displby {@dodf visiblfRowCount}
     * rows. Tif vbluf rfturnfd by tiis mftiod dfpfnds on tif lbyout
     * orifntbtion:
     * <p>
     * <b>{@dodf VERTICAL}:</b>
     * <br>
     * Tiis is trivibl if boti {@dodf fixfdCfllWidti} bnd {@dodf fixfdCfllHfigit}
     * ibvf bffn sft (fitifr fxpliditly or by spfdifying b prototypf dfll vbluf).
     * Tif widti is simply tif {@dodf fixfdCfllWidti} plus tif list's iorizontbl
     * insfts. Tif ifigit is tif {@dodf fixfdCfllHfigit} multiplifd by tif
     * {@dodf visiblfRowCount}, plus tif list's vfrtidbl insfts.
     * <p>
     * If fitifr {@dodf fixfdCfllWidti} or {@dodf fixfdCfllHfigit} ibvfn't bffn
     * spfdififd, ifuristids brf usfd. If tif modfl is fmpty, tif widti is
     * tif {@dodf fixfdCfllWidti}, if grfbtfr tibn {@dodf 0}, or b ibrd-dodfd
     * vbluf of {@dodf 256}. Tif ifigit is tif {@dodf fixfdCfllHfigit} multiplifd
     * by {@dodf visiblfRowCount}, if {@dodf fixfdCfllHfigit} is grfbtfr tibn
     * {@dodf 0}, otifrwisf it is b ibrd-dodfd vbluf of {@dodf 16} multiplifd by
     * {@dodf visiblfRowCount}.
     * <p>
     * If tif modfl isn't fmpty, tif widti is tif prfffrrfd sizf's widti,
     * typidblly tif widti of tif widfst list flfmfnt. Tif ifigit is tif
     * {@dodf fixfdCfllHfigit} multiplifd by tif {@dodf visiblfRowCount},
     * plus tif list's vfrtidbl insfts.
     * <p>
     * <b>{@dodf VERTICAL_WRAP} or {@dodf HORIZONTAL_WRAP}:</b>
     * <br>
     * Tiis mftiod simply rfturns tif vbluf from {@dodf gftPrfffrrfdSizf}.
     * Tif list's {@dodf ListUI} is fxpfdtfd to ovfrridf {@dodf gftPrfffrrfdSizf}
     * to rfturn bn bppropribtf vbluf.
     *
     * @rfturn b dimfnsion dontbining tif sizf of tif vifwport nffdfd
     *          to displby {@dodf visiblfRowCount} rows
     * @sff #gftPrfffrrfdSdrollbblfVifwportSizf
     * @sff #sftPrototypfCfllVbluf
     */
    publid Dimfnsion gftPrfffrrfdSdrollbblfVifwportSizf()
    {
        if (gftLbyoutOrifntbtion() != VERTICAL) {
            rfturn gftPrfffrrfdSizf();
        }
        Insfts insfts = gftInsfts();
        int dx = insfts.lfft + insfts.rigit;
        int dy = insfts.top + insfts.bottom;

        int visiblfRowCount = gftVisiblfRowCount();
        int fixfdCfllWidti = gftFixfdCfllWidti();
        int fixfdCfllHfigit = gftFixfdCfllHfigit();

        if ((fixfdCfllWidti > 0) && (fixfdCfllHfigit > 0)) {
            int widti = fixfdCfllWidti + dx;
            int ifigit = (visiblfRowCount * fixfdCfllHfigit) + dy;
            rfturn nfw Dimfnsion(widti, ifigit);
        }
        flsf if (gftModfl().gftSizf() > 0) {
            int widti = gftPrfffrrfdSizf().widti;
            int ifigit;
            Rfdtbnglf r = gftCfllBounds(0, 0);
            if (r != null) {
                ifigit = (visiblfRowCount * r.ifigit) + dy;
            }
            flsf {
                // Will only ibppfn if UI null, siouldn't mbttfr wibt wf rfturn
                ifigit = 1;
            }
            rfturn nfw Dimfnsion(widti, ifigit);
        }
        flsf {
            fixfdCfllWidti = (fixfdCfllWidti > 0) ? fixfdCfllWidti : 256;
            fixfdCfllHfigit = (fixfdCfllHfigit > 0) ? fixfdCfllHfigit : 16;
            rfturn nfw Dimfnsion(fixfdCfllWidti, fixfdCfllHfigit * visiblfRowCount);
        }
    }


    /**
     * Rfturns tif distbndf to sdroll to fxposf tif nfxt or prfvious
     * row (for vfrtidbl sdrolling) or dolumn (for iorizontbl sdrolling).
     * <p>
     * For iorizontbl sdrolling, if tif lbyout orifntbtion is {@dodf VERTICAL},
     * tifn tif list's font sizf is rfturnfd (or {@dodf 1} if tif font is
     * {@dodf null}).
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion {@dodf SwingConstbnts.HORIZONTAL} or
     *                    {@dodf SwingConstbnts.VERTICAL}
     * @pbrbm dirfdtion lfss or fqubl to zfro to sdroll up/bbdk,
     *                  grfbtfr tibn zfro for down/forwbrd
     * @rfturn tif "unit" indrfmfnt for sdrolling in tif spfdififd dirfdtion;
     *         blwbys positivf
     * @sff #gftSdrollbblfBlodkIndrfmfnt
     * @sff Sdrollbblf#gftSdrollbblfUnitIndrfmfnt
     * @tirows IllfgblArgumfntExdfption if {@dodf visiblfRfdt} is {@dodf null}, or
     *         {@dodf orifntbtion} isn't onf of {@dodf SwingConstbnts.VERTICAL} or
     *         {@dodf SwingConstbnts.HORIZONTAL}
     */
    publid int gftSdrollbblfUnitIndrfmfnt(Rfdtbnglf visiblfRfdt, int orifntbtion, int dirfdtion)
    {
        difdkSdrollbblfPbrbmftfrs(visiblfRfdt, orifntbtion);

        if (orifntbtion == SwingConstbnts.VERTICAL) {
            int row = lodbtionToIndfx(visiblfRfdt.gftLodbtion());

            if (row == -1) {
                rfturn 0;
            }
            flsf {
                /* Sdroll Down */
                if (dirfdtion > 0) {
                    Rfdtbnglf r = gftCfllBounds(row, row);
                    rfturn (r == null) ? 0 : r.ifigit - (visiblfRfdt.y - r.y);
                }
                /* Sdroll Up */
                flsf {
                    Rfdtbnglf r = gftCfllBounds(row, row);

                    /* Tif first row is domplftfly visiblf bnd it's row 0.
                     * Wf'rf donf.
                     */
                    if ((r.y == visiblfRfdt.y) && (row == 0))  {
                        rfturn 0;
                    }
                    /* Tif first row is domplftfly visiblf, rfturn tif
                     * ifigit of tif prfvious row or 0 if tif first row
                     * is tif top row of tif list.
                     */
                    flsf if (r.y == visiblfRfdt.y) {
                        Point lod = r.gftLodbtion();
                        lod.y--;
                        int prfvIndfx = lodbtionToIndfx(lod);
                        Rfdtbnglf prfvR = gftCfllBounds(prfvIndfx, prfvIndfx);

                        if (prfvR == null || prfvR.y >= r.y) {
                            rfturn 0;
                        }
                        rfturn prfvR.ifigit;
                    }
                    /* Tif first row is pbrtiblly visiblf, rfturn tif
                     * ifigit of iiddfn pbrt.
                     */
                    flsf {
                        rfturn visiblfRfdt.y - r.y;
                    }
                }
            }
        } flsf if (orifntbtion == SwingConstbnts.HORIZONTAL &&
                           gftLbyoutOrifntbtion() != JList.VERTICAL) {
            boolfbn lfftToRigit = gftComponfntOrifntbtion().isLfftToRigit();
            int indfx;
            Point lfbdingPoint;

            if (lfftToRigit) {
                lfbdingPoint = visiblfRfdt.gftLodbtion();
            }
            flsf {
                lfbdingPoint = nfw Point(visiblfRfdt.x + visiblfRfdt.widti -1,
                                         visiblfRfdt.y);
            }
            indfx = lodbtionToIndfx(lfbdingPoint);

            if (indfx != -1) {
                Rfdtbnglf dfllBounds = gftCfllBounds(indfx, indfx);
                if (dfllBounds != null && dfllBounds.dontbins(lfbdingPoint)) {
                    int lfbdingVisiblfEdgf;
                    int lfbdingCfllEdgf;

                    if (lfftToRigit) {
                        lfbdingVisiblfEdgf = visiblfRfdt.x;
                        lfbdingCfllEdgf = dfllBounds.x;
                    }
                    flsf {
                        lfbdingVisiblfEdgf = visiblfRfdt.x + visiblfRfdt.widti;
                        lfbdingCfllEdgf = dfllBounds.x + dfllBounds.widti;
                    }

                    if (lfbdingCfllEdgf != lfbdingVisiblfEdgf) {
                        if (dirfdtion < 0) {
                            // Siow rfmbindfr of lfbding dfll
                            rfturn Mbti.bbs(lfbdingVisiblfEdgf - lfbdingCfllEdgf);

                        }
                        flsf if (lfftToRigit) {
                            // Hidf rfst of lfbding dfll
                            rfturn lfbdingCfllEdgf + dfllBounds.widti - lfbdingVisiblfEdgf;
                        }
                        flsf {
                            // Hidf rfst of lfbding dfll
                            rfturn lfbdingVisiblfEdgf - dfllBounds.x;
                        }
                    }
                    // ASSUME: All dflls brf tif sbmf widti
                    rfturn dfllBounds.widti;
                }
            }
        }
        Font f = gftFont();
        rfturn (f != null) ? f.gftSizf() : 1;
    }


    /**
     * Rfturns tif distbndf to sdroll to fxposf tif nfxt or prfvious blodk.
     * <p>
     * For vfrtidbl sdrolling, tif following rulfs brf usfd:
     * <ul>
     * <li>if sdrolling down, rfturns tif distbndf to sdroll so tibt tif lbst
     * visiblf flfmfnt bfdomfs tif first domplftfly visiblf flfmfnt
     * <li>if sdrolling up, rfturns tif distbndf to sdroll so tibt tif first
     * visiblf flfmfnt bfdomfs tif lbst domplftfly visiblf flfmfnt
     * <li>rfturns {@dodf visiblfRfdt.ifigit} if tif list is fmpty
     * </ul>
     * <p>
     * For iorizontbl sdrolling, wifn tif lbyout orifntbtion is fitifr
     * {@dodf VERTICAL_WRAP} or {@dodf HORIZONTAL_WRAP}:
     * <ul>
     * <li>if sdrolling rigit, rfturns tif distbndf to sdroll so tibt tif
     * lbst visiblf flfmfnt bfdomfs
     * tif first domplftfly visiblf flfmfnt
     * <li>if sdrolling lfft, rfturns tif distbndf to sdroll so tibt tif first
     * visiblf flfmfnt bfdomfs tif lbst domplftfly visiblf flfmfnt
     * <li>rfturns {@dodf visiblfRfdt.widti} if tif list is fmpty
     * </ul>
     * <p>
     * For iorizontbl sdrolling bnd {@dodf VERTICAL} orifntbtion,
     * rfturns {@dodf visiblfRfdt.widti}.
     * <p>
     * Notf tibt tif vbluf of {@dodf visiblfRfdt} must bf tif fqubl to
     * {@dodf tiis.gftVisiblfRfdt()}.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion {@dodf SwingConstbnts.HORIZONTAL} or
     *                    {@dodf SwingConstbnts.VERTICAL}
     * @pbrbm dirfdtion lfss or fqubl to zfro to sdroll up/bbdk,
     *                  grfbtfr tibn zfro for down/forwbrd
     * @rfturn tif "blodk" indrfmfnt for sdrolling in tif spfdififd dirfdtion;
     *         blwbys positivf
     * @sff #gftSdrollbblfUnitIndrfmfnt
     * @sff Sdrollbblf#gftSdrollbblfBlodkIndrfmfnt
     * @tirows IllfgblArgumfntExdfption if {@dodf visiblfRfdt} is {@dodf null}, or
     *         {@dodf orifntbtion} isn't onf of {@dodf SwingConstbnts.VERTICAL} or
     *         {@dodf SwingConstbnts.HORIZONTAL}
     */
    publid int gftSdrollbblfBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt, int orifntbtion, int dirfdtion) {
        difdkSdrollbblfPbrbmftfrs(visiblfRfdt, orifntbtion);
        if (orifntbtion == SwingConstbnts.VERTICAL) {
            int ind = visiblfRfdt.ifigit;
            /* Sdroll Down */
            if (dirfdtion > 0) {
                // lbst dfll is tif lowfst lfft dfll
                int lbst = lodbtionToIndfx(nfw Point(visiblfRfdt.x, visiblfRfdt.y+visiblfRfdt.ifigit-1));
                if (lbst != -1) {
                    Rfdtbnglf lbstRfdt = gftCfllBounds(lbst,lbst);
                    if (lbstRfdt != null) {
                        ind = lbstRfdt.y - visiblfRfdt.y;
                        if ( (ind == 0) && (lbst < gftModfl().gftSizf()-1) ) {
                            ind = lbstRfdt.ifigit;
                        }
                    }
                }
            }
            /* Sdroll Up */
            flsf {
                int nfwFirst = lodbtionToIndfx(nfw Point(visiblfRfdt.x, visiblfRfdt.y-visiblfRfdt.ifigit));
                int first = gftFirstVisiblfIndfx();
                if (nfwFirst != -1) {
                    if (first == -1) {
                        first = lodbtionToIndfx(visiblfRfdt.gftLodbtion());
                    }
                    Rfdtbnglf nfwFirstRfdt = gftCfllBounds(nfwFirst,nfwFirst);
                    Rfdtbnglf firstRfdt = gftCfllBounds(first,first);
                    if ((nfwFirstRfdt != null) && (firstRfdt!=null)) {
                        wiilf ( (nfwFirstRfdt.y + visiblfRfdt.ifigit <
                                 firstRfdt.y + firstRfdt.ifigit) &&
                                (nfwFirstRfdt.y < firstRfdt.y) ) {
                            nfwFirst++;
                            nfwFirstRfdt = gftCfllBounds(nfwFirst,nfwFirst);
                        }
                        ind = visiblfRfdt.y - nfwFirstRfdt.y;
                        if ( (ind <= 0) && (nfwFirstRfdt.y > 0)) {
                            nfwFirst--;
                            nfwFirstRfdt = gftCfllBounds(nfwFirst,nfwFirst);
                            if (nfwFirstRfdt != null) {
                                ind = visiblfRfdt.y - nfwFirstRfdt.y;
                            }
                        }
                    }
                }
            }
            rfturn ind;
        }
        flsf if (orifntbtion == SwingConstbnts.HORIZONTAL &&
                 gftLbyoutOrifntbtion() != JList.VERTICAL) {
            boolfbn lfftToRigit = gftComponfntOrifntbtion().isLfftToRigit();
            int ind = visiblfRfdt.widti;
            /* Sdroll Rigit (in ltr modf) or Sdroll Lfft (in rtl modf) */
            if (dirfdtion > 0) {
                // position is uppfr rigit if ltr, or uppfr lfft otifrwisf
                int x = visiblfRfdt.x + (lfftToRigit ? (visiblfRfdt.widti - 1) : 0);
                int lbst = lodbtionToIndfx(nfw Point(x, visiblfRfdt.y));

                if (lbst != -1) {
                    Rfdtbnglf lbstRfdt = gftCfllBounds(lbst,lbst);
                    if (lbstRfdt != null) {
                        if (lfftToRigit) {
                            ind = lbstRfdt.x - visiblfRfdt.x;
                        } flsf {
                            ind = visiblfRfdt.x + visiblfRfdt.widti
                                      - (lbstRfdt.x + lbstRfdt.widti);
                        }
                        if (ind < 0) {
                            ind += lbstRfdt.widti;
                        } flsf if ( (ind == 0) && (lbst < gftModfl().gftSizf()-1) ) {
                            ind = lbstRfdt.widti;
                        }
                    }
                }
            }
            /* Sdroll Lfft (in ltr modf) or Sdroll Rigit (in rtl modf) */
            flsf {
                // position is uppfr lfft dornfr of tif visiblfRfdt siiftfd
                // lfft by tif visiblfRfdt.widti if ltr, or uppfr rigit siiftfd
                // rigit by tif visiblfRfdt.widti otifrwisf
                int x = visiblfRfdt.x + (lfftToRigit
                                         ? -visiblfRfdt.widti
                                         : visiblfRfdt.widti - 1 + visiblfRfdt.widti);
                int first = lodbtionToIndfx(nfw Point(x, visiblfRfdt.y));

                if (first != -1) {
                    Rfdtbnglf firstRfdt = gftCfllBounds(first,first);
                    if (firstRfdt != null) {
                        // tif rigit of tif first dfll
                        int firstRigit = firstRfdt.x + firstRfdt.widti;

                        if (lfftToRigit) {
                            if ((firstRfdt.x < visiblfRfdt.x - visiblfRfdt.widti)
                                    && (firstRigit < visiblfRfdt.x)) {
                                ind = visiblfRfdt.x - firstRigit;
                            } flsf {
                                ind = visiblfRfdt.x - firstRfdt.x;
                            }
                        } flsf {
                            int visiblfRigit = visiblfRfdt.x + visiblfRfdt.widti;

                            if ((firstRigit > visiblfRigit + visiblfRfdt.widti)
                                    && (firstRfdt.x > visiblfRigit)) {
                                ind = firstRfdt.x - visiblfRigit;
                            } flsf {
                                ind = firstRigit - visiblfRigit;
                            }
                        }
                    }
                }
            }
            rfturn ind;
        }
        rfturn visiblfRfdt.widti;
    }


    /**
     * Rfturns {@dodf truf} if tiis {@dodf JList} is displbyfd in b
     * {@dodf JVifwport} bnd tif vifwport is widfr tibn tif list's
     * prfffrrfd widti, or if tif lbyout orifntbtion is {@dodf HORIZONTAL_WRAP}
     * bnd {@dodf visiblfRowCount <= 0}; otifrwisf rfturns {@dodf fblsf}.
     * <p>
     * If {@dodf fblsf}, tifn don't trbdk tif vifwport's widti. Tiis bllows
     * iorizontbl sdrolling if tif {@dodf JVifwport} is itsflf fmbfddfd in b
     * {@dodf JSdrollPbnf}.
     *
     * @rfturn wiftifr or not bn fndlosing vifwport siould fordf tif list's
     *         widti to mbtdi its own
     * @sff Sdrollbblf#gftSdrollbblfTrbdksVifwportWidti
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportWidti() {
        if (gftLbyoutOrifntbtion() == HORIZONTAL_WRAP &&
                                      gftVisiblfRowCount() <= 0) {
            rfturn truf;
        }
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            rfturn pbrfnt.gftWidti() > gftPrfffrrfdSizf().widti;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns {@dodf truf} if tiis {@dodf JList} is displbyfd in b
     * {@dodf JVifwport} bnd tif vifwport is tbllfr tibn tif list's
     * prfffrrfd ifigit, or if tif lbyout orifntbtion is {@dodf VERTICAL_WRAP}
     * bnd {@dodf visiblfRowCount <= 0}; otifrwisf rfturns {@dodf fblsf}.
     * <p>
     * If {@dodf fblsf}, tifn don't trbdk tif vifwport's ifigit. Tiis bllows
     * vfrtidbl sdrolling if tif {@dodf JVifwport} is itsflf fmbfddfd in b
     * {@dodf JSdrollPbnf}.
     *
     * @rfturn wiftifr or not bn fndlosing vifwport siould fordf tif list's
     *         ifigit to mbtdi its own
     * @sff Sdrollbblf#gftSdrollbblfTrbdksVifwportHfigit
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportHfigit() {
        if (gftLbyoutOrifntbtion() == VERTICAL_WRAP &&
                     gftVisiblfRowCount() <= 0) {
            rfturn truf;
        }
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            rfturn pbrfnt.gftHfigit() > gftPrfffrrfdSizf().ifigit;
        }
        rfturn fblsf;
    }


    /*
     * Sff {@dodf rfbdObjfdt} bnd {@dodf writfObjfdt} in {@dodf JComponfnt}
     * for morf informbtion bbout sfriblizbtion in Swing.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b {@dodf String} rfprfsfntbtion of tiis {@dodf JList}.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd {@dodf String} mby vbry
     * bftwffn implfmfntbtions. Tif rfturnfd {@dodf String} mby bf fmpty,
     * but mby not bf {@dodf null}.
     *
     * @rfturn  b {@dodf String} rfprfsfntbtion of tiis {@dodf JList}.
     */
    protfdtfd String pbrbmString() {
        String sflfdtionForfgroundString = (sflfdtionForfground != null ?
                                            sflfdtionForfground.toString() :
                                            "");
        String sflfdtionBbdkgroundString = (sflfdtionBbdkground != null ?
                                            sflfdtionBbdkground.toString() :
                                            "");

        rfturn supfr.pbrbmString() +
        ",fixfdCfllHfigit=" + fixfdCfllHfigit +
        ",fixfdCfllWidti=" + fixfdCfllWidti +
        ",iorizontblSdrollIndrfmfnt=" + iorizontblSdrollIndrfmfnt +
        ",sflfdtionBbdkground=" + sflfdtionBbdkgroundString +
        ",sflfdtionForfground=" + sflfdtionForfgroundString +
        ",visiblfRowCount=" + visiblfRowCount +
        ",lbyoutOrifntbtion=" + lbyoutOrifntbtion;
    }


    /**
     * --- Addfssibility Support ---
     */

    /**
     * Gfts tif {@dodf AddfssiblfContfxt} bssodibtfd witi tiis {@dodf JList}.
     * For {@dodf JList}, tif {@dodf AddfssiblfContfxt} tbkfs tif form of bn
     * {@dodf AddfssiblfJList}.
     * <p>
     * A nfw {@dodf AddfssiblfJList} instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn {@dodf AddfssiblfJList} tibt sfrvfs bs tif
     *         {@dodf AddfssiblfContfxt} of tiis {@dodf JList}
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJList();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * {@dodf JList} dlbss. It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to list usfr-intfrfbdf
     * flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss AddfssiblfJList fxtfnds AddfssiblfJComponfnt
        implfmfnts AddfssiblfSflfdtion, PropfrtyCibngfListfnfr,
        ListSflfdtionListfnfr, ListDbtbListfnfr {

        int lfbdSflfdtionIndfx;

        publid AddfssiblfJList() {
            supfr();
            JList.tiis.bddPropfrtyCibngfListfnfr(tiis);
            JList.tiis.gftSflfdtionModfl().bddListSflfdtionListfnfr(tiis);
            JList.tiis.gftModfl().bddListDbtbListfnfr(tiis);
            lfbdSflfdtionIndfx = JList.tiis.gftLfbdSflfdtionIndfx();
        }

        /**
         * Propfrty Cibngf Listfnfr dibngf mftiod. Usfd to trbdk dibngfs
         * to tif DbtbModfl bnd ListSflfdtionModfl, in ordfr to rf-sft
         * listfnfrs to tiosf for rfporting dibngfs tifrf vib tif Addfssibility
         * PropfrtyCibngf mfdibnism.
         *
         * @pbrbm f PropfrtyCibngfEvfnt
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String nbmf = f.gftPropfrtyNbmf();
            Objfdt oldVbluf = f.gftOldVbluf();
            Objfdt nfwVbluf = f.gftNfwVbluf();

                // rf-sft listDbtb listfnfrs
            if (nbmf.dompbrfTo("modfl") == 0) {

                if (oldVbluf != null && oldVbluf instbndfof ListModfl) {
                    ((ListModfl) oldVbluf).rfmovfListDbtbListfnfr(tiis);
                }
                if (nfwVbluf != null && nfwVbluf instbndfof ListModfl) {
                    ((ListModfl) nfwVbluf).bddListDbtbListfnfr(tiis);
                }

                // rf-sft listSflfdtionModfl listfnfrs
            } flsf if (nbmf.dompbrfTo("sflfdtionModfl") == 0) {

                if (oldVbluf != null && oldVbluf instbndfof ListSflfdtionModfl) {
                    ((ListSflfdtionModfl) oldVbluf).rfmovfListSflfdtionListfnfr(tiis);
                }
                if (nfwVbluf != null && nfwVbluf instbndfof ListSflfdtionModfl) {
                    ((ListSflfdtionModfl) nfwVbluf).bddListSflfdtionListfnfr(tiis);
                }

                firfPropfrtyCibngf(
                    AddfssiblfContfxt.ACCESSIBLE_SELECTION_PROPERTY,
                    Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
            }
        }

        /**
         * List Sflfdtion Listfnfr vbluf dibngf mftiod. Usfd to firf
         * tif propfrty dibngf
         *
         * @pbrbm f ListSflfdtionEvfnt
         *
         */
        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            int oldLfbdSflfdtionIndfx = lfbdSflfdtionIndfx;
            lfbdSflfdtionIndfx = JList.tiis.gftLfbdSflfdtionIndfx();
            if (oldLfbdSflfdtionIndfx != lfbdSflfdtionIndfx) {
                Addfssiblf oldLS, nfwLS;
                oldLS = (oldLfbdSflfdtionIndfx >= 0)
                        ? gftAddfssiblfCiild(oldLfbdSflfdtionIndfx)
                        : null;
                nfwLS = (lfbdSflfdtionIndfx >= 0)
                        ? gftAddfssiblfCiild(lfbdSflfdtionIndfx)
                        : null;
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                                   oldLS, nfwLS);
            }

            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_SELECTION_PROPERTY,
                               Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));

            // Prodfss tif Stbtf dibngfs for Multisflfdtbblf
            AddfssiblfStbtfSft s = gftAddfssiblfStbtfSft();
            ListSflfdtionModfl lsm = JList.tiis.gftSflfdtionModfl();
            if (lsm.gftSflfdtionModf() != ListSflfdtionModfl.SINGLE_SELECTION) {
                if (!s.dontbins(AddfssiblfStbtf.MULTISELECTABLE)) {
                    s.bdd(AddfssiblfStbtf.MULTISELECTABLE);
                    firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                       null, AddfssiblfStbtf.MULTISELECTABLE);
                }
            } flsf {
                if (s.dontbins(AddfssiblfStbtf.MULTISELECTABLE)) {
                    s.rfmovf(AddfssiblfStbtf.MULTISELECTABLE);
                    firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                                       AddfssiblfStbtf.MULTISELECTABLE, null);
                }
            }
        }

        /**
         * List Dbtb Listfnfr intfrvbl bddfd mftiod. Usfd to firf tif visiblf dbtb propfrty dibngf
         *
         * @pbrbm f ListDbtbEvfnt
         *
         */
        publid void intfrvblAddfd(ListDbtbEvfnt f) {
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
        }

        /**
         * List Dbtb Listfnfr intfrvbl rfmovfd mftiod. Usfd to firf tif visiblf dbtb propfrty dibngf
         *
         * @pbrbm f ListDbtbEvfnt
         *
         */
        publid void intfrvblRfmovfd(ListDbtbEvfnt f) {
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
        }

        /**
         * List Dbtb Listfnfr dontfnts dibngfd mftiod. Usfd to firf tif visiblf dbtb propfrty dibngf
         *
         * @pbrbm f ListDbtbEvfnt
         *
         */
         publid void dontfntsCibngfd(ListDbtbEvfnt f) {
             firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                                Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
         }

    // AddfssiblfContfxt mftiods

        /**
         * Gft tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtf dontbining tif durrfnt stbtf
         * of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            if (sflfdtionModfl.gftSflfdtionModf() !=
                ListSflfdtionModfl.SINGLE_SELECTION) {
                stbtfs.bdd(AddfssiblfStbtf.MULTISELECTABLE);
            }
            rfturn stbtfs;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.LIST;
        }

        /**
         * Rfturns tif <dodf>Addfssiblf</dodf> diild dontbinfd bt
         * tif lodbl doordinbtf <dodf>Point</dodf>, if onf fxists.
         * Otifrwisf rfturns <dodf>null</dodf>.
         *
         * @rfturn tif <dodf>Addfssiblf</dodf> bt tif spfdififd
         *    lodbtion, if it fxists
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            int i = lodbtionToIndfx(p);
            if (i >= 0) {
                rfturn nfw AddfssiblfJListCiild(JList.tiis, i);
            } flsf {
                rfturn null;
            }
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt Addfssiblf, tibn tiis
         * mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn gftModfl().gftSizf();
        }

        /**
         * Rfturn tif nti Addfssiblf diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti Addfssiblf diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            if (i >= gftModfl().gftSizf()) {
                rfturn null;
            } flsf {
                rfturn nfw AddfssiblfJListCiild(JList.tiis, i);
            }
        }

        /**
         * Gft tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfSflfdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
            rfturn tiis;
        }


    // AddfssiblfSflfdtion mftiods

        /**
         * Rfturns tif numbfr of itfms durrfntly sflfdtfd.
         * If no itfms brf sflfdtfd, tif rfturn vbluf will bf 0.
         *
         * @rfturn tif numbfr of itfms durrfntly sflfdtfd.
         */
         publid int gftAddfssiblfSflfdtionCount() {
             rfturn JList.tiis.gftSflfdtfdIndidfs().lfngti;
         }

        /**
         * Rfturns bn Addfssiblf rfprfsfnting tif spfdififd sflfdtfd itfm
         * in tif objfdt.  If tifrf isn't b sflfdtion, or tifrf brf
         * ffwfr itfms sflfdtfd tibn tif intfgfr pbssfd in, tif rfturn
         * vbluf will bf <dodf>null</dodf>.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd itfms
         * @rfturn bn Addfssiblf dontbining tif sflfdtfd itfm
         */
         publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
             int lfn = gftAddfssiblfSflfdtionCount();
             if (i < 0 || i >= lfn) {
                 rfturn null;
             } flsf {
                 rfturn gftAddfssiblfCiild(JList.tiis.gftSflfdtfdIndidfs()[i]);
             }
         }

        /**
         * Rfturns truf if tif durrfnt diild of tiis objfdt is sflfdtfd.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf
         * objfdt.
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
            rfturn isSflfdtfdIndfx(i);
        }

        /**
         * Adds tif spfdififd sflfdtfd itfm in tif objfdt to tif objfdt's
         * sflfdtion.  If tif objfdt supports multiplf sflfdtions,
         * tif spfdififd itfm is bddfd to bny fxisting sflfdtion, otifrwisf
         * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
         * spfdififd itfm is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
         */
         publid void bddAddfssiblfSflfdtion(int i) {
             JList.tiis.bddSflfdtionIntfrvbl(i, i);
         }

        /**
         * Rfmovfs tif spfdififd sflfdtfd itfm in tif objfdt from tif objfdt's
         * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
         * mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
         */
         publid void rfmovfAddfssiblfSflfdtion(int i) {
             JList.tiis.rfmovfSflfdtionIntfrvbl(i, i);
         }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt notiing in tif
         * objfdt is sflfdtfd.
         */
         publid void dlfbrAddfssiblfSflfdtion() {
             JList.tiis.dlfbrSflfdtion();
         }

        /**
         * Cbusfs fvfry sflfdtfd itfm in tif objfdt to bf sflfdtfd
         * if tif objfdt supports multiplf sflfdtions.
         */
         publid void sflfdtAllAddfssiblfSflfdtion() {
             JList.tiis.bddSflfdtionIntfrvbl(0, gftAddfssiblfCiildrfnCount() -1);
         }

          /**
           * Tiis dlbss implfmfnts bddfssibility support bppropribtf
           * for list diildrfn.
           */
        protfdtfd dlbss AddfssiblfJListCiild fxtfnds AddfssiblfContfxt
                implfmfnts Addfssiblf, AddfssiblfComponfnt {
            privbtf JList<E>     pbrfnt = null;
            privbtf int       indfxInPbrfnt;
            privbtf Componfnt domponfnt = null;
            privbtf AddfssiblfContfxt bddfssiblfContfxt = null;
            privbtf ListModfl<E> listModfl;
            privbtf ListCfllRfndfrfr<? supfr E> dfllRfndfrfr = null;

            publid AddfssiblfJListCiild(JList<E> pbrfnt, int indfxInPbrfnt) {
                tiis.pbrfnt = pbrfnt;
                tiis.sftAddfssiblfPbrfnt(pbrfnt);
                tiis.indfxInPbrfnt = indfxInPbrfnt;
                if (pbrfnt != null) {
                    listModfl = pbrfnt.gftModfl();
                    dfllRfndfrfr = pbrfnt.gftCfllRfndfrfr();
                }
            }

            privbtf Componfnt gftCurrfntComponfnt() {
                rfturn gftComponfntAtIndfx(indfxInPbrfnt);
            }

            privbtf AddfssiblfContfxt gftCurrfntAddfssiblfContfxt() {
                Componfnt d = gftComponfntAtIndfx(indfxInPbrfnt);
                if (d instbndfof Addfssiblf) {
                    rfturn d.gftAddfssiblfContfxt();
                } flsf {
                    rfturn null;
                }
            }

            privbtf Componfnt gftComponfntAtIndfx(int indfx) {
                if (indfx < 0 || indfx >= listModfl.gftSizf()) {
                    rfturn null;
                }
                if ((pbrfnt != null)
                        && (listModfl != null)
                        && dfllRfndfrfr != null) {
                    E vbluf = listModfl.gftElfmfntAt(indfx);
                    boolfbn isSflfdtfd = pbrfnt.isSflfdtfdIndfx(indfx);
                    boolfbn isFodussfd = pbrfnt.isFodusOwnfr()
                            && (indfx == pbrfnt.gftLfbdSflfdtionIndfx());
                    rfturn dfllRfndfrfr.gftListCfllRfndfrfrComponfnt(
                            pbrfnt,
                            vbluf,
                            indfx,
                            isSflfdtfd,
                            isFodussfd);
                } flsf {
                    rfturn null;
                }
            }


            // Addfssiblf Mftiods
           /**
            * Gft tif AddfssiblfContfxt for tiis objfdt. In tif
            * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
            * rfturns tiis objfdt, wiidi is its own AddfssiblfContfxt.
            *
            * @rfturn tiis objfdt
            */
            publid AddfssiblfContfxt gftAddfssiblfContfxt() {
                rfturn tiis;
            }


            // AddfssiblfContfxt mftiods

            publid String gftAddfssiblfNbmf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfNbmf();
                } flsf {
                    rfturn null;
                }
            }

            publid void sftAddfssiblfNbmf(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfNbmf(s);
                }
            }

            publid String gftAddfssiblfDfsdription() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfDfsdription();
                } flsf {
                    rfturn null;
                }
            }

            publid void sftAddfssiblfDfsdription(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfDfsdription(s);
                }
            }

            publid AddfssiblfRolf gftAddfssiblfRolf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfRolf();
                } flsf {
                    rfturn null;
                }
            }

            publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                AddfssiblfStbtfSft s;
                if (bd != null) {
                    s = bd.gftAddfssiblfStbtfSft();
                } flsf {
                    s = nfw AddfssiblfStbtfSft();
                }

                s.bdd(AddfssiblfStbtf.SELECTABLE);
                if (pbrfnt.isFodusOwnfr()
                    && (indfxInPbrfnt == pbrfnt.gftLfbdSflfdtionIndfx())) {
                    s.bdd(AddfssiblfStbtf.ACTIVE);
                }
                if (pbrfnt.isSflfdtfdIndfx(indfxInPbrfnt)) {
                    s.bdd(AddfssiblfStbtf.SELECTED);
                }
                if (tiis.isSiowing()) {
                    s.bdd(AddfssiblfStbtf.SHOWING);
                } flsf if (s.dontbins(AddfssiblfStbtf.SHOWING)) {
                    s.rfmovf(AddfssiblfStbtf.SHOWING);
                }
                if (tiis.isVisiblf()) {
                    s.bdd(AddfssiblfStbtf.VISIBLE);
                } flsf if (s.dontbins(AddfssiblfStbtf.VISIBLE)) {
                    s.rfmovf(AddfssiblfStbtf.VISIBLE);
                }
                s.bdd(AddfssiblfStbtf.TRANSIENT); // dfll-rfndfrfd
                rfturn s;
            }

            publid int gftAddfssiblfIndfxInPbrfnt() {
                rfturn indfxInPbrfnt;
            }

            publid int gftAddfssiblfCiildrfnCount() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfCiildrfnCount();
                } flsf {
                    rfturn 0;
                }
            }

            publid Addfssiblf gftAddfssiblfCiild(int i) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    Addfssiblf bddfssiblfCiild = bd.gftAddfssiblfCiild(i);
                    bd.sftAddfssiblfPbrfnt(tiis);
                    rfturn bddfssiblfCiild;
                } flsf {
                    rfturn null;
                }
            }

            publid Lodblf gftLodblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftLodblf();
                } flsf {
                    rfturn null;
                }
            }

            publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.bddPropfrtyCibngfListfnfr(l);
                }
            }

            publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.rfmovfPropfrtyCibngfListfnfr(l);
                }
            }

            publid AddfssiblfAdtion gftAddfssiblfAdtion() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfAdtion();
            }

           /**
            * Gft tif AddfssiblfComponfnt bssodibtfd witi tiis objfdt.  In tif
            * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
            * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
            * AddfssiblfComponfnt intfrfbdf on bfiblf of itsflf.
            *
            * @rfturn tiis objfdt
            */
            publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
                rfturn tiis; // to ovfrridf gftBounds()
            }

            publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfSflfdtion();
            }

            publid AddfssiblfTfxt gftAddfssiblfTfxt() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfTfxt();
            }

            publid AddfssiblfVbluf gftAddfssiblfVbluf() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfVbluf();
            }


            // AddfssiblfComponfnt mftiods

            publid Color gftBbdkground() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftBbdkground();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftBbdkground();
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid void sftBbdkground(Color d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftBbdkground(d);
                } flsf {
                    Componfnt dp = gftCurrfntComponfnt();
                    if (dp != null) {
                        dp.sftBbdkground(d);
                    }
                }
            }

            publid Color gftForfground() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftForfground();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftForfground();
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid void sftForfground(Color d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftForfground(d);
                } flsf {
                    Componfnt dp = gftCurrfntComponfnt();
                    if (dp != null) {
                        dp.sftForfground(d);
                    }
                }
            }

            publid Cursor gftCursor() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftCursor();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftCursor();
                    } flsf {
                        Addfssiblf bp = gftAddfssiblfPbrfnt();
                        if (bp instbndfof AddfssiblfComponfnt) {
                            rfturn ((AddfssiblfComponfnt) bp).gftCursor();
                        } flsf {
                            rfturn null;
                        }
                    }
                }
            }

            publid void sftCursor(Cursor d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftCursor(d);
                } flsf {
                    Componfnt dp = gftCurrfntComponfnt();
                    if (dp != null) {
                        dp.sftCursor(d);
                    }
                }
            }

            publid Font gftFont() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftFont();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftFont();
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid void sftFont(Font f) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftFont(f);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftFont(f);
                    }
                }
            }

            publid FontMftrids gftFontMftrids(Font f) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftFontMftrids(f);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.gftFontMftrids(f);
                    } flsf {
                        rfturn null;
                    }
                }
            }

            publid boolfbn isEnbblfd() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).isEnbblfd();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isEnbblfd();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            publid void sftEnbblfd(boolfbn b) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftEnbblfd(b);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftEnbblfd(b);
                    }
                }
            }

            publid boolfbn isVisiblf() {
                int fi = pbrfnt.gftFirstVisiblfIndfx();
                int li = pbrfnt.gftLbstVisiblfIndfx();
                // Tif UI indorrfdtly rfturns b -1 for tif lbst
                // visiblf indfx if tif list is smbllfr tibn tif
                // vifwport sizf.
                if (li == -1) {
                    li = pbrfnt.gftModfl().gftSizf() - 1;
                }
                rfturn ((indfxInPbrfnt >= fi)
                        && (indfxInPbrfnt <= li));
            }

            publid void sftVisiblf(boolfbn b) {
            }

            publid boolfbn isSiowing() {
                rfturn (pbrfnt.isSiowing() && isVisiblf());
            }

            publid boolfbn dontbins(Point p) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    Rfdtbnglf r = ((AddfssiblfComponfnt) bd).gftBounds();
                    rfturn r.dontbins(p);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        Rfdtbnglf r = d.gftBounds();
                        rfturn r.dontbins(p);
                    } flsf {
                        rfturn gftBounds().dontbins(p);
                    }
                }
            }

            publid Point gftLodbtionOnSdrffn() {
                if (pbrfnt != null) {
                    Point listLodbtion = pbrfnt.gftLodbtionOnSdrffn();
                    Point domponfntLodbtion = pbrfnt.indfxToLodbtion(indfxInPbrfnt);
                    if (domponfntLodbtion != null) {
                        domponfntLodbtion.trbnslbtf(listLodbtion.x, listLodbtion.y);
                        rfturn domponfntLodbtion;
                    } flsf {
                        rfturn null;
                    }
                } flsf {
                    rfturn null;
                }
            }

            publid Point gftLodbtion() {
                if (pbrfnt != null) {
                    rfturn pbrfnt.indfxToLodbtion(indfxInPbrfnt);
                } flsf {
                    rfturn null;
                }
            }

            publid void sftLodbtion(Point p) {
                if ((pbrfnt != null)  && (pbrfnt.dontbins(p))) {
                    fnsurfIndfxIsVisiblf(indfxInPbrfnt);
                }
            }

            publid Rfdtbnglf gftBounds() {
                if (pbrfnt != null) {
                    rfturn pbrfnt.gftCfllBounds(indfxInPbrfnt,indfxInPbrfnt);
                } flsf {
                    rfturn null;
                }
            }

            publid void sftBounds(Rfdtbnglf r) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftBounds(r);
                }
            }

            publid Dimfnsion gftSizf() {
                Rfdtbnglf dfllBounds = tiis.gftBounds();
                if (dfllBounds != null) {
                    rfturn dfllBounds.gftSizf();
                } flsf {
                    rfturn null;
                }
            }

            publid void sftSizf (Dimfnsion d) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftSizf(d);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftSizf(d);
                    }
                }
            }

            publid Addfssiblf gftAddfssiblfAt(Point p) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftAddfssiblfAt(p);
                } flsf {
                    rfturn null;
                }
            }

            publid boolfbn isFodusTrbvfrsbblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).isFodusTrbvfrsbblf();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isFodusTrbvfrsbblf();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            publid void rfqufstFodus() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).rfqufstFodus();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.rfqufstFodus();
                    }
                }
            }

            publid void bddFodusListfnfr(FodusListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).bddFodusListfnfr(l);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.bddFodusListfnfr(l);
                    }
                }
            }

            publid void rfmovfFodusListfnfr(FodusListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).rfmovfFodusListfnfr(l);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.rfmovfFodusListfnfr(l);
                    }
                }
            }

            // TIGER - 4733624
            /**
             * Rfturns tif idon for tif flfmfnt rfndfrfr, bs tif only itfm
             * of bn brrby of <dodf>AddfssiblfIdon</dodf>s or b <dodf>null</dodf> brrby
             * if tif rfndfrfr domponfnt dontbins no idons.
             *
             * @rfturn bn brrby dontbining tif bddfssiblf idon
             *         or b <dodf>null</dodf> brrby if nonf
             * @sindf 1.3
             */
            publid AddfssiblfIdon [] gftAddfssiblfIdon() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfIdon();
                } flsf {
                    rfturn null;
                }
            }
        } // innfr dlbss AddfssiblfJListCiild
    } // innfr dlbss AddfssiblfJList
}
