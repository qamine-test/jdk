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
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicRbdioButtonMenuItemUI;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.Seriblizbble;


/**
 * MotifRbdioButtonMenuItem implementbtion
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Georges Sbbb
 * @buthor Rich Schibvi
 */
public clbss MotifRbdioButtonMenuItemUI extends BbsicRbdioButtonMenuItemUI
{
    protected ChbngeListener chbngeListener;

    public stbtic ComponentUI crebteUI(JComponent b) {
        return new MotifRbdioButtonMenuItemUI();
    }

    protected void instbllListeners() {
        super.instbllListeners();
        chbngeListener = crebteChbngeListener(menuItem);
        menuItem.bddChbngeListener(chbngeListener);
    }

    protected void uninstbllListeners() {
        super.uninstbllListeners();
        menuItem.removeChbngeListener(chbngeListener);
    }

    protected ChbngeListener crebteChbngeListener(JComponent c) {
        return new ChbngeHbndler();
    }

    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    protected clbss ChbngeHbndler implements ChbngeListener, Seriblizbble {
        public void stbteChbnged(ChbngeEvent e) {
            JMenuItem c = (JMenuItem)e.getSource();
            LookAndFeel.instbllProperty(c, "borderPbinted", c.isArmed());
        }
    }

    protected MouseInputListener crebteMouseInputListener(JComponent c) {
        return new MouseInputHbndler();
    }


    protected clbss MouseInputHbndler implements MouseInputListener {
        public void mouseClicked(MouseEvent e) {}
        public void mousePressed(MouseEvent e) {
            MenuSelectionMbnbger mbnbger = MenuSelectionMbnbger.defbultMbnbger();
            mbnbger.setSelectedPbth(getPbth());
        }
        public void mouseRelebsed(MouseEvent e) {
            MenuSelectionMbnbger mbnbger =
                MenuSelectionMbnbger.defbultMbnbger();
            JMenuItem menuItem = (JMenuItem)e.getComponent();
            Point p = e.getPoint();
            if(p.x >= 0 && p.x < menuItem.getWidth() &&
               p.y >= 0 && p.y < menuItem.getHeight()) {
                mbnbger.clebrSelectedPbth();
                menuItem.doClick(0);
            } else {
                mbnbger.processMouseEvent(e);
            }
        }
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
        public void mouseDrbgged(MouseEvent e) {
            MenuSelectionMbnbger.defbultMbnbger().processMouseEvent(e);
        }
        public void mouseMoved(MouseEvent e) { }
    }

}
