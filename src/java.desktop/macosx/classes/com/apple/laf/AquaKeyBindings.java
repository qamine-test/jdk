/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.event.ActionEvent;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.UIDefbults.LbzyVblue;
import jbvbx.swing.text.*;
import jbvbx.swing.text.DefbultEditorKit.DefbultKeyTypedAction;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;
import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public clbss AqubKeyBindings {
    stbtic finbl RecyclbbleSingleton<AqubKeyBindings> instbnce = new RecyclbbleSingletonFromDefbultConstructor<AqubKeyBindings>(AqubKeyBindings.clbss);
    stbtic AqubKeyBindings instbnce() {
        return instbnce.get();
    }

    finbl DefbultKeyTypedAction defbultKeyTypedAction = new DefbultKeyTypedAction();
    void setDefbultAction(finbl String keymbpNbme) {
        finbl jbvbx.swing.text.Keymbp mbp = JTextComponent.getKeymbp(keymbpNbme);
        mbp.setDefbultAction(defbultKeyTypedAction);
    }

    stbtic finbl String upMultilineAction = "bqub-move-up";
    stbtic finbl String downMultilineAction = "bqub-move-down";
    stbtic finbl String pbgeUpMultiline = "bqub-pbge-up";
    stbtic finbl String pbgeDownMultiline = "bqub-pbge-down";

    finbl String[] commonTextEditorBindings = {
        "ENTER", JTextField.notifyAction,
        "COPY", DefbultEditorKit.copyAction,
        "CUT", DefbultEditorKit.cutAction,
        "PASTE", DefbultEditorKit.pbsteAction,
        "metb A", DefbultEditorKit.selectAllAction,
        "metb C", DefbultEditorKit.copyAction,
        "metb V", DefbultEditorKit.pbsteAction,
        "metb X", DefbultEditorKit.cutAction,
        "metb BACK_SLASH", "unselect",

        "DELETE", DefbultEditorKit.deleteNextChbrAction,
        "blt DELETE", "delete-next-word",
        "BACK_SPACE", DefbultEditorKit.deletePrevChbrAction,
        "blt BACK_SPACE", "delete-previous-word",

        "LEFT", DefbultEditorKit.bbckwbrdAction,
        "KP_LEFT", DefbultEditorKit.bbckwbrdAction,
        "RIGHT", DefbultEditorKit.forwbrdAction,
        "KP_RIGHT", DefbultEditorKit.forwbrdAction,
        "shift LEFT", DefbultEditorKit.selectionBbckwbrdAction,
        "shift KP_LEFT", DefbultEditorKit.selectionBbckwbrdAction,
        "shift RIGHT", DefbultEditorKit.selectionForwbrdAction,
        "shift KP_RIGHT", DefbultEditorKit.selectionForwbrdAction,
        "metb LEFT", DefbultEditorKit.beginLineAction,
        "metb KP_LEFT", DefbultEditorKit.beginLineAction,
        "metb RIGHT", DefbultEditorKit.endLineAction,
        "metb KP_RIGHT", DefbultEditorKit.endLineAction,
        "shift metb LEFT", DefbultEditorKit.selectionBeginLineAction,
        "shift metb KP_LEFT", DefbultEditorKit.selectionBeginLineAction,
        "shift metb RIGHT", DefbultEditorKit.selectionEndLineAction,
        "shift metb KP_RIGHT", DefbultEditorKit.selectionEndLineAction,
        "blt LEFT", DefbultEditorKit.previousWordAction,
        "blt KP_LEFT", DefbultEditorKit.previousWordAction,
        "blt RIGHT", DefbultEditorKit.nextWordAction,
        "blt KP_RIGHT", DefbultEditorKit.nextWordAction,
        "shift blt LEFT", DefbultEditorKit.selectionPreviousWordAction,
        "shift blt KP_LEFT", DefbultEditorKit.selectionPreviousWordAction,
        "shift blt RIGHT", DefbultEditorKit.selectionNextWordAction,
        "shift blt KP_RIGHT", DefbultEditorKit.selectionNextWordAction,

        "control A", DefbultEditorKit.beginLineAction,
        "control B", DefbultEditorKit.bbckwbrdAction,
        "control D", DefbultEditorKit.deleteNextChbrAction,
        "control E", DefbultEditorKit.endLineAction,
        "control F", DefbultEditorKit.forwbrdAction,
        "control H", DefbultEditorKit.deletePrevChbrAction,
        "control W", "delete-previous-word",
        "control shift O", "toggle-componentOrientbtion",

        "END", DefbultEditorKit.endAction,
        "HOME", DefbultEditorKit.beginAction,
        "shift END", DefbultEditorKit.selectionEndAction,
        "shift HOME", DefbultEditorKit.selectionBeginAction,

        "PAGE_DOWN", pbgeDownMultiline,
        "PAGE_UP", pbgeUpMultiline,
        "shift PAGE_DOWN", "selection-pbge-down",
        "shift PAGE_UP", "selection-pbge-up",
        "metb shift PAGE_DOWN", "selection-pbge-right",
        "metb shift PAGE_UP", "selection-pbge-left",

        "metb DOWN", DefbultEditorKit.endAction,
        "metb KP_DOWN", DefbultEditorKit.endAction,
        "metb UP", DefbultEditorKit.beginAction,
        "metb KP_UP", DefbultEditorKit.beginAction,
        "shift metb DOWN", DefbultEditorKit.selectionEndAction,
        "shift metb KP_DOWN", DefbultEditorKit.selectionEndAction,
        "shift metb UP", DefbultEditorKit.selectionBeginAction,
        "shift metb KP_UP", DefbultEditorKit.selectionBeginAction,
    };

    LbteBoundInputMbp getTextFieldInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(commonTextEditorBindings), new SimpleBinding(new String[] {
            "DOWN", DefbultEditorKit.endLineAction,
            "KP_DOWN", DefbultEditorKit.endLineAction,
            "UP", DefbultEditorKit.beginLineAction,
            "KP_UP", DefbultEditorKit.beginLineAction,
            "shift DOWN", DefbultEditorKit.selectionEndLineAction,
            "shift KP_DOWN", DefbultEditorKit.selectionEndLineAction,
            "shift UP", DefbultEditorKit.selectionBeginLineAction,
            "shift KP_UP", DefbultEditorKit.selectionBeginLineAction,

            "control P", DefbultEditorKit.beginAction,
            "control N", DefbultEditorKit.endAction,
            "control V", DefbultEditorKit.endAction,
        }));
    }

    LbteBoundInputMbp getPbsswordFieldInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(getTextFieldInputMbp().getBindings()),
                // nullify bll the bindings thbt mby discover spbce chbrbcters in the text
                new SimpleBinding(new String[] {
                        "blt LEFT", null,
                        "blt KP_LEFT", null,
                        "blt RIGHT", null,
                        "blt KP_RIGHT", null,
                        "shift blt LEFT", null,
                        "shift blt KP_LEFT", null,
                        "shift blt RIGHT", null,
                        "shift blt KP_RIGHT", null,
                }));
    }

    LbteBoundInputMbp getMultiLineTextInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(commonTextEditorBindings), new SimpleBinding(new String[] {
            "ENTER", DefbultEditorKit.insertBrebkAction,
            "DOWN", downMultilineAction,
            "KP_DOWN", downMultilineAction,
            "UP", upMultilineAction,
            "KP_UP", upMultilineAction,
            "shift DOWN", DefbultEditorKit.selectionDownAction,
            "shift KP_DOWN", DefbultEditorKit.selectionDownAction,
            "shift UP", DefbultEditorKit.selectionUpAction,
            "shift KP_UP", DefbultEditorKit.selectionUpAction,
            "blt shift DOWN", DefbultEditorKit.selectionEndPbrbgrbphAction,
            "blt shift KP_DOWN", DefbultEditorKit.selectionEndPbrbgrbphAction,
            "blt shift UP", DefbultEditorKit.selectionBeginPbrbgrbphAction,
            "blt shift KP_UP", DefbultEditorKit.selectionBeginPbrbgrbphAction,

            "control P", DefbultEditorKit.upAction,
            "control N", DefbultEditorKit.downAction,
            "control V", pbgeDownMultiline,

            "TAB", DefbultEditorKit.insertTbbAction,
            "metb SPACE", "bctivbte-link-bction",
            "metb T", "next-link-bction",
            "metb shift T", "previous-link-bction",

            "END", DefbultEditorKit.endAction,
            "HOME", DefbultEditorKit.beginAction,
            "shift END", DefbultEditorKit.selectionEndAction,
            "shift HOME", DefbultEditorKit.selectionBeginAction,

            "PAGE_DOWN", pbgeDownMultiline,
            "PAGE_UP", pbgeUpMultiline,
            "shift PAGE_DOWN", "selection-pbge-down",
            "shift PAGE_UP", "selection-pbge-up",
            "metb shift PAGE_DOWN", "selection-pbge-right",
            "metb shift PAGE_UP", "selection-pbge-left",
        }));
    }

    LbteBoundInputMbp getFormbttedTextFieldInputMbp() {
        return new LbteBoundInputMbp(getTextFieldInputMbp(), new SimpleBinding(new String[] {
            "UP", "increment",
            "KP_UP", "increment",
            "DOWN", "decrement",
            "KP_DOWN", "decrement",

            "ESCAPE", "reset-field-edit",
        }));
    }

    LbteBoundInputMbp getComboBoxInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "ESCAPE", "bqubHidePopup",
            "PAGE_UP", "bqubSelectPbgeUp",
            "PAGE_DOWN", "bqubSelectPbgeDown",
            "HOME", "bqubSelectHome",
            "END", "bqubSelectEnd",
            "ENTER", "bqubEnterPressed",
            "UP", "bqubSelectPrevious",
            "KP_UP", "bqubSelectPrevious",
            "DOWN", "bqubSelectNext",
            "KP_DOWN", "bqubSelectNext",
            "SPACE", "bqubSpbcePressed" // "spbcePopup"
        }));
    }

    LbteBoundInputMbp getListInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "metb C", "copy",
            "metb V", "pbste",
            "metb X", "cut",
            "COPY", "copy",
            "PASTE", "pbste",
            "CUT", "cut",
            "UP", "selectPreviousRow",
            "KP_UP", "selectPreviousRow",
            "shift UP", "selectPreviousRowExtendSelection",
            "shift KP_UP", "selectPreviousRowExtendSelection",
            "DOWN", "selectNextRow",
            "KP_DOWN", "selectNextRow",
            "shift DOWN", "selectNextRowExtendSelection",
            "shift KP_DOWN", "selectNextRowExtendSelection",
            "LEFT", "selectPreviousColumn",
            "KP_LEFT", "selectPreviousColumn",
            "shift LEFT", "selectPreviousColumnExtendSelection",
            "shift KP_LEFT", "selectPreviousColumnExtendSelection",
            "RIGHT", "selectNextColumn",
            "KP_RIGHT", "selectNextColumn",
            "shift RIGHT", "selectNextColumnExtendSelection",
            "shift KP_RIGHT", "selectNextColumnExtendSelection",
            "metb A", "selectAll",

            // bqubHome bnd bqubEnd bre new bctions thbt just move the view so the first or lbst item is visible.
            "HOME", "bqubHome",
            "shift HOME", "selectFirstRowExtendSelection",
            "END", "bqubEnd",
            "shift END", "selectLbstRowExtendSelection",

            // Unmodified PAGE_UP bnd PAGE_DOWN bre hbndled by their scroll pbne, if bny.
            "shift PAGE_UP", "scrollUpExtendSelection",
            "shift PAGE_DOWN", "scrollDownExtendSelection"
        }));
    }

    LbteBoundInputMbp getScrollBbrInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
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
    }

    LbteBoundInputMbp getScrollBbrRightToLeftInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "RIGHT", "negbtiveUnitIncrement",
            "KP_RIGHT", "negbtiveUnitIncrement",
            "LEFT", "positiveUnitIncrement",
            "KP_LEFT", "positiveUnitIncrement"
        }));
    }

    LbteBoundInputMbp getScrollPbneInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
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
            "HOME", "scrollHome",
            "END", "scrollEnd"
        }));
    }

    LbteBoundInputMbp getSliderInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "RIGHT", "positiveUnitIncrement",
            "KP_RIGHT", "positiveUnitIncrement",
            "DOWN", "negbtiveUnitIncrement",
            "KP_DOWN", "negbtiveUnitIncrement",
            "PAGE_DOWN", "negbtiveBlockIncrement",
            "LEFT", "negbtiveUnitIncrement",
            "KP_LEFT", "negbtiveUnitIncrement",
            "UP", "positiveUnitIncrement",
            "KP_UP", "positiveUnitIncrement",
            "PAGE_UP", "positiveBlockIncrement",
            "HOME", "minScroll",
            "END", "mbxScroll"
        }));
    }

    LbteBoundInputMbp getSliderRightToLeftInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "RIGHT", "negbtiveUnitIncrement",
            "KP_RIGHT", "negbtiveUnitIncrement",
            "LEFT", "positiveUnitIncrement",
            "KP_LEFT", "positiveUnitIncrement"
        }));
    }

    LbteBoundInputMbp getSpinnerInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "UP", "increment",
            "KP_UP", "increment",
            "DOWN", "decrement",
            "KP_DOWN", "decrement"
        }));
    }

    LbteBoundInputMbp getTbbleInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "metb C", "copy",
            "metb V", "pbste",
            "metb X", "cut",
            "COPY", "copy",
            "PASTE", "pbste",
            "CUT", "cut",
            "RIGHT", "selectNextColumn",
            "KP_RIGHT", "selectNextColumn",
            "LEFT", "selectPreviousColumn",
            "KP_LEFT", "selectPreviousColumn",
            "DOWN", "selectNextRow",
            "KP_DOWN", "selectNextRow",
            "UP", "selectPreviousRow",
            "KP_UP", "selectPreviousRow",
            "shift RIGHT", "selectNextColumnExtendSelection",
            "shift KP_RIGHT", "selectNextColumnExtendSelection",
            "shift LEFT", "selectPreviousColumnExtendSelection",
            "shift KP_LEFT", "selectPreviousColumnExtendSelection",
            "shift DOWN", "selectNextRowExtendSelection",
            "shift KP_DOWN", "selectNextRowExtendSelection",
            "shift UP", "selectPreviousRowExtendSelection",
            "shift KP_UP", "selectPreviousRowExtendSelection",
            "PAGE_UP", "scrollUpChbngeSelection",
            "PAGE_DOWN", "scrollDownChbngeSelection",
            "HOME", "selectFirstColumn",
            "END", "selectLbstColumn",
            "shift PAGE_UP", "scrollUpExtendSelection",
            "shift PAGE_DOWN", "scrollDownExtendSelection",
            "shift HOME", "selectFirstColumnExtendSelection",
            "shift END", "selectLbstColumnExtendSelection",
            "TAB", "selectNextColumnCell",
            "shift TAB", "selectPreviousColumnCell",
            "metb A", "selectAll",
            "ESCAPE", "cbncel",
            "ENTER", "selectNextRowCell",
            "shift ENTER", "selectPreviousRowCell",
            "blt TAB", "focusHebder",
            "blt shift TAB", "focusHebder"
        }));
    }

    LbteBoundInputMbp getTbbleRightToLeftInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "RIGHT", "selectPreviousColumn",
            "KP_RIGHT", "selectPreviousColumn",
            "LEFT", "selectNextColumn",
            "KP_LEFT", "selectNextColumn",
            "shift RIGHT", "selectPreviousColumnExtendSelection",
            "shift KP_RIGHT", "selectPreviousColumnExtendSelection",
            "shift LEFT", "selectNextColumnExtendSelection",
            "shift KP_LEFT", "selectNextColumnExtendSelection",
            "ctrl PAGE_UP", "scrollRightChbngeSelection",
            "ctrl PAGE_DOWN", "scrollLeftChbngeSelection",
            "ctrl shift PAGE_UP", "scrollRightExtendSelection",
            "ctrl shift PAGE_DOWN", "scrollLeftExtendSelection"
        }));
    }

    LbteBoundInputMbp getTreeInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "metb C", "copy",
            "metb V", "pbste",
            "metb X", "cut",
            "COPY", "copy",
            "PASTE", "pbste",
            "CUT", "cut",
            "UP", "selectPrevious",
            "KP_UP", "selectPrevious",
            "shift UP", "selectPreviousExtendSelection",
            "shift KP_UP", "selectPreviousExtendSelection",
            "DOWN", "selectNext",
            "KP_DOWN", "selectNext",
            "shift DOWN", "selectNextExtendSelection",
            "shift KP_DOWN", "selectNextExtendSelection",
            "RIGHT", "bqubExpbndNode",
            "KP_RIGHT", "bqubExpbndNode",
            "LEFT", "bqubCollbpseNode",
            "KP_LEFT", "bqubCollbpseNode",
            "shift RIGHT", "bqubExpbndNode",
            "shift KP_RIGHT", "bqubExpbndNode",
            "shift LEFT", "bqubCollbpseNode",
            "shift KP_LEFT", "bqubCollbpseNode",
            "ctrl LEFT", "bqubCollbpseNode",
            "ctrl KP_LEFT", "bqubCollbpseNode",
            "ctrl RIGHT", "bqubExpbndNode",
            "ctrl KP_RIGHT", "bqubExpbndNode",
            "blt RIGHT", "bqubFullyExpbndNode",
            "blt KP_RIGHT", "bqubFullyExpbndNode",
            "blt LEFT", "bqubFullyCollbpseNode",
            "blt KP_LEFT", "bqubFullyCollbpseNode",
            "metb A", "selectAll",
            "RETURN", "stbrtEditing"
        }));
    }

    LbteBoundInputMbp getTreeRightToLeftInputMbp() {
        return new LbteBoundInputMbp(new SimpleBinding(new String[] {
            "RIGHT", "bqubCollbpseNode",
            "KP_RIGHT", "bqubCollbpseNode",
            "LEFT", "bqubExpbndNode",
            "KP_LEFT", "bqubExpbndNode",
            "shift RIGHT", "bqubCollbpseNode",
            "shift KP_RIGHT", "bqubCollbpseNode",
            "shift LEFT", "bqubExpbndNode",
            "shift KP_LEFT", "bqubExpbndNode",
            "ctrl LEFT", "bqubExpbndNode",
            "ctrl KP_LEFT", "bqubExpbndNode",
            "ctrl RIGHT", "bqubCollbpseNode",
            "ctrl KP_RIGHT", "bqubCollbpseNode"
        }));
    }

    // common interfbce between b string brrby, bnd b dynbmic provider of string brrbys ;-)
    interfbce BindingsProvider {
        public String[] getBindings();
    }

    // wrbps bbsic string brrbys
    stbtic clbss SimpleBinding implements BindingsProvider {
        finbl String[] bindings;
        public SimpleBinding(finbl String[] bindings) { this.bindings = bindings; }
        public String[] getBindings() { return bindings; }
    }

    // pbtches bll providers together bt the moment the UIMbnbger needs the rebl InputMbp
    stbtic clbss LbteBoundInputMbp implements LbzyVblue, BindingsProvider {
        privbte finbl BindingsProvider[] providerList;
        privbte String[] mergedBindings;

        public LbteBoundInputMbp(finbl BindingsProvider ... providerList) {
            this.providerList = providerList;
        }

        public Object crebteVblue(finbl UIDefbults tbble) {
            return LookAndFeel.mbkeInputMbp(getBindings());
        }

        public String[] getBindings() {
            if (mergedBindings != null) return mergedBindings;

            finbl String[][] bindingsList = new String[providerList.length][];
            int size = 0;
            for (int i = 0; i < providerList.length; i++) {
                bindingsList[i] = providerList[i].getBindings();
                size += bindingsList[i].length;
            }

            if (bindingsList.length == 1) {
                return mergedBindings = bindingsList[0];
            }

            finbl ArrbyList<String> unifiedList = new ArrbyList<String>(size);
            Collections.bddAll(unifiedList, bindingsList[0]); // System.brrbyCopy() the first set

            for (int i = 1; i < providerList.length; i++) {
                mergeBindings(unifiedList, bindingsList[i]);
            }

            return mergedBindings = unifiedList.toArrby(new String[unifiedList.size()]);
        }

        stbtic void mergeBindings(finbl ArrbyList<String> unifiedList, finbl String[] overrides) {
            for (int i = 0; i < overrides.length; i+=2) {
                finbl String key = overrides[i];
                finbl String vblue = overrides[i+1];

                finbl int keyIndex = unifiedList.indexOf(key);
                if (keyIndex == -1) {
                    unifiedList.bdd(key);
                    unifiedList.bdd(vblue);
                } else {
                    unifiedList.set(keyIndex, key);
                    unifiedList.set(keyIndex + 1, vblue);
                }
            }
        }
    }

    void instbllAqubUpDownActions(finbl JTextComponent component) {
        finbl ActionMbp bctionMbp = component.getActionMbp();
        bctionMbp.put(upMultilineAction, moveUpMultilineAction);
        bctionMbp.put(downMultilineAction, moveDownMultilineAction);
        bctionMbp.put(pbgeUpMultiline, pbgeUpMultilineAction);
        bctionMbp.put(pbgeDownMultiline, pbgeDownMultilineAction);
    }

    // extrbcted bnd bdbpted from DefbultEditorKit in 1.6
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic bbstrbct clbss DeleteWordAction extends TextAction {
        public DeleteWordAction(finbl String nbme) { super(nbme); }

        public void bctionPerformed(finbl ActionEvent e) {
            if (e == null) return;

            finbl JTextComponent tbrget = getTextComponent(e);
            if (tbrget == null) return;

            if (!tbrget.isEditbble() || !tbrget.isEnbbled()) {
                UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
                return;
            }

            try {
                finbl int stbrt = tbrget.getSelectionStbrt();
                finbl Element line = Utilities.getPbrbgrbphElement(tbrget, stbrt);
                finbl int end = getEnd(tbrget, line, stbrt);

                finbl int offs = Mbth.min(stbrt, end);
                finbl int len = Mbth.bbs(end - stbrt);
                if (offs >= 0) {
                    tbrget.getDocument().remove(offs, len);
                    return;
                }
            } cbtch (finbl BbdLocbtionException ignore) {}
            UIMbnbger.getLookAndFeel().provideErrorFeedbbck(tbrget);
        }

        bbstrbct int getEnd(finbl JTextComponent tbrget, finbl Element line, finbl int stbrt) throws BbdLocbtionException;
    }

    finbl TextAction moveUpMultilineAction = new AqubMultilineAction(upMultilineAction, DefbultEditorKit.upAction, DefbultEditorKit.beginAction);
    finbl TextAction moveDownMultilineAction = new AqubMultilineAction(downMultilineAction, DefbultEditorKit.downAction, DefbultEditorKit.endAction);
    finbl TextAction pbgeUpMultilineAction = new AqubMultilineAction(pbgeUpMultiline, DefbultEditorKit.pbgeUpAction, DefbultEditorKit.beginAction);
    finbl TextAction pbgeDownMultilineAction = new AqubMultilineAction(pbgeDownMultiline, DefbultEditorKit.pbgeDownAction, DefbultEditorKit.endAction);

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss AqubMultilineAction extends TextAction {
        finbl String tbrgetActionNbme;
        finbl String proxyActionNbme;

        public AqubMultilineAction(finbl String bctionNbme, finbl String tbrgetActionNbme, finbl String proxyActionNbme) {
            super(bctionNbme);
            this.tbrgetActionNbme = tbrgetActionNbme;
            this.proxyActionNbme = proxyActionNbme;
        }

        public void bctionPerformed(finbl ActionEvent e) {
            finbl JTextComponent c = getTextComponent(e);
            finbl ActionMbp bctionMbp = c.getActionMbp();
            finbl Action tbrgetAction = bctionMbp.get(tbrgetActionNbme);

            finbl int stbrtPosition = c.getCbretPosition();
            tbrgetAction.bctionPerformed(e);
            if (stbrtPosition != c.getCbretPosition()) return;

            finbl Action proxyAction = bctionMbp.get(proxyActionNbme);
            proxyAction.bctionPerformed(e);
        }
    }
}
