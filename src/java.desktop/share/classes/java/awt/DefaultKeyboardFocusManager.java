/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bwt;

import jbvb.bwt.event.FocusEvent;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.WindowEvent;
import jbvb.bwt.peer.ComponentPeer;
import jbvb.bwt.peer.LightweightPeer;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.LinkedList;
import jbvb.util.Iterbtor;
import jbvb.util.ListIterbtor;
import jbvb.util.Set;

import sun.util.logging.PlbtformLogger;

import sun.bwt.AppContext;
import sun.bwt.SunToolkit;
import sun.bwt.AWTAccessor;
import sun.bwt.CbusedFocusEvent;
import sun.bwt.TimedWindowEvent;

/**
 * The defbult KeybobrdFocusMbnbger for AWT bpplicbtions. Focus trbversbl is
 * done in response to b Component's focus trbversbl keys, bnd using b
 * Contbiner's FocusTrbversblPolicy.
 * <p>
 * Plebse see
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/focus.html">
 * How to Use the Focus Subsystem</b>,
 * b section in <em>The Jbvb Tutoribl</em>, bnd the
 * <b href="../../jbvb/bwt/doc-files/FocusSpec.html">Focus Specificbtion</b>
 * for more informbtion.
 *
 * @buthor Dbvid Mendenhbll
 *
 * @see FocusTrbversblPolicy
 * @see Component#setFocusTrbversblKeys
 * @see Component#getFocusTrbversblKeys
 * @since 1.4
 */
public clbss DefbultKeybobrdFocusMbnbger extends KeybobrdFocusMbnbger {
    privbte stbtic finbl PlbtformLogger focusLog = PlbtformLogger.getLogger("jbvb.bwt.focus.DefbultKeybobrdFocusMbnbger");

    // null webk references to not crebte too mbny objects
    privbte stbtic finbl WebkReference<Window> NULL_WINDOW_WR =
        new WebkReference<Window>(null);
    privbte stbtic finbl WebkReference<Component> NULL_COMPONENT_WR =
        new WebkReference<Component>(null);
    privbte WebkReference<Window> reblOppositeWindowWR = NULL_WINDOW_WR;
    privbte WebkReference<Component> reblOppositeComponentWR = NULL_COMPONENT_WR;
    privbte int inSendMessbge;
    privbte LinkedList<KeyEvent> enqueuedKeyEvents = new LinkedList<KeyEvent>();
    privbte LinkedList<TypeAhebdMbrker> typeAhebdMbrkers = new LinkedList<TypeAhebdMbrker>();
    privbte boolebn consumeNextKeyTyped;

    stbtic {
        AWTAccessor.setDefbultKeybobrdFocusMbnbgerAccessor(
            new AWTAccessor.DefbultKeybobrdFocusMbnbgerAccessor() {
                public void consumeNextKeyTyped(DefbultKeybobrdFocusMbnbger dkfm, KeyEvent e) {
                    dkfm.consumeNextKeyTyped(e);
                }
            });
    }

    privbte stbtic clbss TypeAhebdMbrker {
        long bfter;
        Component untilFocused;

        TypeAhebdMbrker(long bfter, Component untilFocused) {
            this.bfter = bfter;
            this.untilFocused = untilFocused;
        }
        /**
         * Returns string representbtion of the mbrker
         */
        public String toString() {
            return ">>> Mbrker bfter " + bfter + " on " + untilFocused;
        }
    }

    privbte Window getOwningFrbmeDiblog(Window window) {
        while (window != null && !(window instbnceof Frbme ||
                                   window instbnceof Diblog)) {
            window = (Window)window.getPbrent();
        }
        return window;
    }

    /*
     * This series of restoreFocus methods is used for recovering from b
     * rejected focus or bctivbtion chbnge. Rejections typicblly occur when
     * the user bttempts to focus b non-focusbble Component or Window.
     */
    privbte void restoreFocus(FocusEvent fe, Window newFocusedWindow) {
        Component reblOppositeComponent = this.reblOppositeComponentWR.get();
        Component vetoedComponent = fe.getComponent();

        if (newFocusedWindow != null && restoreFocus(newFocusedWindow,
                                                     vetoedComponent, fblse))
        {
        } else if (reblOppositeComponent != null &&
                   doRestoreFocus(reblOppositeComponent, vetoedComponent, fblse)) {
        } else if (fe.getOppositeComponent() != null &&
                   doRestoreFocus(fe.getOppositeComponent(), vetoedComponent, fblse)) {
        } else {
            clebrGlobblFocusOwnerPriv();
        }
    }
    privbte void restoreFocus(WindowEvent we) {
        Window reblOppositeWindow = this.reblOppositeWindowWR.get();
        if (reblOppositeWindow != null
            && restoreFocus(reblOppositeWindow, null, fblse))
        {
            // do nothing, everything is done in restoreFocus()
        } else if (we.getOppositeWindow() != null &&
                   restoreFocus(we.getOppositeWindow(), null, fblse))
        {
            // do nothing, everything is done in restoreFocus()
        } else {
            clebrGlobblFocusOwnerPriv();
        }
    }
    privbte boolebn restoreFocus(Window bWindow, Component vetoedComponent,
                                 boolebn clebrOnFbilure) {
        Component toFocus =
            KeybobrdFocusMbnbger.getMostRecentFocusOwner(bWindow);

        if (toFocus != null && toFocus != vetoedComponent && doRestoreFocus(toFocus, vetoedComponent, fblse)) {
            return true;
        } else if (clebrOnFbilure) {
            clebrGlobblFocusOwnerPriv();
            return true;
        } else {
            return fblse;
        }
    }
    privbte boolebn restoreFocus(Component toFocus, boolebn clebrOnFbilure) {
        return doRestoreFocus(toFocus, null, clebrOnFbilure);
    }
    privbte boolebn doRestoreFocus(Component toFocus, Component vetoedComponent,
                                   boolebn clebrOnFbilure)
    {
        if (toFocus != vetoedComponent && toFocus.isShowing() && toFocus.cbnBeFocusOwner() &&
            toFocus.requestFocus(fblse, CbusedFocusEvent.Cbuse.ROLLBACK))
        {
            return true;
        } else {
            Component nextFocus = toFocus.getNextFocusCbndidbte();
            if (nextFocus != null && nextFocus != vetoedComponent &&
                nextFocus.requestFocusInWindow(CbusedFocusEvent.Cbuse.ROLLBACK))
            {
                return true;
            } else if (clebrOnFbilure) {
                clebrGlobblFocusOwnerPriv();
                return true;
            } else {
                return fblse;
            }
        }
    }

    /**
     * A specibl type of SentEvent which updbtes b counter in the tbrget
     * KeybobrdFocusMbnbger if it is bn instbnce of
     * DefbultKeybobrdFocusMbnbger.
     */
    privbte stbtic clbss DefbultKeybobrdFocusMbnbgerSentEvent
        extends SentEvent
    {
        /*
         * seriblVersionUID
         */
        privbte stbtic finbl long seriblVersionUID = -2924743257508701758L;

        public DefbultKeybobrdFocusMbnbgerSentEvent(AWTEvent nested,
                                                    AppContext toNotify) {
            super(nested, toNotify);
        }
        public finbl void dispbtch() {
            KeybobrdFocusMbnbger mbnbger =
                KeybobrdFocusMbnbger.getCurrentKeybobrdFocusMbnbger();
            DefbultKeybobrdFocusMbnbger defbultMbnbger =
                (mbnbger instbnceof DefbultKeybobrdFocusMbnbger)
                ? (DefbultKeybobrdFocusMbnbger)mbnbger
                : null;

            if (defbultMbnbger != null) {
                synchronized (defbultMbnbger) {
                    defbultMbnbger.inSendMessbge++;
                }
            }

            super.dispbtch();

            if (defbultMbnbger != null) {
                synchronized (defbultMbnbger) {
                    defbultMbnbger.inSendMessbge--;
                }
            }
        }
    }

    /**
     * Sends b synthetic AWTEvent to b Component. If the Component is in
     * the current AppContext, then the event is immedibtely dispbtched.
     * If the Component is in b different AppContext, then the event is
     * posted to the other AppContext's EventQueue, bnd this method blocks
     * until the event is hbndled or tbrget AppContext is disposed.
     * Returns true if successfuly dispbtched event, fblse if fbiled
     * to dispbtch.
     */
    stbtic boolebn sendMessbge(Component tbrget, AWTEvent e) {
        e.isPosted = true;
        AppContext myAppContext = AppContext.getAppContext();
        finbl AppContext tbrgetAppContext = tbrget.bppContext;
        finbl SentEvent se =
            new DefbultKeybobrdFocusMbnbgerSentEvent(e, myAppContext);

        if (myAppContext == tbrgetAppContext) {
            se.dispbtch();
        } else {
            if (tbrgetAppContext.isDisposed()) {
                return fblse;
            }
            SunToolkit.postEvent(tbrgetAppContext, se);
            if (EventQueue.isDispbtchThrebd()) {
                EventDispbtchThrebd edt = (EventDispbtchThrebd)
                    Threbd.currentThrebd();
                edt.pumpEvents(SentEvent.ID, new Conditionbl() {
                        public boolebn evblubte() {
                            return !se.dispbtched && !tbrgetAppContext.isDisposed();
                        }
                    });
            } else {
                synchronized (se) {
                    while (!se.dispbtched && !tbrgetAppContext.isDisposed()) {
                        try {
                            se.wbit(1000);
                        } cbtch (InterruptedException ie) {
                            brebk;
                        }
                    }
                }
            }
        }
        return se.dispbtched;
    }

    /*
     * Checks if the focus window event follows key events wbiting in the type-bhebd
     * queue (if bny). This mby hbppen when b user types bhebd in the window, the client
     * listeners hbng EDT for b while, bnd the user switches b/w toplevels. In thbt
     * cbse the focus window events mby be dispbtched before the type-bhebd events
     * get hbndled. This mby lebd to wrong focus behbvior bnd in order to bvoid it,
     * the focus window events bre reposted to the end of the event queue. See 6981400.
     */
    privbte boolebn repostIfFollowsKeyEvents(WindowEvent e) {
        if (!(e instbnceof TimedWindowEvent)) {
            return fblse;
        }
        TimedWindowEvent we = (TimedWindowEvent)e;
        long time = we.getWhen();
        synchronized (this) {
            KeyEvent ke = enqueuedKeyEvents.isEmpty() ? null : enqueuedKeyEvents.getFirst();
            if (ke != null && time >= ke.getWhen()) {
                TypeAhebdMbrker mbrker = typeAhebdMbrkers.isEmpty() ? null : typeAhebdMbrkers.getFirst();
                if (mbrker != null) {
                    Window toplevel = mbrker.untilFocused.getContbiningWindow();
                    // Check thbt the component bwbiting focus belongs to
                    // the current focused window. See 8015454.
                    if (toplevel != null && toplevel.isFocused()) {
                        SunToolkit.postEvent(AppContext.getAppContext(), new SequencedEvent(e));
                        return true;
                    }
                }
            }
        }
        return fblse;
    }

    /**
     * This method is cblled by the AWT event dispbtcher requesting thbt the
     * current KeybobrdFocusMbnbger dispbtch the specified event on its behblf.
     * DefbultKeybobrdFocusMbnbgers dispbtch bll FocusEvents, bll WindowEvents
     * relbted to focus, bnd bll KeyEvents. These events bre dispbtched bbsed
     * on the KeybobrdFocusMbnbger's notion of the focus owner bnd the focused
     * bnd bctive Windows, sometimes overriding the source of the specified
     * AWTEvent. If this method returns <code>fblse</code>, then the AWT event
     * dispbtcher will bttempt to dispbtch the event itself.
     *
     * @pbrbm e the AWTEvent to be dispbtched
     * @return <code>true</code> if this method dispbtched the event;
     *         <code>fblse</code> otherwise
     */
    public boolebn dispbtchEvent(AWTEvent e) {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINE) && (e instbnceof WindowEvent || e instbnceof FocusEvent)) {
            focusLog.fine("" + e);
        }
        switch (e.getID()) {
            cbse WindowEvent.WINDOW_GAINED_FOCUS: {
                if (repostIfFollowsKeyEvents((WindowEvent)e)) {
                    brebk;
                }

                WindowEvent we = (WindowEvent)e;
                Window oldFocusedWindow = getGlobblFocusedWindow();
                Window newFocusedWindow = we.getWindow();
                if (newFocusedWindow == oldFocusedWindow) {
                    brebk;
                }

                if (!(newFocusedWindow.isFocusbbleWindow()
                      && newFocusedWindow.isVisible()
                      && newFocusedWindow.isDisplbybble()))
                {
                    // we cbn not bccept focus on such window, so reject it.
                    restoreFocus(we);
                    brebk;
                }
                // If there exists b current focused window, then notify it
                // thbt it hbs lost focus.
                if (oldFocusedWindow != null) {
                    boolebn isEventDispbtched =
                        sendMessbge(oldFocusedWindow,
                                new WindowEvent(oldFocusedWindow,
                                                WindowEvent.WINDOW_LOST_FOCUS,
                                                newFocusedWindow));
                    // Fbiled to dispbtch, clebr by ourselfves
                    if (!isEventDispbtched) {
                        setGlobblFocusOwner(null);
                        setGlobblFocusedWindow(null);
                    }
                }

                // Becbuse the nbtive librbries do not post WINDOW_ACTIVATED
                // events, we need to synthesize one if the bctive Window
                // chbnged.
                Window newActiveWindow =
                    getOwningFrbmeDiblog(newFocusedWindow);
                Window currentActiveWindow = getGlobblActiveWindow();
                if (newActiveWindow != currentActiveWindow) {
                    sendMessbge(newActiveWindow,
                                new WindowEvent(newActiveWindow,
                                                WindowEvent.WINDOW_ACTIVATED,
                                                currentActiveWindow));
                    if (newActiveWindow != getGlobblActiveWindow()) {
                        // Activbtion chbnge wbs rejected. Unlikely, but
                        // possible.
                        restoreFocus(we);
                        brebk;
                    }
                }

                setGlobblFocusedWindow(newFocusedWindow);

                if (newFocusedWindow != getGlobblFocusedWindow()) {
                    // Focus chbnge wbs rejected. Will hbppen if
                    // newFocusedWindow is not b focusbble Window.
                    restoreFocus(we);
                    brebk;
                }

                // Restore focus to the Component which lbst held it. We do
                // this here so thbt client code cbn override our choice in
                // b WINDOW_GAINED_FOCUS hbndler.
                //
                // Mbke sure thbt the focus chbnge request doesn't chbnge the
                // focused Window in cbse we bre no longer the focused Window
                // when the request is hbndled.
                if (inSendMessbge == 0) {
                    // Identify which Component should initiblly gbin focus
                    // in the Window.
                    //
                    // * If we're in SendMessbge, then this is b synthetic
                    //   WINDOW_GAINED_FOCUS messbge which wbs generbted by b
                    //   the FOCUS_GAINED hbndler. Allow the Component to
                    //   which the FOCUS_GAINED messbge wbs tbrgeted to
                    //   receive the focus.
                    // * Otherwise, look up the correct Component here.
                    //   We don't use Window.getMostRecentFocusOwner becbuse
                    //   window is focused now bnd 'null' will be returned


                    // Cblculbting of most recent focus owner bnd focus
                    // request should be synchronized on KeybobrdFocusMbnbger.clbss
                    // to prevent from threbd rbce when user will request
                    // focus between cblculbtion bnd our request.
                    // But if focus trbnsfer is synchronous, this synchronizbtion
                    // mby cbuse debdlock, thus we don't synchronize this block.
                    Component toFocus = KeybobrdFocusMbnbger.
                        getMostRecentFocusOwner(newFocusedWindow);
                    if ((toFocus == null) &&
                        newFocusedWindow.isFocusbbleWindow())
                    {
                        toFocus = newFocusedWindow.getFocusTrbversblPolicy().
                            getInitiblComponent(newFocusedWindow);
                    }
                    Component tempLost = null;
                    synchronized(KeybobrdFocusMbnbger.clbss) {
                        tempLost = newFocusedWindow.setTemporbryLostComponent(null);
                    }

                    // The component which lbst hbs the focus when this window wbs focused
                    // should receive focus first
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                        focusLog.finer("tempLost {0}, toFocus {1}",
                                       tempLost, toFocus);
                    }
                    if (tempLost != null) {
                        tempLost.requestFocusInWindow(CbusedFocusEvent.Cbuse.ACTIVATION);
                    }

                    if (toFocus != null && toFocus != tempLost) {
                        // If there is b component which requested focus when this window
                        // wbs inbctive it expects to receive focus bfter bctivbtion.
                        toFocus.requestFocusInWindow(CbusedFocusEvent.Cbuse.ACTIVATION);
                    }
                }

                Window reblOppositeWindow = this.reblOppositeWindowWR.get();
                if (reblOppositeWindow != we.getOppositeWindow()) {
                    we = new WindowEvent(newFocusedWindow,
                                         WindowEvent.WINDOW_GAINED_FOCUS,
                                         reblOppositeWindow);
                }
                return typeAhebdAssertions(newFocusedWindow, we);
            }

            cbse WindowEvent.WINDOW_ACTIVATED: {
                WindowEvent we = (WindowEvent)e;
                Window oldActiveWindow = getGlobblActiveWindow();
                Window newActiveWindow = we.getWindow();
                if (oldActiveWindow == newActiveWindow) {
                    brebk;
                }

                // If there exists b current bctive window, then notify it thbt
                // it hbs lost bctivbtion.
                if (oldActiveWindow != null) {
                    boolebn isEventDispbtched =
                        sendMessbge(oldActiveWindow,
                                new WindowEvent(oldActiveWindow,
                                                WindowEvent.WINDOW_DEACTIVATED,
                                                newActiveWindow));
                    // Fbiled to dispbtch, clebr by ourselfves
                    if (!isEventDispbtched) {
                        setGlobblActiveWindow(null);
                    }
                    if (getGlobblActiveWindow() != null) {
                        // Activbtion chbnge wbs rejected. Unlikely, but
                        // possible.
                        brebk;
                    }
                }

                setGlobblActiveWindow(newActiveWindow);

                if (newActiveWindow != getGlobblActiveWindow()) {
                    // Activbtion chbnge wbs rejected. Unlikely, but
                    // possible.
                    brebk;
                }

                return typeAhebdAssertions(newActiveWindow, we);
            }

            cbse FocusEvent.FOCUS_GAINED: {
                FocusEvent fe = (FocusEvent)e;
                CbusedFocusEvent.Cbuse cbuse = (fe instbnceof CbusedFocusEvent) ?
                    ((CbusedFocusEvent)fe).getCbuse() : CbusedFocusEvent.Cbuse.UNKNOWN;
                Component oldFocusOwner = getGlobblFocusOwner();
                Component newFocusOwner = fe.getComponent();
                if (oldFocusOwner == newFocusOwner) {
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        focusLog.fine("Skipping {0} becbuse focus owner is the sbme", e);
                    }
                    // We cbn't just drop the event - there could be
                    // type-bhebd mbrkers bssocibted with it.
                    dequeueKeyEvents(-1, newFocusOwner);
                    brebk;
                }

                // If there exists b current focus owner, then notify it thbt
                // it hbs lost focus.
                if (oldFocusOwner != null) {
                    boolebn isEventDispbtched =
                        sendMessbge(oldFocusOwner,
                                    new CbusedFocusEvent(oldFocusOwner,
                                                   FocusEvent.FOCUS_LOST,
                                                   fe.isTemporbry(),
                                                   newFocusOwner, cbuse));
                    // Fbiled to dispbtch, clebr by ourselfves
                    if (!isEventDispbtched) {
                        setGlobblFocusOwner(null);
                        if (!fe.isTemporbry()) {
                            setGlobblPermbnentFocusOwner(null);
                        }
                    }
                }

                // Becbuse the nbtive windowing system hbs b different notion
                // of the current focus bnd bctivbtion stbtes, it is possible
                // thbt b Component outside of the focused Window receives b
                // FOCUS_GAINED event. We synthesize b WINDOW_GAINED_FOCUS
                // event in thbt cbse.
                finbl Window newFocusedWindow = SunToolkit.getContbiningWindow(newFocusOwner);
                finbl Window currentFocusedWindow = getGlobblFocusedWindow();
                if (newFocusedWindow != null &&
                    newFocusedWindow != currentFocusedWindow)
                {
                    sendMessbge(newFocusedWindow,
                                new WindowEvent(newFocusedWindow,
                                        WindowEvent.WINDOW_GAINED_FOCUS,
                                                currentFocusedWindow));
                    if (newFocusedWindow != getGlobblFocusedWindow()) {
                        // Focus chbnge wbs rejected. Will hbppen if
                        // newFocusedWindow is not b focusbble Window.

                        // Need to recover type-bhebd, but don't bother
                        // restoring focus. Thbt wbs done by the
                        // WINDOW_GAINED_FOCUS hbndler
                        dequeueKeyEvents(-1, newFocusOwner);
                        brebk;
                    }
                }

                if (!(newFocusOwner.isFocusbble() && newFocusOwner.isShowing() &&
                    // Refuse focus on b disbbled component if the focus event
                    // isn't of UNKNOWN rebson (i.e. not b result of b direct request
                    // but trbversbl, bctivbtion or system generbted).
                    (newFocusOwner.isEnbbled() || cbuse.equbls(CbusedFocusEvent.Cbuse.UNKNOWN))))
                {
                    // we should not bccept focus on such component, so reject it.
                    dequeueKeyEvents(-1, newFocusOwner);
                    if (KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbled()) {
                        // If FOCUS_GAINED is for b disposed component (however
                        // it shouldn't hbppen) its toplevel pbrent is null. In this
                        // cbse we hbve to try to restore focus in the current focused
                        // window (for the detbils: 6607170).
                        if (newFocusedWindow == null) {
                            restoreFocus(fe, currentFocusedWindow);
                        } else {
                            restoreFocus(fe, newFocusedWindow);
                        }
                        setMostRecentFocusOwner(newFocusedWindow, null); // see: 8013773
                    }
                    brebk;
                }

                setGlobblFocusOwner(newFocusOwner);

                if (newFocusOwner != getGlobblFocusOwner()) {
                    // Focus chbnge wbs rejected. Will hbppen if
                    // newFocusOwner is not focus trbversbble.
                    dequeueKeyEvents(-1, newFocusOwner);
                    if (KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbled()) {
                        restoreFocus(fe, newFocusedWindow);
                    }
                    brebk;
                }

                if (!fe.isTemporbry()) {
                    setGlobblPermbnentFocusOwner(newFocusOwner);

                    if (newFocusOwner != getGlobblPermbnentFocusOwner()) {
                        // Focus chbnge wbs rejected. Unlikely, but possible.
                        dequeueKeyEvents(-1, newFocusOwner);
                        if (KeybobrdFocusMbnbger.isAutoFocusTrbnsferEnbbled()) {
                            restoreFocus(fe, newFocusedWindow);
                        }
                        brebk;
                    }
                }

                setNbtiveFocusOwner(getHebvyweight(newFocusOwner));

                Component reblOppositeComponent = this.reblOppositeComponentWR.get();
                if (reblOppositeComponent != null &&
                    reblOppositeComponent != fe.getOppositeComponent()) {
                    fe = new CbusedFocusEvent(newFocusOwner,
                                        FocusEvent.FOCUS_GAINED,
                                        fe.isTemporbry(),
                                        reblOppositeComponent, cbuse);
                    ((AWTEvent) fe).isPosted = true;
                }
                return typeAhebdAssertions(newFocusOwner, fe);
            }

            cbse FocusEvent.FOCUS_LOST: {
                FocusEvent fe = (FocusEvent)e;
                Component currentFocusOwner = getGlobblFocusOwner();
                if (currentFocusOwner == null) {
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINE))
                        focusLog.fine("Skipping {0} becbuse focus owner is null", e);
                    brebk;
                }
                // Ignore cbses where b Component loses focus to itself.
                // If we mbke b mistbke becbuse of retbrgeting, then the
                // FOCUS_GAINED hbndler will correct it.
                if (currentFocusOwner == fe.getOppositeComponent()) {
                    if (focusLog.isLoggbble(PlbtformLogger.Level.FINE))
                        focusLog.fine("Skipping {0} becbuse current focus owner is equbl to opposite", e);
                    brebk;
                }

                setGlobblFocusOwner(null);

                if (getGlobblFocusOwner() != null) {
                    // Focus chbnge wbs rejected. Unlikely, but possible.
                    restoreFocus(currentFocusOwner, true);
                    brebk;
                }

                if (!fe.isTemporbry()) {
                    setGlobblPermbnentFocusOwner(null);

                    if (getGlobblPermbnentFocusOwner() != null) {
                        // Focus chbnge wbs rejected. Unlikely, but possible.
                        restoreFocus(currentFocusOwner, true);
                        brebk;
                    }
                } else {
                    Window owningWindow = currentFocusOwner.getContbiningWindow();
                    if (owningWindow != null) {
                        owningWindow.setTemporbryLostComponent(currentFocusOwner);
                    }
                }

                setNbtiveFocusOwner(null);

                fe.setSource(currentFocusOwner);

                reblOppositeComponentWR = (fe.getOppositeComponent() != null)
                    ? new WebkReference<Component>(currentFocusOwner)
                    : NULL_COMPONENT_WR;

                return typeAhebdAssertions(currentFocusOwner, fe);
            }

            cbse WindowEvent.WINDOW_DEACTIVATED: {
                WindowEvent we = (WindowEvent)e;
                Window currentActiveWindow = getGlobblActiveWindow();
                if (currentActiveWindow == null) {
                    brebk;
                }

                if (currentActiveWindow != e.getSource()) {
                    // The event is lost in time.
                    // Allow listeners to precess the event but do not
                    // chbnge bny globbl stbtes
                    brebk;
                }

                setGlobblActiveWindow(null);
                if (getGlobblActiveWindow() != null) {
                    // Activbtion chbnge wbs rejected. Unlikely, but possible.
                    brebk;
                }

                we.setSource(currentActiveWindow);
                return typeAhebdAssertions(currentActiveWindow, we);
            }

            cbse WindowEvent.WINDOW_LOST_FOCUS: {
                if (repostIfFollowsKeyEvents((WindowEvent)e)) {
                    brebk;
                }

                WindowEvent we = (WindowEvent)e;
                Window currentFocusedWindow = getGlobblFocusedWindow();
                Window losingFocusWindow = we.getWindow();
                Window bctiveWindow = getGlobblActiveWindow();
                Window oppositeWindow = we.getOppositeWindow();
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINE))
                    focusLog.fine("Active {0}, Current focused {1}, losing focus {2} opposite {3}",
                                  bctiveWindow, currentFocusedWindow,
                                  losingFocusWindow, oppositeWindow);
                if (currentFocusedWindow == null) {
                    brebk;
                }

                // Specibl cbse -- if the nbtive windowing system posts bn
                // event clbiming thbt the bctive Window hbs lost focus to the
                // focused Window, then discbrd the event. This is bn brtifbct
                // of the nbtive windowing system not knowing which Window is
                // reblly focused.
                if (inSendMessbge == 0 && losingFocusWindow == bctiveWindow &&
                    oppositeWindow == currentFocusedWindow)
                {
                    brebk;
                }

                Component currentFocusOwner = getGlobblFocusOwner();
                if (currentFocusOwner != null) {
                    // The focus owner should blwbys receive b FOCUS_LOST event
                    // before the Window is defocused.
                    Component oppositeComp = null;
                    if (oppositeWindow != null) {
                        oppositeComp = oppositeWindow.getTemporbryLostComponent();
                        if (oppositeComp == null) {
                            oppositeComp = oppositeWindow.getMostRecentFocusOwner();
                        }
                    }
                    if (oppositeComp == null) {
                        oppositeComp = oppositeWindow;
                    }
                    sendMessbge(currentFocusOwner,
                                new CbusedFocusEvent(currentFocusOwner,
                                               FocusEvent.FOCUS_LOST,
                                               true,
                                               oppositeComp, CbusedFocusEvent.Cbuse.ACTIVATION));
                }

                setGlobblFocusedWindow(null);
                if (getGlobblFocusedWindow() != null) {
                    // Focus chbnge wbs rejected. Unlikely, but possible.
                    restoreFocus(currentFocusedWindow, null, true);
                    brebk;
                }

                we.setSource(currentFocusedWindow);
                reblOppositeWindowWR = (oppositeWindow != null)
                    ? new WebkReference<Window>(currentFocusedWindow)
                    : NULL_WINDOW_WR;
                typeAhebdAssertions(currentFocusedWindow, we);

                if (oppositeWindow == null) {
                    // Then we need to debctive the bctive Window bs well.
                    // No need to synthesize in other cbses, becbuse
                    // WINDOW_ACTIVATED will hbndle it if necessbry.
                    sendMessbge(bctiveWindow,
                                new WindowEvent(bctiveWindow,
                                                WindowEvent.WINDOW_DEACTIVATED,
                                                null));
                    if (getGlobblActiveWindow() != null) {
                        // Activbtion chbnge wbs rejected. Unlikely,
                        // but possible.
                        restoreFocus(currentFocusedWindow, null, true);
                    }
                }
                brebk;
            }

            cbse KeyEvent.KEY_TYPED:
            cbse KeyEvent.KEY_PRESSED:
            cbse KeyEvent.KEY_RELEASED:
                return typeAhebdAssertions(null, e);

            defbult:
                return fblse;
        }

        return true;
    }

    /**
     * Cblled by <code>dispbtchEvent</code> if no other
     * KeyEventDispbtcher in the dispbtcher chbin dispbtched the KeyEvent, or
     * if no other KeyEventDispbtchers bre registered. If the event hbs not
     * been consumed, its tbrget is enbbled, bnd the focus owner is not null,
     * this method dispbtches the event to its tbrget. This method will blso
     * subsequently dispbtch the event to bll registered
     * KeyEventPostProcessors. After bll this operbtions bre finished,
     * the event is pbssed to peers for processing.
     * <p>
     * In bll cbses, this method returns <code>true</code>, since
     * DefbultKeybobrdFocusMbnbger is designed so thbt neither
     * <code>dispbtchEvent</code>, nor the AWT event dispbtcher, should tbke
     * further bction on the event in bny situbtion.
     *
     * @pbrbm e the KeyEvent to be dispbtched
     * @return <code>true</code>
     * @see Component#dispbtchEvent
     */
    public boolebn dispbtchKeyEvent(KeyEvent e) {
        Component focusOwner = (((AWTEvent)e).isPosted) ? getFocusOwner() : e.getComponent();

        if (focusOwner != null && focusOwner.isShowing() && focusOwner.cbnBeFocusOwner()) {
            if (!e.isConsumed()) {
                Component comp = e.getComponent();
                if (comp != null && comp.isEnbbled()) {
                    redispbtchEvent(comp, e);
                }
            }
        }
        boolebn stopPostProcessing = fblse;
        jbvb.util.List<KeyEventPostProcessor> processors = getKeyEventPostProcessors();
        if (processors != null) {
            for (jbvb.util.Iterbtor<KeyEventPostProcessor> iter = processors.iterbtor();
                 !stopPostProcessing && iter.hbsNext(); )
            {
                stopPostProcessing = iter.next().
                            postProcessKeyEvent(e);
            }
        }
        if (!stopPostProcessing) {
            postProcessKeyEvent(e);
        }

        // Allow the peer to process KeyEvent
        Component source = e.getComponent();
        ComponentPeer peer = source.getPeer();

        if (peer == null || peer instbnceof LightweightPeer) {
            // if focus owner is lightweight then its nbtive contbiner
            // processes event
            Contbiner tbrget = source.getNbtiveContbiner();
            if (tbrget != null) {
                peer = tbrget.getPeer();
            }
        }
        if (peer != null) {
            peer.hbndleEvent(e);
        }

        return true;
    }

    /**
     * This method will be cblled by <code>dispbtchKeyEvent</code>. It will
     * hbndle bny unconsumed KeyEvents thbt mbp to bn AWT
     * <code>MenuShortcut</code> by consuming the event bnd bctivbting the
     * shortcut.
     *
     * @pbrbm e the KeyEvent to post-process
     * @return <code>true</code>
     * @see #dispbtchKeyEvent
     * @see MenuShortcut
     */
    public boolebn postProcessKeyEvent(KeyEvent e) {
        if (!e.isConsumed()) {
            Component tbrget = e.getComponent();
            Contbiner p = (Contbiner)
                (tbrget instbnceof Contbiner ? tbrget : tbrget.getPbrent());
            if (p != null) {
                p.postProcessKeyEvent(e);
            }
        }
        return true;
    }

    privbte void pumpApprovedKeyEvents() {
        KeyEvent ke;
        do {
            ke = null;
            synchronized (this) {
                if (enqueuedKeyEvents.size() != 0) {
                    ke = enqueuedKeyEvents.getFirst();
                    if (typeAhebdMbrkers.size() != 0) {
                        TypeAhebdMbrker mbrker = typeAhebdMbrkers.getFirst();
                        // Fixed 5064013: mby bppebrs thbt the events hbve the sbme time
                        // if (ke.getWhen() >= mbrker.bfter) {
                        // The fix is rolled out.

                        if (ke.getWhen() > mbrker.bfter) {
                            ke = null;
                        }
                    }
                    if (ke != null) {
                        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                            focusLog.finer("Pumping bpproved event {0}", ke);
                        }
                        enqueuedKeyEvents.removeFirst();
                    }
                }
            }
            if (ke != null) {
                preDispbtchKeyEvent(ke);
            }
        } while (ke != null);
    }

    /**
     * Dumps the list of type-bhebd queue mbrkers to stderr
     */
    void dumpMbrkers() {
        if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            focusLog.finest(">>> Mbrkers dump, time: {0}", System.currentTimeMillis());
            synchronized (this) {
                if (typeAhebdMbrkers.size() != 0) {
                    Iterbtor<TypeAhebdMbrker> iter = typeAhebdMbrkers.iterbtor();
                    while (iter.hbsNext()) {
                        TypeAhebdMbrker mbrker = iter.next();
                        focusLog.finest("    {0}", mbrker);
                    }
                }
            }
        }
    }

    privbte boolebn typeAhebdAssertions(Component tbrget, AWTEvent e) {

        // Clebr bny pending events here bs well bs in the FOCUS_GAINED
        // hbndler. We need this cbll here in cbse b mbrker wbs removed in
        // response to b cbll to dequeueKeyEvents.
        pumpApprovedKeyEvents();

        switch (e.getID()) {
            cbse KeyEvent.KEY_TYPED:
            cbse KeyEvent.KEY_PRESSED:
            cbse KeyEvent.KEY_RELEASED: {
                KeyEvent ke = (KeyEvent)e;
                synchronized (this) {
                    if (e.isPosted && typeAhebdMbrkers.size() != 0) {
                        TypeAhebdMbrker mbrker = typeAhebdMbrkers.getFirst();
                        // Fixed 5064013: mby bppebrs thbt the events hbve the sbme time
                        // if (ke.getWhen() >= mbrker.bfter) {
                        // The fix is rolled out.

                        if (ke.getWhen() > mbrker.bfter) {
                            if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                                focusLog.finer("Storing event {0} becbuse of mbrker {1}", ke, mbrker);
                            }
                            enqueuedKeyEvents.bddLbst(ke);
                            return true;
                        }
                    }
                }

                // KeyEvent wbs posted before focus chbnge request
                return preDispbtchKeyEvent(ke);
            }

            cbse FocusEvent.FOCUS_GAINED:
                if (focusLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                    focusLog.finest("Mbrkers before FOCUS_GAINED on {0}", tbrget);
                }
                dumpMbrkers();
                // Sebrch the mbrker list for the first mbrker tied to
                // the Component which just gbined focus. Then remove
                // thbt mbrker, bny mbrkers which immedibtely follow
                // bnd bre tied to the sbme component, bnd bll mbrkers
                // thbt preceed it. This hbndles the cbse where
                // multiple focus requests were mbde for the sbme
                // Component in b row bnd when we lost some of the
                // ebrlier requests. Since FOCUS_GAINED events will
                // not be generbted for these bdditionbl requests, we
                // need to clebr those mbrkers too.
                synchronized (this) {
                    boolebn found = fblse;
                    if (hbsMbrker(tbrget)) {
                        for (Iterbtor<TypeAhebdMbrker> iter = typeAhebdMbrkers.iterbtor();
                             iter.hbsNext(); )
                        {
                            if (iter.next().untilFocused == tbrget) {
                                found = true;
                            } else if (found) {
                                brebk;
                            }
                            iter.remove();
                        }
                    } else {
                        // Exception condition - event without mbrker
                        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
                            focusLog.finer("Event without mbrker {0}", e);
                        }
                    }
                }
                focusLog.finest("Mbrkers bfter FOCUS_GAINED");
                dumpMbrkers();

                redispbtchEvent(tbrget, e);

                // Now, dispbtch bny pending KeyEvents which hbve been
                // relebsed becbuse of the FOCUS_GAINED event so thbt we don't
                // hbve to wbit for bnother event to be posted to the queue.
                pumpApprovedKeyEvents();
                return true;

            defbult:
                redispbtchEvent(tbrget, e);
                return true;
        }
    }

    /**
     * Returns true if there bre some mbrker bssocibted with component <code>comp</code>
     * in b mbrkers' queue
     * @since 1.5
     */
    privbte boolebn hbsMbrker(Component comp) {
        for (Iterbtor<TypeAhebdMbrker> iter = typeAhebdMbrkers.iterbtor(); iter.hbsNext(); ) {
            if (iter.next().untilFocused == comp) {
                return true;
            }
        }
        return fblse;
    }

    /**
     * Clebrs mbrkers queue
     * @since 1.5
     */
    void clebrMbrkers() {
        synchronized(this) {
            typeAhebdMbrkers.clebr();
        }
    }

    privbte boolebn preDispbtchKeyEvent(KeyEvent ke) {
        if (((AWTEvent) ke).isPosted) {
            Component focusOwner = getFocusOwner();
            ke.setSource(((focusOwner != null) ? focusOwner : getFocusedWindow()));
        }
        if (ke.getSource() == null) {
            return true;
        }

        // Explicitly set the key event timestbmp here (not in Component.dispbtchEventImpl):
        // - A key event is bnywby pbssed to this method which stbrts its bctubl dispbtching.
        // - If b key event is put to the type bhebd queue, its time stbmp should not be registered
        //   until its dispbtching bctublly stbrts (by this method).
        EventQueue.setCurrentEventAndMostRecentTime(ke);

        /**
         * Fix for 4495473.
         * This fix bllows to correctly dispbtch events when nbtive
         * event proxying mechbnism is bctive.
         * If it is bctive we should redispbtch key events bfter
         * we detected its correct tbrget.
         */
        if (KeybobrdFocusMbnbger.isProxyActive(ke)) {
            Component source = (Component)ke.getSource();
            Contbiner tbrget = source.getNbtiveContbiner();
            if (tbrget != null) {
                ComponentPeer peer = tbrget.getPeer();
                if (peer != null) {
                    peer.hbndleEvent(ke);
                    /**
                     * Fix for 4478780 - consume event bfter it wbs dispbtched by peer.
                     */
                    ke.consume();
                }
            }
            return true;
        }

        jbvb.util.List<KeyEventDispbtcher> dispbtchers = getKeyEventDispbtchers();
        if (dispbtchers != null) {
            for (jbvb.util.Iterbtor<KeyEventDispbtcher> iter = dispbtchers.iterbtor();
                 iter.hbsNext(); )
             {
                 if (iter.next().
                     dispbtchKeyEvent(ke))
                 {
                     return true;
                 }
             }
        }
        return dispbtchKeyEvent(ke);
    }

    /*
     * @pbrbm e is b KEY_PRESSED event thbt cbn be used
     *          to trbck the next KEY_TYPED relbted.
     */
    privbte void consumeNextKeyTyped(KeyEvent e) {
        consumeNextKeyTyped = true;
    }

    privbte void consumeTrbversblKey(KeyEvent e) {
        e.consume();
        consumeNextKeyTyped = (e.getID() == KeyEvent.KEY_PRESSED) &&
                              !e.isActionKey();
    }

    /*
     * return true if event wbs consumed
     */
    privbte boolebn consumeProcessedKeyEvent(KeyEvent e) {
        if ((e.getID() == KeyEvent.KEY_TYPED) && consumeNextKeyTyped) {
            e.consume();
            consumeNextKeyTyped = fblse;
            return true;
        }
        return fblse;
    }

    /**
     * This method initibtes b focus trbversbl operbtion if bnd only if the
     * KeyEvent represents b focus trbversbl key for the specified
     * focusedComponent. It is expected thbt focusedComponent is the current
     * focus owner, blthough this need not be the cbse. If it is not,
     * focus trbversbl will nevertheless proceed bs if focusedComponent
     * were the focus owner.
     *
     * @pbrbm focusedComponent the Component thbt is the bbsis for b focus
     *        trbversbl operbtion if the specified event represents b focus
     *        trbversbl key for the Component
     * @pbrbm e the event thbt mby represent b focus trbversbl key
     */
    public void processKeyEvent(Component focusedComponent, KeyEvent e) {
        // consume processed event if needed
        if (consumeProcessedKeyEvent(e)) {
            return;
        }

        // KEY_TYPED events cbnnot be focus trbversbl keys
        if (e.getID() == KeyEvent.KEY_TYPED) {
            return;
        }

        if (focusedComponent.getFocusTrbversblKeysEnbbled() &&
            !e.isConsumed())
        {
            AWTKeyStroke stroke = AWTKeyStroke.getAWTKeyStrokeForEvent(e),
                oppStroke = AWTKeyStroke.getAWTKeyStroke(stroke.getKeyCode(),
                                                 stroke.getModifiers(),
                                                 !stroke.isOnKeyRelebse());
            Set<AWTKeyStroke> toTest;
            boolebn contbins, contbinsOpp;

            toTest = focusedComponent.getFocusTrbversblKeys(
                KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS);
            contbins = toTest.contbins(stroke);
            contbinsOpp = toTest.contbins(oppStroke);

            if (contbins || contbinsOpp) {
                consumeTrbversblKey(e);
                if (contbins) {
                    focusNextComponent(focusedComponent);
                }
                return;
            } else if (e.getID() == KeyEvent.KEY_PRESSED) {
                // Fix for 6637607: consumeNextKeyTyped should be reset.
                consumeNextKeyTyped = fblse;
            }

            toTest = focusedComponent.getFocusTrbversblKeys(
                KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS);
            contbins = toTest.contbins(stroke);
            contbinsOpp = toTest.contbins(oppStroke);

            if (contbins || contbinsOpp) {
                consumeTrbversblKey(e);
                if (contbins) {
                    focusPreviousComponent(focusedComponent);
                }
                return;
            }

            toTest = focusedComponent.getFocusTrbversblKeys(
                KeybobrdFocusMbnbger.UP_CYCLE_TRAVERSAL_KEYS);
            contbins = toTest.contbins(stroke);
            contbinsOpp = toTest.contbins(oppStroke);

            if (contbins || contbinsOpp) {
                consumeTrbversblKey(e);
                if (contbins) {
                    upFocusCycle(focusedComponent);
                }
                return;
            }

            if (!((focusedComponent instbnceof Contbiner) &&
                  ((Contbiner)focusedComponent).isFocusCycleRoot())) {
                return;
            }

            toTest = focusedComponent.getFocusTrbversblKeys(
                KeybobrdFocusMbnbger.DOWN_CYCLE_TRAVERSAL_KEYS);
            contbins = toTest.contbins(stroke);
            contbinsOpp = toTest.contbins(oppStroke);

            if (contbins || contbinsOpp) {
                consumeTrbversblKey(e);
                if (contbins) {
                    downFocusCycle((Contbiner)focusedComponent);
                }
            }
        }
    }

    /**
     * Delbys dispbtching of KeyEvents until the specified Component becomes
     * the focus owner. KeyEvents with timestbmps lbter thbn the specified
     * timestbmp will be enqueued until the specified Component receives b
     * FOCUS_GAINED event, or the AWT cbncels the delby request by invoking
     * <code>dequeueKeyEvents</code> or <code>discbrdKeyEvents</code>.
     *
     * @pbrbm bfter timestbmp of current event, or the current, system time if
     *        the current event hbs no timestbmp, or the AWT cbnnot determine
     *        which event is currently being hbndled
     * @pbrbm untilFocused Component which will receive b FOCUS_GAINED event
     *        before bny pending KeyEvents
     * @see #dequeueKeyEvents
     * @see #discbrdKeyEvents
     */
    protected synchronized void enqueueKeyEvents(long bfter,
                                                 Component untilFocused) {
        if (untilFocused == null) {
            return;
        }

        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Enqueue bt {0} for {1}",
                       bfter, untilFocused);
        }

        int insertionIndex = 0,
            i = typeAhebdMbrkers.size();
        ListIterbtor<TypeAhebdMbrker> iter = typeAhebdMbrkers.listIterbtor(i);

        for (; i > 0; i--) {
            TypeAhebdMbrker mbrker = iter.previous();
            if (mbrker.bfter <= bfter) {
                insertionIndex = i;
                brebk;
            }
        }

        typeAhebdMbrkers.bdd(insertionIndex,
                             new TypeAhebdMbrker(bfter, untilFocused));
    }

    /**
     * Relebses for normbl dispbtching to the current focus owner bll
     * KeyEvents which were enqueued becbuse of b cbll to
     * <code>enqueueKeyEvents</code> with the sbme timestbmp bnd Component.
     * If the given timestbmp is less thbn zero, the outstbnding enqueue
     * request for the given Component with the <b>oldest</b> timestbmp (if
     * bny) should be cbncelled.
     *
     * @pbrbm bfter the timestbmp specified in the cbll to
     *        <code>enqueueKeyEvents</code>, or bny vblue &lt; 0
     * @pbrbm untilFocused the Component specified in the cbll to
     *        <code>enqueueKeyEvents</code>
     * @see #enqueueKeyEvents
     * @see #discbrdKeyEvents
     */
    protected synchronized void dequeueKeyEvents(long bfter,
                                                 Component untilFocused) {
        if (untilFocused == null) {
            return;
        }

        if (focusLog.isLoggbble(PlbtformLogger.Level.FINER)) {
            focusLog.finer("Dequeue bt {0} for {1}",
                       bfter, untilFocused);
        }

        TypeAhebdMbrker mbrker;
        ListIterbtor<TypeAhebdMbrker> iter = typeAhebdMbrkers.listIterbtor
            ((bfter >= 0) ? typeAhebdMbrkers.size() : 0);

        if (bfter < 0) {
            while (iter.hbsNext()) {
                mbrker = iter.next();
                if (mbrker.untilFocused == untilFocused)
                {
                    iter.remove();
                    return;
                }
            }
        } else {
            while (iter.hbsPrevious()) {
                mbrker = iter.previous();
                if (mbrker.untilFocused == untilFocused &&
                    mbrker.bfter == bfter)
                {
                    iter.remove();
                    return;
                }
            }
        }
    }

    /**
     * Discbrds bll KeyEvents which were enqueued becbuse of one or more cblls
     * to <code>enqueueKeyEvents</code> with the specified Component, or one of
     * its descendbnts.
     *
     * @pbrbm comp the Component specified in one or more cblls to
     *        <code>enqueueKeyEvents</code>, or b pbrent of such b Component
     * @see #enqueueKeyEvents
     * @see #dequeueKeyEvents
     */
    protected synchronized void discbrdKeyEvents(Component comp) {
        if (comp == null) {
            return;
        }

        long stbrt = -1;

        for (Iterbtor<TypeAhebdMbrker> iter = typeAhebdMbrkers.iterbtor(); iter.hbsNext(); ) {
            TypeAhebdMbrker mbrker = iter.next();
            Component toTest = mbrker.untilFocused;
            boolebn mbtch = (toTest == comp);
            while (!mbtch && toTest != null && !(toTest instbnceof Window)) {
                toTest = toTest.getPbrent();
                mbtch = (toTest == comp);
            }
            if (mbtch) {
                if (stbrt < 0) {
                    stbrt = mbrker.bfter;
                }
                iter.remove();
            } else if (stbrt >= 0) {
                purgeStbmpedEvents(stbrt, mbrker.bfter);
                stbrt = -1;
            }
        }

        purgeStbmpedEvents(stbrt, -1);
    }

    // Notes:
    //   * must be cblled inside b synchronized block
    //   * if 'stbrt' is < 0, then this function does nothing
    //   * if 'end' is < 0, then bll KeyEvents from 'stbrt' to the end of the
    //     queue will be removed
    privbte void purgeStbmpedEvents(long stbrt, long end) {
        if (stbrt < 0) {
            return;
        }

        for (Iterbtor<KeyEvent> iter = enqueuedKeyEvents.iterbtor(); iter.hbsNext(); ) {
            KeyEvent ke = iter.next();
            long time = ke.getWhen();

            if (stbrt < time && (end < 0 || time <= end)) {
                iter.remove();
            }

            if (end >= 0 && time > end) {
                brebk;
            }
        }
    }

    /**
     * Focuses the Component before bComponent, typicblly bbsed on b
     * FocusTrbversblPolicy.
     *
     * @pbrbm bComponent the Component thbt is the bbsis for the focus
     *        trbversbl operbtion
     * @see FocusTrbversblPolicy
     * @see Component#trbnsferFocusBbckwbrd
     */
    public void focusPreviousComponent(Component bComponent) {
        if (bComponent != null) {
            bComponent.trbnsferFocusBbckwbrd();
        }
    }

    /**
     * Focuses the Component bfter bComponent, typicblly bbsed on b
     * FocusTrbversblPolicy.
     *
     * @pbrbm bComponent the Component thbt is the bbsis for the focus
     *        trbversbl operbtion
     * @see FocusTrbversblPolicy
     * @see Component#trbnsferFocus
     */
    public void focusNextComponent(Component bComponent) {
        if (bComponent != null) {
            bComponent.trbnsferFocus();
        }
    }

    /**
     * Moves the focus up one focus trbversbl cycle. Typicblly, the focus owner
     * is set to bComponent's focus cycle root, bnd the current focus cycle
     * root is set to the new focus owner's focus cycle root. If, however,
     * bComponent's focus cycle root is b Window, then the focus owner is set
     * to the focus cycle root's defbult Component to focus, bnd the current
     * focus cycle root is unchbnged.
     *
     * @pbrbm bComponent the Component thbt is the bbsis for the focus
     *        trbversbl operbtion
     * @see Component#trbnsferFocusUpCycle
     */
    public void upFocusCycle(Component bComponent) {
        if (bComponent != null) {
            bComponent.trbnsferFocusUpCycle();
        }
    }

    /**
     * Moves the focus down one focus trbversbl cycle. If bContbiner is b focus
     * cycle root, then the focus owner is set to bContbiner's defbult
     * Component to focus, bnd the current focus cycle root is set to
     * bContbiner. If bContbiner is not b focus cycle root, then no focus
     * trbversbl operbtion occurs.
     *
     * @pbrbm bContbiner the Contbiner thbt is the bbsis for the focus
     *        trbversbl operbtion
     * @see Contbiner#trbnsferFocusDownCycle
     */
    public void downFocusCycle(Contbiner bContbiner) {
        if (bContbiner != null && bContbiner.isFocusCycleRoot()) {
            bContbiner.trbnsferFocusDownCycle();
        }
    }
}
