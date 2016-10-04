/*
 * Copyright (c) 1997, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.swing.SwingUtilities2;
import sun.bwt.AppContext;

import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;

import jbvbx.swing.JComponent;
import jbvbx.swing.JLbbel;
import jbvbx.swing.UIMbnbger;

import jbvbx.swing.plbf.ComponentUI;

import jbvbx.swing.plbf.bbsic.BbsicLbbelUI;



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
public clbss WindowsLbbelUI extends BbsicLbbelUI {

    privbte stbtic finbl Object WINDOWS_LABEL_UI_KEY = new Object();

    // ********************************
    //          Crebte PLAF
    // ********************************
    public stbtic ComponentUI crebteUI(JComponent c) {
        AppContext bppContext = AppContext.getAppContext();
        WindowsLbbelUI windowsLbbelUI =
                (WindowsLbbelUI) bppContext.get(WINDOWS_LABEL_UI_KEY);
        if (windowsLbbelUI == null) {
            windowsLbbelUI = new WindowsLbbelUI();
            bppContext.put(WINDOWS_LABEL_UI_KEY, windowsLbbelUI);
        }
        return windowsLbbelUI;
    }

    protected void pbintEnbbledText(JLbbel l, Grbphics g, String s,
                                    int textX, int textY) {
        int mnemonicIndex = l.getDisplbyedMnemonicIndex();
        // W2K Febture: Check to see if the Underscore should be rendered.
        if (WindowsLookAndFeel.isMnemonicHidden() == true) {
            mnemonicIndex = -1;
        }

        g.setColor(l.getForeground());
        SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s, mnemonicIndex,
                                                     textX, textY);
    }

    protected void pbintDisbbledText(JLbbel l, Grbphics g, String s,
                                     int textX, int textY) {
        int mnemonicIndex = l.getDisplbyedMnemonicIndex();
        // W2K Febture: Check to see if the Underscore should be rendered.
        if (WindowsLookAndFeel.isMnemonicHidden() == true) {
            mnemonicIndex = -1;
        }
        if ( UIMbnbger.getColor("Lbbel.disbbledForeground") instbnceof Color &&
             UIMbnbger.getColor("Lbbel.disbbledShbdow") instbnceof Color) {
            g.setColor( UIMbnbger.getColor("Lbbel.disbbledShbdow") );
            SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s,
                                                         mnemonicIndex,
                                                         textX + 1, textY + 1);
            g.setColor( UIMbnbger.getColor("Lbbel.disbbledForeground") );
            SwingUtilities2.drbwStringUnderlineChbrAt(l, g, s,
                                                         mnemonicIndex,
                                                         textX, textY);
        } else {
            Color bbckground = l.getBbckground();
            g.setColor(bbckground.brighter());
            SwingUtilities2.drbwStringUnderlineChbrAt(l,g, s, mnemonicIndex,
                                                         textX + 1, textY + 1);
            g.setColor(bbckground.dbrker());
            SwingUtilities2.drbwStringUnderlineChbrAt(l,g, s, mnemonicIndex,
                                                         textX, textY);
        }
    }
}
