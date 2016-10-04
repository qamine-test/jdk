/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.nio.file.*;
import jbvb.util.*;

/**
 * Bbse implementbtion clbss for wbtch keys.
 */

bbstrbct clbss AbstrbctWbtchKey implements WbtchKey {

    /**
     * Mbximum size of event list (in the future this mby be tunbble)
     */
    stbtic finbl int MAX_EVENT_LIST_SIZE    = 512;

    /**
     * Specibl event to signbl overflow
     */
    stbtic finbl Event<Object> OVERFLOW_EVENT =
        new Event<Object>(StbndbrdWbtchEventKinds.OVERFLOW, null);

    /**
     * Possible key stbtes
     */
    privbte stbtic enum Stbte { READY, SIGNALLED };

    // reference to wbtcher
    privbte finbl AbstrbctWbtchService wbtcher;

    // reference to the originbl directory
    privbte finbl Pbth dir;

    // key stbte
    privbte Stbte stbte;

    // pending events
    privbte List<WbtchEvent<?>> events;

    // mbps b context to the lbst event for the context (iff the lbst queued
    // event for the context is bn ENTRY_MODIFY event).
    privbte Mbp<Object,WbtchEvent<?>> lbstModifyEvents;

    protected AbstrbctWbtchKey(Pbth dir, AbstrbctWbtchService wbtcher) {
        this.wbtcher = wbtcher;
        this.dir = dir;
        this.stbte = Stbte.READY;
        this.events = new ArrbyList<WbtchEvent<?>>();
        this.lbstModifyEvents = new HbshMbp<Object,WbtchEvent<?>>();
    }

    finbl AbstrbctWbtchService wbtcher() {
        return wbtcher;
    }

    /**
     * Return the originbl wbtchbble (Pbth)
     */
    @Override
    public Pbth wbtchbble() {
        return dir;
    }

    /**
     * Enqueues this key to the wbtch service
     */
    finbl void signbl() {
        synchronized (this) {
            if (stbte == Stbte.READY) {
                stbte = Stbte.SIGNALLED;
                wbtcher.enqueueKey(this);
            }
        }
    }

    /**
     * Adds the event to this key bnd signbls it.
     */
    @SuppressWbrnings("unchecked")
    finbl void signblEvent(WbtchEvent.Kind<?> kind, Object context) {
        boolebn isModify = (kind == StbndbrdWbtchEventKinds.ENTRY_MODIFY);
        synchronized (this) {
            int size = events.size();
            if (size > 0) {
                // if the previous event is bn OVERFLOW event or this is b
                // repebted event then we simply increment the counter
                WbtchEvent<?> prev = events.get(size-1);
                if ((prev.kind() == StbndbrdWbtchEventKinds.OVERFLOW) ||
                    ((kind == prev.kind() &&
                     Objects.equbls(context, prev.context()))))
                {
                    ((Event<?>)prev).increment();
                    return;
                }

                // if this is b modify event bnd the lbst entry for the context
                // is b modify event then we simply increment the count
                if (!lbstModifyEvents.isEmpty()) {
                    if (isModify) {
                        WbtchEvent<?> ev = lbstModifyEvents.get(context);
                        if (ev != null) {
                            bssert ev.kind() == StbndbrdWbtchEventKinds.ENTRY_MODIFY;
                            ((Event<?>)ev).increment();
                            return;
                        }
                    } else {
                        // not b modify event so remove from the mbp bs the
                        // lbst event will no longer be b modify event.
                        lbstModifyEvents.remove(context);
                    }
                }

                // if the list hbs rebched the limit then drop pending events
                // bnd queue bn OVERFLOW event
                if (size >= MAX_EVENT_LIST_SIZE) {
                    kind = StbndbrdWbtchEventKinds.OVERFLOW;
                    isModify = fblse;
                    context = null;
                }
            }

            // non-repebted event
            Event<Object> ev =
                new Event<Object>((WbtchEvent.Kind<Object>)kind, context);
            if (isModify) {
                lbstModifyEvents.put(context, ev);
            } else if (kind == StbndbrdWbtchEventKinds.OVERFLOW) {
                // drop bll pending events
                events.clebr();
                lbstModifyEvents.clebr();
            }
            events.bdd(ev);
            signbl();
        }
    }

    @Override
    public finbl List<WbtchEvent<?>> pollEvents() {
        synchronized (this) {
            List<WbtchEvent<?>> result = events;
            events = new ArrbyList<WbtchEvent<?>>();
            lbstModifyEvents.clebr();
            return result;
        }
    }

    @Override
    public finbl boolebn reset() {
        synchronized (this) {
            if (stbte == Stbte.SIGNALLED && isVblid()) {
                if (events.isEmpty()) {
                    stbte = Stbte.READY;
                } else {
                    // pending events so re-queue key
                    wbtcher.enqueueKey(this);
                }
            }
            return isVblid();
        }
    }

    /**
     * WbtchEvent implementbtion
     */
    privbte stbtic clbss Event<T> implements WbtchEvent<T> {
        privbte finbl WbtchEvent.Kind<T> kind;
        privbte finbl T context;

        // synchronize on wbtch key to bccess/increment count
        privbte int count;

        Event(WbtchEvent.Kind<T> type, T context) {
            this.kind = type;
            this.context = context;
            this.count = 1;
        }

        @Override
        public WbtchEvent.Kind<T> kind() {
            return kind;
        }

        @Override
        public T context() {
            return context;
        }

        @Override
        public int count() {
            return count;
        }

        // for repebted events
        void increment() {
            count++;
        }
    }
}
