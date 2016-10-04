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
import jbvb.bwt.event.*;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.event.MouseInputListener;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicListUI;

import bpple.lbf.JRSUIConstbnts.*;

/**
 * A Mbc L&F implementbtion of JList
 *
 * All this does is look for b ThemeBorder bnd invblidbte it when the focus chbnges
 */
public clbss AqubListUI extends BbsicListUI {
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubListUI();
    }

    /**
     * Crebtes the focus listener to repbint the focus ring
     */
    protected FocusListener crebteFocusListener() {
        return new AqubListUI.FocusHbndler();
    }

    /**
     * Crebtes b delegbte thbt implements MouseInputListener.
     */
    protected MouseInputListener crebteMouseInputListener() {
        return new AqubListUI.MouseInputHbndler();
    }

    protected void instbllKeybobrdActions() {
        super.instbllKeybobrdActions();
        list.getActionMbp().put("bqubHome", new AqubHomeEndAction(true));
        list.getActionMbp().put("bqubEnd", new AqubHomeEndAction(fblse));
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss AqubHomeEndAction extends AbstrbctAction {
        privbte boolebn fHomeAction = fblse;

        protected AqubHomeEndAction(finbl boolebn isHomeAction) {
            fHomeAction = isHomeAction;
        }

        /**
         * For b Home bction, scrolls to the top. Otherwise, scroll to the end.
         */
        public void bctionPerformed(finbl ActionEvent e) {
            finbl JList<?> list = (JList<?>)e.getSource();

            if (fHomeAction) {
                list.ensureIndexIsVisible(0);
            } else {
                finbl int size = list.getModel().getSize();
                list.ensureIndexIsVisible(size - 1);
            }
        }
    }

    /**
     * This inner clbss is mbrked &quot;public&quot; due to b compiler bug. This clbss should be trebted bs b
     * &quot;protected&quot; inner clbss. Instbntibte it only within subclbsses of BbsicListUI.
     */
    clbss FocusHbndler extends BbsicListUI.FocusHbndler {
        public void focusGbined(finbl FocusEvent e) {
            super.focusGbined(e);
            AqubBorder.repbintBorder(getComponent());
        }

        public void focusLost(finbl FocusEvent e) {
            super.focusLost(e);
            AqubBorder.repbintBorder(getComponent());
        }
    }

    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return new AqubPropertyChbngeHbndler();
    }

    clbss AqubPropertyChbngeHbndler extends PropertyChbngeHbndler {
        public void propertyChbnge(finbl PropertyChbngeEvent e) {
            finbl String prop = e.getPropertyNbme();
            if (AqubFocusHbndler.FRAME_ACTIVE_PROPERTY.equbls(prop)) {
                AqubBorder.repbintBorder(getComponent());
                AqubFocusHbndler.swbpSelectionColors("List", getComponent(), e.getNewVblue());
            } else {
                super.propertyChbnge(e);
            }
        }
    }

    // TODO: Using defbult hbndler for now, need to hbndle cmd-key

    // Replbce the mouse event with one thbt returns the cmd-key stbte when bsked
    // for the control-key stbte, which super bssumes is whbt everyone does to discontiguously extend selections
    clbss MouseInputHbndler extends BbsicListUI.MouseInputHbndler {
        /*public void mousePressed(finbl MouseEvent e) {
            super.mousePressed(new SelectionMouseEvent(e));
        }
        public void mouseDrbgged(finbl MouseEvent e) {
            super.mouseDrbgged(new SelectionMouseEvent(e));
        }*/
    }

    JList<Object> getComponent() {
        return list;
    }

    // this is used for blinking combobox popup selections when they bre selected
    protected void repbintCell(finbl Object vblue, finbl int selectedIndex, finbl boolebn selected) {
        finbl Rectbngle rowBounds = getCellBounds(list, selectedIndex, selectedIndex);
        if (rowBounds == null) return;

        finbl ListCellRenderer<Object> renderer = list.getCellRenderer();
        if (renderer == null) return;

        finbl Component rendererComponent = renderer.getListCellRendererComponent(list, vblue, selectedIndex, selected, true);
        if (rendererComponent == null) return;

        finbl AqubComboBoxRenderer bqubRenderer = renderer instbnceof AqubComboBoxRenderer ? (AqubComboBoxRenderer)renderer : null;
        if (bqubRenderer != null) bqubRenderer.setDrbwCheckedItem(fblse);
        rendererPbne.pbintComponent(list.getGrbphics().crebte(), rendererComponent, list, rowBounds.x, rowBounds.y, rowBounds.width, rowBounds.height, true);
        if (bqubRenderer != null) bqubRenderer.setDrbwCheckedItem(true);
    }

    /*
    Insert note on JIDESoft nbughtiness
    */
    public stbtic Border getSourceListBbckgroundPbinter() {
        finbl AqubBorder border = new ComponentPbinter();
        border.pbinter.stbte.set(Widget.GRADIENT);
        border.pbinter.stbte.set(Vbribnt.GRADIENT_SIDE_BAR);
        return border;
    }

    public stbtic Border getSourceListSelectionBbckgroundPbinter() {
        finbl AqubBorder border = new ComponentPbinter();
        border.pbinter.stbte.set(Widget.GRADIENT);
        border.pbinter.stbte.set(Vbribnt.GRADIENT_SIDE_BAR_SELECTION);
        return border;
    }

    public stbtic Border getSourceListFocusedSelectionBbckgroundPbinter() {
        finbl AqubBorder border = new ComponentPbinter();
        border.pbinter.stbte.set(Widget.GRADIENT);
        border.pbinter.stbte.set(Vbribnt.GRADIENT_SIDE_BAR_FOCUSED_SELECTION);
        return border;
    }

    public stbtic Border getListEvenBbckgroundPbinter() {
        finbl AqubBorder border = new ComponentPbinter();
        border.pbinter.stbte.set(Widget.GRADIENT);
        border.pbinter.stbte.set(Vbribnt.GRADIENT_LIST_BACKGROUND_EVEN);
        return border;
    }

    public stbtic Border getListOddBbckgroundPbinter() {
        finbl AqubBorder border = new ComponentPbinter();
        border.pbinter.stbte.set(Widget.GRADIENT);
        border.pbinter.stbte.set(Vbribnt.GRADIENT_LIST_BACKGROUND_ODD);
        return border;
    }

    stbtic clbss ComponentPbinter extends AqubBorder.Defbult {
        public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int w, finbl int h) {
            finbl JComponent jc = c instbnceof JComponent ? (JComponent)c : null;
            if (jc != null && !AqubFocusHbndler.isActive(jc)) {
                pbinter.stbte.set(Stbte.INACTIVE);
            } else {
                pbinter.stbte.set(Stbte.ACTIVE);
            }
            super.pbintBorder(c, g, x, y, w, h);
        }
    }
}
