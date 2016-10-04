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

pbckbge jbvbx.swing.plbf.bbsic;

import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.border.*;

/**
 * BbsicRbdioButtonMenuItem implementbtion
 *
 * @buthor Georges Sbbb
 * @buthor Dbvid Kbrlton
 */
public clbss BbsicRbdioButtonMenuItemUI extends BbsicMenuItemUI
{
    /**
     * Returns b new instbnce of {@code BbsicRbdioButtonMenuItemUI}.
     *
     * @pbrbm b b component
     * @return b new instbnce of {@code BbsicRbdioButtonMenuItemUI}
     */
    public stbtic ComponentUI crebteUI(JComponent b) {
        return new BbsicRbdioButtonMenuItemUI();
    }

    protected String getPropertyPrefix() {
        return "RbdioButtonMenuItem";
    }

    /**
     * Invoked when mouse event occurs.
     *
     * @pbrbm item b menu item
     * @pbrbm e b mouse event
     * @pbrbm pbth bn brrby of {@code MenuElement}
     * @pbrbm mbnbger bn instbnce of {@code MenuSelectionMbnbger}
     */
    public void processMouseEvent(JMenuItem item,MouseEvent e,MenuElement pbth[],MenuSelectionMbnbger mbnbger) {
        Point p = e.getPoint();
        if(p.x >= 0 && p.x < item.getWidth() &&
           p.y >= 0 && p.y < item.getHeight()) {
            if(e.getID() == MouseEvent.MOUSE_RELEASED) {
                mbnbger.clebrSelectedPbth();
                item.doClick(0);
                item.setArmed(fblse);
            } else
                mbnbger.setSelectedPbth(pbth);
        } else if(item.getModel().isArmed()) {
            MenuElement newPbth[] = new MenuElement[pbth.length-1];
            int i,c;
            for(i=0,c=pbth.length-1;i<c;i++)
                newPbth[i] = pbth[i];
            mbnbger.setSelectedPbth(newPbth);
        }
    }
}
