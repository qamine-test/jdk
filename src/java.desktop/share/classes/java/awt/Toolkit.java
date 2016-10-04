/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.pffr.*;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.bwt.imbgf.ImbgfObsfrvfr;
import jbvb.bwt.imbgf.ImbgfProdudfr;
import jbvb.bwt.imbgf.ColorModfl;
import jbvb.bwt.dbtbtrbnsffr.Clipbobrd;
import jbvb.bwt.dnd.DrbgSourdf;
import jbvb.bwt.dnd.DrbgGfsturfRfdognizfr;
import jbvb.bwt.dnd.DrbgGfsturfEvfnt;
import jbvb.bwt.dnd.DrbgGfsturfListfnfr;
import jbvb.bwt.dnd.InvblidDnDOpfrbtionExdfption;
import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;
import jbvb.nft.URL;
import jbvb.io.Filf;
import jbvb.io.FilfInputStrfbm;

import jbvb.util.*;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvb.bfbns.PropfrtyCibngfSupport;
import sun.bwt.AppContfxt;

import sun.bwt.HfbdlfssToolkit;
import sun.bwt.NullComponfntPffr;
import sun.bwt.PffrEvfnt;
import sun.bwt.SunToolkit;
import sun.bwt.AWTAddfssor;
import sun.bwt.AWTPfrmissions;

import sun.util.CorfRfsourdfBundlfControl;

/**
 * Tiis dlbss is tif bbstrbdt supfrdlbss of bll bdtubl
 * implfmfntbtions of tif Abstrbdt Window Toolkit. Subdlbssfs of
 * tif <dodf>Toolkit</dodf> dlbss brf usfd to bind tif vbrious domponfnts
 * to pbrtidulbr nbtivf toolkit implfmfntbtions.
 * <p>
 * Mbny GUI fvfnts mby bf dflivfrfd to usfr
 * bsyndironously, if tif oppositf is not spfdififd fxpliditly.
 * As wfll bs
 * mbny GUI opfrbtions mby bf pfrformfd bsyndironously.
 * Tiis fbdt mfbns tibt if tif stbtf of b domponfnt is sft, bnd tifn
 * tif stbtf immfdibtfly qufrifd, tif rfturnfd vbluf mby not yft
 * rfflfdt tif rfqufstfd dibngf.  Tiis bfibvior indludfs, but is not
 * limitfd to:
 * <ul>
 * <li>Sdrolling to b spfdififd position.
 * <br>For fxbmplf, dblling <dodf>SdrollPbnf.sftSdrollPosition</dodf>
 *     bnd tifn <dodf>gftSdrollPosition</dodf> mby rfturn bn indorrfdt
 *     vbluf if tif originbl rfqufst ibs not yft bffn prodfssfd.
 *
 * <li>Moving tif fodus from onf domponfnt to bnotifr.
 * <br>For morf informbtion, sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/fodus.itml#trbnsffrTiming">Timing
 * Fodus Trbnsffrs</b>, b sfdtion in
 * <b irff="ittp://jbvb.sun.dom/dods/books/tutoribl/uiswing/">Tif Swing
 * Tutoribl</b>.
 *
 * <li>Mbking b top-lfvfl dontbinfr visiblf.
 * <br>Cblling <dodf>sftVisiblf(truf)</dodf> on b <dodf>Window</dodf>,
 *     <dodf>Frbmf</dodf> or <dodf>Diblog</dodf> mby oddur
 *     bsyndironously.
 *
 * <li>Sftting tif sizf or lodbtion of b top-lfvfl dontbinfr.
 * <br>Cblls to <dodf>sftSizf</dodf>, <dodf>sftBounds</dodf> or
 *     <dodf>sftLodbtion</dodf> on b <dodf>Window</dodf>,
 *     <dodf>Frbmf</dodf> or <dodf>Diblog</dodf> brf forwbrdfd
 *     to tif undfrlying window mbnbgfmfnt systfm bnd mby bf
 *     ignorfd or modififd.  Sff {@link jbvb.bwt.Window} for
 *     morf informbtion.
 * </ul>
 * <p>
 * Most bpplidbtions siould not dbll bny of tif mftiods in tiis
 * dlbss dirfdtly. Tif mftiods dffinfd by <dodf>Toolkit</dodf> brf
 * tif "gluf" tibt joins tif plbtform-indfpfndfnt dlbssfs in tif
 * <dodf>jbvb.bwt</dodf> pbdkbgf witi tifir dountfrpbrts in
 * <dodf>jbvb.bwt.pffr</dodf>. Somf mftiods dffinfd by
 * <dodf>Toolkit</dodf> qufry tif nbtivf opfrbting systfm dirfdtly.
 *
 * @butior      Sbmi Sibio
 * @butior      Artiur vbn Hoff
 * @butior      Frfd Edks
 * @sindf       1.0
 */
publid bbstrbdt dlbss Toolkit {

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of tif <dodf>Dfsktop</dodf>
     * using tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif dfsktop to bf implfmfntfd
     * @rfturn    tiis toolkit's implfmfntbtion of tif <dodf>Dfsktop</dodf>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Dfsktop
     * @sff       jbvb.bwt.pffr.DfsktopPffr
     * @sindf 1.6
     */
    protfdtfd bbstrbdt DfsktopPffr drfbtfDfsktopPffr(Dfsktop tbrgft)
      tirows HfbdlfssExdfption;


    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Button</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif button to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Button</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Button
     * @sff       jbvb.bwt.pffr.ButtonPffr
     */
    protfdtfd bbstrbdt ButtonPffr drfbtfButton(Button tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>TfxtFifld</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif tfxt fifld to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>TfxtFifld</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.TfxtFifld
     * @sff       jbvb.bwt.pffr.TfxtFifldPffr
     */
    protfdtfd bbstrbdt TfxtFifldPffr drfbtfTfxtFifld(TfxtFifld tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Lbbfl</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif lbbfl to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Lbbfl</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Lbbfl
     * @sff       jbvb.bwt.pffr.LbbflPffr
     */
    protfdtfd bbstrbdt LbbflPffr drfbtfLbbfl(Lbbfl tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>List</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif list to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>List</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.List
     * @sff       jbvb.bwt.pffr.ListPffr
     */
    protfdtfd bbstrbdt ListPffr drfbtfList(jbvb.bwt.List tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Cifdkbox</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif difdk box to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Cifdkbox</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Cifdkbox
     * @sff       jbvb.bwt.pffr.CifdkboxPffr
     */
    protfdtfd bbstrbdt CifdkboxPffr drfbtfCifdkbox(Cifdkbox tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Sdrollbbr</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif sdroll bbr to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Sdrollbbr</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Sdrollbbr
     * @sff       jbvb.bwt.pffr.SdrollbbrPffr
     */
    protfdtfd bbstrbdt SdrollbbrPffr drfbtfSdrollbbr(Sdrollbbr tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>SdrollPbnf</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif sdroll pbnf to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>SdrollPbnf</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.SdrollPbnf
     * @sff       jbvb.bwt.pffr.SdrollPbnfPffr
     * @sindf     1.1
     */
    protfdtfd bbstrbdt SdrollPbnfPffr drfbtfSdrollPbnf(SdrollPbnf tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>TfxtArfb</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif tfxt brfb to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>TfxtArfb</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.TfxtArfb
     * @sff       jbvb.bwt.pffr.TfxtArfbPffr
     */
    protfdtfd bbstrbdt TfxtArfbPffr drfbtfTfxtArfb(TfxtArfb tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Cioidf</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif dioidf to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Cioidf</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Cioidf
     * @sff       jbvb.bwt.pffr.CioidfPffr
     */
    protfdtfd bbstrbdt CioidfPffr drfbtfCioidf(Cioidf tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Frbmf</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif frbmf to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Frbmf</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Frbmf
     * @sff       jbvb.bwt.pffr.FrbmfPffr
     */
    protfdtfd bbstrbdt FrbmfPffr drfbtfFrbmf(Frbmf tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Cbnvbs</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif dbnvbs to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Cbnvbs</dodf>.
     * @sff       jbvb.bwt.Cbnvbs
     * @sff       jbvb.bwt.pffr.CbnvbsPffr
     */
    protfdtfd bbstrbdt CbnvbsPffr       drfbtfCbnvbs(Cbnvbs tbrgft);

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Pbnfl</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif pbnfl to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Pbnfl</dodf>.
     * @sff       jbvb.bwt.Pbnfl
     * @sff       jbvb.bwt.pffr.PbnflPffr
     */
    protfdtfd bbstrbdt PbnflPffr        drfbtfPbnfl(Pbnfl tbrgft);

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Window</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif window to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Window</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Window
     * @sff       jbvb.bwt.pffr.WindowPffr
     */
    protfdtfd bbstrbdt WindowPffr drfbtfWindow(Window tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Diblog</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif diblog to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Diblog</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Diblog
     * @sff       jbvb.bwt.pffr.DiblogPffr
     */
    protfdtfd bbstrbdt DiblogPffr drfbtfDiblog(Diblog tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>MfnuBbr</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif mfnu bbr to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>MfnuBbr</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.MfnuBbr
     * @sff       jbvb.bwt.pffr.MfnuBbrPffr
     */
    protfdtfd bbstrbdt MfnuBbrPffr drfbtfMfnuBbr(MfnuBbr tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Mfnu</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif mfnu to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Mfnu</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.Mfnu
     * @sff       jbvb.bwt.pffr.MfnuPffr
     */
    protfdtfd bbstrbdt MfnuPffr drfbtfMfnu(Mfnu tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>PopupMfnu</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif popup mfnu to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>PopupMfnu</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.PopupMfnu
     * @sff       jbvb.bwt.pffr.PopupMfnuPffr
     * @sindf     1.1
     */
    protfdtfd bbstrbdt PopupMfnuPffr drfbtfPopupMfnu(PopupMfnu tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>MfnuItfm</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif mfnu itfm to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>MfnuItfm</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.MfnuItfm
     * @sff       jbvb.bwt.pffr.MfnuItfmPffr
     */
    protfdtfd bbstrbdt MfnuItfmPffr drfbtfMfnuItfm(MfnuItfm tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>FilfDiblog</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif filf diblog to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>FilfDiblog</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.FilfDiblog
     * @sff       jbvb.bwt.pffr.FilfDiblogPffr
     */
    protfdtfd bbstrbdt FilfDiblogPffr drfbtfFilfDiblog(FilfDiblog tbrgft)
        tirows HfbdlfssExdfption;

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>CifdkboxMfnuItfm</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     tbrgft tif difdkbox mfnu itfm to bf implfmfntfd.
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>CifdkboxMfnuItfm</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.CifdkboxMfnuItfm
     * @sff       jbvb.bwt.pffr.CifdkboxMfnuItfmPffr
     */
    protfdtfd bbstrbdt CifdkboxMfnuItfmPffr drfbtfCifdkboxMfnuItfm(
        CifdkboxMfnuItfm tbrgft) tirows HfbdlfssExdfption;

    /**
     * Obtbins tiis toolkit's implfmfntbtion of iflpfr dlbss for
     * <dodf>MousfInfo</dodf> opfrbtions.
     * @rfturn    tiis toolkit's implfmfntbtion of  iflpfr for <dodf>MousfInfo</dodf>
     * @tirows    UnsupportfdOpfrbtionExdfption if tiis opfrbtion is not implfmfntfd
     * @sff       jbvb.bwt.pffr.MousfInfoPffr
     * @sff       jbvb.bwt.MousfInfo
     * @sindf 1.5
     */
    protfdtfd MousfInfoPffr gftMousfInfoPffr() {
        tirow nfw UnsupportfdOpfrbtionExdfption("Not implfmfntfd");
    }

    privbtf stbtid LigitwfigitPffr ligitwfigitMbrkfr;

    /**
     * Crfbtfs b pffr for b domponfnt or dontbinfr.  Tiis pffr is windowlfss
     * bnd bllows tif Componfnt bnd Contbinfr dlbssfs to bf fxtfndfd dirfdtly
     * to drfbtf windowlfss domponfnts tibt brf dffinfd fntirfly in jbvb.
     *
     * @pbrbm  tbrgft Tif Componfnt to bf drfbtfd.
     * @rfturn tif pffr for tif spfdififd domponfnt
     */
    protfdtfd LigitwfigitPffr drfbtfComponfnt(Componfnt tbrgft) {
        if (ligitwfigitMbrkfr == null) {
            ligitwfigitMbrkfr = nfw NullComponfntPffr();
        }
        rfturn ligitwfigitMbrkfr;
    }

    /**
     * Crfbtfs tiis toolkit's implfmfntbtion of <dodf>Font</dodf> using
     * tif spfdififd pffr intfrfbdf.
     * @pbrbm     nbmf tif font to bf implfmfntfd
     * @pbrbm     stylf tif stylf of tif font, sudi bs <dodf>PLAIN</dodf>,
     *            <dodf>BOLD</dodf>, <dodf>ITALIC</dodf>, or b dombinbtion
     * @rfturn    tiis toolkit's implfmfntbtion of <dodf>Font</dodf>
     * @sff       jbvb.bwt.Font
     * @sff       jbvb.bwt.pffr.FontPffr
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#gftAllFonts
     * @dfprfdbtfd  sff jbvb.bwt.GrbpiidsEnvironmfnt#gftAllFonts
     */
    @Dfprfdbtfd
    protfdtfd bbstrbdt FontPffr gftFontPffr(String nbmf, int stylf);

    // Tif following mftiod is dbllfd by tif privbtf mftiod
    // <dodf>updbtfSystfmColors</dodf> in <dodf>SystfmColor</dodf>.

    /**
     * Fills in tif intfgfr brrby tibt is supplifd bs bn brgumfnt
     * witi tif durrfnt systfm dolor vblufs.
     *
     * @pbrbm     systfmColors bn intfgfr brrby.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.1
     */
    protfdtfd void lobdSystfmColors(int[] systfmColors)
        tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
    }

    /**
     * Controls wiftifr tif lbyout of Contbinfrs is vblidbtfd dynbmidblly
     * during rfsizing, or stbtidblly, bftfr rfsizing is domplftf.
     * Usf {@dodf isDynbmidLbyoutAdtivf()} to dftfdt if tiis ffbturf fnbblfd
     * in tiis progrbm bnd is supportfd by tiis opfrbting systfm
     * bnd/or window mbnbgfr.
     * Notf tibt tiis ffbturf is supportfd not on bll plbtforms, bnd
     * donvfrsfly, tibt tiis ffbturf dbnnot bf turnfd off on somf plbtforms.
     * On tifsf plbtforms wifrf dynbmid lbyout during rfsizing is not supportfd
     * (or is blwbys supportfd), sftting tiis propfrty ibs no ffffdt.
     * Notf tibt tiis ffbturf dbn bf sft or unsft bs b propfrty of tif
     * opfrbting systfm or window mbnbgfr on somf plbtforms.  On sudi
     * plbtforms, tif dynbmid rfsizf propfrty must bf sft bt tif opfrbting
     * systfm or window mbnbgfr lfvfl bfforf tiis mftiod dbn tbkf ffffdt.
     * Tiis mftiod dofs not dibngf support or sfttings of tif undfrlying
     * opfrbting systfm or
     * window mbnbgfr.  Tif OS/WM support dbn bf
     * qufrifd using gftDfsktopPropfrty("bwt.dynbmidLbyoutSupportfd") mftiod.
     *
     * @pbrbm     dynbmid  If truf, Contbinfrs siould rf-lbyout tifir
     *            domponfnts bs tif Contbinfr is bfing rfsizfd.  If fblsf,
     *            tif lbyout will bf vblidbtfd bftfr rfsizing is domplftfd.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff       #isDynbmidLbyoutSft()
     * @sff       #isDynbmidLbyoutAdtivf()
     * @sff       #gftDfsktopPropfrty(String propfrtyNbmf)
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     */
    publid void sftDynbmidLbyout(finbl boolfbn dynbmid)
        tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        if (tiis != gftDffbultToolkit()) {
            gftDffbultToolkit().sftDynbmidLbyout(dynbmid);
        }
    }

    /**
     * Rfturns wiftifr tif lbyout of Contbinfrs is vblidbtfd dynbmidblly
     * during rfsizing, or stbtidblly, bftfr rfsizing is domplftf.
     * Notf: tiis mftiod rfturns tif vbluf tibt wbs sft progrbmmbtidblly;
     * it dofs not rfflfdt support bt tif lfvfl of tif opfrbting systfm
     * or window mbnbgfr for dynbmid lbyout on rfsizing, or tif durrfnt
     * opfrbting systfm or window mbnbgfr sfttings.  Tif OS/WM support dbn
     * bf qufrifd using gftDfsktopPropfrty("bwt.dynbmidLbyoutSupportfd").
     *
     * @rfturn    truf if vblidbtion of Contbinfrs is donf dynbmidblly,
     *            fblsf if vblidbtion is donf bftfr rfsizing is finisifd.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     * @sff       #sftDynbmidLbyout(boolfbn dynbmid)
     * @sff       #isDynbmidLbyoutAdtivf()
     * @sff       #gftDfsktopPropfrty(String propfrtyNbmf)
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     */
    protfdtfd boolfbn isDynbmidLbyoutSft()
        tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().isDynbmidLbyoutSft();
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns wiftifr dynbmid lbyout of Contbinfrs on rfsizf is
     * durrfntly bdtivf (boti sft in progrbm
     *( {@dodf isDynbmidLbyoutSft()} )
     *, bnd supportfd
     * by tif undfrlying opfrbting systfm bnd/or window mbnbgfr).
     * If dynbmid lbyout is durrfntly inbdtivf tifn Contbinfrs
     * rf-lbyout tifir domponfnts wifn rfsizing is domplftfd. As b rfsult
     * tif {@dodf Componfnt.vblidbtf()} mftiod will bf invokfd only
     * ondf pfr rfsizf.
     * If dynbmid lbyout is durrfntly bdtivf tifn Contbinfrs
     * rf-lbyout tifir domponfnts on fvfry nbtivf rfsizf fvfnt bnd
     * tif {@dodf vblidbtf()} mftiod will bf invokfd fbdi timf.
     * Tif OS/WM support dbn bf qufrifd using
     * tif gftDfsktopPropfrty("bwt.dynbmidLbyoutSupportfd") mftiod.
     *
     * @rfturn    truf if dynbmid lbyout of Contbinfrs on rfsizf is
     *            durrfntly bdtivf, fblsf otifrwisf.
     * @fxdfption HfbdlfssExdfption if tif GrbpiidsEnvironmfnt.isHfbdlfss()
     *            mftiod rfturns truf
     * @sff       #sftDynbmidLbyout(boolfbn dynbmid)
     * @sff       #isDynbmidLbyoutSft()
     * @sff       #gftDfsktopPropfrty(String propfrtyNbmf)
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     */
    publid boolfbn isDynbmidLbyoutAdtivf()
        tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().isDynbmidLbyoutAdtivf();
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Gfts tif sizf of tif sdrffn.  On systfms witi multiplf displbys, tif
     * primbry displby is usfd.  Multi-sdrffn bwbrf displby dimfnsions brf
     * bvbilbblf from <dodf>GrbpiidsConfigurbtion</dodf> bnd
     * <dodf>GrbpiidsDfvidf</dodf>.
     * @rfturn    tif sizf of tiis toolkit's sdrffn, in pixfls.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsConfigurbtion#gftBounds
     * @sff       jbvb.bwt.GrbpiidsDfvidf#gftDisplbyModf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid bbstrbdt Dimfnsion gftSdrffnSizf()
        tirows HfbdlfssExdfption;

    /**
     * Rfturns tif sdrffn rfsolution in dots-pfr-indi.
     * @rfturn    tiis toolkit's sdrffn rfsolution, in dots-pfr-indi.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid bbstrbdt int gftSdrffnRfsolution()
        tirows HfbdlfssExdfption;

    /**
     * Gfts tif insfts of tif sdrffn.
     * @pbrbm     gd b <dodf>GrbpiidsConfigurbtion</dodf>
     * @rfturn    tif insfts of tiis toolkit's sdrffn, in pixfls.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.4
     */
    publid Insfts gftSdrffnInsfts(GrbpiidsConfigurbtion gd)
        tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().gftSdrffnInsfts(gd);
        } flsf {
            rfturn nfw Insfts(0, 0, 0, 0);
        }
    }

    /**
     * Dftfrminfs tif dolor modfl of tiis toolkit's sdrffn.
     * <p>
     * <dodf>ColorModfl</dodf> is bn bbstrbdt dlbss tibt
     * fndbpsulbtfs tif bbility to trbnslbtf bftwffn tif
     * pixfl vblufs of bn imbgf bnd its rfd, grffn, bluf,
     * bnd blpib domponfnts.
     * <p>
     * Tiis toolkit mftiod is dbllfd by tif
     * <dodf>gftColorModfl</dodf> mftiod
     * of tif <dodf>Componfnt</dodf> dlbss.
     * @rfturn    tif dolor modfl of tiis toolkit's sdrffn.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.imbgf.ColorModfl
     * @sff       jbvb.bwt.Componfnt#gftColorModfl
     */
    publid bbstrbdt ColorModfl gftColorModfl()
        tirows HfbdlfssExdfption;

    /**
     * Rfturns tif nbmfs of tif bvbilbblf fonts in tiis toolkit.<p>
     * For 1.1, tif following font nbmfs brf dfprfdbtfd (tif rfplbdfmfnt
     * nbmf follows):
     * <ul>
     * <li>TimfsRombn (usf Sfrif)
     * <li>Hflvftidb (usf SbnsSfrif)
     * <li>Courifr (usf Monospbdfd)
     * </ul><p>
     * Tif ZbpfDingbbts fontnbmf is blso dfprfdbtfd in 1.1 but tif dibrbdtfrs
     * brf dffinfd in Unidodf stbrting bt 0x2700, bnd bs of 1.1 Jbvb supports
     * tiosf dibrbdtfrs.
     * @rfturn    tif nbmfs of tif bvbilbblf fonts in tiis toolkit.
     * @dfprfdbtfd sff {@link jbvb.bwt.GrbpiidsEnvironmfnt#gftAvbilbblfFontFbmilyNbmfs()}
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#gftAvbilbblfFontFbmilyNbmfs()
     */
    @Dfprfdbtfd
    publid bbstrbdt String[] gftFontList();

    /**
     * Gfts tif sdrffn dfvidf mftrids for rfndfring of tif font.
     * @pbrbm     font   b font
     * @rfturn    tif sdrffn mftrids of tif spfdififd font in tiis toolkit
     * @dfprfdbtfd  As of JDK vfrsion 1.2, rfplbdfd by tif <dodf>Font</dodf>
     *          mftiod <dodf>gftLinfMftrids</dodf>.
     * @sff jbvb.bwt.font.LinfMftrids
     * @sff jbvb.bwt.Font#gftLinfMftrids
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#gftSdrffnDfvidfs
     */
    @Dfprfdbtfd
    publid bbstrbdt FontMftrids gftFontMftrids(Font font);

    /**
     * Syndironizfs tiis toolkit's grbpiids stbtf. Somf window systfms
     * mby do bufffring of grbpiids fvfnts.
     * <p>
     * Tiis mftiod fnsurfs tibt tif displby is up-to-dbtf. It is usfful
     * for bnimbtion.
     */
    publid bbstrbdt void synd();

    /**
     * Tif dffbult toolkit.
     */
    privbtf stbtid Toolkit toolkit;

    /**
     * Usfd intfrnblly by tif bssistivf tfdinologifs fundtions; sft bt
     * init timf bnd usfd bt lobd timf
     */
    privbtf stbtid String btNbmfs;

    /**
     * Initiblizfs propfrtifs rflbtfd to bssistivf tfdinologifs.
     * Tifsf propfrtifs brf usfd boti in tif lobdAssistivfPropfrtifs()
     * fundtion bflow, bs wfll bs otifr dlbssfs in tif jdk tibt dfpfnd
     * on tif propfrtifs (sudi bs tif usf of tif sdrffn_mbgnififr_prfsfnt
     * propfrty in Jbvb2D ibrdwbrf bddflfrbtion initiblizbtion).  Tif
     * initiblizbtion of tif propfrtifs must bf donf bfforf tif plbtform-
     * spfdifid Toolkit dlbss is instbntibtfd so tibt bll nfdfssbry
     * propfrtifs brf sft up propfrly bfforf bny dlbssfs dfpfndfnt upon tifm
     * brf initiblizfd.
     */
    privbtf stbtid void initAssistivfTfdinologifs() {

        // Gft bddfssibility propfrtifs
        finbl String sfp = Filf.sfpbrbtor;
        finbl Propfrtifs propfrtifs = nfw Propfrtifs();


        btNbmfs = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<String>() {
            publid String run() {

                // Try lobding tif pfr-usfr bddfssibility propfrtifs filf.
                try {
                    Filf propsFilf = nfw Filf(
                      Systfm.gftPropfrty("usfr.iomf") +
                      sfp + ".bddfssibility.propfrtifs");
                    FilfInputStrfbm in =
                        nfw FilfInputStrfbm(propsFilf);

                    // Inputstrfbm ibs bffn bufffrfd in Propfrtifs dlbss
                    propfrtifs.lobd(in);
                    in.dlosf();
                } dbtdi (Exdfption f) {
                    // Pfr-usfr bddfssibility propfrtifs filf dofs not fxist
                }

                // Try lobding tif systfm-widf bddfssibility propfrtifs
                // filf only if b pfr-usfr bddfssibility propfrtifs
                // filf dofs not fxist or is fmpty.
                if (propfrtifs.sizf() == 0) {
                    try {
                        Filf propsFilf = nfw Filf(
                            Systfm.gftPropfrty("jbvb.iomf") + sfp + "lib" +
                            sfp + "bddfssibility.propfrtifs");
                        FilfInputStrfbm in =
                            nfw FilfInputStrfbm(propsFilf);

                        // Inputstrfbm ibs bffn bufffrfd in Propfrtifs dlbss
                        propfrtifs.lobd(in);
                        in.dlosf();
                    } dbtdi (Exdfption f) {
                        // Systfm-widf bddfssibility propfrtifs filf dofs
                        // not fxist;
                    }
                }

                // Gft wiftifr b sdrffn mbgnififr is prfsfnt.  First difdk
                // tif systfm propfrty bnd tifn difdk tif propfrtifs filf.
                String mbgPrfsfnt = Systfm.gftPropfrty("jbvbx.bddfssibility.sdrffn_mbgnififr_prfsfnt");
                if (mbgPrfsfnt == null) {
                    mbgPrfsfnt = propfrtifs.gftPropfrty("sdrffn_mbgnififr_prfsfnt", null);
                    if (mbgPrfsfnt != null) {
                        Systfm.sftPropfrty("jbvbx.bddfssibility.sdrffn_mbgnififr_prfsfnt", mbgPrfsfnt);
                    }
                }

                // Gft tif nbmfs of bny bssistivf tfdinolgifs to lobd.  First
                // difdk tif systfm propfrty bnd tifn difdk tif propfrtifs
                // filf.
                String dlbssNbmfs = Systfm.gftPropfrty("jbvbx.bddfssibility.bssistivf_tfdinologifs");
                if (dlbssNbmfs == null) {
                    dlbssNbmfs = propfrtifs.gftPropfrty("bssistivf_tfdinologifs", null);
                    if (dlbssNbmfs != null) {
                        Systfm.sftPropfrty("jbvbx.bddfssibility.bssistivf_tfdinologifs", dlbssNbmfs);
                    }
                }
                rfturn dlbssNbmfs;
            }
        });
    }

    /**
     * Lobds bdditionbl dlbssfs into tif VM, using tif propfrty
     * 'bssistivf_tfdinologifs' spfdififd in tif Sun rfffrfndf
     * implfmfntbtion by b linf in tif 'bddfssibility.propfrtifs'
     * filf.  Tif form is "bssistivf_tfdinologifs=..." wifrf
     * tif "..." is b dommb-sfpbrbtfd list of bssistivf tfdinology
     * dlbssfs to lobd.  Ebdi dlbss is lobdfd in tif ordfr givfn
     * bnd b singlf instbndf of fbdi is drfbtfd using
     * Clbss.forNbmf(dlbss).nfwInstbndf().  All frrors brf ibndlfd
     * vib bn AWTError fxdfption.
     *
     * <p>Tif bssumption is mbdf tibt bssistivf tfdinology dlbssfs brf supplifd
     * bs pbrt of INSTALLED (bs opposfd to: BUNDLED) fxtfnsions or spfdififd
     * on tif dlbss pbti
     * (bnd tifrfforf dbn bf lobdfd using tif dlbss lobdfr rfturnfd by
     * b dbll to <dodf>ClbssLobdfr.gftSystfmClbssLobdfr</dodf>, wiosf
     * dflfgbtion pbrfnt is tif fxtfnsion dlbss lobdfr for instbllfd
     * fxtfnsions).
     */
    privbtf stbtid void lobdAssistivfTfdinologifs() {
        // Lobd bny bssistivf tfdinologifs
        if (btNbmfs != null) {
            ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
            StringTokfnizfr pbrsfr = nfw StringTokfnizfr(btNbmfs," ,");
            String btNbmf;
            wiilf (pbrsfr.ibsMorfTokfns()) {
                btNbmf = pbrsfr.nfxtTokfn();
                try {
                    Clbss<?> dlbzz;
                    if (dl != null) {
                        dlbzz = dl.lobdClbss(btNbmf);
                    } flsf {
                        dlbzz = Clbss.forNbmf(btNbmf);
                    }
                    dlbzz.nfwInstbndf();
                } dbtdi (ClbssNotFoundExdfption f) {
                    tirow nfw AWTError("Assistivf Tfdinology not found: "
                            + btNbmf);
                } dbtdi (InstbntibtionExdfption f) {
                    tirow nfw AWTError("Could not instbntibtf Assistivf"
                            + " Tfdinology: " + btNbmf);
                } dbtdi (IllfgblAddfssExdfption f) {
                    tirow nfw AWTError("Could not bddfss Assistivf"
                            + " Tfdinology: " + btNbmf);
                } dbtdi (Exdfption f) {
                    tirow nfw AWTError("Error trying to instbll Assistivf"
                            + " Tfdinology: " + btNbmf + " " + f);
                }
            }
        }
    }

    /**
     * Gfts tif dffbult toolkit.
     * <p>
     * If b systfm propfrty nbmfd <dodf>"jbvb.bwt.ifbdlfss"</dodf> is sft
     * to <dodf>truf</dodf> tifn tif ifbdlfss implfmfntbtion
     * of <dodf>Toolkit</dodf> is usfd.
     * <p>
     * If tifrf is no <dodf>"jbvb.bwt.ifbdlfss"</dodf> or it is sft to
     * <dodf>fblsf</dodf> bnd tifrf is b systfm propfrty nbmfd
     * <dodf>"bwt.toolkit"</dodf>,
     * tibt propfrty is trfbtfd bs tif nbmf of b dlbss tibt is b subdlbss
     * of <dodf>Toolkit</dodf>;
     * otifrwisf tif dffbult plbtform-spfdifid implfmfntbtion of
     * <dodf>Toolkit</dodf> is usfd.
     * <p>
     * Also lobds bdditionbl dlbssfs into tif VM, using tif propfrty
     * 'bssistivf_tfdinologifs' spfdififd in tif Sun rfffrfndf
     * implfmfntbtion by b linf in tif 'bddfssibility.propfrtifs'
     * filf.  Tif form is "bssistivf_tfdinologifs=..." wifrf
     * tif "..." is b dommb-sfpbrbtfd list of bssistivf tfdinology
     * dlbssfs to lobd.  Ebdi dlbss is lobdfd in tif ordfr givfn
     * bnd b singlf instbndf of fbdi is drfbtfd using
     * Clbss.forNbmf(dlbss).nfwInstbndf().  Tiis is donf just bftfr
     * tif AWT toolkit is drfbtfd.  All frrors brf ibndlfd vib bn
     * AWTError fxdfption.
     * @rfturn    tif dffbult toolkit.
     * @fxdfption  AWTError  if b toolkit dould not bf found, or
     *                 if onf dould not bf bddfssfd or instbntibtfd.
     */
    publid stbtid syndironizfd Toolkit gftDffbultToolkit() {
        if (toolkit == null) {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    Clbss<?> dls = null;
                    String nm = Systfm.gftPropfrty("bwt.toolkit");
                    try {
                        dls = Clbss.forNbmf(nm);
                    } dbtdi (ClbssNotFoundExdfption f) {
                        ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                        if (dl != null) {
                            try {
                                dls = dl.lobdClbss(nm);
                            } dbtdi (finbl ClbssNotFoundExdfption ignorfd) {
                                tirow nfw AWTError("Toolkit not found: " + nm);
                            }
                        }
                    }
                    try {
                        if (dls != null) {
                            toolkit = (Toolkit)dls.nfwInstbndf();
                            if (GrbpiidsEnvironmfnt.isHfbdlfss()) {
                                toolkit = nfw HfbdlfssToolkit(toolkit);
                            }
                        }
                    } dbtdi (finbl InstbntibtionExdfption ignorfd) {
                        tirow nfw AWTError("Could not instbntibtf Toolkit: " + nm);
                    } dbtdi (finbl IllfgblAddfssExdfption ignorfd) {
                        tirow nfw AWTError("Could not bddfss Toolkit: " + nm);
                    }
                    rfturn null;
                }
            });
            lobdAssistivfTfdinologifs();
        }
        rfturn toolkit;
    }

    /**
     * Rfturns bn imbgf wiidi gfts pixfl dbtb from tif spfdififd filf,
     * wiosf formbt dbn bf fitifr GIF, JPEG or PNG.
     * Tif undfrlying toolkit bttfmpts to rfsolvf multiplf rfqufsts
     * witi tif sbmf filfnbmf to tif sbmf rfturnfd Imbgf.
     * <p>
     * Sindf tif mfdibnism rfquirfd to fbdilitbtf tiis sibring of
     * <dodf>Imbgf</dodf> objfdts mby dontinuf to iold onto imbgfs
     * tibt brf no longfr in usf for bn indffinitf pfriod of timf,
     * dfvflopfrs brf fndourbgfd to implfmfnt tifir own dbdiing of
     * imbgfs by using tif {@link #drfbtfImbgf(jbvb.lbng.String) drfbtfImbgf}
     * vbribnt wifrfvfr bvbilbblf.
     * If tif imbgf dbtb dontbinfd in tif spfdififd filf dibngfs,
     * tif <dodf>Imbgf</dodf> objfdt rfturnfd from tiis mftiod mby
     * still dontbin stblf informbtion wiidi wbs lobdfd from tif
     * filf bftfr b prior dbll.
     * Prfviously lobdfd imbgf dbtb dbn bf mbnublly disdbrdfd by
     * dblling tif {@link Imbgf#flusi flusi} mftiod on tif
     * rfturnfd <dodf>Imbgf</dodf>.
     * <p>
     * Tiis mftiod first difdks if tifrf is b sfdurity mbnbgfr instbllfd.
     * If so, tif mftiod dblls tif sfdurity mbnbgfr's
     * <dodf>difdkRfbd</dodf> mftiod witi tif filf spfdififd to fnsurf
     * tibt tif bddfss to tif imbgf is bllowfd.
     * @pbrbm     filfnbmf   tif nbmf of b filf dontbining pixfl dbtb
     *                         in b rfdognizfd filf formbt.
     * @rfturn    bn imbgf wiidi gfts its pixfl dbtb from
     *                         tif spfdififd filf.
     * @tirows SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *                            difdkRfbd mftiod dofsn't bllow tif opfrbtion.
     * @sff #drfbtfImbgf(jbvb.lbng.String)
     */
    publid bbstrbdt Imbgf gftImbgf(String filfnbmf);

    /**
     * Rfturns bn imbgf wiidi gfts pixfl dbtb from tif spfdififd URL.
     * Tif pixfl dbtb rfffrfndfd by tif spfdififd URL must bf in onf
     * of tif following formbts: GIF, JPEG or PNG.
     * Tif undfrlying toolkit bttfmpts to rfsolvf multiplf rfqufsts
     * witi tif sbmf URL to tif sbmf rfturnfd Imbgf.
     * <p>
     * Sindf tif mfdibnism rfquirfd to fbdilitbtf tiis sibring of
     * <dodf>Imbgf</dodf> objfdts mby dontinuf to iold onto imbgfs
     * tibt brf no longfr in usf for bn indffinitf pfriod of timf,
     * dfvflopfrs brf fndourbgfd to implfmfnt tifir own dbdiing of
     * imbgfs by using tif {@link #drfbtfImbgf(jbvb.nft.URL) drfbtfImbgf}
     * vbribnt wifrfvfr bvbilbblf.
     * If tif imbgf dbtb storfd bt tif spfdififd URL dibngfs,
     * tif <dodf>Imbgf</dodf> objfdt rfturnfd from tiis mftiod mby
     * still dontbin stblf informbtion wiidi wbs fftdifd from tif
     * URL bftfr b prior dbll.
     * Prfviously lobdfd imbgf dbtb dbn bf mbnublly disdbrdfd by
     * dblling tif {@link Imbgf#flusi flusi} mftiod on tif
     * rfturnfd <dodf>Imbgf</dodf>.
     * <p>
     * Tiis mftiod first difdks if tifrf is b sfdurity mbnbgfr instbllfd.
     * If so, tif mftiod dblls tif sfdurity mbnbgfr's
     * <dodf>difdkPfrmission</dodf> mftiod witi tif
     * url.opfnConnfdtion().gftPfrmission() pfrmission to fnsurf
     * tibt tif bddfss to tif imbgf is bllowfd. For dompbtibility
     * witi prf-1.2 sfdurity mbnbgfrs, if tif bddfss is dfnifd witi
     * <dodf>FilfPfrmission</dodf> or <dodf>SodkftPfrmission</dodf>,
     * tif mftiod tirows tif <dodf>SfdurityExdfption</dodf>
     * if tif dorrfsponding 1.1-stylf SfdurityMbnbgfr.difdkXXX mftiod
     * blso dfnifs pfrmission.
     * @pbrbm     url   tif URL to usf in fftdiing tif pixfl dbtb.
     * @rfturn    bn imbgf wiidi gfts its pixfl dbtb from
     *                         tif spfdififd URL.
     * @tirows SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *                            difdkPfrmission mftiod dofsn't bllow
     *                            tif opfrbtion.
     * @sff #drfbtfImbgf(jbvb.nft.URL)
     */
    publid bbstrbdt Imbgf gftImbgf(URL url);

    /**
     * Rfturns bn imbgf wiidi gfts pixfl dbtb from tif spfdififd filf.
     * Tif rfturnfd Imbgf is b nfw objfdt wiidi will not bf sibrfd
     * witi bny otifr dbllfr of tiis mftiod or its gftImbgf vbribnt.
     * <p>
     * Tiis mftiod first difdks if tifrf is b sfdurity mbnbgfr instbllfd.
     * If so, tif mftiod dblls tif sfdurity mbnbgfr's
     * <dodf>difdkRfbd</dodf> mftiod witi tif spfdififd filf to fnsurf
     * tibt tif imbgf drfbtion is bllowfd.
     * @pbrbm     filfnbmf   tif nbmf of b filf dontbining pixfl dbtb
     *                         in b rfdognizfd filf formbt.
     * @rfturn    bn imbgf wiidi gfts its pixfl dbtb from
     *                         tif spfdififd filf.
     * @tirows SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *                            difdkRfbd mftiod dofsn't bllow tif opfrbtion.
     * @sff #gftImbgf(jbvb.lbng.String)
     */
    publid bbstrbdt Imbgf drfbtfImbgf(String filfnbmf);

    /**
     * Rfturns bn imbgf wiidi gfts pixfl dbtb from tif spfdififd URL.
     * Tif rfturnfd Imbgf is b nfw objfdt wiidi will not bf sibrfd
     * witi bny otifr dbllfr of tiis mftiod or its gftImbgf vbribnt.
     * <p>
     * Tiis mftiod first difdks if tifrf is b sfdurity mbnbgfr instbllfd.
     * If so, tif mftiod dblls tif sfdurity mbnbgfr's
     * <dodf>difdkPfrmission</dodf> mftiod witi tif
     * url.opfnConnfdtion().gftPfrmission() pfrmission to fnsurf
     * tibt tif imbgf drfbtion is bllowfd. For dompbtibility
     * witi prf-1.2 sfdurity mbnbgfrs, if tif bddfss is dfnifd witi
     * <dodf>FilfPfrmission</dodf> or <dodf>SodkftPfrmission</dodf>,
     * tif mftiod tirows <dodf>SfdurityExdfption</dodf>
     * if tif dorrfsponding 1.1-stylf SfdurityMbnbgfr.difdkXXX mftiod
     * blso dfnifs pfrmission.
     * @pbrbm     url   tif URL to usf in fftdiing tif pixfl dbtb.
     * @rfturn    bn imbgf wiidi gfts its pixfl dbtb from
     *                         tif spfdififd URL.
     * @tirows SfdurityExdfption  if b sfdurity mbnbgfr fxists bnd its
     *                            difdkPfrmission mftiod dofsn't bllow
     *                            tif opfrbtion.
     * @sff #gftImbgf(jbvb.nft.URL)
     */
    publid bbstrbdt Imbgf drfbtfImbgf(URL url);

    /**
     * Prfpbrfs bn imbgf for rfndfring.
     * <p>
     * If tif vblufs of tif widti bnd ifigit brgumfnts brf boti
     * <dodf>-1</dodf>, tiis mftiod prfpbrfs tif imbgf for rfndfring
     * on tif dffbult sdrffn; otifrwisf, tiis mftiod prfpbrfs bn imbgf
     * for rfndfring on tif dffbult sdrffn bt tif spfdififd widti bnd ifigit.
     * <p>
     * Tif imbgf dbtb is downlobdfd bsyndironously in bnotifr tirfbd,
     * bnd bn bppropribtfly sdblfd sdrffn rfprfsfntbtion of tif imbgf is
     * gfnfrbtfd.
     * <p>
     * Tiis mftiod is dbllfd by domponfnts <dodf>prfpbrfImbgf</dodf>
     * mftiods.
     * <p>
     * Informbtion on tif flbgs rfturnfd by tiis mftiod dbn bf found
     * witi tif dffinition of tif <dodf>ImbgfObsfrvfr</dodf> intfrfbdf.

     * @pbrbm     imbgf      tif imbgf for wiidi to prfpbrf b
     *                           sdrffn rfprfsfntbtion.
     * @pbrbm     widti      tif widti of tif dfsirfd sdrffn
     *                           rfprfsfntbtion, or <dodf>-1</dodf>.
     * @pbrbm     ifigit     tif ifigit of tif dfsirfd sdrffn
     *                           rfprfsfntbtion, or <dodf>-1</dodf>.
     * @pbrbm     obsfrvfr   tif <dodf>ImbgfObsfrvfr</dodf>
     *                           objfdt to bf notififd bs tif
     *                           imbgf is bfing prfpbrfd.
     * @rfturn    <dodf>truf</dodf> if tif imbgf ibs blrfbdy bffn
     *                 fully prfpbrfd; <dodf>fblsf</dodf> otifrwisf.
     * @sff       jbvb.bwt.Componfnt#prfpbrfImbgf(jbvb.bwt.Imbgf,
     *                 jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff       jbvb.bwt.Componfnt#prfpbrfImbgf(jbvb.bwt.Imbgf,
     *                 int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff       jbvb.bwt.imbgf.ImbgfObsfrvfr
     */
    publid bbstrbdt boolfbn prfpbrfImbgf(Imbgf imbgf, int widti, int ifigit,
                                         ImbgfObsfrvfr obsfrvfr);

    /**
     * Indidbtfs tif donstrudtion stbtus of b spfdififd imbgf tibt is
     * bfing prfpbrfd for displby.
     * <p>
     * If tif vblufs of tif widti bnd ifigit brgumfnts brf boti
     * <dodf>-1</dodf>, tiis mftiod rfturns tif donstrudtion stbtus of
     * b sdrffn rfprfsfntbtion of tif spfdififd imbgf in tiis toolkit.
     * Otifrwisf, tiis mftiod rfturns tif donstrudtion stbtus of b
     * sdblfd rfprfsfntbtion of tif imbgf bt tif spfdififd widti
     * bnd ifigit.
     * <p>
     * Tiis mftiod dofs not dbusf tif imbgf to bfgin lobding.
     * An bpplidbtion must dbll <dodf>prfpbrfImbgf</dodf> to fordf
     * tif lobding of bn imbgf.
     * <p>
     * Tiis mftiod is dbllfd by tif domponfnt's <dodf>difdkImbgf</dodf>
     * mftiods.
     * <p>
     * Informbtion on tif flbgs rfturnfd by tiis mftiod dbn bf found
     * witi tif dffinition of tif <dodf>ImbgfObsfrvfr</dodf> intfrfbdf.
     * @pbrbm     imbgf   tif imbgf wiosf stbtus is bfing difdkfd.
     * @pbrbm     widti   tif widti of tif sdblfd vfrsion wiosf stbtus is
     *                 bfing difdkfd, or <dodf>-1</dodf>.
     * @pbrbm     ifigit  tif ifigit of tif sdblfd vfrsion wiosf stbtus
     *                 is bfing difdkfd, or <dodf>-1</dodf>.
     * @pbrbm     obsfrvfr   tif <dodf>ImbgfObsfrvfr</dodf> objfdt to bf
     *                 notififd bs tif imbgf is bfing prfpbrfd.
     * @rfturn    tif bitwisf indlusivf <strong>OR</strong> of tif
     *                 <dodf>ImbgfObsfrvfr</dodf> flbgs for tif
     *                 imbgf dbtb tibt is durrfntly bvbilbblf.
     * @sff       jbvb.bwt.Toolkit#prfpbrfImbgf(jbvb.bwt.Imbgf,
     *                 int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff       jbvb.bwt.Componfnt#difdkImbgf(jbvb.bwt.Imbgf,
     *                 jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff       jbvb.bwt.Componfnt#difdkImbgf(jbvb.bwt.Imbgf,
     *                 int, int, jbvb.bwt.imbgf.ImbgfObsfrvfr)
     * @sff       jbvb.bwt.imbgf.ImbgfObsfrvfr
     */
    publid bbstrbdt int difdkImbgf(Imbgf imbgf, int widti, int ifigit,
                                   ImbgfObsfrvfr obsfrvfr);

    /**
     * Crfbtfs bn imbgf witi tif spfdififd imbgf produdfr.
     * @pbrbm     produdfr tif imbgf produdfr to bf usfd.
     * @rfturn    bn imbgf witi tif spfdififd imbgf produdfr.
     * @sff       jbvb.bwt.Imbgf
     * @sff       jbvb.bwt.imbgf.ImbgfProdudfr
     * @sff       jbvb.bwt.Componfnt#drfbtfImbgf(jbvb.bwt.imbgf.ImbgfProdudfr)
     */
    publid bbstrbdt Imbgf drfbtfImbgf(ImbgfProdudfr produdfr);

    /**
     * Crfbtfs bn imbgf wiidi dfdodfs tif imbgf storfd in tif spfdififd
     * bytf brrby.
     * <p>
     * Tif dbtb must bf in somf imbgf formbt, sudi bs GIF or JPEG,
     * tibt is supportfd by tiis toolkit.
     * @pbrbm     imbgfdbtb   bn brrby of bytfs, rfprfsfnting
     *                         imbgf dbtb in b supportfd imbgf formbt.
     * @rfturn    bn imbgf.
     * @sindf     1.1
     */
    publid Imbgf drfbtfImbgf(bytf[] imbgfdbtb) {
        rfturn drfbtfImbgf(imbgfdbtb, 0, imbgfdbtb.lfngti);
    }

    /**
     * Crfbtfs bn imbgf wiidi dfdodfs tif imbgf storfd in tif spfdififd
     * bytf brrby, bnd bt tif spfdififd offsft bnd lfngti.
     * Tif dbtb must bf in somf imbgf formbt, sudi bs GIF or JPEG,
     * tibt is supportfd by tiis toolkit.
     * @pbrbm     imbgfdbtb   bn brrby of bytfs, rfprfsfnting
     *                         imbgf dbtb in b supportfd imbgf formbt.
     * @pbrbm     imbgfoffsft  tif offsft of tif bfginning
     *                         of tif dbtb in tif brrby.
     * @pbrbm     imbgflfngti  tif lfngti of tif dbtb in tif brrby.
     * @rfturn    bn imbgf.
     * @sindf     1.1
     */
    publid bbstrbdt Imbgf drfbtfImbgf(bytf[] imbgfdbtb,
                                      int imbgfoffsft,
                                      int imbgflfngti);

    /**
     * Gfts b <dodf>PrintJob</dodf> objfdt wiidi is tif rfsult of initibting
     * b print opfrbtion on tif toolkit's plbtform.
     * <p>
     * Ebdi bdtubl implfmfntbtion of tiis mftiod siould first difdk if tifrf
     * is b sfdurity mbnbgfr instbllfd. If tifrf is, tif mftiod siould dbll
     * tif sfdurity mbnbgfr's <dodf>difdkPrintJobAddfss</dodf> mftiod to
     * fnsurf initibtion of b print opfrbtion is bllowfd. If tif dffbult
     * implfmfntbtion of <dodf>difdkPrintJobAddfss</dodf> is usfd (tibt is,
     * tibt mftiod is not ovfrridfn), tifn tiis rfsults in b dbll to tif
     * sfdurity mbnbgfr's <dodf>difdkPfrmission</dodf> mftiod witi b <dodf>
     * RuntimfPfrmission("qufufPrintJob")</dodf> pfrmission.
     *
     * @pbrbm   frbmf tif pbrfnt of tif print diblog. Mby not bf null.
     * @pbrbm   jobtitlf tif titlf of tif PrintJob. A null titlf is fquivblfnt
     *          to "".
     * @pbrbm   props b Propfrtifs objfdt dontbining zfro or morf propfrtifs.
     *          Propfrtifs brf not stbndbrdizfd bnd brf not donsistfnt bdross
     *          implfmfntbtions. Bfdbusf of tiis, PrintJobs wiidi rfquirf job
     *          bnd pbgf dontrol siould usf tif vfrsion of tiis fundtion wiidi
     *          tbkfs JobAttributfs bnd PbgfAttributfs objfdts. Tiis objfdt
     *          mby bf updbtfd to rfflfdt tif usfr's job dioidfs on fxit. Mby
     *          bf null.
     * @rfturn  b <dodf>PrintJob</dodf> objfdt, or <dodf>null</dodf> if tif
     *          usfr dbndfllfd tif print job.
     * @tirows  NullPointfrExdfption if frbmf is null
     * @tirows  SfdurityExdfption if tiis tirfbd is not bllowfd to initibtf b
     *          print job rfqufst
     * @sff     jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff     jbvb.bwt.PrintJob
     * @sff     jbvb.lbng.RuntimfPfrmission
     * @sindf   1.1
     */
    publid bbstrbdt PrintJob gftPrintJob(Frbmf frbmf, String jobtitlf,
                                         Propfrtifs props);

    /**
     * Gfts b <dodf>PrintJob</dodf> objfdt wiidi is tif rfsult of initibting
     * b print opfrbtion on tif toolkit's plbtform.
     * <p>
     * Ebdi bdtubl implfmfntbtion of tiis mftiod siould first difdk if tifrf
     * is b sfdurity mbnbgfr instbllfd. If tifrf is, tif mftiod siould dbll
     * tif sfdurity mbnbgfr's <dodf>difdkPrintJobAddfss</dodf> mftiod to
     * fnsurf initibtion of b print opfrbtion is bllowfd. If tif dffbult
     * implfmfntbtion of <dodf>difdkPrintJobAddfss</dodf> is usfd (tibt is,
     * tibt mftiod is not ovfrridfn), tifn tiis rfsults in b dbll to tif
     * sfdurity mbnbgfr's <dodf>difdkPfrmission</dodf> mftiod witi b <dodf>
     * RuntimfPfrmission("qufufPrintJob")</dodf> pfrmission.
     *
     * @pbrbm   frbmf tif pbrfnt of tif print diblog. Mby not bf null.
     * @pbrbm   jobtitlf tif titlf of tif PrintJob. A null titlf is fquivblfnt
     *          to "".
     * @pbrbm   jobAttributfs b sft of job bttributfs wiidi will dontrol tif
     *          PrintJob. Tif bttributfs will bf updbtfd to rfflfdt tif usfr's
     *          dioidfs bs outlinfd in tif JobAttributfs dodumfntbtion. Mby bf
     *          null.
     * @pbrbm   pbgfAttributfs b sft of pbgf bttributfs wiidi will dontrol tif
     *          PrintJob. Tif bttributfs will bf bpplifd to fvfry pbgf in tif
     *          job. Tif bttributfs will bf updbtfd to rfflfdt tif usfr's
     *          dioidfs bs outlinfd in tif PbgfAttributfs dodumfntbtion. Mby bf
     *          null.
     * @rfturn  b <dodf>PrintJob</dodf> objfdt, or <dodf>null</dodf> if tif
     *          usfr dbndfllfd tif print job.
     * @tirows  NullPointfrExdfption if frbmf is null
     * @tirows  IllfgblArgumfntExdfption if pbgfAttributfs spfdififs difffring
     *          dross fffd bnd fffd rfsolutions. Also if tiis tirfbd ibs
     *          bddfss to tif filf systfm bnd jobAttributfs spfdififs
     *          print to filf, bnd tif spfdififd dfstinbtion filf fxists but
     *          is b dirfdtory rbtifr tibn b rfgulbr filf, dofs not fxist but
     *          dbnnot bf drfbtfd, or dbnnot bf opfnfd for bny otifr rfbson.
     *          Howfvfr in tif dbsf of print to filf, if b diblog is blso
     *          rfqufstfd to bf displbyfd tifn tif usfr will bf givfn bn
     *          opportunity to sflfdt b filf bnd prodffd witi printing.
     *          Tif diblog will fnsurf tibt tif sflfdtfd output filf
     *          is vblid bfforf rfturning from tiis mftiod.
     * @tirows  SfdurityExdfption if tiis tirfbd is not bllowfd to initibtf b
     *          print job rfqufst, or if jobAttributfs spfdififs print to filf,
     *          bnd tiis tirfbd is not bllowfd to bddfss tif filf systfm
     * @sff     jbvb.bwt.PrintJob
     * @sff     jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff     jbvb.lbng.RuntimfPfrmission
     * @sff     jbvb.bwt.JobAttributfs
     * @sff     jbvb.bwt.PbgfAttributfs
     * @sindf   1.3
     */
    publid PrintJob gftPrintJob(Frbmf frbmf, String jobtitlf,
                                JobAttributfs jobAttributfs,
                                PbgfAttributfs pbgfAttributfs) {
        // Ovfrridf to bdd printing support witi nfw job/pbgf dontrol dlbssfs

        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().gftPrintJob(frbmf, jobtitlf,
                                                           jobAttributfs,
                                                           pbgfAttributfs);
        } flsf {
            rfturn gftPrintJob(frbmf, jobtitlf, null);
        }
    }

    /**
     * Emits bn budio bffp dfpfnding on nbtivf systfm sfttings bnd ibrdwbrf
     * dbpbbilitifs.
     * @sindf     1.1
     */
    publid bbstrbdt void bffp();

    /**
     * Gfts tif singlfton instbndf of tif systfm Clipbobrd wiidi intfrfbdfs
     * witi dlipbobrd fbdilitifs providfd by tif nbtivf plbtform. Tiis
     * dlipbobrd fnbblfs dbtb trbnsffr bftwffn Jbvb progrbms bnd nbtivf
     * bpplidbtions wiidi usf nbtivf dlipbobrd fbdilitifs.
     * <p>
     * In bddition to bny bnd bll dffbult formbts tfxt rfturnfd by tif systfm
     * Clipbobrd's <dodf>gftTrbnsffrDbtb()</dodf> mftiod is bvbilbblf in tif
     * following flbvors:
     * <ul>
     * <li>DbtbFlbvor.stringFlbvor</li>
     * <li>DbtbFlbvor.plbinTfxtFlbvor (<b>dfprfdbtfd</b>)</li>
     * </ul>
     * As witi <dodf>jbvb.bwt.dbtbtrbnsffr.StringSflfdtion</dodf>, if tif
     * rfqufstfd flbvor is <dodf>DbtbFlbvor.plbinTfxtFlbvor</dodf>, or bn
     * fquivblfnt flbvor, b Rfbdfr is rfturnfd. <b>Notf:</b> Tif bfibvior of
     * tif systfm Clipbobrd's <dodf>gftTrbnsffrDbtb()</dodf> mftiod for <dodf>
     * DbtbFlbvor.plbinTfxtFlbvor</dodf>, bnd fquivblfnt DbtbFlbvors, is
     * indonsistfnt witi tif dffinition of <dodf>DbtbFlbvor.plbinTfxtFlbvor
     * </dodf>. Bfdbusf of tiis, support for <dodf>
     * DbtbFlbvor.plbinTfxtFlbvor</dodf>, bnd fquivblfnt flbvors, is
     * <b>dfprfdbtfd</b>.
     * <p>
     * Ebdi bdtubl implfmfntbtion of tiis mftiod siould first difdk if tifrf
     * is b sfdurity mbnbgfr instbllfd. If tifrf is, tif mftiod siould dbll
     * tif sfdurity mbnbgfr's {@link SfdurityMbnbgfr#difdkPfrmission
     * difdkPfrmission} mftiod to difdk {@dodf AWTPfrmission("bddfssClipbobrd")}.
     *
     * @rfturn    tif systfm Clipbobrd
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.dbtbtrbnsffr.Clipbobrd
     * @sff       jbvb.bwt.dbtbtrbnsffr.StringSflfdtion
     * @sff       jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor#stringFlbvor
     * @sff       jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor#plbinTfxtFlbvor
     * @sff       jbvb.io.Rfbdfr
     * @sff       jbvb.bwt.AWTPfrmission
     * @sindf     1.1
     */
    publid bbstrbdt Clipbobrd gftSystfmClipbobrd()
        tirows HfbdlfssExdfption;

    /**
     * Gfts tif singlfton instbndf of tif systfm sflfdtion bs b
     * <dodf>Clipbobrd</dodf> objfdt. Tiis bllows bn bpplidbtion to rfbd bnd
     * modify tif durrfnt, systfm-widf sflfdtion.
     * <p>
     * An bpplidbtion is rfsponsiblf for updbting tif systfm sflfdtion wifnfvfr
     * tif usfr sflfdts tfxt, using fitifr tif mousf or tif kfybobrd.
     * Typidblly, tiis is implfmfntfd by instblling b
     * <dodf>FodusListfnfr</dodf> on bll <dodf>Componfnt</dodf>s wiidi support
     * tfxt sflfdtion, bnd, bftwffn <dodf>FOCUS_GAINED</dodf> bnd
     * <dodf>FOCUS_LOST</dodf> fvfnts dflivfrfd to tibt <dodf>Componfnt</dodf>,
     * updbting tif systfm sflfdtion <dodf>Clipbobrd</dodf> wifn tif sflfdtion
     * dibngfs insidf tif <dodf>Componfnt</dodf>. Propfrly updbting tif systfm
     * sflfdtion fnsurfs tibt b Jbvb bpplidbtion will intfrbdt dorrfdtly witi
     * nbtivf bpplidbtions bnd otifr Jbvb bpplidbtions running simultbnfously
     * on tif systfm. Notf tibt <dodf>jbvb.bwt.TfxtComponfnt</dodf> bnd
     * <dodf>jbvbx.swing.tfxt.JTfxtComponfnt</dodf> blrfbdy bdifrf to tiis
     * polidy. Wifn using tifsf dlbssfs, bnd tifir subdlbssfs, dfvflopfrs nffd
     * not writf bny bdditionbl dodf.
     * <p>
     * Somf plbtforms do not support b systfm sflfdtion <dodf>Clipbobrd</dodf>.
     * On tiosf plbtforms, tiis mftiod will rfturn <dodf>null</dodf>. In sudi b
     * dbsf, bn bpplidbtion is bbsolvfd from its rfsponsibility to updbtf tif
     * systfm sflfdtion <dodf>Clipbobrd</dodf> bs dfsdribfd bbovf.
     * <p>
     * Ebdi bdtubl implfmfntbtion of tiis mftiod siould first difdk if tifrf
     * is b sfdurity mbnbgfr instbllfd. If tifrf is, tif mftiod siould dbll
     * tif sfdurity mbnbgfr's {@link SfdurityMbnbgfr#difdkPfrmission
     * difdkPfrmission} mftiod to difdk {@dodf AWTPfrmission("bddfssClipbobrd")}.
     *
     * @rfturn tif systfm sflfdtion bs b <dodf>Clipbobrd</dodf>, or
     *         <dodf>null</dodf> if tif nbtivf plbtform dofs not support b
     *         systfm sflfdtion <dodf>Clipbobrd</dodf>
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     *            rfturns truf
     *
     * @sff jbvb.bwt.dbtbtrbnsffr.Clipbobrd
     * @sff jbvb.bwt.fvfnt.FodusListfnfr
     * @sff jbvb.bwt.fvfnt.FodusEvfnt#FOCUS_GAINED
     * @sff jbvb.bwt.fvfnt.FodusEvfnt#FOCUS_LOST
     * @sff TfxtComponfnt
     * @sff jbvbx.swing.tfxt.JTfxtComponfnt
     * @sff AWTPfrmission
     * @sff GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.4
     */
    publid Clipbobrd gftSystfmSflfdtion() tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().gftSystfmSflfdtion();
        } flsf {
            GrbpiidsEnvironmfnt.difdkHfbdlfss();
            rfturn null;
        }
    }

    /**
     * Dftfrminfs wiidi modififr kfy is tif bppropribtf bddflfrbtor
     * kfy for mfnu siortduts.
     * <p>
     * Mfnu siortduts, wiidi brf fmbodifd in tif
     * <dodf>MfnuSiortdut</dodf> dlbss, brf ibndlfd by tif
     * <dodf>MfnuBbr</dodf> dlbss.
     * <p>
     * By dffbult, tiis mftiod rfturns <dodf>Evfnt.CTRL_MASK</dodf>.
     * Toolkit implfmfntbtions siould ovfrridf tiis mftiod if tif
     * <b>Control</b> kfy isn't tif dorrfdt kfy for bddflfrbtors.
     * @rfturn    tif modififr mbsk on tif <dodf>Evfnt</dodf> dlbss
     *                 tibt is usfd for mfnu siortduts on tiis toolkit.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sff       jbvb.bwt.MfnuBbr
     * @sff       jbvb.bwt.MfnuSiortdut
     * @sindf     1.1
     */
    publid int gftMfnuSiortdutKfyMbsk() tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        rfturn Evfnt.CTRL_MASK;
    }

    /**
     * Rfturns wiftifr tif givfn lodking kfy on tif kfybobrd is durrfntly in
     * its "on" stbtf.
     * Vblid kfy dodfs brf
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_CAPS_LOCK VK_CAPS_LOCK},
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_NUM_LOCK VK_NUM_LOCK},
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_SCROLL_LOCK VK_SCROLL_LOCK}, bnd
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_KANA_LOCK VK_KANA_LOCK}.
     *
     * @pbrbm  kfyCodf tif kfy dodf
     * @rfturn {@dodf truf} if tif givfn kfy is durrfntly in its "on" stbtf;
     *          otifrwisf {@dodf fblsf}
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if <dodf>kfyCodf</dodf>
     * is not onf of tif vblid kfy dodfs
     * @fxdfption jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif iost systfm dofsn't
     * bllow gftting tif stbtf of tiis kfy progrbmmbtidblly, or if tif kfybobrd
     * dofsn't ibvf tiis kfy
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.3
     */
    publid boolfbn gftLodkingKfyStbtf(int kfyCodf)
        tirows UnsupportfdOpfrbtionExdfption
    {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (! (kfyCodf == KfyEvfnt.VK_CAPS_LOCK || kfyCodf == KfyEvfnt.VK_NUM_LOCK ||
               kfyCodf == KfyEvfnt.VK_SCROLL_LOCK || kfyCodf == KfyEvfnt.VK_KANA_LOCK)) {
            tirow nfw IllfgblArgumfntExdfption("invblid kfy for Toolkit.gftLodkingKfyStbtf");
        }
        tirow nfw UnsupportfdOpfrbtionExdfption("Toolkit.gftLodkingKfyStbtf");
    }

    /**
     * Sfts tif stbtf of tif givfn lodking kfy on tif kfybobrd.
     * Vblid kfy dodfs brf
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_CAPS_LOCK VK_CAPS_LOCK},
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_NUM_LOCK VK_NUM_LOCK},
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_SCROLL_LOCK VK_SCROLL_LOCK}, bnd
     * {@link jbvb.bwt.fvfnt.KfyEvfnt#VK_KANA_LOCK VK_KANA_LOCK}.
     * <p>
     * Dfpfnding on tif plbtform, sftting tif stbtf of b lodking kfy mby
     * involvf fvfnt prodfssing bnd tifrfforf mby not bf immfdibtfly
     * obsfrvbblf tirougi gftLodkingKfyStbtf.
     *
     * @pbrbm  kfyCodf tif kfy dodf
     * @pbrbm  on tif stbtf of tif kfy
     * @fxdfption jbvb.lbng.IllfgblArgumfntExdfption if <dodf>kfyCodf</dodf>
     * is not onf of tif vblid kfy dodfs
     * @fxdfption jbvb.lbng.UnsupportfdOpfrbtionExdfption if tif iost systfm dofsn't
     * bllow sftting tif stbtf of tiis kfy progrbmmbtidblly, or if tif kfybobrd
     * dofsn't ibvf tiis kfy
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.3
     */
    publid void sftLodkingKfyStbtf(int kfyCodf, boolfbn on)
        tirows UnsupportfdOpfrbtionExdfption
    {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (! (kfyCodf == KfyEvfnt.VK_CAPS_LOCK || kfyCodf == KfyEvfnt.VK_NUM_LOCK ||
               kfyCodf == KfyEvfnt.VK_SCROLL_LOCK || kfyCodf == KfyEvfnt.VK_KANA_LOCK)) {
            tirow nfw IllfgblArgumfntExdfption("invblid kfy for Toolkit.sftLodkingKfyStbtf");
        }
        tirow nfw UnsupportfdOpfrbtionExdfption("Toolkit.sftLodkingKfyStbtf");
    }

    /**
     * Givf nbtivf pffrs tif bbility to qufry tif nbtivf dontbinfr
     * givfn b nbtivf domponfnt (fg tif dirfdt pbrfnt mby bf ligitwfigit).
     *
     * @pbrbm  d tif domponfnt to fftdi tif dontbinfr for
     * @rfturn tif nbtivf dontbinfr objfdt for tif domponfnt
     */
    protfdtfd stbtid Contbinfr gftNbtivfContbinfr(Componfnt d) {
        rfturn d.gftNbtivfContbinfr();
    }

    /**
     * Crfbtfs b nfw dustom dursor objfdt.
     * If tif imbgf to displby is invblid, tif dursor will bf iiddfn (mbdf
     * domplftfly trbnspbrfnt), bnd tif iotspot will bf sft to (0, 0).
     *
     * <p>Notf tibt multi-frbmf imbgfs brf invblid bnd mby dbusf tiis
     * mftiod to ibng.
     *
     * @pbrbm dursor tif imbgf to displby wifn tif dursor is bdtivbtfd
     * @pbrbm iotSpot tif X bnd Y of tif lbrgf dursor's iot spot; tif
     *   iotSpot vblufs must bf lfss tibn tif Dimfnsion rfturnfd by
     *   <dodf>gftBfstCursorSizf</dodf>
     * @pbrbm     nbmf b lodblizfd dfsdription of tif dursor, for Jbvb Addfssibility usf
     * @fxdfption IndfxOutOfBoundsExdfption if tif iotSpot vblufs brf outsidf
     *   tif bounds of tif dursor
     * @rfturn tif dursor drfbtfd
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.2
     */
    publid Cursor drfbtfCustomCursor(Imbgf dursor, Point iotSpot, String nbmf)
        tirows IndfxOutOfBoundsExdfption, HfbdlfssExdfption
    {
        // Ovfrridf to implfmfnt dustom dursor support.
        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().
                drfbtfCustomCursor(dursor, iotSpot, nbmf);
        } flsf {
            rfturn nfw Cursor(Cursor.DEFAULT_CURSOR);
        }
    }

    /**
     * Rfturns tif supportfd dursor dimfnsion wiidi is dlosfst to tif dfsirfd
     * sizfs.  Systfms wiidi only support b singlf dursor sizf will rfturn tibt
     * sizf rfgbrdlfss of tif dfsirfd sizfs.  Systfms wiidi don't support dustom
     * dursors will rfturn b dimfnsion of 0, 0. <p>
     * Notf:  if bn imbgf is usfd wiosf dimfnsions don't mbtdi b supportfd sizf
     * (bs rfturnfd by tiis mftiod), tif Toolkit implfmfntbtion will bttfmpt to
     * rfsizf tif imbgf to b supportfd sizf.
     * Sindf donvfrting low-rfsolution imbgfs is diffidult,
     * no gubrbntffs brf mbdf bs to tif qublity of b dursor imbgf wiidi isn't b
     * supportfd sizf.  It is tifrfforf rfdommfndfd tibt tiis mftiod
     * bf dbllfd bnd bn bppropribtf imbgf usfd so no imbgf donvfrsion is mbdf.
     *
     * @pbrbm     prfffrrfdWidti tif prfffrrfd dursor widti tif domponfnt would likf
     * to usf.
     * @pbrbm     prfffrrfdHfigit tif prfffrrfd dursor ifigit tif domponfnt would likf
     * to usf.
     * @rfturn    tif dlosfst mbtdiing supportfd dursor sizf, or b dimfnsion of 0,0 if
     * tif Toolkit implfmfntbtion dofsn't support dustom dursors.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.2
     */
    publid Dimfnsion gftBfstCursorSizf(int prfffrrfdWidti,
        int prfffrrfdHfigit) tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        // Ovfrridf to implfmfnt dustom dursor support.
        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().
                gftBfstCursorSizf(prfffrrfdWidti, prfffrrfdHfigit);
        } flsf {
            rfturn nfw Dimfnsion(0, 0);
        }
    }

    /**
     * Rfturns tif mbximum numbfr of dolors tif Toolkit supports in b dustom dursor
     * pblfttf.<p>
     * Notf: if bn imbgf is usfd wiidi ibs morf dolors in its pblfttf tibn
     * tif supportfd mbximum, tif Toolkit implfmfntbtion will bttfmpt to flbttfn tif
     * pblfttf to tif mbximum.  Sindf donvfrting low-rfsolution imbgfs is diffidult,
     * no gubrbntffs brf mbdf bs to tif qublity of b dursor imbgf wiidi ibs morf
     * dolors tibn tif systfm supports.  It is tifrfforf rfdommfndfd tibt tiis mftiod
     * bf dbllfd bnd bn bppropribtf imbgf usfd so no imbgf donvfrsion is mbdf.
     *
     * @rfturn    tif mbximum numbfr of dolors, or zfro if dustom dursors brf not
     * supportfd by tiis Toolkit implfmfntbtion.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf     1.2
     */
    publid int gftMbximumCursorColors() tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        // Ovfrridf to implfmfnt dustom dursor support.
        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().gftMbximumCursorColors();
        } flsf {
            rfturn 0;
        }
    }

    /**
     * Rfturns wiftifr Toolkit supports tiis stbtf for
     * <dodf>Frbmf</dodf>s.  Tiis mftiod tflls wiftifr tif <fm>UI
     * dondfpt</fm> of, sby, mbximizbtion or idonifidbtion is
     * supportfd.  It will blwbys rfturn fblsf for "dompound" stbtfs
     * likf <dodf>Frbmf.ICONIFIED|Frbmf.MAXIMIZED_VERT</dodf>.
     * In otifr words, tif rulf of tiumb is tibt only qufrifs witi b
     * singlf frbmf stbtf donstbnt bs bn brgumfnt brf mfbningful.
     * <p>Notf tibt supporting b givfn dondfpt is b plbtform-
     * dfpfndfnt ffbturf. Duf to nbtivf limitbtions tif Toolkit
     * objfdt mby rfport b pbrtidulbr stbtf bs supportfd, iowfvfr bt
     * tif sbmf timf tif Toolkit objfdt will bf unbblf to bpply tif
     * stbtf to b givfn frbmf.  Tiis dirdumstbndf ibs two following
     * donsfqufndfs:
     * <ul>
     * <li>Only tif rfturn vbluf of {@dodf fblsf} for tif prfsfnt
     * mftiod bdtublly indidbtfs tibt tif givfn stbtf is not
     * supportfd. If tif mftiod rfturns {@dodf truf} tif givfn stbtf
     * mby still bf unsupportfd bnd/or unbvbilbblf for b pbrtidulbr
     * frbmf.
     * <li>Tif dfvflopfr siould donsidfr fxbmining tif vbluf of tif
     * {@link jbvb.bwt.fvfnt.WindowEvfnt#gftNfwStbtf} mftiod of tif
     * {@dodf WindowEvfnt} rfdfivfd tirougi tif {@link
     * jbvb.bwt.fvfnt.WindowStbtfListfnfr}, rbtifr tibn bssuming
     * tibt tif stbtf givfn to tif {@dodf sftExtfndfdStbtf()} mftiod
     * will bf dffinitfly bpplifd. For morf informbtion sff tif
     * dodumfntbtion for tif {@link Frbmf#sftExtfndfdStbtf} mftiod.
     * </ul>
     *
     * @pbrbm stbtf onf of nbmfd frbmf stbtf donstbnts.
     * @rfturn <dodf>truf</dodf> is tiis frbmf stbtf is supportfd by
     *     tiis Toolkit implfmfntbtion, <dodf>fblsf</dodf> otifrwisf.
     * @fxdfption HfbdlfssExdfption
     *     if <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf>
     *     rfturns <dodf>truf</dodf>.
     * @sff jbvb.bwt.Window#bddWindowStbtfListfnfr
     * @sindf   1.4
     */
    publid boolfbn isFrbmfStbtfSupportfd(int stbtf)
        tirows HfbdlfssExdfption
    {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        if (tiis != Toolkit.gftDffbultToolkit()) {
            rfturn Toolkit.gftDffbultToolkit().
                isFrbmfStbtfSupportfd(stbtf);
        } flsf {
            rfturn (stbtf == Frbmf.NORMAL); // otifrs brf not gubrbntffd
        }
    }

    /**
     * Support for I18N: bny visiblf strings siould bf storfd in
     * sun.bwt.rfsourdfs.bwt.propfrtifs.  Tif RfsourdfBundlf is storfd
     * ifrf, so tibt only onf dopy is mbintbinfd.
     */
    privbtf stbtid RfsourdfBundlf rfsourdfs;
    privbtf stbtid RfsourdfBundlf plbtformRfsourdfs;

    // dbllfd by plbtform toolkit
    privbtf stbtid void sftPlbtformRfsourdfs(RfsourdfBundlf bundlf) {
        plbtformRfsourdfs = bundlf;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod ids
     */
    privbtf stbtid nbtivf void initIDs();

    /**
     * WARNING: Tiis is b tfmporbry workbround for b problfm in tif
     * wby tif AWT lobds nbtivf librbrifs. A numbfr of dlbssfs in tif
     * AWT pbdkbgf ibvf b nbtivf mftiod, initIDs(), wiidi initiblizfs
     * tif JNI fifld bnd mftiod ids usfd in tif nbtivf portion of
     * tifir implfmfntbtion.
     *
     * Sindf tif usf bnd storbgf of tifsf ids is donf by tif
     * implfmfntbtion librbrifs, tif implfmfntbtion of tifsf mftiod is
     * providfd by tif pbrtidulbr AWT implfmfntbtions (for fxbmplf,
     * "Toolkit"s/Pffr), sudi bs Motif, Midrosoft Windows, or Tiny. Tif
     * problfm is tibt tiis mfbns tibt tif nbtivf librbrifs must bf
     * lobdfd by tif jbvb.* dlbssfs, wiidi do not nfdfssbrily know tif
     * nbmfs of tif librbrifs to lobd. A bfttfr wby of doing tiis
     * would bf to providf b sfpbrbtf librbry wiidi dffinfs jbvb.bwt.*
     * initIDs, bnd fxports tif rflfvbnt symbols out to tif
     * implfmfntbtion librbrifs.
     *
     * For now, wf know it's donf by tif implfmfntbtion, bnd wf bssumf
     * tibt tif nbmf of tif librbry is "bwt".  -br.
     *
     * If you dibngf lobdLibrbrifs(), plfbsf bdd tif dibngf to
     * jbvb.bwt.imbgf.ColorModfl.lobdLibrbrifs(). Unfortunbtfly,
     * dlbssfs dbn bf lobdfd in jbvb.bwt.imbgf tibt dfpfnd on
     * libbwt bnd tifrf is no wby to dbll Toolkit.lobdLibrbrifs()
     * dirfdtly.  -iung
     */
    privbtf stbtid boolfbn lobdfd = fblsf;
    stbtid void lobdLibrbrifs() {
        if (!lobdfd) {
            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        Systfm.lobdLibrbry("bwt");
                        rfturn null;
                    }
                });
            lobdfd = truf;
        }
    }

    stbtid {
        AWTAddfssor.sftToolkitAddfssor(
                nfw AWTAddfssor.ToolkitAddfssor() {
                    @Ovfrridf
                    publid void sftPlbtformRfsourdfs(RfsourdfBundlf bundlf) {
                        Toolkit.sftPlbtformRfsourdfs(bundlf);
                    }
                });

        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                                 nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
            publid Void run() {
                try {
                    rfsourdfs =
                        RfsourdfBundlf.gftBundlf("sun.bwt.rfsourdfs.bwt",
                                                 CorfRfsourdfBundlfControl.gftRBControlInstbndf());
                } dbtdi (MissingRfsourdfExdfption f) {
                    // No rfsourdf filf; dffbults will bf usfd.
                }
                rfturn null;
            }
        });

        // fnsurf tibt tif propfr librbrifs brf lobdfd
        lobdLibrbrifs();
        initAssistivfTfdinologifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Gfts b propfrty witi tif spfdififd kfy bnd dffbult.
     * Tiis mftiod rfturns dffbultVbluf if tif propfrty is not found.
     *
     * @pbrbm  kfy tif kfy
     * @pbrbm  dffbultVbluf tif dffbult vbluf
     * @rfturn tif vbluf of tif propfrty or tif dffbult vbluf
     *         if tif propfrty wbs not found
     */
    publid stbtid String gftPropfrty(String kfy, String dffbultVbluf) {
        // first try plbtform spfdifid bundlf
        if (plbtformRfsourdfs != null) {
            try {
                rfturn plbtformRfsourdfs.gftString(kfy);
            }
            dbtdi (MissingRfsourdfExdfption f) {}
        }

        // tifn sibrfd onf
        if (rfsourdfs != null) {
            try {
                rfturn rfsourdfs.gftString(kfy);
            }
            dbtdi (MissingRfsourdfExdfption f) {}
        }

        rfturn dffbultVbluf;
    }

    /**
     * Gft tif bpplidbtion's or bpplft's EvfntQufuf instbndf.
     * Dfpfnding on tif Toolkit implfmfntbtion, difffrfnt EvfntQufufs
     * mby bf rfturnfd for difffrfnt bpplfts.  Applfts siould
     * tifrfforf not bssumf tibt tif EvfntQufuf instbndf rfturnfd
     * by tiis mftiod will bf sibrfd by otifr bpplfts or tif systfm.
     *
     * <p> If tifrf is b sfdurity mbnbgfr tifn its
     * {@link SfdurityMbnbgfr#difdkPfrmission difdkPfrmission} mftiod
     * is dbllfd to difdk {@dodf AWTPfrmission("bddfssEvfntQufuf")}.
     *
     * @rfturn    tif <dodf>EvfntQufuf</dodf> objfdt
     * @tirows  SfdurityExdfption
     *          if b sfdurity mbnbgfr is sft bnd it dfnifs bddfss to
     *          tif {@dodf EvfntQufuf}
     * @sff     jbvb.bwt.AWTPfrmission
    */
    publid finbl EvfntQufuf gftSystfmEvfntQufuf() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.CHECK_AWT_EVENTQUEUE_PERMISSION);
        }
        rfturn gftSystfmEvfntQufufImpl();
    }

    /**
     * Gfts tif bpplidbtion's or bpplft's <dodf>EvfntQufuf</dodf>
     * instbndf, witiout difdking bddfss.  For sfdurity rfbsons,
     * tiis dbn only bf dbllfd from b <dodf>Toolkit</dodf> subdlbss.
     * @rfturn tif <dodf>EvfntQufuf</dodf> objfdt
     */
    protfdtfd bbstrbdt EvfntQufuf gftSystfmEvfntQufufImpl();

    /* Addfssor mftiod for usf by AWT pbdkbgf routinfs. */
    stbtid EvfntQufuf gftEvfntQufuf() {
        rfturn gftDffbultToolkit().gftSystfmEvfntQufufImpl();
    }

    /**
     * Crfbtfs tif pffr for b DrbgSourdfContfxt.
     * Alwbys tirows InvblidDndOpfrbtionExdfption if
     * GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf.
     *
     * @pbrbm  dgf tif {@dodf DrbgGfsturfEvfnt}
     * @rfturn tif pffr drfbtfd
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid bbstrbdt DrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(DrbgGfsturfEvfnt dgf) tirows InvblidDnDOpfrbtionExdfption;

    /**
     * Crfbtfs b dondrftf, plbtform dfpfndfnt, subdlbss of tif bbstrbdt
     * DrbgGfsturfRfdognizfr dlbss rfqufstfd, bnd bssodibtfs it witi tif
     * DrbgSourdf, Componfnt bnd DrbgGfsturfListfnfr spfdififd.
     *
     * subdlbssfs siould ovfrridf tiis to providf tifir own implfmfntbtion
     *
     * @pbrbm bbstrbdtRfdognizfrClbss Tif bbstrbdt dlbss of tif rfquirfd rfdognizfr
     * @pbrbm ds                      Tif DrbgSourdf
     * @pbrbm d                       Tif Componfnt tbrgft for tif DrbgGfsturfRfdognizfr
     * @pbrbm srdAdtions              Tif bdtions pfrmittfd for tif gfsturf
     * @pbrbm dgl                     Tif DrbgGfsturfListfnfr
     *
     * @rfturn tif nfw objfdt or null.  Alwbys rfturns null if
     * GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid <T fxtfnds DrbgGfsturfRfdognizfr> T
        drfbtfDrbgGfsturfRfdognizfr(Clbss<T> bbstrbdtRfdognizfrClbss,
                                    DrbgSourdf ds, Componfnt d, int srdAdtions,
                                    DrbgGfsturfListfnfr dgl)
    {
        rfturn null;
    }

    /**
     * Obtbins b vbluf for tif spfdififd dfsktop propfrty.
     *
     * A dfsktop propfrty is b uniqufly nbmfd vbluf for b rfsourdf tibt
     * is Toolkit globbl in nbturf. Usublly it blso is bn bbstrbdt
     * rfprfsfntbtion for bn undfrlying plbtform dfpfndfnt dfsktop sftting.
     * For morf informbtion on dfsktop propfrtifs supportfd by tif AWT sff
     * <b irff="dod-filfs/DfsktopPropfrtifs.itml">AWT Dfsktop Propfrtifs</b>.
     *
     * @pbrbm  propfrtyNbmf tif propfrty nbmf
     * @rfturn tif vbluf for tif spfdififd dfsktop propfrty
     */
    publid finbl syndironizfd Objfdt gftDfsktopPropfrty(String propfrtyNbmf) {
        // Tiis is b workbround for ifbdlfss toolkits.  It would bf
        // bfttfr to ovfrridf tiis mftiod but it is dfdlbrfd finbl.
        // "tiis instbndfof" syntbx dfffbts polymorpiism.
        // --mm, 03/03/00
        if (tiis instbndfof HfbdlfssToolkit) {
            rfturn ((HfbdlfssToolkit)tiis).gftUndfrlyingToolkit()
                .gftDfsktopPropfrty(propfrtyNbmf);
        }

        if (dfsktopPropfrtifs.isEmpty()) {
            initiblizfDfsktopPropfrtifs();
        }

        Objfdt vbluf;

        // Tiis propfrty siould nfvfr bf dbdifd
        if (propfrtyNbmf.fqubls("bwt.dynbmidLbyoutSupportfd")) {
            rfturn gftDffbultToolkit().lbzilyLobdDfsktopPropfrty(propfrtyNbmf);
        }

        vbluf = dfsktopPropfrtifs.gft(propfrtyNbmf);

        if (vbluf == null) {
            vbluf = lbzilyLobdDfsktopPropfrty(propfrtyNbmf);

            if (vbluf != null) {
                sftDfsktopPropfrty(propfrtyNbmf, vbluf);
            }
        }

        /* for propfrty "bwt.font.dfsktopiints" */
        if (vbluf instbndfof RfndfringHints) {
            vbluf = ((RfndfringHints)vbluf).dlonf();
        }

        rfturn vbluf;
    }

    /**
     * Sfts tif nbmfd dfsktop propfrty to tif spfdififd vbluf bnd firfs b
     * propfrty dibngf fvfnt to notify bny listfnfrs tibt tif vbluf ibs dibngfd.
     *
     * @pbrbm  nbmf tif propfrty nbmf
     * @pbrbm  nfwVbluf tif nfw propfrty vbluf
     */
    protfdtfd finbl void sftDfsktopPropfrty(String nbmf, Objfdt nfwVbluf) {
        // Tiis is b workbround for ifbdlfss toolkits.  It would bf
        // bfttfr to ovfrridf tiis mftiod but it is dfdlbrfd finbl.
        // "tiis instbndfof" syntbx dfffbts polymorpiism.
        // --mm, 03/03/00
        if (tiis instbndfof HfbdlfssToolkit) {
            ((HfbdlfssToolkit)tiis).gftUndfrlyingToolkit()
                .sftDfsktopPropfrty(nbmf, nfwVbluf);
            rfturn;
        }
        Objfdt oldVbluf;

        syndironizfd (tiis) {
            oldVbluf = dfsktopPropfrtifs.gft(nbmf);
            dfsktopPropfrtifs.put(nbmf, nfwVbluf);
        }

        // Don't firf dibngf fvfnt if old bnd nfw vblufs brf null.
        // It iflps to bvoid rfdursivf rfsfnding of WM_THEMECHANGED
        if (oldVbluf != null || nfwVbluf != null) {
            dfsktopPropsSupport.firfPropfrtyCibngf(nbmf, oldVbluf, nfwVbluf);
        }
    }

    /**
     * bn opportunity to lbzily fvblubtf dfsktop propfrty vblufs.
     */
    protfdtfd Objfdt lbzilyLobdDfsktopPropfrty(String nbmf) {
        rfturn null;
    }

    /**
     * initiblizfDfsktopPropfrtifs
     */
    protfdtfd void initiblizfDfsktopPropfrtifs() {
    }

    /**
     * Adds tif spfdififd propfrty dibngf listfnfr for tif nbmfd dfsktop
     * propfrty. Wifn b {@link jbvb.bfbns.PropfrtyCibngfListfnfrProxy} objfdt is bddfd,
     * its propfrty nbmf is ignorfd, bnd tif wrbppfd listfnfr is bddfd.
     * If {@dodf nbmf} is {@dodf null} or {@dodf pdl} is {@dodf null},
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm   nbmf Tif nbmf of tif propfrty to listfn for
     * @pbrbm   pdl Tif propfrty dibngf listfnfr
     * @sff PropfrtyCibngfSupport#bddPropfrtyCibngfListfnfr(String,
                PropfrtyCibngfListfnfr)
     * @sindf   1.2
     */
    publid void bddPropfrtyCibngfListfnfr(String nbmf, PropfrtyCibngfListfnfr pdl) {
        dfsktopPropsSupport.bddPropfrtyCibngfListfnfr(nbmf, pdl);
    }

    /**
     * Rfmovfs tif spfdififd propfrty dibngf listfnfr for tif nbmfd
     * dfsktop propfrty. Wifn b {@link jbvb.bfbns.PropfrtyCibngfListfnfrProxy} objfdt
     * is rfmovfd, its propfrty nbmf is ignorfd, bnd
     * tif wrbppfd listfnfr is rfmovfd.
     * If {@dodf nbmf} is {@dodf null} or {@dodf pdl} is {@dodf null},
     * no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm   nbmf Tif nbmf of tif propfrty to rfmovf
     * @pbrbm   pdl Tif propfrty dibngf listfnfr
     * @sff PropfrtyCibngfSupport#rfmovfPropfrtyCibngfListfnfr(String,
                PropfrtyCibngfListfnfr)
     * @sindf   1.2
     */
    publid void rfmovfPropfrtyCibngfListfnfr(String nbmf, PropfrtyCibngfListfnfr pdl) {
        dfsktopPropsSupport.rfmovfPropfrtyCibngfListfnfr(nbmf, pdl);
    }

    /**
     * Rfturns bn brrby of bll tif propfrty dibngf listfnfrs
     * rfgistfrfd on tiis toolkit. Tif rfturnfd brrby
     * dontbins {@link jbvb.bfbns.PropfrtyCibngfListfnfrProxy} objfdts
     * tibt bssodibtf listfnfrs witi tif nbmfs of dfsktop propfrtifs.
     *
     * @rfturn bll of tiis toolkit's {@link PropfrtyCibngfListfnfr}
     *         objfdts wrbppfd in {@dodf jbvb.bfbns.PropfrtyCibngfListfnfrProxy} objfdts
     *         or bn fmpty brrby  if no listfnfrs brf bddfd
     *
     * @sff PropfrtyCibngfSupport#gftPropfrtyCibngfListfnfrs()
     * @sindf 1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs() {
        rfturn dfsktopPropsSupport.gftPropfrtyCibngfListfnfrs();
    }

    /**
     * Rfturns bn brrby of bll propfrty dibngf listfnfrs
     * bssodibtfd witi tif spfdififd nbmf of b dfsktop propfrty.
     *
     * @pbrbm  propfrtyNbmf tif nbmfd propfrty
     * @rfturn bll of tif {@dodf PropfrtyCibngfListfnfr} objfdts
     *         bssodibtfd witi tif spfdififd nbmf of b dfsktop propfrty
     *         or bn fmpty brrby if no sudi listfnfrs brf bddfd
     *
     * @sff PropfrtyCibngfSupport#gftPropfrtyCibngfListfnfrs(String)
     * @sindf 1.4
     */
    publid PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(String propfrtyNbmf) {
        rfturn dfsktopPropsSupport.gftPropfrtyCibngfListfnfrs(propfrtyNbmf);
    }

    protfdtfd finbl Mbp<String,Objfdt> dfsktopPropfrtifs =
            nfw HbsiMbp<String,Objfdt>();
    protfdtfd finbl PropfrtyCibngfSupport dfsktopPropsSupport =
            Toolkit.drfbtfPropfrtyCibngfSupport(tiis);

    /**
     * Rfturns wiftifr tif blwbys-on-top modf is supportfd by tiis toolkit.
     * To dftfdt wiftifr tif blwbys-on-top modf is supportfd for b
     * pbrtidulbr Window, usf {@link Window#isAlwbysOnTopSupportfd}.
     * @rfturn <dodf>truf</dodf>, if durrfnt toolkit supports tif blwbys-on-top modf,
     *     otifrwisf rfturns <dodf>fblsf</dodf>
     * @sff Window#isAlwbysOnTopSupportfd
     * @sff Window#sftAlwbysOnTop(boolfbn)
     * @sindf 1.6
     */
    publid boolfbn isAlwbysOnTopSupportfd() {
        rfturn truf;
    }

    /**
     * Rfturns wiftifr tif givfn modblity typf is supportfd by tiis toolkit. If
     * b diblog witi unsupportfd modblity typf is drfbtfd, tifn
     * <dodf>Diblog.ModblityTypf.MODELESS</dodf> is usfd instfbd.
     *
     * @pbrbm modblityTypf modblity typf to bf difdkfd for support by tiis toolkit
     *
     * @rfturn <dodf>truf</dodf>, if durrfnt toolkit supports givfn modblity
     *     typf, <dodf>fblsf</dodf> otifrwisf
     *
     * @sff jbvb.bwt.Diblog.ModblityTypf
     * @sff jbvb.bwt.Diblog#gftModblityTypf
     * @sff jbvb.bwt.Diblog#sftModblityTypf
     *
     * @sindf 1.6
     */
    publid bbstrbdt boolfbn isModblityTypfSupportfd(Diblog.ModblityTypf modblityTypf);

    /**
     * Rfturns wiftifr tif givfn modbl fxdlusion typf is supportfd by tiis
     * toolkit. If bn unsupportfd modbl fxdlusion typf propfrty is sft on b window,
     * tifn <dodf>Diblog.ModblExdlusionTypf.NO_EXCLUDE</dodf> is usfd instfbd.
     *
     * @pbrbm modblExdlusionTypf modbl fxdlusion typf to bf difdkfd for support by tiis toolkit
     *
     * @rfturn <dodf>truf</dodf>, if durrfnt toolkit supports givfn modbl fxdlusion
     *     typf, <dodf>fblsf</dodf> otifrwisf
     *
     * @sff jbvb.bwt.Diblog.ModblExdlusionTypf
     * @sff jbvb.bwt.Window#gftModblExdlusionTypf
     * @sff jbvb.bwt.Window#sftModblExdlusionTypf
     *
     * @sindf 1.6
     */
    publid bbstrbdt boolfbn isModblExdlusionTypfSupportfd(Diblog.ModblExdlusionTypf modblExdlusionTypf);

    // 8014718: logging ibs bffn rfmovfd from SunToolkit

    privbtf stbtid finbl int LONG_BITS = 64;
    privbtf int[] dblls = nfw int[LONG_BITS];
    privbtf stbtid volbtilf long fnbblfdOnToolkitMbsk;
    privbtf AWTEvfntListfnfr fvfntListfnfr = null;
    privbtf WfbkHbsiMbp<AWTEvfntListfnfr, SflfdtivfAWTEvfntListfnfr> listfnfr2SflfdtivfListfnfr = nfw WfbkHbsiMbp<>();

    /*
     * Extrbdts b "purf" AWTEvfntListfnfr from b AWTEvfntListfnfrProxy,
     * if tif listfnfr is proxifd.
     */
    stbtid privbtf AWTEvfntListfnfr dfProxyAWTEvfntListfnfr(AWTEvfntListfnfr l)
    {
        AWTEvfntListfnfr lodblL = l;

        if (lodblL == null) {
            rfturn null;
        }
        // if usfr pbssfd in b AWTEvfntListfnfrProxy objfdt, fxtrbdt
        // tif listfnfr
        if (l instbndfof AWTEvfntListfnfrProxy) {
            lodblL = ((AWTEvfntListfnfrProxy)l).gftListfnfr();
        }
        rfturn lodblL;
    }

    /**
     * Adds bn AWTEvfntListfnfr to rfdfivf bll AWTEvfnts dispbtdifd
     * systfm-widf tibt donform to tif givfn <dodf>fvfntMbsk</dodf>.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkPfrmission</dodf>
     * mftiod is dbllfd witi bn
     * <dodf>AWTPfrmission("listfnToAllAWTEvfnts")</dodf> pfrmission.
     * Tiis mby rfsult in b SfdurityExdfption.
     * <p>
     * <dodf>fvfntMbsk</dodf> is b bitmbsk of fvfnt typfs to rfdfivf.
     * It is donstrudtfd by bitwisf OR-ing togftifr tif fvfnt mbsks
     * dffinfd in <dodf>AWTEvfnt</dodf>.
     * <p>
     * Notf:  fvfnt listfnfr usf is not rfdommfndfd for normbl
     * bpplidbtion usf, but brf intfndfd solfly to support spfdibl
     * purposf fbdilitifs indluding support for bddfssibility,
     * fvfnt rfdord/plbybbdk, bnd dibgnostid trbding.
     *
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm    listfnfr   tif fvfnt listfnfr.
     * @pbrbm    fvfntMbsk  tif bitmbsk of fvfnt typfs to rfdfivf
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @sff      #rfmovfAWTEvfntListfnfr
     * @sff      #gftAWTEvfntListfnfrs
     * @sff      SfdurityMbnbgfr#difdkPfrmission
     * @sff      jbvb.bwt.AWTEvfnt
     * @sff      jbvb.bwt.AWTPfrmission
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfr
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfrProxy
     * @sindf    1.2
     */
    publid void bddAWTEvfntListfnfr(AWTEvfntListfnfr listfnfr, long fvfntMbsk) {
        AWTEvfntListfnfr lodblL = dfProxyAWTEvfntListfnfr(listfnfr);

        if (lodblL == null) {
            rfturn;
        }
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
          sfdurity.difdkPfrmission(AWTPfrmissions.ALL_AWT_EVENTS_PERMISSION);
        }
        syndironizfd (tiis) {
            SflfdtivfAWTEvfntListfnfr sflfdtivfListfnfr =
                listfnfr2SflfdtivfListfnfr.gft(lodblL);

            if (sflfdtivfListfnfr == null) {
                // Crfbtf b nfw sflfdtivfListfnfr.
                sflfdtivfListfnfr = nfw SflfdtivfAWTEvfntListfnfr(lodblL,
                                                                 fvfntMbsk);
                listfnfr2SflfdtivfListfnfr.put(lodblL, sflfdtivfListfnfr);
                fvfntListfnfr = ToolkitEvfntMultidbstfr.bdd(fvfntListfnfr,
                                                            sflfdtivfListfnfr);
            }
            // OR tif fvfntMbsk into tif sflfdtivfListfnfr's fvfnt mbsk.
            sflfdtivfListfnfr.orEvfntMbsks(fvfntMbsk);

            fnbblfdOnToolkitMbsk |= fvfntMbsk;

            long mbsk = fvfntMbsk;
            for (int i=0; i<LONG_BITS; i++) {
                // If no bits brf sft, brfbk out of loop.
                if (mbsk == 0) {
                    brfbk;
                }
                if ((mbsk & 1L) != 0) {  // Alwbys tfst bit 0.
                    dblls[i]++;
                }
                mbsk >>>= 1;  // Rigit siift, fill witi zfros on lfft.
            }
        }
    }

    /**
     * Rfmovfs bn AWTEvfntListfnfr from rfdfiving dispbtdifd AWTEvfnts.
     * <p>
     * First, if tifrf is b sfdurity mbnbgfr, its <dodf>difdkPfrmission</dodf>
     * mftiod is dbllfd witi bn
     * <dodf>AWTPfrmission("listfnToAllAWTEvfnts")</dodf> pfrmission.
     * Tiis mby rfsult in b SfdurityExdfption.
     * <p>
     * Notf:  fvfnt listfnfr usf is not rfdommfndfd for normbl
     * bpplidbtion usf, but brf intfndfd solfly to support spfdibl
     * purposf fbdilitifs indluding support for bddfssibility,
     * fvfnt rfdord/plbybbdk, bnd dibgnostid trbding.
     *
     * If listfnfr is null, no fxdfption is tirown bnd no bdtion is pfrformfd.
     *
     * @pbrbm    listfnfr   tif fvfnt listfnfr.
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @sff      #bddAWTEvfntListfnfr
     * @sff      #gftAWTEvfntListfnfrs
     * @sff      SfdurityMbnbgfr#difdkPfrmission
     * @sff      jbvb.bwt.AWTEvfnt
     * @sff      jbvb.bwt.AWTPfrmission
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfr
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfrProxy
     * @sindf    1.2
     */
    publid void rfmovfAWTEvfntListfnfr(AWTEvfntListfnfr listfnfr) {
        AWTEvfntListfnfr lodblL = dfProxyAWTEvfntListfnfr(listfnfr);

        if (listfnfr == null) {
            rfturn;
        }
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ALL_AWT_EVENTS_PERMISSION);
        }

        syndironizfd (tiis) {
            SflfdtivfAWTEvfntListfnfr sflfdtivfListfnfr =
                listfnfr2SflfdtivfListfnfr.gft(lodblL);

            if (sflfdtivfListfnfr != null) {
                listfnfr2SflfdtivfListfnfr.rfmovf(lodblL);
                int[] listfnfrCblls = sflfdtivfListfnfr.gftCblls();
                for (int i=0; i<LONG_BITS; i++) {
                    dblls[i] -= listfnfrCblls[i];
                    bssfrt dblls[i] >= 0: "Nfgbtivf Listfnfrs dount";

                    if (dblls[i] == 0) {
                        fnbblfdOnToolkitMbsk &= ~(1L<<i);
                    }
                }
            }
            fvfntListfnfr = ToolkitEvfntMultidbstfr.rfmovf(fvfntListfnfr,
            (sflfdtivfListfnfr == null) ? lodblL : sflfdtivfListfnfr);
        }
    }

    stbtid boolfbn fnbblfdOnToolkit(long fvfntMbsk) {
        rfturn (fnbblfdOnToolkitMbsk & fvfntMbsk) != 0;
        }

    syndironizfd int dountAWTEvfntListfnfrs(long fvfntMbsk) {
        int di = 0;
        for (; fvfntMbsk != 0; fvfntMbsk >>>= 1, di++) {
        }
        di--;
        rfturn dblls[di];
    }
    /**
     * Rfturns bn brrby of bll tif <dodf>AWTEvfntListfnfr</dodf>s
     * rfgistfrfd on tiis toolkit.
     * If tifrf is b sfdurity mbnbgfr, its {@dodf difdkPfrmission}
     * mftiod is dbllfd witi bn
     * {@dodf AWTPfrmission("listfnToAllAWTEvfnts")} pfrmission.
     * Tiis mby rfsult in b SfdurityExdfption.
     * Listfnfrs dbn bf rfturnfd
     * witiin <dodf>AWTEvfntListfnfrProxy</dodf> objfdts, wiidi blso dontbin
     * tif fvfnt mbsk for tif givfn listfnfr.
     * Notf tibt listfnfr objfdts
     * bddfd multiplf timfs bppfbr only ondf in tif rfturnfd brrby.
     *
     * @rfturn bll of tif <dodf>AWTEvfntListfnfr</dodf>s or bn fmpty
     *         brrby if no listfnfrs brf durrfntly rfgistfrfd
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @sff      #bddAWTEvfntListfnfr
     * @sff      #rfmovfAWTEvfntListfnfr
     * @sff      SfdurityMbnbgfr#difdkPfrmission
     * @sff      jbvb.bwt.AWTEvfnt
     * @sff      jbvb.bwt.AWTPfrmission
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfr
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfrProxy
     * @sindf 1.4
     */
    publid AWTEvfntListfnfr[] gftAWTEvfntListfnfrs() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ALL_AWT_EVENTS_PERMISSION);
        }
        syndironizfd (tiis) {
            EvfntListfnfr[] lb = ToolkitEvfntMultidbstfr.gftListfnfrs(fvfntListfnfr,AWTEvfntListfnfr.dlbss);

            AWTEvfntListfnfr[] rft = nfw AWTEvfntListfnfr[lb.lfngti];
            for (int i = 0; i < lb.lfngti; i++) {
                SflfdtivfAWTEvfntListfnfr sbfl = (SflfdtivfAWTEvfntListfnfr)lb[i];
                AWTEvfntListfnfr tfmpL = sbfl.gftListfnfr();
                //bssfrt tfmpL is not bn AWTEvfntListfnfrProxy - wf siould
                // ibvf wffdfd tifm bll out
                // don't wbnt to wrbp b proxy insidf b proxy
                rft[i] = nfw AWTEvfntListfnfrProxy(sbfl.gftEvfntMbsk(), tfmpL);
            }
            rfturn rft;
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>AWTEvfntListfnfr</dodf>s
     * rfgistfrfd on tiis toolkit wiidi listfn to bll of tif fvfnt
     * typfs spfdififd in tif {@dodf fvfntMbsk} brgumfnt.
     * If tifrf is b sfdurity mbnbgfr, its {@dodf difdkPfrmission}
     * mftiod is dbllfd witi bn
     * {@dodf AWTPfrmission("listfnToAllAWTEvfnts")} pfrmission.
     * Tiis mby rfsult in b SfdurityExdfption.
     * Listfnfrs dbn bf rfturnfd
     * witiin <dodf>AWTEvfntListfnfrProxy</dodf> objfdts, wiidi blso dontbin
     * tif fvfnt mbsk for tif givfn listfnfr.
     * Notf tibt listfnfr objfdts
     * bddfd multiplf timfs bppfbr only ondf in tif rfturnfd brrby.
     *
     * @pbrbm  fvfntMbsk tif bitmbsk of fvfnt typfs to listfn for
     * @rfturn bll of tif <dodf>AWTEvfntListfnfr</dodf>s rfgistfrfd
     *         on tiis toolkit for tif spfdififd
     *         fvfnt typfs, or bn fmpty brrby if no sudi listfnfrs
     *         brf durrfntly rfgistfrfd
     * @tirows SfdurityExdfption
     *        if b sfdurity mbnbgfr fxists bnd its
     *        <dodf>difdkPfrmission</dodf> mftiod dofsn't bllow tif opfrbtion.
     * @sff      #bddAWTEvfntListfnfr
     * @sff      #rfmovfAWTEvfntListfnfr
     * @sff      SfdurityMbnbgfr#difdkPfrmission
     * @sff      jbvb.bwt.AWTEvfnt
     * @sff      jbvb.bwt.AWTPfrmission
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfr
     * @sff      jbvb.bwt.fvfnt.AWTEvfntListfnfrProxy
     * @sindf 1.4
     */
    publid AWTEvfntListfnfr[] gftAWTEvfntListfnfrs(long fvfntMbsk) {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkPfrmission(AWTPfrmissions.ALL_AWT_EVENTS_PERMISSION);
        }
        syndironizfd (tiis) {
            EvfntListfnfr[] lb = ToolkitEvfntMultidbstfr.gftListfnfrs(fvfntListfnfr,AWTEvfntListfnfr.dlbss);

            jbvb.util.List<AWTEvfntListfnfrProxy> list = nfw ArrbyList<>(lb.lfngti);

            for (int i = 0; i < lb.lfngti; i++) {
                SflfdtivfAWTEvfntListfnfr sbfl = (SflfdtivfAWTEvfntListfnfr)lb[i];
                if ((sbfl.gftEvfntMbsk() & fvfntMbsk) == fvfntMbsk) {
                    //AWTEvfntListfnfr tfmpL = sbfl.gftListfnfr();
                    list.bdd(nfw AWTEvfntListfnfrProxy(sbfl.gftEvfntMbsk(),
                                                       sbfl.gftListfnfr()));
                }
            }
            rfturn list.toArrby(nfw AWTEvfntListfnfr[0]);
        }
    }

    /*
     * Tiis mftiod notififs bny AWTEvfntListfnfrs tibt bn fvfnt
     * is bbout to bf dispbtdifd.
     *
     * @pbrbm tifEvfnt tif fvfnt wiidi will bf dispbtdifd.
     */
    void notifyAWTEvfntListfnfrs(AWTEvfnt tifEvfnt) {
        // Tiis is b workbround for ifbdlfss toolkits.  It would bf
        // bfttfr to ovfrridf tiis mftiod but it is dfdlbrfd pbdkbgf privbtf.
        // "tiis instbndfof" syntbx dfffbts polymorpiism.
        // --mm, 03/03/00
        if (tiis instbndfof HfbdlfssToolkit) {
            ((HfbdlfssToolkit)tiis).gftUndfrlyingToolkit()
                .notifyAWTEvfntListfnfrs(tifEvfnt);
            rfturn;
        }

        AWTEvfntListfnfr fvfntListfnfr = tiis.fvfntListfnfr;
        if (fvfntListfnfr != null) {
            fvfntListfnfr.fvfntDispbtdifd(tifEvfnt);
        }
    }

    stbtid privbtf dlbss ToolkitEvfntMultidbstfr fxtfnds AWTEvfntMultidbstfr
        implfmfnts AWTEvfntListfnfr {
        // Implfmfntbtion dlonfd from AWTEvfntMultidbstfr.

        ToolkitEvfntMultidbstfr(AWTEvfntListfnfr b, AWTEvfntListfnfr b) {
            supfr(b, b);
        }

        @SupprfssWbrnings("ovfrlobds")
        stbtid AWTEvfntListfnfr bdd(AWTEvfntListfnfr b,
                                    AWTEvfntListfnfr b) {
            if (b == null)  rfturn b;
            if (b == null)  rfturn b;
            rfturn nfw ToolkitEvfntMultidbstfr(b, b);
        }

        @SupprfssWbrnings("ovfrlobds")
        stbtid AWTEvfntListfnfr rfmovf(AWTEvfntListfnfr l,
                                       AWTEvfntListfnfr oldl) {
            rfturn (AWTEvfntListfnfr) rfmovfIntfrnbl(l, oldl);
        }

        // #4178589: must ovfrlobd rfmovf(EvfntListfnfr) to dbll our bdd()
        // instfbd of tif stbtid bddIntfrnbl() so wf bllodbtf b
        // ToolkitEvfntMultidbstfr instfbd of bn AWTEvfntMultidbstfr.
        // Notf: tiis mftiod is dbllfd by AWTEvfntListfnfr.rfmovfIntfrnbl(),
        // so its mftiod signbturf must mbtdi AWTEvfntListfnfr.rfmovf().
        protfdtfd EvfntListfnfr rfmovf(EvfntListfnfr oldl) {
            if (oldl == b)  rfturn b;
            if (oldl == b)  rfturn b;
            AWTEvfntListfnfr b2 = (AWTEvfntListfnfr)rfmovfIntfrnbl(b, oldl);
            AWTEvfntListfnfr b2 = (AWTEvfntListfnfr)rfmovfIntfrnbl(b, oldl);
            if (b2 == b && b2 == b) {
                rfturn tiis;    // it's not ifrf
            }
            rfturn bdd(b2, b2);
        }

        publid void fvfntDispbtdifd(AWTEvfnt fvfnt) {
            ((AWTEvfntListfnfr)b).fvfntDispbtdifd(fvfnt);
            ((AWTEvfntListfnfr)b).fvfntDispbtdifd(fvfnt);
        }
    }

    privbtf dlbss SflfdtivfAWTEvfntListfnfr implfmfnts AWTEvfntListfnfr {
        AWTEvfntListfnfr listfnfr;
        privbtf long fvfntMbsk;
        // Tiis brrby dontbins tif numbfr of timfs to dbll tif fvfntlistfnfr
        // for fbdi fvfnt typf.
        int[] dblls = nfw int[Toolkit.LONG_BITS];

        publid AWTEvfntListfnfr gftListfnfr() {rfturn listfnfr;}
        publid long gftEvfntMbsk() {rfturn fvfntMbsk;}
        publid int[] gftCblls() {rfturn dblls;}

        publid void orEvfntMbsks(long mbsk) {
            fvfntMbsk |= mbsk;
            // For fbdi fvfnt bit sft in mbsk, indrfmfnt its dbll dount.
            for (int i=0; i<Toolkit.LONG_BITS; i++) {
                // If no bits brf sft, brfbk out of loop.
                if (mbsk == 0) {
                    brfbk;
                }
                if ((mbsk & 1L) != 0) {  // Alwbys tfst bit 0.
                    dblls[i]++;
                }
                mbsk >>>= 1;  // Rigit siift, fill witi zfros on lfft.
            }
        }

        SflfdtivfAWTEvfntListfnfr(AWTEvfntListfnfr l, long mbsk) {
            listfnfr = l;
            fvfntMbsk = mbsk;
        }

        publid void fvfntDispbtdifd(AWTEvfnt fvfnt) {
            long fvfntBit = 0; // Usfd to sbvf tif bit of tif fvfnt typf.
            if (((fvfntBit = fvfntMbsk & AWTEvfnt.COMPONENT_EVENT_MASK) != 0 &&
                 fvfnt.id >= ComponfntEvfnt.COMPONENT_FIRST &&
                 fvfnt.id <= ComponfntEvfnt.COMPONENT_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.CONTAINER_EVENT_MASK) != 0 &&
                 fvfnt.id >= ContbinfrEvfnt.CONTAINER_FIRST &&
                 fvfnt.id <= ContbinfrEvfnt.CONTAINER_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.FOCUS_EVENT_MASK) != 0 &&
                 fvfnt.id >= FodusEvfnt.FOCUS_FIRST &&
                 fvfnt.id <= FodusEvfnt.FOCUS_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.KEY_EVENT_MASK) != 0 &&
                 fvfnt.id >= KfyEvfnt.KEY_FIRST &&
                 fvfnt.id <= KfyEvfnt.KEY_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.MOUSE_WHEEL_EVENT_MASK) != 0 &&
                 fvfnt.id == MousfEvfnt.MOUSE_WHEEL)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.MOUSE_MOTION_EVENT_MASK) != 0 &&
                 (fvfnt.id == MousfEvfnt.MOUSE_MOVED ||
                  fvfnt.id == MousfEvfnt.MOUSE_DRAGGED))
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.MOUSE_EVENT_MASK) != 0 &&
                 fvfnt.id != MousfEvfnt.MOUSE_MOVED &&
                 fvfnt.id != MousfEvfnt.MOUSE_DRAGGED &&
                 fvfnt.id != MousfEvfnt.MOUSE_WHEEL &&
                 fvfnt.id >= MousfEvfnt.MOUSE_FIRST &&
                 fvfnt.id <= MousfEvfnt.MOUSE_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.WINDOW_EVENT_MASK) != 0 &&
                 (fvfnt.id >= WindowEvfnt.WINDOW_FIRST &&
                 fvfnt.id <= WindowEvfnt.WINDOW_LAST))
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.ACTION_EVENT_MASK) != 0 &&
                 fvfnt.id >= AdtionEvfnt.ACTION_FIRST &&
                 fvfnt.id <= AdtionEvfnt.ACTION_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.ADJUSTMENT_EVENT_MASK) != 0 &&
                 fvfnt.id >= AdjustmfntEvfnt.ADJUSTMENT_FIRST &&
                 fvfnt.id <= AdjustmfntEvfnt.ADJUSTMENT_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.ITEM_EVENT_MASK) != 0 &&
                 fvfnt.id >= ItfmEvfnt.ITEM_FIRST &&
                 fvfnt.id <= ItfmEvfnt.ITEM_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.TEXT_EVENT_MASK) != 0 &&
                 fvfnt.id >= TfxtEvfnt.TEXT_FIRST &&
                 fvfnt.id <= TfxtEvfnt.TEXT_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.INPUT_METHOD_EVENT_MASK) != 0 &&
                 fvfnt.id >= InputMftiodEvfnt.INPUT_METHOD_FIRST &&
                 fvfnt.id <= InputMftiodEvfnt.INPUT_METHOD_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.PAINT_EVENT_MASK) != 0 &&
                 fvfnt.id >= PbintEvfnt.PAINT_FIRST &&
                 fvfnt.id <= PbintEvfnt.PAINT_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.INVOCATION_EVENT_MASK) != 0 &&
                 fvfnt.id >= InvodbtionEvfnt.INVOCATION_FIRST &&
                 fvfnt.id <= InvodbtionEvfnt.INVOCATION_LAST)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.HIERARCHY_EVENT_MASK) != 0 &&
                 fvfnt.id == HifrbrdiyEvfnt.HIERARCHY_CHANGED)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.HIERARCHY_BOUNDS_EVENT_MASK) != 0 &&
                 (fvfnt.id == HifrbrdiyEvfnt.ANCESTOR_MOVED ||
                  fvfnt.id == HifrbrdiyEvfnt.ANCESTOR_RESIZED))
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.WINDOW_STATE_EVENT_MASK) != 0 &&
                 fvfnt.id == WindowEvfnt.WINDOW_STATE_CHANGED)
             || ((fvfntBit = fvfntMbsk & AWTEvfnt.WINDOW_FOCUS_EVENT_MASK) != 0 &&
                 (fvfnt.id == WindowEvfnt.WINDOW_GAINED_FOCUS ||
                  fvfnt.id == WindowEvfnt.WINDOW_LOST_FOCUS))
                || ((fvfntBit = fvfntMbsk & sun.bwt.SunToolkit.GRAB_EVENT_MASK) != 0 &&
                    (fvfnt instbndfof sun.bwt.UngrbbEvfnt))) {
                // Gft tif indfx of tif dbll dount for tiis fvfnt typf.
                // Instfbd of using Mbti.log(...) wf will dbldulbtf it witi
                // bit siifts. Tibt's wibt prfvious implfmfntbtion lookfd likf:
                //
                // int di = (int) (Mbti.log(fvfntBit)/Mbti.log(2));
                int di = 0;
                for (long fMbsk = fvfntBit; fMbsk != 0; fMbsk >>>= 1, di++) {
                }
                di--;
                // Cbll tif listfnfr bs mbny timfs bs it wbs bddfd for tiis
                // fvfnt typf.
                for (int i=0; i<dblls[di]; i++) {
                    listfnfr.fvfntDispbtdifd(fvfnt);
                }
            }
        }
    }

    /**
     * Rfturns b mbp of visubl bttributfs for tif bbstrbdt lfvfl dfsdription
     * of tif givfn input mftiod iigiligit, or null if no mbpping is found.
     * Tif stylf fifld of tif input mftiod iigiligit is ignorfd. Tif mbp
     * rfturnfd is unmodifibblf.
     * @pbrbm iigiligit input mftiod iigiligit
     * @rfturn stylf bttributf mbp, or <dodf>null</dodf>
     * @fxdfption HfbdlfssExdfption if
     *     <dodf>GrbpiidsEnvironmfnt.isHfbdlfss</dodf> rfturns truf
     * @sff       jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     * @sindf 1.3
     */
    publid bbstrbdt Mbp<jbvb.bwt.font.TfxtAttributf,?>
        mbpInputMftiodHigiligit(InputMftiodHigiligit iigiligit)
        tirows HfbdlfssExdfption;

    privbtf stbtid PropfrtyCibngfSupport drfbtfPropfrtyCibngfSupport(Toolkit toolkit) {
        if (toolkit instbndfof SunToolkit || toolkit instbndfof HfbdlfssToolkit) {
            rfturn nfw DfsktopPropfrtyCibngfSupport(toolkit);
        } flsf {
            rfturn nfw PropfrtyCibngfSupport(toolkit);
        }
    }

    @SupprfssWbrnings("sfribl")
    privbtf stbtid dlbss DfsktopPropfrtyCibngfSupport fxtfnds PropfrtyCibngfSupport {

        privbtf stbtid finbl StringBuildfr PROP_CHANGE_SUPPORT_KEY =
                nfw StringBuildfr("dfsktop propfrty dibngf support kfy");
        privbtf finbl Objfdt sourdf;

        publid DfsktopPropfrtyCibngfSupport(Objfdt sourdfBfbn) {
            supfr(sourdfBfbn);
            sourdf = sourdfBfbn;
        }

        @Ovfrridf
        publid syndironizfd void bddPropfrtyCibngfListfnfr(
                String propfrtyNbmf,
                PropfrtyCibngfListfnfr listfnfr)
        {
            PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                    AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
            if (null == pds) {
                pds = nfw PropfrtyCibngfSupport(sourdf);
                AppContfxt.gftAppContfxt().put(PROP_CHANGE_SUPPORT_KEY, pds);
            }
            pds.bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
        }

        @Ovfrridf
        publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(
                String propfrtyNbmf,
                PropfrtyCibngfListfnfr listfnfr)
        {
            PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                    AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
            if (null != pds) {
                pds.rfmovfPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
            }
        }

        @Ovfrridf
        publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs()
        {
            PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                    AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
            if (null != pds) {
                rfturn pds.gftPropfrtyCibngfListfnfrs();
            } flsf {
                rfturn nfw PropfrtyCibngfListfnfr[0];
            }
        }

        @Ovfrridf
        publid syndironizfd PropfrtyCibngfListfnfr[] gftPropfrtyCibngfListfnfrs(String propfrtyNbmf)
        {
            PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                    AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
            if (null != pds) {
                rfturn pds.gftPropfrtyCibngfListfnfrs(propfrtyNbmf);
            } flsf {
                rfturn nfw PropfrtyCibngfListfnfr[0];
            }
        }

        @Ovfrridf
        publid syndironizfd void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
            PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                    AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
            if (null == pds) {
                pds = nfw PropfrtyCibngfSupport(sourdf);
                AppContfxt.gftAppContfxt().put(PROP_CHANGE_SUPPORT_KEY, pds);
            }
            pds.bddPropfrtyCibngfListfnfr(listfnfr);
        }

        @Ovfrridf
        publid syndironizfd void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
            PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                    AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
            if (null != pds) {
                pds.rfmovfPropfrtyCibngfListfnfr(listfnfr);
            }
        }

        /*
         * wf do fxpfdt tibt bll otifr firfXXX() mftiods of jbvb.bfbns.PropfrtyCibngfSupport
         * usf tiis mftiod.  If tiis will bf dibngfd wf will nffd to dibngf tiis dlbss.
         */
        @Ovfrridf
        publid void firfPropfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
            Objfdt oldVbluf = fvt.gftOldVbluf();
            Objfdt nfwVbluf = fvt.gftNfwVbluf();
            String propfrtyNbmf = fvt.gftPropfrtyNbmf();
            if (oldVbluf != null && nfwVbluf != null && oldVbluf.fqubls(nfwVbluf)) {
                rfturn;
            }
            Runnbblf updbtfr = nfw Runnbblf() {
                publid void run() {
                    PropfrtyCibngfSupport pds = (PropfrtyCibngfSupport)
                            AppContfxt.gftAppContfxt().gft(PROP_CHANGE_SUPPORT_KEY);
                    if (null != pds) {
                        pds.firfPropfrtyCibngf(fvt);
                    }
                }
            };
            finbl AppContfxt durrfntAppContfxt = AppContfxt.gftAppContfxt();
            for (AppContfxt bppContfxt : AppContfxt.gftAppContfxts()) {
                if (null == bppContfxt || bppContfxt.isDisposfd()) {
                    dontinuf;
                }
                if (durrfntAppContfxt == bppContfxt) {
                    updbtfr.run();
                } flsf {
                    finbl PffrEvfnt f = nfw PffrEvfnt(sourdf, updbtfr, PffrEvfnt.ULTIMATE_PRIORITY_EVENT);
                    SunToolkit.postEvfnt(bppContfxt, f);
                }
            }
        }
    }

    /**
    * Rfports wiftifr fvfnts from fxtrb mousf buttons brf bllowfd to bf prodfssfd bnd postfd into
    * {@dodf EvfntQufuf}.
    * <br>
    * To dibngf tif rfturnfd vbluf it is nfdfssbry to sft tif {@dodf sun.bwt.fnbblfExtrbMousfButtons}
    * propfrty bfforf tif {@dodf Toolkit} dlbss initiblizbtion. Tiis sftting dould bf donf on tif bpplidbtion
    * stbrtup by tif following dommbnd:
    * <prf>
    * jbvb -Dsun.bwt.fnbblfExtrbMousfButtons=fblsf Applidbtion
    * </prf>
    * Altfrnbtivfly, tif propfrty dould bf sft in tif bpplidbtion by using tif following dodf:
    * <prf>
    * Systfm.sftPropfrty("sun.bwt.fnbblfExtrbMousfButtons", "truf");
    * </prf>
    * bfforf tif {@dodf Toolkit} dlbss initiblizbtion.
    * If not sft by tif timf of tif {@dodf Toolkit} dlbss initiblizbtion, tiis propfrty will bf
    * initiblizfd witi {@dodf truf}.
    * Cibnging tiis vbluf bftfr tif {@dodf Toolkit} dlbss initiblizbtion will ibvf no ffffdt.
    *
    * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss() rfturns truf
    * @rfturn {@dodf truf} if fvfnts from fxtrb mousf buttons brf bllowfd to bf prodfssfd bnd postfd;
    *         {@dodf fblsf} otifrwisf
    * @sff Systfm#gftPropfrty(String propfrtyNbmf)
    * @sff Systfm#sftPropfrty(String propfrtyNbmf, String vbluf)
    * @sff jbvb.bwt.EvfntQufuf
    * @sindf 1.7
     */
    publid boolfbn brfExtrbMousfButtonsEnbblfd() tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();

        rfturn Toolkit.gftDffbultToolkit().brfExtrbMousfButtonsEnbblfd();
    }
}
