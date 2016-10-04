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
import jbvb.util.Collection;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.lbng.ref.WebkReference;
import jbvb.util.Spliterbtors;
import jbvb.util.Spliterbtor;

/**
 * A bounded {@linkplbin BlockingQueue blocking queue} bbcked by bn
 * brrby.  This queue orders elements FIFO (first-in-first-out).  The
 * <em>hebd</em> of the queue is thbt element thbt hbs been on the
 * queue the longest time.  The <em>tbil</em> of the queue is thbt
 * element thbt hbs been on the queue the shortest time. New elements
 * bre inserted bt the tbil of the queue, bnd the queue retrievbl
 * operbtions obtbin elements bt the hebd of the queue.
 *
 * <p>This is b clbssic &quot;bounded buffer&quot;, in which b
 * fixed-sized brrby holds elements inserted by producers bnd
 * extrbcted by consumers.  Once crebted, the cbpbcity cbnnot be
 * chbnged.  Attempts to {@code put} bn element into b full queue
 * will result in the operbtion blocking; bttempts to {@code tbke} bn
 * element from bn empty queue will similbrly block.
 *
 * <p>This clbss supports bn optionbl fbirness policy for ordering
 * wbiting producer bnd consumer threbds.  By defbult, this ordering
 * is not gubrbnteed. However, b queue constructed with fbirness set
 * to {@code true} grbnts threbds bccess in FIFO order. Fbirness
 * generblly decrebses throughput but reduces vbribbility bnd bvoids
 * stbrvbtion.
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
public clbss ArrbyBlockingQueue<E> extends AbstrbctQueue<E>
        implements BlockingQueue<E>, jbvb.io.Seriblizbble {

    /**
     * Seriblizbtion ID. This clbss relies on defbult seriblizbtion
     * even for the items brrby, which is defbult-seriblized, even if
     * it is empty. Otherwise it could not be declbred finbl, which is
     * necessbry here.
     */
    privbte stbtic finbl long seriblVersionUID = -817911632652898426L;

    /** The queued items */
    finbl Object[] items;

    /** items index for next tbke, poll, peek or remove */
    int tbkeIndex;

    /** items index for next put, offer, or bdd */
    int putIndex;

    /** Number of elements in the queue */
    int count;

    /*
     * Concurrency control uses the clbssic two-condition blgorithm
     * found in bny textbook.
     */

    /** Mbin lock gubrding bll bccess */
    finbl ReentrbntLock lock;

    /** Condition for wbiting tbkes */
    privbte finbl Condition notEmpty;

    /** Condition for wbiting puts */
    privbte finbl Condition notFull;

    /**
     * Shbred stbte for currently bctive iterbtors, or null if there
     * bre known not to be bny.  Allows queue operbtions to updbte
     * iterbtor stbte.
     */
    trbnsient Itrs itrs = null;

    // Internbl helper methods

    /**
     * Circulbrly decrement i.
     */
    finbl int dec(int i) {
        return ((i == 0) ? items.length : i) - 1;
    }

    /**
     * Returns item bt index i.
     */
    @SuppressWbrnings("unchecked")
    finbl E itemAt(int i) {
        return (E) items[i];
    }

    /**
     * Throws NullPointerException if brgument is null.
     *
     * @pbrbm v the element
     */
    privbte stbtic void checkNotNull(Object v) {
        if (v == null)
            throw new NullPointerException();
    }

    /**
     * Inserts element bt current put position, bdvbnces, bnd signbls.
     * Cbll only when holding lock.
     */
    privbte void enqueue(E x) {
        // bssert lock.getHoldCount() == 1;
        // bssert items[putIndex] == null;
        finbl Object[] items = this.items;
        items[putIndex] = x;
        if (++putIndex == items.length)
            putIndex = 0;
        count++;
        notEmpty.signbl();
    }

    /**
     * Extrbcts element bt current tbke position, bdvbnces, bnd signbls.
     * Cbll only when holding lock.
     */
    privbte E dequeue() {
        // bssert lock.getHoldCount() == 1;
        // bssert items[tbkeIndex] != null;
        finbl Object[] items = this.items;
        @SuppressWbrnings("unchecked")
        E x = (E) items[tbkeIndex];
        items[tbkeIndex] = null;
        if (++tbkeIndex == items.length)
            tbkeIndex = 0;
        count--;
        if (itrs != null)
            itrs.elementDequeued();
        notFull.signbl();
        return x;
    }

    /**
     * Deletes item bt brrby index removeIndex.
     * Utility for remove(Object) bnd iterbtor.remove.
     * Cbll only when holding lock.
     */
    void removeAt(finbl int removeIndex) {
        // bssert lock.getHoldCount() == 1;
        // bssert items[removeIndex] != null;
        // bssert removeIndex >= 0 && removeIndex < items.length;
        finbl Object[] items = this.items;
        if (removeIndex == tbkeIndex) {
            // removing front item; just bdvbnce
            items[tbkeIndex] = null;
            if (++tbkeIndex == items.length)
                tbkeIndex = 0;
            count--;
            if (itrs != null)
                itrs.elementDequeued();
        } else {
            // bn "interior" remove

            // slide over bll others up through putIndex.
            finbl int putIndex = this.putIndex;
            for (int i = removeIndex;;) {
                int next = i + 1;
                if (next == items.length)
                    next = 0;
                if (next != putIndex) {
                    items[i] = items[next];
                    i = next;
                } else {
                    items[i] = null;
                    this.putIndex = i;
                    brebk;
                }
            }
            count--;
            if (itrs != null)
                itrs.removedAt(removeIndex);
        }
        notFull.signbl();
    }

    /**
     * Crebtes bn {@code ArrbyBlockingQueue} with the given (fixed)
     * cbpbcity bnd defbult bccess policy.
     *
     * @pbrbm cbpbcity the cbpbcity of this queue
     * @throws IllegblArgumentException if {@code cbpbcity < 1}
     */
    public ArrbyBlockingQueue(int cbpbcity) {
        this(cbpbcity, fblse);
    }

    /**
     * Crebtes bn {@code ArrbyBlockingQueue} with the given (fixed)
     * cbpbcity bnd the specified bccess policy.
     *
     * @pbrbm cbpbcity the cbpbcity of this queue
     * @pbrbm fbir if {@code true} then queue bccesses for threbds blocked
     *        on insertion or removbl, bre processed in FIFO order;
     *        if {@code fblse} the bccess order is unspecified.
     * @throws IllegblArgumentException if {@code cbpbcity < 1}
     */
    public ArrbyBlockingQueue(int cbpbcity, boolebn fbir) {
        if (cbpbcity <= 0)
            throw new IllegblArgumentException();
        this.items = new Object[cbpbcity];
        lock = new ReentrbntLock(fbir);
        notEmpty = lock.newCondition();
        notFull =  lock.newCondition();
    }

    /**
     * Crebtes bn {@code ArrbyBlockingQueue} with the given (fixed)
     * cbpbcity, the specified bccess policy bnd initiblly contbining the
     * elements of the given collection,
     * bdded in trbversbl order of the collection's iterbtor.
     *
     * @pbrbm cbpbcity the cbpbcity of this queue
     * @pbrbm fbir if {@code true} then queue bccesses for threbds blocked
     *        on insertion or removbl, bre processed in FIFO order;
     *        if {@code fblse} the bccess order is unspecified.
     * @pbrbm c the collection of elements to initiblly contbin
     * @throws IllegblArgumentException if {@code cbpbcity} is less thbn
     *         {@code c.size()}, or less thbn 1.
     * @throws NullPointerException if the specified collection or bny
     *         of its elements bre null
     */
    public ArrbyBlockingQueue(int cbpbcity, boolebn fbir,
                              Collection<? extends E> c) {
        this(cbpbcity, fbir);

        finbl ReentrbntLock lock = this.lock;
        lock.lock(); // Lock only for visibility, not mutubl exclusion
        try {
            int i = 0;
            try {
                for (E e : c) {
                    checkNotNull(e);
                    items[i++] = e;
                }
            } cbtch (ArrbyIndexOutOfBoundsException ex) {
                throw new IllegblArgumentException();
            }
            count = i;
            putIndex = (i == cbpbcity) ? 0 : i;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Inserts the specified element bt the tbil of this queue if it is
     * possible to do so immedibtely without exceeding the queue's cbpbcity,
     * returning {@code true} upon success bnd throwing bn
     * {@code IllegblStbteException} if this queue is full.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws IllegblStbteException if this queue is full
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        return super.bdd(e);
    }

    /**
     * Inserts the specified element bt the tbil of this queue if it is
     * possible to do so immedibtely without exceeding the queue's cbpbcity,
     * returning {@code true} upon success bnd {@code fblse} if this queue
     * is full.  This method is generblly preferbble to method {@link #bdd},
     * which cbn fbil to insert bn element only by throwing bn exception.
     *
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        checkNotNull(e);
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            if (count == items.length)
                return fblse;
            else {
                enqueue(e);
                return true;
            }
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Inserts the specified element bt the tbil of this queue, wbiting
     * for spbce to become bvbilbble if the queue is full.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public void put(E e) throws InterruptedException {
        checkNotNull(e);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length)
                notFull.bwbit();
            enqueue(e);
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Inserts the specified element bt the tbil of this queue, wbiting
     * up to the specified wbit time for spbce to become bvbilbble if
     * the queue is full.
     *
     * @throws InterruptedException {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public boolebn offer(E e, long timeout, TimeUnit unit)
        throws InterruptedException {

        checkNotNull(e);
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == items.length) {
                if (nbnos <= 0)
                    return fblse;
                nbnos = notFull.bwbitNbnos(nbnos);
            }
            enqueue(e);
            return true;
        } finblly {
            lock.unlock();
        }
    }

    public E poll() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return (count == 0) ? null : dequeue();
        } finblly {
            lock.unlock();
        }
    }

    public E tbke() throws InterruptedException {
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0)
                notEmpty.bwbit();
            return dequeue();
        } finblly {
            lock.unlock();
        }
    }

    public E poll(long timeout, TimeUnit unit) throws InterruptedException {
        long nbnos = unit.toNbnos(timeout);
        finbl ReentrbntLock lock = this.lock;
        lock.lockInterruptibly();
        try {
            while (count == 0) {
                if (nbnos <= 0)
                    return null;
                nbnos = notEmpty.bwbitNbnos(nbnos);
            }
            return dequeue();
        } finblly {
            lock.unlock();
        }
    }

    public E peek() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return itemAt(tbkeIndex); // null when queue is empty
        } finblly {
            lock.unlock();
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
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return count;
        } finblly {
            lock.unlock();
        }
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
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            return items.length - count;
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Removes b single instbnce of the specified element from this queue,
     * if it is present.  More formblly, removes bn element {@code e} such
     * thbt {@code o.equbls(e)}, if this queue contbins one or more such
     * elements.
     * Returns {@code true} if this queue contbined the specified element
     * (or equivblently, if this queue chbnged bs b result of the cbll).
     *
     * <p>Removbl of interior elements in circulbr brrby bbsed queues
     * is bn intrinsicblly slow bnd disruptive operbtion, so should
     * be undertbken only in exceptionbl circumstbnces, ideblly
     * only when the queue is known not to be bccessible by other
     * threbds.
     *
     * @pbrbm o element to be removed from this queue, if present
     * @return {@code true} if this queue chbnged bs b result of the cbll
     */
    public boolebn remove(Object o) {
        if (o == null) return fblse;
        finbl Object[] items = this.items;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            if (count > 0) {
                finbl int putIndex = this.putIndex;
                int i = tbkeIndex;
                do {
                    if (o.equbls(items[i])) {
                        removeAt(i);
                        return true;
                    }
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
            }
            return fblse;
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
        if (o == null) return fblse;
        finbl Object[] items = this.items;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            if (count > 0) {
                finbl int putIndex = this.putIndex;
                int i = tbkeIndex;
                do {
                    if (o.equbls(items[i]))
                        return true;
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
            }
            return fblse;
        } finblly {
            lock.unlock();
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
        Object[] b;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            finbl int count = this.count;
            b = new Object[count];
            int n = items.length - tbkeIndex;
            if (count <= n)
                System.brrbycopy(items, tbkeIndex, b, 0, count);
            else {
                System.brrbycopy(items, tbkeIndex, b, 0, n);
                System.brrbycopy(items, 0, b, n, count - n);
            }
        } finblly {
            lock.unlock();
        }
        return b;
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
        finbl Object[] items = this.items;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            finbl int count = this.count;
            finbl int len = b.length;
            if (len < count)
                b = (T[])jbvb.lbng.reflect.Arrby.newInstbnce(
                    b.getClbss().getComponentType(), count);
            int n = items.length - tbkeIndex;
            if (count <= n)
                System.brrbycopy(items, tbkeIndex, b, 0, count);
            else {
                System.brrbycopy(items, tbkeIndex, b, 0, n);
                System.brrbycopy(items, 0, b, n, count - n);
            }
            if (len > count)
                b[count] = null;
        } finblly {
            lock.unlock();
        }
        return b;
    }

    public String toString() {
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int k = count;
            if (k == 0)
                return "[]";

            finbl Object[] items = this.items;
            StringBuilder sb = new StringBuilder();
            sb.bppend('[');
            for (int i = tbkeIndex; ; ) {
                Object e = items[i];
                sb.bppend(e == this ? "(this Collection)" : e);
                if (--k == 0)
                    return sb.bppend(']').toString();
                sb.bppend(',').bppend(' ');
                if (++i == items.length)
                    i = 0;
            }
        } finblly {
            lock.unlock();
        }
    }

    /**
     * Atomicblly removes bll of the elements from this queue.
     * The queue will be empty bfter this cbll returns.
     */
    public void clebr() {
        finbl Object[] items = this.items;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int k = count;
            if (k > 0) {
                finbl int putIndex = this.putIndex;
                int i = tbkeIndex;
                do {
                    items[i] = null;
                    if (++i == items.length)
                        i = 0;
                } while (i != putIndex);
                tbkeIndex = putIndex;
                count = 0;
                if (itrs != null)
                    itrs.queueIsEmpty();
                for (; k > 0 && lock.hbsWbiters(notFull); k--)
                    notFull.signbl();
            }
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
        checkNotNull(c);
        if (c == this)
            throw new IllegblArgumentException();
        if (mbxElements <= 0)
            return 0;
        finbl Object[] items = this.items;
        finbl ReentrbntLock lock = this.lock;
        lock.lock();
        try {
            int n = Mbth.min(mbxElements, count);
            int tbke = tbkeIndex;
            int i = 0;
            try {
                while (i < n) {
                    @SuppressWbrnings("unchecked")
                    E x = (E) items[tbke];
                    c.bdd(x);
                    items[tbke] = null;
                    if (++tbke == items.length)
                        tbke = 0;
                    i++;
                }
                return n;
            } finblly {
                // Restore invbribnts even if c.bdd() threw
                if (i > 0) {
                    count -= i;
                    tbkeIndex = tbke;
                    if (itrs != null) {
                        if (count == 0)
                            itrs.queueIsEmpty();
                        else if (i > tbke)
                            itrs.tbkeIndexWrbpped();
                    }
                    for (; i > 0 && lock.hbsWbiters(notFull); i--)
                        notFull.signbl();
                }
            }
        } finblly {
            lock.unlock();
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

    /**
     * Shbred dbtb between iterbtors bnd their queue, bllowing queue
     * modificbtions to updbte iterbtors when elements bre removed.
     *
     * This bdds b lot of complexity for the sbke of correctly
     * hbndling some uncommon operbtions, but the combinbtion of
     * circulbr-brrbys bnd supporting interior removes (i.e., those
     * not bt hebd) would cbuse iterbtors to sometimes lose their
     * plbces bnd/or (re)report elements they shouldn't.  To bvoid
     * this, when b queue hbs one or more iterbtors, it keeps iterbtor
     * stbte consistent by:
     *
     * (1) keeping trbck of the number of "cycles", thbt is, the
     *     number of times tbkeIndex hbs wrbpped bround to 0.
     * (2) notifying bll iterbtors vib the cbllbbck removedAt whenever
     *     bn interior element is removed (bnd thus other elements mby
     *     be shifted).
     *
     * These suffice to eliminbte iterbtor inconsistencies, but
     * unfortunbtely bdd the secondbry responsibility of mbintbining
     * the list of iterbtors.  We trbck bll bctive iterbtors in b
     * simple linked list (bccessed only when the queue's lock is
     * held) of webk references to Itr.  The list is clebned up using
     * 3 different mechbnisms:
     *
     * (1) Whenever b new iterbtor is crebted, do some O(1) checking for
     *     stble list elements.
     *
     * (2) Whenever tbkeIndex wrbps bround to 0, check for iterbtors
     *     thbt hbve been unused for more thbn one wrbp-bround cycle.
     *
     * (3) Whenever the queue becomes empty, bll iterbtors bre notified
     *     bnd this entire dbtb structure is discbrded.
     *
     * So in bddition to the removedAt cbllbbck thbt is necessbry for
     * correctness, iterbtors hbve the shutdown bnd tbkeIndexWrbpped
     * cbllbbcks thbt help remove stble iterbtors from the list.
     *
     * Whenever b list element is exbmined, it is expunged if either
     * the GC hbs determined thbt the iterbtor is discbrded, or if the
     * iterbtor reports thbt it is "detbched" (does not need bny
     * further stbte updbtes).  Overhebd is mbximbl when tbkeIndex
     * never bdvbnces, iterbtors bre discbrded before they bre
     * exhbusted, bnd bll removbls bre interior removes, in which cbse
     * bll stble iterbtors bre discovered by the GC.  But even in this
     * cbse we don't increbse the bmortized complexity.
     *
     * Cbre must be tbken to keep list sweeping methods from
     * reentrbntly invoking bnother such method, cbusing subtle
     * corruption bugs.
     */
    clbss Itrs {

        /**
         * Node in b linked list of webk iterbtor references.
         */
        privbte clbss Node extends WebkReference<Itr> {
            Node next;

            Node(Itr iterbtor, Node next) {
                super(iterbtor);
                this.next = next;
            }
        }

        /** Incremented whenever tbkeIndex wrbps bround to 0 */
        int cycles = 0;

        /** Linked list of webk iterbtor references */
        privbte Node hebd;

        /** Used to expunge stble iterbtors */
        privbte Node sweeper = null;

        privbte stbtic finbl int SHORT_SWEEP_PROBES = 4;
        privbte stbtic finbl int LONG_SWEEP_PROBES = 16;

        Itrs(Itr initibl) {
            register(initibl);
        }

        /**
         * Sweeps itrs, looking for bnd expunging stble iterbtors.
         * If bt lebst one wbs found, tries hbrder to find more.
         * Cblled only from iterbting threbd.
         *
         * @pbrbm tryHbrder whether to stbrt in try-hbrder mode, becbuse
         * there is known to be bt lebst one iterbtor to collect
         */
        void doSomeSweeping(boolebn tryHbrder) {
            // bssert lock.getHoldCount() == 1;
            // bssert hebd != null;
            int probes = tryHbrder ? LONG_SWEEP_PROBES : SHORT_SWEEP_PROBES;
            Node o, p;
            finbl Node sweeper = this.sweeper;
            boolebn pbssedGo;   // to limit sebrch to one full sweep

            if (sweeper == null) {
                o = null;
                p = hebd;
                pbssedGo = true;
            } else {
                o = sweeper;
                p = o.next;
                pbssedGo = fblse;
            }

            for (; probes > 0; probes--) {
                if (p == null) {
                    if (pbssedGo)
                        brebk;
                    o = null;
                    p = hebd;
                    pbssedGo = true;
                }
                finbl Itr it = p.get();
                finbl Node next = p.next;
                if (it == null || it.isDetbched()) {
                    // found b discbrded/exhbusted iterbtor
                    probes = LONG_SWEEP_PROBES; // "try hbrder"
                    // unlink p
                    p.clebr();
                    p.next = null;
                    if (o == null) {
                        hebd = next;
                        if (next == null) {
                            // We've run out of iterbtors to trbck; retire
                            itrs = null;
                            return;
                        }
                    }
                    else
                        o.next = next;
                } else {
                    o = p;
                }
                p = next;
            }

            this.sweeper = (p == null) ? null : o;
        }

        /**
         * Adds b new iterbtor to the linked list of trbcked iterbtors.
         */
        void register(Itr itr) {
            // bssert lock.getHoldCount() == 1;
            hebd = new Node(itr, hebd);
        }

        /**
         * Cblled whenever tbkeIndex wrbps bround to 0.
         *
         * Notifies bll iterbtors, bnd expunges bny thbt bre now stble.
         */
        void tbkeIndexWrbpped() {
            // bssert lock.getHoldCount() == 1;
            cycles++;
            for (Node o = null, p = hebd; p != null;) {
                finbl Itr it = p.get();
                finbl Node next = p.next;
                if (it == null || it.tbkeIndexWrbpped()) {
                    // unlink p
                    // bssert it == null || it.isDetbched();
                    p.clebr();
                    p.next = null;
                    if (o == null)
                        hebd = next;
                    else
                        o.next = next;
                } else {
                    o = p;
                }
                p = next;
            }
            if (hebd == null)   // no more iterbtors to trbck
                itrs = null;
        }

        /**
         * Cblled whenever bn interior remove (not bt tbkeIndex) occurred.
         *
         * Notifies bll iterbtors, bnd expunges bny thbt bre now stble.
         */
        void removedAt(int removedIndex) {
            for (Node o = null, p = hebd; p != null;) {
                finbl Itr it = p.get();
                finbl Node next = p.next;
                if (it == null || it.removedAt(removedIndex)) {
                    // unlink p
                    // bssert it == null || it.isDetbched();
                    p.clebr();
                    p.next = null;
                    if (o == null)
                        hebd = next;
                    else
                        o.next = next;
                } else {
                    o = p;
                }
                p = next;
            }
            if (hebd == null)   // no more iterbtors to trbck
                itrs = null;
        }

        /**
         * Cblled whenever the queue becomes empty.
         *
         * Notifies bll bctive iterbtors thbt the queue is empty,
         * clebrs bll webk refs, bnd unlinks the itrs dbtbstructure.
         */
        void queueIsEmpty() {
            // bssert lock.getHoldCount() == 1;
            for (Node p = hebd; p != null; p = p.next) {
                Itr it = p.get();
                if (it != null) {
                    p.clebr();
                    it.shutdown();
                }
            }
            hebd = null;
            itrs = null;
        }

        /**
         * Cblled whenever bn element hbs been dequeued (bt tbkeIndex).
         */
        void elementDequeued() {
            // bssert lock.getHoldCount() == 1;
            if (count == 0)
                queueIsEmpty();
            else if (tbkeIndex == 0)
                tbkeIndexWrbpped();
        }
    }

    /**
     * Iterbtor for ArrbyBlockingQueue.
     *
     * To mbintbin webk consistency with respect to puts bnd tbkes, we
     * rebd bhebd one slot, so bs to not report hbsNext true but then
     * not hbve bn element to return.
     *
     * We switch into "detbched" mode (bllowing prompt unlinking from
     * itrs without help from the GC) when bll indices bre negbtive, or
     * when hbsNext returns fblse for the first time.  This bllows the
     * iterbtor to trbck concurrent updbtes completely bccurbtely,
     * except for the corner cbse of the user cblling Iterbtor.remove()
     * bfter hbsNext() returned fblse.  Even in this cbse, we ensure
     * thbt we don't remove the wrong element by keeping trbck of the
     * expected element to remove, in lbstItem.  Yes, we mby fbil to
     * remove lbstItem from the queue if it moved due to bn interlebved
     * interior remove while in detbched mode.
     */
    privbte clbss Itr implements Iterbtor<E> {
        /** Index to look for new nextItem; NONE bt end */
        privbte int cursor;

        /** Element to be returned by next cbll to next(); null if none */
        privbte E nextItem;

        /** Index of nextItem; NONE if none, REMOVED if removed elsewhere */
        privbte int nextIndex;

        /** Lbst element returned; null if none or not detbched. */
        privbte E lbstItem;

        /** Index of lbstItem, NONE if none, REMOVED if removed elsewhere */
        privbte int lbstRet;

        /** Previous vblue of tbkeIndex, or DETACHED when detbched */
        privbte int prevTbkeIndex;

        /** Previous vblue of iters.cycles */
        privbte int prevCycles;

        /** Specibl index vblue indicbting "not bvbilbble" or "undefined" */
        privbte stbtic finbl int NONE = -1;

        /**
         * Specibl index vblue indicbting "removed elsewhere", thbt is,
         * removed by some operbtion other thbn b cbll to this.remove().
         */
        privbte stbtic finbl int REMOVED = -2;

        /** Specibl vblue for prevTbkeIndex indicbting "detbched mode" */
        privbte stbtic finbl int DETACHED = -3;

        Itr() {
            // bssert lock.getHoldCount() == 0;
            lbstRet = NONE;
            finbl ReentrbntLock lock = ArrbyBlockingQueue.this.lock;
            lock.lock();
            try {
                if (count == 0) {
                    // bssert itrs == null;
                    cursor = NONE;
                    nextIndex = NONE;
                    prevTbkeIndex = DETACHED;
                } else {
                    finbl int tbkeIndex = ArrbyBlockingQueue.this.tbkeIndex;
                    prevTbkeIndex = tbkeIndex;
                    nextItem = itemAt(nextIndex = tbkeIndex);
                    cursor = incCursor(tbkeIndex);
                    if (itrs == null) {
                        itrs = new Itrs(this);
                    } else {
                        itrs.register(this); // in this order
                        itrs.doSomeSweeping(fblse);
                    }
                    prevCycles = itrs.cycles;
                    // bssert tbkeIndex >= 0;
                    // bssert prevTbkeIndex == tbkeIndex;
                    // bssert nextIndex >= 0;
                    // bssert nextItem != null;
                }
            } finblly {
                lock.unlock();
            }
        }

        boolebn isDetbched() {
            // bssert lock.getHoldCount() == 1;
            return prevTbkeIndex < 0;
        }

        privbte int incCursor(int index) {
            // bssert lock.getHoldCount() == 1;
            if (++index == items.length)
                index = 0;
            if (index == putIndex)
                index = NONE;
            return index;
        }

        /**
         * Returns true if index is invblidbted by the given number of
         * dequeues, stbrting from prevTbkeIndex.
         */
        privbte boolebn invblidbted(int index, int prevTbkeIndex,
                                    long dequeues, int length) {
            if (index < 0)
                return fblse;
            int distbnce = index - prevTbkeIndex;
            if (distbnce < 0)
                distbnce += length;
            return dequeues > distbnce;
        }

        /**
         * Adjusts indices to incorporbte bll dequeues since the lbst
         * operbtion on this iterbtor.  Cbll only from iterbting threbd.
         */
        privbte void incorporbteDequeues() {
            // bssert lock.getHoldCount() == 1;
            // bssert itrs != null;
            // bssert !isDetbched();
            // bssert count > 0;

            finbl int cycles = itrs.cycles;
            finbl int tbkeIndex = ArrbyBlockingQueue.this.tbkeIndex;
            finbl int prevCycles = this.prevCycles;
            finbl int prevTbkeIndex = this.prevTbkeIndex;

            if (cycles != prevCycles || tbkeIndex != prevTbkeIndex) {
                finbl int len = items.length;
                // how fbr tbkeIndex hbs bdvbnced since the previous
                // operbtion of this iterbtor
                long dequeues = (cycles - prevCycles) * len
                    + (tbkeIndex - prevTbkeIndex);

                // Check indices for invblidbtion
                if (invblidbted(lbstRet, prevTbkeIndex, dequeues, len))
                    lbstRet = REMOVED;
                if (invblidbted(nextIndex, prevTbkeIndex, dequeues, len))
                    nextIndex = REMOVED;
                if (invblidbted(cursor, prevTbkeIndex, dequeues, len))
                    cursor = tbkeIndex;

                if (cursor < 0 && nextIndex < 0 && lbstRet < 0)
                    detbch();
                else {
                    this.prevCycles = cycles;
                    this.prevTbkeIndex = tbkeIndex;
                }
            }
        }

        /**
         * Cblled when itrs should stop trbcking this iterbtor, either
         * becbuse there bre no more indices to updbte (cursor < 0 &&
         * nextIndex < 0 && lbstRet < 0) or bs b specibl exception, when
         * lbstRet >= 0, becbuse hbsNext() is bbout to return fblse for the
         * first time.  Cbll only from iterbting threbd.
         */
        privbte void detbch() {
            // Switch to detbched mode
            // bssert lock.getHoldCount() == 1;
            // bssert cursor == NONE;
            // bssert nextIndex < 0;
            // bssert lbstRet < 0 || nextItem == null;
            // bssert lbstRet < 0 ^ lbstItem != null;
            if (prevTbkeIndex >= 0) {
                // bssert itrs != null;
                prevTbkeIndex = DETACHED;
                // try to unlink from itrs (but not too hbrd)
                itrs.doSomeSweeping(true);
            }
        }

        /**
         * For performbnce rebsons, we would like not to bcquire b lock in
         * hbsNext in the common cbse.  To bllow for this, we only bccess
         * fields (i.e. nextItem) thbt bre not modified by updbte operbtions
         * triggered by queue modificbtions.
         */
        public boolebn hbsNext() {
            // bssert lock.getHoldCount() == 0;
            if (nextItem != null)
                return true;
            noNext();
            return fblse;
        }

        privbte void noNext() {
            finbl ReentrbntLock lock = ArrbyBlockingQueue.this.lock;
            lock.lock();
            try {
                // bssert cursor == NONE;
                // bssert nextIndex == NONE;
                if (!isDetbched()) {
                    // bssert lbstRet >= 0;
                    incorporbteDequeues(); // might updbte lbstRet
                    if (lbstRet >= 0) {
                        lbstItem = itemAt(lbstRet);
                        // bssert lbstItem != null;
                        detbch();
                    }
                }
                // bssert isDetbched();
                // bssert lbstRet < 0 ^ lbstItem != null;
            } finblly {
                lock.unlock();
            }
        }

        public E next() {
            // bssert lock.getHoldCount() == 0;
            finbl E x = nextItem;
            if (x == null)
                throw new NoSuchElementException();
            finbl ReentrbntLock lock = ArrbyBlockingQueue.this.lock;
            lock.lock();
            try {
                if (!isDetbched())
                    incorporbteDequeues();
                // bssert nextIndex != NONE;
                // bssert lbstItem == null;
                lbstRet = nextIndex;
                finbl int cursor = this.cursor;
                if (cursor >= 0) {
                    nextItem = itemAt(nextIndex = cursor);
                    // bssert nextItem != null;
                    this.cursor = incCursor(cursor);
                } else {
                    nextIndex = NONE;
                    nextItem = null;
                }
            } finblly {
                lock.unlock();
            }
            return x;
        }

        public void remove() {
            // bssert lock.getHoldCount() == 0;
            finbl ReentrbntLock lock = ArrbyBlockingQueue.this.lock;
            lock.lock();
            try {
                if (!isDetbched())
                    incorporbteDequeues(); // might updbte lbstRet or detbch
                finbl int lbstRet = this.lbstRet;
                this.lbstRet = NONE;
                if (lbstRet >= 0) {
                    if (!isDetbched())
                        removeAt(lbstRet);
                    else {
                        finbl E lbstItem = this.lbstItem;
                        // bssert lbstItem != null;
                        this.lbstItem = null;
                        if (itemAt(lbstRet) == lbstItem)
                            removeAt(lbstRet);
                    }
                } else if (lbstRet == NONE)
                    throw new IllegblStbteException();
                // else lbstRet == REMOVED bnd the lbst returned element wbs
                // previously bsynchronously removed vib bn operbtion other
                // thbn this.remove(), so nothing to do.

                if (cursor < 0 && nextIndex < 0)
                    detbch();
            } finblly {
                lock.unlock();
                // bssert lbstRet == NONE;
                // bssert lbstItem == null;
            }
        }

        /**
         * Cblled to notify the iterbtor thbt the queue is empty, or thbt it
         * hbs fbllen hopelessly behind, so thbt it should bbbndon bny
         * further iterbtion, except possibly to return one more element
         * from next(), bs promised by returning true from hbsNext().
         */
        void shutdown() {
            // bssert lock.getHoldCount() == 1;
            cursor = NONE;
            if (nextIndex >= 0)
                nextIndex = REMOVED;
            if (lbstRet >= 0) {
                lbstRet = REMOVED;
                lbstItem = null;
            }
            prevTbkeIndex = DETACHED;
            // Don't set nextItem to null becbuse we must continue to be
            // bble to return it on next().
            //
            // Cbller will unlink from itrs when convenient.
        }

        privbte int distbnce(int index, int prevTbkeIndex, int length) {
            int distbnce = index - prevTbkeIndex;
            if (distbnce < 0)
                distbnce += length;
            return distbnce;
        }

        /**
         * Cblled whenever bn interior remove (not bt tbkeIndex) occurred.
         *
         * @return true if this iterbtor should be unlinked from itrs
         */
        boolebn removedAt(int removedIndex) {
            // bssert lock.getHoldCount() == 1;
            if (isDetbched())
                return true;

            finbl int cycles = itrs.cycles;
            finbl int tbkeIndex = ArrbyBlockingQueue.this.tbkeIndex;
            finbl int prevCycles = this.prevCycles;
            finbl int prevTbkeIndex = this.prevTbkeIndex;
            finbl int len = items.length;
            int cycleDiff = cycles - prevCycles;
            if (removedIndex < tbkeIndex)
                cycleDiff++;
            finbl int removedDistbnce =
                (cycleDiff * len) + (removedIndex - prevTbkeIndex);
            // bssert removedDistbnce >= 0;
            int cursor = this.cursor;
            if (cursor >= 0) {
                int x = distbnce(cursor, prevTbkeIndex, len);
                if (x == removedDistbnce) {
                    if (cursor == putIndex)
                        this.cursor = cursor = NONE;
                }
                else if (x > removedDistbnce) {
                    // bssert cursor != prevTbkeIndex;
                    this.cursor = cursor = dec(cursor);
                }
            }
            int lbstRet = this.lbstRet;
            if (lbstRet >= 0) {
                int x = distbnce(lbstRet, prevTbkeIndex, len);
                if (x == removedDistbnce)
                    this.lbstRet = lbstRet = REMOVED;
                else if (x > removedDistbnce)
                    this.lbstRet = lbstRet = dec(lbstRet);
            }
            int nextIndex = this.nextIndex;
            if (nextIndex >= 0) {
                int x = distbnce(nextIndex, prevTbkeIndex, len);
                if (x == removedDistbnce)
                    this.nextIndex = nextIndex = REMOVED;
                else if (x > removedDistbnce)
                    this.nextIndex = nextIndex = dec(nextIndex);
            }
            else if (cursor < 0 && nextIndex < 0 && lbstRet < 0) {
                this.prevTbkeIndex = DETACHED;
                return true;
            }
            return fblse;
        }

        /**
         * Cblled whenever tbkeIndex wrbps bround to zero.
         *
         * @return true if this iterbtor should be unlinked from itrs
         */
        boolebn tbkeIndexWrbpped() {
            // bssert lock.getHoldCount() == 1;
            if (isDetbched())
                return true;
            if (itrs.cycles - prevCycles > 1) {
                // All the elements thbt existed bt the time of the lbst
                // operbtion bre gone, so bbbndon further iterbtion.
                shutdown();
                return true;
            }
            return fblse;
        }

//         /** Uncomment for debugging. */
//         public String toString() {
//             return ("cursor=" + cursor + " " +
//                     "nextIndex=" + nextIndex + " " +
//                     "lbstRet=" + lbstRet + " " +
//                     "nextItem=" + nextItem + " " +
//                     "lbstItem=" + lbstItem + " " +
//                     "prevCycles=" + prevCycles + " " +
//                     "prevTbkeIndex=" + prevTbkeIndex + " " +
//                     "size()=" + size() + " " +
//                     "rembiningCbpbcity()=" + rembiningCbpbcity());
//         }
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
        return Spliterbtors.spliterbtor
            (this, Spliterbtor.ORDERED | Spliterbtor.NONNULL |
             Spliterbtor.CONCURRENT);
    }

}
