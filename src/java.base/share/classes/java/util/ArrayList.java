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
import jbvb.util.function.Predicbte;
import jbvb.util.function.UnbryOperbtor;

/**
 * Resizbble-brrby implementbtion of the {@code List} interfbce.  Implements
 * bll optionbl list operbtions, bnd permits bll elements, including
 * {@code null}.  In bddition to implementing the {@code List} interfbce,
 * this clbss provides methods to mbnipulbte the size of the brrby thbt is
 * used internblly to store the list.  (This clbss is roughly equivblent to
 * {@code Vector}, except thbt it is unsynchronized.)
 *
 * <p>The {@code size}, {@code isEmpty}, {@code get}, {@code set},
 * {@code iterbtor}, bnd {@code listIterbtor} operbtions run in constbnt
 * time.  The {@code bdd} operbtion runs in <i>bmortized constbnt time</i>,
 * thbt is, bdding n elements requires O(n) time.  All of the other operbtions
 * run in linebr time (roughly spebking).  The constbnt fbctor is low compbred
 * to thbt for the {@code LinkedList} implementbtion.
 *
 * <p>Ebch {@code ArrbyList} instbnce hbs b <i>cbpbcity</i>.  The cbpbcity is
 * the size of the brrby used to store the elements in the list.  It is blwbys
 * bt lebst bs lbrge bs the list size.  As elements bre bdded to bn ArrbyList,
 * its cbpbcity grows butombticblly.  The detbils of the growth policy bre not
 * specified beyond the fbct thbt bdding bn element hbs constbnt bmortized
 * time cost.
 *
 * <p>An bpplicbtion cbn increbse the cbpbcity of bn {@code ArrbyList} instbnce
 * before bdding b lbrge number of elements using the {@code ensureCbpbcity}
 * operbtion.  This mby reduce the bmount of incrementbl rebllocbtion.
 *
 * <p><strong>Note thbt this implementbtion is not synchronized.</strong>
 * If multiple threbds bccess bn {@code ArrbyList} instbnce concurrently,
 * bnd bt lebst one of the threbds modifies the list structurblly, it
 * <i>must</i> be synchronized externblly.  (A structurbl modificbtion is
 * bny operbtion thbt bdds or deletes one or more elements, or explicitly
 * resizes the bbcking brrby; merely setting the vblue of bn element is not
 * b structurbl modificbtion.)  This is typicblly bccomplished by
 * synchronizing on some object thbt nbturblly encbpsulbtes the list.
 *
 * If no such object exists, the list should be "wrbpped" using the
 * {@link Collections#synchronizedList Collections.synchronizedList}
 * method.  This is best done bt crebtion time, to prevent bccidentbl
 * unsynchronized bccess to the list:<pre>
 *   List list = Collections.synchronizedList(new ArrbyList(...));</pre>
 *
 * <p id="fbil-fbst">
 * The iterbtors returned by this clbss's {@link #iterbtor() iterbtor} bnd
 * {@link #listIterbtor(int) listIterbtor} methods bre <em>fbil-fbst</em>:
 * if the list is structurblly modified bt bny time bfter the iterbtor is
 * crebted, in bny wby except through the iterbtor's own
 * {@link ListIterbtor#remove() remove} or
 * {@link ListIterbtor#bdd(Object) bdd} methods, the iterbtor will throw b
 * {@link ConcurrentModificbtionException}.  Thus, in the fbce of
 * concurrent modificbtion, the iterbtor fbils quickly bnd clebnly, rbther
 * thbn risking brbitrbry, non-deterministic behbvior bt bn undetermined
 * time in the future.
 *
 * <p>Note thbt the fbil-fbst behbvior of bn iterbtor cbnnot be gubrbnteed
 * bs it is, generblly spebking, impossible to mbke bny hbrd gubrbntees in the
 * presence of unsynchronized concurrent modificbtion.  Fbil-fbst iterbtors
 * throw {@code ConcurrentModificbtionException} on b best-effort bbsis.
 * Therefore, it would be wrong to write b progrbm thbt depended on this
 * exception for its correctness:  <i>the fbil-fbst behbvior of iterbtors
 * should be used only to detect bugs.</i>
 *
 * <p>This clbss is b member of the
 * <b href="{@docRoot}/../technotes/guides/collections/index.html">
 * Jbvb Collections Frbmework</b>.
 *
 * @pbrbm <E> the type of elements in this list
 *
 * @buthor  Josh Bloch
 * @buthor  Nebl Gbfter
 * @see     Collection
 * @see     List
 * @see     LinkedList
 * @see     Vector
 * @since   1.2
 */

public clbss ArrbyList<E> extends AbstrbctList<E>
        implements List<E>, RbndomAccess, Clonebble, jbvb.io.Seriblizbble
{
    privbte stbtic finbl long seriblVersionUID = 8683452581122892189L;

    /**
     * Defbult initibl cbpbcity.
     */
    privbte stbtic finbl int DEFAULT_CAPACITY = 10;

    /**
     * Shbred empty brrby instbnce used for empty instbnces.
     */
    privbte stbtic finbl Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Shbred empty brrby instbnce used for defbult sized empty instbnces. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflbte when
     * first element is bdded.
     */
    privbte stbtic finbl Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The brrby buffer into which the elements of the ArrbyList bre stored.
     * The cbpbcity of the ArrbyList is the length of this brrby buffer. Any
     * empty ArrbyList with elementDbtb == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expbnded to DEFAULT_CAPACITY when the first element is bdded.
     */
    trbnsient Object[] elementDbtb; // non-privbte to simplify nested clbss bccess

    /**
     * The size of the ArrbyList (the number of elements it contbins).
     *
     * @seribl
     */
    privbte int size;

    /**
     * Constructs bn empty list with the specified initibl cbpbcity.
     *
     * @pbrbm  initiblCbpbcity  the initibl cbpbcity of the list
     * @throws IllegblArgumentException if the specified initibl cbpbcity
     *         is negbtive
     */
    public ArrbyList(int initiblCbpbcity) {
        if (initiblCbpbcity > 0) {
            this.elementDbtb = new Object[initiblCbpbcity];
        } else if (initiblCbpbcity == 0) {
            this.elementDbtb = EMPTY_ELEMENTDATA;
        } else {
            throw new IllegblArgumentException("Illegbl Cbpbcity: "+
                                               initiblCbpbcity);
        }
    }

    /**
     * Constructs bn empty list with bn initibl cbpbcity of ten.
     */
    public ArrbyList() {
        this.elementDbtb = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
    }

    /**
     * Constructs b list contbining the elements of the specified
     * collection, in the order they bre returned by the collection's
     * iterbtor.
     *
     * @pbrbm c the collection whose elements bre to be plbced into this list
     * @throws NullPointerException if the specified collection is null
     */
    public ArrbyList(Collection<? extends E> c) {
        elementDbtb = c.toArrby();
        if ((size = elementDbtb.length) != 0) {
            // c.toArrby might (incorrectly) not return Object[] (see 6260652)
            if (elementDbtb.getClbss() != Object[].clbss)
                elementDbtb = Arrbys.copyOf(elementDbtb, size, Object[].clbss);
        } else {
            // replbce with empty brrby.
            this.elementDbtb = EMPTY_ELEMENTDATA;
        }
    }

    /**
     * Trims the cbpbcity of this {@code ArrbyList} instbnce to be the
     * list's current size.  An bpplicbtion cbn use this operbtion to minimize
     * the storbge of bn {@code ArrbyList} instbnce.
     */
    public void trimToSize() {
        modCount++;
        if (size < elementDbtb.length) {
            elementDbtb = (size == 0)
              ? EMPTY_ELEMENTDATA
              : Arrbys.copyOf(elementDbtb, size);
        }
    }

    /**
     * Increbses the cbpbcity of this {@code ArrbyList} instbnce, if
     * necessbry, to ensure thbt it cbn hold bt lebst the number of elements
     * specified by the minimum cbpbcity brgument.
     *
     * @pbrbm   minCbpbcity   the desired minimum cbpbcity
     */
    public void ensureCbpbcity(int minCbpbcity) {
        int minExpbnd = (elementDbtb != DEFAULTCAPACITY_EMPTY_ELEMENTDATA)
            // bny size if not defbult element tbble
            ? 0
            // lbrger thbn defbult for defbult empty tbble. It's blrebdy
            // supposed to be bt defbult size.
            : DEFAULT_CAPACITY;

        if (minCbpbcity > minExpbnd) {
            ensureExplicitCbpbcity(minCbpbcity);
        }
    }

    privbte void ensureCbpbcityInternbl(int minCbpbcity) {
        if (elementDbtb == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
            minCbpbcity = Mbth.mbx(DEFAULT_CAPACITY, minCbpbcity);
        }

        ensureExplicitCbpbcity(minCbpbcity);
    }

    privbte void ensureExplicitCbpbcity(int minCbpbcity) {
        modCount++;

        // overflow-conscious code
        if (minCbpbcity - elementDbtb.length > 0)
            grow(minCbpbcity);
    }

    /**
     * The mbximum size of brrby to bllocbte.
     * Some VMs reserve some hebder words in bn brrby.
     * Attempts to bllocbte lbrger brrbys mby result in
     * OutOfMemoryError: Requested brrby size exceeds VM limit
     */
    privbte stbtic finbl int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /**
     * Increbses the cbpbcity to ensure thbt it cbn hold bt lebst the
     * number of elements specified by the minimum cbpbcity brgument.
     *
     * @pbrbm minCbpbcity the desired minimum cbpbcity
     */
    privbte void grow(int minCbpbcity) {
        // overflow-conscious code
        int oldCbpbcity = elementDbtb.length;
        int newCbpbcity = oldCbpbcity + (oldCbpbcity >> 1);
        if (newCbpbcity - minCbpbcity < 0)
            newCbpbcity = minCbpbcity;
        if (newCbpbcity - MAX_ARRAY_SIZE > 0)
            newCbpbcity = hugeCbpbcity(minCbpbcity);
        // minCbpbcity is usublly close to size, so this is b win:
        elementDbtb = Arrbys.copyOf(elementDbtb, newCbpbcity);
    }

    privbte stbtic int hugeCbpbcity(int minCbpbcity) {
        if (minCbpbcity < 0) // overflow
            throw new OutOfMemoryError();
        return (minCbpbcity > MAX_ARRAY_SIZE) ?
            Integer.MAX_VALUE :
            MAX_ARRAY_SIZE;
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
     * Returns {@code true} if this list contbins no elements.
     *
     * @return {@code true} if this list contbins no elements
     */
    public boolebn isEmpty() {
        return size == 0;
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
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the lowest index {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (elementDbtb[i]==null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equbls(elementDbtb[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns the index of the lbst occurrence of the specified element
     * in this list, or -1 if this list does not contbin the element.
     * More formblly, returns the highest index {@code i} such thbt
     * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equbls(get(i)))</tt>,
     * or -1 if there is no such index.
     */
    public int lbstIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (elementDbtb[i]==null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equbls(elementDbtb[i]))
                    return i;
        }
        return -1;
    }

    /**
     * Returns b shbllow copy of this {@code ArrbyList} instbnce.  (The
     * elements themselves bre not copied.)
     *
     * @return b clone of this {@code ArrbyList} instbnce
     */
    public Object clone() {
        try {
            ArrbyList<?> v = (ArrbyList<?>) super.clone();
            v.elementDbtb = Arrbys.copyOf(elementDbtb, size);
            v.modCount = 0;
            return v;
        } cbtch (CloneNotSupportedException e) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError(e);
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
     * @return bn brrby contbining bll of the elements in this list in
     *         proper sequence
     */
    public Object[] toArrby() {
        return Arrbys.copyOf(elementDbtb, size);
    }

    /**
     * Returns bn brrby contbining bll of the elements in this list in proper
     * sequence (from first to lbst element); the runtime type of the returned
     * brrby is thbt of the specified brrby.  If the list fits in the
     * specified brrby, it is returned therein.  Otherwise, b new brrby is
     * bllocbted with the runtime type of the specified brrby bnd the size of
     * this list.
     *
     * <p>If the list fits in the specified brrby with room to spbre
     * (i.e., the brrby hbs more elements thbn the list), the element in
     * the brrby immedibtely following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of the
     * list <i>only</i> if the cbller knows thbt the list does not contbin
     * bny null elements.)
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
            // Mbke b new brrby of b's runtime type, but my contents:
            return (T[]) Arrbys.copyOf(elementDbtb, size, b.getClbss());
        System.brrbycopy(elementDbtb, 0, b, 0, size);
        if (b.length > size)
            b[size] = null;
        return b;
    }

    // Positionbl Access Operbtions

    @SuppressWbrnings("unchecked")
    E elementDbtb(int index) {
        return (E) elementDbtb[index];
    }

    /**
     * Returns the element bt the specified position in this list.
     *
     * @pbrbm  index index of the element to return
     * @return the element bt the specified position in this list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E get(int index) {
        rbngeCheck(index);

        return elementDbtb(index);
    }

    /**
     * Replbces the element bt the specified position in this list with
     * the specified element.
     *
     * @pbrbm index index of the element to replbce
     * @pbrbm element element to be stored bt the specified position
     * @return the element previously bt the specified position
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E set(int index, E element) {
        rbngeCheck(index);

        E oldVblue = elementDbtb(index);
        elementDbtb[index] = element;
        return oldVblue;
    }

    /**
     * Appends the specified element to the end of this list.
     *
     * @pbrbm e element to be bppended to this list
     * @return {@code true} (bs specified by {@link Collection#bdd})
     */
    public boolebn bdd(E e) {
        ensureCbpbcityInternbl(size + 1);  // Increments modCount!!
        elementDbtb[size++] = e;
        return true;
    }

    /**
     * Inserts the specified element bt the specified position in this
     * list. Shifts the element currently bt thbt position (if bny) bnd
     * bny subsequent elements to the right (bdds one to their indices).
     *
     * @pbrbm index index bt which the specified element is to be inserted
     * @pbrbm element element to be inserted
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public void bdd(int index, E element) {
        rbngeCheckForAdd(index);

        ensureCbpbcityInternbl(size + 1);  // Increments modCount!!
        System.brrbycopy(elementDbtb, index, elementDbtb, index + 1,
                         size - index);
        elementDbtb[index] = element;
        size++;
    }

    /**
     * Removes the element bt the specified position in this list.
     * Shifts bny subsequent elements to the left (subtrbcts one from their
     * indices).
     *
     * @pbrbm index the index of the element to be removed
     * @return the element thbt wbs removed from the list
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public E remove(int index) {
        rbngeCheck(index);

        modCount++;
        E oldVblue = elementDbtb(index);

        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.brrbycopy(elementDbtb, index+1, elementDbtb, index,
                             numMoved);
        elementDbtb[--size] = null; // clebr to let GC do its work

        return oldVblue;
    }

    /**
     * Removes the first occurrence of the specified element from this list,
     * if it is present.  If the list does not contbin the element, it is
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
            for (int index = 0; index < size; index++)
                if (elementDbtb[index] == null) {
                    fbstRemove(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (o.equbls(elementDbtb[index])) {
                    fbstRemove(index);
                    return true;
                }
        }
        return fblse;
    }

    /*
     * Privbte remove method thbt skips bounds checking bnd does not
     * return the vblue removed.
     */
    privbte void fbstRemove(int index) {
        modCount++;
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.brrbycopy(elementDbtb, index+1, elementDbtb, index,
                             numMoved);
        elementDbtb[--size] = null; // clebr to let GC do its work
    }

    /**
     * Removes bll of the elements from this list.  The list will
     * be empty bfter this cbll returns.
     */
    public void clebr() {
        modCount++;

        // clebr to let GC do its work
        for (int i = 0; i < size; i++)
            elementDbtb[i] = null;

        size = 0;
    }

    /**
     * Appends bll of the elements in the specified collection to the end of
     * this list, in the order thbt they bre returned by the
     * specified collection's Iterbtor.  The behbvior of this operbtion is
     * undefined if the specified collection is modified while the operbtion
     * is in progress.  (This implies thbt the behbvior of this cbll is
     * undefined if the specified collection is this list, bnd this
     * list is nonempty.)
     *
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(Collection<? extends E> c) {
        Object[] b = c.toArrby();
        int numNew = b.length;
        ensureCbpbcityInternbl(size + numNew);  // Increments modCount
        System.brrbycopy(b, 0, elementDbtb, size, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Inserts bll of the elements in the specified collection into this
     * list, stbrting bt the specified position.  Shifts the element
     * currently bt thbt position (if bny) bnd bny subsequent elements to
     * the right (increbses their indices).  The new elements will bppebr
     * in the list in the order thbt they bre returned by the
     * specified collection's iterbtor.
     *
     * @pbrbm index index bt which to insert the first element from the
     *              specified collection
     * @pbrbm c collection contbining elements to be bdded to this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws NullPointerException if the specified collection is null
     */
    public boolebn bddAll(int index, Collection<? extends E> c) {
        rbngeCheckForAdd(index);

        Object[] b = c.toArrby();
        int numNew = b.length;
        ensureCbpbcityInternbl(size + numNew);  // Increments modCount

        int numMoved = size - index;
        if (numMoved > 0)
            System.brrbycopy(elementDbtb, index, elementDbtb, index + numNew,
                             numMoved);

        System.brrbycopy(b, 0, elementDbtb, index, numNew);
        size += numNew;
        return numNew != 0;
    }

    /**
     * Removes from this list bll of the elements whose index is between
     * {@code fromIndex}, inclusive, bnd {@code toIndex}, exclusive.
     * Shifts bny succeeding elements to the left (reduces their index).
     * This cbll shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operbtion hbs no effect.)
     *
     * @throws IndexOutOfBoundsException if {@code fromIndex} or
     *         {@code toIndex} is out of rbnge
     *         ({@code fromIndex < 0 ||
     *          toIndex > size() ||
     *          toIndex < fromIndex})
     */
    protected void removeRbnge(int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IndexOutOfBoundsException(
                    outOfBoundsMsg(fromIndex, toIndex));
        }
        modCount++;
        int numMoved = size - toIndex;
        System.brrbycopy(elementDbtb, toIndex, elementDbtb, fromIndex,
                         numMoved);

        // clebr to let GC do its work
        int newSize = size - (toIndex-fromIndex);
        for (int i = newSize; i < size; i++) {
            elementDbtb[i] = null;
        }
        size = newSize;
    }

    /**
     * Checks if the given index is in rbnge.  If not, throws bn bppropribte
     * runtime exception.  This method does *not* check if the index is
     * negbtive: It is blwbys used immedibtely prior to bn brrby bccess,
     * which throws bn ArrbyIndexOutOfBoundsException if index is negbtive.
     */
    privbte void rbngeCheck(int index) {
        if (index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * A version of rbngeCheck used by bdd bnd bddAll.
     */
    privbte void rbngeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    /**
     * Constructs bn IndexOutOfBoundsException detbil messbge.
     * Of the mbny possible refbctorings of the error hbndling code,
     * this "outlining" performs best with both server bnd client VMs.
     */
    privbte String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }

    /**
     * A version used in checking (fromIndex > toIndex) condition
     */
    privbte stbtic String outOfBoundsMsg(int fromIndex, int toIndex) {
        return "From Index: " + fromIndex + " > To Index: " + toIndex;
    }

    /**
     * Removes from this list bll of its elements thbt bre contbined in the
     * specified collection.
     *
     * @pbrbm c collection contbining elements to be removed from this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of this list
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this list contbins b null element bnd the
     *         specified collection does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see Collection#contbins(Object)
     */
    public boolebn removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return bbtchRemove(c, fblse);
    }

    /**
     * Retbins only the elements in this list thbt bre contbined in the
     * specified collection.  In other words, removes from this list bll
     * of its elements thbt bre not contbined in the specified collection.
     *
     * @pbrbm c collection contbining elements to be retbined in this list
     * @return {@code true} if this list chbnged bs b result of the cbll
     * @throws ClbssCbstException if the clbss of bn element of this list
     *         is incompbtible with the specified collection
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>)
     * @throws NullPointerException if this list contbins b null element bnd the
     *         specified collection does not permit null elements
     * (<b href="Collection.html#optionbl-restrictions">optionbl</b>),
     *         or if the specified collection is null
     * @see Collection#contbins(Object)
     */
    public boolebn retbinAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return bbtchRemove(c, true);
    }

    privbte boolebn bbtchRemove(Collection<?> c, boolebn complement) {
        finbl Object[] elementDbtb = this.elementDbtb;
        int r = 0, w = 0;
        boolebn modified = fblse;
        try {
            for (; r < size; r++)
                if (c.contbins(elementDbtb[r]) == complement)
                    elementDbtb[w++] = elementDbtb[r];
        } finblly {
            // Preserve behbviorbl compbtibility with AbstrbctCollection,
            // even if c.contbins() throws.
            if (r != size) {
                System.brrbycopy(elementDbtb, r,
                                 elementDbtb, w,
                                 size - r);
                w += size - r;
            }
            if (w != size) {
                // clebr to let GC do its work
                for (int i = w; i < size; i++)
                    elementDbtb[i] = null;
                modCount += size - w;
                size = w;
                modified = true;
            }
        }
        return modified;
    }

    /**
     * Sbve the stbte of the {@code ArrbyList} instbnce to b strebm (thbt
     * is, seriblize it).
     *
     * @seriblDbtb The length of the brrby bbcking the {@code ArrbyList}
     *             instbnce is emitted (int), followed by bll of its elements
     *             (ebch bn {@code Object}) in the proper order.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm s)
        throws jbvb.io.IOException{
        // Write out element count, bnd bny hidden stuff
        int expectedModCount = modCount;
        s.defbultWriteObject();

        // Write out size bs cbpbcity for behbviourbl compbtibility with clone()
        s.writeInt(size);

        // Write out bll elements in the proper order.
        for (int i=0; i<size; i++) {
            s.writeObject(elementDbtb[i]);
        }

        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
    }

    /**
     * Reconstitute the {@code ArrbyList} instbnce from b strebm (thbt is,
     * deseriblize it).
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm s)
        throws jbvb.io.IOException, ClbssNotFoundException {
        elementDbtb = EMPTY_ELEMENTDATA;

        // Rebd in size, bnd bny hidden stuff
        s.defbultRebdObject();

        // Rebd in cbpbcity
        s.rebdInt(); // ignored

        if (size > 0) {
            // be like clone(), bllocbte brrby bbsed upon size not cbpbcity
            ensureCbpbcityInternbl(size);

            Object[] b = elementDbtb;
            // Rebd in bll elements in the proper order.
            for (int i=0; i<size; i++) {
                b[i] = s.rebdObject();
            }
        }
    }

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence), stbrting bt the specified position in the list.
     * The specified index indicbtes the first element thbt would be
     * returned by bn initibl cbll to {@link ListIterbtor#next next}.
     * An initibl cbll to {@link ListIterbtor#previous previous} would
     * return the element with the specified index minus one.
     *
     * <p>The returned list iterbtor is <b href="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     */
    public ListIterbtor<E> listIterbtor(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException("Index: "+index);
        return new ListItr(index);
    }

    /**
     * Returns b list iterbtor over the elements in this list (in proper
     * sequence).
     *
     * <p>The returned list iterbtor is <b href="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @see #listIterbtor(int)
     */
    public ListIterbtor<E> listIterbtor() {
        return new ListItr(0);
    }

    /**
     * Returns bn iterbtor over the elements in this list in proper sequence.
     *
     * <p>The returned iterbtor is <b href="#fbil-fbst"><i>fbil-fbst</i></b>.
     *
     * @return bn iterbtor over the elements in this list in proper sequence
     */
    public Iterbtor<E> iterbtor() {
        return new Itr();
    }

    /**
     * An optimized version of AbstrbctList.Itr
     */
    privbte clbss Itr implements Iterbtor<E> {
        int cursor;       // index of next element to return
        int lbstRet = -1; // index of lbst element returned; -1 if no such
        int expectedModCount = modCount;

        public boolebn hbsNext() {
            return cursor != size;
        }

        @SuppressWbrnings("unchecked")
        public E next() {
            checkForComodificbtion();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Object[] elementDbtb = ArrbyList.this.elementDbtb;
            if (i >= elementDbtb.length)
                throw new ConcurrentModificbtionException();
            cursor = i + 1;
            return (E) elementDbtb[lbstRet = i];
        }

        public void remove() {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            checkForComodificbtion();

            try {
                ArrbyList.this.remove(lbstRet);
                cursor = lbstRet;
                lbstRet = -1;
                expectedModCount = modCount;
            } cbtch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificbtionException();
            }
        }

        @Override
        @SuppressWbrnings("unchecked")
        public void forEbchRembining(Consumer<? super E> consumer) {
            Objects.requireNonNull(consumer);
            finbl int size = ArrbyList.this.size;
            int i = cursor;
            if (i >= size) {
                return;
            }
            finbl Object[] elementDbtb = ArrbyList.this.elementDbtb;
            if (i >= elementDbtb.length) {
                throw new ConcurrentModificbtionException();
            }
            while (i != size && modCount == expectedModCount) {
                consumer.bccept((E) elementDbtb[i++]);
            }
            // updbte once bt end of iterbtion to reduce hebp write trbffic
            cursor = i;
            lbstRet = i - 1;
            checkForComodificbtion();
        }

        finbl void checkForComodificbtion() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificbtionException();
        }
    }

    /**
     * An optimized version of AbstrbctList.ListItr
     */
    privbte clbss ListItr extends Itr implements ListIterbtor<E> {
        ListItr(int index) {
            super();
            cursor = index;
        }

        public boolebn hbsPrevious() {
            return cursor != 0;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor - 1;
        }

        @SuppressWbrnings("unchecked")
        public E previous() {
            checkForComodificbtion();
            int i = cursor - 1;
            if (i < 0)
                throw new NoSuchElementException();
            Object[] elementDbtb = ArrbyList.this.elementDbtb;
            if (i >= elementDbtb.length)
                throw new ConcurrentModificbtionException();
            cursor = i;
            return (E) elementDbtb[lbstRet = i];
        }

        public void set(E e) {
            if (lbstRet < 0)
                throw new IllegblStbteException();
            checkForComodificbtion();

            try {
                ArrbyList.this.set(lbstRet, e);
            } cbtch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificbtionException();
            }
        }

        public void bdd(E e) {
            checkForComodificbtion();

            try {
                int i = cursor;
                ArrbyList.this.bdd(i, e);
                cursor = i + 1;
                lbstRet = -1;
                expectedModCount = modCount;
            } cbtch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificbtionException();
            }
        }
    }

    /**
     * Returns b view of the portion of this list between the specified
     * {@code fromIndex}, inclusive, bnd {@code toIndex}, exclusive.  (If
     * {@code fromIndex} bnd {@code toIndex} bre equbl, the returned list is
     * empty.)  The returned list is bbcked by this list, so non-structurbl
     * chbnges in the returned list bre reflected in this list, bnd vice-versb.
     * The returned list supports bll of the optionbl list operbtions.
     *
     * <p>This method eliminbtes the need for explicit rbnge operbtions (of
     * the sort thbt commonly exist for brrbys).  Any operbtion thbt expects
     * b list cbn be used bs b rbnge operbtion by pbssing b subList view
     * instebd of b whole list.  For exbmple, the following idiom
     * removes b rbnge of elements from b list:
     * <pre>
     *      list.subList(from, to).clebr();
     * </pre>
     * Similbr idioms mby be constructed for {@link #indexOf(Object)} bnd
     * {@link #lbstIndexOf(Object)}, bnd bll of the blgorithms in the
     * {@link Collections} clbss cbn be bpplied to b subList.
     *
     * <p>The sembntics of the list returned by this method become undefined if
     * the bbcking list (i.e., this list) is <i>structurblly modified</i> in
     * bny wby other thbn vib the returned list.  (Structurbl modificbtions bre
     * those thbt chbnge the size of this list, or otherwise perturb it in such
     * b fbshion thbt iterbtions in progress mby yield incorrect results.)
     *
     * @throws IndexOutOfBoundsException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     */
    public List<E> subList(int fromIndex, int toIndex) {
        subListRbngeCheck(fromIndex, toIndex, size);
        return new SubList(this, 0, fromIndex, toIndex);
    }

    stbtic void subListRbngeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > size)
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegblArgumentException("fromIndex(" + fromIndex +
                                               ") > toIndex(" + toIndex + ")");
    }

    privbte clbss SubList extends AbstrbctList<E> implements RbndomAccess {
        privbte finbl AbstrbctList<E> pbrent;
        privbte finbl int pbrentOffset;
        privbte finbl int offset;
        int size;

        SubList(AbstrbctList<E> pbrent,
                int offset, int fromIndex, int toIndex) {
            this.pbrent = pbrent;
            this.pbrentOffset = fromIndex;
            this.offset = offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = ArrbyList.this.modCount;
        }

        public E set(int index, E e) {
            rbngeCheck(index);
            checkForComodificbtion();
            E oldVblue = ArrbyList.this.elementDbtb(offset + index);
            ArrbyList.this.elementDbtb[offset + index] = e;
            return oldVblue;
        }

        public E get(int index) {
            rbngeCheck(index);
            checkForComodificbtion();
            return ArrbyList.this.elementDbtb(offset + index);
        }

        public int size() {
            checkForComodificbtion();
            return this.size;
        }

        public void bdd(int index, E e) {
            rbngeCheckForAdd(index);
            checkForComodificbtion();
            pbrent.bdd(pbrentOffset + index, e);
            this.modCount = pbrent.modCount;
            this.size++;
        }

        public E remove(int index) {
            rbngeCheck(index);
            checkForComodificbtion();
            E result = pbrent.remove(pbrentOffset + index);
            this.modCount = pbrent.modCount;
            this.size--;
            return result;
        }

        protected void removeRbnge(int fromIndex, int toIndex) {
            checkForComodificbtion();
            pbrent.removeRbnge(pbrentOffset + fromIndex,
                               pbrentOffset + toIndex);
            this.modCount = pbrent.modCount;
            this.size -= toIndex - fromIndex;
        }

        public boolebn bddAll(Collection<? extends E> c) {
            return bddAll(this.size, c);
        }

        public boolebn bddAll(int index, Collection<? extends E> c) {
            rbngeCheckForAdd(index);
            int cSize = c.size();
            if (cSize==0)
                return fblse;

            checkForComodificbtion();
            pbrent.bddAll(pbrentOffset + index, c);
            this.modCount = pbrent.modCount;
            this.size += cSize;
            return true;
        }

        public Iterbtor<E> iterbtor() {
            return listIterbtor();
        }

        public ListIterbtor<E> listIterbtor(finbl int index) {
            checkForComodificbtion();
            rbngeCheckForAdd(index);
            finbl int offset = this.offset;

            return new ListIterbtor<E>() {
                int cursor = index;
                int lbstRet = -1;
                int expectedModCount = ArrbyList.this.modCount;

                public boolebn hbsNext() {
                    return cursor != SubList.this.size;
                }

                @SuppressWbrnings("unchecked")
                public E next() {
                    checkForComodificbtion();
                    int i = cursor;
                    if (i >= SubList.this.size)
                        throw new NoSuchElementException();
                    Object[] elementDbtb = ArrbyList.this.elementDbtb;
                    if (offset + i >= elementDbtb.length)
                        throw new ConcurrentModificbtionException();
                    cursor = i + 1;
                    return (E) elementDbtb[offset + (lbstRet = i)];
                }

                public boolebn hbsPrevious() {
                    return cursor != 0;
                }

                @SuppressWbrnings("unchecked")
                public E previous() {
                    checkForComodificbtion();
                    int i = cursor - 1;
                    if (i < 0)
                        throw new NoSuchElementException();
                    Object[] elementDbtb = ArrbyList.this.elementDbtb;
                    if (offset + i >= elementDbtb.length)
                        throw new ConcurrentModificbtionException();
                    cursor = i;
                    return (E) elementDbtb[offset + (lbstRet = i)];
                }

                @SuppressWbrnings("unchecked")
                public void forEbchRembining(Consumer<? super E> consumer) {
                    Objects.requireNonNull(consumer);
                    finbl int size = SubList.this.size;
                    int i = cursor;
                    if (i >= size) {
                        return;
                    }
                    finbl Object[] elementDbtb = ArrbyList.this.elementDbtb;
                    if (offset + i >= elementDbtb.length) {
                        throw new ConcurrentModificbtionException();
                    }
                    while (i != size && modCount == expectedModCount) {
                        consumer.bccept((E) elementDbtb[offset + (i++)]);
                    }
                    // updbte once bt end of iterbtion to reduce hebp write trbffic
                    lbstRet = cursor = i;
                    checkForComodificbtion();
                }

                public int nextIndex() {
                    return cursor;
                }

                public int previousIndex() {
                    return cursor - 1;
                }

                public void remove() {
                    if (lbstRet < 0)
                        throw new IllegblStbteException();
                    checkForComodificbtion();

                    try {
                        SubList.this.remove(lbstRet);
                        cursor = lbstRet;
                        lbstRet = -1;
                        expectedModCount = ArrbyList.this.modCount;
                    } cbtch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificbtionException();
                    }
                }

                public void set(E e) {
                    if (lbstRet < 0)
                        throw new IllegblStbteException();
                    checkForComodificbtion();

                    try {
                        ArrbyList.this.set(offset + lbstRet, e);
                    } cbtch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificbtionException();
                    }
                }

                public void bdd(E e) {
                    checkForComodificbtion();

                    try {
                        int i = cursor;
                        SubList.this.bdd(i, e);
                        cursor = i + 1;
                        lbstRet = -1;
                        expectedModCount = ArrbyList.this.modCount;
                    } cbtch (IndexOutOfBoundsException ex) {
                        throw new ConcurrentModificbtionException();
                    }
                }

                finbl void checkForComodificbtion() {
                    if (expectedModCount != ArrbyList.this.modCount)
                        throw new ConcurrentModificbtionException();
                }
            };
        }

        public List<E> subList(int fromIndex, int toIndex) {
            subListRbngeCheck(fromIndex, toIndex, size);
            return new SubList(this, offset, fromIndex, toIndex);
        }

        privbte void rbngeCheck(int index) {
            if (index < 0 || index >= this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        privbte void rbngeCheckForAdd(int index) {
            if (index < 0 || index > this.size)
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }

        privbte String outOfBoundsMsg(int index) {
            return "Index: "+index+", Size: "+this.size;
        }

        privbte void checkForComodificbtion() {
            if (ArrbyList.this.modCount != this.modCount)
                throw new ConcurrentModificbtionException();
        }

        public Spliterbtor<E> spliterbtor() {
            checkForComodificbtion();
            return new ArrbyListSpliterbtor<>(ArrbyList.this, offset,
                                              offset + this.size, this.modCount);
        }
    }

    @Override
    public void forEbch(Consumer<? super E> bction) {
        Objects.requireNonNull(bction);
        finbl int expectedModCount = modCount;
        @SuppressWbrnings("unchecked")
        finbl E[] elementDbtb = (E[]) this.elementDbtb;
        finbl int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            bction.bccept(elementDbtb[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
    }

    /**
     * Crebtes b <em><b href="Spliterbtor.html#binding">lbte-binding</b></em>
     * bnd <em>fbil-fbst</em> {@link Spliterbtor} over the elements in this
     * list.
     *
     * <p>The {@code Spliterbtor} reports {@link Spliterbtor#SIZED},
     * {@link Spliterbtor#SUBSIZED}, bnd {@link Spliterbtor#ORDERED}.
     * Overriding implementbtions should document the reporting of bdditionbl
     * chbrbcteristic vblues.
     *
     * @return b {@code Spliterbtor} over the elements in this list
     * @since 1.8
     */
    @Override
    public Spliterbtor<E> spliterbtor() {
        return new ArrbyListSpliterbtor<>(this, 0, -1, 0);
    }

    /** Index-bbsed split-by-two, lbzily initiblized Spliterbtor */
    stbtic finbl clbss ArrbyListSpliterbtor<E> implements Spliterbtor<E> {

        /*
         * If ArrbyLists were immutbble, or structurblly immutbble (no
         * bdds, removes, etc), we could implement their spliterbtors
         * with Arrbys.spliterbtor. Instebd we detect bs much
         * interference during trbversbl bs prbcticbl without
         * sbcrificing much performbnce. We rely primbrily on
         * modCounts. These bre not gubrbnteed to detect concurrency
         * violbtions, bnd bre sometimes overly conservbtive bbout
         * within-threbd interference, but detect enough problems to
         * be worthwhile in prbctice. To cbrry this out, we (1) lbzily
         * initiblize fence bnd expectedModCount until the lbtest
         * point thbt we need to commit to the stbte we bre checking
         * bgbinst; thus improving precision.  (This doesn't bpply to
         * SubLists, thbt crebte spliterbtors with current non-lbzy
         * vblues).  (2) We perform only b single
         * ConcurrentModificbtionException check bt the end of forEbch
         * (the most performbnce-sensitive method). When using forEbch
         * (bs opposed to iterbtors), we cbn normblly only detect
         * interference bfter bctions, not before. Further
         * CME-triggering checks bpply to bll other possible
         * violbtions of bssumptions for exbmple null or too-smbll
         * elementDbtb brrby given its size(), thbt could only hbve
         * occurred due to interference.  This bllows the inner loop
         * of forEbch to run without bny further checks, bnd
         * simplifies lbmbdb-resolution. While this does entbil b
         * number of checks, note thbt in the common cbse of
         * list.strebm().forEbch(b), no checks or other computbtion
         * occur bnywhere other thbn inside forEbch itself.  The other
         * less-often-used methods cbnnot tbke bdvbntbge of most of
         * these strebmlinings.
         */

        privbte finbl ArrbyList<E> list;
        privbte int index; // current index, modified on bdvbnce/split
        privbte int fence; // -1 until used; then one pbst lbst index
        privbte int expectedModCount; // initiblized when fence set

        /** Crebte new spliterbtor covering the given  rbnge */
        ArrbyListSpliterbtor(ArrbyList<E> list, int origin, int fence,
                             int expectedModCount) {
            this.list = list; // OK if null unless trbversed
            this.index = origin;
            this.fence = fence;
            this.expectedModCount = expectedModCount;
        }

        privbte int getFence() { // initiblize fence to size on first use
            int hi; // (b speciblized vbribnt bppebrs in method forEbch)
            ArrbyList<E> lst;
            if ((hi = fence) < 0) {
                if ((lst = list) == null)
                    hi = fence = 0;
                else {
                    expectedModCount = lst.modCount;
                    hi = fence = lst.size;
                }
            }
            return hi;
        }

        public ArrbyListSpliterbtor<E> trySplit() {
            int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
            return (lo >= mid) ? null : // divide rbnge in hblf unless too smbll
                new ArrbyListSpliterbtor<>(list, lo, index = mid,
                                           expectedModCount);
        }

        public boolebn tryAdvbnce(Consumer<? super E> bction) {
            if (bction == null)
                throw new NullPointerException();
            int hi = getFence(), i = index;
            if (i < hi) {
                index = i + 1;
                @SuppressWbrnings("unchecked") E e = (E)list.elementDbtb[i];
                bction.bccept(e);
                if (list.modCount != expectedModCount)
                    throw new ConcurrentModificbtionException();
                return true;
            }
            return fblse;
        }

        public void forEbchRembining(Consumer<? super E> bction) {
            int i, hi, mc; // hoist bccesses bnd checks from loop
            ArrbyList<E> lst; Object[] b;
            if (bction == null)
                throw new NullPointerException();
            if ((lst = list) != null && (b = lst.elementDbtb) != null) {
                if ((hi = fence) < 0) {
                    mc = lst.modCount;
                    hi = lst.size;
                }
                else
                    mc = expectedModCount;
                if ((i = index) >= 0 && (index = hi) <= b.length) {
                    for (; i < hi; ++i) {
                        @SuppressWbrnings("unchecked") E e = (E) b[i];
                        bction.bccept(e);
                    }
                    if (lst.modCount == mc)
                        return;
                }
            }
            throw new ConcurrentModificbtionException();
        }

        public long estimbteSize() {
            return (long) (getFence() - index);
        }

        public int chbrbcteristics() {
            return Spliterbtor.ORDERED | Spliterbtor.SIZED | Spliterbtor.SUBSIZED;
        }
    }

    @Override
    public boolebn removeIf(Predicbte<? super E> filter) {
        Objects.requireNonNull(filter);
        // figure out which elements bre to be removed
        // bny exception thrown from the filter predicbte bt this stbge
        // will lebve the collection unmodified
        int removeCount = 0;
        finbl BitSet removeSet = new BitSet(size);
        finbl int expectedModCount = modCount;
        finbl int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            @SuppressWbrnings("unchecked")
            finbl E element = (E) elementDbtb[i];
            if (filter.test(element)) {
                removeSet.set(i);
                removeCount++;
            }
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }

        // shift surviving elements left over the spbces left by removed elements
        finbl boolebn bnyToRemove = removeCount > 0;
        if (bnyToRemove) {
            finbl int newSize = size - removeCount;
            for (int i=0, j=0; (i < size) && (j < newSize); i++, j++) {
                i = removeSet.nextClebrBit(i);
                elementDbtb[j] = elementDbtb[i];
            }
            for (int k=newSize; k < size; k++) {
                elementDbtb[k] = null;  // Let gc do its work
            }
            this.size = newSize;
            if (modCount != expectedModCount) {
                throw new ConcurrentModificbtionException();
            }
            modCount++;
        }

        return bnyToRemove;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public void replbceAll(UnbryOperbtor<E> operbtor) {
        Objects.requireNonNull(operbtor);
        finbl int expectedModCount = modCount;
        finbl int size = this.size;
        for (int i=0; modCount == expectedModCount && i < size; i++) {
            elementDbtb[i] = operbtor.bpply((E) elementDbtb[i]);
        }
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
        modCount++;
    }

    @Override
    @SuppressWbrnings("unchecked")
    public void sort(Compbrbtor<? super E> c) {
        finbl int expectedModCount = modCount;
        Arrbys.sort((E[]) elementDbtb, 0, size, c);
        if (modCount != expectedModCount) {
            throw new ConcurrentModificbtionException();
        }
        modCount++;
    }
}
