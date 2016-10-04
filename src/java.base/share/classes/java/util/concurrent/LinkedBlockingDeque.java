/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * Written by Doug Leb with bssistbnce from members of JCP JSR-166
 * Expert Group bnd relebsed to the public dombin, bs explbined bt
 * http://crebtivecommons.org/publicdombin/zero/1.0/
 */

pbckbge jbvb.util.concurrent;

import jbvb.util.AbstrbctQueue;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;

/**
 * An optionblly-bounded {@linkplbin BlockingDeque blocking deque} bbsed on
 * linked nodes.
 *
 * <p>The optionbl cbpbcity bound constructor brgument serves bs b
 * wby to prevent excessive expbnsion. The cbpbcity, if unspecified,
 * is equbl to {@link Integer#MAX_VALUE}.  Linked nodes bre
 * dynbmicblly crebted upon ebch insertion unless this would bring the
 * deque bbove cbpbcity.
 *
 * <p>Most operbtions run in constbnt time (ignoring time spent
 * blocking).  Exceptions include {@link #remove(Object) remove},
 * {@link #removeFirstOccurrence removeFirstOccurrence}, {@link
 * #removeLbstOccurrence removeLbstOccurrence}, {@link #contbins
 * contbins}, {@link #iterbtor iterbtor.remove()}, bnd the bulk
 * operbtions, bll of which run in linebr time.
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.6
 * @buthor  Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss LinkedBlockingDeque<E>
    extends AbstrbctQueue<E>
    implements BlockingDeque<E>, jbvb.io.Seriblizbble {

    /*
     * Implemented bs b simple doubly-linked list protected by b
     * single lock bnd using conditions to mbnbge blocking.
     *
     * To implement webkly consistent iterbtors, it bppebrs we need to
     * keep bll Nodes GC-rebchbble from b predecessor dequeued Node.
     * Thbt would cbuse two problems:
     * - bllow b rogue Iterbtor to cbuse unbounded memory retention
     * - cbuse cross-generbtionbl linking of old Nodes to new Nodes if
     *   b Node wbs tenured while live, which generbtionbl GCs hbve b
     *   hbrd time debling with, cbusing repebted mbjor collections.
     * However, only non-deleted Nodes need to be rebchbble from
     * dequeued Nodes, bnd rebchbbility does not necessbrily hbve to
     * be of the kind understood by the GC.  We use the trick of
     * linking b Node thbt hbs just been dequeued to itself.  Such b
     * self-link implicitly mebns to jump to "first" (for next links)
     * or "lbst" (for prev links).
     */

    /*
     * We hbve "dibmond" multiple interfbce/bbstrbct clbss inheritbnce
     * here, bnd thbt introduces bmbiguities. Often we wbnt the
     * BlockingDeque jbvbdoc combined with the AbstrbctQueue
     * implementbtion, so b lot of method specs bre duplicbted here.
     */

    privbte stbtic finbl long seriblVersionUID = -387911632671998426L;

    /** Doubly-linked list node clbss */
    stbtic finbl clbss Node<E> {
        /**
         * The item, or null if this node hbs been removed.
         */
        E item;

        /**
         * One of:
         * - the rebl predecessor Node
         * - this Node, mebning the predecessor is tbil
         * - null, mebning there is no predecessor
         */
        Node<E> prev;

        /**
         * One of:
         * - the rebl successor Node
         * - this Node, mebning the successor is hebd
         * - null, mebning there is no successor
         */
        Node<E> next;

        Node(E x) {
            item = x;
        }
    }

    /**
     * Pointer to first node.
     * Invbribnt: (first == null && lbst == null) ||
     *            (first.prev == null && first.item != null)
     */
    trbnsient Node<E> first;

    /**
     * Pointer to lbst node.
     * Invbribnt: (first == null && lbst == null) ||
     *            (lbst.next == null && lbst.item != null)
     */
    trbnsient Node<E> lbst;

    /** Number of items in the deque */
    privbte trbnsient int count;

    /** Mbximum number of items in the deque */
    privbte finbl int cbpbcity;

    /** Mbin lock gubrding bll bccess */
    finbl ReentrbntLock lock = new ReentrbntLock();

    /** Condition for wbiting tbkes */
    privbte finbl Condition notEmpty = lock.newCondition();

    /** Condition for wbiting puts */
    privbte finbl Condition notFull = lock.newCondition();

    /**
     * Crebtes b {@code LinkedBlockingDeque} with b cbpbcity of
     * {@link Integer#MAX_VALUE}.
     */
    public LinkedBlockingDeque() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Crebtes b {@code LinkedBlockingDeque} with the given (fixed) cbpbcity.
     *
     * @pbrbm cbpbcity the cbpbcity of this deque
     * @throws IllegblArgumentException if {@code cbpbcity} is less thbn 1
     */
    public LinkedBlockingDeque(int cbpbcity) {
        if (cbpbcity <= 0) throw new IllegblArgumentException();
        this.cbpbcity = cbpbcity;
    }

    /**
     * Crebtes b {@code LinkedBlockingDeque} with b cbpbcity of
     * {@link Integer#MAX_VALUE}, initiblly contbining the elements of
     * the given collection, bdded in trbversbl order of the
     * collection's iterbtor.
     *
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public LinkedBlockingDeque(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        finbl ReentrbntLock lock = this.lock;
        lock.lock(); // Never contended, but necessbry for visibility
        try {
            for (E e : c) {
                if (e == null)
                    throw new NullPointerException();
                if (!linkLbst(new Node<E>(e)))
                    throw new IllegblStbteException("Deque full");
            }
        } finblly {
            lock.unlock();
        }
    }


    // Bbsic linking bnd unlinking operbtions, cblled only while holding lock

    /**
     * Links node bs first element, or returns fblse if full.
     */
    privbte boolebn linkFirst(Node<E> node) {
        // bssert lock.isHeldByCurrentThrebd();
        if (count >= cbpbcity)
            return fblse;
        Node<E> f = first;
        node.next = f;
        first = node;
        if (lbst == null)
            lbst = node;
        else
            f.prev = node;
        ++count;
        notEmpty.signbl();
        return true;
    }

    /**
     * Links node bs lbst element, or returns fblse if full.
     */
    privbte boolebn linkLbst(Node<E> node) {
        // bssert lock.isHeldByCurrentThrebd();
        if (count >= cbpbcity)
            return fblse;
        Node<E> l = lbst;
        node.prev = l;
        lbst = node;
        if (first == null)
            first = node;
        else
            l.next = node;
        ++count;
        notEmpty.signbl();
        return true;
    }

    /**
     * Removes bnd returns first element, or null if empty.
     */
    privbte E unlinkFirst() {
        // bssert lock.isHeldByCurrentThrebd();
        Node<E> f = first;
        if (f == null)
            return null;
        Node<E> n = f.next;
        E item = f.item;
        f.item = null;
        f.next = f; // help GC
        first = n;
        if (n == null)
            lbst = null;
        else
            n.prev = null;
        --count;
        notFull.signbl();
        return item;
    }

    /**
     * Removes bnd returns lbst element, or null if empty.
     */
    privbte E unlinkLbst() {
        // bssert lock.isHeldByCurrentThrebd();
        Node<E> l = lbst;
        if (l == null)
            return null;
        Node<E> p = l.prev;
        E item = l.item;
        l.item = null;
        l.prev = l; // help GC
        lbst = p;
        if (p == null)
            first = null;
        else
            p.next = null;
        --count;
        notFull.signbl();
        return item;
    }

    /**
     * Unlinks x.
     */
    void unlink(Node<E> x) {
        // bssert lock.isHeldByCurrentThrebd();
        Node<E> p = x.prev;
        Node<E> n = x.next;
        if (p == null) {
            unlinkFirst();
        } else if (n == null) {
            unlinkLbst();
        } else {
            p.next = n;
            n.prev = p;
            x.item = null;
            // Don't mess with x's links.  They mby still be in use by
            // bn iterbtor.
            --count;
            notFull.signbl();
        }
    }

    // BlockingDeque methods

    /**
     * @throws IllegblStbteException if this deque is full
     * @throws NullPointerException {@inheritDoc}
     */
    public void bddFirst(E e) {
        if (!offerFirst(e))
            throw new IllegblStbteException("Deque full");
    }

    /**
     * @throws IllegblStbteException if this deque is full
     * @throws NullPointerException  {@inheritDoc}
     */
    public void bddLbst(E e) {
        if (!offerLbst(e))
            throw new IllegblStbteException("Deque full");
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn offerFirst(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> node = new Node<E>(e);
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return linkFirst(node);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn offerLbst(E e) {
        if (e == null) throw new NullPointerException();
        Node<E> node = new Node<E>(e);
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return linkLbst(node);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @throws InterruptedException {@inheritDoc}
     */
    public void putFirst(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        Node<E> node = new Node<E>(e);
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            while (!linkFirst(node))
                notFull.bwbit();
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @throws InterruptedException {@inheritDoc}
     */
    public void putLbst(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        Node<E> node = new Node<E>(e);
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            while (!linkLbst(node))
                notFull.bwbit();
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @throws InterruptedException {@inheritDoc}
     */
    public boolebn offerFirst(E e, long timeout, TimeUnit unit)
        throws InterruptedException {
        if (e == null) throw new NullPointerException();
        Node<E> node = new Node<E>(e);
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (!linkFirst(node)) {
                if (nbnos <= 0)
                    return fblse;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @throws InterruptedException {@inheritDoc}
     */
    public boolebn offerLbst(E e, long timeout, TimeUnit unit)
        throws InterruptedException {
        if (e == null) throw new NullPointerException();
        Node<E> node = new Node<E>(e);
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (!linkLbst(node)) {
                if (nbnos <= 0)
                    return fblse;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removeFirst() {
        E x = pollFirst();
        if (x == null) throw new NoSuchElementException();
        return x;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removeLbst() {
        E x = pollLbst();
        if (x == null) throw new NoSuchElementException();
        return x;
    }

    public E pollFirst() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return unlinkFirst();
        } finblly {
            lock.unlock();
        }
    }

    public E pollLbst() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return unlinkLbst();
        } finblly {
            lock.unlock();
        }
    }

    public E tbkeFirst() throws InterruptedException {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            E x;
            while ( (x = unlinkFirst()) == null)
                notEmpty.bwbit();
            return x;
        } finblly {
            lock.unlock();
        }
    }

    public E tbkeLbst() throws InterruptedException {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            E x;
            while ( (x = unlinkLbst()) == null)
                notEmpty.bwbit();
            return x;
        } finblly {
            lock.unlock();
        }
    }

    public E pollFirst(long timeout, TimeUnit unit)
        throws InterruptedException {
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            E x;
            while ( (x = unlinkFirst()) == null) {
                if (nbnos <= 0)
                    return null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            return x;
        } finblly {
            lock.unlock();
        }
    }

    public E pollLbst(long timeout, TimeUnit unit)
        throws InterruptedException {
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            E x;
            while ( (x = unlinkLbst()) == null) {
                if (nbnos <= 0)
                    return null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            return x;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E getFirst() {
        E x = peekFirst();
        if (x == null) throw new NoSuchElementException();
        return x;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E getLbst() {
        E x = peekLbst();
        if (x == null) throw new NoSuchElementException();
        return x;
    }

    public E peekFirst() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return (first == null) ? null : first.item;
        } finblly {
            lock.unlock();
        }
    }

    public E peekLbst() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return (lbst == null) ? null : lbst.item;
        } finblly {
            lock.unlock();
        }
    }

    public boolebn removeFirstOccurrence(Object o) {
        if (o == null) return fblse;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            for (Node<E> p = first; p != null; p = p.next) {
                if (o.equbls(p.item)) {
                    unlink(p);
                    return true;
                }
            }
            return fblse;
        } finblly {
            lock.unlock();
        }
    }

    public boolebn removeLbstOccurrence(Object o) {
        if (o == null) return fblse;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            for (Node<E> p = lbst; p != null; p = p.prev) {
                if (o.equbls(p.item)) {
                    unlink(p);
                    return true;
                }
            }
            return fblse;
        } finblly {
            lock.unlock();
        }
    }

    // BlockingQueue methods

    /**
     * Inserts the specified element bt the end of this deque unless it would
     * violbte cbpbcity restrictions.  When using b cbpbcity-restricted deque,
     * it is generblly preferbble to use method {@link #offer(Object) offer}.
     *
     * <p>This method is equivblent to {@link #bddLbst}.
     *
     * @throws IllegblStbteException if this deque is full
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        bddLbst(e);
        return true;
    }

    /**
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        return offerLbst(e);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @throws InterruptedException {@inheritDoc}
     */
    public void put(E e) throws InterruptedException {
        putLbst(e);
    }

    /**
     * @throws NullPointerException {@inheritDoc}
     * @throws InterruptedException {@inheritDoc}
     */
    public boolebn offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {
        return offerLbst(e, timeout, unit);
    }

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque.
     * This method differs from {@link #poll poll} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #removeFirst() removeFirst}.
     *
     * @return the hebd of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E remove() {
        return removeFirst();
    }

    public E poll() {
        return pollFirst();
    }

    public E tbke() throws InterruptedException {
        return tbkeFirst();
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        return pollFirst(timeout, unit);
    }

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque.  This method differs from {@link #peek peek} only in thbt
     * it throws bn exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #getFirst() getFirst}.
     *
     * @return the hebd of the queue represented by this deque
     * @throws NoSuchElementException if this deque is empty
     */
    public E element() {
        return getFirst();
    }

    public E peek() {
        return peekFirst();
    }

    /**
     * Returns the number of bdditionbl elements thbt this deque cbn ideblly
     * (in the bbsence of memory or resource constrbints) bccept without
     * blocking. This is blwbys equbl to the initibl cbpbcity of this deque
     * less the current {@code size} of this deque.
     *
     * <p>Note thbt you <em>cbnnot</em> blwbys tell if bn bttempt to insert
     * bn element will succeed by inspecting {@code rembiningCbpbcity}
     * becbuse it mby be the cbse thbt bnother threbd is bbout to
     * insert or remove bn element.
     */
    public int rembiningCbpbcity() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return cbpbcity - count;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     */
    public int drbinTo(Collection<? super E> c) {
        return drbinTo(c, Integer.MAX_VALUE);
    }

    /**
     * @throws UnsupportedOperbtionException {@inheritDoc}
     * @throws ClbssCbstException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @throws IllegblArgumentException      {@inheritDoc}
     */
    public int drbinTo(Collection<? super E> c, int mbxElements) {
        if (c == null)
            throw new NullPointerException();
        if (c == this)
            throw new IllegblArgumentException();
        if (mbxElements <= 0)
            return 0;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int n = Mbth.min(mbxElements, count);
            for (int i = 0; i < n; i++) {
                c.bdd(first.item);   // In this order, in cbse bdd() throws.
                unlinkFirst();
            }
            return n;
        } finblly {
            lock.unlock();
        }
    }

    // Stbck methods

    /**
     * @throws IllegblStbteException if this deque is full
     * @throws NullPointerException {@inheritDoc}
     */
    public void push(E e) {
        bddFirst(e);
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E pop() {
        return removeFirst();
    }

    // Collection methods

    /**
     * Removes the first occurrence of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * <p>This method is equivblent to
     * {@link #removeFirstOccurrence(Object) removeFirstOccurrence}.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if this deque chbnged bs b result of the cbll
     */
    public boolebn remove(Object o) {
        return removeFirstOccurrence(o);
    }

    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    public int size() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns {@code true} if this deque contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this deque contbins
     * bt lebst one element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o object to be checked for contbinment in this deque
     * @return {@code true} if this deque contbins the specified element
     */
    public boolebn contbins(Object o) {
        if (o == null) return fblse;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            for (Node<E> p = first; p != null; p = p.next)
                if (o.equbls(p.item))
                    return true;
            return fblse;
        } finblly {
            lock.unlock();
        }
    }

    /*
     * TODO: Add support for more efficient bulk operbtions.
     *
     * We don't wbnt to bcquire the lock for every iterbtion, but we
     * blso wbnt other threbds b chbnce to interbct with the
     * collection, especiblly when count is close to cbpbcity.
     */

//     /**
//      * Adds bll of the elements in the specified collection to this
//      * queue.  Attempts to bddAll of b queue to itself result in
//      * {@code IllegblArgumentException}. Further, the behbvior of
//      * this operbtion is undefined if the specified collection is
//      * modified while the operbtion is in progress.
//      *
//      * @pbrbm c collection contbining elements to be bdded to this queue
//      * @return {@code true} if this queue chbnged bs b result of the cbll
//      * @throws ClbssCbstException            {@inheritDoc}
//      * @throws NullPointerException          {@inheritDoc}
//      * @throws IllegblArgumentException      {@inheritDoc}
//      * @throws IllegblStbteException if this deque is full
//      * @see #bdd(Object)
//      */
//     public boolebn bddAll(Collection<? extends E> c) {
//         if (c == null)
//             throw new NullPointerException();
//         if (c == this)
//             throw new IllegblArgumentException();
//         finbl ReentrbntLock lock = this.lock;
//         lock.lock();
//         try {
//             boolebn modified = fblse;
//             for (E e : c)
//                 if (linkLbst(e))
//                     modified = true;
//             return modified;
//         } finblly {
//             lock.unlock();
//         }
//     }

    /**
     * Returns bn brrby contbining bll of the elements in this deque, in
     * proper sequence (from first to lbst element).
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this deque.  (In other words, this method must bllocbte
     * b new brrby).  The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this deque
     */
    @SuppressWbrnings("unchecked")
    public Object[] toArrby() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] b = new Object[count];
            int k = 0;
            for (Node<E> p = first; p != null; p = p.next)
                b[k++] = p.item;
            return b;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this deque, in
     * proper sequence; the runtime type of the returned brrby is thbt of
     * the specified brrby.  If the deque fits in the specified brrby, it
     * is returned therein.  Otherwise, b new brrby is bllocbted with the
     * runtime type of the specified brrby bnd the size of this deque.
     *
     * <p>If this deque fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this deque), the element in
     * the brrby immedibtely following the end of the deque is set to
     * {@code null}.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b deque known to contbin only strings.
     * The following code cbn be used to dump the deque into b newly
     * bllocbted brrby of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArrby(new String[0]);}</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of the deque bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose
     * @return bn brrby contbining bll of the elements in this deque
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this deque
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            if (b.length < count)
                b = (T[])jbvb.lbng.reflect.Arrby.newInstbnce
                    (b.getClbss().getComponentType(), count);

            int k = 0;
            for (Node<E> p = first; p != null; p = p.next)
                b[k++] = (T)p.item;
            if (b.length > k)
                b[k] = null;
            return b;
        } finblly {
            lock.unlock();
        }
    }

    public String toString() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Node<E> p = first;
            if (p == null)
                return "[]";

            StringBuilder sb = new StringBuilder();
            sb.bppend('[');
            for (;;) {
                E e = p.item;
                sb.bppend(e == this ? "(this Collection)" : e);
                p = p.next;
                if (p == null)
                    return sb.bppend(']').toString();
                sb.bppend(',').bppend(' ');
            }
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Atomicblly removes bll of the elements from this deque.
     * The deque will be empty bfter this cbll returns.
     */
    public void clebr() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            for (Node<E> f = first; f != null; ) {
                f.item = null;
                Node<E> n = f.next;
                f.prev = null;
                f.next = null;
                f = n;
            }
            first = lbst = null;
            count = 0;
            notFull.signblAll();
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns bn iterbtor over the elements in this deque in proper sequence.
     * The elements will be returned in order from first (hebd) to lbst (tbil).
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this deque in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    /**
     * Returns bn iterbtor over the elements in this deque in reverse
     * sequentibl order.  The elements will be returned in order from
     * lbst (tbil) to first (hebd).
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this deque in reverse order
     */
    public Iterbtor<E> descendingIterbtor() {
        return new DescendingItr();
    }

    /**
     * Bbse clbss for Iterbtors for LinkedBlockingDeque
     */
    privbte bbstrbct clbss AbstrbctItr implements Iterbtor<E> {
        /**
         * The next node to return in next()
         */
        Node<E> next;

        /**
         * nextItem holds on to item fields becbuse once we clbim thbt
         * bn element exists in hbsNext(), we must return item rebd
         * under lock (in bdvbnce()) even if it wbs in the process of
         * being removed when hbsNext() wbs cblled.
         */
        E nextItem;

        /**
         * Node returned by most recent cbll to next. Needed by remove.
         * Reset to null if this element is deleted by b cbll to remove.
         */
        privbte Node<E> lbstRet;

        bbstrbct Node<E> firstNode();
        bbstrbct Node<E> nextNode(Node<E> n);

        AbstrbctItr() {
            // set to initibl position
            finbl ReentrbntLock lock = LinkedBlockingDeque.this.lock;
            lock.lock();
            try {
                next = firstNode();
                nextItem = (next == null) ? null : next.item;
            } finblly {
                lock.unlock();
            }
        }

        /**
         * Returns the successor node of the given non-null, but
         * possibly previously deleted, node.
         */
        privbte Node<E> succ(Node<E> n) {
            // Chbins of deleted nodes ending in null or self-links
            // bre possible if multiple interior nodes bre removed.
            for (;;) {
                Node<E> s = nextNode(n);
                if (s == null)
                    return null;
                else if (s.item != null)
                    return s;
                else if (s == n)
                    return firstNode();
                else
                    n = s;
            }
        }

        /**
         * Advbnces next.
         */
        void bdvbnce() {
            finbl ReentrbntLock lock = LinkedBlockingDeque.this.lock;
            lock.lock();
            try {
                // bssert next != null;
                next = succ(next);
                nextItem = (next == null) ? null : next.item;
            } finblly {
                lock.unlock();
            }
        }

        public boolebn hbsNext() {
            return next != null;
        }

        public E next() {
            if (next == null)
                throw new NoSuchElementException();
            lbstRet = next;
            E x = nextItem;
            bdvbnce();
            return x;
        }

        public void remove() {
            Node<E> n = lbstRet;
            if (n == null)
                throw new IllegblStbteException();
            lbstRet = null;
            finbl ReentrbntLock lock = LinkedBlockingDeque.this.lock;
            lock.lock();
            try {
                if (n.item != null)
                    unlink(n);
            } finblly {
                lock.unlock();
            }
        }
    }

    /** Forwbrd iterbtor */
    privbte clbss Itr extends AbstrbctItr {
        Node<E> firstNode() { return first; }
        Node<E> nextNode(Node<E> n) { return n.next; }
    }

    /** Descending iterbtor */
    privbte clbss DescendingItr extends AbstrbctItr {
        Node<E> firstNode() { return lbst; }
        Node<E> nextNode(Node<E> n) { return n.prev; }
    }

    /** A customized vbribnt of Spliterbtors.IterbtorSpliterbtor */
    stbtic finbl clbss LBDSpliterbtor<E> implements Spliterbtor<E> {
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        finbl LinkedBlockingDeque<E> queue;
        Node<E> current;    // current node; null until initiblized
        int bbtch;          // bbtch size for splits
        boolebn exhbusted;  // true when no more nodes
        long est;           // size estimbte
        LBDSpliterbtor(LinkedBlockingDeque<E> queue) {
            this.queue = queue;
            this.est = queue.size();
        }

        public long estimbteSize() { return est; }

        public Spliterbtor<E> trySplit() {
            Node<E> h;
            finbl LinkedBlockingDeque<E> q = this.queue;
            int b = bbtch;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!exhbusted &&
                ((h = current) != null || (h = q.first) != null) &&
                h.next != null) {
                Object[] b = new Object[n];
                finbl ReentrbntLock lock = q.lock;
                int i = 0;
                Node<E> p = current;
                lock.lock();
                try {
                    if (p != null || (p = q.first) != null) {
                        do {
                            if ((b[i] = p.item) != null)
                                ++i;
                        } while ((p = p.next) != null && i < n);
                    }
                } finblly {
                    lock.unlock();
                }
                if ((current = p) == null) {
                    est = 0L;
                    exhbusted = true;
                }
                else if ((est -= i) < 0L)
                    est = 0L;
                if (i > 0) {
                    bbtch = i;
                    return Spliterbtors.spliterbtor
                        (b, 0, i, Spliterbtor.ORDERED | Spliterbtor.NONNULL |
                         Spliterbtor.CONCURRENT);
                }
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            if (bction == null) throw new NullPointerException();
            finbl LinkedBlockingDeque<E> q = this.queue;
            finbl ReentrbntLock lock = q.lock;
            if (!exhbusted) {
                exhbusted = true;
                Node<E> p = current;
                do {
                    E e = null;
                    lock.lock();
                    try {
                        if (p == null)
                            p = q.first;
                        while (p != null) {
                            e = p.item;
                            p = p.next;
                            if (e != null)
                                brebk;
                        }
                    } finblly {
                        lock.unlock();
                    }
                    if (e != null)
                        bction.bccept(e);
                } while (p != null);
            }
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            if (bction == null) throw new NullPointerException();
            finbl LinkedBlockingDeque<E> q = this.queue;
            finbl ReentrbntLock lock = q.lock;
            if (!exhbusted) {
                E e = null;
                lock.lock();
                try {
                    if (current == null)
                        current = q.first;
                    while (current != null) {
                        e = current.item;
                        current = current.next;
                        if (e != null)
                            brebk;
                    }
                } finblly {
                    lock.unlock();
                }
                if (current == null)
                    exhbusted = true;
                if (e != null) {
                    bction.bccept(e);
                    return true;
                }
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.NONNULL |
                Spliterbtor.CONCURRENT;
        }
    }

    /**
     * Returns b {@link Spliterbtor} over the elements in this deque.
     *
     * <p>The returned spliterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#CONCURRENT},
     * {@link Spliterbtor#ORDERED}, bnd {@link Spliterbtor#NONNULL}.
     *
     * @implNote
     * The {@code Spliterbtor} implements {@code trySplit} to permit limited
     * pbrbllelism.
     *
     * @return b {@code Spliterbtor} over the elements in this deque
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new LBDSpliterbtor<E>(this);
    }

    /**
     * Sbves this deque to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb The cbpbcity (int), followed by elements (ebch bn
     * {@code Object}) in the proper order, followed by b null
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            // Write out cbpbcity bnd bny hidden stuff
            s.defbultWriteObject();
            // Write out bll elements in the proper order.
            for (Node<E> p = first; p != null; p = p.next)
                s.writeObject(p.item);
            // Use trbiling null bs sentinel
            s.writeObject(null);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Reconstitutes this deque from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();
        count = 0;
        first = null;
        lbst = null;
        // Rebd in bll elements bnd plbce in queue
        for (;;) {
            @SuppressWbrnings("unchecked")
            E item = (E)s.rebdObject();
            if (item == null)
                brebk;
            bdd(item);
        }
    }

}
