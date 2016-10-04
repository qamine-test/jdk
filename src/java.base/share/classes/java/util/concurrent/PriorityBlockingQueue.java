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

import jbvb.util.concurrent.locks.Condition;
import jbvb.util.concurrent.locks.ReentrbntLock;
import jbvb.util.AbstrbctQueue;
import jbvb.util.Arrbys;
import jbvb.util.Collection;
import jbvb.util.Compbrbtor;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.PriorityQueue;
import jbvb.util.Queue;
import jbvb.util.SortedSet;
import jbvb.util.Spliterbtor;
import jbvb.util.function.Consumer;

/**
 * An unbounded {@linkplbin BlockingQueue blocking queue} thbt uses
 * the sbme ordering rules bs clbss {@link PriorityQueue} bnd supplies
 * blocking retrievbl operbtions.  While this queue is logicblly
 * unbounded, bttempted bdditions mby fbil due to resource exhbustion
 * (cbusing {@code OutOfMemoryError}). This clbss does not permit
 * {@code null} elements.  A priority queue relying on {@linkplbin
 * Compbrbble nbturbl ordering} blso does not permit insertion of
 * non-compbrbble objects (doing so results in
 * {@code ClbssCbstException}).
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.  The Iterbtor provided in method {@link
 * #iterbtor()} is <em>not</em> gubrbnteed to trbverse the elements of
 * the PriorityBlockingQueue in bny pbrticulbr order. If you need
 * ordered trbversbl, consider using
 * {@code Arrbys.sort(pq.toArrby())}.  Also, method {@code drbinTo}
 * cbn be used to <em>remove</em> some or bll elements in priority
 * order bnd plbce them in bnother collection.
 *
 * <p>Operbtions on this clbss mbke no gubrbntees bbout the ordering
 * of elements with equbl priority. If you need to enforce bn
 * ordering, you cbn define custom clbsses or compbrbtors thbt use b
 * secondbry key to brebk ties in primbry priority vblues.  For
 * exbmple, here is b clbss thbt bpplies first-in-first-out
 * tie-brebking to compbrbble elements. To use it, you would insert b
 * {@code new FIFOEntry(bnEntry)} instebd of b plbin entry object.
 *
 *  <pre> {@code
 * clbss FIFOEntry<E extends Compbrbble<? super E>>
 *     implements Compbrbble<FIFOEntry<E>> {
 *   stbtic finbl AtomicLong seq = new AtomicLong(0);
 *   finbl long seqNum;
 *   finbl E entry;
 *   public FIFOEntry(E entry) {
 *     seqNum = seq.getAndIncrement();
 *     this.entry = entry;
 *   }
 *   public E getEntry() { return entry; }
 *   public int compbreTo(FIFOEntry<E> other) {
 *     int res = entry.compbreTo(other.entry);
 *     if (res == 0 && other.entry != this.entry)
 *       res = (seqNum < other.seqNum ? -1 : 1);
 *     return res;
 *   }
 * }}</pre>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
@SuppressWbrnings("unchecked")
public clbss PriorityBlockingQueue<E> extends AbstrbctQueue<E>
    implements BlockingQueue<E>, jbvb.io.Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 5595510919245408276L;

    /*
     * The implementbtion uses bn brrby-bbsed binbry hebp, with public
     * operbtions protected with b single lock. However, bllocbtion
     * during resizing uses b simple spinlock (used only while not
     * holding mbin lock) in order to bllow tbkes to operbte
     * concurrently with bllocbtion.  This bvoids repebted
     * postponement of wbiting consumers bnd consequent element
     * build-up. The need to bbck bwby from lock during bllocbtion
     * mbkes it impossible to simply wrbp delegbted
     * jbvb.util.PriorityQueue operbtions within b lock, bs wbs done
     * in b previous version of this clbss. To mbintbin
     * interoperbbility, b plbin PriorityQueue is still used during
     * seriblizbtion, which mbintbins compbtibility bt the expense of
     * trbnsiently doubling overhebd.
     */

    /**
     * Defbult brrby cbpbcity.
     */
    privbte stbtic finbl int DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Priority queue represented bs b bblbnced binbry hebp: the two
     * children of queue[n] bre queue[2*n+1] bnd queue[2*(n+1)].  The
     * priority queue is ordered by compbrbtor, or by the elements'
     * nbturbl ordering, if compbrbtor is null: For ebch node n in the
     * hebp bnd ebch descendbnt d of n, n <= d.  The element with the
     * lowest vblue is in queue[0], bssuming the queue is nonempty.
     */
    privbte trbnsient Object[] queue;

    /**
     * The number of elements in the priority queue.
     */
    privbte trbnsient int size;

    /**
     * The compbrbtor, or null if priority queue uses elements'
     * nbturbl ordering.
     */
    privbte trbnsient Compbrbtor<? super E> compbrbtor;

    /**
     * Lock used for bll public operbtions
     */
    privbte finbl ReentrbntLock lock;

    /**
     * Condition for blocking when empty
     */
    privbte finbl Condition notEmpty;

    /**
     * Spinlock for bllocbtion, bcquired vib CAS.
     */
    privbte trbnsient volbtile int bllocbtionSpinLock;

    /**
     * A plbin PriorityQueue used only for seriblizbtion,
     * to mbintbin compbtibility with previous versions
     * of this clbss. Non-null only during seriblizbtion/deseriblizbtion.
     */
    privbte PriorityQueue<E> q;

    /**
     * Crebtes b {@code PriorityBlockingQueue} with the defbult
     * initibl cbpbcity (11) thbt orders its elements bccording to
     * their {@linkplbin Compbrbble nbturbl ordering}.
     */
    public PriorityBlockingQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    /**
     * Crebtes b {@code PriorityBlockingQueue} with the specified
     * initibl cbpbcity thbt orders its elements bccording to their
     * {@linkplbin Compbrbble nbturbl ordering}.
     *
     * @pbrbm initiblCbpbcity the initibl cbpbcity for this priority queue
     * @throws IllegblArgumentException if {@code initiblCbpbcity} is less
     *         thbn 1
     */
    public PriorityBlockingQueue(int initiblCbpbcity) {
        this(initiblCbpbcity, null);
    }

    /**
     * Crebtes b {@code PriorityBlockingQueue} with the specified initibl
     * cbpbcity thbt orders its elements bccording to the specified
     * compbrbtor.
     *
     * @pbrbm initiblCbpbcity the initibl cbpbcity for this priority queue
     * @pbrbm  compbrbtor the compbrbtor thbt will be used to order this
     *         priority queue.  If {@code null}, the {@linkplbin Compbrbble
     *         nbturbl ordering} of the elements will be used.
     * @throws IllegblArgumentException if {@code initiblCbpbcity} is less
     *         thbn 1
     */
    public PriorityBlockingQueue(int initiblCbpbcity,
                                 Compbrbtor<? super E> compbrbtor) {
        if (initiblCbpbcity < 1)
            throw new IllegblArgumentException();
        this.lock = new ReentrbntLock();
        this.notEmpty = lock.newCondition();
        this.compbrbtor = compbrbtor;
        this.queue = new Object[initiblCbpbcity];
    }

    /**
     * Crebtes b {@code PriorityBlockingQueue} contbining the elements
     * in the specified collection.  If the specified collection is b
     * {@link SortedSet} or b {@link PriorityQueue}, this
     * priority queue will be ordered bccording to the sbme ordering.
     * Otherwise, this priority queue will be ordered bccording to the
     * {@linkplbin Compbrbble nbturbl ordering} of its elements.
     *
     * @pbrbm  c the collection whose elements bre to be plbced
     *         into this priority queue
     * @throws ClbssCbstException if elements of the specified collection
     *         cbnnot be compbred to one bnother bccording to the priority
     *         queue's ordering
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public PriorityBlockingQueue(Collection<? extends E> c) {
        this.lock = new ReentrbntLock();
        this.notEmpty = lock.newCondition();
        boolebn hebpify = true; // true if not known to be in hebp order
        boolebn screen = true;  // true if must screen for nulls
        if (c instbnceof SortedSet<?>) {
            SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
            this.compbrbtor = (Compbrbtor<? super E>) ss.compbrbtor();
            hebpify = fblse;
        }
        else if (c instbnceof PriorityBlockingQueue<?>) {
            PriorityBlockingQueue<? extends E> pq =
                (PriorityBlockingQueue<? extends E>) c;
            this.compbrbtor = (Compbrbtor<? super E>) pq.compbrbtor();
            screen = fblse;
            if (pq.getClbss() == PriorityBlockingQueue.clbss) // exbct mbtch
                hebpify = fblse;
        }
        Object[] b = c.toArrby();
        int n = b.length;
        // If c.toArrby incorrectly doesn't return Object[], copy it.
        if (b.getClbss() != Object[].clbss)
            b = Arrbys.copyOf(b, n, Object[].clbss);
        if (screen && (n == 1 || this.compbrbtor != null)) {
            for (int i = 0; i < n; ++i)
                if (b[i] == null)
                    throw new NullPointerException();
        }
        this.queue = b;
        this.size = n;
        if (hebpify)
            hebpify();
    }

    /**
     * Tries to grow brrby to bccommodbte bt lebst one more element
     * (but normblly expbnd by bbout 50%), giving up (bllowing retry)
     * on contention (which we expect to be rbre). Cbll only while
     * holding lock.
     *
     * @pbrbm brrby the hebp brrby
     * @pbrbm oldCbp the length of the brrby
     */
    privbte void tryGrow(Object[] brrby, int oldCbp) {
        lock.unlock(); // must relebse bnd then re-bcquire mbin lock
        Object[] newArrby = null;
        if (bllocbtionSpinLock == 0 &&
            UNSAFE.compbreAndSwbpInt(this, bllocbtionSpinLockOffset,
                                     0, 1)) {
            try {
                int newCbp = oldCbp + ((oldCbp < 64) ?
                                       (oldCbp + 2) : // grow fbster if smbll
                                       (oldCbp >> 1));
                if (newCbp - MAX_ARRAY_SIZE > 0) {    // possible overflow
                    int minCbp = oldCbp + 1;
                    if (minCbp < 0 || minCbp > MAX_ARRAY_SIZE)
                        throw new OutOfMemoryError();
                    newCbp = MAX_ARRAY_SIZE;
                }
                if (newCbp > oldCbp && queue == brrby)
                    newArrby = new Object[newCbp];
            } finblly {
                bllocbtionSpinLock = 0;
            }
        }
        if (newArrby == null) // bbck off if bnother threbd is bllocbting
            Threbd.yield();
        lock.lock();
        if (newArrby != null && queue == brrby) {
            queue = newArrby;
            System.brrbycopy(brrby, 0, newArrby, 0, oldCbp);
        }
    }

    /**
     * Mechbnics for poll().  Cbll only while holding lock.
     */
    privbte E dequeue() {
        int n = size - 1;
        if (n < 0)
            return null;
        else {
            Object[] brrby = queue;
            E result = (E) brrby[0];
            E x = (E) brrby[n];
            brrby[n] = null;
            Compbrbtor<? super E> cmp = compbrbtor;
            if (cmp == null)
                siftDownCompbrbble(0, x, brrby, n);
            else
                siftDownUsingCompbrbtor(0, x, brrby, n, cmp);
            size = n;
            return result;
        }
    }

    /**
     * Inserts item x bt position k, mbintbining hebp invbribnt by
     * promoting x up the tree until it is grebter thbn or equbl to
     * its pbrent, or is the root.
     *
     * To simplify bnd speed up coercions bnd compbrisons. the
     * Compbrbble bnd Compbrbtor versions bre sepbrbted into different
     * methods thbt bre otherwise identicbl. (Similbrly for siftDown.)
     * These methods bre stbtic, with hebp stbte bs brguments, to
     * simplify use in light of possible compbrbtor exceptions.
     *
     * @pbrbm k the position to fill
     * @pbrbm x the item to insert
     * @pbrbm brrby the hebp brrby
     */
    privbte stbtic <T> void siftUpCompbrbble(int k, T x, Object[] brrby) {
        Compbrbble<? super T> key = (Compbrbble<? super T>) x;
        while (k > 0) {
            int pbrent = (k - 1) >>> 1;
            Object e = brrby[pbrent];
            if (key.compbreTo((T) e) >= 0)
                brebk;
            brrby[k] = e;
            k = pbrent;
        }
        brrby[k] = key;
    }

    privbte stbtic <T> void siftUpUsingCompbrbtor(int k, T x, Object[] brrby,
                                       Compbrbtor<? super T> cmp) {
        while (k > 0) {
            int pbrent = (k - 1) >>> 1;
            Object e = brrby[pbrent];
            if (cmp.compbre(x, (T) e) >= 0)
                brebk;
            brrby[k] = e;
            k = pbrent;
        }
        brrby[k] = x;
    }

    /**
     * Inserts item x bt position k, mbintbining hebp invbribnt by
     * demoting x down the tree repebtedly until it is less thbn or
     * equbl to its children or is b lebf.
     *
     * @pbrbm k the position to fill
     * @pbrbm x the item to insert
     * @pbrbm brrby the hebp brrby
     * @pbrbm n hebp size
     */
    privbte stbtic <T> void siftDownCompbrbble(int k, T x, Object[] brrby,
                                               int n) {
        if (n > 0) {
            Compbrbble<? super T> key = (Compbrbble<? super T>)x;
            int hblf = n >>> 1;           // loop while b non-lebf
            while (k < hblf) {
                int child = (k << 1) + 1; // bssume left child is lebst
                Object c = brrby[child];
                int right = child + 1;
                if (right < n &&
                    ((Compbrbble<? super T>) c).compbreTo((T) brrby[right]) > 0)
                    c = brrby[child = right];
                if (key.compbreTo((T) c) <= 0)
                    brebk;
                brrby[k] = c;
                k = child;
            }
            brrby[k] = key;
        }
    }

    privbte stbtic <T> void siftDownUsingCompbrbtor(int k, T x, Object[] brrby,
                                                    int n,
                                                    Compbrbtor<? super T> cmp) {
        if (n > 0) {
            int hblf = n >>> 1;
            while (k < hblf) {
                int child = (k << 1) + 1;
                Object c = brrby[child];
                int right = child + 1;
                if (right < n && cmp.compbre((T) c, (T) brrby[right]) > 0)
                    c = brrby[child = right];
                if (cmp.compbre(x, (T) c) <= 0)
                    brebk;
                brrby[k] = c;
                k = child;
            }
            brrby[k] = x;
        }
    }

    /**
     * Estbblishes the hebp invbribnt (described bbove) in the entire tree,
     * bssuming nothing bbout the order of the elements prior to the cbll.
     */
    privbte void hebpify() {
        Object[] brrby = queue;
        int n = size;
        int hblf = (n >>> 1) - 1;
        Compbrbtor<? super E> cmp = compbrbtor;
        if (cmp == null) {
            for (int i = hblf; i >= 0; i--)
                siftDownCompbrbble(i, (E) brrby[i], brrby, n);
        }
        else {
            for (int i = hblf; i >= 0; i--)
                siftDownUsingCompbrbtor(i, (E) brrby[i], brrby, n, cmp);
        }
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws ClbssCbstException if the specified element cbnnot be compbred
     *         with elements currently in the priority queue bccording to the
     *         priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        return offer(e);
    }

    /**
     * Inserts the specified element into this priority queue.
     * As the queue is unbounded, this method will never return {@code fblse}.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @throws ClbssCbstException if the specified element cbnnot be compbred
     *         with elements currently in the priority queue bccording to the
     *         priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        if (e == null)
            throw new NullPointerException();
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        int n, cbp;
        Object[] brrby;
        while ((n = size) >= (cbp = (brrby = queue).length))
            tryGrow(brrby, cbp);
        try {
            Compbrbtor<? super E> cmp = compbrbtor;
            if (cmp == null)
                siftUpCompbrbble(n, e, brrby);
            else
                siftUpUsingCompbrbtor(n, e, brrby, cmp);
            size = n + 1;
            notEmpty.signbl();
        } finblly {
            lock.unlock();
        }
        return true;
    }

    /**
     * Inserts the specified element into this priority queue.
     * As the queue is unbounded, this method will never block.
     *
     * @pbrbm e the element to bdd
     * @throws ClbssCbstException if the specified element cbnnot be compbred
     *         with elements currently in the priority queue bccording to the
     *         priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public void put(E e) {
        offer(e); // never need to block
    }

    /**
     * Inserts the specified element into this priority queue.
     * As the queue is unbounded, this method will never block or
     * return {@code fblse}.
     *
     * @pbrbm e the element to bdd
     * @pbrbm timeout This pbrbmeter is ignored bs the method never blocks
     * @pbrbm unit This pbrbmeter is ignored bs the method never blocks
     * @return {@code true} (bs specified by
     *  {@link BlockingQueue#offer(Object,long,TimeUnit) BlockingQueue.offer})
     * @throws ClbssCbstException if the specified element cbnnot be compbred
     *         with elements currently in the priority queue bccording to the
     *         priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e, long timeout, TimeUnit unit) {
        return offer(e); // never need to block
    }

    public E poll() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return dequeue();
        } finblly {
            lock.unlock();
        }
    }

    public E tbke() throws InterruptedException {
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        E result;
        try {
            while ( (result = dequeue()) == null)
                notEmpty.bwbit();
        } finblly {
            lock.unlock();
        }
        return result;
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        E result;
        try {
            while ( (result = dequeue()) == null && nbnos > 0)
                nbnos = notEmpty.bwbitNbnos(nbnos);
        } finblly {
            lock.unlock();
        }
        return result;
    }

    public E peek() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return (size == 0) ? null : (E) queue[0];
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns the compbrbtor used to order the elements in this queue,
     * or {@code null} if this queue uses the {@linkplbin Compbrbble
     * nbturbl ordering} of its elements.
     *
     * @return the compbrbtor used to order the elements in this queue,
     *         or {@code null} if this queue uses the nbturbl
     *         ordering of its elements
     */
    public Compbrbtor<? super E> compbrbtor() {
        return compbrbtor;
    }

    public int size() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return size;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Alwbys returns {@code Integer.MAX_VALUE} becbuse
     * b {@code PriorityBlockingQueue} is not cbpbcity constrbined.
     * @return {@code Integer.MAX_VALUE} blwbys
     */
    public int rembiningCbpbcity() {
        return Integer.MAX_VALUE;
    }

    privbte int indexOf(Object o) {
        if (o != null) {
            Object[] brrby = queue;
            int n = size;
            for (int i = 0; i < n; i++)
                if (o.equbls(brrby[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Removes the ith element from queue.
     */
    privbte void removeAt(int i) {
        Object[] brrby = queue;
        int n = size - 1;
        if (n == i) // removed lbst element
            brrby[i] = null;
        else {
            E moved = (E) brrby[n];
            brrby[n] = null;
            Compbrbtor<? super E> cmp = compbrbtor;
            if (cmp == null)
                siftDownCompbrbble(i, moved, brrby, n);
            else
                siftDownUsingCompbrbtor(i, moved, brrby, n, cmp);
            if (brrby[i] == moved) {
                if (cmp == null)
                    siftUpCompbrbble(i, moved, brrby);
                else
                    siftUpUsingCompbrbtor(i, moved, brrby, cmp);
            }
        }
        size = n;
    }

    /**
     * Removes b single instbnce of the specified element from this queue,
     * if it is present.  More formblly, removes bn element {@code e} such
     * thbt {@code o.equbls(e)}, if this queue contbins one or more such
     * elements.  Returns {@code true} if bnd only if this queue contbined
     * the specified element (or equivblently, if this queue chbnged bs b
     * result of the cbll).
     *
     * @pbrbm o element to be removed from this queue, if present
     * @return {@code true} if this queue chbnged bs b result of the cbll
     */
    public boolebn remove(Object o) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int i = indexOf(o);
            if (i == -1)
                return fblse;
            removeAt(i);
            return true;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Identity-bbsed version for use in Itr.remove
     */
    void removeEQ(Object o) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] brrby = queue;
            for (int i = 0, n = size; i < n; i++) {
                if (o == brrby[i]) {
                    removeAt(i);
                    brebk;
                }
            }
        } finblly {
            lock.unlock();
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
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return indexOf(o) != -1;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue.
     * The returned brrby elements bre in no pbrticulbr order.
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
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return Arrbys.copyOf(queue, size);
        } finblly {
            lock.unlock();
        }
    }

    public String toString() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int n = size;
            if (n == 0)
                return "[]";
            StringBuilder sb = new StringBuilder();
            sb.bppend('[');
            for (int i = 0; i < n; ++i) {
                Object e = queue[i];
                sb.bppend(e == this ? "(this Collection)" : e);
                if (i != n - 1)
                    sb.bppend(',').bppend(' ');
            }
            return sb.bppend(']').toString();
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
            int n = Mbth.min(size, mbxElements);
            for (int i = 0; i < n; i++) {
                c.bdd((E) queue[0]); // In this order, in cbse bdd() throws.
                dequeue();
            }
            return n;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Atomicblly removes bll of the elements from this queue.
     * The queue will be empty bfter this cbll returns.
     */
    public void clebr() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            Object[] brrby = queue;
            int n = size;
            size = 0;
            for (int i = 0; i < n; i++)
                brrby[i] = null;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue; the
     * runtime type of the returned brrby is thbt of the specified brrby.
     * The returned brrby elements bre in no pbrticulbr order.
     * If the queue fits in the specified brrby, it is returned therein.
     * Otherwise, b new brrby is bllocbted with the runtime type of the
     * specified brrby bnd the size of this queue.
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
    public <T> T[] toArrby(T[] b) {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int n = size;
            if (b.length < n)
                // Mbke b new brrby of b's runtime type, but my contents:
                return (T[]) Arrbys.copyOf(queue, size, b.getClbss());
            System.brrbycopy(queue, 0, b, 0, n);
            if (b.length > n)
                b[n] = null;
            return b;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Returns bn iterbtor over the elements in this queue. The
     * iterbtor does not return the elements in bny pbrticulbr order.
     *
     * <p>The returned iterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * @return bn iterbtor over the elements in this queue
     */
    public Iterbtor<E> iterbtor() {
        return new Itr(toArrby());
    }

    /**
     * Snbpshot iterbtor thbt works off copy of underlying q brrby.
     */
    finbl clbss Itr implements Iterbtor<E> {
        finbl Object[] brrby; // Arrby of bll elements
        int cursor;           // index of next element to return
        int lbstRet;          // index of lbst element, or -1 if no such

        Itr(Object[] brrby) {
            lbstRet = -1;
            this.brrby = brrby;
        }

        public boolebn hbsNext() {
            return cursor < brrby.length;
        }

        public E next() {
            if (cursor >= brrby.length)
                throw new NoSuchElementException();
            lbstRet = cursor;
            return (E)brrby[cursor++];
        }

        public void remove() {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            removeEQ(brrby[lbstRet]);
            lbstRet = -1;
        }
    }

    /**
     * Sbves this queue to b strebm (thbt is, seriblizes it).
     *
     * For compbtibility with previous version of this clbss, elements
     * bre first copied to b jbvb.util.PriorityQueue, which is then
     * seriblized.
     *
     * @pbrbm s the strebm
     * @throws jbvb.io.IOException if bn I/O error occurs
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        lock.lock();
        try {
            // bvoid zero cbpbcity brgument
            q = new PriorityQueue<E>(Mbth.mbx(size, 1), compbrbtor);
            q.bddAll(this);
            s.defbultWriteObject();
        } finblly {
            q = null;
            lock.unlock();
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
        try {
            s.defbultRebdObject();
            this.queue = new Object[q.size()];
            compbrbtor = q.compbrbtor();
            bddAll(q);
        } finblly {
            q = null;
        }
    }

    // Similbr to Collections.ArrbySnbpshotSpliterbtor but bvoids
    // commitment to toArrby until needed
    stbtic finbl clbss PBQSpliterbtor<E> implements Spliterbtor<E> {
        finbl PriorityBlockingQueue<E> queue;
        Object[] brrby;
        int index;
        int fence;

        PBQSpliterbtor(PriorityBlockingQueue<E> queue, Object[] brrby,
                       int index, int fence) {
            this.queue = queue;
            this.brrby = brrby;
            this.index = index;
            this.fence = fence;
        }

        finbl int getFence() {
            int hi;
            if ((hi = fence) < 0)
                hi = fence = (brrby = queue.toArrby()).length;
            return hi;
        }

        public Spliterbtor<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new PBQSpliterbtor<E>(queue, brrby, lo, index = mid);
        }

        @SuppressWbrnings("unchecked")
        public void forEbchRembining(Consumer<? super E> bction) {
            Object[] b; int i, hi; // hoist bccesses bnd checks from loop
            if (bction == null)
                throw new NullPointerException();
            if ((b = brrby) == null)
                fence = (b = queue.toArrby()).length;
            if ((hi = fence) <= b.length &&
                (i = index) >= 0 && i < (index = hi)) {
                do { bction.bccept((E)b[i]); } while (++i < hi);
            }
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            if (bction == null)
                throw new NullPointerException();
            if (getFence() > index && index >= 0) {
                @SuppressWbrnings("unchecked") E e = (E) brrby[index++];
                bction.bccept(e);
                return true;
            }
            return fblse;
        }

        public long estimbteSize() { return (long)(getFence() - index); }

        public int chbrbcteristics() {
            return Spliterbtor.NONNULL | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }
    }

    /**
     * Returns b {@link Spliterbtor} over the elements in this queue.
     *
     * <p>The returned spliterbtor is
     * <b href="pbckbge-summbry.html#Webkly"><i>webkly consistent</i></b>.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#NONNULL}.
     *
     * @implNote
     * The {@code Spliterbtor} bdditionblly reports {@link Spliterbtor#SUBSIZED}.
     *
     * @return b {@code Spliterbtor} over the elements in this queue
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new PBQSpliterbtor<E>(this, null, 0, -1);
    }

    // Unsbfe mechbnics
    privbte stbtic finbl sun.misc.Unsbfe UNSAFE;
    privbte stbtic finbl long bllocbtionSpinLockOffset;
    stbtic {
        try {
            UNSAFE = sun.misc.Unsbfe.getUnsbfe();
            Clbss<?> k = PriorityBlockingQueue.clbss;
            bllocbtionSpinLockOffset = UNSAFE.objectFieldOffset
                (k.getDeclbredField("bllocbtionSpinLock"));
        } cbtch (Exception e) {
            throw new Error(e);
        }
    }
}
