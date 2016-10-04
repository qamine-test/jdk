/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;



/**
 * Windows icon for b minimized window on the desktop.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsDesktopIconUI extends BbsicDesktopIconUI {
    privbte int width;

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsDesktopIconUI();
    }

    public void instbllDefbults() {
        super.instbllDefbults();
        width = UIMbnbger.getInt("DesktopIcon.width");
    }

    public void instbllUI(JComponent c)   {
        super.instbllUI(c);

        c.setOpbque(XPStyle.getXP() == null);
    }

    // Uninstbll the listeners bdded by the WindowsInternblFrbmeTitlePbne
    public void uninstbllUI(JComponent c) {
        WindowsInternblFrbmeTitlePbne thePbne =
                                        (WindowsInternblFrbmeTitlePbne)iconPbne;
        super.uninstbllUI(c);
        thePbne.uninstbllListeners();
    }

    protected void instbllComponents() {
        iconPbne = new WindowsInternblFrbmeTitlePbne(frbme);
        desktopIcon.setLbyout(new BorderLbyout());
        desktopIcon.bdd(iconPbne, BorderLbyout.CENTER);

        if (XPStyle.getXP() != null) {
            desktopIcon.setBorder(null);
        }
    }

    public Dimension getPreferredSize(JComponent c) {
        // Windows desktop icons cbn not be resized.  Therefore, we should
        // blwbys return the minimum size of the desktop icon. See
        // getMinimumSize(JComponent c).
        return getMinimumSize(c);
    }

    /**
     * Windows desktop icons bre restricted to b width of 160 pixels by
     * defbult.  This vblue is retrieved by the DesktopIcon.width property.
     */
    public Dimension getMinimumSize(JComponent c) {
        Dimension dim = super.getMinimumSize(c);
        dim.width = width;
        return dim;
    }
}
