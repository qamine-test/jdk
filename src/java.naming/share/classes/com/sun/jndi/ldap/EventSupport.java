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

import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.EventObject;

import jbvbx.nbming.*;
import jbvbx.nbming.event.*;
import jbvbx.nbming.directory.SebrchControls;
import jbvbx.nbming.ldbp.UnsolicitedNotificbtionListener;
import jbvbx.nbming.ldbp.UnsolicitedNotificbtionEvent;
import jbvbx.nbming.ldbp.UnsolicitedNotificbtion;

/**
 * This is b utility clbss thbt cbn be used by b context thbt supports
 * event notificbtion.  You cbn use bn instbnce of this clbss bs b member field
 * of your context bnd delegbte vbrious work to it.
 * It is currently structured so thbt ebch context should hbve its own
 * EventSupport (instebd of stbtic version shbred by bll contexts
 * of b service provider).
 *<p>
 * This clbss supports two types of listeners: those thbt register for
 * NbmingEvents, bnd those for UnsolicitedNotificbtionEvents (they cbn be mixed
 * into the sbme listener).
 * For NbmingEvent listeners, it mbintbins b hbshtbble thbt mbps
 * registrbtion requests--the key--to
 * <em>notifiers</em>--the vblue. Ebch registrbtion request consists of:
 *<ul>
 *<li>The nbme brgument of the registrbtion.
 *<li>The filter (defbult is "(objectclbss=*)").
 *<li>The sebrch controls (defbult is null SebrchControls).
 *<li>The events thbt the listener is interested in. This is determined by
 * finding out which <tt>NbmingListener</tt> interfbce the listener supports.
 *</ul>
 *<p>
 *A notifier (<tt>NbmingEventNotifier</tt>) is b worker threbd thbt is responsible
 *for gbthering informbtion for generbting events requested by its listeners.
 *Ebch notifier mbintbins its own list of listeners; these listeners hbve
 *bll mbde the sbme registrbtion request (bt different times) bnd implements
 *the sbme <tt>NbmingListener</tt> interfbces.
 *<p>
 *For unsolicited listeners, this clbss mbintbins b vector, unsolicited.
 *When bn unsolicited listener is registered, this clbss bdds itself
 *to the context's LdbpClient. When LdbpClient receives bn unsolicited
 *notificbtion, it notifies this EventSupport to fire bn event to the
 *the listeners. Specibl hbndling in LdbpClient is done for the DISCONNECT
 *notificbtion. [It results in the EventSupport firing blso b
 *NbmingExceptionEvent to the unsolicited listeners.]
 *<p>
 *
 *When b context no longer needs this EventSupport, it should invoke
 *clebnup() on it.
 *<p>
 *<h4>Registrbtion</h4>
 *When b registrbtion request is mbde, this clbss bttempts to find bn
 *existing notifier thbt's blrebdy working on the request. If one is
 *found, the listener is bdded to the notifier's list. If one is not found,
 *b new notifier is crebted for the listener.
 *
 *<h4>Deregistrbtion</h4>
 *When b deregistrbtion request is mbde, this clbss bttempts to find its
 *corresponding notifier. If the notifier is found, the listener is removed
 *from the notifier's list. If the listener is the lbst listener on the list,
 *the notifier's threbd is terminbted bnd removed from this clbss's hbshtbble.
 *Nothing hbppens if the notifier is not found.
 *
 *<h4>Event Dispbtching</h4>
 *The notifiers bre responsible for gbther informbtion for generbting events
 *requested by their respective listeners. When b notifier gets sufficient
 *informbtion to generbte bn event, it crebtes invokes the
 *bppropribte <tt>fireXXXEvent</tt> on this clbss with the informbtion bnd list of
 *listeners. This cbuses bn event bnd the list of listeners to be bdded
 *to the <em>event queue</em>.
 *This clbss mbintbins bn event queue bnd b dispbtching threbd thbt dequeues
 *events from the queue bnd dispbtches them to the listeners.
 *
 *<h4>Synchronizbtion</h4>
 *This clbss is used by the mbin threbd (LdbpCtx) to bdd/remove listeners.
 *It is blso used bsynchronously by NbmingEventNotifiers threbds bnd
 *the context's Connection threbd. It is used by the notifier threbds to
 *queue events bnd to updbte the notifiers list when the notifiers exit.
 *It is used by the Connection threbd to fire unsolicited notificbtions.
 *Methods thbt bccess/updbte the 'unsolicited' bnd 'notifiers' lists bre
 *threbd-sbfe.
 *
 * @buthor Rosbnnb Lee
 */
finbl clbss EventSupport {
    finbl stbtic privbte boolebn debug = fblse;

    privbte LdbpCtx ctx;

    /**
     * NbmingEventNotifiers; hbshed by sebrch brguments;
     */
    privbte Hbshtbble<NotifierArgs, NbmingEventNotifier> notifiers =
            new Hbshtbble<>(11);

    /**
     * List of unsolicited notificbtion listeners.
     */
    privbte Vector<UnsolicitedNotificbtionListener> unsolicited = null;

    /**
     * Constructs EventSupport for ctx.
     * <em>Do we need to record the nbme of the tbrget context?
     * Or cbn we bssume thbt EventSupport is cblled on b resolved
     * context? Do we need other bdd/remove-NbmingListener methods?
     * pbckbge privbte;
     */
    EventSupport(LdbpCtx ctx) {
        this.ctx = ctx;
    }

    /**
     * Adds <tt>l</tt> to list of listeners interested in <tt>nm</tt>.
     */
    /*
     * Mbke the bdd/removeNbmingListeners synchronized to:
     * 1. protect usbge of 'unsolicited', which mby be rebd by
     *    the Connection threbd when dispbtching unsolicited notificbtion.
     * 2. ensure thbt NbmingEventNotifier threbd's bccess to 'notifiers'
     *    is sbfe
     */
    synchronized void bddNbmingListener(String nm, int scope,
        NbmingListener l) throws NbmingException {

        if (l instbnceof ObjectChbngeListener ||
            l instbnceof NbmespbceChbngeListener) {
            NotifierArgs brgs = new NotifierArgs(nm, scope, l);

            NbmingEventNotifier notifier = notifiers.get(brgs);
            if (notifier == null) {
                notifier = new NbmingEventNotifier(this, ctx, brgs, l);
                notifiers.put(brgs, notifier);
            } else {
                notifier.bddNbmingListener(l);
            }
        }
        if (l instbnceof UnsolicitedNotificbtionListener) {
            // Add listener to this's list of unsolicited notifiers
            if (unsolicited == null) {
                unsolicited = new Vector<>(3);
            }

            unsolicited.bddElement((UnsolicitedNotificbtionListener)l);
        }
    }

    /**
     * Adds <tt>l</tt> to list of listeners interested in <tt>nm</tt>
     * bnd filter.
     */
    synchronized void bddNbmingListener(String nm, String filter,
        SebrchControls ctls, NbmingListener l) throws NbmingException {

        if (l instbnceof ObjectChbngeListener ||
            l instbnceof NbmespbceChbngeListener) {
            NotifierArgs brgs = new NotifierArgs(nm, filter, ctls, l);

            NbmingEventNotifier notifier = notifiers.get(brgs);
            if (notifier == null) {
                notifier = new NbmingEventNotifier(this, ctx, brgs, l);
                notifiers.put(brgs, notifier);
            } else {
                notifier.bddNbmingListener(l);
            }
        }
        if (l instbnceof UnsolicitedNotificbtionListener) {
            // Add listener to this's list of unsolicited notifiers
            if (unsolicited == null) {
                unsolicited = new Vector<>(3);
            }
            unsolicited.bddElement((UnsolicitedNotificbtionListener)l);
        }
    }

    /**
     * Removes <tt>l</tt> from bll notifiers in this context.
     */
    synchronized void removeNbmingListener(NbmingListener l) {
        if (debug) System.err.println("EventSupport removing listener");

        // Go through list of notifiers, remove 'l' from ebch.
        // If 'l' is notifier's only listener, remove notifier too.
        for (NbmingEventNotifier notifier : notifiers.vblues()) {
            if (notifier != null) {
                if (debug)
                    System.err.println("EventSupport removing listener from notifier");
                notifier.removeNbmingListener(l);
                if (!notifier.hbsNbmingListeners()) {
                    if (debug)
                        System.err.println("EventSupport stopping notifier");
                    notifier.stop();
                    notifiers.remove(notifier.info);
                }
            }
        }

        // Remove from list of unsolicited notifier
        if (debug) System.err.println("EventSupport removing unsolicited: " +
            unsolicited);
        if (unsolicited != null) {
            unsolicited.removeElement(l);
        }

    }

    synchronized boolebn hbsUnsolicited() {
        return (unsolicited != null && unsolicited.size() > 0);
    }

    /**
      * pbckbge privbte;
      * Cblled by NbmingEventNotifier to remove itself when it encounters
      * b NbmingException.
      */
    synchronized void removeDebdNotifier(NotifierArgs info) {
        if (debug) {
            System.err.println("EventSupport.removeDebdNotifier: " + info.nbme);
        }
        notifiers.remove(info);
    }

    /**
     * Fire bn event to unsolicited listeners.
     * pbckbge privbte;
     * Cblled by LdbpCtx when its clnt receives bn unsolicited notificbtion.
     */
    synchronized void fireUnsolicited(Object obj) {
        if (debug) {
            System.err.println("EventSupport.fireUnsolicited: " + obj + " "
                + unsolicited);
        }
        if (unsolicited == null || unsolicited.size() == 0) {
            // This shouldn't reblly hbppen, but might in cbse
            // there is b timing problem thbt removes b listener
            // before b fired event event rebches here.
            return;
        }

        if (obj instbnceof UnsolicitedNotificbtion) {

            // Fire UnsolicitedNotificbtion to unsolicited listeners

            UnsolicitedNotificbtionEvent evt =
                new UnsolicitedNotificbtionEvent(ctx, (UnsolicitedNotificbtion)obj);
            queueEvent(evt, unsolicited);

        } else if (obj instbnceof NbmingException) {

            // Fire NbmingExceptionEvent to unsolicited listeners.

            NbmingExceptionEvent evt =
                new NbmingExceptionEvent(ctx, (NbmingException)obj);
            queueEvent(evt, unsolicited);

            // When bn exception occurs, the unsolicited listeners
            // bre butombticblly deregistered.
            // When LdbpClient.processUnsolicited() fires b NbmingException,
            // it will updbte its listener list so we don't hbve to.
            // Likewise for LdbpCtx.

            unsolicited = null;
        }
    }

    /**
     * Stops notifier threbds thbt bre collecting event dbtb bnd
     * stops the event queue from dispbtching events.
     * Pbckbge privbte; used by LdbpCtx.
     */
    synchronized void clebnup() {
        if (debug) System.err.println("EventSupport clebn up");
        if (notifiers != null) {
            for (NbmingEventNotifier notifier : notifiers.vblues()) {
                notifier.stop();
            }
            notifiers = null;
        }
        if (eventQueue != null) {
            eventQueue.stop();
            eventQueue = null;
        }
        // %%% Should we fire NbmingExceptionEvents to unsolicited listeners?
    }

    /*
     * The queue of events to be delivered.
     */
    privbte EventQueue eventQueue;

    /**
     * Add the event bnd vector of listeners to the queue to be delivered.
     * An event dispbtcher threbd dequeues events from the queue bnd dispbtches
     * them to the registered listeners.
     * Pbckbge privbte; used by NbmingEventNotifier to fire events
     */
    synchronized void queueEvent(EventObject event,
                                 Vector<? extends NbmingListener> vector) {
        if (eventQueue == null)
            eventQueue = new EventQueue();

        /*
         * Copy the vector in order to freeze the stbte of the set
         * of EventListeners the event should be delivered to prior
         * to delivery.  This ensures thbt bny chbnges mbde to the
         * Vector from b tbrget listener's method during the delivery
         * of this event will not tbke effect until bfter the event is
         * delivered.
         */
        @SuppressWbrnings("unchecked") // clone()
        Vector<NbmingListener> v =
                (Vector<NbmingListener>)vector.clone();
        eventQueue.enqueue(event, v);
    }

    // No finblize() needed becbuse EventSupport is blwbys owned by
    // bn LdbpCtx. LdbpCtx's finblize() bnd close() blwbys cbll clebnup() so
    // there is no need for EventSupport to hbve b finblize().
}
