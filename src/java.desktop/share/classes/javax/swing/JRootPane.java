/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bpplft.Applft;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvbx.bddfssibility.*;
import jbvbx.swing.plbf.RootPbnfUI;
import jbvb.util.Vfdtor;
import jbvb.io.Sfriblizbblf;
import jbvbx.swing.bordfr.*;
import sun.bwt.AWTAddfssor;
import sun.sfdurity.bdtion.GftBoolfbnAdtion;


/**
 * A ligitwfigit dontbinfr usfd bfiind tif sdfnfs by
 * <dodf>JFrbmf</dodf>, <dodf>JDiblog</dodf>, <dodf>JWindow</dodf>,
 * <dodf>JApplft</dodf>, bnd <dodf>JIntfrnblFrbmf</dodf>.
 * For tbsk-orifntfd informbtion on fundtionblity providfd by root pbnfs
 * sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/rootpbnf.itml">How to Usf Root Pbnfs</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 *
 * <p>
 * Tif following imbgf siows tif rflbtionsiips bftwffn
 * tif dlbssfs tibt usf root pbnfs.
 * <p stylf="tfxt-blign:dfntfr"><img srd="dod-filfs/JRootPbnf-1.gif"
 * blt="Tif following tfxt dfsdribfs tiis grbpiid."
 * HEIGHT=484 WIDTH=629></p>
 * Tif &quot;ifbvywfigit&quot; domponfnts (tiosf tibt dflfgbtf to b pffr, or nbtivf
 * domponfnt on tif iost systfm) brf siown witi b dbrkfr, ifbvifr box. Tif four
 * ifbvywfigit JFC/Swing dontbinfrs (<dodf>JFrbmf</dodf>, <dodf>JDiblog</dodf>,
 * <dodf>JWindow</dodf>, bnd <dodf>JApplft</dodf>) brf
 * siown in rflbtion to tif AWT dlbssfs tify fxtfnd.
 * Tifsf four domponfnts brf tif
 * only ifbvywfigit dontbinfrs in tif Swing librbry. Tif ligitwfigit dontbinfr
 * <dodf>JIntfrnblFrbmf</dodf> is blso siown.
 * All fivf of tifsf JFC/Swing dontbinfrs implfmfnt tif
 * <dodf>RootPbnfContbinfr</dodf> intfrfbdf,
 * bnd tify bll dflfgbtf tifir opfrbtions to b
 * <dodf>JRootPbnf</dodf> (siown witi b littlf "ibndlf" on top).
 * <blodkquotf>
 * <b>Notf:</b> Tif <dodf>JComponfnt</dodf> mftiod <dodf>gftRootPbnf</dodf>
 * dbn bf usfd to obtbin tif <dodf>JRootPbnf</dodf> tibt dontbins
 * b givfn domponfnt.
 * </blodkquotf>
 * <tbblf stylf="flobt:rigit" bordfr="0" summbry="lbyout">
 * <tr>
 * <td blign="dfntfr">
 * <img srd="dod-filfs/JRootPbnf-2.gif"
 * blt="Tif following tfxt dfsdribfs tiis grbpiid." HEIGHT=386 WIDTH=349>
 * </td>
 * </tr>
 * </tbblf>
 * Tif dibgrbm bt rigit siows tif strudturf of b <dodf>JRootPbnf</dodf>.
 * A <dodf>JRootpbnf</dodf> is mbdf up of b <dodf>glbssPbnf</dodf>,
 * bn optionbl <dodf>mfnuBbr</dodf>, bnd b <dodf>dontfntPbnf</dodf>.
 * (Tif <dodf>JLbyfrfdPbnf</dodf> mbnbgfs tif <dodf>mfnuBbr</dodf>
 * bnd tif <dodf>dontfntPbnf</dodf>.)
 * Tif <dodf>glbssPbnf</dodf> sits ovfr tif top of fvfrytiing,
 * wifrf it is in b position to intfrdfpt mousf movfmfnts.
 * Sindf tif <dodf>glbssPbnf</dodf> (likf tif <dodf>dontfntPbnf</dodf>)
 * dbn bf bn brbitrbry domponfnt, it is blso possiblf to sft up tif
 * <dodf>glbssPbnf</dodf> for drbwing. Linfs bnd imbgfs on tif
 * <dodf>glbssPbnf</dodf> dbn tifn rbngf
 * ovfr tif frbmfs undfrnfbti witiout bfing limitfd by tifir boundbrifs.
 * <p>
 * Altiougi tif <dodf>mfnuBbr</dodf> domponfnt is optionbl,
 * tif <dodf>lbyfrfdPbnf</dodf>, <dodf>dontfntPbnf</dodf>,
 * bnd <dodf>glbssPbnf</dodf> blwbys fxist.
 * Attfmpting to sft tifm to <dodf>null</dodf> gfnfrbtfs bn fxdfption.
 * <p>
 * To bdd domponfnts to tif <dodf>JRootPbnf</dodf> (otifr tibn tif
 * optionbl mfnu bbr), you bdd tif objfdt to tif <dodf>dontfntPbnf</dodf>
 * of tif <dodf>JRootPbnf</dodf>, likf tiis:
 * <prf>
 *       rootPbnf.gftContfntPbnf().bdd(diild);
 * </prf>
 * Tif sbmf prindiplf iolds truf for sftting lbyout mbnbgfrs, rfmoving
 * domponfnts, listing diildrfn, ftd. All tifsf mftiods brf invokfd on
 * tif <dodf>dontfntPbnf</dodf> instfbd of on tif <dodf>JRootPbnf</dodf>.
 * <blodkquotf>
 * <b>Notf:</b> Tif dffbult lbyout mbnbgfr for tif <dodf>dontfntPbnf</dodf> is
 *  b <dodf>BordfrLbyout</dodf> mbnbgfr. Howfvfr, tif <dodf>JRootPbnf</dodf>
 *  usfs b dustom <dodf>LbyoutMbnbgfr</dodf>.
 *  So, wifn you wbnt to dibngf tif lbyout mbnbgfr for tif domponfnts you bddfd
 *  to b <dodf>JRootPbnf</dodf>, bf surf to usf dodf likf tiis:
 * <prf>
 *    rootPbnf.gftContfntPbnf().sftLbyout(nfw BoxLbyout());
 * </prf></blodkquotf>
 * If b <dodf>JMfnuBbr</dodf> domponfnt is sft on tif <dodf>JRootPbnf</dodf>,
 * it is positionfd blong tif uppfr fdgf of tif frbmf.
 * Tif <dodf>dontfntPbnf</dodf> is bdjustfd in lodbtion bnd sizf to
 * fill tif rfmbining brfb.
 * (Tif <dodf>JMfnuBbr</dodf> bnd tif <dodf>dontfntPbnf</dodf> brf bddfd to tif
 * <dodf>lbyfrfdPbnf</dodf> domponfnt bt tif
 * <dodf>JLbyfrfdPbnf.FRAME_CONTENT_LAYER</dodf> lbyfr.)
 * <p>
 * Tif <dodf>lbyfrfdPbnf</dodf> is tif pbrfnt of bll diildrfn in tif
 * <dodf>JRootPbnf</dodf> -- boti bs tif dirfdt pbrfnt of tif mfnu bnd
 * tif grbndpbrfnt of bll domponfnts bddfd to tif <dodf>dontfntPbnf</dodf>.
 * It is bn instbndf of <dodf>JLbyfrfdPbnf</dodf>,
 * wiidi providfs tif bbility to bdd domponfnts bt sfvfrbl lbyfrs.
 * Tiis dbpbbility is vfry usfful wifn working witi mfnu popups,
 * diblog boxfs, bnd drbgging -- situbtions in wiidi you nffd to plbdf
 * b domponfnt on top of bll otifr domponfnts in tif pbnf.
 * <p>
 * Tif <dodf>glbssPbnf</dodf> sits on top of bll otifr domponfnts in tif
 * <dodf>JRootPbnf</dodf>.
 * Tibt providfs b donvfnifnt plbdf to drbw bbovf bll otifr domponfnts,
 * bnd mbkfs it possiblf to intfrdfpt mousf fvfnts,
 * wiidi is usfful boti for drbgging bnd for drbwing.
 * Dfvflopfrs dbn usf <dodf>sftVisiblf</dodf> on tif <dodf>glbssPbnf</dodf>
 * to dontrol wifn tif <dodf>glbssPbnf</dodf> displbys ovfr tif otifr diildrfn.
 * By dffbult tif <dodf>glbssPbnf</dodf> is not visiblf.
 * <p>
 * Tif dustom <dodf>LbyoutMbnbgfr</dodf> usfd by <dodf>JRootPbnf</dodf>
 * fnsurfs tibt:
 * <OL>
 * <LI>Tif <dodf>glbssPbnf</dodf> fills tif fntirf vifwbblf
 *     brfb of tif <dodf>JRootPbnf</dodf> (bounds - insfts).
 * <LI>Tif <dodf>lbyfrfdPbnf</dodf> fills tif fntirf vifwbblf brfb of tif
 *     <dodf>JRootPbnf</dodf>. (bounds - insfts)
 * <LI>Tif <dodf>mfnuBbr</dodf> is positionfd bt tif uppfr fdgf of tif
 *     <dodf>lbyfrfdPbnf</dodf>.
 * <LI>Tif <dodf>dontfntPbnf</dodf> fills tif fntirf vifwbblf brfb,
 *     minus tif <dodf>mfnuBbr</dodf>, if prfsfnt.
 * </OL>
 * Any otifr vifws in tif <dodf>JRootPbnf</dodf> vifw iifrbrdiy brf ignorfd.
 * <p>
 * If you rfplbdf tif <dodf>LbyoutMbnbgfr</dodf> of tif <dodf>JRootPbnf</dodf>,
 * you brf rfsponsiblf for mbnbging bll of tifsf vifws.
 * So ordinbrily you will wbnt to bf surf tibt you
 * dibngf tif lbyout mbnbgfr for tif <dodf>dontfntPbnf</dodf> rbtifr tibn
 * for tif <dodf>JRootPbnf</dodf> itsflf!
 * <p>
 * Tif pbinting brdiitfdturf of Swing rfquirfs bn opbquf
 * <dodf>JComponfnt</dodf>
 * to fxist in tif dontbinmfnt iifrbrdiy bbovf bll otifr domponfnts. Tiis is
 * typidblly providfd by wby of tif dontfnt pbnf. If you rfplbdf tif dontfnt
 * pbnf, it is rfdommfndfd tibt you mbkf tif dontfnt pbnf opbquf
 * by wby of <dodf>sftOpbquf(truf)</dodf>. Additionblly, if tif dontfnt pbnf
 * ovfrridfs <dodf>pbintComponfnt</dodf>, it
 * will nffd to domplftfly fill in tif bbdkground in bn opbquf dolor in
 * <dodf>pbintComponfnt</dodf>.
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
 * @sff JLbyfrfdPbnf
 * @sff JMfnuBbr
 * @sff JWindow
 * @sff JFrbmf
 * @sff JDiblog
 * @sff JApplft
 * @sff JIntfrnblFrbmf
 * @sff JComponfnt
 * @sff BoxLbyout
 *
 * @sff <b irff="ittp://jbvb.sun.dom/produdts/jfd/tsd/brtidlfs/mixing/">
 * Mixing Hfbvy bnd Ligit Componfnts</b>
 *
 * @butior Dbvid Klobb
 * @sindf 1.2
 */
/// PENDING(klobbd) Wio siould bf opbquf in tiis domponfnt?
@SupprfssWbrnings("sfribl")
publid dlbss JRootPbnf fxtfnds JComponfnt implfmfnts Addfssiblf {

    privbtf stbtid finbl String uiClbssID = "RootPbnfUI";

    /**
     * Wiftifr or not wf siould dump tif stbdk wifn truf doublf bufffring
     * is disbblfd. Dffbult is fblsf.
     */
    privbtf stbtid finbl boolfbn LOG_DISABLE_TRUE_DOUBLE_BUFFERING;

    /**
     * Wiftifr or not wf siould ignorf rfqufsts to disbblf truf doublf
     * bufffring. Dffbult is fblsf.
     */
    privbtf stbtid finbl boolfbn IGNORE_DISABLE_TRUE_DOUBLE_BUFFERING;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould not providf bny sort of
     * Window dfdorbtions.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int NONE = 0;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Frbmf.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int FRAME = 1;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int PLAIN_DIALOG = 2;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog usfd to displby bn informbtionbl mfssbgf.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int INFORMATION_DIALOG = 3;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog usfd to displby bn frror mfssbgf.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int ERROR_DIALOG = 4;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog usfd to displby b <dodf>JColorCioosfr</dodf>.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int COLOR_CHOOSER_DIALOG = 5;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog usfd to displby b <dodf>JFilfCioosfr</dodf>.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int FILE_CHOOSER_DIALOG = 6;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog usfd to prfsfnt b qufstion to tif usfr.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int QUESTION_DIALOG = 7;

    /**
     * Constbnt usfd for tif windowDfdorbtionStylf propfrty. Indidbtfs tibt
     * tif <dodf>JRootPbnf</dodf> siould providf dfdorbtions bppropribtf for
     * b Diblog usfd to displby b wbrning mfssbgf.
     *
     * @sindf 1.4
     */
    publid stbtid finbl int WARNING_DIALOG = 8;

    privbtf int windowDfdorbtionStylf;

    /** Tif mfnu bbr. */
    protfdtfd JMfnuBbr mfnuBbr;

    /** Tif dontfnt pbnf. */
    protfdtfd Contbinfr dontfntPbnf;

    /** Tif lbyfrfd pbnf tibt mbnbgfs tif mfnu bbr bnd dontfnt pbnf. */
    protfdtfd JLbyfrfdPbnf lbyfrfdPbnf;

    /**
     * Tif glbss pbnf tibt ovfrlbys tif mfnu bbr bnd dontfnt pbnf,
     *  so it dbn intfrdfpt mousf movfmfnts bnd sudi.
     */
    protfdtfd Componfnt glbssPbnf;
    /**
     * Tif button tibt gfts bdtivbtfd wifn tif pbnf ibs tif fodus bnd
     * b UI-spfdifid bdtion likf prfssing tif <b>Entfr</b> kfy oddurs.
     */
    protfdtfd JButton dffbultButton;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis unusbblf fifld is no longfr usfd.
     * To ovfrridf tif dffbult button you siould rfplbdf tif <dodf>Adtion</dodf>
     * in tif <dodf>JRootPbnf</dodf>'s <dodf>AdtionMbp</dodf>. Plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     *  @sff #dffbultButton
     */
    @Dfprfdbtfd
    protfdtfd DffbultAdtion dffbultPrfssAdtion;
    /**
     * As of Jbvb 2 plbtform v1.3 tiis unusbblf fifld is no longfr usfd.
     * To ovfrridf tif dffbult button you siould rfplbdf tif <dodf>Adtion</dodf>
     * in tif <dodf>JRootPbnf</dodf>'s <dodf>AdtionMbp</dodf>. Plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     *  @sff #dffbultButton
     */
    @Dfprfdbtfd
    protfdtfd DffbultAdtion dffbultRflfbsfAdtion;

    /**
     * Wiftifr or not truf doublf bufffring siould bf usfd.  Tiis is typidblly
     * truf, but mby bf sft to fblsf in spfdibl situbtions.  For fxbmplf,
     * ifbvy wfigit popups (bbdkfd by b window) sft tiis to fblsf.
     */
    boolfbn usfTrufDoublfBufffring = truf;

    stbtid {
        LOG_DISABLE_TRUE_DOUBLE_BUFFERING =
            AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion(
                                   "swing.logDoublfBufffringDisbblf"));
        IGNORE_DISABLE_TRUE_DOUBLE_BUFFERING =
            AddfssControllfr.doPrivilfgfd(nfw GftBoolfbnAdtion(
                                   "swing.ignorfDoublfBufffringDisbblf"));
    }

    /**
     * Crfbtfs b <dodf>JRootPbnf</dodf>, sftting up its
     * <dodf>glbssPbnf</dodf>, <dodf>lbyfrfdPbnf</dodf>,
     * bnd <dodf>dontfntPbnf</dodf>.
     */
    publid JRootPbnf() {
        sftGlbssPbnf(drfbtfGlbssPbnf());
        sftLbyfrfdPbnf(drfbtfLbyfrfdPbnf());
        sftContfntPbnf(drfbtfContfntPbnf());
        sftLbyout(drfbtfRootLbyout());
        sftDoublfBufffrfd(truf);
        updbtfUI();
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid void sftDoublfBufffrfd(boolfbn bFlbg) {
        if (isDoublfBufffrfd() != bFlbg) {
            supfr.sftDoublfBufffrfd(bFlbg);
            RfpbintMbnbgfr.durrfntMbnbgfr(tiis).doublfBufffringCibngfd(tiis);
        }
    }

    /**
     * Rfturns b donstbnt idfntifying tif typf of Window dfdorbtions tif
     * <dodf>JRootPbnf</dodf> is providing.
     *
     * @rfturn Onf of <dodf>NONE</dodf>, <dodf>FRAME</dodf>,
     *        <dodf>PLAIN_DIALOG</dodf>, <dodf>INFORMATION_DIALOG</dodf>,
     *        <dodf>ERROR_DIALOG</dodf>, <dodf>COLOR_CHOOSER_DIALOG</dodf>,
     *        <dodf>FILE_CHOOSER_DIALOG</dodf>, <dodf>QUESTION_DIALOG</dodf> or
     *        <dodf>WARNING_DIALOG</dodf>.
     * @sff #sftWindowDfdorbtionStylf
     * @sindf 1.4
     */
    publid int gftWindowDfdorbtionStylf() {
        rfturn windowDfdorbtionStylf;
    }

    /**
     * Sfts tif typf of Window dfdorbtions (sudi bs bordfrs, widgfts for
     * dlosing b Window, titlf ...) tif <dodf>JRootPbnf</dodf> siould
     * providf. Tif dffbult is to providf no Window dfdorbtions
     * (<dodf>NONE</dodf>).
     * <p>
     * Tiis is only b iint, bnd somf look bnd fffls mby not support
     * tiis.
     * Tiis is b bound propfrty.
     *
     * @pbrbm windowDfdorbtionStylf Constbnt idfntifying Window dfdorbtions
     *        to providf.
     * @sff JDiblog#sftDffbultLookAndFfflDfdorbtfd
     * @sff JFrbmf#sftDffbultLookAndFfflDfdorbtfd
     * @sff LookAndFffl#gftSupportsWindowDfdorbtions
     * @tirows IllfgblArgumfntExdfption if <dodf>stylf</dodf> is
     *        not onf of: <dodf>NONE</dodf>, <dodf>FRAME</dodf>,
     *        <dodf>PLAIN_DIALOG</dodf>, <dodf>INFORMATION_DIALOG</dodf>,
     *        <dodf>ERROR_DIALOG</dodf>, <dodf>COLOR_CHOOSER_DIALOG</dodf>,
     *        <dodf>FILE_CHOOSER_DIALOG</dodf>, <dodf>QUESTION_DIALOG</dodf>, or
     *        <dodf>WARNING_DIALOG</dodf>.
     * @sindf 1.4
     * @bfbninfo
     *        bound: truf
     *         fnum: NONE                   JRootPbnf.NONE
     *               FRAME                  JRootPbnf.FRAME
     *               PLAIN_DIALOG           JRootPbnf.PLAIN_DIALOG
     *               INFORMATION_DIALOG     JRootPbnf.INFORMATION_DIALOG
     *               ERROR_DIALOG           JRootPbnf.ERROR_DIALOG
     *               COLOR_CHOOSER_DIALOG   JRootPbnf.COLOR_CHOOSER_DIALOG
     *               FILE_CHOOSER_DIALOG    JRootPbnf.FILE_CHOOSER_DIALOG
     *               QUESTION_DIALOG        JRootPbnf.QUESTION_DIALOG
     *               WARNING_DIALOG         JRootPbnf.WARNING_DIALOG
     *       fxpfrt: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Idfntififs tif typf of Window dfdorbtions to providf
     */
    publid void sftWindowDfdorbtionStylf(int windowDfdorbtionStylf) {
        if (windowDfdorbtionStylf < 0 ||
                  windowDfdorbtionStylf > WARNING_DIALOG) {
            tirow nfw IllfgblArgumfntExdfption("Invblid dfdorbtion stylf");
        }
        int oldWindowDfdorbtionStylf = gftWindowDfdorbtionStylf();
        tiis.windowDfdorbtionStylf = windowDfdorbtionStylf;
        firfPropfrtyCibngf("windowDfdorbtionStylf",
                            oldWindowDfdorbtionStylf,
                            windowDfdorbtionStylf);
    }

    /**
     * Rfturns tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @rfturn <dodf>LbbflUI</dodf> objfdt
     * @sindf 1.3
     */
    publid RootPbnfUI gftUI() {
        rfturn (RootPbnfUI)ui;
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>LbbflUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *      fxpfrt: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     * @sindf 1.3
     */
    publid void sftUI(RootPbnfUI ui) {
        supfr.sftUI(ui);
    }


    /**
     * Rfsfts tif UI propfrty to b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((RootPbnfUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns b string tibt spfdififs tif nbmf of tif L&bmp;F dlbss
     * tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "RootPbnfUI"
     *
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
      * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
      * <dodf>lbyfrfdPbnf</dodf>.
      * Bt dffbult it drfbtfs b nfw <dodf>JLbyfrfdPbnf</dodf>.
      * @rfturn tif dffbult <dodf>lbyfrfdPbnf</dodf>
      */
    protfdtfd JLbyfrfdPbnf drfbtfLbyfrfdPbnf() {
        JLbyfrfdPbnf p = nfw JLbyfrfdPbnf();
        p.sftNbmf(tiis.gftNbmf()+".lbyfrfdPbnf");
        rfturn p;
    }

    /**
     * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
     * <dodf>dontfntPbnf</dodf>.
     * By dffbult tiis mftiod drfbtfs b nfw <dodf>JComponfnt</dodf> bdd sfts b
     * <dodf>BordfrLbyout</dodf> bs its <dodf>LbyoutMbnbgfr</dodf>.
     * @rfturn tif dffbult <dodf>dontfntPbnf</dodf>
     */
    protfdtfd Contbinfr drfbtfContfntPbnf() {
        JComponfnt d = nfw JPbnfl();
        d.sftNbmf(tiis.gftNbmf()+".dontfntPbnf");
        d.sftLbyout(nfw BordfrLbyout() {
            /* Tiis BordfrLbyout subdlbss mbps b null donstrbint to CENTER.
             * Altiougi tif rfffrfndf BordfrLbyout blso dofs tiis, somf VMs
             * tirow bn IllfgblArgumfntExdfption.
             */
            publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {
                if (donstrbints == null) {
                    donstrbints = BordfrLbyout.CENTER;
                }
                supfr.bddLbyoutComponfnt(domp, donstrbints);
            }
        });
        rfturn d;
    }

    /**
      * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
      * <dodf>glbssPbnf</dodf>.
      * By dffbult tiis mftiod drfbtfs b nfw <dodf>JComponfnt</dodf>
      * witi visibility sft to fblsf.
      * @rfturn tif dffbult <dodf>glbssPbnf</dodf>
      */
    protfdtfd Componfnt drfbtfGlbssPbnf() {
        JComponfnt d = nfw JPbnfl();
        d.sftNbmf(tiis.gftNbmf()+".glbssPbnf");
        d.sftVisiblf(fblsf);
        ((JPbnfl)d).sftOpbquf(fblsf);
        rfturn d;
    }

    /**
     * Cbllfd by tif donstrudtor mftiods to drfbtf tif dffbult
     * <dodf>lbyoutMbnbgfr</dodf>.
     * @rfturn tif dffbult <dodf>lbyoutMbnbgfr</dodf>.
     */
    protfdtfd LbyoutMbnbgfr drfbtfRootLbyout() {
        rfturn nfw RootLbyout();
    }

    /**
     * Adds or dibngfs tif mfnu bbr usfd in tif lbyfrfd pbnf.
     * @pbrbm mfnu tif <dodf>JMfnuBbr</dodf> to bdd
     */
    publid void sftJMfnuBbr(JMfnuBbr mfnu) {
        if(mfnuBbr != null && mfnuBbr.gftPbrfnt() == lbyfrfdPbnf)
            lbyfrfdPbnf.rfmovf(mfnuBbr);
        mfnuBbr = mfnu;

        if(mfnuBbr != null)
            lbyfrfdPbnf.bdd(mfnuBbr, JLbyfrfdPbnf.FRAME_CONTENT_LAYER);
    }

    /**
     * Spfdififs tif mfnu bbr vbluf.
     * @dfprfdbtfd As of Swing vfrsion 1.0.3
     *  rfplbdfd by <dodf>sftJMfnuBbr(JMfnuBbr mfnu)</dodf>.
     * @pbrbm mfnu tif <dodf>JMfnuBbr</dodf> to bdd.
     */
    @Dfprfdbtfd
    publid void sftMfnuBbr(JMfnuBbr mfnu){
        if(mfnuBbr != null && mfnuBbr.gftPbrfnt() == lbyfrfdPbnf)
            lbyfrfdPbnf.rfmovf(mfnuBbr);
        mfnuBbr = mfnu;

        if(mfnuBbr != null)
            lbyfrfdPbnf.bdd(mfnuBbr, JLbyfrfdPbnf.FRAME_CONTENT_LAYER);
    }

    /**
     * Rfturns tif mfnu bbr from tif lbyfrfd pbnf.
     * @rfturn tif <dodf>JMfnuBbr</dodf> usfd in tif pbnf
     */
    publid JMfnuBbr gftJMfnuBbr() { rfturn mfnuBbr; }

    /**
     * Rfturns tif mfnu bbr vbluf.
     * @dfprfdbtfd As of Swing vfrsion 1.0.3
     *  rfplbdfd by <dodf>gftJMfnuBbr()</dodf>.
     * @rfturn tif <dodf>JMfnuBbr</dodf> usfd in tif pbnf
     */
    @Dfprfdbtfd
    publid JMfnuBbr gftMfnuBbr() { rfturn mfnuBbr; }

    /**
     * Sfts tif dontfnt pbnf -- tif dontbinfr tibt iolds tif domponfnts
     * pbrfntfd by tif root pbnf.
     * <p>
     * Swing's pbinting brdiitfdturf rfquirfs bn opbquf <dodf>JComponfnt</dodf>
     * in tif dontbinmfnt iifrbrdiy. Tiis is typidblly providfd by tif
     * dontfnt pbnf. If you rfplbdf tif dontfnt pbnf it is rfdommfndfd you
     * rfplbdf it witi bn opbquf <dodf>JComponfnt</dodf>.
     *
     * @pbrbm dontfnt tif <dodf>Contbinfr</dodf> to usf for domponfnt-dontfnts
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif dontfnt pbnf pbrbmftfr is <dodf>null</dodf>
     */
    publid void sftContfntPbnf(Contbinfr dontfnt) {
        if(dontfnt == null)
            tirow nfw IllfgblComponfntStbtfExdfption("dontfntPbnf dbnnot bf sft to null.");
        if(dontfntPbnf != null && dontfntPbnf.gftPbrfnt() == lbyfrfdPbnf)
            lbyfrfdPbnf.rfmovf(dontfntPbnf);
        dontfntPbnf = dontfnt;

        lbyfrfdPbnf.bdd(dontfntPbnf, JLbyfrfdPbnf.FRAME_CONTENT_LAYER);
    }

    /**
     * Rfturns tif dontfnt pbnf -- tif dontbinfr tibt iolds tif domponfnts
     * pbrfntfd by tif root pbnf.
     *
     * @rfturn tif <dodf>Contbinfr</dodf> tibt iolds tif domponfnt-dontfnts
     */
    publid Contbinfr gftContfntPbnf() { rfturn dontfntPbnf; }

// PENDING(klobbd) Siould tiis rfpbrfnt tif dontfntPbnf bnd MfnuBbr?
    /**
     * Sfts tif lbyfrfd pbnf for tif root pbnf. Tif lbyfrfd pbnf
     * typidblly iolds b dontfnt pbnf bnd bn optionbl <dodf>JMfnuBbr</dodf>.
     *
     * @pbrbm lbyfrfd  tif <dodf>JLbyfrfdPbnf</dodf> to usf
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif lbyfrfd pbnf pbrbmftfr is <dodf>null</dodf>
     */
    publid void sftLbyfrfdPbnf(JLbyfrfdPbnf lbyfrfd) {
        if(lbyfrfd == null)
            tirow nfw IllfgblComponfntStbtfExdfption("lbyfrfdPbnf dbnnot bf sft to null.");
        if(lbyfrfdPbnf != null && lbyfrfdPbnf.gftPbrfnt() == tiis)
            tiis.rfmovf(lbyfrfdPbnf);
        lbyfrfdPbnf = lbyfrfd;

        tiis.bdd(lbyfrfdPbnf, -1);
    }
    /**
     * Gfts tif lbyfrfd pbnf usfd by tif root pbnf. Tif lbyfrfd pbnf
     * typidblly iolds b dontfnt pbnf bnd bn optionbl <dodf>JMfnuBbr</dodf>.
     *
     * @rfturn tif <dodf>JLbyfrfdPbnf</dodf> durrfntly in usf
     */
    publid JLbyfrfdPbnf gftLbyfrfdPbnf() { rfturn lbyfrfdPbnf; }

    /**
     * Sfts b spfdififd <dodf>Componfnt</dodf> to bf tif glbss pbnf for tiis
     * root pbnf.  Tif glbss pbnf siould normblly bf b ligitwfigit,
     * trbnspbrfnt domponfnt, bfdbusf it will bf mbdf visiblf wifn
     * fvfr tif root pbnf nffds to grbb input fvfnts.
     * <p>
     * Tif nfw glbss pbnf's visibility is dibngfd to mbtdi tibt of
     * tif durrfnt glbss pbnf.  An implidbtion of tiis is tibt dbrf
     * must bf tbkfn wifn you wbnt to rfplbdf tif glbss pbnf bnd
     * mbkf it visiblf.  Eitifr of tif following will work:
     * <prf>
     *   root.sftGlbssPbnf(nfwGlbssPbnf);
     *   nfwGlbssPbnf.sftVisiblf(truf);
     * </prf>
     * or:
     * <prf>
     *   root.gftGlbssPbnf().sftVisiblf(truf);
     *   root.sftGlbssPbnf(nfwGlbssPbnf);
     * </prf>
     *
     * @pbrbm glbss tif <dodf>Componfnt</dodf> to usf bs tif glbss pbnf
     *              for tiis <dodf>JRootPbnf</dodf>
     * @fxdfption NullPointfrExdfption if tif <dodf>glbss</dodf> pbrbmftfr is
     *          <dodf>null</dodf>
     */
    publid void sftGlbssPbnf(Componfnt glbss) {
        if (glbss == null) {
            tirow nfw NullPointfrExdfption("glbssPbnf dbnnot bf sft to null.");
        }

        AWTAddfssor.gftComponfntAddfssor().sftMixingCutoutSibpf(glbss,
                nfw Rfdtbnglf());

        boolfbn visiblf = fblsf;
        if (glbssPbnf != null && glbssPbnf.gftPbrfnt() == tiis) {
            tiis.rfmovf(glbssPbnf);
            visiblf = glbssPbnf.isVisiblf();
        }

        glbss.sftVisiblf(visiblf);
        glbssPbnf = glbss;
        tiis.bdd(glbssPbnf, 0);
        if (visiblf) {
            rfpbint();
        }
    }

    /**
     * Rfturns tif durrfnt glbss pbnf for tiis <dodf>JRootPbnf</dodf>.
     * @rfturn tif durrfnt glbss pbnf
     * @sff #sftGlbssPbnf
     */
    publid Componfnt gftGlbssPbnf() {
        rfturn glbssPbnf;
    }

    /**
     * If b dfsdfndbnt of tiis <dodf>JRootPbnf</dodf> dblls
     * <dodf>rfvblidbtf</dodf>, vblidbtf from ifrf on down.
     *<p>
     * Dfffrrfd rfqufsts to lbyout b domponfnt bnd its dfsdfndfnts bgbin.
     * For fxbmplf, dblls to <dodf>rfvblidbtf</dodf>, brf pusifd upwbrds to
     * fitifr b <dodf>JRootPbnf</dodf> or b <dodf>JSdrollPbnf</dodf>
     * bfdbusf boti dlbssfs ovfrridf <dodf>isVblidbtfRoot</dodf> to rfturn truf.
     *
     * @sff JComponfnt#isVblidbtfRoot
     * @sff jbvb.bwt.Contbinfr#isVblidbtfRoot
     * @rfturn truf
     */
    @Ovfrridf
    publid boolfbn isVblidbtfRoot() {
        rfturn truf;
    }

    /**
     * Tif <dodf>glbssPbnf</dodf> bnd <dodf>dontfntPbnf</dodf>
     * ibvf tif sbmf bounds, wiidi mfbns <dodf>JRootPbnf</dodf>
     * dofs not tilfs its diildrfn bnd tiis siould rfturn fblsf.
     * On tif otifr ibnd, tif <dodf>glbssPbnf</dodf>
     * is normblly not visiblf, bnd so tiis dbn rfturn truf if tif
     * <dodf>glbssPbnf</dodf> isn't visiblf. Tifrfforf, tif
     * rfturn vbluf ifrf dfpfnds upon tif visibility of tif
     * <dodf>glbssPbnf</dodf>.
     *
     * @rfturn truf if tiis domponfnt's diildrfn don't ovfrlbp
     */
    publid boolfbn isOptimizfdDrbwingEnbblfd() {
        rfturn !glbssPbnf.isVisiblf();
    }

    /**
     * {@inifritDod}
     */
    publid void bddNotify() {
        supfr.bddNotify();
        fnbblfEvfnts(AWTEvfnt.KEY_EVENT_MASK);
    }

    /**
     * {@inifritDod}
     */
    publid void rfmovfNotify() {
        supfr.rfmovfNotify();
    }


    /**
     * Sfts tif <dodf>dffbultButton</dodf> propfrty,
     * wiidi dftfrminfs tif durrfnt dffbult button for tiis <dodf>JRootPbnf</dodf>.
     * Tif dffbult button is tif button wiidi will bf bdtivbtfd
     * wifn b UI-dffinfd bdtivbtion fvfnt (typidblly tif <b>Entfr</b> kfy)
     * oddurs in tif root pbnf rfgbrdlfss of wiftifr or not tif button
     * ibs kfybobrd fodus (unlfss tifrf is bnotifr domponfnt witiin
     * tif root pbnf wiidi donsumfs tif bdtivbtion fvfnt,
     * sudi bs b <dodf>JTfxtPbnf</dodf>).
     * For dffbult bdtivbtion to work, tif button must bf bn fnbblfd
     * dfsdfndfnt of tif root pbnf wifn bdtivbtion oddurs.
     * To rfmovf b dffbult button from tiis root pbnf, sft tiis
     * propfrty to <dodf>null</dodf>.
     *
     * @sff JButton#isDffbultButton
     * @pbrbm dffbultButton tif <dodf>JButton</dodf> wiidi is to bf tif dffbult button
     *
     * @bfbninfo
     *  dfsdription: Tif button bdtivbtfd by dffbult in tiis root pbnf
     */
    publid void sftDffbultButton(JButton dffbultButton) {
        JButton oldDffbult = tiis.dffbultButton;

        if (oldDffbult != dffbultButton) {
            tiis.dffbultButton = dffbultButton;

            if (oldDffbult != null) {
                oldDffbult.rfpbint();
            }
            if (dffbultButton != null) {
                dffbultButton.rfpbint();
            }
        }

        firfPropfrtyCibngf("dffbultButton", oldDffbult, dffbultButton);
    }

    /**
     * Rfturns tif vbluf of tif <dodf>dffbultButton</dodf> propfrty.
     * @rfturn tif <dodf>JButton</dodf> wiidi is durrfntly tif dffbult button
     * @sff #sftDffbultButton
     */
    publid JButton gftDffbultButton() {
        rfturn dffbultButton;
    }

    finbl void sftUsfTrufDoublfBufffring(boolfbn usfTrufDoublfBufffring) {
        tiis.usfTrufDoublfBufffring = usfTrufDoublfBufffring;
    }

    finbl boolfbn gftUsfTrufDoublfBufffring() {
        rfturn usfTrufDoublfBufffring;
    }

    finbl void disbblfTrufDoublfBufffring() {
        if (usfTrufDoublfBufffring) {
            if (!IGNORE_DISABLE_TRUE_DOUBLE_BUFFERING) {
                if (LOG_DISABLE_TRUE_DOUBLE_BUFFERING) {
                    Systfm.out.println("Disbbling truf doublf bufffring for " +
                                       tiis);
                    Tirfbd.dumpStbdk();
                }
                usfTrufDoublfBufffring = fblsf;
                RfpbintMbnbgfr.durrfntMbnbgfr(tiis).
                        doublfBufffringCibngfd(tiis);
            }
        }
    }

    @SupprfssWbrnings("sfribl")
    stbtid dlbss DffbultAdtion fxtfnds AbstrbdtAdtion {
        JButton ownfr;
        JRootPbnf root;
        boolfbn prfss;
        DffbultAdtion(JRootPbnf root, boolfbn prfss) {
            tiis.root = root;
            tiis.prfss = prfss;
        }
        publid void sftOwnfr(JButton ownfr) {
            tiis.ownfr = ownfr;
        }
        publid void bdtionPfrformfd(AdtionEvfnt f) {
            if (ownfr != null && SwingUtilitifs.gftRootPbnf(ownfr) == root) {
                ButtonModfl modfl = ownfr.gftModfl();
                if (prfss) {
                    modfl.sftArmfd(truf);
                    modfl.sftPrfssfd(truf);
                } flsf {
                    modfl.sftPrfssfd(fblsf);
                }
            }
        }
        publid boolfbn isEnbblfd() {
            rfturn ownfr.gftModfl().isEnbblfd();
        }
    }


    /**
     * Ovfrriddfn to fnfordf tif position of tif glbss domponfnt bs
     * tif zfro diild.
     *
     * @pbrbm domp tif domponfnt to bf fnibndfd
     * @pbrbm donstrbints tif donstrbints to bf rfspfdtfd
     * @pbrbm indfx tif indfx
     */
    protfdtfd void bddImpl(Componfnt domp, Objfdt donstrbints, int indfx) {
        supfr.bddImpl(domp, donstrbints, indfx);

        /// Wf brf mbking surf tif glbssPbnf is on top.
        if(glbssPbnf != null
            && glbssPbnf.gftPbrfnt() == tiis
            && gftComponfnt(0) != glbssPbnf) {
            bdd(glbssPbnf, 0);
        }
    }


///////////////////////////////////////////////////////////////////////////////
//// Bfgin Innfr Clbssfs
///////////////////////////////////////////////////////////////////////////////


    /**
     * A dustom lbyout mbnbgfr tibt is rfsponsiblf for tif lbyout of
     * lbyfrfdPbnf, glbssPbnf, bnd mfnuBbr.
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
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss RootLbyout implfmfnts LbyoutMbnbgfr2, Sfriblizbblf
    {
        /**
         * Rfturns tif bmount of spbdf tif lbyout would likf to ibvf.
         *
         * @pbrbm pbrfnt tif Contbinfr for wiidi tiis lbyout mbnbgfr
         * is bfing usfd
         * @rfturn b Dimfnsion objfdt dontbining tif lbyout's prfffrrfd sizf
         */
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
            Dimfnsion rd, mbd;
            Insfts i = gftInsfts();

            if(dontfntPbnf != null) {
                rd = dontfntPbnf.gftPrfffrrfdSizf();
            } flsf {
                rd = pbrfnt.gftSizf();
            }
            if(mfnuBbr != null && mfnuBbr.isVisiblf()) {
                mbd = mfnuBbr.gftPrfffrrfdSizf();
            } flsf {
                mbd = nfw Dimfnsion(0, 0);
            }
            rfturn nfw Dimfnsion(Mbti.mbx(rd.widti, mbd.widti) + i.lfft + i.rigit,
                                        rd.ifigit + mbd.ifigit + i.top + i.bottom);
        }

        /**
         * Rfturns tif minimum bmount of spbdf tif lbyout nffds.
         *
         * @pbrbm pbrfnt tif Contbinfr for wiidi tiis lbyout mbnbgfr
         * is bfing usfd
         * @rfturn b Dimfnsion objfdt dontbining tif lbyout's minimum sizf
         */
        publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
            Dimfnsion rd, mbd;
            Insfts i = gftInsfts();
            if(dontfntPbnf != null) {
                rd = dontfntPbnf.gftMinimumSizf();
            } flsf {
                rd = pbrfnt.gftSizf();
            }
            if(mfnuBbr != null && mfnuBbr.isVisiblf()) {
                mbd = mfnuBbr.gftMinimumSizf();
            } flsf {
                mbd = nfw Dimfnsion(0, 0);
            }
            rfturn nfw Dimfnsion(Mbti.mbx(rd.widti, mbd.widti) + i.lfft + i.rigit,
                        rd.ifigit + mbd.ifigit + i.top + i.bottom);
        }

        /**
         * Rfturns tif mbximum bmount of spbdf tif lbyout dbn usf.
         *
         * @pbrbm tbrgft tif Contbinfr for wiidi tiis lbyout mbnbgfr
         * is bfing usfd
         * @rfturn b Dimfnsion objfdt dontbining tif lbyout's mbximum sizf
         */
        publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
            Dimfnsion rd, mbd;
            Insfts i = gftInsfts();
            if(mfnuBbr != null && mfnuBbr.isVisiblf()) {
                mbd = mfnuBbr.gftMbximumSizf();
            } flsf {
                mbd = nfw Dimfnsion(0, 0);
            }
            if(dontfntPbnf != null) {
                rd = dontfntPbnf.gftMbximumSizf();
            } flsf {
                // Tiis is silly, but siould stop bn ovfrflow frror
                rd = nfw Dimfnsion(Intfgfr.MAX_VALUE,
                        Intfgfr.MAX_VALUE - i.top - i.bottom - mbd.ifigit - 1);
            }
            rfturn nfw Dimfnsion(Mbti.min(rd.widti, mbd.widti) + i.lfft + i.rigit,
                                         rd.ifigit + mbd.ifigit + i.top + i.bottom);
        }

        /**
         * Instrudts tif lbyout mbnbgfr to pfrform tif lbyout for tif spfdififd
         * dontbinfr.
         *
         * @pbrbm pbrfnt tif Contbinfr for wiidi tiis lbyout mbnbgfr
         * is bfing usfd
         */
        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            Rfdtbnglf b = pbrfnt.gftBounds();
            Insfts i = gftInsfts();
            int dontfntY = 0;
            int w = b.widti - i.rigit - i.lfft;
            int i = b.ifigit - i.top - i.bottom;

            if(lbyfrfdPbnf != null) {
                lbyfrfdPbnf.sftBounds(i.lfft, i.top, w, i);
            }
            if(glbssPbnf != null) {
                glbssPbnf.sftBounds(i.lfft, i.top, w, i);
            }
            // Notf: Tiis is lbying out tif diildrfn in tif lbyfrfdPbnf,
            // tfdinidblly, tifsf brf not our diildrfn.
            if(mfnuBbr != null && mfnuBbr.isVisiblf()) {
                Dimfnsion mbd = mfnuBbr.gftPrfffrrfdSizf();
                mfnuBbr.sftBounds(0, 0, w, mbd.ifigit);
                dontfntY += mbd.ifigit;
            }
            if(dontfntPbnf != null) {
                dontfntPbnf.sftBounds(0, dontfntY, w, i - dontfntY);
            }
        }

        publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {}
        publid void rfmovfLbyoutComponfnt(Componfnt domp) {}
        publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {}
        publid flobt gftLbyoutAlignmfntX(Contbinfr tbrgft) { rfturn 0.0f; }
        publid flobt gftLbyoutAlignmfntY(Contbinfr tbrgft) { rfturn 0.0f; }
        publid void invblidbtfLbyout(Contbinfr tbrgft) {}
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JRootPbnf</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JRootPbnf</dodf>.
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JRootPbnf</dodf>. For root pbnfs, tif
     * <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJRootPbnf</dodf>.
     * A nfw <dodf>AddfssiblfJRootPbnf</dodf> instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJRootPbnf</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis <dodf>JRootPbnf</dodf>
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJRootPbnf();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JRootPbnf</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to root pbnf usfr-intfrfbdf flfmfnts.
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
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfJRootPbnf fxtfnds AddfssiblfJComponfnt {
        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of
         * tif objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.ROOT_PANE;
        }

        /**
         * Rfturns tif numbfr of bddfssiblf diildrfn of tif objfdt.
         *
         * @rfturn tif numbfr of bddfssiblf diildrfn of tif objfdt.
         */
        publid int gftAddfssiblfCiildrfnCount() {
            rfturn supfr.gftAddfssiblfCiildrfnCount();
        }

        /**
         * Rfturns tif spfdififd Addfssiblf diild of tif objfdt.  Tif Addfssiblf
         * diildrfn of bn Addfssiblf objfdt brf zfro-bbsfd, so tif first diild
         * of bn Addfssiblf diild is bt indfx 0, tif sfdond diild is bt indfx 1,
         * bnd so on.
         *
         * @pbrbm i zfro-bbsfd indfx of diild
         * @rfturn tif Addfssiblf diild of tif objfdt
         * @sff #gftAddfssiblfCiildrfnCount
         */
        publid Addfssiblf gftAddfssiblfCiild(int i) {
            rfturn supfr.gftAddfssiblfCiild(i);
        }
    } // innfr dlbss AddfssiblfJRootPbnf
}
