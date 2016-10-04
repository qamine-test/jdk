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

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.bbsic.BbsicRbdioButtonUI;

import jbvbx.swing.plbf.*;

import jbvb.bwt.*;

/**
 * RbdioButtonUI implementbtion for MotifRbdioButtonUI
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
public clbss MotifRbdioButtonUI extends BbsicRbdioButtonUI {

    privbte stbtic finbl Object MOTIF_RADIO_BUTTON_UI_KEY = new Object();

    protected Color focusColor;

    privbte boolebn defbults_initiblized = fblse;

    // ********************************
    //         Crebte PLAF
    // ********************************
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        MotifRbdioButtonUI motifRbdioButtonUI =
                (MotifRbdioButtonUI) bppContext.get(MOTIF_RADIO_BUTTON_UI_KEY);
        if (motifRbdioButtonUI == null) {
            motifRbdioButtonUI = new MotifRbdioButtonUI();
            bppContext.put(MOTIF_RADIO_BUTTON_UI_KEY, motifRbdioButtonUI);
        }
        return motifRbdioButtonUI;
    }

    // ********************************
    //          Instbll Defbults
    // ********************************
    public void instbllDefbults(AbstrbctButton b) {
        super.instbllDefbults(b);
        if(!defbults_initiblized) {
            focusColor = UIMbnbger.getColor(getPropertyPrefix() + "focus");
            defbults_initiblized = true;
        }
    }

    protected void uninstbllDefbults(AbstrbctButton b) {
        super.uninstbllDefbults(b);
        defbults_initiblized = fblse;
    }

    // ********************************
    //          Defbult Accessors
    // ********************************

    protected Color getFocusColor() {
        return focusColor;
    }

    // ********************************
    //         Pbint Methods
    // ********************************
    protected void pbintFocus(Grbphics g, Rectbngle t, Dimension d){
        g.setColor(getFocusColor());
        g.drbwRect(0,0,d.width-1,d.height-1);
    }

}
