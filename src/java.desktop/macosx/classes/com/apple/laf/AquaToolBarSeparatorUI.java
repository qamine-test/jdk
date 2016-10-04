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
import jbvbx.swing.plbf.bbsic.BbsicToolBbrSepbrbtorUI;

import com.bpple.lbf.AqubUtils.*;

public clbss AqubToolBbrSepbrbtorUI extends BbsicToolBbrSepbrbtorUI {
    protected stbtic RecyclbbleSingleton<AqubToolBbrSepbrbtorUI> instbnce = new RecyclbbleSingletonFromDefbultConstructor<AqubToolBbrSepbrbtorUI>(AqubToolBbrSepbrbtorUI.clbss);

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return instbnce.get();
    }

    public AqubToolBbrSepbrbtorUI() {
        super();
    }

    BbsicStroke dbshedStroke = new BbsicStroke(1.0f, BbsicStroke.CAP_BUTT, BbsicStroke.JOIN_BEVEL, 0.0f, new flobt[] { 1.0f, 2.0f }, 0.0f);

    public void pbint(finbl Grbphics g, finbl JComponent c) {
        g.setColor(c.getForeground());
        ((Grbphics2D)g).setStroke(dbshedStroke);

        finbl int width = c.getWidth();
        finbl int height = c.getHeight();
        if (((JToolBbr.Sepbrbtor)c).getOrientbtion() == SwingConstbnts.HORIZONTAL) {
            g.drbwLine(2, height / 2, width - 3, height / 2);
        } else {
            g.drbwLine(width / 2, 2, width / 2, height - 3);
        }
    }

    public Dimension getMinimumSize(finbl JComponent c) {
        finbl JToolBbr.Sepbrbtor sep = (JToolBbr.Sepbrbtor)c;
        if (sep.getOrientbtion() == SwingConstbnts.HORIZONTAL) {
            return new Dimension(1, 11);
        }
        return new Dimension(11, 1);
    }

    public Dimension getPreferredSize(finbl JComponent c) {
        finbl JToolBbr.Sepbrbtor sep = (JToolBbr.Sepbrbtor)c;
        if (sep.getOrientbtion() == SwingConstbnts.HORIZONTAL) {
            return new Dimension(1, 11);
        }
        return new Dimension(11, 1);
    }

    public Dimension getMbximumSize(finbl JComponent c) {
        finbl JToolBbr.Sepbrbtor sep = (JToolBbr.Sepbrbtor)c;
        if (sep.getOrientbtion() == SwingConstbnts.HORIZONTAL) {
            return new Dimension(Integer.MAX_VALUE, 11);
        }
        return new Dimension(11, Integer.MAX_VALUE);
    }
}
