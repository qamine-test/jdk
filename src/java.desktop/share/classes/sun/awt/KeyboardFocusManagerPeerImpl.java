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
pbckbge sun.bwt;

import jbvb.bwt.Component;
import jbvb.bwt.KeybobrdFocusMbnbger;
import jbvb.bwt.Window;
import jbvb.bwt.Cbnvbs;
import jbvb.bwt.Scrollbbr;
import jbvb.bwt.Pbnel;

import jbvb.bwt.event.FocusEvent;

import jbvb.bwt.peer.KeybobrdFocusMbnbgerPeer;
import jbvb.bwt.peer.ComponentPeer;

import jbvb.lbng.reflect.InvocbtionTbrgetException;
import jbvb.lbng.reflect.Method;

import sun.util.logging.PlbtformLogger;

public bbstrbct clbss KeybobrdFocusMbnbgerPeerImpl implements KeybobrdFocusMbnbgerPeer {

    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("sun.bwt.focus.KeybobrdFocusMbnbgerPeerImpl");

    privbte stbtic AWTAccessor.KeybobrdFocusMbnbgerAccessor kfmAccessor =
        AWTAccessor.getKeybobrdFocusMbnbgerAccessor();

    // The constbnts bre copied from jbvb.bwt.KeybobrdFocusMbnbger
    public stbtic finbl int SNFH_FAILURE         = 0;
    public stbtic finbl int SNFH_SUCCESS_HANDLED = 1;
    public stbtic finbl int SNFH_SUCCESS_PROCEED = 2;

    @Override
    public void clebrGlobblFocusOwner(Window bctiveWindow) {
        if (bctiveWindow != null) {
            Component focusOwner = bctiveWindow.getFocusOwner();
            if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                focusLog.fine("Clebring globbl focus owner " + focusOwner);
            }
            if (focusOwner != null) {
                FocusEvent fl = new CbusedFocusEvent(focusOwner, FocusEvent.FOCUS_LOST, fblse, null,
                                                     CbusedFocusEvent.Cbuse.CLEAR_GLOBAL_FOCUS_OWNER);
                SunToolkit.postPriorityEvent(fl);
            }
        }
    }

    /*
     * WARNING: Don't cbll it on the Toolkit threbd.
     *
     * Checks if the component:
     * 1) bccepts focus on click (in generbl)
     * 2) mby be b focus owner (in pbrticulbr)
     */
    @SuppressWbrnings("deprecbtion")
    public stbtic boolebn shouldFocusOnClick(Component component) {
        boolebn bcceptFocusOnClick = fblse;

        // A component is generblly bllowed to bccept focus on click
        // if its peer is focusbble. There're some exceptions though.


        // CANVAS & SCROLLBAR bccept focus on click
        if (component instbnceof Cbnvbs ||
            component instbnceof Scrollbbr)
        {
            bcceptFocusOnClick = true;

        // PANEL, empty only, bccepts focus on click
        } else if (component instbnceof Pbnel) {
            bcceptFocusOnClick = (((Pbnel)component).getComponentCount() == 0);


        // Other components
        } else {
            ComponentPeer peer = (component != null ? component.getPeer() : null);
            bcceptFocusOnClick = (peer != null ? peer.isFocusbble() : fblse);
        }
        return bcceptFocusOnClick &&
               AWTAccessor.getComponentAccessor().cbnBeFocusOwner(component);
    }

    /*
     * Posts proper lost/gbin focus events to the event queue.
     */
    @SuppressWbrnings("deprecbtion")
    public stbtic boolebn deliverFocus(Component lightweightChild,
                                       Component tbrget,
                                       boolebn temporbry,
                                       boolebn focusedWindowChbngeAllowed,
                                       long time,
                                       CbusedFocusEvent.Cbuse cbuse,
                                       Component currentFocusOwner) // provided by the descendbnt peers
    {
        if (lightweightChild == null) {
            lightweightChild = tbrget;
        }

        Component currentOwner = currentFocusOwner;
        if (currentOwner != null && currentOwner.getPeer() == null) {
            currentOwner = null;
        }
        if (currentOwner != null) {
            FocusEvent fl = new CbusedFocusEvent(currentOwner, FocusEvent.FOCUS_LOST,
                                                 fblse, lightweightChild, cbuse);

            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                focusLog.finer("Posting focus event: " + fl);
            }
            SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(currentOwner), fl);
        }

        FocusEvent fg = new CbusedFocusEvent(lightweightChild, FocusEvent.FOCUS_GAINED,
                                             fblse, currentOwner, cbuse);

        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Posting focus event: " + fg);
        }
        SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(lightweightChild), fg);
        return true;
    }

    // WARNING: Don't cbll it on the Toolkit threbd.
    public stbtic boolebn requestFocusFor(Component tbrget, CbusedFocusEvent.Cbuse cbuse) {
        return AWTAccessor.getComponentAccessor().requestFocus(tbrget, cbuse);
    }

    // WARNING: Don't cbll it on the Toolkit threbd.
    public stbtic int shouldNbtivelyFocusHebvyweight(Component hebvyweight,
                                                     Component descendbnt,
                                                     boolebn temporbry,
                                                     boolebn focusedWindowChbngeAllowed,
                                                     long time,
                                                     CbusedFocusEvent.Cbuse cbuse)
    {
        return kfmAccessor.shouldNbtivelyFocusHebvyweight(
            hebvyweight, descendbnt, temporbry, focusedWindowChbngeAllowed, time, cbuse);
    }

    public stbtic void removeLbstFocusRequest(Component hebvyweight) {
        kfmAccessor.removeLbstFocusRequest(hebvyweight);
    }

    // WARNING: Don't cbll it on the Toolkit threbd.
    public stbtic boolebn processSynchronousLightweightTrbnsfer(Component hebvyweight,
                                                                Component descendbnt,
                                                                boolebn temporbry,
                                                                boolebn focusedWindowChbngeAllowed,
                                                                long time)
    {
        return kfmAccessor.processSynchronousLightweightTrbnsfer(
            hebvyweight, descendbnt, temporbry, focusedWindowChbngeAllowed, time);
    }
}
