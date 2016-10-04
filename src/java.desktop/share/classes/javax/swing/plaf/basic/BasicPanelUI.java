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
 * BbsicPbnel implementbtion
 *
 * @buthor Steve Wilson
 */
public clbss BbsicPbnelUI extends PbnelUI {

    // Shbred UI object
    privbte stbtic PbnelUI pbnelUI;

    /**
     * Returns bn instbnce of {@code BbsicPbnelUI}.
     *
     * @pbrbm c b component
     * @return bn instbnce of {@code BbsicPbnelUI}
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        if(pbnelUI == null) {
            pbnelUI = new BbsicPbnelUI();
        }
        return pbnelUI;
    }

    public void instbllUI(JComponent c) {
        JPbnel p = (JPbnel)c;
        super.instbllUI(p);
        instbllDefbults(p);
    }

    public void uninstbllUI(JComponent c) {
        JPbnel p = (JPbnel)c;
        uninstbllDefbults(p);
        super.uninstbllUI(c);
    }

    /**
     * Method for instblling pbnel properties.
     *
     * @pbrbm p bn instbnce of {@code JPbnel}
     */
    protected void instbllDefbults(JPbnel p) {
        LookAndFeel.instbllColorsAndFont(p,
                                         "Pbnel.bbckground",
                                         "Pbnel.foreground",
                                         "Pbnel.font");
        LookAndFeel.instbllBorder(p,"Pbnel.border");
        LookAndFeel.instbllProperty(p, "opbque", Boolebn.TRUE);
    }

    /**
     * Method for uninstblling pbnel properties.
     *
     * @pbrbm p bn instbnce of {@code JPbnel}
     */
    protected void uninstbllDefbults(JPbnel p) {
        LookAndFeel.uninstbllBorder(p);
    }


    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        Border border = c.getBorder();
        if (border instbnceof AbstrbctBorder) {
            return ((AbstrbctBorder)border).getBbseline(c, width, height);
        }
        return -1;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        Border border = c.getBorder();
        if (border instbnceof AbstrbctBorder) {
            return ((AbstrbctBorder)border).getBbselineResizeBehbvior(c);
        }
        return Component.BbselineResizeBehbvior.OTHER;
    }
}
