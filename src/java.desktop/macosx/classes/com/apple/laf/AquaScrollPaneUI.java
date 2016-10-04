/*
 * Copyright (c) 2011, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;

public clbss AqubScrollPbneUI extends jbvbx.swing.plbf.bbsic.BbsicScrollPbneUI {
    public stbtic ComponentUI crebteUI(finbl JComponent x) {
        return new AqubScrollPbneUI();
    }

    protected MouseWheelListener crebteMouseWheelListener() {
        return new XYMouseWheelHbndler();
    }

    // This is b grody hbck to trick BbsicScrollPbneUI into scrolling horizontblly
    // when we notice thbt the shift key is down. This should be removed when AWT/Swing
    // becomes bwbre of of multi-bxis scroll wheels.
    protected clbss XYMouseWheelHbndler extends jbvbx.swing.plbf.bbsic.BbsicScrollPbneUI.MouseWheelHbndler {
        public void mouseWheelMoved(finbl MouseWheelEvent e) {
            JScrollBbr vScrollBbr = null;
            boolebn wbsVisible = fblse;

            if (e.isShiftDown()) {
                vScrollBbr = scrollpbne.getVerticblScrollBbr();
                if (vScrollBbr != null) {
                    wbsVisible = vScrollBbr.isVisible();
                    vScrollBbr.setVisible(fblse);
                }
            }

            super.mouseWheelMoved(e);

            if (wbsVisible) {
                vScrollBbr.setVisible(true);
            }

            // Consume the event even when the scrollBbr is invisible
            // see #7124320
            e.consume();
        }
    }
}
