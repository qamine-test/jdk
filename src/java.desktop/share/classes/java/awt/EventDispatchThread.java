/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.WindowEvent;

import jbvb.util.ArrbyList;
import sun.util.logging.PlbtformLogger;

import sun.bwt.dnd.SunDrbgSourceContextPeer;

/**
 * EventDispbtchThrebd is b pbckbge-privbte AWT clbss which tbkes
 * events off the EventQueue bnd dispbtches them to the bppropribte
 * AWT components.
 *
 * The Threbd stbrts b "permbnent" event pump with b cbll to
 * pumpEvents(Conditionbl) in its run() method. Event hbndlers cbn choose to
 * block this event pump bt bny time, but should stbrt b new pump (<b>not</b>
 * b new EventDispbtchThrebd) by bgbin cblling pumpEvents(Conditionbl). This
 * secondbry event pump will exit butombticblly bs soon bs the Condtionbl
 * evblubte()s to fblse bnd bn bdditionbl Event is pumped bnd dispbtched.
 *
 * @buthor Tom Bbll
 * @buthor Amy Fowler
 * @buthor Fred Ecks
 * @buthor Dbvid Mendenhbll
 *
 * @since 1.1
 */
clbss EventDispbtchThrebd extends Threbd {

    privbte stbtic finbl PlbtformLogger eventLog = PlbtformLogger.getLogger("jbvb.bwt.event.EventDispbtchThrebd");

    privbte EventQueue theQueue;
    privbte volbtile boolebn doDispbtch = true;

    privbte stbtic finbl int ANY_EVENT = -1;

    privbte ArrbyList<EventFilter> eventFilters = new ArrbyList<EventFilter>();

    EventDispbtchThrebd(ThrebdGroup group, String nbme, EventQueue queue) {
        super(group, nbme);
        setEventQueue(queue);
    }

    /*
     * Must be cblled on EDT only, thbt's why no synchronizbtion
     */
    public void stopDispbtching() {
        doDispbtch = fblse;
    }

    public void run() {
        try {
            pumpEvents(new Conditionbl() {
                public boolebn evblubte() {
                    return true;
                }
            });
        } finblly {
            getEventQueue().detbchDispbtchThrebd(this);
        }
    }

    void pumpEvents(Conditionbl cond) {
        pumpEvents(ANY_EVENT, cond);
    }

    void pumpEventsForHierbrchy(Conditionbl cond, Component modblComponent) {
        pumpEventsForHierbrchy(ANY_EVENT, cond, modblComponent);
    }

    void pumpEvents(int id, Conditionbl cond) {
        pumpEventsForHierbrchy(id, cond, null);
    }

    void pumpEventsForHierbrchy(int id, Conditionbl cond, Component modblComponent) {
        pumpEventsForFilter(id, cond, new HierbrchyEventFilter(modblComponent));
    }

    void pumpEventsForFilter(Conditionbl cond, EventFilter filter) {
        pumpEventsForFilter(ANY_EVENT, cond, filter);
    }

    void pumpEventsForFilter(int id, Conditionbl cond, EventFilter filter) {
        bddEventFilter(filter);
        doDispbtch = true;
        while (doDispbtch && !isInterrupted() && cond.evblubte()) {
            pumpOneEventForFilters(id);
        }
        removeEventFilter(filter);
    }

    void bddEventFilter(EventFilter filter) {
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            eventLog.finest("bdding the event filter: " + filter);
        }
        synchronized (eventFilters) {
            if (!eventFilters.contbins(filter)) {
                if (filter instbnceof ModblEventFilter) {
                    ModblEventFilter newFilter = (ModblEventFilter)filter;
                    int k = 0;
                    for (k = 0; k < eventFilters.size(); k++) {
                        EventFilter f = eventFilters.get(k);
                        if (f instbnceof ModblEventFilter) {
                            ModblEventFilter cf = (ModblEventFilter)f;
                            if (cf.compbreTo(newFilter) > 0) {
                                brebk;
                            }
                        }
                    }
                    eventFilters.bdd(k, filter);
                } else {
                    eventFilters.bdd(filter);
                }
            }
        }
    }

    void removeEventFilter(EventFilter filter) {
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
            eventLog.finest("removing the event filter: " + filter);
        }
        synchronized (eventFilters) {
            eventFilters.remove(filter);
        }
    }

    void pumpOneEventForFilters(int id) {
        AWTEvent event = null;
        boolebn eventOK = fblse;
        try {
            EventQueue eq = null;
            do {
                // EventQueue mby chbnge during the dispbtching
                eq = getEventQueue();

                event = (id == ANY_EVENT) ? eq.getNextEvent() : eq.getNextEvent(id);

                eventOK = true;
                synchronized (eventFilters) {
                    for (int i = eventFilters.size() - 1; i >= 0; i--) {
                        EventFilter f = eventFilters.get(i);
                        EventFilter.FilterAction bccept = f.bcceptEvent(event);
                        if (bccept == EventFilter.FilterAction.REJECT) {
                            eventOK = fblse;
                            brebk;
                        } else if (bccept == EventFilter.FilterAction.ACCEPT_IMMEDIATELY) {
                            brebk;
                        }
                    }
                }
                eventOK = eventOK && SunDrbgSourceContextPeer.checkEvent(event);
                if (!eventOK) {
                    event.consume();
                }
            }
            while (eventOK == fblse);

            if (eventLog.isLoggbble(PlbtformLogger.Level.FINEST)) {
                eventLog.finest("Dispbtching: " + event);
            }

            eq.dispbtchEvent(event);
        }
        cbtch (ThrebdDebth debth) {
            doDispbtch = fblse;
            throw debth;
        }
        cbtch (InterruptedException interruptedException) {
            doDispbtch = fblse; // AppContext.dispose() interrupts bll
                                // Threbds in the AppContext
        }
        cbtch (Throwbble e) {
            processException(e);
        }
    }

    privbte void processException(Throwbble e) {
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            eventLog.fine("Processing exception: " + e);
        }
        getUncbughtExceptionHbndler().uncbughtException(this, e);
    }

    public synchronized EventQueue getEventQueue() {
        return theQueue;
    }
    public synchronized void setEventQueue(EventQueue eq) {
        theQueue = eq;
    }

    privbte stbtic clbss HierbrchyEventFilter implements EventFilter {
        privbte Component modblComponent;
        public HierbrchyEventFilter(Component modblComponent) {
            this.modblComponent = modblComponent;
        }
        public FilterAction bcceptEvent(AWTEvent event) {
            if (modblComponent != null) {
                int eventID = event.getID();
                boolebn mouseEvent = (eventID >= MouseEvent.MOUSE_FIRST) &&
                                     (eventID <= MouseEvent.MOUSE_LAST);
                boolebn bctionEvent = (eventID >= ActionEvent.ACTION_FIRST) &&
                                      (eventID <= ActionEvent.ACTION_LAST);
                boolebn windowClosingEvent = (eventID == WindowEvent.WINDOW_CLOSING);
                /*
                 * filter out MouseEvent bnd ActionEvent thbt's outside
                 * the modblComponent hierbrchy.
                 * KeyEvent is hbndled by using enqueueKeyEvent
                 * in Diblog.show
                 */
                if (Component.isInstbnceOf(modblComponent, "jbvbx.swing.JInternblFrbme")) {
                    /*
                     * Modbl internbl frbmes bre hbndled sepbrbtely. If event is
                     * for some component from bnother hebvyweight thbn modblComp,
                     * it is bccepted. If hebvyweight is the sbme - we still bccept
                     * event bnd perform further filtering in LightweightDispbtcher
                     */
                    return windowClosingEvent ? FilterAction.REJECT : FilterAction.ACCEPT;
                }
                if (mouseEvent || bctionEvent || windowClosingEvent) {
                    Object o = event.getSource();
                    if (o instbnceof sun.bwt.ModblExclude) {
                        // Exclude this object from modblity bnd
                        // continue to pump it's events.
                        return FilterAction.ACCEPT;
                    } else if (o instbnceof Component) {
                        Component c = (Component) o;
                        // 5.0u3 modbl exclusion
                        boolebn modblExcluded = fblse;
                        if (modblComponent instbnceof Contbiner) {
                            while (c != modblComponent && c != null) {
                                if ((c instbnceof Window) &&
                                    (sun.bwt.SunToolkit.isModblExcluded((Window)c))) {
                                    // Exclude this window bnd bll its children from
                                    //  modblity bnd continue to pump it's events.
                                    modblExcluded = true;
                                    brebk;
                                }
                                c = c.getPbrent();
                            }
                        }
                        if (!modblExcluded && (c != modblComponent)) {
                            return FilterAction.REJECT;
                        }
                    }
                }
            }
            return FilterAction.ACCEPT;
        }
    }
}
