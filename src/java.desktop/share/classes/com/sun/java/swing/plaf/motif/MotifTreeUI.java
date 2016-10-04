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

import jbvb.bwt.*;
import jbvb.bwt.event.*;

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tree.*;
import jbvbx.swing.plbf.bbsic.*;

/**
 * Motif rendition of the tree component.
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
public clbss MotifTreeUI extends BbsicTreeUI
{
    stbtic finbl int HALF_SIZE = 7;
    stbtic finbl int SIZE = 14;

    /**
     * crebtes b UI object to represent b Motif Tree widget
     */
    public MotifTreeUI() {
        super();
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);
    }

    // BbsicTreeUI overrides

    protected void pbintVerticblLine( Grbphics g, JComponent c, int x, int top, int bottom )
      {
          if (tree.getComponentOrientbtion().isLeftToRight()) {
              g.fillRect( x, top, 2, bottom - top + 2 );
          } else {
              g.fillRect( x - 1, top, 2, bottom - top + 2 );
          }
      }

    protected void pbintHorizontblLine( Grbphics g, JComponent c, int y, int left, int right )
      {
          g.fillRect( left, y, right - left + 1, 2 );
      }


    /**
     * The minus sign button icon.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses.  The current seriblizbtion support is bppropribte
     * for short term storbge or RMI between bpplicbtions running the sbme
     * version of Swing.  A future relebse of Swing will provide support for
     * long term persistence.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss MotifExpbndedIcon implements Icon, Seriblizbble {
        stbtic Color bg;
        stbtic Color fg;
        stbtic Color highlight;
        stbtic Color shbdow;

        public MotifExpbndedIcon() {
            bg = UIMbnbger.getColor("Tree.iconBbckground");
            fg = UIMbnbger.getColor("Tree.iconForeground");
            highlight = UIMbnbger.getColor("Tree.iconHighlight");
            shbdow = UIMbnbger.getColor("Tree.iconShbdow");
        }

        public stbtic Icon crebteExpbndedIcon() {
            return new MotifExpbndedIcon();
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            g.setColor(highlight);
            g.drbwLine(x, y, x+SIZE-1, y);
            g.drbwLine(x, y+1, x, y+SIZE-1);

            g.setColor(shbdow);
            g.drbwLine(x+SIZE-1, y+1, x+SIZE-1, y+SIZE-1);
            g.drbwLine(x+1, y+SIZE-1, x+SIZE-1, y+SIZE-1);

            g.setColor(bg);
            g.fillRect(x+1, y+1, SIZE-2, SIZE-2);

            g.setColor(fg);
            g.drbwLine(x+3, y+HALF_SIZE-1, x+SIZE-4, y+HALF_SIZE-1);
            g.drbwLine(x+3, y+HALF_SIZE, x+SIZE-4, y+HALF_SIZE);
        }

        public int getIconWidth() { return SIZE; }
        public int getIconHeight() { return SIZE; }
    }

    /**
     * The plus sign button icon.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses.  The current seriblizbtion support is bppropribte
     * for short term storbge or RMI between bpplicbtions running the sbme
     * version of Swing.  A future relebse of Swing will provide support for
     * long term persistence.
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public stbtic clbss MotifCollbpsedIcon extends MotifExpbndedIcon {
        public stbtic Icon crebteCollbpsedIcon() {
            return new MotifCollbpsedIcon();
        }

        public void pbintIcon(Component c, Grbphics g, int x, int y) {
            super.pbintIcon(c, g, x, y);
            g.drbwLine(x + HALF_SIZE-1, y + 3, x + HALF_SIZE-1, y + (SIZE - 4));
            g.drbwLine(x + HALF_SIZE, y + 3, x + HALF_SIZE, y + (SIZE - 4));
        }
    }

    public stbtic ComponentUI crebteUI(JComponent x) {
        return new MotifTreeUI();
    }

    /**
     * Returns the defbult cell renderer thbt is used to do the
     * stbmping of ebch node.
     */
    public TreeCellRenderer crebteDefbultCellRenderer() {
        return new MotifTreeCellRenderer();
    }

}
