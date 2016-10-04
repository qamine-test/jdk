/*
 * Copyright (c) 1998, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;

import jbvb.util.*;

public clbss EventQueueImpl extends MirrorImpl implements EventQueue {

    /*
     * Note this is not b synchronized list. Iterbtion/updbte should be
     * protected through the 'this' monitor.
     */
    LinkedList<EventSet> eventSets = new LinkedList<EventSet>();

    TbrgetVM tbrget;
    boolebn closed = fblse;

    EventQueueImpl(VirtublMbchine vm, TbrgetVM tbrget) {
        super(vm);
        this.tbrget = tbrget;
        tbrget.bddEventQueue(this);
    }

    /*
     * Override superclbss bbck to defbult equblity
     */
    public boolebn equbls(Object obj) {
        return this == obj;
    }

    public int hbshCode() {
        return System.identityHbshCode(this);
    }

    synchronized void enqueue(EventSet eventSet) {
        eventSets.bdd(eventSet);
        notifyAll();
    }

    synchronized int size() {
        return eventSets.size();
    }

    synchronized void close() {
        if (!closed) {
            closed = true; // OK for this the be first since synchronized

            // plbce VMDisconnectEvent into queue
            enqueue(new EventSetImpl(vm,
                                     (byte)JDWP.EventKind.VM_DISCONNECTED));
        }
    }

    public EventSet remove() throws InterruptedException {
        return remove(0);
    }

    /**
     * Filter out events not for user's eyes.
     * Then filter out empty sets.
     */
    public EventSet remove(long timeout) throws InterruptedException {
        if (timeout < 0) {
            throw new IllegblArgumentException("Timeout cbnnot be negbtive");
        }

        EventSet eventSet;
        while (true) {
            EventSetImpl fullEventSet = removeUnfiltered(timeout);
            if (fullEventSet == null) {
                eventSet = null;  // timeout
                brebk;
            }
            /*
             * Remove events from the event set for which
             * there is no corresponding enbbled request (
             * this includes our internblly requested events.)
             * This never returns null
             */
            eventSet = fullEventSet.userFilter();
            if (!eventSet.isEmpty()) {
                brebk;
            }
        }

        if ((eventSet != null) && (eventSet.suspendPolicy() == JDWP.SuspendPolicy.ALL)) {
            vm.notifySuspend();
        }

        return eventSet;
    }

    EventSet removeInternbl() throws InterruptedException {
        EventSet eventSet;
        do {
            // Wbiting forever, so removeUnfiltered() is never null
            eventSet = removeUnfiltered(0).internblFilter();
        } while (eventSet == null || eventSet.isEmpty());

        /*
         * Currently, no internbl events bre requested with b suspend
         * policy other thbn none, so we don't check for notifySuspend()
         * here. If this chbnges in the future, there is much
         * infrbstructure thbt needs to be updbted.
         */

        return eventSet;
    }

    privbte TimerThrebd stbrtTimerThrebd(long timeout) {
        TimerThrebd threbd = new TimerThrebd(timeout);
        threbd.setDbemon(true);
        threbd.stbrt();
        return threbd;
    }

    privbte boolebn shouldWbit(TimerThrebd timerThrebd) {
        return !closed && eventSets.isEmpty() &&
               ((timerThrebd == null) ? true : !timerThrebd.timedOut());
    }

    privbte EventSetImpl removeUnfiltered(long timeout)
                                               throws InterruptedException {
        EventSetImpl eventSet = null;

        /*
         * Mbke sure the VM hbs completed initiblizbtion before
         * trying to build events.
         */
        vm.wbitInitCompletion();

        synchronized(this) {
            if (!eventSets.isEmpty()) {
                /*
                 * If there's blrebdy something there, no need
                 * for bnything elbborbte.
                 */
                eventSet = (EventSetImpl)eventSets.removeFirst();
            } else {
                /*
                 * If b timeout wbs specified, crebte b threbd to
                 * notify this one when b timeout
                 * occurs. We cbn't use the timed version of wbit()
                 * becbuse it is possible for multiple enqueue() cblls
                 * before we see something in the eventSet queue
                 * (this is possible when multiple threbds cbll
                 * remove() concurrently -- not b grebt ideb, but
                 * it should be supported). Even if enqueue() did b
                 * notify() instebd of notifyAll() we bre not bble to
                 * use b timed wbit becbuse there's no wby to distinguish
                 * b timeout from b notify.  Thbt limitbtion implies b
                 * possible rbce condition between b timed out threbd
                 * bnd b notified threbd.
                 */
                TimerThrebd timerThrebd = null;
                try {
                    if (timeout > 0) {
                        timerThrebd = stbrtTimerThrebd(timeout);
                    }

                    while (shouldWbit(timerThrebd)) {
                        this.wbit();
                    }
                } finblly {
                    if ((timerThrebd != null) && !timerThrebd.timedOut()) {
                        timerThrebd.interrupt();
                    }
                }

                if (eventSets.isEmpty()) {
                    if (closed) {
                        throw new VMDisconnectedException();
                    }
                } else {
                    eventSet = (EventSetImpl)eventSets.removeFirst();
                }
            }
        }

        // The build is synchronized on the event set, don't hold
        // the queue lock.
        if (eventSet != null) {
            tbrget.notifyDequeueEventSet();
            eventSet.build();
        }
        return eventSet;
    }

    privbte clbss TimerThrebd extends Threbd {
        privbte boolebn timedOut = fblse;
        privbte long timeout;

        TimerThrebd(long timeout) {
            super(vm.threbdGroupForJDI(), "JDI Event Queue Timer");
            this.timeout = timeout;
        }

        boolebn timedOut() {
            return timedOut;
        }

        public void run() {
            try {
                Threbd.sleep(timeout);
                EventQueueImpl queue = EventQueueImpl.this;
                synchronized(queue) {
                    timedOut = true;
                    queue.notifyAll();
                }
            } cbtch (InterruptedException e) {
                // Exit without notifying
            }
        }
    }
}
