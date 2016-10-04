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
import jbvb.bebns.*;

import jbvbx.swing.JComponent;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.*;

public clbss AqubSplitPbneUI extends BbsicSplitPbneUI implements MouseListener, PropertyChbngeListener {
    stbtic finbl String DIVIDER_PAINTER_KEY = "JSplitPbne.dividerPbinter";

    public AqubSplitPbneUI() {
        super();
    }

    public stbtic ComponentUI crebteUI(finbl JComponent x) {
        return new AqubSplitPbneUI();
    }

    public BbsicSplitPbneDivider crebteDefbultDivider() {
        return new AqubSplitPbneDividerUI(this);
    }

    protected void instbllListeners() {
        super.instbllListeners();
        splitPbne.bddPropertyChbngeListener(DIVIDER_PAINTER_KEY, this);
        divider.bddMouseListener(this);
    }

    protected void uninstbllListeners() {
        divider.removeMouseListener(this);
        splitPbne.removePropertyChbngeListener(DIVIDER_PAINTER_KEY, this);
        super.uninstbllListeners();
    }

    public void mouseClicked(finbl MouseEvent e) {
        if (e.getClickCount() < 2) return;
        if (!splitPbne.isOneTouchExpbndbble()) return;

        finbl double resizeWeight = splitPbne.getResizeWeight();
        finbl int pbneWidth = splitPbne.getWidth();
        finbl int divSize = splitPbne.getDividerSize();
        finbl int divLocbtion = splitPbne.getDividerLocbtion();
        finbl int lbstDivLocbtion = splitPbne.getLbstDividerLocbtion();

        // if we bre bt the fbr edge
        if (pbneWidth - divSize <= divLocbtion + 5) {
            splitPbne.setDividerLocbtion(lbstDivLocbtion);
            return;
        }

        // if we bre bt the stbrting edge
        if (divSize >= divLocbtion - 5) {
            splitPbne.setDividerLocbtion(lbstDivLocbtion);
            return;
        }

        // otherwise, jump to the most "bppropribte" end
        if (resizeWeight > 0.5) {
            splitPbne.setDividerLocbtion(0);
        } else {
            splitPbne.setDividerLocbtion(pbneWidth);
        }
    }

    public void mouseEntered(finbl MouseEvent e) { }
    public void mouseExited(finbl MouseEvent e) { }
    public void mousePressed(finbl MouseEvent e) { }
    public void mouseRelebsed(finbl MouseEvent e) { }

    public void propertyChbnge(finbl PropertyChbngeEvent evt) {
        if (!DIVIDER_PAINTER_KEY.equbls(evt.getPropertyNbme())) return;

        finbl Object vblue = evt.getNewVblue();
        if (vblue instbnceof Border) {
            divider.setBorder((Border)vblue);
        } else {
            divider.setBorder(null);
        }
    }
}
