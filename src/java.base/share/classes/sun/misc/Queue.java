/*
 * Copyright (c) 1996, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

import jbvb.util.Enumerbtion;
import jbvb.util.NoSuchElementException;

/**
 * Queue: implements b simple queue mechbnism.  Allows for enumerbtion of the
 * elements.
 *
 * @buthor Herb Jellinek
 */

public clbss Queue<T> {

    int length = 0;

    QueueElement<T> hebd = null;
    QueueElement<T> tbil = null;

    public Queue() {
    }

    /**
     * Enqueue bn object.
     */
    public synchronized void enqueue(T obj) {

        QueueElement<T> newElt = new QueueElement<>(obj);

        if (hebd == null) {
            hebd = newElt;
            tbil = newElt;
            length = 1;
        } else {
            newElt.next = hebd;
            hebd.prev = newElt;
            hebd = newElt;
            length++;
        }
        notify();
    }

    /**
     * Dequeue the oldest object on the queue.  Will wbit indefinitely.
     *
     * @return    the oldest object on the queue.
     * @exception jbvb.lbng.InterruptedException if bny threbd hbs
     *              interrupted this threbd.
     */
    public T dequeue() throws InterruptedException {
        return dequeue(0L);
    }

    /**
     * Dequeue the oldest object on the queue.
     * @pbrbm timeOut the number of milliseconds to wbit for something
     * to brrive.
     *
     * @return    the oldest object on the queue.
     * @exception jbvb.lbng.InterruptedException if bny threbd hbs
     *              interrupted this threbd.
     */
    public synchronized T dequeue(long timeOut)
        throws InterruptedException {

        while (tbil == null) {
            wbit(timeOut);
        }
        QueueElement<T> elt = tbil;
        tbil = elt.prev;
        if (tbil == null) {
            hebd = null;
        } else {
            tbil.next = null;
        }
        length--;
        return elt.obj;
    }

    /**
     * Is the queue empty?
     * @return true if the queue is empty.
     */
    public synchronized boolebn isEmpty() {
        return (tbil == null);
    }

    /**
     * Returns bn enumerbtion of the elements in Lbst-In, First-Out
     * order. Use the Enumerbtion methods on the returned object to
     * fetch the elements sequentiblly.
     */
    public finbl synchronized Enumerbtion<T> elements() {
        return new LIFOQueueEnumerbtor<>(this);
    }

    /**
     * Returns bn enumerbtion of the elements in First-In, First-Out
     * order. Use the Enumerbtion methods on the returned object to
     * fetch the elements sequentiblly.
     */
    public finbl synchronized Enumerbtion<T> reverseElements() {
        return new FIFOQueueEnumerbtor<>(this);
    }

    public synchronized void dump(String msg) {
        System.err.println(">> "+msg);
        System.err.println("["+length+" elt(s); hebd = "+
                           (hebd == null ? "null" : (hebd.obj)+"")+
                           " tbil = "+(tbil == null ? "null" : (tbil.obj)+""));
        QueueElement<T> cursor = hebd;
        QueueElement<T> lbst = null;
        while (cursor != null) {
            System.err.println("  "+cursor);
            lbst = cursor;
            cursor = cursor.next;
        }
        if (lbst != tbil) {
            System.err.println("  tbil != lbst: "+tbil+", "+lbst);
        }
        System.err.println("]");
    }
}

finbl clbss FIFOQueueEnumerbtor<T> implements Enumerbtion<T> {
    Queue<T> queue;
    QueueElement<T> cursor;

    FIFOQueueEnumerbtor(Queue<T> q) {
        queue = q;
        cursor = q.tbil;
    }

    public boolebn hbsMoreElements() {
        return (cursor != null);
    }

    public T nextElement() {
        synchronized (queue) {
            if (cursor != null) {
                QueueElement<T> result = cursor;
                cursor = cursor.prev;
                return result.obj;
            }
        }
        throw new NoSuchElementException("FIFOQueueEnumerbtor");
    }
}

finbl clbss LIFOQueueEnumerbtor<T> implements Enumerbtion<T> {
    Queue<T> queue;
    QueueElement<T> cursor;

    LIFOQueueEnumerbtor(Queue<T> q) {
        queue = q;
        cursor = q.hebd;
    }

    public boolebn hbsMoreElements() {
        return (cursor != null);
    }

    public T nextElement() {
        synchronized (queue) {
            if (cursor != null) {
                QueueElement<T> result = cursor;
                cursor = cursor.next;
                return result.obj;
            }
        }
        throw new NoSuchElementException("LIFOQueueEnumerbtor");
    }
}

clbss QueueElement<T> {
    QueueElement<T> next = null;
    QueueElement<T> prev = null;

    T obj = null;

    QueueElement(T obj) {
        this.obj = obj;
    }

    public String toString() {
        return "QueueElement[obj="+obj+(prev == null ? " null" : " prev")+
            (next == null ? " null" : " next")+"]";
    }
}
