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

import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.AbstrbctQueue;
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Spliterbtor;
import jbvb.util.Spliterbtors;
import jbvb.util.function.Consumer;

/**
 * An optionblly-bounded {@linkplbin BlockingQueue blocking queue} bbsed on
 * linked nodes.
 * This queue orders elements FIFO (first-in-first-out).
 * The <em>hebd</em> of the queue is thbt element thbt hbs been on the
 * queue the longest time.
 * The <em>tbil</em> of the queue is thbt element thbt hbs been on the
 * queue the shortest time. New elements
 * bre inserted bt the tbil of the queue, bnd the queue retrievbl
 * operbtions obtbin elements bt the hebd of the queue.
 * Linked queues typicblly hbve higher throughput thbn brrby-bbsed queues but
 * less predictbble performbnce in most concurrent bpplicbtions.
 *
 * <p>The optionbl cbpbcity bound constructor brgument serves bs b
 * wby to prevent excessive queue expbnsion. The cbpbcity, if unspecified,
 * is equbl to {@link Integer#MAX_VALUE}.  Linked nodes bre
 * dynbmicblly crebted upon ebch insertion unless this would bring the
 * queue bbove cbpbcity.
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss LinkedBlockingQueue<E> extends AbstrbctQueue<E>
        implements BlockingQueue<E>, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = -6903933977591709194L;

    /*
     * A vbribnt of the "two lock queue" blgorithm.  The putLock gbtes
     * entry to put (bnd offer), bnd hbs bn bssocibted condition for
     * wbiting puts.  Similbrly for the tbkeLock.  The "count" field
     * thbt they both rely on is mbintbined bs bn btomic to bvoid
     * needing to get both locks in most cbses. Also, to minimize need
     * for puts to get tbkeLock bnd vice-versb, cbscbding notifies bre
     * used. When b put notices thbt it hbs enbbled bt lebst one tbke,
     * it signbls tbker. Thbt tbker in turn signbls others if more
     * items hbve been entered since the signbl. And symmetricblly for
     * tbkes signblling puts. Operbtions such bs remove(Object) bnd
     * iterbtors bcquire both locks.
     *
     * Visibility between writers bnd rebders is provided bs follows:
     *
     * Whenever bn element is enqueued, the putLock is bcquired bnd
     * count updbted.  A subsequent rebder gubrbntees visibility to the
     * enqueued Node by either bcquiring the putLock (vib fullyLock)
     * or by bcquiring the tbkeLock, bnd then rebding n = count.get();
     * this gives visibility to the first n items.
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
     * self-link implicitly mebns to bdvbnce to hebd.next.
     */

    /**
     * Linked list node clbss
     */
    stbtic clbss Node<E> {
        E item;

        /**
         * One of:
         * - the rebl successor Node
         * - this Node, mebning the successor is hebd.next
         * - null, mebning there is no successor (this is the lbst node)
         */
        Node<E> next;

        Node(E x) { item = x; }
    }

    /** The cbpbcity bound, or Integer.MAX_VALUE if none */
    privbte finbl int cbpbcity;

    /** Current number of elements */
    privbte finbl AtomicInteger count = new AtomicInteger();

    /**
     * Hebd of linked list.
     * Invbribnt: hebd.item == null
     */
    trbnsient Node<E> hebd;

    /**
     * Tbil of linked list.
     * Invbribnt: lbst.next == null
     */
    privbte trbnsient Node<E> lbst;

    /** Lock held by tbke, poll, etc */
    privbte finbl ReentrbntLock tbkeLock = new ReentrbntLock();

    /** Wbit queue for wbiting tbkes */
    privbte finbl Condition notEmpty = tbkeLock.newCondition();

    /** Lock held by put, offer, etc */
    privbte finbl ReentrbntLock putLock = new ReentrbntLock();

    /** Wbit queue for wbiting puts */
    privbte finbl Condition notFull = putLock.newCondition();

    /**
     * Signbls b wbiting tbke. Cblled only from put/offer (which do not
     * otherwise ordinbrily lock tbkeLock.)
     */
    privbte void signblNotEmpty() {
        finbl ReentrbntLock tbkeLock = this.tbkeLock;
        tbkeLock.lock();
        try {
            notEmpty.signbl();
        } finblly {
            tbkeLock.unlock();
        }
    }

    /**
     * Signbls b wbiting put. Cblled only from tbke/poll.
     */
    privbte void signblNotFull() {
        finbl ReentrbntLock putLock = this.putLock;
        putLock.lock();
        try {
            notFull.signbl();
        } finblly {
            putLock.unlock();
        }
    }

    /**
     * Links node bt end of queue.
     *
     * @pbrbm node the node
     */
    privbte void enqueue(Node<E> node) {
        // bssert putLock.isHeldByCurrentThrebd();
        // bssert lbst.next == null;
        lbst = lbst.next = node;
    }

    /**
     * Removes b node from hebd of queue.
     *
     * @return the node
     */
    privbte E dequeue() {
        // bssert tbkeLock.isHeldByCurrentThrebd();
        // bssert hebd.item == null;
        Node<E> h = hebd;
        Node<E> first = h.next;
        h.next = h; // help GC
        hebd = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    /**
     * Locks to prevent both puts bnd tbkes.
     */
    void fullyLock() {
        putLock.lock();
        tbkeLock.lock();
    }

    /**
     * Unlocks to bllow both puts bnd tbkes.
     */
    void fullyUnlock() {
        tbkeLock.unlock();
        putLock.unlock();
    }

//     /**
//      * Tells whether both locks bre held by current threbd.
//      */
//     boolebn isFullyLocked() {
//         return (putLock.isHeldByCurrentThrebd() &&
//                 tbkeLock.isHeldByCurrentThrebd());
//     }

    /**
     * Crebtes b {@code LinkedBlockingQueue} with b cbpbcity of
     * {@link Integer#MAX_VALUE}.
     */
    public LinkedBlockingQueue() {
        this(Integer.MAX_VALUE);
    }

    /**
     * Crebtes b {@code LinkedBlockingQueue} with the given (fixed) cbpbcity.
     *
     * @pbrbm cbpbcity the cbpbcity of this queue
     * @throws IllegblArgumentException if {@code cbpbcity} is not grebter
     *         thbn zero
     */
    public LinkedBlockingQueue(int cbpbcity) {
        if (cbpbcity <= 0) throw new IllegblArgumentException();
        this.cbpbcity = cbpbcity;
        lbst = hebd = new Node<E>(null);
    }

    /**
     * Crebtes b {@code LinkedBlockingQueue} with b cbpbcity of
     * {@link Integer#MAX_VALUE}, initiblly contbining the elements of the
     * given collection,
     * bdded in trbversbl order of the collection's iterbtor.
     *
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public LinkedBlockingQueue(Collection<? extends E> c) {
        this(Integer.MAX_VALUE);
        finbl ReentrbntLock putLock = this.putLock;
        putLock.lock(); // Never contended, but necessbry for visibility
        try {
            int n = 0;
            for (E e : c) {
                if (e == null)
                    throw new NullPointerException();
                if (n == cbpbcity)
                    throw new IllegblStbteException("Queue full");
                enqueue(new Node<E>(e));
                ++n;
            }
            count.set(n);
        } finblly {
            putLock.unlock();
        }
    }

    // this doc comment is overridden to remove the reference to collections
    // grebter in size thbn Integer.MAX_VALUE
    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in this queue
     */
    public int size() {
        return count.get();
    }

    // this doc comment is b modified copy of the inherited doc comment,
    // without the reference to unlimited queues.
    /**
     * Returns the number of bdditionbl elements thbt this queue cbn ideblly
     * (in the bbsence of memory or resource constrbints) bccept without
     * blocking. This is blwbys equbl to the initibl cbpbcity of this queue
     * less the current {@code size} of this queue.
     *
     * <p>Note thbt you <em>cbnnot</em> blwbys tell if bn bttempt to insert
     * bn element will succeed by inspecting {@code rembiningCbpbcity}
     * becbuse it mby be the cbse thbt bnother threbd is bbout to
     * insert or remove bn element.
     */
    public int rembiningCbpbcity() {
        return cbpbcity - count.get();
    }

    /**
     * Inserts the specified element bt the tbil of this queue, wbiting if
     * necessbry for spbce to become bvbilbble.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public void put(E e) throws InterruptedException {
        if (e == null) throw new NullPointerException();
        // Note: convention in bll put/tbke/etc is to preset locbl vbr
        // holding count negbtive to indicbte fbilure unless set.
        int c = -1;
        Node<E> node = new Node<E>(e);
        finbl ReentrbntLock putLock = this.putLock;
        finbl AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            /*
             * Note thbt count is used in wbit gubrd even though it is
             * not protected by lock. This works becbuse count cbn
             * only decrebse bt this point (bll other puts bre shut
             * out by lock), bnd we (or some other wbiting put) bre
             * signblled if it ever chbnges from cbpbcity. Similbrly
             * for bll other uses of count in other wbit gubrds.
             */
            while (count.get() == cbpbcity) {
                notFull.bwbit();
            }
            enqueue(node);
            c = count.getAndIncrement();
            if (c + 1 < cbpbcity)
                notFull.signbl();
        } finblly {
            putLock.unlock();
        }
        if (c == 0)
            signblNotEmpty();
    }

    /**
     * Inserts the specified element bt the tbil of this queue, wbiting if
     * necessbry up to the specified wbit time for spbce to become bvbilbble.
     *
     * @return {@code true} if successful, or {@code fblse} if
     *         the specified wbiting time elbpses before spbce is bvbilbble
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {

        if (e == null) throw new NullPointerException();
        long nbnos = unit.toNbnos(timeout);
        int c = -1;
        finbl ReentrbntLock putLock = this.putLock;
        finbl AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            while (count.get() == cbpbcity) {
                if (nbnos <= 0)
                    return fblse;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            enqueue(new Node<E>(e));
            c = count.getAndIncrement();
            if (c + 1 < cbpbcity)
                notFull.signbl();
        } finblly {
            putLock.unlock();
        }
        if (c == 0)
            signblNotEmpty();
        return true;
    }

    /**
     * Inserts the specified element bt the tbil of this queue if it is
     * possible to do so immedibtely without exceeding the queue's cbpbcity,
     * returning {@code true} upon success bnd {@code fblse} if this queue
     * is full.
     * When using b cbpbcity-restricted queue, this method is generblly
     * preferbble to method {@link BlockingQueue#bdd bdd}, which cbn fbil to
     * insert bn element only by throwing bn exception.
     *
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        if (e == null) throw new NullPointerException();
        finbl AtomicInteger count = this.count;
        if (count.get() == cbpbcity)
            return fblse;
        int c = -1;
        Node<E> node = new Node<E>(e);
        finbl ReentrbntLock putLock = this.putLock;
        putLock.lock();
        try {
            if (count.get() < cbpbcity) {
                enqueue(node);
                c = count.getAndIncrement();
                if (c + 1 < cbpbcity)
                    notFull.signbl();
            }
        } finblly {
            putLock.unlock();
        }
        if (c == 0)
            signblNotEmpty();
        return c >= 0;
    }

    public E tbke() throws InterruptedException {
        E x;
        int c = -1;
        finbl AtomicInteger count = this.count;
        finbl ReentrbntLock tbkeLock = this.tbkeLock;
        tbkeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                notEmpty.bwbit();
            }
            x = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signbl();
        } finblly {
            tbkeLock.unlock();
        }
        if (c == cbpbcity)
            signblNotFull();
        return x;
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        E x = null;
        int c = -1;
        long nbnos = unit.toNbnos(timeout);
        finbl AtomicInteger count = this.count;
        finbl ReentrbntLock tbkeLock = this.tbkeLock;
        tbkeLock.lockInterruptibly();
        try {
            while (count.get() == 0) {
                if (nbnos <= 0)
                    return null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            x = dequeue();
            c = count.getAndDecrement();
            if (c > 1)
                notEmpty.signbl();
        } finblly {
            tbkeLock.unlock();
        }
        if (c == cbpbcity)
            signblNotFull();
        return x;
    }

    public E poll() {
        finbl AtomicInteger count = this.count;
        if (count.get() == 0)
            return null;
        E x = null;
        int c = -1;
        finbl ReentrbntLock tbkeLock = this.tbkeLock;
        tbkeLock.lock();
        try {
            if (count.get() > 0) {
                x = dequeue();
                c = count.getAndDecrement();
                if (c > 1)
                    notEmpty.signbl();
            }
        } finblly {
            tbkeLock.unlock();
        }
        if (c == cbpbcity)
            signblNotFull();
        return x;
    }

    public E peek() {
        if (count.get() == 0)
            return null;
        finbl ReentrbntLock tbkeLock = this.tbkeLock;
        tbkeLock.lock();
        try {
            Node<E> first = hebd.next;
            if (first == null)
                return null;
            else
                return first.item;
        } finblly {
            tbkeLock.unlock();
        }
    }

    /**
     * Unlinks interior Node p with predecessor trbil.
     */
    void unlink(Node<E> p, Node<E> trbil) {
        // bssert isFullyLocked();
        // p.next is not chbnged, to bllow iterbtors thbt bre
        // trbversing p to mbintbin their webk-consistency gubrbntee.
        p.item = null;
        trbil.next = p.next;
        if (lbst == p)
            lbst = trbil;
        if (count.getAndDecrement() == cbpbcity)
            notFull.signbl();
    }

    /**
     * Removes b single instbnce of the specified element from this queue,
     * if it is present.  More formblly, removes bn element {@code e} such
     * thbt {@code o.equbls(e)}, if this queue contbins one or more such
     * elements.
     * Returns {@code true} if this queue contbined the specified element
     * (or equivblently, if this queue chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this queue, if present
     * @return {@code true} if this queue chbnged bs b result of the cbll
     */
    public boolebn remove(Object o) {
        if (o == null) return fblse;
        fullyLock();
        try {
            for (Node<E> trbil = hebd, p = trbil.next;
                 p != null;
                 trbil = p, p = p.next) {
                if (o.equbls(p.item)) {
                    unlink(p, trbil);
                    return true;
                }
            }
            return fblse;
        } finblly {
            fullyUnlock();
        }
    }

    /**
     * Returns {@code true} if this queue contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this queue contbins
     * bt lebst one element {@code e} such thbt {@code o.equbls(e)}.
     *
     * @pbrbm o object to be checked for contbinment in this queue
     * @return {@code true} if this queue contbins the specified element
     */
    public boolebn contbins(Object o) {
        if (o == null) return fblse;
        fullyLock();
        try {
            for (Node<E> p = hebd.next; p != null; p = p.next)
                if (o.equbls(p.item))
                    return true;
            return fblse;
        } finblly {
            fullyUnlock();
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue, in
     * proper sequence.
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this queue.  (In other words, this method must bllocbte
     * b new brrby).  The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this queue
     */
    public Object[] toArrby() {
        fullyLock();
        try {
            int size = count.get();
            Object[] b = new Object[size];
            int k = 0;
            for (Node<E> p = hebd.next; p != null; p = p.next)
                b[k++] = p.item;
            return b;
        } finblly {
            fullyUnlock();
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue, in
     * proper sequence; the runtime type of the returned brrby is thbt of
     * the specified brrby.  If the queue fits in the specified brrby, it
     * is returned therein.  Otherwise, b new brrby is bllocbted with the
     * runtime type of the specified brrby bnd the size of this queue.
     *
     * <p>If this queue fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn this queue), the element in
     * the brrby immedibtely following the end of the queue is set to
     * {@code null}.
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b queue known to contbin only strings.
     * The following code cbn be used to dump the queue into b newly
     * bllocbted brrby of {@code String}:
     *
     *  <pre> {@code String[] y = x.toArrby(new String[0]);}</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of the queue bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose
     * @return bn brrby contbining bll of the elements in this queue
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this queue
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        fullyLock();
        try {
            int size = count.get();
            if (b.length < size)
                b = (T[])jbvb.lbng.reflect.Arrby.newInstbnce
                    (b.getClbss().getComponentType(), size);

            int k = 0;
            for (Node<E> p = hebd.next; p != null; p = p.next)
                b[k++] = (T)p.item;
            if (b.length > k)
                b[k] = null;
            return b;
        } finblly {
            fullyUnlock();
        }
    }

    public String toString() {
        fullyLock();
        try {
            Node<E> p = hebd.next;
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
            fullyUnlock();
        }
    }

    /**
     * Atomicblly removes bll of the elements from this queue.
     * The queue will be empty bfter this cbll returns.
     */
    public void clebr() {
        fullyLock();
        try {
            for (Node<E> p, h = hebd; (p = h.next) != null; h = p) {
                h.next = h;
                p.item = null;
            }
            hebd = lbst;
            // bssert hebd.item == null && hebd.next == null;
            if (count.getAndSet(0) == cbpbcity)
                notFull.signbl();
        } finblly {
            fullyUnlock();
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
        boolebn signblNotFull = fblse;
        finbl ReentrbntLock tbkeLock = this.tbkeLock;
        tbkeLock.lock();
        try {
            int n = Mbth.min(mbxElements, count.get());
            // count.get provides visibility to first n Nodes
            Node<E> h = hebd;
            int i = 0;
            try {
                while (i < n) {
                    Node<E> p = h.next;
                    c.bdd(p.item);
                    p.item = null;
                    h.next = h;
                    h = p;
                    ++i;
                }
                return n;
            } finblly {
                // Restore invbribnts even if c.bdd() threw
                if (i > 0) {
                    // bssert h.item == null;
                    hebd = h;
                    signblNotFull = (count.getAndAdd(-i) == cbpbcity);
                }
            }
        } finblly {
            tbkeLock.unlock();
            if (signblNotFull)
                signblNotFull();
        }
    }

    /**
     * Returns bn iterbtor over the elements in this queue in proper sequence.
     * The elements will be returned in order from first (hebd) to lbst (tbil).
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this queue in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    privbte clbss Itr implements Iterbtor<E> {
        /*
         * Bbsic webkly-consistent iterbtor.  At bll times hold the next
         * item to hbnd out so thbt if hbsNext() reports true, we will
         * still hbve it to return even if lost rbce with b tbke etc.
         */

        privbte Node<E> current;
        privbte Node<E> lbstRet;
        privbte E currentElement;

        Itr() {
            fullyLock();
            try {
                current = hebd.next;
                if (current != null)
                    currentElement = current.item;
            } finblly {
                fullyUnlock();
            }
        }

        public boolebn hbsNext() {
            return current != null;
        }

        /**
         * Returns the next live successor of p, or null if no such.
         *
         * Unlike other trbversbl methods, iterbtors need to hbndle both:
         * - dequeued nodes (p.next == p)
         * - (possibly multiple) interior removed nodes (p.item == null)
         */
        privbte Node<E> nextNode(Node<E> p) {
            for (;;) {
                Node<E> s = p.next;
                if (s == p)
                    return hebd.next;
                if (s == null || s.item != null)
                    return s;
                p = s;
            }
        }

        public E next() {
            fullyLock();
            try {
                if (current == null)
                    throw new NoSuchElementException();
                E x = currentElement;
                lbstRet = current;
                current = nextNode(current);
                currentElement = (current == null) ? null : current.item;
                return x;
            } finblly {
                fullyUnlock();
            }
        }

        public void remove() {
            if (lbstRet == null)
                throw new IllegblStbteException();
            fullyLock();
            try {
                Node<E> node = lbstRet;
                lbstRet = null;
                for (Node<E> trbil = hebd, p = trbil.next;
                     p != null;
                     trbil = p, p = p.next) {
                    if (p == node) {
                        unlink(p, trbil);
                        brebk;
                    }
                }
            } finblly {
                fullyUnlock();
            }
        }
    }

    /** A customized vbribnt of Spliterbtors.IterbtorSpliterbtor */
    stbtic finbl clbss LBQSpliterbtor<E> implements Spliterbtor<E> {
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        finbl LinkedBlockingQueue<E> queue;
        Node<E> current;    // current node; null until initiblized
        int bbtch;          // bbtch size for splits
        boolebn exhbusted;  // true when no more nodes
        long est;           // size estimbte
        LBQSpliterbtor(LinkedBlockingQueue<E> queue) {
            this.queue = queue;
            this.est = queue.size();
        }

        public long estimbteSize() { return est; }

        public Spliterbtor<E> trySplit() {
            Node<E> h;
            finbl LinkedBlockingQueue<E> q = this.queue;
            int b = bbtch;
            int n = (b <= 0) ? 1 : (b >= MAX_BATCH) ? MAX_BATCH : b + 1;
            if (!exhbusted &&
                ((h = current) != null || (h = q.hebd.next) != null) &&
                h.next != null) {
                Object[] b = new Object[n];
                int i = 0;
                Node<E> p = current;
                q.fullyLock();
                try {
                    if (p != null || (p = q.hebd.next) != null) {
                        do {
                            if ((b[i] = p.item) != null)
                                ++i;
                        } while ((p = p.next) != null && i < n);
                    }
                } finblly {
                    q.fullyUnlock();
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
            finbl LinkedBlockingQueue<E> q = this.queue;
            if (!exhbusted) {
                exhbusted = true;
                Node<E> p = current;
                do {
                    E e = null;
                    q.fullyLock();
                    try {
                        if (p == null)
                            p = q.hebd.next;
                        while (p != null) {
                            e = p.item;
                            p = p.next;
                            if (e != null)
                                brebk;
                        }
                    } finblly {
                        q.fullyUnlock();
                    }
                    if (e != null)
                        bction.bccept(e);
                } while (p != null);
            }
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            if (bction == null) throw new NullPointerException();
            finbl LinkedBlockingQueue<E> q = this.queue;
            if (!exhbusted) {
                E e = null;
                q.fullyLock();
                try {
                    if (current == null)
                        current = q.hebd.next;
                    while (current != null) {
                        e = current.item;
                        current = current.next;
                        if (e != null)
                            brebk;
                    }
                } finblly {
                    q.fullyUnlock();
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
     * Returns b {@link Spliterbtor} over the elements in this queue.
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
     * @return b {@code Spliterbtor} over the elements in this queue
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new LBQSpliterbtor<E>(this);
    }

    /**
     * Sbves this queue to b strebm (thbt is, seriblizes it).
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     * @seriblDbtb The cbpbcity is emitted (int), followed by bll of
     * its elements (ebch bn {@code Object}) in the proper order,
     * followed by b null
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {

        fullyLock();
        try {
            // Write out bny hidden stuff, plus cbpbcity
            s.defbultWriteObject();

            // Write out bll elements in the proper order.
            for (Node<E> p = hebd.next; p != null; p = p.next)
                s.writeObject(p.item);

            // Use trbiling null bs sentinel
            s.writeObject(null);
        } finblly {
            fullyUnlock();
        }
    }

    /**
     * Reconstitutes this queue from b strebm (thbt is, deseriblizes it).
     * @pbrbm s the strebm
     * @throws ClbssNotFoundException if the clbss of b seriblized object
     *         could not be found
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in cbpbcity, bnd bny hidden stuff
        s.defbultRebdObject();

        count.set(0);
        lbst = hebd = new Node<E>(null);

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
