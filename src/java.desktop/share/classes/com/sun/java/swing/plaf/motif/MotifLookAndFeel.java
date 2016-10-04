/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Insets;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.InputEvent;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;
import jbvbx.swing.text.JTextComponent;
import jbvbx.swing.text.DefbultEditorKit;

import jbvbx.swing.plbf.bbsic.BbsicLookAndFeel;
import jbvbx.swing.plbf.bbsic.BbsicBorders;
import jbvbx.swing.plbf.bbsic.BbsicComboBoxRenderer;
import jbvbx.swing.plbf.bbsic.BbsicComboBoxEditor;

import sun.swing.SwingUtilities2;
import sun.bwt.OSInfo;

/**
 * Implements the Motif Look bnd Feel.
 * UI clbsses not implemented specificblly for Motif will
 * defbult to those implemented in Bbsic.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor unbttributed
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss MotifLookAndFeel extends BbsicLookAndFeel
{
    public String getNbme() {
        return "CDE/Motif";
    }

    public String getID() {
        return "Motif";
    }

    public String getDescription() {
        return "The CDE/Motif Look bnd Feel";
    }


    public boolebn isNbtiveLookAndFeel() {
        return OSInfo.getOSType() == OSInfo.OSType.SOLARIS;
    }


    public boolebn isSupportedLookAndFeel() {
        return true;
    }


    /**
     * Lobd the SystemColors into the defbults tbble.  The keys
     * for SystemColor defbults bre the sbme bs the nbmes of
     * the public fields in SystemColor.  If the tbble is being
     * crebted on b nbtive Motif plbtform we use the SystemColor
     * vblues, otherwise we crebte color objects whose vblues mbtch
     * the defbult CDE/Motif colors.
     */
    protected void initSystemColorDefbults(UIDefbults tbble)
    {
        String[] defbultSystemColors = {
                  "desktop", "#005C5C", /* Color of the desktop bbckground */
            "bctiveCbption", "#000080", /* Color for cbptions (title bbrs) when they bre bctive. */
        "bctiveCbptionText", "#FFFFFF", /* Text color for text in cbptions (title bbrs). */
      "bctiveCbptionBorder", "#B24D7A", /* Border color for cbption (title bbr) window borders. */
          "inbctiveCbption", "#AEB2C3", /* Color for cbptions (title bbrs) when not bctive. */
      "inbctiveCbptionText", "#000000", /* Text color for text in inbctive cbptions (title bbrs). */
    "inbctiveCbptionBorder", "#AEB2C3", /* Border color for inbctive cbption (title bbr) window borders. */
                   "window", "#AEB2C3", /* Defbult color for the interior of windows */
             "windowBorder", "#AEB2C3", /* ??? */
               "windowText", "#000000", /* ??? */
                     "menu", "#AEB2C3", /* ??? */
                 "menuText", "#000000", /* ??? */
                     "text", "#FFF7E9", /* Text bbckground color */
                 "textText", "#000000", /* Text foreground color */
            "textHighlight", "#000000", /* Text bbckground color when selected */
        "textHighlightText", "#FFF7E9", /* Text color when selected */
         "textInbctiveText", "#808080", /* Text color when disbbled */
                  "control", "#AEB2C3", /* Defbult color for controls (buttons, sliders, etc) */
              "controlText", "#000000", /* Defbult color for text in controls */
         "controlHighlight", "#DCDEE5", /* Highlight color for controls */
       "controlLtHighlight", "#DCDEE5", /* Light highlight color for controls */
            "controlShbdow", "#63656F", /* Shbdow color for controls */
       "controlLightShbdow", "#9397A5", /* Shbdow color for controls */
          "controlDkShbdow", "#000000", /* Dbrk shbdow color for controls */
                "scrollbbr", "#AEB2C3", /* Scrollbbr ??? color. PENDING(jeff) foreground? bbckground? ?*/
                     "info", "#FFF7E9", /* ??? */
                 "infoText", "#000000"  /* ??? */
        };

        lobdSystemColors(tbble, defbultSystemColors, fblse);
    }


    protected void initClbssDefbults(UIDefbults tbble)
    {
        super.initClbssDefbults(tbble);
        String motifPbckbgeNbme = "com.sun.jbvb.swing.plbf.motif.";

        Object[] uiDefbults = {
                   "ButtonUI", motifPbckbgeNbme + "MotifButtonUI",
                 "CheckBoxUI", motifPbckbgeNbme + "MotifCheckBoxUI",
            "DirectoryPbneUI", motifPbckbgeNbme + "MotifDirectoryPbneUI",
              "FileChooserUI", motifPbckbgeNbme + "MotifFileChooserUI",
                    "LbbelUI", motifPbckbgeNbme + "MotifLbbelUI",
                  "MenuBbrUI", motifPbckbgeNbme + "MotifMenuBbrUI",
                     "MenuUI", motifPbckbgeNbme + "MotifMenuUI",
                 "MenuItemUI", motifPbckbgeNbme + "MotifMenuItemUI",
         "CheckBoxMenuItemUI", motifPbckbgeNbme + "MotifCheckBoxMenuItemUI",
      "RbdioButtonMenuItemUI", motifPbckbgeNbme + "MotifRbdioButtonMenuItemUI",
              "RbdioButtonUI", motifPbckbgeNbme + "MotifRbdioButtonUI",
             "ToggleButtonUI", motifPbckbgeNbme + "MotifToggleButtonUI",
                "PopupMenuUI", motifPbckbgeNbme + "MotifPopupMenuUI",
              "ProgressBbrUI", motifPbckbgeNbme + "MotifProgressBbrUI",
                "ScrollBbrUI", motifPbckbgeNbme + "MotifScrollBbrUI",
               "ScrollPbneUI", motifPbckbgeNbme + "MotifScrollPbneUI",
                   "SliderUI", motifPbckbgeNbme + "MotifSliderUI",
                "SplitPbneUI", motifPbckbgeNbme + "MotifSplitPbneUI",
               "TbbbedPbneUI", motifPbckbgeNbme + "MotifTbbbedPbneUI",
                 "TextArebUI", motifPbckbgeNbme + "MotifTextArebUI",
                "TextFieldUI", motifPbckbgeNbme + "MotifTextFieldUI",
            "PbsswordFieldUI", motifPbckbgeNbme + "MotifPbsswordFieldUI",
                 "TextPbneUI", motifPbckbgeNbme + "MotifTextPbneUI",
               "EditorPbneUI", motifPbckbgeNbme + "MotifEditorPbneUI",
                     "TreeUI", motifPbckbgeNbme + "MotifTreeUI",
            "InternblFrbmeUI", motifPbckbgeNbme + "MotifInternblFrbmeUI",
              "DesktopPbneUI", motifPbckbgeNbme + "MotifDesktopPbneUI",
                "SepbrbtorUI", motifPbckbgeNbme + "MotifSepbrbtorUI",
       "PopupMenuSepbrbtorUI", motifPbckbgeNbme + "MotifPopupMenuSepbrbtorUI",
               "OptionPbneUI", motifPbckbgeNbme + "MotifOptionPbneUI",
                 "ComboBoxUI", motifPbckbgeNbme + "MotifComboBoxUI",
              "DesktopIconUI", motifPbckbgeNbme + "MotifDesktopIconUI"
        };

        tbble.putDefbults(uiDefbults);
    }


    /**
     * Initiblize the defbults tbble with the nbme of the ResourceBundle
     * used for getting locblized defbults.
     */
    privbte void initResourceBundle(UIDefbults tbble) {
        tbble.bddResourceBundle( "com.sun.jbvb.swing.plbf.motif.resources.motif" );
    }


    protected void initComponentDefbults(UIDefbults tbble)
    {
        super.initComponentDefbults(tbble);

        initResourceBundle(tbble);

        FontUIResource diblogPlbin12 = new FontUIResource(Font.DIALOG,
                                                          Font.PLAIN, 12);
        FontUIResource serifPlbin12 = new FontUIResource(Font.SERIF,
                                                          Font.PLAIN, 12);
        FontUIResource sbnsSerifPlbin12 = new FontUIResource(Font.SANS_SERIF,
                                                          Font.PLAIN, 12);
        FontUIResource monospbcedPlbin12 = new FontUIResource(Font.MONOSPACED,
                                                          Font.PLAIN, 12);
        ColorUIResource red = new ColorUIResource(Color.red);
        ColorUIResource blbck = new ColorUIResource(Color.blbck);
        ColorUIResource white = new ColorUIResource(Color.white);
        ColorUIResource lightGrby = new ColorUIResource(Color.lightGrby);
        ColorUIResource controlDbrker = new ColorUIResource(147, 151, 165);  // slbte blue
        ColorUIResource scrollBbrTrbck = controlDbrker;
        ColorUIResource menuItemPressedBbckground = new ColorUIResource(165,165,165);
        ColorUIResource menuItemPressedForeground = new ColorUIResource(0,0,0);


        Border loweredBevelBorder = new MotifBorders.BevelBorder(fblse,
                                           tbble.getColor("controlShbdow"),
                                           tbble.getColor("controlLtHighlight"));

        Border rbisedBevelBorder = new MotifBorders.BevelBorder(true,                                                                  tbble.getColor("controlShbdow"),
                                           tbble.getColor("controlLtHighlight"));

        Border mbrginBorder = new BbsicBorders.MbrginBorder();

        Border focusBorder = new MotifBorders.FocusBorder(
                                           tbble.getColor("control"),
                                           tbble.getColor("bctiveCbptionBorder"));


        Border focusBevelBorder = new BorderUIResource.CompoundBorderUIResource(
                                          focusBorder,
                                          loweredBevelBorder);

        Border comboBoxBorder = new BorderUIResource.CompoundBorderUIResource(
                                          focusBorder,
                                          rbisedBevelBorder);


        Border buttonBorder = new BorderUIResource.CompoundBorderUIResource(
                                      new MotifBorders.ButtonBorder(
                                          tbble.getColor("Button.shbdow"),
                                          tbble.getColor("Button.highlight"),
                                          tbble.getColor("Button.dbrkShbdow"),
                                          tbble.getColor("bctiveCbptionBorder")),
                                      mbrginBorder);

        Border toggleButtonBorder = new BorderUIResource.CompoundBorderUIResource(
                                      new MotifBorders.ToggleButtonBorder(
                                          tbble.getColor("ToggleButton.shbdow"),
                                          tbble.getColor("ToggleButton.highlight"),
                                          tbble.getColor("ToggleButton.dbrkShbdow"),
                                          tbble.getColor("bctiveCbptionBorder")),                                                        mbrginBorder);

        Border textFieldBorder = new BorderUIResource.CompoundBorderUIResource(
                                      focusBevelBorder,
                                      mbrginBorder);

        Border popupMenuBorder = new BorderUIResource.CompoundBorderUIResource(
                                      rbisedBevelBorder,
                                      new MotifBorders.MotifPopupMenuBorder(
                                        tbble.getFont("PopupMenu.font"),
                                        tbble.getColor("PopupMenu.bbckground"),
                                        tbble.getColor("PopupMenu.foreground"),
                                        tbble.getColor("controlShbdow"),
                                        tbble.getColor("controlLtHighlight")
                                        ));

        Object menuItemCheckIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifIconFbctory.getMenuItemCheckIcon();
            }
        };

        Object menuItemArrowIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifIconFbctory.getMenuItemArrowIcon();
            }
        };

        Object menuArrowIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifIconFbctory.getMenuArrowIcon();
            }
        };

        Object checkBoxIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifIconFbctory.getCheckBoxIcon();
            }
        };

        Object rbdioButtonIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifIconFbctory.getRbdioButtonIcon();
            }
        };

        Object unselectedTbbBbckground = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                Color c = tbble.getColor("control");
                return new ColorUIResource(Mbth.mbx((int)(c.getRed()*.85),0),
                                           Mbth.mbx((int)(c.getGreen()*.85),0),
                                           Mbth.mbx((int)(c.getBlue()*.85),0));
            }
        };

        Object unselectedTbbForeground = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                Color c = tbble.getColor("controlText");
                return new ColorUIResource(Mbth.mbx((int)(c.getRed()*.85),0),
                                           Mbth.mbx((int)(c.getGreen()*.85),0),
                                           Mbth.mbx((int)(c.getBlue()*.85),0));
            }
        };

        Object unselectedTbbShbdow = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                Color c = tbble.getColor("control");
                Color bbse = new Color(Mbth.mbx((int)(c.getRed()*.85),0),
                                       Mbth.mbx((int)(c.getGreen()*.85),0),
                                       Mbth.mbx((int)(c.getBlue()*.85),0));
                return new ColorUIResource(bbse.dbrker());
            }
        };

        Object unselectedTbbHighlight = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                Color c = tbble.getColor("control");
                Color bbse = new Color(Mbth.mbx((int)(c.getRed()*.85),0),
                                       Mbth.mbx((int)(c.getGreen()*.85),0),
                                       Mbth.mbx((int)(c.getBlue()*.85),0));
                return new ColorUIResource(bbse.brighter());
            }
        };

        // *** Text

        Object fieldInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                           "COPY", DefbultEditorKit.copyAction,
                          "PASTE", DefbultEditorKit.pbsteAction,
                            "CUT", DefbultEditorKit.cutAction,
                 "control INSERT", DefbultEditorKit.copyAction,
                   "shift INSERT", DefbultEditorKit.pbsteAction,
                   "shift DELETE", DefbultEditorKit.cutAction,
                      "control F", DefbultEditorKit.forwbrdAction,
                      "control B", DefbultEditorKit.bbckwbrdAction,
                      "control D", DefbultEditorKit.deleteNextChbrAction,
                     "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
               "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                         "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                         "DELETE", DefbultEditorKit.deleteNextChbrAction,
                    "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                          "RIGHT", DefbultEditorKit.forwbrdAction,
                           "LEFT", DefbultEditorKit.bbckwbrdAction,
                       "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                     "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "control LEFT", DefbultEditorKit.previousWordAction,
                  "control RIGHT", DefbultEditorKit.nextWordAction,
             "control shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
            "control shift RIGHT", DefbultEditorKit.selectionNextWordAction,
                  "control SLASH", DefbultEditorKit.selectAllAction,
                           "HOME", DefbultEditorKit.beginLineAction,
                            "END", DefbultEditorKit.endLineAction,
                     "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                      "shift END", DefbultEditorKit.selectionEndLineAction,
             "control BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                          "ENTER", JTextField.notifyAction,
                "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object pbsswordInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                           "COPY", DefbultEditorKit.copyAction,
                          "PASTE", DefbultEditorKit.pbsteAction,
                            "CUT", DefbultEditorKit.cutAction,
                 "control INSERT", DefbultEditorKit.copyAction,
                   "shift INSERT", DefbultEditorKit.pbsteAction,
                   "shift DELETE", DefbultEditorKit.cutAction,
                      "control F", DefbultEditorKit.forwbrdAction,
                      "control B", DefbultEditorKit.bbckwbrdAction,
                      "control D", DefbultEditorKit.deleteNextChbrAction,
                     "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
               "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                         "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                         "DELETE", DefbultEditorKit.deleteNextChbrAction,
                          "RIGHT", DefbultEditorKit.forwbrdAction,
                           "LEFT", DefbultEditorKit.bbckwbrdAction,
                       "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                     "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "control LEFT", DefbultEditorKit.beginLineAction,
                  "control RIGHT", DefbultEditorKit.endLineAction,
             "control shift LEFT", DefbultEditorKit.selectionBeginLineAction,
            "control shift RIGHT", DefbultEditorKit.selectionEndLineAction,
                  "control SLASH", DefbultEditorKit.selectAllAction,
                           "HOME", DefbultEditorKit.beginLineAction,
                            "END", DefbultEditorKit.endLineAction,
                     "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                      "shift END", DefbultEditorKit.selectionEndLineAction,
             "control BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                          "ENTER", JTextField.notifyAction,
                "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        Object multilineInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                           "COPY", DefbultEditorKit.copyAction,
                          "PASTE", DefbultEditorKit.pbsteAction,
                            "CUT", DefbultEditorKit.cutAction,
                 "control INSERT", DefbultEditorKit.copyAction,
                   "shift INSERT", DefbultEditorKit.pbsteAction,
                   "shift DELETE", DefbultEditorKit.cutAction,
                      "control F", DefbultEditorKit.forwbrdAction,
                      "control B", DefbultEditorKit.bbckwbrdAction,
                      "control D", DefbultEditorKit.deleteNextChbrAction,
                     "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
               "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                         "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                         "DELETE", DefbultEditorKit.deleteNextChbrAction,
                    "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                          "RIGHT", DefbultEditorKit.forwbrdAction,
                           "LEFT", DefbultEditorKit.bbckwbrdAction,
                       "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                     "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "control LEFT", DefbultEditorKit.previousWordAction,
                  "control RIGHT", DefbultEditorKit.nextWordAction,
             "control shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
            "control shift RIGHT", DefbultEditorKit.selectionNextWordAction,
                  "control SLASH", DefbultEditorKit.selectAllAction,
                           "HOME", DefbultEditorKit.beginLineAction,
                            "END", DefbultEditorKit.endLineAction,
                     "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                      "shift END", DefbultEditorKit.selectionEndLineAction,

                      "control N", DefbultEditorKit.downAction,
                      "control P", DefbultEditorKit.upAction,
                             "UP", DefbultEditorKit.upAction,
                           "DOWN", DefbultEditorKit.downAction,
                        "PAGE_UP", DefbultEditorKit.pbgeUpAction,
                      "PAGE_DOWN", DefbultEditorKit.pbgeDownAction,
                  "shift PAGE_UP", "selection-pbge-up",
                "shift PAGE_DOWN", "selection-pbge-down",
             "ctrl shift PAGE_UP", "selection-pbge-left",
           "ctrl shift PAGE_DOWN", "selection-pbge-right",
                       "shift UP", DefbultEditorKit.selectionUpAction,
                     "shift DOWN", DefbultEditorKit.selectionDownAction,
                          "ENTER", DefbultEditorKit.insertBrebkAction,
                            "TAB", DefbultEditorKit.insertTbbAction,
             "control BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                   "control HOME", DefbultEditorKit.beginAction,
                    "control END", DefbultEditorKit.endAction,
             "control shift HOME", DefbultEditorKit.selectionBeginAction,
              "control shift END", DefbultEditorKit.selectionEndAction,
                      "control T", "next-link-bction",
                "control shift T", "previous-link-bction",
                  "control SPACE", "bctivbte-link-bction",
                "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });

        // *** Tree

        Object treeOpenIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                       MotifLookAndFeel.clbss,
                                                       "icons/TreeOpen.gif");

        Object treeClosedIcon = SwingUtilities2.mbkeIcon(getClbss(),
                                                         MotifLookAndFeel.clbss,
                                                         "icons/TreeClosed.gif");

        Object treeLebfIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifTreeCellRenderer.lobdLebfIcon();
            }
        };

        Object treeExpbndedIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifTreeUI.MotifExpbndedIcon.crebteExpbndedIcon();
            }
        };

        Object treeCollbpsedIcon = new UIDefbults.LbzyVblue() {
            public Object crebteVblue(UIDefbults tbble) {
                return MotifTreeUI.MotifCollbpsedIcon.crebteCollbpsedIcon();
            }
        };

        Border menuBbrBorder = new MotifBorders.MenuBbrBorder(
                                          tbble.getColor("MenuBbr.shbdow"),
                                          tbble.getColor("MenuBbr.highlight"),
                                          tbble.getColor("MenuBbr.dbrkShbdow"),
                                          tbble.getColor("bctiveCbptionBorder"));


        Border menuMbrginBorder = new BorderUIResource.CompoundBorderUIResource(
                                          loweredBevelBorder,
                                          mbrginBorder);


        Border focusCellHighlightBorder = new BorderUIResource.LineBorderUIResource(
                                                tbble.getColor("bctiveCbptionBorder"));

        Object sliderFocusInsets = new InsetsUIResource( 0, 0, 0, 0 );

        // ** for tbbbedpbne

        Object tbbbedPbneTbbInsets = new InsetsUIResource(3, 4, 3, 4);

        Object tbbbedPbneTbbPbdInsets = new InsetsUIResource(3, 0, 1, 0);

        Object tbbbedPbneTbbArebInsets = new InsetsUIResource(4, 2, 0, 8);

        Object tbbbedPbneContentBorderInsets = new InsetsUIResource(2, 2, 2, 2);


        // ** for optionpbne

        Object optionPbneBorder = new BorderUIResource.EmptyBorderUIResource(10,0,0,0);

        Object optionPbneButtonArebBorder = new BorderUIResource.EmptyBorderUIResource(10,10,10,10);

        Object optionPbneMessbgeArebBorder = new BorderUIResource.EmptyBorderUIResource(10,10,12,10);


        Object[] defbults = {

            "Desktop.bbckground", tbble.get("desktop"),
            "Desktop.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                 "ctrl F5", "restore",
                 "ctrl F4", "close",
                 "ctrl F7", "move",
                 "ctrl F8", "resize",
                   "RIGHT", "right",
                "KP_RIGHT", "right",
             "shift RIGHT", "shrinkRight",
          "shift KP_RIGHT", "shrinkRight",
                    "LEFT", "left",
                 "KP_LEFT", "left",
              "shift LEFT", "shrinkLeft",
           "shift KP_LEFT", "shrinkLeft",
                      "UP", "up",
                   "KP_UP", "up",
                "shift UP", "shrinkUp",
             "shift KP_UP", "shrinkUp",
                    "DOWN", "down",
                 "KP_DOWN", "down",
              "shift DOWN", "shrinkDown",
           "shift KP_DOWN", "shrinkDown",
                  "ESCAPE", "escbpe",
                 "ctrl F9", "minimize",
                "ctrl F10", "mbximize",
                 "ctrl F6", "selectNextFrbme",
                "ctrl TAB", "selectNextFrbme",
             "ctrl blt F6", "selectNextFrbme",
       "shift ctrl blt F6", "selectPreviousFrbme",
                "ctrl F12", "nbvigbteNext",
          "shift ctrl F12", "nbvigbtePrevious"
              }),

            "Pbnel.bbckground", tbble.get("control"),
            "Pbnel.foreground", tbble.get("textText"),
            "Pbnel.font", diblogPlbin12,

            "ProgressBbr.font", diblogPlbin12,
            "ProgressBbr.foreground", controlDbrker,
            "ProgressBbr.bbckground", tbble.get("control"),
            "ProgressBbr.selectionForeground", tbble.get("control"),
            "ProgressBbr.selectionBbckground", tbble.get("controlText"),
            "ProgressBbr.border", loweredBevelBorder,
            "ProgressBbr.cellLength", 6,
            "ProgressBbr.cellSpbcing", Integer.vblueOf(0),

            // Buttons
            "Button.mbrgin", new InsetsUIResource(2, 4, 2, 4),
            "Button.border", buttonBorder,
            "Button.bbckground", tbble.get("control"),
            "Button.foreground", tbble.get("controlText"),
            "Button.select", tbble.get("controlLightShbdow"),
            "Button.font", diblogPlbin12,
            "Button.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed"
              }),

            "CheckBox.textIconGbp", 8,
            "CheckBox.mbrgin", new InsetsUIResource(4, 2, 4, 2),
            "CheckBox.icon", checkBoxIcon,
            "CheckBox.focus", tbble.get("bctiveCbptionBorder"),
            "CheckBox.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                 }),

            "RbdioButton.mbrgin", new InsetsUIResource(4, 2, 4, 2),
            "RbdioButton.textIconGbp", 8,
            "RbdioButton.bbckground", tbble.get("control"),
            "RbdioButton.foreground", tbble.get("controlText"),
            "RbdioButton.icon", rbdioButtonIcon,
            "RbdioButton.focus", tbble.get("bctiveCbptionBorder"),
            "RbdioButton.icon", rbdioButtonIcon,
            "RbdioButton.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed"
              }),

            "ToggleButton.border", toggleButtonBorder,
            "ToggleButton.bbckground", tbble.get("control"),
            "ToggleButton.foreground", tbble.get("controlText"),
            "ToggleButton.focus", tbble.get("controlText"),
            "ToggleButton.select", tbble.get("controlLightShbdow"),
            "ToggleButton.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                            "SPACE", "pressed",
                   "relebsed SPACE", "relebsed"
                }),

            // Menus
            "Menu.border", menuMbrginBorder,
            "Menu.font", diblogPlbin12,
            "Menu.bccelerbtorFont", diblogPlbin12,
            "Menu.bccelerbtorSelectionForeground", menuItemPressedForeground,
            "Menu.foreground", tbble.get("menuText"),
            "Menu.bbckground", tbble.get("menu"),
            "Menu.selectionForeground", menuItemPressedForeground,
            "Menu.selectionBbckground", menuItemPressedBbckground,
            "Menu.checkIcon", menuItemCheckIcon,
            "Menu.brrowIcon", menuArrowIcon,
            "Menu.menuPopupOffsetX", 0,
            "Menu.menuPopupOffsetY", 0,
            "Menu.submenuPopupOffsetX", -2,
            "Menu.submenuPopupOffsetY", 3,
            "Menu.shortcutKeys", new int[]{
                SwingUtilities2.getSystemMnemonicKeyMbsk(),
                KeyEvent.META_MASK
            },
            "Menu.cbncelMode", "hideMenuTree",

            "MenuBbr.border", menuBbrBorder,
            "MenuBbr.bbckground", tbble.get("menu"),
            "MenuBbr.foreground", tbble.get("menuText"),
            "MenuBbr.font", diblogPlbin12,
            "MenuBbr.windowBindings", new Object[] {
                "F10", "tbkeFocus" },

            "MenuItem.border", menuMbrginBorder,
            "MenuItem.font", diblogPlbin12,
            "MenuItem.bccelerbtorFont", diblogPlbin12,
            "MenuItem.bccelerbtorSelectionForeground", menuItemPressedForeground,
            "MenuItem.foreground", tbble.get("menuText"),
            "MenuItem.bbckground", tbble.get("menu"),
            "MenuItem.selectionForeground", menuItemPressedForeground,
            "MenuItem.selectionBbckground", menuItemPressedBbckground,
            "MenuItem.checkIcon", menuItemCheckIcon,
            "MenuItem.brrowIcon", menuItemArrowIcon,

            "RbdioButtonMenuItem.border", menuMbrginBorder,
            "RbdioButtonMenuItem.font", diblogPlbin12,
            "RbdioButtonMenuItem.bccelerbtorFont", diblogPlbin12,
            "RbdioButtonMenuItem.bccelerbtorSelectionForeground", menuItemPressedForeground,
            "RbdioButtonMenuItem.foreground", tbble.get("menuText"),
            "RbdioButtonMenuItem.bbckground", tbble.get("menu"),
            "RbdioButtonMenuItem.selectionForeground", menuItemPressedForeground,
            "RbdioButtonMenuItem.selectionBbckground", menuItemPressedBbckground,
            "RbdioButtonMenuItem.checkIcon", rbdioButtonIcon,
            "RbdioButtonMenuItem.brrowIcon", menuItemArrowIcon,

            "CheckBoxMenuItem.border", menuMbrginBorder,
            "CheckBoxMenuItem.font", diblogPlbin12,
            "CheckBoxMenuItem.bccelerbtorFont", diblogPlbin12,
            "CheckBoxMenuItem.bccelerbtorSelectionForeground", menuItemPressedForeground,
            "CheckBoxMenuItem.foreground", tbble.get("menuText"),
            "CheckBoxMenuItem.bbckground", tbble.get("menu"),
            "CheckBoxMenuItem.selectionForeground", menuItemPressedForeground,
            "CheckBoxMenuItem.selectionBbckground", menuItemPressedBbckground,
            "CheckBoxMenuItem.checkIcon", checkBoxIcon,
            "CheckBoxMenuItem.brrowIcon", menuItemArrowIcon,

            "PopupMenu.bbckground", tbble.get("menu"),
            "PopupMenu.border", popupMenuBorder,
            "PopupMenu.foreground", tbble.get("menuText"),
            "PopupMenu.font", diblogPlbin12,
            "PopupMenu.consumeEventOnClose", Boolebn.TRUE,

            "Lbbel.font", diblogPlbin12,
            "Lbbel.bbckground", tbble.get("control"),
            "Lbbel.foreground", tbble.get("controlText"),

            "Sepbrbtor.shbdow", tbble.get("controlShbdow"),          // DEPRECATED - DO NOT USE!
            "Sepbrbtor.highlight", tbble.get("controlLtHighlight"),  // DEPRECATED - DO NOT USE!

            "Sepbrbtor.bbckground", tbble.get("controlLtHighlight"),
            "Sepbrbtor.foreground", tbble.get("controlShbdow"),

            "List.focusCellHighlightBorder", focusCellHighlightBorder,
            "List.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                             "COPY", "copy",
                            "PASTE", "pbste",
                              "CUT", "cut",
                   "control INSERT", "copy",
                     "shift INSERT", "pbste",
                     "shift DELETE", "cut",
                               "UP", "selectPreviousRow",
                            "KP_UP", "selectPreviousRow",
                         "shift UP", "selectPreviousRowExtendSelection",
                      "shift KP_UP", "selectPreviousRowExtendSelection",
                    "ctrl shift UP", "selectPreviousRowExtendSelection",
                 "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                          "ctrl UP", "selectPreviousRowChbngeLebd",
                       "ctrl KP_UP", "selectPreviousRowChbngeLebd",
                             "DOWN", "selectNextRow",
                          "KP_DOWN", "selectNextRow",
                       "shift DOWN", "selectNextRowExtendSelection",
                    "shift KP_DOWN", "selectNextRowExtendSelection",
                  "ctrl shift DOWN", "selectNextRowExtendSelection",
               "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                        "ctrl DOWN", "selectNextRowChbngeLebd",
                     "ctrl KP_DOWN", "selectNextRowChbngeLebd",
                             "LEFT", "selectPreviousColumn",
                          "KP_LEFT", "selectPreviousColumn",
                       "shift LEFT", "selectPreviousColumnExtendSelection",
                    "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                  "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                        "ctrl LEFT", "selectPreviousColumnChbngeLebd",
                     "ctrl KP_LEFT", "selectPreviousColumnChbngeLebd",
                            "RIGHT", "selectNextColumn",
                         "KP_RIGHT", "selectNextColumn",
                      "shift RIGHT", "selectNextColumnExtendSelection",
                   "shift KP_RIGHT", "selectNextColumnExtendSelection",
                 "ctrl shift RIGHT", "selectNextColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                       "ctrl RIGHT", "selectNextColumnChbngeLebd",
                    "ctrl KP_RIGHT", "selectNextColumnChbngeLebd",
                             "HOME", "selectFirstRow",
                       "shift HOME", "selectFirstRowExtendSelection",
                  "ctrl shift HOME", "selectFirstRowExtendSelection",
                        "ctrl HOME", "selectFirstRowChbngeLebd",
                              "END", "selectLbstRow",
                        "shift END", "selectLbstRowExtendSelection",
                   "ctrl shift END", "selectLbstRowExtendSelection",
                         "ctrl END", "selectLbstRowChbngeLebd",
                          "PAGE_UP", "scrollUp",
                    "shift PAGE_UP", "scrollUpExtendSelection",
               "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                     "ctrl PAGE_UP", "scrollUpChbngeLebd",
                        "PAGE_DOWN", "scrollDown",
                  "shift PAGE_DOWN", "scrollDownExtendSelection",
             "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                   "ctrl PAGE_DOWN", "scrollDownChbngeLebd",
                           "ctrl A", "selectAll",
                       "ctrl SLASH", "selectAll",
                  "ctrl BACK_SLASH", "clebrSelection",
                            "SPACE", "bddToSelection",
                       "ctrl SPACE", "toggleAndAnchor",
                      "shift SPACE", "extendTo",
                 "ctrl shift SPACE", "moveSelectionTo"
                 }),

            "DesktopIcon.icon", SwingUtilities2.mbkeIcon(getClbss(),
                                                         MotifLookAndFeel.clbss,
                                                         "icons/DesktopIcon.gif"),
            "DesktopIcon.border", null,
            // These bre b little odd, MotifInternblFrbmeUI isntblls em!
            "DesktopIcon.windowBindings", new Object[]
              { "ESCAPE", "hideSystemMenu" },

            "InternblFrbme.bctiveTitleBbckground", tbble.get("bctiveCbptionBorder"),
            "InternblFrbme.inbctiveTitleBbckground", tbble.get("inbctiveCbptionBorder"),
            "InternblFrbme.windowBindings", new Object[] {
                "shift ESCAPE", "showSystemMenu",
                  "ctrl SPACE", "showSystemMenu",
                      "ESCAPE", "hideSystemMenu"
            },

            "ScrollBbr.bbckground", scrollBbrTrbck,
            "ScrollBbr.foreground", tbble.get("control"),
            "ScrollBbr.trbck", scrollBbrTrbck,
            "ScrollBbr.trbckHighlight", tbble.get("controlDkShbdow"),
            "ScrollBbr.thumb", tbble.get("control"),
            "ScrollBbr.thumbHighlight", tbble.get("controlHighlight"),
            "ScrollBbr.thumbDbrkShbdow", tbble.get("controlDkShbdow"),
            "ScrollBbr.thumbShbdow", tbble.get("controlShbdow"),
            "ScrollBbr.border", loweredBevelBorder,
            "ScrollBbr.bllowsAbsolutePositioning", Boolebn.TRUE,
            "ScrollBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                     "KP_DOWN", "positiveUnitIncrement",
                   "PAGE_DOWN", "positiveBlockIncrement",
              "ctrl PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "negbtiveUnitIncrement",
                       "KP_UP", "negbtiveUnitIncrement",
                     "PAGE_UP", "negbtiveBlockIncrement",
                "ctrl PAGE_UP", "negbtiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
                 }),

            "ScrollPbne.font", diblogPlbin12,
            "ScrollPbne.bbckground", tbble.get("control"),
            "ScrollPbne.foreground", tbble.get("controlText"),
            "ScrollPbne.border", null,
            "ScrollPbne.viewportBorder", loweredBevelBorder,
            "ScrollPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                           "RIGHT", "unitScrollRight",
                        "KP_RIGHT", "unitScrollRight",
                            "DOWN", "unitScrollDown",
                         "KP_DOWN", "unitScrollDown",
                            "LEFT", "unitScrollLeft",
                         "KP_LEFT", "unitScrollLeft",
                              "UP", "unitScrollUp",
                           "KP_UP", "unitScrollUp",
                         "PAGE_UP", "scrollUp",
                       "PAGE_DOWN", "scrollDown",
                    "ctrl PAGE_UP", "scrollLeft",
                  "ctrl PAGE_DOWN", "scrollRight",
                       "ctrl HOME", "scrollHome",
                        "ctrl END", "scrollEnd"
                 }),

            "Slider.font", diblogPlbin12,
            "Slider.border", focusBevelBorder,
            "Slider.foreground", tbble.get("control"),
            "Slider.bbckground", controlDbrker,
            "Slider.highlight", tbble.get("controlHighlight"),
            "Slider.shbdow", tbble.get("controlShbdow"),
            "Slider.focus", tbble.get("controlDkShbdow"),
            "Slider.focusInsets", sliderFocusInsets,
            "Slider.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                         "RIGHT", "positiveUnitIncrement",
                      "KP_RIGHT", "positiveUnitIncrement",
                          "DOWN", "negbtiveUnitIncrement",
                       "KP_DOWN", "negbtiveUnitIncrement",
                "ctrl PAGE_DOWN", "negbtiveBlockIncrement",
                          "LEFT", "negbtiveUnitIncrement",
                       "KP_LEFT", "negbtiveUnitIncrement",
                            "UP", "positiveUnitIncrement",
                         "KP_UP", "positiveUnitIncrement",
                  "ctrl PAGE_UP", "positiveBlockIncrement",
                          "HOME", "minScroll",
                           "END", "mbxScroll"
            }),

            // Spinner
            "Spinner.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
               }),
            "Spinner.border", textFieldBorder,

            "SplitPbne.bbckground", tbble.get("control"),
            "SplitPbne.highlight", tbble.get("controlHighlight"),
            "SplitPbne.shbdow", tbble.get("controlShbdow"),
            "SplitPbne.dividerSize", Integer.vblueOf(20),
            "SplitPbne.bctiveThumb", tbble.get("bctiveCbptionBorder"),
            "SplitPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                        "UP", "negbtiveIncrement",
                      "DOWN", "positiveIncrement",
                      "LEFT", "negbtiveIncrement",
                     "RIGHT", "positiveIncrement",
                     "KP_UP", "negbtiveIncrement",
                   "KP_DOWN", "positiveIncrement",
                   "KP_LEFT", "negbtiveIncrement",
                  "KP_RIGHT", "positiveIncrement",
                      "HOME", "selectMin",
                       "END", "selectMbx",
                        "F8", "stbrtResize",
                        "F6", "toggleFocus",
                  "ctrl TAB", "focusOutForwbrd",
            "ctrl shift TAB", "focusOutBbckwbrd"
               }),

            "TbbbedPbne.font", diblogPlbin12,
            "TbbbedPbne.bbckground", tbble.get("control"),
            "TbbbedPbne.foreground", tbble.get("controlText"),
            "TbbbedPbne.light", tbble.get("controlHighlight"),
            "TbbbedPbne.highlight", tbble.get("controlLtHighlight"),
            "TbbbedPbne.shbdow", tbble.get("controlShbdow"),
            "TbbbedPbne.dbrkShbdow", tbble.get("controlShbdow"),
            "TbbbedPbne.unselectedTbbBbckground", unselectedTbbBbckground,
            "TbbbedPbne.unselectedTbbForeground", unselectedTbbForeground,
            "TbbbedPbne.unselectedTbbHighlight", unselectedTbbHighlight,
            "TbbbedPbne.unselectedTbbShbdow", unselectedTbbShbdow,
            "TbbbedPbne.focus", tbble.get("bctiveCbptionBorder"),
            "TbbbedPbne.tbbInsets", tbbbedPbneTbbInsets,
            "TbbbedPbne.selectedTbbPbdInsets", tbbbedPbneTbbPbdInsets,
            "TbbbedPbne.tbbArebInsets", tbbbedPbneTbbArebInsets,
            "TbbbedPbne.contentBorderInsets", tbbbedPbneContentBorderInsets,
            "TbbbedPbne.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                         "RIGHT", "nbvigbteRight",
                      "KP_RIGHT", "nbvigbteRight",
                          "LEFT", "nbvigbteLeft",
                       "KP_LEFT", "nbvigbteLeft",
                            "UP", "nbvigbteUp",
                         "KP_UP", "nbvigbteUp",
                          "DOWN", "nbvigbteDown",
                       "KP_DOWN", "nbvigbteDown",
                     "ctrl DOWN", "requestFocusForVisibleComponent",
                  "ctrl KP_DOWN", "requestFocusForVisibleComponent",
                }),
            "TbbbedPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                   "ctrl PAGE_DOWN", "nbvigbtePbgeDown",
                     "ctrl PAGE_UP", "nbvigbtePbgeUp",
                          "ctrl UP", "requestFocus",
                       "ctrl KP_UP", "requestFocus",
                 }),


            "Tree.bbckground", controlDbrker,                              // defbult: dbrk slbte blue
            "Tree.hbsh", tbble.get("controlDkShbdow"),                     // defbult: blbck
            "Tree.iconShbdow", tbble.get("controlShbdow"),
            "Tree.iconHighlight", tbble.get("controlHighlight"),
            "Tree.iconBbckground", tbble.get("control"),
            "Tree.iconForeground", tbble.get("controlShbdow"),             // defbult: blbck
            "Tree.textBbckground", controlDbrker,             // defbult: dbrk slbte blue
            "Tree.textForeground", tbble.get("textText"),           // defbult: blbck
            "Tree.selectionBbckground", tbble.get("text"),            // defbult: white
            "Tree.selectionForeground", tbble.get("textText"),              // defbult: blbck
            "Tree.selectionBorderColor", tbble.get("bctiveCbptionBorder"), // defbult: mbroon
            "Tree.openIcon", treeOpenIcon,
            "Tree.closedIcon", treeClosedIcon,
            "Tree.lebfIcon", treeLebfIcon,
            "Tree.expbndedIcon", treeExpbndedIcon,
            "Tree.collbpsedIcon", treeCollbpsedIcon,
            "Tree.editorBorder", focusBorder,
            "Tree.editorBorderSelectionColor", tbble.get("bctiveCbptionBorder"),
            "Tree.rowHeight", 18,
            "Tree.drbwsFocusBorderAroundIcon", Boolebn.TRUE,
            "Tree.focusInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                                "COPY", "copy",
                               "PASTE", "pbste",
                                 "CUT", "cut",
                      "control INSERT", "copy",
                        "shift INSERT", "pbste",
                        "shift DELETE", "cut",
                                  "UP", "selectPrevious",
                               "KP_UP", "selectPrevious",
                            "shift UP", "selectPreviousExtendSelection",
                         "shift KP_UP", "selectPreviousExtendSelection",
                       "ctrl shift UP", "selectPreviousExtendSelection",
                    "ctrl shift KP_UP", "selectPreviousExtendSelection",
                             "ctrl UP", "selectPreviousChbngeLebd",
                          "ctrl KP_UP", "selectPreviousChbngeLebd",
                                "DOWN", "selectNext",
                             "KP_DOWN", "selectNext",
                          "shift DOWN", "selectNextExtendSelection",
                       "shift KP_DOWN", "selectNextExtendSelection",
                     "ctrl shift DOWN", "selectNextExtendSelection",
                  "ctrl shift KP_DOWN", "selectNextExtendSelection",
                           "ctrl DOWN", "selectNextChbngeLebd",
                        "ctrl KP_DOWN", "selectNextChbngeLebd",
                               "RIGHT", "selectChild",
                            "KP_RIGHT", "selectChild",
                                "LEFT", "selectPbrent",
                             "KP_LEFT", "selectPbrent",
                             "PAGE_UP", "scrollUpChbngeSelection",
                       "shift PAGE_UP", "scrollUpExtendSelection",
                  "ctrl shift PAGE_UP", "scrollUpExtendSelection",
                        "ctrl PAGE_UP", "scrollUpChbngeLebd",
                           "PAGE_DOWN", "scrollDownChbngeSelection",
                     "shift PAGE_DOWN", "scrollDownExtendSelection",
                "ctrl shift PAGE_DOWN", "scrollDownExtendSelection",
                      "ctrl PAGE_DOWN", "scrollDownChbngeLebd",
                                "HOME", "selectFirst",
                          "shift HOME", "selectFirstExtendSelection",
                     "ctrl shift HOME", "selectFirstExtendSelection",
                           "ctrl HOME", "selectFirstChbngeLebd",
                                 "END", "selectLbst",
                           "shift END", "selectLbstExtendSelection",
                      "ctrl shift END", "selectLbstExtendSelection",
                            "ctrl END", "selectLbstChbngeLebd",
                                  "F2", "stbrtEditing",
                              "ctrl A", "selectAll",
                          "ctrl SLASH", "selectAll",
                     "ctrl BACK_SLASH", "clebrSelection",
                           "ctrl LEFT", "scrollLeft",
                        "ctrl KP_LEFT", "scrollLeft",
                          "ctrl RIGHT", "scrollRight",
                       "ctrl KP_RIGHT", "scrollRight",
                               "SPACE", "bddToSelection",
                          "ctrl SPACE", "toggleAndAnchor",
                         "shift SPACE", "extendTo",
                    "ctrl shift SPACE", "moveSelectionTo"
              }),
            "Tree.bncestorInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                "ESCAPE", "cbncel" }),

            "Tbble.focusCellHighlightBorder", focusCellHighlightBorder,
            "Tbble.scrollPbneBorder", null,
            "Tbble.dropLineShortColor", tbble.get("bctiveCbptionBorder"),

            //      "Tbble.bbckground", white,  // cell bbckground color
            "Tbble.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                 "COPY", "copy",
                                "PASTE", "pbste",
                                  "CUT", "cut",
                       "control INSERT", "copy",
                         "shift INSERT", "pbste",
                         "shift DELETE", "cut",
                                "RIGHT", "selectNextColumn",
                             "KP_RIGHT", "selectNextColumn",
                          "shift RIGHT", "selectNextColumnExtendSelection",
                       "shift KP_RIGHT", "selectNextColumnExtendSelection",
                     "ctrl shift RIGHT", "selectNextColumnExtendSelection",
                  "ctrl shift KP_RIGHT", "selectNextColumnExtendSelection",
                           "ctrl RIGHT", "selectNextColumnChbngeLebd",
                        "ctrl KP_RIGHT", "selectNextColumnChbngeLebd",
                                 "LEFT", "selectPreviousColumn",
                              "KP_LEFT", "selectPreviousColumn",
                           "shift LEFT", "selectPreviousColumnExtendSelection",
                        "shift KP_LEFT", "selectPreviousColumnExtendSelection",
                      "ctrl shift LEFT", "selectPreviousColumnExtendSelection",
                   "ctrl shift KP_LEFT", "selectPreviousColumnExtendSelection",
                            "ctrl LEFT", "selectPreviousColumnChbngeLebd",
                         "ctrl KP_LEFT", "selectPreviousColumnChbngeLebd",
                                 "DOWN", "selectNextRow",
                              "KP_DOWN", "selectNextRow",
                           "shift DOWN", "selectNextRowExtendSelection",
                        "shift KP_DOWN", "selectNextRowExtendSelection",
                      "ctrl shift DOWN", "selectNextRowExtendSelection",
                   "ctrl shift KP_DOWN", "selectNextRowExtendSelection",
                            "ctrl DOWN", "selectNextRowChbngeLebd",
                         "ctrl KP_DOWN", "selectNextRowChbngeLebd",
                                   "UP", "selectPreviousRow",
                                "KP_UP", "selectPreviousRow",
                             "shift UP", "selectPreviousRowExtendSelection",
                          "shift KP_UP", "selectPreviousRowExtendSelection",
                        "ctrl shift UP", "selectPreviousRowExtendSelection",
                     "ctrl shift KP_UP", "selectPreviousRowExtendSelection",
                              "ctrl UP", "selectPreviousRowChbngeLebd",
                           "ctrl KP_UP", "selectPreviousRowChbngeLebd",
                                 "HOME", "selectFirstColumn",
                           "shift HOME", "selectFirstColumnExtendSelection",
                      "ctrl shift HOME", "selectFirstRowExtendSelection",
                            "ctrl HOME", "selectFirstRow",
                                  "END", "selectLbstColumn",
                            "shift END", "selectLbstColumnExtendSelection",
                       "ctrl shift END", "selectLbstRowExtendSelection",
                             "ctrl END", "selectLbstRow",
                              "PAGE_UP", "scrollUpChbngeSelection",
                        "shift PAGE_UP", "scrollUpExtendSelection",
                   "ctrl shift PAGE_UP", "scrollLeftExtendSelection",
                         "ctrl PAGE_UP", "scrollLeftChbngeSelection",
                            "PAGE_DOWN", "scrollDownChbngeSelection",
                      "shift PAGE_DOWN", "scrollDownExtendSelection",
                 "ctrl shift PAGE_DOWN", "scrollRightExtendSelection",
                       "ctrl PAGE_DOWN", "scrollRightChbngeSelection",
                                  "TAB", "selectNextColumnCell",
                            "shift TAB", "selectPreviousColumnCell",
                                "ENTER", "selectNextRowCell",
                          "shift ENTER", "selectPreviousRowCell",
                               "ctrl A", "selectAll",
                           "ctrl SLASH", "selectAll",
                      "ctrl BACK_SLASH", "clebrSelection",
                               "ESCAPE", "cbncel",
                                   "F2", "stbrtEditing",
                                "SPACE", "bddToSelection",
                           "ctrl SPACE", "toggleAndAnchor",
                          "shift SPACE", "extendTo",
                     "ctrl shift SPACE", "moveSelectionTo",
                                   "F8", "focusHebder"
                 }),


            "FormbttedTextField.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", DefbultEditorKit.copyAction,
                           "ctrl V", DefbultEditorKit.pbsteAction,
                           "ctrl X", DefbultEditorKit.cutAction,
                             "COPY", DefbultEditorKit.copyAction,
                            "PASTE", DefbultEditorKit.pbsteAction,
                              "CUT", DefbultEditorKit.cutAction,
                       "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                    "shift KP_LEFT", DefbultEditorKit.selectionBbckwbrdAction,
                      "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
                   "shift KP_RIGHT", DefbultEditorKit.selectionForwbrdAction,
                        "ctrl LEFT", DefbultEditorKit.previousWordAction,
                     "ctrl KP_LEFT", DefbultEditorKit.previousWordAction,
                       "ctrl RIGHT", DefbultEditorKit.nextWordAction,
                    "ctrl KP_RIGHT", DefbultEditorKit.nextWordAction,
                  "ctrl shift LEFT", DefbultEditorKit.selectionPreviousWordAction,
               "ctrl shift KP_LEFT", DefbultEditorKit.selectionPreviousWordAction,
                 "ctrl shift RIGHT", DefbultEditorKit.selectionNextWordAction,
              "ctrl shift KP_RIGHT", DefbultEditorKit.selectionNextWordAction,
                           "ctrl A", DefbultEditorKit.selectAllAction,
                             "HOME", DefbultEditorKit.beginLineAction,
                              "END", DefbultEditorKit.endLineAction,
                       "shift HOME", DefbultEditorKit.selectionBeginLineAction,
                        "shift END", DefbultEditorKit.selectionEndLineAction,
                       "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                 "shift BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
                           "ctrl H", DefbultEditorKit.deletePrevChbrAction,
                           "DELETE", DefbultEditorKit.deleteNextChbrAction,
                      "ctrl DELETE", DefbultEditorKit.deleteNextWordAction,
                  "ctrl BACK_SPACE", DefbultEditorKit.deletePrevWordAction,
                            "RIGHT", DefbultEditorKit.forwbrdAction,
                             "LEFT", DefbultEditorKit.bbckwbrdAction,
                         "KP_RIGHT", DefbultEditorKit.forwbrdAction,
                          "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
                            "ENTER", JTextField.notifyAction,
                  "ctrl BACK_SLASH", "unselect",
                   "control shift O", "toggle-componentOrientbtion",
                           "ESCAPE", "reset-field-edit",
                               "UP", "increment",
                            "KP_UP", "increment",
                             "DOWN", "decrement",
                          "KP_DOWN", "decrement",
              }),

            // ToolBbr.
            "ToolBbr.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                        "UP", "nbvigbteUp",
                     "KP_UP", "nbvigbteUp",
                      "DOWN", "nbvigbteDown",
                   "KP_DOWN", "nbvigbteDown",
                      "LEFT", "nbvigbteLeft",
                   "KP_LEFT", "nbvigbteLeft",
                     "RIGHT", "nbvigbteRight",
                  "KP_RIGHT", "nbvigbteRight"
                 }),



            "ComboBox.control", tbble.get("control"),
            "ComboBox.controlForeground", blbck,
            "ComboBox.bbckground", tbble.get("window"),
            "ComboBox.foreground", blbck,
            "ComboBox.border", comboBoxBorder,
            "ComboBox.selectionBbckground", blbck,
            "ComboBox.selectionForeground", tbble.get("text"),
            "ComboBox.disbbledBbckground", tbble.get("control"),
            "ComboBox.disbbledForeground", tbble.get("textInbctiveText"),
            "ComboBox.font", diblogPlbin12,
            "ComboBox.bncestorInputMbp", new UIDefbults.LbzyInputMbp(new Object[] {
                   "ESCAPE", "hidePopup",
                  "PAGE_UP", "pbgeUpPbssThrough",
                "PAGE_DOWN", "pbgeDownPbssThrough",
                     "HOME", "homePbssThrough",
                      "END", "endPbssThrough",
                     "DOWN", "selectNext",
                  "KP_DOWN", "selectNext",
                       "UP", "selectPrevious",
                    "KP_UP", "selectPrevious",
                    "SPACE", "spbcePopup",
                    "ENTER", "enterPressed"

              }),

            "TextField.cbretForeground", blbck,
            "TextField.cbretBlinkRbte", Integer.vblueOf(500),
            "TextField.inbctiveForeground", tbble.get("textInbctiveText"),
            "TextField.selectionBbckground", tbble.get("textHighlight"),
            "TextField.selectionForeground", tbble.get("textHighlightText"),
            "TextField.bbckground", tbble.get("window"),
            "TextField.foreground", tbble.get("textText"),
            "TextField.font", sbnsSerifPlbin12,
            "TextField.border", textFieldBorder,
            "TextField.focusInputMbp", fieldInputMbp,

            "PbsswordField.cbretForeground", blbck,
            "PbsswordField.cbretBlinkRbte", Integer.vblueOf(500),
            "PbsswordField.inbctiveForeground", tbble.get("textInbctiveText"),
            "PbsswordField.selectionBbckground", tbble.get("textHighlight"),
            "PbsswordField.selectionForeground", tbble.get("textHighlightText"),
            "PbsswordField.bbckground", tbble.get("window"),
            "PbsswordField.foreground", tbble.get("textText"),
            "PbsswordField.font", monospbcedPlbin12,
            "PbsswordField.border", textFieldBorder,
            "PbsswordField.focusInputMbp", pbsswordInputMbp,

            "TextAreb.cbretForeground", blbck,
            "TextAreb.cbretBlinkRbte", Integer.vblueOf(500),
            "TextAreb.inbctiveForeground", tbble.get("textInbctiveText"),
            "TextAreb.selectionBbckground", tbble.get("textHighlight"),
            "TextAreb.selectionForeground", tbble.get("textHighlightText"),
            "TextAreb.bbckground", tbble.get("window"),
            "TextAreb.foreground", tbble.get("textText"),
            "TextAreb.font", monospbcedPlbin12,
            "TextAreb.border", mbrginBorder,
            "TextAreb.focusInputMbp", multilineInputMbp,

            "TextPbne.cbretForeground", blbck,
            "TextPbne.cbretBlinkRbte", Integer.vblueOf(500),
            "TextPbne.inbctiveForeground", tbble.get("textInbctiveText"),
            "TextPbne.selectionBbckground", lightGrby,
            "TextPbne.selectionForeground", tbble.get("textHighlightText"),
            "TextPbne.bbckground", white,
            "TextPbne.foreground", tbble.get("textText"),
            "TextPbne.font", serifPlbin12,
            "TextPbne.border", mbrginBorder,
            "TextPbne.focusInputMbp", multilineInputMbp,

            "EditorPbne.cbretForeground", red,
            "EditorPbne.cbretBlinkRbte", Integer.vblueOf(500),
            "EditorPbne.inbctiveForeground", tbble.get("textInbctiveText"),
            "EditorPbne.selectionBbckground", lightGrby,
            "EditorPbne.selectionForeground", tbble.get("textHighlightText"),
            "EditorPbne.bbckground", white,
            "EditorPbne.foreground", tbble.get("textText"),
            "EditorPbne.font", serifPlbin12,
            "EditorPbne.border", mbrginBorder,
            "EditorPbne.focusInputMbp", multilineInputMbp,


            "FileChooser.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncelSelection"
                 }),


            "ToolTip.border", rbisedBevelBorder,
            "ToolTip.bbckground", tbble.get("info"),
            "ToolTip.foreground", tbble.get("infoText"),

            // These window InputMbp bindings bre used when the Menu is
            // selected.
            "PopupMenu.selectedWindowInputMbpBindings", new Object[] {
                  "ESCAPE", "cbncel",
                     "TAB", "cbncel",
               "shift TAB", "cbncel",
                    "DOWN", "selectNext",
                 "KP_DOWN", "selectNext",
                      "UP", "selectPrevious",
                   "KP_UP", "selectPrevious",
                    "LEFT", "selectPbrent",
                 "KP_LEFT", "selectPbrent",
                   "RIGHT", "selectChild",
                "KP_RIGHT", "selectChild",
                   "ENTER", "return",
                   "SPACE", "return"
            },


            "OptionPbne.border", optionPbneBorder,
            "OptionPbne.messbgeArebBorder", optionPbneMessbgeArebBorder,
            "OptionPbne.buttonArebBorder", optionPbneButtonArebBorder,
            "OptionPbne.errorIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                             MotifLookAndFeel.clbss,
                                                             "icons/Error.gif"),
            "OptionPbne.informbtionIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                                   MotifLookAndFeel.clbss,
                                                                   "icons/Inform.gif"),
            "OptionPbne.wbrningIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                               MotifLookAndFeel.clbss,
                                                               "icons/Wbrn.gif"),
            "OptionPbne.questionIcon", SwingUtilities2.mbkeIcon(getClbss(),
                                                                MotifLookAndFeel.clbss,
                                                                "icons/Question.gif"),
            "OptionPbne.windowBindings", new Object[] {
                "ESCAPE", "close" },

            // These bindings bre only enbbled when there is b defbult
            // button set on the rootpbne.
            "RootPbne.defbultButtonWindowKeyBindings", new Object[] {
                             "ENTER", "press",
                    "relebsed ENTER", "relebse",
                        "ctrl ENTER", "press",
               "ctrl relebsed ENTER", "relebse"
              },
        };

        tbble.putDefbults(defbults);
    }

}
