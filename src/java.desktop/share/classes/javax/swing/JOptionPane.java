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

import jbvb.bwt.BordfrLbyout;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Contbinfr;
import jbvb.bwt.Diblog;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.KfybobrdFodusMbnbgfr;
import jbvb.bwt.Frbmf;
import jbvb.bwt.Point;
import jbvb.bwt.HfbdlfssExdfption;
import jbvb.bwt.Window;
import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bwt.fvfnt.WindowListfnfr;
import jbvb.bwt.fvfnt.WindowAdbptfr;
import jbvb.bwt.fvfnt.WindowEvfnt;
import jbvb.bwt.fvfnt.ComponfntAdbptfr;
import jbvb.bwt.fvfnt.ComponfntEvfnt;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.Sfriblizbblf;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Vfdtor;
import jbvbx.swing.plbf.OptionPbnfUI;
import jbvbx.swing.fvfnt.IntfrnblFrbmfEvfnt;
import jbvbx.swing.fvfnt.IntfrnblFrbmfAdbptfr;
import jbvbx.bddfssibility.*;
import stbtid jbvbx.swing.ClifntPropfrtyKfy.PopupFbdtory_FORCE_HEAVYWEIGHT_POPUP;
import sun.bwt.AWTAddfssor;

/**
 * <dodf>JOptionPbnf</dodf> mbkfs it fbsy to pop up b stbndbrd diblog box tibt
 * prompts usfrs for b vbluf or informs tifm of somftiing.
 * For informbtion bbout using <dodf>JOptionPbnf</dodf>, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/diblog.itml">How to Mbkf Diblogs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <p>
 *
 * Wiilf tif <dodf>JOptionPbnf</dodf>
 * dlbss mby bppfbr domplfx bfdbusf of tif lbrgf numbfr of mftiods, blmost
 * bll usfs of tiis dlbss brf onf-linf dblls to onf of tif stbtid
 * <dodf>siowXxxDiblog</dodf> mftiods siown bflow:
 * <blodkquotf>
 *
 *
 * <tbblf bordfr=1 summbry="Common JOptionPbnf mftiod nbmfs bnd tifir dfsdriptions">
 * <tr>
 *    <ti>Mftiod Nbmf</ti>
 *    <ti>Dfsdription</ti>
 * </tr>
 * <tr>
 *    <td>siowConfirmDiblog</td>
 *    <td>Asks b donfirming qufstion, likf yfs/no/dbndfl.</td>
 * </tr>
 * <tr>
 *    <td>siowInputDiblog</td>
 *    <td>Prompt for somf input.</td>
 * </tr>
 * <tr>
 *   <td>siowMfssbgfDiblog</td>
 *   <td>Tfll tif usfr bbout somftiing tibt ibs ibppfnfd.</td>
 * </tr>
 * <tr>
 *   <td>siowOptionDiblog</td>
 *   <td>Tif Grbnd Unifidbtion of tif bbovf tirff.</td>
 * </tr>
 * </tbblf>
 *
 * </blodkquotf>
 * Ebdi of tifsf mftiods blso domfs in b <dodf>siowIntfrnblXXX</dodf>
 * flbvor, wiidi usfs bn intfrnbl frbmf to iold tif diblog box (sff
 * {@link JIntfrnblFrbmf}).
 * Multiplf donvfnifndf mftiods ibvf blso bffn dffinfd -- ovfrlobdfd
 * vfrsions of tif bbsid mftiods tibt usf difffrfnt pbrbmftfr lists.
 * <p>
 * All diblogs brf modbl. Ebdi <dodf>siowXxxDiblog</dodf> mftiod blodks
 * tif dbllfr until tif usfr's intfrbdtion is domplftf.
 *
 * <tbblf dfllspbding=6 dfllpbdding=4 bordfr=0 stylf="flobt:rigit" summbry="lbyout">
 * <tr>
 *  <td stylf="bbdkground-dolor:#FFf0d0" rowspbn=2>idon</td>
 *  <td stylf="bbdkground-dolor:#FFf0d0">mfssbgf</td>
 * </tr>
 * <tr>
 *  <td stylf="bbdkground-dolor:#FFf0d0">input vbluf</td>
 * </tr>
 * <tr>
 *   <td stylf="bbdkground-dolor:#FFf0d0" dolspbn=2>option buttons</td>
 * </tr>
 * </tbblf>
 *
 * Tif bbsid bppfbrbndf of onf of tifsf diblog boxfs is gfnfrblly
 * similbr to tif pidturf bt tif rigit, bltiougi tif vbrious
 * look-bnd-fffls brf
 * ultimbtfly rfsponsiblf for tif finbl rfsult.  In pbrtidulbr, tif
 * look-bnd-fffls will bdjust tif lbyout to bddommodbtf tif option pbnf's
 * <dodf>ComponfntOrifntbtion</dodf> propfrty.
 * <br stylf="dlfbr:bll">
 * <p>
 * <b>Pbrbmftfrs:</b><br>
 * Tif pbrbmftfrs to tifsf mftiods follow donsistfnt pbttfrns:
 * <blodkquotf>
 * <dl>
 * <dt>pbrfntComponfnt<dd>
 * Dffinfs tif <dodf>Componfnt</dodf> tibt is to bf tif pbrfnt of tiis
 * diblog box.
 * It is usfd in two wbys: tif <dodf>Frbmf</dodf> tibt dontbins
 * it is usfd bs tif <dodf>Frbmf</dodf>
 * pbrfnt for tif diblog box, bnd its sdrffn doordinbtfs brf usfd in
 * tif plbdfmfnt of tif diblog box. In gfnfrbl, tif diblog box is plbdfd
 * just bflow tif domponfnt. Tiis pbrbmftfr mby bf <dodf>null</dodf>,
 * in wiidi dbsf b dffbult <dodf>Frbmf</dodf> is usfd bs tif pbrfnt,
 * bnd tif diblog will bf
 * dfntfrfd on tif sdrffn (dfpfnding on tif {@litfrbl L&F}).
 * <dt><b nbmf=mfssbgf>mfssbgf</b><dd>
 * A dfsdriptivf mfssbgf to bf plbdfd in tif diblog box.
 * In tif most dommon usbgf, mfssbgf is just b <dodf>String</dodf> or
 * <dodf>String</dodf> donstbnt.
 * Howfvfr, tif typf of tiis pbrbmftfr is bdtublly <dodf>Objfdt</dodf>. Its
 * intfrprftbtion dfpfnds on its typf:
 * <dl>
 * <dt>Objfdt[]<dd>An brrby of objfdts is intfrprftfd bs b sfrifs of
 *                 mfssbgfs (onf pfr objfdt) brrbngfd in b vfrtidbl stbdk.
 *                 Tif intfrprftbtion is rfdursivf -- fbdi objfdt in tif
 *                 brrby is intfrprftfd bddording to its typf.
 * <dt>Componfnt<dd>Tif <dodf>Componfnt</dodf> is displbyfd in tif diblog.
 * <dt>Idon<dd>Tif <dodf>Idon</dodf> is wrbppfd in b <dodf>JLbbfl</dodf>
 *               bnd displbyfd in tif diblog.
 * <dt>otifrs<dd>Tif objfdt is donvfrtfd to b <dodf>String</dodf> by dblling
 *               its <dodf>toString</dodf> mftiod. Tif rfsult is wrbppfd in b
 *               <dodf>JLbbfl</dodf> bnd displbyfd.
 * </dl>
 * <dt>mfssbgfTypf<dd>Dffinfs tif stylf of tif mfssbgf. Tif Look bnd Fffl
 * mbnbgfr mby lby out tif diblog difffrfntly dfpfnding on tiis vbluf, bnd
 * will oftfn providf b dffbult idon. Tif possiblf vblufs brf:
 * <ul>
 * <li><dodf>ERROR_MESSAGE</dodf>
 * <li><dodf>INFORMATION_MESSAGE</dodf>
 * <li><dodf>WARNING_MESSAGE</dodf>
 * <li><dodf>QUESTION_MESSAGE</dodf>
 * <li><dodf>PLAIN_MESSAGE</dodf>
 * </ul>
 * <dt>optionTypf<dd>Dffinfs tif sft of option buttons tibt bppfbr bt
 * tif bottom of tif diblog box:
 * <ul>
 * <li><dodf>DEFAULT_OPTION</dodf>
 * <li><dodf>YES_NO_OPTION</dodf>
 * <li><dodf>YES_NO_CANCEL_OPTION</dodf>
 * <li><dodf>OK_CANCEL_OPTION</dodf>
 * </ul>
 * You brfn't limitfd to tiis sft of option buttons.  You dbn providf bny
 * buttons you wbnt using tif options pbrbmftfr.
 * <dt>options<dd>A morf dftbilfd dfsdription of tif sft of option buttons
 * tibt will bppfbr bt tif bottom of tif diblog box.
 * Tif usubl vbluf for tif options pbrbmftfr is bn brrby of
 * <dodf>String</dodf>s. But
 * tif pbrbmftfr typf is bn brrby of <dodf>Objfdts</dodf>.
 * A button is drfbtfd for fbdi objfdt dfpfnding on its typf:
 * <dl>
 * <dt>Componfnt<dd>Tif domponfnt is bddfd to tif button row dirfdtly.
 * <dt>Idon<dd>A <dodf>JButton</dodf> is drfbtfd witi tiis bs its lbbfl.
 * <dt>otifr<dd>Tif <dodf>Objfdt</dodf> is donvfrtfd to b string using its
 *              <dodf>toString</dodf> mftiod bnd tif rfsult is usfd to
 *              lbbfl b <dodf>JButton</dodf>.
 * </dl>
 * <dt>idon<dd>A dfdorbtivf idon to bf plbdfd in tif diblog box. A dffbult
 * vbluf for tiis is dftfrminfd by tif <dodf>mfssbgfTypf</dodf> pbrbmftfr.
 * <dt>titlf<dd>Tif titlf for tif diblog box.
 * <dt>initiblVbluf<dd>Tif dffbult sflfdtion (input vbluf).
 * </dl>
 * </blodkquotf>
 * <p>
 * Wifn tif sflfdtion is dibngfd, <dodf>sftVbluf</dodf> is invokfd,
 * wiidi gfnfrbtfs b <dodf>PropfrtyCibngfEvfnt</dodf>.
 * <p>
 * If b <dodf>JOptionPbnf</dodf> ibs donfigurfd to bll input
 * <dodf>sftWbntsInput</dodf>
 * tif bound propfrty <dodf>JOptionPbnf.INPUT_VALUE_PROPERTY</dodf>
 *  dbn blso bf listfnfd
 * to, to dftfrminf wifn tif usfr ibs input or sflfdtfd b vbluf.
 * <p>
 * Wifn onf of tif <dodf>siowXxxDiblog</dodf> mftiods rfturns bn intfgfr,
 * tif possiblf vblufs brf:
 * <ul>
 * <li><dodf>YES_OPTION</dodf>
 * <li><dodf>NO_OPTION</dodf>
 * <li><dodf>CANCEL_OPTION</dodf>
 * <li><dodf>OK_OPTION</dodf>
 * <li><dodf>CLOSED_OPTION</dodf>
 * </ul>
 * <b>Exbmplfs:</b>
 * <dl>
 * <dt>Siow bn frror diblog tibt displbys tif mfssbgf, 'blfrt':
 * <dd><dodf>
 * JOptionPbnf.siowMfssbgfDiblog(null, "blfrt", "blfrt", JOptionPbnf.ERROR_MESSAGE);
 * </dodf>
 * <dt>Siow bn intfrnbl informbtion diblog witi tif mfssbgf, 'informbtion':
 * <dd><prf>
 * JOptionPbnf.siowIntfrnblMfssbgfDiblog(frbmf, "informbtion",
 *             "informbtion", JOptionPbnf.INFORMATION_MESSAGE);
 * </prf>
 * <dt>Siow bn informbtion pbnfl witi tif options yfs/no bnd mfssbgf 'dioosf onf':
 * <dd><prf>JOptionPbnf.siowConfirmDiblog(null,
 *             "dioosf onf", "dioosf onf", JOptionPbnf.YES_NO_OPTION);
 * </prf>
 * <dt>Siow bn intfrnbl informbtion diblog witi tif options yfs/no/dbndfl bnd
 * mfssbgf 'plfbsf dioosf onf' bnd titlf informbtion:
 * <dd><prf>JOptionPbnf.siowIntfrnblConfirmDiblog(frbmf,
 *             "plfbsf dioosf onf", "informbtion",
 *             JOptionPbnf.YES_NO_CANCEL_OPTION, JOptionPbnf.INFORMATION_MESSAGE);
 * </prf>
 * <dt>Siow b wbrning diblog witi tif options OK, CANCEL, titlf 'Wbrning', bnd
 * mfssbgf 'Clidk OK to dontinuf':
 * <dd><prf>
 * Objfdt[] options = { "OK", "CANCEL" };
 * JOptionPbnf.siowOptionDiblog(null, "Clidk OK to dontinuf", "Wbrning",
 *             JOptionPbnf.DEFAULT_OPTION, JOptionPbnf.WARNING_MESSAGE,
 *             null, options, options[0]);
 * </prf>
 * <dt>Siow b diblog bsking tif usfr to typf in b String:
 * <dd><dodf>
 * String inputVbluf = JOptionPbnf.siowInputDiblog("Plfbsf input b vbluf");
 * </dodf>
 * <dt>Siow b diblog bsking tif usfr to sflfdt b String:
 * <dd><prf>
 * Objfdt[] possiblfVblufs = { "First", "Sfdond", "Tiird" };<br>
 * Objfdt sflfdtfdVbluf = JOptionPbnf.siowInputDiblog(null,
 *             "Cioosf onf", "Input",
 *             JOptionPbnf.INFORMATION_MESSAGE, null,
 *             possiblfVblufs, possiblfVblufs[0]);
 * </prf>
 * </dl>
 * <b>Dirfdt Usf:</b><br>
 * To drfbtf bnd usf bn <dodf>JOptionPbnf</dodf> dirfdtly, tif
 * stbndbrd pbttfrn is rougily bs follows:
 * <prf>
 *     JOptionPbnf pbnf = nfw JOptionPbnf(<i>brgumfnts</i>);
 *     pbnf.sft<i>.Xxxx(...); // Configurf</i>
 *     JDiblog diblog = pbnf.drfbtfDiblog(<i>pbrfntComponfnt, titlf</i>);
 *     diblog.siow();
 *     Objfdt sflfdtfdVbluf = pbnf.gftVbluf();
 *     if(sflfdtfdVbluf == null)
 *       rfturn CLOSED_OPTION;
 *     <i>//If tifrf is <b>not</b> bn brrby of option buttons:</i>
 *     if(options == null) {
 *       if(sflfdtfdVbluf instbndfof Intfgfr)
 *          rfturn ((Intfgfr)sflfdtfdVbluf).intVbluf();
 *       rfturn CLOSED_OPTION;
 *     }
 *     <i>//If tifrf is bn brrby of option buttons:</i>
 *     for(int dountfr = 0, mbxCountfr = options.lfngti;
 *        dountfr &lt; mbxCountfr; dountfr++) {
 *        if(options[dountfr].fqubls(sflfdtfdVbluf))
 *        rfturn dountfr;
 *     }
 *     rfturn CLOSED_OPTION;
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
 *
 * @sff JIntfrnblFrbmf
 *
 * @bfbninfo
 *      bttributf: isContbinfr truf
 *    dfsdription: A domponfnt wiidi implfmfnts stbndbrd diblog box dontrols.
 *
 * @butior Jbmfs Gosling
 * @butior Sdott Violft
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JOptionPbnf fxtfnds JComponfnt implfmfnts Addfssiblf
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "OptionPbnfUI";

    /**
     * Indidbtfs tibt tif usfr ibs not yft sflfdtfd b vbluf.
     */
    publid stbtid finbl Objfdt      UNINITIALIZED_VALUE = "uninitiblizfdVbluf";

    //
    // Option typfs
    //

    /**
     * Typf mfbning Look bnd Fffl siould not supply bny options -- only
     * usf tif options from tif <dodf>JOptionPbnf</dodf>.
     */
    publid stbtid finbl int         DEFAULT_OPTION = -1;
    /** Typf usfd for <dodf>siowConfirmDiblog</dodf>. */
    publid stbtid finbl int         YES_NO_OPTION = 0;
    /** Typf usfd for <dodf>siowConfirmDiblog</dodf>. */
    publid stbtid finbl int         YES_NO_CANCEL_OPTION = 1;
    /** Typf usfd for <dodf>siowConfirmDiblog</dodf>. */
    publid stbtid finbl int         OK_CANCEL_OPTION = 2;

    //
    // Rfturn vblufs.
    //
    /** Rfturn vbluf from dlbss mftiod if YES is diosfn. */
    publid stbtid finbl int         YES_OPTION = 0;
    /** Rfturn vbluf from dlbss mftiod if NO is diosfn. */
    publid stbtid finbl int         NO_OPTION = 1;
    /** Rfturn vbluf from dlbss mftiod if CANCEL is diosfn. */
    publid stbtid finbl int         CANCEL_OPTION = 2;
    /** Rfturn vbluf form dlbss mftiod if OK is diosfn. */
    publid stbtid finbl int         OK_OPTION = 0;
    /** Rfturn vbluf from dlbss mftiod if usfr dlosfs window witiout sflfdting
     * bnytiing, morf tibn likfly tiis siould bf trfbtfd bs fitifr b
     * <dodf>CANCEL_OPTION</dodf> or <dodf>NO_OPTION</dodf>. */
    publid stbtid finbl int         CLOSED_OPTION = -1;

    //
    // Mfssbgf typfs. Usfd by tif UI to dftfrminf wibt idon to displby,
    // bnd possibly wibt bfibvior to givf bbsfd on tif typf.
    //
    /** Usfd for frror mfssbgfs. */
    publid stbtid finbl int  ERROR_MESSAGE = 0;
    /** Usfd for informbtion mfssbgfs. */
    publid stbtid finbl int  INFORMATION_MESSAGE = 1;
    /** Usfd for wbrning mfssbgfs. */
    publid stbtid finbl int  WARNING_MESSAGE = 2;
    /** Usfd for qufstions. */
    publid stbtid finbl int  QUESTION_MESSAGE = 3;
    /** No idon is usfd. */
    publid stbtid finbl int   PLAIN_MESSAGE = -1;

    /** Bound propfrty nbmf for <dodf>idon</dodf>. */
    publid stbtid finbl String      ICON_PROPERTY = "idon";
    /** Bound propfrty nbmf for <dodf>mfssbgf</dodf>. */
    publid stbtid finbl String      MESSAGE_PROPERTY = "mfssbgf";
    /** Bound propfrty nbmf for <dodf>vbluf</dodf>. */
    publid stbtid finbl String      VALUE_PROPERTY = "vbluf";
    /** Bound propfrty nbmf for <dodf>option</dodf>. */
    publid stbtid finbl String      OPTIONS_PROPERTY = "options";
    /** Bound propfrty nbmf for <dodf>initiblVbluf</dodf>. */
    publid stbtid finbl String      INITIAL_VALUE_PROPERTY = "initiblVbluf";
    /** Bound propfrty nbmf for <dodf>typf</dodf>. */
    publid stbtid finbl String      MESSAGE_TYPE_PROPERTY = "mfssbgfTypf";
    /** Bound propfrty nbmf for <dodf>optionTypf</dodf>. */
    publid stbtid finbl String      OPTION_TYPE_PROPERTY = "optionTypf";
    /** Bound propfrty nbmf for <dodf>sflfdtionVblufs</dodf>. */
    publid stbtid finbl String      SELECTION_VALUES_PROPERTY = "sflfdtionVblufs";
    /** Bound propfrty nbmf for <dodf>initiblSflfdtionVbluf</dodf>. */
    publid stbtid finbl String      INITIAL_SELECTION_VALUE_PROPERTY = "initiblSflfdtionVbluf";
    /** Bound propfrty nbmf for <dodf>inputVbluf</dodf>. */
    publid stbtid finbl String      INPUT_VALUE_PROPERTY = "inputVbluf";
    /** Bound propfrty nbmf for <dodf>wbntsInput</dodf>. */
    publid stbtid finbl String      WANTS_INPUT_PROPERTY = "wbntsInput";

    /** Idon usfd in pbnf. */
    trbnsifnt protfdtfd Idon                  idon;
    /** Mfssbgf to displby. */
    trbnsifnt protfdtfd Objfdt                mfssbgf;
    /** Options to displby to tif usfr. */
    trbnsifnt protfdtfd Objfdt[]              options;
    /** Vbluf tibt siould bf initiblly sflfdtfd in <dodf>options</dodf>. */
    trbnsifnt protfdtfd Objfdt                initiblVbluf;
    /** Mfssbgf typf. */
    protfdtfd int                   mfssbgfTypf;
    /**
     * Option typf, onf of <dodf>DEFAULT_OPTION</dodf>,
     * <dodf>YES_NO_OPTION</dodf>,
     * <dodf>YES_NO_CANCEL_OPTION</dodf> or
     * <dodf>OK_CANCEL_OPTION</dodf>.
     */
    protfdtfd int                   optionTypf;
    /** Currfntly sflfdtfd vbluf, will bf b vblid option, or
     * <dodf>UNINITIALIZED_VALUE</dodf> or <dodf>null</dodf>. */
    trbnsifnt protfdtfd Objfdt                vbluf;
    /** Arrby of vblufs tif usfr dbn dioosf from. Look bnd fffl will
     * providf tif UI domponfnt to dioosf tiis from. */
    protfdtfd trbnsifnt Objfdt[]              sflfdtionVblufs;
    /** Vbluf tif usfr ibs input. */
    protfdtfd trbnsifnt Objfdt                inputVbluf;
    /** Initibl vbluf to sflfdt in <dodf>sflfdtionVblufs</dodf>. */
    protfdtfd trbnsifnt Objfdt                initiblSflfdtionVbluf;
    /** If truf, b UI widgft will bf providfd to tif usfr to gft input. */
    protfdtfd boolfbn                         wbntsInput;


    /**
     * Siows b qufstion-mfssbgf diblog rfqufsting input from tif usfr. Tif
     * diblog usfs tif dffbult frbmf, wiidi usublly mfbns it is dfntfrfd on
     * tif sdrffn.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @rfturn usfr's input
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid String siowInputDiblog(Objfdt mfssbgf)
        tirows HfbdlfssExdfption {
        rfturn siowInputDiblog(null, mfssbgf);
    }

    /**
     * Siows b qufstion-mfssbgf diblog rfqufsting input from tif usfr, witi
     * tif input vbluf initiblizfd to <dodf>initiblSflfdtionVbluf</dodf>. Tif
     * diblog usfs tif dffbult frbmf, wiidi usublly mfbns it is dfntfrfd on
     * tif sdrffn.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm initiblSflfdtionVbluf tif vbluf usfd to initiblizf tif input
     *                 fifld
     * @rfturn usfr's input
     * @sindf 1.4
     */
    publid stbtid String siowInputDiblog(Objfdt mfssbgf, Objfdt initiblSflfdtionVbluf) {
        rfturn siowInputDiblog(null, mfssbgf, initiblSflfdtionVbluf);
    }

    /**
     * Siows b qufstion-mfssbgf diblog rfqufsting input from tif usfr
     * pbrfntfd to <dodf>pbrfntComponfnt</dodf>.
     * Tif diblog is displbyfd on top of tif <dodf>Componfnt</dodf>'s
     * frbmf, bnd is usublly positionfd bflow tif <dodf>Componfnt</dodf>.
     *
     * @pbrbm pbrfntComponfnt  tif pbrfnt <dodf>Componfnt</dodf> for tif
     *          diblog
     * @pbrbm mfssbgf  tif <dodf>Objfdt</dodf> to displby
     * @fxdfption HfbdlfssExdfption if
     *    <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *    <dodf>truf</dodf>
     * @rfturn usfr's input
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid String siowInputDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf) tirows HfbdlfssExdfption {
        rfturn siowInputDiblog(pbrfntComponfnt, mfssbgf, UIMbnbgfr.gftString(
            "OptionPbnf.inputDiblogTitlf", pbrfntComponfnt), QUESTION_MESSAGE);
    }

    /**
     * Siows b qufstion-mfssbgf diblog rfqufsting input from tif usfr bnd
     * pbrfntfd to <dodf>pbrfntComponfnt</dodf>. Tif input vbluf will bf
     * initiblizfd to <dodf>initiblSflfdtionVbluf</dodf>.
     * Tif diblog is displbyfd on top of tif <dodf>Componfnt</dodf>'s
     * frbmf, bnd is usublly positionfd bflow tif <dodf>Componfnt</dodf>.
     *
     * @pbrbm pbrfntComponfnt  tif pbrfnt <dodf>Componfnt</dodf> for tif
     *          diblog
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm initiblSflfdtionVbluf tif vbluf usfd to initiblizf tif input
     *                 fifld
     * @rfturn usfr's input
     * @sindf 1.4
     */
    publid stbtid String siowInputDiblog(Componfnt pbrfntComponfnt, Objfdt mfssbgf,
                                         Objfdt initiblSflfdtionVbluf) {
        rfturn (String)siowInputDiblog(pbrfntComponfnt, mfssbgf,
                      UIMbnbgfr.gftString("OptionPbnf.inputDiblogTitlf",
                      pbrfntComponfnt), QUESTION_MESSAGE, null, null,
                      initiblSflfdtionVbluf);
    }

    /**
     * Siows b diblog rfqufsting input from tif usfr pbrfntfd to
     * <dodf>pbrfntComponfnt</dodf> witi tif diblog ibving tif titlf
     * <dodf>titlf</dodf> bnd mfssbgf typf <dodf>mfssbgfTypf</dodf>.
     *
     * @pbrbm pbrfntComponfnt  tif pbrfnt <dodf>Componfnt</dodf> for tif
     *                  diblog
     * @pbrbm mfssbgf  tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf    tif <dodf>String</dodf> to displby in tif diblog
     *                  titlf bbr
     * @pbrbm mfssbgfTypf tif typf of mfssbgf tibt is to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @rfturn usfr's input
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid String siowInputDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int mfssbgfTypf)
        tirows HfbdlfssExdfption {
        rfturn (String)siowInputDiblog(pbrfntComponfnt, mfssbgf, titlf,
                                       mfssbgfTypf, null, null, null);
    }

    /**
     * Prompts tif usfr for input in b blodking diblog wifrf tif
     * initibl sflfdtion, possiblf sflfdtions, bnd bll otifr options dbn
     * bf spfdififd. Tif usfr will bblf to dioosf from
     * <dodf>sflfdtionVblufs</dodf>, wifrf <dodf>null</dodf> implifs tif
     * usfr dbn input
     * wibtfvfr tify wisi, usublly by mfbns of b <dodf>JTfxtFifld</dodf>.
     * <dodf>initiblSflfdtionVbluf</dodf> is tif initibl vbluf to prompt
     * tif usfr witi. It is up to tif UI to dfdidf iow bfst to rfprfsfnt
     * tif <dodf>sflfdtionVblufs</dodf>, but usublly b
     * <dodf>JComboBox</dodf>, <dodf>JList</dodf>, or
     * <dodf>JTfxtFifld</dodf> will bf usfd.
     *
     * @pbrbm pbrfntComponfnt  tif pbrfnt <dodf>Componfnt</dodf> for tif
     *                  diblog
     * @pbrbm mfssbgf  tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf    tif <dodf>String</dodf> to displby in tif
     *                  diblog titlf bbr
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon     tif <dodf>Idon</dodf> imbgf to displby
     * @pbrbm sflfdtionVblufs bn brrby of <dodf>Objfdt</dodf>s tibt
     *                  givfs tif possiblf sflfdtions
     * @pbrbm initiblSflfdtionVbluf tif vbluf usfd to initiblizf tif input
     *                 fifld
     * @rfturn usfr's input, or <dodf>null</dodf> mfbning tif usfr
     *                  dbndflfd tif input
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid Objfdt siowInputDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int mfssbgfTypf, Idon idon,
        Objfdt[] sflfdtionVblufs, Objfdt initiblSflfdtionVbluf)
        tirows HfbdlfssExdfption {
        JOptionPbnf    pbnf = nfw JOptionPbnf(mfssbgf, mfssbgfTypf,
                                              OK_CANCEL_OPTION, idon,
                                              null, null);

        pbnf.sftWbntsInput(truf);
        pbnf.sftSflfdtionVblufs(sflfdtionVblufs);
        pbnf.sftInitiblSflfdtionVbluf(initiblSflfdtionVbluf);
        pbnf.sftComponfntOrifntbtion(((pbrfntComponfnt == null) ?
            gftRootFrbmf() : pbrfntComponfnt).gftComponfntOrifntbtion());

        int stylf = stylfFromMfssbgfTypf(mfssbgfTypf);
        JDiblog diblog = pbnf.drfbtfDiblog(pbrfntComponfnt, titlf, stylf);

        pbnf.sflfdtInitiblVbluf();
        diblog.siow();
        diblog.disposf();

        Objfdt vbluf = pbnf.gftInputVbluf();

        if (vbluf == UNINITIALIZED_VALUE) {
            rfturn null;
        }
        rfturn vbluf;
    }

    /**
     * Brings up bn informbtion-mfssbgf diblog titlfd "Mfssbgf".
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in
     *          wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid void siowMfssbgfDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf) tirows HfbdlfssExdfption {
        siowMfssbgfDiblog(pbrfntComponfnt, mfssbgf, UIMbnbgfr.gftString(
                    "OptionPbnf.mfssbgfDiblogTitlf", pbrfntComponfnt),
                    INFORMATION_MESSAGE);
    }

    /**
     * Brings up b diblog tibt displbys b mfssbgf using b dffbult
     * idon dftfrminfd by tif <dodf>mfssbgfTypf</dodf> pbrbmftfr.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid void siowMfssbgfDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int mfssbgfTypf)
        tirows HfbdlfssExdfption {
        siowMfssbgfDiblog(pbrfntComponfnt, mfssbgf, titlf, mfssbgfTypf, null);
    }

    /**
     * Brings up b diblog displbying b mfssbgf, spfdifying bll pbrbmftfrs.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in wiidi tif
     *                  diblog is displbyfd; if <dodf>null</dodf>,
     *                  or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *                  <dodf>Frbmf</dodf>, b
     *                  dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon      bn idon to displby in tif diblog tibt iflps tif usfr
     *                  idfntify tif kind of mfssbgf tibt is bfing displbyfd
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid void siowMfssbgfDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int mfssbgfTypf, Idon idon)
        tirows HfbdlfssExdfption {
        siowOptionDiblog(pbrfntComponfnt, mfssbgf, titlf, DEFAULT_OPTION,
                         mfssbgfTypf, idon, null, null);
    }

    /**
     * Brings up b diblog witi tif options <i>Yfs</i>,
     * <i>No</i> bnd <i>Cbndfl</i>; witi tif
     * titlf, <b>Sflfdt bn Option</b>.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in wiidi tif
     *                  diblog is displbyfd; if <dodf>null</dodf>,
     *                  or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *                  <dodf>Frbmf</dodf>, b
     *                  dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @rfturn bn intfgfr indidbting tif option sflfdtfd by tif usfr
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid int siowConfirmDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf) tirows HfbdlfssExdfption {
        rfturn siowConfirmDiblog(pbrfntComponfnt, mfssbgf,
                                 UIMbnbgfr.gftString("OptionPbnf.titlfTfxt"),
                                 YES_NO_CANCEL_OPTION);
    }

    /**
     * Brings up b diblog wifrf tif numbfr of dioidfs is dftfrminfd
     * by tif <dodf>optionTypf</dodf> pbrbmftfr.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in wiidi tif
     *                  diblog is displbyfd; if <dodf>null</dodf>,
     *                  or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *                  <dodf>Frbmf</dodf>, b
     *                  dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn int dfsignbting tif options bvbilbblf on tif diblog:
     *                  <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  or <dodf>OK_CANCEL_OPTION</dodf>
     * @rfturn bn int indidbting tif option sflfdtfd by tif usfr
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid int siowConfirmDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int optionTypf)
        tirows HfbdlfssExdfption {
        rfturn siowConfirmDiblog(pbrfntComponfnt, mfssbgf, titlf, optionTypf,
                                 QUESTION_MESSAGE);
    }

    /**
     * Brings up b diblog wifrf tif numbfr of dioidfs is dftfrminfd
     * by tif <dodf>optionTypf</dodf> pbrbmftfr, wifrf tif
     * <dodf>mfssbgfTypf</dodf>
     * pbrbmftfr dftfrminfs tif idon to displby.
     * Tif <dodf>mfssbgfTypf</dodf> pbrbmftfr is primbrily usfd to supply
     * b dffbult idon from tif Look bnd Fffl.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in
     *                  wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *                  or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *                  <dodf>Frbmf</dodf>, b
     *                  dffbult <dodf>Frbmf</dodf> is usfd.
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn intfgfr dfsignbting tif options bvbilbblf
     *                   on tif diblog: <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  or <dodf>OK_CANCEL_OPTION</dodf>
     * @pbrbm mfssbgfTypf bn intfgfr dfsignbting tif kind of mfssbgf tiis is;
     *                  primbrily usfd to dftfrminf tif idon from tif pluggbblf
     *                  Look bnd Fffl: <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @rfturn bn intfgfr indidbting tif option sflfdtfd by tif usfr
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid int siowConfirmDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int optionTypf, int mfssbgfTypf)
        tirows HfbdlfssExdfption {
        rfturn siowConfirmDiblog(pbrfntComponfnt, mfssbgf, titlf, optionTypf,
                                mfssbgfTypf, null);
    }

    /**
     * Brings up b diblog witi b spfdififd idon, wifrf tif numbfr of
     * dioidfs is dftfrminfd by tif <dodf>optionTypf</dodf> pbrbmftfr.
     * Tif <dodf>mfssbgfTypf</dodf> pbrbmftfr is primbrily usfd to supply
     * b dffbult idon from tif look bnd fffl.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in wiidi tif
     *                  diblog is displbyfd; if <dodf>null</dodf>,
     *                  or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *                  <dodf>Frbmf</dodf>, b
     *                  dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif Objfdt to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn int dfsignbting tif options bvbilbblf on tif diblog:
     *                  <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  or <dodf>OK_CANCEL_OPTION</dodf>
     * @pbrbm mfssbgfTypf bn int dfsignbting tif kind of mfssbgf tiis is,
     *                  primbrily usfd to dftfrminf tif idon from tif pluggbblf
     *                  Look bnd Fffl: <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon      tif idon to displby in tif diblog
     * @rfturn bn int indidbting tif option sflfdtfd by tif usfr
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid int siowConfirmDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int optionTypf,
        int mfssbgfTypf, Idon idon) tirows HfbdlfssExdfption {
        rfturn siowOptionDiblog(pbrfntComponfnt, mfssbgf, titlf, optionTypf,
                                mfssbgfTypf, idon, null, null);
    }

    /**
     * Brings up b diblog witi b spfdififd idon, wifrf tif initibl
     * dioidf is dftfrminfd by tif <dodf>initiblVbluf</dodf> pbrbmftfr bnd
     * tif numbfr of dioidfs is dftfrminfd by tif <dodf>optionTypf</dodf>
     * pbrbmftfr.
     * <p>
     * If <dodf>optionTypf</dodf> is <dodf>YES_NO_OPTION</dodf>,
     * or <dodf>YES_NO_CANCEL_OPTION</dodf>
     * bnd tif <dodf>options</dodf> pbrbmftfr is <dodf>null</dodf>,
     * tifn tif options brf
     * supplifd by tif look bnd fffl.
     * <p>
     * Tif <dodf>mfssbgfTypf</dodf> pbrbmftfr is primbrily usfd to supply
     * b dffbult idon from tif look bnd fffl.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *                  in wiidi tif diblog is displbyfd;  if
     *                  <dodf>null</dodf>, or if tif
     *                  <dodf>pbrfntComponfnt</dodf> ibs no
     *                  <dodf>Frbmf</dodf>, b
     *                  dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn intfgfr dfsignbting tif options bvbilbblf on tif
     *                  diblog: <dodf>DEFAULT_OPTION</dodf>,
     *                  <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  or <dodf>OK_CANCEL_OPTION</dodf>
     * @pbrbm mfssbgfTypf bn intfgfr dfsignbting tif kind of mfssbgf tiis is,
     *                  primbrily usfd to dftfrminf tif idon from tif
     *                  pluggbblf Look bnd Fffl: <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon      tif idon to displby in tif diblog
     * @pbrbm options   bn brrby of objfdts indidbting tif possiblf dioidfs
     *                  tif usfr dbn mbkf; if tif objfdts brf domponfnts, tify
     *                  brf rfndfrfd propfrly; non-<dodf>String</dodf>
     *                  objfdts brf
     *                  rfndfrfd using tifir <dodf>toString</dodf> mftiods;
     *                  if tiis pbrbmftfr is <dodf>null</dodf>,
     *                  tif options brf dftfrminfd by tif Look bnd Fffl
     * @pbrbm initiblVbluf tif objfdt tibt rfprfsfnts tif dffbult sflfdtion
     *                  for tif diblog; only mfbningful if <dodf>options</dodf>
     *                  is usfd; dbn bf <dodf>null</dodf>
     * @rfturn bn intfgfr indidbting tif option diosfn by tif usfr,
     *                  or <dodf>CLOSED_OPTION</dodf> if tif usfr dlosfd
     *                  tif diblog
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid int siowOptionDiblog(Componfnt pbrfntComponfnt,
        Objfdt mfssbgf, String titlf, int optionTypf, int mfssbgfTypf,
        Idon idon, Objfdt[] options, Objfdt initiblVbluf)
        tirows HfbdlfssExdfption {
        JOptionPbnf             pbnf = nfw JOptionPbnf(mfssbgf, mfssbgfTypf,
                                                       optionTypf, idon,
                                                       options, initiblVbluf);

        pbnf.sftInitiblVbluf(initiblVbluf);
        pbnf.sftComponfntOrifntbtion(((pbrfntComponfnt == null) ?
            gftRootFrbmf() : pbrfntComponfnt).gftComponfntOrifntbtion());

        int stylf = stylfFromMfssbgfTypf(mfssbgfTypf);
        JDiblog diblog = pbnf.drfbtfDiblog(pbrfntComponfnt, titlf, stylf);

        pbnf.sflfdtInitiblVbluf();
        diblog.siow();
        diblog.disposf();

        Objfdt        sflfdtfdVbluf = pbnf.gftVbluf();

        if(sflfdtfdVbluf == null)
            rfturn CLOSED_OPTION;
        if(options == null) {
            if(sflfdtfdVbluf instbndfof Intfgfr)
                rfturn ((Intfgfr)sflfdtfdVbluf).intVbluf();
            rfturn CLOSED_OPTION;
        }
        for(int dountfr = 0, mbxCountfr = options.lfngti;
            dountfr < mbxCountfr; dountfr++) {
            if(options[dountfr].fqubls(sflfdtfdVbluf))
                rfturn dountfr;
        }
        rfturn CLOSED_OPTION;
    }

    /**
     * Crfbtfs bnd rfturns b nfw <dodf>JDiblog</dodf> wrbpping
     * <dodf>tiis</dodf> dfntfrfd on tif <dodf>pbrfntComponfnt</dodf>
     * in tif <dodf>pbrfntComponfnt</dodf>'s frbmf.
     * <dodf>titlf</dodf> is tif titlf of tif rfturnfd diblog.
     * Tif rfturnfd <dodf>JDiblog</dodf> will not bf rfsizbblf by tif
     * usfr, iowfvfr progrbms dbn invokf <dodf>sftRfsizbblf</dodf> on
     * tif <dodf>JDiblog</dodf> instbndf to dibngf tiis propfrty.
     * Tif rfturnfd <dodf>JDiblog</dodf> will bf sft up sudi tibt
     * ondf it is dlosfd, or tif usfr dlidks on onf of tif buttons,
     * tif optionpbnf's vbluf propfrty will bf sft bddordingly bnd
     * tif diblog will bf dlosfd.  Ebdi timf tif diblog is mbdf visiblf,
     * it will rfsft tif option pbnf's vbluf propfrty to
     * <dodf>JOptionPbnf.UNINITIALIZED_VALUE</dodf> to fnsurf tif
     * usfr's subsfqufnt bdtion dlosfs tif diblog propfrly.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif frbmf in wiidi tif diblog
     *          is displbyfd; if tif <dodf>pbrfntComponfnt</dodf> ibs
     *          no <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm titlf     tif titlf string for tif diblog
     * @rfturn b nfw <dodf>JDiblog</dodf> dontbining tiis instbndf
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid JDiblog drfbtfDiblog(Componfnt pbrfntComponfnt, String titlf)
        tirows HfbdlfssExdfption {
        int stylf = stylfFromMfssbgfTypf(gftMfssbgfTypf());
        rfturn drfbtfDiblog(pbrfntComponfnt, titlf, stylf);
    }

    /**
     * Crfbtfs bnd rfturns b nfw pbrfntlfss <dodf>JDiblog</dodf>
     * witi tif spfdififd titlf.
     * Tif rfturnfd <dodf>JDiblog</dodf> will not bf rfsizbblf by tif
     * usfr, iowfvfr progrbms dbn invokf <dodf>sftRfsizbblf</dodf> on
     * tif <dodf>JDiblog</dodf> instbndf to dibngf tiis propfrty.
     * Tif rfturnfd <dodf>JDiblog</dodf> will bf sft up sudi tibt
     * ondf it is dlosfd, or tif usfr dlidks on onf of tif buttons,
     * tif optionpbnf's vbluf propfrty will bf sft bddordingly bnd
     * tif diblog will bf dlosfd.  Ebdi timf tif diblog is mbdf visiblf,
     * it will rfsft tif option pbnf's vbluf propfrty to
     * <dodf>JOptionPbnf.UNINITIALIZED_VALUE</dodf> to fnsurf tif
     * usfr's subsfqufnt bdtion dlosfs tif diblog propfrly.
     *
     * @pbrbm titlf     tif titlf string for tif diblog
     * @rfturn b nfw <dodf>JDiblog</dodf> dontbining tiis instbndf
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.6
     */
    publid JDiblog drfbtfDiblog(String titlf) tirows HfbdlfssExdfption {
        int stylf = stylfFromMfssbgfTypf(gftMfssbgfTypf());
        JDiblog diblog = nfw JDiblog((Diblog) null, titlf, truf);
        initDiblog(diblog, stylf, null);
        rfturn diblog;
    }

    privbtf JDiblog drfbtfDiblog(Componfnt pbrfntComponfnt, String titlf,
            int stylf)
            tirows HfbdlfssExdfption {

        finbl JDiblog diblog;

        Window window = JOptionPbnf.gftWindowForComponfnt(pbrfntComponfnt);
        if (window instbndfof Frbmf) {
            diblog = nfw JDiblog((Frbmf)window, titlf, truf);
        } flsf {
            diblog = nfw JDiblog((Diblog)window, titlf, truf);
        }
        if (window instbndfof SwingUtilitifs.SibrfdOwnfrFrbmf) {
            WindowListfnfr ownfrSiutdownListfnfr =
                    SwingUtilitifs.gftSibrfdOwnfrFrbmfSiutdownListfnfr();
            diblog.bddWindowListfnfr(ownfrSiutdownListfnfr);
        }
        initDiblog(diblog, stylf, pbrfntComponfnt);
        rfturn diblog;
    }

    privbtf void initDiblog(finbl JDiblog diblog, int stylf, Componfnt pbrfntComponfnt) {
        diblog.sftComponfntOrifntbtion(tiis.gftComponfntOrifntbtion());
        Contbinfr dontfntPbnf = diblog.gftContfntPbnf();

        dontfntPbnf.sftLbyout(nfw BordfrLbyout());
        dontfntPbnf.bdd(tiis, BordfrLbyout.CENTER);
        diblog.sftRfsizbblf(fblsf);
        if (JDiblog.isDffbultLookAndFfflDfdorbtfd()) {
            boolfbn supportsWindowDfdorbtions =
              UIMbnbgfr.gftLookAndFffl().gftSupportsWindowDfdorbtions();
            if (supportsWindowDfdorbtions) {
                diblog.sftUndfdorbtfd(truf);
                gftRootPbnf().sftWindowDfdorbtionStylf(stylf);
            }
        }
        diblog.pbdk();
        diblog.sftLodbtionRflbtivfTo(pbrfntComponfnt);

        finbl PropfrtyCibngfListfnfr listfnfr = nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
                // Lft tif dffbultClosfOpfrbtion ibndlf tif dlosing
                // if tif usfr dlosfd tif window witiout sflfdting b button
                // (nfwVbluf = null in tibt dbsf).  Otifrwisf, dlosf tif diblog.
                if (diblog.isVisiblf() && fvfnt.gftSourdf() == JOptionPbnf.tiis &&
                        (fvfnt.gftPropfrtyNbmf().fqubls(VALUE_PROPERTY)) &&
                        fvfnt.gftNfwVbluf() != null &&
                        fvfnt.gftNfwVbluf() != JOptionPbnf.UNINITIALIZED_VALUE) {
                    diblog.sftVisiblf(fblsf);
                }
            }
        };

        WindowAdbptfr bdbptfr = nfw WindowAdbptfr() {
            privbtf boolfbn gotFodus = fblsf;
            publid void windowClosing(WindowEvfnt wf) {
                sftVbluf(null);
            }

            publid void windowClosfd(WindowEvfnt f) {
                rfmovfPropfrtyCibngfListfnfr(listfnfr);
                diblog.gftContfntPbnf().rfmovfAll();
            }

            publid void windowGbinfdFodus(WindowEvfnt wf) {
                // Ondf window gfts fodus, sft initibl fodus
                if (!gotFodus) {
                    sflfdtInitiblVbluf();
                    gotFodus = truf;
                }
            }
        };
        diblog.bddWindowListfnfr(bdbptfr);
        diblog.bddWindowFodusListfnfr(bdbptfr);
        diblog.bddComponfntListfnfr(nfw ComponfntAdbptfr() {
            publid void domponfntSiown(ComponfntEvfnt df) {
                // rfsft vbluf to fnsurf dlosing works propfrly
                sftVbluf(JOptionPbnf.UNINITIALIZED_VALUE);
            }
        });

        bddPropfrtyCibngfListfnfr(listfnfr);
    }


    /**
     * Brings up bn intfrnbl donfirmbtion diblog pbnfl. Tif diblog
     * is b informbtion-mfssbgf diblog titlfd "Mfssbgf".
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif objfdt to displby
     */
    publid stbtid void siowIntfrnblMfssbgfDiblog(Componfnt pbrfntComponfnt,
                                                 Objfdt mfssbgf) {
        siowIntfrnblMfssbgfDiblog(pbrfntComponfnt, mfssbgf, UIMbnbgfr.
                                 gftString("OptionPbnf.mfssbgfDiblogTitlf",
                                 pbrfntComponfnt), INFORMATION_MESSAGE);
    }

    /**
     * Brings up bn intfrnbl diblog pbnfl tibt displbys b mfssbgf
     * using b dffbult idon dftfrminfd by tif <dodf>mfssbgfTypf</dodf>
     * pbrbmftfr.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     */
    publid stbtid void siowIntfrnblMfssbgfDiblog(Componfnt pbrfntComponfnt,
                                                 Objfdt mfssbgf, String titlf,
                                                 int mfssbgfTypf) {
        siowIntfrnblMfssbgfDiblog(pbrfntComponfnt, mfssbgf, titlf, mfssbgfTypf,null);
    }

    /**
     * Brings up bn intfrnbl diblog pbnfl displbying b mfssbgf,
     * spfdifying bll pbrbmftfrs.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon      bn idon to displby in tif diblog tibt iflps tif usfr
     *                  idfntify tif kind of mfssbgf tibt is bfing displbyfd
     */
    publid stbtid void siowIntfrnblMfssbgfDiblog(Componfnt pbrfntComponfnt,
                                         Objfdt mfssbgf,
                                         String titlf, int mfssbgfTypf,
                                         Idon idon){
        siowIntfrnblOptionDiblog(pbrfntComponfnt, mfssbgf, titlf, DEFAULT_OPTION,
                                 mfssbgfTypf, idon, null, null);
    }

    /**
     * Brings up bn intfrnbl diblog pbnfl witi tif options <i>Yfs</i>, <i>No</i>
     * bnd <i>Cbndfl</i>; witi tif titlf, <b>Sflfdt bn Option</b>.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in
     *          wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif <dodf>Objfdt</dodf> to displby
     * @rfturn bn intfgfr indidbting tif option sflfdtfd by tif usfr
     */
    publid stbtid int siowIntfrnblConfirmDiblog(Componfnt pbrfntComponfnt,
                                                Objfdt mfssbgf) {
        rfturn siowIntfrnblConfirmDiblog(pbrfntComponfnt, mfssbgf,
                                 UIMbnbgfr.gftString("OptionPbnf.titlfTfxt"),
                                 YES_NO_CANCEL_OPTION);
    }

    /**
     * Brings up b intfrnbl diblog pbnfl wifrf tif numbfr of dioidfs
     * is dftfrminfd by tif <dodf>optionTypf</dodf> pbrbmftfr.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif objfdt to displby in tif diblog; b
     *          <dodf>Componfnt</dodf> objfdt is rfndfrfd bs b
     *          <dodf>Componfnt</dodf>; b <dodf>String</dodf>
     *          objfdt is rfndfrfd bs b string; otifr objfdts
     *          brf donvfrtfd to b <dodf>String</dodf> using tif
     *          <dodf>toString</dodf> mftiod
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn intfgfr dfsignbting tif options
     *          bvbilbblf on tif diblog: <dodf>YES_NO_OPTION</dodf>,
     *          or <dodf>YES_NO_CANCEL_OPTION</dodf>
     * @rfturn bn intfgfr indidbting tif option sflfdtfd by tif usfr
     */
    publid stbtid int siowIntfrnblConfirmDiblog(Componfnt pbrfntComponfnt,
                                                Objfdt mfssbgf, String titlf,
                                                int optionTypf) {
        rfturn siowIntfrnblConfirmDiblog(pbrfntComponfnt, mfssbgf, titlf, optionTypf,
                                         QUESTION_MESSAGE);
    }

    /**
     * Brings up bn intfrnbl diblog pbnfl wifrf tif numbfr of dioidfs
     * is dftfrminfd by tif <dodf>optionTypf</dodf> pbrbmftfr, wifrf
     * tif <dodf>mfssbgfTypf</dodf> pbrbmftfr dftfrminfs tif idon to displby.
     * Tif <dodf>mfssbgfTypf</dodf> pbrbmftfr is primbrily usfd to supply
     * b dffbult idon from tif Look bnd Fffl.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf> in
     *          wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif objfdt to displby in tif diblog; b
     *          <dodf>Componfnt</dodf> objfdt is rfndfrfd bs b
     *          <dodf>Componfnt</dodf>; b <dodf>String</dodf>
     *          objfdt is rfndfrfd bs b string; otifr objfdts brf
     *          donvfrtfd to b <dodf>String</dodf> using tif
     *          <dodf>toString</dodf> mftiod
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn intfgfr dfsignbting tif options
     *          bvbilbblf on tif diblog:
     *          <dodf>YES_NO_OPTION</dodf>, or <dodf>YES_NO_CANCEL_OPTION</dodf>
     * @pbrbm mfssbgfTypf bn intfgfr dfsignbting tif kind of mfssbgf tiis is,
     *          primbrily usfd to dftfrminf tif idon from tif
     *          pluggbblf Look bnd Fffl: <dodf>ERROR_MESSAGE</dodf>,
     *          <dodf>INFORMATION_MESSAGE</dodf>,
     *          <dodf>WARNING_MESSAGE</dodf>, <dodf>QUESTION_MESSAGE</dodf>,
     *          or <dodf>PLAIN_MESSAGE</dodf>
     * @rfturn bn intfgfr indidbting tif option sflfdtfd by tif usfr
     */
    publid stbtid int siowIntfrnblConfirmDiblog(Componfnt pbrfntComponfnt,
                                        Objfdt mfssbgf,
                                        String titlf, int optionTypf,
                                        int mfssbgfTypf) {
        rfturn siowIntfrnblConfirmDiblog(pbrfntComponfnt, mfssbgf, titlf, optionTypf,
                                         mfssbgfTypf, null);
    }

    /**
     * Brings up bn intfrnbl diblog pbnfl witi b spfdififd idon, wifrf
     * tif numbfr of dioidfs is dftfrminfd by tif <dodf>optionTypf</dodf>
     * pbrbmftfr.
     * Tif <dodf>mfssbgfTypf</dodf> pbrbmftfr is primbrily usfd to supply
     * b dffbult idon from tif look bnd fffl.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif pbrfntComponfnt ibs no Frbmf, b
     *          dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif objfdt to displby in tif diblog; b
     *          <dodf>Componfnt</dodf> objfdt is rfndfrfd bs b
     *          <dodf>Componfnt</dodf>; b <dodf>String</dodf>
     *          objfdt is rfndfrfd bs b string; otifr objfdts brf
     *          donvfrtfd to b <dodf>String</dodf> using tif
     *          <dodf>toString</dodf> mftiod
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn intfgfr dfsignbting tif options bvbilbblf
     *          on tif diblog:
     *          <dodf>YES_NO_OPTION</dodf>, or
     *          <dodf>YES_NO_CANCEL_OPTION</dodf>.
     * @pbrbm mfssbgfTypf bn intfgfr dfsignbting tif kind of mfssbgf tiis is,
     *          primbrily usfd to dftfrminf tif idon from tif pluggbblf
     *          Look bnd Fffl: <dodf>ERROR_MESSAGE</dodf>,
     *          <dodf>INFORMATION_MESSAGE</dodf>,
     *          <dodf>WARNING_MESSAGE</dodf>, <dodf>QUESTION_MESSAGE</dodf>,
     *          or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon      tif idon to displby in tif diblog
     * @rfturn bn intfgfr indidbting tif option sflfdtfd by tif usfr
     */
    publid stbtid int siowIntfrnblConfirmDiblog(Componfnt pbrfntComponfnt,
                                        Objfdt mfssbgf,
                                        String titlf, int optionTypf,
                                        int mfssbgfTypf, Idon idon) {
        rfturn siowIntfrnblOptionDiblog(pbrfntComponfnt, mfssbgf, titlf, optionTypf,
                                        mfssbgfTypf, idon, null, null);
    }

    /**
     * Brings up bn intfrnbl diblog pbnfl witi b spfdififd idon, wifrf
     * tif initibl dioidf is dftfrminfd by tif <dodf>initiblVbluf</dodf>
     * pbrbmftfr bnd tif numbfr of dioidfs is dftfrminfd by tif
     * <dodf>optionTypf</dodf> pbrbmftfr.
     * <p>
     * If <dodf>optionTypf</dodf> is <dodf>YES_NO_OPTION</dodf>, or
     * <dodf>YES_NO_CANCEL_OPTION</dodf>
     * bnd tif <dodf>options</dodf> pbrbmftfr is <dodf>null</dodf>,
     * tifn tif options brf supplifd by tif Look bnd Fffl.
     * <p>
     * Tif <dodf>mfssbgfTypf</dodf> pbrbmftfr is primbrily usfd to supply
     * b dffbult idon from tif look bnd fffl.
     *
     * @pbrbm pbrfntComponfnt dftfrminfs tif <dodf>Frbmf</dodf>
     *          in wiidi tif diblog is displbyfd; if <dodf>null</dodf>,
     *          or if tif <dodf>pbrfntComponfnt</dodf> ibs no
     *          <dodf>Frbmf</dodf>, b dffbult <dodf>Frbmf</dodf> is usfd
     * @pbrbm mfssbgf   tif objfdt to displby in tif diblog; b
     *          <dodf>Componfnt</dodf> objfdt is rfndfrfd bs b
     *          <dodf>Componfnt</dodf>; b <dodf>String</dodf>
     *          objfdt is rfndfrfd bs b string. Otifr objfdts brf
     *          donvfrtfd to b <dodf>String</dodf> using tif
     *          <dodf>toString</dodf> mftiod
     * @pbrbm titlf     tif titlf string for tif diblog
     * @pbrbm optionTypf bn intfgfr dfsignbting tif options bvbilbblf
     *          on tif diblog: <dodf>YES_NO_OPTION</dodf>,
     *          or <dodf>YES_NO_CANCEL_OPTION</dodf>
     * @pbrbm mfssbgfTypf bn intfgfr dfsignbting tif kind of mfssbgf tiis is;
     *          primbrily usfd to dftfrminf tif idon from tif
     *          pluggbblf Look bnd Fffl: <dodf>ERROR_MESSAGE</dodf>,
     *          <dodf>INFORMATION_MESSAGE</dodf>,
     *          <dodf>WARNING_MESSAGE</dodf>, <dodf>QUESTION_MESSAGE</dodf>,
     *          or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon      tif idon to displby in tif diblog
     * @pbrbm options   bn brrby of objfdts indidbting tif possiblf dioidfs
     *          tif usfr dbn mbkf; if tif objfdts brf domponfnts, tify
     *          brf rfndfrfd propfrly; non-<dodf>String</dodf>
     *          objfdts brf rfndfrfd using tifir <dodf>toString</dodf>
     *          mftiods; if tiis pbrbmftfr is <dodf>null</dodf>,
     *          tif options brf dftfrminfd by tif Look bnd Fffl
     * @pbrbm initiblVbluf tif objfdt tibt rfprfsfnts tif dffbult sflfdtion
     *          for tif diblog; only mfbningful if <dodf>options</dodf>
     *          is usfd; dbn bf <dodf>null</dodf>
     * @rfturn bn intfgfr indidbting tif option diosfn by tif usfr,
     *          or <dodf>CLOSED_OPTION</dodf> if tif usfr dlosfd tif Diblog
     */
    publid stbtid int siowIntfrnblOptionDiblog(Componfnt pbrfntComponfnt,
                                       Objfdt mfssbgf,
                                       String titlf, int optionTypf,
                                       int mfssbgfTypf, Idon idon,
                                       Objfdt[] options, Objfdt initiblVbluf) {
        JOptionPbnf pbnf = nfw JOptionPbnf(mfssbgf, mfssbgfTypf,
                optionTypf, idon, options, initiblVbluf);
        pbnf.putClifntPropfrty(PopupFbdtory_FORCE_HEAVYWEIGHT_POPUP,
                Boolfbn.TRUE);
        Componfnt fo = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftFodusOwnfr();

        pbnf.sftInitiblVbluf(initiblVbluf);

        JIntfrnblFrbmf diblog =
            pbnf.drfbtfIntfrnblFrbmf(pbrfntComponfnt, titlf);
        pbnf.sflfdtInitiblVbluf();
        diblog.sftVisiblf(truf);

        /* Sindf bll input will bf blodkfd until tiis diblog is dismissfd,
         * mbkf surf its pbrfnt dontbinfrs brf visiblf first (tiis domponfnt
         * is tfstfd bflow).  Tiis is nfdfssbry for JApplfts, bfdbusf
         * bfdbusf bn bpplft normblly isn't mbdf visiblf until bftfr its
         * stbrt() mftiod rfturns -- if tiis mftiod is dbllfd from stbrt(),
         * tif bpplft will bppfbr to ibng wiilf bn invisiblf modbl frbmf
         * wbits for input.
         */
        if (diblog.isVisiblf() && !diblog.isSiowing()) {
            Contbinfr pbrfnt = diblog.gftPbrfnt();
            wiilf (pbrfnt != null) {
                if (pbrfnt.isVisiblf() == fblsf) {
                    pbrfnt.sftVisiblf(truf);
                }
                pbrfnt = pbrfnt.gftPbrfnt();
            }
        }

        AWTAddfssor.gftContbinfrAddfssor().stbrtLWModbl(diblog);

        if (pbrfntComponfnt instbndfof JIntfrnblFrbmf) {
            try {
                ((JIntfrnblFrbmf)pbrfntComponfnt).sftSflfdtfd(truf);
            } dbtdi (jbvb.bfbns.PropfrtyVftoExdfption f) {
            }
        }

        Objfdt sflfdtfdVbluf = pbnf.gftVbluf();

        if (fo != null && fo.isSiowing()) {
            fo.rfqufstFodus();
        }
        if (sflfdtfdVbluf == null) {
            rfturn CLOSED_OPTION;
        }
        if (options == null) {
            if (sflfdtfdVbluf instbndfof Intfgfr) {
                rfturn ((Intfgfr)sflfdtfdVbluf).intVbluf();
            }
            rfturn CLOSED_OPTION;
        }
        for(int dountfr = 0, mbxCountfr = options.lfngti;
            dountfr < mbxCountfr; dountfr++) {
            if (options[dountfr].fqubls(sflfdtfdVbluf)) {
                rfturn dountfr;
            }
        }
        rfturn CLOSED_OPTION;
    }

    /**
     * Siows bn intfrnbl qufstion-mfssbgf diblog rfqufsting input from
     * tif usfr pbrfntfd to <dodf>pbrfntComponfnt</dodf>. Tif diblog
     * is displbyfd in tif <dodf>Componfnt</dodf>'s frbmf,
     * bnd is usublly positionfd bflow tif <dodf>Componfnt</dodf>.
     *
     * @pbrbm pbrfntComponfnt  tif pbrfnt <dodf>Componfnt</dodf>
     *          for tif diblog
     * @pbrbm mfssbgf  tif <dodf>Objfdt</dodf> to displby
     * @rfturn usfr's input
     */
    publid stbtid String siowIntfrnblInputDiblog(Componfnt pbrfntComponfnt,
                                                 Objfdt mfssbgf) {
        rfturn siowIntfrnblInputDiblog(pbrfntComponfnt, mfssbgf, UIMbnbgfr.
               gftString("OptionPbnf.inputDiblogTitlf", pbrfntComponfnt),
               QUESTION_MESSAGE);
    }

    /**
     * Siows bn intfrnbl diblog rfqufsting input from tif usfr pbrfntfd
     * to <dodf>pbrfntComponfnt</dodf> witi tif diblog ibving tif titlf
     * <dodf>titlf</dodf> bnd mfssbgf typf <dodf>mfssbgfTypf</dodf>.
     *
     * @pbrbm pbrfntComponfnt tif pbrfnt <dodf>Componfnt</dodf> for tif diblog
     * @pbrbm mfssbgf  tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf    tif <dodf>String</dodf> to displby in tif
     *          diblog titlf bbr
     * @pbrbm mfssbgfTypf tif typf of mfssbgf tibt is to bf displbyfd:
     *                    ERROR_MESSAGE, INFORMATION_MESSAGE, WARNING_MESSAGE,
     *                    QUESTION_MESSAGE, or PLAIN_MESSAGE
     * @rfturn usfr's input
     */
    publid stbtid String siowIntfrnblInputDiblog(Componfnt pbrfntComponfnt,
                             Objfdt mfssbgf, String titlf, int mfssbgfTypf) {
        rfturn (String)siowIntfrnblInputDiblog(pbrfntComponfnt, mfssbgf, titlf,
                                       mfssbgfTypf, null, null, null);
    }

    /**
     * Prompts tif usfr for input in b blodking intfrnbl diblog wifrf
     * tif initibl sflfdtion, possiblf sflfdtions, bnd bll otifr
     * options dbn bf spfdififd. Tif usfr will bblf to dioosf from
     * <dodf>sflfdtionVblufs</dodf>, wifrf <dodf>null</dodf>
     * implifs tif usfr dbn input
     * wibtfvfr tify wisi, usublly by mfbns of b <dodf>JTfxtFifld</dodf>.
     * <dodf>initiblSflfdtionVbluf</dodf> is tif initibl vbluf to prompt
     * tif usfr witi. It is up to tif UI to dfdidf iow bfst to rfprfsfnt
     * tif <dodf>sflfdtionVblufs</dodf>, but usublly b
     * <dodf>JComboBox</dodf>, <dodf>JList</dodf>, or
     * <dodf>JTfxtFifld</dodf> will bf usfd.
     *
     * @pbrbm pbrfntComponfnt tif pbrfnt <dodf>Componfnt</dodf> for tif diblog
     * @pbrbm mfssbgf  tif <dodf>Objfdt</dodf> to displby
     * @pbrbm titlf    tif <dodf>String</dodf> to displby in tif diblog
     *          titlf bbr
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>, <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>, or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm idon     tif <dodf>Idon</dodf> imbgf to displby
     * @pbrbm sflfdtionVblufs bn brrby of <dodf>Objfdts</dodf> tibt
     *                  givfs tif possiblf sflfdtions
     * @pbrbm initiblSflfdtionVbluf tif vbluf usfd to initiblizf tif input
     *                  fifld
     * @rfturn usfr's input, or <dodf>null</dodf> mfbning tif usfr
     *          dbndflfd tif input
     */
    publid stbtid Objfdt siowIntfrnblInputDiblog(Componfnt pbrfntComponfnt,
            Objfdt mfssbgf, String titlf, int mfssbgfTypf, Idon idon,
            Objfdt[] sflfdtionVblufs, Objfdt initiblSflfdtionVbluf) {
        JOptionPbnf pbnf = nfw JOptionPbnf(mfssbgf, mfssbgfTypf,
                OK_CANCEL_OPTION, idon, null, null);
        pbnf.putClifntPropfrty(PopupFbdtory_FORCE_HEAVYWEIGHT_POPUP,
                Boolfbn.TRUE);
        Componfnt fo = KfybobrdFodusMbnbgfr.gftCurrfntKfybobrdFodusMbnbgfr().
                gftFodusOwnfr();

        pbnf.sftWbntsInput(truf);
        pbnf.sftSflfdtionVblufs(sflfdtionVblufs);
        pbnf.sftInitiblSflfdtionVbluf(initiblSflfdtionVbluf);

        JIntfrnblFrbmf diblog =
            pbnf.drfbtfIntfrnblFrbmf(pbrfntComponfnt, titlf);

        pbnf.sflfdtInitiblVbluf();
        diblog.sftVisiblf(truf);

        /* Sindf bll input will bf blodkfd until tiis diblog is dismissfd,
         * mbkf surf its pbrfnt dontbinfrs brf visiblf first (tiis domponfnt
         * is tfstfd bflow).  Tiis is nfdfssbry for JApplfts, bfdbusf
         * bfdbusf bn bpplft normblly isn't mbdf visiblf until bftfr its
         * stbrt() mftiod rfturns -- if tiis mftiod is dbllfd from stbrt(),
         * tif bpplft will bppfbr to ibng wiilf bn invisiblf modbl frbmf
         * wbits for input.
         */
        if (diblog.isVisiblf() && !diblog.isSiowing()) {
            Contbinfr pbrfnt = diblog.gftPbrfnt();
            wiilf (pbrfnt != null) {
                if (pbrfnt.isVisiblf() == fblsf) {
                    pbrfnt.sftVisiblf(truf);
                }
                pbrfnt = pbrfnt.gftPbrfnt();
            }
        }

        AWTAddfssor.gftContbinfrAddfssor().stbrtLWModbl(diblog);

        if (pbrfntComponfnt instbndfof JIntfrnblFrbmf) {
            try {
                ((JIntfrnblFrbmf)pbrfntComponfnt).sftSflfdtfd(truf);
            } dbtdi (jbvb.bfbns.PropfrtyVftoExdfption f) {
            }
        }

        if (fo != null && fo.isSiowing()) {
            fo.rfqufstFodus();
        }
        Objfdt vbluf = pbnf.gftInputVbluf();

        if (vbluf == UNINITIALIZED_VALUE) {
            rfturn null;
        }
        rfturn vbluf;
    }

    /**
     * Crfbtfs bnd rfturns bn instbndf of <dodf>JIntfrnblFrbmf</dodf>.
     * Tif intfrnbl frbmf is drfbtfd witi tif spfdififd titlf,
     * bnd wrbpping tif <dodf>JOptionPbnf</dodf>.
     * Tif rfturnfd <dodf>JIntfrnblFrbmf</dodf> is
     * bddfd to tif <dodf>JDfsktopPbnf</dodf> bndfstor of
     * <dodf>pbrfntComponfnt</dodf>, or domponfnts
     * pbrfnt if onf its bndfstors isn't b <dodf>JDfsktopPbnf</dodf>,
     * or if <dodf>pbrfntComponfnt</dodf>
     * dofsn't ibvf b pbrfnt tifn b <dodf>RuntimfExdfption</dodf> is tirown.
     *
     * @pbrbm pbrfntComponfnt  tif pbrfnt <dodf>Componfnt</dodf> for
     *          tif intfrnbl frbmf
     * @pbrbm titlf    tif <dodf>String</dodf> to displby in tif
     *          frbmf's titlf bbr
     * @rfturn b <dodf>JIntfrnblFrbmf</dodf> dontbining b
     *          <dodf>JOptionPbnf</dodf>
     * @fxdfption RuntimfExdfption if <dodf>pbrfntComponfnt</dodf> dofs
     *          not ibvf b vblid pbrfnt
     */
    publid JIntfrnblFrbmf drfbtfIntfrnblFrbmf(Componfnt pbrfntComponfnt,
                                 String titlf) {
        Contbinfr pbrfnt =
                JOptionPbnf.gftDfsktopPbnfForComponfnt(pbrfntComponfnt);

        if (pbrfnt == null && (pbrfntComponfnt == null ||
                (pbrfnt = pbrfntComponfnt.gftPbrfnt()) == null)) {
            tirow nfw RuntimfExdfption("JOptionPbnf: pbrfntComponfnt dofs " +
                    "not ibvf b vblid pbrfnt");
        }

        // Option diblogs siould bf dlosbblf only
        finbl JIntfrnblFrbmf  iFrbmf = nfw JIntfrnblFrbmf(titlf, fblsf, truf,
                                                           fblsf, fblsf);

        iFrbmf.putClifntPropfrty("JIntfrnblFrbmf.frbmfTypf", "optionDiblog");
        iFrbmf.putClifntPropfrty("JIntfrnblFrbmf.mfssbgfTypf",
                                 Intfgfr.vblufOf(gftMfssbgfTypf()));

        iFrbmf.bddIntfrnblFrbmfListfnfr(nfw IntfrnblFrbmfAdbptfr() {
            publid void intfrnblFrbmfClosing(IntfrnblFrbmfEvfnt f) {
                if (gftVbluf() == UNINITIALIZED_VALUE) {
                    sftVbluf(null);
                }
            }
        });
        bddPropfrtyCibngfListfnfr(nfw PropfrtyCibngfListfnfr() {
            publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvfnt) {
                // Lft tif dffbultClosfOpfrbtion ibndlf tif dlosing
                // if tif usfr dlosfd tif ifrbmf witiout sflfdting b button
                // (nfwVbluf = null in tibt dbsf).  Otifrwisf, dlosf tif diblog.
                if (iFrbmf.isVisiblf() &&
                        fvfnt.gftSourdf() == JOptionPbnf.tiis &&
                        fvfnt.gftPropfrtyNbmf().fqubls(VALUE_PROPERTY)) {
                    AWTAddfssor.gftContbinfrAddfssor().stopLWModbl(iFrbmf);

                try {
                    iFrbmf.sftClosfd(truf);
                }
                dbtdi (jbvb.bfbns.PropfrtyVftoExdfption f) {
                }

                iFrbmf.sftVisiblf(fblsf);
                }
            }
        });
        iFrbmf.gftContfntPbnf().bdd(tiis, BordfrLbyout.CENTER);
        if (pbrfnt instbndfof JDfsktopPbnf) {
            pbrfnt.bdd(iFrbmf, JLbyfrfdPbnf.MODAL_LAYER);
        } flsf {
            pbrfnt.bdd(iFrbmf, BordfrLbyout.CENTER);
        }
        Dimfnsion iFrbmfSizf = iFrbmf.gftPrfffrrfdSizf();
        Dimfnsion rootSizf = pbrfnt.gftSizf();
        Dimfnsion pbrfntSizf = pbrfntComponfnt.gftSizf();

        iFrbmf.sftBounds((rootSizf.widti - iFrbmfSizf.widti) / 2,
                         (rootSizf.ifigit - iFrbmfSizf.ifigit) / 2,
                         iFrbmfSizf.widti, iFrbmfSizf.ifigit);
        // Wf wbnt diblog dfntfrfd rflbtivf to its pbrfnt domponfnt
        Point iFrbmfCoord =
          SwingUtilitifs.donvfrtPoint(pbrfntComponfnt, 0, 0, pbrfnt);
        int x = (pbrfntSizf.widti - iFrbmfSizf.widti) / 2 + iFrbmfCoord.x;
        int y = (pbrfntSizf.ifigit - iFrbmfSizf.ifigit) / 2 + iFrbmfCoord.y;

        // If possiblf, diblog siould bf fully visiblf
        int ovrx = x + iFrbmfSizf.widti - rootSizf.widti;
        int ovry = y + iFrbmfSizf.ifigit - rootSizf.ifigit;
        x = Mbti.mbx((ovrx > 0? x - ovrx: x), 0);
        y = Mbti.mbx((ovry > 0? y - ovry: y), 0);
        iFrbmf.sftBounds(x, y, iFrbmfSizf.widti, iFrbmfSizf.ifigit);

        pbrfnt.vblidbtf();
        try {
            iFrbmf.sftSflfdtfd(truf);
        } dbtdi (jbvb.bfbns.PropfrtyVftoExdfption f) {}

        rfturn iFrbmf;
    }

    /**
     * Rfturns tif spfdififd domponfnt's <dodf>Frbmf</dodf>.
     *
     * @pbrbm pbrfntComponfnt tif <dodf>Componfnt</dodf> to difdk for b
     *          <dodf>Frbmf</dodf>
     * @rfturn tif <dodf>Frbmf</dodf> tibt dontbins tif domponfnt,
     *          or <dodf>gftRootFrbmf</dodf>
     *          if tif domponfnt is <dodf>null</dodf>,
     *          or dofs not ibvf b vblid <dodf>Frbmf</dodf> pbrfnt
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff #gftRootFrbmf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid Frbmf gftFrbmfForComponfnt(Componfnt pbrfntComponfnt)
        tirows HfbdlfssExdfption {
        if (pbrfntComponfnt == null)
            rfturn gftRootFrbmf();
        if (pbrfntComponfnt instbndfof Frbmf)
            rfturn (Frbmf)pbrfntComponfnt;
        rfturn JOptionPbnf.gftFrbmfForComponfnt(pbrfntComponfnt.gftPbrfnt());
    }

    /**
     * Rfturns tif spfdififd domponfnt's toplfvfl <dodf>Frbmf</dodf> or
     * <dodf>Diblog</dodf>.
     *
     * @pbrbm pbrfntComponfnt tif <dodf>Componfnt</dodf> to difdk for b
     *          <dodf>Frbmf</dodf> or <dodf>Diblog</dodf>
     * @rfturn tif <dodf>Frbmf</dodf> or <dodf>Diblog</dodf> tibt
     *          dontbins tif domponfnt, or tif dffbult
     *          frbmf if tif domponfnt is <dodf>null</dodf>,
     *          or dofs not ibvf b vblid
     *          <dodf>Frbmf</dodf> or <dodf>Diblog</dodf> pbrfnt
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    stbtid Window gftWindowForComponfnt(Componfnt pbrfntComponfnt)
        tirows HfbdlfssExdfption {
        if (pbrfntComponfnt == null)
            rfturn gftRootFrbmf();
        if (pbrfntComponfnt instbndfof Frbmf || pbrfntComponfnt instbndfof Diblog)
            rfturn (Window)pbrfntComponfnt;
        rfturn JOptionPbnf.gftWindowForComponfnt(pbrfntComponfnt.gftPbrfnt());
    }


    /**
     * Rfturns tif spfdififd domponfnt's dfsktop pbnf.
     *
     * @pbrbm pbrfntComponfnt tif <dodf>Componfnt</dodf> to difdk for b
     *          dfsktop
     * @rfturn tif <dodf>JDfsktopPbnf</dodf> tibt dontbins tif domponfnt,
     *          or <dodf>null</dodf> if tif domponfnt is <dodf>null</dodf>
     *          or dofs not ibvf bn bndfstor tibt is b
     *          <dodf>JIntfrnblFrbmf</dodf>
     */
    publid stbtid JDfsktopPbnf gftDfsktopPbnfForComponfnt(Componfnt pbrfntComponfnt) {
        if(pbrfntComponfnt == null)
            rfturn null;
        if(pbrfntComponfnt instbndfof JDfsktopPbnf)
            rfturn (JDfsktopPbnf)pbrfntComponfnt;
        rfturn gftDfsktopPbnfForComponfnt(pbrfntComponfnt.gftPbrfnt());
    }

    privbtf stbtid finbl Objfdt sibrfdFrbmfKfy = JOptionPbnf.dlbss;

    /**
     * Sfts tif frbmf to usf for dlbss mftiods in wiidi b frbmf is
     * not providfd.
     * <p>
     * <strong>Notf:</strong>
     * It is rfdommfndfd tibt rbtifr tibn using tiis mftiod you supply b vblid pbrfnt.
     *
     * @pbrbm nfwRootFrbmf tif dffbult <dodf>Frbmf</dodf> to usf
     */
    publid stbtid void sftRootFrbmf(Frbmf nfwRootFrbmf) {
        if (nfwRootFrbmf != null) {
            SwingUtilitifs.bppContfxtPut(sibrfdFrbmfKfy, nfwRootFrbmf);
        } flsf {
            SwingUtilitifs.bppContfxtRfmovf(sibrfdFrbmfKfy);
        }
    }

    /**
     * Rfturns tif <dodf>Frbmf</dodf> to usf for tif dlbss mftiods in
     * wiidi b frbmf is not providfd.
     *
     * @rfturn tif dffbult <dodf>Frbmf</dodf> to usf
     * @fxdfption HfbdlfssExdfption if
     *   <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns
     *   <dodf>truf</dodf>
     * @sff #sftRootFrbmf
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid stbtid Frbmf gftRootFrbmf() tirows HfbdlfssExdfption {
        Frbmf sibrfdFrbmf =
            (Frbmf)SwingUtilitifs.bppContfxtGft(sibrfdFrbmfKfy);
        if (sibrfdFrbmf == null) {
            sibrfdFrbmf = SwingUtilitifs.gftSibrfdOwnfrFrbmf();
            SwingUtilitifs.bppContfxtPut(sibrfdFrbmfKfy, sibrfdFrbmf);
        }
        rfturn sibrfdFrbmf;
    }

    /**
     * Crfbtfs b <dodf>JOptionPbnf</dodf> witi b tfst mfssbgf.
     */
    publid JOptionPbnf() {
        tiis("JOptionPbnf mfssbgf");
    }

    /**
     * Crfbtfs b instbndf of <dodf>JOptionPbnf</dodf> to displby b
     * mfssbgf using tif
     * plbin-mfssbgf mfssbgf typf bnd tif dffbult options dflivfrfd by
     * tif UI.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     */
    publid JOptionPbnf(Objfdt mfssbgf) {
        tiis(mfssbgf, PLAIN_MESSAGE);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>JOptionPbnf</dodf> to displby b mfssbgf
     * witi tif spfdififd mfssbgf typf bnd tif dffbult options,
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     */
    publid JOptionPbnf(Objfdt mfssbgf, int mfssbgfTypf) {
        tiis(mfssbgf, mfssbgfTypf, DEFAULT_OPTION);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>JOptionPbnf</dodf> to displby b mfssbgf
     * witi tif spfdififd mfssbgf typf bnd options.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm optionTypf tif options to displby in tif pbnf:
     *                  <dodf>DEFAULT_OPTION</dodf>, <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  <dodf>OK_CANCEL_OPTION</dodf>
     */
    publid JOptionPbnf(Objfdt mfssbgf, int mfssbgfTypf, int optionTypf) {
        tiis(mfssbgf, mfssbgfTypf, optionTypf, null);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>JOptionPbnf</dodf> to displby b mfssbgf
     * witi tif spfdififd mfssbgf typf, options, bnd idon.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm optionTypf tif options to displby in tif pbnf:
     *                  <dodf>DEFAULT_OPTION</dodf>, <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  <dodf>OK_CANCEL_OPTION</dodf>
     * @pbrbm idon tif <dodf>Idon</dodf> imbgf to displby
     */
    publid JOptionPbnf(Objfdt mfssbgf, int mfssbgfTypf, int optionTypf,
                       Idon idon) {
        tiis(mfssbgf, mfssbgfTypf, optionTypf, idon, null);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>JOptionPbnf</dodf> to displby b mfssbgf
     * witi tif spfdififd mfssbgf typf, idon, bnd options.
     * Nonf of tif options is initiblly sflfdtfd.
     * <p>
     * Tif options objfdts siould dontbin fitifr instbndfs of
     * <dodf>Componfnt</dodf>s, (wiidi brf bddfd dirfdtly) or
     * <dodf>Strings</dodf> (wiidi brf wrbppfd in b <dodf>JButton</dodf>).
     * If you providf <dodf>Componfnt</dodf>s, you must fnsurf tibt wifn tif
     * <dodf>Componfnt</dodf> is dlidkfd it mfssbgfs <dodf>sftVbluf</dodf>
     * in tif drfbtfd <dodf>JOptionPbnf</dodf>.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm optionTypf tif options to displby in tif pbnf:
     *                  <dodf>DEFAULT_OPTION</dodf>,
     *                  <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  <dodf>OK_CANCEL_OPTION</dodf>
     * @pbrbm idon tif <dodf>Idon</dodf> imbgf to displby
     * @pbrbm options  tif dioidfs tif usfr dbn sflfdt
     */
    publid JOptionPbnf(Objfdt mfssbgf, int mfssbgfTypf, int optionTypf,
                       Idon idon, Objfdt[] options) {
        tiis(mfssbgf, mfssbgfTypf, optionTypf, idon, options, null);
    }

    /**
     * Crfbtfs bn instbndf of <dodf>JOptionPbnf</dodf> to displby b mfssbgf
     * witi tif spfdififd mfssbgf typf, idon, bnd options, witi tif
     * initiblly-sflfdtfd option spfdififd.
     *
     * @pbrbm mfssbgf tif <dodf>Objfdt</dodf> to displby
     * @pbrbm mfssbgfTypf tif typf of mfssbgf to bf displbyfd:
     *                  <dodf>ERROR_MESSAGE</dodf>,
     *                  <dodf>INFORMATION_MESSAGE</dodf>,
     *                  <dodf>WARNING_MESSAGE</dodf>,
     *                  <dodf>QUESTION_MESSAGE</dodf>,
     *                  or <dodf>PLAIN_MESSAGE</dodf>
     * @pbrbm optionTypf tif options to displby in tif pbnf:
     *                  <dodf>DEFAULT_OPTION</dodf>,
     *                  <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  <dodf>OK_CANCEL_OPTION</dodf>
     * @pbrbm idon tif Idon imbgf to displby
     * @pbrbm options  tif dioidfs tif usfr dbn sflfdt
     * @pbrbm initiblVbluf tif dioidf tibt is initiblly sflfdtfd; if
     *                  <dodf>null</dodf>, tifn notiing will bf initiblly sflfdtfd;
     *                  only mfbningful if <dodf>options</dodf> is usfd
     */
    publid JOptionPbnf(Objfdt mfssbgf, int mfssbgfTypf, int optionTypf,
                       Idon idon, Objfdt[] options, Objfdt initiblVbluf) {

        tiis.mfssbgf = mfssbgf;
        tiis.options = options;
        tiis.initiblVbluf = initiblVbluf;
        tiis.idon = idon;
        sftMfssbgfTypf(mfssbgfTypf);
        sftOptionTypf(optionTypf);
        vbluf = UNINITIALIZED_VALUE;
        inputVbluf = UNINITIALIZED_VALUE;
        updbtfUI();
    }

    /**
     * Sfts tif UI objfdt wiidi implfmfnts tif {@litfrbl L&F} for tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>OptionPbnfUI</dodf> {@litfrbl L&F} objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *       bound: truf
     *      iiddfn: truf
     * dfsdription: Tif UI objfdt tibt implfmfnts tif optionpbnf's LookAndFffl
     */
    publid void sftUI(OptionPbnfUI ui) {
        if (tiis.ui != ui) {
            supfr.sftUI(ui);
            invblidbtf();
        }
    }

    /**
     * Rfturns tif UI objfdt wiidi implfmfnts tif {@litfrbl L&F} for tiis domponfnt.
     *
     * @rfturn tif <dodf>OptionPbnfUI</dodf> objfdt
     */
    publid OptionPbnfUI gftUI() {
        rfturn (OptionPbnfUI)ui;
    }

    /**
     * Notifidbtion from tif <dodf>UIMbnbgfr</dodf> tibt tif {@litfrbl L&F} ibs dibngfd.
     * Rfplbdfs tif durrfnt UI objfdt witi tif lbtfst vfrsion from tif
     * <dodf>UIMbnbgfr</dodf>.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((OptionPbnfUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns tif nbmf of tif UI dlbss tibt implfmfnts tif
     * {@litfrbl L&F} for tiis domponfnt.
     *
     * @rfturn tif string "OptionPbnfUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Sfts tif option pbnf's mfssbgf-objfdt.
     * @pbrbm nfwMfssbgf tif <dodf>Objfdt</dodf> to displby
     * @sff #gftMfssbgf
     *
     * @bfbninfo
     *   prfffrrfd: truf
     *   bound: truf
     * dfsdription: Tif optionpbnf's mfssbgf objfdt.
     */
    publid void sftMfssbgf(Objfdt nfwMfssbgf) {
        Objfdt           oldMfssbgf = mfssbgf;

        mfssbgf = nfwMfssbgf;
        firfPropfrtyCibngf(MESSAGE_PROPERTY, oldMfssbgf, mfssbgf);
    }

    /**
     * Rfturns tif mfssbgf-objfdt tiis pbnf displbys.
     * @sff #sftMfssbgf
     *
     * @rfturn tif <dodf>Objfdt</dodf> tibt is displbyfd
     */
    publid Objfdt gftMfssbgf() {
        rfturn mfssbgf;
    }

    /**
     * Sfts tif idon to displby. If non-<dodf>null</dodf>, tif look bnd fffl
     * dofs not providf bn idon.
     * @pbrbm nfwIdon tif <dodf>Idon</dodf> to displby
     *
     * @sff #gftIdon
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif option pbnf's typf idon.
     */
    publid void sftIdon(Idon nfwIdon) {
        Objfdt              oldIdon = idon;

        idon = nfwIdon;
        firfPropfrtyCibngf(ICON_PROPERTY, oldIdon, idon);
    }

    /**
     * Rfturns tif idon tiis pbnf displbys.
     * @rfturn tif <dodf>Idon</dodf> tibt is displbyfd
     *
     * @sff #sftIdon
     */
    publid Idon gftIdon() {
        rfturn idon;
    }

    /**
     * Sfts tif vbluf tif usfr ibs diosfn.
     * @pbrbm nfwVbluf  tif diosfn vbluf
     *
     * @sff #gftVbluf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif option pbnf's vbluf objfdt.
     */
    publid void sftVbluf(Objfdt nfwVbluf) {
        Objfdt               oldVbluf = vbluf;

        vbluf = nfwVbluf;
        firfPropfrtyCibngf(VALUE_PROPERTY, oldVbluf, vbluf);
    }

    /**
     * Rfturns tif vbluf tif usfr ibs sflfdtfd. <dodf>UNINITIALIZED_VALUE</dodf>
     * implifs tif usfr ibs not yft mbdf b dioidf, <dodf>null</dodf> mfbns tif
     * usfr dlosfd tif window witi out dioosing bnytiing. Otifrwisf
     * tif rfturnfd vbluf will bf onf of tif options dffinfd in tiis
     * objfdt.
     *
     * @rfturn tif <dodf>Objfdt</dodf> diosfn by tif usfr,
     *         <dodf>UNINITIALIZED_VALUE</dodf>
     *         if tif usfr ibs not yft mbdf b dioidf, or <dodf>null</dodf> if
     *         tif usfr dlosfd tif window witiout mbking b dioidf
     *
     * @sff #sftVbluf
     */
    publid Objfdt gftVbluf() {
        rfturn vbluf;
    }

    /**
     * Sfts tif options tiis pbnf displbys. If bn flfmfnt in
     * <dodf>nfwOptions</dodf> is b <dodf>Componfnt</dodf>
     * it is bddfd dirfdtly to tif pbnf,
     * otifrwisf b button is drfbtfd for tif flfmfnt.
     *
     * @pbrbm nfwOptions bn brrby of <dodf>Objfdts</dodf> tibt drfbtf tif
     *          buttons tif usfr dbn dlidk on, or brbitrbry
     *          <dodf>Componfnts</dodf> to bdd to tif pbnf
     *
     * @sff #gftOptions
     * @bfbninfo
     *       bound: truf
     * dfsdription: Tif option pbnf's options objfdts.
     */
    publid void sftOptions(Objfdt[] nfwOptions) {
        Objfdt[]           oldOptions = options;

        options = nfwOptions;
        firfPropfrtyCibngf(OPTIONS_PROPERTY, oldOptions, options);
    }

    /**
     * Rfturns tif dioidfs tif usfr dbn mbkf.
     * @rfturn tif brrby of <dodf>Objfdts</dodf> tibt givf tif usfr's dioidfs
     *
     * @sff #sftOptions
     */
    publid Objfdt[] gftOptions() {
        if(options != null) {
            int             optionCount = options.lfngti;
            Objfdt[]        rftOptions = nfw Objfdt[optionCount];

            Systfm.brrbydopy(options, 0, rftOptions, 0, optionCount);
            rfturn rftOptions;
        }
        rfturn options;
    }

    /**
     * Sfts tif initibl vbluf tibt is to bf fnbblfd -- tif
     * <dodf>Componfnt</dodf>
     * tibt ibs tif fodus wifn tif pbnf is initiblly displbyfd.
     *
     * @pbrbm nfwInitiblVbluf tif <dodf>Objfdt</dodf> tibt gfts tif initibl
     *                         kfybobrd fodus
     *
     * @sff #gftInitiblVbluf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif option pbnf's initibl vbluf objfdt.
     */
    publid void sftInitiblVbluf(Objfdt nfwInitiblVbluf) {
        Objfdt            oldIV = initiblVbluf;

        initiblVbluf = nfwInitiblVbluf;
        firfPropfrtyCibngf(INITIAL_VALUE_PROPERTY, oldIV, initiblVbluf);
    }

    /**
     * Rfturns tif initibl vbluf.
     *
     * @rfturn tif <dodf>Objfdt</dodf> tibt gfts tif initibl kfybobrd fodus
     *
     * @sff #sftInitiblVbluf
     */
    publid Objfdt gftInitiblVbluf() {
        rfturn initiblVbluf;
    }

    /**
     * Sfts tif option pbnf's mfssbgf typf.
     * Tif mfssbgf typf is usfd by tif Look bnd Fffl to dftfrminf tif
     * idon to displby (if not supplifd) bs wfll bs potfntiblly iow to
     * lby out tif <dodf>pbrfntComponfnt</dodf>.
     * @pbrbm nfwTypf bn intfgfr spfdifying tif kind of mfssbgf to displby:
     *                <dodf>ERROR_MESSAGE</dodf>, <dodf>INFORMATION_MESSAGE</dodf>,
     *                <dodf>WARNING_MESSAGE</dodf>,
     *                <dodf>QUESTION_MESSAGE</dodf>, or <dodf>PLAIN_MESSAGE</dodf>
     * @fxdfption RuntimfExdfption if <dodf>nfwTypf</dodf> is not onf of tif
     *          lfgbl vblufs listfd bbovf

     * @sff #gftMfssbgfTypf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif option pbnf's mfssbgf typf.
     */
    publid void sftMfssbgfTypf(int nfwTypf) {
        if(nfwTypf != ERROR_MESSAGE && nfwTypf != INFORMATION_MESSAGE &&
           nfwTypf != WARNING_MESSAGE && nfwTypf != QUESTION_MESSAGE &&
           nfwTypf != PLAIN_MESSAGE)
            tirow nfw RuntimfExdfption("JOptionPbnf: typf must bf onf of JOptionPbnf.ERROR_MESSAGE, JOptionPbnf.INFORMATION_MESSAGE, JOptionPbnf.WARNING_MESSAGE, JOptionPbnf.QUESTION_MESSAGE or JOptionPbnf.PLAIN_MESSAGE");

        int           oldTypf = mfssbgfTypf;

        mfssbgfTypf = nfwTypf;
        firfPropfrtyCibngf(MESSAGE_TYPE_PROPERTY, oldTypf, mfssbgfTypf);
    }

    /**
     * Rfturns tif mfssbgf typf.
     *
     * @rfturn bn intfgfr spfdifying tif mfssbgf typf
     *
     * @sff #sftMfssbgfTypf
     */
    publid int gftMfssbgfTypf() {
        rfturn mfssbgfTypf;
    }

    /**
     * Sfts tif options to displby.
     * Tif option typf is usfd by tif Look bnd Fffl to
     * dftfrminf wibt buttons to siow (unlfss options brf supplifd).
     * @pbrbm nfwTypf bn intfgfr spfdifying tif options tif {@litfrbl L&F} is to displby:
     *                  <dodf>DEFAULT_OPTION</dodf>,
     *                  <dodf>YES_NO_OPTION</dodf>,
     *                  <dodf>YES_NO_CANCEL_OPTION</dodf>,
     *                  or <dodf>OK_CANCEL_OPTION</dodf>
     * @fxdfption RuntimfExdfption if <dodf>nfwTypf</dodf> is not onf of
     *          tif lfgbl vblufs listfd bbovf
     *
     * @sff #gftOptionTypf
     * @sff #sftOptions
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif option pbnf's option typf.
      */
    publid void sftOptionTypf(int nfwTypf) {
        if(nfwTypf != DEFAULT_OPTION && nfwTypf != YES_NO_OPTION &&
           nfwTypf != YES_NO_CANCEL_OPTION && nfwTypf != OK_CANCEL_OPTION)
            tirow nfw RuntimfExdfption("JOptionPbnf: option typf must bf onf of JOptionPbnf.DEFAULT_OPTION, JOptionPbnf.YES_NO_OPTION, JOptionPbnf.YES_NO_CANCEL_OPTION or JOptionPbnf.OK_CANCEL_OPTION");

        int            oldTypf = optionTypf;

        optionTypf = nfwTypf;
        firfPropfrtyCibngf(OPTION_TYPE_PROPERTY, oldTypf, optionTypf);
    }

    /**
     * Rfturns tif typf of options tibt brf displbyfd.
     *
     * @rfturn bn intfgfr spfdifying tif usfr-sflfdtbblf options
     *
     * @sff #sftOptionTypf
     */
    publid int gftOptionTypf() {
        rfturn optionTypf;
    }

    /**
     * Sfts tif input sflfdtion vblufs for b pbnf tibt providfs tif usfr
     * witi b list of itfms to dioosf from. (Tif UI providfs b widgft
     * for dioosing onf of tif vblufs.)  A <dodf>null</dodf> vbluf
     * implifs tif usfr dbn input wibtfvfr tify wisi, usublly by mfbns
     * of b <dodf>JTfxtFifld</dodf>.
     * <p>
     * Sfts <dodf>wbntsInput</dodf> to truf. Usf
     * <dodf>sftInitiblSflfdtionVbluf</dodf> to spfdify tif initiblly-diosfn
     * vbluf. Aftfr tif pbnf bs bffn fnbblfd, <dodf>inputVbluf</dodf> is
     * sft to tif vbluf tif usfr ibs sflfdtfd.
     * @pbrbm nfwVblufs bn brrby of <dodf>Objfdts</dodf> tif usfr to bf
     *                  displbyfd
     *                  (usublly in b list or dombo-box) from wiidi
     *                  tif usfr dbn mbkf b sflfdtion
     * @sff #sftWbntsInput
     * @sff #sftInitiblSflfdtionVbluf
     * @sff #gftSflfdtionVblufs
     * @bfbninfo
     *       bound: truf
     * dfsdription: Tif option pbnf's sflfdtion vblufs.
     */
    publid void sftSflfdtionVblufs(Objfdt[] nfwVblufs) {
        Objfdt[]           oldVblufs = sflfdtionVblufs;

        sflfdtionVblufs = nfwVblufs;
        firfPropfrtyCibngf(SELECTION_VALUES_PROPERTY, oldVblufs, nfwVblufs);
        if(sflfdtionVblufs != null)
            sftWbntsInput(truf);
    }

    /**
     * Rfturns tif input sflfdtion vblufs.
     *
     * @rfturn tif brrby of <dodf>Objfdts</dodf> tif usfr dbn sflfdt
     * @sff #sftSflfdtionVblufs
     */
    publid Objfdt[] gftSflfdtionVblufs() {
        rfturn sflfdtionVblufs;
    }

    /**
     * Sfts tif input vbluf tibt is initiblly displbyfd bs sflfdtfd to tif usfr.
     * Only usfd if <dodf>wbntsInput</dodf> is truf.
     * @pbrbm nfwVbluf tif initiblly sflfdtfd vbluf
     * @sff #sftSflfdtionVblufs
     * @sff #gftInitiblSflfdtionVbluf
     * @bfbninfo
     *       bound: truf
     * dfsdription: Tif option pbnf's initibl sflfdtion vbluf objfdt.
     */
    publid void sftInitiblSflfdtionVbluf(Objfdt nfwVbluf) {
        Objfdt          oldVbluf = initiblSflfdtionVbluf;

        initiblSflfdtionVbluf = nfwVbluf;
        firfPropfrtyCibngf(INITIAL_SELECTION_VALUE_PROPERTY, oldVbluf,
                           nfwVbluf);
    }

    /**
     * Rfturns tif input vbluf tibt is displbyfd bs initiblly sflfdtfd to tif usfr.
     *
     * @rfturn tif initiblly sflfdtfd vbluf
     * @sff #sftInitiblSflfdtionVbluf
     * @sff #sftSflfdtionVblufs
     */
    publid Objfdt gftInitiblSflfdtionVbluf() {
        rfturn initiblSflfdtionVbluf;
    }

    /**
     * Sfts tif input vbluf tibt wbs sflfdtfd or input by tif usfr.
     * Only usfd if <dodf>wbntsInput</dodf> is truf.  Notf tibt tiis mftiod
     * is invokfd intfrnblly by tif option pbnf (in rfsponsf to usfr bdtion)
     * bnd siould gfnfrblly not bf dbllfd by dlifnt progrbms.  To sft tif
     * input vbluf initiblly displbyfd bs sflfdtfd to tif usfr, usf
     * <dodf>sftInitiblSflfdtionVbluf</dodf>.
     *
     * @pbrbm nfwVbluf tif <dodf>Objfdt</dodf> usfd to sft tif
     *          vbluf tibt tif usfr spfdififd (usublly in b tfxt fifld)
     * @sff #sftSflfdtionVblufs
     * @sff #sftInitiblSflfdtionVbluf
     * @sff #sftWbntsInput
     * @sff #gftInputVbluf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Tif option pbnf's input vbluf objfdt.
     */
    publid void sftInputVbluf(Objfdt nfwVbluf) {
        Objfdt              oldVbluf = inputVbluf;

        inputVbluf = nfwVbluf;
        firfPropfrtyCibngf(INPUT_VALUE_PROPERTY, oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns tif vbluf tif usfr ibs input, if <dodf>wbntsInput</dodf>
     * is truf.
     *
     * @rfturn tif <dodf>Objfdt</dodf> tif usfr spfdififd,
     *          if it wbs onf of tif objfdts, or b
     *          <dodf>String</dodf> if it wbs b vbluf typfd into b
     *          fifld
     * @sff #sftSflfdtionVblufs
     * @sff #sftWbntsInput
     * @sff #sftInputVbluf
     */
    publid Objfdt gftInputVbluf() {
        rfturn inputVbluf;
    }

    /**
     * Rfturns tif mbximum numbfr of dibrbdtfrs to plbdf on b linf in b
     * mfssbgf. Dffbult is to rfturn <dodf>Intfgfr.MAX_VALUE</dodf>.
     * Tif vbluf dbn bf
     * dibngfd by ovfrriding tiis mftiod in b subdlbss.
     *
     * @rfturn bn intfgfr giving tif mbximum numbfr of dibrbdtfrs on b linf
     */
    publid int gftMbxCibrbdtfrsPfrLinfCount() {
        rfturn Intfgfr.MAX_VALUE;
    }

    /**
     * Sfts tif <dodf>wbntsInput</dodf> propfrty.
     * If <dodf>nfwVbluf</dodf> is truf, bn input domponfnt
     * (sudi bs b tfxt fifld or dombo box) wiosf pbrfnt is
     * <dodf>pbrfntComponfnt</dodf> is providfd to
     * bllow tif usfr to input b vbluf. If <dodf>gftSflfdtionVblufs</dodf>
     * rfturns b non-<dodf>null</dodf> brrby, tif input vbluf is onf of tif
     * objfdts in tibt brrby. Otifrwisf tif input vbluf is wibtfvfr
     * tif usfr inputs.
     * <p>
     * Tiis is b bound propfrty.
     *
     * @pbrbm nfwVbluf if truf, bn input domponfnt wiosf pbrfnt is {@dodf pbrfntComponfnt}
     *                 is providfd to bllow tif usfr to input b vbluf.
     * @sff #sftSflfdtionVblufs
     * @sff #sftInputVbluf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Flbg wiidi bllows tif usfr to input b vbluf.
     */
    publid void sftWbntsInput(boolfbn nfwVbluf) {
        boolfbn            oldVbluf = wbntsInput;

        wbntsInput = nfwVbluf;
        firfPropfrtyCibngf(WANTS_INPUT_PROPERTY, oldVbluf, nfwVbluf);
    }

    /**
     * Rfturns tif vbluf of tif <dodf>wbntsInput</dodf> propfrty.
     *
     * @rfturn truf if bn input domponfnt will bf providfd
     * @sff #sftWbntsInput
     */
    publid boolfbn gftWbntsInput() {
        rfturn wbntsInput;
    }

    /**
     * Rfqufsts tibt tif initibl vbluf bf sflfdtfd, wiidi will sft
     * fodus to tif initibl vbluf. Tiis mftiod
     * siould bf invokfd bftfr tif window dontbining tif option pbnf
     * is mbdf visiblf.
     */
    publid void sflfdtInitiblVbluf() {
        OptionPbnfUI         ui = gftUI();
        if (ui != null) {
            ui.sflfdtInitiblVbluf(tiis);
        }
    }


    privbtf stbtid int stylfFromMfssbgfTypf(int mfssbgfTypf) {
        switdi (mfssbgfTypf) {
        dbsf ERROR_MESSAGE:
            rfturn JRootPbnf.ERROR_DIALOG;
        dbsf QUESTION_MESSAGE:
            rfturn JRootPbnf.QUESTION_DIALOG;
        dbsf WARNING_MESSAGE:
            rfturn JRootPbnf.WARNING_DIALOG;
        dbsf INFORMATION_MESSAGE:
            rfturn JRootPbnf.INFORMATION_DIALOG;
        dbsf PLAIN_MESSAGE:
        dffbult:
            rfturn JRootPbnf.PLAIN_DIALOG;
        }
    }

    // Sfriblizbtion support.
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        Vfdtor<Objfdt> vblufs = nfw Vfdtor<Objfdt>();

        s.dffbultWritfObjfdt();
        // Sbvf tif idon, if its Sfriblizbblf.
        if(idon != null && idon instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("idon");
            vblufs.bddElfmfnt(idon);
        }
        // Sbvf tif mfssbgf, if its Sfriblizbblf.
        if(mfssbgf != null && mfssbgf instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("mfssbgf");
            vblufs.bddElfmfnt(mfssbgf);
        }
        // Sbvf tif trffModfl, if its Sfriblizbblf.
        if(options != null) {
            Vfdtor<Objfdt> sfrOptions = nfw Vfdtor<Objfdt>();

            for(int dountfr = 0, mbxCountfr = options.lfngti;
                dountfr < mbxCountfr; dountfr++)
                if(options[dountfr] instbndfof Sfriblizbblf)
                    sfrOptions.bddElfmfnt(options[dountfr]);
            if(sfrOptions.sizf() > 0) {
                int             optionCount = sfrOptions.sizf();
                Objfdt[]        brrbyOptions = nfw Objfdt[optionCount];

                sfrOptions.dopyInto(brrbyOptions);
                vblufs.bddElfmfnt("options");
                vblufs.bddElfmfnt(brrbyOptions);
            }
        }
        // Sbvf tif initiblVbluf, if its Sfriblizbblf.
        if(initiblVbluf != null && initiblVbluf instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("initiblVbluf");
            vblufs.bddElfmfnt(initiblVbluf);
        }
        // Sbvf tif vbluf, if its Sfriblizbblf.
        if(vbluf != null && vbluf instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("vbluf");
            vblufs.bddElfmfnt(vbluf);
        }
        // Sbvf tif sflfdtionVblufs, if its Sfriblizbblf.
        if(sflfdtionVblufs != null) {
            boolfbn            sfriblizf = truf;

            for(int dountfr = 0, mbxCountfr = sflfdtionVblufs.lfngti;
                dountfr < mbxCountfr; dountfr++) {
                if(sflfdtionVblufs[dountfr] != null &&
                   !(sflfdtionVblufs[dountfr] instbndfof Sfriblizbblf)) {
                    sfriblizf = fblsf;
                    brfbk;
                }
            }
            if(sfriblizf) {
                vblufs.bddElfmfnt("sflfdtionVblufs");
                vblufs.bddElfmfnt(sflfdtionVblufs);
            }
        }
        // Sbvf tif inputVbluf, if its Sfriblizbblf.
        if(inputVbluf != null && inputVbluf instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("inputVbluf");
            vblufs.bddElfmfnt(inputVbluf);
        }
        // Sbvf tif initiblSflfdtionVbluf, if its Sfriblizbblf.
        if(initiblSflfdtionVbluf != null &&
           initiblSflfdtionVbluf instbndfof Sfriblizbblf) {
            vblufs.bddElfmfnt("initiblSflfdtionVbluf");
            vblufs.bddElfmfnt(initiblSflfdtionVbluf);
        }
        s.writfObjfdt(vblufs);
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();

        Vfdtor<?>       vblufs = (Vfdtor)s.rfbdObjfdt();
        int             indfxCountfr = 0;
        int             mbxCountfr = vblufs.sizf();

        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("idon")) {
            idon = (Idon)vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("mfssbgf")) {
            mfssbgf = vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("options")) {
            options = (Objfdt[])vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("initiblVbluf")) {
            initiblVbluf = vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("vbluf")) {
            vbluf = vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("sflfdtionVblufs")) {
            sflfdtionVblufs = (Objfdt[])vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("inputVbluf")) {
            inputVbluf = vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if(indfxCountfr < mbxCountfr && vblufs.flfmfntAt(indfxCountfr).
           fqubls("initiblSflfdtionVbluf")) {
            initiblSflfdtionVbluf = vblufs.flfmfntAt(++indfxCountfr);
            indfxCountfr++;
        }
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JOptionPbnf</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JOptionPbnf</dodf>
     */
    protfdtfd String pbrbmString() {
        String idonString = (idon != null ?
                             idon.toString() : "");
        String initiblVblufString = (initiblVbluf != null ?
                                     initiblVbluf.toString() : "");
        String mfssbgfString = (mfssbgf != null ?
                                mfssbgf.toString() : "");
        String mfssbgfTypfString;
        if (mfssbgfTypf == ERROR_MESSAGE) {
            mfssbgfTypfString = "ERROR_MESSAGE";
        } flsf if (mfssbgfTypf == INFORMATION_MESSAGE) {
            mfssbgfTypfString = "INFORMATION_MESSAGE";
        } flsf if (mfssbgfTypf == WARNING_MESSAGE) {
            mfssbgfTypfString = "WARNING_MESSAGE";
        } flsf if (mfssbgfTypf == QUESTION_MESSAGE) {
            mfssbgfTypfString = "QUESTION_MESSAGE";
        } flsf if (mfssbgfTypf == PLAIN_MESSAGE)  {
            mfssbgfTypfString = "PLAIN_MESSAGE";
        } flsf mfssbgfTypfString = "";
        String optionTypfString;
        if (optionTypf == DEFAULT_OPTION) {
            optionTypfString = "DEFAULT_OPTION";
        } flsf if (optionTypf == YES_NO_OPTION) {
            optionTypfString = "YES_NO_OPTION";
        } flsf if (optionTypf == YES_NO_CANCEL_OPTION) {
            optionTypfString = "YES_NO_CANCEL_OPTION";
        } flsf if (optionTypf == OK_CANCEL_OPTION) {
            optionTypfString = "OK_CANCEL_OPTION";
        } flsf optionTypfString = "";
        String wbntsInputString = (wbntsInput ?
                                   "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",idon=" + idonString +
        ",initiblVbluf=" + initiblVblufString +
        ",mfssbgf=" + mfssbgfString +
        ",mfssbgfTypf=" + mfssbgfTypfString +
        ",optionTypf=" + optionTypfString +
        ",wbntsInput=" + wbntsInputString;
    }

///////////////////
// Addfssibility support
///////////////////

    /**
     * Rfturns tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis JOptionPbnf.
     * For option pbnfs, tif <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJOptionPbnf</dodf>.
     * A nfw <dodf>AddfssiblfJOptionPbnf</dodf> instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJOptionPbnf tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis AddfssiblfJOptionPbnf
     * @bfbninfo
     *       fxpfrt: truf
     *  dfsdription: Tif AddfssiblfContfxt bssodibtfd witi tiis option pbnf
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJOptionPbnf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JOptionPbnf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to option pbnf usfr-intfrfbdf
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
    protfdtfd dlbss AddfssiblfJOptionPbnf fxtfnds AddfssiblfJComponfnt {

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            switdi (mfssbgfTypf) {
            dbsf ERROR_MESSAGE:
            dbsf INFORMATION_MESSAGE:
            dbsf WARNING_MESSAGE:
                rfturn AddfssiblfRolf.ALERT;

            dffbult:
                rfturn AddfssiblfRolf.OPTION_PANE;
            }
        }

    } // innfr dlbss AddfssiblfJOptionPbnf
}
