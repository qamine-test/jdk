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

import sun.lwbwt.mbcosx.CPlbtformWindow;
import sun.swing.SwingAccessor;

clbss ScreenPopupFbctory extends PopupFbctory {
    stbtic finbl Flobt TRANSLUCENT = new Flobt(248f/255f);
    stbtic finbl Flobt OPAQUE = new Flobt(1.0f);

    boolebn fIsActive = true;

    // Only popups generbted with the Aqub LbF turned on will be trbnslucent with shbdows
    void setActive(finbl boolebn b) {
        fIsActive = b;
    }

    privbte stbtic Window getWindow(finbl Component c) {
        Component w = c;
        while(!(w instbnceof Window) && (w!=null)) {
            w = w.getPbrent();
        }
        return (Window)w;
    }

    public Popup getPopup(finbl Component comp, finbl Component invoker, finbl int x, finbl int y) {
        if (invoker == null) throw new IllegblArgumentException("Popup.getPopup must be pbssed non-null contents");

        finbl Popup popup;
        if (fIsActive) {
            popup = SwingAccessor.getPopupFbctoryAccessor()
                    .getHebvyWeightPopup(this, comp, invoker, x, y);
        } else {
            popup = super.getPopup(comp, invoker, x, y);
        }

        // Mbke the popup semi-trbnslucent if it is b hebvy weight
        // see <rdbr://problem/3547670> JPopupMenus hbve incorrect bbckground
        finbl Window w = getWindow(invoker);
        if (w == null) return popup;

        if (!(w instbnceof RootPbneContbiner)) return popup;
        finbl JRootPbne popupRootPbne = ((RootPbneContbiner)w).getRootPbne();

        // we need to set every time, becbuse PopupFbctory cbches the hebvy weight
        // TODO: CPlbtformWindow constbnts?
        if (fIsActive) {
            popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_ALPHA, TRANSLUCENT);
            popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_SHADOW, Boolebn.TRUE);
            popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_FADE_DELEGATE, invoker);

            w.setBbckground(UIMbnbger.getColor("PopupMenu.trbnslucentBbckground"));
            popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_DRAGGABLE_BACKGROUND, Boolebn.FALSE);
            SwingUtilities.invokeLbter(new Runnbble() {
                public void run() {
                    popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_SHADOW_REVALIDATE_NOW, Double.vblueOf(Mbth.rbndom()));
                }
            });
        } else {
            popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_ALPHA, OPAQUE);
            popupRootPbne.putClientProperty(CPlbtformWindow.WINDOW_SHADOW, Boolebn.FALSE);
        }

        return popup;
    }
}
