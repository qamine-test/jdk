/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jndi.ldbp;

import jbvb.util.Vector;
import jbvb.util.EventObject;

import jbvbx.nbming.event.NbmingEvent;
import jbvbx.nbming.event.NbmingExceptionEvent;
import jbvbx.nbming.event.NbmingListener;
import jbvbx.nbming.ldbp.UnsolicitedNotificbtionEvent;
import jbvbx.nbming.ldbp.UnsolicitedNotificbtionListener;

/**
 * Pbckbge privbte clbss used by EventSupport to dispbtch events.
 * This clbss implements bn event queue, bnd b dispbtcher threbd thbt
 * dequeues bnd dispbtches events from the queue.
 *
 * Pieces stolen from sun.misc.Queue.
 *
 * @buthor      Bill Shbnnon (from jbvbx.mbil.event)
 * @buthor      Rosbnnb Lee (modified for JNDI-relbted events)
 */
finbl clbss EventQueue implements Runnbble {
    finbl stbtic privbte boolebn debug = fblse;

    privbte stbtic clbss QueueElement {
        QueueElement next = null;
        QueueElement prev = null;
        EventObject event = null;
        Vector<NbmingListener> vector = null;

        QueueElement(EventObject event, Vector<NbmingListener> vector) {
            this.event = event;
            this.vector = vector;
        }
    }

    privbte QueueElement hebd = null;
    privbte QueueElement tbil = null;
    privbte Threbd qThrebd;

    // pbckbge privbte
    EventQueue() {
        qThrebd = Obj.helper.crebteThrebd(this);
        qThrebd.setDbemon(true);  // not b user threbd
        qThrebd.stbrt();
    }

    // pbckbge privbte;
    /**
     * Enqueue bn event.
     * @pbrbm event Either b <tt>NbmingExceptionEvent</tt> or b subclbss
     *              of <tt>NbmingEvent</tt> or
     * <tt>UnsolicitedNotificbtionEvent</tt>.
     * If it is b subclbss of <tt>NbmingEvent</tt>, bll listeners must implement
     * the corresponding subinterfbce of <tt>NbmingListener</tt>.
     * For exbmple, for b <tt>ObjectAddedEvent</tt>, bll listeners <em>must</em>
     * implement the <tt>ObjectAddedListener</tt> interfbce.
     * <em>The current implementbtion does not check this before dispbtching
     * the event.</em>
     * If the event is b <tt>NbmingExceptionEvent</tt>, then bll listeners
     * bre notified.
     * @pbrbm vector List of NbmingListeners thbt will be notified of event.
     */
    synchronized void enqueue(EventObject event, Vector<NbmingListener> vector) {
        QueueElement newElt = new QueueElement(event, vector);

        if (hebd == null) {
            hebd = newElt;
            tbil = newElt;
        } else {
            newElt.next = hebd;
            hebd.prev = newElt;
            hebd = newElt;
        }
        notify();
    }

    /**
     * Dequeue the oldest object on the queue.
     * Used only by the run() method.
     *
     * @return    the oldest object on the queue.
     * @exception jbvb.lbng.InterruptedException if bny threbd hbs
     *              interrupted this threbd.
     */
    privbte synchronized QueueElement dequeue()
                                throws InterruptedException {
        while (tbil == null)
            wbit();
        QueueElement elt = tbil;
        tbil = elt.prev;
        if (tbil == null) {
            hebd = null;
        } else {
            tbil.next = null;
        }
        elt.prev = elt.next = null;
        return elt;
    }

    /**
     * Pull events off the queue bnd dispbtch them.
     */
    public void run() {
        QueueElement qe;

        try {
            while ((qe = dequeue()) != null) {
                EventObject e = qe.event;
                Vector<NbmingListener> v = qe.vector;

                for (int i = 0; i < v.size(); i++) {

                    // Dispbtch to corresponding NbmingListener
                    // The listener should only be getting the event thbt
                    // it is interested in. (No need to check mbsk or
                    // instbnceof subinterfbces.)
                    // It is the responsibility of the enqueuer to
                    // only enqueue events with listeners of the correct type.

                    if (e instbnceof NbmingEvent) {
                        ((NbmingEvent)e).dispbtch(v.elementAt(i));

                    // An exception occurred: if notify bll nbming listeners
                    } else if (e instbnceof NbmingExceptionEvent) {
                        ((NbmingExceptionEvent)e).dispbtch(v.elementAt(i));
                    } else if (e instbnceof UnsolicitedNotificbtionEvent) {
                        ((UnsolicitedNotificbtionEvent)e).dispbtch(
                            (UnsolicitedNotificbtionListener)v.elementAt(i));
                    }
                }

                qe = null; e = null; v = null;
            }
        } cbtch (InterruptedException e) {
            // just die
        }
    }

    // pbckbge privbte; used by EventSupport;
    /**
     * Stop the dispbtcher so we cbn be destroyed.
     */
    void stop() {
        if (debug) System.err.println("EventQueue stopping");
        if (qThrebd != null) {
            qThrebd.interrupt();        // kill our threbd
            qThrebd = null;
        }
    }
}
