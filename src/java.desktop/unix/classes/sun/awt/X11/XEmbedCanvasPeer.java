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

import jbvb.bwt.*;
import jbvb.bwt.dnd.DropTbrget;
import jbvb.bwt.dnd.DropTbrgetListener;
import jbvb.bwt.event.*;
import sun.bwt.*;
import sun.bwt.AWTAccessor;
import sun.util.logging.PlbtformLogger;
import jbvb.util.*;
import stbtic sun.bwt.X11.XEmbedHelper.*;

import jbvb.security.AccessController;
import sun.security.bction.GetBoolebnAction;

public clbss XEmbedCbnvbsPeer extends XCbnvbsPeer implements WindowFocusListener, KeyEventPostProcessor, ModblityListener, WindowIDProvider {
    privbte stbtic finbl PlbtformLogger xembedLog = PlbtformLogger.getLogger("sun.bwt.X11.xembed.XEmbedCbnvbsPeer");

    boolebn bpplicbtionActive; // Whether the bpplicbtion is bctive(hbs focus)
    XEmbedServer xembed = new XEmbedServer(); // Helper object, contbins XEmbed intrinsics
    Mbp<Long, AWTKeyStroke> bccelerbtors = new HbshMbp<Long, AWTKeyStroke>(); // Mbps bccelerbtor ID into AWTKeyStroke
    Mbp<AWTKeyStroke, Long> bccel_lookup = new HbshMbp<AWTKeyStroke, Long>(); // Mbps AWTKeyStroke into bccelerbtor ID
    Set<GrbbbedKey> grbbbed_keys = new HbshSet<GrbbbedKey>(); // A set of keys grbbbed by client
    Object ACCEL_LOCK = bccelerbtors; // Lock object for working with bccelerbtors;
    Object GRAB_LOCK = grbbbed_keys; // Lock object for working with keys grbbbed by client

    XEmbedCbnvbsPeer() {}

    XEmbedCbnvbsPeer(XCrebteWindowPbrbms pbrbms) {
        super(pbrbms);
    }

    XEmbedCbnvbsPeer(Component tbrget) {
        super(tbrget);
    }

    protected void postInit(XCrebteWindowPbrbms pbrbms) {
        super.postInit(pbrbms);

        instbllActivbteListener();
        instbllAccelerbtorListener();
        instbllModblityListener();

        // XEmbed cbnvbs should be non-trbversbble.
        // FIXME: Probbbly should be removed bnd enforced setting of it by the users
        tbrget.setFocusTrbversblKeysEnbbled(fblse);
    }

    protected void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);

        pbrbms.put(EVENT_MASK,
                   XConstbnts.KeyPressMbsk       | XConstbnts.KeyRelebseMbsk
                   | XConstbnts.FocusChbngeMbsk  | XConstbnts.ButtonPressMbsk | XConstbnts.ButtonRelebseMbsk
                   | XConstbnts.EnterWindowMbsk  | XConstbnts.LebveWindowMbsk | XConstbnts.PointerMotionMbsk
                   | XConstbnts.ButtonMotionMbsk | XConstbnts.ExposureMbsk    | XConstbnts.StructureNotifyMbsk | XConstbnts.SubstructureNotifyMbsk);

    }

    void instbllModblityListener() {
        ((SunToolkit)Toolkit.getDefbultToolkit()).bddModblityListener(this);
    }

    void deinstbllModblityListener() {
        ((SunToolkit)Toolkit.getDefbultToolkit()).removeModblityListener(this);
    }

    void instbllAccelerbtorListener() {
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().bddKeyEventPostProcessor(this);
    }

    void deinstbllAccelerbtorListener() {
        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().removeKeyEventPostProcessor(this);
    }

    void instbllActivbteListener() {
        // FIXME: should wbtch for hierbrchy chbnges
        Window toplevel = getTopLevel(tbrget);
        if (toplevel != null) {
            toplevel.bddWindowFocusListener(this);
            bpplicbtionActive = toplevel.isFocused();
        }
    }

    void deinstbllActivbteListener() {
        Window toplevel = getTopLevel(tbrget);
        if (toplevel != null) {
            toplevel.removeWindowFocusListener(this);
        }
    }

    boolebn isXEmbedActive() {
        return xembed.hbndle != 0;
    }

    boolebn isApplicbtionActive() {
        return bpplicbtionActive;
    }

    void initDispbtching() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine("Init embedding for " + Long.toHexString(xembed.hbndle));
        }
        XToolkit.bwtLock();
        try {
            XToolkit.bddEventDispbtcher(xembed.hbndle, xembed);
            XlibWrbpper.XSelectInput(XToolkit.getDisplby(), xembed.hbndle,
                                     XConstbnts.StructureNotifyMbsk | XConstbnts.PropertyChbngeMbsk);

            XDropTbrgetRegistry.getRegistry().registerXEmbedClient(getWindow(), xembed.hbndle);
        } finblly {
            XToolkit.bwtUnlock();
        }
        xembed.processXEmbedInfo();

        notifyChildEmbedded();
    }

    void endDispbtching() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine("End dispbtching for " + Long.toHexString(xembed.hbndle));
        }
        XToolkit.bwtLock();
        try {
            XDropTbrgetRegistry.getRegistry().unregisterXEmbedClient(getWindow(), xembed.hbndle);
            // We cbn't deselect input since someone else might be interested in it
            XToolkit.removeEventDispbtcher(xembed.hbndle, xembed);
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    void embedChild(long child) {
        if (xembed.hbndle != 0) {
            detbchChild();
        }
        xembed.hbndle = child;
        initDispbtching();
    }

    void childDestroyed() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine("Child " + Long.toHexString(xembed.hbndle) + " hbs self-destroyed.");
        }
        endDispbtching();
        xembed.hbndle = 0;
    }

    public void hbndleEvent(AWTEvent e) {
        super.hbndleEvent(e);
        if (isXEmbedActive()) {
            switch (e.getID()) {
              cbse FocusEvent.FOCUS_GAINED:
                  cbnvbsFocusGbined((FocusEvent)e);
                  brebk;
              cbse FocusEvent.FOCUS_LOST:
                  cbnvbsFocusLost((FocusEvent)e);
                  brebk;
              cbse KeyEvent.KEY_PRESSED:
              cbse KeyEvent.KEY_RELEASED:
                  if (!((InputEvent)e).isConsumed()) {
                      forwbrdKeyEvent((KeyEvent)e);
                  }
                  brebk;
            }
        }
    }

    public void dispbtchEvent(XEvent ev) {
        super.dispbtchEvent(ev);
        switch (ev.get_type()) {
          cbse XConstbnts.CrebteNotify:
              XCrebteWindowEvent cr = ev.get_xcrebtewindow();
              if (xembedLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                  xembedLog.finest("Messbge on embedder: " + cr);
              }
              if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  xembedLog.finer("Crebte notify for pbrent " + Long.toHexString(cr.get_pbrent()) +
                                  ", window " + Long.toHexString(cr.get_window()));
              }
              embedChild(cr.get_window());
              brebk;
          cbse XConstbnts.DestroyNotify:
              XDestroyWindowEvent dn = ev.get_xdestroywindow();
              if (xembedLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                  xembedLog.finest("Messbge on embedder: " + dn);
              }
              if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  xembedLog.finer("Destroy notify for pbrent: " + dn);
              }
              childDestroyed();
              brebk;
          cbse XConstbnts.RepbrentNotify:
              XRepbrentEvent rep = ev.get_xrepbrent();
              if (xembedLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                  xembedLog.finest("Messbge on embedder: " + rep);
              }
              if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                  xembedLog.finer("Repbrent notify for pbrent " + Long.toHexString(rep.get_pbrent()) +
                                  ", window " + Long.toHexString(rep.get_window()) +
                                  ", event " + Long.toHexString(rep.get_event()));
              }
              if (rep.get_pbrent() == getWindow()) {
                  // Repbrented into us - embed it
                  embedChild(rep.get_window());
              } else {
                  // Repbrented out of us - detbch it
                  childDestroyed();
              }
              brebk;
        }
    }

    public Dimension getPreferredSize() {
        if (isXEmbedActive()) {
            XToolkit.bwtLock();
            try {
                long p_hints = XlibWrbpper.XAllocSizeHints();
                XSizeHints hints = new XSizeHints(p_hints);
                XlibWrbpper.XGetWMNormblHints(XToolkit.getDisplby(), xembed.hbndle, p_hints, XlibWrbpper.lbrg1);
                Dimension res = new Dimension(hints.get_width(), hints.get_height());
                XlibWrbpper.XFree(p_hints);
                return res;
            } finblly {
                XToolkit.bwtUnlock();
            }
        } else {
            return super.getPreferredSize();
        }
    }
    public Dimension getMinimumSize() {
        if (isXEmbedActive()) {
            XToolkit.bwtLock();
            try {
                long p_hints = XlibWrbpper.XAllocSizeHints();
                XSizeHints hints = new XSizeHints(p_hints);
                XlibWrbpper.XGetWMNormblHints(XToolkit.getDisplby(), xembed.hbndle, p_hints, XlibWrbpper.lbrg1);
                Dimension res = new Dimension(hints.get_min_width(), hints.get_min_height());
                XlibWrbpper.XFree(p_hints);
                return res;
            } finblly {
                XToolkit.bwtUnlock();
            }
        } else {
            return super.getMinimumSize();
        }
    }
    public void dispose() {
        if (isXEmbedActive()) {
            detbchChild();
        }
        deinstbllActivbteListener();
        deinstbllModblityListener();
        deinstbllAccelerbtorListener();

        // BUG: Focus trbversbl doesn't become enbbled bfter the one round of embedding
        //tbrget.setFocusTrbversblKeysEnbbled(true);

        super.dispose();
    }

    // Focusbble is true in order to enbble focus trbversbl through this Cbnvbs
    public boolebn isFocusbble() {
        return true;
    }

    Window getTopLevel(Component comp) {
        while (comp != null && !(comp instbnceof Window)) {
            comp = comp.getPbrent();
        }
        return (Window)comp;
    }

    Rectbngle getClientBounds() {
        XToolkit.bwtLock();
        try {
            XWindowAttributes wbttr = new XWindowAttributes();
            try {
                XErrorHbndlerUtil.WITH_XERROR_HANDLER(XErrorHbndler.IgnoreBbdWindowHbndler.getInstbnce());
                int stbtus = XlibWrbpper.XGetWindowAttributes(XToolkit.getDisplby(),
                                                              xembed.hbndle, wbttr.pDbtb);

                XErrorHbndlerUtil.RESTORE_XERROR_HANDLER();

                if ((stbtus == 0) ||
                    ((XErrorHbndlerUtil.sbved_error != null) &&
                    (XErrorHbndlerUtil.sbved_error.get_error_code() != XConstbnts.Success))) {
                    return null;
                }

                return new Rectbngle(wbttr.get_x(), wbttr.get_y(), wbttr.get_width(), wbttr.get_height());
            } finblly {
                wbttr.dispose();
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
    }

    void childResized() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            Rectbngle bounds = getClientBounds();
            xembedLog.finer("Child resized: " + bounds);
            // It is not required to updbte embedder's size when client size chbnges
            // However, since there is no bny mebns to get client size it seems to be the
            // only wby to provide it. However, it contrbdicts with Jbvb lbyout concept -
            // so it is disbbled for now.
//             Rectbngle my_bounds = getBounds();
//             setBounds(my_bounds.x, my_bounds.y, bounds.width, bounds.height, SET_BOUNDS);
        }
        XToolkit.postEvent(XToolkit.tbrgetToAppContext(tbrget), new ComponentEvent(tbrget, ComponentEvent.COMPONENT_RESIZED));
    }

    void focusNext() {
        if (isXEmbedActive()) {
            xembedLog.fine("Requesting focus for the next component bfter embedder");
            postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                    public void run() {
                        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().focusNextComponent(tbrget);
                    }
                }));
        } else {
            xembedLog.fine("XEmbed is not bctive - denying focus next");
        }
    }

    void focusPrev() {
        if (isXEmbedActive()) {
            xembedLog.fine("Requesting focus for the next component bfter embedder");
            postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                    public void run() {
                        KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger().focusPreviousComponent(tbrget);
                    }
                }));
        } else {
            xembedLog.fine("XEmbed is not bctive - denying focus prev");
        }
    }

    void requestXEmbedFocus() {
        if (isXEmbedActive()) {
            xembedLog.fine("Requesting focus for client");
            postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                    public void run() {
                        tbrget.requestFocus();
                    }
                }));
        } else {
            xembedLog.fine("XEmbed is not bctive - denying request focus");
        }
    }

    void notifyChildEmbedded() {
        xembed.sendMessbge(xembed.hbndle, XEMBED_EMBEDDED_NOTIFY, getWindow(), Mbth.min(xembed.version, XEMBED_VERSION), 0);
        if (isApplicbtionActive()) {
            xembedLog.fine("Sending WINDOW_ACTIVATE during initiblizbtion");
            xembed.sendMessbge(xembed.hbndle, XEMBED_WINDOW_ACTIVATE);
            if (hbsFocus()) {
                xembedLog.fine("Sending FOCUS_GAINED during initiblizbtion");
                xembed.sendMessbge(xembed.hbndle, XEMBED_FOCUS_IN, XEMBED_FOCUS_CURRENT, 0, 0);
            }
        }
    }

    void detbchChild() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            xembedLog.fine("Detbching child " + Long.toHexString(xembed.hbndle));
        }
        /**
         *  XEmbed specificbtion:
         *  "The embedder cbn unmbp the client bnd repbrent the client window to the root window. If the
         *  client receives bn RepbrentNotify event, it should check the pbrent field of the XRepbrentEvent
         *  structure. If this is the root window of the window's screen, then the protocol is finished bnd
         *  there is no further interbction. If it is b window other thbn the root window, then the protocol
         *  continues with the new pbrent bcting bs the embedder window."
         */
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XUnmbpWindow(XToolkit.getDisplby(), xembed.hbndle);
            XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(), xembed.hbndle, XToolkit.getDefbultRootWindow(), 0, 0);
        } finblly {
            XToolkit.bwtUnlock();
        }
        endDispbtching();
        xembed.hbndle = 0;
    }

    public void windowGbinedFocus(WindowEvent e) {
        bpplicbtionActive = true;
        if (isXEmbedActive()) {
            xembedLog.fine("Sending WINDOW_ACTIVATE");
            xembed.sendMessbge(xembed.hbndle, XEMBED_WINDOW_ACTIVATE);
        }
    }

    public void windowLostFocus(WindowEvent e) {
        bpplicbtionActive = fblse;
        if (isXEmbedActive()) {
            xembedLog.fine("Sending WINDOW_DEACTIVATE");
            xembed.sendMessbge(xembed.hbndle, XEMBED_WINDOW_DEACTIVATE);
        }
    }

    void cbnvbsFocusGbined(FocusEvent e) {
        if (isXEmbedActive()) {
            xembedLog.fine("Forwbrding FOCUS_GAINED");
            int flbvor = XEMBED_FOCUS_CURRENT;
            if (e instbnceof CbusedFocusEvent) {
                CbusedFocusEvent ce = (CbusedFocusEvent)e;
                if (ce.getCbuse() == CbusedFocusEvent.Cbuse.TRAVERSAL_FORWARD) {
                    flbvor = XEMBED_FOCUS_FIRST;
                } else if (ce.getCbuse() == CbusedFocusEvent.Cbuse.TRAVERSAL_BACKWARD) {
                    flbvor = XEMBED_FOCUS_LAST;
                }
            }
            xembed.sendMessbge(xembed.hbndle, XEMBED_FOCUS_IN, flbvor, 0, 0);
        }
    }

    void cbnvbsFocusLost(FocusEvent e) {
        if (isXEmbedActive() && !e.isTemporbry()) {
            xembedLog.fine("Forwbrding FOCUS_LOST");
            int num = 0;
            if (AccessController.doPrivileged(new GetBoolebnAction("sun.bwt.xembed.testing"))) {
                Component opp = e.getOppositeComponent();
                try {
                    num = Integer.pbrseInt(opp.getNbme());
                } cbtch (NumberFormbtException nfe) {
                }
            }
            xembed.sendMessbge(xembed.hbndle, XEMBED_FOCUS_OUT, num, 0, 0);
        }
    }

    stbtic byte[] getBDbtb(KeyEvent e) {
        return AWTAccessor.getAWTEventAccessor().getBDbtb(e);
    }

    void forwbrdKeyEvent(KeyEvent e) {
        xembedLog.fine("Try to forwbrd key event");
        byte[] bdbtb = getBDbtb(e);
        long dbtb = Nbtive.toDbtb(bdbtb);
        if (dbtb == 0) {
            return;
        }
        try {
            XKeyEvent ke = new XKeyEvent(dbtb);
            ke.set_window(xembed.hbndle);
            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                xembedLog.fine("Forwbrding nbtive key event: " + ke);
            }
            XToolkit.bwtLock();
            try {
                XlibWrbpper.XSendEvent(XToolkit.getDisplby(), xembed.hbndle, fblse, XConstbnts.NoEventMbsk, dbtb);
            } finblly {
                XToolkit.bwtUnlock();
            }
        } finblly {
            XlibWrbpper.unsbfe.freeMemory(dbtb);
        }
    }


    /**
     * Grbb/ungrbb key functionblity is bn unofficibl API supported by
     * GTK.  Unfortunbtely, it doesn't support bccelerbtor API, so,
     * since this is the ONLY shortcut-processing API bvbilbble, we
     * must support it.  See XEmbed.NON_STANDARD_XEMBED_GTK_*
     * messbges.  The formbt of these messbges is bs follows:
     * - request from client:
     * dbtb[1] = NON_STANDARD_XEMBED_GTK_GRAB_KEY or NON_STANDARD_XEMBED_GTK_UNGRAB_KEY
     * dbtb[3] = X keysym
     * dbtb[4] = X modifiers
     *
     * - response from server (in cbse the grbbbed key hbs been pressed):
     * forwbrded XKeyEvent thbt mbtches keysym/modifiers pbir
     */
    void grbbKey(finbl long keysym, finbl long modifiers) {
        postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                public void run() {
                    GrbbbedKey grbb = new GrbbbedKey(keysym, modifiers);
                    if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        xembedLog.fine("Grbbbing key: " + grbb);
                    }
                    synchronized(GRAB_LOCK) {
                        grbbbed_keys.bdd(grbb);
                    }
                }
            }));
    }

    void ungrbbKey(finbl long keysym, finbl long modifiers) {
        postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                public void run() {
                    GrbbbedKey grbb = new GrbbbedKey(keysym, modifiers);
                    if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        xembedLog.fine("UnGrbbbing key: " + grbb);
                    }
                    synchronized(GRAB_LOCK) {
                        grbbbed_keys.remove(grbb);
                    }
                }
            }));
    }

    void registerAccelerbtor(finbl long bccel_id, finbl long keysym, finbl long modifiers) {
        postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                public void run() {
                    AWTKeyStroke stroke = xembed.getKeyStrokeForKeySym(keysym, modifiers);
                    if (stroke != null) {
                        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                            xembedLog.fine("Registering bccelerbtor " + bccel_id + " for " + stroke);
                        }
                        synchronized(ACCEL_LOCK) {
                            bccelerbtors.put(bccel_id, stroke);
                            bccel_lookup.put(stroke, bccel_id);
                        }
                    }
                    propogbteRegisterAccelerbtor(stroke);
                }
            }));
    }

    void unregisterAccelerbtor(finbl long bccel_id) {
        postEvent(new InvocbtionEvent(tbrget, new Runnbble() {
                public void run() {
                    AWTKeyStroke stroke = null;
                    synchronized(ACCEL_LOCK) {
                        stroke = bccelerbtors.get(bccel_id);
                        if (stroke != null) {
                            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                                xembedLog.fine("Unregistering bccelerbtor: " + bccel_id);
                            }
                            bccelerbtors.remove(bccel_id);
                            bccel_lookup.remove(stroke); // FIXME: How bbout severbl bccelerbtors with the sbme stroke?
                        }
                    }
                    propogbteUnRegisterAccelerbtor(stroke);
                }
            }));
    }

    void propogbteRegisterAccelerbtor(AWTKeyStroke stroke) {
        // Find the top-level bnd see if it is XEmbed client. If so, bsk him to
        // register the bccelerbtor
        XWindowPeer pbrent = getToplevelXWindow();
        if (pbrent != null && pbrent instbnceof XEmbeddedFrbmePeer) {
            XEmbeddedFrbmePeer embedded = (XEmbeddedFrbmePeer)pbrent;
            embedded.registerAccelerbtor(stroke);
        }
    }

    void propogbteUnRegisterAccelerbtor(AWTKeyStroke stroke) {
        // Find the top-level bnd see if it is XEmbed client. If so, bsk him to
        // register the bccelerbtor
        XWindowPeer pbrent = getToplevelXWindow();
        if (pbrent != null && pbrent instbnceof XEmbeddedFrbmePeer) {
            XEmbeddedFrbmePeer embedded = (XEmbeddedFrbmePeer)pbrent;
            embedded.unregisterAccelerbtor(stroke);
        }
    }

    public boolebn postProcessKeyEvent(KeyEvent e) {
        // Processing events only if we bre in the focused window but
        // we bre not focus owner since otherwise we will get
        // duplicbte shortcut events in the client - one is from
        // bctivbte_bccelerbtor, bnother from forwbrded event
        // FIXME: This is probbbly bn incompbtibility, protocol
        // doesn't sby bnything bbout disbble bccelerbtors when client
        // is focused.

        XWindowPeer pbrent = getToplevelXWindow();
        if (pbrent == null || !((Window)pbrent.getTbrget()).isFocused() || tbrget.isFocusOwner()) {
            return fblse;
        }

        boolebn result = fblse;

        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            xembedLog.finer("Post-processing event " + e);
        }

        // Process ACCELERATORS
        AWTKeyStroke stroke = AWTKeyStroke.getAWTKeyStrokeForEvent(e);
        long bccel_id = 0;
        boolebn exists = fblse;
        synchronized(ACCEL_LOCK) {
            exists = bccel_lookup.contbinsKey(stroke);
            if (exists) {
                bccel_id = bccel_lookup.get(stroke).longVblue();
            }
        }
        if (exists) {
            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                xembedLog.fine("Activbting bccelerbtor " + bccel_id);
            }
            xembed.sendMessbge(xembed.hbndle, XEMBED_ACTIVATE_ACCELERATOR, bccel_id, 0, 0); // FIXME: How bbout overlobded?
            result = true;
        }

        // Process Grbbs, unofficibl GTK febture
        exists = fblse;
        GrbbbedKey key = new GrbbbedKey(e);
        synchronized(GRAB_LOCK) {
            exists = grbbbed_keys.contbins(key);
        }
        if (exists) {
            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                xembedLog.fine("Forwbrding grbbbed key " + e);
            }
            forwbrdKeyEvent(e);
            result = true;
        }

        return result;
    }

    public void modblityPushed(ModblityEvent ev) {
        xembed.sendMessbge(xembed.hbndle, XEMBED_MODALITY_ON);
    }

    public void modblityPopped(ModblityEvent ev) {
        xembed.sendMessbge(xembed.hbndle, XEMBED_MODALITY_OFF);
    }

    public void hbndleClientMessbge(XEvent xev) {
        super.hbndleClientMessbge(xev);
        XClientMessbgeEvent msg = xev.get_xclient();
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            xembedLog.finer("Client messbge to embedder: " + msg);
        }
        if (msg.get_messbge_type() == XEmbedHelper.XEmbed.getAtom()) {
            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                xembedLog.fine(XEmbedHelper.XEmbedMessbgeToString(msg));
            }
        }
        if (isXEmbedActive()) {
            switch ((int)msg.get_dbtb(1)) {
              cbse XEMBED_REQUEST_FOCUS:
                  requestXEmbedFocus();
                  brebk;
              cbse XEMBED_FOCUS_NEXT:
                  focusNext();
                  brebk;
              cbse XEMBED_FOCUS_PREV:
                  focusPrev();
                  brebk;
              cbse XEMBED_REGISTER_ACCELERATOR:
                  registerAccelerbtor(msg.get_dbtb(2), msg.get_dbtb(3), msg.get_dbtb(4));
                  brebk;
              cbse XEMBED_UNREGISTER_ACCELERATOR:
                  unregisterAccelerbtor(msg.get_dbtb(2));
                  brebk;
              cbse NON_STANDARD_XEMBED_GTK_GRAB_KEY:
                  grbbKey(msg.get_dbtb(3), msg.get_dbtb(4));
                  brebk;
              cbse NON_STANDARD_XEMBED_GTK_UNGRAB_KEY:
                  ungrbbKey(msg.get_dbtb(3), msg.get_dbtb(4));
                  brebk;
            }
        } else {
            xembedLog.finer("But XEmbed is not Active!");
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte stbtic clbss XEmbedDropTbrget extends DropTbrget {
        public void bddDropTbrgetListener(DropTbrgetListener dtl)
          throws TooMbnyListenersException {
            // Drop tbrget listeners registered with this tbrget will never be
            // notified, since bll drbg notificbtions bre routed to the XEmbed
            // client. To bvoid confusion we prohibit listeners registrbtion
            // by throwing TooMbnyListenersException bs if there is b listener
            // registered with this tbrget blrebdy.
            throw new TooMbnyListenersException();
        }
    }

    public void setXEmbedDropTbrget() {
        // Register b drop site on the top level.
        Runnbble r = new Runnbble() {
                public void run() {
                    tbrget.setDropTbrget(new XEmbedDropTbrget());
                }
            };
        SunToolkit.executeOnEventHbndlerThrebd(tbrget, r);
    }

    public void removeXEmbedDropTbrget() {
        // Unregister b drop site on the top level.
        Runnbble r = new Runnbble() {
                public void run() {
                    if (tbrget.getDropTbrget() instbnceof XEmbedDropTbrget) {
                        tbrget.setDropTbrget(null);
                    }
                }
            };
        SunToolkit.executeOnEventHbndlerThrebd(tbrget, r);
    }

    public boolebn processXEmbedDnDEvent(long ctxt, int eventID) {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            xembedLog.finest("     Drop tbrget=" + tbrget.getDropTbrget());
        }
        if (tbrget.getDropTbrget() instbnceof XEmbedDropTbrget) {
            AppContext bppContext = XToolkit.tbrgetToAppContext(getTbrget());
            XDropTbrgetContextPeer peer =
                XDropTbrgetContextPeer.getPeer(bppContext);
            peer.forwbrdEventToEmbedded(xembed.hbndle, ctxt, eventID);
            return true;
        } else {
            return fblse;
        }
    }

    clbss XEmbedServer extends XEmbedHelper implements XEventDispbtcher {
        long hbndle; // Hbndle to XEmbed client
        long version;
        long flbgs;

        boolebn processXEmbedInfo() {
            long xembed_info_dbtb = Nbtive.bllocbteLongArrby(2);
            try {
                if (!XEmbedInfo.getAtomDbtb(hbndle, xembed_info_dbtb, 2)) {
                    // No more XEMBED_INFO? This is not XEmbed client!
                    // Unfortunbtely this is the initibl stbte of the most clients
                    // FIXME: bdd 5-stbte processing
                    //childDestroyed();
                    xembedLog.finer("Unbble to get XEMBED_INFO btom dbtb");
                    return fblse;
                }
                version = Nbtive.getCbrd32(xembed_info_dbtb, 0);
                flbgs = Nbtive.getCbrd32(xembed_info_dbtb, 1);
                boolebn new_mbpped = (flbgs & XEMBED_MAPPED) != 0;
                boolebn currently_mbpped = XlibUtil.getWindowMbpStbte(hbndle) != XConstbnts.IsUnmbpped;
                if (new_mbpped != currently_mbpped) {
                    if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        xembedLog.finer("Mbpping stbte of the client hbs chbnged, old stbte: " + currently_mbpped + ", new stbte: " + new_mbpped);
                    }
                    if (new_mbpped) {
                        XToolkit.bwtLock();
                        try {
                            XlibWrbpper.XMbpWindow(XToolkit.getDisplby(), hbndle);
                        } finblly {
                            XToolkit.bwtUnlock();
                        }
                    } else {
                        XToolkit.bwtLock();
                        try {
                            XlibWrbpper.XUnmbpWindow(XToolkit.getDisplby(), hbndle);
                        } finblly {
                            XToolkit.bwtUnlock();
                        }
                    }
                } else {
                    if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        xembedLog.finer("Mbpping stbte didn't chbnge, mbpped: " + currently_mbpped);
                    }
                }
                return true;
            } finblly {
                XlibWrbpper.unsbfe.freeMemory(xembed_info_dbtb);
            }
        }

        public void hbndlePropertyNotify(XEvent xev) {
            if (isXEmbedActive()) {
                XPropertyEvent ev = xev.get_xproperty();
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    xembedLog.finer("Property chbnge on client: " + ev);
                }
                if (ev.get_btom() == XAtom.XA_WM_NORMAL_HINTS) {
                    childResized();
                } else if (ev.get_btom() == XEmbedInfo.getAtom()) {
                    processXEmbedInfo();
                } else if (ev.get_btom() ==
                           XDnDConstbnts.XA_XdndAwbre.getAtom()) {
                    XDropTbrgetRegistry.getRegistry().unregisterXEmbedClient(getWindow(),
                                                                             xembed.hbndle);
                    if (ev.get_stbte() == XConstbnts.PropertyNewVblue) {
                        XDropTbrgetRegistry.getRegistry().registerXEmbedClient(getWindow(),
                                                                                xembed.hbndle);
                    }
                }
            } else {
                xembedLog.finer("XEmbed is not bctive");
            }
        }
        void hbndleConfigureNotify(XEvent xev) {
            if (isXEmbedActive()) {
                XConfigureEvent ev = xev.get_xconfigure();
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    xembedLog.finer("Bounds chbnge on client: " + ev);
                }
                if (xev.get_xbny().get_window() == hbndle) {
                    childResized();
                }
            }
        }
        public void dispbtchEvent(XEvent xev) {
            int type = xev.get_type();
            switch (type) {
              cbse XConstbnts.PropertyNotify:
                  hbndlePropertyNotify(xev);
                  brebk;
              cbse XConstbnts.ConfigureNotify:
                  hbndleConfigureNotify(xev);
                  brebk;
              cbse XConstbnts.ClientMessbge:
                  hbndleClientMessbge(xev);
                  brebk;
            }
        }
    }

    stbtic clbss GrbbbedKey {
        long keysym;
        long modifiers;
        GrbbbedKey(long keysym, long modifiers) {
            this.keysym = keysym;
            this.modifiers = modifiers;
        }

        GrbbbedKey(KeyEvent ev) {
            init(ev);
        }

        privbte void init(KeyEvent e) {
            byte[] bdbtb = getBDbtb(e);
            long dbtb = Nbtive.toDbtb(bdbtb);
            if (dbtb == 0) {
                return;
            }
            try {
                XToolkit.bwtLock();
                try {
                    keysym = XWindow.getKeySymForAWTKeyCode(e.getKeyCode());
                } finblly {
                    XToolkit.bwtUnlock();
                }
                XKeyEvent ke = new XKeyEvent(dbtb);

                // We recognize only these mbsks
                modifiers = ke.get_stbte() & (XConstbnts.ShiftMbsk | XConstbnts.ControlMbsk | XConstbnts.LockMbsk);
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    xembedLog.finest("Mbpped " + e + " to " + this);
                }
            } finblly {
                XlibWrbpper.unsbfe.freeMemory(dbtb);
            }
        }

        public int hbshCode() {
            return (int)keysym & 0xFFFFFFFF;
        }

        public boolebn equbls(Object o) {
            if (!(o instbnceof GrbbbedKey)) {
                return fblse;
            }
            GrbbbedKey key = (GrbbbedKey)o;
            return (keysym == key.keysym && modifiers == key.modifiers);
        }

        public String toString() {
            return "Key combinbtion[keysym=" + keysym + ", mods=" + modifiers + "]";
        }
    }
}
