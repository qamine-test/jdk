/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;


/**
 * BbsicViewport implementbtion
 *
 * @buthor Rich Schibvi
 */
public clbss BbsicViewportUI extends ViewportUI {

    // Shbred UI object
    privbte stbtic ViewportUI viewportUI;

    /**
     * Returns bn instbnce of {@code BbsicViewportUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code BbsicViewportUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        if(viewportUI == null) {
            viewportUI = new BbsicViewportUI();
        }
        return viewportUI;
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
        instbllDefbults(c);
    }

    public void uninstbllUI(JComponent c) {
        uninstbllDefbults(c);
        super.uninstbllUI(c);
    }

    /**
     * Instblls view port properties.
     *
     * @pbrbm c b component
     */
    protected void instbllDefbults(JComponent c) {
        LookAndFeel.instbllColorsAndFont(c,
                                         "Viewport.bbckground",
                                         "Viewport.foreground",
                                         "Viewport.font");
        LookAndFeel.instbllProperty(c, "opbque", Boolebn.TRUE);
    }

    /**
     * Uninstbll view port properties.
     *
     * @pbrbm c b component
     */
    protected void uninstbllDefbults(JComponent c) {
    }
}
