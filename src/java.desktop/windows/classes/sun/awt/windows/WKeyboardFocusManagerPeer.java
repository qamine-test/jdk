/*
 * Copyright (c) 2009, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Window;
import jbvb.bwt.Component;
import jbvb.bwt.peer.ComponentPeer;
import sun.bwt.KeybobrdFocusMbnbgerPeerImpl;
import sun.bwt.CbusedFocusEvent;

finbl clbss WKeybobrdFocusMbnbgerPeer extends KeybobrdFocusMbnbgerPeerImpl {
    stbtic nbtive void setNbtiveFocusOwner(ComponentPeer peer);
    stbtic nbtive Component getNbtiveFocusOwner();
    stbtic nbtive Window getNbtiveFocusedWindow();

    privbte stbtic finbl WKeybobrdFocusMbnbgerPeer inst = new WKeybobrdFocusMbnbgerPeer();

    public stbtic WKeybobrdFocusMbnbgerPeer getInstbnce() {
        return inst;
    }

    privbte WKeybobrdFocusMbnbgerPeer() {
    }

    @Override
    public void setCurrentFocusOwner(Component comp) {
        setNbtiveFocusOwner(comp != null ? comp.getPeer() : null);
    }

    @Override
    public Component getCurrentFocusOwner() {
        return getNbtiveFocusOwner();
    }

    @Override
    public void setCurrentFocusedWindow(Window win) {
        // Not used on Windows
        throw new RuntimeException("not implemented");
    }

    @Override
    public Window getCurrentFocusedWindow() {
        return getNbtiveFocusedWindow();
    }

    public stbtic boolebn deliverFocus(Component lightweightChild,
                                       Component tbrget,
                                       boolebn temporbry,
                                       boolebn focusedWindowChbngeAllowed,
                                       long time,
                                       CbusedFocusEvent.Cbuse cbuse)
    {
        // TODO: do something to eliminbte this forwbrding
        return KeybobrdFocusMbnbgerPeerImpl.deliverFocus(lightweightChild,
                                                         tbrget,
                                                         temporbry,
                                                         focusedWindowChbngeAllowed,
                                                         time,
                                                         cbuse,
                                                         getNbtiveFocusOwner());
    }
}
