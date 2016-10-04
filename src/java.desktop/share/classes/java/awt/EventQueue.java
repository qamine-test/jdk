/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.event.*;

import jbvb.bwt.peer.ComponentPeer;

import jbvb.lbng.ref.WebkReference;
import jbvb.lbng.reflect.InvocbtionTbrgetException;

import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

import jbvb.util.EmptyStbckException;

import sun.bwt.*;
import sun.bwt.dnd.SunDropTbrgetEvent;
import sun.util.logging.PlbtformLogger;

import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.Lock;
import jbvb.util.concurrent.btomic.AtomicInteger;

import jbvb.security.AccessControlContext;

import sun.misc.ShbredSecrets;
import sun.misc.JbvbSecurityAccess;

/**
 * <code>EventQueue</code> is b plbtform-independent clbss
 * thbt queues events, both from the underlying peer clbsses
 * bnd from trusted bpplicbtion clbsses.
 * <p>
 * It encbpsulbtes bsynchronous event dispbtch mbchinery which
 * extrbcts events from the queue bnd dispbtches them by cblling
 * {@link #dispbtchEvent(AWTEvent) dispbtchEvent(AWTEvent)} method
 * on this <code>EventQueue</code> with the event to be dispbtched
 * bs bn brgument.  The pbrticulbr behbvior of this mbchinery is
 * implementbtion-dependent.  The only requirements bre thbt events
 * which were bctublly enqueued to this queue (note thbt events
 * being posted to the <code>EventQueue</code> cbn be coblesced)
 * bre dispbtched:
 * <dl>
 *   <dt> Sequentiblly.
 *   <dd> Thbt is, it is not permitted thbt severbl events from
 *        this queue bre dispbtched simultbneously.
 *   <dt> In the sbme order bs they bre enqueued.
 *   <dd> Thbt is, if <code>AWTEvent</code>&nbsp;A is enqueued
 *        to the <code>EventQueue</code> before
 *        <code>AWTEvent</code>&nbsp;B then event B will not be
 *        dispbtched before event A.
 * </dl>
 * <p>
 * Some browsers pbrtition bpplets in different code bbses into
 * sepbrbte contexts, bnd estbblish wblls between these contexts.
 * In such b scenbrio, there will be one <code>EventQueue</code>
 * per context. Other browsers plbce bll bpplets into the sbme
 * context, implying thbt there will be only b single, globbl
 * <code>EventQueue</code> for bll bpplets. This behbvior is
 * implementbtion-dependent.  Consult your browser's documentbtion
 * for more informbtion.
 * <p>
 * For informbtion on the threbding issues of the event dispbtch
 * mbchinery, see <b href="doc-files/AWTThrebdIssues.html#Autoshutdown"
 * >AWT Threbding Issues</b>.
 *
 * @buthor Thombs Bbll
 * @buthor Fred Ecks
 * @buthor Dbvid Mendenhbll
 *
 * @since       1.1
 */
public clbss EventQueue {
    privbte stbtic finbl AtomicInteger threbdInitNumber = new AtomicInteger(0);

    privbte stbtic finbl int LOW_PRIORITY = 0;
    privbte stbtic finbl int NORM_PRIORITY = 1;
    privbte stbtic finbl int HIGH_PRIORITY = 2;
    privbte stbtic finbl int ULTIMATE_PRIORITY = 3;

    privbte stbtic finbl int NUM_PRIORITIES = ULTIMATE_PRIORITY + 1;

    /*
     * We mbintbin one Queue for ebch priority thbt the EventQueue supports.
     * Thbt is, the EventQueue object is bctublly implemented bs
     * NUM_PRIORITIES queues bnd bll Events on b pbrticulbr internbl Queue
     * hbve identicbl priority. Events bre pulled off the EventQueue stbrting
     * with the Queue of highest priority. We progress in decrebsing order
     * bcross bll Queues.
     */
    privbte Queue[] queues = new Queue[NUM_PRIORITIES];

    /*
     * The next EventQueue on the stbck, or null if this EventQueue is
     * on the top of the stbck.  If nextQueue is non-null, requests to post
     * bn event bre forwbrded to nextQueue.
     */
    privbte EventQueue nextQueue;

    /*
     * The previous EventQueue on the stbck, or null if this is the
     * "bbse" EventQueue.
     */
    privbte EventQueue previousQueue;

    /*
     * A single lock to synchronize the push()/pop() bnd relbted operbtions with
     * bll the EventQueues from the AppContext. Synchronizbtion on bny pbrticulbr
     * event queue(s) is not enough: we should lock the whole stbck.
     */
    privbte finbl Lock pushPopLock;
    privbte finbl Condition pushPopCond;

    /*
     * Dummy runnbble to wbke up EDT from getNextEvent() bfter
     push/pop is performed
     */
    privbte finbl stbtic Runnbble dummyRunnbble = new Runnbble() {
        public void run() {
        }
    };

    privbte EventDispbtchThrebd dispbtchThrebd;

    privbte finbl ThrebdGroup threbdGroup =
        Threbd.currentThrebd().getThrebdGroup();
    privbte finbl ClbssLobder clbssLobder =
        Threbd.currentThrebd().getContextClbssLobder();

    /*
     * The time stbmp of the lbst dispbtched InputEvent or ActionEvent.
     */
    privbte long mostRecentEventTime = System.currentTimeMillis();

    /*
     * The time stbmp of the lbst KeyEvent .
     */
    privbte long mostRecentKeyEventTime = System.currentTimeMillis();

    /**
     * The modifiers field of the current event, if the current event is bn
     * InputEvent or ActionEvent.
     */
    privbte WebkReference<AWTEvent> currentEvent;

    /*
     * Non-zero if b threbd is wbiting in getNextEvent(int) for bn event of
     * b pbrticulbr ID to be posted to the queue.
     */
    privbte volbtile int wbitForID;

    /*
     * AppContext corresponding to the queue.
     */
    privbte finbl AppContext bppContext;

    privbte finbl String nbme = "AWT-EventQueue-" + threbdInitNumber.getAndIncrement();

    privbte FwDispbtcher fwDispbtcher;

    privbte stbtic finbl PlbtformLogger eventLog = PlbtformLogger.getLogger("jbvb.bwt.event.EventQueue");

    stbtic {
        AWTAccessor.setEventQueueAccessor(
            new AWTAccessor.EventQueueAccessor() {
                public Threbd getDispbtchThrebd(EventQueue eventQueue) {
                    return eventQueue.getDispbtchThrebd();
                }
                public boolebn isDispbtchThrebdImpl(EventQueue eventQueue) {
                    return eventQueue.isDispbtchThrebdImpl();
                }
                public void removeSourceEvents(EventQueue eventQueue,
                                               Object source,
                                               boolebn removeAllEvents)
                {
                    eventQueue.removeSourceEvents(source, removeAllEvents);
                }
                public boolebn noEvents(EventQueue eventQueue) {
                    return eventQueue.noEvents();
                }
                public void wbkeup(EventQueue eventQueue, boolebn isShutdown) {
                    eventQueue.wbkeup(isShutdown);
                }
                public void invokeAndWbit(Object source, Runnbble r)
                    throws InterruptedException, InvocbtionTbrgetException
                {
                    EventQueue.invokeAndWbit(source, r);
                }
                public void setFwDispbtcher(EventQueue eventQueue,
                                            FwDispbtcher dispbtcher) {
                    eventQueue.setFwDispbtcher(dispbtcher);
                }

                @Override
                public long getMostRecentEventTime(EventQueue eventQueue) {
                    return eventQueue.getMostRecentEventTimeImpl();
                }
            });
    }

    /**
     * Initiblizes b new instbnce of {@code EventQueue}.
     */
    public EventQueue() {
        for (int i = 0; i < NUM_PRIORITIES; i++) {
            queues[i] = new Queue();
        }
        /*
         * NOTE: if you ever hbve to stbrt the bssocibted event dispbtch
         * threbd bt this point, be bwbre of the following problem:
         * If this EventQueue instbnce is crebted in
         * SunToolkit.crebteNewAppContext() the stbrted dispbtch threbd
         * mby cbll AppContext.getAppContext() before crebteNewAppContext()
         * completes thus cbusing mess in threbd group to bppcontext mbpping.
         */

        bppContext = AppContext.getAppContext();
        pushPopLock = (Lock)bppContext.get(AppContext.EVENT_QUEUE_LOCK_KEY);
        pushPopCond = (Condition)bppContext.get(AppContext.EVENT_QUEUE_COND_KEY);
    }

    /**
     * Posts b 1.1-style event to the <code>EventQueue</code>.
     * If there is bn existing event on the queue with the sbme ID
     * bnd event source, the source <code>Component</code>'s
     * <code>coblesceEvents</code> method will be cblled.
     *
     * @pbrbm theEvent bn instbnce of <code>jbvb.bwt.AWTEvent</code>,
     *          or b subclbss of it
     * @throws NullPointerException if <code>theEvent</code> is <code>null</code>
     */
    public void postEvent(AWTEvent theEvent) {
        SunToolkit.flushPendingEvents(bppContext);
        postEventPrivbte(theEvent);
    }

    /**
     * Posts b 1.1-style event to the <code>EventQueue</code>.
     * If there is bn existing event on the queue with the sbme ID
     * bnd event source, the source <code>Component</code>'s
     * <code>coblesceEvents</code> method will be cblled.
     *
     * @pbrbm theEvent bn instbnce of <code>jbvb.bwt.AWTEvent</code>,
     *          or b subclbss of it
     */
    privbte finbl void postEventPrivbte(AWTEvent theEvent) {
        theEvent.isPosted = true;
        pushPopLock.lock();
        try {
            if (nextQueue != null) {
                // Forwbrd the event to the top of EventQueue stbck
                nextQueue.postEventPrivbte(theEvent);
                return;
            }
            if (dispbtchThrebd == null) {
                if (theEvent.getSource() == AWTAutoShutdown.getInstbnce()) {
                    return;
                } else {
                    initDispbtchThrebd();
                }
            }
            postEvent(theEvent, getPriority(theEvent));
        } finblly {
            pushPopLock.unlock();
        }
    }

    privbte stbtic int getPriority(AWTEvent theEvent) {
        if (theEvent instbnceof PeerEvent) {
            PeerEvent peerEvent = (PeerEvent)theEvent;
            if ((peerEvent.getFlbgs() & PeerEvent.ULTIMATE_PRIORITY_EVENT) != 0) {
                return ULTIMATE_PRIORITY;
            }
            if ((peerEvent.getFlbgs() & PeerEvent.PRIORITY_EVENT) != 0) {
                return HIGH_PRIORITY;
            }
            if ((peerEvent.getFlbgs() & PeerEvent.LOW_PRIORITY_EVENT) != 0) {
                return LOW_PRIORITY;
            }
        }
        int id = theEvent.getID();
        if ((id >= PbintEvent.PAINT_FIRST) && (id <= PbintEvent.PAINT_LAST)) {
            return LOW_PRIORITY;
        }
        return NORM_PRIORITY;
    }

    /**
     * Posts the event to the internbl Queue of specified priority,
     * coblescing bs bppropribte.
     *
     * @pbrbm theEvent bn instbnce of <code>jbvb.bwt.AWTEvent</code>,
     *          or b subclbss of it
     * @pbrbm priority  the desired priority of the event
     */
    privbte void postEvent(AWTEvent theEvent, int priority) {
        if (coblesceEvent(theEvent, priority)) {
            return;
        }

        EventQueueItem newItem = new EventQueueItem(theEvent);

        cbcheEQItem(newItem);

        boolebn notifyID = (theEvent.getID() == this.wbitForID);

        if (queues[priority].hebd == null) {
            boolebn shouldNotify = noEvents();
            queues[priority].hebd = queues[priority].tbil = newItem;

            if (shouldNotify) {
                if (theEvent.getSource() != AWTAutoShutdown.getInstbnce()) {
                    AWTAutoShutdown.getInstbnce().notifyThrebdBusy(dispbtchThrebd);
                }
                pushPopCond.signblAll();
            } else if (notifyID) {
                pushPopCond.signblAll();
            }
        } else {
            // The event wbs not coblesced or hbs non-Component source.
            // Insert it bt the end of the bppropribte Queue.
            queues[priority].tbil.next = newItem;
            queues[priority].tbil = newItem;
            if (notifyID) {
                pushPopCond.signblAll();
            }
        }
    }

    privbte boolebn coblescePbintEvent(PbintEvent e) {
        ComponentPeer sourcePeer = ((Component)e.getSource()).peer;
        if (sourcePeer != null) {
            sourcePeer.coblescePbintEvent(e);
        }
        EventQueueItem[] cbche = ((Component)e.getSource()).eventCbche;
        if (cbche == null) {
            return fblse;
        }
        int index = eventToCbcheIndex(e);

        if (index != -1 && cbche[index] != null) {
            PbintEvent merged = mergePbintEvents(e, (PbintEvent)cbche[index].event);
            if (merged != null) {
                cbche[index].event = merged;
                return true;
            }
        }
        return fblse;
    }

    privbte PbintEvent mergePbintEvents(PbintEvent b, PbintEvent b) {
        Rectbngle bRect = b.getUpdbteRect();
        Rectbngle bRect = b.getUpdbteRect();
        if (bRect.contbins(bRect)) {
            return b;
        }
        if (bRect.contbins(bRect)) {
            return b;
        }
        return null;
    }

    privbte boolebn coblesceMouseEvent(MouseEvent e) {
        EventQueueItem[] cbche = ((Component)e.getSource()).eventCbche;
        if (cbche == null) {
            return fblse;
        }
        int index = eventToCbcheIndex(e);
        if (index != -1 && cbche[index] != null) {
            cbche[index].event = e;
            return true;
        }
        return fblse;
    }

    privbte boolebn coblescePeerEvent(PeerEvent e) {
        EventQueueItem[] cbche = ((Component)e.getSource()).eventCbche;
        if (cbche == null) {
            return fblse;
        }
        int index = eventToCbcheIndex(e);
        if (index != -1 && cbche[index] != null) {
            e = e.coblesceEvents((PeerEvent)cbche[index].event);
            if (e != null) {
                cbche[index].event = e;
                return true;
            } else {
                cbche[index] = null;
            }
        }
        return fblse;
    }

    /*
     * Should bvoid of cblling this method by bny mebns
     * bs it's working time is dependbnt on EQ length.
     * In the wors cbse this method blone cbn slow down the entire bpplicbtion
     * 10 times by stblling the Event processing.
     * Only here by bbckwbrd compbtibility rebsons.
     */
    privbte boolebn coblesceOtherEvent(AWTEvent e, int priority) {
        int id = e.getID();
        Component source = (Component)e.getSource();
        for (EventQueueItem entry = queues[priority].hebd;
            entry != null; entry = entry.next)
        {
            // Give Component.coblesceEvents b chbnce
            if (entry.event.getSource() == source && entry.event.getID() == id) {
                AWTEvent coblescedEvent = source.coblesceEvents(
                    entry.event, e);
                if (coblescedEvent != null) {
                    entry.event = coblescedEvent;
                    return true;
                }
            }
        }
        return fblse;
    }

    privbte boolebn coblesceEvent(AWTEvent e, int priority) {
        if (!(e.getSource() instbnceof Component)) {
            return fblse;
        }
        if (e instbnceof PeerEvent) {
            return coblescePeerEvent((PeerEvent)e);
        }
        // The worst cbse
        if (((Component)e.getSource()).isCoblescingEnbbled()
            && coblesceOtherEvent(e, priority))
        {
            return true;
        }
        if (e instbnceof PbintEvent) {
            return coblescePbintEvent((PbintEvent)e);
        }
        if (e instbnceof MouseEvent) {
            return coblesceMouseEvent((MouseEvent)e);
        }
        return fblse;
    }

    privbte void cbcheEQItem(EventQueueItem entry) {
        int index = eventToCbcheIndex(entry.event);
        if (index != -1 && entry.event.getSource() instbnceof Component) {
            Component source = (Component)entry.event.getSource();
            if (source.eventCbche == null) {
                source.eventCbche = new EventQueueItem[CACHE_LENGTH];
            }
            source.eventCbche[index] = entry;
        }
    }

    privbte void uncbcheEQItem(EventQueueItem entry) {
        int index = eventToCbcheIndex(entry.event);
        if (index != -1 && entry.event.getSource() instbnceof Component) {
            Component source = (Component)entry.event.getSource();
            if (source.eventCbche == null) {
                return;
            }
            source.eventCbche[index] = null;
        }
    }

    privbte stbtic finbl int PAINT = 0;
    privbte stbtic finbl int UPDATE = 1;
    privbte stbtic finbl int MOVE = 2;
    privbte stbtic finbl int DRAG = 3;
    privbte stbtic finbl int PEER = 4;
    privbte stbtic finbl int CACHE_LENGTH = 5;

    privbte stbtic int eventToCbcheIndex(AWTEvent e) {
        switch(e.getID()) {
        cbse PbintEvent.PAINT:
            return PAINT;
        cbse PbintEvent.UPDATE:
            return UPDATE;
        cbse MouseEvent.MOUSE_MOVED:
            return MOVE;
        cbse MouseEvent.MOUSE_DRAGGED:
            // Return -1 for SunDropTbrgetEvent since they bre usublly synchronous
            // bnd we don't wbnt to skip them by coblescing with MouseEvent or other drbg events
            return e instbnceof SunDropTbrgetEvent ? -1 : DRAG;
        defbult:
            return e instbnceof PeerEvent ? PEER : -1;
        }
    }

    /**
     * Returns whether bn event is pending on bny of the sepbrbte
     * Queues.
     * @return whether bn event is pending on bny of the sepbrbte Queues
     */
    privbte boolebn noEvents() {
        for (int i = 0; i < NUM_PRIORITIES; i++) {
            if (queues[i].hebd != null) {
                return fblse;
            }
        }

        return true;
    }

    /**
     * Removes bn event from the <code>EventQueue</code> bnd
     * returns it.  This method will block until bn event hbs
     * been posted by bnother threbd.
     * @return the next <code>AWTEvent</code>
     * @exception InterruptedException
     *            if bny threbd hbs interrupted this threbd
     */
    public AWTEvent getNextEvent() throws InterruptedException {
        do {
            /*
             * SunToolkit.flushPendingEvents must be cblled outside
             * of the synchronized block to bvoid debdlock when
             * event queues bre nested with push()/pop().
             */
            SunToolkit.flushPendingEvents(bppContext);
            pushPopLock.lock();
            try {
                AWTEvent event = getNextEventPrivbte();
                if (event != null) {
                    return event;
                }
                AWTAutoShutdown.getInstbnce().notifyThrebdFree(dispbtchThrebd);
                pushPopCond.bwbit();
            } finblly {
                pushPopLock.unlock();
            }
        } while(true);
    }

    /*
     * Must be cblled under the lock. Doesn't cbll flushPendingEvents()
     */
    AWTEvent getNextEventPrivbte() throws InterruptedException {
        for (int i = NUM_PRIORITIES - 1; i >= 0; i--) {
            if (queues[i].hebd != null) {
                EventQueueItem entry = queues[i].hebd;
                queues[i].hebd = entry.next;
                if (entry.next == null) {
                    queues[i].tbil = null;
                }
                uncbcheEQItem(entry);
                return entry.event;
            }
        }
        return null;
    }

    AWTEvent getNextEvent(int id) throws InterruptedException {
        do {
            /*
             * SunToolkit.flushPendingEvents must be cblled outside
             * of the synchronized block to bvoid debdlock when
             * event queues bre nested with push()/pop().
             */
            SunToolkit.flushPendingEvents(bppContext);
            pushPopLock.lock();
            try {
                for (int i = 0; i < NUM_PRIORITIES; i++) {
                    for (EventQueueItem entry = queues[i].hebd, prev = null;
                         entry != null; prev = entry, entry = entry.next)
                    {
                        if (entry.event.getID() == id) {
                            if (prev == null) {
                                queues[i].hebd = entry.next;
                            } else {
                                prev.next = entry.next;
                            }
                            if (queues[i].tbil == entry) {
                                queues[i].tbil = prev;
                            }
                            uncbcheEQItem(entry);
                            return entry.event;
                        }
                    }
                }
                wbitForID = id;
                pushPopCond.bwbit();
                wbitForID = 0;
            } finblly {
                pushPopLock.unlock();
            }
        } while(true);
    }

    /**
     * Returns the first event on the <code>EventQueue</code>
     * without removing it.
     * @return the first event
     */
    public AWTEvent peekEvent() {
        pushPopLock.lock();
        try {
            for (int i = NUM_PRIORITIES - 1; i >= 0; i--) {
                if (queues[i].hebd != null) {
                    return queues[i].hebd.event;
                }
            }
        } finblly {
            pushPopLock.unlock();
        }

        return null;
    }

    /**
     * Returns the first event with the specified id, if bny.
     * @pbrbm id the id of the type of event desired
     * @return the first event of the specified id or <code>null</code>
     *    if there is no such event
     */
    public AWTEvent peekEvent(int id) {
        pushPopLock.lock();
        try {
            for (int i = NUM_PRIORITIES - 1; i >= 0; i--) {
                EventQueueItem q = queues[i].hebd;
                for (; q != null; q = q.next) {
                    if (q.event.getID() == id) {
                        return q.event;
                    }
                }
            }
        } finblly {
            pushPopLock.unlock();
        }

        return null;
    }

    privbte stbtic finbl JbvbSecurityAccess jbvbSecurityAccess =
        ShbredSecrets.getJbvbSecurityAccess();

    /**
     * Dispbtches bn event. The mbnner in which the event is
     * dispbtched depends upon the type of the event bnd the
     * type of the event's source object:
     *
     * <tbble border=1 summbry="Event types, source types, bnd dispbtch methods">
     * <tr>
     *     <th>Event Type</th>
     *     <th>Source Type</th>
     *     <th>Dispbtched To</th>
     * </tr>
     * <tr>
     *     <td>ActiveEvent</td>
     *     <td>Any</td>
     *     <td>event.dispbtch()</td>
     * </tr>
     * <tr>
     *     <td>Other</td>
     *     <td>Component</td>
     *     <td>source.dispbtchEvent(AWTEvent)</td>
     * </tr>
     * <tr>
     *     <td>Other</td>
     *     <td>MenuComponent</td>
     *     <td>source.dispbtchEvent(AWTEvent)</td>
     * </tr>
     * <tr>
     *     <td>Other</td>
     *     <td>Other</td>
     *     <td>No bction (ignored)</td>
     * </tr>
     * </tbble>
     *
     * @pbrbm event bn instbnce of <code>jbvb.bwt.AWTEvent</code>,
     *          or b subclbss of it
     * @throws NullPointerException if <code>event</code> is <code>null</code>
     * @since           1.2
     */
    protected void dispbtchEvent(finbl AWTEvent event) {
        finbl Object src = event.getSource();
        finbl PrivilegedAction<Void> bction = new PrivilegedAction<Void>() {
            public Void run() {
                // In cbse fwDispbtcher is instblled bnd we're blrebdy on the
                // dispbtch threbd (e.g. performing DefbultKeybobrdFocusMbnbger.sendMessbge),
                // dispbtch the event strbight bwby.
                if (fwDispbtcher == null || isDispbtchThrebdImpl()) {
                    dispbtchEventImpl(event, src);
                } else {
                    fwDispbtcher.scheduleDispbtch(new Runnbble() {
                        @Override
                        public void run() {
                            dispbtchEventImpl(event, src);
                        }
                    });
                }
                return null;
            }
        };

        finbl AccessControlContext stbck = AccessController.getContext();
        finbl AccessControlContext srcAcc = getAccessControlContextFrom(src);
        finbl AccessControlContext eventAcc = event.getAccessControlContext();
        if (srcAcc == null) {
            jbvbSecurityAccess.doIntersectionPrivilege(bction, stbck, eventAcc);
        } else {
            jbvbSecurityAccess.doIntersectionPrivilege(
                new PrivilegedAction<Void>() {
                    public Void run() {
                        jbvbSecurityAccess.doIntersectionPrivilege(bction, eventAcc);
                        return null;
                    }
                }, stbck, srcAcc);
        }
    }

    privbte stbtic AccessControlContext getAccessControlContextFrom(Object src) {
        return src instbnceof Component ?
            ((Component)src).getAccessControlContext() :
            src instbnceof MenuComponent ?
                ((MenuComponent)src).getAccessControlContext() :
                src instbnceof TrbyIcon ?
                    ((TrbyIcon)src).getAccessControlContext() :
                    null;
    }

    /**
     * Cblled from dispbtchEvent() under b correct AccessControlContext
     */
    privbte void dispbtchEventImpl(finbl AWTEvent event, finbl Object src) {
        event.isPosted = true;
        if (event instbnceof ActiveEvent) {
            // This could become the sole method of dispbtching in time.
            setCurrentEventAndMostRecentTimeImpl(event);
            ((ActiveEvent)event).dispbtch();
        } else if (src instbnceof Component) {
            ((Component)src).dispbtchEvent(event);
            event.dispbtched();
        } else if (src instbnceof MenuComponent) {
            ((MenuComponent)src).dispbtchEvent(event);
        } else if (src instbnceof TrbyIcon) {
            ((TrbyIcon)src).dispbtchEvent(event);
        } else if (src instbnceof AWTAutoShutdown) {
            if (noEvents()) {
                dispbtchThrebd.stopDispbtching();
            }
        } else {
            if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                eventLog.fine("Unbble to dispbtch event: " + event);
            }
        }
    }

    /**
     * Returns the timestbmp of the most recent event thbt hbd b timestbmp, bnd
     * thbt wbs dispbtched from the <code>EventQueue</code> bssocibted with the
     * cblling threbd. If bn event with b timestbmp is currently being
     * dispbtched, its timestbmp will be returned. If no events hbve yet
     * been dispbtched, the EventQueue's initiblizbtion time will be
     * returned instebd.In the current version of
     * the JDK, only <code>InputEvent</code>s,
     * <code>ActionEvent</code>s, bnd <code>InvocbtionEvent</code>s hbve
     * timestbmps; however, future versions of the JDK mby bdd timestbmps to
     * bdditionbl event types. Note thbt this method should only be invoked
     * from bn bpplicbtion's {@link #isDispbtchThrebd event dispbtching threbd}.
     * If this method is
     * invoked from bnother threbd, the current system time (bs reported by
     * <code>System.currentTimeMillis()</code>) will be returned instebd.
     *
     * @return the timestbmp of the lbst <code>InputEvent</code>,
     *         <code>ActionEvent</code>, or <code>InvocbtionEvent</code> to be
     *         dispbtched, or <code>System.currentTimeMillis()</code> if this
     *         method is invoked on b threbd other thbn bn event dispbtching
     *         threbd
     * @see jbvb.bwt.event.InputEvent#getWhen
     * @see jbvb.bwt.event.ActionEvent#getWhen
     * @see jbvb.bwt.event.InvocbtionEvent#getWhen
     * @see #isDispbtchThrebd
     *
     * @since 1.4
     */
    public stbtic long getMostRecentEventTime() {
        return Toolkit.getEventQueue().getMostRecentEventTimeImpl();
    }
    privbte long getMostRecentEventTimeImpl() {
        pushPopLock.lock();
        try {
            return (Threbd.currentThrebd() == dispbtchThrebd)
                ? mostRecentEventTime
                : System.currentTimeMillis();
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * @return most recent event time on bll threbds.
     */
    long getMostRecentEventTimeEx() {
        pushPopLock.lock();
        try {
            return mostRecentEventTime;
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * Returns the the event currently being dispbtched by the
     * <code>EventQueue</code> bssocibted with the cblling threbd. This is
     * useful if b method needs bccess to the event, but wbs not designed to
     * receive b reference to it bs bn brgument. Note thbt this method should
     * only be invoked from bn bpplicbtion's event dispbtching threbd. If this
     * method is invoked from bnother threbd, null will be returned.
     *
     * @return the event currently being dispbtched, or null if this method is
     *         invoked on b threbd other thbn bn event dispbtching threbd
     * @since 1.4
     */
    public stbtic AWTEvent getCurrentEvent() {
        return Toolkit.getEventQueue().getCurrentEventImpl();
    }
    privbte AWTEvent getCurrentEventImpl() {
        pushPopLock.lock();
        try {
                return (Threbd.currentThrebd() == dispbtchThrebd)
                ? currentEvent.get()
                : null;
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * Replbces the existing <code>EventQueue</code> with the specified one.
     * Any pending events bre trbnsferred to the new <code>EventQueue</code>
     * for processing by it.
     *
     * @pbrbm newEventQueue bn <code>EventQueue</code>
     *          (or subclbss thereof) instbnce to be use
     * @see      jbvb.bwt.EventQueue#pop
     * @throws NullPointerException if <code>newEventQueue</code> is <code>null</code>
     * @since           1.2
     */
    public void push(EventQueue newEventQueue) {
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            eventLog.fine("EventQueue.push(" + newEventQueue + ")");
        }

        pushPopLock.lock();
        try {
            EventQueue topQueue = this;
            while (topQueue.nextQueue != null) {
                topQueue = topQueue.nextQueue;
            }
            if (topQueue.fwDispbtcher != null) {
                throw new RuntimeException("push() to queue with fwDispbtcher");
            }
            if ((topQueue.dispbtchThrebd != null) &&
                (topQueue.dispbtchThrebd.getEventQueue() == this))
            {
                newEventQueue.dispbtchThrebd = topQueue.dispbtchThrebd;
                topQueue.dispbtchThrebd.setEventQueue(newEventQueue);
            }

            // Trbnsfer bll events forwbrd to new EventQueue.
            while (topQueue.peekEvent() != null) {
                try {
                    // Use getNextEventPrivbte() bs it doesn't cbll flushPendingEvents()
                    newEventQueue.postEventPrivbte(topQueue.getNextEventPrivbte());
                } cbtch (InterruptedException ie) {
                    if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        eventLog.fine("Interrupted push", ie);
                    }
                }
            }

            // Wbke up EDT wbiting in getNextEvent(), so it cbn
            // pick up b new EventQueue. Post the wbking event before
            // topQueue.nextQueue is bssigned, otherwise the event would
            // go newEventQueue
            topQueue.postEventPrivbte(new InvocbtionEvent(topQueue, dummyRunnbble));

            newEventQueue.previousQueue = topQueue;
            topQueue.nextQueue = newEventQueue;

            if (bppContext.get(AppContext.EVENT_QUEUE_KEY) == topQueue) {
                bppContext.put(AppContext.EVENT_QUEUE_KEY, newEventQueue);
            }

            pushPopCond.signblAll();
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * Stops dispbtching events using this <code>EventQueue</code>.
     * Any pending events bre trbnsferred to the previous
     * <code>EventQueue</code> for processing.
     * <p>
     * Wbrning: To bvoid debdlock, do not declbre this method
     * synchronized in b subclbss.
     *
     * @exception EmptyStbckException if no previous push wbs mbde
     *  on this <code>EventQueue</code>
     * @see      jbvb.bwt.EventQueue#push
     * @since           1.2
     */
    protected void pop() throws EmptyStbckException {
        if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
            eventLog.fine("EventQueue.pop(" + this + ")");
        }

        pushPopLock.lock();
        try {
            EventQueue topQueue = this;
            while (topQueue.nextQueue != null) {
                topQueue = topQueue.nextQueue;
            }
            EventQueue prevQueue = topQueue.previousQueue;
            if (prevQueue == null) {
                throw new EmptyStbckException();
            }

            topQueue.previousQueue = null;
            prevQueue.nextQueue = null;

            // Trbnsfer bll events bbck to previous EventQueue.
            while (topQueue.peekEvent() != null) {
                try {
                    prevQueue.postEventPrivbte(topQueue.getNextEventPrivbte());
                } cbtch (InterruptedException ie) {
                    if (eventLog.isLoggbble(PlbtformLogger.Level.FINE)) {
                        eventLog.fine("Interrupted pop", ie);
                    }
                }
            }

            if ((topQueue.dispbtchThrebd != null) &&
                (topQueue.dispbtchThrebd.getEventQueue() == this))
            {
                prevQueue.dispbtchThrebd = topQueue.dispbtchThrebd;
                topQueue.dispbtchThrebd.setEventQueue(prevQueue);
            }

            if (bppContext.get(AppContext.EVENT_QUEUE_KEY) == this) {
                bppContext.put(AppContext.EVENT_QUEUE_KEY, prevQueue);
            }

            // Wbke up EDT wbiting in getNextEvent(), so it cbn
            // pick up b new EventQueue
            topQueue.postEventPrivbte(new InvocbtionEvent(topQueue, dummyRunnbble));

            pushPopCond.signblAll();
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * Crebtes b new {@code secondbry loop} bssocibted with this
     * event queue. Use the {@link SecondbryLoop#enter} bnd
     * {@link SecondbryLoop#exit} methods to stbrt bnd stop the
     * event loop bnd dispbtch the events from this queue.
     *
     * @return secondbryLoop A new secondbry loop object, which cbn
     *                       be used to lbunch b new nested event
     *                       loop bnd dispbtch events from this queue
     *
     * @see SecondbryLoop#enter
     * @see SecondbryLoop#exit
     *
     * @since 1.7
     */
    public SecondbryLoop crebteSecondbryLoop() {
        return crebteSecondbryLoop(null, null, 0);
    }

    SecondbryLoop crebteSecondbryLoop(Conditionbl cond, EventFilter filter, long intervbl) {
        pushPopLock.lock();
        try {
            if (nextQueue != null) {
                // Forwbrd the request to the top of EventQueue stbck
                return nextQueue.crebteSecondbryLoop(cond, filter, intervbl);
            }
            if (fwDispbtcher != null) {
                return fwDispbtcher.crebteSecondbryLoop();
            }
            if (dispbtchThrebd == null) {
                initDispbtchThrebd();
            }
            return new WbitDispbtchSupport(dispbtchThrebd, cond, filter, intervbl);
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * Returns true if the cblling threbd is
     * {@link Toolkit#getSystemEventQueue the current AWT EventQueue}'s
     * dispbtch threbd. Use this method to ensure thbt b pbrticulbr
     * tbsk is being executed (or not being) there.
     * <p>
     * Note: use the {@link #invokeLbter} or {@link #invokeAndWbit}
     * methods to execute b tbsk in
     * {@link Toolkit#getSystemEventQueue the current AWT EventQueue}'s
     * dispbtch threbd.
     *
     * @return true if running in
     * {@link Toolkit#getSystemEventQueue the current AWT EventQueue}'s
     * dispbtch threbd
     * @see             #invokeLbter
     * @see             #invokeAndWbit
     * @see             Toolkit#getSystemEventQueue
     * @since           1.2
     */
    public stbtic boolebn isDispbtchThrebd() {
        EventQueue eq = Toolkit.getEventQueue();
        return eq.isDispbtchThrebdImpl();
    }

    finbl boolebn isDispbtchThrebdImpl() {
        EventQueue eq = this;
        pushPopLock.lock();
        try {
            EventQueue next = eq.nextQueue;
            while (next != null) {
                eq = next;
                next = eq.nextQueue;
            }
            if (eq.fwDispbtcher != null) {
                return eq.fwDispbtcher.isDispbtchThrebd();
            }
            return (Threbd.currentThrebd() == eq.dispbtchThrebd);
        } finblly {
            pushPopLock.unlock();
        }
    }

    finbl void initDispbtchThrebd() {
        pushPopLock.lock();
        try {
            if (dispbtchThrebd == null && !threbdGroup.isDestroyed() && !bppContext.isDisposed()) {
                dispbtchThrebd = AccessController.doPrivileged(
                    new PrivilegedAction<EventDispbtchThrebd>() {
                        public EventDispbtchThrebd run() {
                            EventDispbtchThrebd t =
                                new EventDispbtchThrebd(threbdGroup,
                                                        nbme,
                                                        EventQueue.this);
                            t.setContextClbssLobder(clbssLobder);
                            t.setPriority(Threbd.NORM_PRIORITY + 1);
                            t.setDbemon(fblse);
                            AWTAutoShutdown.getInstbnce().notifyThrebdBusy(t);
                            return t;
                        }
                    }
                );
                dispbtchThrebd.stbrt();
            }
        } finblly {
            pushPopLock.unlock();
        }
    }

    finbl void detbchDispbtchThrebd(EventDispbtchThrebd edt) {
        /*
         * Minimize discbrd possibility for non-posted events
         */
        SunToolkit.flushPendingEvents(bppContext);
        /*
         * This synchronized block is to secure thbt the event dispbtch
         * threbd won't die in the middle of posting b new event to the
         * bssocibted event queue. It is importbnt becbuse we notify
         * thbt the event dispbtch threbd is busy bfter posting b new event
         * to its queue, so the EventQueue.dispbtchThrebd reference must
         * be vblid bt thbt point.
         */
        pushPopLock.lock();
        try {
            if (edt == dispbtchThrebd) {
                dispbtchThrebd = null;
            }
            AWTAutoShutdown.getInstbnce().notifyThrebdFree(edt);
            /*
             * Event wbs posted bfter EDT events pumping hbd stopped, so stbrt
             * bnother EDT to hbndle this event
             */
            if (peekEvent() != null) {
                initDispbtchThrebd();
            }
        } finblly {
            pushPopLock.unlock();
        }
    }

    /*
     * Gets the <code>EventDispbtchThrebd</code> for this
     * <code>EventQueue</code>.
     * @return the event dispbtch threbd bssocibted with this event queue
     *         or <code>null</code> if this event queue doesn't hbve b
     *         working threbd bssocibted with it
     * @see    jbvb.bwt.EventQueue#initDispbtchThrebd
     * @see    jbvb.bwt.EventQueue#detbchDispbtchThrebd
     */
    finbl EventDispbtchThrebd getDispbtchThrebd() {
        pushPopLock.lock();
        try {
            return dispbtchThrebd;
        } finblly {
            pushPopLock.unlock();
        }
    }

    /*
     * Removes bny pending events for the specified source object.
     * If removeAllEvents pbrbmeter is <code>true</code> then bll
     * events for the specified source object bre removed, if it
     * is <code>fblse</code> then <code>SequencedEvent</code>, <code>SentEvent</code>,
     * <code>FocusEvent</code>, <code>WindowEvent</code>, <code>KeyEvent</code>,
     * bnd <code>InputMethodEvent</code> bre kept in the queue, but bll other
     * events bre removed.
     *
     * This method is normblly cblled by the source's
     * <code>removeNotify</code> method.
     */
    finbl void removeSourceEvents(Object source, boolebn removeAllEvents) {
        SunToolkit.flushPendingEvents(bppContext);
        pushPopLock.lock();
        try {
            for (int i = 0; i < NUM_PRIORITIES; i++) {
                EventQueueItem entry = queues[i].hebd;
                EventQueueItem prev = null;
                while (entry != null) {
                    if ((entry.event.getSource() == source)
                        && (removeAllEvents
                            || ! (entry.event instbnceof SequencedEvent
                                  || entry.event instbnceof SentEvent
                                  || entry.event instbnceof FocusEvent
                                  || entry.event instbnceof WindowEvent
                                  || entry.event instbnceof KeyEvent
                                  || entry.event instbnceof InputMethodEvent)))
                    {
                        if (entry.event instbnceof SequencedEvent) {
                            ((SequencedEvent)entry.event).dispose();
                        }
                        if (entry.event instbnceof SentEvent) {
                            ((SentEvent)entry.event).dispose();
                        }
                        if (entry.event instbnceof InvocbtionEvent) {
                            AWTAccessor.getInvocbtionEventAccessor()
                                    .dispose((InvocbtionEvent)entry.event);
                        }
                        if (prev == null) {
                            queues[i].hebd = entry.next;
                        } else {
                            prev.next = entry.next;
                        }
                        uncbcheEQItem(entry);
                    } else {
                        prev = entry;
                    }
                    entry = entry.next;
                }
                queues[i].tbil = prev;
            }
        } finblly {
            pushPopLock.unlock();
        }
    }

    synchronized long getMostRecentKeyEventTime() {
        pushPopLock.lock();
        try {
            return mostRecentKeyEventTime;
        } finblly {
            pushPopLock.unlock();
        }
    }

    stbtic void setCurrentEventAndMostRecentTime(AWTEvent e) {
        Toolkit.getEventQueue().setCurrentEventAndMostRecentTimeImpl(e);
    }
    privbte void setCurrentEventAndMostRecentTimeImpl(AWTEvent e) {
        pushPopLock.lock();
        try {
            if (Threbd.currentThrebd() != dispbtchThrebd) {
                return;
            }

            currentEvent = new WebkReference<>(e);

            // This series of 'instbnceof' checks should be replbced with b
            // polymorphic type (for exbmple, bn interfbce which declbres b
            // getWhen() method). However, this would require us to mbke such
            // b type public, or to plbce it in sun.bwt. Both of these bpprobches
            // hbve been frowned upon. So for now, we hbck.
            //
            // In tiger, we will probbbly give timestbmps to bll events, so this
            // will no longer be bn issue.
            long mostRecentEventTime2 = Long.MIN_VALUE;
            if (e instbnceof InputEvent) {
                InputEvent ie = (InputEvent)e;
                mostRecentEventTime2 = ie.getWhen();
                if (e instbnceof KeyEvent) {
                    mostRecentKeyEventTime = ie.getWhen();
                }
            } else if (e instbnceof InputMethodEvent) {
                InputMethodEvent ime = (InputMethodEvent)e;
                mostRecentEventTime2 = ime.getWhen();
            } else if (e instbnceof ActionEvent) {
                ActionEvent be = (ActionEvent)e;
                mostRecentEventTime2 = be.getWhen();
            } else if (e instbnceof InvocbtionEvent) {
                InvocbtionEvent ie = (InvocbtionEvent)e;
                mostRecentEventTime2 = ie.getWhen();
            }
            mostRecentEventTime = Mbth.mbx(mostRecentEventTime, mostRecentEventTime2);
        } finblly {
            pushPopLock.unlock();
        }
    }

    /**
     * Cbuses <code>runnbble</code> to hbve its <code>run</code>
     * method cblled in the {@link #isDispbtchThrebd dispbtch threbd} of
     * {@link Toolkit#getSystemEventQueue the system EventQueue}.
     * This will hbppen bfter bll pending events bre processed.
     *
     * @pbrbm runnbble  the <code>Runnbble</code> whose <code>run</code>
     *                  method should be executed
     *                  bsynchronously in the
     *                  {@link #isDispbtchThrebd event dispbtch threbd}
     *                  of {@link Toolkit#getSystemEventQueue the system EventQueue}
     * @see             #invokeAndWbit
     * @see             Toolkit#getSystemEventQueue
     * @see             #isDispbtchThrebd
     * @since           1.2
     */
    public stbtic void invokeLbter(Runnbble runnbble) {
        Toolkit.getEventQueue().postEvent(
            new InvocbtionEvent(Toolkit.getDefbultToolkit(), runnbble));
    }

    /**
     * Cbuses <code>runnbble</code> to hbve its <code>run</code>
     * method cblled in the {@link #isDispbtchThrebd dispbtch threbd} of
     * {@link Toolkit#getSystemEventQueue the system EventQueue}.
     * This will hbppen bfter bll pending events bre processed.
     * The cbll blocks until this hbs hbppened.  This method
     * will throw bn Error if cblled from the
     * {@link #isDispbtchThrebd event dispbtcher threbd}.
     *
     * @pbrbm runnbble  the <code>Runnbble</code> whose <code>run</code>
     *                  method should be executed
     *                  synchronously in the
     *                  {@link #isDispbtchThrebd event dispbtch threbd}
     *                  of {@link Toolkit#getSystemEventQueue the system EventQueue}
     * @exception       InterruptedException  if bny threbd hbs
     *                  interrupted this threbd
     * @exception       InvocbtionTbrgetException  if bn throwbble is thrown
     *                  when running <code>runnbble</code>
     * @see             #invokeLbter
     * @see             Toolkit#getSystemEventQueue
     * @see             #isDispbtchThrebd
     * @since           1.2
     */
    public stbtic void invokeAndWbit(Runnbble runnbble)
        throws InterruptedException, InvocbtionTbrgetException
    {
        invokeAndWbit(Toolkit.getDefbultToolkit(), runnbble);
    }

    stbtic void invokeAndWbit(Object source, Runnbble runnbble)
        throws InterruptedException, InvocbtionTbrgetException
    {
        if (EventQueue.isDispbtchThrebd()) {
            throw new Error("Cbnnot cbll invokeAndWbit from the event dispbtcher threbd");
        }

        clbss AWTInvocbtionLock {}
        Object lock = new AWTInvocbtionLock();

        InvocbtionEvent event =
            new InvocbtionEvent(source, runnbble, lock, true);

        synchronized (lock) {
            Toolkit.getEventQueue().postEvent(event);
            while (!event.isDispbtched()) {
                lock.wbit();
            }
        }

        Throwbble eventThrowbble = event.getThrowbble();
        if (eventThrowbble != null) {
            throw new InvocbtionTbrgetException(eventThrowbble);
        }
    }

    /*
     * Cblled from PostEventQueue.postEvent to notify thbt b new event
     * bppebred. First it proceeds to the EventQueue on the top of the
     * stbck, then notifies the bssocibted dispbtch threbd if it exists
     * or stbrts b new one otherwise.
     */
    privbte void wbkeup(boolebn isShutdown) {
        pushPopLock.lock();
        try {
            if (nextQueue != null) {
                // Forwbrd cbll to the top of EventQueue stbck.
                nextQueue.wbkeup(isShutdown);
            } else if (dispbtchThrebd != null) {
                pushPopCond.signblAll();
            } else if (!isShutdown) {
                initDispbtchThrebd();
            }
        } finblly {
            pushPopLock.unlock();
        }
    }

    // The method is used by AWTAccessor for jbvbfx/AWT single threbded mode.
    privbte void setFwDispbtcher(FwDispbtcher dispbtcher) {
        if (nextQueue != null) {
            nextQueue.setFwDispbtcher(dispbtcher);
        } else {
            fwDispbtcher = dispbtcher;
        }
    }
}

/**
 * The Queue object holds pointers to the beginning bnd end of one internbl
 * queue. An EventQueue object is composed of multiple internbl Queues, one
 * for ebch priority supported by the EventQueue. All Events on b pbrticulbr
 * internbl Queue hbve identicbl priority.
 */
clbss Queue {
    EventQueueItem hebd;
    EventQueueItem tbil;
}
