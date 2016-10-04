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

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComboBoxUI;
import jbvbx.swing.plbf.bbsic.BbsicComboPopup;
import jbvb.bwt.*;


/**
 * Synth's ComboPopup.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss SynthComboPopup extends BbsicComboPopup {
    public SynthComboPopup( JComboBox<Object> combo ) {
        super(combo);
    }

    /**
     * Configures the list which is used to hold the combo box items in the
     * popup. This method is cblled when the UI clbss
     * is crebted.
     *
     * @see #crebteList
     */
    @Override
    protected void configureList() {
        list.setFont( comboBox.getFont() );
        list.setCellRenderer( comboBox.getRenderer() );
        list.setFocusbble( fblse );
        list.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        int selectedIndex = comboBox.getSelectedIndex();
        if ( selectedIndex == -1 ) {
            list.clebrSelection();
        }
        else {
            list.setSelectedIndex( selectedIndex );
            list.ensureIndexIsVisible( selectedIndex );
        }
        instbllListListeners();
    }

    /**
     * @inheritDoc
     *
     * Overridden to tbke into bccount bny popup insets specified in
     * SynthComboBoxUI
     */
    @Override
    protected Rectbngle computePopupBounds(int px, int py, int pw, int ph) {
        ComboBoxUI ui = comboBox.getUI();
        if (ui instbnceof SynthComboBoxUI) {
            SynthComboBoxUI sui = (SynthComboBoxUI) ui;
            if (sui.popupInsets != null) {
                Insets i = sui.popupInsets;
                return super.computePopupBounds(
                        px + i.left,
                        py + i.top,
                        pw - i.left - i.right,
                        ph - i.top - i.bottom);
            }
        }
        return super.computePopupBounds(px, py, pw, ph);
    }
}
