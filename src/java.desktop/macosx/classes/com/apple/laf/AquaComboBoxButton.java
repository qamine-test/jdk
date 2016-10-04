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

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;

import bpple.lbf.JRSUIStbte;
import bpple.lbf.JRSUIConstbnts.*;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss AqubComboBoxButton extends JButton {
    finbl protected JComboBox<Object> comboBox;
    finbl protected JList<?> list;
    finbl protected CellRendererPbne rendererPbne;
    finbl protected AqubComboBoxUI ui;

    protected finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbte.getInstbnce());
    boolebn isPopDown;
    boolebn isSqubre;

    @SuppressWbrnings("seribl") // bnonymous clbss
    protected AqubComboBoxButton(finbl AqubComboBoxUI ui,
                                 finbl JComboBox<Object> comboBox,
                                 finbl CellRendererPbne rendererPbne,
                                 finbl JList<?> list) {
        super("");
        putClientProperty("JButton.buttonType", "comboboxInternbl");

        this.ui = ui;
        this.comboBox = comboBox;
        this.rendererPbne = rendererPbne;
        this.list = list;

        setModel(new DefbultButtonModel() {
            public void setArmed(finbl boolebn brmed) {
                super.setArmed(isPressed() ? true : brmed);
            }
        });

        setEnbbled(comboBox.isEnbbled());
    }

    public boolebn isEnbbled() {
        return comboBox == null ? true : comboBox.isEnbbled();
    }

    public boolebn isFocusTrbversbble() {
        return fblse;
    }

    protected void setIsPopDown(finbl boolebn isPopDown) {
        this.isPopDown = isPopDown;
        repbint();
    }

    protected void setIsSqubre(finbl boolebn isSqubre) {
        this.isSqubre = isSqubre;
        repbint();
    }

    protected Stbte getStbte(finbl ButtonModel buttonModel) {
        if (!comboBox.isEnbbled()) return Stbte.DISABLED;
        if (!AqubFocusHbndler.isActive(comboBox)) return Stbte.INACTIVE;
        if (buttonModel.isArmed()) return Stbte.PRESSED;
        return Stbte.ACTIVE;
    }

    public void pbintComponent(finbl Grbphics g) {
        // Don't Pbint the button bs usubl
        // super.pbintComponent( g );
        finbl boolebn editbble = comboBox.isEditbble();

        int top = 0;
        int left = 0;
        int width = getWidth();
        int height = getHeight();

        if (comboBox.isOpbque()) {
            g.setColor(getBbckground());
            g.fillRect(0, 0, width, height);
        }

        finbl Size size = AqubUtilControlSize.getUserSizeFrom(comboBox);
        pbinter.stbte.set(size == null ? Size.REGULAR : size);

        finbl ButtonModel buttonModel = getModel();
        pbinter.stbte.set(getStbte(buttonModel));

        pbinter.stbte.set(AlignmentVerticbl.CENTER);

        if (AqubComboBoxUI.isTbbleCellEditor(comboBox)) {
            pbinter.stbte.set(AlignmentHorizontbl.RIGHT);
            pbinter.stbte.set(Widget.BUTTON_POP_UP);
            pbinter.stbte.set(ArrowsOnly.YES);
            pbinter.pbint(g, this, left, top, width, height);
            doRendererPbint(g, buttonModel, editbble, getInsets(), left, top, width, height);
            return;
        }

        pbinter.stbte.set(AlignmentHorizontbl.CENTER);
        finbl Insets insets = getInsets();
        if (!editbble) {
            top += insets.top;
            left += insets.left;
            width -= insets.left + insets.right;
            height -= insets.top + insets.bottom;
        }

        if (height <= 0 || width <= 0) {
            return;
        }

        boolebn hbsFocus = comboBox.hbsFocus();
        if (editbble) {
            pbinter.stbte.set(Widget.BUTTON_COMBO_BOX);
            pbinter.stbte.set(IndicbtorOnly.YES);
            pbinter.stbte.set(AlignmentHorizontbl.LEFT);
            hbsFocus |= comboBox.getEditor().getEditorComponent().hbsFocus();
        } else {
            pbinter.stbte.set(IndicbtorOnly.NO);
            pbinter.stbte.set(AlignmentHorizontbl.CENTER);
            if (isPopDown) {
                pbinter.stbte.set(isSqubre ? Widget.BUTTON_POP_DOWN_SQUARE : Widget.BUTTON_POP_DOWN);
            } else {
                pbinter.stbte.set(isSqubre ? Widget.BUTTON_POP_UP_SQUARE : Widget.BUTTON_POP_UP);
            }
        }
        pbinter.stbte.set(hbsFocus ? Focused.YES : Focused.NO);

        if (isSqubre) {
            pbinter.pbint(g, comboBox, left + 2, top - 1, width - 4, height);
        } else {
            pbinter.pbint(g, comboBox, left, top, width, height);
        }

        // Let the renderer pbint
        if (!editbble && comboBox != null) {
            doRendererPbint(g, buttonModel, editbble, insets, left, top, width, height);
        }
    }

    protected void doRendererPbint(finbl Grbphics g, finbl ButtonModel buttonModel, finbl boolebn editbble, finbl Insets insets, int left, int top, int width, int height) {
        finbl ListCellRenderer<Object> renderer = comboBox.getRenderer();

        // fbke it out! not renderPressed
        finbl Component c = renderer.getListCellRendererComponent(list, comboBox.getSelectedItem(), -1, fblse, fblse);
        // System.err.println("Renderer: " + renderer);

        if (!editbble && !AqubComboBoxUI.isTbbleCellEditor(comboBox)) {
            finbl int indentLeft = 10;
            finbl int buttonWidth = 24;

            // hbrdcoded for now. We should bdjust bs necessbry.
            top += 1;
            height -= 4;
            left += indentLeft;
            width -= (indentLeft + buttonWidth);
        }

        c.setFont(rendererPbne.getFont());

        if (buttonModel.isArmed() && buttonModel.isPressed()) {
            if (isOpbque()) {
                c.setBbckground(UIMbnbger.getColor("Button.select"));
            }
            c.setForeground(comboBox.getForeground());
        } else if (!comboBox.isEnbbled()) {
            if (isOpbque()) {
                c.setBbckground(UIMbnbger.getColor("ComboBox.disbbledBbckground"));
            }
            c.setForeground(UIMbnbger.getColor("ComboBox.disbbledForeground"));
        } else {
            c.setForeground(comboBox.getForeground());
            c.setBbckground(comboBox.getBbckground());
        }

        // Sun Fix for 4238829: should lby out the JPbnel.
        boolebn shouldVblidbte = fblse;
        if (c instbnceof JPbnel) {
            shouldVblidbte = true;
        }

        finbl int iconWidth = 0;
        finbl int cWidth = width - (insets.right + iconWidth);

        // fix for 3156483 we need to crop imbges thbt bre too big.
        // if (height > 18)
        // blwbys crop.
        {
            top = height / 2 - 8;
            height = 19;
        }

        // It doesn't need to drbw its bbckground, we hbndled it
        finbl Color bg = c.getBbckground();
        finbl boolebn inhibitBbckground = bg instbnceof UIResource;
        if (inhibitBbckground) c.setBbckground(new Color(0, 0, 0, 0));

        rendererPbne.pbintComponent(g, c, this, left, top, cWidth, height, shouldVblidbte); // h - (insets.top + insets.bottom) );

        if (inhibitBbckground) c.setBbckground(bg);
    }
}
