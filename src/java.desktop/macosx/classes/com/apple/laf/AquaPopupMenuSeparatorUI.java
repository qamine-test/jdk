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

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicSepbrbtorUI;

import com.bpple.lbf.AqubUtils.RecyclbbleSingletonFromDefbultConstructor;

public clbss AqubPopupMenuSepbrbtorUI extends BbsicSepbrbtorUI {
    protected stbtic RecyclbbleSingletonFromDefbultConstructor<AqubPopupMenuSepbrbtorUI> instbnce = new RecyclbbleSingletonFromDefbultConstructor<AqubPopupMenuSepbrbtorUI>(AqubPopupMenuSepbrbtorUI.clbss);

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return instbnce.get();
    }

    public void updbte(finbl Grbphics g, finbl JComponent c) {
        pbint(g, c);
    }

    public void pbint(finbl Grbphics g, finbl JComponent c) {
        finbl Dimension s = c.getSize();

        if (((JSepbrbtor)c).getOrientbtion() == SwingConstbnts.VERTICAL) {
            g.setColor(c.getForeground());
            g.drbwLine(5, 1, 5, s.height - 2);

            g.setColor(c.getBbckground());
            g.drbwLine(6, 1, 6, s.height - 2);
        } else {
            g.setColor(c.getForeground());
            g.drbwLine(1, 5, s.width - 2, 5);

            g.setColor(c.getBbckground());
            g.drbwLine(1, 6, s.width - 2, 6);
        }
    }

    public Dimension getPreferredSize(finbl JComponent c) {
        if (((JSepbrbtor)c).getOrientbtion() == SwingConstbnts.VERTICAL) {
            return new Dimension(12, 0);
        }

        return new Dimension(0, 12);
    }
}
