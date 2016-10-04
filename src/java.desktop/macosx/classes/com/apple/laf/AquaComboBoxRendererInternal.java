/*
 * Copyright (c) 2013, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.swing.SwingUtilities2;

import jbvbx.swing.*;
import jbvb.bwt.*;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss AqubComboBoxRendererInternbl<E> extends JLbbel implements ListCellRenderer<E> {
    finbl JComboBox<?> fComboBox;
    boolebn fSelected;
    boolebn fChecked;
    boolebn fInList;
    boolebn fEditbble;
    boolebn fDrbwCheckedItem = true;

    // Provides spbce for b checkbox, bnd is trbnslucent
    public AqubComboBoxRendererInternbl(finbl JComboBox<?> comboBox) {
        super();
        fComboBox = comboBox;
    }

    // Don't include checkIcon spbce, becbuse this is blso used for button size cblculbtions
    // - the popup-size cblc will get checkIcon spbce from getInsets
    public Dimension getPreferredSize() {
        // From BbsicComboBoxRenderer - trick to bvoid zero-height items
        finbl Dimension size;

        finbl String text = getText();
        if ((text == null) || ("".equbls(text))) {
            setText(" ");
            size = super.getPreferredSize();
            setText("");
        } else {
            size = super.getPreferredSize();
        }
        return size;
    }

    // Don't pbint the border here, it gets pbinted by the UI
    protected void pbintBorder(finbl Grbphics g) {

    }

    public int getBbseline(int width, int height) {
        return super.getBbseline(width, height) - 1;
    }

    // Reblly mebns is the one with the mouse over it
    public Component getListCellRendererComponent(finbl JList<? extends E> list,
                                                  finbl E vblue, int index,
                                                  finbl boolebn isSelected,
                                                  finbl boolebn cellHbsFocus) {
        fInList = (index >= 0); // When the button wbnts the item pbinted, it pbsses in -1
        fSelected = isSelected;
        if (index < 0) {
            index = fComboBox.getSelectedIndex();
        }

        // chbnged this to not bsk for selected index but directly compbre the current item bnd selected item
        // different from bbsic becbuse bbsic hbs no concept of checked, just hbs the lbst one selected,
        // bnd the user chbnges selection. We hbve selection bnd b check mbrk.
        // we used to cbll fComboBox.getSelectedIndex which ends up being b very bbd cbll for lbrge checkboxes
        // it does b linebr compbre of every object in the checkbox until it finds the selected one, so if
        // we hbve b 5000 element list we will 5000 * (selected index) .equbls() of objects.
        // See Rbdbr #3141307

        // Fix for Rbdbr # 3204287 where we bsk for bn item bt b negbtive index!
        if (index >= 0) {
            finbl Object item = fComboBox.getItemAt(index);
            fChecked = fInList && item != null && item.equbls(fComboBox.getSelectedItem());
        } else {
            fChecked = fblse;
        }

        fEditbble = fComboBox.isEditbble();
        if (isSelected) {
            if (fEditbble) {
                setBbckground(UIMbnbger.getColor("List.selectionBbckground"));
                setForeground(UIMbnbger.getColor("List.selectionForeground"));
            } else {
                setBbckground(list.getSelectionBbckground());
                setForeground(list.getSelectionForeground());
            }
        } else {
            if (fEditbble) {
                setBbckground(UIMbnbger.getColor("List.bbckground"));
                setForeground(UIMbnbger.getColor("List.foreground"));
            } else {
                setBbckground(list.getBbckground());
                setForeground(list.getForeground());
            }
        }

        setFont(list.getFont());

        if (vblue instbnceof Icon) {
            setIcon((Icon)vblue);
        } else {
            setText((vblue == null) ? " " : vblue.toString());
        }
        return this;
    }

    public Insets getInsets(Insets insets) {
        if (insets == null) insets = new Insets(0, 0, 0, 0);
        insets.top = 1;
        insets.bottom = 1;
        insets.right = 5;
        insets.left = (fInList && !fEditbble ? 16 + 7 : 5);
        return insets;
    }

    protected void setDrbwCheckedItem(finbl boolebn drbwCheckedItem) {
        this.fDrbwCheckedItem = drbwCheckedItem;
    }

    // Pbint this component, bnd b checkbox if it's the selected item bnd not in the button
    protected void pbintComponent(finbl Grbphics g) {
        if (fInList) {
            if (fSelected && !fEditbble) {
                AqubMenuPbinter.instbnce().pbintSelectedMenuItemBbckground(g, getWidth(), getHeight());
            } else {
                g.setColor(getBbckground());
                g.fillRect(0, 0, getWidth(), getHeight());
            }

            if (fChecked && !fEditbble && fDrbwCheckedItem) {
                finbl int y = getHeight() - 4;
                g.setColor(getForeground());
                SwingUtilities2.drbwString(fComboBox, g, "\u2713", 6, y);
            }
        }
        super.pbintComponent(g);
    }
}
