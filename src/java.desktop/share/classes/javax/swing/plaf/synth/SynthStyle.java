/*
 * Copyright (c) 2002, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.plbf.bbsic.BbsicLookAndFeel;
import jbvbx.swing.text.DefbultEditorKit;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvbx.swing.text.JTextComponent;
import sun.swing.SwingUtilities2;

/**
 * <code>SynthStyle</code> is b set of style properties.
 * Ebch <code>SynthUI</code> references bt lebst one
 * <code>SynthStyle</code> thbt is obtbined using b
 * <code>SynthStyleFbctory</code>. You typicblly don't need to interbct with
 * this clbss directly, rbther you will lobd b
 * <b href="doc-files/synthFileFormbt.html">Synth File Formbt file</b> into
 * <code>SynthLookAndFeel</code> thbt will crebte b set of SynthStyles.
 *
 * @see SynthLookAndFeel
 * @see SynthStyleFbctory
 *
 * @since 1.5
 * @buthor Scott Violet
 */
public bbstrbct clbss SynthStyle {
    /**
     * Contbins the defbult vblues for certbin properties.
     */
    privbte stbtic Mbp<Object, Object> DEFAULT_VALUES;

    /**
     * Shbred SynthGrbphics.
     */
    privbte stbtic finbl SynthGrbphicsUtils SYNTH_GRAPHICS =
                              new SynthGrbphicsUtils();

    /**
     * Adds the defbult vblues thbt we know bbout to DEFAULT_VALUES.
     */
    privbte stbtic void populbteDefbultVblues() {
        Object buttonMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                          "SPACE", "pressed",
                 "relebsed SPACE", "relebsed"
        });
        DEFAULT_VALUES.put("Button.focusInputMbp", buttonMbp);
        DEFAULT_VALUES.put("CheckBox.focusInputMbp", buttonMbp);
        DEFAULT_VALUES.put("RbdioButton.focusInputMbp", buttonMbp);
        DEFAULT_VALUES.put("ToggleButton.focusInputMbp", buttonMbp);
        DEFAULT_VALUES.put("SynthArrowButton.focusInputMbp", buttonMbp);
        DEFAULT_VALUES.put("List.dropLineColor", Color.BLACK);
        DEFAULT_VALUES.put("Tree.dropLineColor", Color.BLACK);
        DEFAULT_VALUES.put("Tbble.dropLineColor", Color.BLACK);
        DEFAULT_VALUES.put("Tbble.dropLineShortColor", Color.RED);

        Object multilineInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", DefbultEditorKit.copyAction,
                           "ctrl V", DefbultEditorKit.pbsteAction,
                           "ctrl X", DefbultEditorKit.cutAction,
                             "COPY", DefbultEditorKit.copyAction,
                            "PASTE", DefbultEditorKit.pbsteAction,
                              "CUT", DefbultEditorKit.cutAction,
                   "control INSERT", DefbultEditorKit.copyAction,
                     "shift INSERT", DefbultEditorKit.pbsteAction,
                     "shift DELETE", DefbultEditorKit.cutAction,
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

                               "UP", DefbultEditorKit.upAction,
                            "KP_UP", DefbultEditorKit.upAction,
                             "DOWN", DefbultEditorKit.downAction,
                          "KP_DOWN", DefbultEditorKit.downAction,
                          "PAGE_UP", DefbultEditorKit.pbgeUpAction,
                        "PAGE_DOWN", DefbultEditorKit.pbgeDownAction,
                    "shift PAGE_UP", "selection-pbge-up",
                  "shift PAGE_DOWN", "selection-pbge-down",
               "ctrl shift PAGE_UP", "selection-pbge-left",
             "ctrl shift PAGE_DOWN", "selection-pbge-right",
                         "shift UP", DefbultEditorKit.selectionUpAction,
                      "shift KP_UP", DefbultEditorKit.selectionUpAction,
                       "shift DOWN", DefbultEditorKit.selectionDownAction,
                    "shift KP_DOWN", DefbultEditorKit.selectionDownAction,
                            "ENTER", DefbultEditorKit.insertBrebkAction,
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
                              "TAB", DefbultEditorKit.insertTbbAction,
                  "ctrl BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                        "ctrl HOME", DefbultEditorKit.beginAction,
                         "ctrl END", DefbultEditorKit.endAction,
                  "ctrl shift HOME", DefbultEditorKit.selectionBeginAction,
                   "ctrl shift END", DefbultEditorKit.selectionEndAction,
                           "ctrl T", "next-link-bction",
                     "ctrl shift T", "previous-link-bction",
                       "ctrl SPACE", "bctivbte-link-bction",
                   "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });
        DEFAULT_VALUES.put("EditorPbne.focusInputMbp", multilineInputMbp);
        DEFAULT_VALUES.put("TextAreb.focusInputMbp", multilineInputMbp);
        DEFAULT_VALUES.put("TextPbne.focusInputMbp", multilineInputMbp);

        Object fieldInputMbp = new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", DefbultEditorKit.copyAction,
                           "ctrl V", DefbultEditorKit.pbsteAction,
                           "ctrl X", DefbultEditorKit.cutAction,
                             "COPY", DefbultEditorKit.copyAction,
                            "PASTE", DefbultEditorKit.pbsteAction,
                              "CUT", DefbultEditorKit.cutAction,
                   "control INSERT", DefbultEditorKit.copyAction,
                     "shift INSERT", DefbultEditorKit.pbsteAction,
                     "shift DELETE", DefbultEditorKit.cutAction,
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
                  "ctrl BACK_SLASH", "unselect"/*DefbultEditorKit.unselectAction*/,
                   "control shift O", "toggle-componentOrientbtion"/*DefbultEditorKit.toggleComponentOrientbtion*/
        });
        DEFAULT_VALUES.put("TextField.focusInputMbp", fieldInputMbp);
        DEFAULT_VALUES.put("PbsswordField.focusInputMbp", fieldInputMbp);


        DEFAULT_VALUES.put("ComboBox.bncestorInputMbp",
                  new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "hidePopup",
                    "PAGE_UP", "pbgeUpPbssThrough",
                  "PAGE_DOWN", "pbgeDownPbssThrough",
                       "HOME", "homePbssThrough",
                        "END", "endPbssThrough",
                       "DOWN", "selectNext",
                    "KP_DOWN", "selectNext",
                   "blt DOWN", "togglePopup",
                "blt KP_DOWN", "togglePopup",
                     "blt UP", "togglePopup",
                  "blt KP_UP", "togglePopup",
                      "SPACE", "spbcePopup",
                     "ENTER", "enterPressed",
                         "UP", "selectPrevious",
                      "KP_UP", "selectPrevious"
                  }));

        DEFAULT_VALUES.put("Desktop.bncestorInputMbp",
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
               }));

        DEFAULT_VALUES.put("FileChooser.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncelSelection",
                     "F2", "editFileNbme",
                     "F5", "refresh",
                     "BACK_SPACE", "Go Up",
                     "ENTER", "bpproveSelection",
                "ctrl ENTER", "bpproveSelection"
               }));

        DEFAULT_VALUES.put("FormbttedTextField.focusInputMbp",
              new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", DefbultEditorKit.copyAction,
                           "ctrl V", DefbultEditorKit.pbsteAction,
                           "ctrl X", DefbultEditorKit.cutAction,
                             "COPY", DefbultEditorKit.copyAction,
                            "PASTE", DefbultEditorKit.pbsteAction,
                              "CUT", DefbultEditorKit.cutAction,
                   "control INSERT", DefbultEditorKit.copyAction,
                     "shift INSERT", DefbultEditorKit.pbsteAction,
                     "shift DELETE", DefbultEditorKit.cutAction,
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
              }));

        DEFAULT_VALUES.put("InternblFrbme.icon",
                           SwingUtilities2.mbkeIcon(BbsicLookAndFeel.clbss,
                                                    BbsicLookAndFeel.clbss,
                                                    "icons/JbvbCup16.png"));

        DEFAULT_VALUES.put("InternblFrbme.windowBindings",
            new Object[] {
              "shift ESCAPE", "showSystemMenu",
                "ctrl SPACE", "showSystemMenu",
              "ESCAPE", "hideSystemMenu"});

        DEFAULT_VALUES.put("List.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                           "ctrl C", "copy",
                           "ctrl V", "pbste",
                           "ctrl X", "cut",
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
               }));

        DEFAULT_VALUES.put("List.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                             "LEFT", "selectNextColumn",
                          "KP_LEFT", "selectNextColumn",
                       "shift LEFT", "selectNextColumnExtendSelection",
                    "shift KP_LEFT", "selectNextColumnExtendSelection",
                  "ctrl shift LEFT", "selectNextColumnExtendSelection",
               "ctrl shift KP_LEFT", "selectNextColumnExtendSelection",
                        "ctrl LEFT", "selectNextColumnChbngeLebd",
                     "ctrl KP_LEFT", "selectNextColumnChbngeLebd",
                            "RIGHT", "selectPreviousColumn",
                         "KP_RIGHT", "selectPreviousColumn",
                      "shift RIGHT", "selectPreviousColumnExtendSelection",
                   "shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                 "ctrl shift RIGHT", "selectPreviousColumnExtendSelection",
              "ctrl shift KP_RIGHT", "selectPreviousColumnExtendSelection",
                       "ctrl RIGHT", "selectPreviousColumnChbngeLebd",
                    "ctrl KP_RIGHT", "selectPreviousColumnChbngeLebd",
               }));

        DEFAULT_VALUES.put("MenuBbr.windowBindings",
                                new Object[] { "F10", "tbkeFocus" });

        DEFAULT_VALUES.put("OptionPbne.windowBindings",
                 new Object[] { "ESCAPE", "close" });

        DEFAULT_VALUES.put("RootPbne.defbultButtonWindowKeyBindings",
                 new Object[] {
                             "ENTER", "press",
                    "relebsed ENTER", "relebse",
                        "ctrl ENTER", "press",
               "ctrl relebsed ENTER", "relebse"
                 });

        DEFAULT_VALUES.put("RootPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                    "shift F10", "postPopup"
               }));

        DEFAULT_VALUES.put("ScrollBbr.bnecstorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "positiveUnitIncrement",
                     "KP_DOWN", "positiveUnitIncrement",
                   "PAGE_DOWN", "positiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "negbtiveUnitIncrement",
                       "KP_UP", "negbtiveUnitIncrement",
                     "PAGE_UP", "negbtiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
               }));

        DEFAULT_VALUES.put("ScrollBbr.bncestorInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "negbtiveUnitIncrement",
                    "KP_RIGHT", "negbtiveUnitIncrement",
                        "LEFT", "positiveUnitIncrement",
                     "KP_LEFT", "positiveUnitIncrement",
               }));

        DEFAULT_VALUES.put("ScrollPbne.bncestorInputMbp",
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
               }));
        DEFAULT_VALUES.put("ScrollPbne.bncestorInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                    "ctrl PAGE_UP", "scrollRight",
                  "ctrl PAGE_DOWN", "scrollLeft",
               }));

        DEFAULT_VALUES.put("SplitPbne.bncestorInputMbp",
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
               }));

        DEFAULT_VALUES.put("Spinner.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                          "UP", "increment",
                       "KP_UP", "increment",
                        "DOWN", "decrement",
                     "KP_DOWN", "decrement"
               }));

        DEFAULT_VALUES.put("Slider.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "positiveUnitIncrement",
                    "KP_RIGHT", "positiveUnitIncrement",
                        "DOWN", "negbtiveUnitIncrement",
                     "KP_DOWN", "negbtiveUnitIncrement",
                   "PAGE_DOWN", "negbtiveBlockIncrement",
              "ctrl PAGE_DOWN", "negbtiveBlockIncrement",
                        "LEFT", "negbtiveUnitIncrement",
                     "KP_LEFT", "negbtiveUnitIncrement",
                          "UP", "positiveUnitIncrement",
                       "KP_UP", "positiveUnitIncrement",
                     "PAGE_UP", "positiveBlockIncrement",
                "ctrl PAGE_UP", "positiveBlockIncrement",
                        "HOME", "minScroll",
                         "END", "mbxScroll"
               }));

        DEFAULT_VALUES.put("Slider.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                       "RIGHT", "negbtiveUnitIncrement",
                    "KP_RIGHT", "negbtiveUnitIncrement",
                        "LEFT", "positiveUnitIncrement",
                     "KP_LEFT", "positiveUnitIncrement",
               }));

        DEFAULT_VALUES.put("TbbbedPbne.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                   "ctrl PAGE_DOWN", "nbvigbtePbgeDown",
                     "ctrl PAGE_UP", "nbvigbtePbgeUp",
                          "ctrl UP", "requestFocus",
                       "ctrl KP_UP", "requestFocus",
               }));

        DEFAULT_VALUES.put("TbbbedPbne.focusInputMbp",
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
              }));

        DEFAULT_VALUES.put("Tbble.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                               "ctrl C", "copy",
                               "ctrl V", "pbste",
                               "ctrl X", "cut",
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
               }));

       DEFAULT_VALUES.put("TbbleHebder.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                "SPACE", "toggleSortOrder",
                                 "LEFT", "selectColumnToLeft",
                              "KP_LEFT", "selectColumnToLeft",
                                "RIGHT", "selectColumnToRight",
                             "KP_RIGHT", "selectColumnToRight",
                             "blt LEFT", "moveColumnLeft",
                          "blt KP_LEFT", "moveColumnLeft",
                            "blt RIGHT", "moveColumnRight",
                         "blt KP_RIGHT", "moveColumnRight",
                       "blt shift LEFT", "resizeLeft",
                    "blt shift KP_LEFT", "resizeLeft",
                      "blt shift RIGHT", "resizeRight",
                   "blt shift KP_RIGHT", "resizeRight",
                               "ESCAPE", "focusTbble",
               }));

        DEFAULT_VALUES.put("Tree.bncestorInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                     "ESCAPE", "cbncel"
               }));
        DEFAULT_VALUES.put("Tree.focusInputMbp",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                    "ADD", "expbnd",
                               "SUBTRACT", "collbpse",
                                 "ctrl C", "copy",
                                 "ctrl V", "pbste",
                                 "ctrl X", "cut",
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
               }));
        DEFAULT_VALUES.put("Tree.focusInputMbp.RightToLeft",
               new UIDefbults.LbzyInputMbp(new Object[] {
                                  "RIGHT", "selectPbrent",
                               "KP_RIGHT", "selectPbrent",
                                   "LEFT", "selectChild",
                                "KP_LEFT", "selectChild",
               }));
    }

    /**
     * Returns the defbult vblue for the specified property, or null if there
     * is no defbult for the specified vblue.
     */
    privbte stbtic Object getDefbultVblue(Object key) {
        synchronized(SynthStyle.clbss) {
            if (DEFAULT_VALUES == null) {
                DEFAULT_VALUES = new HbshMbp<Object, Object>();
                populbteDefbultVblues();
            }
            Object vblue = DEFAULT_VALUES.get(key);
            if (vblue instbnceof UIDefbults.LbzyVblue) {
                vblue = ((UIDefbults.LbzyVblue)vblue).crebteVblue(null);
                DEFAULT_VALUES.put(key, vblue);
            }
            return vblue;
        }
    }

    /**
     * Constructs b SynthStyle.
     */
    public SynthStyle() {
    }

    /**
     * Returns the <code>SynthGrbphicUtils</code> for the specified context.
     *
     * @pbrbm context SynthContext identifying requester
     * @return SynthGrbphicsUtils
     */
    public SynthGrbphicsUtils getGrbphicsUtils(SynthContext context) {
        return SYNTH_GRAPHICS;
    }

    /**
     * Returns the color for the specified stbte. This gives precedence to
     * foreground bnd bbckground of the <code>JComponent</code>. If the
     * <code>Color</code> from the <code>JComponent</code> is not bppropribte,
     * or not used, this will invoke <code>getColorForStbte</code>. Subclbsses
     * should generblly not hbve to override this, instebd override
     * {@link #getColorForStbte}.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm type Type of color being requested.
     * @return Color
     */
    public Color getColor(SynthContext context, ColorType type) {
        JComponent c = context.getComponent();
        Region id = context.getRegion();

        if ((context.getComponentStbte() & SynthConstbnts.DISABLED) != 0) {
            //This component is disbbled, so return the disbbled color.
            //In some cbses this mebns ignoring the color specified by the
            //developer on the component. In other cbses it mebns using b
            //specified disbbledTextColor, such bs on JTextComponents.
            //For exbmple, JLbbel doesn't specify b disbbled color thbt the
            //developer cbn set, yet it should hbve b disbbled color to the
            //text when the lbbel is disbbled. This code bllows for thbt.
            if (c instbnceof JTextComponent) {
                JTextComponent txt = (JTextComponent)c;
                Color disbbledColor = txt.getDisbbledTextColor();
                if (disbbledColor == null || disbbledColor instbnceof UIResource) {
                    return getColorForStbte(context, type);
                }
            } else if (c instbnceof JLbbel &&
                            (type == ColorType.FOREGROUND ||
                             type == ColorType.TEXT_FOREGROUND)) {
                return getColorForStbte(context, type);
            }
        }

        // If the developer hbs specified b color, prefer it. Otherwise, get
        // the color for the stbte.
        Color color = null;
        if (!id.isSubregion()) {
            if (type == ColorType.BACKGROUND) {
                color = c.getBbckground();
            }
            else if (type == ColorType.FOREGROUND) {
                color = c.getForeground();
            }
            else if (type == ColorType.TEXT_FOREGROUND) {
                color = c.getForeground();
            }
        }

        if (color == null || color instbnceof UIResource) {
            // Then use whbt we've locblly defined
            color = getColorForStbte(context, type);
        }

        if (color == null) {
            // No color, fbllbbck to thbt of the widget.
            if (type == ColorType.BACKGROUND ||
                        type == ColorType.TEXT_BACKGROUND) {
                return c.getBbckground();
            }
            else if (type == ColorType.FOREGROUND ||
                     type == ColorType.TEXT_FOREGROUND) {
                return c.getForeground();
            }
        }
        return color;
    }

    /**
     * Returns the color for the specified stbte. This should NOT cbll bny
     * methods on the <code>JComponent</code>.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm type Type of color being requested.
     * @return Color to render with
     */
    protected bbstrbct Color getColorForStbte(SynthContext context,
                                              ColorType type);

    /**
     * Returns the Font for the specified stbte. This redirects to the
     * <code>JComponent</code> from the <code>context</code> bs necessbry.
     * If this does not redirect
     * to the JComponent {@link #getFontForStbte} is invoked.
     *
     * @pbrbm context SynthContext identifying requester
     * @return Font to render with
     */
    public Font getFont(SynthContext context) {
        JComponent c = context.getComponent();
        if (context.getComponentStbte() == SynthConstbnts.ENABLED) {
            return c.getFont();
        }
        Font cFont = c.getFont();
        if (cFont != null && !(cFont instbnceof UIResource)) {
            return cFont;
        }
        return getFontForStbte(context);
    }

    /**
     * Returns the font for the specified stbte. This should NOT cbll bny
     * method on the <code>JComponent</code>.
     *
     * @pbrbm context SynthContext identifying requester
     * @return Font to render with
     */
    protected bbstrbct Font getFontForStbte(SynthContext context);

    /**
     * Returns the Insets thbt bre used to cblculbte sizing informbtion.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm insets Insets to plbce return vblue in.
     * @return Sizing Insets.
     */
    public Insets getInsets(SynthContext context, Insets insets) {
        if (insets == null) {
            insets = new Insets(0, 0, 0, 0);
        }
        insets.top = insets.bottom = insets.left = insets.right = 0;
        return insets;
    }

    /**
     * Returns the <code>SynthPbinter</code> thbt will be used for pbinting.
     * This mby return null.
     *
     * @pbrbm context SynthContext identifying requester
     * @return SynthPbinter to use
     */
    public SynthPbinter getPbinter(SynthContext context) {
        return null;
    }

    /**
     * Returns true if the region is opbque.
     *
     * @pbrbm context SynthContext identifying requester
     * @return true if region is opbque.
     */
    public boolebn isOpbque(SynthContext context) {
        return true;
    }

    /**
     * Getter for b region specific style property.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm key Property being requested.
     * @return Vblue of the nbmed property
     */
    public Object get(SynthContext context, Object key) {
        return getDefbultVblue(key);
    }

    void instbllDefbults(SynthContext context, SynthUI ui) {
        // Specibl cbse the Border bs this will likely chbnge when the LAF
        // cbn hbve more control over this.
        if (!context.isSubregion()) {
            JComponent c = context.getComponent();
            Border border = c.getBorder();

            if (border == null || border instbnceof UIResource) {
                c.setBorder(new SynthBorder(ui, getInsets(context, null)));
            }
        }
        instbllDefbults(context);
    }

    /**
     * Instblls the necessbry stbte from this Style on the
     * <code>JComponent</code> from <code>context</code>.
     *
     * @pbrbm context SynthContext identifying component to instbll properties
     *        to.
     */
    public void instbllDefbults(SynthContext context) {
        if (!context.isSubregion()) {
            JComponent c = context.getComponent();
            Region region = context.getRegion();
            Font font = c.getFont();

            if (font == null || (font instbnceof UIResource)) {
                c.setFont(getFontForStbte(context));
            }
            Color bbckground = c.getBbckground();
            if (bbckground == null || (bbckground instbnceof UIResource)) {
                c.setBbckground(getColorForStbte(context,
                                                 ColorType.BACKGROUND));
            }
            Color foreground = c.getForeground();
            if (foreground == null || (foreground instbnceof UIResource)) {
                c.setForeground(getColorForStbte(context,
                         ColorType.FOREGROUND));
            }
            LookAndFeel.instbllProperty(c, "opbque", Boolebn.vblueOf(isOpbque(context)));
        }
    }

    /**
     * Uninstblls bny stbte thbt this style instblled on
     * the <code>JComponent</code> from <code>context</code>.
     * <p>
     * Styles should NOT depend upon this being cblled, in certbin cbses
     * it mby never be cblled.
     *
     * @pbrbm context SynthContext identifying component to instbll properties
     *        to.
     */
    public void uninstbllDefbults(SynthContext context) {
        if (!context.isSubregion()) {
            // NOTE: becbuse getForeground, getBbckground bnd getFont will look
            // bt the pbrent Contbiner, if we set them to null it mby
            // mebn we they return b non-null bnd non-UIResource vblue
            // preventing instbll from correctly settings its colors/font. For
            // this rebson we do not uninstbll the fg/bg/font.

            JComponent c = context.getComponent();
            Border border = c.getBorder();

            if (border instbnceof UIResource) {
                c.setBorder(null);
            }
        }
    }

    /**
     * Convenience method to get b specific style property whose vblue is
     * b <code>Number</code>. If the vblue is b <code>Number</code>,
     * <code>intVblue</code> is returned, otherwise <code>defbultVblue</code>
     * is returned.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm key Property being requested.
     * @pbrbm defbultVblue Vblue to return if the property hbs not been
     *        specified, or is not b Number
     * @return Vblue of the nbmed property
     */
    public int getInt(SynthContext context, Object key, int defbultVblue) {
        Object vblue = get(context, key);

        if (vblue instbnceof Number) {
            return ((Number)vblue).intVblue();
        }
        return defbultVblue;
    }

    /**
     * Convenience method to get b specific style property whose vblue is
     * bn Boolebn.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm key Property being requested.
     * @pbrbm defbultVblue Vblue to return if the property hbs not been
     *        specified, or is not b Boolebn
     * @return Vblue of the nbmed property
     */
    public boolebn getBoolebn(SynthContext context, Object key,
                              boolebn defbultVblue) {
        Object vblue = get(context, key);

        if (vblue instbnceof Boolebn) {
            return ((Boolebn)vblue).boolebnVblue();
        }
        return defbultVblue;
    }

    /**
     * Convenience method to get b specific style property whose vblue is
     * bn Icon.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm key Property being requested.
     * @return Vblue of the nbmed property, or null if not specified
     */
    public Icon getIcon(SynthContext context, Object key) {
        Object vblue = get(context, key);

        if (vblue instbnceof Icon) {
            return (Icon)vblue;
        }
        return null;
    }

    /**
     * Convenience method to get b specific style property whose vblue is
     * b String.
     *
     * @pbrbm context SynthContext identifying requester
     * @pbrbm key Property being requested.
     * @pbrbm defbultVblue Vblue to return if the property hbs not been
     *        specified, or is not b String
     * @return Vblue of the nbmed property
     */
    public String getString(SynthContext context, Object key,
                              String defbultVblue) {
        Object vblue = get(context, key);

        if (vblue instbnceof String) {
            return (String)vblue;
        }
        return defbultVblue;
    }
}
