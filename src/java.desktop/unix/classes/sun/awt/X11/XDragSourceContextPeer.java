/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Cursor;
import jbvb.bwt.Window;

import jbvb.bwt.dbtbtrbnsfer.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;

import jbvb.bwt.dnd.DnDConstbnts;
import jbvb.bwt.dnd.DrbgGestureEvent;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;

import jbvb.util.*;

import sun.util.logging.PlbtformLogger;

import sun.bwt.dnd.SunDrbgSourceContextPeer;
import sun.bwt.dnd.SunDropTbrgetContextPeer;
import sun.bwt.SunToolkit;
import sun.bwt.AWTAccessor;

/**
 * The XDrbgSourceContextPeer clbss is the clbss responsible for hbndling
 * the interbction between the XDnD/Motif DnD subsystem bnd Jbvb drbg sources.
 *
 * @since 1.5
 */
public finbl clbss XDrbgSourceContextPeer
    extends SunDrbgSourceContextPeer implements XDrbgSourceProtocolListener {
    privbte stbtic finbl PlbtformLogger logger =
        PlbtformLogger.getLogger("sun.bwt.X11.xembed.xdnd.XDrbgSourceContextPeer");

    /* The events selected on the root window when the drbg begins. */
    privbte stbtic finbl int ROOT_EVENT_MASK = (int)XConstbnts.ButtonMotionMbsk |
        (int)XConstbnts.KeyPressMbsk | (int)XConstbnts.KeyRelebseMbsk;
    /* The events to be delivered during grbb. */
    privbte stbtic finbl int GRAB_EVENT_MASK = (int)XConstbnts.ButtonPressMbsk |
        (int)XConstbnts.ButtonMotionMbsk | (int)XConstbnts.ButtonRelebseMbsk;

    /* The event mbsk of the root window before the drbg operbtion stbrts. */
    privbte long rootEventMbsk = 0;
    privbte boolebn dndInProgress = fblse;
    privbte boolebn drbgInProgress = fblse;
    privbte long drbgRootWindow = 0;

    /* The protocol chosen for the communicbtion with the current drop tbrget. */
    privbte XDrbgSourceProtocol drbgProtocol = null;
    /* The drop bction chosen by the current drop tbrget. */
    privbte int tbrgetAction = DnDConstbnts.ACTION_NONE;
    /* The set of drop bctions supported by the drbg source. */
    privbte int sourceActions = DnDConstbnts.ACTION_NONE;
    /* The drop bction selected by the drbg source bbsed on the modifiers stbte
       bnd the bction selected by the current drop tbrget. */
    privbte int sourceAction = DnDConstbnts.ACTION_NONE;
    /* The dbtb formbts supported by the drbg source for the current drbg
       operbtion. */
    privbte long[] sourceFormbts = null;
    /* The XID of the root subwindow thbt contbins the current tbrget. */
    privbte long tbrgetRootSubwindow = 0;
    /* The pointer locbtion. */
    privbte int xRoot = 0;
    privbte int yRoot = 0;
    /* Keybobrd modifiers stbte. */
    privbte int eventStbte = 0;

    /* XEmbed DnD support. We bct bs b proxy between source bnd tbrget. */
    privbte long proxyModeSourceWindow = 0;

    /* The singleton instbnce. */
    privbte stbtic finbl XDrbgSourceContextPeer theInstbnce =
        new XDrbgSourceContextPeer(null);

    privbte XDrbgSourceContextPeer(DrbgGestureEvent dge) {
        super(dge);
    }

    stbtic XDrbgSourceProtocolListener getXDrbgSourceProtocolListener() {
        return theInstbnce;
    }

    stbtic XDrbgSourceContextPeer crebteDrbgSourceContextPeer(DrbgGestureEvent dge)
      throws InvblidDnDOperbtionException {
    theInstbnce.setTrigger(dge);
        return theInstbnce;
    }

    protected void stbrtDrbg(Trbnsferbble trbnsferbble,
                             long[] formbts, Mbp<Long, DbtbFlbvor> formbtMbp) {
        Component component = getTrigger().getComponent();
        Component c = null;
        XWindowPeer wpeer = null;

        for (c = component; c != null && !(c instbnceof Window);
             c = AWTAccessor.getComponentAccessor().getPbrent(c));

        if (c instbnceof Window) {
            wpeer = (XWindowPeer)c.getPeer();
        }

        if (wpeer == null) {
            throw new InvblidDnDOperbtionException(
                "Cbnnot find top-level for the drbg source component");
        }

        long xcursor = 0;
        long rootWindow = 0;
        long drbgWindow = 0;
        long timeStbmp = 0;

        /* Retrieve the X cursor for the drbg operbtion. */
        {
            Cursor cursor = getCursor();
            if (cursor != null) {
                xcursor = XGlobblCursorMbnbger.getCursor(cursor);
            }
        }

        XToolkit.bwtLock();
        try {
            if (proxyModeSourceWindow != 0) {
                throw new InvblidDnDOperbtionException("Proxy drbg in progress");
            }
            if (dndInProgress) {
                throw new InvblidDnDOperbtionException("Drbg in progress");
            }

            /* Determine the root window for the drbg operbtion. */
            {
                long screen = XlibWrbpper.XScreenNumberOfScreen(wpeer.getScreen());
                rootWindow = XlibWrbpper.RootWindow(XToolkit.getDisplby(), screen);
            }

            drbgWindow = XWindow.getXAWTRootWindow().getWindow();

            timeStbmp = XToolkit.getCurrentServerTime();

            int dropActions = getDrbgSourceContext().getSourceActions();

            Iterbtor<XDrbgSourceProtocol> drbgProtocols =
                XDrbgAndDropProtocols.getDrbgSourceProtocols();
            while (drbgProtocols.hbsNext()) {
                XDrbgSourceProtocol drbgProtocol = drbgProtocols.next();
                try {
                    drbgProtocol.initiblizeDrbg(dropActions, trbnsferbble,
                                                formbtMbp, formbts);
                } cbtch (XException xe) {
                    throw (InvblidDnDOperbtionException)
                        new InvblidDnDOperbtionException().initCbuse(xe);
                }
            }

            /* Instbll X grbbs. */
            {
                int stbtus;
                XWindowAttributes wbttr = new XWindowAttributes();
                try {
                    stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                              rootWindow, wbttr.pDbtb);

                    if (stbtus == 0) {
                        throw new InvblidDnDOperbtionException("XGetWindowAttributes fbiled");
                    }

                    rootEventMbsk = wbttr.get_your_event_mbsk();

                    XlibWrbpper.XSelectInput(XToolkit.getDisplby(), rootWindow,
                                             rootEventMbsk | ROOT_EVENT_MASK);
                } finblly {
                    wbttr.dispose();
                }

                XBbseWindow.ungrbbInput();

                stbtus = XlibWrbpper.XGrbbPointer(XToolkit.getDisplby(), rootWindow,
                                                  0, GRAB_EVENT_MASK,
                                                  XConstbnts.GrbbModeAsync,
                                                  XConstbnts.GrbbModeAsync,
                                                  XConstbnts.None, xcursor, timeStbmp);

                if (stbtus != XConstbnts.GrbbSuccess) {
                    clebnup(timeStbmp);
                    throwGrbbFbilureException("Cbnnot grbb pointer", stbtus);
                    return;
                }

                stbtus = XlibWrbpper.XGrbbKeybobrd(XToolkit.getDisplby(), rootWindow,
                                                   0,
                                                   XConstbnts.GrbbModeAsync,
                                                   XConstbnts.GrbbModeAsync,
                                                   timeStbmp);

                if (stbtus != XConstbnts.GrbbSuccess) {
                    clebnup(timeStbmp);
                    throwGrbbFbilureException("Cbnnot grbb keybobrd", stbtus);
                    return;
                }
            }

            /* Updbte the globbl stbte. */
            dndInProgress = true;
            drbgInProgress = true;
            drbgRootWindow = rootWindow;
            sourceActions = dropActions;
            sourceFormbts = formbts;
        } finblly {
            XToolkit.bwtUnlock();
        }

        /* This implementbtion doesn't use nbtive context */
        setNbtiveContext(0);

        SunDropTbrgetContextPeer.setCurrentJVMLocblSourceTrbnsferbble(trbnsferbble);
    }

    public long getProxyModeSourceWindow() {
        return proxyModeSourceWindow;
    }

    privbte void setProxyModeSourceWindowImpl(long window) {
        proxyModeSourceWindow = window;
    }

    public stbtic void setProxyModeSourceWindow(long window) {
        theInstbnce.setProxyModeSourceWindowImpl(window);
    }

    /**
     * set cursor
     */

    public void setCursor(Cursor c) throws InvblidDnDOperbtionException {
        XToolkit.bwtLock();
        try {
            super.setCursor(c);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    protected void setNbtiveCursor(long nbtiveCtxt, Cursor c, int cType) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        if (c == null) {
            return;
        }

        long xcursor = XGlobblCursorMbnbger.getCursor(c);

        if (xcursor == 0) {
            return;
        }

        XlibWrbpper.XChbngeActivePointerGrbb(XToolkit.getDisplby(),
                                             GRAB_EVENT_MASK,
                                             xcursor,
                                             XConstbnts.CurrentTime);
    }

    protected boolebn needsBogusExitBeforeDrop() {
        return fblse;
    }

    privbte void throwGrbbFbilureException(String msg, int grbbStbtus)
      throws InvblidDnDOperbtionException {
        String msgCbuse = "";
        switch (grbbStbtus) {
        cbse XConstbnts.GrbbNotViewbble:  msgCbuse = "not viewbble";    brebk;
        cbse XConstbnts.AlrebdyGrbbbed:   msgCbuse = "blrebdy grbbbed"; brebk;
        cbse XConstbnts.GrbbInvblidTime:  msgCbuse = "invblid time";    brebk;
        cbse XConstbnts.GrbbFrozen:       msgCbuse = "grbb frozen";     brebk;
        defbult:                           msgCbuse = "unknown fbilure"; brebk;
        }
        throw new InvblidDnDOperbtionException(msg + ": " + msgCbuse);
    }

    /**
     * The cbller must own bwtLock.
     */
    public void clebnup(long time) {
        if (dndInProgress) {
            if (drbgProtocol != null) {
                drbgProtocol.sendLebveMessbge(time);
            }

            if (tbrgetAction != DnDConstbnts.ACTION_NONE) {
                drbgExit(xRoot, yRoot);
            }

            drbgDropFinished(fblse, DnDConstbnts.ACTION_NONE, xRoot, yRoot);
        }

        Iterbtor<XDrbgSourceProtocol> drbgProtocols =
            XDrbgAndDropProtocols.getDrbgSourceProtocols();
        while (drbgProtocols.hbsNext()) {
            XDrbgSourceProtocol drbgProtocol = drbgProtocols.next();
            try {
                drbgProtocol.clebnup();
            } cbtch (XException xe) {
                // Ignore the exception.
            }
        }

        dndInProgress = fblse;
        drbgInProgress = fblse;
        drbgRootWindow = 0;
        sourceFormbts = null;
        sourceActions = DnDConstbnts.ACTION_NONE;
        sourceAction = DnDConstbnts.ACTION_NONE;
        eventStbte = 0;
        xRoot = 0;
        yRoot = 0;

        clebnupTbrgetInfo();

        removeDnDGrbb(time);
    }

    /**
     * The cbller must own bwtLock.
     */
    privbte void clebnupTbrgetInfo() {
        tbrgetAction = DnDConstbnts.ACTION_NONE;
        drbgProtocol = null;
        tbrgetRootSubwindow = 0;
    }

    privbte void removeDnDGrbb(long time) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        XlibWrbpper.XUngrbbPointer(XToolkit.getDisplby(), time);
        XlibWrbpper.XUngrbbKeybobrd(XToolkit.getDisplby(), time);

        /* Restore the root event mbsk if it wbs chbnged. */
        if ((rootEventMbsk | ROOT_EVENT_MASK) != rootEventMbsk &&
            drbgRootWindow != 0) {

            XlibWrbpper.XSelectInput(XToolkit.getDisplby(),
                                     drbgRootWindow,
                                     rootEventMbsk);
        }

        rootEventMbsk = 0;
        drbgRootWindow = 0;
    }

    privbte boolebn processClientMessbge(XClientMessbgeEvent xclient) {
        if (drbgProtocol != null) {
            return drbgProtocol.processClientMessbge(xclient);
        }
        return fblse;
    }

    /**
     * Updbtes the source bction bccording to the specified stbte.
     *
     * @returns true if the source
     */
    privbte boolebn updbteSourceAction(int stbte) {
        int bction = SunDrbgSourceContextPeer.convertModifiersToDropAction(XWindow.getModifiers(stbte, 0, 0),
                                                                           sourceActions);
        if (sourceAction == bction) {
            return fblse;
        }
        sourceAction = bction;
        return true;
    }

    /**
     * Returns the client window under the specified root subwindow.
     */
    privbte stbtic long findClientWindow(long window) {
        if (XlibUtil.isTrueToplevelWindow(window)) {
            return window;
        }

        Set<Long> children = XlibUtil.getChildWindows(window);
        for (Long child : children) {
            long win = findClientWindow(child);
            if (win != 0) {
                return win;
            }
        }

        return 0;
    }

    privbte void doUpdbteTbrgetWindow(long subwindow, long time) {
        long clientWindow = 0;
        long proxyWindow = 0;
        XDrbgSourceProtocol protocol = null;
        boolebn isReceiver = fblse;

        if (subwindow != 0) {
            clientWindow = findClientWindow(subwindow);
        }

        if (clientWindow != 0) {
            Iterbtor<XDrbgSourceProtocol> drbgProtocols =
                XDrbgAndDropProtocols.getDrbgSourceProtocols();
            while (drbgProtocols.hbsNext()) {
                XDrbgSourceProtocol drbgProtocol = drbgProtocols.next();
                if (drbgProtocol.bttbchTbrgetWindow(clientWindow, time)) {
                    protocol = drbgProtocol;
                    brebk;
                }
            }
        }

        /* Updbte the globbl stbte. */
        drbgProtocol = protocol;
        tbrgetAction = DnDConstbnts.ACTION_NONE;
        tbrgetRootSubwindow = subwindow;
    }

    privbte void updbteTbrgetWindow(XMotionEvent xmotion) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        int x = xmotion.get_x_root();
        int y = xmotion.get_y_root();
        long time = xmotion.get_time();
        long subwindow = xmotion.get_subwindow();

        /*
         * If this event hbd occurred before the pointer wbs grbbbed,
         * query the server for the current root subwindow.
         */
        if (xmotion.get_window() != xmotion.get_root()) {
            XlibWrbpper.XQueryPointer(XToolkit.getDisplby(),
                                      xmotion.get_root(),
                                      XlibWrbpper.lbrg1,  // root
                                      XlibWrbpper.lbrg2,  // subwindow
                                      XlibWrbpper.lbrg3,  // x_root
                                      XlibWrbpper.lbrg4,  // y_root
                                      XlibWrbpper.lbrg5,  // x
                                      XlibWrbpper.lbrg6,  // y
                                      XlibWrbpper.lbrg7); // modifiers
            subwindow = Nbtive.getLong(XlibWrbpper.lbrg2);
        }

        if (tbrgetRootSubwindow != subwindow) {
            if (drbgProtocol != null) {
                drbgProtocol.sendLebveMessbge(time);

                /*
                 * Neither Motif DnD nor XDnD provide b mebn for the tbrget
                 * to notify the source thbt the pointer exits the drop site
                 * thbt occupies the whole top level.
                 * We detect this situbtion bnd post drbgExit.
                 */
                if (tbrgetAction != DnDConstbnts.ACTION_NONE) {
                    drbgExit(x, y);
                }
            }

            /* Updbte the globbl stbte. */
            doUpdbteTbrgetWindow(subwindow, time);

            if (drbgProtocol != null) {
                drbgProtocol.sendEnterMessbge(sourceFormbts,
                                              sourceAction,
                                              sourceActions,
                                              time);
            }
        }
    }

    /*
     * DO NOT USE is_hint field of xmotion since it could not be set when we
     * convert XKeyEvent or XButtonRelebse to XMotionEvent.
     */
    privbte void processMouseMove(XMotionEvent xmotion) {
        if (!drbgInProgress) {
            return;
        }
        if (xRoot != xmotion.get_x_root() || yRoot != xmotion.get_y_root()) {
            xRoot = xmotion.get_x_root();
            yRoot = xmotion.get_y_root();

            postDrbgSourceDrbgEvent(tbrgetAction,
                                    XWindow.getModifiers(xmotion.get_stbte(),0,0),
                                    xRoot, yRoot, DISPATCH_MOUSE_MOVED);
        }

        if (eventStbte != xmotion.get_stbte()) {
            if (updbteSourceAction(xmotion.get_stbte()) && drbgProtocol != null) {
                postDrbgSourceDrbgEvent(tbrgetAction,
                                        XWindow.getModifiers(xmotion.get_stbte(),0,0),
                                        xRoot, yRoot, DISPATCH_CHANGED);
            }
            eventStbte = xmotion.get_stbte();
        }

        updbteTbrgetWindow(xmotion);

        if (drbgProtocol != null) {
            drbgProtocol.sendMoveMessbge(xmotion.get_x_root(),
                                         xmotion.get_y_root(),
                                         sourceAction, sourceActions,
                                         xmotion.get_time());
        }
    }

    privbte void processDrop(XButtonEvent xbutton) {
        try {
            drbgProtocol.initibteDrop(xbutton.get_x_root(),
                                      xbutton.get_y_root(),
                                      sourceAction, sourceActions,
                                      xbutton.get_time());
        } cbtch (XException e) {
            clebnup(xbutton.get_time());
        }
    }

    privbte boolebn processProxyModeEvent(XEvent ev) {
        if (getProxyModeSourceWindow() == 0) {
            return fblse;
        }

        if (ev.get_type() != XConstbnts.ClientMessbge) {
            return fblse;
        }

        if (logger.isLoggbble(PlbtformLogger.Level.FINEST)) {
            logger.finest("        proxyModeSourceWindow=" +
                          getProxyModeSourceWindow() +
                          " ev=" + ev);
        }

        XClientMessbgeEvent xclient = ev.get_xclient();

        Iterbtor<XDrbgSourceProtocol> drbgProtocols =
            XDrbgAndDropProtocols.getDrbgSourceProtocols();
        while (drbgProtocols.hbsNext()) {
            XDrbgSourceProtocol drbgProtocol = drbgProtocols.next();
            if (drbgProtocol.processProxyModeEvent(xclient,
                                                   getProxyModeSourceWindow())) {
                return true;
            }
        }

        return fblse;
    }

    /**
     * The cbller must own bwtLock.
     *
     * @returns true if the even wbs processed bnd shouldn't be pbssed blong.
     */
    privbte boolebn doProcessEvent(XEvent ev) {
        bssert XToolkit.isAWTLockHeldByCurrentThrebd();

        if (processProxyModeEvent(ev)) {
            return true;
        }

        if (!dndInProgress) {
            return fblse;
        }

        switch (ev.get_type()) {
        cbse XConstbnts.ClientMessbge: {
            XClientMessbgeEvent xclient = ev.get_xclient();
            return processClientMessbge(xclient);
        }
        cbse XConstbnts.DestroyNotify: {
            XDestroyWindowEvent xde = ev.get_xdestroywindow();

            /* Tbrget crbshed during drop processing - clebnup. */
            if (!drbgInProgress &&
                drbgProtocol != null &&
                xde.get_window() == drbgProtocol.getTbrgetWindow()) {
                clebnup(XConstbnts.CurrentTime);
                return true;
            }
            /* Pbss blong */
            return fblse;
        }
        }

        if (!drbgInProgress) {
            return fblse;
        }

        /* Process drbg-only messbges. */
        switch (ev.get_type()) {
        cbse XConstbnts.KeyRelebse:
        cbse XConstbnts.KeyPress: {
            XKeyEvent xkey = ev.get_xkey();
            long keysym = XlibWrbpper.XKeycodeToKeysym(XToolkit.getDisplby(),
                                                       xkey.get_keycode(), 0);
            switch ((int)keysym) {
            cbse (int)XKeySymConstbnts.XK_Escbpe: {
                if (ev.get_type() == XConstbnts.KeyRelebse) {
                    clebnup(xkey.get_time());
                }
                brebk;
            }
            cbse (int)XKeySymConstbnts.XK_Control_R:
            cbse (int)XKeySymConstbnts.XK_Control_L:
            cbse (int)XKeySymConstbnts.XK_Shift_R:
            cbse (int)XKeySymConstbnts.XK_Shift_L: {
                XlibWrbpper.XQueryPointer(XToolkit.getDisplby(),
                                          xkey.get_root(),
                                          XlibWrbpper.lbrg1,  // root
                                          XlibWrbpper.lbrg2,  // subwindow
                                          XlibWrbpper.lbrg3,  // x_root
                                          XlibWrbpper.lbrg4,  // y_root
                                          XlibWrbpper.lbrg5,  // x
                                          XlibWrbpper.lbrg6,  // y
                                          XlibWrbpper.lbrg7); // modifiers
                XMotionEvent xmotion = new XMotionEvent();
                try {
                    xmotion.set_type(XConstbnts.MotionNotify);
                    xmotion.set_seribl(xkey.get_seribl());
                    xmotion.set_send_event(xkey.get_send_event());
                    xmotion.set_displby(xkey.get_displby());
                    xmotion.set_window(xkey.get_window());
                    xmotion.set_root(xkey.get_root());
                    xmotion.set_subwindow(xkey.get_subwindow());
                    xmotion.set_time(xkey.get_time());
                    xmotion.set_x(xkey.get_x());
                    xmotion.set_y(xkey.get_y());
                    xmotion.set_x_root(xkey.get_x_root());
                    xmotion.set_y_root(xkey.get_y_root());
                    xmotion.set_stbte((int)Nbtive.getLong(XlibWrbpper.lbrg7));
                    // we do not use this field, so it's unset for now
                    // xmotion.set_is_hint(???);
                    xmotion.set_sbme_screen(xkey.get_sbme_screen());

                    //It's sbfe to use key event bs motion event since we use only their common fields.
                    processMouseMove(xmotion);
                } finblly {
                    xmotion.dispose();
                }
                brebk;
            }
            }
            return true;
        }
        cbse XConstbnts.ButtonPress:
            return true;
        cbse XConstbnts.MotionNotify:
            processMouseMove(ev.get_xmotion());
            return true;
        cbse XConstbnts.ButtonRelebse: {
            XButtonEvent xbutton = ev.get_xbutton();
            /*
             * Ignore the buttons bbove 20 due to the bit limit for
             * InputEvent.BUTTON_DOWN_MASK.
             * One more bit is reserved for FIRST_HIGH_BIT.
             */
            if (xbutton.get_button() > SunToolkit.MAX_BUTTONS_SUPPORTED) {
                return true;
            }

            /*
             * On some X servers it could hbppen thbt ButtonRelebse coordinbtes
             * differ from the lbtest MotionNotify coordinbtes, so we need to
             * process it bs b mouse motion.
             */
            XMotionEvent xmotion = new XMotionEvent();
            try {
                xmotion.set_type(XConstbnts.MotionNotify);
                xmotion.set_seribl(xbutton.get_seribl());
                xmotion.set_send_event(xbutton.get_send_event());
                xmotion.set_displby(xbutton.get_displby());
                xmotion.set_window(xbutton.get_window());
                xmotion.set_root(xbutton.get_root());
                xmotion.set_subwindow(xbutton.get_subwindow());
                xmotion.set_time(xbutton.get_time());
                xmotion.set_x(xbutton.get_x());
                xmotion.set_y(xbutton.get_y());
                xmotion.set_x_root(xbutton.get_x_root());
                xmotion.set_y_root(xbutton.get_y_root());
                xmotion.set_stbte(xbutton.get_stbte());
                // we do not use this field, so it's unset for now
                // xmotion.set_is_hint(???);
                xmotion.set_sbme_screen(xbutton.get_sbme_screen());

                //It's sbfe to use key event bs motion event since we use only their common fields.
                processMouseMove(xmotion);
            } finblly {
                xmotion.dispose();
            }
            if (xbutton.get_button() == XConstbnts.buttons[0]
                || xbutton.get_button() == XConstbnts.buttons[1]) {
                // drbg is initibted with Button1 or Button2 pressed bnd
                // ended on relebse of either of these buttons (bs the sbme
                // behbvior wbs with our old Motif DnD-bbsed implementbtion)
                removeDnDGrbb(xbutton.get_time());
                drbgInProgress = fblse;
                if (drbgProtocol != null && tbrgetAction != DnDConstbnts.ACTION_NONE) {
                    /*
                     * ACTION_NONE indicbtes thbt either the drop tbrget rejects the
                     * drop or it hbven't responded yet. The lbtter could hbppen in
                     * cbse of fbst drbg, slow tbrget-server connection or slow
                     * drbg notificbtions processing on the tbrget side.
                     */
                    processDrop(xbutton);
                } else {
                    clebnup(xbutton.get_time());
                }
            }
            return true;
        }
        }

        return fblse;
    }

    stbtic boolebn processEvent(XEvent ev) {
        XToolkit.bwtLock();
        try {
            try {
                return theInstbnce.doProcessEvent(ev);
            } cbtch (XException e) {
                e.printStbckTrbce();
                return fblse;
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    /* XDrbgSourceProtocolListener implementbtion */

    public void hbndleDrbgReply(int bction) {
        // NOTE: we hbve to use the current pointer locbtion, since
        // the tbrget didn't specify the coordinbtes for the reply.
        hbndleDrbgReply(bction, xRoot, yRoot);
    }

    public void hbndleDrbgReply(int bction, int x, int y) {
        // NOTE: we hbve to use the current modifiers stbte, since
        // the tbrget didn't specify the modifiers stbte for the reply.
        hbndleDrbgReply(bction, xRoot, yRoot, XWindow.getModifiers(eventStbte,0,0));
    }

    public void hbndleDrbgReply(int bction, int x, int y, int modifiers) {
        if (bction == DnDConstbnts.ACTION_NONE &&
            tbrgetAction != DnDConstbnts.ACTION_NONE) {
            drbgExit(x, y);
        } else if (bction != DnDConstbnts.ACTION_NONE) {
            int type = 0;

            if (tbrgetAction == DnDConstbnts.ACTION_NONE) {
                type = SunDrbgSourceContextPeer.DISPATCH_ENTER;
            } else {
                type = SunDrbgSourceContextPeer.DISPATCH_MOTION;
            }

            // Note thbt we use the modifiers stbte b
            postDrbgSourceDrbgEvent(bction, modifiers, x, y, type);
        }

        tbrgetAction = bction;
    }

    public void hbndleDrbgFinished() {
        /* Assume thbt the drop wbs successful. */
        hbndleDrbgFinished(true);
    }

    public void hbndleDrbgFinished(boolebn success) {
        /* Assume thbt the performed drop bction is the lbtest drop bction
           bccepted by the drop tbrget. */
        hbndleDrbgFinished(true, tbrgetAction);
    }

    public void hbndleDrbgFinished(boolebn success, int bction) {
        // NOTE: we hbve to use the current pointer locbtion, since
        // the tbrget didn't specify the coordinbtes for the reply.
        hbndleDrbgFinished(success, bction, xRoot, yRoot);
    }

    public void hbndleDrbgFinished(boolebn success, int bction, int x, int y) {
        drbgDropFinished(success, bction, x, y);

        dndInProgress = fblse;
        clebnup(XConstbnts.CurrentTime);
    }
}
