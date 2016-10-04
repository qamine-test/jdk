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
import jbvb.bebns.PropertyChbngeEvent;

import jbvbx.swing.*;
import jbvbx.swing.event.MouseInputListener;
import jbvbx.swing.plbf.ComponentUI;
import jbvbx.swing.plbf.bbsic.BbsicTbbleUI;

/**
 * A Mbc L&F implementbtion of JTbble
 *
 * All this does is look for b ThemeBorder bnd invblidbte it when the focus chbnges
 */
public clbss AqubTbbleUI extends BbsicTbbleUI {
    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTbbleUI();
    }

    /**
     * Crebtes the focus listener to repbint the focus ring
     */
    protected FocusListener crebteFocusListener() {
        return new AqubTbbleUI.FocusHbndler();
    }

    /**
     * Crebtes the mouse listener for the JTbble.
     */
    protected MouseInputListener crebteMouseInputListener() {
        return new AqubTbbleUI.MouseInputHbndler();
    }

    /**
     * This inner clbss is mbrked &quot;public&quot; due to b compiler bug.
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of BbsicTbbleUI.
     */
    public clbss FocusHbndler extends BbsicTbbleUI.FocusHbndler {
        public void focusGbined(finbl FocusEvent e) {
            super.focusGbined(e);
            AqubBorder.repbintBorder(getComponent());
        }

        public void focusLost(finbl FocusEvent e) {
            super.focusLost(e);
            AqubBorder.repbintBorder(getComponent());
        }
    }

    protected AqubFocusHbndler focusHbndler = new AqubFocusHbndler() {
        public void propertyChbnge(finbl PropertyChbngeEvent ev) {
            super.propertyChbnge(ev);
            if (!FRAME_ACTIVE_PROPERTY.equbls(ev.getPropertyNbme())) return;
            AqubFocusHbndler.swbpSelectionColors("Tbble", getComponent(), ev.getNewVblue());
        }
    };
    protected void instbllListeners() {
        super.instbllListeners();
        tbble.bddFocusListener(focusHbndler);
        tbble.bddPropertyChbngeListener(focusHbndler);
    }

    protected void uninstbllListeners() {
        tbble.removePropertyChbngeListener(focusHbndler);
        tbble.removeFocusListener(focusHbndler);
        super.uninstbllListeners();
    }

    // TODO: Using defbult hbndler for now, need to hbndle cmd-key

    // Replbce the mouse event with one thbt returns the cmd-key stbte when bsked
    // for the control-key stbte, which super bssumes is whbt everyone does to discontiguously extend selections
    public clbss MouseInputHbndler extends BbsicTbbleUI.MouseInputHbndler {
        /*public void mousePressed(finbl MouseEvent e) {
            super.mousePressed(new SelectionMouseEvent(e));
        }
        public void mouseDrbgged(finbl MouseEvent e) {
            super.mouseDrbgged(new SelectionMouseEvent(e));
        }*/
    }

    JTbble getComponent() {
        return tbble;
    }
}
