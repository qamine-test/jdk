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

import jbvb.util.*;

import jbvb.bpplft.Applft;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.print.*;

import jbvb.bfbns.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.bddfssibility.*;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tbblf.*;
import jbvbx.swing.bordfr.*;

import jbvb.tfxt.NumbfrFormbt;
import jbvb.tfxt.DbtfFormbt;
import jbvb.tfxt.MfssbgfFormbt;

import jbvbx.print.bttributf.*;
import jbvbx.print.PrintSfrvidf;
import sun.rfflfdt.misd.RfflfdtUtil;

import sun.swing.SwingUtilitifs2;
import sun.swing.SwingUtilitifs2.Sfdtion;
import stbtid sun.swing.SwingUtilitifs2.Sfdtion.*;
import sun.swing.PrintingStbtus;

/**
 * Tif <dodf>JTbblf</dodf> is usfd to displby bnd fdit rfgulbr two-dimfnsionbl tbblfs
 * of dflls.
 * Sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tbblf.itml">How to Usf Tbblfs</b>
 * in <fm>Tif Jbvb Tutoribl</fm>
 * for tbsk-orifntfd dodumfntbtion bnd fxbmplfs of using <dodf>JTbblf</dodf>.
 *
 * <p>
 * Tif <dodf>JTbblf</dodf> ibs mbny
 * fbdilitifs tibt mbkf it possiblf to dustomizf its rfndfring bnd fditing
 * but providfs dffbults for tifsf ffbturfs so tibt simplf tbblfs dbn bf
 * sft up fbsily.  For fxbmplf, to sft up b tbblf witi 10 rows bnd 10
 * dolumns of numbfrs:
 *
 * <prf>
 *      TbblfModfl dbtbModfl = nfw AbstrbdtTbblfModfl() {
 *          publid int gftColumnCount() { rfturn 10; }
 *          publid int gftRowCount() { rfturn 10;}
 *          publid Objfdt gftVblufAt(int row, int dol) { rfturn nfw Intfgfr(row*dol); }
 *      };
 *      JTbblf tbblf = nfw JTbblf(dbtbModfl);
 *      JSdrollPbnf sdrollpbnf = nfw JSdrollPbnf(tbblf);
 * </prf>
 * <p>
 * {@dodf JTbblf}s brf typidblly plbdfd insidf of b {@dodf JSdrollPbnf}.  By
 * dffbult, b {@dodf JTbblf} will bdjust its widti sudi tibt
 * b iorizontbl sdrollbbr is unnfdfssbry.  To bllow for b iorizontbl sdrollbbr,
 * invokf {@link #sftAutoRfsizfModf} witi {@dodf AUTO_RESIZE_OFF}.
 * Notf tibt if you wisi to usf b <dodf>JTbblf</dodf> in b stbndblonf
 * vifw (outsidf of b <dodf>JSdrollPbnf</dodf>) bnd wbnt tif ifbdfr
 * displbyfd, you dbn gft it using {@link #gftTbblfHfbdfr} bnd
 * displby it sfpbrbtfly.
 * <p>
 * To fnbblf sorting bnd filtfring of rows, usf b
 * {@dodf RowSortfr}.
 * You dbn sft up b row sortfr in fitifr of two wbys:
 * <ul>
 *   <li>Dirfdtly sft tif {@dodf RowSortfr}. For fxbmplf:
 *        {@dodf tbblf.sftRowSortfr(nfw TbblfRowSortfr(modfl))}.
 *   <li>Sft tif {@dodf butoCrfbtfRowSortfr}
 *       propfrty to {@dodf truf}, so tibt tif {@dodf JTbblf}
 *       drfbtfs b {@dodf RowSortfr} for
 *       you. For fxbmplf: {@dodf sftAutoCrfbtfRowSortfr(truf)}.
 * </ul>
 * <p>
 * Wifn dfsigning bpplidbtions tibt usf tif <dodf>JTbblf</dodf> it is worti pbying
 * dlosf bttfntion to tif dbtb strudturfs tibt will rfprfsfnt tif tbblf's dbtb.
 * Tif <dodf>DffbultTbblfModfl</dodf> is b modfl implfmfntbtion tibt
 * usfs b <dodf>Vfdtor</dodf> of <dodf>Vfdtor</dodf>s of <dodf>Objfdt</dodf>s to
 * storf tif dfll vblufs. As wfll bs dopying tif dbtb from bn
 * bpplidbtion into tif <dodf>DffbultTbblfModfl</dodf>,
 * it is blso possiblf to wrbp tif dbtb in tif mftiods of tif
 * <dodf>TbblfModfl</dodf> intfrfbdf so tibt tif dbtb dbn bf pbssfd to tif
 * <dodf>JTbblf</dodf> dirfdtly, bs in tif fxbmplf bbovf. Tiis oftfn rfsults
 * in morf fffidifnt bpplidbtions bfdbusf tif modfl is frff to dioosf tif
 * intfrnbl rfprfsfntbtion tibt bfst suits tif dbtb.
 * A good rulf of tiumb for dfdiding wiftifr to usf tif <dodf>AbstrbdtTbblfModfl</dodf>
 * or tif <dodf>DffbultTbblfModfl</dodf> is to usf tif <dodf>AbstrbdtTbblfModfl</dodf>
 * bs tif bbsf dlbss for drfbting subdlbssfs bnd tif <dodf>DffbultTbblfModfl</dodf>
 * wifn subdlbssing is not rfquirfd.
 * <p>
 * Tif "TbblfExbmplf" dirfdtory in tif dfmo brfb of tif sourdf distribution
 * givfs b numbfr of domplftf fxbmplfs of <dodf>JTbblf</dodf> usbgf,
 * dovfring iow tif <dodf>JTbblf</dodf> dbn bf usfd to providf bn
 * fditbblf vifw of dbtb tbkfn from b dbtbbbsf bnd iow to modify
 * tif dolumns in tif displby to usf spfdiblizfd rfndfrfrs bnd fditors.
 * <p>
 * Tif <dodf>JTbblf</dodf> usfs intfgfrs fxdlusivfly to rfffr to boti tif rows bnd tif dolumns
 * of tif modfl tibt it displbys. Tif <dodf>JTbblf</dodf> simply tbkfs b tbbulbr rbngf of dflls
 * bnd usfs <dodf>gftVblufAt(int, int)</dodf> to rftrifvf tif
 * vblufs from tif modfl during pbinting.  It is importbnt to rfmfmbfr tibt
 * tif dolumn bnd row indfxfs rfturnfd by vbrious <dodf>JTbblf</dodf> mftiods
 * brf in tfrms of tif <dodf>JTbblf</dodf> (tif vifw) bnd brf not
 * nfdfssbrily tif sbmf indfxfs usfd by tif modfl.
 * <p>
 * By dffbult, dolumns mby bf rfbrrbngfd in tif <dodf>JTbblf</dodf> so tibt tif
 * vifw's dolumns bppfbr in b difffrfnt ordfr to tif dolumns in tif modfl.
 * Tiis dofs not bfffdt tif implfmfntbtion of tif modfl bt bll: wifn tif
 * dolumns brf rfordfrfd, tif <dodf>JTbblf</dodf> mbintbins tif nfw ordfr of tif dolumns
 * intfrnblly bnd donvfrts its dolumn indidfs bfforf qufrying tif modfl.
 * <p>
 * So, wifn writing b <dodf>TbblfModfl</dodf>, it is not nfdfssbry to listfn for dolumn
 * rfordfring fvfnts bs tif modfl will bf qufrifd in its own doordinbtf
 * systfm rfgbrdlfss of wibt is ibppfning in tif vifw.
 * In tif fxbmplfs brfb tifrf is b dfmonstrbtion of b sorting blgoritim mbking
 * usf of fxbdtly tiis tfdiniquf to intfrposf yft bnotifr doordinbtf systfm
 * wifrf tif ordfr of tif rows is dibngfd, rbtifr tibn tif ordfr of tif dolumns.
 * <p>
 * Similbrly wifn using tif sorting bnd filtfring fundtionblity
 * providfd by <dodf>RowSortfr</dodf> tif undfrlying
 * <dodf>TbblfModfl</dodf> dofs not nffd to know iow to do sorting,
 * rbtifr <dodf>RowSortfr</dodf> will ibndlf it.  Coordinbtf
 * donvfrsions will bf nfdfssbry wifn using tif row bbsfd mftiods of
 * <dodf>JTbblf</dodf> witi tif undfrlying <dodf>TbblfModfl</dodf>.
 * All of <dodf>JTbblf</dodf>s row bbsfd mftiods brf in tfrms of tif
 * <dodf>RowSortfr</dodf>, wiidi is not nfdfssbrily tif sbmf bs tibt
 * of tif undfrlying <dodf>TbblfModfl</dodf>.  For fxbmplf, tif
 * sflfdtion is blwbys in tfrms of <dodf>JTbblf</dodf> so tibt wifn
 * using <dodf>RowSortfr</dodf> you will nffd to donvfrt using
 * <dodf>donvfrtRowIndfxToVifw</dodf> or
 * <dodf>donvfrtRowIndfxToModfl</dodf>.  Tif following siows iow to
 * donvfrt doordinbtfs from <dodf>JTbblf</dodf> to tibt of tif
 * undfrlying modfl:
 * <prf>
 *   int[] sflfdtion = tbblf.gftSflfdtfdRows();
 *   for (int i = 0; i &lt; sflfdtion.lfngti; i++) {
 *     sflfdtion[i] = tbblf.donvfrtRowIndfxToModfl(sflfdtion[i]);
 *   }
 *   // sflfdtion is now in tfrms of tif undfrlying TbblfModfl
 * </prf>
 * <p>
 * By dffbult if sorting is fnbblfd <dodf>JTbblf</dodf> will pfrsist tif
 * sflfdtion bnd vbribblf row ifigits in tfrms of tif modfl on
 * sorting.  For fxbmplf if row 0, in tfrms of tif undfrlying modfl,
 * is durrfntly sflfdtfd, bftfr tif sort row 0, in tfrms of tif
 * undfrlying modfl will bf sflfdtfd.  Visublly tif sflfdtion mby
 * dibngf, but in tfrms of tif undfrlying modfl it will rfmbin tif
 * sbmf.  Tif onf fxdfption to tibt is if tif modfl indfx is no longfr
 * visiblf or wbs rfmovfd.  For fxbmplf, if row 0 in tfrms of modfl
 * wbs filtfrfd out tif sflfdtion will bf fmpty bftfr tif sort.
 * <p>
 * J2SE 5 bdds mftiods to <dodf>JTbblf</dodf> to providf donvfnifnt bddfss to somf
 * dommon printing nffds. Simplf nfw {@link #print()} mftiods bllow for quidk
 * bnd fbsy bddition of printing support to your bpplidbtion. In bddition, b nfw
 * {@link #gftPrintbblf} mftiod is bvbilbblf for morf bdvbndfd printing nffds.
 * <p>
 * As for bll <dodf>JComponfnt</dodf> dlbssfs, you dbn usf
 * {@link InputMbp} bnd {@link AdtionMbp} to bssodibtf bn
 * {@link Adtion} objfdt witi b {@link KfyStrokf} bnd fxfdutf tif
 * bdtion undfr spfdififd donditions.
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
 *
 *
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: A domponfnt wiidi displbys dbtb in b two dimfnsionbl grid.
 *
 * @butior Piilip Milnf
 * @butior Sibnnon Hidkfy (printing support)
 * @sff jbvbx.swing.tbblf.DffbultTbblfModfl
 * @sff jbvbx.swing.tbblf.TbblfRowSortfr
 * @sindf 1.2
 */
/* Tif first vfrsions of tif JTbblf, dontbinfd in Swing-0.1 tirougi
 * Swing-0.4, wfrf writtfn by Albn Ciung.
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JTbblf fxtfnds JComponfnt implfmfnts TbblfModflListfnfr, Sdrollbblf,
    TbblfColumnModflListfnfr, ListSflfdtionListfnfr, CfllEditorListfnfr,
    Addfssiblf, RowSortfrListfnfr
{
//
// Stbtid Constbnts
//

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "TbblfUI";

    /** Do not bdjust dolumn widtis butombtidblly; usf b iorizontbl sdrollbbr instfbd. */
    publid stbtid finbl int     AUTO_RESIZE_OFF = 0;

    /** Wifn b dolumn is bdjustfd in tif UI, bdjust tif nfxt dolumn tif oppositf wby. */
    publid stbtid finbl int     AUTO_RESIZE_NEXT_COLUMN = 1;

    /** During UI bdjustmfnt, dibngf subsfqufnt dolumns to prfsfrvf tif totbl widti;
      * tiis is tif dffbult bfibvior. */
    publid stbtid finbl int     AUTO_RESIZE_SUBSEQUENT_COLUMNS = 2;

    /** During bll rfsizf opfrbtions, bpply bdjustmfnts to tif lbst dolumn only. */
    publid stbtid finbl int     AUTO_RESIZE_LAST_COLUMN = 3;

    /** During bll rfsizf opfrbtions, proportionbtfly rfsizf bll dolumns. */
    publid stbtid finbl int     AUTO_RESIZE_ALL_COLUMNS = 4;


    /**
     * Printing modfs, usfd in printing <dodf>JTbblf</dodf>s.
     *
     * @sff #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     *             boolfbn, PrintRfqufstAttributfSft, boolfbn)
     * @sff #gftPrintbblf
     * @sindf 1.5
     */
    publid fnum PrintModf {

        /**
         * Printing modf tibt prints tif tbblf bt its durrfnt sizf,
         * sprfbding boti dolumns bnd rows bdross multiplf pbgfs if nfdfssbry.
         */
        NORMAL,

        /**
         * Printing modf tibt sdblfs tif output smbllfr, if nfdfssbry,
         * to fit tif tbblf's fntirf widti (bnd tifrfby bll dolumns) on fbdi pbgf;
         * Rows brf sprfbd bdross multiplf pbgfs bs nfdfssbry.
         */
        FIT_WIDTH
    }


//
// Instbndf Vbribblfs
//

    /** Tif <dodf>TbblfModfl</dodf> of tif tbblf. */
    protfdtfd TbblfModfl        dbtbModfl;

    /** Tif <dodf>TbblfColumnModfl</dodf> of tif tbblf. */
    protfdtfd TbblfColumnModfl  dolumnModfl;

    /** Tif <dodf>ListSflfdtionModfl</dodf> of tif tbblf, usfd to kffp trbdk of row sflfdtions. */
    protfdtfd ListSflfdtionModfl sflfdtionModfl;

    /** Tif <dodf>TbblfHfbdfr</dodf> working witi tif tbblf. */
    protfdtfd JTbblfHfbdfr      tbblfHfbdfr;

    /** Tif ifigit in pixfls of fbdi row in tif tbblf. */
    protfdtfd int               rowHfigit;

    /** Tif ifigit in pixfls of tif mbrgin bftwffn tif dflls in fbdi row. */
    protfdtfd int               rowMbrgin;

    /** Tif dolor of tif grid. */
    protfdtfd Color             gridColor;

    /** Tif tbblf drbws iorizontbl linfs bftwffn dflls if <dodf>siowHorizontblLinfs</dodf> is truf. */
    protfdtfd boolfbn           siowHorizontblLinfs;

    /** Tif tbblf drbws vfrtidbl linfs bftwffn dflls if <dodf>siowVfrtidblLinfs</dodf> is truf. */
    protfdtfd boolfbn           siowVfrtidblLinfs;

    /**
     *  Dftfrminfs if tif tbblf butombtidblly rfsizfs tif
     *  widti of tif tbblf's dolumns to tbkf up tif fntirf widti of tif
     *  tbblf, bnd iow it dofs tif rfsizing.
     */
    protfdtfd int               butoRfsizfModf;

    /**
     *  Tif tbblf will qufry tif <dodf>TbblfModfl</dodf> to build tif dffbult
     *  sft of dolumns if tiis is truf.
     */
    protfdtfd boolfbn           butoCrfbtfColumnsFromModfl;

    /** Usfd by tif <dodf>Sdrollbblf</dodf> intfrfbdf to dftfrminf tif initibl visiblf brfb. */
    protfdtfd Dimfnsion         prfffrrfdVifwportSizf;

    /** Truf if row sflfdtion is bllowfd in tiis tbblf. */
    protfdtfd boolfbn           rowSflfdtionAllowfd;

    /**
     * Obsolftf bs of Jbvb 2 plbtform v1.3.  Plfbsf usf tif
     * <dodf>rowSflfdtionAllowfd</dodf> propfrty bnd tif
     * <dodf>dolumnSflfdtionAllowfd</dodf> propfrty of tif
     * <dodf>dolumnModfl</dodf> instfbd. Or usf tif
     * mftiod <dodf>gftCfllSflfdtionEnbblfd</dodf>.
     */
    /*
     * If truf, boti b row sflfdtion bnd b dolumn sflfdtion
     * dbn bf non-fmpty bt tif sbmf timf, tif sflfdtfd dflls brf tif
     * tif dflls wiosf row bnd dolumn brf boti sflfdtfd.
     */
    protfdtfd boolfbn           dfllSflfdtionEnbblfd;

    /** If fditing, tif <dodf>Componfnt</dodf> tibt is ibndling tif fditing. */
    trbnsifnt protfdtfd Componfnt       fditorComp;

    /**
     * Tif bdtivf dfll fditor objfdt, tibt ovfrwritfs tif sdrffn rfbl fstbtf
     * oddupifd by tif durrfnt dfll bnd bllows tif usfr to dibngf its dontfnts.
     * {@dodf null} if tif tbblf isn't durrfntly fditing.
     */
    trbnsifnt protfdtfd TbblfCfllEditor dfllEditor;

    /** Idfntififs tif dolumn of tif dfll bfing fditfd. */
    trbnsifnt protfdtfd int             fditingColumn;

    /** Idfntififs tif row of tif dfll bfing fditfd. */
    trbnsifnt protfdtfd int             fditingRow;

   /**
     * A tbblf of objfdts tibt displby tif dontfnts of b dfll,
     * indfxfd by dlbss bs dfdlbrfd in <dodf>gftColumnClbss</dodf>
     * in tif <dodf>TbblfModfl</dodf> intfrfbdf.
     */
    trbnsifnt protfdtfd Hbsitbblf<Objfdt, Objfdt> dffbultRfndfrfrsByColumnClbss;
    // Logidbly, tif bbovf is b Hbsitbblf<Clbss<?>, TbblfCfllRfndfrfr>.
    // It is dfdlbrfd otifrwisf to bddomodbtf using UIDffbults.

    /**
     * A tbblf of objfdts tibt displby bnd fdit tif dontfnts of b dfll,
     * indfxfd by dlbss bs dfdlbrfd in <dodf>gftColumnClbss</dodf>
     * in tif <dodf>TbblfModfl</dodf> intfrfbdf.
     */
    trbnsifnt protfdtfd Hbsitbblf<Objfdt, Objfdt> dffbultEditorsByColumnClbss;
    // Logidbly, tif bbovf is b Hbsitbblf<Clbss<?>, TbblfCfllEditor>.
    // It is dfdlbrfd otifrwisf to bddomodbtf using UIDffbults.

    /** Tif forfground dolor of sflfdtfd dflls. */
    protfdtfd Color sflfdtionForfground;

    /** Tif bbdkground dolor of sflfdtfd dflls. */
    protfdtfd Color sflfdtionBbdkground;

//
// Privbtf stbtf
//

    // WARNING: If you dirfdtly bddfss tiis fifld you siould blso dibngf tif
    // SortMbnbgfr.modflRowSizfs fifld bs wfll.
    privbtf SizfSfqufndf rowModfl;
    privbtf boolfbn drbgEnbblfd;
    privbtf boolfbn surrfndfrsFodusOnKfystrokf;
    privbtf PropfrtyCibngfListfnfr fditorRfmovfr = null;
    /**
     * Tif lbst vbluf of gftVblufIsAdjusting from tif dolumn sflfdtion modfls
     * dolumnSflfdtionCibngfd notifidbtion. Usfd to tfst if b rfpbint is
     * nffdfd.
     */
    privbtf boolfbn dolumnSflfdtionAdjusting;
    /**
     * Tif lbst vbluf of gftVblufIsAdjusting from tif row sflfdtion modfls
     * vblufCibngfd notifidbtion. Usfd to tfst if b rfpbint is nffdfd.
     */
    privbtf boolfbn rowSflfdtionAdjusting;

    /**
     * To dommunidbtf frrors bftwffn tirfbds during printing.
     */
    privbtf Tirowbblf printError;

    /**
     * Truf wifn sftRowHfigit(int) ibs bffn invokfd.
     */
    privbtf boolfbn isRowHfigitSft;

    /**
     * If truf, on b sort tif sflfdtion is rfsft.
     */
    privbtf boolfbn updbtfSflfdtionOnSort;

    /**
     * Informbtion usfd in sorting.
     */
    privbtf trbnsifnt SortMbnbgfr sortMbnbgfr;

    /**
     * If truf, wifn sortfrCibngfd is invokfd it's vbluf is ignorfd.
     */
    privbtf boolfbn ignorfSortCibngf;

    /**
     * Wiftifr or not sortfrCibngfd ibs bffn invokfd.
     */
    privbtf boolfbn sortfrCibngfd;

    /**
     * If truf, bny timf tif modfl dibngfs b nfw RowSortfr is sft.
     */
    privbtf boolfbn butoCrfbtfRowSortfr;

    /**
     * Wiftifr or not tif tbblf blwbys fills tif vifwport ifigit.
     * @sff #sftFillsVifwportHfigit
     * @sff #gftSdrollbblfTrbdksVifwportHfigit
     */
    privbtf boolfbn fillsVifwportHfigit;

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
     * b drop lodbtion for b <dodf>JTbblf</dodf>.
     *
     * @sff #gftDropLodbtion
     * @sindf 1.6
     */
    publid stbtid finbl dlbss DropLodbtion fxtfnds TrbnsffrHbndlfr.DropLodbtion {
        privbtf finbl int row;
        privbtf finbl int dol;
        privbtf finbl boolfbn isInsfrtRow;
        privbtf finbl boolfbn isInsfrtCol;

        privbtf DropLodbtion(Point p, int row, int dol,
                             boolfbn isInsfrtRow, boolfbn isInsfrtCol) {

            supfr(p);
            tiis.row = row;
            tiis.dol = dol;
            tiis.isInsfrtRow = isInsfrtRow;
            tiis.isInsfrtCol = isInsfrtCol;
        }

        /**
         * Rfturns tif row indfx wifrf b droppfd itfm siould bf plbdfd in tif
         * tbblf. Intfrprftbtion of tif vbluf dfpfnds on tif rfturn of
         * <dodf>isInsfrtRow()</dodf>. If tibt mftiod rfturns
         * <dodf>truf</dodf> tiis vbluf indidbtfs tif indfx wifrf b nfw
         * row siould bf insfrtfd. Otifrwisf, it rfprfsfnts tif vbluf
         * of bn fxisting row on wiidi tif dbtb wbs droppfd. Tiis indfx is
         * in tfrms of tif vifw.
         * <p>
         * <dodf>-1</dodf> indidbtfs tibt tif drop oddurrfd ovfr fmpty spbdf,
         * bnd no row dould bf dbldulbtfd.
         *
         * @rfturn tif drop row
         */
        publid int gftRow() {
            rfturn row;
        }

        /**
         * Rfturns tif dolumn indfx wifrf b droppfd itfm siould bf plbdfd in tif
         * tbblf. Intfrprftbtion of tif vbluf dfpfnds on tif rfturn of
         * <dodf>isInsfrtColumn()</dodf>. If tibt mftiod rfturns
         * <dodf>truf</dodf> tiis vbluf indidbtfs tif indfx wifrf b nfw
         * dolumn siould bf insfrtfd. Otifrwisf, it rfprfsfnts tif vbluf
         * of bn fxisting dolumn on wiidi tif dbtb wbs droppfd. Tiis indfx is
         * in tfrms of tif vifw.
         * <p>
         * <dodf>-1</dodf> indidbtfs tibt tif drop oddurrfd ovfr fmpty spbdf,
         * bnd no dolumn dould bf dbldulbtfd.
         *
         * @rfturn tif drop row
         */
        publid int gftColumn() {
            rfturn dol;
        }

        /**
         * Rfturns wiftifr or not tiis lodbtion rfprfsfnts bn insfrt
         * of b row.
         *
         * @rfturn wiftifr or not tiis is bn insfrt row
         */
        publid boolfbn isInsfrtRow() {
            rfturn isInsfrtRow;
        }

        /**
         * Rfturns wiftifr or not tiis lodbtion rfprfsfnts bn insfrt
         * of b dolumn.
         *
         * @rfturn wiftifr or not tiis is bn insfrt dolumn
         */
        publid boolfbn isInsfrtColumn() {
            rfturn isInsfrtCol;
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
                   + "row=" + row + ","
                   + "dolumn=" + dol + ","
                   + "insfrtRow=" + isInsfrtRow + ","
                   + "insfrtColumn=" + isInsfrtCol + "]";
        }
    }

//
// Construdtors
//

    /**
     * Construdts b dffbult <dodf>JTbblf</dodf> tibt is initiblizfd witi b dffbult
     * dbtb modfl, b dffbult dolumn modfl, bnd b dffbult sflfdtion
     * modfl.
     *
     * @sff #drfbtfDffbultDbtbModfl
     * @sff #drfbtfDffbultColumnModfl
     * @sff #drfbtfDffbultSflfdtionModfl
     */
    publid JTbblf() {
        tiis(null, null, null);
    }

    /**
     * Construdts b <dodf>JTbblf</dodf> tibt is initiblizfd witi
     * <dodf>dm</dodf> bs tif dbtb modfl, b dffbult dolumn modfl,
     * bnd b dffbult sflfdtion modfl.
     *
     * @pbrbm dm        tif dbtb modfl for tif tbblf
     * @sff #drfbtfDffbultColumnModfl
     * @sff #drfbtfDffbultSflfdtionModfl
     */
    publid JTbblf(TbblfModfl dm) {
        tiis(dm, null, null);
    }

    /**
     * Construdts b <dodf>JTbblf</dodf> tibt is initiblizfd witi
     * <dodf>dm</dodf> bs tif dbtb modfl, <dodf>dm</dodf>
     * bs tif dolumn modfl, bnd b dffbult sflfdtion modfl.
     *
     * @pbrbm dm        tif dbtb modfl for tif tbblf
     * @pbrbm dm        tif dolumn modfl for tif tbblf
     * @sff #drfbtfDffbultSflfdtionModfl
     */
    publid JTbblf(TbblfModfl dm, TbblfColumnModfl dm) {
        tiis(dm, dm, null);
    }

    /**
     * Construdts b <dodf>JTbblf</dodf> tibt is initiblizfd witi
     * <dodf>dm</dodf> bs tif dbtb modfl, <dodf>dm</dodf> bs tif
     * dolumn modfl, bnd <dodf>sm</dodf> bs tif sflfdtion modfl.
     * If bny of tif pbrbmftfrs brf <dodf>null</dodf> tiis mftiod
     * will initiblizf tif tbblf witi tif dorrfsponding dffbult modfl.
     * Tif <dodf>butoCrfbtfColumnsFromModfl</dodf> flbg is sft to fblsf
     * if <dodf>dm</dodf> is non-null, otifrwisf it is sft to truf
     * bnd tif dolumn modfl is populbtfd witi suitbblf
     * <dodf>TbblfColumns</dodf> for tif dolumns in <dodf>dm</dodf>.
     *
     * @pbrbm dm        tif dbtb modfl for tif tbblf
     * @pbrbm dm        tif dolumn modfl for tif tbblf
     * @pbrbm sm        tif row sflfdtion modfl for tif tbblf
     * @sff #drfbtfDffbultDbtbModfl
     * @sff #drfbtfDffbultColumnModfl
     * @sff #drfbtfDffbultSflfdtionModfl
     */
    publid JTbblf(TbblfModfl dm, TbblfColumnModfl dm, ListSflfdtionModfl sm) {
        supfr();
        sftLbyout(null);

        sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.FORWARD_TRAVERSAL_KEYS,
                           JComponfnt.gftMbnbgingFodusForwbrdTrbvfrsblKfys());
        sftFodusTrbvfrsblKfys(KfybobrdFodusMbnbgfr.BACKWARD_TRAVERSAL_KEYS,
                           JComponfnt.gftMbnbgingFodusBbdkwbrdTrbvfrsblKfys());
        if (dm == null) {
            dm = drfbtfDffbultColumnModfl();
            butoCrfbtfColumnsFromModfl = truf;
        }
        sftColumnModfl(dm);

        if (sm == null) {
            sm = drfbtfDffbultSflfdtionModfl();
        }
        sftSflfdtionModfl(sm);

    // Sft tif modfl lbst, tibt wby if tif butoCrfbtColumnsFromModfl ibs
    // bffn sft bbovf, wf will butombtidblly populbtf bn fmpty dolumnModfl
    // witi suitbblf dolumns for tif nfw modfl.
        if (dm == null) {
            dm = drfbtfDffbultDbtbModfl();
        }
        sftModfl(dm);

        initiblizfLodblVbrs();
        updbtfUI();
    }

    /**
     * Construdts b <dodf>JTbblf</dodf> witi <dodf>numRows</dodf>
     * bnd <dodf>numColumns</dodf> of fmpty dflls using
     * <dodf>DffbultTbblfModfl</dodf>.  Tif dolumns will ibvf
     * nbmfs of tif form "A", "B", "C", ftd.
     *
     * @pbrbm numRows           tif numbfr of rows tif tbblf iolds
     * @pbrbm numColumns        tif numbfr of dolumns tif tbblf iolds
     * @sff jbvbx.swing.tbblf.DffbultTbblfModfl
     */
    publid JTbblf(int numRows, int numColumns) {
        tiis(nfw DffbultTbblfModfl(numRows, numColumns));
    }

    /**
     * Construdts b <dodf>JTbblf</dodf> to displby tif vblufs in tif
     * <dodf>Vfdtor</dodf> of <dodf>Vfdtors</dodf>, <dodf>rowDbtb</dodf>,
     * witi dolumn nbmfs, <dodf>dolumnNbmfs</dodf>.  Tif
     * <dodf>Vfdtors</dodf> dontbinfd in <dodf>rowDbtb</dodf>
     * siould dontbin tif vblufs for tibt row. In otifr words,
     * tif vbluf of tif dfll bt row 1, dolumn 5 dbn bf obtbinfd
     * witi tif following dodf:
     *
     * <prf>((Vfdtor)rowDbtb.flfmfntAt(1)).flfmfntAt(5);</prf>
     *
     * @pbrbm rowDbtb           tif dbtb for tif nfw tbblf
     * @pbrbm dolumnNbmfs       nbmfs of fbdi dolumn
     */
    publid JTbblf(Vfdtor<Vfdtor<Objfdt>> rowDbtb, Vfdtor<Objfdt> dolumnNbmfs) {
        tiis(nfw DffbultTbblfModfl(rowDbtb, dolumnNbmfs));
    }

    /**
     * Construdts b <dodf>JTbblf</dodf> to displby tif vblufs in tif two dimfnsionbl brrby,
     * <dodf>rowDbtb</dodf>, witi dolumn nbmfs, <dodf>dolumnNbmfs</dodf>.
     * <dodf>rowDbtb</dodf> is bn brrby of rows, so tif vbluf of tif dfll bt row 1,
     * dolumn 5 dbn bf obtbinfd witi tif following dodf:
     *
     * <prf> rowDbtb[1][5]; </prf>
     * <p>
     * All rows must bf of tif sbmf lfngti bs <dodf>dolumnNbmfs</dodf>.
     *
     * @pbrbm rowDbtb           tif dbtb for tif nfw tbblf
     * @pbrbm dolumnNbmfs       nbmfs of fbdi dolumn
     */
    publid JTbblf(finbl Objfdt[][] rowDbtb, finbl Objfdt[] dolumnNbmfs) {
        tiis(nfw AbstrbdtTbblfModfl() {
            publid String gftColumnNbmf(int dolumn) { rfturn dolumnNbmfs[dolumn].toString(); }
            publid int gftRowCount() { rfturn rowDbtb.lfngti; }
            publid int gftColumnCount() { rfturn dolumnNbmfs.lfngti; }
            publid Objfdt gftVblufAt(int row, int dol) { rfturn rowDbtb[row][dol]; }
            publid boolfbn isCfllEditbblf(int row, int dolumn) { rfturn truf; }
            publid void sftVblufAt(Objfdt vbluf, int row, int dol) {
                rowDbtb[row][dol] = vbluf;
                firfTbblfCfllUpdbtfd(row, dol);
            }
        });
    }

    /**
     * Cblls tif <dodf>donfigurfEndlosingSdrollPbnf</dodf> mftiod.
     *
     * @sff #donfigurfEndlosingSdrollPbnf
     */
    publid void bddNotify() {
        supfr.bddNotify();
        donfigurfEndlosingSdrollPbnf();
    }

    /**
     * If tiis <dodf>JTbblf</dodf> is tif <dodf>vifwportVifw</dodf> of bn fndlosing <dodf>JSdrollPbnf</dodf>
     * (tif usubl situbtion), donfigurf tiis <dodf>SdrollPbnf</dodf> by, bmongst otifr tiings,
     * instblling tif tbblf's <dodf>tbblfHfbdfr</dodf> bs tif <dodf>dolumnHfbdfrVifw</dodf> of tif sdroll pbnf.
     * Wifn b <dodf>JTbblf</dodf> is bddfd to b <dodf>JSdrollPbnf</dodf> in tif usubl wby,
     * using <dodf>nfw JSdrollPbnf(myTbblf)</dodf>, <dodf>bddNotify</dodf> is
     * dbllfd in tif <dodf>JTbblf</dodf> (wifn tif tbblf is bddfd to tif vifwport).
     * <dodf>JTbblf</dodf>'s <dodf>bddNotify</dodf> mftiod in turn dblls tiis mftiod,
     * wiidi is protfdtfd so tibt tiis dffbult instbllbtion prodfdurf dbn
     * bf ovfrriddfn by b subdlbss.
     *
     * @sff #bddNotify
     */
    protfdtfd void donfigurfEndlosingSdrollPbnf() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            JVifwport port = (JVifwport) pbrfnt;
            Contbinfr gp = port.gftPbrfnt();
            if (gp instbndfof JSdrollPbnf) {
                JSdrollPbnf sdrollPbnf = (JSdrollPbnf)gp;
                // Mbkf dfrtbin wf brf tif vifwPort's vifw bnd not, for
                // fxbmplf, tif rowHfbdfrVifw of tif sdrollPbnf -
                // bn implfmfntor of fixfd dolumns migit do tiis.
                JVifwport vifwport = sdrollPbnf.gftVifwport();
                if (vifwport == null ||
                        SwingUtilitifs.gftUnwrbppfdVifw(vifwport) != tiis) {
                    rfturn;
                }
                sdrollPbnf.sftColumnHfbdfrVifw(gftTbblfHfbdfr());
                // donfigurf tif sdrollpbnf for bny LAF dfpfndfnt sfttings
                donfigurfEndlosingSdrollPbnfUI();
            }
        }
    }

    /**
     * Tiis is b sub-pbrt of donfigurfEndlosingSdrollPbnf() tibt donfigurfs
     * bnytiing on tif sdrollpbnf tibt mby dibngf wifn tif look bnd fffl
     * dibngfs. It nffdfd to bf split out from donfigurfEndlosingSdrollPbnf() so
     * tibt it dbn bf dbllfd from updbtfUI() wifn tif LAF dibngfs witiout
     * dbusing tif rfgrfssion found in bug 6687962. Tiis wbs bfdbusf updbtfUI()
     * is dbllfd from tif donstrudtor wiidi tifn dbusfd
     * donfigurfEndlosingSdrollPbnf() to bf dbllfd by tif donstrudtor wiidi
     * dibngfs its dontrbdt for bny subdlbss tibt ovfrridfs it. So by splitting
     * it out in tiis wby donfigurfEndlosingSdrollPbnfUI() dbn bf dbllfd boti
     * from donfigurfEndlosingSdrollPbnf() bnd updbtfUI() in b sbff mbnor.
     */
    privbtf void donfigurfEndlosingSdrollPbnfUI() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            JVifwport port = (JVifwport) pbrfnt;
            Contbinfr gp = port.gftPbrfnt();
            if (gp instbndfof JSdrollPbnf) {
                JSdrollPbnf sdrollPbnf = (JSdrollPbnf)gp;
                // Mbkf dfrtbin wf brf tif vifwPort's vifw bnd not, for
                // fxbmplf, tif rowHfbdfrVifw of tif sdrollPbnf -
                // bn implfmfntor of fixfd dolumns migit do tiis.
                JVifwport vifwport = sdrollPbnf.gftVifwport();
                if (vifwport == null ||
                        SwingUtilitifs.gftUnwrbppfdVifw(vifwport) != tiis) {
                    rfturn;
                }
                //  sdrollPbnf.gftVifwport().sftBbdkingStorfEnbblfd(truf);
                Bordfr bordfr = sdrollPbnf.gftBordfr();
                if (bordfr == null || bordfr instbndfof UIRfsourdf) {
                    Bordfr sdrollPbnfBordfr =
                        UIMbnbgfr.gftBordfr("Tbblf.sdrollPbnfBordfr");
                    if (sdrollPbnfBordfr != null) {
                        sdrollPbnf.sftBordfr(sdrollPbnfBordfr);
                    }
                }
                // bdd JSdrollBbr dornfr domponfnt if bvbilbblf from LAF bnd not blrfbdy sft by tif usfr
                Componfnt dornfr =
                        sdrollPbnf.gftCornfr(JSdrollPbnf.UPPER_TRAILING_CORNER);
                if (dornfr == null || dornfr instbndfof UIRfsourdf){
                    dornfr = null;
                    try {
                        dornfr = (Componfnt) UIMbnbgfr.gft(
                                "Tbblf.sdrollPbnfCornfrComponfnt");
                    } dbtdi (Exdfption f) {
                        // just ignorf bnd don't sft dornfr
                    }
                    sdrollPbnf.sftCornfr(JSdrollPbnf.UPPER_TRAILING_CORNER,
                            dornfr);
                }
            }
        }
    }

    /**
     * Cblls tif <dodf>undonfigurfEndlosingSdrollPbnf</dodf> mftiod.
     *
     * @sff #undonfigurfEndlosingSdrollPbnf
     */
    publid void rfmovfNotify() {
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
            rfmovfPropfrtyCibngfListfnfr("pfrmbnfntFodusOwnfr", fditorRfmovfr);
        fditorRfmovfr = null;
        undonfigurfEndlosingSdrollPbnf();
        supfr.rfmovfNotify();
    }

    /**
     * Rfvfrsfs tif ffffdt of <dodf>donfigurfEndlosingSdrollPbnf</dodf>
     * by rfplbding tif <dodf>dolumnHfbdfrVifw</dodf> of tif fndlosing
     * sdroll pbnf witi <dodf>null</dodf>. <dodf>JTbblf</dodf>'s
     * <dodf>rfmovfNotify</dodf> mftiod dblls
     * tiis mftiod, wiidi is protfdtfd so tibt tiis dffbult uninstbllbtion
     * prodfdurf dbn bf ovfrriddfn by b subdlbss.
     *
     * @sff #rfmovfNotify
     * @sff #donfigurfEndlosingSdrollPbnf
     * @sindf 1.3
     */
    protfdtfd void undonfigurfEndlosingSdrollPbnf() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        if (pbrfnt instbndfof JVifwport) {
            JVifwport port = (JVifwport) pbrfnt;
            Contbinfr gp = port.gftPbrfnt();
            if (gp instbndfof JSdrollPbnf) {
                JSdrollPbnf sdrollPbnf = (JSdrollPbnf)gp;
                // Mbkf dfrtbin wf brf tif vifwPort's vifw bnd not, for
                // fxbmplf, tif rowHfbdfrVifw of tif sdrollPbnf -
                // bn implfmfntor of fixfd dolumns migit do tiis.
                JVifwport vifwport = sdrollPbnf.gftVifwport();
                if (vifwport == null ||
                        SwingUtilitifs.gftUnwrbppfdVifw(vifwport) != tiis) {
                    rfturn;
                }
                sdrollPbnf.sftColumnHfbdfrVifw(null);
                // rfmovf SdrollPbnf dornfr if onf wbs bddfd by tif LAF
                Componfnt dornfr =
                        sdrollPbnf.gftCornfr(JSdrollPbnf.UPPER_TRAILING_CORNER);
                if (dornfr instbndfof UIRfsourdf){
                    sdrollPbnf.sftCornfr(JSdrollPbnf.UPPER_TRAILING_CORNER,
                            null);
                }
            }
        }
    }

    void sftUIPropfrty(String propfrtyNbmf, Objfdt vbluf) {
        if (propfrtyNbmf == "rowHfigit") {
            if (!isRowHfigitSft) {
                sftRowHfigit(((Numbfr)vbluf).intVbluf());
                isRowHfigitSft = fblsf;
            }
            rfturn;
        }
        supfr.sftUIPropfrty(propfrtyNbmf, vbluf);
    }

//
// Stbtid Mftiods
//

    /**
     * Equivblfnt to <dodf>nfw JSdrollPbnf(bTbblf)</dodf>.
     *
     * @pbrbm bTbblf b {@dodf JTbblf} to bf usfd for tif sdroll pbnf
     * @rfturn b {@dodf JSdrollPbnf} drfbtfd using {@dodf bTbblf}
     * @dfprfdbtfd As of Swing vfrsion 1.0.2,
     * rfplbdfd by <dodf>nfw JSdrollPbnf(bTbblf)</dodf>.
     */
    @Dfprfdbtfd
    stbtid publid JSdrollPbnf drfbtfSdrollPbnfForTbblf(JTbblf bTbblf) {
        rfturn nfw JSdrollPbnf(bTbblf);
    }

//
// Tbblf Attributfs
//

    /**
     * Sfts tif <dodf>tbblfHfbdfr</dodf> working witi tiis <dodf>JTbblf</dodf> to <dodf>nfwHfbdfr</dodf>.
     * It is lfgbl to ibvf b <dodf>null</dodf> <dodf>tbblfHfbdfr</dodf>.
     *
     * @pbrbm   tbblfHfbdfr                       nfw tbblfHfbdfr
     * @sff     #gftTbblfHfbdfr
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif JTbblfHfbdfr instbndf wiidi rfndfrs tif dolumn ifbdfrs.
     */
    publid void sftTbblfHfbdfr(JTbblfHfbdfr tbblfHfbdfr) {
        if (tiis.tbblfHfbdfr != tbblfHfbdfr) {
            JTbblfHfbdfr old = tiis.tbblfHfbdfr;
            // Rflfbsf tif old ifbdfr
            if (old != null) {
                old.sftTbblf(null);
            }
            tiis.tbblfHfbdfr = tbblfHfbdfr;
            if (tbblfHfbdfr != null) {
                tbblfHfbdfr.sftTbblf(tiis);
            }
            firfPropfrtyCibngf("tbblfHfbdfr", old, tbblfHfbdfr);
        }
    }

    /**
     * Rfturns tif <dodf>tbblfHfbdfr</dodf> usfd by tiis <dodf>JTbblf</dodf>.
     *
     * @rfturn  tif <dodf>tbblfHfbdfr</dodf> usfd by tiis tbblf
     * @sff     #sftTbblfHfbdfr
     */
    publid JTbblfHfbdfr gftTbblfHfbdfr() {
        rfturn tbblfHfbdfr;
    }

    /**
     * Sfts tif ifigit, in pixfls, of bll dflls to <dodf>rowHfigit</dodf>,
     * rfvblidbtfs, bnd rfpbints.
     * Tif ifigit of tif dflls will bf fqubl to tif row ifigit minus
     * tif row mbrgin.
     *
     * @pbrbm   rowHfigit                       nfw row ifigit
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>rowHfigit</dodf> is
     *                                          lfss tibn 1
     * @sff     #gftRowHfigit
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif ifigit of tif spfdififd row.
     */
    publid void sftRowHfigit(int rowHfigit) {
        if (rowHfigit <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfw row ifigit lfss tibn 1");
        }
        int old = tiis.rowHfigit;
        tiis.rowHfigit = rowHfigit;
        rowModfl = null;
        if (sortMbnbgfr != null) {
            sortMbnbgfr.modflRowSizfs = null;
        }
        isRowHfigitSft = truf;
        rfsizfAndRfpbint();
        firfPropfrtyCibngf("rowHfigit", old, rowHfigit);
    }

    /**
     * Rfturns tif ifigit of b tbblf row, in pixfls.
     *
     * @rfturn  tif ifigit in pixfls of b tbblf row
     * @sff     #sftRowHfigit
     */
    publid int gftRowHfigit() {
        rfturn rowHfigit;
    }

    privbtf SizfSfqufndf gftRowModfl() {
        if (rowModfl == null) {
            rowModfl = nfw SizfSfqufndf(gftRowCount(), gftRowHfigit());
        }
        rfturn rowModfl;
    }

    /**
     * Sfts tif ifigit for <dodf>row</dodf> to <dodf>rowHfigit</dodf>,
     * rfvblidbtfs, bnd rfpbints. Tif ifigit of tif dflls in tiis row
     * will bf fqubl to tif row ifigit minus tif row mbrgin.
     *
     * @pbrbm   row                             tif row wiosf ifigit is bfing
                                                dibngfd
     * @pbrbm   rowHfigit                       nfw row ifigit, in pixfls
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>rowHfigit</dodf> is
     *                                          lfss tibn 1
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif ifigit in pixfls of tif dflls in <dodf>row</dodf>
     * @sindf 1.3
     */
    publid void sftRowHfigit(int row, int rowHfigit) {
        if (rowHfigit <= 0) {
            tirow nfw IllfgblArgumfntExdfption("Nfw row ifigit lfss tibn 1");
        }
        gftRowModfl().sftSizf(row, rowHfigit);
        if (sortMbnbgfr != null) {
            sortMbnbgfr.sftVifwRowHfigit(row, rowHfigit);
        }
        rfsizfAndRfpbint();
    }

    /**
     * Rfturns tif ifigit, in pixfls, of tif dflls in <dodf>row</dodf>.
     * @pbrbm   row              tif row wiosf ifigit is to bf rfturnfd
     * @rfturn tif ifigit, in pixfls, of tif dflls in tif row
     * @sindf 1.3
     */
    publid int gftRowHfigit(int row) {
        rfturn (rowModfl == null) ? gftRowHfigit() : rowModfl.gftSizf(row);
    }

    /**
     * Sfts tif bmount of fmpty spbdf bftwffn dflls in bdjbdfnt rows.
     *
     * @pbrbm  rowMbrgin  tif numbfr of pixfls bftwffn dflls in b row
     * @sff     #gftRowMbrgin
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif bmount of spbdf bftwffn dflls.
     */
    publid void sftRowMbrgin(int rowMbrgin) {
        int old = tiis.rowMbrgin;
        tiis.rowMbrgin = rowMbrgin;
        rfsizfAndRfpbint();
        firfPropfrtyCibngf("rowMbrgin", old, rowMbrgin);
    }

    /**
     * Gfts tif bmount of fmpty spbdf, in pixfls, bftwffn dflls. Equivblfnt to:
     * <dodf>gftIntfrdfllSpbding().ifigit</dodf>.
     * @rfturn tif numbfr of pixfls bftwffn dflls in b row
     *
     * @sff     #sftRowMbrgin
     */
    publid int gftRowMbrgin() {
        rfturn rowMbrgin;
    }

    /**
     * Sfts tif <dodf>rowMbrgin</dodf> bnd tif <dodf>dolumnMbrgin</dodf> --
     * tif ifigit bnd widti of tif spbdf bftwffn dflls -- to
     * <dodf>intfrdfllSpbding</dodf>.
     *
     * @pbrbm   intfrdfllSpbding        b <dodf>Dimfnsion</dodf>
     *                                  spfdifying tif nfw widti
     *                                  bnd ifigit bftwffn dflls
     * @sff     #gftIntfrdfllSpbding
     * @bfbninfo
     *  dfsdription: Tif spbding bftwffn tif dflls,
     *               drbwn in tif bbdkground dolor of tif JTbblf.
     */
    publid void sftIntfrdfllSpbding(Dimfnsion intfrdfllSpbding) {
        // Sft tif rowMbrgin ifrf bnd dolumnMbrgin in tif TbblfColumnModfl
        sftRowMbrgin(intfrdfllSpbding.ifigit);
        gftColumnModfl().sftColumnMbrgin(intfrdfllSpbding.widti);

        rfsizfAndRfpbint();
    }

    /**
     * Rfturns tif iorizontbl bnd vfrtidbl spbdf bftwffn dflls.
     * Tif dffbult spbding is look bnd fffl dfpfndfnt.
     *
     * @rfturn  tif iorizontbl bnd vfrtidbl spbding bftwffn dflls
     * @sff     #sftIntfrdfllSpbding
     */
    publid Dimfnsion gftIntfrdfllSpbding() {
        rfturn nfw Dimfnsion(gftColumnModfl().gftColumnMbrgin(), rowMbrgin);
    }

    /**
     * Sfts tif dolor usfd to drbw grid linfs to <dodf>gridColor</dodf> bnd rfdisplbys.
     * Tif dffbult dolor is look bnd fffl dfpfndfnt.
     *
     * @pbrbm   gridColor                       tif nfw dolor of tif grid linfs
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>gridColor</dodf> is <dodf>null</dodf>
     * @sff     #gftGridColor
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif grid dolor.
     */
    publid void sftGridColor(Color gridColor) {
        if (gridColor == null) {
            tirow nfw IllfgblArgumfntExdfption("Nfw dolor is null");
        }
        Color old = tiis.gridColor;
        tiis.gridColor = gridColor;
        firfPropfrtyCibngf("gridColor", old, gridColor);
        // Rfdrbw
        rfpbint();
    }

    /**
     * Rfturns tif dolor usfd to drbw grid linfs.
     * Tif dffbult dolor is look bnd fffl dfpfndfnt.
     *
     * @rfturn  tif dolor usfd to drbw grid linfs
     * @sff     #sftGridColor
     */
    publid Color gftGridColor() {
        rfturn gridColor;
    }

    /**
     *  Sfts wiftifr tif tbblf drbws grid linfs bround dflls.
     *  If <dodf>siowGrid</dodf> is truf it dofs; if it is fblsf it dofsn't.
     *  Tifrf is no <dodf>gftSiowGrid</dodf> mftiod bs tiis stbtf is ifld
     *  in two vbribblfs -- <dodf>siowHorizontblLinfs</dodf> bnd <dodf>siowVfrtidblLinfs</dodf> --
     *  fbdi of wiidi dbn bf qufrifd indfpfndfntly.
     *
     * @pbrbm   siowGrid                 truf if tbblf vifw siould drbw grid linfs
     *
     * @sff     #sftSiowVfrtidblLinfs
     * @sff     #sftSiowHorizontblLinfs
     * @bfbninfo
     *  dfsdription: Tif dolor usfd to drbw tif grid linfs.
     */
    publid void sftSiowGrid(boolfbn siowGrid) {
        sftSiowHorizontblLinfs(siowGrid);
        sftSiowVfrtidblLinfs(siowGrid);

        // Rfdrbw
        rfpbint();
    }

    /**
     *  Sfts wiftifr tif tbblf drbws iorizontbl linfs bftwffn dflls.
     *  If <dodf>siowHorizontblLinfs</dodf> is truf it dofs; if it is fblsf it dofsn't.
     *
     * @pbrbm   siowHorizontblLinfs      truf if tbblf vifw siould drbw iorizontbl linfs
     * @sff     #gftSiowHorizontblLinfs
     * @sff     #sftSiowGrid
     * @sff     #sftSiowVfrtidblLinfs
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Wiftifr iorizontbl linfs siould bf drbwn in bftwffn tif dflls.
     */
    publid void sftSiowHorizontblLinfs(boolfbn siowHorizontblLinfs) {
        boolfbn old = tiis.siowHorizontblLinfs;
        tiis.siowHorizontblLinfs = siowHorizontblLinfs;
        firfPropfrtyCibngf("siowHorizontblLinfs", old, siowHorizontblLinfs);

        // Rfdrbw
        rfpbint();
    }

    /**
     *  Sfts wiftifr tif tbblf drbws vfrtidbl linfs bftwffn dflls.
     *  If <dodf>siowVfrtidblLinfs</dodf> is truf it dofs; if it is fblsf it dofsn't.
     *
     * @pbrbm   siowVfrtidblLinfs              truf if tbblf vifw siould drbw vfrtidbl linfs
     * @sff     #gftSiowVfrtidblLinfs
     * @sff     #sftSiowGrid
     * @sff     #sftSiowHorizontblLinfs
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Wiftifr vfrtidbl linfs siould bf drbwn in bftwffn tif dflls.
     */
    publid void sftSiowVfrtidblLinfs(boolfbn siowVfrtidblLinfs) {
        boolfbn old = tiis.siowVfrtidblLinfs;
        tiis.siowVfrtidblLinfs = siowVfrtidblLinfs;
        firfPropfrtyCibngf("siowVfrtidblLinfs", old, siowVfrtidblLinfs);
        // Rfdrbw
        rfpbint();
    }

    /**
     * Rfturns truf if tif tbblf drbws iorizontbl linfs bftwffn dflls, fblsf if it
     * dofsn't. Tif dffbult vbluf is look bnd fffl dfpfndfnt.
     *
     * @rfturn  truf if tif tbblf drbws iorizontbl linfs bftwffn dflls, fblsf if it
     *          dofsn't
     * @sff     #sftSiowHorizontblLinfs
     */
    publid boolfbn gftSiowHorizontblLinfs() {
        rfturn siowHorizontblLinfs;
    }

    /**
     * Rfturns truf if tif tbblf drbws vfrtidbl linfs bftwffn dflls, fblsf if it
     * dofsn't. Tif dffbult vbluf is look bnd fffl dfpfndfnt.
     *
     * @rfturn  truf if tif tbblf drbws vfrtidbl linfs bftwffn dflls, fblsf if it
     *          dofsn't
     * @sff     #sftSiowVfrtidblLinfs
     */
    publid boolfbn gftSiowVfrtidblLinfs() {
        rfturn siowVfrtidblLinfs;
    }

    /**
     * Sfts tif tbblf's buto rfsizf modf wifn tif tbblf is rfsizfd.  For furtifr
     * informbtion on iow tif difffrfnt rfsizf modfs work, sff
     * {@link #doLbyout}.
     *
     * @pbrbm   modf Onf of 5 lfgbl vblufs:
     *                   AUTO_RESIZE_OFF,
     *                   AUTO_RESIZE_NEXT_COLUMN,
     *                   AUTO_RESIZE_SUBSEQUENT_COLUMNS,
     *                   AUTO_RESIZE_LAST_COLUMN,
     *                   AUTO_RESIZE_ALL_COLUMNS
     *
     * @sff     #gftAutoRfsizfModf
     * @sff     #doLbyout
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Wiftifr tif dolumns siould bdjust tifmsflvfs butombtidblly.
     *        fnum: AUTO_RESIZE_OFF                JTbblf.AUTO_RESIZE_OFF
     *              AUTO_RESIZE_NEXT_COLUMN        JTbblf.AUTO_RESIZE_NEXT_COLUMN
     *              AUTO_RESIZE_SUBSEQUENT_COLUMNS JTbblf.AUTO_RESIZE_SUBSEQUENT_COLUMNS
     *              AUTO_RESIZE_LAST_COLUMN        JTbblf.AUTO_RESIZE_LAST_COLUMN
     *              AUTO_RESIZE_ALL_COLUMNS        JTbblf.AUTO_RESIZE_ALL_COLUMNS
     */
    publid void sftAutoRfsizfModf(int modf) {
        if ((modf == AUTO_RESIZE_OFF) ||
            (modf == AUTO_RESIZE_NEXT_COLUMN) ||
            (modf == AUTO_RESIZE_SUBSEQUENT_COLUMNS) ||
            (modf == AUTO_RESIZE_LAST_COLUMN) ||
            (modf == AUTO_RESIZE_ALL_COLUMNS)) {
            int old = butoRfsizfModf;
            butoRfsizfModf = modf;
            rfsizfAndRfpbint();
            if (tbblfHfbdfr != null) {
                tbblfHfbdfr.rfsizfAndRfpbint();
            }
            firfPropfrtyCibngf("butoRfsizfModf", old, butoRfsizfModf);
        }
    }

    /**
     * Rfturns tif buto rfsizf modf of tif tbblf.  Tif dffbult modf
     * is AUTO_RESIZE_SUBSEQUENT_COLUMNS.
     *
     * @rfturn  tif butoRfsizfModf of tif tbblf
     *
     * @sff     #sftAutoRfsizfModf
     * @sff     #doLbyout
     */
    publid int gftAutoRfsizfModf() {
        rfturn butoRfsizfModf;
    }

    /**
     * Sfts tiis tbblf's <dodf>butoCrfbtfColumnsFromModfl</dodf> flbg.
     * Tiis mftiod dblls <dodf>drfbtfDffbultColumnsFromModfl</dodf> if
     * <dodf>butoCrfbtfColumnsFromModfl</dodf> dibngfs from fblsf to truf.
     *
     * @pbrbm   butoCrfbtfColumnsFromModfl   truf if <dodf>JTbblf</dodf> siould butombtidblly drfbtf dolumns
     * @sff     #gftAutoCrfbtfColumnsFromModfl
     * @sff     #drfbtfDffbultColumnsFromModfl
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Autombtidblly populbtfs tif dolumnModfl wifn b nfw TbblfModfl is submittfd.
     */
    publid void sftAutoCrfbtfColumnsFromModfl(boolfbn butoCrfbtfColumnsFromModfl) {
        if (tiis.butoCrfbtfColumnsFromModfl != butoCrfbtfColumnsFromModfl) {
            boolfbn old = tiis.butoCrfbtfColumnsFromModfl;
            tiis.butoCrfbtfColumnsFromModfl = butoCrfbtfColumnsFromModfl;
            if (butoCrfbtfColumnsFromModfl) {
                drfbtfDffbultColumnsFromModfl();
            }
            firfPropfrtyCibngf("butoCrfbtfColumnsFromModfl", old, butoCrfbtfColumnsFromModfl);
        }
    }

    /**
     * Dftfrminfs wiftifr tif tbblf will drfbtf dffbult dolumns from tif modfl.
     * If truf, <dodf>sftModfl</dodf> will dlfbr bny fxisting dolumns bnd
     * drfbtf nfw dolumns from tif nfw modfl.  Also, if tif fvfnt in
     * tif <dodf>tbblfCibngfd</dodf> notifidbtion spfdififs tibt tif
     * fntirf tbblf dibngfd, tifn tif dolumns will bf rfbuilt.
     * Tif dffbult is truf.
     *
     * @rfturn  tif butoCrfbtfColumnsFromModfl of tif tbblf
     * @sff     #sftAutoCrfbtfColumnsFromModfl
     * @sff     #drfbtfDffbultColumnsFromModfl
     */
    publid boolfbn gftAutoCrfbtfColumnsFromModfl() {
        rfturn butoCrfbtfColumnsFromModfl;
    }

    /**
     * Crfbtfs dffbult dolumns for tif tbblf from
     * tif dbtb modfl using tif <dodf>gftColumnCount</dodf> mftiod
     * dffinfd in tif <dodf>TbblfModfl</dodf> intfrfbdf.
     * <p>
     * Clfbrs bny fxisting dolumns bfforf drfbting tif
     * nfw dolumns bbsfd on informbtion from tif modfl.
     *
     * @sff     #gftAutoCrfbtfColumnsFromModfl
     */
    publid void drfbtfDffbultColumnsFromModfl() {
        TbblfModfl m = gftModfl();
        if (m != null) {
            // Rfmovf bny durrfnt dolumns
            TbblfColumnModfl dm = gftColumnModfl();
            wiilf (dm.gftColumnCount() > 0) {
                dm.rfmovfColumn(dm.gftColumn(0));
            }

            // Crfbtf nfw dolumns from tif dbtb modfl info
            for (int i = 0; i < m.gftColumnCount(); i++) {
                TbblfColumn nfwColumn = nfw TbblfColumn(i);
                bddColumn(nfwColumn);
            }
        }
    }

    /**
     * Sfts b dffbult dfll rfndfrfr to bf usfd if no rfndfrfr ibs bffn sft in
     * b <dodf>TbblfColumn</dodf>. If rfndfrfr is <dodf>null</dodf>,
     * rfmovfs tif dffbult rfndfrfr for tiis dolumn dlbss.
     *
     * @pbrbm  dolumnClbss     sft tif dffbult dfll rfndfrfr for tiis dolumnClbss
     * @pbrbm  rfndfrfr        dffbult dfll rfndfrfr to bf usfd for tiis
     *                         dolumnClbss
     * @sff     #gftDffbultRfndfrfr
     * @sff     #sftDffbultEditor
     */
    publid void sftDffbultRfndfrfr(Clbss<?> dolumnClbss, TbblfCfllRfndfrfr rfndfrfr) {
        if (rfndfrfr != null) {
            dffbultRfndfrfrsByColumnClbss.put(dolumnClbss, rfndfrfr);
        }
        flsf {
            dffbultRfndfrfrsByColumnClbss.rfmovf(dolumnClbss);
        }
    }

    /**
     * Rfturns tif dfll rfndfrfr to bf usfd wifn no rfndfrfr ibs bffn sft in
     * b <dodf>TbblfColumn</dodf>. During tif rfndfring of dflls tif rfndfrfr is fftdifd from
     * b <dodf>Hbsitbblf</dodf> of fntrifs bddording to tif dlbss of tif dflls in tif dolumn. If
     * tifrf is no fntry for tiis <dodf>dolumnClbss</dodf> tif mftiod rfturns
     * tif fntry for tif most spfdifid supfrdlbss. Tif <dodf>JTbblf</dodf> instblls fntrifs
     * for <dodf>Objfdt</dodf>, <dodf>Numbfr</dodf>, bnd <dodf>Boolfbn</dodf>, bll of wiidi dbn bf modififd
     * or rfplbdfd.
     *
     * @pbrbm   dolumnClbss   rfturn tif dffbult dfll rfndfrfr
     *                        for tiis dolumnClbss
     * @rfturn  tif rfndfrfr for tiis dolumnClbss
     * @sff     #sftDffbultRfndfrfr
     * @sff     #gftColumnClbss
     */
    publid TbblfCfllRfndfrfr gftDffbultRfndfrfr(Clbss<?> dolumnClbss) {
        if (dolumnClbss == null) {
            rfturn null;
        }
        flsf {
            Objfdt rfndfrfr = dffbultRfndfrfrsByColumnClbss.gft(dolumnClbss);
            if (rfndfrfr != null) {
                rfturn (TbblfCfllRfndfrfr)rfndfrfr;
            }
            flsf {
                Clbss<?> d = dolumnClbss.gftSupfrdlbss();
                if (d == null && dolumnClbss != Objfdt.dlbss) {
                    d = Objfdt.dlbss;
                }
                rfturn gftDffbultRfndfrfr(d);
            }
        }
    }

    /**
     * Sfts b dffbult dfll fditor to bf usfd if no fditor ibs bffn sft in
     * b <dodf>TbblfColumn</dodf>. If no fditing is rfquirfd in b tbblf, or b
     * pbrtidulbr dolumn in b tbblf, usfs tif <dodf>isCfllEditbblf</dodf>
     * mftiod in tif <dodf>TbblfModfl</dodf> intfrfbdf to fnsurf tibt tiis
     * <dodf>JTbblf</dodf> will not stbrt bn fditor in tifsf dolumns.
     * If fditor is <dodf>null</dodf>, rfmovfs tif dffbult fditor for tiis
     * dolumn dlbss.
     *
     * @pbrbm  dolumnClbss  sft tif dffbult dfll fditor for tiis dolumnClbss
     * @pbrbm  fditor   dffbult dfll fditor to bf usfd for tiis dolumnClbss
     * @sff     TbblfModfl#isCfllEditbblf
     * @sff     #gftDffbultEditor
     * @sff     #sftDffbultRfndfrfr
     */
    publid void sftDffbultEditor(Clbss<?> dolumnClbss, TbblfCfllEditor fditor) {
        if (fditor != null) {
            dffbultEditorsByColumnClbss.put(dolumnClbss, fditor);
        }
        flsf {
            dffbultEditorsByColumnClbss.rfmovf(dolumnClbss);
        }
    }

    /**
     * Rfturns tif fditor to bf usfd wifn no fditor ibs bffn sft in
     * b <dodf>TbblfColumn</dodf>. During tif fditing of dflls tif fditor is fftdifd from
     * b <dodf>Hbsitbblf</dodf> of fntrifs bddording to tif dlbss of tif dflls in tif dolumn. If
     * tifrf is no fntry for tiis <dodf>dolumnClbss</dodf> tif mftiod rfturns
     * tif fntry for tif most spfdifid supfrdlbss. Tif <dodf>JTbblf</dodf> instblls fntrifs
     * for <dodf>Objfdt</dodf>, <dodf>Numbfr</dodf>, bnd <dodf>Boolfbn</dodf>, bll of wiidi dbn bf modififd
     * or rfplbdfd.
     *
     * @pbrbm   dolumnClbss  rfturn tif dffbult dfll fditor for tiis dolumnClbss
     * @rfturn tif dffbult dfll fditor to bf usfd for tiis dolumnClbss
     * @sff     #sftDffbultEditor
     * @sff     #gftColumnClbss
     */
    publid TbblfCfllEditor gftDffbultEditor(Clbss<?> dolumnClbss) {
        if (dolumnClbss == null) {
            rfturn null;
        }
        flsf {
            Objfdt fditor = dffbultEditorsByColumnClbss.gft(dolumnClbss);
            if (fditor != null) {
                rfturn (TbblfCfllEditor)fditor;
            }
            flsf {
                rfturn gftDffbultEditor(dolumnClbss.gftSupfrdlbss());
            }
        }
    }

    /**
     * Turns on or off butombtid drbg ibndling. In ordfr to fnbblf butombtid
     * drbg ibndling, tiis propfrty siould bf sft to {@dodf truf}, bnd tif
     * tbblf's {@dodf TrbnsffrHbndlfr} nffds to bf {@dodf non-null}.
     * Tif dffbult vbluf of tif {@dodf drbgEnbblfd} propfrty is {@dodf fblsf}.
     * <p>
     * Tif job of ionoring tiis propfrty, bnd rfdognizing b usfr drbg gfsturf,
     * lifs witi tif look bnd fffl implfmfntbtion, bnd in pbrtidulbr, tif tbblf's
     * {@dodf TbblfUI}. Wifn butombtid drbg ibndling is fnbblfd, most look bnd
     * fffls (indluding tiosf tibt subdlbss {@dodf BbsidLookAndFffl}) bfgin b
     * drbg bnd drop opfrbtion wifnfvfr tif usfr prfssfs tif mousf button ovfr
     * bn itfm (in singlf sflfdtion modf) or b sflfdtion (in otifr sflfdtion
     * modfs) bnd tifn movfs tif mousf b ffw pixfls. Sftting tiis propfrty to
     * {@dodf truf} dbn tifrfforf ibvf b subtlf ffffdt on iow sflfdtions bfibvf.
     * <p>
     * If b look bnd fffl is usfd tibt ignorfs tiis propfrty, you dbn still
     * bfgin b drbg bnd drop opfrbtion by dblling {@dodf fxportAsDrbg} on tif
     * tbblf's {@dodf TrbnsffrHbndlfr}.
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
     * bfffdting tif bdtubl sflfdtion in tif tbblf.
     * <p>
     * <dodf>JTbblf</dodf> supports tif following drop modfs:
     * <ul>
     *    <li><dodf>DropModf.USE_SELECTION</dodf></li>
     *    <li><dodf>DropModf.ON</dodf></li>
     *    <li><dodf>DropModf.INSERT</dodf></li>
     *    <li><dodf>DropModf.INSERT_ROWS</dodf></li>
     *    <li><dodf>DropModf.INSERT_COLS</dodf></li>
     *    <li><dodf>DropModf.ON_OR_INSERT</dodf></li>
     *    <li><dodf>DropModf.ON_OR_INSERT_ROWS</dodf></li>
     *    <li><dodf>DropModf.ON_OR_INSERT_COLS</dodf></li>
     * </ul>
     * <p>
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
                dbsf INSERT_ROWS:
                dbsf INSERT_COLS:
                dbsf ON_OR_INSERT:
                dbsf ON_OR_INSERT_ROWS:
                dbsf ON_OR_INSERT_COLS:
                    tiis.dropModf = dropModf;
                    rfturn;
            }
        }

        tirow nfw IllfgblArgumfntExdfption(dropModf + ": Unsupportfd drop modf for tbblf");
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

        int row = rowAtPoint(p);
        int dol = dolumnAtPoint(p);
        boolfbn outsidf = Boolfbn.TRUE == gftClifntPropfrty("Tbblf.isFilfList")
                          && SwingUtilitifs2.pointOutsidfPrffSizf(tiis, row, dol, p);

        Rfdtbnglf rfdt = gftCfllRfdt(row, dol, truf);
        Sfdtion xSfdtion, ySfdtion;
        boolfbn bftwffn = fblsf;
        boolfbn ltr = gftComponfntOrifntbtion().isLfftToRigit();

        switdi(dropModf) {
            dbsf USE_SELECTION:
            dbsf ON:
                if (row == -1 || dol == -1 || outsidf) {
                    lodbtion = nfw DropLodbtion(p, -1, -1, fblsf, fblsf);
                } flsf {
                    lodbtion = nfw DropLodbtion(p, row, dol, fblsf, fblsf);
                }
                brfbk;
            dbsf INSERT:
                if (row == -1 && dol == -1) {
                    lodbtion = nfw DropLodbtion(p, 0, 0, truf, truf);
                    brfbk;
                }

                xSfdtion = SwingUtilitifs2.lifsInHorizontbl(rfdt, p, ltr, truf);

                if (row == -1) {
                    if (xSfdtion == LEADING) {
                        lodbtion = nfw DropLodbtion(p, gftRowCount(), dol, truf, truf);
                    } flsf if (xSfdtion == TRAILING) {
                        lodbtion = nfw DropLodbtion(p, gftRowCount(), dol + 1, truf, truf);
                    } flsf {
                        lodbtion = nfw DropLodbtion(p, gftRowCount(), dol, truf, fblsf);
                    }
                } flsf if (xSfdtion == LEADING || xSfdtion == TRAILING) {
                    ySfdtion = SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, truf);
                    if (ySfdtion == LEADING) {
                        bftwffn = truf;
                    } flsf if (ySfdtion == TRAILING) {
                        row++;
                        bftwffn = truf;
                    }

                    lodbtion = nfw DropLodbtion(p, row,
                                                xSfdtion == TRAILING ? dol + 1 : dol,
                                                bftwffn, truf);
                } flsf {
                    if (SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, fblsf) == TRAILING) {
                        row++;
                    }

                    lodbtion = nfw DropLodbtion(p, row, dol, truf, fblsf);
                }

                brfbk;
            dbsf INSERT_ROWS:
                if (row == -1 && dol == -1) {
                    lodbtion = nfw DropLodbtion(p, -1, -1, fblsf, fblsf);
                    brfbk;
                }

                if (row == -1) {
                    lodbtion = nfw DropLodbtion(p, gftRowCount(), dol, truf, fblsf);
                    brfbk;
                }

                if (SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, fblsf) == TRAILING) {
                    row++;
                }

                lodbtion = nfw DropLodbtion(p, row, dol, truf, fblsf);
                brfbk;
            dbsf ON_OR_INSERT_ROWS:
                if (row == -1 && dol == -1) {
                    lodbtion = nfw DropLodbtion(p, -1, -1, fblsf, fblsf);
                    brfbk;
                }

                if (row == -1) {
                    lodbtion = nfw DropLodbtion(p, gftRowCount(), dol, truf, fblsf);
                    brfbk;
                }

                ySfdtion = SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, truf);
                if (ySfdtion == LEADING) {
                    bftwffn = truf;
                } flsf if (ySfdtion == TRAILING) {
                    row++;
                    bftwffn = truf;
                }

                lodbtion = nfw DropLodbtion(p, row, dol, bftwffn, fblsf);
                brfbk;
            dbsf INSERT_COLS:
                if (row == -1) {
                    lodbtion = nfw DropLodbtion(p, -1, -1, fblsf, fblsf);
                    brfbk;
                }

                if (dol == -1) {
                    lodbtion = nfw DropLodbtion(p, gftColumnCount(), dol, fblsf, truf);
                    brfbk;
                }

                if (SwingUtilitifs2.lifsInHorizontbl(rfdt, p, ltr, fblsf) == TRAILING) {
                    dol++;
                }

                lodbtion = nfw DropLodbtion(p, row, dol, fblsf, truf);
                brfbk;
            dbsf ON_OR_INSERT_COLS:
                if (row == -1) {
                    lodbtion = nfw DropLodbtion(p, -1, -1, fblsf, fblsf);
                    brfbk;
                }

                if (dol == -1) {
                    lodbtion = nfw DropLodbtion(p, row, gftColumnCount(), fblsf, truf);
                    brfbk;
                }

                xSfdtion = SwingUtilitifs2.lifsInHorizontbl(rfdt, p, ltr, truf);
                if (xSfdtion == LEADING) {
                    bftwffn = truf;
                } flsf if (xSfdtion == TRAILING) {
                    dol++;
                    bftwffn = truf;
                }

                lodbtion = nfw DropLodbtion(p, row, dol, fblsf, bftwffn);
                brfbk;
            dbsf ON_OR_INSERT:
                if (row == -1 && dol == -1) {
                    lodbtion = nfw DropLodbtion(p, 0, 0, truf, truf);
                    brfbk;
                }

                xSfdtion = SwingUtilitifs2.lifsInHorizontbl(rfdt, p, ltr, truf);

                if (row == -1) {
                    if (xSfdtion == LEADING) {
                        lodbtion = nfw DropLodbtion(p, gftRowCount(), dol, truf, truf);
                    } flsf if (xSfdtion == TRAILING) {
                        lodbtion = nfw DropLodbtion(p, gftRowCount(), dol + 1, truf, truf);
                    } flsf {
                        lodbtion = nfw DropLodbtion(p, gftRowCount(), dol, truf, fblsf);
                    }

                    brfbk;
                }

                ySfdtion = SwingUtilitifs2.lifsInVfrtidbl(rfdt, p, truf);
                if (ySfdtion == LEADING) {
                    bftwffn = truf;
                } flsf if (ySfdtion == TRAILING) {
                    row++;
                    bftwffn = truf;
                }

                lodbtion = nfw DropLodbtion(p, row,
                                            xSfdtion == TRAILING ? dol + 1 : dol,
                                            bftwffn,
                                            xSfdtion != MIDDLE);

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
        DropLodbtion tbblfLodbtion = (DropLodbtion)lodbtion;

        if (dropModf == DropModf.USE_SELECTION) {
            if (tbblfLodbtion == null) {
                if (!forDrop && stbtf != null) {
                    dlfbrSflfdtion();

                    int[] rows = ((int[][])stbtf)[0];
                    int[] dols = ((int[][])stbtf)[1];
                    int[] bndilfbds = ((int[][])stbtf)[2];

                    for (int row : rows) {
                        bddRowSflfdtionIntfrvbl(row, row);
                    }

                    for (int dol : dols) {
                        bddColumnSflfdtionIntfrvbl(dol, dol);
                    }

                    SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(
                            gftSflfdtionModfl(), bndilfbds[1], bndilfbds[0]);

                    SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(
                            gftColumnModfl().gftSflfdtionModfl(),
                            bndilfbds[3], bndilfbds[2]);
                }
            } flsf {
                if (dropLodbtion == null) {
                    rftVbl = nfw int[][]{
                        gftSflfdtfdRows(),
                        gftSflfdtfdColumns(),
                        {gftAdjustfdIndfx(gftSflfdtionModfl()
                             .gftAndiorSflfdtionIndfx(), truf),
                         gftAdjustfdIndfx(gftSflfdtionModfl()
                             .gftLfbdSflfdtionIndfx(), truf),
                         gftAdjustfdIndfx(gftColumnModfl().gftSflfdtionModfl()
                             .gftAndiorSflfdtionIndfx(), fblsf),
                         gftAdjustfdIndfx(gftColumnModfl().gftSflfdtionModfl()
                             .gftLfbdSflfdtionIndfx(), fblsf)}};
                } flsf {
                    rftVbl = stbtf;
                }

                if (tbblfLodbtion.gftRow() == -1) {
                    dlfbrSflfdtionAndLfbdAndior();
                } flsf {
                    sftRowSflfdtionIntfrvbl(tbblfLodbtion.gftRow(),
                                            tbblfLodbtion.gftRow());
                    sftColumnSflfdtionIntfrvbl(tbblfLodbtion.gftColumn(),
                                               tbblfLodbtion.gftColumn());
                }
            }
        }

        DropLodbtion old = dropLodbtion;
        dropLodbtion = tbblfLodbtion;
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
     * Spfdififs wiftifr b {@dodf RowSortfr} siould bf drfbtfd for tif
     * tbblf wifnfvfr its modfl dibngfs.
     * <p>
     * Wifn {@dodf sftAutoCrfbtfRowSortfr(truf)} is invokfd, b {@dodf
     * TbblfRowSortfr} is immfdibtfly drfbtfd bnd instbllfd on tif
     * tbblf.  Wiilf tif {@dodf butoCrfbtfRowSortfr} propfrty rfmbins
     * {@dodf truf}, fvfry timf tif modfl is dibngfd, b nfw {@dodf
     * TbblfRowSortfr} is drfbtfd bnd sft bs tif tbblf's row sortfr.
     * Tif dffbult vbluf for tif {@dodf butoCrfbtfRowSortfr}
     * propfrty is {@dodf fblsf}.
     *
     * @pbrbm butoCrfbtfRowSortfr wiftifr or not b {@dodf RowSortfr}
     *        siould bf butombtidblly drfbtfd
     * @sff jbvbx.swing.tbblf.TbblfRowSortfr
     * @bfbninfo
     *        bound: truf
     *    prfffrrfd: truf
     *  dfsdription: Wiftifr or not to turn on sorting by dffbult.
     * @sindf 1.6
     */
    publid void sftAutoCrfbtfRowSortfr(boolfbn butoCrfbtfRowSortfr) {
        boolfbn oldVbluf = tiis.butoCrfbtfRowSortfr;
        tiis.butoCrfbtfRowSortfr = butoCrfbtfRowSortfr;
        if (butoCrfbtfRowSortfr) {
            sftRowSortfr(nfw TbblfRowSortfr<TbblfModfl>(gftModfl()));
        }
        firfPropfrtyCibngf("butoCrfbtfRowSortfr", oldVbluf,
                           butoCrfbtfRowSortfr);
    }

    /**
     * Rfturns {@dodf truf} if wifnfvfr tif modfl dibngfs, b nfw
     * {@dodf RowSortfr} siould bf drfbtfd bnd instbllfd
     * bs tif tbblf's sortfr; otifrwisf, rfturns {@dodf fblsf}.
     *
     * @rfturn truf if b {@dodf RowSortfr} siould bf drfbtfd wifn
     *         tif modfl dibngfs
     * @sindf 1.6
     */
    publid boolfbn gftAutoCrfbtfRowSortfr() {
        rfturn butoCrfbtfRowSortfr;
    }

    /**
     * Spfdififs wiftifr tif sflfdtion siould bf updbtfd bftfr sorting.
     * If truf, on sorting tif sflfdtion is rfsft sudi tibt
     * tif sbmf rows, in tfrms of tif modfl, rfmbin sflfdtfd.  Tif dffbult
     * is truf.
     *
     * @pbrbm updbtf wiftifr or not to updbtf tif sflfdtion on sorting
     * @bfbninfo
     *        bound: truf
     *       fxpfrt: truf
     *  dfsdription: Wiftifr or not to updbtf tif sflfdtion on sorting
     * @sindf 1.6
     */
    publid void sftUpdbtfSflfdtionOnSort(boolfbn updbtf) {
        if (updbtfSflfdtionOnSort != updbtf) {
            updbtfSflfdtionOnSort = updbtf;
            firfPropfrtyCibngf("updbtfSflfdtionOnSort", !updbtf, updbtf);
        }
    }

    /**
     * Rfturns truf if tif sflfdtion siould bf updbtfd bftfr sorting.
     *
     * @rfturn wiftifr to updbtf tif sflfdtion on b sort
     * @sindf 1.6
     */
    publid boolfbn gftUpdbtfSflfdtionOnSort() {
        rfturn updbtfSflfdtionOnSort;
    }

    /**
     * Sfts tif <dodf>RowSortfr</dodf>.  <dodf>RowSortfr</dodf> is usfd
     * to providf sorting bnd filtfring to b <dodf>JTbblf</dodf>.
     * <p>
     * Tiis mftiod dlfbrs tif sflfdtion bnd rfsfts bny vbribblf row ifigits.
     * <p>
     * Tiis mftiod firfs b <dodf>PropfrtyCibngfEvfnt</dodf> wifn bppropribtf,
     * witi tif propfrty nbmf <dodf>"rowSortfr"</dodf>.  For
     * bbdkwbrd-dompbtibility, tiis mftiod firfs bn bdditionbl fvfnt witi tif
     * propfrty nbmf <dodf>"sortfr"</dodf>.
     * <p>
     * If tif undfrlying modfl of tif <dodf>RowSortfr</dodf> difffrs from
     * tibt of tiis <dodf>JTbblf</dodf> undffinfd bfibvior will rfsult.
     *
     * @pbrbm sortfr tif <dodf>RowSortfr</dodf>; <dodf>null</dodf> turns
     *        sorting off
     * @sff jbvbx.swing.tbblf.TbblfRowSortfr
     * @bfbninfo
     *        bound: truf
     *  dfsdription: Tif tbblf's RowSortfr
     * @sindf 1.6
     */
    publid void sftRowSortfr(RowSortfr<? fxtfnds TbblfModfl> sortfr) {
        RowSortfr<? fxtfnds TbblfModfl> oldRowSortfr = null;
        if (sortMbnbgfr != null) {
            oldRowSortfr = sortMbnbgfr.sortfr;
            sortMbnbgfr.disposf();
            sortMbnbgfr = null;
        }
        rowModfl = null;
        dlfbrSflfdtionAndLfbdAndior();
        if (sortfr != null) {
            sortMbnbgfr = nfw SortMbnbgfr(sortfr);
        }
        rfsizfAndRfpbint();
        firfPropfrtyCibngf("rowSortfr", oldRowSortfr, sortfr);
        firfPropfrtyCibngf("sortfr", oldRowSortfr, sortfr);
    }

    /**
     * Rfturns tif objfdt rfsponsiblf for sorting.
     *
     * @rfturn tif objfdt rfsponsiblf for sorting
     * @sindf 1.6
     */
    publid RowSortfr<? fxtfnds TbblfModfl> gftRowSortfr() {
        rfturn (sortMbnbgfr != null) ? sortMbnbgfr.sortfr : null;
    }

//
// Sflfdtion mftiods
//
    /**
     * Sfts tif tbblf's sflfdtion modf to bllow only singlf sflfdtions, b singlf
     * dontiguous intfrvbl, or multiplf intfrvbls.
     * <P>
     * <b>Notf:</b>
     * <dodf>JTbblf</dodf> providfs bll tif mftiods for ibndling
     * dolumn bnd row sflfdtion.  Wifn sftting stbtfs,
     * sudi bs <dodf>sftSflfdtionModf</dodf>, it not only
     * updbtfs tif modf for tif row sflfdtion modfl but blso sfts similbr
     * vblufs in tif sflfdtion modfl of tif <dodf>dolumnModfl</dodf>.
     * If you wbnt to ibvf tif row bnd dolumn sflfdtion modfls opfrbting
     * in difffrfnt modfs, sft tifm boti dirfdtly.
     * <p>
     * Boti tif row bnd dolumn sflfdtion modfls for <dodf>JTbblf</dodf>
     * dffbult to using b <dodf>DffbultListSflfdtionModfl</dodf>
     * so tibt <dodf>JTbblf</dodf> works tif sbmf wby bs tif
     * <dodf>JList</dodf>. Sff tif <dodf>sftSflfdtionModf</dodf> mftiod
     * in <dodf>JList</dodf> for dftbils bbout tif modfs.
     *
     * @pbrbm sflfdtionModf tif modf usfd by tif row bnd dolumn sflfdtion modfls
     * @sff JList#sftSflfdtionModf
     * @bfbninfo
     * dfsdription: Tif sflfdtion modf usfd by tif row bnd dolumn sflfdtion modfls.
     *        fnum: SINGLE_SELECTION            ListSflfdtionModfl.SINGLE_SELECTION
     *              SINGLE_INTERVAL_SELECTION   ListSflfdtionModfl.SINGLE_INTERVAL_SELECTION
     *              MULTIPLE_INTERVAL_SELECTION ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION
     */
    publid void sftSflfdtionModf(int sflfdtionModf) {
        dlfbrSflfdtion();
        gftSflfdtionModfl().sftSflfdtionModf(sflfdtionModf);
        gftColumnModfl().gftSflfdtionModfl().sftSflfdtionModf(sflfdtionModf);
    }

    /**
     * Sfts wiftifr tif rows in tiis modfl dbn bf sflfdtfd.
     *
     * @pbrbm rowSflfdtionAllowfd   truf if tiis modfl will bllow row sflfdtion
     * @sff #gftRowSflfdtionAllowfd
     * @bfbninfo
     *  bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: If truf, bn fntirf row is sflfdtfd for fbdi sflfdtfd dfll.
     */
    publid void sftRowSflfdtionAllowfd(boolfbn rowSflfdtionAllowfd) {
        boolfbn old = tiis.rowSflfdtionAllowfd;
        tiis.rowSflfdtionAllowfd = rowSflfdtionAllowfd;
        if (old != rowSflfdtionAllowfd) {
            rfpbint();
        }
        firfPropfrtyCibngf("rowSflfdtionAllowfd", old, rowSflfdtionAllowfd);
    }

    /**
     * Rfturns truf if rows dbn bf sflfdtfd.
     *
     * @rfturn truf if rows dbn bf sflfdtfd, otifrwisf fblsf
     * @sff #sftRowSflfdtionAllowfd
     */
    publid boolfbn gftRowSflfdtionAllowfd() {
        rfturn rowSflfdtionAllowfd;
    }

    /**
     * Sfts wiftifr tif dolumns in tiis modfl dbn bf sflfdtfd.
     *
     * @pbrbm dolumnSflfdtionAllowfd   truf if tiis modfl will bllow dolumn sflfdtion
     * @sff #gftColumnSflfdtionAllowfd
     * @bfbninfo
     *  bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: If truf, bn fntirf dolumn is sflfdtfd for fbdi sflfdtfd dfll.
     */
    publid void sftColumnSflfdtionAllowfd(boolfbn dolumnSflfdtionAllowfd) {
        boolfbn old = dolumnModfl.gftColumnSflfdtionAllowfd();
        dolumnModfl.sftColumnSflfdtionAllowfd(dolumnSflfdtionAllowfd);
        if (old != dolumnSflfdtionAllowfd) {
            rfpbint();
        }
        firfPropfrtyCibngf("dolumnSflfdtionAllowfd", old, dolumnSflfdtionAllowfd);
    }

    /**
     * Rfturns truf if dolumns dbn bf sflfdtfd.
     *
     * @rfturn truf if dolumns dbn bf sflfdtfd, otifrwisf fblsf
     * @sff #sftColumnSflfdtionAllowfd
     */
    publid boolfbn gftColumnSflfdtionAllowfd() {
        rfturn dolumnModfl.gftColumnSflfdtionAllowfd();
    }

    /**
     * Sfts wiftifr tiis tbblf bllows boti b dolumn sflfdtion bnd b
     * row sflfdtion to fxist simultbnfously. Wifn sft,
     * tif tbblf trfbts tif intfrsfdtion of tif row bnd dolumn sflfdtion
     * modfls bs tif sflfdtfd dflls. Ovfrridf <dodf>isCfllSflfdtfd</dodf> to
     * dibngf tiis dffbult bfibvior. Tiis mftiod is fquivblfnt to sftting
     * boti tif <dodf>rowSflfdtionAllowfd</dodf> propfrty bnd
     * <dodf>dolumnSflfdtionAllowfd</dodf> propfrty of tif
     * <dodf>dolumnModfl</dodf> to tif supplifd vbluf.
     *
     * @pbrbm  dfllSflfdtionEnbblfd     truf if simultbnfous row bnd dolumn
     *                                  sflfdtion is bllowfd
     * @sff #gftCfllSflfdtionEnbblfd
     * @sff #isCfllSflfdtfd
     * @bfbninfo
     *  bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Sflfdt b rfdtbngulbr rfgion of dflls rbtifr tibn
     *               rows or dolumns.
     */
    publid void sftCfllSflfdtionEnbblfd(boolfbn dfllSflfdtionEnbblfd) {
        sftRowSflfdtionAllowfd(dfllSflfdtionEnbblfd);
        sftColumnSflfdtionAllowfd(dfllSflfdtionEnbblfd);
        boolfbn old = tiis.dfllSflfdtionEnbblfd;
        tiis.dfllSflfdtionEnbblfd = dfllSflfdtionEnbblfd;
        firfPropfrtyCibngf("dfllSflfdtionEnbblfd", old, dfllSflfdtionEnbblfd);
    }

    /**
     * Rfturns truf if boti row bnd dolumn sflfdtion modfls brf fnbblfd.
     * Equivblfnt to <dodf>gftRowSflfdtionAllowfd() &bmp;&bmp;
     * gftColumnSflfdtionAllowfd()</dodf>.
     *
     * @rfturn truf if boti row bnd dolumn sflfdtion modfls brf fnbblfd
     *
     * @sff #sftCfllSflfdtionEnbblfd
     */
    publid boolfbn gftCfllSflfdtionEnbblfd() {
        rfturn gftRowSflfdtionAllowfd() && gftColumnSflfdtionAllowfd();
    }

    /**
     *  Sflfdts bll rows, dolumns, bnd dflls in tif tbblf.
     */
    publid void sflfdtAll() {
        // If I'm durrfntly fditing, tifn I siould stop fditing
        if (isEditing()) {
            rfmovfEditor();
        }
        if (gftRowCount() > 0 && gftColumnCount() > 0) {
            int oldLfbd;
            int oldAndior;
            ListSflfdtionModfl sflModfl;

            sflModfl = sflfdtionModfl;
            sflModfl.sftVblufIsAdjusting(truf);
            oldLfbd = gftAdjustfdIndfx(sflModfl.gftLfbdSflfdtionIndfx(), truf);
            oldAndior = gftAdjustfdIndfx(sflModfl.gftAndiorSflfdtionIndfx(), truf);

            sftRowSflfdtionIntfrvbl(0, gftRowCount()-1);

            // tiis is donf to rfstorf tif bndior bnd lfbd
            SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(sflModfl, oldLfbd, oldAndior);

            sflModfl.sftVblufIsAdjusting(fblsf);

            sflModfl = dolumnModfl.gftSflfdtionModfl();
            sflModfl.sftVblufIsAdjusting(truf);
            oldLfbd = gftAdjustfdIndfx(sflModfl.gftLfbdSflfdtionIndfx(), fblsf);
            oldAndior = gftAdjustfdIndfx(sflModfl.gftAndiorSflfdtionIndfx(), fblsf);

            sftColumnSflfdtionIntfrvbl(0, gftColumnCount()-1);

            // tiis is donf to rfstorf tif bndior bnd lfbd
            SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(sflModfl, oldLfbd, oldAndior);

            sflModfl.sftVblufIsAdjusting(fblsf);
        }
    }

    /**
     * Dfsflfdts bll sflfdtfd dolumns bnd rows.
     */
    publid void dlfbrSflfdtion() {
        sflfdtionModfl.dlfbrSflfdtion();
        dolumnModfl.gftSflfdtionModfl().dlfbrSflfdtion();
    }

    privbtf void dlfbrSflfdtionAndLfbdAndior() {
        sflfdtionModfl.sftVblufIsAdjusting(truf);
        dolumnModfl.gftSflfdtionModfl().sftVblufIsAdjusting(truf);

        dlfbrSflfdtion();

        sflfdtionModfl.sftAndiorSflfdtionIndfx(-1);
        sflfdtionModfl.sftLfbdSflfdtionIndfx(-1);
        dolumnModfl.gftSflfdtionModfl().sftAndiorSflfdtionIndfx(-1);
        dolumnModfl.gftSflfdtionModfl().sftLfbdSflfdtionIndfx(-1);

        sflfdtionModfl.sftVblufIsAdjusting(fblsf);
        dolumnModfl.gftSflfdtionModfl().sftVblufIsAdjusting(fblsf);
    }

    privbtf int gftAdjustfdIndfx(int indfx, boolfbn row) {
        int dompbrf = row ? gftRowCount() : gftColumnCount();
        rfturn indfx < dompbrf ? indfx : -1;
    }

    privbtf int boundRow(int row) tirows IllfgblArgumfntExdfption {
        if (row < 0 || row >= gftRowCount()) {
            tirow nfw IllfgblArgumfntExdfption("Row indfx out of rbngf");
        }
        rfturn row;
    }

    privbtf int boundColumn(int dol) {
        if (dol< 0 || dol >= gftColumnCount()) {
            tirow nfw IllfgblArgumfntExdfption("Column indfx out of rbngf");
        }
        rfturn dol;
    }

    /**
     * Sflfdts tif rows from <dodf>indfx0</dodf> to <dodf>indfx1</dodf>,
     * indlusivf.
     *
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>indfx0</dodf> or
     *                                          <dodf>indfx1</dodf> lif outsidf
     *                                          [0, <dodf>gftRowCount()</dodf>-1]
     * @pbrbm   indfx0 onf fnd of tif intfrvbl
     * @pbrbm   indfx1 tif otifr fnd of tif intfrvbl
     */
    publid void sftRowSflfdtionIntfrvbl(int indfx0, int indfx1) {
        sflfdtionModfl.sftSflfdtionIntfrvbl(boundRow(indfx0), boundRow(indfx1));
    }

    /**
     * Sflfdts tif dolumns from <dodf>indfx0</dodf> to <dodf>indfx1</dodf>,
     * indlusivf.
     *
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>indfx0</dodf> or
     *                                          <dodf>indfx1</dodf> lif outsidf
     *                                          [0, <dodf>gftColumnCount()</dodf>-1]
     * @pbrbm   indfx0 onf fnd of tif intfrvbl
     * @pbrbm   indfx1 tif otifr fnd of tif intfrvbl
     */
    publid void sftColumnSflfdtionIntfrvbl(int indfx0, int indfx1) {
        dolumnModfl.gftSflfdtionModfl().sftSflfdtionIntfrvbl(boundColumn(indfx0), boundColumn(indfx1));
    }

    /**
     * Adds tif rows from <dodf>indfx0</dodf> to <dodf>indfx1</dodf>, indlusivf, to
     * tif durrfnt sflfdtion.
     *
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>indfx0</dodf> or <dodf>indfx1</dodf>
     *                                          lif outsidf [0, <dodf>gftRowCount()</dodf>-1]
     * @pbrbm   indfx0 onf fnd of tif intfrvbl
     * @pbrbm   indfx1 tif otifr fnd of tif intfrvbl
     */
    publid void bddRowSflfdtionIntfrvbl(int indfx0, int indfx1) {
        sflfdtionModfl.bddSflfdtionIntfrvbl(boundRow(indfx0), boundRow(indfx1));
    }

    /**
     * Adds tif dolumns from <dodf>indfx0</dodf> to <dodf>indfx1</dodf>,
     * indlusivf, to tif durrfnt sflfdtion.
     *
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>indfx0</dodf> or
     *                                          <dodf>indfx1</dodf> lif outsidf
     *                                          [0, <dodf>gftColumnCount()</dodf>-1]
     * @pbrbm   indfx0 onf fnd of tif intfrvbl
     * @pbrbm   indfx1 tif otifr fnd of tif intfrvbl
     */
    publid void bddColumnSflfdtionIntfrvbl(int indfx0, int indfx1) {
        dolumnModfl.gftSflfdtionModfl().bddSflfdtionIntfrvbl(boundColumn(indfx0), boundColumn(indfx1));
    }

    /**
     * Dfsflfdts tif rows from <dodf>indfx0</dodf> to <dodf>indfx1</dodf>, indlusivf.
     *
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>indfx0</dodf> or
     *                                          <dodf>indfx1</dodf> lif outsidf
     *                                          [0, <dodf>gftRowCount()</dodf>-1]
     * @pbrbm   indfx0 onf fnd of tif intfrvbl
     * @pbrbm   indfx1 tif otifr fnd of tif intfrvbl
     */
    publid void rfmovfRowSflfdtionIntfrvbl(int indfx0, int indfx1) {
        sflfdtionModfl.rfmovfSflfdtionIntfrvbl(boundRow(indfx0), boundRow(indfx1));
    }

    /**
     * Dfsflfdts tif dolumns from <dodf>indfx0</dodf> to <dodf>indfx1</dodf>, indlusivf.
     *
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>indfx0</dodf> or
     *                                          <dodf>indfx1</dodf> lif outsidf
     *                                          [0, <dodf>gftColumnCount()</dodf>-1]
     * @pbrbm   indfx0 onf fnd of tif intfrvbl
     * @pbrbm   indfx1 tif otifr fnd of tif intfrvbl
     */
    publid void rfmovfColumnSflfdtionIntfrvbl(int indfx0, int indfx1) {
        dolumnModfl.gftSflfdtionModfl().rfmovfSflfdtionIntfrvbl(boundColumn(indfx0), boundColumn(indfx1));
    }

    /**
     * Rfturns tif indfx of tif first sflfdtfd row, -1 if no row is sflfdtfd.
     * @rfturn tif indfx of tif first sflfdtfd row
     */
    publid int gftSflfdtfdRow() {
        rfturn sflfdtionModfl.gftMinSflfdtionIndfx();
    }

    /**
     * Rfturns tif indfx of tif first sflfdtfd dolumn,
     * -1 if no dolumn is sflfdtfd.
     * @rfturn tif indfx of tif first sflfdtfd dolumn
     */
    publid int gftSflfdtfdColumn() {
        rfturn dolumnModfl.gftSflfdtionModfl().gftMinSflfdtionIndfx();
    }

    /**
     * Rfturns tif indidfs of bll sflfdtfd rows.
     *
     * @rfturn bn brrby of intfgfrs dontbining tif indidfs of bll sflfdtfd rows,
     *         or bn fmpty brrby if no row is sflfdtfd
     * @sff #gftSflfdtfdRow
     */
    publid int[] gftSflfdtfdRows() {
        int iMin = sflfdtionModfl.gftMinSflfdtionIndfx();
        int iMbx = sflfdtionModfl.gftMbxSflfdtionIndfx();

        if ((iMin == -1) || (iMbx == -1)) {
            rfturn nfw int[0];
        }

        int[] rvTmp = nfw int[1+ (iMbx - iMin)];
        int n = 0;
        for(int i = iMin; i <= iMbx; i++) {
            if (sflfdtionModfl.isSflfdtfdIndfx(i)) {
                rvTmp[n++] = i;
            }
        }
        int[] rv = nfw int[n];
        Systfm.brrbydopy(rvTmp, 0, rv, 0, n);
        rfturn rv;
    }

    /**
     * Rfturns tif indidfs of bll sflfdtfd dolumns.
     *
     * @rfturn bn brrby of intfgfrs dontbining tif indidfs of bll sflfdtfd dolumns,
     *         or bn fmpty brrby if no dolumn is sflfdtfd
     * @sff #gftSflfdtfdColumn
     */
    publid int[] gftSflfdtfdColumns() {
        rfturn dolumnModfl.gftSflfdtfdColumns();
    }

    /**
     * Rfturns tif numbfr of sflfdtfd rows.
     *
     * @rfturn tif numbfr of sflfdtfd rows, 0 if no rows brf sflfdtfd
     */
    publid int gftSflfdtfdRowCount() {
        int iMin = sflfdtionModfl.gftMinSflfdtionIndfx();
        int iMbx = sflfdtionModfl.gftMbxSflfdtionIndfx();
        int dount = 0;

        for(int i = iMin; i <= iMbx; i++) {
            if (sflfdtionModfl.isSflfdtfdIndfx(i)) {
                dount++;
            }
        }
        rfturn dount;
    }

    /**
     * Rfturns tif numbfr of sflfdtfd dolumns.
     *
     * @rfturn tif numbfr of sflfdtfd dolumns, 0 if no dolumns brf sflfdtfd
     */
    publid int gftSflfdtfdColumnCount() {
        rfturn dolumnModfl.gftSflfdtfdColumnCount();
    }

    /**
     * Rfturns truf if tif spfdififd indfx is in tif vblid rbngf of rows,
     * bnd tif row bt tibt indfx is sflfdtfd.
     *
     * @pbrbm row b row in tif row modfl
     * @rfturn truf if <dodf>row</dodf> is b vblid indfx bnd tif row bt
     *              tibt indfx is sflfdtfd (wifrf 0 is tif first row)
     */
    publid boolfbn isRowSflfdtfd(int row) {
        rfturn sflfdtionModfl.isSflfdtfdIndfx(row);
    }

    /**
     * Rfturns truf if tif spfdififd indfx is in tif vblid rbngf of dolumns,
     * bnd tif dolumn bt tibt indfx is sflfdtfd.
     *
     * @pbrbm   dolumn   tif dolumn in tif dolumn modfl
     * @rfturn truf if <dodf>dolumn</dodf> is b vblid indfx bnd tif dolumn bt
     *              tibt indfx is sflfdtfd (wifrf 0 is tif first dolumn)
     */
    publid boolfbn isColumnSflfdtfd(int dolumn) {
        rfturn dolumnModfl.gftSflfdtionModfl().isSflfdtfdIndfx(dolumn);
    }

    /**
     * Rfturns truf if tif spfdififd indidfs brf in tif vblid rbngf of rows
     * bnd dolumns bnd tif dfll bt tif spfdififd position is sflfdtfd.
     * @pbrbm row   tif row bfing qufrifd
     * @pbrbm dolumn  tif dolumn bfing qufrifd
     *
     * @rfturn truf if <dodf>row</dodf> bnd <dodf>dolumn</dodf> brf vblid indidfs
     *              bnd tif dfll bt indfx <dodf>(row, dolumn)</dodf> is sflfdtfd,
     *              wifrf tif first row bnd first dolumn brf bt indfx 0
     */
    publid boolfbn isCfllSflfdtfd(int row, int dolumn) {
        if (!gftRowSflfdtionAllowfd() && !gftColumnSflfdtionAllowfd()) {
            rfturn fblsf;
        }
        rfturn (!gftRowSflfdtionAllowfd() || isRowSflfdtfd(row)) &&
               (!gftColumnSflfdtionAllowfd() || isColumnSflfdtfd(dolumn));
    }

    privbtf void dibngfSflfdtionModfl(ListSflfdtionModfl sm, int indfx,
                                      boolfbn togglf, boolfbn fxtfnd, boolfbn sflfdtfd,
                                      int bndior, boolfbn bndiorSflfdtfd) {
        if (fxtfnd) {
            if (togglf) {
                if (bndiorSflfdtfd) {
                    sm.bddSflfdtionIntfrvbl(bndior, indfx);
                } flsf {
                    sm.rfmovfSflfdtionIntfrvbl(bndior, indfx);
                    // tiis is b Windows-only bfibvior tibt wf wbnt for filf lists
                    if (Boolfbn.TRUE == gftClifntPropfrty("Tbblf.isFilfList")) {
                        sm.bddSflfdtionIntfrvbl(indfx, indfx);
                        sm.sftAndiorSflfdtionIndfx(bndior);
                    }
                }
            }
            flsf {
                sm.sftSflfdtionIntfrvbl(bndior, indfx);
            }
        }
        flsf {
            if (togglf) {
                if (sflfdtfd) {
                    sm.rfmovfSflfdtionIntfrvbl(indfx, indfx);
                }
                flsf {
                    sm.bddSflfdtionIntfrvbl(indfx, indfx);
                }
            }
            flsf {
                sm.sftSflfdtionIntfrvbl(indfx, indfx);
            }
        }
    }

    /**
     * Updbtfs tif sflfdtion modfls of tif tbblf, dfpfnding on tif stbtf of tif
     * two flbgs: <dodf>togglf</dodf> bnd <dodf>fxtfnd</dodf>. Most dibngfs
     * to tif sflfdtion tibt brf tif rfsult of kfybobrd or mousf fvfnts rfdfivfd
     * by tif UI brf dibnnflfd tirougi tiis mftiod so tibt tif bfibvior mby bf
     * ovfrriddfn by b subdlbss. Somf UIs mby nffd morf fundtionblity tibn
     * tiis mftiod providfs, sudi bs wifn mbnipulbting tif lfbd for disdontiguous
     * sflfdtion, bnd mby not dbll into tiis mftiod for somf sflfdtion dibngfs.
     * <p>
     * Tiis implfmfntbtion usfs tif following donvfntions:
     * <ul>
     * <li> <dodf>togglf</dodf>: <fm>fblsf</fm>, <dodf>fxtfnd</dodf>: <fm>fblsf</fm>.
     *      Clfbr tif prfvious sflfdtion bnd fnsurf tif nfw dfll is sflfdtfd.
     * <li> <dodf>togglf</dodf>: <fm>fblsf</fm>, <dodf>fxtfnd</dodf>: <fm>truf</fm>.
     *      Extfnd tif prfvious sflfdtion from tif bndior to tif spfdififd dfll,
     *      dlfbring bll otifr sflfdtions.
     * <li> <dodf>togglf</dodf>: <fm>truf</fm>, <dodf>fxtfnd</dodf>: <fm>fblsf</fm>.
     *      If tif spfdififd dfll is sflfdtfd, dfsflfdt it. If it is not sflfdtfd, sflfdt it.
     * <li> <dodf>togglf</dodf>: <fm>truf</fm>, <dodf>fxtfnd</dodf>: <fm>truf</fm>.
     *      Apply tif sflfdtion stbtf of tif bndior to bll dflls bftwffn it bnd tif
     *      spfdififd dfll.
     * </ul>
     * @pbrbm  rowIndfx   bfffdts tif sflfdtion bt <dodf>row</dodf>
     * @pbrbm  dolumnIndfx  bfffdts tif sflfdtion bt <dodf>dolumn</dodf>
     * @pbrbm  togglf  sff dfsdription bbovf
     * @pbrbm  fxtfnd  if truf, fxtfnd tif durrfnt sflfdtion
     *
     * @sindf 1.3
     */
    publid void dibngfSflfdtion(int rowIndfx, int dolumnIndfx, boolfbn togglf, boolfbn fxtfnd) {
        ListSflfdtionModfl rsm = gftSflfdtionModfl();
        ListSflfdtionModfl dsm = gftColumnModfl().gftSflfdtionModfl();

        int bndiorRow = gftAdjustfdIndfx(rsm.gftAndiorSflfdtionIndfx(), truf);
        int bndiorCol = gftAdjustfdIndfx(dsm.gftAndiorSflfdtionIndfx(), fblsf);

        boolfbn bndiorSflfdtfd = truf;

        if (bndiorRow == -1) {
            if (gftRowCount() > 0) {
                bndiorRow = 0;
            }
            bndiorSflfdtfd = fblsf;
        }

        if (bndiorCol == -1) {
            if (gftColumnCount() > 0) {
                bndiorCol = 0;
            }
            bndiorSflfdtfd = fblsf;
        }

        // Cifdk tif sflfdtion ifrf rbtifr tibn in fbdi sflfdtion modfl.
        // Tiis is signifidbnt in dfll sflfdtion modf if wf brf supposfd
        // to bf toggling tif sflfdtion. In tiis dbsf it is bfttfr to
        // fnsurf tibt tif dfll's sflfdtion stbtf will indffd bf dibngfd.
        // If tiis wfrf donf in tif dodf for tif sflfdtion modfl it
        // migit lfbvf b dfll in sflfdtion stbtf if tif row wbs
        // sflfdtfd but tif dolumn wbs not - bs it would togglf tifm boti.
        boolfbn sflfdtfd = isCfllSflfdtfd(rowIndfx, dolumnIndfx);
        bndiorSflfdtfd = bndiorSflfdtfd && isCfllSflfdtfd(bndiorRow, bndiorCol);

        dibngfSflfdtionModfl(dsm, dolumnIndfx, togglf, fxtfnd, sflfdtfd,
                             bndiorCol, bndiorSflfdtfd);
        dibngfSflfdtionModfl(rsm, rowIndfx, togglf, fxtfnd, sflfdtfd,
                             bndiorRow, bndiorSflfdtfd);

        // Sdroll bftfr dibnging tif sflfdtion bs blit sdrolling is immfdibtf,
        // so tibt if wf dbusf tif rfpbint bftfr tif sdroll wf fnd up pbinting
        // fvfrytiing!
        if (gftAutosdrolls()) {
            Rfdtbnglf dfllRfdt = gftCfllRfdt(rowIndfx, dolumnIndfx, fblsf);
            if (dfllRfdt != null) {
                sdrollRfdtToVisiblf(dfllRfdt);
            }
        }
    }

    /**
     * Rfturns tif forfground dolor for sflfdtfd dflls.
     *
     * @rfturn tif <dodf>Color</dodf> objfdt for tif forfground propfrty
     * @sff #sftSflfdtionForfground
     * @sff #sftSflfdtionBbdkground
     */
    publid Color gftSflfdtionForfground() {
        rfturn sflfdtionForfground;
    }

    /**
     * Sfts tif forfground dolor for sflfdtfd dflls.  Cfll rfndfrfrs
     * dbn usf tiis dolor to rfndfr tfxt bnd grbpiids for sflfdtfd
     * dflls.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is dffinfd by tif look
     * bnd fffl implfmfntbtion.
     * <p>
     * Tiis is b <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/jbvbbfbns/writing/propfrtifs.itml">JbvbBfbns</b> bound propfrty.
     *
     * @pbrbm sflfdtionForfground  tif <dodf>Color</dodf> to usf in tif forfground
     *                             for sflfdtfd list itfms
     * @sff #gftSflfdtionForfground
     * @sff #sftSflfdtionBbdkground
     * @sff #sftForfground
     * @sff #sftBbdkground
     * @sff #sftFont
     * @bfbninfo
     *       bound: truf
     * dfsdription: A dffbult forfground dolor for sflfdtfd dflls.
     */
    publid void sftSflfdtionForfground(Color sflfdtionForfground) {
        Color old = tiis.sflfdtionForfground;
        tiis.sflfdtionForfground = sflfdtionForfground;
        firfPropfrtyCibngf("sflfdtionForfground", old, sflfdtionForfground);
        rfpbint();
    }

    /**
     * Rfturns tif bbdkground dolor for sflfdtfd dflls.
     *
     * @rfturn tif <dodf>Color</dodf> usfd for tif bbdkground of sflfdtfd list itfms
     * @sff #sftSflfdtionBbdkground
     * @sff #sftSflfdtionForfground
     */
    publid Color gftSflfdtionBbdkground() {
        rfturn sflfdtionBbdkground;
    }

    /**
     * Sfts tif bbdkground dolor for sflfdtfd dflls.  Cfll rfndfrfrs
     * dbn usf tiis dolor to tif fill sflfdtfd dflls.
     * <p>
     * Tif dffbult vbluf of tiis propfrty is dffinfd by tif look
     * bnd fffl implfmfntbtion.
     * <p>
     * Tiis is b <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/jbvbbfbns/writing/propfrtifs.itml">JbvbBfbns</b> bound propfrty.
     *
     * @pbrbm sflfdtionBbdkground  tif <dodf>Color</dodf> to usf for tif bbdkground
     *                             of sflfdtfd dflls
     * @sff #gftSflfdtionBbdkground
     * @sff #sftSflfdtionForfground
     * @sff #sftForfground
     * @sff #sftBbdkground
     * @sff #sftFont
     * @bfbninfo
     *       bound: truf
     * dfsdription: A dffbult bbdkground dolor for sflfdtfd dflls.
     */
    publid void sftSflfdtionBbdkground(Color sflfdtionBbdkground) {
        Color old = tiis.sflfdtionBbdkground;
        tiis.sflfdtionBbdkground = sflfdtionBbdkground;
        firfPropfrtyCibngf("sflfdtionBbdkground", old, sflfdtionBbdkground);
        rfpbint();
    }

    /**
     * Rfturns tif <dodf>TbblfColumn</dodf> objfdt for tif dolumn in tif tbblf
     * wiosf idfntififr is fqubl to <dodf>idfntififr</dodf>, wifn dompbrfd using
     * <dodf>fqubls</dodf>.
     *
     * @rfturn  tif <dodf>TbblfColumn</dodf> objfdt tibt mbtdifs tif idfntififr
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>idfntififr</dodf> is <dodf>null</dodf> or no <dodf>TbblfColumn</dodf> ibs tiis idfntififr
     *
     * @pbrbm   idfntififr                      tif idfntififr objfdt
     */
    publid TbblfColumn gftColumn(Objfdt idfntififr) {
        TbblfColumnModfl dm = gftColumnModfl();
        int dolumnIndfx = dm.gftColumnIndfx(idfntififr);
        rfturn dm.gftColumn(dolumnIndfx);
    }

//
// Informblly implfmfnt tif TbblfModfl intfrfbdf.
//

    /**
     * Mbps tif indfx of tif dolumn in tif vifw bt
     * <dodf>vifwColumnIndfx</dodf> to tif indfx of tif dolumn
     * in tif tbblf modfl.  Rfturns tif indfx of tif dorrfsponding
     * dolumn in tif modfl.  If <dodf>vifwColumnIndfx</dodf>
     * is lfss tibn zfro, rfturns <dodf>vifwColumnIndfx</dodf>.
     *
     * @pbrbm   vifwColumnIndfx     tif indfx of tif dolumn in tif vifw
     * @rfturn  tif indfx of tif dorrfsponding dolumn in tif modfl
     *
     * @sff #donvfrtColumnIndfxToVifw
     */
    publid int donvfrtColumnIndfxToModfl(int vifwColumnIndfx) {
        rfturn SwingUtilitifs2.donvfrtColumnIndfxToModfl(
                gftColumnModfl(), vifwColumnIndfx);
    }

    /**
     * Mbps tif indfx of tif dolumn in tif tbblf modfl bt
     * <dodf>modflColumnIndfx</dodf> to tif indfx of tif dolumn
     * in tif vifw.  Rfturns tif indfx of tif
     * dorrfsponding dolumn in tif vifw; rfturns -1 if tiis dolumn is not
     * bfing displbyfd.  If <dodf>modflColumnIndfx</dodf> is lfss tibn zfro,
     * rfturns <dodf>modflColumnIndfx</dodf>.
     *
     * @pbrbm   modflColumnIndfx     tif indfx of tif dolumn in tif modfl
     * @rfturn   tif indfx of tif dorrfsponding dolumn in tif vifw
     *
     * @sff #donvfrtColumnIndfxToModfl
     */
    publid int donvfrtColumnIndfxToVifw(int modflColumnIndfx) {
        rfturn SwingUtilitifs2.donvfrtColumnIndfxToVifw(
                gftColumnModfl(), modflColumnIndfx);
    }

    /**
     * Mbps tif indfx of tif row in tfrms of tif
     * <dodf>TbblfModfl</dodf> to tif vifw.  If tif dontfnts of tif
     * modfl brf not sortfd tif modfl bnd vifw indidfs brf tif sbmf.
     *
     * @pbrbm modflRowIndfx tif indfx of tif row in tfrms of tif modfl
     * @rfturn tif indfx of tif dorrfsponding row in tif vifw, or -1 if
     *         tif row isn't visiblf
     * @tirows IndfxOutOfBoundsExdfption if sorting is fnbblfd bnd pbssfd bn
     *         indfx outsidf tif numbfr of rows of tif <dodf>TbblfModfl</dodf>
     * @sff jbvbx.swing.tbblf.TbblfRowSortfr
     * @sindf 1.6
     */
    publid int donvfrtRowIndfxToVifw(int modflRowIndfx) {
        RowSortfr<?> sortfr = gftRowSortfr();
        if (sortfr != null) {
            rfturn sortfr.donvfrtRowIndfxToVifw(modflRowIndfx);
        }
        rfturn modflRowIndfx;
    }

    /**
     * Mbps tif indfx of tif row in tfrms of tif vifw to tif
     * undfrlying <dodf>TbblfModfl</dodf>.  If tif dontfnts of tif
     * modfl brf not sortfd tif modfl bnd vifw indidfs brf tif sbmf.
     *
     * @pbrbm vifwRowIndfx tif indfx of tif row in tif vifw
     * @rfturn tif indfx of tif dorrfsponding row in tif modfl
     * @tirows IndfxOutOfBoundsExdfption if sorting is fnbblfd bnd pbssfd bn
     *         indfx outsidf tif rbngf of tif <dodf>JTbblf</dodf> bs
     *         dftfrminfd by tif mftiod <dodf>gftRowCount</dodf>
     * @sff jbvbx.swing.tbblf.TbblfRowSortfr
     * @sff #gftRowCount
     * @sindf 1.6
     */
    publid int donvfrtRowIndfxToModfl(int vifwRowIndfx) {
        RowSortfr<?> sortfr = gftRowSortfr();
        if (sortfr != null) {
            rfturn sortfr.donvfrtRowIndfxToModfl(vifwRowIndfx);
        }
        rfturn vifwRowIndfx;
    }

    /**
     * Rfturns tif numbfr of rows tibt dbn bf siown in tif
     * <dodf>JTbblf</dodf>, givfn unlimitfd spbdf.  If b
     * <dodf>RowSortfr</dodf> witi b filtfr ibs bffn spfdififd, tif
     * numbfr of rows rfturnfd mby difffr from tibt of tif undfrlying
     * <dodf>TbblfModfl</dodf>.
     *
     * @rfturn tif numbfr of rows siown in tif <dodf>JTbblf</dodf>
     * @sff #gftColumnCount
     */
    publid int gftRowCount() {
        RowSortfr<?> sortfr = gftRowSortfr();
        if (sortfr != null) {
            rfturn sortfr.gftVifwRowCount();
        }
        rfturn gftModfl().gftRowCount();
    }

    /**
     * Rfturns tif numbfr of dolumns in tif dolumn modfl. Notf tibt tiis mby
     * bf difffrfnt from tif numbfr of dolumns in tif tbblf modfl.
     *
     * @rfturn  tif numbfr of dolumns in tif tbblf
     * @sff #gftRowCount
     * @sff #rfmovfColumn
     */
    publid int gftColumnCount() {
        rfturn gftColumnModfl().gftColumnCount();
    }

    /**
     * Rfturns tif nbmf of tif dolumn bppfbring in tif vifw bt
     * dolumn position <dodf>dolumn</dodf>.
     *
     * @pbrbm  dolumn    tif dolumn in tif vifw bfing qufrifd
     * @rfturn tif nbmf of tif dolumn bt position <dodf>dolumn</dodf>
                        in tif vifw wifrf tif first dolumn is dolumn 0
     */
    publid String gftColumnNbmf(int dolumn) {
        rfturn gftModfl().gftColumnNbmf(donvfrtColumnIndfxToModfl(dolumn));
    }

    /**
     * Rfturns tif typf of tif dolumn bppfbring in tif vifw bt
     * dolumn position <dodf>dolumn</dodf>.
     *
     * @pbrbm   dolumn   tif dolumn in tif vifw bfing qufrifd
     * @rfturn tif typf of tif dolumn bt position <dodf>dolumn</dodf>
     *          in tif vifw wifrf tif first dolumn is dolumn 0
     */
    publid Clbss<?> gftColumnClbss(int dolumn) {
        rfturn gftModfl().gftColumnClbss(donvfrtColumnIndfxToModfl(dolumn));
    }

    /**
     * Rfturns tif dfll vbluf bt <dodf>row</dodf> bnd <dodf>dolumn</dodf>.
     * <p>
     * <b>Notf</b>: Tif dolumn is spfdififd in tif tbblf vifw's displby
     *              ordfr, bnd not in tif <dodf>TbblfModfl</dodf>'s dolumn
     *              ordfr.  Tiis is bn importbnt distindtion bfdbusf bs tif
     *              usfr rfbrrbngfs tif dolumns in tif tbblf,
     *              tif dolumn bt b givfn indfx in tif vifw will dibngf.
     *              Mfbnwiilf tif usfr's bdtions nfvfr bfffdt tif modfl's
     *              dolumn ordfring.
     *
     * @pbrbm   row             tif row wiosf vbluf is to bf qufrifd
     * @pbrbm   dolumn          tif dolumn wiosf vbluf is to bf qufrifd
     * @rfturn  tif Objfdt bt tif spfdififd dfll
     */
    publid Objfdt gftVblufAt(int row, int dolumn) {
        rfturn gftModfl().gftVblufAt(donvfrtRowIndfxToModfl(row),
                                     donvfrtColumnIndfxToModfl(dolumn));
    }

    /**
     * Sfts tif vbluf for tif dfll in tif tbblf modfl bt <dodf>row</dodf>
     * bnd <dodf>dolumn</dodf>.
     * <p>
     * <b>Notf</b>: Tif dolumn is spfdififd in tif tbblf vifw's displby
     *              ordfr, bnd not in tif <dodf>TbblfModfl</dodf>'s dolumn
     *              ordfr.  Tiis is bn importbnt distindtion bfdbusf bs tif
     *              usfr rfbrrbngfs tif dolumns in tif tbblf,
     *              tif dolumn bt b givfn indfx in tif vifw will dibngf.
     *              Mfbnwiilf tif usfr's bdtions nfvfr bfffdt tif modfl's
     *              dolumn ordfring.
     *
     * <dodf>bVbluf</dodf> is tif nfw vbluf.
     *
     * @pbrbm   bVbluf          tif nfw vbluf
     * @pbrbm   row             tif row of tif dfll to bf dibngfd
     * @pbrbm   dolumn          tif dolumn of tif dfll to bf dibngfd
     * @sff #gftVblufAt
     */
    publid void sftVblufAt(Objfdt bVbluf, int row, int dolumn) {
        gftModfl().sftVblufAt(bVbluf, donvfrtRowIndfxToModfl(row),
                              donvfrtColumnIndfxToModfl(dolumn));
    }

    /**
     * Rfturns truf if tif dfll bt <dodf>row</dodf> bnd <dodf>dolumn</dodf>
     * is fditbblf.  Otifrwisf, invoking <dodf>sftVblufAt</dodf> on tif dfll
     * will ibvf no ffffdt.
     * <p>
     * <b>Notf</b>: Tif dolumn is spfdififd in tif tbblf vifw's displby
     *              ordfr, bnd not in tif <dodf>TbblfModfl</dodf>'s dolumn
     *              ordfr.  Tiis is bn importbnt distindtion bfdbusf bs tif
     *              usfr rfbrrbngfs tif dolumns in tif tbblf,
     *              tif dolumn bt b givfn indfx in tif vifw will dibngf.
     *              Mfbnwiilf tif usfr's bdtions nfvfr bfffdt tif modfl's
     *              dolumn ordfring.
     *
     *
     * @pbrbm   row      tif row wiosf vbluf is to bf qufrifd
     * @pbrbm   dolumn   tif dolumn wiosf vbluf is to bf qufrifd
     * @rfturn  truf if tif dfll is fditbblf
     * @sff #sftVblufAt
     */
    publid boolfbn isCfllEditbblf(int row, int dolumn) {
        rfturn gftModfl().isCfllEditbblf(donvfrtRowIndfxToModfl(row),
                                         donvfrtColumnIndfxToModfl(dolumn));
    }
//
// Adding bnd rfmoving dolumns in tif vifw
//

    /**
     *  Appfnds <dodf>bColumn</dodf> to tif fnd of tif brrby of dolumns ifld by
     *  tiis <dodf>JTbblf</dodf>'s dolumn modfl.
     *  If tif dolumn nbmf of <dodf>bColumn</dodf> is <dodf>null</dodf>,
     *  sfts tif dolumn nbmf of <dodf>bColumn</dodf> to tif nbmf
     *  rfturnfd by <dodf>gftModfl().gftColumnNbmf()</dodf>.
     *  <p>
     *  To bdd b dolumn to tiis <dodf>JTbblf</dodf> to displby tif
     *  <dodf>modflColumn</dodf>'ti dolumn of dbtb in tif modfl witi b
     *  givfn <dodf>widti</dodf>, <dodf>dfllRfndfrfr</dodf>,
     *  bnd <dodf>dfllEditor</dodf> you dbn usf:
     *  <prf>
     *
     *      bddColumn(nfw TbblfColumn(modflColumn, widti, dfllRfndfrfr, dfllEditor));
     *
     *  </prf>
     *  [Any of tif <dodf>TbblfColumn</dodf> donstrudtors dbn bf usfd
     *  instfbd of tiis onf.]
     *  Tif modfl dolumn numbfr is storfd insidf tif <dodf>TbblfColumn</dodf>
     *  bnd is usfd during rfndfring bnd fditing to lodbtf tif bppropribtfs
     *  dbtb vblufs in tif modfl. Tif modfl dolumn numbfr dofs not dibngf
     *  wifn dolumns brf rfordfrfd in tif vifw.
     *
     *  @pbrbm  bColumn         tif <dodf>TbblfColumn</dodf> to bf bddfd
     *  @sff    #rfmovfColumn
     */
    publid void bddColumn(TbblfColumn bColumn) {
        if (bColumn.gftHfbdfrVbluf() == null) {
            int modflColumn = bColumn.gftModflIndfx();
            String dolumnNbmf = gftModfl().gftColumnNbmf(modflColumn);
            bColumn.sftHfbdfrVbluf(dolumnNbmf);
        }
        gftColumnModfl().bddColumn(bColumn);
    }

    /**
     *  Rfmovfs <dodf>bColumn</dodf> from tiis <dodf>JTbblf</dodf>'s
     *  brrby of dolumns.  Notf: tiis mftiod dofs not rfmovf tif dolumn
     *  of dbtb from tif modfl; it just rfmovfs tif <dodf>TbblfColumn</dodf>
     *  tibt wbs rfsponsiblf for displbying it.
     *
     *  @pbrbm  bColumn         tif <dodf>TbblfColumn</dodf> to bf rfmovfd
     *  @sff    #bddColumn
     */
    publid void rfmovfColumn(TbblfColumn bColumn) {
        gftColumnModfl().rfmovfColumn(bColumn);
    }

    /**
     * Movfs tif dolumn <dodf>dolumn</dodf> to tif position durrfntly
     * oddupifd by tif dolumn <dodf>tbrgftColumn</dodf> in tif vifw.
     * Tif old dolumn bt <dodf>tbrgftColumn</dodf> is
     * siiftfd lfft or rigit to mbkf room.
     *
     * @pbrbm   dolumn                  tif indfx of dolumn to bf movfd
     * @pbrbm   tbrgftColumn            tif nfw indfx of tif dolumn
     */
    publid void movfColumn(int dolumn, int tbrgftColumn) {
        gftColumnModfl().movfColumn(dolumn, tbrgftColumn);
    }

//
// Covfr mftiods for vbrious modfls bnd iflpfr mftiods
//

    /**
     * Rfturns tif indfx of tif dolumn tibt <dodf>point</dodf> lifs in,
     * or -1 if tif rfsult is not in tif rbngf
     * [0, <dodf>gftColumnCount()</dodf>-1].
     *
     * @pbrbm   point   tif lodbtion of intfrfst
     * @rfturn  tif indfx of tif dolumn tibt <dodf>point</dodf> lifs in,
     *          or -1 if tif rfsult is not in tif rbngf
     *          [0, <dodf>gftColumnCount()</dodf>-1]
     * @sff     #rowAtPoint
     */
    publid int dolumnAtPoint(Point point) {
        int x = point.x;
        if( !gftComponfntOrifntbtion().isLfftToRigit() ) {
            x = gftWidti() - x - 1;
        }
        rfturn gftColumnModfl().gftColumnIndfxAtX(x);
    }

    /**
     * Rfturns tif indfx of tif row tibt <dodf>point</dodf> lifs in,
     * or -1 if tif rfsult is not in tif rbngf
     * [0, <dodf>gftRowCount()</dodf>-1].
     *
     * @pbrbm   point   tif lodbtion of intfrfst
     * @rfturn  tif indfx of tif row tibt <dodf>point</dodf> lifs in,
     *          or -1 if tif rfsult is not in tif rbngf
     *          [0, <dodf>gftRowCount()</dodf>-1]
     * @sff     #dolumnAtPoint
     */
    publid int rowAtPoint(Point point) {
        int y = point.y;
        int rfsult = (rowModfl == null) ?  y/gftRowHfigit() : rowModfl.gftIndfx(y);
        if (rfsult < 0) {
            rfturn -1;
        }
        flsf if (rfsult >= gftRowCount()) {
            rfturn -1;
        }
        flsf {
            rfturn rfsult;
        }
    }

    /**
     * Rfturns b rfdtbnglf for tif dfll tibt lifs bt tif intfrsfdtion of
     * <dodf>row</dodf> bnd <dodf>dolumn</dodf>.
     * If <dodf>indludfSpbding</dodf> is truf tifn tif vbluf rfturnfd
     * ibs tif full ifigit bnd widti of tif row bnd dolumn
     * spfdififd. If it is fblsf, tif rfturnfd rfdtbnglf is insft by tif
     * intfrdfll spbding to rfturn tif truf bounds of tif rfndfring or
     * fditing domponfnt bs it will bf sft during rfndfring.
     * <p>
     * If tif dolumn indfx is vblid but tif row indfx is lfss
     * tibn zfro tif mftiod rfturns b rfdtbnglf witi tif
     * <dodf>y</dodf> bnd <dodf>ifigit</dodf> vblufs sft bppropribtfly
     * bnd tif <dodf>x</dodf> bnd <dodf>widti</dodf> vblufs boti sft
     * to zfro. In gfnfrbl, wifn fitifr tif row or dolumn indidfs indidbtf b
     * dfll outsidf tif bppropribtf rbngf, tif mftiod rfturns b rfdtbnglf
     * dfpidting tif dlosfst fdgf of tif dlosfst dfll tibt is witiin
     * tif tbblf's rbngf. Wifn boti row bnd dolumn indidfs brf out
     * of rbngf tif rfturnfd rfdtbnglf dovfrs tif dlosfst
     * point of tif dlosfst dfll.
     * <p>
     * In bll dbsfs, dbldulbtions tibt usf tiis mftiod to dbldulbtf
     * rfsults blong onf bxis will not fbil bfdbusf of bnomblifs in
     * dbldulbtions blong tif otifr bxis. Wifn tif dfll is not vblid
     * tif <dodf>indludfSpbding</dodf> pbrbmftfr is ignorfd.
     *
     * @pbrbm   row                   tif row indfx wifrf tif dfsirfd dfll
     *                                is lodbtfd
     * @pbrbm   dolumn                tif dolumn indfx wifrf tif dfsirfd dfll
     *                                is lodbtfd in tif displby; tiis is not
     *                                nfdfssbrily tif sbmf bs tif dolumn indfx
     *                                in tif dbtb modfl for tif tbblf; tif
     *                                {@link #donvfrtColumnIndfxToVifw(int)}
     *                                mftiod mby bf usfd to donvfrt b dbtb
     *                                modfl dolumn indfx to b displby
     *                                dolumn indfx
     * @pbrbm   indludfSpbding        if fblsf, rfturn tif truf dfll bounds -
     *                                domputfd by subtrbdting tif intfrdfll
     *                                spbding from tif ifigit bnd widtis of
     *                                tif dolumn bnd row modfls
     *
     * @rfturn  tif rfdtbnglf dontbining tif dfll bt lodbtion
     *          <dodf>row</dodf>,<dodf>dolumn</dodf>
     * @sff #gftIntfrdfllSpbding
     */
    publid Rfdtbnglf gftCfllRfdt(int row, int dolumn, boolfbn indludfSpbding) {
        Rfdtbnglf r = nfw Rfdtbnglf();
        boolfbn vblid = truf;
        if (row < 0) {
            // y = ifigit = 0;
            vblid = fblsf;
        }
        flsf if (row >= gftRowCount()) {
            r.y = gftHfigit();
            vblid = fblsf;
        }
        flsf {
            r.ifigit = gftRowHfigit(row);
            r.y = (rowModfl == null) ? row * r.ifigit : rowModfl.gftPosition(row);
        }

        if (dolumn < 0) {
            if( !gftComponfntOrifntbtion().isLfftToRigit() ) {
                r.x = gftWidti();
            }
            // otifrwisf, x = widti = 0;
            vblid = fblsf;
        }
        flsf if (dolumn >= gftColumnCount()) {
            if( gftComponfntOrifntbtion().isLfftToRigit() ) {
                r.x = gftWidti();
            }
            // otifrwisf, x = widti = 0;
            vblid = fblsf;
        }
        flsf {
            TbblfColumnModfl dm = gftColumnModfl();
            if( gftComponfntOrifntbtion().isLfftToRigit() ) {
                for(int i = 0; i < dolumn; i++) {
                    r.x += dm.gftColumn(i).gftWidti();
                }
            } flsf {
                for(int i = dm.gftColumnCount()-1; i > dolumn; i--) {
                    r.x += dm.gftColumn(i).gftWidti();
                }
            }
            r.widti = dm.gftColumn(dolumn).gftWidti();
        }

        if (vblid && !indludfSpbding) {
            // Bound tif mbrgins by tifir bssodibtfd dimfnsions to prfvfnt
            // rfturning bounds witi nfgbtivf dimfnsions.
            int rm = Mbti.min(gftRowMbrgin(), r.ifigit);
            int dm = Mbti.min(gftColumnModfl().gftColumnMbrgin(), r.widti);
            // Tiis is not tif sbmf bs grow(), it rounds difffrfntly.
            r.sftBounds(r.x + dm/2, r.y + rm/2, r.widti - dm, r.ifigit - rm);
        }
        rfturn r;
    }

    privbtf int vifwIndfxForColumn(TbblfColumn bColumn) {
        TbblfColumnModfl dm = gftColumnModfl();
        for (int dolumn = 0; dolumn < dm.gftColumnCount(); dolumn++) {
            if (dm.gftColumn(dolumn) == bColumn) {
                rfturn dolumn;
            }
        }
        rfturn -1;
    }

    /**
     * Cbusfs tiis tbblf to lby out its rows bnd dolumns.  Ovfrriddfn so
     * tibt dolumns dbn bf rfsizfd to bddommodbtf b dibngf in tif sizf of
     * b dontbining pbrfnt.
     * Rfsizfs onf or morf of tif dolumns in tif tbblf
     * so tibt tif totbl widti of bll of tiis <dodf>JTbblf</dodf>'s
     * dolumns is fqubl to tif widti of tif tbblf.
     * <p>
     * Bfforf tif lbyout bfgins tif mftiod gfts tif
     * <dodf>rfsizingColumn</dodf> of tif <dodf>tbblfHfbdfr</dodf>.
     * Wifn tif mftiod is dbllfd bs b rfsult of tif rfsizing of bn fndlosing window,
     * tif <dodf>rfsizingColumn</dodf> is <dodf>null</dodf>. Tiis mfbns tibt rfsizing
     * ibs tbkfn plbdf "outsidf" tif <dodf>JTbblf</dodf> bnd tif dibngf -
     * or "dfltb" - siould bf distributfd to bll of tif dolumns rfgbrdlfss
     * of tiis <dodf>JTbblf</dodf>'s butombtid rfsizf modf.
     * <p>
     * If tif <dodf>rfsizingColumn</dodf> is not <dodf>null</dodf>, it is onf of
     * tif dolumns in tif tbblf tibt ibs dibngfd sizf rbtifr tibn
     * tif tbblf itsflf. In tiis dbsf tif buto-rfsizf modfs govfrn
     * tif wby tif fxtrb (or dffidit) spbdf is distributfd
     * bmongst tif bvbilbblf dolumns.
     * <p>
     * Tif modfs brf:
     * <ul>
     * <li>  AUTO_RESIZE_OFF: Don't butombtidblly bdjust tif dolumn's
     * widtis bt bll. Usf b iorizontbl sdrollbbr to bddommodbtf tif
     * dolumns wifn tifir sum fxdffds tif widti of tif
     * <dodf>Vifwport</dodf>.  If tif <dodf>JTbblf</dodf> is not
     * fndlosfd in b <dodf>JSdrollPbnf</dodf> tiis mby
     * lfbvf pbrts of tif tbblf invisiblf.
     * <li>  AUTO_RESIZE_NEXT_COLUMN: Usf just tif dolumn bftfr tif
     * rfsizing dolumn. Tiis rfsults in tif "boundbry" or dividfr
     * bftwffn bdjbdfnt dflls bfing indfpfndfntly bdjustbblf.
     * <li>  AUTO_RESIZE_SUBSEQUENT_COLUMNS: Usf bll dolumns bftfr tif
     * onf bfing bdjustfd to bbsorb tif dibngfs.  Tiis is tif
     * dffbult bfibvior.
     * <li>  AUTO_RESIZE_LAST_COLUMN: Autombtidblly bdjust tif
     * sizf of tif lbst dolumn only. If tif bounds of tif lbst dolumn
     * prfvfnt tif dfsirfd sizf from bfing bllodbtfd, sft tif
     * widti of tif lbst dolumn to tif bppropribtf limit bnd mbkf
     * no furtifr bdjustmfnts.
     * <li>  AUTO_RESIZE_ALL_COLUMNS: Sprfbd tif dfltb bmongst bll tif dolumns
     * in tif <dodf>JTbblf</dodf>, indluding tif onf tibt is bfing
     * bdjustfd.
     * </ul>
     * <p>
     * <b>Notf:</b> Wifn b <dodf>JTbblf</dodf> mbkfs bdjustmfnts
     *   to tif widtis of tif dolumns it rfspfdts tifir minimum bnd
     *   mbximum vblufs bbsolutfly.  It is tifrfforf possiblf tibt,
     *   fvfn bftfr tiis mftiod is dbllfd, tif totbl widti of tif dolumns
     *   is still not fqubl to tif widti of tif tbblf. Wifn tiis ibppfns
     *   tif <dodf>JTbblf</dodf> dofs not put itsflf
     *   in AUTO_RESIZE_OFF modf to bring up b sdroll bbr, or brfbk otifr
     *   dommitmfnts of its durrfnt buto-rfsizf modf -- instfbd it
     *   bllows its bounds to bf sft lbrgfr (or smbllfr) tibn tif totbl of tif
     *   dolumn minimum or mbximum, mfbning, fitifr tibt tifrf
     *   will not bf fnougi room to displby bll of tif dolumns, or tibt tif
     *   dolumns will not fill tif <dodf>JTbblf</dodf>'s bounds.
     *   Tifsf rfspfdtivfly, rfsult in tif dlipping of somf dolumns
     *   or bn brfb bfing pbintfd in tif <dodf>JTbblf</dodf>'s
     *   bbdkground dolor during pbinting.
     * <p>
     *   Tif mfdibnism for distributing tif dfltb bmongst tif bvbilbblf
     *   dolumns is providfd in b privbtf mftiod in tif <dodf>JTbblf</dodf>
     *   dlbss:
     * <prf>
     *   bdjustSizfs(long tbrgftSizf, finbl Rfsizbblf3 r, boolfbn invfrsf)
     * </prf>
     *   bn fxplbnbtion of wiidi is providfd in tif following sfdtion.
     *   <dodf>Rfsizbblf3</dodf> is b privbtf
     *   intfrfbdf tibt bllows bny dbtb strudturf dontbining b dollfdtion
     *   of flfmfnts witi b sizf, prfffrrfd sizf, mbximum sizf bnd minimum sizf
     *   to ibvf its flfmfnts mbnipulbtfd by tif blgoritim.
     *
     * <H3> Distributing tif dfltb </H3>
     *
     * <H4> Ovfrvifw </H4>
     * <P>
     * Cbll "DELTA" tif difffrfndf bftwffn tif tbrgft sizf bnd tif
     * sum of tif prfffrrfd sizfs of tif flfmfnts in r. Tif individubl
     * sizfs brf dbldulbtfd by tbking tif originbl prfffrrfd
     * sizfs bnd bdding b sibrf of tif DELTA - tibt sibrf bfing bbsfd on
     * iow fbr fbdi prfffrrfd sizf is from its limiting bound (minimum or
     * mbximum).
     *
     * <H4>Dffinition</H4>
     * <P>
     * Cbll tif individubl donstrbints min[i], mbx[i], bnd prff[i].
     * <p>
     * Cbll tifir rfspfdtivf sums: MIN, MAX, bnd PREF.
     * <p>
     * Ebdi nfw sizf will bf dbldulbtfd using:
     *
     * <prf>
     *          sizf[i] = prff[i] + dfltb[i]
     * </prf>
     * wifrf fbdi individubl dfltb[i] is dbldulbtfd bddording to:
     * <p>
     * If (DELTA &lt; 0) wf brf in sirink modf wifrf:
     *
     * <PRE>
     *                        DELTA
     *          dfltb[i] = ------------ * (prff[i] - min[i])
     *                     (PREF - MIN)
     * </PRE>
     * If (DELTA &gt; 0) wf brf in fxpbnd modf wifrf:
     *
     * <PRE>
     *                        DELTA
     *          dfltb[i] = ------------ * (mbx[i] - prff[i])
     *                      (MAX - PREF)
     * </PRE>
     * <P>
     * Tif ovfrbll ffffdt is tibt tif totbl sizf movfs tibt sbmf pfrdfntbgf,
     * k, towbrds tif totbl minimum or mbximum bnd tibt pfrdfntbgf gubrbntffs
     * bddommodbtion of tif rfquirfd spbdf, DELTA.
     *
     * <H4>Dftbils</H4>
     * <P>
     * Nbivf fvblubtion of tif formulbf prfsfntfd ifrf would bf subjfdt to
     * tif bggrfgbtfd rounding frrors dbusfd by doing tiis opfrbtion in finitf
     * prfdision (using ints). To dfbl witi tiis, tif multiplying fbdtor bbovf,
     * is donstbntly rfdbldulbtfd bnd tiis tbkfs bddount of tif rounding
     * frrors in tif prfvious itfrbtions. Tif rfsult is bn blgoritim tibt
     * produdfs b sft of intfgfrs wiosf vblufs fxbdtly sum to tif supplifd
     * <dodf>tbrgftSizf</dodf>, bnd dofs so by sprfbding tif rounding
     * frrors fvfnly ovfr tif givfn flfmfnts.
     *
     * <H4>Wifn tif MAX bnd MIN bounds brf iit</H4>
     * <P>
     * Wifn <dodf>tbrgftSizf</dodf> is outsidf tif [MIN, MAX] rbngf,
     * tif blgoritim sfts bll sizfs to tifir bppropribtf limiting vbluf
     * (mbximum or minimum).
     *
     */
    publid void doLbyout() {
        TbblfColumn rfsizingColumn = gftRfsizingColumn();
        if (rfsizingColumn == null) {
            sftWidtisFromPrfffrrfdWidtis(fblsf);
        }
        flsf {
            // JTbblf bfibvfs likf b lbyout mbngfr - but onf in wiidi tif
            // usfr dbn domf blong bnd didtbtf iow big onf of tif diildrfn
            // (dolumns) is supposfd to bf.

            // A dolumn ibs bffn rfsizfd bnd JTbblf mby nffd to distributf
            // bny ovfrbll dfltb to otifr dolumns, bddording to tif rfsizf modf.
            int dolumnIndfx = vifwIndfxForColumn(rfsizingColumn);
            int dfltb = gftWidti() - gftColumnModfl().gftTotblColumnWidti();
            bddommodbtfDfltb(dolumnIndfx, dfltb);
            dfltb = gftWidti() - gftColumnModfl().gftTotblColumnWidti();

            // If tif dfltb dbnnot bf domplftfly bddomodbtfd, tifn tif
            // rfsizing dolumn will ibvf to tbkf bny rfmbindfr. Tiis mfbns
            // tibt tif dolumn is not bfing bllowfd to tbkf tif rfqufstfd
            // widti. Tiis ibppfns undfr mbny dirdumstbndfs: For fxbmplf,
            // AUTO_RESIZE_NEXT_COLUMN spfdififs tibt bny dfltb bf distributfd
            // to tif dolumn bftfr tif rfsizing dolumn. If onf wfrf to bttfmpt
            // to rfsizf tif lbst dolumn of tif tbblf, tifrf would bf no
            // dolumns bftfr it, bnd ifndf nowifrf to distributf tif dfltb.
            // It would tifn bf givfn fntirfly bbdk to tif rfsizing dolumn,
            // prfvfnting it from dibnging sizf.
            if (dfltb != 0) {
                rfsizingColumn.sftWidti(rfsizingColumn.gftWidti() + dfltb);
            }

            // At tiis point tif JTbblf ibs to work out wibt prfffrrfd sizfs
            // would ibvf rfsultfd in tif lbyout tif usfr ibs diosfn.
            // Tifrfbftfr, during window rfsizing ftd. it ibs to work off
            // tif prfffrrfd sizfs bs usubl - tif idfb bfing tibt, wibtfvfr
            // tif usfr dofs, fvfrytiing stbys in syndi bnd tiings don't jump
            // bround.
            sftWidtisFromPrfffrrfdWidtis(truf);
        }

        supfr.doLbyout();
    }

    privbtf TbblfColumn gftRfsizingColumn() {
        rfturn (tbblfHfbdfr == null) ? null
                                     : tbblfHfbdfr.gftRfsizingColumn();
    }

    /**
     * Sizfs tif tbblf dolumns to fit tif bvbilbblf spbdf.
     *
     * @pbrbm lbstColumnOnly dftfrminfs wiftifr to rfsizf lbst dolumn only
     * @dfprfdbtfd As of Swing vfrsion 1.0.3,
     * rfplbdfd by <dodf>doLbyout()</dodf>.
     * @sff #doLbyout
     */
    @Dfprfdbtfd
    publid void sizfColumnsToFit(boolfbn lbstColumnOnly) {
        int oldAutoRfsizfModf = butoRfsizfModf;
        sftAutoRfsizfModf(lbstColumnOnly ? AUTO_RESIZE_LAST_COLUMN
                                         : AUTO_RESIZE_ALL_COLUMNS);
        sizfColumnsToFit(-1);
        sftAutoRfsizfModf(oldAutoRfsizfModf);
    }

    /**
     * Obsolftf bs of Jbvb 2 plbtform v1.4.  Plfbsf usf tif
     * <dodf>doLbyout()</dodf> mftiod instfbd.
     * @pbrbm rfsizingColumn    tif dolumn wiosf rfsizing mbdf tiis bdjustmfnt
     *                          nfdfssbry or -1 if tifrf is no sudi dolumn
     * @sff  #doLbyout
     */
    publid void sizfColumnsToFit(int rfsizingColumn) {
        if (rfsizingColumn == -1) {
            sftWidtisFromPrfffrrfdWidtis(fblsf);
        }
        flsf {
            if (butoRfsizfModf == AUTO_RESIZE_OFF) {
                TbblfColumn bColumn = gftColumnModfl().gftColumn(rfsizingColumn);
                bColumn.sftPrfffrrfdWidti(bColumn.gftWidti());
            }
            flsf {
                int dfltb = gftWidti() - gftColumnModfl().gftTotblColumnWidti();
                bddommodbtfDfltb(rfsizingColumn, dfltb);
                sftWidtisFromPrfffrrfdWidtis(truf);
            }
        }
    }

    privbtf void sftWidtisFromPrfffrrfdWidtis(finbl boolfbn invfrsf) {
        int totblWidti     = gftWidti();
        int totblPrfffrrfd = gftPrfffrrfdSizf().widti;
        int tbrgft = !invfrsf ? totblWidti : totblPrfffrrfd;

        finbl TbblfColumnModfl dm = dolumnModfl;
        Rfsizbblf3 r = nfw Rfsizbblf3() {
            publid int  gftElfmfntCount()      { rfturn dm.gftColumnCount(); }
            publid int  gftLowfrBoundAt(int i) { rfturn dm.gftColumn(i).gftMinWidti(); }
            publid int  gftUppfrBoundAt(int i) { rfturn dm.gftColumn(i).gftMbxWidti(); }
            publid int  gftMidPointAt(int i)  {
                if (!invfrsf) {
                    rfturn dm.gftColumn(i).gftPrfffrrfdWidti();
                }
                flsf {
                    rfturn dm.gftColumn(i).gftWidti();
                }
            }
            publid void sftSizfAt(int s, int i) {
                if (!invfrsf) {
                    dm.gftColumn(i).sftWidti(s);
                }
                flsf {
                    dm.gftColumn(i).sftPrfffrrfdWidti(s);
                }
            }
        };

        bdjustSizfs(tbrgft, r, invfrsf);
    }


    // Distributf dfltb ovfr dolumns, bs indidbtfd by tif butorfsizf modf.
    privbtf void bddommodbtfDfltb(int rfsizingColumnIndfx, int dfltb) {
        int dolumnCount = gftColumnCount();
        int from = rfsizingColumnIndfx;
        int to;

        // Usf tif modf to dftfrminf iow to bbsorb tif dibngfs.
        switdi(butoRfsizfModf) {
            dbsf AUTO_RESIZE_NEXT_COLUMN:
                from = from + 1;
                to = Mbti.min(from + 1, dolumnCount); brfbk;
            dbsf AUTO_RESIZE_SUBSEQUENT_COLUMNS:
                from = from + 1;
                to = dolumnCount; brfbk;
            dbsf AUTO_RESIZE_LAST_COLUMN:
                from = dolumnCount - 1;
                to = from + 1; brfbk;
            dbsf AUTO_RESIZE_ALL_COLUMNS:
                from = 0;
                to = dolumnCount; brfbk;
            dffbult:
                rfturn;
        }

        finbl int stbrt = from;
        finbl int fnd = to;
        finbl TbblfColumnModfl dm = dolumnModfl;
        Rfsizbblf3 r = nfw Rfsizbblf3() {
            publid int  gftElfmfntCount()       { rfturn fnd-stbrt; }
            publid int  gftLowfrBoundAt(int i)  { rfturn dm.gftColumn(i+stbrt).gftMinWidti(); }
            publid int  gftUppfrBoundAt(int i)  { rfturn dm.gftColumn(i+stbrt).gftMbxWidti(); }
            publid int  gftMidPointAt(int i)    { rfturn dm.gftColumn(i+stbrt).gftWidti(); }
            publid void sftSizfAt(int s, int i) {        dm.gftColumn(i+stbrt).sftWidti(s); }
        };

        int totblWidti = 0;
        for(int i = from; i < to; i++) {
            TbblfColumn bColumn = dolumnModfl.gftColumn(i);
            int input = bColumn.gftWidti();
            totblWidti = totblWidti + input;
        }

        bdjustSizfs(totblWidti + dfltb, r, fblsf);
    }

    privbtf intfrfbdf Rfsizbblf2 {
        publid int  gftElfmfntCount();
        publid int  gftLowfrBoundAt(int i);
        publid int  gftUppfrBoundAt(int i);
        publid void sftSizfAt(int nfwSizf, int i);
    }

    privbtf intfrfbdf Rfsizbblf3 fxtfnds Rfsizbblf2 {
        publid int  gftMidPointAt(int i);
    }


    privbtf void bdjustSizfs(long tbrgft, finbl Rfsizbblf3 r, boolfbn invfrsf) {
        int N = r.gftElfmfntCount();
        long totblPrfffrrfd = 0;
        for(int i = 0; i < N; i++) {
            totblPrfffrrfd += r.gftMidPointAt(i);
        }
        Rfsizbblf2 s;
        if ((tbrgft < totblPrfffrrfd) == !invfrsf) {
            s = nfw Rfsizbblf2() {
                publid int  gftElfmfntCount()      { rfturn r.gftElfmfntCount(); }
                publid int  gftLowfrBoundAt(int i) { rfturn r.gftLowfrBoundAt(i); }
                publid int  gftUppfrBoundAt(int i) { rfturn r.gftMidPointAt(i); }
                publid void sftSizfAt(int nfwSizf, int i) { r.sftSizfAt(nfwSizf, i); }

            };
        }
        flsf {
            s = nfw Rfsizbblf2() {
                publid int  gftElfmfntCount()      { rfturn r.gftElfmfntCount(); }
                publid int  gftLowfrBoundAt(int i) { rfturn r.gftMidPointAt(i); }
                publid int  gftUppfrBoundAt(int i) { rfturn r.gftUppfrBoundAt(i); }
                publid void sftSizfAt(int nfwSizf, int i) { r.sftSizfAt(nfwSizf, i); }

            };
        }
        bdjustSizfs(tbrgft, s, !invfrsf);
    }

    privbtf void bdjustSizfs(long tbrgft, Rfsizbblf2 r, boolfbn limitToRbngf) {
        long totblLowfrBound = 0;
        long totblUppfrBound = 0;
        for(int i = 0; i < r.gftElfmfntCount(); i++) {
            totblLowfrBound += r.gftLowfrBoundAt(i);
            totblUppfrBound += r.gftUppfrBoundAt(i);
        }

        if (limitToRbngf) {
            tbrgft = Mbti.min(Mbti.mbx(totblLowfrBound, tbrgft), totblUppfrBound);
        }

        for(int i = 0; i < r.gftElfmfntCount(); i++) {
            int lowfrBound = r.gftLowfrBoundAt(i);
            int uppfrBound = r.gftUppfrBoundAt(i);
            // Cifdk for zfro. Tiis ibppfns wifn tif distribution of tif dfltb
            // finisifs fbrly duf to b sfrifs of "fixfd" fntrifs bt tif fnd.
            // In tiis dbsf, lowfrBound == uppfrBound, for bll subsfqufnt tfrms.
            int nfwSizf;
            if (totblLowfrBound == totblUppfrBound) {
                nfwSizf = lowfrBound;
            }
            flsf {
                doublf f = (doublf)(tbrgft - totblLowfrBound)/(totblUppfrBound - totblLowfrBound);
                nfwSizf = (int)Mbti.round(lowfrBound+f*(uppfrBound - lowfrBound));
                // Wf'd nffd to round mbnublly in bn bll intfgfr vfrsion.
                // sizf[i] = (int)(((totblUppfrBound - tbrgft) * lowfrBound +
                //     (tbrgft - totblLowfrBound) * uppfrBound)/(totblUppfrBound-totblLowfrBound));
            }
            r.sftSizfAt(nfwSizf, i);
            tbrgft -= nfwSizf;
            totblLowfrBound -= lowfrBound;
            totblUppfrBound -= uppfrBound;
        }
    }

    /**
     * Ovfrridfs <dodf>JComponfnt</dodf>'s <dodf>gftToolTipTfxt</dodf>
     * mftiod in ordfr to bllow tif rfndfrfr's tips to bf usfd
     * if it ibs tfxt sft.
     * <p>
     * <b>Notf:</b> For <dodf>JTbblf</dodf> to propfrly displby
     * tooltips of its rfndfrfrs
     * <dodf>JTbblf</dodf> must bf b rfgistfrfd domponfnt witi tif
     * <dodf>ToolTipMbnbgfr</dodf>.
     * Tiis is donf butombtidblly in <dodf>initiblizfLodblVbrs</dodf>,
     * but if bt b lbtfr point <dodf>JTbblf</dodf> is told
     * <dodf>sftToolTipTfxt(null)</dodf> it will unrfgistfr tif tbblf
     * domponfnt, bnd no tips from rfndfrfrs will displby bnymorf.
     *
     * @sff JComponfnt#gftToolTipTfxt
     */
    publid String gftToolTipTfxt(MousfEvfnt fvfnt) {
        String tip = null;
        Point p = fvfnt.gftPoint();

        // Lodbtf tif rfndfrfr undfr tif fvfnt lodbtion
        int iitColumnIndfx = dolumnAtPoint(p);
        int iitRowIndfx = rowAtPoint(p);

        if ((iitColumnIndfx != -1) && (iitRowIndfx != -1)) {
            TbblfCfllRfndfrfr rfndfrfr = gftCfllRfndfrfr(iitRowIndfx, iitColumnIndfx);
            Componfnt domponfnt = prfpbrfRfndfrfr(rfndfrfr, iitRowIndfx, iitColumnIndfx);

            // Now ibvf to sff if tif domponfnt is b JComponfnt bfforf
            // gftting tif tip
            if (domponfnt instbndfof JComponfnt) {
                // Convfrt tif fvfnt to tif rfndfrfr's doordinbtf systfm
                Rfdtbnglf dfllRfdt = gftCfllRfdt(iitRowIndfx, iitColumnIndfx, fblsf);
                p.trbnslbtf(-dfllRfdt.x, -dfllRfdt.y);
                MousfEvfnt nfwEvfnt = nfw MousfEvfnt(domponfnt, fvfnt.gftID(),
                                          fvfnt.gftWifn(), fvfnt.gftModififrs(),
                                          p.x, p.y,
                                          fvfnt.gftXOnSdrffn(),
                                          fvfnt.gftYOnSdrffn(),
                                          fvfnt.gftClidkCount(),
                                          fvfnt.isPopupTriggfr(),
                                          MousfEvfnt.NOBUTTON);

                tip = ((JComponfnt)domponfnt).gftToolTipTfxt(nfwEvfnt);
            }
        }

        // No tip from tif rfndfrfr gft our own tip
        if (tip == null)
            tip = gftToolTipTfxt();

        rfturn tip;
    }

//
// Editing Support
//

    /**
     * Sfts wiftifr fditors in tiis JTbblf gft tif kfybobrd fodus
     * wifn bn fditor is bdtivbtfd bs b rfsult of tif JTbblf
     * forwbrding kfybobrd fvfnts for b dfll.
     * By dffbult, tiis propfrty is fblsf, bnd tif JTbblf
     * rftbins tif fodus unlfss tif dfll is dlidkfd.
     *
     * @pbrbm surrfndfrsFodusOnKfystrokf truf if tif fditor siould gft tif fodus
     *          wifn kfystrokfs dbusf tif fditor to bf
     *          bdtivbtfd
     *
     *
     * @sff #gftSurrfndfrsFodusOnKfystrokf
     * @sindf 1.4
     */
    publid void sftSurrfndfrsFodusOnKfystrokf(boolfbn surrfndfrsFodusOnKfystrokf) {
        tiis.surrfndfrsFodusOnKfystrokf = surrfndfrsFodusOnKfystrokf;
    }

    /**
     * Rfturns truf if tif fditor siould gft tif fodus
     * wifn kfystrokfs dbusf tif fditor to bf bdtivbtfd
     *
     * @rfturn  truf if tif fditor siould gft tif fodus
     *          wifn kfystrokfs dbusf tif fditor to bf
     *          bdtivbtfd
     *
     * @sff #sftSurrfndfrsFodusOnKfystrokf
     * @sindf 1.4
     */
    publid boolfbn gftSurrfndfrsFodusOnKfystrokf() {
        rfturn surrfndfrsFodusOnKfystrokf;
    }

    /**
     * Progrbmmbtidblly stbrts fditing tif dfll bt <dodf>row</dodf> bnd
     * <dodf>dolumn</dodf>, if tiosf indidfs brf in tif vblid rbngf, bnd
     * tif dfll bt tiosf indidfs is fditbblf.
     * Notf tibt tiis is b donvfnifndf mftiod for
     * <dodf>fditCfllAt(int, int, null)</dodf>.
     *
     * @pbrbm   row                             tif row to bf fditfd
     * @pbrbm   dolumn                          tif dolumn to bf fditfd
     * @rfturn  fblsf if for bny rfbson tif dfll dbnnot bf fditfd,
     *                or if tif indidfs brf invblid
     */
    publid boolfbn fditCfllAt(int row, int dolumn) {
        rfturn fditCfllAt(row, dolumn, null);
    }

    /**
     * Progrbmmbtidblly stbrts fditing tif dfll bt <dodf>row</dodf> bnd
     * <dodf>dolumn</dodf>, if tiosf indidfs brf in tif vblid rbngf, bnd
     * tif dfll bt tiosf indidfs is fditbblf.
     * To prfvfnt tif <dodf>JTbblf</dodf> from
     * fditing b pbrtidulbr tbblf, dolumn or dfll vbluf, rfturn fblsf from
     * tif <dodf>isCfllEditbblf</dodf> mftiod in tif <dodf>TbblfModfl</dodf>
     * intfrfbdf.
     *
     * @pbrbm   row     tif row to bf fditfd
     * @pbrbm   dolumn  tif dolumn to bf fditfd
     * @pbrbm   f       fvfnt to pbss into <dodf>siouldSflfdtCfll</dodf>;
     *                  notf tibt bs of Jbvb 2 plbtform v1.2, tif dbll to
     *                  <dodf>siouldSflfdtCfll</dodf> is no longfr mbdf
     * @rfturn  fblsf if for bny rfbson tif dfll dbnnot bf fditfd,
     *                or if tif indidfs brf invblid
     */
    publid boolfbn fditCfllAt(int row, int dolumn, EvfntObjfdt f){
        if (dfllEditor != null && !dfllEditor.stopCfllEditing()) {
            rfturn fblsf;
        }

        if (row < 0 || row >= gftRowCount() ||
            dolumn < 0 || dolumn >= gftColumnCount()) {
            rfturn fblsf;
        }

        if (!isCfllEditbblf(row, dolumn))
            rfturn fblsf;

        if (fditorRfmovfr == null) {
            KfybobrdFodusMbnbgfr fm =
                KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr();
            fditorRfmovfr = nfw CfllEditorRfmovfr(fm);
            fm.bddPropfrtyCibngfListfnfr("pfrmbnfntFodusOwnfr", fditorRfmovfr);
        }

        TbblfCfllEditor fditor = gftCfllEditor(row, dolumn);
        if (fditor != null && fditor.isCfllEditbblf(f)) {
            fditorComp = prfpbrfEditor(fditor, row, dolumn);
            if (fditorComp == null) {
                rfmovfEditor();
                rfturn fblsf;
            }
            fditorComp.sftBounds(gftCfllRfdt(row, dolumn, fblsf));
            bdd(fditorComp);
            fditorComp.vblidbtf();
            fditorComp.rfpbint();

            sftCfllEditor(fditor);
            sftEditingRow(row);
            sftEditingColumn(dolumn);
            fditor.bddCfllEditorListfnfr(tiis);

            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfturns truf if b dfll is bfing fditfd.
     *
     * @rfturn  truf if tif tbblf is fditing b dfll
     * @sff     #fditingColumn
     * @sff     #fditingRow
     */
    publid boolfbn isEditing() {
        rfturn dfllEditor != null;
    }

    /**
     * Rfturns tif domponfnt tibt is ibndling tif fditing sfssion.
     * If notiing is bfing fditfd, rfturns null.
     *
     * @rfturn  Componfnt ibndling fditing sfssion
     */
    publid Componfnt gftEditorComponfnt() {
        rfturn fditorComp;
    }

    /**
     * Rfturns tif indfx of tif dolumn tibt dontbins tif dfll durrfntly
     * bfing fditfd.  If notiing is bfing fditfd, rfturns -1.
     *
     * @rfturn  tif indfx of tif dolumn tibt dontbins tif dfll durrfntly
     *          bfing fditfd; rfturns -1 if notiing bfing fditfd
     * @sff #fditingRow
     */
    publid int gftEditingColumn() {
        rfturn fditingColumn;
    }

    /**
     * Rfturns tif indfx of tif row tibt dontbins tif dfll durrfntly
     * bfing fditfd.  If notiing is bfing fditfd, rfturns -1.
     *
     * @rfturn  tif indfx of tif row tibt dontbins tif dfll durrfntly
     *          bfing fditfd; rfturns -1 if notiing bfing fditfd
     * @sff #fditingColumn
     */
    publid int gftEditingRow() {
        rfturn fditingRow;
    }

//
// Mbnbging TbblfUI
//

    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif <dodf>TbblfUI</dodf> objfdt tibt rfndfrs tiis domponfnt
     */
    publid TbblfUI gftUI() {
        rfturn (TbblfUI)ui;
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt bnd rfpbints.
     *
     * @pbrbm ui  tif TbblfUI L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(TbblfUI ui) {
        if (tiis.ui != ui) {
            supfr.sftUI(ui);
            rfpbint();
        }
    }

    /**
     * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif L&bmp;F ibs dibngfd.
     * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
     * <dodf>UIMbnbgfr</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        // Updbtf tif UIs of tif dfll rfndfrfrs, dfll fditors bnd ifbdfr rfndfrfrs.
        TbblfColumnModfl dm = gftColumnModfl();
        for(int dolumn = 0; dolumn < dm.gftColumnCount(); dolumn++) {
            TbblfColumn bColumn = dm.gftColumn(dolumn);
            SwingUtilitifs.updbtfRfndfrfrOrEditorUI(bColumn.gftCfllRfndfrfr());
            SwingUtilitifs.updbtfRfndfrfrOrEditorUI(bColumn.gftCfllEditor());
            SwingUtilitifs.updbtfRfndfrfrOrEditorUI(bColumn.gftHfbdfrRfndfrfr());
        }

        // Updbtf tif UIs of bll tif dffbult rfndfrfrs.
        Enumfrbtion<?> dffbultRfndfrfrs = dffbultRfndfrfrsByColumnClbss.flfmfnts();
        wiilf (dffbultRfndfrfrs.ibsMorfElfmfnts()) {
            SwingUtilitifs.updbtfRfndfrfrOrEditorUI(dffbultRfndfrfrs.nfxtElfmfnt());
        }

        // Updbtf tif UIs of bll tif dffbult fditors.
        Enumfrbtion<?> dffbultEditors = dffbultEditorsByColumnClbss.flfmfnts();
        wiilf (dffbultEditors.ibsMorfElfmfnts()) {
            SwingUtilitifs.updbtfRfndfrfrOrEditorUI(dffbultEditors.nfxtElfmfnt());
        }

        // Updbtf tif UI of tif tbblf ifbdfr
        if (tbblfHfbdfr != null && tbblfHfbdfr.gftPbrfnt() == null) {
            tbblfHfbdfr.updbtfUI();
        }

        // Updbtf UI bpplifd to pbrfnt SdrollPbnf
        donfigurfEndlosingSdrollPbnfUI();

        sftUI((TbblfUI)UIMbnbgfr.gftUI(tiis));
    }

    /**
     * Rfturns tif suffix usfd to donstrudt tif nbmf of tif L&bmp;F dlbss usfd to
     * rfndfr tiis domponfnt.
     *
     * @rfturn tif string "TbblfUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


//
// Mbnbging modfls
//

    /**
     * Sfts tif dbtb modfl for tiis tbblf to <dodf>nfwModfl</dodf> bnd rfgistfrs
     * witi it for listfnfr notifidbtions from tif nfw dbtb modfl.
     *
     * @pbrbm   dbtbModfl        tif nfw dbtb sourdf for tiis tbblf
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>nfwModfl</dodf> is <dodf>null</dodf>
     * @sff     #gftModfl
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif modfl tibt is tif sourdf of tif dbtb for tiis vifw.
     */
    publid void sftModfl(TbblfModfl dbtbModfl) {
        if (dbtbModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot sft b null TbblfModfl");
        }
        if (tiis.dbtbModfl != dbtbModfl) {
            TbblfModfl old = tiis.dbtbModfl;
            if (old != null) {
                old.rfmovfTbblfModflListfnfr(tiis);
            }
            tiis.dbtbModfl = dbtbModfl;
            dbtbModfl.bddTbblfModflListfnfr(tiis);

            tbblfCibngfd(nfw TbblfModflEvfnt(dbtbModfl, TbblfModflEvfnt.HEADER_ROW));

            firfPropfrtyCibngf("modfl", old, dbtbModfl);

            if (gftAutoCrfbtfRowSortfr()) {
                sftRowSortfr(nfw TbblfRowSortfr<TbblfModfl>(dbtbModfl));
            }
        }
    }

    /**
     * Rfturns tif <dodf>TbblfModfl</dodf> tibt providfs tif dbtb displbyfd by tiis
     * <dodf>JTbblf</dodf>.
     *
     * @rfturn  tif <dodf>TbblfModfl</dodf> tibt providfs tif dbtb displbyfd by tiis <dodf>JTbblf</dodf>
     * @sff     #sftModfl
     */
    publid TbblfModfl gftModfl() {
        rfturn dbtbModfl;
    }

    /**
     * Sfts tif dolumn modfl for tiis tbblf to <dodf>nfwModfl</dodf> bnd rfgistfrs
     * for listfnfr notifidbtions from tif nfw dolumn modfl. Also sfts
     * tif dolumn modfl of tif <dodf>JTbblfHfbdfr</dodf> to <dodf>dolumnModfl</dodf>.
     *
     * @pbrbm   dolumnModfl        tif nfw dbtb sourdf for tiis tbblf
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>dolumnModfl</dodf> is <dodf>null</dodf>
     * @sff     #gftColumnModfl
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif objfdt govfrning tif wby dolumns bppfbr in tif vifw.
     */
    publid void sftColumnModfl(TbblfColumnModfl dolumnModfl) {
        if (dolumnModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot sft b null ColumnModfl");
        }
        TbblfColumnModfl old = tiis.dolumnModfl;
        if (dolumnModfl != old) {
            if (old != null) {
                old.rfmovfColumnModflListfnfr(tiis);
            }
            tiis.dolumnModfl = dolumnModfl;
            dolumnModfl.bddColumnModflListfnfr(tiis);

            // Sft tif dolumn modfl of tif ifbdfr bs wfll.
            if (tbblfHfbdfr != null) {
                tbblfHfbdfr.sftColumnModfl(dolumnModfl);
            }

            firfPropfrtyCibngf("dolumnModfl", old, dolumnModfl);
            rfsizfAndRfpbint();
        }
    }

    /**
     * Rfturns tif <dodf>TbblfColumnModfl</dodf> tibt dontbins bll dolumn informbtion
     * of tiis tbblf.
     *
     * @rfturn  tif objfdt tibt providfs tif dolumn stbtf of tif tbblf
     * @sff     #sftColumnModfl
     */
    publid TbblfColumnModfl gftColumnModfl() {
        rfturn dolumnModfl;
    }

    /**
     * Sfts tif row sflfdtion modfl for tiis tbblf to <dodf>nfwModfl</dodf>
     * bnd rfgistfrs for listfnfr notifidbtions from tif nfw sflfdtion modfl.
     *
     * @pbrbm   nfwModfl        tif nfw sflfdtion modfl
     * @fxdfption IllfgblArgumfntExdfption      if <dodf>nfwModfl</dodf> is <dodf>null</dodf>
     * @sff     #gftSflfdtionModfl
     * @bfbninfo
     *      bound: truf
     *      dfsdription: Tif sflfdtion modfl for rows.
     */
    publid void sftSflfdtionModfl(ListSflfdtionModfl nfwModfl) {
        if (nfwModfl == null) {
            tirow nfw IllfgblArgumfntExdfption("Cbnnot sft b null SflfdtionModfl");
        }

        ListSflfdtionModfl oldModfl = sflfdtionModfl;

        if (nfwModfl != oldModfl) {
            if (oldModfl != null) {
                oldModfl.rfmovfListSflfdtionListfnfr(tiis);
            }

            sflfdtionModfl = nfwModfl;
            nfwModfl.bddListSflfdtionListfnfr(tiis);

            firfPropfrtyCibngf("sflfdtionModfl", oldModfl, nfwModfl);
            rfpbint();
        }
    }

    /**
     * Rfturns tif <dodf>ListSflfdtionModfl</dodf> tibt is usfd to mbintbin row
     * sflfdtion stbtf.
     *
     * @rfturn  tif objfdt tibt providfs row sflfdtion stbtf, <dodf>null</dodf>
     *          if row sflfdtion is not bllowfd
     * @sff     #sftSflfdtionModfl
     */
    publid ListSflfdtionModfl gftSflfdtionModfl() {
        rfturn sflfdtionModfl;
    }

//
// RowSortfrListfnfr
//

    /**
     * <dodf>RowSortfrListfnfr</dodf> notifidbtion tibt tif
     * <dodf>RowSortfr</dodf> ibs dibngfd in somf wby.
     *
     * @pbrbm f tif <dodf>RowSortfrEvfnt</dodf> dfsdribing tif dibngf
     * @tirows NullPointfrExdfption if <dodf>f</dodf> is <dodf>null</dodf>
     * @sindf 1.6
     */
    publid void sortfrCibngfd(RowSortfrEvfnt f) {
        if (f.gftTypf() == RowSortfrEvfnt.Typf.SORT_ORDER_CHANGED) {
            JTbblfHfbdfr ifbdfr = gftTbblfHfbdfr();
            if (ifbdfr != null) {
                ifbdfr.rfpbint();
            }
        }
        flsf if (f.gftTypf() == RowSortfrEvfnt.Typf.SORTED) {
            sortfrCibngfd = truf;
            if (!ignorfSortCibngf) {
                sortfdTbblfCibngfd(f, null);
            }
        }
    }


    /**
     * SortMbnbgfr providfs support for mbnbging tif sflfdtion bnd vbribblf
     * row ifigits wifn sorting is fnbblfd. Tiis informbtion is fndbpsulbtfd
     * into b dlbss to bvoid bulking up JTbblf.
     */
    privbtf finbl dlbss SortMbnbgfr {
        RowSortfr<? fxtfnds TbblfModfl> sortfr;

        // Sflfdtion, in tfrms of tif modfl. Tiis is lbzily drfbtfd
        // bs nffdfd.
        privbtf ListSflfdtionModfl modflSflfdtion;
        privbtf int modflLfbdIndfx;
        // Sft to truf wiilf in tif prodfss of dibnging tif sflfdtion.
        // If tiis is truf tif sflfdtion dibngf is ignorfd.
        privbtf boolfbn syndingSflfdtion;
        // Tfmporbry dbdif of sflfdtion, in tfrms of modfl. Tiis is only usfd
        // if wf don't nffd tif full wfigit of modflSflfdtion.
        privbtf int[] lbstModflSflfdtion;

        // Hfigits of tif rows in tfrms of tif modfl.
        privbtf SizfSfqufndf modflRowSizfs;


        SortMbnbgfr(RowSortfr<? fxtfnds TbblfModfl> sortfr) {
            tiis.sortfr = sortfr;
            sortfr.bddRowSortfrListfnfr(JTbblf.tiis);
        }

        /**
         * Disposfs bny rfsourdfs usfd by tiis SortMbnbgfr.
         */
        publid void disposf() {
            if (sortfr != null) {
                sortfr.rfmovfRowSortfrListfnfr(JTbblf.tiis);
            }
        }

        /**
         * Sfts tif ifigit for b row bt b spfdififd indfx.
         */
        publid void sftVifwRowHfigit(int vifwIndfx, int rowHfigit) {
            if (modflRowSizfs == null) {
                modflRowSizfs = nfw SizfSfqufndf(gftModfl().gftRowCount(),
                                                 gftRowHfigit());
            }
            modflRowSizfs.sftSizf(donvfrtRowIndfxToModfl(vifwIndfx),rowHfigit);
        }

        /**
         * Invokfd wifn tif undfrlying modfl ibs domplftfly dibngfd.
         */
        publid void bllCibngfd() {
            modflLfbdIndfx = -1;
            modflSflfdtion = null;
            modflRowSizfs = null;
        }

        /**
         * Invokfd wifn tif sflfdtion, on tif vifw, ibs dibngfd.
         */
        publid void vifwSflfdtionCibngfd(ListSflfdtionEvfnt f) {
            if (!syndingSflfdtion && modflSflfdtion != null) {
                modflSflfdtion = null;
            }
        }

        /**
         * Invokfd wifn fitifr tif tbblf modfl ibs dibngfd, or tif RowSortfr
         * ibs dibngfd. Tiis is invokfd prior to notifying tif sortfr of tif
         * dibngf.
         */
        publid void prfpbrfForCibngf(RowSortfrEvfnt sortEvfnt,
                                     ModflCibngf dibngf) {
            if (gftUpdbtfSflfdtionOnSort()) {
                dbdifSflfdtion(sortEvfnt, dibngf);
            }
        }

        /**
         * Updbtfs tif intfrnbl dbdif of tif sflfdtion bbsfd on tif dibngf.
         */
        privbtf void dbdifSflfdtion(RowSortfrEvfnt sortEvfnt,
                                    ModflCibngf dibngf) {
            if (sortEvfnt != null) {
                // sort ordfr dibngfd. If modflSflfdtion is null bnd filtfring
                // is fnbblfd wf nffd to dbdif tif sflfdtion in tfrms of tif
                // undfrlying modfl, tiis will bllow us to dorrfdtly rfstorf
                // tif sflfdtion fvfn if rows brf filtfrfd out.
                if (modflSflfdtion == null &&
                        sortfr.gftVifwRowCount() != gftModfl().gftRowCount()) {
                    modflSflfdtion = nfw DffbultListSflfdtionModfl();
                    ListSflfdtionModfl vifwSflfdtion = gftSflfdtionModfl();
                    int min = vifwSflfdtion.gftMinSflfdtionIndfx();
                    int mbx = vifwSflfdtion.gftMbxSflfdtionIndfx();
                    int modflIndfx;
                    for (int vifwIndfx = min; vifwIndfx <= mbx; vifwIndfx++) {
                        if (vifwSflfdtion.isSflfdtfdIndfx(vifwIndfx)) {
                            modflIndfx = donvfrtRowIndfxToModfl(
                                    sortEvfnt, vifwIndfx);
                            if (modflIndfx != -1) {
                                modflSflfdtion.bddSflfdtionIntfrvbl(
                                    modflIndfx, modflIndfx);
                            }
                        }
                    }
                    modflIndfx = donvfrtRowIndfxToModfl(sortEvfnt,
                            vifwSflfdtion.gftLfbdSflfdtionIndfx());
                    SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(
                            modflSflfdtion, modflIndfx, modflIndfx);
                } flsf if (modflSflfdtion == null) {
                    // Sorting dibngfd, ibvfn't dbdifd sflfdtion in tfrms
                    // of modfl bnd no filtfring. Tfmporbrily dbdif sflfdtion.
                    dbdifModflSflfdtion(sortEvfnt);
                }
            } flsf if (dibngf.bllRowsCibngfd) {
                // All tif rows ibvf dibngfd, diudk bny dbdifd sflfdtion.
                modflSflfdtion = null;
            } flsf if (modflSflfdtion != null) {
                // Tbblf dibngfd, rfflfdt dibngfs in dbdifd sflfdtion modfl.
                switdi(dibngf.typf) {
                dbsf TbblfModflEvfnt.DELETE:
                    modflSflfdtion.rfmovfIndfxIntfrvbl(dibngf.stbrtModflIndfx,
                                                       dibngf.fndModflIndfx);
                    brfbk;
                dbsf TbblfModflEvfnt.INSERT:
                    modflSflfdtion.insfrtIndfxIntfrvbl(dibngf.stbrtModflIndfx,
                                                       dibngf.lfngti,
                                                       truf);
                    brfbk;
                dffbult:
                    brfbk;
                }
            } flsf {
                // tbblf dibngfd, but ibvfn't dbdifd rows, tfmporbrily
                // dbdif tifm.
                dbdifModflSflfdtion(null);
            }
        }

        privbtf void dbdifModflSflfdtion(RowSortfrEvfnt sortEvfnt) {
            lbstModflSflfdtion = donvfrtSflfdtionToModfl(sortEvfnt);
            modflLfbdIndfx = donvfrtRowIndfxToModfl(sortEvfnt,
                        sflfdtionModfl.gftLfbdSflfdtionIndfx());
        }

        /**
         * Inovkfd wifn fitifr tif tbblf ibs dibngfd or tif sortfr ibs dibngfd
         * bnd bftfr tif sortfr ibs bffn notififd. If nfdfssbry tiis will
         * rfbpply tif sflfdtion bnd vbribblf row ifigits.
         */
        publid void prodfssCibngf(RowSortfrEvfnt sortEvfnt,
                                  ModflCibngf dibngf,
                                  boolfbn sortfrCibngfd) {
            if (dibngf != null) {
                if (dibngf.bllRowsCibngfd) {
                    modflRowSizfs = null;
                    rowModfl = null;
                } flsf if (modflRowSizfs != null) {
                    if (dibngf.typf == TbblfModflEvfnt.INSERT) {
                        modflRowSizfs.insfrtEntrifs(dibngf.stbrtModflIndfx,
                                                    dibngf.fndModflIndfx -
                                                    dibngf.stbrtModflIndfx + 1,
                                                    gftRowHfigit());
                    } flsf if (dibngf.typf == TbblfModflEvfnt.DELETE) {
                        modflRowSizfs.rfmovfEntrifs(dibngf.stbrtModflIndfx,
                                                    dibngf.fndModflIndfx -
                                                    dibngf.stbrtModflIndfx +1 );
                    }
                }
            }
            if (sortfrCibngfd) {
                sftVifwRowHfigitsFromModfl();
                rfstorfSflfdtion(dibngf);
            }
        }

        /**
         * Rfsfts tif vbribblf row ifigits in tfrms of tif vifw from
         * tibt of tif vbribblf row ifigits in tfrms of tif modfl.
         */
        privbtf void sftVifwRowHfigitsFromModfl() {
            if (modflRowSizfs != null) {
                rowModfl.sftSizfs(gftRowCount(), gftRowHfigit());
                for (int vifwIndfx = gftRowCount() - 1; vifwIndfx >= 0;
                         vifwIndfx--) {
                    int modflIndfx = donvfrtRowIndfxToModfl(vifwIndfx);
                    rowModfl.sftSizf(vifwIndfx,
                                     modflRowSizfs.gftSizf(modflIndfx));
                }
            }
        }

        /**
         * Rfstorfs tif sflfdtion from tibt in tfrms of tif modfl.
         */
        privbtf void rfstorfSflfdtion(ModflCibngf dibngf) {
            syndingSflfdtion = truf;
            if (lbstModflSflfdtion != null) {
                rfstorfSortingSflfdtion(lbstModflSflfdtion,
                                        modflLfbdIndfx, dibngf);
                lbstModflSflfdtion = null;
            } flsf if (modflSflfdtion != null) {
                ListSflfdtionModfl vifwSflfdtion = gftSflfdtionModfl();
                vifwSflfdtion.sftVblufIsAdjusting(truf);
                vifwSflfdtion.dlfbrSflfdtion();
                int min = modflSflfdtion.gftMinSflfdtionIndfx();
                int mbx = modflSflfdtion.gftMbxSflfdtionIndfx();
                int vifwIndfx;
                for (int modflIndfx = min; modflIndfx <= mbx; modflIndfx++) {
                    if (modflSflfdtion.isSflfdtfdIndfx(modflIndfx)) {
                        vifwIndfx = donvfrtRowIndfxToVifw(modflIndfx);
                        if (vifwIndfx != -1) {
                            vifwSflfdtion.bddSflfdtionIntfrvbl(vifwIndfx,
                                                               vifwIndfx);
                        }
                    }
                }
                // Rfstorf tif lfbd
                int vifwLfbdIndfx = modflSflfdtion.gftLfbdSflfdtionIndfx();
                if (vifwLfbdIndfx != -1 && !modflSflfdtion.isSflfdtionEmpty()) {
                    vifwLfbdIndfx = donvfrtRowIndfxToVifw(vifwLfbdIndfx);
                }
                SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(
                        vifwSflfdtion, vifwLfbdIndfx, vifwLfbdIndfx);
                vifwSflfdtion.sftVblufIsAdjusting(fblsf);
            }
            syndingSflfdtion = fblsf;
        }
    }


    /**
     * ModflCibngf is usfd wifn sorting to rfstorf stbtf, it dorrfsponds
     * to dbtb from b TbblfModflEvfnt.  Tif vblufs brf prfdbldulbtfd bs
     * tify brf usfd fxtfnsivfly.
     */
    privbtf finbl dlbss ModflCibngf {
        // Stbrting indfx of tif dibngf, in tfrms of tif modfl
        int stbrtModflIndfx;

        // Ending indfx of tif dibngf, in tfrms of tif modfl
        int fndModflIndfx;

        // Typf of dibngf
        int typf;

        // Numbfr of rows in tif modfl
        int modflRowCount;

        // Tif fvfnt tibt triggfrfd tiis.
        TbblfModflEvfnt fvfnt;

        // Lfngti of tif dibngf (fnd - stbrt + 1)
        int lfngti;

        // Truf if tif fvfnt indidbtfs bll tif dontfnts ibvf dibngfd
        boolfbn bllRowsCibngfd;

        ModflCibngf(TbblfModflEvfnt f) {
            stbrtModflIndfx = Mbti.mbx(0, f.gftFirstRow());
            fndModflIndfx = f.gftLbstRow();
            modflRowCount = gftModfl().gftRowCount();
            if (fndModflIndfx < 0) {
                fndModflIndfx = Mbti.mbx(0, modflRowCount - 1);
            }
            lfngti = fndModflIndfx - stbrtModflIndfx + 1;
            typf = f.gftTypf();
            fvfnt = f;
            bllRowsCibngfd = (f.gftLbstRow() == Intfgfr.MAX_VALUE);
        }
    }

    /**
     * Invokfd wifn <dodf>sortfrCibngfd</dodf> is invokfd, or
     * wifn <dodf>tbblfCibngfd</dodf> is invokfd bnd sorting is fnbblfd.
     */
    privbtf void sortfdTbblfCibngfd(RowSortfrEvfnt sortfdEvfnt,
                                    TbblfModflEvfnt f) {
        int fditingModflIndfx = -1;
        ModflCibngf dibngf = (f != null) ? nfw ModflCibngf(f) : null;

        if ((dibngf == null || !dibngf.bllRowsCibngfd) &&
                tiis.fditingRow != -1) {
            fditingModflIndfx = donvfrtRowIndfxToModfl(sortfdEvfnt,
                                                       tiis.fditingRow);
        }

        sortMbnbgfr.prfpbrfForCibngf(sortfdEvfnt, dibngf);

        if (f != null) {
            if (dibngf.typf == TbblfModflEvfnt.UPDATE) {
                rfpbintSortfdRows(dibngf);
            }
            notifySortfr(dibngf);
            if (dibngf.typf != TbblfModflEvfnt.UPDATE) {
                // If tif Sortfr is unsortfd wf will not ibvf rfdfivfd
                // notifidbtion, fordf trfbting insfrt/dflftf bs b dibngf.
                sortfrCibngfd = truf;
            }
        }
        flsf {
            sortfrCibngfd = truf;
        }

        sortMbnbgfr.prodfssCibngf(sortfdEvfnt, dibngf, sortfrCibngfd);

        if (sortfrCibngfd) {
            // Updbtf tif fditing row
            if (tiis.fditingRow != -1) {
                int nfwIndfx = (fditingModflIndfx == -1) ? -1 :
                        donvfrtRowIndfxToVifw(fditingModflIndfx,dibngf);
                rfstorfSortingEditingRow(nfwIndfx);
            }

            // And ibndlf tif bppropribtf rfpbinting.
            if (f == null || dibngf.typf != TbblfModflEvfnt.UPDATE) {
                rfsizfAndRfpbint();
            }
        }

        // Cifdk if lfbd/bndior nffd to bf rfsft.
        if (dibngf != null && dibngf.bllRowsCibngfd) {
            dlfbrSflfdtionAndLfbdAndior();
            rfsizfAndRfpbint();
        }
    }

    /**
     * Rfpbints tif sort of sortfd rows in rfsponsf to b TbblfModflEvfnt.
     */
    privbtf void rfpbintSortfdRows(ModflCibngf dibngf) {
        if (dibngf.stbrtModflIndfx > dibngf.fndModflIndfx ||
                dibngf.stbrtModflIndfx + 10 < dibngf.fndModflIndfx) {
            // Too mudi ibs dibngfd, punt
            rfpbint();
            rfturn;
        }
        int fvfntColumn = dibngf.fvfnt.gftColumn();
        int dolumnVifwIndfx = fvfntColumn;
        if (dolumnVifwIndfx == TbblfModflEvfnt.ALL_COLUMNS) {
            dolumnVifwIndfx = 0;
        }
        flsf {
            dolumnVifwIndfx = donvfrtColumnIndfxToVifw(dolumnVifwIndfx);
            if (dolumnVifwIndfx == -1) {
                rfturn;
            }
        }
        int modflIndfx = dibngf.stbrtModflIndfx;
        wiilf (modflIndfx <= dibngf.fndModflIndfx) {
            int vifwIndfx = donvfrtRowIndfxToVifw(modflIndfx++);
            if (vifwIndfx != -1) {
                Rfdtbnglf dirty = gftCfllRfdt(vifwIndfx, dolumnVifwIndfx,
                                              fblsf);
                int x = dirty.x;
                int w = dirty.widti;
                if (fvfntColumn == TbblfModflEvfnt.ALL_COLUMNS) {
                    x = 0;
                    w = gftWidti();
                }
                rfpbint(x, dirty.y, w, dirty.ifigit);
            }
        }
    }

    /**
     * Rfstorfs tif sflfdtion bftfr b modfl fvfnt/sort ordfr dibngfs.
     * All doordinbtfs brf in tfrms of tif modfl.
     */
    privbtf void rfstorfSortingSflfdtion(int[] sflfdtion, int lfbd,
            ModflCibngf dibngf) {
        // Convfrt tif sflfdtion from modfl to vifw
        for (int i = sflfdtion.lfngti - 1; i >= 0; i--) {
            sflfdtion[i] = donvfrtRowIndfxToVifw(sflfdtion[i], dibngf);
        }
        lfbd = donvfrtRowIndfxToVifw(lfbd, dibngf);

        // Cifdk for tif dommon dbsf of no dibngf in sflfdtion for 1 row
        if (sflfdtion.lfngti == 0 ||
            (sflfdtion.lfngti == 1 && sflfdtion[0] == gftSflfdtfdRow())) {
            rfturn;
        }

        // And bpply tif nfw sflfdtion
        sflfdtionModfl.sftVblufIsAdjusting(truf);
        sflfdtionModfl.dlfbrSflfdtion();
        for (int i = sflfdtion.lfngti - 1; i >= 0; i--) {
            if (sflfdtion[i] != -1) {
                sflfdtionModfl.bddSflfdtionIntfrvbl(sflfdtion[i],
                                                    sflfdtion[i]);
            }
        }
        SwingUtilitifs2.sftLfbdAndiorWitioutSflfdtion(
                sflfdtionModfl, lfbd, lfbd);
        sflfdtionModfl.sftVblufIsAdjusting(fblsf);
    }

    /**
     * Rfstorfs tif fditing row bftfr b modfl fvfnt/sort ordfr dibngf.
     *
     * @pbrbm fditingRow nfw indfx of tif fditingRow, in tfrms of tif vifw
     */
    privbtf void rfstorfSortingEditingRow(int fditingRow) {
        if (fditingRow == -1) {
            // Editing row no longfr bfing siown, dbndfl fditing
            TbblfCfllEditor fditor = gftCfllEditor();
            if (fditor != null) {
                // First try bnd dbndfl
                fditor.dbndflCfllEditing();
                if (gftCfllEditor() != null) {
                    // CfllEditor didn't dfdf dontrol, fordffully
                    // rfmovf it
                    rfmovfEditor();
                }
            }
        }
        flsf {
            // Rfpositioning ibndlfd in BbsidTbblfUI
            tiis.fditingRow = fditingRow;
            rfpbint();
        }
    }

    /**
     * Notififs tif sortfr of b dibngf in tif undfrlying modfl.
     */
    privbtf void notifySortfr(ModflCibngf dibngf) {
        try {
            ignorfSortCibngf = truf;
            sortfrCibngfd = fblsf;
            switdi(dibngf.typf) {
            dbsf TbblfModflEvfnt.UPDATE:
                if (dibngf.fvfnt.gftLbstRow() == Intfgfr.MAX_VALUE) {
                    sortMbnbgfr.sortfr.bllRowsCibngfd();
                } flsf if (dibngf.fvfnt.gftColumn() ==
                           TbblfModflEvfnt.ALL_COLUMNS) {
                    sortMbnbgfr.sortfr.rowsUpdbtfd(dibngf.stbrtModflIndfx,
                                       dibngf.fndModflIndfx);
                } flsf {
                    sortMbnbgfr.sortfr.rowsUpdbtfd(dibngf.stbrtModflIndfx,
                                       dibngf.fndModflIndfx,
                                       dibngf.fvfnt.gftColumn());
                }
                brfbk;
            dbsf TbblfModflEvfnt.INSERT:
                sortMbnbgfr.sortfr.rowsInsfrtfd(dibngf.stbrtModflIndfx,
                                    dibngf.fndModflIndfx);
                brfbk;
            dbsf TbblfModflEvfnt.DELETE:
                sortMbnbgfr.sortfr.rowsDflftfd(dibngf.stbrtModflIndfx,
                                   dibngf.fndModflIndfx);
                brfbk;
            }
        } finblly {
            ignorfSortCibngf = fblsf;
        }
    }

    /**
     * Convfrts b modfl indfx to vifw indfx.  Tiis is dbllfd wifn tif
     * sortfr or modfl dibngfs bnd sorting is fnbblfd.
     *
     * @pbrbm dibngf dfsdribfs tif TbblfModflEvfnt tibt initibtfd tif dibngf;
     *        will bf null if dbllfd bs tif rfsult of b sort
     */
    privbtf int donvfrtRowIndfxToVifw(int modflIndfx, ModflCibngf dibngf) {
        if (modflIndfx < 0) {
            rfturn -1;
        }
        if (dibngf != null && modflIndfx >= dibngf.stbrtModflIndfx) {
            if (dibngf.typf == TbblfModflEvfnt.INSERT) {
                if (modflIndfx + dibngf.lfngti >= dibngf.modflRowCount) {
                    rfturn -1;
                }
                rfturn sortMbnbgfr.sortfr.donvfrtRowIndfxToVifw(
                        modflIndfx + dibngf.lfngti);
            }
            flsf if (dibngf.typf == TbblfModflEvfnt.DELETE) {
                if (modflIndfx <= dibngf.fndModflIndfx) {
                    // dflftfd
                    rfturn -1;
                }
                flsf {
                    if (modflIndfx - dibngf.lfngti >= dibngf.modflRowCount) {
                        rfturn -1;
                    }
                    rfturn sortMbnbgfr.sortfr.donvfrtRowIndfxToVifw(
                            modflIndfx - dibngf.lfngti);
                }
            }
            // flsf, updbtfd
        }
        if (modflIndfx >= gftModfl().gftRowCount()) {
            rfturn -1;
        }
        rfturn sortMbnbgfr.sortfr.donvfrtRowIndfxToVifw(modflIndfx);
    }

    /**
     * Convfrts tif sflfdtion to modfl doordinbtfs.  Tiis is usfd wifn
     * tif modfl dibngfs or tif sortfr dibngfs.
     */
    privbtf int[] donvfrtSflfdtionToModfl(RowSortfrEvfnt f) {
        int[] sflfdtion = gftSflfdtfdRows();
        for (int i = sflfdtion.lfngti - 1; i >= 0; i--) {
            sflfdtion[i] = donvfrtRowIndfxToModfl(f, sflfdtion[i]);
        }
        rfturn sflfdtion;
    }

    privbtf int donvfrtRowIndfxToModfl(RowSortfrEvfnt f, int vifwIndfx) {
        if (f != null) {
            if (f.gftPrfviousRowCount() == 0) {
                rfturn vifwIndfx;
            }
            // rbngf difdking ibndlfd by RowSortfrEvfnt
            rfturn f.donvfrtPrfviousRowIndfxToModfl(vifwIndfx);
        }
        // Mbkf surf tif vifwIndfx is vblid
        if (vifwIndfx < 0 || vifwIndfx >= gftRowCount()) {
            rfturn -1;
        }
        rfturn donvfrtRowIndfxToModfl(vifwIndfx);
    }

//
// Implfmfnting TbblfModflListfnfr intfrfbdf
//

    /**
     * Invokfd wifn tiis tbblf's <dodf>TbblfModfl</dodf> gfnfrbtfs
     * b <dodf>TbblfModflEvfnt</dodf>.
     * Tif <dodf>TbblfModflEvfnt</dodf> siould bf donstrudtfd in tif
     * doordinbtf systfm of tif modfl; tif bppropribtf mbpping to tif
     * vifw doordinbtf systfm is pfrformfd by tiis <dodf>JTbblf</dodf>
     * wifn it rfdfivfs tif fvfnt.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by <dodf>JTbblf</dodf>.
     * <p>
     * Notf tibt bs of 1.3, tiis mftiod dlfbrs tif sflfdtion, if bny.
     */
    publid void tbblfCibngfd(TbblfModflEvfnt f) {
        if (f == null || f.gftFirstRow() == TbblfModflEvfnt.HEADER_ROW) {
            // Tif wiolf tiing dibngfd
            dlfbrSflfdtionAndLfbdAndior();

            rowModfl = null;

            if (sortMbnbgfr != null) {
                try {
                    ignorfSortCibngf = truf;
                    sortMbnbgfr.sortfr.modflStrudturfCibngfd();
                } finblly {
                    ignorfSortCibngf = fblsf;
                }
                sortMbnbgfr.bllCibngfd();
            }

            if (gftAutoCrfbtfColumnsFromModfl()) {
                // Tiis will ffffdt invblidbtion of tif JTbblf bnd JTbblfHfbdfr.
                drfbtfDffbultColumnsFromModfl();
                rfturn;
            }

            rfsizfAndRfpbint();
            rfturn;
        }

        if (sortMbnbgfr != null) {
            sortfdTbblfCibngfd(null, f);
            rfturn;
        }

        // Tif totblRowHfigit dbldulbtfd bflow will bf indorrfdt if
        // tifrf brf vbribblf ifigit rows. Rfpbint tif visiblf rfgion,
        // but don't rfturn bs b rfvblidbtf mby bf nfdfssbry bs wfll.
        if (rowModfl != null) {
            rfpbint();
        }

        if (f.gftTypf() == TbblfModflEvfnt.INSERT) {
            tbblfRowsInsfrtfd(f);
            rfturn;
        }

        if (f.gftTypf() == TbblfModflEvfnt.DELETE) {
            tbblfRowsDflftfd(f);
            rfturn;
        }

        int modflColumn = f.gftColumn();
        int stbrt = f.gftFirstRow();
        int fnd = f.gftLbstRow();

        Rfdtbnglf dirtyRfgion;
        if (modflColumn == TbblfModflEvfnt.ALL_COLUMNS) {
            // 1 or morf rows dibngfd
            dirtyRfgion = nfw Rfdtbnglf(0, stbrt * gftRowHfigit(),
                                        gftColumnModfl().gftTotblColumnWidti(), 0);
        }
        flsf {
            // A dfll or dolumn of dflls ibs dibngfd.
            // Unlikf tif rfst of tif mftiods in tif JTbblf, tif TbblfModflEvfnt
            // usfs tif doordinbtf systfm of tif modfl instfbd of tif vifw.
            // Tiis is tif only plbdf in tif JTbblf wifrf tiis "rfvfrsf mbpping"
            // is usfd.
            int dolumn = donvfrtColumnIndfxToVifw(modflColumn);
            dirtyRfgion = gftCfllRfdt(stbrt, dolumn, fblsf);
        }

        // Now bdjust tif ifigit of tif dirty rfgion bddording to tif vbluf of "fnd".
        // Cifdk for Intfgfr.MAX_VALUE bs tiis will dbusf bn ovfrflow.
        if (fnd != Intfgfr.MAX_VALUE) {
            dirtyRfgion.ifigit = (fnd-stbrt+1)*gftRowHfigit();
            rfpbint(dirtyRfgion.x, dirtyRfgion.y, dirtyRfgion.widti, dirtyRfgion.ifigit);
        }
        // In fbdt, if tif fnd is Intfgfr.MAX_VALUE wf nffd to rfvblidbtf bnywby
        // bfdbusf tif sdrollbbr mby nffd rfpbinting.
        flsf {
            dlfbrSflfdtionAndLfbdAndior();
            rfsizfAndRfpbint();
            rowModfl = null;
        }
    }

    /*
     * Invokfd wifn rows ibvf bffn insfrtfd into tif tbblf.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm f tif TbblfModflEvfnt fndbpsulbting tif insfrtion
     */
    privbtf void tbblfRowsInsfrtfd(TbblfModflEvfnt f) {
        int stbrt = f.gftFirstRow();
        int fnd = f.gftLbstRow();
        if (stbrt < 0) {
            stbrt = 0;
        }
        if (fnd < 0) {
            fnd = gftRowCount()-1;
        }

        // Adjust tif sflfdtion to bddount for tif nfw rows.
        int lfngti = fnd - stbrt + 1;
        sflfdtionModfl.insfrtIndfxIntfrvbl(stbrt, lfngti, truf);

        // If wf ibvf vbribblf ifigit rows, bdjust tif row modfl.
        if (rowModfl != null) {
            rowModfl.insfrtEntrifs(stbrt, lfngti, gftRowHfigit());
        }
        int ri = gftRowHfigit() ;
        Rfdtbnglf drbwRfdt = nfw Rfdtbnglf(0, stbrt * ri,
                                        gftColumnModfl().gftTotblColumnWidti(),
                                           (gftRowCount()-stbrt) * ri);

        rfvblidbtf();
        // PENDING(milnf) rfvblidbtf dblls rfpbint() if pbrfnt is b SdrollPbnf
        // rfpbint still rfquirfd in tif unusubl dbsf wifrf tifrf is no SdrollPbnf
        rfpbint(drbwRfdt);
    }

    /*
     * Invokfd wifn rows ibvf bffn rfmovfd from tif tbblf.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm f tif TbblfModflEvfnt fndbpsulbting tif dflftion
     */
    privbtf void tbblfRowsDflftfd(TbblfModflEvfnt f) {
        int stbrt = f.gftFirstRow();
        int fnd = f.gftLbstRow();
        if (stbrt < 0) {
            stbrt = 0;
        }
        if (fnd < 0) {
            fnd = gftRowCount()-1;
        }

        int dflftfdCount = fnd - stbrt + 1;
        int prfviousRowCount = gftRowCount() + dflftfdCount;
        // Adjust tif sflfdtion to bddount for tif nfw rows
        sflfdtionModfl.rfmovfIndfxIntfrvbl(stbrt, fnd);

        // If wf ibvf vbribblf ifigit rows, bdjust tif row modfl.
        if (rowModfl != null) {
            rowModfl.rfmovfEntrifs(stbrt, dflftfdCount);
        }

        int ri = gftRowHfigit();
        Rfdtbnglf drbwRfdt = nfw Rfdtbnglf(0, stbrt * ri,
                                        gftColumnModfl().gftTotblColumnWidti(),
                                        (prfviousRowCount - stbrt) * ri);

        rfvblidbtf();
        // PENDING(milnf) rfvblidbtf dblls rfpbint() if pbrfnt is b SdrollPbnf
        // rfpbint still rfquirfd in tif unusubl dbsf wifrf tifrf is no SdrollPbnf
        rfpbint(drbwRfdt);
    }

//
// Implfmfnting TbblfColumnModflListfnfr intfrfbdf
//

    /**
     * Invokfd wifn b dolumn is bddfd to tif tbblf dolumn modfl.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @sff TbblfColumnModflListfnfr
     */
    publid void dolumnAddfd(TbblfColumnModflEvfnt f) {
        // If I'm durrfntly fditing, tifn I siould stop fditing
        if (isEditing()) {
            rfmovfEditor();
        }
        rfsizfAndRfpbint();
    }

    /**
     * Invokfd wifn b dolumn is rfmovfd from tif tbblf dolumn modfl.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @sff TbblfColumnModflListfnfr
     */
    publid void dolumnRfmovfd(TbblfColumnModflEvfnt f) {
        // If I'm durrfntly fditing, tifn I siould stop fditing
        if (isEditing()) {
            rfmovfEditor();
        }
        rfsizfAndRfpbint();
    }

    /**
     * Invokfd wifn b dolumn is rfpositionfd. If b dfll is bfing
     * fditfd, tifn fditing is stoppfd bnd tif dfll is rfdrbwn.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm f   tif fvfnt rfdfivfd
     * @sff TbblfColumnModflListfnfr
     */
    publid void dolumnMovfd(TbblfColumnModflEvfnt f) {
        if (isEditing() && !gftCfllEditor().stopCfllEditing()) {
            gftCfllEditor().dbndflCfllEditing();
        }
        rfpbint();
    }

    /**
     * Invokfd wifn b dolumn is movfd duf to b mbrgin dibngf.
     * If b dfll is bfing fditfd, tifn fditing is stoppfd bnd tif dfll
     * is rfdrbwn.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm  f    tif fvfnt rfdfivfd
     * @sff TbblfColumnModflListfnfr
     */
    publid void dolumnMbrginCibngfd(CibngfEvfnt f) {
        if (isEditing() && !gftCfllEditor().stopCfllEditing()) {
            gftCfllEditor().dbndflCfllEditing();
        }
        TbblfColumn rfsizingColumn = gftRfsizingColumn();
        // Nffd to do tiis ifrf, bfforf tif pbrfnt's
        // lbyout mbnbgfr dblls gftPrfffrrfdSizf().
        if (rfsizingColumn != null && butoRfsizfModf == AUTO_RESIZE_OFF) {
            rfsizingColumn.sftPrfffrrfdWidti(rfsizingColumn.gftWidti());
        }
        rfsizfAndRfpbint();
    }

    privbtf int limit(int i, int b, int b) {
        rfturn Mbti.min(b, Mbti.mbx(i, b));
    }

    /**
     * Invokfd wifn tif sflfdtion modfl of tif <dodf>TbblfColumnModfl</dodf>
     * is dibngfd.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm  f  tif fvfnt rfdfivfd
     * @sff TbblfColumnModflListfnfr
     */
    publid void dolumnSflfdtionCibngfd(ListSflfdtionEvfnt f) {
        boolfbn isAdjusting = f.gftVblufIsAdjusting();
        if (dolumnSflfdtionAdjusting && !isAdjusting) {
            // Tif bssumption is tibt wifn tif modfl is no longfr bdjusting
            // wf will ibvf blrfbdy gottfn bll tif dibngfs, bnd tifrfforf
            // don't nffd to do bn bdditionbl pbint.
            dolumnSflfdtionAdjusting = fblsf;
            rfturn;
        }
        dolumnSflfdtionAdjusting = isAdjusting;
        // Tif gftCfllRfdt() dbll will fbil unlfss tifrf is bt lfbst onf row.
        if (gftRowCount() <= 0 || gftColumnCount() <= 0) {
            rfturn;
        }
        int firstIndfx = limit(f.gftFirstIndfx(), 0, gftColumnCount()-1);
        int lbstIndfx = limit(f.gftLbstIndfx(), 0, gftColumnCount()-1);
        int minRow = 0;
        int mbxRow = gftRowCount() - 1;
        if (gftRowSflfdtionAllowfd()) {
            minRow = sflfdtionModfl.gftMinSflfdtionIndfx();
            mbxRow = sflfdtionModfl.gftMbxSflfdtionIndfx();
            int lfbdRow = gftAdjustfdIndfx(sflfdtionModfl.gftLfbdSflfdtionIndfx(), truf);

            if (minRow == -1 || mbxRow == -1) {
                if (lfbdRow == -1) {
                    // notiing to rfpbint, rfturn
                    rfturn;
                }

                // only tiing to rfpbint is tif lfbd
                minRow = mbxRow = lfbdRow;
            } flsf {
                // Wf nffd to donsidfr morf tibn just tif rbngf bftwffn
                // tif min bnd mbx sflfdtfd indfx. Tif lfbd row, wiidi dould
                // bf outsidf tiis rbngf, siould bf donsidfrfd blso.
                if (lfbdRow != -1) {
                    minRow = Mbti.min(minRow, lfbdRow);
                    mbxRow = Mbti.mbx(mbxRow, lfbdRow);
                }
            }
        }
        Rfdtbnglf firstColumnRfdt = gftCfllRfdt(minRow, firstIndfx, fblsf);
        Rfdtbnglf lbstColumnRfdt = gftCfllRfdt(mbxRow, lbstIndfx, fblsf);
        Rfdtbnglf dirtyRfgion = firstColumnRfdt.union(lbstColumnRfdt);
        rfpbint(dirtyRfgion);
    }

//
// Implfmfnting ListSflfdtionListfnfr intfrfbdf
//

    /**
     * Invokfd wifn tif row sflfdtion dibngfs -- rfpbints to siow tif nfw
     * sflfdtion.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm f   tif fvfnt rfdfivfd
     * @sff ListSflfdtionListfnfr
     */
    publid void vblufCibngfd(ListSflfdtionEvfnt f) {
        if (sortMbnbgfr != null) {
            sortMbnbgfr.vifwSflfdtionCibngfd(f);
        }
        boolfbn isAdjusting = f.gftVblufIsAdjusting();
        if (rowSflfdtionAdjusting && !isAdjusting) {
            // Tif bssumption is tibt wifn tif modfl is no longfr bdjusting
            // wf will ibvf blrfbdy gottfn bll tif dibngfs, bnd tifrfforf
            // don't nffd to do bn bdditionbl pbint.
            rowSflfdtionAdjusting = fblsf;
            rfturn;
        }
        rowSflfdtionAdjusting = isAdjusting;
        // Tif gftCfllRfdt() dblls will fbil unlfss tifrf is bt lfbst onf dolumn.
        if (gftRowCount() <= 0 || gftColumnCount() <= 0) {
            rfturn;
        }
        int firstIndfx = limit(f.gftFirstIndfx(), 0, gftRowCount()-1);
        int lbstIndfx = limit(f.gftLbstIndfx(), 0, gftRowCount()-1);
        Rfdtbnglf firstRowRfdt = gftCfllRfdt(firstIndfx, 0, fblsf);
        Rfdtbnglf lbstRowRfdt = gftCfllRfdt(lbstIndfx, gftColumnCount()-1, fblsf);
        Rfdtbnglf dirtyRfgion = firstRowRfdt.union(lbstRowRfdt);
        rfpbint(dirtyRfgion);
    }

//
// Implfmfnting tif CfllEditorListfnfr intfrfbdf
//

    /**
     * Invokfd wifn fditing is finisifd. Tif dibngfs brf sbvfd bnd tif
     * fditor is disdbrdfd.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm  f  tif fvfnt rfdfivfd
     * @sff CfllEditorListfnfr
     */
    publid void fditingStoppfd(CibngfEvfnt f) {
        // Tbkf in tif nfw vbluf
        TbblfCfllEditor fditor = gftCfllEditor();
        if (fditor != null) {
            Objfdt vbluf = fditor.gftCfllEditorVbluf();
            sftVblufAt(vbluf, fditingRow, fditingColumn);
            rfmovfEditor();
        }
    }

    /**
     * Invokfd wifn fditing is dbndflfd. Tif fditor objfdt is disdbrdfd
     * bnd tif dfll is rfndfrfd ondf bgbin.
     * <p>
     * Applidbtion dodf will not usf tifsf mftiods fxpliditly, tify
     * brf usfd intfrnblly by JTbblf.
     *
     * @pbrbm  f  tif fvfnt rfdfivfd
     * @sff CfllEditorListfnfr
     */
    publid void fditingCbndflfd(CibngfEvfnt f) {
        rfmovfEditor();
    }

//
// Implfmfnting tif Sdrollbblf intfrfbdf
//

    /**
     * Sfts tif prfffrrfd sizf of tif vifwport for tiis tbblf.
     *
     * @pbrbm sizf  b <dodf>Dimfnsion</dodf> objfdt spfdifying tif <dodf>prfffrrfdSizf</dodf> of b
     *              <dodf>JVifwport</dodf> wiosf vifw is tiis tbblf
     * @sff Sdrollbblf#gftPrfffrrfdSdrollbblfVifwportSizf
     * @bfbninfo
     * dfsdription: Tif prfffrrfd sizf of tif vifwport.
     */
    publid void sftPrfffrrfdSdrollbblfVifwportSizf(Dimfnsion sizf) {
        prfffrrfdVifwportSizf = sizf;
    }

    /**
     * Rfturns tif prfffrrfd sizf of tif vifwport for tiis tbblf.
     *
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt dontbining tif <dodf>prfffrrfdSizf</dodf> of tif <dodf>JVifwport</dodf>
     *         wiidi displbys tiis tbblf
     * @sff Sdrollbblf#gftPrfffrrfdSdrollbblfVifwportSizf
     */
    publid Dimfnsion gftPrfffrrfdSdrollbblfVifwportSizf() {
        rfturn prfffrrfdVifwportSizf;
    }

    /**
     * Rfturns tif sdroll indrfmfnt (in pixfls) tibt domplftfly fxposfs onf nfw
     * row or dolumn (dfpfnding on tif orifntbtion).
     * <p>
     * Tiis mftiod is dbllfd fbdi timf tif usfr rfqufsts b unit sdroll.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion fitifr <dodf>SwingConstbnts.VERTICAL</dodf>
     *                  or <dodf>SwingConstbnts.HORIZONTAL</dodf>
     * @pbrbm dirfdtion lfss tibn zfro to sdroll up/lfft,
     *                  grfbtfr tibn zfro for down/rigit
     * @rfturn tif "unit" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     * @sff Sdrollbblf#gftSdrollbblfUnitIndrfmfnt
     */
    publid int gftSdrollbblfUnitIndrfmfnt(Rfdtbnglf visiblfRfdt,
                                          int orifntbtion,
                                          int dirfdtion) {
        int lfbdingRow;
        int lfbdingCol;
        Rfdtbnglf lfbdingCfllRfdt;

        int lfbdingVisiblfEdgf;
        int lfbdingCfllEdgf;
        int lfbdingCfllSizf;

        lfbdingRow = gftLfbdingRow(visiblfRfdt);
        lfbdingCol = gftLfbdingCol(visiblfRfdt);
        if (orifntbtion == SwingConstbnts.VERTICAL && lfbdingRow < 0) {
            // Couldn't find lfbding row - rfturn somf dffbult vbluf
            rfturn gftRowHfigit();
        }
        flsf if (orifntbtion == SwingConstbnts.HORIZONTAL && lfbdingCol < 0) {
            // Couldn't find lfbding dol - rfturn somf dffbult vbluf
            rfturn 100;
        }

        // Notf tibt it's possiblf for onf of lfbdingCol or lfbdingRow to bf
        // -1, dfpfnding on tif orifntbtion.  Tiis is okby, bs gftCfllRfdt()
        // still providfs fnougi informbtion to dbldulbtf tif unit indrfmfnt.
        lfbdingCfllRfdt = gftCfllRfdt(lfbdingRow, lfbdingCol, truf);
        lfbdingVisiblfEdgf = lfbdingEdgf(visiblfRfdt, orifntbtion);
        lfbdingCfllEdgf = lfbdingEdgf(lfbdingCfllRfdt, orifntbtion);

        if (orifntbtion == SwingConstbnts.VERTICAL) {
            lfbdingCfllSizf = lfbdingCfllRfdt.ifigit;

        }
        flsf {
            lfbdingCfllSizf = lfbdingCfllRfdt.widti;
        }

        // 4 dbsfs:
        // #1: Lfbding dfll fully visiblf, rfvfbl nfxt dfll
        // #2: Lfbding dfll fully visiblf, iidf lfbding dfll
        // #3: Lfbding dfll pbrtiblly visiblf, iidf rfst of lfbding dfll
        // #4: Lfbding dfll pbrtiblly visiblf, rfvfbl rfst of lfbding dfll

        if (lfbdingVisiblfEdgf == lfbdingCfllEdgf) { // Lfbding dfll is fully
                                                     // visiblf
            // Cbsf #1: Rfvfbl prfvious dfll
            if (dirfdtion < 0) {
                int rftVbl = 0;

                if (orifntbtion == SwingConstbnts.VERTICAL) {
                    // Loop pbst bny zfro-ifigit rows
                    wiilf (--lfbdingRow >= 0) {
                        rftVbl = gftRowHfigit(lfbdingRow);
                        if (rftVbl != 0) {
                            brfbk;
                        }
                    }
                }
                flsf { // HORIZONTAL
                    // Loop pbst bny zfro-widti dols
                    wiilf (--lfbdingCol >= 0) {
                        rftVbl = gftCfllRfdt(lfbdingRow, lfbdingCol, truf).widti;
                        if (rftVbl != 0) {
                            brfbk;
                        }
                    }
                }
                rfturn rftVbl;
            }
            flsf { // Cbsf #2: iidf lfbding dfll
                rfturn lfbdingCfllSizf;
            }
        }
        flsf { // Lfbding dfll is pbrtiblly iiddfn
            // Computf visiblf, iiddfn portions
            int iiddfnAmt = Mbti.bbs(lfbdingVisiblfEdgf - lfbdingCfllEdgf);
            int visiblfAmt = lfbdingCfllSizf - iiddfnAmt;

            if (dirfdtion > 0) {
                // Cbsf #3: iidf siowing portion of lfbding dfll
                rfturn visiblfAmt;
            }
            flsf { // Cbsf #4: rfvfbl iiddfn portion of lfbding dfll
                rfturn iiddfnAmt;
            }
        }
    }

    /**
     * Rfturns <dodf>visiblfRfdt.ifigit</dodf> or
     * <dodf>visiblfRfdt.widti</dodf>,
     * dfpfnding on tiis tbblf's orifntbtion.  Notf tibt bs of Swing 1.1.1
     * (Jbvb 2 v 1.2.2) tif vbluf
     * rfturnfd will fnsurf tibt tif vifwport is dlfbnly blignfd on
     * b row boundbry.
     *
     * @rfturn <dodf>visiblfRfdt.ifigit</dodf> or
     *                                  <dodf>visiblfRfdt.widti</dodf>
     *                                  pfr tif orifntbtion
     * @sff Sdrollbblf#gftSdrollbblfBlodkIndrfmfnt
     */
    publid int gftSdrollbblfBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt,
            int orifntbtion, int dirfdtion) {

        if (gftRowCount() == 0) {
            // Siort-dirduit fmpty tbblf modfl
            if (SwingConstbnts.VERTICAL == orifntbtion) {
                int ri = gftRowHfigit();
                rfturn (ri > 0) ? Mbti.mbx(ri, (visiblfRfdt.ifigit / ri) * ri) :
                                  visiblfRfdt.ifigit;
            }
            flsf {
                rfturn visiblfRfdt.widti;
            }
        }
        // Siortdut for vfrtidbl sdrolling of b tbblf w/ uniform row ifigit
        if (null == rowModfl && SwingConstbnts.VERTICAL == orifntbtion) {
            int row = rowAtPoint(visiblfRfdt.gftLodbtion());
            bssfrt row != -1;
            int dol = dolumnAtPoint(visiblfRfdt.gftLodbtion());
            Rfdtbnglf dfllRfdt = gftCfllRfdt(row, dol, truf);

            if (dfllRfdt.y == visiblfRfdt.y) {
                int ri = gftRowHfigit();
                bssfrt ri > 0;
                rfturn Mbti.mbx(ri, (visiblfRfdt.ifigit / ri) * ri);
            }
        }
        if (dirfdtion < 0) {
            rfturn gftPrfviousBlodkIndrfmfnt(visiblfRfdt, orifntbtion);
        }
        flsf {
            rfturn gftNfxtBlodkIndrfmfnt(visiblfRfdt, orifntbtion);
        }
    }

    /**
     * Cbllfd to gft tif blodk indrfmfnt for upwbrd sdrolling in dbsfs of
     * iorizontbl sdrolling, or for vfrtidbl sdrolling of b tbblf witi
     * vbribblf row ifigits.
     */
    privbtf int gftPrfviousBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt,
                                          int orifntbtion) {
        // Mfbsurf bbdk from visiblf lfbding fdgf
        // If wf iit tif dfll on its lfbding fdgf, it bfdomfs tif lfbding dfll.
        // Elsf, usf following dfll

        int row;
        int dol;

        int   nfwEdgf;
        Point nfwCfllLod;

        int visiblfLfbdingEdgf = lfbdingEdgf(visiblfRfdt, orifntbtion);
        boolfbn lfftToRigit = gftComponfntOrifntbtion().isLfftToRigit();
        int nfwLfbdingEdgf;

        // Rougily dftfrminf tif nfw lfbding fdgf by mfbsuring bbdk from tif
        // lfbding visiblf fdgf by tif sizf of tif visiblf rfdt, bnd find tif
        // dfll tifrf.
        if (orifntbtion == SwingConstbnts.VERTICAL) {
            nfwEdgf = visiblfLfbdingEdgf - visiblfRfdt.ifigit;
            int x = visiblfRfdt.x + (lfftToRigit ? 0 : visiblfRfdt.widti);
            nfwCfllLod = nfw Point(x, nfwEdgf);
        }
        flsf if (lfftToRigit) {
            nfwEdgf = visiblfLfbdingEdgf - visiblfRfdt.widti;
            nfwCfllLod = nfw Point(nfwEdgf, visiblfRfdt.y);
        }
        flsf { // Horizontbl, rigit-to-lfft
            nfwEdgf = visiblfLfbdingEdgf + visiblfRfdt.widti;
            nfwCfllLod = nfw Point(nfwEdgf - 1, visiblfRfdt.y);
        }
        row = rowAtPoint(nfwCfllLod);
        dol = dolumnAtPoint(nfwCfllLod);

        // If wf'rf mfbsuring pbst tif bfginning of tif tbblf, wf gft bn invblid
        // dfll.  Just go to tif bfginning of tif tbblf in tiis dbsf.
        if (orifntbtion == SwingConstbnts.VERTICAL & row < 0) {
            nfwLfbdingEdgf = 0;
        }
        flsf if (orifntbtion == SwingConstbnts.HORIZONTAL & dol < 0) {
            if (lfftToRigit) {
                nfwLfbdingEdgf = 0;
            }
            flsf {
                nfwLfbdingEdgf = gftWidti();
            }
        }
        flsf {
            // Rffinf our mfbsurfmfnt
            Rfdtbnglf nfwCfllRfdt = gftCfllRfdt(row, dol, truf);
            int nfwCfllLfbdingEdgf = lfbdingEdgf(nfwCfllRfdt, orifntbtion);
            int nfwCfllTrbilingEdgf = trbilingEdgf(nfwCfllRfdt, orifntbtion);

            // Usublly, wf iit in tif middlf of nfwCfll, bnd wbnt to sdroll to
            // tif bfginning of tif dfll bftfr nfwCfll.  But tifrf brf b
            // douplf dornfr dbsfs wifrf wf wbnt to sdroll to tif bfginning of
            // nfwCfll itsflf.  Tifsf dbsfs brf:
            // 1) nfwCfll is so lbrgf tibt it fnds bt or fxtfnds into tif
            //    visiblfRfdt (nfwCfll is tif lfbding dfll, or is bdjbdfnt to
            //    tif lfbding dfll)
            // 2) nfwEdgf ibppfns to fbll rigit on tif bfginning of b dfll

            // Cbsf 1
            if ((orifntbtion == SwingConstbnts.VERTICAL || lfftToRigit) &&
                (nfwCfllTrbilingEdgf >= visiblfLfbdingEdgf)) {
                nfwLfbdingEdgf = nfwCfllLfbdingEdgf;
            }
            flsf if (orifntbtion == SwingConstbnts.HORIZONTAL &&
                     !lfftToRigit &&
                     nfwCfllTrbilingEdgf <= visiblfLfbdingEdgf) {
                nfwLfbdingEdgf = nfwCfllLfbdingEdgf;
            }
            // Cbsf 2:
            flsf if (nfwEdgf == nfwCfllLfbdingEdgf) {
                nfwLfbdingEdgf = nfwCfllLfbdingEdgf;
            }
            // Common dbsf: sdroll to dfll bftfr nfwCfll
            flsf {
                nfwLfbdingEdgf = nfwCfllTrbilingEdgf;
            }
        }
        rfturn Mbti.bbs(visiblfLfbdingEdgf - nfwLfbdingEdgf);
    }

    /**
     * Cbllfd to gft tif blodk indrfmfnt for downwbrd sdrolling in dbsfs of
     * iorizontbl sdrolling, or for vfrtidbl sdrolling of b tbblf witi
     * vbribblf row ifigits.
     */
    privbtf int gftNfxtBlodkIndrfmfnt(Rfdtbnglf visiblfRfdt,
                                      int orifntbtion) {
        // Find tif dfll bt tif trbiling fdgf.  Rfturn tif distbndf to put
        // tibt dfll bt tif lfbding fdgf.
        int trbilingRow = gftTrbilingRow(visiblfRfdt);
        int trbilingCol = gftTrbilingCol(visiblfRfdt);

        Rfdtbnglf dfllRfdt;
        boolfbn dfllFillsVis;

        int dfllLfbdingEdgf;
        int dfllTrbilingEdgf;
        int nfwLfbdingEdgf;
        int visiblfLfbdingEdgf = lfbdingEdgf(visiblfRfdt, orifntbtion);

        // If wf douldn't find trbiling dfll, just rfturn tif sizf of tif
        // visiblfRfdt.  Notf tibt, for instbndf, wf don't nffd tif
        // trbilingCol to prodffd if wf'rf sdrolling vfrtidblly, bfdbusf
        // dfllRfdt will still fill in tif rfquirfd dimfnsions.  Tiis would
        // ibppfn if wf'rf sdrolling vfrtidblly, bnd tif tbblf is not widf
        // fnougi to fill tif visiblfRfdt.
        if (orifntbtion == SwingConstbnts.VERTICAL && trbilingRow < 0) {
            rfturn visiblfRfdt.ifigit;
        }
        flsf if (orifntbtion == SwingConstbnts.HORIZONTAL && trbilingCol < 0) {
            rfturn visiblfRfdt.widti;
        }
        dfllRfdt = gftCfllRfdt(trbilingRow, trbilingCol, truf);
        dfllLfbdingEdgf = lfbdingEdgf(dfllRfdt, orifntbtion);
        dfllTrbilingEdgf = trbilingEdgf(dfllRfdt, orifntbtion);

        if (orifntbtion == SwingConstbnts.VERTICAL ||
            gftComponfntOrifntbtion().isLfftToRigit()) {
            dfllFillsVis = dfllLfbdingEdgf <= visiblfLfbdingEdgf;
        }
        flsf { // Horizontbl, rigit-to-lfft
            dfllFillsVis = dfllLfbdingEdgf >= visiblfLfbdingEdgf;
        }

        if (dfllFillsVis) {
            // Tif visiblfRfdt dontbins b singlf lbrgf dfll.  Sdroll to tif fnd
            // of tiis dfll, so tif following dfll is tif first dfll.
            nfwLfbdingEdgf = dfllTrbilingEdgf;
        }
        flsf if (dfllTrbilingEdgf == trbilingEdgf(visiblfRfdt, orifntbtion)) {
            // Tif trbiling dfll ibppfns to fnd rigit bt tif fnd of tif
            // visiblfRfdt.  Agbin, sdroll to tif bfginning of tif nfxt dfll.
            nfwLfbdingEdgf = dfllTrbilingEdgf;
        }
        flsf {
            // Common dbsf: tif trbiling dfll is pbrtiblly visiblf, bnd isn't
            // big fnougi to tbkf up tif fntirf visiblfRfdt.  Sdroll so it
            // bfdomfs tif lfbding dfll.
            nfwLfbdingEdgf = dfllLfbdingEdgf;
        }
        rfturn Mbti.bbs(nfwLfbdingEdgf - visiblfLfbdingEdgf);
    }

    /*
     * Rfturn tif row bt tif top of tif visiblfRfdt
     *
     * Mby rfturn -1
     */
    privbtf int gftLfbdingRow(Rfdtbnglf visiblfRfdt) {
        Point lfbdingPoint;

        if (gftComponfntOrifntbtion().isLfftToRigit()) {
            lfbdingPoint = nfw Point(visiblfRfdt.x, visiblfRfdt.y);
        }
        flsf {
            lfbdingPoint = nfw Point(visiblfRfdt.x + visiblfRfdt.widti - 1,
                                     visiblfRfdt.y);
        }
        rfturn rowAtPoint(lfbdingPoint);
    }

    /*
     * Rfturn tif dolumn bt tif lfbding fdgf of tif visiblfRfdt.
     *
     * Mby rfturn -1
     */
    privbtf int gftLfbdingCol(Rfdtbnglf visiblfRfdt) {
        Point lfbdingPoint;

        if (gftComponfntOrifntbtion().isLfftToRigit()) {
            lfbdingPoint = nfw Point(visiblfRfdt.x, visiblfRfdt.y);
        }
        flsf {
            lfbdingPoint = nfw Point(visiblfRfdt.x + visiblfRfdt.widti - 1,
                                     visiblfRfdt.y);
        }
        rfturn dolumnAtPoint(lfbdingPoint);
    }

    /*
     * Rfturn tif row bt tif bottom of tif visiblfRfdt.
     *
     * Mby rfturn -1
     */
    privbtf int gftTrbilingRow(Rfdtbnglf visiblfRfdt) {
        Point trbilingPoint;

        if (gftComponfntOrifntbtion().isLfftToRigit()) {
            trbilingPoint = nfw Point(visiblfRfdt.x,
                                      visiblfRfdt.y + visiblfRfdt.ifigit - 1);
        }
        flsf {
            trbilingPoint = nfw Point(visiblfRfdt.x + visiblfRfdt.widti - 1,
                                      visiblfRfdt.y + visiblfRfdt.ifigit - 1);
        }
        rfturn rowAtPoint(trbilingPoint);
    }

    /*
     * Rfturn tif dolumn bt tif trbiling fdgf of tif visiblfRfdt.
     *
     * Mby rfturn -1
     */
    privbtf int gftTrbilingCol(Rfdtbnglf visiblfRfdt) {
        Point trbilingPoint;

        if (gftComponfntOrifntbtion().isLfftToRigit()) {
            trbilingPoint = nfw Point(visiblfRfdt.x + visiblfRfdt.widti - 1,
                                      visiblfRfdt.y);
        }
        flsf {
            trbilingPoint = nfw Point(visiblfRfdt.x, visiblfRfdt.y);
        }
        rfturn dolumnAtPoint(trbilingPoint);
    }

    /*
     * Rfturns tif lfbding fdgf ("bfginning") of tif givfn Rfdtbnglf.
     * For VERTICAL, tiis is tif top, for lfft-to-rigit, tif lfft sidf, bnd for
     * rigit-to-lfft, tif rigit sidf.
     */
    privbtf int lfbdingEdgf(Rfdtbnglf rfdt, int orifntbtion) {
        if (orifntbtion == SwingConstbnts.VERTICAL) {
            rfturn rfdt.y;
        }
        flsf if (gftComponfntOrifntbtion().isLfftToRigit()) {
            rfturn rfdt.x;
        }
        flsf { // Horizontbl, rigit-to-lfft
            rfturn rfdt.x + rfdt.widti;
        }
    }

    /*
     * Rfturns tif trbiling fdgf ("fnd") of tif givfn Rfdtbnglf.
     * For VERTICAL, tiis is tif bottom, for lfft-to-rigit, tif rigit sidf, bnd
     * for rigit-to-lfft, tif lfft sidf.
     */
    privbtf int trbilingEdgf(Rfdtbnglf rfdt, int orifntbtion) {
        if (orifntbtion == SwingConstbnts.VERTICAL) {
            rfturn rfdt.y + rfdt.ifigit;
        }
        flsf if (gftComponfntOrifntbtion().isLfftToRigit()) {
            rfturn rfdt.x + rfdt.widti;
        }
        flsf { // Horizontbl, rigit-to-lfft
            rfturn rfdt.x;
        }
    }

    /**
     * Rfturns fblsf if <dodf>butoRfsizfModf</dodf> is sft to
     * <dodf>AUTO_RESIZE_OFF</dodf>, wiidi indidbtfs tibt tif
     * widti of tif vifwport dofs not dftfrminf tif widti
     * of tif tbblf.  Otifrwisf rfturns truf.
     *
     * @rfturn fblsf if <dodf>butoRfsizfModf</dodf> is sft
     *   to <dodf>AUTO_RESIZE_OFF</dodf>, otifrwisf rfturns truf
     * @sff Sdrollbblf#gftSdrollbblfTrbdksVifwportWidti
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportWidti() {
        rfturn !(butoRfsizfModf == AUTO_RESIZE_OFF);
    }

    /**
     * Rfturns {@dodf fblsf} to indidbtf tibt tif ifigit of tif vifwport dofs
     * not dftfrminf tif ifigit of tif tbblf, unlfss
     * {@dodf gftFillsVifwportHfigit} is {@dodf truf} bnd tif prfffrrfd ifigit
     * of tif tbblf is smbllfr tibn tif vifwport's ifigit.
     *
     * @rfturn {@dodf fblsf} unlfss {@dodf gftFillsVifwportHfigit} is
     *         {@dodf truf} bnd tif tbblf nffds to bf strftdifd to fill
     *         tif vifwport
     * @sff Sdrollbblf#gftSdrollbblfTrbdksVifwportHfigit
     * @sff #sftFillsVifwportHfigit
     * @sff #gftFillsVifwportHfigit
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportHfigit() {
        Contbinfr pbrfnt = SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis);
        rfturn gftFillsVifwportHfigit()
               && pbrfnt instbndfof JVifwport
               && pbrfnt.gftHfigit() > gftPrfffrrfdSizf().ifigit;
    }

    /**
     * Sfts wiftifr or not tiis tbblf is blwbys mbdf lbrgf fnougi
     * to fill tif ifigit of bn fndlosing vifwport. If tif prfffrrfd
     * ifigit of tif tbblf is smbllfr tibn tif vifwport, tifn tif tbblf
     * will bf strftdifd to fill tif vifwport. In otifr words, tiis
     * fnsurfs tif tbblf is nfvfr smbllfr tibn tif vifwport.
     * Tif dffbult for tiis propfrty is {@dodf fblsf}.
     *
     * @pbrbm fillsVifwportHfigit wiftifr or not tiis tbblf is blwbys
     *        mbdf lbrgf fnougi to fill tif ifigit of bn fndlosing
     *        vifwport
     * @sff #gftFillsVifwportHfigit
     * @sff #gftSdrollbblfTrbdksVifwportHfigit
     * @sindf 1.6
     * @bfbninfo
     *      bound: truf
     *      dfsdription: Wiftifr or not tiis tbblf is blwbys mbdf lbrgf fnougi
     *                   to fill tif ifigit of bn fndlosing vifwport
     */
    publid void sftFillsVifwportHfigit(boolfbn fillsVifwportHfigit) {
        boolfbn old = tiis.fillsVifwportHfigit;
        tiis.fillsVifwportHfigit = fillsVifwportHfigit;
        rfsizfAndRfpbint();
        firfPropfrtyCibngf("fillsVifwportHfigit", old, fillsVifwportHfigit);
    }

    /**
     * Rfturns wiftifr or not tiis tbblf is blwbys mbdf lbrgf fnougi
     * to fill tif ifigit of bn fndlosing vifwport.
     *
     * @rfturn wiftifr or not tiis tbblf is blwbys mbdf lbrgf fnougi
     *         to fill tif ifigit of bn fndlosing vifwport
     * @sff #sftFillsVifwportHfigit
     * @sindf 1.6
     */
    publid boolfbn gftFillsVifwportHfigit() {
        rfturn fillsVifwportHfigit;
    }

//
// Protfdtfd Mftiods
//

    protfdtfd boolfbn prodfssKfyBinding(KfyStrokf ks, KfyEvfnt f,
                                        int dondition, boolfbn prfssfd) {
        boolfbn rftVbluf = supfr.prodfssKfyBinding(ks, f, dondition, prfssfd);

        // Stbrt fditing wifn b kfy is typfd. UI dlbssfs dbn disbblf tiis bfibvior
        // by sftting tif dlifnt propfrty JTbblf.butoStbrtsEdit to Boolfbn.FALSE.
        if (!rftVbluf && dondition == WHEN_ANCESTOR_OF_FOCUSED_COMPONENT &&
            isFodusOwnfr() &&
            !Boolfbn.FALSE.fqubls(gftClifntPropfrty("JTbblf.butoStbrtsEdit"))) {
            // Wf do not ibvf b binding for tif fvfnt.
            Componfnt fditorComponfnt = gftEditorComponfnt();
            if (fditorComponfnt == null) {
                // Only bttfmpt to instbll tif fditor on b KEY_PRESSED,
                if (f == null || f.gftID() != KfyEvfnt.KEY_PRESSED) {
                    rfturn fblsf;
                }
                // Don't stbrt wifn just b modififr is prfssfd
                int dodf = f.gftKfyCodf();
                if (dodf == KfyEvfnt.VK_SHIFT || dodf == KfyEvfnt.VK_CONTROL ||
                    dodf == KfyEvfnt.VK_ALT) {
                    rfturn fblsf;
                }
                // Try to instbll tif fditor
                int lfbdRow = gftSflfdtionModfl().gftLfbdSflfdtionIndfx();
                int lfbdColumn = gftColumnModfl().gftSflfdtionModfl().
                                   gftLfbdSflfdtionIndfx();
                if (lfbdRow != -1 && lfbdColumn != -1 && !isEditing()) {
                    if (!fditCfllAt(lfbdRow, lfbdColumn, f)) {
                        rfturn fblsf;
                    }
                }
                fditorComponfnt = gftEditorComponfnt();
                if (fditorComponfnt == null) {
                    rfturn fblsf;
                }
            }
            // If tif fditorComponfnt is b JComponfnt, pbss tif fvfnt to it.
            if (fditorComponfnt instbndfof JComponfnt) {
                rftVbluf = ((JComponfnt)fditorComponfnt).prodfssKfyBinding
                                        (ks, f, WHEN_FOCUSED, prfssfd);
                // If wf ibvf stbrtfd bn fditor bs b rfsult of tif usfr
                // prfssing b kfy bnd tif surrfndfrsFodusOnKfystrokf propfrty
                // is truf, givf tif fodus to tif nfw fditor.
                if (gftSurrfndfrsFodusOnKfystrokf()) {
                    fditorComponfnt.rfqufstFodus();
                }
            }
        }
        rfturn rftVbluf;
    }

    /**
     * Crfbtfs dffbult dfll rfndfrfrs for objfdts, numbfrs, doublfs, dbtfs,
     * boolfbns, bnd idons.
     * @sff jbvbx.swing.tbblf.DffbultTbblfCfllRfndfrfr
     *
     */
    protfdtfd void drfbtfDffbultRfndfrfrs() {
        dffbultRfndfrfrsByColumnClbss = nfw UIDffbults(8, 0.75f);

        // Objfdts
        dffbultRfndfrfrsByColumnClbss.put(Objfdt.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw DffbultTbblfCfllRfndfrfr.UIRfsourdf());

        // Numbfrs
        dffbultRfndfrfrsByColumnClbss.put(Numbfr.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw NumbfrRfndfrfr());

        // Doublfs bnd Flobts
        dffbultRfndfrfrsByColumnClbss.put(Flobt.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw DoublfRfndfrfr());
        dffbultRfndfrfrsByColumnClbss.put(Doublf.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw DoublfRfndfrfr());

        // Dbtfs
        dffbultRfndfrfrsByColumnClbss.put(Dbtf.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw DbtfRfndfrfr());

        // Idons bnd ImbgfIdons
        dffbultRfndfrfrsByColumnClbss.put(Idon.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw IdonRfndfrfr());
        dffbultRfndfrfrsByColumnClbss.put(ImbgfIdon.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw IdonRfndfrfr());

        // Boolfbns
        dffbultRfndfrfrsByColumnClbss.put(Boolfbn.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw BoolfbnRfndfrfr());
    }

    /**
     * Dffbult Rfndfrfrs
     **/
    stbtid dlbss NumbfrRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr.UIRfsourdf {
        publid NumbfrRfndfrfr() {
            supfr();
            sftHorizontblAlignmfnt(JLbbfl.RIGHT);
        }
    }

    stbtid dlbss DoublfRfndfrfr fxtfnds NumbfrRfndfrfr {
        NumbfrFormbt formbttfr;
        publid DoublfRfndfrfr() { supfr(); }

        publid void sftVbluf(Objfdt vbluf) {
            if (formbttfr == null) {
                formbttfr = NumbfrFormbt.gftInstbndf();
            }
            sftTfxt((vbluf == null) ? "" : formbttfr.formbt(vbluf));
        }
    }

    stbtid dlbss DbtfRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr.UIRfsourdf {
        DbtfFormbt formbttfr;
        publid DbtfRfndfrfr() { supfr(); }

        publid void sftVbluf(Objfdt vbluf) {
            if (formbttfr==null) {
                formbttfr = DbtfFormbt.gftDbtfInstbndf();
            }
            sftTfxt((vbluf == null) ? "" : formbttfr.formbt(vbluf));
        }
    }

    stbtid dlbss IdonRfndfrfr fxtfnds DffbultTbblfCfllRfndfrfr.UIRfsourdf {
        publid IdonRfndfrfr() {
            supfr();
            sftHorizontblAlignmfnt(JLbbfl.CENTER);
        }
        publid void sftVbluf(Objfdt vbluf) { sftIdon((vbluf instbndfof Idon) ? (Idon)vbluf : null); }
    }


    stbtid dlbss BoolfbnRfndfrfr fxtfnds JCifdkBox implfmfnts TbblfCfllRfndfrfr, UIRfsourdf
    {
        privbtf stbtid finbl Bordfr noFodusBordfr = nfw EmptyBordfr(1, 1, 1, 1);

        publid BoolfbnRfndfrfr() {
            supfr();
            sftHorizontblAlignmfnt(JLbbfl.CENTER);
            sftBordfrPbintfd(truf);
        }

        publid Componfnt gftTbblfCfllRfndfrfrComponfnt(JTbblf tbblf, Objfdt vbluf,
                                                       boolfbn isSflfdtfd, boolfbn ibsFodus, int row, int dolumn) {
            if (isSflfdtfd) {
                sftForfground(tbblf.gftSflfdtionForfground());
                supfr.sftBbdkground(tbblf.gftSflfdtionBbdkground());
            }
            flsf {
                sftForfground(tbblf.gftForfground());
                sftBbdkground(tbblf.gftBbdkground());
            }
            sftSflfdtfd((vbluf != null && ((Boolfbn)vbluf).boolfbnVbluf()));

            if (ibsFodus) {
                sftBordfr(UIMbnbgfr.gftBordfr("Tbblf.fodusCfllHigiligitBordfr"));
            } flsf {
                sftBordfr(noFodusBordfr);
            }

            rfturn tiis;
        }
    }

    /**
     * Crfbtfs dffbult dfll fditors for objfdts, numbfrs, bnd boolfbn vblufs.
     * @sff DffbultCfllEditor
     */
    protfdtfd void drfbtfDffbultEditors() {
        dffbultEditorsByColumnClbss = nfw UIDffbults(3, 0.75f);

        // Objfdts
        dffbultEditorsByColumnClbss.put(Objfdt.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw GfnfridEditor());

        // Numbfrs
        dffbultEditorsByColumnClbss.put(Numbfr.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw NumbfrEditor());

        // Boolfbns
        dffbultEditorsByColumnClbss.put(Boolfbn.dlbss, (UIDffbults.LbzyVbluf)
                t -> nfw BoolfbnEditor());
    }

    /**
     * Dffbult Editors
     */
    stbtid dlbss GfnfridEditor fxtfnds DffbultCfllEditor {

        Clbss<?>[] brgTypfs = nfw Clbss<?>[]{String.dlbss};
        jbvb.lbng.rfflfdt.Construdtor<?> donstrudtor;
        Objfdt vbluf;

        publid GfnfridEditor() {
            supfr(nfw JTfxtFifld());
            gftComponfnt().sftNbmf("Tbblf.fditor");
        }

        publid boolfbn stopCfllEditing() {
            String s = (String)supfr.gftCfllEditorVbluf();
            // Hfrf wf brf dfbling witi tif dbsf wifrf b usfr
            // ibs dflftfd tif string vbluf in b dfll, possibly
            // bftfr b fbilfd vblidbtion. Rfturn null, so tibt
            // tify ibvf tif option to rfplbdf tif vbluf witi
            // null or usf fsdbpf to rfstorf tif originbl.
            // For Strings, rfturn "" for bbdkwbrd dompbtibility.
            try {
                if ("".fqubls(s)) {
                    if (donstrudtor.gftDfdlbringClbss() == String.dlbss) {
                        vbluf = s;
                    }
                    rfturn supfr.stopCfllEditing();
                }

                SwingUtilitifs2.difdkAddfss(donstrudtor.gftModififrs());
                vbluf = donstrudtor.nfwInstbndf(nfw Objfdt[]{s});
            }
            dbtdi (Exdfption f) {
                ((JComponfnt)gftComponfnt()).sftBordfr(nfw LinfBordfr(Color.rfd));
                rfturn fblsf;
            }
            rfturn supfr.stopCfllEditing();
        }

        publid Componfnt gftTbblfCfllEditorComponfnt(JTbblf tbblf, Objfdt vbluf,
                                                 boolfbn isSflfdtfd,
                                                 int row, int dolumn) {
            tiis.vbluf = null;
            ((JComponfnt)gftComponfnt()).sftBordfr(nfw LinfBordfr(Color.blbdk));
            try {
                Clbss<?> typf = tbblf.gftColumnClbss(dolumn);
                // Sindf our obligbtion is to produdf b vbluf wiidi is
                // bssignbblf for tif rfquirfd typf it is OK to usf tif
                // String donstrudtor for dolumns wiidi brf dfdlbrfd
                // to dontbin Objfdts. A String is bn Objfdt.
                if (typf == Objfdt.dlbss) {
                    typf = String.dlbss;
                }
                RfflfdtUtil.difdkPbdkbgfAddfss(typf);
                SwingUtilitifs2.difdkAddfss(typf.gftModififrs());
                donstrudtor = typf.gftConstrudtor(brgTypfs);
            }
            dbtdi (Exdfption f) {
                rfturn null;
            }
            rfturn supfr.gftTbblfCfllEditorComponfnt(tbblf, vbluf, isSflfdtfd, row, dolumn);
        }

        publid Objfdt gftCfllEditorVbluf() {
            rfturn vbluf;
        }
    }

    stbtid dlbss NumbfrEditor fxtfnds GfnfridEditor {

        publid NumbfrEditor() {
            ((JTfxtFifld)gftComponfnt()).sftHorizontblAlignmfnt(JTfxtFifld.RIGHT);
        }
    }

    stbtid dlbss BoolfbnEditor fxtfnds DffbultCfllEditor {
        publid BoolfbnEditor() {
            supfr(nfw JCifdkBox());
            JCifdkBox difdkBox = (JCifdkBox)gftComponfnt();
            difdkBox.sftHorizontblAlignmfnt(JCifdkBox.CENTER);
        }
    }

    /**
     * Initiblizfs tbblf propfrtifs to tifir dffbult vblufs.
     */
    protfdtfd void initiblizfLodblVbrs() {
        updbtfSflfdtionOnSort = truf;
        sftOpbquf(truf);
        drfbtfDffbultRfndfrfrs();
        drfbtfDffbultEditors();

        sftTbblfHfbdfr(drfbtfDffbultTbblfHfbdfr());

        sftSiowGrid(truf);
        sftAutoRfsizfModf(AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        sftRowHfigit(16);
        isRowHfigitSft = fblsf;
        sftRowMbrgin(1);
        sftRowSflfdtionAllowfd(truf);
        sftCfllEditor(null);
        sftEditingColumn(-1);
        sftEditingRow(-1);
        sftSurrfndfrsFodusOnKfystrokf(fblsf);
        sftPrfffrrfdSdrollbblfVifwportSizf(nfw Dimfnsion(450, 400));

        // I'm rfgistfrfd to do tool tips so wf dbn drbw tips for tif rfndfrfrs
        ToolTipMbnbgfr toolTipMbnbgfr = ToolTipMbnbgfr.sibrfdInstbndf();
        toolTipMbnbgfr.rfgistfrComponfnt(tiis);

        sftAutosdrolls(truf);
    }

    /**
     * Rfturns tif dffbult tbblf modfl objfdt, wiidi is
     * b <dodf>DffbultTbblfModfl</dodf>.  A subdlbss dbn ovfrridf tiis
     * mftiod to rfturn b difffrfnt tbblf modfl objfdt.
     *
     * @rfturn tif dffbult tbblf modfl objfdt
     * @sff jbvbx.swing.tbblf.DffbultTbblfModfl
     */
    protfdtfd TbblfModfl drfbtfDffbultDbtbModfl() {
        rfturn nfw DffbultTbblfModfl();
    }

    /**
     * Rfturns tif dffbult dolumn modfl objfdt, wiidi is
     * b <dodf>DffbultTbblfColumnModfl</dodf>.  A subdlbss dbn ovfrridf tiis
     * mftiod to rfturn b difffrfnt dolumn modfl objfdt.
     *
     * @rfturn tif dffbult dolumn modfl objfdt
     * @sff jbvbx.swing.tbblf.DffbultTbblfColumnModfl
     */
    protfdtfd TbblfColumnModfl drfbtfDffbultColumnModfl() {
        rfturn nfw DffbultTbblfColumnModfl();
    }

    /**
     * Rfturns tif dffbult sflfdtion modfl objfdt, wiidi is
     * b <dodf>DffbultListSflfdtionModfl</dodf>.  A subdlbss dbn ovfrridf tiis
     * mftiod to rfturn b difffrfnt sflfdtion modfl objfdt.
     *
     * @rfturn tif dffbult sflfdtion modfl objfdt
     * @sff jbvbx.swing.DffbultListSflfdtionModfl
     */
    protfdtfd ListSflfdtionModfl drfbtfDffbultSflfdtionModfl() {
        rfturn nfw DffbultListSflfdtionModfl();
    }

    /**
     * Rfturns tif dffbult tbblf ifbdfr objfdt, wiidi is
     * b <dodf>JTbblfHfbdfr</dodf>.  A subdlbss dbn ovfrridf tiis
     * mftiod to rfturn b difffrfnt tbblf ifbdfr objfdt.
     *
     * @rfturn tif dffbult tbblf ifbdfr objfdt
     * @sff jbvbx.swing.tbblf.JTbblfHfbdfr
     */
    protfdtfd JTbblfHfbdfr drfbtfDffbultTbblfHfbdfr() {
        rfturn nfw JTbblfHfbdfr(dolumnModfl);
    }

    /**
     * Equivblfnt to <dodf>rfvblidbtf</dodf> followfd by <dodf>rfpbint</dodf>.
     */
    protfdtfd void rfsizfAndRfpbint() {
        rfvblidbtf();
        rfpbint();
    }

    /**
     * Rfturns tif bdtivf dfll fditor, wiidi is {@dodf null} if tif tbblf
     * is not durrfntly fditing.
     *
     * @rfturn tif {@dodf TbblfCfllEditor} tibt dofs tif fditing,
     *         or {@dodf null} if tif tbblf is not durrfntly fditing.
     * @sff #dfllEditor
     * @sff #gftCfllEditor(int, int)
     */
    publid TbblfCfllEditor gftCfllEditor() {
        rfturn dfllEditor;
    }

    /**
     * Sfts tif bdtivf dfll fditor.
     *
     * @pbrbm bnEditor tif bdtivf dfll fditor
     * @sff #dfllEditor
     * @bfbninfo
     *  bound: truf
     *  dfsdription: Tif tbblf's bdtivf dfll fditor.
     */
    publid void sftCfllEditor(TbblfCfllEditor bnEditor) {
        TbblfCfllEditor oldEditor = dfllEditor;
        dfllEditor = bnEditor;
        firfPropfrtyCibngf("tbblfCfllEditor", oldEditor, bnEditor);
    }

    /**
     * Sfts tif <dodf>fditingColumn</dodf> vbribblf.
     * @pbrbm bColumn  tif dolumn of tif dfll to bf fditfd
     *
     * @sff #fditingColumn
     */
    publid void sftEditingColumn(int bColumn) {
        fditingColumn = bColumn;
    }

    /**
     * Sfts tif <dodf>fditingRow</dodf> vbribblf.
     * @pbrbm bRow  tif row of tif dfll to bf fditfd
     *
     * @sff #fditingRow
     */
    publid void sftEditingRow(int bRow) {
        fditingRow = bRow;
    }

    /**
     * Rfturns bn bppropribtf rfndfrfr for tif dfll spfdififd by tiis row bnd
     * dolumn. If tif <dodf>TbblfColumn</dodf> for tiis dolumn ibs b non-null
     * rfndfrfr, rfturns tibt.  If not, finds tif dlbss of tif dbtb in
     * tiis dolumn (using <dodf>gftColumnClbss</dodf>)
     * bnd rfturns tif dffbult rfndfrfr for tiis typf of dbtb.
     * <p>
     * <b>Notf:</b>
     * Tirougiout tif tbblf pbdkbgf, tif intfrnbl implfmfntbtions blwbys
     * usf tiis mftiod to providf rfndfrfrs so tibt tiis dffbult bfibvior
     * dbn bf sbffly ovfrriddfn by b subdlbss.
     *
     * @pbrbm row       tif row of tif dfll to rfndfr, wifrf 0 is tif first row
     * @pbrbm dolumn    tif dolumn of tif dfll to rfndfr,
     *                  wifrf 0 is tif first dolumn
     * @rfturn tif bssignfd rfndfrfr; if <dodf>null</dodf>
     *                  rfturns tif dffbult rfndfrfr
     *                  for tiis typf of objfdt
     * @sff jbvbx.swing.tbblf.DffbultTbblfCfllRfndfrfr
     * @sff jbvbx.swing.tbblf.TbblfColumn#sftCfllRfndfrfr
     * @sff #sftDffbultRfndfrfr
     */
    publid TbblfCfllRfndfrfr gftCfllRfndfrfr(int row, int dolumn) {
        TbblfColumn tbblfColumn = gftColumnModfl().gftColumn(dolumn);
        TbblfCfllRfndfrfr rfndfrfr = tbblfColumn.gftCfllRfndfrfr();
        if (rfndfrfr == null) {
            rfndfrfr = gftDffbultRfndfrfr(gftColumnClbss(dolumn));
        }
        rfturn rfndfrfr;
    }

    /**
     * Prfpbrfs tif rfndfrfr by qufrying tif dbtb modfl for tif
     * vbluf bnd sflfdtion stbtf
     * of tif dfll bt <dodf>row</dodf>, <dodf>dolumn</dodf>.
     * Rfturns tif domponfnt (mby bf b <dodf>Componfnt</dodf>
     * or b <dodf>JComponfnt</dodf>) undfr tif fvfnt lodbtion.
     * <p>
     * During b printing opfrbtion, tiis mftiod will donfigurf tif
     * rfndfrfr witiout indidbting sflfdtion or fodus, to prfvfnt
     * tifm from bppfbring in tif printfd output. To do otifr
     * dustomizbtions bbsfd on wiftifr or not tif tbblf is bfing
     * printfd, you dbn difdk tif vbluf of
     * {@link jbvbx.swing.JComponfnt#isPbintingForPrint()}, fitifr ifrf
     * or witiin dustom rfndfrfrs.
     * <p>
     * <b>Notf:</b>
     * Tirougiout tif tbblf pbdkbgf, tif intfrnbl implfmfntbtions blwbys
     * usf tiis mftiod to prfpbrf rfndfrfrs so tibt tiis dffbult bfibvior
     * dbn bf sbffly ovfrriddfn by b subdlbss.
     *
     * @pbrbm rfndfrfr  tif <dodf>TbblfCfllRfndfrfr</dodf> to prfpbrf
     * @pbrbm row       tif row of tif dfll to rfndfr, wifrf 0 is tif first row
     * @pbrbm dolumn    tif dolumn of tif dfll to rfndfr,
     *                  wifrf 0 is tif first dolumn
     * @rfturn          tif <dodf>Componfnt</dodf> undfr tif fvfnt lodbtion
     */
    publid Componfnt prfpbrfRfndfrfr(TbblfCfllRfndfrfr rfndfrfr, int row, int dolumn) {
        Objfdt vbluf = gftVblufAt(row, dolumn);

        boolfbn isSflfdtfd = fblsf;
        boolfbn ibsFodus = fblsf;

        // Only indidbtf tif sflfdtion bnd fodusfd dfll if not printing
        if (!isPbintingForPrint()) {
            isSflfdtfd = isCfllSflfdtfd(row, dolumn);

            boolfbn rowIsLfbd =
                (sflfdtionModfl.gftLfbdSflfdtionIndfx() == row);
            boolfbn dolIsLfbd =
                (dolumnModfl.gftSflfdtionModfl().gftLfbdSflfdtionIndfx() == dolumn);

            ibsFodus = (rowIsLfbd && dolIsLfbd) && isFodusOwnfr();
        }

        rfturn rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(tiis, vbluf,
                                                      isSflfdtfd, ibsFodus,
                                                      row, dolumn);
    }

    /**
     * Rfturns bn bppropribtf fditor for tif dfll spfdififd by
     * <dodf>row</dodf> bnd <dodf>dolumn</dodf>. If tif
     * <dodf>TbblfColumn</dodf> for tiis dolumn ibs b non-null fditor,
     * rfturns tibt.  If not, finds tif dlbss of tif dbtb in tiis
     * dolumn (using <dodf>gftColumnClbss</dodf>)
     * bnd rfturns tif dffbult fditor for tiis typf of dbtb.
     * <p>
     * <b>Notf:</b>
     * Tirougiout tif tbblf pbdkbgf, tif intfrnbl implfmfntbtions blwbys
     * usf tiis mftiod to providf fditors so tibt tiis dffbult bfibvior
     * dbn bf sbffly ovfrriddfn by b subdlbss.
     *
     * @pbrbm row       tif row of tif dfll to fdit, wifrf 0 is tif first row
     * @pbrbm dolumn    tif dolumn of tif dfll to fdit,
     *                  wifrf 0 is tif first dolumn
     * @rfturn          tif fditor for tiis dfll;
     *                  if <dodf>null</dodf> rfturn tif dffbult fditor for
     *                  tiis typf of dfll
     * @sff DffbultCfllEditor
     */
    publid TbblfCfllEditor gftCfllEditor(int row, int dolumn) {
        TbblfColumn tbblfColumn = gftColumnModfl().gftColumn(dolumn);
        TbblfCfllEditor fditor = tbblfColumn.gftCfllEditor();
        if (fditor == null) {
            fditor = gftDffbultEditor(gftColumnClbss(dolumn));
        }
        rfturn fditor;
    }


    /**
     * Prfpbrfs tif fditor by qufrying tif dbtb modfl for tif vbluf bnd
     * sflfdtion stbtf of tif dfll bt <dodf>row</dodf>, <dodf>dolumn</dodf>.
     * <p>
     * <b>Notf:</b>
     * Tirougiout tif tbblf pbdkbgf, tif intfrnbl implfmfntbtions blwbys
     * usf tiis mftiod to prfpbrf fditors so tibt tiis dffbult bfibvior
     * dbn bf sbffly ovfrriddfn by b subdlbss.
     *
     * @pbrbm fditor  tif <dodf>TbblfCfllEditor</dodf> to sft up
     * @pbrbm row     tif row of tif dfll to fdit,
     *                wifrf 0 is tif first row
     * @pbrbm dolumn  tif dolumn of tif dfll to fdit,
     *                wifrf 0 is tif first dolumn
     * @rfturn tif <dodf>Componfnt</dodf> bfing fditfd
     */
    publid Componfnt prfpbrfEditor(TbblfCfllEditor fditor, int row, int dolumn) {
        Objfdt vbluf = gftVblufAt(row, dolumn);
        boolfbn isSflfdtfd = isCfllSflfdtfd(row, dolumn);
        Componfnt domp = fditor.gftTbblfCfllEditorComponfnt(tiis, vbluf, isSflfdtfd,
                                                  row, dolumn);
        if (domp instbndfof JComponfnt) {
            JComponfnt jComp = (JComponfnt)domp;
            if (jComp.gftNfxtFodusbblfComponfnt() == null) {
                jComp.sftNfxtFodusbblfComponfnt(tiis);
            }
        }
        rfturn domp;
    }

    /**
     * Disdbrds tif fditor objfdt bnd frffs tif rfbl fstbtf it usfd for
     * dfll rfndfring.
     */
    publid void rfmovfEditor() {
        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
            rfmovfPropfrtyCibngfListfnfr("pfrmbnfntFodusOwnfr", fditorRfmovfr);
        fditorRfmovfr = null;

        TbblfCfllEditor fditor = gftCfllEditor();
        if(fditor != null) {
            fditor.rfmovfCfllEditorListfnfr(tiis);
            if (fditorComp != null) {
                Componfnt fodusOwnfr =
                        KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().gftFodusOwnfr();
                boolfbn isFodusOwnfrInTifTbblf = fodusOwnfr != null?
                        SwingUtilitifs.isDfsdfndingFrom(fodusOwnfr, tiis):fblsf;
                rfmovf(fditorComp);
                if(isFodusOwnfrInTifTbblf) {
                    rfqufstFodusInWindow();
                }
            }

            Rfdtbnglf dfllRfdt = gftCfllRfdt(fditingRow, fditingColumn, fblsf);

            sftCfllEditor(null);
            sftEditingColumn(-1);
            sftEditingRow(-1);
            fditorComp = null;

            rfpbint(dfllRfdt);
        }
    }

//
// Sfriblizbtion
//

    /**
     * Sff rfbdObjfdt() bnd writfObjfdt() in JComponfnt for morf
     * informbtion bbout sfriblizbtion in Swing.
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

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        if ((ui != null) && (gftUIClbssID().fqubls(uiClbssID))) {
            ui.instbllUI(tiis);
        }
        drfbtfDffbultRfndfrfrs();
        drfbtfDffbultEditors();

        // If ToolTipTfxt != null, tifn tif tooltip ibs blrfbdy bffn
        // rfgistfrfd by JComponfnt.rfbdObjfdt() bnd wf don't wbnt
        // to rf-rfgistfr ifrf
        if (gftToolTipTfxt() == null) {
            ToolTipMbnbgfr.sibrfdInstbndf().rfgistfrComponfnt(tiis);
         }
    }

    /* Cbllfd from tif JComponfnt's EnbblfSfriblizbtionFodusListfnfr to
     * do bny Swing-spfdifid prf-sfriblizbtion donfigurbtion.
     */
    void dompWritfObjfdtNotify() {
        supfr.dompWritfObjfdtNotify();
        // If ToolTipTfxt != null, tifn tif tooltip ibs blrfbdy bffn
        // unrfgistfrfd by JComponfnt.dompWritfObjfdtNotify()
        if (gftToolTipTfxt() == null) {
            ToolTipMbnbgfr.sibrfdInstbndf().unrfgistfrComponfnt(tiis);
        }
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis tbblf. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis tbblf
     */
    protfdtfd String pbrbmString() {
        String gridColorString = (gridColor != null ?
                                  gridColor.toString() : "");
        String siowHorizontblLinfsString = (siowHorizontblLinfs ?
                                            "truf" : "fblsf");
        String siowVfrtidblLinfsString = (siowVfrtidblLinfs ?
                                          "truf" : "fblsf");
        String butoRfsizfModfString;
        if (butoRfsizfModf == AUTO_RESIZE_OFF) {
            butoRfsizfModfString = "AUTO_RESIZE_OFF";
        } flsf if (butoRfsizfModf == AUTO_RESIZE_NEXT_COLUMN) {
            butoRfsizfModfString = "AUTO_RESIZE_NEXT_COLUMN";
        } flsf if (butoRfsizfModf == AUTO_RESIZE_SUBSEQUENT_COLUMNS) {
            butoRfsizfModfString = "AUTO_RESIZE_SUBSEQUENT_COLUMNS";
        } flsf if (butoRfsizfModf == AUTO_RESIZE_LAST_COLUMN) {
            butoRfsizfModfString = "AUTO_RESIZE_LAST_COLUMN";
        } flsf if (butoRfsizfModf == AUTO_RESIZE_ALL_COLUMNS)  {
            butoRfsizfModfString = "AUTO_RESIZE_ALL_COLUMNS";
        } flsf butoRfsizfModfString = "";
        String butoCrfbtfColumnsFromModflString = (butoCrfbtfColumnsFromModfl ?
                                                   "truf" : "fblsf");
        String prfffrrfdVifwportSizfString = (prfffrrfdVifwportSizf != null ?
                                              prfffrrfdVifwportSizf.toString()
                                              : "");
        String rowSflfdtionAllowfdString = (rowSflfdtionAllowfd ?
                                            "truf" : "fblsf");
        String dfllSflfdtionEnbblfdString = (dfllSflfdtionEnbblfd ?
                                            "truf" : "fblsf");
        String sflfdtionForfgroundString = (sflfdtionForfground != null ?
                                            sflfdtionForfground.toString() :
                                            "");
        String sflfdtionBbdkgroundString = (sflfdtionBbdkground != null ?
                                            sflfdtionBbdkground.toString() :
                                            "");

        rfturn supfr.pbrbmString() +
        ",butoCrfbtfColumnsFromModfl=" + butoCrfbtfColumnsFromModflString +
        ",butoRfsizfModf=" + butoRfsizfModfString +
        ",dfllSflfdtionEnbblfd=" + dfllSflfdtionEnbblfdString +
        ",fditingColumn=" + fditingColumn +
        ",fditingRow=" + fditingRow +
        ",gridColor=" + gridColorString +
        ",prfffrrfdVifwportSizf=" + prfffrrfdVifwportSizfString +
        ",rowHfigit=" + rowHfigit +
        ",rowMbrgin=" + rowMbrgin +
        ",rowSflfdtionAllowfd=" + rowSflfdtionAllowfdString +
        ",sflfdtionBbdkground=" + sflfdtionBbdkgroundString +
        ",sflfdtionForfground=" + sflfdtionForfgroundString +
        ",siowHorizontblLinfs=" + siowHorizontblLinfsString +
        ",siowVfrtidblLinfs=" + siowVfrtidblLinfsString;
    }

    // Tiis dlbss trbdks dibngfs in tif kfybobrd fodus stbtf. It is usfd
    // wifn tif JTbblf is fditing to dftfrminf wifn to dbndfl tif fdit.
    // If fodus switdifs to b domponfnt outsidf of tif jtbblf, but in tif
    // sbmf window, tiis will dbndfl fditing.
    dlbss CfllEditorRfmovfr implfmfnts PropfrtyCibngfListfnfr {
        KfybobrdFodusMbnbgfr fodusMbnbgfr;

        publid CfllEditorRfmovfr(KfybobrdFodusMbnbgfr fm) {
            tiis.fodusMbnbgfr = fm;
        }

        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fv) {
            if (!isEditing() || gftClifntPropfrty("tfrminbtfEditOnFodusLost") != Boolfbn.TRUE) {
                rfturn;
            }

            Componfnt d = fodusMbnbgfr.gftPfrmbnfntFodusOwnfr();
            wiilf (d != null) {
                if (d == JTbblf.tiis) {
                    // fodus rfmbins insidf tif tbblf
                    rfturn;
                } flsf if ((d instbndfof Window) ||
                           (d instbndfof Applft && d.gftPbrfnt() == null)) {
                    if (d == SwingUtilitifs.gftRoot(JTbblf.tiis)) {
                        if (!gftCfllEditor().stopCfllEditing()) {
                            gftCfllEditor().dbndflCfllEditing();
                        }
                    }
                    brfbk;
                }
                d = d.gftPbrfnt();
            }
        }
    }

/////////////////
// Printing Support
/////////////////

    /**
     * A donvfnifndf mftiod tibt displbys b printing diblog, bnd tifn prints
     * tiis <dodf>JTbblf</dodf> in modf <dodf>PrintModf.FIT_WIDTH</dodf>,
     * witi no ifbdfr or footfr tfxt. A modbl progrfss diblog, witi bn bbort
     * option, will bf siown for tif durbtion of printing.
     * <p>
     * Notf: In ifbdlfss modf, no diblogs brf siown bnd printing
     * oddurs on tif dffbult printfr.
     *
     * @rfturn truf, unlfss printing is dbndfllfd by tif usfr
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *                          to bf bbortfd
     * @sff #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     *             boolfbn, PrintRfqufstAttributfSft, boolfbn, PrintSfrvidf)
     * @sff #gftPrintbblf
     *
     * @sindf 1.5
     */
    publid boolfbn print() tirows PrintfrExdfption {

        rfturn print(PrintModf.FIT_WIDTH);
    }

    /**
     * A donvfnifndf mftiod tibt displbys b printing diblog, bnd tifn prints
     * tiis <dodf>JTbblf</dodf> in tif givfn printing modf,
     * witi no ifbdfr or footfr tfxt. A modbl progrfss diblog, witi bn bbort
     * option, will bf siown for tif durbtion of printing.
     * <p>
     * Notf: In ifbdlfss modf, no diblogs brf siown bnd printing
     * oddurs on tif dffbult printfr.
     *
     * @pbrbm  printModf        tif printing modf tibt tif printbblf siould usf
     * @rfturn truf, unlfss printing is dbndfllfd by tif usfr
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *                          to bf bbortfd
     * @sff #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     *             boolfbn, PrintRfqufstAttributfSft, boolfbn, PrintSfrvidf)
     * @sff #gftPrintbblf
     *
     * @sindf 1.5
     */
    publid boolfbn print(PrintModf printModf) tirows PrintfrExdfption {

        rfturn print(printModf, null, null);
    }

    /**
     * A donvfnifndf mftiod tibt displbys b printing diblog, bnd tifn prints
     * tiis <dodf>JTbblf</dodf> in tif givfn printing modf,
     * witi tif spfdififd ifbdfr bnd footfr tfxt. A modbl progrfss diblog,
     * witi bn bbort option, will bf siown for tif durbtion of printing.
     * <p>
     * Notf: In ifbdlfss modf, no diblogs brf siown bnd printing
     * oddurs on tif dffbult printfr.
     *
     * @pbrbm  printModf        tif printing modf tibt tif printbblf siould usf
     * @pbrbm  ifbdfrFormbt     b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt
     *                          to bf usfd in printing b ifbdfr,
     *                          or null for nonf
     * @pbrbm  footfrFormbt     b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt
     *                          to bf usfd in printing b footfr,
     *                          or null for nonf
     * @rfturn truf, unlfss printing is dbndfllfd by tif usfr
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *                          to bf bbortfd
     * @sff #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     *             boolfbn, PrintRfqufstAttributfSft, boolfbn, PrintSfrvidf)
     * @sff #gftPrintbblf
     *
     * @sindf 1.5
     */
    publid boolfbn print(PrintModf printModf,
                         MfssbgfFormbt ifbdfrFormbt,
                         MfssbgfFormbt footfrFormbt) tirows PrintfrExdfption {

        boolfbn siowDiblogs = !GrbpiidsEnvironmfnt.isHfbdlfss();
        rfturn print(printModf, ifbdfrFormbt, footfrFormbt,
                     siowDiblogs, null, siowDiblogs);
    }

    /**
     * Prints tiis tbblf, bs spfdififd by tif fully ffbturfd
     * {@link #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     * boolfbn, PrintRfqufstAttributfSft, boolfbn, PrintSfrvidf) print}
     * mftiod, witi tif dffbult printfr spfdififd bs tif print sfrvidf.
     *
     * @pbrbm  printModf        tif printing modf tibt tif printbblf siould usf
     * @pbrbm  ifbdfrFormbt     b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt
     *                          to bf usfd in printing b ifbdfr,
     *                          or <dodf>null</dodf> for nonf
     * @pbrbm  footfrFormbt     b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt
     *                          to bf usfd in printing b footfr,
     *                          or <dodf>null</dodf> for nonf
     * @pbrbm  siowPrintDiblog  wiftifr or not to displby b print diblog
     * @pbrbm  bttr             b <dodf>PrintRfqufstAttributfSft</dodf>
     *                          spfdifying bny printing bttributfs,
     *                          or <dodf>null</dodf> for nonf
     * @pbrbm  intfrbdtivf      wiftifr or not to print in bn intfrbdtivf modf
     * @rfturn truf, unlfss printing is dbndfllfd by tif usfr
     * @tirows HfbdlfssExdfption if tif mftiod is bskfd to siow b printing
     *                           diblog or run intfrbdtivfly, bnd
     *                           <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *                           rfturns <dodf>truf</dodf>
     * @tirows SfdurityExdfption if tiis tirfbd is not bllowfd to
     *                           initibtf b print job rfqufst
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *                          to bf bbortfd
     * @sff #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     *             boolfbn, PrintRfqufstAttributfSft, boolfbn, PrintSfrvidf)
     * @sff #gftPrintbblf
     *
     * @sindf 1.5
     */
    publid boolfbn print(PrintModf printModf,
                         MfssbgfFormbt ifbdfrFormbt,
                         MfssbgfFormbt footfrFormbt,
                         boolfbn siowPrintDiblog,
                         PrintRfqufstAttributfSft bttr,
                         boolfbn intfrbdtivf) tirows PrintfrExdfption,
                                                     HfbdlfssExdfption {

        rfturn print(printModf,
                     ifbdfrFormbt,
                     footfrFormbt,
                     siowPrintDiblog,
                     bttr,
                     intfrbdtivf,
                     null);
    }

    /**
     * Prints tiis <dodf>JTbblf</dodf>. Tbkfs stfps tibt tif mbjority of
     * dfvflopfrs would tbkf in ordfr to print b <dodf>JTbblf</dodf>.
     * In siort, it prfpbrfs tif tbblf, dblls <dodf>gftPrintbblf</dodf> to
     * fftdi bn bppropribtf <dodf>Printbblf</dodf>, bnd tifn sfnds it to tif
     * printfr.
     * <p>
     * A <dodf>boolfbn</dodf> pbrbmftfr bllows you to spfdify wiftifr or not
     * b printing diblog is displbyfd to tif usfr. Wifn it is, tif usfr mby
     * usf tif diblog to dibngf tif dfstinbtion printfr or printing bttributfs,
     * or fvfn to dbndfl tif print. Anotifr two pbrbmftfrs bllow for b
     * <dodf>PrintSfrvidf</dodf> bnd printing bttributfs to bf spfdififd.
     * Tifsf pbrbmftfrs dbn bf usfd fitifr to providf initibl vblufs for tif
     * print diblog, or to spfdify vblufs wifn tif diblog is not siown.
     * <p>
     * A sfdond <dodf>boolfbn</dodf> pbrbmftfr bllows you to spfdify wiftifr
     * or not to pfrform printing in bn intfrbdtivf modf. If <dodf>truf</dodf>,
     * b modbl progrfss diblog, witi bn bbort option, is displbyfd for tif
     * durbtion of printing . Tiis diblog blso prfvfnts bny usfr bdtion wiidi
     * mby bfffdt tif tbblf. Howfvfr, it dbn not prfvfnt tif tbblf from bfing
     * modififd by dodf (for fxbmplf, bnotifr tirfbd tibt posts updbtfs using
     * <dodf>SwingUtilitifs.invokfLbtfr</dodf>). It is tifrfforf tif
     * rfsponsibility of tif dfvflopfr to fnsurf tibt no otifr dodf modififs
     * tif tbblf in bny wby during printing (invblid modifidbtions indludf
     * dibngfs in: sizf, rfndfrfrs, or undfrlying dbtb). Printing bfibvior is
     * undffinfd wifn tif tbblf is dibngfd during printing.
     * <p>
     * If <dodf>fblsf</dodf> is spfdififd for tiis pbrbmftfr, no diblog will
     * bf displbyfd bnd printing will bfgin immfdibtfly on tif fvfnt-dispbtdi
     * tirfbd. Tiis blodks bny otifr fvfnts, indluding rfpbints, from bfing
     * prodfssfd until printing is domplftf. Altiougi tiis ffffdtivfly prfvfnts
     * tif tbblf from bfing dibngfd, it dofsn't providf b good usfr fxpfrifndf.
     * For tiis rfbson, spfdifying <dodf>fblsf</dodf> is only rfdommfndfd wifn
     * printing from bn bpplidbtion witi no visiblf GUI.
     * <p>
     * Notf: Attfmpting to siow tif printing diblog or run intfrbdtivfly, wiilf
     * in ifbdlfss modf, will rfsult in b <dodf>HfbdlfssExdfption</dodf>.
     * <p>
     * Bfforf fftdiing tif printbblf, tiis mftiod will grbdffully tfrminbtf
     * fditing, if nfdfssbry, to prfvfnt bn fditor from siowing in tif printfd
     * rfsult. Additionblly, <dodf>JTbblf</dodf> will prfpbrf its rfndfrfrs
     * during printing sudi tibt sflfdtion bnd fodus brf not indidbtfd.
     * As fbr bs dustomizing furtifr iow tif tbblf looks in tif printout,
     * dfvflopfrs dbn providf dustom rfndfrfrs or pbint dodf tibt donditionblizf
     * on tif vbluf of {@link jbvbx.swing.JComponfnt#isPbintingForPrint()}.
     * <p>
     * Sff {@link #gftPrintbblf} for morf dfsdription on iow tif tbblf is
     * printfd.
     *
     * @pbrbm  printModf        tif printing modf tibt tif printbblf siould usf
     * @pbrbm  ifbdfrFormbt     b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt
     *                          to bf usfd in printing b ifbdfr,
     *                          or <dodf>null</dodf> for nonf
     * @pbrbm  footfrFormbt     b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt
     *                          to bf usfd in printing b footfr,
     *                          or <dodf>null</dodf> for nonf
     * @pbrbm  siowPrintDiblog  wiftifr or not to displby b print diblog
     * @pbrbm  bttr             b <dodf>PrintRfqufstAttributfSft</dodf>
     *                          spfdifying bny printing bttributfs,
     *                          or <dodf>null</dodf> for nonf
     * @pbrbm  intfrbdtivf      wiftifr or not to print in bn intfrbdtivf modf
     * @pbrbm  sfrvidf          tif dfstinbtion <dodf>PrintSfrvidf</dodf>,
     *                          or <dodf>null</dodf> to usf tif dffbult printfr
     * @rfturn truf, unlfss printing is dbndfllfd by tif usfr
     * @tirows HfbdlfssExdfption if tif mftiod is bskfd to siow b printing
     *                           diblog or run intfrbdtivfly, bnd
     *                           <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf>
     *                           rfturns <dodf>truf</dodf>
     * @tirows  SfdurityExdfption if b sfdurity mbnbgfr fxists bnd its
     *          {@link jbvb.lbng.SfdurityMbnbgfr#difdkPrintJobAddfss}
     *          mftiod disbllows tiis tirfbd from drfbting b print job rfqufst
     * @tirows PrintfrExdfption if bn frror in tif print systfm dbusfs tif job
     *                          to bf bbortfd
     * @sff #gftPrintbblf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     *
     * @sindf 1.6
     */
    publid boolfbn print(PrintModf printModf,
                         MfssbgfFormbt ifbdfrFormbt,
                         MfssbgfFormbt footfrFormbt,
                         boolfbn siowPrintDiblog,
                         PrintRfqufstAttributfSft bttr,
                         boolfbn intfrbdtivf,
                         PrintSfrvidf sfrvidf) tirows PrintfrExdfption,
                                                      HfbdlfssExdfption {

        // domplbin fbrly if bn invblid pbrbmftfr is spfdififd for ifbdlfss modf
        boolfbn isHfbdlfss = GrbpiidsEnvironmfnt.isHfbdlfss();
        if (isHfbdlfss) {
            if (siowPrintDiblog) {
                tirow nfw HfbdlfssExdfption("Cbn't siow print diblog.");
            }

            if (intfrbdtivf) {
                tirow nfw HfbdlfssExdfption("Cbn't run intfrbdtivfly.");
            }
        }

        // Gft b PrintfrJob.
        // Do tiis bfforf bnytiing witi sidf-ffffdts sindf it mby tirow b
        // sfdurity fxdfption - in wiidi dbsf wf don't wbnt to do bnytiing flsf.
        finbl PrintfrJob job = PrintfrJob.gftPrintfrJob();

        if (isEditing()) {
            // try to stop dfll fditing, bnd fbiling tibt, dbndfl it
            if (!gftCfllEditor().stopCfllEditing()) {
                gftCfllEditor().dbndflCfllEditing();
            }
        }

        if (bttr == null) {
            bttr = nfw HbsiPrintRfqufstAttributfSft();
        }

        finbl PrintingStbtus printingStbtus;

         // fftdi tif Printbblf
        Printbblf printbblf =
             gftPrintbblf(printModf, ifbdfrFormbt, footfrFormbt);

        if (intfrbdtivf) {
            // wrbp tif Printbblf so tibt wf dbn print on bnotifr tirfbd
            printbblf = nfw TirfbdSbffPrintbblf(printbblf);
            printingStbtus = PrintingStbtus.drfbtfPrintingStbtus(tiis, job);
            printbblf = printingStbtus.drfbtfNotifidbtionPrintbblf(printbblf);
        } flsf {
            // to plfbsf dompilfr
            printingStbtus = null;
        }

        // sft tif printbblf on tif PrintfrJob
        job.sftPrintbblf(printbblf);

        // if spfdififd, sft tif PrintSfrvidf on tif PrintfrJob
        if (sfrvidf != null) {
            job.sftPrintSfrvidf(sfrvidf);
        }

        // if rfqufstfd, siow tif print diblog
        if (siowPrintDiblog && !job.printDiblog(bttr)) {
            // tif usfr dbndfllfd tif print diblog
            rfturn fblsf;
        }

        // if not intfrbdtivf, just print on tiis tirfbd (no diblog)
        if (!intfrbdtivf) {
            // do tif printing
            job.print(bttr);

            // wf'rf donf
            rfturn truf;
        }

        // mbkf surf tiis is dlfbr sindf wf'll difdk it bftfr
        printError = null;

        // to syndironizf on
        finbl Objfdt lodk = nfw Objfdt();

        // dopifd so wf dbn bddfss from tif innfr dlbss
        finbl PrintRfqufstAttributfSft dopyAttr = bttr;

        // tiis runnbblf will bf usfd to do tif printing
        // (bnd sbvf bny tirowbblfs) on bnotifr tirfbd
        Runnbblf runnbblf = nfw Runnbblf() {
            publid void run() {
                try {
                    // do tif printing
                    job.print(dopyAttr);
                } dbtdi (Tirowbblf t) {
                    // sbvf bny Tirowbblf to bf rftirown
                    syndironizfd(lodk) {
                        printError = t;
                    }
                } finblly {
                    // wf'rf finisifd - iidf tif diblog
                    printingStbtus.disposf();
                }
            }
        };

        // stbrt printing on bnotifr tirfbd
        Tirfbd ti = nfw Tirfbd(runnbblf);
        ti.stbrt();

        printingStbtus.siowModbl(truf);

        // look for bny frror tibt tif printing mby ibvf gfnfrbtfd
        Tirowbblf pf;
        syndironizfd(lodk) {
            pf = printError;
            printError = null;
        }

        // difdk tif typf of frror bnd ibndlf it
        if (pf != null) {
            // b subdlbss of PrintfrExdfption mfbning tif job wbs bbortfd,
            // in tiis dbsf, by tif usfr
            if (pf instbndfof PrintfrAbortExdfption) {
                rfturn fblsf;
            } flsf if (pf instbndfof PrintfrExdfption) {
                tirow (PrintfrExdfption)pf;
            } flsf if (pf instbndfof RuntimfExdfption) {
                tirow (RuntimfExdfption)pf;
            } flsf if (pf instbndfof Error) {
                tirow (Error)pf;
            }

            // dbn not ibppfn
            tirow nfw AssfrtionError(pf);
        }

        rfturn truf;
    }

    /**
     * Rfturn b <dodf>Printbblf</dodf> for usf in printing tiis JTbblf.
     * <p>
     * Tiis mftiod is mfbnt for tiosf wisiing to dustomizf tif dffbult
     * <dodf>Printbblf</dodf> implfmfntbtion usfd by <dodf>JTbblf</dodf>'s
     * <dodf>print</dodf> mftiods. Dfvflopfrs wbnting simply to print tif tbblf
     * siould usf onf of tiosf mftiods dirfdtly.
     * <p>
     * Tif <dodf>Printbblf</dodf> dbn bf rfqufstfd in onf of two printing modfs.
     * In boti modfs, it sprfbds tbblf rows nbturblly in sfqufndf bdross
     * multiplf pbgfs, fitting bs mbny rows bs possiblf pfr pbgf.
     * <dodf>PrintModf.NORMAL</dodf> spfdififs tibt tif tbblf bf
     * printfd bt its durrfnt sizf. In tiis modf, tifrf mby bf b nffd to sprfbd
     * dolumns bdross pbgfs in b similbr mbnnfr to tibt of tif rows. Wifn tif
     * nffd brisfs, dolumns brf distributfd in bn ordfr donsistfnt witi tif
     * tbblf's <dodf>ComponfntOrifntbtion</dodf>.
     * <dodf>PrintModf.FIT_WIDTH</dodf> spfdififs tibt tif output bf
     * sdblfd smbllfr, if nfdfssbry, to fit tif tbblf's fntirf widti
     * (bnd tifrfby bll dolumns) on fbdi pbgf. Widti bnd ifigit brf sdblfd
     * fqublly, mbintbining tif bspfdt rbtio of tif output.
     * <p>
     * Tif <dodf>Printbblf</dodf> ifbds tif portion of tbblf on fbdi pbgf
     * witi tif bppropribtf sfdtion from tif tbblf's <dodf>JTbblfHfbdfr</dodf>,
     * if it ibs onf.
     * <p>
     * Hfbdfr bnd footfr tfxt dbn bf bddfd to tif output by providing
     * <dodf>MfssbgfFormbt</dodf> brgumfnts. Tif printing dodf rfqufsts
     * Strings from tif formbts, providing b singlf itfm wiidi mby bf indludfd
     * in tif formbttfd string: bn <dodf>Intfgfr</dodf> rfprfsfnting tif durrfnt
     * pbgf numbfr.
     * <p>
     * You brf fndourbgfd to rfbd tif dodumfntbtion for
     * <dodf>MfssbgfFormbt</dodf> bs somf dibrbdtfrs, sudi bs singlf-quotf,
     * brf spfdibl bnd nffd to bf fsdbpfd.
     * <p>
     * Hfrf's bn fxbmplf of drfbting b <dodf>MfssbgfFormbt</dodf> tibt dbn bf
     * usfd to print "Dukf's Tbblf: Pbgf - " bnd tif durrfnt pbgf numbfr:
     *
     * <prf>
     *     // notidf tif fsdbping of tif singlf quotf
     *     // notidf iow tif pbgf numbfr is indludfd witi "{0}"
     *     MfssbgfFormbt formbt = nfw MfssbgfFormbt("Dukf''s Tbblf: Pbgf - {0}");
     * </prf>
     * <p>
     * Tif <dodf>Printbblf</dodf> donstrbins wibt it drbws to tif printbblf
     * brfb of fbdi pbgf tibt it prints. Undfr dfrtbin dirdumstbndfs, it mby
     * find it impossiblf to fit bll of b pbgf's dontfnt into tibt brfb. In
     * tifsf dbsfs tif output mby bf dlippfd, but tif implfmfntbtion
     * mbkfs bn fffort to do somftiing rfbsonbblf. Hfrf brf b ffw situbtions
     * wifrf tiis is known to oddur, bnd iow tify mby bf ibndlfd by tiis
     * pbrtidulbr implfmfntbtion:
     * <ul>
     *   <li>In bny modf, wifn tif ifbdfr or footfr tfxt is too widf to fit
     *       domplftfly in tif printbblf brfb -- print bs mudi of tif tfxt bs
     *       possiblf stbrting from tif bfginning, bs dftfrminfd by tif tbblf's
     *       <dodf>ComponfntOrifntbtion</dodf>.
     *   <li>In bny modf, wifn b row is too tbll to fit in tif
     *       printbblf brfb -- print tif uppfr-most portion of tif row
     *       bnd pbint no lowfr bordfr on tif tbblf.
     *   <li>In <dodf>PrintModf.NORMAL</dodf> wifn b dolumn
     *       is too widf to fit in tif printbblf brfb -- print tif dfntfr
     *       portion of tif dolumn bnd lfbvf tif lfft bnd rigit bordfrs
     *       off tif tbblf.
     * </ul>
     * <p>
     * It is fntirfly vblid for tiis <dodf>Printbblf</dodf> to bf wrbppfd
     * insidf bnotifr in ordfr to drfbtf domplfx rfports bnd dodumfnts. You mby
     * fvfn rfqufst tibt difffrfnt pbgfs bf rfndfrfd into difffrfnt sizfd
     * printbblf brfbs. Tif implfmfntbtion must bf prfpbrfd to ibndlf tiis
     * (possibly by doing its lbyout dbldulbtions on tif fly). Howfvfr,
     * providing difffrfnt ifigits to fbdi pbgf will likfly not work wfll
     * witi <dodf>PrintModf.NORMAL</dodf> wifn it ibs to sprfbd dolumns
     * bdross pbgfs.
     * <p>
     * As fbr bs dustomizing iow tif tbblf looks in tif printfd rfsult,
     * <dodf>JTbblf</dodf> itsflf will tbkf dbrf of iiding tif sflfdtion
     * bnd fodus during printing. For bdditionbl dustomizbtions, your
     * rfndfrfrs or pbinting dodf dbn dustomizf tif look bbsfd on tif vbluf
     * of {@link jbvbx.swing.JComponfnt#isPbintingForPrint()}
     * <p>
     * Also, <i>bfforf</i> dblling tiis mftiod you mby wisi to <i>first</i>
     * modify tif stbtf of tif tbblf, sudi bs to dbndfl dfll fditing or
     * ibvf tif usfr sizf tif tbblf bppropribtfly. Howfvfr, you must not
     * modify tif stbtf of tif tbblf <i>bftfr</i> tiis <dodf>Printbblf</dodf>
     * ibs bffn fftdifd (invblid modifidbtions indludf dibngfs in sizf or
     * undfrlying dbtb). Tif bfibvior of tif rfturnfd <dodf>Printbblf</dodf>
     * is undffinfd ondf tif tbblf ibs bffn dibngfd.
     *
     * @pbrbm  printModf     tif printing modf tibt tif printbblf siould usf
     * @pbrbm  ifbdfrFormbt  b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt to
     *                       bf usfd in printing b ifbdfr, or null for nonf
     * @pbrbm  footfrFormbt  b <dodf>MfssbgfFormbt</dodf> spfdifying tif tfxt to
     *                       bf usfd in printing b footfr, or null for nonf
     * @rfturn b <dodf>Printbblf</dodf> for printing tiis JTbblf
     * @sff #print(JTbblf.PrintModf, MfssbgfFormbt, MfssbgfFormbt,
     *             boolfbn, PrintRfqufstAttributfSft, boolfbn)
     * @sff Printbblf
     * @sff PrintfrJob
     *
     * @sindf 1.5
     */
    publid Printbblf gftPrintbblf(PrintModf printModf,
                                  MfssbgfFormbt ifbdfrFormbt,
                                  MfssbgfFormbt footfrFormbt) {

        rfturn nfw TbblfPrintbblf(tiis, printModf, ifbdfrFormbt, footfrFormbt);
    }


    /**
     * A <dodf>Printbblf</dodf> implfmfntbtion tibt wrbps bnotifr
     * <dodf>Printbblf</dodf>, mbking it sbff for printing on bnotifr tirfbd.
     */
    privbtf dlbss TirfbdSbffPrintbblf implfmfnts Printbblf {

        /** Tif dflfgbtf <dodf>Printbblf</dodf>. */
        privbtf Printbblf printDflfgbtf;

        /**
         * To dommunidbtf bny rfturn vbluf wifn dflfgbting.
         */
        privbtf int rftVbl;

        /**
         * To dommunidbtf bny <dodf>Tirowbblf</dodf> wifn dflfgbting.
         */
        privbtf Tirowbblf rftTirowbblf;

        /**
         * Construdt b <dodf>TirfbdSbffPrintbblf</dodf> bround tif givfn
         * dflfgbtf.
         *
         * @pbrbm printDflfgbtf tif <dodf>Printbblf</dodf> to dflfgbtf to
         */
        publid TirfbdSbffPrintbblf(Printbblf printDflfgbtf) {
            tiis.printDflfgbtf = printDflfgbtf;
        }

        /**
         * Prints tif spfdififd pbgf into tif givfn {@link Grbpiids}
         * dontfxt, in tif spfdififd formbt.
         * <p>
         * Rfgbrdlfss of wibt tirfbd tiis mftiod is dbllfd on, bll dblls into
         * tif dflfgbtf will bf donf on tif fvfnt-dispbtdi tirfbd.
         *
         * @pbrbm   grbpiids    tif dontfxt into wiidi tif pbgf is drbwn
         * @pbrbm   pbgfFormbt  tif sizf bnd orifntbtion of tif pbgf bfing drbwn
         * @pbrbm   pbgfIndfx   tif zfro bbsfd indfx of tif pbgf to bf drbwn
         * @rfturn  PAGE_EXISTS if tif pbgf is rfndfrfd suddfssfully, or
         *          NO_SUCH_PAGE if b non-fxistfnt pbgf indfx is spfdififd
         * @tirows  PrintfrExdfption if bn frror dbusfs printing to bf bbortfd
         */
        publid int print(finbl Grbpiids grbpiids,
                         finbl PbgfFormbt pbgfFormbt,
                         finbl int pbgfIndfx) tirows PrintfrExdfption {

            // Wf'll usf tiis Runnbblf
            Runnbblf runnbblf = nfw Runnbblf() {
                publid syndironizfd void run() {
                    try {
                        // dbll into tif dflfgbtf bnd sbvf tif rfturn vbluf
                        rftVbl = printDflfgbtf.print(grbpiids, pbgfFormbt, pbgfIndfx);
                    } dbtdi (Tirowbblf tirowbblf) {
                        // sbvf bny Tirowbblf to bf rftirown
                        rftTirowbblf = tirowbblf;
                    } finblly {
                        // notify tif dbllfr tibt wf'rf donf
                        notifyAll();
                    }
                }
            };

            syndironizfd(runnbblf) {
                // mbkf surf tifsf brf initiblizfd
                rftVbl = -1;
                rftTirowbblf = null;

                // dbll into tif EDT
                SwingUtilitifs.invokfLbtfr(runnbblf);

                // wbit for tif runnbblf to finisi
                wiilf (rftVbl == -1 && rftTirowbblf == null) {
                    try {
                        runnbblf.wbit();
                    } dbtdi (IntfrruptfdExdfption if) {
                        // siort prodfss, sbff to ignorf intfrrupts
                    }
                }

                // if tif dflfgbtf tirfw b tirowbblf, rftirow it ifrf
                if (rftTirowbblf != null) {
                    if (rftTirowbblf instbndfof PrintfrExdfption) {
                        tirow (PrintfrExdfption)rftTirowbblf;
                    } flsf if (rftTirowbblf instbndfof RuntimfExdfption) {
                        tirow (RuntimfExdfption)rftTirowbblf;
                    } flsf if (rftTirowbblf instbndfof Error) {
                        tirow (Error)rftTirowbblf;
                    }

                    // dbn not ibppfn
                    tirow nfw AssfrtionError(rftTirowbblf);
                }

                rfturn rftVbl;
            }
        }
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JTbblf.
     * For tbblfs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJTbblf.
     * A nfw AddfssiblfJTbblf instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJTbblf tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JTbblf
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJTbblf();
        }
        rfturn bddfssiblfContfxt;
    }

    //
    // *** siould blso implfmfnt AddfssiblfSflfdtion?
    // *** bnd wibt's up witi kfybobrd nbvigbtion/mbnipulbtion?
    //
    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JTbblf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tbblf usfr-intfrfbdf flfmfnts.
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
    protfdtfd dlbss AddfssiblfJTbblf fxtfnds AddfssiblfJComponfnt
    implfmfnts AddfssiblfSflfdtion, ListSflfdtionListfnfr, TbblfModflListfnfr,
    TbblfColumnModflListfnfr, CfllEditorListfnfr, PropfrtyCibngfListfnfr,
    AddfssiblfExtfndfdTbblf {

        int prfviousFodusfdRow;
        int prfviousFodusfdCol;

        /**
         * AddfssiblfJTbblf donstrudtor
         *
         * @sindf 1.5
         */
        protfdtfd AddfssiblfJTbblf() {
            supfr();
            JTbblf.tiis.bddPropfrtyCibngfListfnfr(tiis);
            JTbblf.tiis.gftSflfdtionModfl().bddListSflfdtionListfnfr(tiis);
            TbblfColumnModfl tdm = JTbblf.tiis.gftColumnModfl();
            tdm.bddColumnModflListfnfr(tiis);
            tdm.gftSflfdtionModfl().bddListSflfdtionListfnfr(tiis);
            JTbblf.tiis.gftModfl().bddTbblfModflListfnfr(tiis);
            prfviousFodusfdRow = JTbblf.tiis.gftSflfdtionModfl().
                                        gftLfbdSflfdtionIndfx();
            prfviousFodusfdCol = JTbblf.tiis.gftColumnModfl().
                                        gftSflfdtionModfl().gftLfbdSflfdtionIndfx();
        }

    // Listfnfrs to trbdk modfl, ftd. dibngfs to bs to rf-plbdf tif otifr
    // listfnfrs

        /**
         * Trbdk dibngfs to sflfdtion modfl, dolumn modfl, ftd. so bs to
         * bf bblf to rf-plbdf listfnfrs on tiosf in ordfr to pbss on
         * informbtion to tif Addfssibility PropfrtyCibngf mfdibnism
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            String nbmf = f.gftPropfrtyNbmf();
            Objfdt oldVbluf = f.gftOldVbluf();
            Objfdt nfwVbluf = f.gftNfwVbluf();

                // rf-sft tbblfModfl listfnfrs
            if (nbmf.dompbrfTo("modfl") == 0) {

                if (oldVbluf != null && oldVbluf instbndfof TbblfModfl) {
                    ((TbblfModfl) oldVbluf).rfmovfTbblfModflListfnfr(tiis);
                }
                if (nfwVbluf != null && nfwVbluf instbndfof TbblfModfl) {
                    ((TbblfModfl) nfwVbluf).bddTbblfModflListfnfr(tiis);
                }

                // rf-sft sflfdtionModfl listfnfrs
            } flsf if (nbmf.dompbrfTo("sflfdtionModfl") == 0) {

                Objfdt sourdf = f.gftSourdf();
                if (sourdf == JTbblf.tiis) {    // row sflfdtion modfl

                    if (oldVbluf != null &&
                        oldVbluf instbndfof ListSflfdtionModfl) {
                        ((ListSflfdtionModfl) oldVbluf).rfmovfListSflfdtionListfnfr(tiis);
                    }
                    if (nfwVbluf != null &&
                        nfwVbluf instbndfof ListSflfdtionModfl) {
                        ((ListSflfdtionModfl) nfwVbluf).bddListSflfdtionListfnfr(tiis);
                    }

                } flsf if (sourdf == JTbblf.tiis.gftColumnModfl()) {

                    if (oldVbluf != null &&
                        oldVbluf instbndfof ListSflfdtionModfl) {
                        ((ListSflfdtionModfl) oldVbluf).rfmovfListSflfdtionListfnfr(tiis);
                    }
                    if (nfwVbluf != null &&
                        nfwVbluf instbndfof ListSflfdtionModfl) {
                        ((ListSflfdtionModfl) nfwVbluf).bddListSflfdtionListfnfr(tiis);
                    }

                } flsf {
                  //        Systfm.out.println("!!! Bug in sourdf of sflfdtionModfl propfrtyCibngfEvfnt");
                }

                // rf-sft dolumnModfl listfnfrs
                // bnd dolumn's sflfdtion propfrty listfnfr bs wfll
            } flsf if (nbmf.dompbrfTo("dolumnModfl") == 0) {

                if (oldVbluf != null && oldVbluf instbndfof TbblfColumnModfl) {
                    TbblfColumnModfl tdm = (TbblfColumnModfl) oldVbluf;
                    tdm.rfmovfColumnModflListfnfr(tiis);
                    tdm.gftSflfdtionModfl().rfmovfListSflfdtionListfnfr(tiis);
                }
                if (nfwVbluf != null && nfwVbluf instbndfof TbblfColumnModfl) {
                    TbblfColumnModfl tdm = (TbblfColumnModfl) nfwVbluf;
                    tdm.bddColumnModflListfnfr(tiis);
                    tdm.gftSflfdtionModfl().bddListSflfdtionListfnfr(tiis);
                }

                // rf-sf dfllEditor listfnfrs
            } flsf if (nbmf.dompbrfTo("tbblfCfllEditor") == 0) {

                if (oldVbluf != null && oldVbluf instbndfof TbblfCfllEditor) {
                    ((TbblfCfllEditor) oldVbluf).rfmovfCfllEditorListfnfr(tiis);
                }
                if (nfwVbluf != null && nfwVbluf instbndfof TbblfCfllEditor) {
                    ((TbblfCfllEditor) nfwVbluf).bddCfllEditorListfnfr(tiis);
                }
            }
        }


    // Listfnfrs to fdio dibngfs to tif AddfssiblfPropfrtyCibngf mfdibnism

        /**
         * Dfsdribfs b dibngf in tif bddfssiblf tbblf modfl.
         */
        protfdtfd dlbss AddfssiblfJTbblfModflCibngf
            implfmfnts AddfssiblfTbblfModflCibngf {

            protfdtfd int typf;
            protfdtfd int firstRow;
            protfdtfd int lbstRow;
            protfdtfd int firstColumn;
            protfdtfd int lbstColumn;

            protfdtfd AddfssiblfJTbblfModflCibngf(int typf, int firstRow,
                                                  int lbstRow, int firstColumn,
                                                  int lbstColumn) {
                tiis.typf = typf;
                tiis.firstRow = firstRow;
                tiis.lbstRow = lbstRow;
                tiis.firstColumn = firstColumn;
                tiis.lbstColumn = lbstColumn;
            }

            publid int gftTypf() {
                rfturn typf;
            }

            publid int gftFirstRow() {
                rfturn firstRow;
            }

            publid int gftLbstRow() {
                rfturn lbstRow;
            }

            publid int gftFirstColumn() {
                rfturn firstColumn;
            }

            publid int gftLbstColumn() {
                rfturn lbstColumn;
            }
        }

        /**
         * Trbdk dibngfs to tif tbblf dontfnts
         *
         * @pbrbm f b {@dodf TbblfModflEvfnt} dfsdribing tif fvfnt
         */
        publid void tbblfCibngfd(TbblfModflEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);
           if (f != null) {
               int firstColumn = f.gftColumn();
               int lbstColumn = f.gftColumn();
               if (firstColumn == TbblfModflEvfnt.ALL_COLUMNS) {
                   firstColumn = 0;
                   lbstColumn = gftColumnCount() - 1;
               }

               // Firf b propfrty dibngf fvfnt indidbting tif tbblf modfl
               // ibs dibngfd.
               AddfssiblfJTbblfModflCibngf dibngf =
                   nfw AddfssiblfJTbblfModflCibngf(f.gftTypf(),
                                                   f.gftFirstRow(),
                                                   f.gftLbstRow(),
                                                   firstColumn,
                                                   lbstColumn);
               firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                                  null, dibngf);
            }
        }

        /**
         * Trbdk dibngfs to tif tbblf dontfnts (row insfrtions)
         *
         * @pbrbm f b {@dodf TbblfModflEvfnt} dfsdribing tif fvfnt
         */
        publid void tbblfRowsInsfrtfd(TbblfModflEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);

           // Firf b propfrty dibngf fvfnt indidbting tif tbblf modfl
           // ibs dibngfd.
           int firstColumn = f.gftColumn();
           int lbstColumn = f.gftColumn();
           if (firstColumn == TbblfModflEvfnt.ALL_COLUMNS) {
               firstColumn = 0;
               lbstColumn = gftColumnCount() - 1;
           }
           AddfssiblfJTbblfModflCibngf dibngf =
               nfw AddfssiblfJTbblfModflCibngf(f.gftTypf(),
                                               f.gftFirstRow(),
                                               f.gftLbstRow(),
                                               firstColumn,
                                               lbstColumn);
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                              null, dibngf);
        }

        /**
         * Trbdk dibngfs to tif tbblf dontfnts (row dflftions)
         *
         * @pbrbm f b {@dodf TbblfModflEvfnt} dfsdribing tif fvfnt
         */
        publid void tbblfRowsDflftfd(TbblfModflEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);

           // Firf b propfrty dibngf fvfnt indidbting tif tbblf modfl
           // ibs dibngfd.
           int firstColumn = f.gftColumn();
           int lbstColumn = f.gftColumn();
           if (firstColumn == TbblfModflEvfnt.ALL_COLUMNS) {
               firstColumn = 0;
               lbstColumn = gftColumnCount() - 1;
           }
           AddfssiblfJTbblfModflCibngf dibngf =
               nfw AddfssiblfJTbblfModflCibngf(f.gftTypf(),
                                               f.gftFirstRow(),
                                               f.gftLbstRow(),
                                               firstColumn,
                                               lbstColumn);
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                              null, dibngf);
        }

        /**
         * Trbdk dibngfs to tif tbblf dontfnts (dolumn insfrtions)
         */
        publid void dolumnAddfd(TbblfColumnModflEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);

           // Firf b propfrty dibngf fvfnt indidbting tif tbblf modfl
           // ibs dibngfd.
           int typf = AddfssiblfTbblfModflCibngf.INSERT;
           AddfssiblfJTbblfModflCibngf dibngf =
               nfw AddfssiblfJTbblfModflCibngf(typf,
                                               0,
                                               0,
                                               f.gftFromIndfx(),
                                               f.gftToIndfx());
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                              null, dibngf);
        }

        /**
         * Trbdk dibngfs to tif tbblf dontfnts (dolumn dflftions)
         */
        publid void dolumnRfmovfd(TbblfColumnModflEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);
           // Firf b propfrty dibngf fvfnt indidbting tif tbblf modfl
           // ibs dibngfd.
           int typf = AddfssiblfTbblfModflCibngf.DELETE;
           AddfssiblfJTbblfModflCibngf dibngf =
               nfw AddfssiblfJTbblfModflCibngf(typf,
                                               0,
                                               0,
                                               f.gftFromIndfx(),
                                               f.gftToIndfx());
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                              null, dibngf);
        }

        /**
         * Trbdk dibngfs of b dolumn rfpositioning.
         *
         * @sff TbblfColumnModflListfnfr
         */
        publid void dolumnMovfd(TbblfColumnModflEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);

           // Firf propfrty dibngf fvfnts indidbting tif tbblf modfl
           // ibs dibngfd.
           int typf = AddfssiblfTbblfModflCibngf.DELETE;
           AddfssiblfJTbblfModflCibngf dibngf =
               nfw AddfssiblfJTbblfModflCibngf(typf,
                                               0,
                                               0,
                                               f.gftFromIndfx(),
                                               f.gftFromIndfx());
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                              null, dibngf);

           int typf2 = AddfssiblfTbblfModflCibngf.INSERT;
           AddfssiblfJTbblfModflCibngf dibngf2 =
               nfw AddfssiblfJTbblfModflCibngf(typf2,
                                               0,
                                               0,
                                               f.gftToIndfx(),
                                               f.gftToIndfx());
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_MODEL_CHANGED,
                              null, dibngf2);
        }

        /**
         * Trbdk dibngfs of b dolumn moving duf to mbrgin dibngfs.
         *
         * @sff TbblfColumnModflListfnfr
         */
        publid void dolumnMbrginCibngfd(CibngfEvfnt f) {
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);
        }

        /**
         * Trbdk tibt tif sflfdtion modfl of tif TbblfColumnModfl dibngfd.
         *
         * @sff TbblfColumnModflListfnfr
         */
        publid void dolumnSflfdtionCibngfd(ListSflfdtionEvfnt f) {
            // wf siould now rf-plbdf our TbblfColumn listfnfr
        }

        /**
         * Trbdk dibngfs to b dfll's dontfnts.
         *
         * Invokfd wifn fditing is finisifd. Tif dibngfs brf sbvfd, tif
         * fditor objfdt is disdbrdfd, bnd tif dfll is rfndfrfd ondf bgbin.
         *
         * @sff CfllEditorListfnfr
         */
        publid void fditingStoppfd(CibngfEvfnt f) {
           // it'd bf grfbt if wf dould figurf out wiidi dfll, bnd pbss tibt
           // somfiow bs b pbrbmftfr
           firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                              null, null);
        }

        /**
         * Invokfd wifn fditing is dbndflfd. Tif fditor objfdt is disdbrdfd
         * bnd tif dfll is rfndfrfd ondf bgbin.
         *
         * @sff CfllEditorListfnfr
         */
        publid void fditingCbndflfd(CibngfEvfnt f) {
            // notiing to rfport, 'dbusf notiing dibngfd
        }

        /**
         * Trbdk dibngfs to tbblf dfll sflfdtions
         */
        publid void vblufCibngfd(ListSflfdtionEvfnt f) {
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_SELECTION_PROPERTY,
                            Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));

            // Using lfbd sflfdtion indfx to dovfr boti dbsfs: nodf sflfdtfd bnd nodf
            // is fodusfd but not sflfdtfd (Ctrl+up/down)
            int fodusfdRow = JTbblf.tiis.gftSflfdtionModfl().gftLfbdSflfdtionIndfx();
            int fodusfdCol = JTbblf.tiis.gftColumnModfl().gftSflfdtionModfl().
                                                    gftLfbdSflfdtionIndfx();

            if (fodusfdRow != prfviousFodusfdRow ||
                fodusfdCol != prfviousFodusfdCol) {
                Addfssiblf oldA = gftAddfssiblfAt(prfviousFodusfdRow, prfviousFodusfdCol);
                Addfssiblf nfwA = gftAddfssiblfAt(fodusfdRow, fodusfdCol);
                firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_ACTIVE_DESCENDANT_PROPERTY,
                                    oldA, nfwA);
                prfviousFodusfdRow = fodusfdRow;
                prfviousFodusfdCol = fodusfdCol;
            }
        }




    // AddfssiblfContfxt support

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

        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.TABLE;
        }

        /**
         * Rfturns tif <dodf>Addfssiblf</dodf> diild, if onf fxists,
         * dontbinfd bt tif lodbl doordinbtf <dodf>Point</dodf>.
         *
         * @pbrbm p tif point dffining tif top-lfft dornfr of tif
         *    <dodf>Addfssiblf</dodf>, givfn in tif doordinbtf spbdf
         *    of tif objfdt's pbrfnt
         * @rfturn tif <dodf>Addfssiblf</dodf>, if it fxists,
         *    bt tif spfdififd lodbtion; flsf <dodf>null</dodf>
         */
        publid Addfssiblf gftAddfssiblfAt(Point p) {
            int dolumn = dolumnAtPoint(p);
            int row = rowAtPoint(p);

            if ((dolumn != -1) && (row != -1)) {
                TbblfColumn bColumn = gftColumnModfl().gftColumn(dolumn);
                TbblfCfllRfndfrfr rfndfrfr = bColumn.gftCfllRfndfrfr();
                if (rfndfrfr == null) {
                    Clbss<?> dolumnClbss = gftColumnClbss(dolumn);
                    rfndfrfr = gftDffbultRfndfrfr(dolumnClbss);
                }
                Componfnt domponfnt = rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(
                                  JTbblf.tiis, null, fblsf, fblsf,
                                  row, dolumn);
                rfturn nfw AddfssiblfJTbblfCfll(JTbblf.tiis, row, dolumn,
                      gftAddfssiblfIndfxAt(row, dolumn));
            }
            rfturn null;
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.  If bll
         * of tif diildrfn of tiis objfdt implfmfnt <dodf>Addfssiblf</dodf>,
         * tifn tiis mftiod siould rfturn tif numbfr of diildrfn of tiis objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn (JTbblf.tiis.gftColumnCount() * JTbblf.tiis.gftRowCount());
        }

        /**
         * Rfturns tif nti <dodf>Addfssiblf</dodf> diild of tif objfdt.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif nti Addfssiblf diild of tif objfdt
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            if (i < 0 || i >= gftAddfssiblfCiildrfnCount()) {
                rfturn null;
            } flsf {
                // diildrfn indrfbsf bdross, bnd tifn down, for tbblfs
                // (brbitrbry dfdision)
                int dolumn = gftAddfssiblfColumnAtIndfx(i);
                int row = gftAddfssiblfRowAtIndfx(i);

                TbblfColumn bColumn = gftColumnModfl().gftColumn(dolumn);
                TbblfCfllRfndfrfr rfndfrfr = bColumn.gftCfllRfndfrfr();
                if (rfndfrfr == null) {
                    Clbss<?> dolumnClbss = gftColumnClbss(dolumn);
                    rfndfrfr = gftDffbultRfndfrfr(dolumnClbss);
                }
                Componfnt domponfnt = rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(
                                  JTbblf.tiis, null, fblsf, fblsf,
                                  row, dolumn);
                rfturn nfw AddfssiblfJTbblfCfll(JTbblf.tiis, row, dolumn,
                      gftAddfssiblfIndfxAt(row, dolumn));
            }
        }

    // AddfssiblfSflfdtion support

        /**
         * Rfturns tif numbfr of <dodf>Addfssiblf</dodf> diildrfn
         * durrfntly sflfdtfd.
         * If no diildrfn brf sflfdtfd, tif rfturn vbluf will bf 0.
         *
         * @rfturn tif numbfr of itfms durrfntly sflfdtfd
         */
        publid int gftAddfssiblfSflfdtionCount() {
            int rowsSfl = JTbblf.tiis.gftSflfdtfdRowCount();
            int dolsSfl = JTbblf.tiis.gftSflfdtfdColumnCount();

            if (JTbblf.tiis.dfllSflfdtionEnbblfd) { // b dontiguous blodk
                rfturn rowsSfl * dolsSfl;

            } flsf {
                // b dolumn swbti bnd b row swbti, witi b sibrfd blodk
                if (JTbblf.tiis.gftRowSflfdtionAllowfd() &&
                    JTbblf.tiis.gftColumnSflfdtionAllowfd()) {
                    rfturn rowsSfl * JTbblf.tiis.gftColumnCount() +
                           dolsSfl * JTbblf.tiis.gftRowCount() -
                           rowsSfl * dolsSfl;

                // just onf or morf rows in sflfdtion
                } flsf if (JTbblf.tiis.gftRowSflfdtionAllowfd()) {
                    rfturn rowsSfl * JTbblf.tiis.gftColumnCount();

                // just onf or morf rows in sflfdtion
                } flsf if (JTbblf.tiis.gftColumnSflfdtionAllowfd()) {
                    rfturn dolsSfl * JTbblf.tiis.gftRowCount();

                } flsf {
                    rfturn 0;    // JTbblf dofsn't bllow sflfdtions
                }
            }
        }

        /**
         * Rfturns bn <dodf>Addfssiblf</dodf> rfprfsfnting tif
         * spfdififd sflfdtfd diild in tif objfdt.  If tifrf
         * isn't b sflfdtion, or tifrf brf ffwfr diildrfn sflfdtfd
         * tibn tif intfgfr pbssfd in, tif rfturn
         * vbluf will bf <dodf>null</dodf>.
         * <p>Notf tibt tif indfx rfprfsfnts tif i-ti sflfdtfd diild, wiidi
         * is difffrfnt from tif i-ti diild.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtfd diildrfn
         * @rfturn tif i-ti sflfdtfd diild
         * @sff #gftAddfssiblfSflfdtionCount
         */
        publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
            if (i < 0 || i > gftAddfssiblfSflfdtionCount()) {
                rfturn null;
            }

            int rowsSfl = JTbblf.tiis.gftSflfdtfdRowCount();
            int dolsSfl = JTbblf.tiis.gftSflfdtfdColumnCount();
            int rowIndidifs[] = gftSflfdtfdRows();
            int dolIndidifs[] = gftSflfdtfdColumns();
            int ttlCols = JTbblf.tiis.gftColumnCount();
            int ttlRows = JTbblf.tiis.gftRowCount();
            int r;
            int d;

            if (JTbblf.tiis.dfllSflfdtionEnbblfd) { // b dontiguous blodk
                r = rowIndidifs[i / dolsSfl];
                d = dolIndidifs[i % dolsSfl];
                rfturn gftAddfssiblfCiild((r * ttlCols) + d);
            } flsf {

                // b dolumn swbti bnd b row swbti, witi b sibrfd blodk
                if (JTbblf.tiis.gftRowSflfdtionAllowfd() &&
                    JTbblf.tiis.gftColumnSflfdtionAllowfd()) {

                    // Situbtion:
                    //   Wf ibvf b tbblf, likf tif 6x3 tbblf bflow,
                    //   wifrfin tirff dolums bnd onf row sflfdtfd
                    //   (sflfdtfd dflls mbrkfd witi "*", unsflfdtfd "0"):
                    //
                    //            0 * 0 * * 0
                    //            * * * * * *
                    //            0 * 0 * * 0
                    //

                    // Stbtf mbdiinf bflow wblks tirougi tif brrby of
                    // sflfdtfd rows in two stbtfs: in b sflfdtfd row,
                    // bnd not in onf; dontinuing until wf brf in b row
                    // in wiidi tif iti sflfdtion fxists.  Tifn wf rfturn
                    // tif bppropribtf dfll.  In tif stbtf mbdiinf, wf
                    // blwbys do rows bbovf tif "durrfnt" sflfdtfd row first,
                    // tifn tif dflls in tif sflfdtfd row.  If wf'rf donf
                    // witi tif stbtf mbdiinf bfforf finding tif rfqufstfd
                    // sflfdtfd diild, wf ibndlf tif rows bflow tif lbst
                    // sflfdtfd row bt tif fnd.
                    //
                    int durIndfx = i;
                    finbl int IN_ROW = 0;
                    finbl int NOT_IN_ROW = 1;
                    int stbtf = (rowIndidifs[0] == 0 ? IN_ROW : NOT_IN_ROW);
                    int j = 0;
                    int prfvRow = -1;
                    wiilf (j < rowIndidifs.lfngti) {
                        switdi (stbtf) {

                        dbsf IN_ROW:   // on individubl row full of sflfdtions
                            if (durIndfx < ttlCols) { // it's ifrf!
                                d = durIndfx % ttlCols;
                                r = rowIndidifs[j];
                                rfturn gftAddfssiblfCiild((r * ttlCols) + d);
                            } flsf {                               // not ifrf
                                durIndfx -= ttlCols;
                            }
                            // is tif nfxt row in tbblf sflfdtfd or not?
                            if (j + 1 == rowIndidifs.lfngti ||
                                rowIndidifs[j] != rowIndidifs[j+1] - 1) {
                                stbtf = NOT_IN_ROW;
                                prfvRow = rowIndidifs[j];
                            }
                            j++;  // wf didn't rfturn fbrlifr, so go to nfxt row
                            brfbk;

                        dbsf NOT_IN_ROW:  // spbrsf bundi of rows of sflfdtions
                            if (durIndfx <
                                (dolsSfl * (rowIndidifs[j] -
                                (prfvRow == -1 ? 0 : (prfvRow + 1))))) {

                                // it's ifrf!
                                d = dolIndidifs[durIndfx % dolsSfl];
                                r = (j > 0 ? rowIndidifs[j-1] + 1 : 0)
                                    + durIndfx / dolsSfl;
                                rfturn gftAddfssiblfCiild((r * ttlCols) + d);
                            } flsf {                               // not ifrf
                                durIndfx -= dolsSfl * (rowIndidifs[j] -
                                (prfvRow == -1 ? 0 : (prfvRow + 1)));
                            }
                            stbtf = IN_ROW;
                            brfbk;
                        }
                    }
                    // wf got ifrf, so wf didn't find it yft; find it in
                    // tif lbst spbrsf bundi of rows
                    if (durIndfx <
                        (dolsSfl * (ttlRows -
                        (prfvRow == -1 ? 0 : (prfvRow + 1))))) { // it's ifrf!
                        d = dolIndidifs[durIndfx % dolsSfl];
                        r = rowIndidifs[j-1] + durIndfx / dolsSfl + 1;
                        rfturn gftAddfssiblfCiild((r * ttlCols) + d);
                    } flsf {                               // not ifrf
                        // wf siouldn't gft to tiis spot in tif dodf!
//                      Systfm.out.println("Bug in AddfssiblfJTbblf.gftAddfssiblfSflfdtion()");
                    }

                // onf or morf rows sflfdtfd
                } flsf if (JTbblf.tiis.gftRowSflfdtionAllowfd()) {
                    d = i % ttlCols;
                    r = rowIndidifs[i / ttlCols];
                    rfturn gftAddfssiblfCiild((r * ttlCols) + d);

                // onf or morf dolumns sflfdtfd
                } flsf if (JTbblf.tiis.gftColumnSflfdtionAllowfd()) {
                    d = dolIndidifs[i % dolsSfl];
                    r = i / dolsSfl;
                    rfturn gftAddfssiblfCiild((r * ttlCols) + d);
                }
            }
            rfturn null;
        }

        /**
         * Dftfrminfs if tif durrfnt diild of tiis objfdt is sflfdtfd.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis
         *    <dodf>Addfssiblf</dodf> objfdt
         * @rfturn truf if tif durrfnt diild of tiis objfdt is sflfdtfd
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
            int dolumn = gftAddfssiblfColumnAtIndfx(i);
            int row = gftAddfssiblfRowAtIndfx(i);
            rfturn JTbblf.tiis.isCfllSflfdtfd(row, dolumn);
        }

        /**
         * Adds tif spfdififd <dodf>Addfssiblf</dodf> diild of tif
         * objfdt to tif objfdt's sflfdtion.  If tif objfdt supports
         * multiplf sflfdtions, tif spfdififd diild is bddfd to
         * bny fxisting sflfdtion, otifrwisf
         * it rfplbdfs bny fxisting sflfdtion in tif objfdt.  If tif
         * spfdififd diild is blrfbdy sflfdtfd, tiis mftiod ibs no ffffdt.
         * <p>
         * Tiis mftiod only works on <dodf>JTbblf</dodf>s wiidi ibvf
         * individubl dfll sflfdtion fnbblfd.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid void bddAddfssiblfSflfdtion(int i) {
            // TIGER - 4495286
            int dolumn = gftAddfssiblfColumnAtIndfx(i);
            int row = gftAddfssiblfRowAtIndfx(i);
            JTbblf.tiis.dibngfSflfdtion(row, dolumn, truf, fblsf);
        }

        /**
         * Rfmovfs tif spfdififd diild of tif objfdt from tif objfdt's
         * sflfdtion.  If tif spfdififd itfm isn't durrfntly sflfdtfd, tiis
         * mftiod ibs no ffffdt.
         * <p>
         * Tiis mftiod only works on <dodf>JTbblfs</dodf> wiidi ibvf
         * individubl dfll sflfdtion fnbblfd.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid void rfmovfAddfssiblfSflfdtion(int i) {
            if (JTbblf.tiis.dfllSflfdtionEnbblfd) {
                int dolumn = gftAddfssiblfColumnAtIndfx(i);
                int row = gftAddfssiblfRowAtIndfx(i);
                JTbblf.tiis.rfmovfRowSflfdtionIntfrvbl(row, row);
                JTbblf.tiis.rfmovfColumnSflfdtionIntfrvbl(dolumn, dolumn);
            }
        }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt no diildrfn in tif
         * objfdt brf sflfdtfd.
         */
        publid void dlfbrAddfssiblfSflfdtion() {
            JTbblf.tiis.dlfbrSflfdtion();
        }

        /**
         * Cbusfs fvfry diild of tif objfdt to bf sflfdtfd, but only
         * if tif <dodf>JTbblf</dodf> supports multiplf sflfdtions,
         * bnd if individubl dfll sflfdtion is fnbblfd.
         */
        publid void sflfdtAllAddfssiblfSflfdtion() {
            if (JTbblf.tiis.dfllSflfdtionEnbblfd) {
                JTbblf.tiis.sflfdtAll();
            }
        }

        // bfgin AddfssiblfExtfndfdTbblf implfmfntbtion -------------

        /**
         * Rfturns tif row numbfr of bn indfx in tif tbblf.
         *
         * @pbrbm indfx tif zfro-bbsfd indfx in tif tbblf
         * @rfturn tif zfro-bbsfd row of tif tbblf if onf fxists;
         * otifrwisf -1.
         * @sindf 1.4
         */
        publid int gftAddfssiblfRow(int indfx) {
            rfturn gftAddfssiblfRowAtIndfx(indfx);
        }

        /**
         * Rfturns tif dolumn numbfr of bn indfx in tif tbblf.
         *
         * @pbrbm indfx tif zfro-bbsfd indfx in tif tbblf
         * @rfturn tif zfro-bbsfd dolumn of tif tbblf if onf fxists;
         * otifrwisf -1.
         * @sindf 1.4
         */
        publid int gftAddfssiblfColumn(int indfx) {
            rfturn gftAddfssiblfColumnAtIndfx(indfx);
        }

        /**
         * Rfturns tif indfx bt b row bnd dolumn in tif tbblf.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @rfturn tif zfro-bbsfd indfx in tif tbblf if onf fxists;
         * otifrwisf -1.
         * @sindf 1.4
         */
        publid int gftAddfssiblfIndfx(int r, int d) {
            rfturn gftAddfssiblfIndfxAt(r, d);
        }

        // fnd of AddfssiblfExtfndfdTbblf implfmfntbtion ------------

        // stbrt of AddfssiblfTbblf implfmfntbtion ------------------

        privbtf Addfssiblf dbption;
        privbtf Addfssiblf summbry;
        privbtf Addfssiblf [] rowDfsdription;
        privbtf Addfssiblf [] dolumnDfsdription;

        /**
         * Gfts tif <dodf>AddfssiblfTbblf</dodf> bssodibtfd witi tiis
         * objfdt.  In tif implfmfntbtion of tif Jbvb Addfssibility
         * API for tiis dlbss, rfturn tiis objfdt, wiidi is rfsponsiblf
         * for implfmfnting tif <dodf>AddfssiblfTbblfs</dodf> intfrfbdf
         * on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         * @sindf 1.3
         */
        publid AddfssiblfTbblf gftAddfssiblfTbblf() {
            rfturn tiis;
        }

        /**
         * Rfturns tif dbption for tif tbblf.
         *
         * @rfturn tif dbption for tif tbblf
         * @sindf 1.3
         */
        publid Addfssiblf gftAddfssiblfCbption() {
            rfturn tiis.dbption;
        }

        /**
         * Sfts tif dbption for tif tbblf.
         *
         * @pbrbm b tif dbption for tif tbblf
         * @sindf 1.3
         */
        publid void sftAddfssiblfCbption(Addfssiblf b) {
            Addfssiblf oldCbption = dbption;
            tiis.dbption = b;
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_CAPTION_CHANGED,
                               oldCbption, tiis.dbption);
        }

        /**
         * Rfturns tif summbry dfsdription of tif tbblf.
         *
         * @rfturn tif summbry dfsdription of tif tbblf
         * @sindf 1.3
         */
        publid Addfssiblf gftAddfssiblfSummbry() {
            rfturn tiis.summbry;
        }

        /**
         * Sfts tif summbry dfsdription of tif tbblf.
         *
         * @pbrbm b tif summbry dfsdription of tif tbblf
         * @sindf 1.3
         */
        publid void sftAddfssiblfSummbry(Addfssiblf b) {
            Addfssiblf oldSummbry = summbry;
            tiis.summbry = b;
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_TABLE_SUMMARY_CHANGED,
                               oldSummbry, tiis.summbry);
        }

        /*
         * Rfturns tif totbl numbfr of rows in tiis tbblf.
         *
         * @rfturn tif totbl numbfr of rows in tiis tbblf
         */
        publid int gftAddfssiblfRowCount() {
            rfturn JTbblf.tiis.gftRowCount();
        }

        /*
         * Rfturns tif totbl numbfr of dolumns in tif tbblf.
         *
         * @rfturn tif totbl numbfr of dolumns in tif tbblf
         */
        publid int gftAddfssiblfColumnCount() {
            rfturn JTbblf.tiis.gftColumnCount();
        }

        /*
         * Rfturns tif <dodf>Addfssiblf</dodf> bt b spfdififd row
         * bnd dolumn in tif tbblf.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @rfturn tif <dodf>Addfssiblf</dodf> bt tif spfdififd row bnd dolumn
         * in tif tbblf
         */
        publid Addfssiblf gftAddfssiblfAt(int r, int d) {
            rfturn gftAddfssiblfCiild((r * gftAddfssiblfColumnCount()) + d);
        }

        /**
         * Rfturns tif numbfr of rows oddupifd by tif <dodf>Addfssiblf</dodf>
         * bt b spfdififd row bnd dolumn in tif tbblf.
         *
         * @rfturn tif numbfr of rows oddupifd by tif <dodf>Addfssiblf</dodf>
         *     bt b spfdififd row bnd dolumn in tif tbblf
         * @sindf 1.3
         */
        publid int gftAddfssiblfRowExtfntAt(int r, int d) {
            rfturn 1;
        }

        /**
         * Rfturns tif numbfr of dolumns oddupifd by tif
         * <dodf>Addfssiblf</dodf> bt b givfn (row, dolumn).
         *
         * @rfturn tif numbfr of dolumns oddupifd by tif <dodf>Addfssiblf</dodf>
         *     bt b spfdififd row bnd dolumn in tif tbblf
         * @sindf 1.3
         */
        publid int gftAddfssiblfColumnExtfntAt(int r, int d) {
            rfturn 1;
        }

        /**
         * Rfturns tif row ifbdfrs bs bn <dodf>AddfssiblfTbblf</dodf>.
         *
         * @rfturn bn <dodf>AddfssiblfTbblf</dodf> rfprfsfnting tif row
         * ifbdfrs
         * @sindf 1.3
         */
        publid AddfssiblfTbblf gftAddfssiblfRowHfbdfr() {
            // row ifbdfrs brf not supportfd
            rfturn null;
        }

        /**
         * Sfts tif row ifbdfrs bs bn <dodf>AddfssiblfTbblf</dodf>.
         *
         * @pbrbm b bn <dodf>AddfssiblfTbblf</dodf> rfprfsfnting tif row
         *  ifbdfrs
         * @sindf 1.3
         */
        publid void sftAddfssiblfRowHfbdfr(AddfssiblfTbblf b) {
            // row ifbdfrs brf not supportfd
        }

        /**
         * Rfturns tif dolumn ifbdfrs bs bn <dodf>AddfssiblfTbblf</dodf>.
         *
         *  @rfturn bn <dodf>AddfssiblfTbblf</dodf> rfprfsfnting tif dolumn
         *          ifbdfrs, or <dodf>null</dodf> if tif tbblf ifbdfr is
         *          <dodf>null</dodf>
         * @sindf 1.3
         */
        publid AddfssiblfTbblf gftAddfssiblfColumnHfbdfr() {
            JTbblfHfbdfr ifbdfr = JTbblf.tiis.gftTbblfHfbdfr();
            rfturn ifbdfr == null ? null : nfw AddfssiblfTbblfHfbdfr(ifbdfr);
        }

        /*
         * Privbtf dlbss rfprfsfnting b tbblf dolumn ifbdfr
         */
        privbtf dlbss AddfssiblfTbblfHfbdfr implfmfnts AddfssiblfTbblf {
            privbtf JTbblfHfbdfr ifbdfr;
            privbtf TbblfColumnModfl ifbdfrModfl;

            AddfssiblfTbblfHfbdfr(JTbblfHfbdfr ifbdfr) {
                tiis.ifbdfr = ifbdfr;
                tiis.ifbdfrModfl = ifbdfr.gftColumnModfl();
            }

            /**
             * Rfturns tif dbption for tif tbblf.
             *
             * @rfturn tif dbption for tif tbblf
             */
            publid Addfssiblf gftAddfssiblfCbption() { rfturn null; }


            /**
             * Sfts tif dbption for tif tbblf.
             *
             * @pbrbm b tif dbption for tif tbblf
             */
            publid void sftAddfssiblfCbption(Addfssiblf b) {}

            /**
             * Rfturns tif summbry dfsdription of tif tbblf.
             *
             * @rfturn tif summbry dfsdription of tif tbblf
             */
            publid Addfssiblf gftAddfssiblfSummbry() { rfturn null; }

            /**
             * Sfts tif summbry dfsdription of tif tbblf
             *
             * @pbrbm b tif summbry dfsdription of tif tbblf
             */
            publid void sftAddfssiblfSummbry(Addfssiblf b) {}

            /**
             * Rfturns tif numbfr of rows in tif tbblf.
             *
             * @rfturn tif numbfr of rows in tif tbblf
             */
            publid int gftAddfssiblfRowCount() { rfturn 1; }

            /**
             * Rfturns tif numbfr of dolumns in tif tbblf.
             *
             * @rfturn tif numbfr of dolumns in tif tbblf
             */
            publid int gftAddfssiblfColumnCount() {
                rfturn ifbdfrModfl.gftColumnCount();
            }

            /**
             * Rfturns tif Addfssiblf bt b spfdififd row bnd dolumn
             * in tif tbblf.
             *
             * @pbrbm row zfro-bbsfd row of tif tbblf
             * @pbrbm dolumn zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif Addfssiblf bt tif spfdififd row bnd dolumn
             */
            publid Addfssiblf gftAddfssiblfAt(int row, int dolumn) {


                // TIGER - 4715503
                TbblfColumn bColumn = ifbdfrModfl.gftColumn(dolumn);
                TbblfCfllRfndfrfr rfndfrfr = bColumn.gftHfbdfrRfndfrfr();
                if (rfndfrfr == null) {
                    rfndfrfr = ifbdfr.gftDffbultRfndfrfr();
                }
                Componfnt domponfnt = rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(
                                  ifbdfr.gftTbblf(),
                                  bColumn.gftHfbdfrVbluf(), fblsf, fblsf,
                                  -1, dolumn);

                rfturn nfw AddfssiblfJTbblfHfbdfrCfll(row, dolumn,
                                                      JTbblf.tiis.gftTbblfHfbdfr(),
                                                      domponfnt);
            }

            /**
             * Rfturns tif numbfr of rows oddupifd by tif Addfssiblf bt
             * b spfdififd row bnd dolumn in tif tbblf.
             *
             * @rfturn tif numbfr of rows oddupifd by tif Addfssiblf bt b
             * givfn spfdififd (row, dolumn)
             */
            publid int gftAddfssiblfRowExtfntAt(int r, int d) { rfturn 1; }

            /**
             * Rfturns tif numbfr of dolumns oddupifd by tif Addfssiblf bt
             * b spfdififd row bnd dolumn in tif tbblf.
             *
             * @rfturn tif numbfr of dolumns oddupifd by tif Addfssiblf bt b
             * givfn spfdififd row bnd dolumn
             */
            publid int gftAddfssiblfColumnExtfntAt(int r, int d) { rfturn 1; }

            /**
             * Rfturns tif row ifbdfrs bs bn AddfssiblfTbblf.
             *
             * @rfturn bn AddfssiblfTbblf rfprfsfnting tif row
             * ifbdfrs
             */
            publid AddfssiblfTbblf gftAddfssiblfRowHfbdfr() { rfturn null; }

            /**
             * Sfts tif row ifbdfrs.
             *
             * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
             * row ifbdfrs
             */
            publid void sftAddfssiblfRowHfbdfr(AddfssiblfTbblf tbblf) {}

            /**
             * Rfturns tif dolumn ifbdfrs bs bn AddfssiblfTbblf.
             *
             * @rfturn bn AddfssiblfTbblf rfprfsfnting tif dolumn
             * ifbdfrs
             */
            publid AddfssiblfTbblf gftAddfssiblfColumnHfbdfr() { rfturn null; }

            /**
             * Sfts tif dolumn ifbdfrs.
             *
             * @pbrbm tbblf bn AddfssiblfTbblf rfprfsfnting tif
             * dolumn ifbdfrs
             * @sindf 1.3
             */
            publid void sftAddfssiblfColumnHfbdfr(AddfssiblfTbblf tbblf) {}

            /**
             * Rfturns tif dfsdription of tif spfdififd row in tif tbblf.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @rfturn tif dfsdription of tif row
             * @sindf 1.3
             */
            publid Addfssiblf gftAddfssiblfRowDfsdription(int r) { rfturn null; }

            /**
             * Sfts tif dfsdription tfxt of tif spfdififd row of tif tbblf.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @pbrbm b tif dfsdription of tif row
             * @sindf 1.3
             */
            publid void sftAddfssiblfRowDfsdription(int r, Addfssiblf b) {}

            /**
             * Rfturns tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
             *
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif tfxt dfsdription of tif dolumn
             * @sindf 1.3
             */
            publid Addfssiblf gftAddfssiblfColumnDfsdription(int d) { rfturn null; }

            /**
             * Sfts tif dfsdription tfxt of tif spfdififd dolumn in tif tbblf.
             *
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @pbrbm b tif tfxt dfsdription of tif dolumn
             * @sindf 1.3
             */
            publid void sftAddfssiblfColumnDfsdription(int d, Addfssiblf b) {}

            /**
             * Rfturns b boolfbn vbluf indidbting wiftifr tif bddfssiblf bt
             * b spfdififd row bnd dolumn is sflfdtfd.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif boolfbn vbluf truf if tif bddfssiblf bt tif
             * row bnd dolumn is sflfdtfd. Otifrwisf, tif boolfbn vbluf
             * fblsf
             * @sindf 1.3
             */
            publid boolfbn isAddfssiblfSflfdtfd(int r, int d) { rfturn fblsf; }

            /**
             * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd row
             * is sflfdtfd.
             *
             * @pbrbm r zfro-bbsfd row of tif tbblf
             * @rfturn tif boolfbn vbluf truf if tif spfdififd row is sflfdtfd.
             * Otifrwisf, fblsf.
             * @sindf 1.3
             */
            publid boolfbn isAddfssiblfRowSflfdtfd(int r) { rfturn fblsf; }

            /**
             * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd dolumn
             * is sflfdtfd.
             *
             * @pbrbm d zfro-bbsfd dolumn of tif tbblf
             * @rfturn tif boolfbn vbluf truf if tif spfdififd dolumn is sflfdtfd.
             * Otifrwisf, fblsf.
             * @sindf 1.3
             */
            publid boolfbn isAddfssiblfColumnSflfdtfd(int d) { rfturn fblsf; }

            /**
             * Rfturns tif sflfdtfd rows in b tbblf.
             *
             * @rfturn bn brrby of sflfdtfd rows wifrf fbdi flfmfnt is b
             * zfro-bbsfd row of tif tbblf
             * @sindf 1.3
             */
            publid int [] gftSflfdtfdAddfssiblfRows() { rfturn nfw int[0]; }

            /**
             * Rfturns tif sflfdtfd dolumns in b tbblf.
             *
             * @rfturn bn brrby of sflfdtfd dolumns wifrf fbdi flfmfnt is b
             * zfro-bbsfd dolumn of tif tbblf
             * @sindf 1.3
             */
            publid int [] gftSflfdtfdAddfssiblfColumns() { rfturn nfw int[0]; }
        }


        /**
         * Sfts tif dolumn ifbdfrs bs bn <dodf>AddfssiblfTbblf</dodf>.
         *
         * @pbrbm b bn <dodf>AddfssiblfTbblf</dodf> rfprfsfnting tif
         * dolumn ifbdfrs
         * @sindf 1.3
         */
        publid void sftAddfssiblfColumnHfbdfr(AddfssiblfTbblf b) {
            // XXX not implfmfntfd
        }

        /**
         * Rfturns tif dfsdription of tif spfdififd row in tif tbblf.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @rfturn tif dfsdription of tif row
         * @sindf 1.3
         */
        publid Addfssiblf gftAddfssiblfRowDfsdription(int r) {
            if (r < 0 || r >= gftAddfssiblfRowCount()) {
                tirow nfw IllfgblArgumfntExdfption(Intfgfr.toString(r));
            }
            if (rowDfsdription == null) {
                rfturn null;
            } flsf {
                rfturn rowDfsdription[r];
            }
        }

        /**
         * Sfts tif dfsdription tfxt of tif spfdififd row of tif tbblf.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @pbrbm b tif dfsdription of tif row
         * @sindf 1.3
         */
        publid void sftAddfssiblfRowDfsdription(int r, Addfssiblf b) {
            if (r < 0 || r >= gftAddfssiblfRowCount()) {
                tirow nfw IllfgblArgumfntExdfption(Intfgfr.toString(r));
            }
            if (rowDfsdription == null) {
                int numRows = gftAddfssiblfRowCount();
                rowDfsdription = nfw Addfssiblf[numRows];
            }
            rowDfsdription[r] = b;
        }

        /**
         * Rfturns tif dfsdription of tif spfdififd dolumn in tif tbblf.
         *
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @rfturn tif dfsdription of tif dolumn
         * @sindf 1.3
         */
        publid Addfssiblf gftAddfssiblfColumnDfsdription(int d) {
            if (d < 0 || d >= gftAddfssiblfColumnCount()) {
                tirow nfw IllfgblArgumfntExdfption(Intfgfr.toString(d));
            }
            if (dolumnDfsdription == null) {
                rfturn null;
            } flsf {
                rfturn dolumnDfsdription[d];
            }
        }

        /**
         * Sfts tif dfsdription tfxt of tif spfdififd dolumn of tif tbblf.
         *
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @pbrbm b tif dfsdription of tif dolumn
         * @sindf 1.3
         */
        publid void sftAddfssiblfColumnDfsdription(int d, Addfssiblf b) {
            if (d < 0 || d >= gftAddfssiblfColumnCount()) {
                tirow nfw IllfgblArgumfntExdfption(Intfgfr.toString(d));
            }
            if (dolumnDfsdription == null) {
                int numColumns = gftAddfssiblfColumnCount();
                dolumnDfsdription = nfw Addfssiblf[numColumns];
            }
            dolumnDfsdription[d] = b;
        }

        /**
         * Rfturns b boolfbn vbluf indidbting wiftifr tif bddfssiblf bt b
         * givfn (row, dolumn) is sflfdtfd.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @rfturn tif boolfbn vbluf truf if tif bddfssiblf bt (row, dolumn)
         *     is sflfdtfd; otifrwisf, tif boolfbn vbluf fblsf
         * @sindf 1.3
         */
        publid boolfbn isAddfssiblfSflfdtfd(int r, int d) {
            rfturn JTbblf.tiis.isCfllSflfdtfd(r, d);
        }

        /**
         * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd row
         * is sflfdtfd.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @rfturn tif boolfbn vbluf truf if tif spfdififd row is sflfdtfd;
         *     otifrwisf, fblsf
         * @sindf 1.3
         */
        publid boolfbn isAddfssiblfRowSflfdtfd(int r) {
            rfturn JTbblf.tiis.isRowSflfdtfd(r);
        }

        /**
         * Rfturns b boolfbn vbluf indidbting wiftifr tif spfdififd dolumn
         * is sflfdtfd.
         *
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @rfturn tif boolfbn vbluf truf if tif spfdififd dolumn is sflfdtfd;
         *     otifrwisf, fblsf
         * @sindf 1.3
         */
        publid boolfbn isAddfssiblfColumnSflfdtfd(int d) {
            rfturn JTbblf.tiis.isColumnSflfdtfd(d);
        }

        /**
         * Rfturns tif sflfdtfd rows in b tbblf.
         *
         * @rfturn bn brrby of sflfdtfd rows wifrf fbdi flfmfnt is b
         *     zfro-bbsfd row of tif tbblf
         * @sindf 1.3
         */
        publid int [] gftSflfdtfdAddfssiblfRows() {
            rfturn JTbblf.tiis.gftSflfdtfdRows();
        }

        /**
         * Rfturns tif sflfdtfd dolumns in b tbblf.
         *
         * @rfturn bn brrby of sflfdtfd dolumns wifrf fbdi flfmfnt is b
         *     zfro-bbsfd dolumn of tif tbblf
         * @sindf 1.3
         */
        publid int [] gftSflfdtfdAddfssiblfColumns() {
            rfturn JTbblf.tiis.gftSflfdtfdColumns();
        }

        /**
         * Rfturns tif row bt b givfn indfx into tif tbblf.
         *
         * @pbrbm i zfro-bbsfd indfx into tif tbblf
         * @rfturn tif row bt b givfn indfx
         * @sindf 1.3
         */
        publid int gftAddfssiblfRowAtIndfx(int i) {
            int dolumnCount = gftAddfssiblfColumnCount();
            if (dolumnCount == 0) {
                rfturn -1;
            } flsf {
                rfturn (i / dolumnCount);
            }
        }

        /**
         * Rfturns tif dolumn bt b givfn indfx into tif tbblf.
         *
         * @pbrbm i zfro-bbsfd indfx into tif tbblf
         * @rfturn tif dolumn bt b givfn indfx
         * @sindf 1.3
         */
        publid int gftAddfssiblfColumnAtIndfx(int i) {
            int dolumnCount = gftAddfssiblfColumnCount();
            if (dolumnCount == 0) {
                rfturn -1;
            } flsf {
                rfturn (i % dolumnCount);
            }
        }

        /**
         * Rfturns tif indfx bt b givfn (row, dolumn) in tif tbblf.
         *
         * @pbrbm r zfro-bbsfd row of tif tbblf
         * @pbrbm d zfro-bbsfd dolumn of tif tbblf
         * @rfturn tif indfx into tif tbblf
         * @sindf 1.3
         */
        publid int gftAddfssiblfIndfxAt(int r, int d) {
            rfturn ((r * gftAddfssiblfColumnCount()) + d);
        }

        // fnd of AddfssiblfTbblf implfmfntbtion --------------------

        /**
         * Tif dlbss providfs bn implfmfntbtion of tif Jbvb Addfssibility
         * API bppropribtf to tbblf dflls.
         */
        protfdtfd dlbss AddfssiblfJTbblfCfll fxtfnds AddfssiblfContfxt
            implfmfnts Addfssiblf, AddfssiblfComponfnt {

            privbtf JTbblf pbrfnt;
            privbtf int row;
            privbtf int dolumn;
            privbtf int indfx;

            /**
             *  Construdts bn <dodf>AddfssiblfJTbblfHfbdfrEntry</dodf>.
             *
             * @pbrbm t b {@dodf JTbblf}
             * @pbrbm r bn {@dodf int} spfdifying b row
             * @pbrbm d bn {@dodf int} spfdifying b dolumn
             * @pbrbm i bn {@dodf int} spfdifying tif indfx to tiis dfll
             * @sindf 1.4
             */
            publid AddfssiblfJTbblfCfll(JTbblf t, int r, int d, int i) {
                pbrfnt = t;
                row = r;
                dolumn = d;
                indfx = i;
                tiis.sftAddfssiblfPbrfnt(pbrfnt);
            }

            /**
             * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
             * domponfnt. In tif implfmfntbtion of tif Jbvb Addfssibility
             * API for tiis dlbss, rfturn tiis objfdt, wiidi is its own
             * <dodf>AddfssiblfContfxt</dodf>.
             *
             * @rfturn tiis objfdt
             */
            publid AddfssiblfContfxt gftAddfssiblfContfxt() {
                rfturn tiis;
            }

            /**
             * Gfts tif AddfssiblfContfxt for tif tbblf dfll rfndfrfr.
             *
             * @rfturn tif <dodf>AddfssiblfContfxt</dodf> for tif tbblf
             * dfll rfndfrfr if onf fxists;
             * otifrwisf, rfturns <dodf>null</dodf>.
             * @sindf 1.6
             */
            protfdtfd AddfssiblfContfxt gftCurrfntAddfssiblfContfxt() {
                TbblfColumn bColumn = gftColumnModfl().gftColumn(dolumn);
                TbblfCfllRfndfrfr rfndfrfr = bColumn.gftCfllRfndfrfr();
                if (rfndfrfr == null) {
                    Clbss<?> dolumnClbss = gftColumnClbss(dolumn);
                    rfndfrfr = gftDffbultRfndfrfr(dolumnClbss);
                }
                Componfnt domponfnt = rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(
                                  JTbblf.tiis, gftVblufAt(row, dolumn),
                                  fblsf, fblsf, row, dolumn);
                if (domponfnt instbndfof Addfssiblf) {
                    rfturn domponfnt.gftAddfssiblfContfxt();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Gfts tif tbblf dfll rfndfrfr domponfnt.
             *
             * @rfturn tif tbblf dfll rfndfrfr domponfnt if onf fxists;
             * otifrwisf, rfturns <dodf>null</dodf>.
             * @sindf 1.6
             */
            protfdtfd Componfnt gftCurrfntComponfnt() {
                TbblfColumn bColumn = gftColumnModfl().gftColumn(dolumn);
                TbblfCfllRfndfrfr rfndfrfr = bColumn.gftCfllRfndfrfr();
                if (rfndfrfr == null) {
                    Clbss<?> dolumnClbss = gftColumnClbss(dolumn);
                    rfndfrfr = gftDffbultRfndfrfr(dolumnClbss);
                }
                rfturn rfndfrfr.gftTbblfCfllRfndfrfrComponfnt(
                                  JTbblf.tiis, null, fblsf, fblsf,
                                  row, dolumn);
            }

        // AddfssiblfContfxt mftiods

            /**
             * Gfts tif bddfssiblf nbmf of tiis objfdt.
             *
             * @rfturn tif lodblizfd nbmf of tif objfdt; <dodf>null</dodf>
             *     if tiis objfdt dofs not ibvf b nbmf
             */
            publid String gftAddfssiblfNbmf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    String nbmf = bd.gftAddfssiblfNbmf();
                    if ((nbmf != null) && (nbmf != "")) {
                        // rfturn tif dfll rfndfrfr's AddfssiblfNbmf
                        rfturn nbmf;
                    }
                }
                if ((bddfssiblfNbmf != null) && (bddfssiblfNbmf != "")) {
                    rfturn bddfssiblfNbmf;
                } flsf {
                    // fbll bbdk to tif dlifnt propfrty
                    rfturn (String)gftClifntPropfrty(AddfssiblfContfxt.ACCESSIBLE_NAME_PROPERTY);
                }
            }

            /**
             * Sfts tif lodblizfd bddfssiblf nbmf of tiis objfdt.
             *
             * @pbrbm s tif nfw lodblizfd nbmf of tif objfdt
             */
            publid void sftAddfssiblfNbmf(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfNbmf(s);
                } flsf {
                    supfr.sftAddfssiblfNbmf(s);
                }
            }

            //
            // *** siould difdk toolTip tfxt for dfsd. (nffds MousfEvfnt)
            //
            /**
             * Gfts tif bddfssiblf dfsdription of tiis objfdt.
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt;
             *     <dodf>null</dodf> if tiis objfdt dofs not ibvf
             *     b dfsdription
             */
            publid String gftAddfssiblfDfsdription() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfDfsdription();
                } flsf {
                    rfturn supfr.gftAddfssiblfDfsdription();
                }
            }

            /**
             * Sfts tif bddfssiblf dfsdription of tiis objfdt.
             *
             * @pbrbm s tif nfw lodblizfd dfsdription of tif objfdt
             */
            publid void sftAddfssiblfDfsdription(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfDfsdription(s);
                } flsf {
                    supfr.sftAddfssiblfDfsdription(s);
                }
            }

            /**
             * Gfts tif rolf of tiis objfdt.
             *
             * @rfturn bn instbndf of <dodf>AddfssiblfRolf</dodf>
             *      dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfRolf();
                } flsf {
                    rfturn AddfssiblfRolf.UNKNOWN;
                }
            }

            /**
             * Gfts tif stbtf sft of tiis objfdt.
             *
             * @rfturn bn instbndf of <dodf>AddfssiblfStbtfSft</dodf>
             *     dontbining tif durrfnt stbtf sft of tif objfdt
             * @sff AddfssiblfStbtf
             */
            publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                AddfssiblfStbtfSft bs = null;

                if (bd != null) {
                    bs = bd.gftAddfssiblfStbtfSft();
                }
                if (bs == null) {
                    bs = nfw AddfssiblfStbtfSft();
                }
                Rfdtbnglf rjt = JTbblf.tiis.gftVisiblfRfdt();
                Rfdtbnglf rdfll = JTbblf.tiis.gftCfllRfdt(row, dolumn, fblsf);
                if (rjt.intfrsfdts(rdfll)) {
                    bs.bdd(AddfssiblfStbtf.SHOWING);
                } flsf {
                    if (bs.dontbins(AddfssiblfStbtf.SHOWING)) {
                         bs.rfmovf(AddfssiblfStbtf.SHOWING);
                    }
                }
                if (pbrfnt.isCfllSflfdtfd(row, dolumn)) {
                    bs.bdd(AddfssiblfStbtf.SELECTED);
                } flsf if (bs.dontbins(AddfssiblfStbtf.SELECTED)) {
                    bs.rfmovf(AddfssiblfStbtf.SELECTED);
                }
                if ((row == gftSflfdtfdRow()) && (dolumn == gftSflfdtfdColumn())) {
                    bs.bdd(AddfssiblfStbtf.ACTIVE);
                }
                bs.bdd(AddfssiblfStbtf.TRANSIENT);
                rfturn bs;
            }

            /**
             * Gfts tif <dodf>Addfssiblf</dodf> pbrfnt of tiis objfdt.
             *
             * @rfturn tif Addfssiblf pbrfnt of tiis objfdt;
             *     <dodf>null</dodf> if tiis objfdt dofs not
             *     ibvf bn <dodf>Addfssiblf</dodf> pbrfnt
             */
            publid Addfssiblf gftAddfssiblfPbrfnt() {
                rfturn pbrfnt;
            }

            /**
             * Gfts tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
             *
             * @rfturn tif indfx of tiis objfdt in its pbrfnt; -1 if tiis
             *     objfdt dofs not ibvf bn bddfssiblf pbrfnt
             * @sff #gftAddfssiblfPbrfnt
             */
            publid int gftAddfssiblfIndfxInPbrfnt() {
                rfturn indfx;
            }

            /**
             * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.
             *
             * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
             */
            publid int gftAddfssiblfCiildrfnCount() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfCiildrfnCount();
                } flsf {
                    rfturn 0;
                }
            }

            /**
             * Rfturns tif spfdififd <dodf>Addfssiblf</dodf> diild of tif
             * objfdt.
             *
             * @pbrbm i zfro-bbsfd indfx of diild
             * @rfturn tif <dodf>Addfssiblf</dodf> diild of tif objfdt
             */
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

            /**
             * Gfts tif lodblf of tif domponfnt. If tif domponfnt
             * dofs not ibvf b lodblf, tifn tif lodblf of its pbrfnt
             * is rfturnfd.
             *
             * @rfturn tiis domponfnt's lodblf; if tiis domponfnt dofs
             *    not ibvf b lodblf, tif lodblf of its pbrfnt is rfturnfd
             * @fxdfption IllfgblComponfntStbtfExdfption if tif
             *    <dodf>Componfnt</dodf> dofs not ibvf its own lodblf
             *    bnd ibs not yft bffn bddfd to b dontbinmfnt iifrbrdiy
             *    sudi tibt tif lodblf dbn bf dftfrminfd from tif
             *    dontbining pbrfnt
             * @sff #sftLodblf
             */
            publid Lodblf gftLodblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftLodblf();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Adds b <dodf>PropfrtyCibngfListfnfr</dodf> to tif listfnfr list.
             * Tif listfnfr is rfgistfrfd for bll propfrtifs.
             *
             * @pbrbm l  tif <dodf>PropfrtyCibngfListfnfr</dodf>
             *     to bf bddfd
             */
            publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.bddPropfrtyCibngfListfnfr(l);
                } flsf {
                    supfr.bddPropfrtyCibngfListfnfr(l);
                }
            }

            /**
             * Rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> from tif
             * listfnfr list. Tiis rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf>
             * tibt wbs rfgistfrfd for bll propfrtifs.
             *
             * @pbrbm l  tif <dodf>PropfrtyCibngfListfnfr</dodf>
             *    to bf rfmovfd
             */
            publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.rfmovfPropfrtyCibngfListfnfr(l);
                } flsf {
                    supfr.rfmovfPropfrtyCibngfListfnfr(l);
                }
            }

            /**
             * Gfts tif <dodf>AddfssiblfAdtion</dodf> bssodibtfd witi tiis
             * objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfAdtion</dodf>, or <dodf>null</dodf>
             */
            publid AddfssiblfAdtion gftAddfssiblfAdtion() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfAdtion();
            }

            /**
             * Gfts tif <dodf>AddfssiblfComponfnt</dodf> bssodibtfd witi
             * tiis objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfComponfnt</dodf>, or
             *    <dodf>null</dodf>
             */
            publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
                rfturn tiis; // to ovfrridf gftBounds()
            }

            /**
             * Gfts tif <dodf>AddfssiblfSflfdtion</dodf> bssodibtfd witi
             * tiis objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfSflfdtion</dodf>, or
             *    <dodf>null</dodf>
             */
            publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfSflfdtion();
            }

            /**
             * Gfts tif <dodf>AddfssiblfTfxt</dodf> bssodibtfd witi tiis
             * objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfTfxt</dodf>, or <dodf>null</dodf>
             */
            publid AddfssiblfTfxt gftAddfssiblfTfxt() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfTfxt();
            }

            /**
             * Gfts tif <dodf>AddfssiblfVbluf</dodf> bssodibtfd witi
             * tiis objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfVbluf</dodf>, or <dodf>null</dodf>
             */
            publid AddfssiblfVbluf gftAddfssiblfVbluf() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfVbluf();
            }


        // AddfssiblfComponfnt mftiods

            /**
             * Gfts tif bbdkground dolor of tiis objfdt.
             *
             * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
             *     otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif bbdkground dolor of tiis objfdt.
             *
             * @pbrbm d tif nfw <dodf>Color</dodf> for tif bbdkground
             */
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

            /**
             * Gfts tif forfground dolor of tiis objfdt.
             *
             * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
             *     otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif forfground dolor of tiis objfdt.
             *
             * @pbrbm d tif nfw <dodf>Color</dodf> for tif forfground
             */
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

            /**
             * Gfts tif <dodf>Cursor</dodf> of tiis objfdt.
             *
             * @rfturn tif <dodf>Cursor</dodf>, if supportfd,
             *    of tif objfdt; otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif <dodf>Cursor</dodf> of tiis objfdt.
             *
             * @pbrbm d tif nfw <dodf>Cursor</dodf> for tif objfdt
             */
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

            /**
             * Gfts tif <dodf>Font</dodf> of tiis objfdt.
             *
             * @rfturn tif <dodf>Font</dodf>,if supportfd,
             *   for tif objfdt; otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif <dodf>Font</dodf> of tiis objfdt.
             *
             * @pbrbm f tif nfw <dodf>Font</dodf> for tif objfdt
             */
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

            /**
             * Gfts tif <dodf>FontMftrids</dodf> of tiis objfdt.
             *
             * @pbrbm f tif <dodf>Font</dodf>
             * @rfturn tif <dodf>FontMftrids</dodf> objfdt, if supportfd;
             *    otifrwisf <dodf>null</dodf>
             * @sff #gftFont
             */
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

            /**
             * Dftfrminfs if tif objfdt is fnbblfd.
             *
             * @rfturn truf if objfdt is fnbblfd; otifrwisf, fblsf
             */
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

            /**
             * Sfts tif fnbblfd stbtf of tif objfdt.
             *
             * @pbrbm b if truf, fnbblfs tiis objfdt; otifrwisf, disbblfs it
             */
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

            /**
             * Dftfrminfs if tiis objfdt is visiblf.  Notf: tiis mfbns tibt tif
             * objfdt intfnds to bf visiblf; iowfvfr, it mby not in fbdt bf
             * siowing on tif sdrffn bfdbusf onf of tif objfdts tibt tiis objfdt
             * is dontbinfd by is not visiblf.  To dftfrminf if bn objfdt is
             * siowing on tif sdrffn, usf <dodf>isSiowing</dodf>.
             *
             * @rfturn truf if objfdt is visiblf; otifrwisf, fblsf
             */
            publid boolfbn isVisiblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).isVisiblf();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isVisiblf();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            /**
             * Sfts tif visiblf stbtf of tif objfdt.
             *
             * @pbrbm b if truf, siows tiis objfdt; otifrwisf, iidfs it
             */
            publid void sftVisiblf(boolfbn b) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftVisiblf(b);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftVisiblf(b);
                    }
                }
            }

            /**
             * Dftfrminfs if tif objfdt is siowing.  Tiis is dftfrminfd
             * by difdking tif visibility of tif objfdt bnd bndfstors
             * of tif objfdt.  Notf: tiis will rfturn truf fvfn if tif
             * objfdt is obsdurfd by bnotifr (for fxbmplf,
             * it ibppfns to bf undfrnfbti b mfnu tibt wbs pullfd down).
             *
             * @rfturn truf if tif objfdt is siowing; otifrwisf, fblsf
             */
            publid boolfbn isSiowing() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    if (bd.gftAddfssiblfPbrfnt() != null) {
                        rfturn ((AddfssiblfComponfnt) bd).isSiowing();
                    } flsf {
                        // Fixfs 4529616 - AddfssiblfJTbblfCfll.isSiowing()
                        // rfturns fblsf wifn tif dfll on tif sdrffn
                        // if no pbrfnt
                        rfturn isVisiblf();
                    }
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isSiowing();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            /**
             * Cifdks wiftifr tif spfdififd point is witiin tiis
             * objfdt's bounds, wifrf tif point's x bnd y doordinbtfs
             * brf dffinfd to bf rflbtivf to tif doordinbtf systfm of
             * tif objfdt.
             *
             * @pbrbm p tif <dodf>Point</dodf> rflbtivf to tif
             *    doordinbtf systfm of tif objfdt
             * @rfturn truf if objfdt dontbins <dodf>Point</dodf>;
             *    otifrwisf fblsf
             */
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

            /**
             * Rfturns tif lodbtion of tif objfdt on tif sdrffn.
             *
             * @rfturn lodbtion of objfdt on sdrffn -- dbn bf
             *    <dodf>null</dodf> if tiis objfdt is not on tif sdrffn
             */
            publid Point gftLodbtionOnSdrffn() {
                if (pbrfnt != null && pbrfnt.isSiowing()) {
                    Point pbrfntLodbtion = pbrfnt.gftLodbtionOnSdrffn();
                    Point domponfntLodbtion = gftLodbtion();
                    domponfntLodbtion.trbnslbtf(pbrfntLodbtion.x, pbrfntLodbtion.y);
                    rfturn domponfntLodbtion;
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Gfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt
             * in tif form of b point spfdifying tif objfdt's
             * top-lfft dornfr in tif sdrffn's doordinbtf spbdf.
             *
             * @rfturn bn instbndf of <dodf>Point</dodf> rfprfsfnting
             *    tif top-lfft dornfr of tif objfdt's bounds in tif
             *    doordinbtf spbdf of tif sdrffn; <dodf>null</dodf> if
             *    tiis objfdt or its pbrfnt brf not on tif sdrffn
             */
            publid Point gftLodbtion() {
                if (pbrfnt != null) {
                    Rfdtbnglf r = pbrfnt.gftCfllRfdt(row, dolumn, fblsf);
                    if (r != null) {
                        rfturn r.gftLodbtion();
                    }
                }
                rfturn null;
            }

            /**
             * Sfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt.
             */
            publid void sftLodbtion(Point p) {
//              if ((pbrfnt != null)  && (pbrfnt.dontbins(p))) {
//                  fnsurfIndfxIsVisiblf(indfxInPbrfnt);
//              }
            }

            publid Rfdtbnglf gftBounds() {
                if (pbrfnt != null) {
                    rfturn pbrfnt.gftCfllRfdt(row, dolumn, fblsf);
                } flsf {
                    rfturn null;
                }
            }

            publid void sftBounds(Rfdtbnglf r) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftBounds(r);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftBounds(r);
                    }
                }
            }

            publid Dimfnsion gftSizf() {
                if (pbrfnt != null) {
                    Rfdtbnglf r = pbrfnt.gftCfllRfdt(row, dolumn, fblsf);
                    if (r != null) {
                        rfturn r.gftSizf();
                    }
                }
                rfturn null;
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

        } // innfr dlbss AddfssiblfJTbblfCfll

        // Bfgin AddfssiblfJTbblfHfbdfr ========== // TIGER - 4715503

        /**
         * Tiis dlbss implfmfnts bddfssibility for JTbblf ifbdfr dflls.
         */
        privbtf dlbss AddfssiblfJTbblfHfbdfrCfll fxtfnds AddfssiblfContfxt
            implfmfnts Addfssiblf, AddfssiblfComponfnt {

            privbtf int row;
            privbtf int dolumn;
            privbtf JTbblfHfbdfr pbrfnt;
            privbtf Componfnt rfndfrfrComponfnt;

            /**
             * Construdts bn <dodf>AddfssiblfJTbblfHfbdfrEntry</dodf> instbndf.
             *
             * @pbrbm row ifbdfr dfll row indfx
             * @pbrbm dolumn ifbdfr dfll dolumn indfx
             * @pbrbm pbrfnt ifbdfr dfll pbrfnt
             * @pbrbm rfndfrfrComponfnt domponfnt tibt rfndfrs tif ifbdfr dfll
             */
            publid AddfssiblfJTbblfHfbdfrCfll(int row, int dolumn,
                                              JTbblfHfbdfr pbrfnt,
                                              Componfnt rfndfrfrComponfnt) {
                tiis.row = row;
                tiis.dolumn = dolumn;
                tiis.pbrfnt = pbrfnt;
                tiis.rfndfrfrComponfnt = rfndfrfrComponfnt;
                tiis.sftAddfssiblfPbrfnt(pbrfnt);
            }

            /**
             * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
             * domponfnt. In tif implfmfntbtion of tif Jbvb Addfssibility
             * API for tiis dlbss, rfturn tiis objfdt, wiidi is its own
             * <dodf>AddfssiblfContfxt</dodf>.
             *
             * @rfturn tiis objfdt
             */
            publid AddfssiblfContfxt gftAddfssiblfContfxt() {
                rfturn tiis;
            }

            /*
             * Rfturns tif AddfssiblfContfxt for tif ifbdfr dfll
             * rfndfrfr.
             */
            privbtf AddfssiblfContfxt gftCurrfntAddfssiblfContfxt() {
                rfturn rfndfrfrComponfnt.gftAddfssiblfContfxt();
            }

            /*
             * Rfturns tif domponfnt tibt rfndfrs tif ifbdfr dfll.
             */
            privbtf Componfnt gftCurrfntComponfnt() {
                rfturn rfndfrfrComponfnt;
            }

            // AddfssiblfContfxt mftiods ==========

            /**
             * Gfts tif bddfssiblf nbmf of tiis objfdt.
             *
             * @rfturn tif lodblizfd nbmf of tif objfdt; <dodf>null</dodf>
             *     if tiis objfdt dofs not ibvf b nbmf
             */
            publid String gftAddfssiblfNbmf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    String nbmf = bd.gftAddfssiblfNbmf();
                    if ((nbmf != null) && (nbmf != "")) {
                        rfturn bd.gftAddfssiblfNbmf();
                    }
                }
                if ((bddfssiblfNbmf != null) && (bddfssiblfNbmf != "")) {
                    rfturn bddfssiblfNbmf;
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Sfts tif lodblizfd bddfssiblf nbmf of tiis objfdt.
             *
             * @pbrbm s tif nfw lodblizfd nbmf of tif objfdt
             */
            publid void sftAddfssiblfNbmf(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfNbmf(s);
                } flsf {
                    supfr.sftAddfssiblfNbmf(s);
                }
            }

            /**
             * Gfts tif bddfssiblf dfsdription of tiis objfdt.
             *
             * @rfturn tif lodblizfd dfsdription of tif objfdt;
             *     <dodf>null</dodf> if tiis objfdt dofs not ibvf
             *     b dfsdription
             */
            publid String gftAddfssiblfDfsdription() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfDfsdription();
                } flsf {
                    rfturn supfr.gftAddfssiblfDfsdription();
                }
            }

            /**
             * Sfts tif bddfssiblf dfsdription of tiis objfdt.
             *
             * @pbrbm s tif nfw lodblizfd dfsdription of tif objfdt
             */
            publid void sftAddfssiblfDfsdription(String s) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.sftAddfssiblfDfsdription(s);
                } flsf {
                    supfr.sftAddfssiblfDfsdription(s);
                }
            }

            /**
             * Gfts tif rolf of tiis objfdt.
             *
             * @rfturn bn instbndf of <dodf>AddfssiblfRolf</dodf>
             *      dfsdribing tif rolf of tif objfdt
             * @sff AddfssiblfRolf
             */
            publid AddfssiblfRolf gftAddfssiblfRolf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfRolf();
                } flsf {
                    rfturn AddfssiblfRolf.UNKNOWN;
                }
            }

            /**
             * Gfts tif stbtf sft of tiis objfdt.
             *
             * @rfturn bn instbndf of <dodf>AddfssiblfStbtfSft</dodf>
             *     dontbining tif durrfnt stbtf sft of tif objfdt
             * @sff AddfssiblfStbtf
             */
            publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                AddfssiblfStbtfSft bs = null;

                if (bd != null) {
                    bs = bd.gftAddfssiblfStbtfSft();
                }
                if (bs == null) {
                    bs = nfw AddfssiblfStbtfSft();
                }
                Rfdtbnglf rjt = JTbblf.tiis.gftVisiblfRfdt();
                Rfdtbnglf rdfll = JTbblf.tiis.gftCfllRfdt(row, dolumn, fblsf);
                if (rjt.intfrsfdts(rdfll)) {
                    bs.bdd(AddfssiblfStbtf.SHOWING);
                } flsf {
                    if (bs.dontbins(AddfssiblfStbtf.SHOWING)) {
                         bs.rfmovf(AddfssiblfStbtf.SHOWING);
                    }
                }
                if (JTbblf.tiis.isCfllSflfdtfd(row, dolumn)) {
                    bs.bdd(AddfssiblfStbtf.SELECTED);
                } flsf if (bs.dontbins(AddfssiblfStbtf.SELECTED)) {
                    bs.rfmovf(AddfssiblfStbtf.SELECTED);
                }
                if ((row == gftSflfdtfdRow()) && (dolumn == gftSflfdtfdColumn())) {
                    bs.bdd(AddfssiblfStbtf.ACTIVE);
                }
                bs.bdd(AddfssiblfStbtf.TRANSIENT);
                rfturn bs;
            }

            /**
             * Gfts tif <dodf>Addfssiblf</dodf> pbrfnt of tiis objfdt.
             *
             * @rfturn tif Addfssiblf pbrfnt of tiis objfdt;
             *     <dodf>null</dodf> if tiis objfdt dofs not
             *     ibvf bn <dodf>Addfssiblf</dodf> pbrfnt
             */
            publid Addfssiblf gftAddfssiblfPbrfnt() {
                rfturn pbrfnt;
            }

            /**
             * Gfts tif indfx of tiis objfdt in its bddfssiblf pbrfnt.
             *
             * @rfturn tif indfx of tiis objfdt in its pbrfnt; -1 if tiis
             *     objfdt dofs not ibvf bn bddfssiblf pbrfnt
             * @sff #gftAddfssiblfPbrfnt
             */
            publid int gftAddfssiblfIndfxInPbrfnt() {
                rfturn dolumn;
            }

            /**
             * Rfturns tif numbfr of bddfssiblf diildrfn in tif objfdt.
             *
             * @rfturn tif numbfr of bddfssiblf diildrfn in tif objfdt
             */
            publid int gftAddfssiblfCiildrfnCount() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftAddfssiblfCiildrfnCount();
                } flsf {
                    rfturn 0;
                }
            }

            /**
             * Rfturns tif spfdififd <dodf>Addfssiblf</dodf> diild of tif
             * objfdt.
             *
             * @pbrbm i zfro-bbsfd indfx of diild
             * @rfturn tif <dodf>Addfssiblf</dodf> diild of tif objfdt
             */
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

            /**
             * Gfts tif lodblf of tif domponfnt. If tif domponfnt
             * dofs not ibvf b lodblf, tifn tif lodblf of its pbrfnt
             * is rfturnfd.
             *
             * @rfturn tiis domponfnt's lodblf; if tiis domponfnt dofs
             *    not ibvf b lodblf, tif lodblf of its pbrfnt is rfturnfd
             * @fxdfption IllfgblComponfntStbtfExdfption if tif
             *    <dodf>Componfnt</dodf> dofs not ibvf its own lodblf
             *    bnd ibs not yft bffn bddfd to b dontbinmfnt iifrbrdiy
             *    sudi tibt tif lodblf dbn bf dftfrminfd from tif
             *    dontbining pbrfnt
             * @sff #sftLodblf
             */
            publid Lodblf gftLodblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    rfturn bd.gftLodblf();
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Adds b <dodf>PropfrtyCibngfListfnfr</dodf> to tif listfnfr list.
             * Tif listfnfr is rfgistfrfd for bll propfrtifs.
             *
             * @pbrbm l  tif <dodf>PropfrtyCibngfListfnfr</dodf>
             *     to bf bddfd
             */
            publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.bddPropfrtyCibngfListfnfr(l);
                } flsf {
                    supfr.bddPropfrtyCibngfListfnfr(l);
                }
            }

            /**
             * Rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf> from tif
             * listfnfr list. Tiis rfmovfs b <dodf>PropfrtyCibngfListfnfr</dodf>
             * tibt wbs rfgistfrfd for bll propfrtifs.
             *
             * @pbrbm l  tif <dodf>PropfrtyCibngfListfnfr</dodf>
             *    to bf rfmovfd
             */
            publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr l) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd != null) {
                    bd.rfmovfPropfrtyCibngfListfnfr(l);
                } flsf {
                    supfr.rfmovfPropfrtyCibngfListfnfr(l);
                }
            }

            /**
             * Gfts tif <dodf>AddfssiblfAdtion</dodf> bssodibtfd witi tiis
             * objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfAdtion</dodf>, or <dodf>null</dodf>
             */
            publid AddfssiblfAdtion gftAddfssiblfAdtion() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfAdtion();
            }

            /**
             * Gfts tif <dodf>AddfssiblfComponfnt</dodf> bssodibtfd witi
             * tiis objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfComponfnt</dodf>, or
             *    <dodf>null</dodf>
             */
            publid AddfssiblfComponfnt gftAddfssiblfComponfnt() {
                rfturn tiis; // to ovfrridf gftBounds()
            }

            /**
             * Gfts tif <dodf>AddfssiblfSflfdtion</dodf> bssodibtfd witi
             * tiis objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfSflfdtion</dodf>, or
             *    <dodf>null</dodf>
             */
            publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfSflfdtion();
            }

            /**
             * Gfts tif <dodf>AddfssiblfTfxt</dodf> bssodibtfd witi tiis
             * objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfTfxt</dodf>, or <dodf>null</dodf>
             */
            publid AddfssiblfTfxt gftAddfssiblfTfxt() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfTfxt();
            }

            /**
             * Gfts tif <dodf>AddfssiblfVbluf</dodf> bssodibtfd witi
             * tiis objfdt if onf fxists.  Otifrwisf rfturns <dodf>null</dodf>.
             *
             * @rfturn tif <dodf>AddfssiblfVbluf</dodf>, or <dodf>null</dodf>
             */
            publid AddfssiblfVbluf gftAddfssiblfVbluf() {
                rfturn gftCurrfntAddfssiblfContfxt().gftAddfssiblfVbluf();
            }


            // AddfssiblfComponfnt mftiods ==========

            /**
             * Gfts tif bbdkground dolor of tiis objfdt.
             *
             * @rfturn tif bbdkground dolor, if supportfd, of tif objfdt;
             *     otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif bbdkground dolor of tiis objfdt.
             *
             * @pbrbm d tif nfw <dodf>Color</dodf> for tif bbdkground
             */
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

            /**
             * Gfts tif forfground dolor of tiis objfdt.
             *
             * @rfturn tif forfground dolor, if supportfd, of tif objfdt;
             *     otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif forfground dolor of tiis objfdt.
             *
             * @pbrbm d tif nfw <dodf>Color</dodf> for tif forfground
             */
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

            /**
             * Gfts tif <dodf>Cursor</dodf> of tiis objfdt.
             *
             * @rfturn tif <dodf>Cursor</dodf>, if supportfd,
             *    of tif objfdt; otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif <dodf>Cursor</dodf> of tiis objfdt.
             *
             * @pbrbm d tif nfw <dodf>Cursor</dodf> for tif objfdt
             */
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

            /**
             * Gfts tif <dodf>Font</dodf> of tiis objfdt.
             *
             * @rfturn tif <dodf>Font</dodf>,if supportfd,
             *   for tif objfdt; otifrwisf, <dodf>null</dodf>
             */
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

            /**
             * Sfts tif <dodf>Font</dodf> of tiis objfdt.
             *
             * @pbrbm f tif nfw <dodf>Font</dodf> for tif objfdt
             */
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

            /**
             * Gfts tif <dodf>FontMftrids</dodf> of tiis objfdt.
             *
             * @pbrbm f tif <dodf>Font</dodf>
             * @rfturn tif <dodf>FontMftrids</dodf> objfdt, if supportfd;
             *    otifrwisf <dodf>null</dodf>
             * @sff #gftFont
             */
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

            /**
             * Dftfrminfs if tif objfdt is fnbblfd.
             *
             * @rfturn truf if objfdt is fnbblfd; otifrwisf, fblsf
             */
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

            /**
             * Sfts tif fnbblfd stbtf of tif objfdt.
             *
             * @pbrbm b if truf, fnbblfs tiis objfdt; otifrwisf, disbblfs it
             */
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

            /**
             * Dftfrminfs if tiis objfdt is visiblf.  Notf: tiis mfbns tibt tif
             * objfdt intfnds to bf visiblf; iowfvfr, it mby not in fbdt bf
             * siowing on tif sdrffn bfdbusf onf of tif objfdts tibt tiis objfdt
             * is dontbinfd by is not visiblf.  To dftfrminf if bn objfdt is
             * siowing on tif sdrffn, usf <dodf>isSiowing</dodf>.
             *
             * @rfturn truf if objfdt is visiblf; otifrwisf, fblsf
             */
            publid boolfbn isVisiblf() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).isVisiblf();
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isVisiblf();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            /**
             * Sfts tif visiblf stbtf of tif objfdt.
             *
             * @pbrbm b if truf, siows tiis objfdt; otifrwisf, iidfs it
             */
            publid void sftVisiblf(boolfbn b) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftVisiblf(b);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftVisiblf(b);
                    }
                }
            }

            /**
             * Dftfrminfs if tif objfdt is siowing.  Tiis is dftfrminfd
             * by difdking tif visibility of tif objfdt bnd bndfstors
             * of tif objfdt.  Notf: tiis will rfturn truf fvfn if tif
             * objfdt is obsdurfd by bnotifr (for fxbmplf,
             * it ibppfns to bf undfrnfbti b mfnu tibt wbs pullfd down).
             *
             * @rfturn truf if tif objfdt is siowing; otifrwisf, fblsf
             */
            publid boolfbn isSiowing() {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    if (bd.gftAddfssiblfPbrfnt() != null) {
                        rfturn ((AddfssiblfComponfnt) bd).isSiowing();
                    } flsf {
                        // Fixfs 4529616 - AddfssiblfJTbblfCfll.isSiowing()
                        // rfturns fblsf wifn tif dfll on tif sdrffn
                        // if no pbrfnt
                        rfturn isVisiblf();
                    }
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        rfturn d.isSiowing();
                    } flsf {
                        rfturn fblsf;
                    }
                }
            }

            /**
             * Cifdks wiftifr tif spfdififd point is witiin tiis
             * objfdt's bounds, wifrf tif point's x bnd y doordinbtfs
             * brf dffinfd to bf rflbtivf to tif doordinbtf systfm of
             * tif objfdt.
             *
             * @pbrbm p tif <dodf>Point</dodf> rflbtivf to tif
             *    doordinbtf systfm of tif objfdt
             * @rfturn truf if objfdt dontbins <dodf>Point</dodf>;
             *    otifrwisf fblsf
             */
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

            /**
             * Rfturns tif lodbtion of tif objfdt on tif sdrffn.
             *
             * @rfturn lodbtion of objfdt on sdrffn -- dbn bf
             *    <dodf>null</dodf> if tiis objfdt is not on tif sdrffn
             */
            publid Point gftLodbtionOnSdrffn() {
                if (pbrfnt != null && pbrfnt.isSiowing()) {
                    Point pbrfntLodbtion = pbrfnt.gftLodbtionOnSdrffn();
                    Point domponfntLodbtion = gftLodbtion();
                    domponfntLodbtion.trbnslbtf(pbrfntLodbtion.x, pbrfntLodbtion.y);
                    rfturn domponfntLodbtion;
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Gfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt
             * in tif form of b point spfdifying tif objfdt's
             * top-lfft dornfr in tif sdrffn's doordinbtf spbdf.
             *
             * @rfturn bn instbndf of <dodf>Point</dodf> rfprfsfnting
             *    tif top-lfft dornfr of tif objfdt's bounds in tif
             *    doordinbtf spbdf of tif sdrffn; <dodf>null</dodf> if
             *    tiis objfdt or its pbrfnt brf not on tif sdrffn
             */
            publid Point gftLodbtion() {
                if (pbrfnt != null) {
                    Rfdtbnglf r = pbrfnt.gftHfbdfrRfdt(dolumn);
                    if (r != null) {
                        rfturn r.gftLodbtion();
                    }
                }
                rfturn null;
            }

            /**
             * Sfts tif lodbtion of tif objfdt rflbtivf to tif pbrfnt.
             * @pbrbm p tif nfw position for tif top-lfft dornfr
             * @sff #gftLodbtion
             */
            publid void sftLodbtion(Point p) {
            }

            /**
             * Gfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
             * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
             * rflbtivf to its pbrfnt.
             *
             * @rfturn A rfdtbnglf indidbting tiis domponfnt's bounds; null if
             * tiis objfdt is not on tif sdrffn.
             * @sff #dontbins
             */
            publid Rfdtbnglf gftBounds() {
                if (pbrfnt != null) {
                    rfturn pbrfnt.gftHfbdfrRfdt(dolumn);
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Sfts tif bounds of tiis objfdt in tif form of b Rfdtbnglf objfdt.
             * Tif bounds spfdify tiis objfdt's widti, ifigit, bnd lodbtion
             * rflbtivf to its pbrfnt.
             *
             * @pbrbm r rfdtbnglf indidbting tiis domponfnt's bounds
             * @sff #gftBounds
             */
            publid void sftBounds(Rfdtbnglf r) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    ((AddfssiblfComponfnt) bd).sftBounds(r);
                } flsf {
                    Componfnt d = gftCurrfntComponfnt();
                    if (d != null) {
                        d.sftBounds(r);
                    }
                }
            }

            /**
             * Rfturns tif sizf of tiis objfdt in tif form of b Dimfnsion objfdt.
             * Tif ifigit fifld of tif Dimfnsion objfdt dontbins tiis objfdt's
             * ifigit, bnd tif widti fifld of tif Dimfnsion objfdt dontbins tiis
             * objfdt's widti.
             *
             * @rfturn A Dimfnsion objfdt tibt indidbtfs tif sizf of tiis domponfnt;
             * null if tiis objfdt is not on tif sdrffn
             * @sff #sftSizf
             */
            publid Dimfnsion gftSizf() {
                if (pbrfnt != null) {
                    Rfdtbnglf r = pbrfnt.gftHfbdfrRfdt(dolumn);
                    if (r != null) {
                        rfturn r.gftSizf();
                    }
                }
                rfturn null;
            }

            /**
             * Rfsizfs tiis objfdt so tibt it ibs widti bnd ifigit.
             *
             * @pbrbm d Tif dimfnsion spfdifying tif nfw sizf of tif objfdt.
             * @sff #gftSizf
             */
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

            /**
             * Rfturns tif Addfssiblf diild, if onf fxists, dontbinfd bt tif lodbl
             * doordinbtf Point.
             *
             * @pbrbm p Tif point rflbtivf to tif doordinbtf systfm of tiis objfdt.
             * @rfturn tif Addfssiblf, if it fxists, bt tif spfdififd lodbtion;
             * otifrwisf null
             */
            publid Addfssiblf gftAddfssiblfAt(Point p) {
                AddfssiblfContfxt bd = gftCurrfntAddfssiblfContfxt();
                if (bd instbndfof AddfssiblfComponfnt) {
                    rfturn ((AddfssiblfComponfnt) bd).gftAddfssiblfAt(p);
                } flsf {
                    rfturn null;
                }
            }

            /**
             * Rfturns wiftifr tiis objfdt dbn bddfpt fodus or not.   Objfdts tibt
             * dbn bddfpt fodus will blso ibvf tif AddfssiblfStbtf.FOCUSABLE stbtf
             * sft in tifir AddfssiblfStbtfSfts.
             *
             * @rfturn truf if objfdt dbn bddfpt fodus; otifrwisf fblsf
             * @sff AddfssiblfContfxt#gftAddfssiblfStbtfSft
             * @sff AddfssiblfStbtf#FOCUSABLE
             * @sff AddfssiblfStbtf#FOCUSED
             * @sff AddfssiblfStbtfSft
             */
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

            /**
             * Rfqufsts fodus for tiis objfdt.  If tiis objfdt dbnnot bddfpt fodus,
             * notiing will ibppfn.  Otifrwisf, tif objfdt will bttfmpt to tbkf
             * fodus.
             * @sff #isFodusTrbvfrsbblf
             */
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

            /**
             * Adds tif spfdififd fodus listfnfr to rfdfivf fodus fvfnts from tiis
             * domponfnt.
             *
             * @pbrbm l tif fodus listfnfr
             * @sff #rfmovfFodusListfnfr
             */
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

            /**
             * Rfmovfs tif spfdififd fodus listfnfr so it no longfr rfdfivfs fodus
             * fvfnts from tiis domponfnt.
             *
             * @pbrbm l tif fodus listfnfr
             * @sff #bddFodusListfnfr
             */
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

        } // innfr dlbss AddfssiblfJTbblfHfbdfrCfll

    }  // innfr dlbss AddfssiblfJTbblf

}  // End of Clbss JTbblf
