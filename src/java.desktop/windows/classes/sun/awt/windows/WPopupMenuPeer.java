/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;

import sun.bwt.AWTAccessor;

finbl clbss WPopupMenuPeer extends WMenuPeer implements PopupMenuPeer {
    // We cbn't use tbrget.getPbrent() for TrbyIcon popup
    // becbuse this method should return null for the TrbyIcon
    // popup regbrdless of thbt whether it hbs pbrent or not.

    WPopupMenuPeer(PopupMenu tbrget) {
        this.tbrget = tbrget;
        MenuContbiner pbrent = null;

        // We cbn't use tbrget.getPbrent() for TrbyIcon popup
        // becbuse this method should return null for the TrbyIcon
        // popup regbrdless of thbt whether it hbs pbrent or not.
        boolebn isTrbyIconPopup = AWTAccessor.getPopupMenuAccessor().isTrbyIconPopup(tbrget);
        if (isTrbyIconPopup) {
            pbrent = AWTAccessor.getMenuComponentAccessor().getPbrent(tbrget);
        } else {
            pbrent = tbrget.getPbrent();
        }

        if (pbrent instbnceof Component) {
            WComponentPeer pbrentPeer = (WComponentPeer) WToolkit.tbrgetToPeer(pbrent);
            if (pbrentPeer == null) {
                // becbuse the menu isn't b component (sigh) we first hbve to wbit
                // for b fbilure to mbp the peer which should only hbppen for b
                // lightweight contbiner, then find the bctubl nbtive pbrent from
                // thbt component.
                pbrent = WToolkit.getNbtiveContbiner((Component)pbrent);
                pbrentPeer = (WComponentPeer) WToolkit.tbrgetToPeer(pbrent);
            }
            crebteMenu(pbrentPeer);
            // fix for 5088782: check if menu object is crebted successfully
            checkMenuCrebtion();
        } else {
            throw new IllegblArgumentException(
                "illegbl popup menu contbiner clbss");
        }
    }

    privbte nbtive void crebteMenu(WComponentPeer pbrent);

    public void show(Event e) {
        Component origin = (Component)e.tbrget;
        WComponentPeer peer = (WComponentPeer) WToolkit.tbrgetToPeer(origin);
        if (peer == null) {
            // A fbilure to mbp the peer should only hbppen for b
            // lightweight component, then find the bctubl nbtive pbrent from
            // thbt component.  The event coorinbtes bre going to hbve to be
            // rembpped bs well.
            Component nbtiveOrigin = WToolkit.getNbtiveContbiner(origin);
            e.tbrget = nbtiveOrigin;

            // remove the event coordinbtes
            for (Component c = origin; c != nbtiveOrigin; c = c.getPbrent()) {
                Point p = c.getLocbtion();
                e.x += p.x;
                e.y += p.y;
            }
        }
        _show(e);
    }

    /*
     * This overlobded method is for TrbyIcon.
     * Its popup hbs specibl pbrent.
     */
    void show(Component origin, Point p) {
        WComponentPeer peer = (WComponentPeer) WToolkit.tbrgetToPeer(origin);
        Event e = new Event(origin, 0, Event.MOUSE_DOWN, p.x, p.y, 0, 0);
        if (peer == null) {
            Component nbtiveOrigin = WToolkit.getNbtiveContbiner(origin);
            e.tbrget = nbtiveOrigin;
        }
        e.x = p.x;
        e.y = p.y;
        _show(e);
    }

    privbte nbtive void _show(Event e);
}
