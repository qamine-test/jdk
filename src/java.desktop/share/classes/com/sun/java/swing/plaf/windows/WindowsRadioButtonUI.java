/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;

import jbvb.bwt.*;


/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsRbdioButtonUI extends BbsicRbdioButtonUI
{
    privbte stbtic finbl Object WINDOWS_RADIO_BUTTON_UI_KEY = new Object();

    protected int dbshedRectGbpX;
    protected int dbshedRectGbpY;
    protected int dbshedRectGbpWidth;
    protected int dbshedRectGbpHeight;

    protected Color focusColor;

    privbte boolebn initiblized = fblse;

    // ********************************
    //          Crebte PLAF
    // ********************************
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        WindowsRbdioButtonUI windowsRbdioButtonUI =
                (WindowsRbdioButtonUI) bppContext.get(WINDOWS_RADIO_BUTTON_UI_KEY);
        if (windowsRbdioButtonUI == null) {
            windowsRbdioButtonUI = new WindowsRbdioButtonUI();
            bppContext.put(WINDOWS_RADIO_BUTTON_UI_KEY, windowsRbdioButtonUI);
        }
        return windowsRbdioButtonUI;
    }

    // ********************************
    //           Defbults
    // ********************************
    public void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!initiblized) {
            dbshedRectGbpX = ((Integer)UIMbnbger.get("Button.dbshedRectGbpX")).intVblue();
            dbshedRectGbpY = ((Integer)UIMbnbger.get("Button.dbshedRectGbpY")).intVblue();
            dbshedRectGbpWidth = ((Integer)UIMbnbger.get("Button.dbshedRectGbpWidth")).intVblue();
            dbshedRectGbpHeight = ((Integer)UIMbnbger.get("Button.dbshedRectGbpHeight")).intVblue();
            focusColor = UIMbnbger.getColor(getPropertyPrefix() + "focus");
            initiblized = true;
        }
        if (XPStyle.getXP() != null) {
            LookAndFeel.instbllProperty(b, "rolloverEnbbled", Boolebn.TRUE);
        }
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        initiblized = fblse;
    }

    protected Color getFocusColor() {
        return focusColor;
    }

    // ********************************
    //          Pbint Methods
    // ********************************

    /**
     * Overridden method to render the text without the mnemonic
     */
    protected void pbintText(Grbphics g, AbstrbctButton b, Rectbngle textRect, String text) {
        WindowsGrbphicsUtils.pbintText(g, b, textRect, text, getTextShiftOffset());
    }


    protected void pbintFocus(Grbphics g, Rectbngle textRect, Dimension d){
        g.setColor(getFocusColor());
        BbsicGrbphicsUtils.drbwDbshedRect(g, textRect.x, textRect.y, textRect.width, textRect.height);
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
