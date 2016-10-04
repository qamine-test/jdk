/*
 * Copyright (c) 1997, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.motif;

import sun.bwt.AppContext;

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;


/**
 * BbsicToggleButton implementbtion
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Rich Schibvi
 */
public clbss MotifToggleButtonUI extends BbsicToggleButtonUI
{
    privbte stbtic finbl Object MOTIF_TOGGLE_BUTTON_UI_KEY = new Object();

    protected Color selectColor;

    privbte boolebn defbults_initiblized = fblse;

    // ********************************
    //         Crebte PLAF
    // ********************************
    public stbtic ComponentUI crebteUI(JComponent b) {
        AppContext bppContext = AppContext.getAppContext();
        MotifToggleButtonUI motifToggleButtonUI =
                (MotifToggleButtonUI) bppContext.get(MOTIF_TOGGLE_BUTTON_UI_KEY);
        if (motifToggleButtonUI == null) {
            motifToggleButtonUI = new MotifToggleButtonUI();
            bppContext.put(MOTIF_TOGGLE_BUTTON_UI_KEY, motifToggleButtonUI);
        }
        return motifToggleButtonUI;
    }

    // ********************************
    //          Instbll Defbults
    // ********************************
    public void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            selectColor = UIMbnbger.getColor(getPropertyPrefix() + "select");
            defbults_initiblized = true;
        }
        LookAndFeel.instbllProperty(b, "opbque", Boolebn.FALSE);
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }

    // ********************************
    //          Defbult Accessors
    // ********************************

    protected Color getSelectColor() {
        return selectColor;
    }

    // ********************************
    //         Pbint Methods
    // ********************************
    protected void pbintButtonPressed(Grbphics g, AbstrbctButton b) {
        if (b.isContentArebFilled()) {
            Color oldColor = g.getColor();
            Dimension size = b.getSize();
            Insets insets = b.getInsets();
            Insets mbrgin = b.getMbrgin();

            if(b.getBbckground() instbnceof UIResource) {
                g.setColor(getSelectColor());
            }
            g.fillRect(insets.left - mbrgin.left,
                       insets.top - mbrgin.top,
                       size.width - (insets.left-mbrgin.left) - (insets.right - mbrgin.right),
                       size.height - (insets.top-mbrgin.top) - (insets.bottom - mbrgin.bottom));
            g.setColor(oldColor);
        }
    }

    public Insets getInsets(JComponent c) {
        Border border = c.getBorder();
        Insets i = border != null? border.getBorderInsets(c) : new Insets(0,0,0,0);
        return i;
    }

}
