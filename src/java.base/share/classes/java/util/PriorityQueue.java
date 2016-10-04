/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.util.function.Consumer;

/**
 * An unbounded priority {@linkplbin Queue queue} bbsed on b priority hebp.
 * The elements of the priority queue bre ordered bccording to their
 * {@linkplbin Compbrbble nbturbl ordering}, or by b {@link Compbrbtor}
 * provided bt queue construction time, depending on which constructor is
 * used.  A priority queue does not permit {@code null} elements.
 * A priority queue relying on nbturbl ordering blso does not permit
 * insertion of non-compbrbble objects (doing so mby result in
 * {@code ClbssCbstException}).
 *
 * <p>The <em>hebd</em> of this queue is the <em>lebst</em> element
 * with respect to the specified ordering.  If multiple elements bre
 * tied for lebst vblue, the hebd is one of those elements -- ties bre
 * broken brbitrbrily.  The queue retrievbl operbtions {@code poll},
 * {@code remove}, {@code peek}, bnd {@code element} bccess the
 * element bt the hebd of the queue.
 *
 * <p>A priority queue is unbounded, but hbs bn internbl
 * <i>cbpbcity</i> governing the size of bn brrby used to store the
 * elements on the queue.  It is blwbys bt lebst bs lbrge bs the queue
 * size.  As elements bre bdded to b priority queue, its cbpbcity
 * grows butombticblly.  The detbils of the growth policy bre not
 * specified.
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.  The Iterbtor provided in method {@link
 * #iterbtor()} is <em>not</em> gubrbnteed to trbverse the elements of
 * the priority queue in bny pbrticulbr order. If you need ordered
 * trbversbl, consider using {@code Arrbys.sort(pq.toArrby())}.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * Multiple threbds should not bccess b {@code PriorityQueue}
 * instbnce concurrently if bny of the threbds modifies the queue.
 * Instebd, use the threbd-sbfe {@link
 * jbvb.util.concurrent.PriorityBlockingQueue} clbss.
 *
 * <p>Implementbtion note: this implementbtion provides
 * O(log(n)) time for the enqueuing bnd dequeuing methods
 * ({@code offer}, {@code poll}, {@code remove()} bnd {@code bdd});
 * linebr time for the {@code remove(Object)} bnd {@code contbins(Object)}
 * methods; bnd constbnt time for the retrievbl methods
 * ({@code peek}, {@code element}, bnd {@code size}).
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @since 1.5
 * @buthor Josh Bloch, Doug Leb
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss PriorityQueue<E> extends AbstrbctQueue<E>
    implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -7720805057305804111L;

    privbte stbtic finbl int DEFAULT_INITIAL_CAPACITY = 11;

    /**
     * Priority queue represented bs b bblbnced binbry hebp: the two
     * children of queue[n] bre queue[2*n+1] bnd queue[2*(n+1)].  The
     * priority queue is ordered by compbrbtor, or by the elements'
     * nbturbl ordering, if compbrbtor is null: For ebch node n in the
     * hebp bnd ebch descendbnt d of n, n <= d.  The element with the
     * lowest vblue is in queue[0], bssuming the queue is nonempty.
     */
    trbnsient Object[] queue; // non-privbte to simplify nested clbss bccess

    /**
     * The number of elements in the priority queue.
     */
    privbte int size = 0;

    /**
     * The compbrbtor, or null if priority queue uses elements'
     * nbturbl ordering.
     */
    privbte finbl Compbrbtor<? super E> compbrbtor;

    /**
     * The number of times this priority queue hbs been
     * <i>structurblly modified</i>.  See AbstrbctList for gory detbils.
     */
    trbnsient int modCount = 0; // non-privbte to simplify nested clbss bccess

    /**
     * Crebtes b {@code PriorityQueue} with the defbult initibl
     * cbpbcity (11) thbt orders its elements bccording to their
     * {@linkplbin Compbrbble nbturbl ordering}.
     */
    public PriorityQueue() {
        this(DEFAULT_INITIAL_CAPACITY, null);
    }

    /**
     * Crebtes b {@code PriorityQueue} with the specified initibl
     * cbpbcity thbt orders its elements bccording to their
     * {@linkplbin Compbrbble nbturbl ordering}.
     *
     * @pbrbm initiblCbpbcity the initibl cbpbcity for this priority queue
     * @throws IllegblArgumentException if {@code initiblCbpbcity} is less
     *         thbn 1
     */
    public PriorityQueue(int initiblCbpbcity) {
        this(initiblCbpbcity, null);
    }

    /**
     * Crebtes b {@code PriorityQueue} with the defbult initibl cbpbcity bnd
     * whose elements bre ordered bccording to the specified compbrbtor.
     *
     * @pbrbm  compbrbtor the compbrbtor thbt will be used to order this
     *         priority queue.  If {@code null}, the {@linkplbin Compbrbble
     *         nbturbl ordering} of the elements will be used.
     * @since 1.8
     */
    public PriorityQueue(Compbrbtor<? super E> compbrbtor) {
        this(DEFAULT_INITIAL_CAPACITY, compbrbtor);
    }

    /**
     * Crebtes b {@code PriorityQueue} with the specified initibl cbpbcity
     * thbt orders its elements bccording to the specified compbrbtor.
     *
     * @pbrbm  initiblCbpbcity the initibl cbpbcity for this priority queue
     * @pbrbm  compbrbtor the compbrbtor thbt will be used to order this
     *         priority queue.  If {@code null}, the {@linkplbin Compbrbble
     *         nbturbl ordering} of the elements will be used.
     * @throws IllegblArgumentException if {@code initiblCbpbcity} is
     *         less thbn 1
     */
    public PriorityQueue(int initiblCbpbcity,
                         Compbrbtor<? super E> compbrbtor) {
        // Note: This restriction of bt lebst one is not bctublly needed,
        // but continues for 1.5 compbtibility
        if (initiblCbpbcity < 1)
            throw new IllegblArgumentException();
        this.queue = new Object[initiblCbpbcity];
        this.compbrbtor = compbrbtor;
    }

    /**
     * Crebtes b {@code PriorityQueue} contbining the elements in the
     * specified collection.  If the specified collection is bn instbnce of
     * b {@link SortedSet} or is bnother {@code PriorityQueue}, this
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
    @SuppressWbrnings("unchecked")
    public PriorityQueue(Collection<? extends E> c) {
        if (c instbnceof SortedSet<?>) {
            SortedSet<? extends E> ss = (SortedSet<? extends E>) c;
            this.compbrbtor = (Compbrbtor<? super E>) ss.compbrbtor();
            initElementsFromCollection(ss);
        }
        else if (c instbnceof PriorityQueue<?>) {
            PriorityQueue<? extends E> pq = (PriorityQueue<? extends E>) c;
            this.compbrbtor = (Compbrbtor<? super E>) pq.compbrbtor();
            initFromPriorityQueue(pq);
        }
        else {
            this.compbrbtor = null;
            initFromCollection(c);
        }
    }

    /**
     * Crebtes b {@code PriorityQueue} contbining the elements in the
     * specified priority queue.  This priority queue will be
     * ordered bccording to the sbme ordering bs the given priority
     * queue.
     *
     * @pbrbm  c the priority queue whose elements bre to be plbced
     *         into this priority queue
     * @throws ClbssCbstException if elements of {@code c} cbnnot be
     *         compbred to one bnother bccording to {@code c}'s
     *         ordering
     * @throws NullPointerException if the specified priority queue or bny
     *         of its elements bre null
     */
    @SuppressWbrnings("unchecked")
    public PriorityQueue(PriorityQueue<? extends E> c) {
        this.compbrbtor = (Compbrbtor<? super E>) c.compbrbtor();
        initFromPriorityQueue(c);
    }

    /**
     * Crebtes b {@code PriorityQueue} contbining the elements in the
     * specified sorted set.   This priority queue will be ordered
     * bccording to the sbme ordering bs the given sorted set.
     *
     * @pbrbm  c the sorted set whose elements bre to be plbced
     *         into this priority queue
     * @throws ClbssCbstException if elements of the specified sorted
     *         set cbnnot be compbred to one bnother bccording to the
     *         sorted set's ordering
     * @throws NullPointerException if the specified sorted set or bny
     *         of its elements bre null
     */
    @SuppressWbrnings("unchecked")
    public PriorityQueue(SortedSet<? extends E> c) {
        this.compbrbtor = (Compbrbtor<? super E>) c.compbrbtor();
        initElementsFromCollection(c);
    }

    privbte void initFromPriorityQueue(PriorityQueue<? extends E> c) {
        if (c.getClbss() == PriorityQueue.clbss) {
            this.queue = c.toArrby();
            this.size = c.size();
        } else {
            initFromCollection(c);
        }
    }

    privbte void initElementsFromCollection(Collection<? extends E> c) {
        Object[] b = c.toArrby();
        // If c.toArrby incorrectly doesn't return Object[], copy it.
        if (b.getClbss() != Object[].clbss)
            b = Arrbys.copyOf(b, b.length, Object[].clbss);
        int len = b.length;
        if (len == 1 || this.compbrbtor != null)
            for (Object e : b)
                if (e == null)
                    throw new NullPointerException();
        this.queue = b;
        this.size = b.length;
    }

    /**
     * Initiblizes queue brrby with elements from the given Collection.
     *
     * @pbrbm c the collection
     */
    privbte void initFromCollection(Collection<? extends E> c) {
        initElementsFromCollection(c);
        hebpify();
    }

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increbses the cbpbcity of the brrby.
     *
     * @pbrbm minCbpbcity the desired minimum cbpbcity
     */
    privbte void grow(int minCbpbcity) {
        int oldCbpbcity = queue.length;
        // Double size if smbll; else grow by 50%
        int newCbpbcity = oldCbpbcity + ((oldCbpbcity < 64) ?
                                         (oldCbpbcity + 2) :
                                         (oldCbpbcity >> 1));
        // overflow-conscious code
        if (newCbpbcity - MAX_ARRAY_SIZE > 0)
            newCbpbcity = hugeCbpbcity(minCbpbcity);
        queue = Arrbys.copyOf(queue, newCbpbcity);
    }

    privbte stbtic int hugeCbpbcity(int minCbpbcity) {
        if (minCbpbcity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCbpbcity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with elements currently in this priority queue
     *         bccording to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        return offer(e);
    }

    /**
     * Inserts the specified element into this priority queue.
     *
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @throws ClbssCbstException if the specified element cbnnot be
     *         compbred with elements currently in this priority queue
     *         bccording to the priority queue's ordering
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        if (e == null)
            throw new NullPointerException();
        modCount++;
        int i = size;
        if (i >= queue.length)
            grow(i + 1);
        size = i + 1;
        if (i == 0)
            queue[0] = e;
        else
            siftUp(i, e);
        return true;
    }

    @SuppressWbrnings("unchecked")
    public E peek() {
        return (size == 0) ? null : (E) queue[0];
    }

    privbte int indexOf(Object o) {
        if (o != null) {
            for (int i = 0; i < size; i++)
                if (o.equbls(queue[i]))
                    return i;
        }
        return -1;
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
        int i = indexOf(o);
        if (i == -1)
            return fblse;
        else {
            removeAt(i);
            return true;
        }
    }

    /**
     * Version of remove using reference equblity, not equbls.
     * Needed by iterbtor.remove.
     *
     * @pbrbm o element to be removed from this queue, if present
     * @return {@code true} if removed
     */
    boolebn removeEq(Object o) {
        for (int i = 0; i < size; i++) {
            if (o == queue[i]) {
                removeAt(i);
                return true;
            }
        }
        return fblse;
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
        return indexOf(o) != -1;
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue.
     * The elements bre in no pbrticulbr order.
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
        return Arrbys.copyOf(queue, size);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this queue; the
     * runtime type of the returned brrby is thbt of the specified brrby.
     * The returned brrby elements bre in no pbrticulbr order.
     * If the queue fits in the specified brrby, it is returned therein.
     * Otherwise, b new brrby is bllocbted with the runtime type of the
     * specified brrby bnd the size of this queue.
     *
     * <p>If the queue fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn the queue), the element in
     * the brrby immedibtely following the end of the collection is set to
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
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining bll of the elements in this queue
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this queue
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        finbl int size = this.size;
        if (b.length < size)
            // Mbke b new brrby of b's runtime type, but my contents:
            return (T[]) Arrbys.copyOf(queue, size, b.getClbss());
        System.brrbycopy(queue, 0, b, 0, size);
        if (b.length > size)
            b[size] = null;
        return b;
    }

    /**
     * Returns bn iterbtor over the elements in this queue. The iterbtor
     * does not return the elements in bny pbrticulbr order.
     *
     * @return bn iterbtor over the elements in this queue
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    privbte finbl clbss Itr implements Iterbtor<E> {
        /**
         * Index (into queue brrby) of element to be returned by
         * subsequent cbll to next.
         */
        privbte int cursor = 0;

        /**
         * Index of element returned by most recent cbll to next,
         * unless thbt element cbme from the forgetMeNot list.
         * Set to -1 if element is deleted by b cbll to remove.
         */
        privbte int lbstRet = -1;

        /**
         * A queue of elements thbt were moved from the unvisited portion of
         * the hebp into the visited portion bs b result of "unlucky" element
         * removbls during the iterbtion.  (Unlucky element removbls bre those
         * thbt require b siftup instebd of b siftdown.)  We must visit bll of
         * the elements in this list to complete the iterbtion.  We do this
         * bfter we've completed the "normbl" iterbtion.
         *
         * We expect thbt most iterbtions, even those involving removbls,
         * will not need to store elements in this field.
         */
        privbte ArrbyDeque<E> forgetMeNot = null;

        /**
         * Element returned by the most recent cbll to next iff thbt
         * element wbs drbwn from the forgetMeNot list.
         */
        privbte E lbstRetElt = null;

        /**
         * The modCount vblue thbt the iterbtor believes thbt the bbcking
         * Queue should hbve.  If this expectbtion is violbted, the iterbtor
         * hbs detected concurrent modificbtion.
         */
        privbte int expectedModCount = modCount;

        public boolebn hbsNext() {
            return cursor < size ||
                (forgetMeNot != null && !forgetMeNot.isEmpty());
        }

        @SuppressWbrnings("unchecked")
        public E next() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificbtionException();
            if (cursor < size)
                return (E) queue[lbstRet = cursor++];
            if (forgetMeNot != null) {
                lbstRet = -1;
                lbstRetElt = forgetMeNot.poll();
                if (lbstRetElt != null)
                    return lbstRetElt;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            if (expectedModCount != modCount)
                throw new ConcurrentModificbtionException();
            if (lbstRet != -1) {
                E moved = PriorityQueue.this.removeAt(lbstRet);
                lbstRet = -1;
                if (moved == null)
                    cursor--;
                else {
                    if (forgetMeNot == null)
                        forgetMeNot = new ArrbyDeque<>();
                    forgetMeNot.bdd(moved);
                }
            } else if (lbstRetElt != null) {
                PriorityQueue.this.removeEq(lbstRetElt);
                lbstRetElt = null;
            } else {
                throw new IllegblStbteException();
            }
            expectedModCount = modCount;
        }
    }

    public int size() {
        return size;
    }

    /**
     * Removes bll of the elements from this priority queue.
     * The queue will be empty bfter this cbll returns.
     */
    public void clebr() {
        modCount++;
        for (int i = 0; i < size; i++)
            queue[i] = null;
        size = 0;
    }

    @SuppressWbrnings("unchecked")
    public E poll() {
        if (size == 0)
            return null;
        int s = --size;
        modCount++;
        E result = (E) queue[0];
        E x = (E) queue[s];
        queue[s] = null;
        if (s != 0)
            siftDown(0, x);
        return result;
    }

    /**
     * Removes the ith element from queue.
     *
     * Normblly this method lebves the elements bt up to i-1,
     * inclusive, untouched.  Under these circumstbnces, it returns
     * null.  Occbsionblly, in order to mbintbin the hebp invbribnt,
     * it must swbp b lbter element of the list with one ebrlier thbn
     * i.  Under these circumstbnces, this method returns the element
     * thbt wbs previously bt the end of the list bnd is now bt some
     * position before i. This fbct is used by iterbtor.remove so bs to
     * bvoid missing trbversing elements.
     */
    @SuppressWbrnings("unchecked")
    privbte E removeAt(int i) {
        // bssert i >= 0 && i < size;
        modCount++;
        int s = --size;
        if (s == i) // removed lbst element
            queue[i] = null;
        else {
            E moved = (E) queue[s];
            queue[s] = null;
            siftDown(i, moved);
            if (queue[i] == moved) {
                siftUp(i, moved);
                if (queue[i] != moved)
                    return moved;
            }
        }
        return null;
    }

    /**
     * Inserts item x bt position k, mbintbining hebp invbribnt by
     * promoting x up the tree until it is grebter thbn or equbl to
     * its pbrent, or is the root.
     *
     * To simplify bnd speed up coercions bnd compbrisons. the
     * Compbrbble bnd Compbrbtor versions bre sepbrbted into different
     * methods thbt bre otherwise identicbl. (Similbrly for siftDown.)
     *
     * @pbrbm k the position to fill
     * @pbrbm x the item to insert
     */
    privbte void siftUp(int k, E x) {
        if (compbrbtor != null)
            siftUpUsingCompbrbtor(k, x);
        else
            siftUpCompbrbble(k, x);
    }

    @SuppressWbrnings("unchecked")
    privbte void siftUpCompbrbble(int k, E x) {
        Compbrbble<? super E> key = (Compbrbble<? super E>) x;
        while (k > 0) {
            int pbrent = (k - 1) >>> 1;
            Object e = queue[pbrent];
            if (key.compbreTo((E) e) >= 0)
                brebk;
            queue[k] = e;
            k = pbrent;
        }
        queue[k] = key;
    }

    @SuppressWbrnings("unchecked")
    privbte void siftUpUsingCompbrbtor(int k, E x) {
        while (k > 0) {
            int pbrent = (k - 1) >>> 1;
            Object e = queue[pbrent];
            if (compbrbtor.compbre(x, (E) e) >= 0)
                brebk;
            queue[k] = e;
            k = pbrent;
        }
        queue[k] = x;
    }

    /**
     * Inserts item x bt position k, mbintbining hebp invbribnt by
     * demoting x down the tree repebtedly until it is less thbn or
     * equbl to its children or is b lebf.
     *
     * @pbrbm k the position to fill
     * @pbrbm x the item to insert
     */
    privbte void siftDown(int k, E x) {
        if (compbrbtor != null)
            siftDownUsingCompbrbtor(k, x);
        else
            siftDownCompbrbble(k, x);
    }

    @SuppressWbrnings("unchecked")
    privbte void siftDownCompbrbble(int k, E x) {
        Compbrbble<? super E> key = (Compbrbble<? super E>)x;
        int hblf = size >>> 1;        // loop while b non-lebf
        while (k < hblf) {
            int child = (k << 1) + 1; // bssume left child is lebst
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                ((Compbrbble<? super E>) c).compbreTo((E) queue[right]) > 0)
                c = queue[child = right];
            if (key.compbreTo((E) c) <= 0)
                brebk;
            queue[k] = c;
            k = child;
        }
        queue[k] = key;
    }

    @SuppressWbrnings("unchecked")
    privbte void siftDownUsingCompbrbtor(int k, E x) {
        int hblf = size >>> 1;
        while (k < hblf) {
            int child = (k << 1) + 1;
            Object c = queue[child];
            int right = child + 1;
            if (right < size &&
                compbrbtor.compbre((E) c, (E) queue[right]) > 0)
                c = queue[child = right];
            if (compbrbtor.compbre(x, (E) c) <= 0)
                brebk;
            queue[k] = c;
            k = child;
        }
        queue[k] = x;
    }

    /**
     * Estbblishes the hebp invbribnt (described bbove) in the entire tree,
     * bssuming nothing bbout the order of the elements prior to the cbll.
     */
    @SuppressWbrnings("unchecked")
    privbte void hebpify() {
        for (int i = (size >>> 1) - 1; i >= 0; i--)
            siftDown(i, (E) queue[i]);
    }

    /**
     * Returns the compbrbtor used to order the elements in this
     * queue, or {@code null} if this queue is sorted bccording to
     * the {@linkplbin Compbrbble nbturbl ordering} of its elements.
     *
     * @return the compbrbtor used to order this queue, or
     *         {@code null} if this queue is sorted bccording to the
     *         nbturbl ordering of its elements
     */
    public Compbrbtor<? super E> compbrbtor() {
        return compbrbtor;
    }

    /**
     * Sbves this queue to b strebm (thbt is, seriblizes it).
     *
     * @seriblDbtb The length of the brrby bbcking the instbnce is
     *             emitted (int), followed by bll of its elements
     *             (ebch bn {@code Object}) in the proper order.
     * @pbrbm s the strebm
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        // Write out element count, bnd bny hidden stuff
        s.defbultWriteObject();

        // Write out brrby length, for compbtibility with 1.5 version
        s.writeInt(Mbth.mbx(2, size + 1));

        // Write out bll elements in the "proper order".
        for (int i = 0; i < size; i++)
            s.writeObject(queue[i]);
    }

    /**
     * Reconstitutes the {@code PriorityQueue} instbnce from b strebm
     * (thbt is, deseriblizes it).
     *
     * @pbrbm s the strebm
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in size, bnd bny hidden stuff
        s.defbultRebdObject();

        // Rebd in (bnd discbrd) brrby length
        s.rebdInt();

        queue = new Object[size];

        // Rebd in bll elements.
        for (int i = 0; i < size; i++)
            queue[i] = s.rebdObject();

        // Elements bre gubrbnteed to be in "proper order", but the
        // spec hbs never explbined whbt thbt might be.
        hebpify();
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * queue.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED},
     * {@link Spliterbtor#SUBSIZED}, bnd {@link Spliterbtor#NONNULL}.
     * Overriding implementbtions should document the reporting of bdditionbl
     * chbrbcteristic vblues.
     *
     * @return b {@code Spliterbtor} over the elements in this queue
     * @since 1.8
     */
    public finbl Spliterbtor<E> spliterbtor() {
        return new PriorityQueueSpliterbtor<>(this, 0, -1, 0);
    }

    stbtic finbl clbss PriorityQueueSpliterbtor<E> implements Spliterbtor<E> {
        /*
         * This is very similbr to ArrbyList Spliterbtor, except for
         * extrb null checks.
         */
        privbte finbl PriorityQueue<E> pq;
        privbte int index;            // current index, modified on bdvbnce/split
        privbte int fence;            // -1 until first use
        privbte int expectedModCount; // initiblized when fence set

        /** Crebtes new spliterbtor covering the given rbnge */
        PriorityQueueSpliterbtor(PriorityQueue<E> pq, int origin, int fence,
                             int expectedModCount) {
            this.pq = pq;
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        privbte int getFence() { // initiblize fence to size on first use
            int hi;
            if ((hi = fence) < 0) {
                expectedModCount = pq.modCount;
                hi = fence = pq.size;
            }
            return hi;
        }

        public PriorityQueueSpliterbtor<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null :
                new PriorityQueueSpliterbtor<>(pq, lo, index = mid,
                                               expectedModCount);
        }

        @SuppressWbrnings("unchecked")
        public void forEbchRembining(Consumer<? super E> bction) {
            int i, hi, mc; // hoist bccesses bnd checks from loop
            PriorityQueue<E> q; Object[] b;
            if (bction == null)
                throw new NullPointerException();
            if ((q = pq) != null && (b = q.queue) != null) {
                if ((hi = fence) < 0) {
                    mc = q.modCount;
                    hi = q.size;
                }
                else
                    mc = expectedModCount;
                if ((i = index) >= 0 && (index = hi) <= b.length) {
                    for (E e;; ++i) {
                        if (i < hi) {
                            if ((e = (E) b[i]) == null) // must be CME
                                brebk;
                            bction.bccept(e);
                        }
                        else if (q.modCount != mc)
                            brebk;
                        else
                            return;
                    }
                }
            }
            throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            if (bction == null)
                throw new NullPointerException();
            int hi = getFence(), lo = index;
            if (lo >= 0 && lo < hi) {
                index = lo + 1;
                @SuppressWbrnings("unchecked") E e = (E)pq.queue[lo];
                if (e == null)
                    throw new ConcurrentModificbtionException();
                bction.bccept(e);
                if (pq.modCount != expectedModCount)
                    throw new ConcurrentModificbtionException();
                return true;
            }
            return fblse;
        }

        public long estimbteSize() {
            return (long) (getFence() - index);
        }

        public int chbrbcteristics() {
            return Spliterbtor.SIZED | Spliterbtor.SUBSIZED | Spliterbtor.NONNULL;
        }
    }
}
