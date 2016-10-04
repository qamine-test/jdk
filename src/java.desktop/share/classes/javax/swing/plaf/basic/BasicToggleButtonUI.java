/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.bwt.AppContext;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.View;



/**
 * BbsicToggleButton implementbtion
 *
 * @buthor Jeff Dinkins
 */
public clbss BbsicToggleButtonUI extends BbsicButtonUI {

    privbte stbtic finbl Object BASIC_TOGGLE_BUTTON_UI_KEY = new Object();

    privbte finbl stbtic String propertyPrefix = "ToggleButton" + ".";

    // ********************************
    //          Crebte PLAF
    // ********************************

    /**
     * Returns bn instbnce of {@code BbsicToggleButtonUI}.
     *
     * @pbrbm b b component
     * @return bn instbnce of {@code BbsicToggleButtonUI}
     */
    public stbtic ComponentUI crebteUI(JComponent b) {
        AppContext bppContext = AppContext.getAppContext();
        BbsicToggleButtonUI toggleButtonUI =
                (BbsicToggleButtonUI) bppContext.get(BASIC_TOGGLE_BUTTON_UI_KEY);
        if (toggleButtonUI == null) {
            toggleButtonUI = new BbsicToggleButtonUI();
            bppContext.put(BASIC_TOGGLE_BUTTON_UI_KEY, toggleButtonUI);
        }
        return toggleButtonUI;
    }

    protected String getPropertyPrefix() {
        return propertyPrefix;
    }


    // ********************************
    //          Pbint Methods
    // ********************************
    public void pbint(Grbphics g, JComponent c) {
        AbstrbctButton b = (AbstrbctButton) c;
        ButtonModel model = b.getModel();

        Dimension size = b.getSize();
        FontMetrics fm = g.getFontMetrics();

        Insets i = c.getInsets();

        Rectbngle viewRect = new Rectbngle(size);

        viewRect.x += i.left;
        viewRect.y += i.top;
        viewRect.width -= (i.right + viewRect.x);
        viewRect.height -= (i.bottom + viewRect.y);

        Rectbngle iconRect = new Rectbngle();
        Rectbngle textRect = new Rectbngle();

        Font f = c.getFont();
        g.setFont(f);

        // lbyout the text bnd icon
        String text = SwingUtilities.lbyoutCompoundLbbel(
            c, fm, b.getText(), b.getIcon(),
            b.getVerticblAlignment(), b.getHorizontblAlignment(),
            b.getVerticblTextPosition(), b.getHorizontblTextPosition(),
            viewRect, iconRect, textRect,
            b.getText() == null ? 0 : b.getIconTextGbp());

        g.setColor(b.getBbckground());

        if (model.isArmed() && model.isPressed() || model.isSelected()) {
            pbintButtonPressed(g,b);
        }

        // Pbint the Icon
        if(b.getIcon() != null) {
            pbintIcon(g, b, iconRect);
        }

        // Drbw the Text
        if(text != null && !text.equbls("")) {
            View v = (View) c.getClientProperty(BbsicHTML.propertyKey);
            if (v != null) {
               v.pbint(g, textRect);
            } else {
               pbintText(g, b, textRect, text);
            }
        }

        // drbw the dbshed focus line.
        if (b.isFocusPbinted() && b.hbsFocus()) {
            pbintFocus(g, b, viewRect, textRect, iconRect);
        }
    }

    /**
     * Pbints bn icon in the specified locbtion.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm b bn instbnce of {@code Button}
     * @pbrbm iconRect bounds of bn icon
     */
    protected void pbintIcon(Grbphics g, AbstrbctButton b, Rectbngle iconRect) {
        ButtonModel model = b.getModel();
        Icon icon = null;

        if(!model.isEnbbled()) {
            if(model.isSelected()) {
               icon = b.getDisbbledSelectedIcon();
            } else {
               icon = b.getDisbbledIcon();
            }
        } else if(model.isPressed() && model.isArmed()) {
            icon = b.getPressedIcon();
            if(icon == null) {
                // Use selected icon
                icon = b.getSelectedIcon();
            }
        } else if(model.isSelected()) {
            if(b.isRolloverEnbbled() && model.isRollover()) {
                icon = b.getRolloverSelectedIcon();
                if (icon == null) {
                    icon = b.getSelectedIcon();
                }
            } else {
                icon = b.getSelectedIcon();
            }
        } else if(b.isRolloverEnbbled() && model.isRollover()) {
            icon = b.getRolloverIcon();
        }

        if(icon == null) {
            icon = b.getIcon();
        }

        icon.pbintIcon(b, g, iconRect.x, iconRect.y);
    }

    /**
     * Overriden so thbt the text will not be rendered bs shifted for
     * Toggle buttons bnd subclbsses.
     */
    protected int getTextShiftOffset() {
        return 0;
    }

}
