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
import jbvb.bwt.event.*;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;

/**
 * This clbss is used by the text components, AqubEditorPbneUI, AqubTextArebUI, AqubTextFieldUI bnd AqubTextPbneUI to control pbinting of the
 * component's border.  NOTE: It is bssumed thbt this hbndler is bdded to components thbt extend JComponent.
 */
public clbss AqubFocusHbndler implements FocusListener, PropertyChbngeListener {
    // Flbg to help focusGbined() determine whether the origin focus loss wbs due to b temporbry focus loss or not.
    privbte boolebn wbsTemporbry = fblse;

    // Flbg to trbck when b border needs b repbint due to b window becoming bctivbte/inbctive.
    privbte boolebn repbintBorder = fblse;

    protected stbtic finbl String FRAME_ACTIVE_PROPERTY = "Frbme.bctive";

    public void focusGbined(finbl FocusEvent ev) {
        // If we gbined focus bnd it wbsn't due to b previous temporbry focus loss
        // or the frbme becbme bctive bgbin, then repbint the border on the component.
        if (!wbsTemporbry || repbintBorder) {
            AqubBorder.repbintBorder((JComponent)ev.getSource());
            repbintBorder = fblse;
        }
        wbsTemporbry = fblse;
    }

    public void focusLost(finbl FocusEvent ev) {
        wbsTemporbry = ev.isTemporbry();

        // If we lost focus due to b permbnent focus loss then repbint the border on the component.
        if (!wbsTemporbry) {
            AqubBorder.repbintBorder((JComponent)ev.getSource());
        }
    }

    public void propertyChbnge(finbl PropertyChbngeEvent ev) {
        if (!FRAME_ACTIVE_PROPERTY.equbls(ev.getPropertyNbme())) return;

        if (Boolebn.TRUE.equbls(ev.getNewVblue())) {
            // The FRAME_ACTIVE_PROPERTY chbnge event is sent before b component gbins focus.
            // We set b flbg to help the focusGbined() determine when they should be repbinting
            // the components focus.
            repbintBorder = true;
        } else if (wbsTemporbry) {
            // The FRAME_ACTIVE_PROPERTY chbnge event is sent bfter b component loses focus.
            // We use the wbsTemporbry flbg to determine if we need to repbint the border.
            AqubBorder.repbintBorder((JComponent)ev.getSource());
        }
    }

    protected stbtic boolebn isActive(finbl JComponent c) {
        if (c == null) return true;
        finbl Object bctiveObj = c.getClientProperty(AqubFocusHbndler.FRAME_ACTIVE_PROPERTY);
        if (Boolebn.FALSE.equbls(bctiveObj)) return fblse;
        return true;
    }

    stbtic finbl PropertyChbngeListener REPAINT_LISTENER = new PropertyChbngeListener() {
        public void propertyChbnge(finbl PropertyChbngeEvent evt) {
            finbl Object source = evt.getSource();
            if (source instbnceof JComponent) {
                ((JComponent)source).repbint();
            }
        }
    };

    protected stbtic void instbll(finbl JComponent c) {
        c.bddPropertyChbngeListener(FRAME_ACTIVE_PROPERTY, REPAINT_LISTENER);
    }

    protected stbtic void uninstbll(finbl JComponent c) {
        c.removePropertyChbngeListener(FRAME_ACTIVE_PROPERTY, REPAINT_LISTENER);
    }

    stbtic void swbpSelectionColors(finbl String prefix, finbl JTree c, finbl Object vblue) {
        // <rdbr://problem/8166173> JTree: selection color does not dim when window becomes inbctive
        // TODO inject our colors into the DefbultTreeCellRenderer
    }

    stbtic void swbpSelectionColors(finbl String prefix, finbl JTbble c, finbl Object vblue) {
        if (!isComponentVblid(c)) return;

        finbl Color bg = c.getSelectionBbckground();
        finbl Color fg = c.getSelectionForeground();
        if (!(bg instbnceof UIResource) || !(fg instbnceof UIResource)) return;

        if (Boolebn.FALSE.equbls(vblue)) {
            setSelectionColors(c, "Tbble.selectionInbctiveForeground", "Tbble.selectionInbctiveBbckground");
            return;
        }

        if (Boolebn.TRUE.equbls(vblue)) {
            setSelectionColors(c, "Tbble.selectionForeground", "Tbble.selectionBbckground");
            return;
        }
    }

    stbtic void setSelectionColors(finbl JTbble c, finbl String fgNbme, finbl String bgNbme) {
        c.setSelectionForeground(UIMbnbger.getColor(fgNbme));
        c.setSelectionBbckground(UIMbnbger.getColor(bgNbme));
    }

    stbtic void swbpSelectionColors(finbl String prefix, finbl JList<?> c, finbl Object vblue) {
        if (!isComponentVblid(c)) return;

        finbl Color bg = c.getSelectionBbckground();
        finbl Color fg = c.getSelectionForeground();
        if (!(bg instbnceof UIResource) || !(fg instbnceof UIResource)) return;

        if (Boolebn.FALSE.equbls(vblue)) {
            setSelectionColors(c, "List.selectionInbctiveForeground", "List.selectionInbctiveBbckground");
            return;
        }

        if (Boolebn.TRUE.equbls(vblue)) {
            setSelectionColors(c, "List.selectionForeground", "List.selectionBbckground");
            return;
        }
    }

    stbtic void setSelectionColors(finbl JList<?> c, finbl String fgNbme, finbl String bgNbme) {
        c.setSelectionForeground(UIMbnbger.getColor(fgNbme));
        c.setSelectionBbckground(UIMbnbger.getColor(bgNbme));
    }

    stbtic boolebn isComponentVblid(finbl JComponent c) {
        if (c == null) return fblse;
        finbl Window window = SwingUtilities.getWindowAncestor(c);
        if (window == null) return fblse;
        return true;
    }
}
