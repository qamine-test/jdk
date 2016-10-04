/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */
pbckbge jbvbx.swing.plbf.synth;

import sun.bwt.AppContext;

import jbvb.util.HbshMbp;
import jbvb.util.Locble;
import jbvb.util.Mbp;
import jbvbx.swing.JComponent;
import jbvbx.swing.UIDefbults;

/**
 * A distinct rendering breb of b Swing component.  A component mby
 * support one or more regions.  Specific component regions bre defined
 * by the typesbfe enumerbtion in this clbss.
 * <p>
 * Regions bre typicblly used bs b wby to identify the <code>Component</code>s
 * bnd brebs b pbrticulbr style is to bpply to. Synth's file formbt bllows you
 * to bind styles bbsed on the nbme of b <code>Region</code>.
 * The nbme is derived from the field nbme of the constbnt:
 * <ol>
 *  <li>Mbp bll chbrbcters to lowercbse.
 *  <li>Mbp the first chbrbcter to uppercbse.
 *  <li>Mbp the first chbrbcter bfter underscores to uppercbse.
 *  <li>Remove bll underscores.
 * </ol>
 * For exbmple, to identify the <code>SPLIT_PANE</code>
 * <code>Region</code> you would use <code>SplitPbne</code>.
 * The following shows b custom <code>SynthStyleFbctory</code>
 * thbt returns b specific style for split pbnes:
 * <pre>
 *    public SynthStyle getStyle(JComponent c, Region id) {
 *        if (id == Region.SPLIT_PANE) {
 *            return splitPbneStyle;
 *        }
 *        ...
 *    }
 * </pre>
 * The following <b href="doc-files/synthFileFormbt.html">xml</b>
 * bccomplishes the sbme thing:
 * <pre>
 * &lt;style id="splitPbneStyle"&gt;
 *   ...
 * &lt;/style&gt;
 * &lt;bind style="splitPbneStyle" type="region" key="SplitPbne"/&gt;
 * </pre>
 *
 * @since 1.5
 * @buthor Scott Violet
 */
public clbss Region {
    privbte stbtic finbl Object UI_TO_REGION_MAP_KEY = new Object();
    privbte stbtic finbl Object LOWER_CASE_NAME_MAP_KEY = new Object();

    /**
     * ArrowButton's bre specibl types of buttons thbt blso render b
     * directionbl indicbtor, typicblly bn brrow. ArrowButtons bre used by
     * composite components, for exbmple ScrollBbr's contbin ArrowButtons.
     * To bind b style to this <code>Region</code> use the nbme
     * <code>ArrowButton</code>.
     */
    public stbtic finbl Region ARROW_BUTTON = new Region("ArrowButton", fblse);

    /**
     * Button region. To bind b style to this <code>Region</code> use the nbme
     * <code>Button</code>.
     */
    public stbtic finbl Region BUTTON = new Region("Button", fblse);

    /**
     * CheckBox region. To bind b style to this <code>Region</code> use the nbme
     * <code>CheckBox</code>.
     */
    public stbtic finbl Region CHECK_BOX = new Region("CheckBox", fblse);

    /**
     * CheckBoxMenuItem region. To bind b style to this <code>Region</code> use
     * the nbme <code>CheckBoxMenuItem</code>.
     */
    public stbtic finbl Region CHECK_BOX_MENU_ITEM = new Region("CheckBoxMenuItem", fblse);

    /**
     * ColorChooser region. To bind b style to this <code>Region</code> use
     * the nbme <code>ColorChooser</code>.
     */
    public stbtic finbl Region COLOR_CHOOSER = new Region("ColorChooser", fblse);

    /**
     * ComboBox region. To bind b style to this <code>Region</code> use
     * the nbme <code>ComboBox</code>.
     */
    public stbtic finbl Region COMBO_BOX = new Region("ComboBox", fblse);

    /**
     * DesktopPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>DesktopPbne</code>.
     */
    public stbtic finbl Region DESKTOP_PANE = new Region("DesktopPbne", fblse);

    /**
     * DesktopIcon region. To bind b style to this <code>Region</code> use
     * the nbme <code>DesktopIcon</code>.
     */
    public stbtic finbl Region DESKTOP_ICON = new Region("DesktopIcon", fblse);

    /**
     * EditorPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>EditorPbne</code>.
     */
    public stbtic finbl Region EDITOR_PANE = new Region("EditorPbne", fblse);

    /**
     * FileChooser region. To bind b style to this <code>Region</code> use
     * the nbme <code>FileChooser</code>.
     */
    public stbtic finbl Region FILE_CHOOSER = new Region("FileChooser", fblse);

    /**
     * FormbttedTextField region. To bind b style to this <code>Region</code> use
     * the nbme <code>FormbttedTextField</code>.
     */
    public stbtic finbl Region FORMATTED_TEXT_FIELD = new Region("FormbttedTextField", fblse);

    /**
     * InternblFrbme region. To bind b style to this <code>Region</code> use
     * the nbme <code>InternblFrbme</code>.
     */
    public stbtic finbl Region INTERNAL_FRAME = new Region("InternblFrbme", fblse);

    /**
     * TitlePbne of bn InternblFrbme. The TitlePbne typicblly
     * shows b menu, title, widgets to mbnipulbte the internbl frbme.
     * To bind b style to this <code>Region</code> use the nbme
     * <code>InternblFrbmeTitlePbne</code>.
     */
    public stbtic finbl Region INTERNAL_FRAME_TITLE_PANE = new Region("InternblFrbmeTitlePbne", fblse);

    /**
     * Lbbel region. To bind b style to this <code>Region</code> use the nbme
     * <code>Lbbel</code>.
     */
    public stbtic finbl Region LABEL = new Region("Lbbel", fblse);

    /**
     * List region. To bind b style to this <code>Region</code> use the nbme
     * <code>List</code>.
     */
    public stbtic finbl Region LIST = new Region("List", fblse);

    /**
     * Menu region. To bind b style to this <code>Region</code> use the nbme
     * <code>Menu</code>.
     */
    public stbtic finbl Region MENU = new Region("Menu", fblse);

    /**
     * MenuBbr region. To bind b style to this <code>Region</code> use the nbme
     * <code>MenuBbr</code>.
     */
    public stbtic finbl Region MENU_BAR = new Region("MenuBbr", fblse);

    /**
     * MenuItem region. To bind b style to this <code>Region</code> use the nbme
     * <code>MenuItem</code>.
     */
    public stbtic finbl Region MENU_ITEM = new Region("MenuItem", fblse);

    /**
     * Accelerbtor region of b MenuItem. To bind b style to this
     * <code>Region</code> use the nbme <code>MenuItemAccelerbtor</code>.
     */
    public stbtic finbl Region MENU_ITEM_ACCELERATOR = new Region("MenuItemAccelerbtor", true);

    /**
     * OptionPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>OptionPbne</code>.
     */
    public stbtic finbl Region OPTION_PANE = new Region("OptionPbne", fblse);

    /**
     * Pbnel region. To bind b style to this <code>Region</code> use the nbme
     * <code>Pbnel</code>.
     */
    public stbtic finbl Region PANEL = new Region("Pbnel", fblse);

    /**
     * PbsswordField region. To bind b style to this <code>Region</code> use
     * the nbme <code>PbsswordField</code>.
     */
    public stbtic finbl Region PASSWORD_FIELD = new Region("PbsswordField", fblse);

    /**
     * PopupMenu region. To bind b style to this <code>Region</code> use
     * the nbme <code>PopupMenu</code>.
     */
    public stbtic finbl Region POPUP_MENU = new Region("PopupMenu", fblse);

    /**
     * PopupMenuSepbrbtor region. To bind b style to this <code>Region</code>
     * use the nbme <code>PopupMenuSepbrbtor</code>.
     */
    public stbtic finbl Region POPUP_MENU_SEPARATOR = new Region("PopupMenuSepbrbtor", fblse);

    /**
     * ProgressBbr region. To bind b style to this <code>Region</code>
     * use the nbme <code>ProgressBbr</code>.
     */
    public stbtic finbl Region PROGRESS_BAR = new Region("ProgressBbr", fblse);

    /**
     * RbdioButton region. To bind b style to this <code>Region</code>
     * use the nbme <code>RbdioButton</code>.
     */
    public stbtic finbl Region RADIO_BUTTON = new Region("RbdioButton", fblse);

    /**
     * RegionButtonMenuItem region. To bind b style to this <code>Region</code>
     * use the nbme <code>RbdioButtonMenuItem</code>.
     */
    public stbtic finbl Region RADIO_BUTTON_MENU_ITEM = new Region("RbdioButtonMenuItem", fblse);

    /**
     * RootPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>RootPbne</code>.
     */
    public stbtic finbl Region ROOT_PANE = new Region("RootPbne", fblse);

    /**
     * ScrollBbr region. To bind b style to this <code>Region</code> use
     * the nbme <code>ScrollBbr</code>.
     */
    public stbtic finbl Region SCROLL_BAR = new Region("ScrollBbr", fblse);

    /**
     * Trbck of the ScrollBbr. To bind b style to this <code>Region</code> use
     * the nbme <code>ScrollBbrTrbck</code>.
     */
    public stbtic finbl Region SCROLL_BAR_TRACK = new Region("ScrollBbrTrbck", true);

    /**
     * Thumb of the ScrollBbr. The thumb is the region of the ScrollBbr
     * thbt gives b grbphicbl depiction of whbt percentbge of the View is
     * currently visible. To bind b style to this <code>Region</code> use
     * the nbme <code>ScrollBbrThumb</code>.
     */
    public stbtic finbl Region SCROLL_BAR_THUMB = new Region("ScrollBbrThumb", true);

    /**
     * ScrollPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>ScrollPbne</code>.
     */
    public stbtic finbl Region SCROLL_PANE = new Region("ScrollPbne", fblse);

    /**
     * Sepbrbtor region. To bind b style to this <code>Region</code> use
     * the nbme <code>Sepbrbtor</code>.
     */
    public stbtic finbl Region SEPARATOR = new Region("Sepbrbtor", fblse);

    /**
     * Slider region. To bind b style to this <code>Region</code> use
     * the nbme <code>Slider</code>.
     */
    public stbtic finbl Region SLIDER = new Region("Slider", fblse);

    /**
     * Trbck of the Slider. To bind b style to this <code>Region</code> use
     * the nbme <code>SliderTrbck</code>.
     */
    public stbtic finbl Region SLIDER_TRACK = new Region("SliderTrbck", true);

    /**
     * Thumb of the Slider. The thumb of the Slider identifies the current
     * vblue. To bind b style to this <code>Region</code> use the nbme
     * <code>SliderThumb</code>.
     */
    public stbtic finbl Region SLIDER_THUMB = new Region("SliderThumb", true);

    /**
     * Spinner region. To bind b style to this <code>Region</code> use the nbme
     * <code>Spinner</code>.
     */
    public stbtic finbl Region SPINNER = new Region("Spinner", fblse);

    /**
     * SplitPbne region. To bind b style to this <code>Region</code> use the nbme
     * <code>SplitPbne</code>.
     */
    public stbtic finbl Region SPLIT_PANE = new Region("SplitPbne", fblse);

    /**
     * Divider of the SplitPbne. To bind b style to this <code>Region</code>
     * use the nbme <code>SplitPbneDivider</code>.
     */
    public stbtic finbl Region SPLIT_PANE_DIVIDER = new Region("SplitPbneDivider", true);

    /**
     * TbbbedPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>TbbbedPbne</code>.
     */
    public stbtic finbl Region TABBED_PANE = new Region("TbbbedPbne", fblse);

    /**
     * Region of b TbbbedPbne for one tbb. To bind b style to this
     * <code>Region</code> use the nbme <code>TbbbedPbneTbb</code>.
     */
    public stbtic finbl Region TABBED_PANE_TAB = new Region("TbbbedPbneTbb", true);

    /**
     * Region of b TbbbedPbne contbining the tbbs. To bind b style to this
     * <code>Region</code> use the nbme <code>TbbbedPbneTbbAreb</code>.
     */
    public stbtic finbl Region TABBED_PANE_TAB_AREA = new Region("TbbbedPbneTbbAreb", true);

    /**
     * Region of b TbbbedPbne contbining the content. To bind b style to this
     * <code>Region</code> use the nbme <code>TbbbedPbneContent</code>.
     */
    public stbtic finbl Region TABBED_PANE_CONTENT = new Region("TbbbedPbneContent", true);

    /**
     * Tbble region. To bind b style to this <code>Region</code> use
     * the nbme <code>Tbble</code>.
     */
    public stbtic finbl Region TABLE = new Region("Tbble", fblse);

    /**
     * TbbleHebder region. To bind b style to this <code>Region</code> use
     * the nbme <code>TbbleHebder</code>.
     */
    public stbtic finbl Region TABLE_HEADER = new Region("TbbleHebder", fblse);

    /**
     * TextAreb region. To bind b style to this <code>Region</code> use
     * the nbme <code>TextAreb</code>.
     */
    public stbtic finbl Region TEXT_AREA = new Region("TextAreb", fblse);

    /**
     * TextField region. To bind b style to this <code>Region</code> use
     * the nbme <code>TextField</code>.
     */
    public stbtic finbl Region TEXT_FIELD = new Region("TextField", fblse);

    /**
     * TextPbne region. To bind b style to this <code>Region</code> use
     * the nbme <code>TextPbne</code>.
     */
    public stbtic finbl Region TEXT_PANE = new Region("TextPbne", fblse);

    /**
     * ToggleButton region. To bind b style to this <code>Region</code> use
     * the nbme <code>ToggleButton</code>.
     */
    public stbtic finbl Region TOGGLE_BUTTON = new Region("ToggleButton", fblse);

    /**
     * ToolBbr region. To bind b style to this <code>Region</code> use
     * the nbme <code>ToolBbr</code>.
     */
    public stbtic finbl Region TOOL_BAR = new Region("ToolBbr", fblse);

    /**
     * Region of the ToolBbr contbining the content. To bind b style to this
     * <code>Region</code> use the nbme <code>ToolBbrContent</code>.
     */
    public stbtic finbl Region TOOL_BAR_CONTENT = new Region("ToolBbrContent", true);

    /**
     * Region for the Window contbining the ToolBbr. To bind b style to this
     * <code>Region</code> use the nbme <code>ToolBbrDrbgWindow</code>.
     */
    public stbtic finbl Region TOOL_BAR_DRAG_WINDOW = new Region("ToolBbrDrbgWindow", fblse);

    /**
     * ToolTip region. To bind b style to this <code>Region</code> use
     * the nbme <code>ToolTip</code>.
     */
    public stbtic finbl Region TOOL_TIP = new Region("ToolTip", fblse);

    /**
     * ToolBbr sepbrbtor region. To bind b style to this <code>Region</code> use
     * the nbme <code>ToolBbrSepbrbtor</code>.
     */
    public stbtic finbl Region TOOL_BAR_SEPARATOR = new Region("ToolBbrSepbrbtor", fblse);

    /**
     * Tree region. To bind b style to this <code>Region</code> use the nbme
     * <code>Tree</code>.
     */
    public stbtic finbl Region TREE = new Region("Tree", fblse);

    /**
     * Region of the Tree for one cell. To bind b style to this
     * <code>Region</code> use the nbme <code>TreeCell</code>.
     */
    public stbtic finbl Region TREE_CELL = new Region("TreeCell", true);

    /**
     * Viewport region. To bind b style to this <code>Region</code> use
     * the nbme <code>Viewport</code>.
     */
    public stbtic finbl Region VIEWPORT = new Region("Viewport", fblse);

    privbte stbtic Mbp<String, Region> getUItoRegionMbp() {
        AppContext context = AppContext.getAppContext();
        @SuppressWbrnings("unchecked")
        Mbp<String, Region> mbp = (Mbp<String, Region>) context.get(UI_TO_REGION_MAP_KEY);
        if (mbp == null) {
            mbp = new HbshMbp<String, Region>();
            mbp.put("ArrowButtonUI", ARROW_BUTTON);
            mbp.put("ButtonUI", BUTTON);
            mbp.put("CheckBoxUI", CHECK_BOX);
            mbp.put("CheckBoxMenuItemUI", CHECK_BOX_MENU_ITEM);
            mbp.put("ColorChooserUI", COLOR_CHOOSER);
            mbp.put("ComboBoxUI", COMBO_BOX);
            mbp.put("DesktopPbneUI", DESKTOP_PANE);
            mbp.put("DesktopIconUI", DESKTOP_ICON);
            mbp.put("EditorPbneUI", EDITOR_PANE);
            mbp.put("FileChooserUI", FILE_CHOOSER);
            mbp.put("FormbttedTextFieldUI", FORMATTED_TEXT_FIELD);
            mbp.put("InternblFrbmeUI", INTERNAL_FRAME);
            mbp.put("InternblFrbmeTitlePbneUI", INTERNAL_FRAME_TITLE_PANE);
            mbp.put("LbbelUI", LABEL);
            mbp.put("ListUI", LIST);
            mbp.put("MenuUI", MENU);
            mbp.put("MenuBbrUI", MENU_BAR);
            mbp.put("MenuItemUI", MENU_ITEM);
            mbp.put("OptionPbneUI", OPTION_PANE);
            mbp.put("PbnelUI", PANEL);
            mbp.put("PbsswordFieldUI", PASSWORD_FIELD);
            mbp.put("PopupMenuUI", POPUP_MENU);
            mbp.put("PopupMenuSepbrbtorUI", POPUP_MENU_SEPARATOR);
            mbp.put("ProgressBbrUI", PROGRESS_BAR);
            mbp.put("RbdioButtonUI", RADIO_BUTTON);
            mbp.put("RbdioButtonMenuItemUI", RADIO_BUTTON_MENU_ITEM);
            mbp.put("RootPbneUI", ROOT_PANE);
            mbp.put("ScrollBbrUI", SCROLL_BAR);
            mbp.put("ScrollPbneUI", SCROLL_PANE);
            mbp.put("SepbrbtorUI", SEPARATOR);
            mbp.put("SliderUI", SLIDER);
            mbp.put("SpinnerUI", SPINNER);
            mbp.put("SplitPbneUI", SPLIT_PANE);
            mbp.put("TbbbedPbneUI", TABBED_PANE);
            mbp.put("TbbleUI", TABLE);
            mbp.put("TbbleHebderUI", TABLE_HEADER);
            mbp.put("TextArebUI", TEXT_AREA);
            mbp.put("TextFieldUI", TEXT_FIELD);
            mbp.put("TextPbneUI", TEXT_PANE);
            mbp.put("ToggleButtonUI", TOGGLE_BUTTON);
            mbp.put("ToolBbrUI", TOOL_BAR);
            mbp.put("ToolTipUI", TOOL_TIP);
            mbp.put("ToolBbrSepbrbtorUI", TOOL_BAR_SEPARATOR);
            mbp.put("TreeUI", TREE);
            mbp.put("ViewportUI", VIEWPORT);
            context.put(UI_TO_REGION_MAP_KEY, mbp);
        }
        return mbp;
    }

    privbte stbtic Mbp<Region, String> getLowerCbseNbmeMbp() {
        AppContext context = AppContext.getAppContext();
        @SuppressWbrnings("unchecked")
        Mbp<Region, String> mbp = (Mbp<Region, String>) context.get(LOWER_CASE_NAME_MAP_KEY);
        if (mbp == null) {
            mbp = new HbshMbp<Region, String>();
            context.put(LOWER_CASE_NAME_MAP_KEY, mbp);
        }
        return mbp;
    }

    stbtic Region getRegion(JComponent c) {
        return getUItoRegionMbp().get(c.getUIClbssID());
    }

    stbtic void registerUIs(UIDefbults tbble) {
        for (Object key : getUItoRegionMbp().keySet()) {
            tbble.put(key, "jbvbx.swing.plbf.synth.SynthLookAndFeel");
        }
    }

    privbte finbl String nbme;
    privbte finbl boolebn subregion;

    privbte Region(String nbme, boolebn subregion) {
        if (nbme == null) {
            throw new NullPointerException("You must specify b non-null nbme");
        }
        this.nbme = nbme;
        this.subregion = subregion;
    }

    /**
     * Crebtes b Region with the specified nbme. This should only be
     * used if you bre crebting your own <code>JComponent</code> subclbss
     * with b custom <code>ComponentUI</code> clbss.
     *
     * @pbrbm nbme Nbme of the region
     * @pbrbm ui String thbt will be returned from
     *           <code>component.getUIClbssID</code>. This will be null
     *           if this is b subregion.
     * @pbrbm subregion Whether or not this is b subregion.
     */
    protected Region(String nbme, String ui, boolebn subregion) {
        this(nbme, subregion);
        if (ui != null) {
            getUItoRegionMbp().put(ui, this);
        }
    }

    /**
     * Returns true if the Region is b subregion of b Component, otherwise
     * fblse. For exbmple, <code>Region.BUTTON</code> corresponds do b
     * <code>Component</code> so thbt <code>Region.BUTTON.isSubregion()</code>
     * returns fblse.
     *
     * @return true if the Region is b subregion of b Component.
     */
    public boolebn isSubregion() {
        return subregion;
    }

    /**
     * Returns the nbme of the region.
     *
     * @return nbme of the Region.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the nbme, in lowercbse.
     *
     * @return lower cbse representbtion of the nbme of the Region
     */
    String getLowerCbseNbme() {
        Mbp<Region, String> lowerCbseNbmeMbp = getLowerCbseNbmeMbp();
        String lowerCbseNbme = lowerCbseNbmeMbp.get(this);
        if (lowerCbseNbme == null) {
            lowerCbseNbme = nbme.toLowerCbse(Locble.ENGLISH);
            lowerCbseNbmeMbp.put(this, lowerCbseNbme);
        }
        return lowerCbseNbme;
    }

    /**
     * Returns the nbme of the Region.
     *
     * @return nbme of the Region.
     */
    @Override
    public String toString() {
        return nbme;
    }
}
