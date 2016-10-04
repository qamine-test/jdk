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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tree.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * Motif rendered to displby b tree cell.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Jeff Dinkins
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss MotifTreeCellRenderer extends DefbultTreeCellRenderer
{
    stbtic finbl int LEAF_SIZE = 13;
    stbtic finbl Icon LEAF_ICON = new IconUIResource(new TreeLebfIcon());

    public MotifTreeCellRenderer() {
        super();
    }

    public stbtic Icon lobdLebfIcon() {
        return LEAF_ICON;
    }

    /**
     * Icon for b node with no children.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses.  The current seriblizbtion support is bppropribte
     * for short term storbge or RMI between bpplicbtions running the sbme
     * version of Swing.  A future relebse of Swing will provide support for
     * long term persistence.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss TreeLebfIcon implements Icon, Seriblizbble {

        Color bg;
        Color shbdow;
        Color highlight;

        public TreeLebfIcon() {
            bg = UIMbnbger.getColor("Tree.iconBbckground");
            shbdow = UIMbnbger.getColor("Tree.iconShbdow");
            highlight = UIMbnbger.getColor("Tree.iconHighlight");
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            g.setColor(bg);

            y -= 3;
            g.fillRect(x + 4, y + 7, 5, 5);

            g.drbwLine(x + 6, y + 6, x + 6, y + 6);
            g.drbwLine(x + 3, y + 9, x + 3, y + 9);
            g.drbwLine(x + 6, y + 12, x + 6, y + 12);
            g.drbwLine(x + 9, y + 9, x + 9, y + 9);

            g.setColor(highlight);
            g.drbwLine(x + 2, y + 9, x + 5, y + 6);
            g.drbwLine(x + 3, y + 10, x + 5, y + 12);

            g.setColor(shbdow);
            g.drbwLine(x + 6, y + 13, x + 10, y + 9);
            g.drbwLine(x + 9, y + 8, x + 7, y + 6);
        }

        public int getIconWidth() {
            return LEAF_SIZE;
        }

        public int getIconHeight() {
            return LEAF_SIZE;
        }

    }
}
