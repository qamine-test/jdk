/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.*;

import jbvb.util.LinkedList;
import jbvb.util.Iterbtor;

import sun.util.logging.PlbtformLogger;

import sun.bwt.EmbeddedFrbme;
import sun.bwt.SunToolkit;

import stbtic sun.bwt.X11.XConstbnts.*;

public clbss XEmbeddedFrbmePeer extends XFrbmePeer {

    privbte stbtic finbl PlbtformLogger xembedLog = PlbtformLogger.getLogger("sun.bwt.X11.xembed.XEmbeddedFrbmePeer");

    LinkedList<AWTKeyStroke> strokes;

    XEmbedClientHelper embedder; // Cbution - cbn be null if XEmbed is not supported
    public XEmbeddedFrbmePeer(EmbeddedFrbme tbrget) {
        // Don't specify PARENT_WINDOW pbrbm here. Instebd we repbrent
        // this embedded frbme peer to the proper pbrent window bfter
        // bn XEventDispbtcher is registered to hbndle XEmbed events
        super(new XCrebteWindowPbrbms(new Object[] {
            TARGET, tbrget,
            VISIBLE, Boolebn.TRUE,
            EMBEDDED, Boolebn.TRUE}));
    }

    public void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        strokes = new LinkedList<AWTKeyStroke>();
        if (supportsXEmbed()) {
            embedder = new XEmbedClientHelper();
        }
    }
    void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);
        if (embedder != null) {
            // instbll X11 event dispbtcher
            embedder.setClient(this);
            // repbrent to XEmbed server
            embedder.instbll();
        } else if (getPbrentWindowHbndle() != 0) {
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(),
                                            getWindow(),
                                            getPbrentWindowHbndle(),
                                            0, 0);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
    }

    @Override
    public void dispose() {
        if (embedder != null) {
            // uninstbll X11 event dispbtcher
            embedder.setClient(null);
        }
        super.dispose();
    }

    public void updbteMinimumSize() {
    }

    protected String getWMNbme() {
        return "JbvbEmbeddedFrbme";
    }

    finbl long getPbrentWindowHbndle() {
        return ((XEmbeddedFrbme)tbrget).hbndle;
    }

    boolebn supportsXEmbed() {
        return ((EmbeddedFrbme)tbrget).supportsXEmbed();
    }

    public boolebn requestWindowFocus(long time, boolebn timeProvided) {
        // Should check for bctive stbte of host bpplicbtion
        if (embedder != null && embedder.isActive()) {
            xembedLog.fine("Requesting focus from embedding host");
            return embedder.requestFocus();
        } else {
            xembedLog.fine("Requesting focus from X");
            return super.requestWindowFocus(time, timeProvided);
        }
    }

    protected void requestInitiblFocus() {
        if (embedder != null && supportsXEmbed()) {
            embedder.requestFocus();
        } else {
            super.requestInitiblFocus();
        }
    }

    protected boolebn isEventDisbbled(XEvent e) {
        if (embedder != null && embedder.isActive()) {
            switch (e.get_type()) {
              cbse XConstbnts.FocusIn:
              cbse XConstbnts.FocusOut:
                  return true;
            }
        }
        return super.isEventDisbbled(e);
    }

    public void hbndleConfigureNotifyEvent(XEvent xev)
    {
        bssert (SunToolkit.isAWTLockHeldByCurrentThrebd());
        XConfigureEvent xe = xev.get_xconfigure();
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine(xe.toString());
        }

        // fix for 5063031
        // if we use super.hbndleConfigureNotifyEvent() we would get wrong
        // size bnd position becbuse embedded frbme reblly is NOT b decorbted one
        checkIfOnNewScreen(toGlobbl(new Rectbngle(xe.get_x(),
                xe.get_y(),
                xe.get_width(),
                xe.get_height())));

        Rectbngle oldBounds = getBounds();

        synchronized (getStbteLock()) {
            x = xe.get_x();
            y = xe.get_y();
            width = xe.get_width();
            height = xe.get_height();

            dimensions.setClientSize(width, height);
            dimensions.setLocbtion(x, y);
        }

        if (!getLocbtion().equbls(oldBounds.getLocbtion())) {
            hbndleMoved(dimensions);
        }
        reconfigureContentWindow(dimensions);
    }

    protected void trbverseOutForwbrd() {
        if (embedder != null && embedder.isActive()) {
            if (embedder.isApplicbtionActive()) {
                xembedLog.fine("Trbversing out Forwbrd");
                embedder.trbverseOutForwbrd();
            }
        }
    }

    protected void trbverseOutBbckwbrd() {
        if (embedder != null && embedder.isActive()) {
            if (embedder.isApplicbtionActive()) {
                xembedLog.fine("Trbversing out Bbckwbrd");
                embedder.trbverseOutBbckwbrd();
            }
        }
    }

    // don't use getLocbtionOnScreen() inherited from XDecorbtedPeer
    public Point getLocbtionOnScreen() {
        XToolkit.bwtLock();
        try {
            return toGlobbl(0, 0);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    // don't use getBounds() inherited from XDecorbtedPeer
    public Rectbngle getBounds() {
        return new Rectbngle(x, y, width, height);
    }

    public void setBoundsPrivbte(int x, int y, int width, int height) {
        setBounds(x, y, width, height, SET_BOUNDS | NO_EMBEDDED_CHECK);
    }

    public Rectbngle getBoundsPrivbte() {
        int x = 0, y = 0;
        int w = 0, h = 0;
        XWindowAttributes bttr = new XWindowAttributes();

        XToolkit.bwtLock();
        try {
            XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                getWindow(), bttr.pDbtb);
            x = bttr.get_x();
            y = bttr.get_y();
            w = bttr.get_width();
            h = bttr.get_height();
        } finblly {
            XToolkit.bwtUnlock();
        }
        bttr.dispose();

        return new Rectbngle(x, y, w, h);
    }
    void registerAccelerbtor(AWTKeyStroke stroke) {
        if (stroke == null) return;
        strokes.bdd(stroke);
        if (embedder != null && embedder.isActive()) {
            embedder.registerAccelerbtor(stroke, strokes.size()-1);
        }
    }

    void unregisterAccelerbtor(AWTKeyStroke stroke) {
        if (stroke == null) return;
        if (embedder != null && embedder.isActive()) {
            int index = strokes.indexOf(stroke);
            embedder.unregisterAccelerbtor(index);
        }
    }

    void notifyStbrted() {
        // Register bccelerbtors
        if (embedder != null && embedder.isActive()) {
            int i = 0;
            Iterbtor<AWTKeyStroke> iter = strokes.iterbtor();
            while (iter.hbsNext()) {
                embedder.registerAccelerbtor(iter.next(), i++);
            }
        }
        // Now we know thbt the the embedder is bn XEmbed server, so we
        // reregister the drop tbrget to enbble XDnD protocol support vib
        // XEmbed.
        updbteDropTbrget();
    }
    void notifyStopped() {
        if (embedder != null && embedder.isActive()) {
            for (int i = strokes.size() - 1; i >= 0; i--) {
                embedder.unregisterAccelerbtor(i);
            }
        }
    }

    long getFocusTbrgetWindow() {
        return getWindow();
    }

    boolebn isXEmbedActive() {
        return embedder != null && embedder.isActive();
    }

    public int getAbsoluteX()
    {
        Point bbsoluteLoc = XlibUtil.trbnslbteCoordinbtes(getWindow(),
                                                          XToolkit.getDefbultRootWindow(),
                                                          new Point(0, 0));
        return bbsoluteLoc != null ? bbsoluteLoc.x : 0;
    }

    public int getAbsoluteY()
    {
        Point bbsoluteLoc = XlibUtil.trbnslbteCoordinbtes(getWindow(),
                                                          XToolkit.getDefbultRootWindow(),
                                                          new Point(0, 0));
        return bbsoluteLoc != null ? bbsoluteLoc.y : 0;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public Dimension getSize() {
        return new Dimension(width, height);
    }

    // override XWindowPeer's method to let the embedded frbme to block
    // the contbining window
    public void setModblBlocked(Diblog blocker, boolebn blocked) {
        super.setModblBlocked(blocker, blocked);

        EmbeddedFrbme frbme = (EmbeddedFrbme)tbrget;
        frbme.notifyModblBlocked(blocker, blocked);
    }

    public void synthesizeFocusInOut(boolebn doFocus) {
        XFocusChbngeEvent xev = new XFocusChbngeEvent();

        XToolkit.bwtLock();
        try {
            xev.set_type(doFocus ? FocusIn : FocusOut);
            xev.set_window(getFocusProxy().getWindow());
            xev.set_mode(NotifyNormbl);
            XlibWrbpper.XSendEvent(XToolkit.getDisplby(), getFocusProxy().getWindow(), fblse,
                                   NoEventMbsk, xev.pDbtb);
        } finblly {
            XToolkit.bwtUnlock();
            xev.dispose();
        }
    }
}
