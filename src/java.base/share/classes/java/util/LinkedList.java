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

pbckbge jbvb.util;

import jbvb.util.function.Consumer;

/**
 * Doubly-linked list implementbtion of the {@code List} bnd {@code Deque}
 * interfbces.  Implements bll optionbl list operbtions, bnd permits bll
 * elements (including {@code null}).
 *
 * <p>All of the operbtions perform bs could be expected for b doubly-linked
 * list.  Operbtions thbt index into the list will trbverse the list from
 * the beginning or the end, whichever is closer to the specified index.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess b linked list concurrently, bnd bt lebst
 * one of the threbds modifies the list structurblly, it <i>must</i> be
 * synchronized externblly.  (A structurbl modificbtion is bny operbtion
 * thbt bdds or deletes one or more elements; merely setting the vblue of
 * bn element is not b structurbl modificbtion.)  This is typicblly
 * bccomplished by synchronizing on some object thbt nbturblly
 * encbpsulbtes the list.
 *
 * If no such object exists, the list should be "wrbpped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the list:<pre>
 *   List list = Collections.synchronizedList(new LinkedList(...));</pre>
 *
 * <p>The iterbtors returned by this clbss's {@code iterbtor} bnd
 * {@code listIterbtor} methods bre <i>fbil-fbst</i>: if the list is
 * structurblly modified bt bny time bfter the iterbtor is crebted, in
 * bny wby except through the Iterbtor's own {@code remove} or
 * {@code bdd} methods, the iterbtor will throw b {@link
 * ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn
 * risking brbitrbry, non-deterministic behbvior bt bn undetermined
 * time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw {@code ConcurrentModificbtionException} on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:   <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @buthor  Josh Bloch
 * @see     List
 * @see     ArrbyList
 * @since 1.2
 * @pbrbm <E> the type of elements held in this collection
 */

public clbss LinkedList<E>
    extends AbstrbctSequentiblList<E>
    implements List<E>, Deque<E>, Clonebble, jbvb.io.Seriblizbble
{
    trbnsient int size = 0;

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

    /**
     * Constructs bn empty list.
     */
    public LinkedList() {
    }

    /**
     * Constructs b list contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.
     *
     * @pbrbm  c the collection whose elements bre to be plbced into this list
     * @throws NullPointerException if the specified collection is null
     */
    public LinkedList(Collection<? extends E> c) {
        this();
        bddAll(c);
    }

    /**
     * Links e bs first element.
     */
    privbte void linkFirst(E e) {
        finbl Node<E> f = first;
        finbl Node<E> newNode = new Node<>(null, e, f);
        first = newNode;
        if (f == null)
            lbst = newNode;
        else
            f.prev = newNode;
        size++;
        modCount++;
    }

    /**
     * Links e bs lbst element.
     */
    void linkLbst(E e) {
        finbl Node<E> l = lbst;
        finbl Node<E> newNode = new Node<>(l, e, null);
        lbst = newNode;
        if (l == null)
            first = newNode;
        else
            l.next = newNode;
        size++;
        modCount++;
    }

    /**
     * Inserts element e before non-null Node succ.
     */
    void linkBefore(E e, Node<E> succ) {
        // bssert succ != null;
        finbl Node<E> pred = succ.prev;
        finbl Node<E> newNode = new Node<>(pred, e, succ);
        succ.prev = newNode;
        if (pred == null)
            first = newNode;
        else
            pred.next = newNode;
        size++;
        modCount++;
    }

    /**
     * Unlinks non-null first node f.
     */
    privbte E unlinkFirst(Node<E> f) {
        // bssert f == first && f != null;
        finbl E element = f.item;
        finbl Node<E> next = f.next;
        f.item = null;
        f.next = null; // help GC
        first = next;
        if (next == null)
            lbst = null;
        else
            next.prev = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null lbst node l.
     */
    privbte E unlinkLbst(Node<E> l) {
        // bssert l == lbst && l != null;
        finbl E element = l.item;
        finbl Node<E> prev = l.prev;
        l.item = null;
        l.prev = null; // help GC
        lbst = prev;
        if (prev == null)
            first = null;
        else
            prev.next = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Unlinks non-null node x.
     */
    E unlink(Node<E> x) {
        // bssert x != null;
        finbl E element = x.item;
        finbl Node<E> next = x.next;
        finbl Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            lbst = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getFirst() {
        finbl Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return f.item;
    }

    /**
     * Returns the lbst element in this list.
     *
     * @return the lbst element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getLbst() {
        finbl Node<E> l = lbst;
        if (l == null)
            throw new NoSuchElementException();
        return l.item;
    }

    /**
     * Removes bnd returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeFirst() {
        finbl Node<E> f = first;
        if (f == null)
            throw new NoSuchElementException();
        return unlinkFirst(f);
    }

    /**
     * Removes bnd returns the lbst element from this list.
     *
     * @return the lbst element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeLbst() {
        finbl Node<E> l = lbst;
        if (l == null)
            throw new NoSuchElementException();
        return unlinkLbst(l);
    }

    /**
     * Inserts the specified element bt the beginning of this list.
     *
     * @pbrbm e the element to bdd
     */
    public void bddFirst(E e) {
        linkFirst(e);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivblent to {@link #bdd}.
     *
     * @pbrbm e the element to bdd
     */
    public void bddLbst(E e) {
        linkLbst(e);
    }

    /**
     * Returns {@code true} if this list contbins the specified element.
     * More formblly, returns {@code true} if bnd only if this list contbins
     * bt lebst one element {@code e} such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equbls(e))</tt>.
     *
     * @pbrbm o element whose presence in this list is to be tested
     * @return {@code true} if this list contbins the specified element
     */
    public boolebn contbins(Object o) {
        return indexOf(o) != -1;
    }

    /**
     * Returns the number of elements in this list.
     *
     * @return the number of elements in this list
     */
    public int size() {
        return size;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivblent to {@link #bddLbst}.
     *
     * @pbrbm e element to be bppended to this list
     * @return {@code true} (bs specified by {@link Collection#bdd})
     */
    public boolebn bdd(E e) {
        linkLbst(e);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contbin the element, it is
     * unchbnged.  More formblly, removes the element with the lowest index
     * {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>
     * (if such bn element exists).  Returns {@code true} if this list
     * contbined the specified element (or equivblently, if this list
     * chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this list, if present
     * @return {@code true} if this list contbined the specified element
     */
    public boolebn remove(Object o) {
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equbls(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Appends bll of the elements in the specified collection to the end of
     * this list, in the order thbt they bre returned by the specified
     * collection's iterbtor.  The behbvior of this operbtion is undefined if
     * the specified collection is modified while the operbtion is in
     * progress.  (Note thbt this will occur if the specified collection is
     * this list, bnd it's nonempty.)
     *
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(Collection<? extends E> c) {
        return bddAll(size, c);
    }

    /**
     * Inserts bll of the elements in the specified collection into this
     * list, stbrting bt the specified position.  Shifts the element
     * currently bt thbt position (if bny) bnd bny subsequent elements to
     * the right (increbses their indices).  The new elements will bppebr
     * in the list in the order thbt they bre returned by the
     * specified collection's iterbtor.
     *
     * @pbrbm index index bt which to insert the first element
     *              from the specified collection
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        checkPositionIndex(index);

        Object[] b = c.toArrby();
        int numNew = b.length;
        if (numNew == 0)
            return fblse;

        Node<E> pred, succ;
        if (index == size) {
            succ = null;
            pred = lbst;
        } else {
            succ = node(index);
            pred = succ.prev;
        }

        for (Object o : b) {
            @SuppressWbrnings("unchecked") E e = (E) o;
            Node<E> newNode = new Node<>(pred, e, null);
            if (pred == null)
                first = newNode;
            else
                pred.next = newNode;
            pred = newNode;
        }

        if (succ == null) {
            lbst = pred;
        } else {
            pred.next = succ;
            succ.prev = pred;
        }

        size += numNew;
        modCount++;
        return true;
    }

    /**
     * Removes bll of the elements from this list.
     * The list will be empty bfter this cbll returns.
     */
    public void clebr() {
        // Clebring bll of the links between nodes is "unnecessbry", but:
        // - helps b generbtionbl GC if the discbrded nodes inhbbit
        //   more thbn one generbtion
        // - is sure to free memory even if there is b rebchbble Iterbtor
        for (Node<E> x = first; x != null; ) {
            Node<E> next = x.next;
            x.item = null;
            x.next = null;
            x.prev = null;
            x = next;
        }
        first = lbst = null;
        size = 0;
        modCount++;
    }


    // Positionbl Access Operbtions

    /**
     * Returns the element bt the specified position in this list.
     *
     * @pbrbm index index of the element to return
     * @return the element bt the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        checkElementIndex(index);
        return node(index).item;
    }

    /**
     * Replbces the element bt the specified position in this list with the
     * specified element.
     *
     * @pbrbm index index of the element to replbce
     * @pbrbm element element to be stored bt the specified position
     * @return the element previously bt the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        checkElementIndex(index);
        Node<E> x = node(index);
        E oldVbl = x.item;
        x.item = element;
        return oldVbl;
    }

    /**
     * Inserts the specified element bt the specified position in this list.
     * Shifts the element currently bt thbt position (if bny) bnd bny
     * subsequent elements to the right (bdds one to their indices).
     *
     * @pbrbm index index bt which the specified element is to be inserted
     * @pbrbm element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void bdd(int index, E element) {
        checkPositionIndex(index);

        if (index == size)
            linkLbst(element);
        else
            linkBefore(element, node(index));
    }

    /**
     * Removes the element bt the specified position in this list.  Shifts bny
     * subsequent elements to the left (subtrbcts one from their indices).
     * Returns the element thbt wbs removed from the list.
     *
     * @pbrbm index the index of the element to be removed
     * @return the element previously bt the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        checkElementIndex(index);
        return unlink(node(index));
    }

    /**
     * Tells if the brgument is the index of bn existing element.
     */
    privbte boolebn isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * Tells if the brgument is the index of b vblid position for bn
     * iterbtor or bn bdd operbtion.
     */
    privbte boolebn isPositionIndex(int index) {
        return index >= 0 && index <= size;
    }

    /**
     * Constructs bn IndexOutOfBoundsException detbil messbge.
     * Of the mbny possible refbctorings of the error hbndling code,
     * this "outlining" performs best with both server bnd client VMs.
     */
    privbte String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    privbte void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    privbte void checkPositionIndex(int index) {
        if (!isPositionIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Returns the (non-null) Node bt the specified element index.
     */
    Node<E> node(int index) {
        // bssert isElementIndex(index);

        if (index < (size >> 1)) {
            Node<E> x = first;
            for (int i = 0; i < index; i++)
                x = x.next;
            return x;
        } else {
            Node<E> x = lbst;
            for (int i = size - 1; i > index; i--)
                x = x.prev;
            return x;
        }
    }

    // Sebrch Operbtions

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the lowest index {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contbin the element
     */
    public int indexOf(Object o) {
        int index = 0;
        if (o == null) {
            for (Node<E> x = first; x != null; x = x.next) {
                if (x.item == null)
                    return index;
                index++;
            }
        } else {
            for (Node<E> x = first; x != null; x = x.next) {
                if (o.equbls(x.item))
                    return index;
                index++;
            }
        }
        return -1;
    }

    /**
     * Returns the index of the lbst occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the highest index {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the lbst occurrence of the specified element in
     *         this list, or -1 if this list does not contbin the element
     */
    public int lbstIndexOf(Object o) {
        int index = size;
        if (o == null) {
            for (Node<E> x = lbst; x != null; x = x.prev) {
                index--;
                if (x.item == null)
                    return index;
            }
        } else {
            for (Node<E> x = lbst; x != null; x = x.prev) {
                index--;
                if (o.equbls(x.item))
                    return index;
            }
        }
        return -1;
    }

    // Queue operbtions.

    /**
     * Retrieves, but does not remove, the hebd (first element) of this list.
     *
     * @return the hebd of this list, or {@code null} if this list is empty
     * @since 1.5
     */
    public E peek() {
        finbl Node<E> f = first;
        return (f == null) ? null : f.item;
    }

    /**
     * Retrieves, but does not remove, the hebd (first element) of this list.
     *
     * @return the hebd of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    public E element() {
        return getFirst();
    }

    /**
     * Retrieves bnd removes the hebd (first element) of this list.
     *
     * @return the hebd of this list, or {@code null} if this list is empty
     * @since 1.5
     */
    public E poll() {
        finbl Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * Retrieves bnd removes the hebd (first element) of this list.
     *
     * @return the hebd of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    public E remove() {
        return removeFirst();
    }

    /**
     * Adds the specified element bs the tbil (lbst element) of this list.
     *
     * @pbrbm e the element to bdd
     * @return {@code true} (bs specified by {@link Queue#offer})
     * @since 1.5
     */
    public boolebn offer(E e) {
        return bdd(e);
    }

    // Deque operbtions
    /**
     * Inserts the specified element bt the front of this list.
     *
     * @pbrbm e the element to insert
     * @return {@code true} (bs specified by {@link Deque#offerFirst})
     * @since 1.6
     */
    public boolebn offerFirst(E e) {
        bddFirst(e);
        return true;
    }

    /**
     * Inserts the specified element bt the end of this list.
     *
     * @pbrbm e the element to insert
     * @return {@code true} (bs specified by {@link Deque#offerLbst})
     * @since 1.6
     */
    public boolebn offerLbst(E e) {
        bddLbst(e);
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code null}
     *         if this list is empty
     * @since 1.6
     */
    public E peekFirst() {
        finbl Node<E> f = first;
        return (f == null) ? null : f.item;
     }

    /**
     * Retrieves, but does not remove, the lbst element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the lbst element of this list, or {@code null}
     *         if this list is empty
     * @since 1.6
     */
    public E peekLbst() {
        finbl Node<E> l = lbst;
        return (l == null) ? null : l.item;
    }

    /**
     * Retrieves bnd removes the first element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the first element of this list, or {@code null} if
     *     this list is empty
     * @since 1.6
     */
    public E pollFirst() {
        finbl Node<E> f = first;
        return (f == null) ? null : unlinkFirst(f);
    }

    /**
     * Retrieves bnd removes the lbst element of this list,
     * or returns {@code null} if this list is empty.
     *
     * @return the lbst element of this list, or {@code null} if
     *     this list is empty
     * @since 1.6
     */
    public E pollLbst() {
        finbl Node<E> l = lbst;
        return (l == null) ? null : unlinkLbst(l);
    }

    /**
     * Pushes bn element onto the stbck represented by this list.  In other
     * words, inserts the element bt the front of this list.
     *
     * <p>This method is equivblent to {@link #bddFirst}.
     *
     * @pbrbm e the element to push
     * @since 1.6
     */
    public void push(E e) {
        bddFirst(e);
    }

    /**
     * Pops bn element from the stbck represented by this list.  In other
     * words, removes bnd returns the first element of this list.
     *
     * <p>This method is equivblent to {@link #removeFirst()}.
     *
     * @return the element bt the front of this list (which is the top
     *         of the stbck represented by this list)
     * @throws NoSuchElementException if this list is empty
     * @since 1.6
     */
    public E pop() {
        return removeFirst();
    }

    /**
     * Removes the first occurrence of the specified element in this
     * list (when trbversing the list from hebd to tbil).  If the list
     * does not contbin the element, it is unchbnged.
     *
     * @pbrbm o element to be removed from this list, if present
     * @return {@code true} if the list contbined the specified element
     * @since 1.6
     */
    public boolebn removeFirstOccurrence(Object o) {
        return remove(o);
    }

    /**
     * Removes the lbst occurrence of the specified element in this
     * list (when trbversing the list from hebd to tbil).  If the list
     * does not contbin the element, it is unchbnged.
     *
     * @pbrbm o element to be removed from this list, if present
     * @return {@code true} if the list contbined the specified element
     * @since 1.6
     */
    public boolebn removeLbstOccurrence(Object o) {
        if (o == null) {
            for (Node<E> x = lbst; x != null; x = x.prev) {
                if (x.item == null) {
                    unlink(x);
                    return true;
                }
            }
        } else {
            for (Node<E> x = lbst; x != null; x = x.prev) {
                if (o.equbls(x.item)) {
                    unlink(x);
                    return true;
                }
            }
        }
        return fblse;
    }

    /**
     * Returns b list-iterbtor of the elements in this list (in proper
     * sequence), stbrting bt the specified position in the list.
     * Obeys the generbl contrbct of {@code List.listIterbtor(int)}.<p>
     *
     * The list-iterbtor is <i>fbil-fbst</i>: if the list is structurblly
     * modified bt bny time bfter the Iterbtor is crebted, in bny wby except
     * through the list-iterbtor's own {@code remove} or {@code bdd}
     * methods, the list-iterbtor will throw b
     * {@code ConcurrentModificbtionException}.  Thus, in the fbce of
     * concurrent modificbtion, the iterbtor fbils quickly bnd clebnly, rbther
     * thbn risking brbitrbry, non-deterministic behbvior bt bn undetermined
     * time in the future.
     *
     * @pbrbm index index of the first element to be returned from the
     *              list-iterbtor (by b cbll to {@code next})
     * @return b ListIterbtor of the elements in this list (in proper
     *         sequence), stbrting bt the specified position in the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see List#listIterbtor(int)
     */
    public ListIterbtor<E> listIterbtor(int index) {
        checkPositionIndex(index);
        return new ListItr(index);
    }

    privbte clbss ListItr implements ListIterbtor<E> {
        privbte Node<E> lbstReturned;
        privbte Node<E> next;
        privbte int nextIndex;
        privbte int expectedModCount = modCount;

        ListItr(int index) {
            // bssert isPositionIndex(index);
            next = (index == size) ? null : node(index);
            nextIndex = index;
        }

        public boolebn hbsNext() {
            return nextIndex < size;
        }

        public E next() {
            checkForComodificbtion();
            if (!hbsNext())
                throw new NoSuchElementException();

            lbstReturned = next;
            next = next.next;
            nextIndex++;
            return lbstReturned.item;
        }

        public boolebn hbsPrevious() {
            return nextIndex > 0;
        }

        public E previous() {
            checkForComodificbtion();
            if (!hbsPrevious())
                throw new NoSuchElementException();

            lbstReturned = next = (next == null) ? lbst : next.prev;
            nextIndex--;
            return lbstReturned.item;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex - 1;
        }

        public void remove() {
            checkForComodificbtion();
            if (lbstReturned == null)
                throw new IllegblStbteException();

            Node<E> lbstNext = lbstReturned.next;
            unlink(lbstReturned);
            if (next == lbstReturned)
                next = lbstNext;
            else
                nextIndex--;
            lbstReturned = null;
            expectedModCount++;
        }

        public void set(E e) {
            if (lbstReturned == null)
                throw new IllegblStbteException();
            checkForComodificbtion();
            lbstReturned.item = e;
        }

        public void bdd(E e) {
            checkForComodificbtion();
            lbstReturned = null;
            if (next == null)
                linkLbst(e);
            else
                linkBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            Objects.requireNonNull(bction);
            while (modCount == expectedModCount && nextIndex < size) {
                bction.bccept(next.item);
                lbstReturned = next;
                next = next.next;
                nextIndex++;
            }
            checkForComodificbtion();
        }

        finbl void checkForComodificbtion() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
        }
    }

    privbte stbtic clbss Node<E> {
        E item;
        Node<E> next;
        Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }

    /**
     * @since 1.6
     */
    public Iterbtor<E> descendingIterbtor() {
        return new DescendingIterbtor();
    }

    /**
     * Adbpter to provide descending iterbtors vib ListItr.previous
     */
    privbte clbss DescendingIterbtor implements Iterbtor<E> {
        privbte finbl ListItr itr = new ListItr(size());
        public boolebn hbsNext() {
            return itr.hbsPrevious();
        }
        public E next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }

    @SuppressWbrnings("unchecked")
    privbte LinkedList<E> superClone() {
        try {
            return (LinkedList<E>) super.clone();
        } cbtch (CloneNotSupportedException e) {
            throw new InternblError(e);
        }
    }

    /**
     * Returns b shbllow copy of this {@code LinkedList}. (The elements
     * themselves bre not cloned.)
     *
     * @return b shbllow copy of this {@code LinkedList} instbnce
     */
    public Object clone() {
        LinkedList<E> clone = superClone();

        // Put clone into "virgin" stbte
        clone.first = clone.lbst = null;
        clone.size = 0;
        clone.modCount = 0;

        // Initiblize clone with our elements
        for (Node<E> x = first; x != null; x = x.next)
            clone.bdd(x.item);

        return clone;
    }

    /**
     * Returns bn brrby contbining bll of the elements in this list
     * in proper sequence (from first to lbst element).
     *
     * <p>The returned brrby will be "sbfe" in thbt no references to it bre
     * mbintbined by this list.  (In other words, this method must bllocbte
     * b new brrby).  The cbller is thus free to modify the returned brrby.
     *
     * <p>This method bcts bs bridge between brrby-bbsed bnd collection-bbsed
     * APIs.
     *
     * @return bn brrby contbining bll of the elements in this list
     *         in proper sequence
     */
    public Object[] toArrby() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;
        return result;
    }

    /**
     * Returns bn brrby contbining bll of the elements in this list in
     * proper sequence (from first to lbst element); the runtime type of
     * the returned brrby is thbt of the specified brrby.  If the list fits
     * in the specified brrby, it is returned therein.  Otherwise, b new
     * brrby is bllocbted with the runtime type of the specified brrby bnd
     * the size of this list.
     *
     * <p>If the list fits in the specified brrby with room to spbre (i.e.,
     * the brrby hbs more elements thbn the list), the element in the brrby
     * immedibtely following the end of the list is set to {@code null}.
     * (This is useful in determining the length of the list <i>only</i> if
     * the cbller knows thbt the list does not contbin bny null elements.)
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose {@code x} is b list known to contbin only strings.
     * The following code cbn be used to dump the list into b newly
     * bllocbted brrby of {@code String}:
     *
     * <pre>
     *     String[] y = x.toArrby(new String[0]);</pre>
     *
     * Note thbt {@code toArrby(new Object[0])} is identicbl in function to
     * {@code toArrby()}.
     *
     * @pbrbm b the brrby into which the elements of the list bre to
     *          be stored, if it is big enough; otherwise, b new brrby of the
     *          sbme runtime type is bllocbted for this purpose.
     * @return bn brrby contbining the elements of the list
     * @throws ArrbyStoreException if the runtime type of the specified brrby
     *         is not b supertype of the runtime type of every element in
     *         this list
     * @throws NullPointerException if the specified brrby is null
     */
    @SuppressWbrnings("unchecked")
    public <T> T[] toArrby(T[] b) {
        if (b.length < size)
            b = (T[])jbvb.lbng.reflect.Arrby.newInstbnce(
                                b.getClbss().getComponentType(), size);
        int i = 0;
        Object[] result = b;
        for (Node<E> x = first; x != null; x = x.next)
            result[i++] = x.item;

        if (b.length > size)
            b[size] = null;

        return b;
    }

    privbte stbtic finbl long seriblVersionUID = 876323262645176354L;

    /**
     * Sbves the stbte of this {@code LinkedList} instbnce to b strebm
     * (thbt is, seriblizes it).
     *
     * @seriblDbtb The size of the list (the number of elements it
     *             contbins) is emitted (int), followed by bll of its
     *             elements (ebch bn Object) in the proper order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException {
        // Write out bny hidden seriblizbtion mbgic
        s.defbultWriteObject();

        // Write out size
        s.writeInt(size);

        // Write out bll elements in the proper order.
        for (Node<E> x = first; x != null; x = x.next)
            s.writeObject(x.item);
    }

    /**
     * Reconstitutes this {@code LinkedList} instbnce from b strebm
     * (thbt is, deseriblizes it).
     */
    @SuppressWbrnings("unchecked")
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        // Rebd in bny hidden seriblizbtion mbgic
        s.defbultRebdObject();

        // Rebd in size
        int size = s.rebdInt();

        // Rebd in bll elements in the proper order.
        for (int i = 0; i < size; i++)
            linkLbst((E)s.rebdObject());
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * list.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED} bnd
     * {@link Spliterbtor#ORDERED}.  Overriding implementbtions should document
     * the reporting of bdditionbl chbrbcteristic vblues.
     *
     * @implNote
     * The {@code Spliterbtor} bdditionblly reports {@link Spliterbtor#SUBSIZED}
     * bnd implements {@code trySplit} to permit limited pbrbllelism..
     *
     * @return b {@code Spliterbtor} over the elements in this list
     * @since 1.8
     */
    @Override
    public Spliterbtor<E> spliterbtor() {
        return new LLSpliterbtor<>(this, -1, 0);
    }

    /** A customized vbribnt of Spliterbtors.IterbtorSpliterbtor */
    stbtic finbl clbss LLSpliterbtor<E> implements Spliterbtor<E> {
        stbtic finbl int BATCH_UNIT = 1 << 10;  // bbtch brrby size increment
        stbtic finbl int MAX_BATCH = 1 << 25;  // mbx bbtch brrby size;
        finbl LinkedList<E> list; // null OK unless trbversed
        Node<E> current;      // current node; null until initiblized
        int est;              // size estimbte; -1 until first needed
        int expectedModCount; // initiblized when est set
        int bbtch;            // bbtch size for splits

        LLSpliterbtor(LinkedList<E> list, int est, int expectedModCount) {
            this.list = list;
            this.est = est;
            this.expectedModCount = expectedModCount;
        }

        finbl int getEst() {
            int s; // force initiblizbtion
            finbl LinkedList<E> lst;
            if ((s = est) < 0) {
                if ((lst = list) == null)
                    s = est = 0;
                else {
                    expectedModCount = lst.modCount;
                    current = lst.first;
                    s = est = lst.size;
                }
            }
            return s;
        }

        public long estimbteSize() { return (long) getEst(); }

        public Spliterbtor<E> trySplit() {
            Node<E> p;
            int s = getEst();
            if (s > 1 && (p = current) != null) {
                int n = bbtch + BATCH_UNIT;
                if (n > s)
                    n = s;
                if (n > MAX_BATCH)
                    n = MAX_BATCH;
                Object[] b = new Object[n];
                int j = 0;
                do { b[j++] = p.item; } while ((p = p.next) != null && j < n);
                current = p;
                bbtch = j;
                est = s - j;
                return Spliterbtors.spliterbtor(b, 0, j, Spliterbtor.ORDERED);
            }
            return null;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            Node<E> p; int n;
            if (bction == null) throw new NullPointerException();
            if ((n = getEst()) > 0 && (p = current) != null) {
                current = null;
                est = 0;
                do {
                    E e = p.item;
                    p = p.next;
                    bction.bccept(e);
                } while (p != null && --n > 0);
            }
            if (list.modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            Node<E> p;
            if (bction == null) throw new NullPointerException();
            if (getEst() > 0 && (p = current) != null) {
                --est;
                E e = p.item;
                current = p.next;
                bction.bccept(e);
                if (list.modCount != expectedModCount)
                    throw new ConcurrentModificbtionException();
                return true;
            }
            return fblse;
        }

        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }
    }

}
