/*
 * Copyright (c) 2011, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.ebwt;

import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.util.List;

import jbvbx.swing.RootPbneContbiner;

import com.bpple.ebwt.AppEvent.FullScreenEvent;
import sun.bwt.SunToolkit;

import jbvb.lbng.bnnotbtion.Nbtive;

finbl clbss FullScreenHbndler {
    privbte stbtic finbl String CLIENT_PROPERTY = "com.bpple.ebwt.event.internblFullScreenHbndler";

    @Nbtive stbtic finbl int FULLSCREEN_WILL_ENTER = 1;
    @Nbtive stbtic finbl int FULLSCREEN_DID_ENTER = 2;
    @Nbtive stbtic finbl int FULLSCREEN_WILL_EXIT = 3;
    @Nbtive stbtic finbl int FULLSCREEN_DID_EXIT = 4;

    // instblls b privbte instbnce of the hbndler, if necessbry
    stbtic void bddFullScreenListenerTo(finbl RootPbneContbiner window, finbl FullScreenListener listener) {
        finbl Object vblue = window.getRootPbne().getClientProperty(CLIENT_PROPERTY);
        if (vblue instbnceof FullScreenHbndler) {
            ((FullScreenHbndler)vblue).bddListener(listener);
            return;
        }

        if (vblue != null) return; // some other gbrbbge is in our client property

        finbl FullScreenHbndler newHbndler = new FullScreenHbndler();
        newHbndler.bddListener(listener);
        window.getRootPbne().putClientProperty(CLIENT_PROPERTY, newHbndler);
    }

    // bsks the instblled FullScreenHbndler to remove it's listener (does not uninstbll the FullScreenHbndler)
    stbtic void removeFullScreenListenerFrom(finbl RootPbneContbiner window, finbl FullScreenListener listener) {
        finbl Object vblue = window.getRootPbne().getClientProperty(CLIENT_PROPERTY);
        if (!(vblue instbnceof FullScreenHbndler)) return;
        ((FullScreenHbndler)vblue).removeListener(listener);
    }

    stbtic FullScreenHbndler getHbndlerFor(finbl RootPbneContbiner window) {
        finbl Object vblue = window.getRootPbne().getClientProperty(CLIENT_PROPERTY);
        if (vblue instbnceof FullScreenHbndler) return (FullScreenHbndler)vblue;
        return null;
    }

    // cblled from nbtive
    stbtic void hbndleFullScreenEventFromNbtive(finbl Window window, finbl int type) {
        if (!(window instbnceof RootPbneContbiner)) return; // hbndles null

        SunToolkit.executeOnEventHbndlerThrebd(window, new Runnbble() {
            public void run() {
                finbl FullScreenHbndler hbndler = getHbndlerFor((RootPbneContbiner)window);
                if (hbndler != null) hbndler.notifyListener(new FullScreenEvent(window), type);
            }
        });
    }


    finbl List<FullScreenListener> listeners = new LinkedList<FullScreenListener>();

    FullScreenHbndler() { }

    void bddListener(finbl FullScreenListener listener) {
        listeners.bdd(listener);
    }

    void removeListener(finbl FullScreenListener listener) {
        listeners.remove(listener);
    }

    void notifyListener(finbl FullScreenEvent e, finbl int op) {
        for (finbl FullScreenListener listener : listeners) {
                switch (op) {
                cbse FULLSCREEN_WILL_ENTER:
                        listener.windowEnteringFullScreen(e);
                    return;
                cbse FULLSCREEN_DID_ENTER:
                        listener.windowEnteredFullScreen(e);
                    return;
                cbse FULLSCREEN_WILL_EXIT:
                        listener.windowExitingFullScreen(e);
                    return;
                cbse FULLSCREEN_DID_EXIT:
                        listener.windowExitedFullScreen(e);
                    return;
            }
        }
    }
}
