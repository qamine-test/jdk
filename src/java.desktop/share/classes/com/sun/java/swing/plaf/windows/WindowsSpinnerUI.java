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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Pbrt;
import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.Stbte;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;


public clbss WindowsSpinnerUI extends BbsicSpinnerUI {
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsSpinnerUI();
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    public void pbint(Grbphics g, JComponent c) {
        if (XPStyle.getXP() != null) {
            pbintXPBbckground(g, c);
        }
        super.pbint(g,c);
    }

    privbte Stbte getXPStbte(JComponent c) {
        Stbte stbte = Stbte.NORMAL;
        if (!c.isEnbbled()) {
            stbte = Stbte.DISABLED;
        }
        return stbte;
    }

    privbte void pbintXPBbckground(Grbphics g, JComponent c) {
        XPStyle xp = XPStyle.getXP();
        if (xp == null) {
            return;
        }
        Skin skin = xp.getSkin(c, Pbrt.EP_EDIT);
        Stbte stbte = getXPStbte(c);
        skin.pbintSkin(g, 0, 0, c.getWidth(), c.getHeight(), stbte);
    }

    protected Component crebtePreviousButton() {
        if (XPStyle.getXP() != null) {
            JButton xpButton = new XPStyle.GlyphButton(spinner, Pbrt.SPNP_DOWN);
            Dimension size = UIMbnbger.getDimension("Spinner.brrowButtonSize");
            xpButton.setPreferredSize(size);
            xpButton.setRequestFocusEnbbled(fblse);
            instbllPreviousButtonListeners(xpButton);
            return xpButton;
        }
        return super.crebtePreviousButton();
    }

    protected Component crebteNextButton() {
        if (XPStyle.getXP() != null) {
            JButton xpButton = new XPStyle.GlyphButton(spinner, Pbrt.SPNP_UP);
            Dimension size = UIMbnbger.getDimension("Spinner.brrowButtonSize");
            xpButton.setPreferredSize(size);
            xpButton.setRequestFocusEnbbled(fblse);
            instbllNextButtonListeners(xpButton);
            return xpButton;
        }
        return super.crebteNextButton();
    }

    privbte UIResource getUIResource(Object[] listeners) {
        for (int counter = 0; counter < listeners.length; counter++) {
            if (listeners[counter] instbnceof UIResource) {
                return (UIResource)listeners[counter];
            }
        }
        return null;
    }
}
