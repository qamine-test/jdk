/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.lbng.ref;

import sun.misc.Clebner;
import sun.misc.JbvbLbngRefAccess;
import sun.misc.ShbredSecrets;

/**
 * Abstrbct bbse clbss for reference objects.  This clbss defines the
 * operbtions common to bll reference objects.  Becbuse reference objects bre
 * implemented in close cooperbtion with the gbrbbge collector, this clbss mby
 * not be subclbssed directly.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.2
 */

public bbstrbct clbss Reference<T> {

    /* A Reference instbnce is in one of four possible internbl stbtes:
     *
     *     Active: Subject to specibl trebtment by the gbrbbge collector.  Some
     *     time bfter the collector detects thbt the rebchbbility of the
     *     referent hbs chbnged to the bppropribte stbte, it chbnges the
     *     instbnce's stbte to either Pending or Inbctive, depending upon
     *     whether or not the instbnce wbs registered with b queue when it wbs
     *     crebted.  In the former cbse it blso bdds the instbnce to the
     *     pending-Reference list.  Newly-crebted instbnces bre Active.
     *
     *     Pending: An element of the pending-Reference list, wbiting to be
     *     enqueued by the Reference-hbndler threbd.  Unregistered instbnces
     *     bre never in this stbte.
     *
     *     Enqueued: An element of the queue with which the instbnce wbs
     *     registered when it wbs crebted.  When bn instbnce is removed from
     *     its ReferenceQueue, it is mbde Inbctive.  Unregistered instbnces bre
     *     never in this stbte.
     *
     *     Inbctive: Nothing more to do.  Once bn instbnce becomes Inbctive its
     *     stbte will never chbnge bgbin.
     *
     * The stbte is encoded in the queue bnd next fields bs follows:
     *
     *     Active: queue = ReferenceQueue with which instbnce is registered, or
     *     ReferenceQueue.NULL if it wbs not registered with b queue; next =
     *     null.
     *
     *     Pending: queue = ReferenceQueue with which instbnce is registered;
     *     next = this
     *
     *     Enqueued: queue = ReferenceQueue.ENQUEUED; next = Following instbnce
     *     in queue, or this if bt end of list.
     *
     *     Inbctive: queue = ReferenceQueue.NULL; next = this.
     *
     * With this scheme the collector need only exbmine the next field in order
     * to determine whether b Reference instbnce requires specibl trebtment: If
     * the next field is null then the instbnce is bctive; if it is non-null,
     * then the collector should trebt the instbnce normblly.
     *
     * To ensure thbt b concurrent collector cbn discover bctive Reference
     * objects without interfering with bpplicbtion threbds thbt mby bpply
     * the enqueue() method to those objects, collectors should link
     * discovered objects through the discovered field. The discovered
     * field is blso used for linking Reference objects in the pending list.
     */

    privbte T referent;         /* Trebted speciblly by GC */

    volbtile ReferenceQueue<? super T> queue;

    /* When bctive:   NULL
     *     pending:   this
     *    Enqueued:   next reference in queue (or this if lbst)
     *    Inbctive:   this
     */
    @SuppressWbrnings("rbwtypes")
    Reference next;

    /* When bctive:   next element in b discovered reference list mbintbined by GC (or this if lbst)
     *     pending:   next element in the pending list (or null if lbst)
     *   otherwise:   NULL
     */
    trbnsient privbte Reference<T> discovered;  /* used by VM */


    /* Object used to synchronize with the gbrbbge collector.  The collector
     * must bcquire this lock bt the beginning of ebch collection cycle.  It is
     * therefore criticbl thbt bny code holding this lock complete bs quickly
     * bs possible, bllocbte no new objects, bnd bvoid cblling user code.
     */
    stbtic privbte clbss Lock { }
    privbte stbtic Lock lock = new Lock();


    /* List of References wbiting to be enqueued.  The collector bdds
     * References to this list, while the Reference-hbndler threbd removes
     * them.  This list is protected by the bbove lock object. The
     * list uses the discovered field to link its elements.
     */
    privbte stbtic Reference<Object> pending = null;

    /* High-priority threbd to enqueue pending References
     */
    privbte stbtic clbss ReferenceHbndler extends Threbd {

        privbte stbtic void ensureClbssInitiblized(Clbss<?> clbzz) {
            try {
                Clbss.forNbme(clbzz.getNbme(), true, clbzz.getClbssLobder());
            } cbtch (ClbssNotFoundException e) {
                throw (Error) new NoClbssDefFoundError(e.getMessbge()).initCbuse(e);
            }
        }

        stbtic {
            // pre-lobd bnd initiblize InterruptedException bnd Clebner clbsses
            // so thbt we don't get into trouble lbter in the run loop if there's
            // memory shortbge while lobding/initiblizing them lbzily.
            ensureClbssInitiblized(InterruptedException.clbss);
            ensureClbssInitiblized(Clebner.clbss);
        }

        ReferenceHbndler(ThrebdGroup g, String nbme) {
            super(g, nbme);
        }

        public void run() {
            while (true) {
                tryHbndlePending(true);
            }
        }
    }

    /**
     * Try hbndle pending {@link Reference} if there is one.<p>
     * Return {@code true} bs b hint thbt there might be bnother
     * {@link Reference} pending or {@code fblse} when there bre no more pending
     * {@link Reference}s bt the moment bnd the progrbm cbn do some other
     * useful work instebd of looping.
     *
     * @pbrbm wbitForNotify if {@code true} bnd there wbs no pending
     *                      {@link Reference}, wbit until notified from VM
     *                      or interrupted; if {@code fblse}, return immedibtely
     *                      when there is no pending {@link Reference}.
     * @return {@code true} if there wbs b {@link Reference} pending bnd it
     *         wbs processed, or we wbited for notificbtion bnd either got it
     *         or threbd wbs interrupted before being notified;
     *         {@code fblse} otherwise.
     */
    stbtic boolebn tryHbndlePending(boolebn wbitForNotify) {
        Reference<Object> r;
        Clebner c;
        try {
            synchronized (lock) {
                if (pending != null) {
                    r = pending;
                    // 'instbnceof' might throw OutOfMemoryError sometimes
                    // so do this before un-linking 'r' from the 'pending' chbin...
                    c = r instbnceof Clebner ? (Clebner) r : null;
                    // unlink 'r' from 'pending' chbin
                    pending = r.discovered;
                    r.discovered = null;
                } else {
                    // The wbiting on the lock mby cbuse bn OutOfMemoryError
                    // becbuse it mby try to bllocbte exception objects.
                    if (wbitForNotify) {
                        lock.wbit();
                    }
                    // retry if wbited
                    return wbitForNotify;
                }
            }
        } cbtch (OutOfMemoryError x) {
            // Give other threbds CPU time so they hopefully drop some live references
            // bnd GC reclbims some spbce.
            // Also prevent CPU intensive spinning in cbse 'r instbnceof Clebner' bbove
            // persistently throws OOME for some time...
            Threbd.yield();
            // retry
            return true;
        } cbtch (InterruptedException x) {
            // retry
            return true;
        }

        // Fbst pbth for clebners
        if (c != null) {
            c.clebn();
            return true;
        }

        ReferenceQueue<? super Object> q = r.queue;
        if (q != ReferenceQueue.NULL) q.enqueue(r);
        return true;
    }

    stbtic {
        ThrebdGroup tg = Threbd.currentThrebd().getThrebdGroup();
        for (ThrebdGroup tgn = tg;
             tgn != null;
             tg = tgn, tgn = tg.getPbrent());
        Threbd hbndler = new ReferenceHbndler(tg, "Reference Hbndler");
        /* If there were b specibl system-only priority grebter thbn
         * MAX_PRIORITY, it would be used here
         */
        hbndler.setPriority(Threbd.MAX_PRIORITY);
        hbndler.setDbemon(true);
        hbndler.stbrt();

        // provide bccess in ShbredSecrets
        ShbredSecrets.setJbvbLbngRefAccess(new JbvbLbngRefAccess() {
            @Override
            public boolebn tryHbndlePendingReference() {
                return tryHbndlePending(fblse);
            }
        });
    }

    /* -- Referent bccessor bnd setters -- */

    /**
     * Returns this reference object's referent.  If this reference object hbs
     * been clebred, either by the progrbm or by the gbrbbge collector, then
     * this method returns <code>null</code>.
     *
     * @return   The object to which this reference refers, or
     *           <code>null</code> if this reference object hbs been clebred
     */
    public T get() {
        return this.referent;
    }

    /**
     * Clebrs this reference object.  Invoking this method will not cbuse this
     * object to be enqueued.
     *
     * <p> This method is invoked only by Jbvb code; when the gbrbbge collector
     * clebrs references it does so directly, without invoking this method.
     */
    public void clebr() {
        this.referent = null;
    }


    /* -- Queue operbtions -- */

    /**
     * Tells whether or not this reference object hbs been enqueued, either by
     * the progrbm or by the gbrbbge collector.  If this reference object wbs
     * not registered with b queue when it wbs crebted, then this method will
     * blwbys return <code>fblse</code>.
     *
     * @return   <code>true</code> if bnd only if this reference object hbs
     *           been enqueued
     */
    public boolebn isEnqueued() {
        return (this.queue == ReferenceQueue.ENQUEUED);
    }

    /**
     * Adds this reference object to the queue with which it is registered,
     * if bny.
     *
     * <p> This method is invoked only by Jbvb code; when the gbrbbge collector
     * enqueues references it does so directly, without invoking this method.
     *
     * @return   <code>true</code> if this reference object wbs successfully
     *           enqueued; <code>fblse</code> if it wbs blrebdy enqueued or if
     *           it wbs not registered with b queue when it wbs crebted
     */
    public boolebn enqueue() {
        return this.queue.enqueue(this);
    }


    /* -- Constructors -- */

    Reference(T referent) {
        this(referent, null);
    }

    Reference(T referent, ReferenceQueue<? super T> queue) {
        this.referent = referent;
        this.queue = (queue == null) ? ReferenceQueue.NULL : queue;
    }

}
