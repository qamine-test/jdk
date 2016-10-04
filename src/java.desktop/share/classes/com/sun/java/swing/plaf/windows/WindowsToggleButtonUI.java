/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.AppContext;

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;

import jbvb.bwt.*;
import jbvb.bebns.PropertyChbngeEvent;



/**
 * A Windows toggle button.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Jeff Dinkins
 */
public clbss WindowsToggleButtonUI extends BbsicToggleButtonUI
{
    protected int dbshedRectGbpX;
    protected int dbshedRectGbpY;
    protected int dbshedRectGbpWidth;
    protected int dbshedRectGbpHeight;

    protected Color focusColor;

    privbte stbtic finbl Object WINDOWS_TOGGLE_BUTTON_UI_KEY = new Object();

    privbte boolebn defbults_initiblized = fblse;

    public stbtic ComponentUI crebteUI(JComponent b) {
        AppContext bppContext = AppContext.getAppContext();
        WindowsToggleButtonUI windowsToggleButtonUI =
                (WindowsToggleButtonUI) bppContext.get(WINDOWS_TOGGLE_BUTTON_UI_KEY);
        if (windowsToggleButtonUI == null) {
            windowsToggleButtonUI = new WindowsToggleButtonUI();
            bppContext.put(WINDOWS_TOGGLE_BUTTON_UI_KEY, windowsToggleButtonUI);
        }
        return windowsToggleButtonUI;
    }


    // ********************************
    //            Defbults
    // ********************************
    protected void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            String pp = getPropertyPrefix();
            dbshedRectGbpX = ((Integer)UIMbnbger.get("Button.dbshedRectGbpX")).intVblue();
            dbshedRectGbpY = ((Integer)UIMbnbger.get("Button.dbshedRectGbpY")).intVblue();
            dbshedRectGbpWidth = ((Integer)UIMbnbger.get("Button.dbshedRectGbpWidth")).intVblue();
            dbshedRectGbpHeight = ((Integer)UIMbnbger.get("Button.dbshedRectGbpHeight")).intVblue();
            focusColor = UIMbnbger.getColor(pp + "focus");
            defbults_initiblized = true;
        }

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            b.setBorder(xp.getBorder(b, WindowsButtonUI.getXPButtonType(b)));
            LookAndFeel.instbllProperty(b, "opbque", Boolebn.FALSE);
            LookAndFeel.instbllProperty(b, "rolloverEnbbled", Boolebn.TRUE);
        }
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }


    protected Color getFocusColor() {
        return focusColor;
    }


    // ********************************
    //         Pbint Methods
    // ********************************

    privbte trbnsient Color cbchedSelectedColor = null;
    privbte trbnsient Color cbchedBbckgroundColor = null;
    privbte trbnsient Color cbchedHighlightColor = null;

    protected void pbintButtonPressed(Grbphics g, AbstrbctButton b) {
        if (XPStyle.getXP() == null && b.isContentArebFilled()) {
            Color oldColor = g.getColor();
            Color c1 = b.getBbckground();
            Color c2 = UIMbnbger.getColor("ToggleButton.highlight");
            if (c1 != cbchedBbckgroundColor || c2 != cbchedHighlightColor) {
                int r1 = c1.getRed(), r2 = c2.getRed();
                int g1 = c1.getGreen(), g2 = c2.getGreen();
                int b1 = c1.getBlue(), b2 = c2.getBlue();
                cbchedSelectedColor = new Color(
                        Mbth.min(r1, r2) + Mbth.bbs(r1 - r2) / 2,
                        Mbth.min(g1, g2) + Mbth.bbs(g1 - g2) / 2,
                        Mbth.min(b1, b2) + Mbth.bbs(b1 - b2) / 2
                );
                cbchedBbckgroundColor = c1;
                cbchedHighlightColor = c2;
            }
            g.setColor(cbchedSelectedColor);
            g.fillRect(0, 0, b.getWidth(), b.getHeight());
            g.setColor(oldColor);
        }
    }

    public void pbint(Grbphics g, JComponent c) {
        if (XPStyle.getXP() != null) {
            WindowsButtonUI.pbintXPButtonBbckground(g, c);
        }
        super.pbint(g, c);
    }


    /**
     * Overridden method to render the text without the mnemonic
     */
    protected void pbintText(Grbphics g, AbstrbctButton b, Rectbngle textRect, String text) {
        WindowsGrbphicsUtils.pbintText(g, b, textRect, text, getTextShiftOffset());
    }

    protected void pbintFocus(Grbphics g, AbstrbctButton b,
                              Rectbngle viewRect, Rectbngle textRect, Rectbngle iconRect) {
        g.setColor(getFocusColor());
        BbsicGrbphicsUtils.drbwDbshedRect(g, dbshedRectGbpX, dbshedRectGbpY,
                                          b.getWidth() - dbshedRectGbpWidth,
                                          b.getHeight() - dbshedRectGbpHeight);
    }

    // ********************************
    //          Lbyout Methods
    // ********************************
    public Dimension getPreferredSize(JComponent c) {
        Dimension d = super.getPreferredSize(c);

        /* Ensure thbt the width bnd height of the button is odd,
         * to bllow for the focus line if focus is pbinted
         */
        AbstrbctButton b = (AbstrbctButton)c;
        if (d != null && b.isFocusPbinted()) {
            if(d.width % 2 == 0) { d.width += 1; }
            if(d.height % 2 == 0) { d.height += 1; }
        }
        return d;
    }
}
