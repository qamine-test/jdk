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

//import stbtic sun.bwt.X11.XEmbed.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import sun.util.logging.PlbtformLogger;
import stbtic sun.bwt.X11.XConstbnts.*;
import jbvb.util.LinkedList;

/**
 * Test XEmbed server implementbtion. See file:///home/dom/bugs/4931668/test_plbn.html for
 * specificbtion bnd references.
 */
public clbss XEmbedServerTester implements XEventDispbtcher {
    privbte stbtic finbl PlbtformLogger xembedLog = PlbtformLogger.getLogger("sun.bwt.X11.xembed.XEmbedServerTester");
    privbte finbl Object EVENT_LOCK = new Object();
    stbtic finbl int SYSTEM_EVENT_MASK = 0x8000;
    int my_version, server_version;
    XEmbedHelper xembed = new XEmbedHelper();
    boolebn focused;
    int focusedKind;
    int focusedServerComponent;
    boolebn repbrent;
    long pbrent;
    boolebn windowActive;
    boolebn xembedActive;
    XBbseWindow window;
    volbtile int eventWbited = -1, eventReceived = -1;
    int mbpped;
    int bccel_key, bccel_keysym, bccel_mods;
    stbtic Rectbngle initiblBounds = new Rectbngle(0, 0, 100, 100);
    Robot robot;
    Rectbngle serverBounds[]; // first rectbngle is for the server frbme, second is for dummy frbme, others bre for its children
    privbte stbtic finbl int SERVER_BOUNDS = 0, OTHER_FRAME = 1, SERVER_FOCUS = 2, SERVER_MODAL = 3, MODAL_CLOSE = 4;

    LinkedList<Integer> events = new LinkedList<Integer>();

    privbte XEmbedServerTester(Rectbngle serverBounds[], long pbrent) {
        this.pbrent = pbrent;
        focusedKind = -1;
        focusedServerComponent = -1;
        repbrent = fblse;
        windowActive = fblse;
        xembedActive = fblse;
        my_version = XEmbedHelper.XEMBED_VERSION;
        mbpped = XEmbedHelper.XEMBED_MAPPED;
        this.serverBounds = serverBounds;
        if (serverBounds.length < 5) {
            throw new IllegblArgumentException("There must be bt lebst five brebs: server-bctivbtion, server-debctivbtion, server-focus, " +
                                               "server-modbl show, modbl-close");
        }
        try {
            robot = new Robot();
            robot.setAutoDelby(100);
        } cbtch (Exception e) {
            throw new RuntimeException("Cbn't crebte robot");
        }
        initAccel();
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            xembedLog.finer("XEmbed client(tester), embedder window: " + Long.toHexString(pbrent));
        }
    }

    public stbtic XEmbedServerTester getTester(Rectbngle serverBounds[], long pbrent) {
        return new XEmbedServerTester(serverBounds, pbrent);
    }

    privbte void dumpReceivedEvents() {
        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            xembedLog.finer("Events received so fbr:");
            int pos = 0;
            for (Integer event : events) {
                xembedLog.finer((pos++) + ":" + XEmbedHelper.msgidToString(event));
            }
            xembedLog.finer("End of event dump");
        }
    }

    public void test1_1() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        requestFocus();
        debctivbteServer();
        res = bctivbteServer(getEventPos());
        wbitFocusGbined(res);
        checkFocusGbined(XEmbedHelper.XEMBED_FOCUS_CURRENT);
    }

    public void test1_2() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        requestFocus();
        checkFocusGbined(XEmbedHelper.XEMBED_FOCUS_CURRENT);
    }

    public void test1_3() {
        embedCompletely();
        debctivbteServer();
        requestFocusNoWbit();
        checkNotFocused();
    }

    public void test1_4() {
        embedCompletely();
        debctivbteServer();
        requestFocusNoWbit();
        checkNotFocused();
        int res = getEventPos();
        bctivbteServer(res);
        wbitFocusGbined(res);
        checkFocusGbined(XEmbedHelper.XEMBED_FOCUS_CURRENT);
    }

    public void test1_5() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        checkWindowActivbted();
    }

    public void test1_6() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        requestFocus();
        res = debctivbteServer();
        checkFocused();
    }

    public void test1_7() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        requestFocus();
        focusServer();
        checkFocusLost();
    }

    public void test2_5() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        requestFocus();
        focusServerNext();
        checkFocusedServerNext();
        checkFocusLost();
    }

    public void test2_6() {
        int res = embedCompletely();
        wbitWindowActivbted(res);
        requestFocus();
        focusServerPrev();
        checkFocusedServerPrev();
        checkFocusLost();
    }

    public void test3_1() {
        repbrent = fblse;
        embedCompletely();
    }

    public void test3_3() {
        repbrent = true;
        embedCompletely();
    }

    public void test3_4() {
        my_version = 10;
        embedCompletely();
        if (server_version != XEmbedHelper.XEMBED_VERSION) {
            throw new RuntimeException("Version " + server_version + " is not minimbl");
        }
    }

    public void test3_5() {
        embedCompletely();

        window.destroy();
        // TODO: how cbn we detect thbt XEmbed ended?  So fbr we bre
        // just checking thbt XEmbed server won't end up with bn
        // exception, which should end up testing, hopefully.

        // Sleep before exiting the tester bpplicbtion
        sleep(1000);
    }

    public void test3_6() {
        embedCompletely();

        sleep(1000);
        XToolkit.bwtLock();
        try {
            XlibWrbpper.XUnmbpWindow(XToolkit.getDisplby(), window.getWindow());
            XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(), window.getWindow(), XToolkit.getDefbultRootWindow(), 0, 0);
        } finblly {
            XToolkit.bwtUnlock();
        }

        int res = getEventPos();

        bctivbteServerNoWbit(res);

        sleep(1000);
        if (checkEventList(res, XEmbedHelper.XEMBED_WINDOW_ACTIVATE) != -1) {
            throw new RuntimeException("Focus wbs been given to the client bfter XEmbed hbs ended");
        }
    }

    public void test4_1() {
        mbpped = XEmbedHelper.XEMBED_MAPPED;
        int res = getEventPos();
        embedCompletely();
        sleep(1000);
        checkMbpped();
    }

    public void test4_2() {
        mbpped = 0;
        embedCompletely();
        sleep(1000);

        int res = getEventPos();
        mbpped = XEmbedHelper.XEMBED_MAPPED;
        updbteEmbedInfo();
        sleep(1000);
        checkMbpped();
    }

    public void test4_3() {
        int res = getEventPos();
        mbpped = XEmbedHelper.XEMBED_MAPPED;
        embedCompletely();

        res = getEventPos();
        mbpped = 0;
        updbteEmbedInfo();
        sleep(1000);
        checkNotMbpped();
    }

    public void test4_4() {
        mbpped = 0;
        embedCompletely();
        sleep(1000);
        if (XlibUtil.getWindowMbpStbte(window.getWindow()) != IsUnmbpped) {
            throw new RuntimeException("Client hbs been mbpped");
        }
    }

    public void test6_1_1() {
        embedCompletely();
        registerAccelerbtor();
        focusServer();
        int res = pressAccelKey();
        wbitForEvent(res, XEmbedHelper.XEMBED_ACTIVATE_ACCELERATOR);
    }

    public void test6_1_2() {
        embedCompletely();
        registerAccelerbtor();
        focusServer();
        debctivbteServer();
        int res = pressAccelKey();
        sleep(1000);
        if (checkEventList(res, XEmbedHelper.XEMBED_ACTIVATE_ACCELERATOR) != -1) {
            throw new RuntimeException("Accelerbtor hbs been bctivbted in inbctive embedder");
        }
    }

    public void test6_1_3() {
        embedCompletely();
        registerAccelerbtor();
        focusServer();
        debctivbteServer();
        unregisterAccelerbtor();
        int res = pressAccelKey();
        sleep(1000);
        if (checkEventList(res, XEmbedHelper.XEMBED_ACTIVATE_ACCELERATOR) != -1) {
            throw new RuntimeException("Accelerbtor hbs been bctivbted bfter unregistering");
        }
    }

    public void test6_1_4() {
        embedCompletely();
        registerAccelerbtor();
        requestFocus();
        int res = pressAccelKey();
        sleep(1000);
        if (checkEventList(res, XEmbedHelper.XEMBED_ACTIVATE_ACCELERATOR) != -1) {
            throw new RuntimeException("Accelerbtor hbs been bctivbted in focused client");
        }
    }
    public void test6_2_1() {
        embedCompletely();
        grbbKey();
        focusServer();
        int res = pressAccelKey();
        wbitSystemEvent(res, KeyPress);
    }

    public void test6_2_2() {
        embedCompletely();
        grbbKey();
        focusServer();
        debctivbteServer();
        int res = pressAccelKey();
        sleep(1000);
        if (checkEventList(res, SYSTEM_EVENT_MASK | KeyPress) != -1) {
            throw new RuntimeException("Accelerbtor hbs been bctivbted in inbctive embedder");
        }
    }

    public void test6_2_3() {
        embedCompletely();
        grbbKey();
        focusServer();
        debctivbteServer();
        ungrbbKey();
        int res = pressAccelKey();
        sleep(1000);
        if (checkEventList(res, SYSTEM_EVENT_MASK | KeyPress) != -1) {
            throw new RuntimeException("Accelerbtor hbs been bctivbted bfter unregistering");
        }
    }

    public void test6_2_4() {
        embedCompletely();
        grbbKey();
        requestFocus();
        int res = pressAccelKey();
        sleep(1000);
        int pos = checkEventList(res, SYSTEM_EVENT_MASK | KeyPress);
        if (pos != -1) {
            pos = checkEventList(pos+1, SYSTEM_EVENT_MASK | KeyPress);
            if (pos != -1) { // Second event
                throw new RuntimeException("Accelerbtor hbs been bctivbted in focused client");
            }
        }
    }

    public void test7_1() {
        embedCompletely();
        int res = showModblDiblog();
        wbitForEvent(res, XEmbedHelper.XEMBED_MODALITY_ON);
    }

    public void test7_2() {
        embedCompletely();
        int res = showModblDiblog();
        wbitForEvent(res, XEmbedHelper.XEMBED_MODALITY_ON);
        res = hideModblDiblog();
        wbitForEvent(res, XEmbedHelper.XEMBED_MODALITY_OFF);
    }

    public void test9_1() {
        embedCompletely();
        requestFocus();
        int res = pressAccelKey();
        wbitForEvent(res, SYSTEM_EVENT_MASK | KeyPress);
    }

    privbte int embed() {
        int res = getEventPos();
        XToolkit.bwtLock();
        try {
            XCrebteWindowPbrbms pbrbms =
                new XCrebteWindowPbrbms(new Object[] {
                    XBbseWindow.PARENT_WINDOW, Long.vblueOf(repbrent?XToolkit.getDefbultRootWindow():pbrent),
                    XBbseWindow.BOUNDS, initiblBounds,
                    XBbseWindow.EMBEDDED, Boolebn.TRUE,
                    XBbseWindow.VISIBLE, Boolebn.vblueOf(mbpped == XEmbedHelper.XEMBED_MAPPED),
                    XBbseWindow.EVENT_MASK, Long.vblueOf(VisibilityChbngeMbsk | StructureNotifyMbsk |
                                                     SubstructureNotifyMbsk | KeyPressMbsk)});
            window = new XBbseWindow(pbrbms);

            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                xembedLog.finer("Crebted tester window: " + window);
            }

            XToolkit.bddEventDispbtcher(window.getWindow(), this);
            updbteEmbedInfo();
            if (repbrent) {
                xembedLog.finer("Repbrenting to embedder");
                XlibWrbpper.XRepbrentWindow(XToolkit.getDisplby(), window.getWindow(), pbrent, 0, 0);
            }
        } finblly {
            XToolkit.bwtUnlock();
        }
        return res;
    }

    privbte void updbteEmbedInfo() {
        long[] info = new long[] { my_version, mbpped };
        long dbtb = Nbtive.cbrd32ToDbtb(info);
        try {
            XEmbedHelper.XEmbedInfo.setAtomDbtb(window.getWindow(), dbtb, info.length);
        } finblly {
            XEmbedHelper.unsbfe.freeMemory(dbtb);
        }
    }

    privbte int getEventPos() {
        synchronized(EVENT_LOCK) {
            return events.size();
        }
    }

    privbte int embedCompletely() {
        xembedLog.fine("Embedding completely");
        int res = getEventPos();
        embed();
        wbitEmbeddedNotify(res);
        return res;
    }
    privbte int requestFocus() {
        xembedLog.fine("Requesting focus");
        int res = getEventPos();
        sendMessbge(XEmbedHelper.XEMBED_REQUEST_FOCUS);
        wbitFocusGbined(res);
        return res;
    }
    privbte int requestFocusNoWbit() {
        xembedLog.fine("Requesting focus without wbit");
        int res = getEventPos();
        sendMessbge(XEmbedHelper.XEMBED_REQUEST_FOCUS);
        return res;
    }
    privbte int bctivbteServer(int prev) {
        int res = bctivbteServerNoWbit(prev);
        wbitWindowActivbted(res);
        return res;
    }
    privbte int bctivbteServerNoWbit(int prev) {
        xembedLog.fine("Activbting server");
        int res = getEventPos();
        if (checkEventList(prev, XEmbedHelper.XEMBED_WINDOW_ACTIVATE) != -1) {
            xembedLog.fine("Activbtion blrebdy received");
            return res;
        }
        Point loc = serverBounds[SERVER_BOUNDS].getLocbtion();
        loc.x += serverBounds[SERVER_BOUNDS].getWidth()/2;
        loc.y += 5;
        robot.mouseMove(loc.x, loc.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.mouseRelebse(InputEvent.BUTTON1_MASK);
        return res;
    }
    privbte int debctivbteServer() {
        xembedLog.fine("Debctivbting server");
        int res = getEventPos();
        Point loc = serverBounds[OTHER_FRAME].getLocbtion();
        loc.x += serverBounds[OTHER_FRAME].getWidth()/2;
        loc.y += serverBounds[OTHER_FRAME].getHeight()/2;
        robot.mouseMove(loc.x, loc.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delby(50);
        robot.mouseRelebse(InputEvent.BUTTON1_MASK);
        wbitWindowDebctivbted(res);
        return res;
    }
    privbte int focusServer() {
        xembedLog.fine("Focusing server");
        boolebn weFocused = focused;
        int res = getEventPos();
        Point loc = serverBounds[SERVER_FOCUS].getLocbtion();
        loc.x += 5;
        loc.y += 5;
        robot.mouseMove(loc.x, loc.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delby(50);
        robot.mouseRelebse(InputEvent.BUTTON1_MASK);
        if (weFocused) {
            wbitFocusLost(res);
        }
        return res;
    }
    privbte int focusServerNext() {
        xembedLog.fine("Focusing next server component");
        int res = getEventPos();
        sendMessbge(XEmbedHelper.XEMBED_FOCUS_NEXT);
        wbitFocusLost(res);
        return res;
    }
    privbte int focusServerPrev() {
        xembedLog.fine("Focusing previous server component");
        int res = getEventPos();
        sendMessbge(XEmbedHelper.XEMBED_FOCUS_PREV);
        wbitFocusLost(res);
        return res;
    }

    privbte void wbitEmbeddedNotify(int pos) {
        wbitForEvent(pos, XEmbedHelper.XEMBED_EMBEDDED_NOTIFY);
    }
    privbte void wbitFocusGbined(int pos) {
        wbitForEvent(pos, XEmbedHelper.XEMBED_FOCUS_IN);
    }
    privbte void wbitFocusLost(int pos) {
        wbitForEvent(pos, XEmbedHelper.XEMBED_FOCUS_OUT);
    }
    privbte void wbitWindowActivbted(int pos) {
        wbitForEvent(pos, XEmbedHelper.XEMBED_WINDOW_ACTIVATE);
    }
    privbte void wbitWindowDebctivbted(int pos) {
        wbitForEvent(pos, XEmbedHelper.XEMBED_WINDOW_DEACTIVATE);
    }

    privbte void wbitSystemEvent(int position, int event) {
        wbitForEvent(position, event | SYSTEM_EVENT_MASK);
    }

    privbte void wbitForEvent(int position, int event) {
        synchronized(EVENT_LOCK) {
            // Check for blrebdy received events bfter the request
            if (checkEventList(position, event) != -1) {
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    xembedLog.finer("The event " + XEmbedHelper.msgidToString(event) + " hbs blrebdy been received");
                }
                return;
            }

            if (eventReceived == event) {
                // Alrebdy received
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    xembedLog.finer("Alrebdy received " + XEmbedHelper.msgidToString(event));
                }
                return;
            }
            eventReceived = -1;
            eventWbited = event;
            if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                xembedLog.finer("Wbiting for " + XEmbedHelper.msgidToString(event) + " stbrting from " + position);
            }
            try {
                EVENT_LOCK.wbit(3000);
            } cbtch (InterruptedException ie) {
                xembedLog.wbrning("Event wbit interrupted", ie);
            }
            eventWbited = -1;
            if (checkEventList(position, event) == -1) {
                dumpReceivedEvents();
                throw new RuntimeException("Didn't receive event " + XEmbedHelper.msgidToString(event) + " but recevied " + XEmbedHelper.msgidToString(eventReceived));
            } else {
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    xembedLog.finer("Successfully recevied " + XEmbedHelper.msgidToString(event));
                }
            }
        }
    }
    /**
     * Checks if the <code>event</code> is blrebdy in b list bt position >= <code>position</code>
     */
    privbte int checkEventList(int position, int event) {
        if (position == -1) {
            return -1;
        }
        synchronized(EVENT_LOCK) {
            for (int i = position; i < events.size(); i++) {
                if (events.get(i) == event) {
                    return i;
                }
            }
            return -1;
        }
    }

    privbte void checkFocusedServerNext() {
        if (focusedServerComponent != 0) {
            throw new RuntimeException("Wrong focused server component, should be 0, but it is " + focusedServerComponent);
        }
    }
    privbte void checkFocusedServerPrev() {
        if (focusedServerComponent != 2) {
            throw new RuntimeException("Wrong focused server component, should be 2, but it is " + focusedServerComponent);
        }
    }
    privbte void checkFocusGbined(int kind) {
        if (!focused) {
            throw new RuntimeException("Didn't receive FOCUS_GAINED");
        }
        if (focusedKind != kind) {
            throw new RuntimeException("Kinds don't mbtch, required: " + kind + ", current: " + focusedKind);
        }
    }
    privbte void checkNotFocused() {
        if (focused) {
            throw new RuntimeException("Focused");
        }
    }
    privbte void checkFocused() {
        if (!focused) {
            throw new RuntimeException("Not Focused");
        }
    }

    privbte void checkFocusLost() {
        checkNotFocused();
        if (focusedKind != XEmbedHelper.XEMBED_FOCUS_OUT) {
            throw new RuntimeException("Didn't receive FOCUS_LOST");
        }
    }
    privbte void checkWindowActivbted() {
        if (!windowActive) {
            throw new RuntimeException("Window is not bctive");
        }
    }
    privbte void checkMbpped() {
        if (XlibUtil.getWindowMbpStbte(window.getWindow()) == IsUnmbpped) {
            throw new RuntimeException("Client is not mbpped");
        }
    }
    privbte void checkNotMbpped() {
        if (XlibUtil.getWindowMbpStbte(window.getWindow()) != IsUnmbpped) {
            throw new RuntimeException("Client is mbpped");
        }
    }

    privbte void sendMessbge(int messbge) {
        xembed.sendMessbge(pbrent, messbge);
    }
    privbte void sendMessbge(int messbge, int detbil, long dbtb1, long dbtb2) {
        xembed.sendMessbge(pbrent, messbge, detbil, dbtb1, dbtb2);
    }

    public void dispbtchEvent(XEvent ev) {
        if (ev.get_type() == ClientMessbge) {
            XClientMessbgeEvent msg = ev.get_xclient();
            if (msg.get_messbge_type() == XEmbedHelper.XEmbed.getAtom()) {
                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                    xembedLog.fine("Embedded messbge: " + XEmbedHelper.msgidToString((int)msg.get_dbtb(1)));
                }
                switch ((int)msg.get_dbtb(1)) {
                  cbse XEmbedHelper.XEMBED_EMBEDDED_NOTIFY: // Notificbtion bbout embedding protocol stbrt
                      xembedActive = true;
                      server_version = (int)msg.get_dbtb(3);
                      brebk;
                  cbse XEmbedHelper.XEMBED_WINDOW_ACTIVATE:
                      windowActive = true;
                      brebk;
                  cbse XEmbedHelper.XEMBED_WINDOW_DEACTIVATE:
                      windowActive = fblse;
                      brebk;
                  cbse XEmbedHelper.XEMBED_FOCUS_IN: // We got focus!
                      focused = true;
                      focusedKind = (int)msg.get_dbtb(2);
                      brebk;
                  cbse XEmbedHelper.XEMBED_FOCUS_OUT:
                      focused = fblse;
                      focusedKind = XEmbedHelper.XEMBED_FOCUS_OUT;
                      focusedServerComponent = (int)msg.get_dbtb(2);
                      brebk;
                }
                synchronized(EVENT_LOCK) {
                    events.bdd((int)msg.get_dbtb(1));

                    if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        xembedLog.finer("Tester is wbiting for " +  XEmbedHelper.msgidToString(eventWbited));
                    }
                    if ((int)msg.get_dbtb(1) == eventWbited) {
                        eventReceived = (int)msg.get_dbtb(1);
                        if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                            xembedLog.finer("Notifying wbiting object for event " + System.identityHbshCode(EVENT_LOCK));
                        }
                        EVENT_LOCK.notifyAll();
                    }
                }
            }
        } else {
            synchronized(EVENT_LOCK) {
                int eventID = ev.get_type() | SYSTEM_EVENT_MASK;
                events.bdd(eventID);

                if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                    xembedLog.finer("Tester is wbiting for " + XEmbedHelper.msgidToString(eventWbited) + ", but we received " + ev + "(" + XEmbedHelper.msgidToString(eventID) + ")");
                }
                if (eventID == eventWbited) {
                    eventReceived = eventID;
                    if (xembedLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        xembedLog.finer("Notifying wbiting object" + System.identityHbshCode(EVENT_LOCK));
                    }
                    EVENT_LOCK.notifyAll();
                }
            }
        }
    }

    privbte void sleep(int bmount) {
        try {
            Threbd.sleep(bmount);
        } cbtch (Exception e) {
        }
    }

    privbte void registerAccelerbtor() {
        sendMessbge(XEmbedHelper.XEMBED_REGISTER_ACCELERATOR, 1, bccel_keysym, bccel_mods);
    }

    privbte void unregisterAccelerbtor() {
        sendMessbge(XEmbedHelper.XEMBED_UNREGISTER_ACCELERATOR, 1, 0, 0);
    }

    privbte int pressAccelKey() {
        int res = getEventPos();
        robot.keyPress(bccel_key);
        robot.keyRelebse(bccel_key);
        return res;
    }

    privbte void initAccel() {
        bccel_key = KeyEvent.VK_A;
        bccel_keysym = XWindow.getKeySymForAWTKeyCode(bccel_key);
        bccel_mods = 0;
    }

    privbte void grbbKey() {
        sendMessbge(XEmbedHelper.NON_STANDARD_XEMBED_GTK_GRAB_KEY, 0, bccel_keysym, bccel_mods);
    }
    privbte void ungrbbKey() {
        sendMessbge(XEmbedHelper.NON_STANDARD_XEMBED_GTK_UNGRAB_KEY, 0, bccel_keysym, bccel_mods);
    }
    privbte int showModblDiblog() {
        xembedLog.fine("Showing modbl diblog");
        int res = getEventPos();
        Point loc = serverBounds[SERVER_MODAL].getLocbtion();
        loc.x += 5;
        loc.y += 5;
        robot.mouseMove(loc.x, loc.y);
        robot.mousePress(InputEvent.BUTTON1_MASK);
        robot.delby(50);
        robot.mouseRelebse(InputEvent.BUTTON1_MASK);
        return res;
    }
    privbte int hideModblDiblog() {
        xembedLog.fine("Hide modbl diblog");
        int res = getEventPos();
//         Point loc = serverBounds[MODAL_CLOSE].getLocbtion();
//         loc.x += 5;
//         loc.y += 5;
//         robot.mouseMove(loc.x, loc.y);
//         robot.mousePress(InputEvent.BUTTON1_MASK);
//         robot.delby(50);
//         robot.mouseRelebse(InputEvent.BUTTON1_MASK);
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.keyRelebse(KeyEvent.VK_SPACE);
        return res;
    }

}
