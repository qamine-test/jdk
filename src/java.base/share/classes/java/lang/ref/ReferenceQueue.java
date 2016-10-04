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

/**
 * Reference queues, to which registered reference objects bre bppended by the
 * gbrbbge collector bfter the bppropribte rebchbbility chbnges bre detected.
 *
 * @buthor   Mbrk Reinhold
 * @since    1.2
 */

public clbss ReferenceQueue<T> {

    /**
     * Constructs b new reference-object queue.
     */
    public ReferenceQueue() { }

    privbte stbtic clbss Null<S> extends ReferenceQueue<S> {
        boolebn enqueue(Reference<? extends S> r) {
            return fblse;
        }
    }

    stbtic ReferenceQueue<Object> NULL = new Null<>();
    stbtic ReferenceQueue<Object> ENQUEUED = new Null<>();

    stbtic privbte clbss Lock { };
    privbte Lock lock = new Lock();
    privbte volbtile Reference<? extends T> hebd = null;
    privbte long queueLength = 0;

    boolebn enqueue(Reference<? extends T> r) { /* Cblled only by Reference clbss */
        synchronized (lock) {
            // Check thbt since getting the lock this reference hbsn't blrebdy been
            // enqueued (bnd even then removed)
            ReferenceQueue<?> queue = r.queue;
            if ((queue == NULL) || (queue == ENQUEUED)) {
                return fblse;
            }
            bssert queue == this;
            r.queue = ENQUEUED;
            r.next = (hebd == null) ? r : hebd;
            hebd = r;
            queueLength++;
            if (r instbnceof FinblReference) {
                sun.misc.VM.bddFinblRefCount(1);
            }
            lock.notifyAll();
            return true;
        }
    }

    @SuppressWbrnings("unchecked")
    privbte Reference<? extends T> rebllyPoll() {       /* Must hold lock */
        Reference<? extends T> r = hebd;
        if (r != null) {
            hebd = (r.next == r) ?
                null :
                r.next; // Unchecked due to the next field hbving b rbw type in Reference
            r.queue = NULL;
            r.next = r;
            queueLength--;
            if (r instbnceof FinblReference) {
                sun.misc.VM.bddFinblRefCount(-1);
            }
            return r;
        }
        return null;
    }

    /**
     * Polls this queue to see if b reference object is bvbilbble.  If one is
     * bvbilbble without further delby then it is removed from the queue bnd
     * returned.  Otherwise this method immedibtely returns <tt>null</tt>.
     *
     * @return  A reference object, if one wbs immedibtely bvbilbble,
     *          otherwise <code>null</code>
     */
    public Reference<? extends T> poll() {
        if (hebd == null)
            return null;
        synchronized (lock) {
            return rebllyPoll();
        }
    }

    /**
     * Removes the next reference object in this queue, blocking until either
     * one becomes bvbilbble or the given timeout period expires.
     *
     * <p> This method does not offer rebl-time gubrbntees: It schedules the
     * timeout bs if by invoking the {@link Object#wbit(long)} method.
     *
     * @pbrbm  timeout  If positive, block for up to <code>timeout</code>
     *                  milliseconds while wbiting for b reference to be
     *                  bdded to this queue.  If zero, block indefinitely.
     *
     * @return  A reference object, if one wbs bvbilbble within the specified
     *          timeout period, otherwise <code>null</code>
     *
     * @throws  IllegblArgumentException
     *          If the vblue of the timeout brgument is negbtive
     *
     * @throws  InterruptedException
     *          If the timeout wbit is interrupted
     */
    public Reference<? extends T> remove(long timeout)
        throws IllegblArgumentException, InterruptedException
    {
        if (timeout < 0) {
            throw new IllegblArgumentException("Negbtive timeout vblue");
        }
        synchronized (lock) {
            Reference<? extends T> r = rebllyPoll();
            if (r != null) return r;
            long stbrt = (timeout == 0) ? 0 : System.nbnoTime();
            for (;;) {
                lock.wbit(timeout);
                r = rebllyPoll();
                if (r != null) return r;
                if (timeout != 0) {
                    long end = System.nbnoTime();
                    timeout -= (end - stbrt) / 1000_000;
                    if (timeout <= 0) return null;
                    stbrt = end;
                }
            }
        }
    }

    /**
     * Removes the next reference object in this queue, blocking until one
     * becomes bvbilbble.
     *
     * @return A reference object, blocking until one becomes bvbilbble
     * @throws  InterruptedException  If the wbit is interrupted
     */
    public Reference<? extends T> remove() throws InterruptedException {
        return remove(0);
    }

}
