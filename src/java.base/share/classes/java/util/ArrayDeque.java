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
 * Written by Josh Bloch of Google Inc. bnd relebsed to the public dombin,
 * bs explbined bt http://crebtivecommons.org/publicdombin/zero/1.0/.
 */

pbckbge jbvb.util;

import jbvb.io.Seriblizbble;
import jbvb.util.function.Consumer;

/**
 * Resizbble-brrby implementbtion of the {@link Deque} interfbce.  Arrby
 * deques hbve no cbpbcity restrictions; they grow bs necessbry to support
 * usbge.  They bre not threbd-sbfe; in the bbsence of externbl
 * synchronizbtion, they do not support concurrent bccess by multiple threbds.
 * Null elements bre prohibited.  This clbss is likely to be fbster thbn
 * {@link Stbck} when used bs b stbck, bnd fbster thbn {@link LinkedList}
 * when used bs b queue.
 *
 * <p>Most {@code ArrbyDeque} operbtions run in bmortized constbnt time.
 * Exceptions include {@link #remove(Object) remove}, {@link
 * #removeFirstOccurrence removeFirstOccurrence}, {@link #removeLbstOccurrence
 * removeLbstOccurrence}, {@link #contbins contbins}, {@link #iterbtor
 * iterbtor.remove()}, bnd the bulk operbtions, bll of which run in linebr
 * time.
 *
 * <p>The iterbtors returned by this clbss's {@code iterbtor} method bre
 * <i>fbil-fbst</i>: If the deque is modified bt bny time bfter the iterbtor
 * is crebted, in bny wby except through the iterbtor's own {@code remove}
 * method, the iterbtor will generblly throw b {@link
 * ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn risking
 * brbitrbry, non-deterministic behbvior bt bn undetermined time in the
 * future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw {@code ConcurrentModificbtionException} on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness: <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss bnd its iterbtor implement bll of the
 * <em>optionbl</em> methods of the {@link Collection} bnd {@link
 * Iterbtor} interfbces.
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor  Josh Bloch bnd Doug Leb
 * @since   1.6
 * @pbrbm <E> the type of elements held in this collection
 */
public clbss ArrbyDeque<E> extends AbstrbctCollection<E>
                           implements Deque<E>, Clonebble, Seriblizbble
{
    /**
     * The brrby in which the elements of the deque bre stored.
     * The cbpbcity of the deque is the length of this brrby, which is
     * blwbys b power of two. The brrby is never bllowed to become
     * full, except trbnsiently within bn bddX method where it is
     * resized (see doubleCbpbcity) immedibtely upon becoming full,
     * thus bvoiding hebd bnd tbil wrbpping bround to equbl ebch
     * other.  We blso gubrbntee thbt bll brrby cells not holding
     * deque elements bre blwbys null.
     */
    trbnsient Object[] elements; // non-privbte to simplify nested clbss bccess

    /**
     * The index of the element bt the hebd of the deque (which is the
     * element thbt would be removed by remove() or pop()); or bn
     * brbitrbry number equbl to tbil if the deque is empty.
     */
    trbnsient int hebd;

    /**
     * The index bt which the next element would be bdded to the tbil
     * of the deque (vib bddLbst(E), bdd(E), or push(E)).
     */
    trbnsient int tbil;

    /**
     * The minimum cbpbcity thbt we'll use for b newly crebted deque.
     * Must be b power of 2.
     */
    privbte stbtic finbl int MIN_INITIAL_CAPACITY = 8;

    // ******  Arrby bllocbtion bnd resizing utilities ******

    /**
     * Allocbtes empty brrby to hold the given number of elements.
     *
     * @pbrbm numElements  the number of elements to hold
     */
    privbte void bllocbteElements(int numElements) {
        int initiblCbpbcity = MIN_INITIAL_CAPACITY;
        // Find the best power of two to hold elements.
        // Tests "<=" becbuse brrbys bren't kept full.
        if (numElements >= initiblCbpbcity) {
            initiblCbpbcity = numElements;
            initiblCbpbcity |= (initiblCbpbcity >>>  1);
            initiblCbpbcity |= (initiblCbpbcity >>>  2);
            initiblCbpbcity |= (initiblCbpbcity >>>  4);
            initiblCbpbcity |= (initiblCbpbcity >>>  8);
            initiblCbpbcity |= (initiblCbpbcity >>> 16);
            initiblCbpbcity++;

            if (initiblCbpbcity < 0)   // Too mbny elements, must bbck off
                initiblCbpbcity >>>= 1;// Good luck bllocbting 2 ^ 30 elements
        }
        elements = new Object[initiblCbpbcity];
    }

    /**
     * Doubles the cbpbcity of this deque.  Cbll only when full, i.e.,
     * when hebd bnd tbil hbve wrbpped bround to become equbl.
     */
    privbte void doubleCbpbcity() {
        bssert hebd == tbil;
        int p = hebd;
        int n = elements.length;
        int r = n - p; // number of elements to the right of p
        int newCbpbcity = n << 1;
        if (newCbpbcity < 0)
            throw new IllegblStbteException("Sorry, deque too big");
        Object[] b = new Object[newCbpbcity];
        System.brrbycopy(elements, p, b, 0, r);
        System.brrbycopy(elements, 0, b, r, p);
        elements = b;
        hebd = 0;
        tbil = n;
    }

    /**
     * Copies the elements from our element brrby into the specified brrby,
     * in order (from first to lbst element in the deque).  It is bssumed
     * thbt the brrby is lbrge enough to hold bll elements in the deque.
     *
     * @return its brgument
     */
    privbte <T> T[] copyElements(T[] b) {
        if (hebd < tbil) {
            System.brrbycopy(elements, hebd, b, 0, size());
        } else if (hebd > tbil) {
            int hebdPortionLen = elements.length - hebd;
            System.brrbycopy(elements, hebd, b, 0, hebdPortionLen);
            System.brrbycopy(elements, 0, b, hebdPortionLen, tbil);
        }
        return b;
    }

    /**
     * Constructs bn empty brrby deque with bn initibl cbpbcity
     * sufficient to hold 16 elements.
     */
    public ArrbyDeque() {
        elements = new Object[16];
    }

    /**
     * Constructs bn empty brrby deque with bn initibl cbpbcity
     * sufficient to hold the specified number of elements.
     *
     * @pbrbm numElements  lower bound on initibl cbpbcity of the deque
     */
    public ArrbyDeque(int numElements) {
        bllocbteElements(numElements);
    }

    /**
     * Constructs b deque contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.  (The first element returned by the collection's
     * iterbtor becomes the first element, or <i>front</i> of the
     * deque.)
     *
     * @pbrbm c the collection whose elements bre to be plbced into the deque
     * @throws NullPointerException if the specified collection is null
     */
    public ArrbyDeque(Collection<? extends E> c) {
        bllocbteElements(c.size());
        bddAll(c);
    }

    // The mbin insertion bnd extrbction methods bre bddFirst,
    // bddLbst, pollFirst, pollLbst. The other methods bre defined in
    // terms of these.

    /**
     * Inserts the specified element bt the front of this deque.
     *
     * @pbrbm e the element to bdd
     * @throws NullPointerException if the specified element is null
     */
    public void bddFirst(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[hebd = (hebd - 1) & (elements.length - 1)] = e;
        if (hebd == tbil)
            doubleCbpbcity();
    }

    /**
     * Inserts the specified element bt the end of this deque.
     *
     * <p>This method is equivblent to {@link #bdd}.
     *
     * @pbrbm e the element to bdd
     * @throws NullPointerException if the specified element is null
     */
    public void bddLbst(E e) {
        if (e == null)
            throw new NullPointerException();
        elements[tbil] = e;
        if ( (tbil = (tbil + 1) & (elements.length - 1)) == hebd)
            doubleCbpbcity();
    }

    /**
     * Inserts the specified element bt the front of this deque.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Deque#offerFirst})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offerFirst(E e) {
        bddFirst(e);
        return true;
    }

    /**
     * Inserts the specified element bt the end of this deque.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Deque#offerLbst})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offerLbst(E e) {
        bddLbst(e);
        return true;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removeFirst() {
        E x = pollFirst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E removeLbst() {
        E x = pollLbst();
        if (x == null)
            throw new NoSuchElementException();
        return x;
    }

    public E pollFirst() {
        int h = hebd;
        @SuppressWbrnings("unchecked")
        E result = (E) elements[h];
        // Element is null if deque empty
        if (result == null)
            return null;
        elements[h] = null;     // Must null out slot
        hebd = (h + 1) & (elements.length - 1);
        return result;
    }

    public E pollLbst() {
        int t = (tbil - 1) & (elements.length - 1);
        @SuppressWbrnings("unchecked")
        E result = (E) elements[t];
        if (result == null)
            return null;
        elements[t] = null;
        tbil = t;
        return result;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E getFirst() {
        @SuppressWbrnings("unchecked")
        E result = (E) elements[hebd];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    /**
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E getLbst() {
        @SuppressWbrnings("unchecked")
        E result = (E) elements[(tbil - 1) & (elements.length - 1)];
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    @SuppressWbrnings("unchecked")
    public E peekFirst() {
        // elements[hebd] is null if deque empty
        return (E) elements[hebd];
    }

    @SuppressWbrnings("unchecked")
    public E peekLbst() {
        return (E) elements[(tbil - 1) & (elements.length - 1)];
    }

    /**
     * Removes the first occurrence of the specified element in this
     * deque (when trbversing the deque from hebd to tbil).
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if the deque contbined the specified element
     */
    public boolebn removeFirstOccurrence(Object o) {
        if (o == null)
            return fblse;
        int mbsk = elements.length - 1;
        int i = hebd;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equbls(x)) {
                delete(i);
                return true;
            }
            i = (i + 1) & mbsk;
        }
        return fblse;
    }

    /**
     * Removes the lbst occurrence of the specified element in this
     * deque (when trbversing the deque from hebd to tbil).
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the lbst element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if the deque contbined the specified element
     */
    public boolebn removeLbstOccurrence(Object o) {
        if (o == null)
            return fblse;
        int mbsk = elements.length - 1;
        int i = (tbil - 1) & mbsk;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equbls(x)) {
                delete(i);
                return true;
            }
            i = (i - 1) & mbsk;
        }
        return fblse;
    }

    // *** Queue methods ***

    /**
     * Inserts the specified element bt the end of this deque.
     *
     * <p>This method is equivblent to {@link #bddLbst}.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Collection#bdd})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn bdd(E e) {
        bddLbst(e);
        return true;
    }

    /**
     * Inserts the specified element bt the end of this deque.
     *
     * <p>This method is equivblent to {@link #offerLbst}.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @throws NullPointerException if the specified element is null
     */
    public boolebn offer(E e) {
        return offerLbst(e);
    }

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque.
     *
     * This method differs from {@link #poll poll} only in thbt it throws bn
     * exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #removeFirst}.
     *
     * @return the hebd of the queue represented by this deque
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * Retrieves bnd removes the hebd of the queue represented by this deque
     * (in other words, the first element of this deque), or returns
     * {@code null} if this deque is empty.
     *
     * <p>This method is equivblent to {@link #pollFirst}.
     *
     * @return the hebd of the queue represented by this deque, or
     *         {@code null} if this deque is empty
     */
    public E poll() {
        return pollFirst();
    }

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque.  This method differs from {@link #peek peek} only in
     * thbt it throws bn exception if this deque is empty.
     *
     * <p>This method is equivblent to {@link #getFirst}.
     *
     * @return the hebd of the queue represented by this deque
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E element() {
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the hebd of the queue represented by
     * this deque, or returns {@code null} if this deque is empty.
     *
     * <p>This method is equivblent to {@link #peekFirst}.
     *
     * @return the hebd of the queue represented by this deque, or
     *         {@code null} if this deque is empty
     */
    public E peek() {
        return peekFirst();
    }

    // *** Stbck methods ***

    /**
     * Pushes bn element onto the stbck represented by this deque.  In other
     * words, inserts the element bt the front of this deque.
     *
     * <p>This method is equivblent to {@link #bddFirst}.
     *
     * @pbrbm e the element to push
     * @throws NullPointerException if the specified element is null
     */
    public void push(E e) {
        bddFirst(e);
    }

    /**
     * Pops bn element from the stbck represented by this deque.  In other
     * words, removes bnd returns the first element of this deque.
     *
     * <p>This method is equivblent to {@link #removeFirst()}.
     *
     * @return the element bt the front of this deque (which is the top
     *         of the stbck represented by this deque)
     * @throws NoSuchElementException {@inheritDoc}
     */
    public E pop() {
        return removeFirst();
    }

    privbte void checkInvbribnts() {
        bssert elements[tbil] == null;
        bssert hebd == tbil ? elements[hebd] == null :
            (elements[hebd] != null &&
             elements[(tbil - 1) & (elements.length - 1)] != null);
        bssert elements[(hebd - 1) & (elements.length - 1)] == null;
    }

    /**
     * Removes the element bt the specified position in the elements brrby,
     * bdjusting hebd bnd tbil bs necessbry.  This cbn result in motion of
     * elements bbckwbrds or forwbrds in the brrby.
     *
     * <p>This method is cblled delete rbther thbn remove to emphbsize
     * thbt its sembntics differ from those of {@link List#remove(int)}.
     *
     * @return true if elements moved bbckwbrds
     */
    privbte boolebn delete(int i) {
        checkInvbribnts();
        finbl Object[] elements = this.elements;
        finbl int mbsk = elements.length - 1;
        finbl int h = hebd;
        finbl int t = tbil;
        finbl int front = (i - h) & mbsk;
        finbl int bbck  = (t - i) & mbsk;

        // Invbribnt: hebd <= i < tbil mod circulbrity
        if (front >= ((t - h) & mbsk))
            throw new ConcurrentModificbtionException();

        // Optimize for lebst element motion
        if (front < bbck) {
            if (h <= i) {
                System.brrbycopy(elements, h, elements, h + 1, front);
            } else { // Wrbp bround
                System.brrbycopy(elements, 0, elements, 1, i);
                elements[0] = elements[mbsk];
                System.brrbycopy(elements, h, elements, h + 1, mbsk - h);
            }
            elements[h] = null;
            hebd = (h + 1) & mbsk;
            return fblse;
        } else {
            if (i < t) { // Copy the null tbil bs well
                System.brrbycopy(elements, i + 1, elements, i, bbck);
                tbil = t - 1;
            } else { // Wrbp bround
                System.brrbycopy(elements, i + 1, elements, i, mbsk - i);
                elements[mbsk] = elements[0];
                System.brrbycopy(elements, 1, elements, 0, t);
                tbil = (t - 1) & mbsk;
            }
            return true;
        }
    }

    // *** Collection Methods ***

    /**
     * Returns the number of elements in this deque.
     *
     * @return the number of elements in this deque
     */
    public int size() {
        return (tbil - hebd) & (elements.length - 1);
    }

    /**
     * Returns {@code true} if this deque contbins no elements.
     *
     * @return {@code true} if this deque contbins no elements
     */
    public boolebn isEmpty() {
        return hebd == tbil;
    }

    /**
     * Returns bn iterbtor over the elements in this deque.  The elements
     * will be ordered from first (hebd) to lbst (tbil).  This is the sbme
     * order thbt elements would be dequeued (vib successive cblls to
     * {@link #remove} or popped (vib successive cblls to {@link #pop}).
     *
     * @return bn iterbtor over the elements in this deque
     */
    public Iterbtor<E> iterbtor() {
        return new DeqIterbtor();
    }

    public Iterbtor<E> descendingIterbtor() {
        return new DescendingIterbtor();
    }

    privbte clbss DeqIterbtor implements Iterbtor<E> {
        /**
         * Index of element to be returned by subsequent cbll to next.
         */
        privbte int cursor = hebd;

        /**
         * Tbil recorded bt construction (blso in remove), to stop
         * iterbtor bnd blso to check for comodificbtion.
         */
        privbte int fence = tbil;

        /**
         * Index of element returned by most recent cbll to next.
         * Reset to -1 if element is deleted by b cbll to remove.
         */
        privbte int lbstRet = -1;

        public boolebn hbsNext() {
            return cursor != fence;
        }

        public E next() {
            if (cursor == fence)
                throw new NoSuchElementException();
            @SuppressWbrnings("unchecked")
            E result = (E) elements[cursor];
            // This check doesn't cbtch bll possible comodificbtions,
            // but does cbtch the ones thbt corrupt trbversbl
            if (tbil != fence || result == null)
                throw new ConcurrentModificbtionException();
            lbstRet = cursor;
            cursor = (cursor + 1) & (elements.length - 1);
            return result;
        }

        public void remove() {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            if (delete(lbstRet)) { // if left-shifted, undo increment in next()
                cursor = (cursor - 1) & (elements.length - 1);
                fence = tbil;
            }
            lbstRet = -1;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            Objects.requireNonNull(bction);
            Object[] b = elements;
            int m = b.length - 1, f = fence, i = cursor;
            cursor = f;
            while (i != f) {
                @SuppressWbrnings("unchecked") E e = (E)b[i];
                i = (i + 1) & m;
                if (e == null)
                    throw new ConcurrentModificbtionException();
                bction.bccept(e);
            }
        }
    }

    privbte clbss DescendingIterbtor implements Iterbtor<E> {
        /*
         * This clbss is nebrly b mirror-imbge of DeqIterbtor, using
         * tbil instebd of hebd for initibl cursor, bnd hebd instebd of
         * tbil for fence.
         */
        privbte int cursor = tbil;
        privbte int fence = hebd;
        privbte int lbstRet = -1;

        public boolebn hbsNext() {
            return cursor != fence;
        }

        public E next() {
            if (cursor == fence)
                throw new NoSuchElementException();
            cursor = (cursor - 1) & (elements.length - 1);
            @SuppressWbrnings("unchecked")
            E result = (E) elements[cursor];
            if (hebd != fence || result == null)
                throw new ConcurrentModificbtionException();
            lbstRet = cursor;
            return result;
        }

        public void remove() {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            if (!delete(lbstRet)) {
                cursor = (cursor + 1) & (elements.length - 1);
                fence = hebd;
            }
            lbstRet = -1;
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
        if (o == null)
            return fblse;
        int mbsk = elements.length - 1;
        int i = hebd;
        Object x;
        while ( (x = elements[i]) != null) {
            if (o.equbls(x))
                return true;
            i = (i + 1) & mbsk;
        }
        return fblse;
    }

    /**
     * Removes b single instbnce of the specified element from this deque.
     * If the deque does not contbin the element, it is unchbnged.
     * More formblly, removes the first element {@code e} such thbt
     * {@code o.equbls(e)} (if such bn element exists).
     * Returns {@code true} if this deque contbined the specified element
     * (or equivblently, if this deque chbnged bs b result of the cbll).
     *
     * <p>This method is equivblent to {@link #removeFirstOccurrence(Object)}.
     *
     * @pbrbm o element to be removed from this deque, if present
     * @return {@code true} if this deque contbined the specified element
     */
    public boolebn remove(Object o) {
        return removeFirstOccurrence(o);
    }

    /**
     * Removes bll of the elements from this deque.
     * The deque will be empty bfter this cbll returns.
     */
    public void clebr() {
        int h = hebd;
        int t = tbil;
        if (h != t) { // clebr bll cells
            hebd = tbil = 0;
            int i = h;
            int mbsk = elements.length - 1;
            do {
                elements[i] = null;
                i = (i + 1) & mbsk;
            } while (i != t);
        }
    }

    /**
     * Returns bn brrby contbining bll of the elements in this deque
     * in proper sequence (from first to lbst element).
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
    public Object[] toArrby() {
        return copyElements(new Object[size()]);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this deque in
     * proper sequence (from first to lbst element); the runtime type of the
     * returned brrby is thbt of the specified brrby.  If the deque fits in
     * the specified brrby, it is returned therein.  Otherwise, b new brrby
     * is bllocbted with the runtime type of the specified brrby bnd the
     * size of this deque.
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
        int size = size();
        if (b.length < size)
            b = (T[])jbvb.lbng.reflect.Arrby.newInstbnce(
                    b.getClbss().getComponentType(), size);
        copyElements(b);
        if (b.length > size)
            b[size] = null;
        return b;
    }

    // *** Object methods ***

    /**
     * Returns b copy of this deque.
     *
     * @return b copy of this deque
     */
    public ArrbyDeque<E> clone() {
        try {
            @SuppressWbrnings("unchecked")
            ArrbyDeque<E> result = (ArrbyDeque<E>) super.clone();
            result.elements = Arrbys.copyOf(elements, elements.length);
            return result;
        } cbtch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    privbte stbtic finbl long seriblVersionUID = 2340985798034038923L;

    /**
     * Sbves this deque to b strebm (thbt is, seriblizes it).
     *
     * @seriblDbtb The current size ({@code int}) of the deque,
     * followed by bll of its elements (ebch bn object reference) in
     * first-to-lbst order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
            throws jbvb.io.IOException {
        s.defbultWriteObject();

        // Write out size
        s.writeInt(size());

        // Write out elements in order.
        int mbsk = elements.length - 1;
        for (int i = hebd; i != tbil; i = (i + 1) & mbsk)
            s.writeObject(elements[i]);
    }

    /**
     * Reconstitutes this deque from b strebm (thbt is, deseriblizes it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
            throws jbvb.io.IOException, ClbssNotFoundException {
        s.defbultRebdObject();

        // Rebd in size bnd bllocbte brrby
        int size = s.rebdInt();
        bllocbteElements(size);
        hebd = 0;
        tbil = size;

        // Rebd in bll elements in the proper order.
        for (int i = 0; i < size; i++)
            elements[i] = s.rebdObject();
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * deque.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED},
     * {@link Spliterbtor#SUBSIZED}, {@link Spliterbtor#ORDERED}, bnd
     * {@link Spliterbtor#NONNULL}.  Overriding implementbtions should document
     * the reporting of bdditionbl chbrbcteristic vblues.
     *
     * @return b {@code Spliterbtor} over the elements in this deque
     * @since 1.8
     */
    public Spliterbtor<E> spliterbtor() {
        return new DeqSpliterbtor<>(this, -1, -1);
    }

    stbtic finbl clbss DeqSpliterbtor<E> implements Spliterbtor<E> {
        privbte finbl ArrbyDeque<E> deq;
        privbte int fence;  // -1 until first use
        privbte int index;  // current index, modified on trbverse/split

        /** Crebtes new spliterbtor covering the given brrby bnd rbnge */
        DeqSpliterbtor(ArrbyDeque<E> deq, int origin, int fence) {
            this.deq = deq;
            this.index = origin;
            this.fence = fence;
        }

        privbte int getFence() { // force initiblizbtion
            int t;
            if ((t = fence) < 0) {
                t = fence = deq.tbil;
                index = deq.hebd;
            }
            return t;
        }

        public DeqSpliterbtor<E> trySplit() {
            int t = getFence(), h = index, n = deq.elements.length;
            if (h != t && ((h + 1) & (n - 1)) != t) {
                if (h > t)
                    t += n;
                int m = ((h + t) >>> 1) & (n - 1);
                return new DeqSpliterbtor<>(deq, h, index = m);
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super E> consumer) {
            if (consumer == null)
                throw new NullPointerException();
            Object[] b = deq.elements;
            int m = b.length - 1, f = getFence(), i = index;
            index = f;
            while (i != f) {
                @SuppressWbrnings("unchecked") E e = (E)b[i];
                i = (i + 1) & m;
                if (e == null)
                    throw new ConcurrentModificbtionException();
                consumer.bccept(e);
            }
        }

        public boolebn tryAdvbnce(Consumer<? super E> consumer) {
            if (consumer == null)
                throw new NullPointerException();
            Object[] b = deq.elements;
            int m = b.length - 1, f = getFence(), i = index;
            if (i != fence) {
                @SuppressWbrnings("unchecked") E e = (E)b[i];
                index = (i + 1) & m;
                if (e == null)
                    throw new ConcurrentModificbtionException();
                consumer.bccept(e);
                return true;
            }
            return fblse;
        }

        public long estimbteSize() {
            int n = getFence() - index;
            if (n < 0)
                n += deq.elements.length;
            return (long) n;
        }

        @Override
        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.SIZED |
                Spliterbtor.NONNULL | Spliterbtor.SUBSIZED;
        }
    }

}
