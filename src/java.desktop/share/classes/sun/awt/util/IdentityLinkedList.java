/*
 * Copyright (c) 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.util;

import jbvb.util.AbstrbctSequentiblList;
import jbvb.util.Collection;
import jbvb.util.ConcurrentModificbtionException;
import jbvb.util.Deque;
import jbvb.util.Iterbtor;
import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.NoSuchElementException;

/**
 * Linked list implementbtion of the <tt>List</tt> interfbce.  Implements bll
 * optionbl list operbtions, bnd permits bll elements (including
 * <tt>null</tt>).  In bddition to implementing the <tt>List</tt> interfbce,
 * the <tt>IdentityLinkedList</tt> clbss provides uniformly nbmed methods to
 * <tt>get</tt>, <tt>remove</tt> bnd <tt>insert</tt> bn element bt the
 * beginning bnd end of the list.  These operbtions bllow linked lists to be
 * used bs b stbck, {@linkplbin Queue queue}, or {@linkplbin Deque
 * double-ended queue}. <p>
 *
 * The clbss implements the <tt>Deque</tt> interfbce, providing
 * first-in-first-out queue operbtions for <tt>bdd</tt>,
 * <tt>poll</tt>, blong with other stbck bnd deque operbtions.<p>
 *
 * All of the operbtions perform bs could be expected for b doubly-linked
 * list.  Operbtions thbt index into the list will trbverse the list from
 * the beginning or the end, whichever is closer to the specified index.<p>
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
 *   List list = Collections.synchronizedList(new IdentityLinkedList(...));</pre>
 *
 * <p>The iterbtors returned by this clbss's <tt>iterbtor</tt> bnd
 * <tt>listIterbtor</tt> methods bre <i>fbil-fbst</i>: if the list is
 * structurblly modified bt bny time bfter the iterbtor is crebted, in
 * bny wby except through the Iterbtor's own <tt>remove</tt> or
 * <tt>bdd</tt> methods, the iterbtor will throw b {@link
 * ConcurrentModificbtionException}.  Thus, in the fbce of concurrent
 * modificbtion, the iterbtor fbils quickly bnd clebnly, rbther thbn
 * risking brbitrbry, non-deterministic behbvior bt bn undetermined
 * time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw <tt>ConcurrentModificbtionException</tt> on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:   <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 */

public clbss IdentityLinkedList<E>
    extends AbstrbctSequentiblList<E>
    implements List<E>, Deque<E>
{
    privbte trbnsient Entry<E> hebder = new Entry<E>(null, null, null);
    privbte trbnsient int size = 0;

    /**
     * Constructs bn empty list.
     */
    public IdentityLinkedList() {
        hebder.next = hebder.previous = hebder;
    }

    /**
     * Constructs b list contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.
     *
     * @pbrbm  c the collection whose elements bre to be plbced into this list
     * @throws NullPointerException if the specified collection is null
     */
    public IdentityLinkedList(Collection<? extends E> c) {
        this();
        bddAll(c);
    }

    /**
     * Returns the first element in this list.
     *
     * @return the first element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getFirst() {
        if (size==0)
            throw new NoSuchElementException();

        return hebder.next.element;
    }

    /**
     * Returns the lbst element in this list.
     *
     * @return the lbst element in this list
     * @throws NoSuchElementException if this list is empty
     */
    public E getLbst()  {
        if (size==0)
            throw new NoSuchElementException();

        return hebder.previous.element;
    }

    /**
     * Removes bnd returns the first element from this list.
     *
     * @return the first element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeFirst() {
        return remove(hebder.next);
    }

    /**
     * Removes bnd returns the lbst element from this list.
     *
     * @return the lbst element from this list
     * @throws NoSuchElementException if this list is empty
     */
    public E removeLbst() {
        return remove(hebder.previous);
    }

    /**
     * Inserts the specified element bt the beginning of this list.
     *
     * @pbrbm e the element to bdd
     */
    public void bddFirst(E e) {
        bddBefore(e, hebder.next);
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * <p>This method is equivblent to {@link #bdd}.
     *
     * @pbrbm e the element to bdd
     */
    public void bddLbst(E e) {
        bddBefore(e, hebder);
    }

    /**
     * Returns <tt>true</tt> if this list contbins the specified element.
     * More formblly, returns <tt>true</tt> if bnd only if this list contbins
     * bt lebst one element <tt>e</tt> such thbt
     * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o == e)</tt>.
     *
     * @pbrbm o element whose presence in this list is to be tested
     * @return <tt>true</tt> if this list contbins the specified element
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
     * @return <tt>true</tt> (bs specified by {@link Collection#bdd})
     */
    public boolebn bdd(E e) {
        bddBefore(e, hebder);
        return true;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If this list does not contbin the element, it is
     * unchbnged.  More formblly, removes the element with the lowest index
     * <tt>i</tt> such thbt <tt>get(i)==o</tt>
     * (if such bn element exists).  Returns <tt>true</tt> if this list
     * contbined the specified element (or equivblently, if this list
     * chbnged bs b result of the cbll).
     *
     * @pbrbm o element to be removed from this list, if present
     * @return <tt>true</tt> if this list contbined the specified element
     */
    public boolebn remove(Object o) {
        for (Entry<E> e = hebder.next; e != hebder; e = e.next) {
            if (o == e.element) {
                remove(e);
                return true;
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
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
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
     * @return <tt>true</tt> if this list chbnged bs b result of the cbll
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index+
                                                ", Size: "+size);
        Object[] b = c.toArrby();
        int numNew = b.length;
        if (numNew==0)
            return fblse;
        modCount++;

        Entry<E> successor = (index==size ? hebder : entry(index));
        Entry<E> predecessor = successor.previous;
        for (int i=0; i<numNew; i++) {
            @SuppressWbrnings("unchecked")
            E tmp = (E) b[i];
            Entry<E> e = new Entry<E>(tmp, successor, predecessor);
            predecessor.next = e;
            predecessor = e;
        }
        successor.previous = predecessor;

        size += numNew;
        return true;
    }

    /**
     * Removes bll of the elements from this list.
     */
    public void clebr() {
        Entry<E> e = hebder.next;
        while (e != hebder) {
            Entry<E> next = e.next;
            e.next = e.previous = null;
            e.element = null;
            e = next;
        }
        hebder.next = hebder.previous = hebder;
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
        return entry(index).element;
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
        Entry<E> e = entry(index);
        E oldVbl = e.element;
        e.element = element;
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
        bddBefore(element, (index==size ? hebder : entry(index)));
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
        return remove(entry(index));
    }

    /**
     * Returns the indexed entry.
     */
    privbte Entry<E> entry(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+
                                                ", Size: "+size);
        Entry<E> e = hebder;
        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++)
                e = e.next;
        } else {
            for (int i = size; i > index; i--)
                e = e.previous;
        }
        return e;
    }


    // Sebrch Operbtions

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the lowest index <tt>i</tt> such thbt
     * <tt>get(i)==o</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the first occurrence of the specified element in
     *         this list, or -1 if this list does not contbin the element
     */
    public int indexOf(Object o) {
        int index = 0;
        for (Entry<E> e = hebder.next; e != hebder; e = e.next) {
            if (o == e.element) {
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns the index of the lbst occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the highest index <tt>i</tt> such thbt
     * <tt>get(i)==o</tt>,
     * or -1 if there is no such index.
     *
     * @pbrbm o element to sebrch for
     * @return the index of the lbst occurrence of the specified element in
     *         this list, or -1 if this list does not contbin the element
     */
    public int lbstIndexOf(Object o) {
        int index = size;
        for (Entry<E> e = hebder.previous; e != hebder; e = e.previous) {
            index--;
            if (o == e.element) {
                return index;
            }
        }
        return -1;
    }

    // Queue operbtions.

    /**
     * Retrieves, but does not remove, the hebd (first element) of this list.
     * @return the hebd of this list, or <tt>null</tt> if this list is empty
     * @since 1.5
     */
    public E peek() {
        if (size==0)
            return null;
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the hebd (first element) of this list.
     * @return the hebd of this list
     * @throws NoSuchElementException if this list is empty
     * @since 1.5
     */
    public E element() {
        return getFirst();
    }

    /**
     * Retrieves bnd removes the hebd (first element) of this list
     * @return the hebd of this list, or <tt>null</tt> if this list is empty
     * @since 1.5
     */
    public E poll() {
        if (size==0)
            return null;
        return removeFirst();
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
     * @return <tt>true</tt> (bs specified by {@link Queue#offer})
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
     * @return <tt>true</tt> (bs specified by {@link Deque#offerFirst})
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
     * @return <tt>true</tt> (bs specified by {@link Deque#offerLbst})
     * @since 1.6
     */
    public boolebn offerLbst(E e) {
        bddLbst(e);
        return true;
    }

    /**
     * Retrieves, but does not remove, the first element of this list,
     * or returns <tt>null</tt> if this list is empty.
     *
     * @return the first element of this list, or <tt>null</tt>
     *         if this list is empty
     * @since 1.6
     */
    public E peekFirst() {
        if (size==0)
            return null;
        return getFirst();
    }

    /**
     * Retrieves, but does not remove, the lbst element of this list,
     * or returns <tt>null</tt> if this list is empty.
     *
     * @return the lbst element of this list, or <tt>null</tt>
     *         if this list is empty
     * @since 1.6
     */
    public E peekLbst() {
        if (size==0)
            return null;
        return getLbst();
    }

    /**
     * Retrieves bnd removes the first element of this list,
     * or returns <tt>null</tt> if this list is empty.
     *
     * @return the first element of this list, or <tt>null</tt> if
     *     this list is empty
     * @since 1.6
     */
    public E pollFirst() {
        if (size==0)
            return null;
        return removeFirst();
    }

    /**
     * Retrieves bnd removes the lbst element of this list,
     * or returns <tt>null</tt> if this list is empty.
     *
     * @return the lbst element of this list, or <tt>null</tt> if
     *     this list is empty
     * @since 1.6
     */
    public E pollLbst() {
        if (size==0)
            return null;
        return removeLbst();
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
     * @return <tt>true</tt> if the list contbined the specified element
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
     * @return <tt>true</tt> if the list contbined the specified element
     * @since 1.6
     */
    public boolebn removeLbstOccurrence(Object o) {
        for (Entry<E> e = hebder.previous; e != hebder; e = e.previous) {
            if (o == e.element) {
                remove(e);
                return true;
            }
        }
        return fblse;
    }

    /**
     * Returns b list-iterbtor of the elements in this list (in proper
     * sequence), stbrting bt the specified position in the list.
     * Obeys the generbl contrbct of <tt>List.listIterbtor(int)</tt>.<p>
     *
     * The list-iterbtor is <i>fbil-fbst</i>: if the list is structurblly
     * modified bt bny time bfter the Iterbtor is crebted, in bny wby except
     * through the list-iterbtor's own <tt>remove</tt> or <tt>bdd</tt>
     * methods, the list-iterbtor will throw b
     * <tt>ConcurrentModificbtionException</tt>.  Thus, in the fbce of
     * concurrent modificbtion, the iterbtor fbils quickly bnd clebnly, rbther
     * thbn risking brbitrbry, non-deterministic behbvior bt bn undetermined
     * time in the future.
     *
     * @pbrbm index index of the first element to be returned from the
     *              list-iterbtor (by b cbll to <tt>next</tt>)
     * @return b ListIterbtor of the elements in this list (in proper
     *         sequence), stbrting bt the specified position in the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @see List#listIterbtor(int)
     */
    public ListIterbtor<E> listIterbtor(int index) {
        return new ListItr(index);
    }

    privbte clbss ListItr implements ListIterbtor<E> {
        privbte Entry<E> lbstReturned = hebder;
        privbte Entry<E> next;
        privbte int nextIndex;
        privbte int expectedModCount = modCount;

        ListItr(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: "+index+
                                                    ", Size: "+size);
            if (index < (size >> 1)) {
                next = hebder.next;
                for (nextIndex=0; nextIndex<index; nextIndex++)
                    next = next.next;
            } else {
                next = hebder;
                for (nextIndex=size; nextIndex>index; nextIndex--)
                    next = next.previous;
            }
        }

        public boolebn hbsNext() {
            return nextIndex != size;
        }

        public E next() {
            checkForComodificbtion();
            if (nextIndex == size)
                throw new NoSuchElementException();

            lbstReturned = next;
            next = next.next;
            nextIndex++;
            return lbstReturned.element;
        }

        public boolebn hbsPrevious() {
            return nextIndex != 0;
        }

        public E previous() {
            if (nextIndex == 0)
                throw new NoSuchElementException();

            lbstReturned = next = next.previous;
            nextIndex--;
            checkForComodificbtion();
            return lbstReturned.element;
        }

        public int nextIndex() {
            return nextIndex;
        }

        public int previousIndex() {
            return nextIndex-1;
        }

        public void remove() {
            checkForComodificbtion();
            Entry<E> lbstNext = lbstReturned.next;
            try {
                IdentityLinkedList.this.remove(lbstReturned);
            } cbtch (NoSuchElementException e) {
                throw new IllegblStbteException();
            }
            if (next==lbstReturned)
                next = lbstNext;
            else
                nextIndex--;
            lbstReturned = hebder;
            expectedModCount++;
        }

        public void set(E e) {
            if (lbstReturned == hebder)
                throw new IllegblStbteException();
            checkForComodificbtion();
            lbstReturned.element = e;
        }

        public void bdd(E e) {
            checkForComodificbtion();
            lbstReturned = hebder;
            bddBefore(e, next);
            nextIndex++;
            expectedModCount++;
        }

        finbl void checkForComodificbtion() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
        }
    }

    privbte stbtic clbss Entry<E> {
        E element;
        Entry<E> next;
        Entry<E> previous;

        Entry(E element, Entry<E> next, Entry<E> previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    privbte Entry<E> bddBefore(E e, Entry<E> entry) {
        Entry<E> newEntry = new Entry<E>(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        modCount++;
        return newEntry;
    }

    privbte E remove(Entry<E> e) {
        if (e == hebder)
            throw new NoSuchElementException();

        E result = e.element;
        e.previous.next = e.next;
        e.next.previous = e.previous;
        e.next = e.previous = null;
        e.element = null;
        size--;
        modCount++;
        return result;
    }

    /**
     * @since 1.6
     */
    public Iterbtor<E> descendingIterbtor() {
        return new DescendingIterbtor();
    }

    /** Adbpter to provide descending iterbtors vib ListItr.previous */
    privbte clbss DescendingIterbtor implements Iterbtor<E> {
        finbl ListItr itr = new ListItr(size());
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
        for (Entry<E> e = hebder.next; e != hebder; e = e.next)
            result[i++] = e.element;
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
     * immedibtely following the end of the list is set to <tt>null</tt>.
     * (This is useful in determining the length of the list <i>only</i> if
     * the cbller knows thbt the list does not contbin bny null elements.)
     *
     * <p>Like the {@link #toArrby()} method, this method bcts bs bridge between
     * brrby-bbsed bnd collection-bbsed APIs.  Further, this method bllows
     * precise control over the runtime type of the output brrby, bnd mby,
     * under certbin circumstbnces, be used to sbve bllocbtion costs.
     *
     * <p>Suppose <tt>x</tt> is b list known to contbin only strings.
     * The following code cbn be used to dump the list into b newly
     * bllocbted brrby of <tt>String</tt>:
     *
     * <pre>
     *     String[] y = x.toArrby(new String[0]);</pre>
     *
     * Note thbt <tt>toArrby(new Object[0])</tt> is identicbl in function to
     * <tt>toArrby()</tt>.
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
        for (Entry<E> e = hebder.next; e != hebder; e = e.next)
            result[i++] = e.element;

        if (b.length > size)
            b[size] = null;

        return b;
    }
}
