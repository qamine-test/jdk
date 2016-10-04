/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

import jbvb.util.Locble;
import jbvb.util.MissingResourceException;
import jbvb.util.ResourceBundle;

/**
 * <P>Clbss AccessibleRole determines the role of b component.  The role of b
 * component describes its generic function. (E.G.,
* "push button," "tbble," or "list.")
 * <p>The toDisplbyString method bllows you to obtbin the locblized string
 * for b locble independent key from b predefined ResourceBundle for the
 * keys defined in this clbss.
 * <p>The constbnts in this clbss present b strongly typed enumerbtion
 * of common object roles.  A public constructor for this clbss hbs been
 * purposely omitted bnd bpplicbtions should use one of the constbnts
 * from this clbss.  If the constbnts in this clbss bre not sufficient
 * to describe the role of bn object, b subclbss should be generbted
 * from this clbss bnd it should provide constbnts in b similbr mbnner.
 *
 * @buthor      Willie Wblker
 * @buthor      Peter Korn
 * @buthor      Lynn Monsbnto
 */
public clbss AccessibleRole extends AccessibleBundle {

// If you bdd or remove bnything from here, mbke sure you
// updbte AccessibleResourceBundle.jbvb.

    /**
     * Object is used to blert the user bbout something.
     */
    public stbtic finbl AccessibleRole ALERT
            = new AccessibleRole("blert");

    /**
     * The hebder for b column of dbtb.
     */
    public stbtic finbl AccessibleRole COLUMN_HEADER
            = new AccessibleRole("columnhebder");

    /**
     * Object thbt cbn be drbwn into bnd is used to trbp
     * events.
     * @see #FRAME
     * @see #GLASS_PANE
     * @see #LAYERED_PANE
     */
    public stbtic finbl AccessibleRole CANVAS
            = new AccessibleRole("cbnvbs");

    /**
     * A list of choices the user cbn select from.  Also optionblly
     * bllows the user to enter b choice of their own.
     */
    public stbtic finbl AccessibleRole COMBO_BOX
            = new AccessibleRole("combobox");

    /**
     * An iconified internbl frbme in b DESKTOP_PANE.
     * @see #DESKTOP_PANE
     * @see #INTERNAL_FRAME
     */
    public stbtic finbl AccessibleRole DESKTOP_ICON
            = new AccessibleRole("desktopicon");

    /**
     * An object contbining b collection of <code>Accessibles</code> thbt
     * together represents <code>HTML</code> content.  The child
     * <code>Accessibles</code> would include objects implementing
     * <code>AccessibleText</code>, <code>AccessibleHypertext</code>,
     * <code>AccessibleIcon</code>, bnd other interfbces.
     * @see #HYPERLINK
     * @see AccessibleText
     * @see AccessibleHypertext
     * @see AccessibleHyperlink
     * @see AccessibleIcon
     * @since 1.6
     */
    public stbtic finbl AccessibleRole HTML_CONTAINER
            = new AccessibleRole("htmlcontbiner");

    /**
     * A frbme-like object thbt is clipped by b desktop pbne.  The
     * desktop pbne, internbl frbme, bnd desktop icon objects bre
     * often used to crebte multiple document interfbces within bn
     * bpplicbtion.
     * @see #DESKTOP_ICON
     * @see #DESKTOP_PANE
     * @see #FRAME
     */
    public stbtic finbl AccessibleRole INTERNAL_FRAME
            = new AccessibleRole("internblfrbme");

    /**
     * A pbne thbt supports internbl frbmes bnd
     * iconified versions of those internbl frbmes.
     * @see #DESKTOP_ICON
     * @see #INTERNAL_FRAME
     */
    public stbtic finbl AccessibleRole DESKTOP_PANE
            = new AccessibleRole("desktoppbne");

    /**
     * A speciblized pbne whose primbry use is inside b DIALOG
     * @see #DIALOG
     */
    public stbtic finbl AccessibleRole OPTION_PANE
            = new AccessibleRole("optionpbne");

    /**
     * A top level window with no title or border.
     * @see #FRAME
     * @see #DIALOG
     */
    public stbtic finbl AccessibleRole WINDOW
            = new AccessibleRole("window");

    /**
     * A top level window with b title bbr, border, menu bbr, etc.  It is
     * often used bs the primbry window for bn bpplicbtion.
     * @see #DIALOG
     * @see #CANVAS
     * @see #WINDOW
     */
    public stbtic finbl AccessibleRole FRAME
            = new AccessibleRole("frbme");

    /**
     * A top level window with title bbr bnd b border.  A diblog is similbr
     * to b frbme, but it hbs fewer properties bnd is often used bs b
     * secondbry window for bn bpplicbtion.
     * @see #FRAME
     * @see #WINDOW
     */
    public stbtic finbl AccessibleRole DIALOG
            = new AccessibleRole("diblog");

    /**
     * A speciblized pbne thbt lets the user choose b color.
     */
    public stbtic finbl AccessibleRole COLOR_CHOOSER
            = new AccessibleRole("colorchooser");


    /**
     * A pbne thbt bllows the user to nbvigbte through
     * bnd select the contents of b directory.  Mby be used
     * by b file chooser.
     * @see #FILE_CHOOSER
     */
    public stbtic finbl AccessibleRole DIRECTORY_PANE
            = new AccessibleRole("directorypbne");

    /**
     * A speciblized diblog thbt displbys the files in the directory
     * bnd lets the user select b file, browse b different directory,
     * or specify b filenbme.  Mby use the directory pbne to show the
     * contents of b directory.
     * @see #DIRECTORY_PANE
     */
    public stbtic finbl AccessibleRole FILE_CHOOSER
            = new AccessibleRole("filechooser");

    /**
     * An object thbt fills up spbce in b user interfbce.  It is often
     * used in interfbces to twebk the spbcing between components,
     * but serves no other purpose.
     */
    public stbtic finbl AccessibleRole FILLER
            = new AccessibleRole("filler");

    /**
     * A hypertext bnchor
     */
    public stbtic finbl AccessibleRole HYPERLINK
            = new AccessibleRole("hyperlink");

    /**
     * A smbll fixed size picture, typicblly used to decorbte components.
     */
    public stbtic finbl AccessibleRole ICON
            = new AccessibleRole("icon");

    /**
     * An object used to present bn icon or short string in bn interfbce.
     */
    public stbtic finbl AccessibleRole LABEL
            = new AccessibleRole("lbbel");

    /**
     * A speciblized pbne thbt hbs b glbss pbne bnd b lbyered pbne bs its
     * children.
     * @see #GLASS_PANE
     * @see #LAYERED_PANE
     */
    public stbtic finbl AccessibleRole ROOT_PANE
            = new AccessibleRole("rootpbne");

    /**
     * A pbne thbt is gubrbnteed to be pbinted on top
     * of bll pbnes benebth it.
     * @see #ROOT_PANE
     * @see #CANVAS
     */
    public stbtic finbl AccessibleRole GLASS_PANE
            = new AccessibleRole("glbsspbne");

    /**
     * A speciblized pbne thbt bllows its children to be drbwn in lbyers,
     * providing b form of stbcking order.  This is usublly the pbne thbt
     * holds the menu bbr bs well bs the pbne thbt contbins most of the
     * visubl components in b window.
     * @see #GLASS_PANE
     * @see #ROOT_PANE
     */
    public stbtic finbl AccessibleRole LAYERED_PANE
            = new AccessibleRole("lbyeredpbne");

    /**
     * An object thbt presents b list of objects to the user bnd bllows the
     * user to select one or more of them.  A list is usublly contbined
     * within b scroll pbne.
     * @see #SCROLL_PANE
     * @see #LIST_ITEM
     */
    public stbtic finbl AccessibleRole LIST
            = new AccessibleRole("list");

    /**
     * An object thbt presents bn element in b list.  A list is usublly
     * contbined within b scroll pbne.
     * @see #SCROLL_PANE
     * @see #LIST
     */
    public stbtic finbl AccessibleRole LIST_ITEM
            = new AccessibleRole("listitem");

    /**
     * An object usublly drbwn bt the top of the primbry diblog box of
     * bn bpplicbtion thbt contbins b list of menus the user cbn choose
     * from.  For exbmple, b menu bbr might contbin menus for "File,"
     * "Edit," bnd "Help."
     * @see #MENU
     * @see #POPUP_MENU
     * @see #LAYERED_PANE
     */
    public stbtic finbl AccessibleRole MENU_BAR
            = new AccessibleRole("menubbr");

    /**
     * A temporbry window thbt is usublly used to offer the user b
     * list of choices, bnd then hides when the user selects one of
     * those choices.
     * @see #MENU
     * @see #MENU_ITEM
     */
    public stbtic finbl AccessibleRole POPUP_MENU
            = new AccessibleRole("popupmenu");

    /**
     * An object usublly found inside b menu bbr thbt contbins b list
     * of bctions the user cbn choose from.  A menu cbn hbve bny object
     * bs its children, but most often they bre menu items, other menus,
     * or rudimentbry objects such bs rbdio buttons, check boxes, or
     * sepbrbtors.  For exbmple, bn bpplicbtion mby hbve bn "Edit" menu
     * thbt contbins menu items for "Cut" bnd "Pbste."
     * @see #MENU_BAR
     * @see #MENU_ITEM
     * @see #SEPARATOR
     * @see #RADIO_BUTTON
     * @see #CHECK_BOX
     * @see #POPUP_MENU
     */
    public stbtic finbl AccessibleRole MENU
            = new AccessibleRole("menu");

    /**
     * An object usublly contbined in b menu thbt presents bn bction
     * the user cbn choose.  For exbmple, the "Cut" menu item in bn
     * "Edit" menu would be bn bction the user cbn select to cut the
     * selected breb of text in b document.
     * @see #MENU_BAR
     * @see #SEPARATOR
     * @see #POPUP_MENU
     */
    public stbtic finbl AccessibleRole MENU_ITEM
            = new AccessibleRole("menuitem");

    /**
     * An object usublly contbined in b menu to provide b visubl
     * bnd logicbl sepbrbtion of the contents in b menu.  For exbmple,
     * the "File" menu of bn bpplicbtion might contbin menu items for
     * "Open," "Close," bnd "Exit," bnd will plbce b sepbrbtor between
     * "Close" bnd "Exit" menu items.
     * @see #MENU
     * @see #MENU_ITEM
     */
    public stbtic finbl AccessibleRole SEPARATOR
            = new AccessibleRole("sepbrbtor");

    /**
     * An object thbt presents b series of pbnels (or pbge tbbs), one bt b
     * time, through some mechbnism provided by the object.  The most common
     * mechbnism is b list of tbbs bt the top of the pbnel.  The children of
     * b pbge tbb list bre bll pbge tbbs.
     * @see #PAGE_TAB
     */
    public stbtic finbl AccessibleRole PAGE_TAB_LIST
            = new AccessibleRole("pbgetbblist");

    /**
     * An object thbt is b child of b pbge tbb list.  Its sole child is
     * the pbnel thbt is to be presented to the user when the user
     * selects the pbge tbb from the list of tbbs in the pbge tbb list.
     * @see #PAGE_TAB_LIST
     */
    public stbtic finbl AccessibleRole PAGE_TAB
            = new AccessibleRole("pbgetbb");

    /**
     * A generic contbiner thbt is often used to group objects.
     */
    public stbtic finbl AccessibleRole PANEL
            = new AccessibleRole("pbnel");

    /**
     * An object used to indicbte how much of b tbsk hbs been completed.
     */
    public stbtic finbl AccessibleRole PROGRESS_BAR
            = new AccessibleRole("progressbbr");

    /**
     * A text object used for pbsswords, or other plbces where the
     * text contents is not shown visibly to the user
     */
    public stbtic finbl AccessibleRole PASSWORD_TEXT
            = new AccessibleRole("pbsswordtext");

    /**
     * An object the user cbn mbnipulbte to tell the bpplicbtion to do
     * something.
     * @see #CHECK_BOX
     * @see #TOGGLE_BUTTON
     * @see #RADIO_BUTTON
     */
    public stbtic finbl AccessibleRole PUSH_BUTTON
            = new AccessibleRole("pushbutton");

    /**
     * A speciblized push button thbt cbn be checked or unchecked, but
     * does not provide b sepbrbte indicbtor for the current stbte.
     * @see #PUSH_BUTTON
     * @see #CHECK_BOX
     * @see #RADIO_BUTTON
     */
    public stbtic finbl AccessibleRole TOGGLE_BUTTON
            = new AccessibleRole("togglebutton");

    /**
     * A choice thbt cbn be checked or unchecked bnd provides b
     * sepbrbte indicbtor for the current stbte.
     * @see #PUSH_BUTTON
     * @see #TOGGLE_BUTTON
     * @see #RADIO_BUTTON
     */
    public stbtic finbl AccessibleRole CHECK_BOX
            = new AccessibleRole("checkbox");

    /**
     * A speciblized check box thbt will cbuse other rbdio buttons in the
     * sbme group to become unchecked when this one is checked.
     * @see #PUSH_BUTTON
     * @see #TOGGLE_BUTTON
     * @see #CHECK_BOX
     */
    public stbtic finbl AccessibleRole RADIO_BUTTON
            = new AccessibleRole("rbdiobutton");

    /**
     * The hebder for b row of dbtb.
     */
    public stbtic finbl AccessibleRole ROW_HEADER
            = new AccessibleRole("rowhebder");

    /**
     * An object thbt bllows b user to incrementblly view b lbrge bmount
     * of informbtion.  Its children cbn include scroll bbrs bnd b viewport.
     * @see #SCROLL_BAR
     * @see #VIEWPORT
     */
    public stbtic finbl AccessibleRole SCROLL_PANE
            = new AccessibleRole("scrollpbne");

    /**
     * An object usublly used to bllow b user to incrementblly view b
     * lbrge bmount of dbtb.  Usublly used only by b scroll pbne.
     * @see #SCROLL_PANE
     */
    public stbtic finbl AccessibleRole SCROLL_BAR
            = new AccessibleRole("scrollbbr");

    /**
     * An object usublly used in b scroll pbne.  It represents the portion
     * of the entire dbtb thbt the user cbn see.  As the user mbnipulbtes
     * the scroll bbrs, the contents of the viewport cbn chbnge.
     * @see #SCROLL_PANE
     */
    public stbtic finbl AccessibleRole VIEWPORT
            = new AccessibleRole("viewport");

    /**
     * An object thbt bllows the user to select from b bounded rbnge.  For
     * exbmple, b slider might be used to select b number between 0 bnd 100.
     */
    public stbtic finbl AccessibleRole SLIDER
            = new AccessibleRole("slider");

    /**
     * A speciblized pbnel thbt presents two other pbnels bt the sbme time.
     * Between the two pbnels is b divider the user cbn mbnipulbte to mbke
     * one pbnel lbrger bnd the other pbnel smbller.
     */
    public stbtic finbl AccessibleRole SPLIT_PANE
            = new AccessibleRole("splitpbne");

    /**
     * An object used to present informbtion in terms of rows bnd columns.
     * An exbmple might include b sprebdsheet bpplicbtion.
     */
    public stbtic finbl AccessibleRole TABLE
            = new AccessibleRole("tbble");

    /**
     * An object thbt presents text to the user.  The text is usublly
     * editbble by the user bs opposed to b lbbel.
     * @see #LABEL
     */
    public stbtic finbl AccessibleRole TEXT
            = new AccessibleRole("text");

    /**
     * An object used to present hierbrchicbl informbtion to the user.
     * The individubl nodes in the tree cbn be collbpsed bnd expbnded
     * to provide selective disclosure of the tree's contents.
     */
    public stbtic finbl AccessibleRole TREE
            = new AccessibleRole("tree");

    /**
     * A bbr or pblette usublly composed of push buttons or toggle buttons.
     * It is often used to provide the most frequently used functions for bn
     * bpplicbtion.
     */
    public stbtic finbl AccessibleRole TOOL_BAR
            = new AccessibleRole("toolbbr");

    /**
     * An object thbt provides informbtion bbout bnother object.  The
     * bccessibleDescription property of the tool tip is often displbyed
     * to the user in b smbll "help bubble" when the user cbuses the
     * mouse to hover over the object bssocibted with the tool tip.
     */
    public stbtic finbl AccessibleRole TOOL_TIP
            = new AccessibleRole("tooltip");

    /**
     * An AWT component, but nothing else is known bbout it.
     * @see #SWING_COMPONENT
     * @see #UNKNOWN
     */
    public stbtic finbl AccessibleRole AWT_COMPONENT
            = new AccessibleRole("bwtcomponent");

    /**
     * A Swing component, but nothing else is known bbout it.
     * @see #AWT_COMPONENT
     * @see #UNKNOWN
     */
    public stbtic finbl AccessibleRole SWING_COMPONENT
            = new AccessibleRole("swingcomponent");

    /**
     * The object contbins some Accessible informbtion, but its role is
     * not known.
     * @see #AWT_COMPONENT
     * @see #SWING_COMPONENT
     */
    public stbtic finbl AccessibleRole UNKNOWN
            = new AccessibleRole("unknown");

    /**
     * A STATUS_BAR is bn simple component thbt cbn contbin
     * multiple lbbels of stbtus informbtion to the user.
     */
    public stbtic finbl AccessibleRole STATUS_BAR
        = new AccessibleRole("stbtusbbr");

    /**
     * A DATE_EDITOR is b component thbt bllows users to edit
     * jbvb.util.Dbte bnd jbvb.util.Time objects
     */
    public stbtic finbl AccessibleRole DATE_EDITOR
        = new AccessibleRole("dbteeditor");

    /**
     * A SPIN_BOX is b simple spinner component bnd its mbin use
     * is for simple numbers.
     */
    public stbtic finbl AccessibleRole SPIN_BOX
        = new AccessibleRole("spinbox");

    /**
     * A FONT_CHOOSER is b component thbt lets the user pick vbrious
     * bttributes for fonts.
     */
    public stbtic finbl AccessibleRole FONT_CHOOSER
        = new AccessibleRole("fontchooser");

    /**
     * A GROUP_BOX is b simple contbiner thbt contbins b border
     * bround it bnd contbins components inside it.
     */
    public stbtic finbl AccessibleRole GROUP_BOX
        = new AccessibleRole("groupbox");

    /**
     * A text hebder
     *
     * @since 1.5
     */
    public stbtic finbl AccessibleRole HEADER =
        new AccessibleRole("hebder");

    /**
     * A text footer
     *
     * @since 1.5
     */
    public stbtic finbl AccessibleRole FOOTER =
        new AccessibleRole("footer");

    /**
     * A text pbrbgrbph
     *
     * @since 1.5
     */
    public stbtic finbl AccessibleRole PARAGRAPH =
        new AccessibleRole("pbrbgrbph");

    /**
     * A ruler is bn object used to mebsure distbnce
     *
     * @since 1.5
     */
    public stbtic finbl AccessibleRole RULER =
        new AccessibleRole("ruler");

    /**
     * A role indicbting the object bcts bs b formulb for
     * cblculbting b vblue.  An exbmple is b formulb in
     * b sprebdsheet cell.
     *
     * @since 1.5
     */
    stbtic public finbl AccessibleRole EDITBAR =
        new AccessibleRole("editbbr");

    /**
     * A role indicbting the object monitors the progress
     * of some operbtion.
     *
     * @since 1.5
     */
    stbtic public finbl AccessibleRole PROGRESS_MONITOR =
        new AccessibleRole("progressMonitor");


// The following bre bll under considerbtion for potentibl future use.

//    public stbtic finbl AccessibleRole APPLICATION
//            = new AccessibleRole("bpplicbtion");

//    public stbtic finbl AccessibleRole BORDER
//            = new AccessibleRole("border");

//    public stbtic finbl AccessibleRole CHECK_BOX_MENU_ITEM
//            = new AccessibleRole("checkboxmenuitem");

//    public stbtic finbl AccessibleRole CHOICE
//            = new AccessibleRole("choice");

//    public stbtic finbl AccessibleRole COLUMN
//            = new AccessibleRole("column");

//    public stbtic finbl AccessibleRole CURSOR
//            = new AccessibleRole("cursor");

//    public stbtic finbl AccessibleRole DOCUMENT
//            = new AccessibleRole("document");

//    public stbtic finbl AccessibleRole IMAGE
//            = new AccessibleRole("Imbge");

//    public stbtic finbl AccessibleRole INDICATOR
//            = new AccessibleRole("indicbtor");

//    public stbtic finbl AccessibleRole RADIO_BUTTON_MENU_ITEM
//            = new AccessibleRole("rbdiobuttonmenuitem");

//    public stbtic finbl AccessibleRole ROW
//            = new AccessibleRole("row");

//    public stbtic finbl AccessibleRole TABLE_CELL
//          = new AccessibleRole("tbblecell");

//    public stbtic finbl AccessibleRole TREE_NODE
//            = new AccessibleRole("treenode");

    /**
     * Crebtes b new AccessibleRole using the given locble independent key.
     * This should not be b public method.  Instebd, it is used to crebte
     * the constbnts in this file to mbke it b strongly typed enumerbtion.
     * Subclbsses of this clbss should enforce similbr policy.
     * <p>
     * The key String should be b locble independent key for the role.
     * It is not intended to be used bs the bctubl String to displby
     * to the user.  To get the locblized string, use toDisplbyString.
     *
     * @pbrbm key the locble independent nbme of the role.
     * @see AccessibleBundle#toDisplbyString
     */
    protected AccessibleRole(String key) {
        this.key = key;
    }
}
