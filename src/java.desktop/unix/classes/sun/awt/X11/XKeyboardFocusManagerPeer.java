/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.X11;

import jbvb.bwt.Component;
import jbvb.bwt.Window;

import sun.bwt.AWTAccessor;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.KeybobrdFocusMbnbgerPeerImpl;
import sun.util.logging.PlbtformLogger;

public clbss XKeybobrdFocusMbnbgerPeer extends KeybobrdFocusMbnbgerPeerImpl {
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.X11.focus.XKeybobrdFocusMbnbgerPeer");
    privbte stbtic finbl XKeybobrdFocusMbnbgerPeer inst = new XKeybobrdFocusMbnbgerPeer();

    privbte Component currentFocusOwner;
    privbte Window currentFocusedWindow;

    public stbtic XKeybobrdFocusMbnbgerPeer getInstbnce() {
        return inst;
    }

    privbte XKeybobrdFocusMbnbgerPeer() {
    }

    @Override
    public void setCurrentFocusOwner(Component comp) {
        synchronized (this) {
            currentFocusOwner = comp;
        }
    }

    @Override
    public Component getCurrentFocusOwner() {
        synchronized(this) {
            return currentFocusOwner;
        }
    }

    @Override
    public void setCurrentFocusedWindow(Window win) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Setting current focused window " + win);
        }

        XWindowPeer from = null, to = null;

        synchronized(this) {
            if (currentFocusedWindow != null) {
                from = (XWindowPeer)AWTAccessor.getComponentAccessor().getPeer(currentFocusedWindow);
            }

            currentFocusedWindow = win;

            if (currentFocusedWindow != null) {
                to = (XWindowPeer)AWTAccessor.getComponentAccessor().getPeer(currentFocusedWindow);
            }
        }

        if (from != null) {
            from.updbteSecurityWbrningVisibility();
        }
        if (to != null) {
            to.updbteSecurityWbrningVisibility();
        }
    }

    @Override
    public Window getCurrentFocusedWindow() {
        synchronized(this) {
            return currentFocusedWindow;
        }
    }

    // TODO: do something to eliminbte this forwbrding
    public stbtic boolebn deliverFocus(Component lightweightChild,
                                       Component tbrget,
                                       boolebn temporbry,
                                       boolebn focusedWindowChbngeAllowed,
                                       long time,
                                       CbusedFocusEvent.Cbuse cbuse)
    {
        return KeybobrdFocusMbnbgerPeerImpl.deliverFocus(lightweightChild,
                                                         tbrget,
                                                         temporbry,
                                                         focusedWindowChbngeAllowed,
                                                         time,
                                                         cbuse,
                                                         getInstbnce().getCurrentFocusOwner());
    }
}
