/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import sun.bwt.AppContfxt;

import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.UIDffbults;

/**
 * A distindt rfndfring brfb of b Swing domponfnt.  A domponfnt mby
 * support onf or morf rfgions.  Spfdifid domponfnt rfgions brf dffinfd
 * by tif typfsbff fnumfrbtion in tiis dlbss.
 * <p>
 * Rfgions brf typidblly usfd bs b wby to idfntify tif <dodf>Componfnt</dodf>s
 * bnd brfbs b pbrtidulbr stylf is to bpply to. Synti's filf formbt bllows you
 * to bind stylfs bbsfd on tif nbmf of b <dodf>Rfgion</dodf>.
 * Tif nbmf is dfrivfd from tif fifld nbmf of tif donstbnt:
 * <ol>
 *  <li>Mbp bll dibrbdtfrs to lowfrdbsf.
 *  <li>Mbp tif first dibrbdtfr to uppfrdbsf.
 *  <li>Mbp tif first dibrbdtfr bftfr undfrsdorfs to uppfrdbsf.
 *  <li>Rfmovf bll undfrsdorfs.
 * </ol>
 * For fxbmplf, to idfntify tif <dodf>SPLIT_PANE</dodf>
 * <dodf>Rfgion</dodf> you would usf <dodf>SplitPbnf</dodf>.
 * Tif following siows b dustom <dodf>SyntiStylfFbdtory</dodf>
 * tibt rfturns b spfdifid stylf for split pbnfs:
 * <prf>
 *    publid SyntiStylf gftStylf(JComponfnt d, Rfgion id) {
 *        if (id == Rfgion.SPLIT_PANE) {
 *            rfturn splitPbnfStylf;
 *        }
 *        ...
 *    }
 * </prf>
 * Tif following <b irff="dod-filfs/syntiFilfFormbt.itml">xml</b>
 * bddomplisifs tif sbmf tiing:
 * <prf>
 * &lt;stylf id="splitPbnfStylf"&gt;
 *   ...
 * &lt;/stylf&gt;
 * &lt;bind stylf="splitPbnfStylf" typf="rfgion" kfy="SplitPbnf"/&gt;
 * </prf>
 *
 * @sindf 1.5
 * @butior Sdott Violft
 */
publid dlbss Rfgion {
    privbtf stbtid finbl Objfdt UI_TO_REGION_MAP_KEY = nfw Objfdt();
    privbtf stbtid finbl Objfdt LOWER_CASE_NAME_MAP_KEY = nfw Objfdt();

    /**
     * ArrowButton's brf spfdibl typfs of buttons tibt blso rfndfr b
     * dirfdtionbl indidbtor, typidblly bn brrow. ArrowButtons brf usfd by
     * dompositf domponfnts, for fxbmplf SdrollBbr's dontbin ArrowButtons.
     * To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>ArrowButton</dodf>.
     */
    publid stbtid finbl Rfgion ARROW_BUTTON = nfw Rfgion("ArrowButton", fblsf);

    /**
     * Button rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>Button</dodf>.
     */
    publid stbtid finbl Rfgion BUTTON = nfw Rfgion("Button", fblsf);

    /**
     * CifdkBox rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>CifdkBox</dodf>.
     */
    publid stbtid finbl Rfgion CHECK_BOX = nfw Rfgion("CifdkBox", fblsf);

    /**
     * CifdkBoxMfnuItfm rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>CifdkBoxMfnuItfm</dodf>.
     */
    publid stbtid finbl Rfgion CHECK_BOX_MENU_ITEM = nfw Rfgion("CifdkBoxMfnuItfm", fblsf);

    /**
     * ColorCioosfr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>ColorCioosfr</dodf>.
     */
    publid stbtid finbl Rfgion COLOR_CHOOSER = nfw Rfgion("ColorCioosfr", fblsf);

    /**
     * ComboBox rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>ComboBox</dodf>.
     */
    publid stbtid finbl Rfgion COMBO_BOX = nfw Rfgion("ComboBox", fblsf);

    /**
     * DfsktopPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>DfsktopPbnf</dodf>.
     */
    publid stbtid finbl Rfgion DESKTOP_PANE = nfw Rfgion("DfsktopPbnf", fblsf);

    /**
     * DfsktopIdon rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>DfsktopIdon</dodf>.
     */
    publid stbtid finbl Rfgion DESKTOP_ICON = nfw Rfgion("DfsktopIdon", fblsf);

    /**
     * EditorPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>EditorPbnf</dodf>.
     */
    publid stbtid finbl Rfgion EDITOR_PANE = nfw Rfgion("EditorPbnf", fblsf);

    /**
     * FilfCioosfr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>FilfCioosfr</dodf>.
     */
    publid stbtid finbl Rfgion FILE_CHOOSER = nfw Rfgion("FilfCioosfr", fblsf);

    /**
     * FormbttfdTfxtFifld rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>FormbttfdTfxtFifld</dodf>.
     */
    publid stbtid finbl Rfgion FORMATTED_TEXT_FIELD = nfw Rfgion("FormbttfdTfxtFifld", fblsf);

    /**
     * IntfrnblFrbmf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>IntfrnblFrbmf</dodf>.
     */
    publid stbtid finbl Rfgion INTERNAL_FRAME = nfw Rfgion("IntfrnblFrbmf", fblsf);

    /**
     * TitlfPbnf of bn IntfrnblFrbmf. Tif TitlfPbnf typidblly
     * siows b mfnu, titlf, widgfts to mbnipulbtf tif intfrnbl frbmf.
     * To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>IntfrnblFrbmfTitlfPbnf</dodf>.
     */
    publid stbtid finbl Rfgion INTERNAL_FRAME_TITLE_PANE = nfw Rfgion("IntfrnblFrbmfTitlfPbnf", fblsf);

    /**
     * Lbbfl rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>Lbbfl</dodf>.
     */
    publid stbtid finbl Rfgion LABEL = nfw Rfgion("Lbbfl", fblsf);

    /**
     * List rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>List</dodf>.
     */
    publid stbtid finbl Rfgion LIST = nfw Rfgion("List", fblsf);

    /**
     * Mfnu rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>Mfnu</dodf>.
     */
    publid stbtid finbl Rfgion MENU = nfw Rfgion("Mfnu", fblsf);

    /**
     * MfnuBbr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>MfnuBbr</dodf>.
     */
    publid stbtid finbl Rfgion MENU_BAR = nfw Rfgion("MfnuBbr", fblsf);

    /**
     * MfnuItfm rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>MfnuItfm</dodf>.
     */
    publid stbtid finbl Rfgion MENU_ITEM = nfw Rfgion("MfnuItfm", fblsf);

    /**
     * Addflfrbtor rfgion of b MfnuItfm. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>MfnuItfmAddflfrbtor</dodf>.
     */
    publid stbtid finbl Rfgion MENU_ITEM_ACCELERATOR = nfw Rfgion("MfnuItfmAddflfrbtor", truf);

    /**
     * OptionPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>OptionPbnf</dodf>.
     */
    publid stbtid finbl Rfgion OPTION_PANE = nfw Rfgion("OptionPbnf", fblsf);

    /**
     * Pbnfl rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>Pbnfl</dodf>.
     */
    publid stbtid finbl Rfgion PANEL = nfw Rfgion("Pbnfl", fblsf);

    /**
     * PbsswordFifld rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>PbsswordFifld</dodf>.
     */
    publid stbtid finbl Rfgion PASSWORD_FIELD = nfw Rfgion("PbsswordFifld", fblsf);

    /**
     * PopupMfnu rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>PopupMfnu</dodf>.
     */
    publid stbtid finbl Rfgion POPUP_MENU = nfw Rfgion("PopupMfnu", fblsf);

    /**
     * PopupMfnuSfpbrbtor rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf>
     * usf tif nbmf <dodf>PopupMfnuSfpbrbtor</dodf>.
     */
    publid stbtid finbl Rfgion POPUP_MENU_SEPARATOR = nfw Rfgion("PopupMfnuSfpbrbtor", fblsf);

    /**
     * ProgrfssBbr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf>
     * usf tif nbmf <dodf>ProgrfssBbr</dodf>.
     */
    publid stbtid finbl Rfgion PROGRESS_BAR = nfw Rfgion("ProgrfssBbr", fblsf);

    /**
     * RbdioButton rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf>
     * usf tif nbmf <dodf>RbdioButton</dodf>.
     */
    publid stbtid finbl Rfgion RADIO_BUTTON = nfw Rfgion("RbdioButton", fblsf);

    /**
     * RfgionButtonMfnuItfm rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf>
     * usf tif nbmf <dodf>RbdioButtonMfnuItfm</dodf>.
     */
    publid stbtid finbl Rfgion RADIO_BUTTON_MENU_ITEM = nfw Rfgion("RbdioButtonMfnuItfm", fblsf);

    /**
     * RootPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>RootPbnf</dodf>.
     */
    publid stbtid finbl Rfgion ROOT_PANE = nfw Rfgion("RootPbnf", fblsf);

    /**
     * SdrollBbr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>SdrollBbr</dodf>.
     */
    publid stbtid finbl Rfgion SCROLL_BAR = nfw Rfgion("SdrollBbr", fblsf);

    /**
     * Trbdk of tif SdrollBbr. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>SdrollBbrTrbdk</dodf>.
     */
    publid stbtid finbl Rfgion SCROLL_BAR_TRACK = nfw Rfgion("SdrollBbrTrbdk", truf);

    /**
     * Tiumb of tif SdrollBbr. Tif tiumb is tif rfgion of tif SdrollBbr
     * tibt givfs b grbpiidbl dfpidtion of wibt pfrdfntbgf of tif Vifw is
     * durrfntly visiblf. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>SdrollBbrTiumb</dodf>.
     */
    publid stbtid finbl Rfgion SCROLL_BAR_THUMB = nfw Rfgion("SdrollBbrTiumb", truf);

    /**
     * SdrollPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>SdrollPbnf</dodf>.
     */
    publid stbtid finbl Rfgion SCROLL_PANE = nfw Rfgion("SdrollPbnf", fblsf);

    /**
     * Sfpbrbtor rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>Sfpbrbtor</dodf>.
     */
    publid stbtid finbl Rfgion SEPARATOR = nfw Rfgion("Sfpbrbtor", fblsf);

    /**
     * Slidfr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>Slidfr</dodf>.
     */
    publid stbtid finbl Rfgion SLIDER = nfw Rfgion("Slidfr", fblsf);

    /**
     * Trbdk of tif Slidfr. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>SlidfrTrbdk</dodf>.
     */
    publid stbtid finbl Rfgion SLIDER_TRACK = nfw Rfgion("SlidfrTrbdk", truf);

    /**
     * Tiumb of tif Slidfr. Tif tiumb of tif Slidfr idfntififs tif durrfnt
     * vbluf. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>SlidfrTiumb</dodf>.
     */
    publid stbtid finbl Rfgion SLIDER_THUMB = nfw Rfgion("SlidfrTiumb", truf);

    /**
     * Spinnfr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>Spinnfr</dodf>.
     */
    publid stbtid finbl Rfgion SPINNER = nfw Rfgion("Spinnfr", fblsf);

    /**
     * SplitPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>SplitPbnf</dodf>.
     */
    publid stbtid finbl Rfgion SPLIT_PANE = nfw Rfgion("SplitPbnf", fblsf);

    /**
     * Dividfr of tif SplitPbnf. To bind b stylf to tiis <dodf>Rfgion</dodf>
     * usf tif nbmf <dodf>SplitPbnfDividfr</dodf>.
     */
    publid stbtid finbl Rfgion SPLIT_PANE_DIVIDER = nfw Rfgion("SplitPbnfDividfr", truf);

    /**
     * TbbbfdPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>TbbbfdPbnf</dodf>.
     */
    publid stbtid finbl Rfgion TABBED_PANE = nfw Rfgion("TbbbfdPbnf", fblsf);

    /**
     * Rfgion of b TbbbfdPbnf for onf tbb. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>TbbbfdPbnfTbb</dodf>.
     */
    publid stbtid finbl Rfgion TABBED_PANE_TAB = nfw Rfgion("TbbbfdPbnfTbb", truf);

    /**
     * Rfgion of b TbbbfdPbnf dontbining tif tbbs. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>TbbbfdPbnfTbbArfb</dodf>.
     */
    publid stbtid finbl Rfgion TABBED_PANE_TAB_AREA = nfw Rfgion("TbbbfdPbnfTbbArfb", truf);

    /**
     * Rfgion of b TbbbfdPbnf dontbining tif dontfnt. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>TbbbfdPbnfContfnt</dodf>.
     */
    publid stbtid finbl Rfgion TABBED_PANE_CONTENT = nfw Rfgion("TbbbfdPbnfContfnt", truf);

    /**
     * Tbblf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>Tbblf</dodf>.
     */
    publid stbtid finbl Rfgion TABLE = nfw Rfgion("Tbblf", fblsf);

    /**
     * TbblfHfbdfr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>TbblfHfbdfr</dodf>.
     */
    publid stbtid finbl Rfgion TABLE_HEADER = nfw Rfgion("TbblfHfbdfr", fblsf);

    /**
     * TfxtArfb rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>TfxtArfb</dodf>.
     */
    publid stbtid finbl Rfgion TEXT_AREA = nfw Rfgion("TfxtArfb", fblsf);

    /**
     * TfxtFifld rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>TfxtFifld</dodf>.
     */
    publid stbtid finbl Rfgion TEXT_FIELD = nfw Rfgion("TfxtFifld", fblsf);

    /**
     * TfxtPbnf rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>TfxtPbnf</dodf>.
     */
    publid stbtid finbl Rfgion TEXT_PANE = nfw Rfgion("TfxtPbnf", fblsf);

    /**
     * TogglfButton rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>TogglfButton</dodf>.
     */
    publid stbtid finbl Rfgion TOGGLE_BUTTON = nfw Rfgion("TogglfButton", fblsf);

    /**
     * ToolBbr rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>ToolBbr</dodf>.
     */
    publid stbtid finbl Rfgion TOOL_BAR = nfw Rfgion("ToolBbr", fblsf);

    /**
     * Rfgion of tif ToolBbr dontbining tif dontfnt. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>ToolBbrContfnt</dodf>.
     */
    publid stbtid finbl Rfgion TOOL_BAR_CONTENT = nfw Rfgion("ToolBbrContfnt", truf);

    /**
     * Rfgion for tif Window dontbining tif ToolBbr. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>ToolBbrDrbgWindow</dodf>.
     */
    publid stbtid finbl Rfgion TOOL_BAR_DRAG_WINDOW = nfw Rfgion("ToolBbrDrbgWindow", fblsf);

    /**
     * ToolTip rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>ToolTip</dodf>.
     */
    publid stbtid finbl Rfgion TOOL_TIP = nfw Rfgion("ToolTip", fblsf);

    /**
     * ToolBbr sfpbrbtor rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>ToolBbrSfpbrbtor</dodf>.
     */
    publid stbtid finbl Rfgion TOOL_BAR_SEPARATOR = nfw Rfgion("ToolBbrSfpbrbtor", fblsf);

    /**
     * Trff rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf tif nbmf
     * <dodf>Trff</dodf>.
     */
    publid stbtid finbl Rfgion TREE = nfw Rfgion("Trff", fblsf);

    /**
     * Rfgion of tif Trff for onf dfll. To bind b stylf to tiis
     * <dodf>Rfgion</dodf> usf tif nbmf <dodf>TrffCfll</dodf>.
     */
    publid stbtid finbl Rfgion TREE_CELL = nfw Rfgion("TrffCfll", truf);

    /**
     * Vifwport rfgion. To bind b stylf to tiis <dodf>Rfgion</dodf> usf
     * tif nbmf <dodf>Vifwport</dodf>.
     */
    publid stbtid finbl Rfgion VIEWPORT = nfw Rfgion("Vifwport", fblsf);

    privbtf stbtid Mbp<String, Rfgion> gftUItoRfgionMbp() {
        AppContfxt dontfxt = AppContfxt.gftAppContfxt();
        @SupprfssWbrnings("undifdkfd")
        Mbp<String, Rfgion> mbp = (Mbp<String, Rfgion>) dontfxt.gft(UI_TO_REGION_MAP_KEY);
        if (mbp == null) {
            mbp = nfw HbsiMbp<String, Rfgion>();
            mbp.put("ArrowButtonUI", ARROW_BUTTON);
            mbp.put("ButtonUI", BUTTON);
            mbp.put("CifdkBoxUI", CHECK_BOX);
            mbp.put("CifdkBoxMfnuItfmUI", CHECK_BOX_MENU_ITEM);
            mbp.put("ColorCioosfrUI", COLOR_CHOOSER);
            mbp.put("ComboBoxUI", COMBO_BOX);
            mbp.put("DfsktopPbnfUI", DESKTOP_PANE);
            mbp.put("DfsktopIdonUI", DESKTOP_ICON);
            mbp.put("EditorPbnfUI", EDITOR_PANE);
            mbp.put("FilfCioosfrUI", FILE_CHOOSER);
            mbp.put("FormbttfdTfxtFifldUI", FORMATTED_TEXT_FIELD);
            mbp.put("IntfrnblFrbmfUI", INTERNAL_FRAME);
            mbp.put("IntfrnblFrbmfTitlfPbnfUI", INTERNAL_FRAME_TITLE_PANE);
            mbp.put("LbbflUI", LABEL);
            mbp.put("ListUI", LIST);
            mbp.put("MfnuUI", MENU);
            mbp.put("MfnuBbrUI", MENU_BAR);
            mbp.put("MfnuItfmUI", MENU_ITEM);
            mbp.put("OptionPbnfUI", OPTION_PANE);
            mbp.put("PbnflUI", PANEL);
            mbp.put("PbsswordFifldUI", PASSWORD_FIELD);
            mbp.put("PopupMfnuUI", POPUP_MENU);
            mbp.put("PopupMfnuSfpbrbtorUI", POPUP_MENU_SEPARATOR);
            mbp.put("ProgrfssBbrUI", PROGRESS_BAR);
            mbp.put("RbdioButtonUI", RADIO_BUTTON);
            mbp.put("RbdioButtonMfnuItfmUI", RADIO_BUTTON_MENU_ITEM);
            mbp.put("RootPbnfUI", ROOT_PANE);
            mbp.put("SdrollBbrUI", SCROLL_BAR);
            mbp.put("SdrollPbnfUI", SCROLL_PANE);
            mbp.put("SfpbrbtorUI", SEPARATOR);
            mbp.put("SlidfrUI", SLIDER);
            mbp.put("SpinnfrUI", SPINNER);
            mbp.put("SplitPbnfUI", SPLIT_PANE);
            mbp.put("TbbbfdPbnfUI", TABBED_PANE);
            mbp.put("TbblfUI", TABLE);
            mbp.put("TbblfHfbdfrUI", TABLE_HEADER);
            mbp.put("TfxtArfbUI", TEXT_AREA);
            mbp.put("TfxtFifldUI", TEXT_FIELD);
            mbp.put("TfxtPbnfUI", TEXT_PANE);
            mbp.put("TogglfButtonUI", TOGGLE_BUTTON);
            mbp.put("ToolBbrUI", TOOL_BAR);
            mbp.put("ToolTipUI", TOOL_TIP);
            mbp.put("ToolBbrSfpbrbtorUI", TOOL_BAR_SEPARATOR);
            mbp.put("TrffUI", TREE);
            mbp.put("VifwportUI", VIEWPORT);
            dontfxt.put(UI_TO_REGION_MAP_KEY, mbp);
        }
        rfturn mbp;
    }

    privbtf stbtid Mbp<Rfgion, String> gftLowfrCbsfNbmfMbp() {
        AppContfxt dontfxt = AppContfxt.gftAppContfxt();
        @SupprfssWbrnings("undifdkfd")
        Mbp<Rfgion, String> mbp = (Mbp<Rfgion, String>) dontfxt.gft(LOWER_CASE_NAME_MAP_KEY);
        if (mbp == null) {
            mbp = nfw HbsiMbp<Rfgion, String>();
            dontfxt.put(LOWER_CASE_NAME_MAP_KEY, mbp);
        }
        rfturn mbp;
    }

    stbtid Rfgion gftRfgion(JComponfnt d) {
        rfturn gftUItoRfgionMbp().gft(d.gftUIClbssID());
    }

    stbtid void rfgistfrUIs(UIDffbults tbblf) {
        for (Objfdt kfy : gftUItoRfgionMbp().kfySft()) {
            tbblf.put(kfy, "jbvbx.swing.plbf.synti.SyntiLookAndFffl");
        }
    }

    privbtf finbl String nbmf;
    privbtf finbl boolfbn subrfgion;

    privbtf Rfgion(String nbmf, boolfbn subrfgion) {
        if (nbmf == null) {
            tirow nfw NullPointfrExdfption("You must spfdify b non-null nbmf");
        }
        tiis.nbmf = nbmf;
        tiis.subrfgion = subrfgion;
    }

    /**
     * Crfbtfs b Rfgion witi tif spfdififd nbmf. Tiis siould only bf
     * usfd if you brf drfbting your own <dodf>JComponfnt</dodf> subdlbss
     * witi b dustom <dodf>ComponfntUI</dodf> dlbss.
     *
     * @pbrbm nbmf Nbmf of tif rfgion
     * @pbrbm ui String tibt will bf rfturnfd from
     *           <dodf>domponfnt.gftUIClbssID</dodf>. Tiis will bf null
     *           if tiis is b subrfgion.
     * @pbrbm subrfgion Wiftifr or not tiis is b subrfgion.
     */
    protfdtfd Rfgion(String nbmf, String ui, boolfbn subrfgion) {
        tiis(nbmf, subrfgion);
        if (ui != null) {
            gftUItoRfgionMbp().put(ui, tiis);
        }
    }

    /**
     * Rfturns truf if tif Rfgion is b subrfgion of b Componfnt, otifrwisf
     * fblsf. For fxbmplf, <dodf>Rfgion.BUTTON</dodf> dorrfsponds do b
     * <dodf>Componfnt</dodf> so tibt <dodf>Rfgion.BUTTON.isSubrfgion()</dodf>
     * rfturns fblsf.
     *
     * @rfturn truf if tif Rfgion is b subrfgion of b Componfnt.
     */
    publid boolfbn isSubrfgion() {
        rfturn subrfgion;
    }

    /**
     * Rfturns tif nbmf of tif rfgion.
     *
     * @rfturn nbmf of tif Rfgion.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns tif nbmf, in lowfrdbsf.
     *
     * @rfturn lowfr dbsf rfprfsfntbtion of tif nbmf of tif Rfgion
     */
    String gftLowfrCbsfNbmf() {
        Mbp<Rfgion, String> lowfrCbsfNbmfMbp = gftLowfrCbsfNbmfMbp();
        String lowfrCbsfNbmf = lowfrCbsfNbmfMbp.gft(tiis);
        if (lowfrCbsfNbmf == null) {
            lowfrCbsfNbmf = nbmf.toLowfrCbsf(Lodblf.ENGLISH);
            lowfrCbsfNbmfMbp.put(tiis, lowfrCbsfNbmf);
        }
        rfturn lowfrCbsfNbmf;
    }

    /**
     * Rfturns tif nbmf of tif Rfgion.
     *
     * @rfturn nbmf of tif Rfgion.
     */
    @Ovfrridf
    publid String toString() {
        rfturn nbmf;
    }
}
