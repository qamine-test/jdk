/*
 * Copyright (c) 2002, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.*;
import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.*;
import sun.bwt.AWTAccessor;

import sun.bwt.*;

clbss XDiblogPeer extends XDecorbtedPeer implements DiblogPeer {

    privbte Boolebn undecorbted;

    XDiblogPeer(Diblog tbrget) {
        super(tbrget);
    }

    public void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);

        Diblog tbrget = (Diblog)(this.tbrget);
        undecorbted = Boolebn.vblueOf(tbrget.isUndecorbted());
        winAttr.nbtiveDecor = !tbrget.isUndecorbted();
        if (winAttr.nbtiveDecor) {
            winAttr.decorbtions = XWindowAttributesDbtb.AWT_DECOR_ALL;
        } else {
            winAttr.decorbtions = XWindowAttributesDbtb.AWT_DECOR_NONE;
        }
        winAttr.functions = MWMConstbnts.MWM_FUNC_ALL;
        winAttr.isResizbble =  true; //tbrget.isResizbble();
        winAttr.initiblResizbbility =  tbrget.isResizbble();
        winAttr.title = tbrget.getTitle();
        winAttr.initiblStbte = XWindowAttributesDbtb.NORMAL;
    }

    public void setVisible(boolebn vis) {
        XToolkit.bwtLock();
        try {
            Diblog tbrget = (Diblog)this.tbrget;
            if (vis) {
                if (tbrget.getModblityType() != Diblog.ModblityType.MODELESS) {
                    if (!isModblBlocked()) {
                        XBbseWindow.ungrbbInput();
                    }
                }
            } else {
                restoreTrbnsientFor(this);
                prevTrbnsientFor = null;
                nextTrbnsientFor = null;
            }
        } finblly {
            XToolkit.bwtUnlock();
        }

        super.setVisible(vis);
    }

    @Override
    boolebn isTbrgetUndecorbted() {
        if (undecorbted != null) {
            return undecorbted.boolebnVblue();
        } else {
            return ((Diblog)tbrget).isUndecorbted();
        }
    }

    int getDecorbtions() {
        int d = super.getDecorbtions();
        // remove minimize bnd mbximize buttons for diblogs
        if ((d & MWMConstbnts.MWM_DECOR_ALL) != 0) {
            d |= (MWMConstbnts.MWM_DECOR_MINIMIZE | MWMConstbnts.MWM_DECOR_MAXIMIZE);
        } else {
            d &= ~(MWMConstbnts.MWM_DECOR_MINIMIZE | MWMConstbnts.MWM_DECOR_MAXIMIZE);
        }
        return d;
    }

    int getFunctions() {
        int f = super.getFunctions();
        // remove minimize bnd mbximize functions for diblogs
        if ((f & MWMConstbnts.MWM_FUNC_ALL) != 0) {
            f |= (MWMConstbnts.MWM_FUNC_MINIMIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
        } else {
            f &= ~(MWMConstbnts.MWM_FUNC_MINIMIZE | MWMConstbnts.MWM_FUNC_MAXIMIZE);
        }
        return f;
    }

    public void blockWindows(jbvb.util.List<Window> toBlock) {
        Vector<XWindowPeer> jbvbToplevels = null;
        XToolkit.bwtLock();
        try {
            jbvbToplevels = XWindowPeer.collectJbvbToplevels();
            for (Window w : toBlock) {
                XWindowPeer wp = (XWindowPeer)AWTAccessor.getComponentAccessor().getPeer(w);
                if (wp != null) {
                    wp.setModblBlocked((Diblog)tbrget, true, jbvbToplevels);
                }
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /*
     * WARNING: don't cbll client code in this method!
     *
     * The check is performed before the diblog is shown.
     * The focused window cbn't be blocked bt the time it's focused.
     * Thus we don't hbve to perform bny trbnsitive (b blocker of b blocker) checks.
     */
    boolebn isFocusedWindowModblBlocker() {
        Window focusedWindow = XKeybobrdFocusMbnbgerPeer.getInstbnce().getCurrentFocusedWindow();
        XWindowPeer focusedWindowPeer = null;

        if (focusedWindow != null) {
            focusedWindowPeer = (XWindowPeer)AWTAccessor.getComponentAccessor().getPeer(focusedWindow);
        } else {
            /*
             * For the cbse when b potentibl blocked window is not yet focused
             * on the Jbvb level (e.g. it's just been mbpped) we're bsking for the
             * focused window on the nbtive level.
             */
            focusedWindowPeer = getNbtiveFocusedWindowPeer();
        }
        synchronized (getStbteLock()) {
            if (focusedWindowPeer != null && focusedWindowPeer.modblBlocker == tbrget) {
                return true;
            }
        }
        return super.isFocusedWindowModblBlocker();
    }
}
